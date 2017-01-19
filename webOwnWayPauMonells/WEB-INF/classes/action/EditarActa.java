package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import java.util.Vector;

public class EditarActa {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
    if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
		String competicion=request.getParameter("competicion");
		String jornada=request.getParameter("jornada");
		String equipo1=request.getParameter("equipo1");
		String equipo2=request.getParameter("equipo2");

		String page="entraracta.jsp";
		DBManager2 dbman=new DBManager2();

		/*//mirar si tu eres uno de esos equipos
		if(!entrenador.equals("god"))
			if(dbman.executeValue("select count(*) from lebb_equipos where entrenador='"+entrenador+"' and (equipo='"+equipo1+"' or equipo='"+equipo2+"')").equals("0"))
				page="main.do";
		//mirar si ese partido existe para modificar
		if(dbman.executeValue("select count(*) from lebb_partidos where jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"' and (estado='pendiente#1' or estado='pendiente#2')").equals("0"))
			page="main.do";
		//mirar si hay actas anteriores de esos equipos no ok
		if(!dbman.executeValue("select count(*) from lebb_partidos where estado<>'jugado' and jornada<"+jornada+" and (equipo1='"+equipo1+"' or equipo1='"+equipo2+"' or equipo2='"+equipo1+"' or equipo2='"+equipo2+"')").equals("0"))
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


		StringBuilder hanJugado1=new StringBuilder();
		StringBuilder hanJugado2=new StringBuilder();
		ResultSet2 rset;
		rset=dbman.executeQuery("select numero from lebb_jugadores where equipo='"+equipo1+"' and status='ok' order by numero");
		while(rset.next())
			hanJugado1.append(rset.getString("numero")+"#");
		rset=dbman.executeQuery("select numero from lebb_jugadores where equipo='"+equipo2+"' and status='ok' order by numero");
		while(rset.next())
			hanJugado2.append(rset.getString("numero")+"#");

		//conseguimos los datos ya introducidos
		ResultSet2 rs=dbman.executeQuery("select * from lebb_partidos where jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"'");
		rs.next();

		String repeticion=rs.getString("repeticion");

		Vector<String> datos1=new Vector<String>();
		datos1.addElement(rs.getString("touchdowns1"));
		datos1.addElement(rs.getString("heridos1"));
		datos1.addElement(rs.getString("fama1"));
		datos1.addElement(rs.getString("gastos1"));
		datos1.addElement(rs.getString("actuaciones1"));

		Vector<String> datos2=new Vector<String>();
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



		request.setAttribute("hanJugado",new String[]{hanJugado1+"*",hanJugado2+"*"});
		request.setAttribute("equipos",new String[]{equipo1,equipo2});
		request.setAttribute("entrenadores",new String[]{rser1.getString("entrenador"),rser2.getString("entrenador")});
		request.setAttribute("razas",new String[]{rser1.getString("raza"),rser2.getString("raza")});
		request.setAttribute("competicion",competicion);
		request.setAttribute("jornada",jornada);
		request.setAttribute("repeticion",repeticion);
		request.setAttribute("datos1",datos1);
		request.setAttribute("datos2",datos2);


        return page;
    }

}
