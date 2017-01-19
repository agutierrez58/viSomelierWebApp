package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;

public class Main {

  public static String think(HttpServletRequest request) throws Exception {

		DBManager2 dbman=new DBManager2();
    //ultimos 10 partidos
    ResultSet2 ultimos=dbman.executeQuery("select equipo1,equipo2,e1.raza raza1,e2.raza raza2,touchdowns1,touchdowns2,fecha,lebb_partidos.competicion,jornada from lebb_partidos,lebb_equipos e1,lebb_equipos e2 where equipo1=e1.equipo and equipo2=e2.equipo and estado='jugado' order by fecha desc limit 10");
		dbman.close();

    request.setAttribute("ultimos",ultimos);

    return "main.jsp";
  }

}
