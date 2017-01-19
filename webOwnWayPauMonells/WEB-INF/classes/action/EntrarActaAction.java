package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;

public class EntrarActaAction {

    public static String think(HttpServletRequest request) throws Exception {

    if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
		String resequ1=request.getParameter("resequ1");
		String resequ2=request.getParameter("resequ2");
		String resjug1=request.getParameter("resjug1");
		String resjug2=request.getParameter("resjug2");
		String global=request.getParameter("global");

		DBManager2 dbman=new DBManager2();

		String equipo1mio=dbman.executeValue("select count(*) from lebb_equipos where equipo='"+resequ1.split("#")[0]+"' and entrenador='"+entrenador+"'");
		String pendiente=equipo1mio.equals("1")?"2":"1";
		dbman.execute("update lebb_partidos set touchdowns1="+resequ1.split("#")[1]+",touchdowns2="+resequ2.split("#")[1]+",heridos1="+resequ1.split("#")[2]+",heridos2="+resequ2.split("#")[2]+",fama1="+resequ1.split("#")[3]+",fama2="+resequ2.split("#")[3]+",gastos1="+resequ1.split("#")[4]+",gastos2="+resequ2.split("#")[4]+",repeticion="+global.split("#")[2]+",actuaciones1='"+resjug1+"',actuaciones2='"+resjug2+"',estado='pendiente#"+pendiente+"' where equipo1='"+resequ1.split("#")[0]+"' and equipo2='"+resequ2.split("#")[0]+"' and competicion='"+global.split("#")[0]+"' and jornada="+global.split("#")[1]);

        dbman.close();

        return "verpartidos.do?competicion="+global.split("#")[0];
    }

}
