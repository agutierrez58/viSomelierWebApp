package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import java.util.Hashtable;
import java.util.Vector;

public class VerPartidos {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
		String entrenador=(String)request.getSession().getAttribute("entrenador");
    String competicion=request.getParameter("competicion");


		DBManager2 dbman=new DBManager2();


		//seleccionamos mis equipos
		Vector<String> misequipos=new Vector<String>();
		ResultSet2 rsme;
		if(entrenador.equals("god"))
			rsme=dbman.executeQuery("select equipo from lebb_equipos where competicion='"+competicion+"'");
		else
			rsme=dbman.executeQuery("select equipo from lebb_equipos where entrenador='"+entrenador+"' and competicion='"+competicion+"'");
		while(rsme.next())
			misequipos.addElement(rsme.getString("equipo"));


		//calculamos el siguiente partido de cada equipo
		Hashtable<String,String> sigpartido=new Hashtable<String,String>();
		ResultSet2 rseq=dbman.executeQuery("select equipo from lebb_equipos where competicion='"+competicion+"'");
		String sig;
		while(rseq.next()) {
			sig=dbman.executeValue("select min(jornada) from lebb_partidos where competicion='"+competicion+"' and estado='sin jugar' and (equipo1='"+rseq.getString("equipo")+"' or equipo2='"+rseq.getString("equipo")+"')");
			if(sig==null) sig="0";
			sigpartido.put(rseq.getString("equipo"),sig);
		}
		ResultSet2 rspd=dbman.executeQuery("select equipo1,equipo2 from lebb_partidos where competicion='"+competicion+"' and estado like 'pendiente%'");
		while(rspd.next()) {
			sigpartido.remove(rspd.getString("equipo1"));
			sigpartido.put(rspd.getString("equipo1"),"0");
			sigpartido.remove(rspd.getString("equipo2"));
			sigpartido.put(rspd.getString("equipo2"),"0");
		}


		//recorremos los partidos y los ponemos en un ResultSet2
		//String campos="jornada,equipo1,equipo2,touchdowns1,touchdowns2,heridos1,heridos2,recaudacion1,recaudacion2,estado";
		String campos="jornada,equipo1,equipo2,e1.entrenador entrenador1,e2.entrenador entrenador2,e1.raza raza1,e2.raza raza2,touchdowns1,touchdowns2,heridos1,heridos2,recaudacion1,recaudacion2,estado";
		//ResultSet2 rset=dbman.executeQuery("select "+campos+" from lebb_partidos where competicion='"+competicion+"' order by jornada");
		ResultSet2 rset=dbman.executeQuery("select "+campos+" from lebb_partidos,lebb_equipos e1,lebb_equipos e2 where lebb_partidos.competicion='"+competicion+"' and equipo1=e1.equipo and equipo2=e2.equipo order by jornada");
		ResultSet2 partidos=new ResultSet2(new String[]{"jornada","equipo1","equipo2","entrenador1","entrenador2","raza1","raza2","td1","td2","hr1","hr2","re1","re2","estado"});
		String estado;
		while(rset.next()) {
			estado=rset.getString("estado");
			if(estado.equals("jugado")) {
				partidos.addRow(new String[]{rset.getString("jornada"),rset.getString("equipo1"),rset.getString("equipo2"),rset.getString("entrenador1"),rset.getString("entrenador2"),rset.getString("raza1"),rset.getString("raza2"),rset.getString("touchdowns1"),rset.getString("touchdowns2"),rset.getString("heridos1"),rset.getString("heridos2"),rset.getString("recaudacion1")+"k",rset.getString("recaudacion2")+"k",estado});
			} else if(estado.equals("sin jugar")) {
				if((sigpartido.get(rset.getString("equipo1")).equals(rset.getString("jornada")))&&(sigpartido.get(rset.getString("equipo2")).equals(rset.getString("jornada"))))
					if((misequipos.contains(rset.getString("equipo1")))||(misequipos.contains(rset.getString("equipo2"))))
						estado="introducir";
				partidos.addRow(new String[]{rset.getString("jornada"),rset.getString("equipo1"),rset.getString("equipo2"),rset.getString("entrenador1"),rset.getString("entrenador2"),rset.getString("raza1"),rset.getString("raza2"),"-","-","-","-","-","-",estado});
			} else {
				if(misequipos.contains(rset.getString("equipo"+rset.getString("estado").split("#")[1])))
					estado="verificar";
				partidos.addRow(new String[]{rset.getString("jornada"),rset.getString("equipo1"),rset.getString("equipo2"),rset.getString("entrenador1"),rset.getString("entrenador2"),rset.getString("raza1"),rset.getString("raza2"),"?","?","?","?","?","?",estado});
			}
		}
		sigpartido.clear();

		dbman.close();

		request.setAttribute("competicion",competicion);
        request.setAttribute("partidos",partidos);
        return "verpartidos.jsp";
    }

}
