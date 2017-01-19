package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import java.util.Vector;
import java.util.Collections;



public class RoundRobinAction {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
        String participantes=request.getParameter("participantes").substring(1);
        String torneo=request.getParameter("torneo");

		//lo pasamos a Vector para hacerle un shuffle
		Vector<String> teamsV=new Vector<String>();
		for(String participante:participantes.split("#"))
			teamsV.addElement(participante);
		Collections.shuffle(teamsV);

		//lo pasamos a String
		String[] teams=new String[teamsV.size()];
		for(int i=0;i<teamsV.size();i++)
			teams[i]=teamsV.elementAt(i);
		teamsV.clear();

		//roundrobin
		StringBuilder sb=new StringBuilder("insert into lebb_partidos values");
		String equipo1,equipo2;
		for(int j=1;j<=(teams.length-1)*Integer.parseInt(torneo.split("#")[1]);j++) {
		    for(int p=0;p<teams.length/2;p++) {
				int i=(p+j-1)%(teams.length/2);
				if(i==0)
					equipo1=teams[0];
				else
					equipo1=teams[(i+j-2)%(teams.length-1)+1];
				equipo2=teams[(teams.length-i+j-3)%(teams.length-1)+1];
				sb.append("('"+(j%2==1?equipo1:equipo2)+"','"+(j%2==1?equipo2:equipo1)+"','"+torneo.split("#")[0]+"',"+j+",0,0,0,0,0,0,0,0,0,'','','','',0,0,0,0,'sin jugar',0),");
			}
		}

		//query actualizacion equipos
		StringBuilder sb2=new StringBuilder("update lebb_equipos set competicion='"+torneo.split("#")[0]+"' where ");
		for(int i=0;i<teams.length;i++)
			sb2.append("equipo='"+teams[i]+"' or ");

		DBManager2 dbman=new DBManager2();
		dbman.execute(sb.substring(0,sb.length()-1));
		dbman.execute("insert into lebb_ligas values('"+torneo.split("#")[0]+"',"+torneo.split("#")[2]+","+torneo.split("#")[3]+","+torneo.split("#")[4]+","+torneo.split("#")[5]+","+torneo.split("#")[6]+")");
		dbman.execute(sb2.substring(0,sb2.length()-4));
		dbman.close();

        return "verpartidos.do?competicion="+torneo.split("#")[0];
    }

}
