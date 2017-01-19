package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;


public class Historial {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
        String equipo=request.getParameter("nombreequipo");

		DBManager2 dbman=new DBManager2();

		//recorremos los partidos jugados de ese equipo
		String campos="jornada,equipo1,equipo2,e1.entrenador entrenador1,e2.entrenador entrenador2,e1.raza raza1,e2.raza raza2,touchdowns1,touchdowns2,heridos1,heridos2,recaudacion1,recaudacion2,estado";
		ResultSet2 rset=dbman.executeQuery("select "+campos+" from lebb_partidos,lebb_equipos e1,lebb_equipos e2 where equipo1=e1.equipo and equipo2=e2.equipo and estado='jugado' and (equipo1='"+equipo+"' or equipo2='"+equipo+"')order by jornada");
		ResultSet2 partidos=new ResultSet2(new String[]{"jornada","equipo1","equipo2","entrenador1","entrenador2","raza1","raza2","td1","td2","hr1","hr2","re1","re2","estado"});
		while(rset.next())
			partidos.addRow(new String[]{rset.getString("jornada"),rset.getString("equipo1"),rset.getString("equipo2"),rset.getString("entrenador1"),rset.getString("entrenador2"),rset.getString("raza1"),rset.getString("raza2"),rset.getString("touchdowns1"),rset.getString("touchdowns2"),rset.getString("heridos1"),rset.getString("heridos2"),rset.getString("recaudacion1")+"k",rset.getString("recaudacion2")+"k",rset.getString("estado")});
		String competicion=dbman.executeValue("select competicion from lebb_equipos where equipo='"+equipo+"'");

		dbman.close();

        request.setAttribute("partidos",partidos);
        request.setAttribute("competicion",competicion);
        request.setAttribute("equipo",equipo);
        return "verpartidos.jsp";
    }

}
