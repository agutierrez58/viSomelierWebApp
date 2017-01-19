package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import java.util.Vector;
import bloodbowl.Parser;

public class Estadisticas {

  public static String think(HttpServletRequest request) throws Exception {

    //recogemos parametros
    String competicion=request.getParameter("competicion")==null?"":request.getParameter("competicion");
    if(competicion.length()>0) competicion=" and competicion='"+competicion+"'";
    String raza=request.getParameter("raza")==null?"":request.getParameter("raza");
    if(raza.length()>0) raza=" and raza='"+raza+"'";
    String orderby=request.getParameter("orderby")==null?"totalpx":request.getParameter("orderby");
    //if(orderby.length()==0) orderby="totalpx";

		DBManager2 dbman=new DBManager2();

    Vector<String> competiciones=new Vector<String>();
    ResultSet2 rscom=dbman.executeQuery("select competicion from lebb_ligas order by competicion");
    while(rscom.next())
      competiciones.addElement(rscom.getString(1));
    rscom.close();
    
    Vector<String> razas=Parser.listaRazas();

    Vector<String[]> mejores=new Vector<String[]>();
    //System.out.println("select numero,nombre,posicion,lebb_jugadores.equipo,raza,competicion,subidas,lesiones,pxcp,pxtd,pxin,pxcs,pxmvp,pxcp+3*pxtd+2*pxin+2*pxcs+5*pxmvp totalpx,status from lebb_jugadores,lebb_equipos where lebb_jugadores.equipo=lebb_equipos.equipo and "+orderby+">0"+competicion+raza+" order by "+orderby.equals("totalpx")?"pxcp+3*pxtd+2*pxin+2*pxcs+5*pxmvp":totalpx+" desc limit 20");
    ResultSet2 rsmej=dbman.executeQuery("select numero,nombre,posicion,lebb_jugadores.equipo,raza,subidas,lesiones,pxcp,pxtd,pxin,pxcs,pxmvp,pxcp+3*pxtd+2*pxin+2*pxcs+5*pxmvp totalpx,status from lebb_jugadores,lebb_equipos where lebb_jugadores.equipo=lebb_equipos.equipo and "+(orderby.equals("totalpx")?"pxcp+3*pxtd+2*pxin+2*pxcs+5*pxmvp":orderby)+">0"+competicion+raza+" order by "+orderby+" desc limit 20");
    while(rsmej.next())
      mejores.addElement(new String[]{rsmej.getString(1),rsmej.getString(2),rsmej.getString(3),rsmej.getString(4),rsmej.getString(5),rsmej.getString(6),rsmej.getString(7),rsmej.getString(8),rsmej.getString(9),rsmej.getString(10),rsmej.getString(11),rsmej.getString(12),rsmej.getString(13),rsmej.getString(14)});
    rsmej.close();
    
    dbman.close();

    request.setAttribute("competiciones",competiciones);
    request.setAttribute("razas",razas);
    request.setAttribute("mejores",mejores);
    return "estadisticas.jsp";
  }

}
