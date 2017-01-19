package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bloodbowl.Parser;

public class BuyOthers {

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
			String raza=dbman.executeValue("select raza from lebb_equipos where equipo='"+equipo+"'");
			int dinero=Integer.parseInt(dbman.executeValue("select tesoreria from lebb_equipos where equipo='"+equipo+"'"));
			//dbman.executeUpdate("update lebb_jugadores set status='fired' where status in ('ok','miss') and equipo='"+equipo+"' and numero="+numero);
			if(what.equals("reroll")) {
				int precio=2*Integer.parseInt(Parser.parseOtros(raza).split("#")[0]);
				int rerolls=Integer.parseInt(dbman.executeValue("select rerolls from lebb_equipos where equipo='"+equipo+"'"));
				if((rerolls==8)||(dinero<precio))
					page+="&error=no se puede comprar reroll.";
				else {
					dbman.executeUpdate("update lebb_equipos set rerolls=rerolls+1,tesoreria=tesoreria-"+precio+" where equipo='"+equipo+"'");
				}
			} else if(what.equals("ayudante")) {
				if(dinero<10)
					page+="&error=no se puede comprar ayudante.";
				else
					dbman.executeUpdate("update lebb_equipos set ayudantes=ayudantes+1,tesoreria=tesoreria-10 where equipo='"+equipo+"'");
			} else if(what.equals("animadora")) {
				if(dinero<10)
					page+="&error=no se puede comprar animadora.";
				else
					dbman.executeUpdate("update lebb_equipos set animadoras=animadoras+1,tesoreria=tesoreria-10 where equipo='"+equipo+"'");
			} else if(what.equals("medico")) {
				String disponible=Parser.parseOtros(raza).split("#")[1];
				String medico=dbman.executeValue("select medico from lebb_equipos where equipo='"+equipo+"'");
				if((disponible.equals("no"))||(medico.equals("si"))||(dinero<50))
					page+="&error=no se puede comprar medico.";
				else
					dbman.executeUpdate("update lebb_equipos set medico='si',tesoreria=tesoreria-50 where equipo='"+equipo+"'");
			}
		}
		dbman.close();

        return page;
    }

}
