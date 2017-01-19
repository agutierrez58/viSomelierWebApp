package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import java.util.Vector;

public class AceptarActa {

    public static String think(HttpServletRequest request) throws Exception {

if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
		String competicion=request.getParameter("competicion");
		String jornada=request.getParameter("jornada");
		String equipo1=request.getParameter("equipo1");
		String equipo2=request.getParameter("equipo2");

		String page="aceptaracta.jsp";
		DBManager2 dbman=new DBManager2();

		/*ResultSet2 rsp=dbman.executeQuery("select equipo1,equipo2,estado from lebb_partidos where jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"' and estado like 'pendiente%'");
		if(rsp.next()) {
			String equipo=rsp.getString("equipo"+rsp.getString("estado").split("#")[1]);
			if((!entrenador.equals("god"))&&(dbman.executeValue("select count(*) from lebb_equipos where entrenador='"+entrenador+"' and equipo='"+equipo+"'").equals("0")))
				page="main.do";
		} else
			page="main.do";*/


		if(dbman.executeValue("select count(*) from lebb_partidos where competicion='"+competicion+"' and jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"' and (estado='pendiente#1' or estado='pendiente#2')").equals("0"))
			page="main.do";
		else if(!entrenador.equals("god"))
			if(dbman.executeValue("select count(*) from lebb_equipos where entrenador='"+entrenador+"' and equipo='"+request.getParameter("equipo"+dbman.executeValue("select estado from lebb_partidos where competicion='"+competicion+"' and jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"'").split("#")[1])+"'").equals("0"))
				page="main.do";

		if(page.equals("main.do")) {
			dbman.close();
			return page;
		}


		ResultSet2 rs=dbman.executeQuery("select * from lebb_partidos where jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"'");
		rs.next();

		String repeticion=rs.getString("repeticion");

		Vector<String> datos1=new Vector<String>();
		datos1.addElement(equipo1);
		datos1.addElement(rs.getString("touchdowns1"));
		datos1.addElement(rs.getString("heridos1"));
		datos1.addElement(rs.getString("fama1"));
		datos1.addElement(rs.getString("gastos1"));
		datos1.addElement(rs.getString("actuaciones1"));

		Vector<String> datos2=new Vector<String>();
		datos2.addElement(equipo2);
		datos2.addElement(rs.getString("touchdowns2"));
		datos2.addElement(rs.getString("heridos2"));
		datos2.addElement(rs.getString("fama2"));
		datos2.addElement(rs.getString("gastos2"));
		datos2.addElement(rs.getString("actuaciones2"));

		ResultSet2 rser1=dbman.executeQuery("select entrenador,raza from lebb_equipos where equipo='"+equipo1+"'");
		rser1.next();
		ResultSet2 rser2=dbman.executeQuery("select entrenador,raza from lebb_equipos where equipo='"+equipo2+"'");
		rser2.next();

		dbman.close();

		request.setAttribute("competicion",competicion);
		request.setAttribute("jornada",jornada);
		request.setAttribute("entrenador1",rser1.getString("entrenador"));
		request.setAttribute("entrenador2",rser2.getString("entrenador"));
		request.setAttribute("raza1",rser1.getString("raza"));
		request.setAttribute("raza2",rser2.getString("raza"));
		request.setAttribute("datos1",datos1);
		request.setAttribute("datos2",datos2);
		request.setAttribute("repeticion",repeticion);

        return page;
    }

}
