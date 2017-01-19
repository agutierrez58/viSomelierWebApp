<%
bdatos.DBManager2 dbman=new bdatos.DBManager2();
out.print(dbman.executeValue("select count(*) from lebb_equipos where equipo='"+request.getParameter("equipo")+"'"));
dbman.close();
%>