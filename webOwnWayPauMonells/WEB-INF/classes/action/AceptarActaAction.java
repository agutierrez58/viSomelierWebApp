package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import bdatos.ResultSet2;
import bloodbowl.Dado;
import java.util.Hashtable;

public class AceptarActaAction {

    public static String think(HttpServletRequest request) throws Exception {


		//recogemos parametros
    if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
		String competicion=request.getParameter("competicion");
		String jornada=request.getParameter("jornada");
		String equipo1=request.getParameter("equipo1");
		String equipo2=request.getParameter("equipo2");


		//String page="verpartidos.do?competicion="+competicion;
		String page="veracta.do?competicion="+competicion+"&jornada="+jornada+"&equipo1="+equipo1+"&equipo2="+equipo2;
		DBManager2 dbman=new DBManager2();
		//control permisos
		ResultSet2 rsp=dbman.executeQuery("select * from lebb_partidos where competicion='"+competicion+"' and jornada='"+jornada+"' and equipo1='"+equipo1+"' and equipo2='"+equipo2+"' and estado like 'pendiente%'");
		if(!rsp.next())
			page="main.do";

		if(page.equals("main.do")) {
			dbman.close();
			return page;
		}


		//ya tenemos el registro del partido, cogemos tambien los de los equipos a modificar
		ResultSet2 rsft1=dbman.executeQuery("select fanfactor,tesoreria from lebb_equipos where equipo='"+equipo1+"'");
		rsft1.next();
		ResultSet2 rsft2=dbman.executeQuery("select fanfactor,tesoreria from lebb_equipos where equipo='"+equipo2+"'");
		rsft2.next();


		//campo tiradas
		StringBuilder tiradas1=new StringBuilder();
		StringBuilder tiradas2=new StringBuilder();


		//calcular recaudaciones, teniendo en cuenta empatar/ganar repeticion y fama
		int recaudacion1=Dado.tiraDado(6);
		tiradas1.append(recaudacion1);
		if((rsp.getInt("touchdowns1")>rsp.getInt("touchdowns2"))&&(recaudacion1<rsp.getInt("repeticion"))) {
			recaudacion1=Dado.tiraDado(6);
			tiradas1.append(recaudacion1);
		}
		if(rsp.getInt("touchdowns1")>=rsp.getInt("touchdowns2"))
			recaudacion1+=1;
		recaudacion1=(recaudacion1+rsp.getInt("fama1"))*10;
		tiradas1.append(".");

		int recaudacion2=Dado.tiraDado(6);
		tiradas2.append(recaudacion2);
		if((rsp.getInt("touchdowns2")>rsp.getInt("touchdowns1"))&&(recaudacion2<rsp.getInt("repeticion"))) {
			recaudacion2=Dado.tiraDado(6);
			tiradas2.append(recaudacion2);
		}
		if(rsp.getInt("touchdowns2")>=rsp.getInt("touchdowns1"))
			recaudacion2+=1;
		recaudacion2=(recaudacion2+rsp.getInt("fama2"))*10;
		tiradas2.append(".");


		//tirar fan factor
		int tirada;
		int total;

		//total=Dado.tiraDado(6)+Dado.tiraDado(6);
		tirada=Dado.tiraDado(6);
		tiradas1.append(tirada);
		total=tirada;
		tirada=Dado.tiraDado(6);
		tiradas1.append(tirada);
		total+=tirada;
		int ff1=rsft1.getInt("fanfactor");
		int ffmod1=0;
		if(rsp.getInt("touchdowns1")>rsp.getInt("touchdowns2")) {
			tirada=Dado.tiraDado(6);
			tiradas1.append(tirada);
			total+=tirada;
		}
		if((total>ff1)&&(rsp.getInt("touchdowns1")>=rsp.getInt("touchdowns2")))
			ffmod1=1;
		else if((total<ff1)&&(rsp.getInt("touchdowns1")<=rsp.getInt("touchdowns2")))
			ffmod1=-1;
		ff1+=ffmod1;
		if(ff1==-1)
			ff1=0;


		//total=Dado.tiraDado(6)+Dado.tiraDado(6);
		tirada=Dado.tiraDado(6);
		tiradas2.append(tirada);
		total=tirada;
		tirada=Dado.tiraDado(6);
		tiradas2.append(tirada);
		total+=tirada;
		int ff2=rsft2.getInt("fanfactor");
		int ffmod2=0;
		if(rsp.getInt("touchdowns2")>rsp.getInt("touchdowns1")) {
			tirada=Dado.tiraDado(6);
			tiradas2.append(tirada);
			total+=tirada;
		}
		if((total>ff2)&&(rsp.getInt("touchdowns2")>=rsp.getInt("touchdowns1")))
			ffmod2=1;
		else if((total<ff2)&&(rsp.getInt("touchdowns2")<=rsp.getInt("touchdowns1")))
			ffmod2=-1;
		ff2+=ffmod2;
		if(ff2==-1)
			ff2=0;



		//actualizamos equipos
		dbman.executeUpdate("update lebb_equipos set fanfactor="+ff1+",tesoreria="+(rsft1.getInt("tesoreria")+recaudacion1-rsp.getInt("gastos1"))+" where equipo='"+equipo1+"'");
		dbman.executeUpdate("update lebb_equipos set fanfactor="+ff2+",tesoreria="+(rsft2.getInt("tesoreria")+recaudacion2-rsp.getInt("gastos2"))+" where equipo='"+equipo2+"'");
		//recuperamos heridos
		dbman.executeUpdate("update lebb_jugadores set status='ok' where status='miss' and (equipo='"+equipo1+"' or equipo='"+equipo2+"')");

		//modificamos jugadores
		Hashtable<String,String> newstatus=new Hashtable<String,String>();
		newstatus.put("","ok");
		newstatus.put("miss","miss");
		newstatus.put("Lp","miss");
		newstatus.put("-1Mo","miss");
		newstatus.put("-1Fu","miss");
		newstatus.put("-1Ag","miss");
		newstatus.put("-1Ar","miss");
		newstatus.put("RIP","RIP");
		Hashtable<String,String> newlesion=new Hashtable<String,String>();
		newlesion.put("","");
		newlesion.put("miss","");
		newlesion.put("Lp","Lp");
		newlesion.put("-1Mo","-1Mo");
		newlesion.put("-1Fu","-1Fu");
		newlesion.put("-1Ag","-1Ag");
		newlesion.put("-1Ar","-1Ar");
		newlesion.put("RIP","");
		String actuaciones;
		String lesion;
		actuaciones=rsp.getString("actuaciones1");
		for(int i=1;i<actuaciones.split("#").length;i++) {
		lesion=(actuaciones.split("#")[i].split(":")[1]+" ").split(",")[5].trim();
		dbman.executeUpdate("update lebb_jugadores set pxcp=pxcp+"+actuaciones.split("#")[i].split(":")[1].split(",")[0]+",pxtd=pxtd+"+actuaciones.split("#")[i].split(":")[1].split(",")[1]+",pxin=pxin+"+actuaciones.split("#")[i].split(":")[1].split(",")[2]+",pxcs=pxcs+"+actuaciones.split("#")[i].split(":")[1].split(",")[3]+",pxmvp=pxmvp+"+actuaciones.split("#")[i].split(":")[1].split(",")[4]+",lesiones=trim(concat(lesiones,' "+newlesion.get(lesion)+"')),status='"+newstatus.get(lesion)+"' where equipo='"+equipo1+"' and status!='RIP' and numero="+actuaciones.split("#")[i].split(":")[0]);
		}
		actuaciones=rsp.getString("actuaciones2");
		for(int i=1;i<actuaciones.split("#").length;i++) {
		lesion=(actuaciones.split("#")[i].split(":")[1]+" ").split(",")[5].trim();
		dbman.executeUpdate("update lebb_jugadores set pxcp=pxcp+"+actuaciones.split("#")[i].split(":")[1].split(",")[0]+",pxtd=pxtd+"+actuaciones.split("#")[i].split(":")[1].split(",")[1]+",pxin=pxin+"+actuaciones.split("#")[i].split(":")[1].split(",")[2]+",pxcs=pxcs+"+actuaciones.split("#")[i].split(":")[1].split(",")[3]+",pxmvp=pxmvp+"+actuaciones.split("#")[i].split(":")[1].split(",")[4]+",lesiones=trim(concat(lesiones,' "+newlesion.get(lesion)+"')),status='"+newstatus.get(lesion)+"' where equipo='"+equipo2+"' and status!='RIP' and numero="+actuaciones.split("#")[i].split(":")[0]);
		}
		newstatus.clear();
		newlesion.clear();


		//miramos quien ha subido
		int numsub;
		String subida;
		String subidas;
		ResultSet2 rsjs=dbman.executeQuery("select 1*pxcp+3*pxtd+2*pxin+2*pxcs+5*pxmvp pxtotal,subidas,length(subidas)-length(replace(subidas,' ',''))+if(length(subidas)>0,1,0) numsubidas,numero,equipo from lebb_jugadores where status!='RIP' and (equipo='"+equipo1+"' or equipo='"+equipo2+"') order by numero");
		while(rsjs.next()) {
			numsub=(rsjs.getInt("pxtotal")>=6?1:0)+(rsjs.getInt("pxtotal")>=16?1:0)+(rsjs.getInt("pxtotal")>=31?1:0)+(rsjs.getInt("pxtotal")>=51?1:0)+(rsjs.getInt("pxtotal")>=76?1:0)+(rsjs.getInt("pxtotal")>=176?1:0);
			subidas="";
			for(int i=0;i<numsub-rsjs.getInt("numsubidas");i++) {
				subida=""+Dado.tiraDado(6)+Dado.tiraDado(6);
				subidas+=" "+subida;
				if(rsjs.getString("equipo").equals(equipo1))
					tiradas1.append("."+rsjs.getInt("numero")+":"+subida);
				else
					tiradas2.append("."+rsjs.getInt("numero")+":"+subida);
			}
			if(subidas.length()>0) {
				if(rsjs.getInt("numsubidas")==0)
					subidas=subidas.trim();
				dbman.executeUpdate("update lebb_jugadores set subidas=concat(subidas,'"+subidas+"') where numero="+rsjs.getString("numero")+" and equipo='"+rsjs.getString("equipo")+"' and status!='RIP'");
			}
		}

        //actualizamos partido
        dbman.executeUpdate("update lebb_partidos set recaudacion1="+recaudacion1+",recaudacion2="+recaudacion2+",fanfactor1="+ffmod1+",fanfactor2="+ffmod2+",estado='jugado',tiradas1='"+tiradas1+"',tiradas2='"+tiradas2+"',fecha="+System.currentTimeMillis()+" where competicion='"+competicion+"' and jornada="+jornada+" and equipo1='"+equipo1+"' and equipo2='"+equipo2+"'");


//log.Log5.write(tiradas1.toString());
//log.Log5.write(tiradas2.toString());


        dbman.close();

        return page;
    }

}
