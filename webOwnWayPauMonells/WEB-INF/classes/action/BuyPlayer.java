package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bloodbowl.Parser;
import java.util.Hashtable;

public class BuyPlayer {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
    if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
        String equipo=request.getParameter("equipo");
        String numero=request.getParameter("numero");
        String nombre=request.getParameter("nombre").replace("'","''");
        String posicion=request.getParameter("posicion");

		String page="main.do";
		DBManager2 dbman=new DBManager2();
		
		//si el equipo es tuyo
		if(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+equipo+"' and entrenador='"+entrenador+"'").equals("1"))
			//si no hay ningun jugador con ese numero
			if(dbman.executeValue("select count(*) from lebb_jugadores where equipo='"+equipo+"' and status in ('ok','miss') and numero="+numero).equals("0")) {
				String raza=dbman.executeValue("select raza from lebb_equipos where equipo='"+equipo+"'");
				Hashtable<String,String> posnum=Parser.getValuesRaza(raza,"cantidad");
				//si no tienes ya el maximo de posicion
				if(!posnum.get(posicion).equals("select count(*) from lebb_jugadores where equipo='"+equipo+"' and status in ('ok','miss') and posicion='"+posicion+"'")) {
					int precio=Integer.parseInt(Parser.getValuesRaza(raza,"coste").get(posicion));
					//si hay pasta suficiente
					if(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+equipo+"' and tesoreria >="+precio).equals("1")) {
						dbman.executeUpdate("update lebb_equipos set tesoreria=tesoreria-"+precio+" where equipo='"+equipo+"'");
						dbman.executeUpdate("insert into lebb_jugadores values("+numero+",'"+equipo+"','"+nombre+"','"+posicion+"','',0,0,0,0,0,'','ok')");
						page="editteam.do?nombreequipo="+equipo;
					}else
						page="editteam.do?nombreequipo="+equipo+"&error=no se puede comprar jugador.";
				}
			}
		
		dbman.close();

        return page;
    }

}
