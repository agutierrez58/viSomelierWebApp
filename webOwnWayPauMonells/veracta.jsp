<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>

<%java.util.Hashtable<String,String> partido=(java.util.Hashtable<String,String>)request.getAttribute("partido");%>

<span class="titulo"><%=request.getAttribute("competicion")%> - jornada <%=request.getAttribute("jornada")%></span>
<br><br>

<table width="750px">
<tr>
<%for(int i=1;i<=2;i++){%>
<td style="border-style: none;" valign="top" width="50%">
<%--zona izquierda--%>
<table width="350px">
<tr class="par">
<th width="90px">entrenador</th>
<td width="85px" align="center"><span style="font-weight: bold;"><%=partido.get("entrenador"+i)%></span></td>
<th width="90px">raza</th>
<td width="85px" align="center"><img src="img/razas/<%=partido.get("raza"+i)%>.png"></td>
</tr>
<tr class="impar">
<th>equipo</th>
<td colspan="3" align="center"><a href="viewteam.do?nombreequipo=<%=partido.get("equipo"+i)%>"><%=partido.get("equipo"+i)%></a></td>
</tr>
<tr class="par">
<th>touchdowns</th>
<td align="center"><%=partido.get("touchdowns"+i)%></td>
<th>heridos</th>
<td align="center"><%=partido.get("heridos"+i)%></td>
</tr>
<tr class="impar">
<th>fama</th>
<td align="center"><%=partido.get("fama"+i)%></td>
<th>gastos</th>
<td align="center"><%=partido.get("gastos"+i)%></td>
</tr>
</table>
<%--fin izquierda--%>
</td>
<%}%>
</tr>
</table>

<br>

<table width="750px">
<tr>
<%for(int i=1;i<=2;i++){%>
<td style="border-style: none;" valign="top" width="50%">
<%--zona izquierda--%>
<table width="350px">
<tr>
<th width="30px" class="acenter">#</th>
<th width="30px" class="acenter">ps</th>
<th width="30px" class="acenter">td</th>
<th width="30px" class="acenter">in</th>
<th width="30px" class="acenter">cs</th>
<th width="30px" class="acenter">mvp</th>
<th width="100px" class="acenter">subidas</th>
<th width="70px" class="acenter">lesiones</th>
</tr>
<%for(int a=1;a<partido.get("actuaciones"+i).split("#").length;a++){%>
<tr class="<%=a%2==1?"par":"impar"%>">
<td class="acenter"><%=partido.get("actuaciones"+i).split("#")[a].split(":")[0]%></td>
<td class="acenter"><span class="<%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[0].equals("0")?"grey":"blue"%>"><%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[0]%></span></td>
<td class="acenter"><span class="<%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[1].equals("0")?"grey":"blue"%>"><%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[1]%></span></td>
<td class="acenter"><span class="<%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[2].equals("0")?"grey":"blue"%>"><%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[2]%></span></td>
<td class="acenter"><span class="<%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[3].equals("0")?"grey":"blue"%>"><%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[3]%></span></td>
<td class="acenter"><span class="<%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[4].equals("0")?"grey":"blue"%>"><%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[4]%></span></td>
<td class="acenter"><span class="blue">
<%for(int c=2;c<partido.get("tiradas"+i).split("\\.").length;c++){%>
<%if(partido.get("tiradas"+i).split("\\.")[c].split(":")[0].equals(partido.get("actuaciones"+i).split("#")[a].split(":")[0])){%>
<%=partido.get("tiradas"+i).split("\\.")[c].split(":")[1]+" "%>
<%}}%>
</span></td>
<td class="acenter"><span class="red"><%=partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",").length==5?"&nbsp;":partido.get("actuaciones"+i).split("#")[a].split(":")[1].split(",")[5]%></span></td>
</tr>
<%}%>
</table>
<%--fin izquierda--%>
</td>
<%}%>
</tr>
</table>

<br>

<table width="750px">
<tr>
<%for(int i=1;i<=2;i++){%>
<td style="border-style: none;" valign="top" width="50%">
<%--zona izquierda--%>
<table width="350px">
<tr class="par">
<th width="100px">recaudacion</th>
<td class="acenter" width="45px" nowrap><span class="grey"><%=partido.get("tiradas"+i).split("\\.")[0].charAt(0)%><%=partido.get("tiradas"+i).split("\\.")[0].length()==1?"":" > "+partido.get("tiradas"+i).split("\\.")[0].charAt(1)%></span></td>
<td class="acenter" width="25px"><%=partido.get("recaudacion"+i)%></td>

<th width="100px">fanfactor</th>
<td class="acenter" width="55px" nowrap><span class="grey"><%for(int b=0;b<partido.get("tiradas"+i).split("\\.")[1].length();b++){%><%=b==0?"":"+"%><%=partido.get("tiradas"+i).split("\\.")[1].charAt(b)%><%}%></span></td>
<td class="acenter" width="25px"><%=partido.get("fanfactor"+i)%></td>
</tr>
</table>
<%--fin izquierda--%>
</td>
<%}%>
</tr>
</table>

<br>

</body>
