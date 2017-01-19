package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Collections;
import bloodbowl.Parser;
import bloodbowl.Varios;

public class EditarEquipo {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
    if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
        String nombreequipo=request.getParameter("nombreequipo");

		DBManager2 dbman=new DBManager2();

		//el equipo es mio?
		if(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+nombreequipo+"' and entrenador='"+entrenador+"'").equals("0")) {
			dbman.close();
			return "main.do";
		}

		ResultSet2 equBD=dbman.executeQuery("select * from lebb_equipos where equipo='"+nombreequipo+"'");
		ResultSet2 jugBD=dbman.executeQuery("select * from lebb_jugadores where equipo='"+nombreequipo+"' and status<>'RIP' and status<>'fired' order by numero");
		equBD.next();

		Hashtable<String,String> XMLmov=Parser.getValuesRaza(equBD.getString("raza"),"mov");
		Hashtable<String,String> XMLfue=Parser.getValuesRaza(equBD.getString("raza"),"fue");
		Hashtable<String,String> XMLagi=Parser.getValuesRaza(equBD.getString("raza"),"agi");
		Hashtable<String,String> XMLarm=Parser.getValuesRaza(equBD.getString("raza"),"arm");
		Hashtable<String,String> XMLhabilidades=Parser.getValuesRaza(equBD.getString("raza"),"habilidades");
		Hashtable<String,String> XMLcoste=Parser.getValuesRaza(equBD.getString("raza"),"coste");
		Hashtable<String,String> XMLmejoras=Parser.getValuesRaza(equBD.getString("raza"),"mejoras");
		int teamrating=0;

		ResultSet2 jugadores=new ResultSet2(new String[]{"numero","nombre","posicion","mov","fue","agi","arm","habilidades","subidas","lesiones","pxcp","pxtd","pxin","pxcs","pxmvp","pxtotal","coste"});//,"status"});
        String[] row;
		while(jugBD.next()) {
	        row=new String[18];
			row[0]=jugBD.getString("numero");
			row[1]=jugBD.getString("nombre");
			row[2]=jugBD.getString("posicion");
			int[] ajustes=Varios.ajustesAtributos(jugBD.getString("subidas")+" "+jugBD.getString("lesiones"));
			row[3]=""+(Integer.parseInt(XMLmov.get(jugBD.getString("posicion")))+ajustes[0]);
			row[4]=""+(Integer.parseInt(XMLfue.get(jugBD.getString("posicion")))+ajustes[1]);
			row[5]=""+(Integer.parseInt(XMLagi.get(jugBD.getString("posicion")))+ajustes[2]);
			row[6]=""+(Integer.parseInt(XMLarm.get(jugBD.getString("posicion")))+ajustes[3]);
			row[7]=XMLhabilidades.get(jugBD.getString("posicion"));
			if(row[7].length()>0) row[7]+=" ";
			row[8]=jugBD.getString("subidas");
			if(row[8].length()>0) row[8]+=" ";
			row[9]=jugBD.getString("lesiones");
			if(row[9].length()>0) row[9]+=" ";
			row[10]=jugBD.getString("pxcp");
			row[11]=jugBD.getString("pxtd");
			row[12]=jugBD.getString("pxin");
			row[13]=jugBD.getString("pxcs");
			row[14]=jugBD.getString("pxmvp");
			row[15]=""+(Integer.parseInt(row[10])*1+Integer.parseInt(row[11])*3+Integer.parseInt(row[12])*2+Integer.parseInt(row[13])*2+Integer.parseInt(row[14])*5);
			if(jugBD.getString("status").equals("miss"))
				row[16]="miss";
			else {
				row[16]=""+(Integer.parseInt(XMLcoste.get(jugBD.getString("posicion")))+Varios.precioSubidas(jugBD.getString("subidas"),XMLmejoras.get(jugBD.getString("posicion"))));
				teamrating+=Integer.parseInt(row[16])/10;
			}
	        jugadores.addRow(row);
		}

		ResultSet2 equipo=new ResultSet2(new String[]{"equipo","entrenador","raza","teamrating","tesoreria","rerolls","fanfactor","ayudantes","animadoras","medico"});
		row=new String[10];
		row[0]=equBD.getString("equipo");
		row[1]=equBD.getString("entrenador");
		row[2]=equBD.getString("raza");
		row[3]=""+(teamrating+Integer.parseInt(equBD.getString("rerolls"))*Integer.parseInt(Parser.parseOtros(equBD.getString("raza")).split("#")[0])/10+Integer.parseInt(equBD.getString("fanfactor"))+Integer.parseInt(equBD.getString("ayudantes"))+Integer.parseInt(equBD.getString("animadoras")));
		if(equBD.getString("medico").equals("si")) row[3]=""+(Integer.parseInt(row[3])+5);
		row[4]=equBD.getString("tesoreria");
		row[5]=equBD.getString("rerolls");
		row[6]=equBD.getString("fanfactor");
		row[7]=equBD.getString("ayudantes");
		row[8]=equBD.getString("animadoras");
		row[9]=equBD.getString("medico");
		equipo.addRow(row);

		String raza=row[2];

		//obtener numeros disponibles
		Vector<Integer> numerosused=new Vector<Integer>();
		ResultSet2 rsnu=dbman.executeQuery("select numero from lebb_jugadores where equipo='"+nombreequipo+"' and status in ('ok','miss')");
		while(rsnu.next())
			numerosused.addElement(rsnu.getInt(1));
		rsnu.close();
		Vector<Integer> numerosdisp=new Vector<Integer>();
		for(int i=1;i<=16;i++)
			if(!numerosused.contains(i))
				numerosdisp.addElement(i);
		numerosused.clear();


		//obtener posiciones disponibles
		Hashtable<String,String> posnum=Parser.getValuesRaza(raza,"cantidad");
		Vector<String> posicionesdisp=new Vector<String>(posnum.keySet());
		ResultSet2 rspo=dbman.executeQuery("select posicion,count(*) from lebb_jugadores where equipo='"+nombreequipo+"' and status in ('ok','miss') group by posicion");
		while(rspo.next())
			if(rspo.getString(2).equals(posnum.get(rspo.getString("posicion"))))
				posicionesdisp.remove(rspo.getString("posicion"));
		rspo.next();
		rspo.close();
		posnum.clear();


		Vector<String[]> subidas=new Vector<String[]>();

		//query jugadores subidos
		ResultSet2 rssu=dbman.executeQuery("select numero,posicion,subidas from lebb_jugadores where equipo='"+nombreequipo+"' and subidas!=''");

		//habilidades de cada posicion
		Hashtable<String,String> hthp=Parser.getValuesRaza(raza,"habilidades");

		//mejoras de cada posicion
		Hashtable<String,String> htme=Parser.getValuesRaza(raza,"mejoras");

		//total de habilidades
		Hashtable<String,String> htha=Parser.parseHabilidades();
		//en vector ordenado
		Vector<String> vha=new Vector<String>(htha.keySet());
		Collections.sort(vha);

		//correspondencia categoria a numero
		Hashtable<String,Integer> htnc=new Hashtable<String,Integer>();
		htnc.put("gn",0);htnc.put("ag",1);htnc.put("st",2);htnc.put("ps",3);htnc.put("mt",4);

		//miramos cada jugador con subidas
		StringBuilder elecciones=new StringBuilder();
		while(rssu.next())
		    for(int i=0;i<rssu.getString("subidas").split(" ").length;i++)
		        if(rssu.getString("subidas").split(" ")[i].matches("[1-6][1-6]")) {
		            //contamplamos la subida de atributos
		            if(rssu.getString("subidas").split(" ")[i].charAt(0)+rssu.getString("subidas").split(" ")[i].charAt(1)-96==12)
		                elecciones.append("#+1Fu");
		            else if(rssu.getString("subidas").split(" ")[i].charAt(0)+rssu.getString("subidas").split(" ")[i].charAt(1)-96==11)
		                elecciones.append("#+1Ag");
		            else if(rssu.getString("subidas").split(" ")[i].charAt(0)+rssu.getString("subidas").split(" ")[i].charAt(1)-96==10)
		                elecciones.append("#+1Mo#+1Ar");
		            for(int a=0;a<vha.size();a++)
		                //si no tiene ya la habilidad
		                if(!(" "+hthp.get(rssu.getString("posicion"))+" "+rssu.getString("subidas")+" ").matches(".* "+vha.elementAt(a)+" .*"))
		                    //si es normal o doble y tiene derecho
		                    if((htme.get(rssu.getString("posicion")).charAt(htnc.get(htha.get(vha.elementAt(a))))=='1')||(rssu.getString("subidas").split(" ")[i].charAt(0)==rssu.getString("subidas").split(" ")[i].charAt(1))&&(htme.get(rssu.getString("posicion")).charAt(htnc.get(htha.get(vha.elementAt(a))))=='2'))
		                        elecciones.append("#"+vha.elementAt(a));
		            //out.println(rssu.getString("numero")+" "+rssu.getString("posicion")+" "+rssu.getString("subidas").split(" ")[i]+" "+elecciones+"<br>");
		            subidas.addElement(new String[]{rssu.getString("numero"),rssu.getString("posicion"),rssu.getString("subidas").split(" ")[i],elecciones.toString()});
		            elecciones.delete(0,elecciones.length());
		        }

		//limpieza
		rssu.close();
		hthp.clear();
		htme.clear();
		htha.clear();
		vha.clear();
		htnc.clear();


		dbman.close();


		//devolvemos parametros
		request.setAttribute("mode","edit");
        request.setAttribute("equipo",equipo);
        request.setAttribute("jugadores",jugadores);
		request.setAttribute("numerosdisp",numerosdisp);
		request.setAttribute("posicionesdisp",posicionesdisp);
		request.setAttribute("subidas",subidas);
		
        
        return "viewteam.jsp";
    }

}
