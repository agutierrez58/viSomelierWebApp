<%if(!request.getSession().getAttribute("entrenador").equals("god")) response.sendRedirect("login.do");%>

<link rel="stylesheet" href="css/mysql.css">

<img src="img/logo_mysql.gif"></img><br><br>

<body onload="document.forms[0].sql.focus();">

<form action="mysql.do" method="post">
  <input type="text" name="sql" style="width: 700px;"/>
</form>

<%
bdatos.DBManager2 dbman=new bdatos.DBManager2();
if((request.getParameter("sql")!=null)&&!(request.getParameter("sql").trim().equals(""))) {
    out.println(request.getParameter("sql").trim()+"<br><br>");
    out.println(dbman.executeClever(request.getParameter("sql").trim()));
} else {
    bdatos.ResultSet2 rset2=dbman.executeQuery("show tables like 'lebb_%'");
    out.println("<table>");
    while(rset2.next())
        out.println("<tr><th><a onclick=\"document.forms[0].sql.value='select * from "+rset2.getString(1)+"';document.forms[0].submit();\">"+rset2.getString(1)+"</a></th></tr>");
    out.println("</table>");
}
dbman.close();
%>

</body>
