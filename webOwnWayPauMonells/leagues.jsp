<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>


<%bdatos.ResultSet2 rset2=(bdatos.ResultSet2)request.getAttribute("ligas");%>
<%bdatos.ResultSet2 rset3=(bdatos.ResultSet2)request.getAttribute("terminadas");%>


<table>
  <tr><th colspan="6">ligas</th></tr>
  <%while(rset2.next()) {%>
  <tr class="<%if(rset2.getPointer()%2==0) out.print("par"); else out.print("impar");%>">
    <td><%=rset2.getString("competicion")%></td>
    <td>participantes: <%=rset2.getString("numplayers")%></td>
    <td>jornada: <%=rset2.getString("jornada")%></td>
    <td><a href="verclasificacion.do?competicion=<%=rset2.getString("competicion")%>">[clasificacion]</a></td>
    <td><a href="verpartidos.do?competicion=<%=rset2.getString("competicion")%>">[partidos]</a></td>
    <td><a href="estadisticas.do?competicion=<%=rset2.getString("competicion")%>">[estadisticas]</a></td>
  </tr>
  <%}%>
</table>

<br><br>

<table>
  <tr><th colspan="4">terminadas</th></tr>
  <%while(rset3.next()) {%>
  <tr class="<%if(rset3.getPointer()%2==0) out.print("par"); else out.print("impar");%>">
    <td><%=rset3.getString("competicion")%></td>
    <td><a href="verclasificacion.do?competicion=<%=rset3.getString("competicion")%>">[clasificacion]</a></td>
    <td><a href="verpartidos.do?competicion=<%=rset3.getString("competicion")%>">[partidos]</a></td>
    <td><a href="estadisticas.do?competicion=<%=rset3.getString("competicion")%>">[estadisticas]</a></td>
  </tr>
  <%}%>
</table>


<%if("god".equals(request.getSession().getAttribute("entrenador"))){%>
<br><br>
<a href="roundrobin.do">[nueva liga]</a>
<%}%>

<br>

</body>
