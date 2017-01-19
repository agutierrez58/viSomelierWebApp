package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import java.util.Vector;
import java.util.Hashtable;

public class VerClasificacion {

    public static String think(HttpServletRequest request) throws Exception {

        //recogemos parametros
        String competicion=request.getParameter("competicion");

        //variables a calcular
        Hashtable<String,int[]> resultados=new java.util.Hashtable<String,int[]>();
        Hashtable<String,String[]> equipos=new java.util.Hashtable<String,String[]>();
        Vector<String> sinordenar=new java.util.Vector<String>();
        Vector<String> ordenados=new java.util.Vector<String>();
        StringBuilder le=new StringBuilder();

        //conectamos
        DBManager2 dbman=new DBManager2();

        //datos de la liga
        ResultSet2 rsl=dbman.executeQuery("select * from lebb_ligas where competicion='"+competicion+"'");
        rsl.next();
        //equipos de la liga
        ResultSet2 rse=dbman.executeQuery("select distinct(equipo1) from lebb_partidos where competicion='"+competicion+"' and estado='jugado' union select distinct(equipo2) from lebb_partidos where competicion='"+competicion+"'");
        while(rse.next()) {
            resultados.put(rse.getString(1),new int[]{0,0,0,0,0,0,0,0});
            sinordenar.addElement(rse.getString(1));
            le.append(",'"+rse.getString(1)+"'");
        }
        rse.close();

        //datos equipos
        ResultSet2 rses=dbman.executeQuery("select equipo,raza,entrenador from lebb_equipos where equipo in("+le.substring(1)+")");
        while(rses.next())
            equipos.put(rses.getString("equipo"),new String[]{rses.getString("raza"),rses.getString("entrenador")});
        rses.close();

        //recorrer partidos
        ResultSet2 rsp=dbman.executeQuery("select equipo1,equipo2,touchdowns1,touchdowns2,heridos1,heridos2 from lebb_partidos where competicion='"+competicion+"' and estado='jugado'");
        while(rsp.next()) {
            //sumar victorias, empates, derrotas, y puntos
            if(rsp.getInt("touchdowns1")>rsp.getInt("touchdowns2")+1) {
                resultados.get(rsp.getString("equipo1"))[0]++;
                resultados.get(rsp.getString("equipo2"))[2]++;
                resultados.get(rsp.getString("equipo1"))[7]+=rsl.getInt("v2");
                resultados.get(rsp.getString("equipo2"))[7]+=rsl.getInt("d2");
            } else if(rsp.getInt("touchdowns1")>rsp.getInt("touchdowns2")) {
                resultados.get(rsp.getString("equipo1"))[0]++;
                resultados.get(rsp.getString("equipo2"))[2]++;
                resultados.get(rsp.getString("equipo1"))[7]+=rsl.getInt("v1");
                resultados.get(rsp.getString("equipo2"))[7]+=rsl.getInt("d1");
            } else if(rsp.getInt("touchdowns2")>rsp.getInt("touchdowns1")+1) {
                resultados.get(rsp.getString("equipo1"))[2]++;
                resultados.get(rsp.getString("equipo2"))[0]++;
                resultados.get(rsp.getString("equipo1"))[7]+=rsl.getInt("d2");
                resultados.get(rsp.getString("equipo2"))[7]+=rsl.getInt("v2");
            } else if(rsp.getInt("touchdowns2")>rsp.getInt("touchdowns1")) {
                resultados.get(rsp.getString("equipo1"))[2]++;
                resultados.get(rsp.getString("equipo2"))[0]++;
                resultados.get(rsp.getString("equipo1"))[7]+=rsl.getInt("d1");
                resultados.get(rsp.getString("equipo2"))[7]+=rsl.getInt("v1");
            } else {
                resultados.get(rsp.getString("equipo1"))[1]++;
                resultados.get(rsp.getString("equipo2"))[1]++;
                resultados.get(rsp.getString("equipo1"))[7]+=rsl.getInt("em");
                resultados.get(rsp.getString("equipo2"))[7]+=rsl.getInt("em");
            }
            //sumar touchdowns
            resultados.get(rsp.getString("equipo1"))[3]+=rsp.getInt("touchdowns1");
            resultados.get(rsp.getString("equipo2"))[3]+=rsp.getInt("touchdowns2");
            resultados.get(rsp.getString("equipo1"))[4]+=rsp.getInt("touchdowns2");
            resultados.get(rsp.getString("equipo2"))[4]+=rsp.getInt("touchdowns1");
            //sumar heridos
            resultados.get(rsp.getString("equipo1"))[5]+=rsp.getInt("heridos1");
            resultados.get(rsp.getString("equipo2"))[5]+=rsp.getInt("heridos2");
            resultados.get(rsp.getString("equipo1"))[6]+=rsp.getInt("heridos2");
            resultados.get(rsp.getString("equipo2"))[6]+=rsp.getInt("heridos1");
        }
        rsp.close();

        //cerramos conexion
        dbman.close();

        //calculamos la ordenacion
        String mayor;
        int filas=sinordenar.size();
        for(int f=0;f<filas;f++) {
            mayor=null;
            for(int e=0;e<sinordenar.size();e++) {
                if(mayor==null)
                    mayor=sinordenar.elementAt(e);
                else
                    if(resultados.get(sinordenar.elementAt(e))[7]>resultados.get(mayor)[7])
                        mayor=sinordenar.elementAt(e);
                    else if(resultados.get(sinordenar.elementAt(e))[7]==resultados.get(mayor)[7])
                        if(resultados.get(sinordenar.elementAt(e))[3]-resultados.get(sinordenar.elementAt(e))[4]>resultados.get(mayor)[3]-resultados.get(mayor)[4])
                            mayor=sinordenar.elementAt(e);
                        else if(resultados.get(sinordenar.elementAt(e))[3]-resultados.get(sinordenar.elementAt(e))[4]==resultados.get(mayor)[3]-resultados.get(mayor)[4])
                            if(resultados.get(sinordenar.elementAt(e))[5]-resultados.get(sinordenar.elementAt(e))[6]>resultados.get(mayor)[5]-resultados.get(mayor)[6])
                                mayor=sinordenar.elementAt(e);
                            else if(resultados.get(sinordenar.elementAt(e))[5]-resultados.get(sinordenar.elementAt(e))[6]==resultados.get(mayor)[5]-resultados.get(mayor)[6])
                                if(resultados.get(sinordenar.elementAt(e))[3]>resultados.get(mayor)[3])
                                    mayor=sinordenar.elementAt(e);
                                else if(resultados.get(sinordenar.elementAt(e))[3]==resultados.get(mayor)[3])
                                    if(resultados.get(sinordenar.elementAt(e))[5]>resultados.get(mayor)[5])
                                        mayor=sinordenar.elementAt(e);
            }
            ordenados.addElement(mayor);
            sinordenar.remove(mayor);
        }

        //devolvemos parametros
        request.setAttribute("competicion",competicion);
        request.setAttribute("ordenados",ordenados);
        request.setAttribute("equipos",equipos);
        request.setAttribute("resultados",resultados);
        return "verclasificacion.jsp";
    }

}
