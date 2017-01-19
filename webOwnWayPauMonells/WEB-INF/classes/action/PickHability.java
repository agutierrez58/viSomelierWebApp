package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;


public class PickHability {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
    if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
        String equipo=request.getParameter("equipo");
        String numero=request.getParameter("numero");
        String tirada=request.getParameter("tirada");
        String hability=request.getParameter("hability").replace(" ","+");
        //System.out.println(hability);

		String page="editteam.do?nombreequipo="+equipo;
		
		DBManager2 dbman=new DBManager2();
		
		//si el equipo es tuyo
		if(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+equipo+"' and entrenador='"+entrenador+"'").equals("0"))
			page="main.do";
		else {
			ResultSet2 rs=dbman.executeQuery("select subidas from lebb_jugadores where status in ('ok','miss') and equipo='"+equipo+"' and numero="+numero);
			rs.next();
			dbman.executeUpdate("update lebb_jugadores set subidas='"+rs.getString(1).replaceFirst(tirada,hability)+"' where status in ('ok','miss') and equipo='"+equipo+"' and numero="+numero);
			//System.out.println("update lebb_jugadores set subidas='"+rs.getString(1).replaceFirst(tirada,hability)+"' where status in ('ok','miss') and equipo='"+equipo+"' and numero="+numero);
			rs.close();
		}

		dbman.close();


        return page;
    }

}
