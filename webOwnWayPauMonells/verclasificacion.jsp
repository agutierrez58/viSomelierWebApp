<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>

<%java.util.Vector<String> ordenados=(java.util.Vector<String>)request.getAttribute("ordenados");%>
<%java.util.Hashtable<String,int[]> resultados=(java.util.Hashtable<String,int[]>)request.getAttribute("resultados");%>
<%java.util.Hashtable<String,String[]> equipos=(java.util.Hashtable<String,String[]>)request.getAttribute("equipos");%>

<span class="titulo">CLASIFICACION - <%=request.getAttribute("competicion")%></span>
<br><br>

<table>
    <tr>
        <th width="20px" class="acenter">#</th>
        <th width="30px" class="acenter">raza</th>
        <th width="150px">equipo</th>
        <th width="95px" class="acenter">entrenador</th>
        <th width="75px" class="acenter">resultados</th>
        <th width="20px" class="acenter">tf</th>
        <th width="20px" class="acenter">tc</th>
        <th width="20px" class="acenter">hf</th>
        <th width="20px" class="acenter">hc</th>
        <th width="50px" class="acenter">puntos</th>
    </tr>
    <%for(int j=0;j<ordenados.size();j++){%>
    <tr class="<%=j%2==0?"par":"impar"%>">
        <td align="center"><%=j+1%></td>
        <td align="center"><img src="img/razas/<%=equipos.get(ordenados.elementAt(j))[0]%>.png" alt="<%=equipos.get(ordenados.elementAt(j))[0]%>" width="20" height="20"></img></td>
        <td><a href="viewteam.do?nombreequipo=<%=ordenados.elementAt(j)%>"><%=ordenados.elementAt(j)%></a></td>
        <td align="center"><%=equipos.get(ordenados.elementAt(j))[1]%></td>
        <td align="center"><%=resultados.get(ordenados.elementAt(j))[0]%> - <%=resultados.get(ordenados.elementAt(j))[1]%> - <%=resultados.get(ordenados.elementAt(j))[2]%></td>
        <td align="center"><%=resultados.get(ordenados.elementAt(j))[3]%></td>
        <td align="center"><%=resultados.get(ordenados.elementAt(j))[4]%></td>
        <td align="center"><%=resultados.get(ordenados.elementAt(j))[5]%></td>
        <td align="center"><%=resultados.get(ordenados.elementAt(j))[6]%></td>
        <td align="center"><%=resultados.get(ordenados.elementAt(j))[7]%></td>
    </tr>
    <%}%>
</table>

<br>
<a href="verpartidos.do?competicion=<%=request.getAttribute("competicion")%>">[partidos]</a>
<br>
<br>
	
</body>
	