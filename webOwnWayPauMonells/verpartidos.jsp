<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>

<%if(request.getAttribute("equipo")==null){%>
<span class="titulo">PARTIDOS - <%=request.getAttribute("competicion")%></span>
<br><br>
<%}%>

<%bdatos.ResultSet2 partidos=(bdatos.ResultSet2)request.getAttribute("partidos");%>

<table width="750px">
  <tr>
    <th class="acenter" width="20px">#</th>
    <th class="acenter" width="35px">raza1</th>
    <th width="190px">equipo1</th>
    <th class="acenter" width="35px">raza2</th>
    <th width="190px">equipo2</th>
    <th class="acenter" width="30px">td1</th>
    <th class="acenter" width="30px">td2</th>
    <th class="acenter" width="30px">hr1</th>
    <th class="acenter" width="30px">hr2</th>
    <th class="acenter" width="40px">re1</th>
    <th class="acenter" width="40px">re2</th>
    <th class="acenter" width="80px">estado</th>
  </tr>
  <%while(partidos.next()){%>
  <tr class="<%if(Integer.parseInt(partidos.getString("jornada"))%2==1) out.print("par"); else out.print("impar");%>">
    <td class="acenter"><%=partidos.getString("jornada")%></td>
    <td class="acenter"><img src="img/razas/<%=partidos.getString("raza1")%>.png" height="20" width="20"></td>
    <td>
      <a href="viewteam.do?nombreequipo=<%=partidos.getString("equipo1")%>"><%=partidos.getString("equipo1")%></a>
      (<%=partidos.getString("entrenador1")%>)
    </td>
    <td class="acenter"><img src="img/razas/<%=partidos.getString("raza2")%>.png" height="20" width="20"></td>
    <td>
      <a href="viewteam.do?nombreequipo=<%=partidos.getString("equipo2")%>"><%=partidos.getString("equipo2")%></a>
      (<%=partidos.getString("entrenador2")%>)
    </td>
    <td class="acenter"><%=partidos.getString("td1")%></td>
    <td class="acenter"><%=partidos.getString("td2")%></td>
    <td class="acenter"><%=partidos.getString("hr1")%></td>
    <td class="acenter"><%=partidos.getString("hr2")%></td>
    <td class="acenter"><%=partidos.getString("re1")%></td>
    <td class="acenter"><%=partidos.getString("re2")%></td>

	<%if(partidos.getString("estado").equals("introducir")){%>
	<td class="acenter"><a onclick="document.forms[0].jornada.value='<%=partidos.getString("jornada")%>'; document.forms[0].equipo1.value='<%=partidos.getString("equipo1")%>'; document.forms[0].equipo2.value='<%=partidos.getString("equipo2")%>'; document.forms[0].submit();"><%=partidos.getString("estado")%></a></td>
	<%}else if(partidos.getString("estado").equals("verificar")){%>
	<td class="acenter"><a onclick="document.forms[1].jornada.value='<%=partidos.getString("jornada")%>'; document.forms[1].equipo1.value='<%=partidos.getString("equipo1")%>'; document.forms[1].equipo2.value='<%=partidos.getString("equipo2")%>'; document.forms[1].submit();"><%=partidos.getString("estado")%></a></td>
	<%}else if(partidos.getString("estado").equals("jugado")){%>
	<td class="acenter"><a href="veracta.do?competicion=<%=request.getAttribute("competicion")%>&jornada=<%=partidos.getString("jornada")%>&equipo1=<%=partidos.getString("equipo1")%>&equipo2=<%=partidos.getString("equipo2")%>"><%=partidos.getString("estado")%></a></td>
	<%}else{%>
	<td class="acenter"><%=partidos.getString("estado")%></td>
	<%}%>

  </tr>
  <%}%>
</table>

<form action="entraracta.do" method="get">
<input type="hidden" name="competicion" value="<%=request.getAttribute("competicion")%>"/>
<input type="hidden" name="jornada"/>
<input type="hidden" name="equipo1"/>
<input type="hidden" name="equipo2"/>
</form>

<form action="aceptaracta.do" method="get">
<input type="hidden" name="competicion" value="<%=request.getAttribute("competicion")%>"/>
<input type="hidden" name="jornada"/>
<input type="hidden" name="equipo1"/>
<input type="hidden" name="equipo2"/>
</form>

<br>
	
<%if(request.getAttribute("equipo")==null){%>
<a href="verclasificacion.do?competicion=<%=request.getAttribute("competicion")%>">[clasificacion]</a>
<%}else{%>
<a href="viewteam.do?nombreequipo=<%=request.getAttribute("equipo")%>">[equipo]</a>
<%}%>
<br>
<br>
</body>
	
