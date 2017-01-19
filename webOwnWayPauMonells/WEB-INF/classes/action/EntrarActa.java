package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;

public class EntrarActa {

    public static String think(HttpServletRequest request) throws Exception {

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
		//mirar si ese partido existe para introducir
		if(dbman.executeValue("select count(*) from lebb_partidos where jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"' and estado='sin jugar'").equals("0"))
			page="main.do";
		//mirar si hay actas anteriores de esos equipos no ok
		if(!dbman.executeValue("select count(*) from lebb_partidos where estado<>'jugado' and jornada<"+jornada+" and (equipo1='"+equipo1+"' or equipo1='"+equipo2+"' or equipo2='"+equipo1+"' or equipo2='"+equipo2+"')").equals("0"))
			page="main.do";*/


		if(dbman.executeValue("select count(*) from lebb_partidos where competicion='"+competicion+"' and jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"' and estado='sin jugar'").equals("0"))
			page="main.do";
		if(!dbman.executeValue("select count(*) from lebb_partidos where competicion='"+competicion+"' and jornada<"+jornada+" and (equipo1='"+equipo1+"' or equipo1='"+equipo2+"' or equipo2='"+equipo1+"' or equipo2='"+equipo2+"') and estado<>'jugado'").equals("0"))
			page="main.do";
		if(!entrenador.equals("god"))
			if(dbman.executeValue("select count(*) from lebb_equipos where entrenador='"+entrenador+"' and (equipo='"+equipo1+"' or equipo='"+equipo2+"')").equals("0"))
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


        return page;
    }

}
