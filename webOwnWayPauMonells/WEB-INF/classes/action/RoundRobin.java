package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;

public class RoundRobin {

    public static String think(HttpServletRequest request) throws Exception {
        DBManager2 dbman=new DBManager2();
        ResultSet2 disponibles=dbman.executeQuery("select equipo,raza,entrenador from lebb_equipos where competicion=''");
        dbman.close();
        request.setAttribute("disponibles",disponibles);
        return "roundrobin.jsp";
    }

}
