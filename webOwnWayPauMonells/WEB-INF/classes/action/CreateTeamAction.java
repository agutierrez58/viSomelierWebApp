package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import bloodbowl.Parser;

public class CreateTeamAction {

    public static String think(HttpServletRequest request) throws Exception {

    		//recogemos parametros
        //if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        //String entrenador=(String)request.getSession().getAttribute("entrenador");
        String entrenador=request.getParameter("entrenador");
        String[] posicion=new String[16];
        String[] nombre=new String[16];
        for(int i=0;i<16;i++) {
            posicion[i]=request.getParameter("posicion"+(i+1));
            nombre[i]=request.getParameter("nombre"+(i+1)).replace("'","''");
        }
        String nombreequipo=request.getParameter("nombreequipo");
        String[] extras=request.getParameter("extras").split("#");

		//cuerpo
        DBManager2 dbman=new DBManager2();
        if(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+nombreequipo+"'").equals("0")) {
			dbman.executeUpdate("insert into lebb_equipos values('"+nombreequipo+"','"+extras[5]+"',"+Integer.parseInt(extras[0])+","+Integer.parseInt(extras[1])+","+Integer.parseInt(extras[2])+","+Integer.parseInt(extras[3])+",'"+extras[4]+"',"+Integer.parseInt(extras[6])+",'"+entrenador+"','')");
			StringBuilder sb=new StringBuilder("insert into lebb_jugadores values");
			for(int i=0;i<16;i++)
				if((nombre[i].length()>0)&&(posicion[i].length()>0))
					sb.append("("+(i+1)+",'"+nombreequipo+"','"+nombre[i]+"','"+posicion[i]+"','',0,0,0,0,0,'','ok'),");
			dbman.executeUpdate(sb.toString().substring(0,sb.length()-1));
		}
        ResultSet2 equipos=dbman.executeQuery("select * from lebb_equipos where entrenador='"+entrenador+"'");
        dbman.close();
        return "main.do";
    }

}
