<link rel="stylesheet" href="css/mysql.css">

<%bdatos.DBManager2 dbman=new bdatos.DBManager2();%>

<%=dbman.backup("lebb_entrenadores")%><br><br>
<%=dbman.backup("lebb_equipos")%><br><br>
<%=dbman.backup("lebb_jugadores")%><br><br>
<%=dbman.backup("lebb_ligas")%><br><br>
<%=dbman.backup("lebb_partidos")%><br><br>

<%dbman.close();%>
