package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import java.util.Hashtable;
import bloodbowl.Parser;
import bloodbowl.Varios;

public class VerEquipo {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
        //String entrenador=(String)request.getSession().getAttribute("entrenador");
        String nombreequipo=request.getParameter("nombreequipo");

		DBManager2 dbman=new DBManager2();
		ResultSet2 equBD=dbman.executeQuery("select * from lebb_equipos where equipo='"+nombreequipo+"'");
		ResultSet2 jugBD=dbman.executeQuery("select * from lebb_jugadores where equipo='"+nombreequipo+"' and status<>'RIP' and status<>'fired' order by numero");
		dbman.close();
		equBD.next();

		Hashtable<String,String> XMLmov=Parser.getValuesRaza(equBD.getString("raza"),"mov");
		Hashtable<String,String> XMLfue=Parser.getValuesRaza(equBD.getString("raza"),"fue");
		Hashtable<String,String> XMLagi=Parser.getValuesRaza(equBD.getString("raza"),"agi");
		Hashtable<String,String> XMLarm=Parser.getValuesRaza(equBD.getString("raza"),"arm");
		Hashtable<String,String> XMLhabilidades=Parser.getValuesRaza(equBD.getString("raza"),"habilidades");
		Hashtable<String,String> XMLcoste=Parser.getValuesRaza(equBD.getString("raza"),"coste");
		Hashtable<String,String> XMLmejoras=Parser.getValuesRaza(equBD.getString("raza"),"mejoras");
		int teamrating=0;
		int totalpx=0;

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
			totalpx+=Integer.parseInt(row[15]);
			if(jugBD.getString("status").equals("miss"))
				row[16]="miss";
			else {
				row[16]=""+(Integer.parseInt(XMLcoste.get(jugBD.getString("posicion")))+Varios.precioSubidas(jugBD.getString("subidas"),XMLmejoras.get(jugBD.getString("posicion"))));
				teamrating+=Integer.parseInt(row[16])/10;
			}
	        jugadores.addRow(row);
		}

		ResultSet2 equipo=new ResultSet2(new String[]{"equipo","entrenador","raza","teamrating","tesoreria","rerolls","fanfactor","ayudantes","animadoras","medico","competicion"});
		row=new String[11];
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
		row[10]=equBD.getString("competicion");
		equipo.addRow(row);

		//devolvemos parametros
		request.setAttribute("mode","view");
        request.setAttribute("equipo",equipo);
        request.setAttribute("jugadores",jugadores);
        request.setAttribute("totalpx",""+totalpx);
        return "viewteam.jsp";
    }

}
