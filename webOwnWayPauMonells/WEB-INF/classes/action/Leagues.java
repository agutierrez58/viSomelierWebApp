package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;


public class Leagues {

    public static String think(HttpServletRequest request) throws Exception {

        DBManager2 dbman=new DBManager2();

        //ResultSet2 rsligas=dbman.executeQuery("select lebb_ligas.competicion,count(*) numplayers from lebb_ligas,lebb_equipos where lebb_ligas.competicion=lebb_equipos.competicion group by lebb_ligas.competicion");
        //ResultSet2 rsligas=dbman.executeQuery("select lebb_ligas.competicion,count(*) numplayers,min(jornada) jornada from lebb_ligas,lebb_equipos,lebb_partidos where lebb_ligas.competicion=lebb_equipos.competicion and lebb_ligas.competicion=lebb_partidos.competicion and estado!='jugado' group by lebb_ligas.competicion");
        ResultSet2 rsligas=dbman.executeQuery("select lebb_ligas.competicion,count(distinct equipo) numplayers,min(jornada) jornada from lebb_ligas,lebb_equipos,lebb_partidos where lebb_ligas.competicion=lebb_equipos.competicion and lebb_ligas.competicion=lebb_partidos.competicion and estado!='jugado' group by lebb_ligas.competicion");

        ResultSet2 rsterminadas=dbman.executeQuery("select competicion from lebb_ligas where competicion not in (select competicion from lebb_partidos where estado!='jugado' group by competicion)");

        dbman.close();
     
        request.setAttribute("ligas",rsligas);
        request.setAttribute("terminadas",rsterminadas);

        return "leagues.jsp";
    }

}
