<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<body>

<jsp:include page="menu.jsp"/>

<br><br><br>


<%bdatos.ResultSet2 rset=(bdatos.ResultSet2)request.getAttribute("equipos");%>

<table>
  <tr>
    <th colspan="4">mis equipos</th>
  </tr>
  <%while(rset.next()) {%>
  <tr class="<%if(rset.getPointer()%2==0) out.print("par"); else out.print("impar");%>">
    <td><img src="img/razas/<%=rset.getString("raza")%>.png" height="20" width="20" alt="<%=rset.getString("raza")%>"></td>
    <td><a href="viewteam.do?nombreequipo=<%=rset.getString("equipo")%>"><%=rset.getString("equipo")%></a></td>
    <td><a href="verclasificacion.do?competicion=<%=rset.getString("competicion")%>"><%=rset.getString("competicion")%></a></td>
    <td><a href="editteam.do?nombreequipo=<%=rset.getString("equipo")%>"><img src="img/iconos/editar.png" style="border: 0px;" alt="edit"></a></td>

  </tr>
  <%}%>
</table>

<br><br>

<table>
  <tr>
    <th colspan="2">nuevo equipo</th>
  </tr>
  <tr class="par">
    <td>raza</td>
    <td>
      <select onchange="if(this.value!='') window.location='createteam.do?raza='+this.value;">
        <option></option>
        <%for(Object r:bloodbowl.Parser.listaRazas()) {%>
        <option value="<%=r.toString()%>"><%=r.toString()%></option>
        <%}%>
      </select>
    </td>
  </tr>
</table>

<br><br>


</body>

