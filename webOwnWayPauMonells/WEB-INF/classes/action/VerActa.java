package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;

public class VerActa {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
		String competicion=request.getParameter("competicion");
		String jornada=request.getParameter("jornada");
		String equipo1=request.getParameter("equipo1");
		String equipo2=request.getParameter("equipo2");

        String page="main.do";

        DBManager2 dbman=new DBManager2();

        ResultSet2 rs=dbman.executeQuery("select e1.entrenador entrenador1,e2.entrenador entrenador2,equipo1,equipo2,e1.raza raza1,e2.raza raza2,p.competicion,jornada,touchdowns1,touchdowns2,heridos1,heridos2,fama1,fama2,gastos1,gastos2,repeticion,actuaciones1,actuaciones2,tiradas1,tiradas2,recaudacion1,recaudacion2,fanfactor1,fanfactor2 from lebb_partidos p,lebb_equipos e1,lebb_equipos e2 where equipo1=e1.equipo and equipo2=e2.equipo and estado='jugado' and p.competicion='"+competicion+"' and jornada="+jornada+" and equipo1='"+equipo1+"' and equipo2='"+equipo2+"'");
        if(rs.next()) {
			page="veracta.jsp";
			request.setAttribute("competicion",competicion);
			request.setAttribute("jornada",jornada);
			request.setAttribute("partido",rs.toHashtable());
		}
		rs.close();
		dbman.close();

        return page;
    }
}

