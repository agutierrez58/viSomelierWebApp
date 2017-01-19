package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;

public class FireOthers {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
            if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");

        String entrenador=(String)request.getSession().getAttribute("entrenador");
        String equipo=request.getParameter("equipo");
        String what=request.getParameter("what");

		String page="editteam.do?nombreequipo="+equipo;

		DBManager2 dbman=new DBManager2();
		if(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+equipo+"' and entrenador='"+entrenador+"'").equals("0"))
			page="main.do";
		else {
			if(what.equals("medico"))
				if(dbman.executeValue("select medico from lebb_equipos where equipo='"+equipo+"'").equals("no"))
					page+="&error=no se puede despedir "+what+".";
				else
					dbman.executeUpdate("update lebb_equipos set medico='no' where equipo='"+equipo+"'");
			else
				if(dbman.executeValue("select "+what+"s from lebb_equipos where equipo='"+equipo+"'").equals("0"))
					page+="&error=no se puede despedir "+what+".";
				else
					dbman.executeUpdate("update lebb_equipos set "+what+"s="+what+"s-1 where equipo='"+equipo+"'");
		}
		dbman.close();

        return page;
    }

}
