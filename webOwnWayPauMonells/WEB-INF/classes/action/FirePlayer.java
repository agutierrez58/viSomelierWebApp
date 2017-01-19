package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;

public class FirePlayer {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
            if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");

        String entrenador=(String)request.getSession().getAttribute("entrenador");
        String equipo=request.getParameter("equipo");
        String numero=request.getParameter("numero");

		String page="editteam.do?nombreequipo="+equipo;

		DBManager2 dbman=new DBManager2();
		if(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+equipo+"' and entrenador='"+entrenador+"'").equals("0"))
			page="main.do";
		else
			dbman.executeUpdate("update lebb_jugadores set status='fired' where status in ('ok','miss') and equipo='"+equipo+"' and numero="+numero);

		dbman.close();

        return page;
    }

}
