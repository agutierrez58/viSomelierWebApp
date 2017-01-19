package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;

public class MyTeams {

    public static String think(HttpServletRequest request) throws Exception {

        if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");

        DBManager2 dbman=new DBManager2();
        ResultSet2 rsequipos=dbman.executeQuery("select equipo,raza,competicion from lebb_equipos where entrenador='"+entrenador+"'");
        dbman.close();

        request.setAttribute("equipos",rsequipos);

        return "myteams.jsp";
    }

}
