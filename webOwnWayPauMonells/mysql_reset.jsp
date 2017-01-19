<link rel="stylesheet" href="css/mysql.css">

<%bdatos.DBManager2 dbman=new bdatos.DBManager2();%>

<%=dbman.executeClever("drop table lebb_entrenadores")%><br>
<%=dbman.executeClever("drop table lebb_equipos")%><br>
<%=dbman.executeClever("drop table lebb_jugadores")%><br>
<%=dbman.executeClever("drop table lebb_ligas")%><br>
<%=dbman.executeClever("drop table lebb_partidos")%><br>
<br>
<%=dbman.executeClever("create table lebb_entrenadores (entrenador varchar(20),password tinytext)")%><br>
<%=dbman.executeClever("create table lebb_equipos (equipo varchar(20),raza varchar(20),rerolls tinyint,fanfactor tinyint,ayudantes tinyint,animadoras tinyint,medico varchar(2),tesoreria mediumint,entrenador varchar(20),competicion varchar(20))")%><br>
<%=dbman.executeClever("create table lebb_jugadores (numero tinyint,equipo varchar(20),nombre varchar(20),posicion varchar(20),subidas tinytext,pxcp tinyint,pxtd tinyint,pxin tinyint,pxcs tinyint,pxmvp tinyint,lesiones tinytext,status varchar(20))")%><br>
<%=dbman.executeClever("create table lebb_ligas (competicion varchar(30),v2 tinyint,v1 tinyint,em tinyint,d1 tinyint,d2 tinyint)")%><br>
<%=dbman.executeClever("create table lebb_partidos (equipo1 varchar(20),equipo2 varchar(20),competicion varchar(30),jornada tinyint,touchdowns1 tinyint,touchdowns2 tinyint,heridos1 tinyint,heridos2 tinyint,fama1 tinyint,fama2 tinyint,gastos1 tinyint,gastos2 tinyint,repeticion tinyint,actuaciones1 tinytext,actuaciones2 tinytext,tiradas1 tinytext,tiradas2 tinytext,recaudacion1 tinyint,recaudacion2 tinyint,fanfactor1 tinyint,fanfactor2 tinyint,estado varchar(20),fecha long)")%><br>
<br>
	
<%dbman.close();%>

