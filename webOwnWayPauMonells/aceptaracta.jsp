<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<body>

<jsp:include page="menu.jsp"/>
<br><br>

<span class="titulo"><%=request.getAttribute("competicion")%> - jornada <%=request.getAttribute("jornada")%></span>
<br><br><br>

<%java.util.Vector<String> datos1=(java.util.Vector<String>)request.getAttribute("datos1");%>
<%java.util.Vector<String> datos2=(java.util.Vector<String>)request.getAttribute("datos2");%>



<table border="0" width="750px">
<tr>
<td style="border-style: none;" valign="top" width="50%">

<table width="350px">
	<tr class="par">
		<th width="90px">entrenador</th>
		<td width="85px" class="acenter"><span style="font-weight: bold;"><%=request.getAttribute("entrenador1")%></span></td>
		<th width="90px">raza</th>
		<td width="85px" class="acenter"><img src="img/razas/<%=request.getAttribute("raza1")%>.png"></td>
	</tr>
	<tr class="impar">
		<th>equipo</th>
		<td colspan="3" class="acenter"><a href="viewteam.do?nombreequipo=<%=datos1.elementAt(0)%>"><%=datos1.elementAt(0)%></a></td>
	</tr>
	<tr class="par">
		<th>touchdowns</th>
		<td align="center"><%=datos1.elementAt(1)%></td>
		<th>heridos</th>
		<td align="center"><%=datos1.elementAt(2)%></td>
	</tr>
	<tr class="impar">
		<th>fama</th>
		<td align="center"><%=datos1.elementAt(3)%></td>
		<th>gastos</th>
		<td align="center"><%=datos1.elementAt(4)%></td>
	</tr>
</table>
	
<br>
	
<%if(datos1.elementAt(5).length()>0){%>
<table width="350px">
<tr>
<th class="acenter" width="20px">#</th>
<th class="acenter" width="50px">ps</th>
<th class="acenter" width="50px">td</th>
<th class="acenter" width="50px">in</th>
<th class="acenter" width="50px">cs</th>
<th class="acenter" width="50px">mvp</th>
<th width="80px">lesiones</th>
</tr>
<%for(int i=1;i<datos1.elementAt(5).split("#").length;i++){%>
<tr class="<%if(i%2==0) out.print("im");%>par">
<td class="acenter"><%=datos1.elementAt(5).split("#")[i].split(":")[0]%></td>
<td class="acenter"><span class="<%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[0].equals("0")?"grey":"blue"%>"><%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[0]%></span></td>
<td class="acenter"><span class="<%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[1].equals("0")?"grey":"blue"%>"><%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[1]%></span></td>
<td class="acenter"><span class="<%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[2].equals("0")?"grey":"blue"%>"><%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[2]%></span></td>
<td class="acenter"><span class="<%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[3].equals("0")?"grey":"blue"%>"><%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[3]%></span></td>
<td class="acenter"><span class="<%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[4].equals("0")?"grey":"blue"%>"><%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[4]%></span></td>
<td><span class="red"><%=datos1.elementAt(5).split("#")[i].split(":")[1].split(",").length==5?"&nbsp;":datos1.elementAt(5).split("#")[i].split(":")[1].split(",")[5]%></span></td>
</tr>
<%}%>
</table>
<%}%>

</td>
<td width="150px" style="border-style: none;" valign="top" width="50%">

<table width="350px">
	<tr class="par">
		<th width="90px">entrenador</th>
		<td width="85px" class="acenter"><span style="font-weight: bold;"><%=request.getAttribute("entrenador2")%></span></td>
		<th width="90px">raza</th>
		<td width="85px" class="acenter"><img src="img/razas/<%=request.getAttribute("raza2")%>.png"></td>
	</tr>
	<tr class="impar">
		<th>equipo</th>
		<td colspan="3" class="acenter"><a href="viewteam.do?nombreequipo=<%=datos2.elementAt(0)%>"><%=datos2.elementAt(0)%></a></td>
	</tr>
	<tr class="par">
		<th>touchdowns</th>
		<td align="center"><%=datos2.elementAt(1)%></td>
		<th>heridos</th>
		<td align="center"><%=datos2.elementAt(2)%></td>
	</tr>
	<tr class="impar">
		<th>fama</th>
		<td align="center"><%=datos2.elementAt(3)%></td>
		<th>gastos</th>
		<td align="center"><%=datos2.elementAt(4)%></td>
	</tr>
</table>
	
<br>
	
<%if(datos2.elementAt(5).length()>0){%>
<table width="350px">
<tr>
<th class="acenter" width="20px">#</th>
<th class="acenter" width="50px">ps</th>
<th class="acenter" width="50px">td</th>
<th class="acenter" width="50px">in</th>
<th class="acenter" width="50px">cs</th>
<th class="acenter" width="50px">mvp</th>
<th width="80px">lesiones</th>
</tr>
<%for(int i=1;i<datos2.elementAt(5).split("#").length;i++){%>
<tr class="<%if(i%2==0) out.print("im");%>par">
<td class="acenter"><%=datos2.elementAt(5).split("#")[i].split(":")[0]%></td>
<td class="acenter"><span class="<%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[0].equals("0")?"grey":"blue"%>"><%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[0]%></span></td>
<td class="acenter"><span class="<%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[1].equals("0")?"grey":"blue"%>"><%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[1]%></span></td>
<td class="acenter"><span class="<%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[2].equals("0")?"grey":"blue"%>"><%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[2]%></span></td>
<td class="acenter"><span class="<%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[3].equals("0")?"grey":"blue"%>"><%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[3]%></span></td>
<td class="acenter"><span class="<%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[4].equals("0")?"grey":"blue"%>"><%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[4]%></span></td>
<td><span class="red"><%=datos2.elementAt(5).split("#")[i].split(":")[1].split(",").length==5?"&nbsp;":datos2.elementAt(5).split("#")[i].split(":")[1].split(",")[5]%></span></td>
</tr>
<%}%>
</table>
<%}%>

</td>
</tr>
</table>


<br>

<form action="" method="get">
<input type="hidden" name="competicion" value="<%=request.getAttribute("competicion")%>">
<input type="hidden" name="jornada" value="<%=request.getAttribute("jornada")%>">
<input type="hidden" name="equipo1" value="<%=datos1.elementAt(0)%>">
<input type="hidden" name="equipo2" value="<%=datos2.elementAt(0)%>">
</form>


<table border="0" width="725px">
	<tr>
		<td width="350px" style="border-style: none;">
			<table>
				<tr class="par">
					<th width="325px">ganador repite recaudacion si no saca un:</th>
					<td width="25px" align="center"><%=request.getAttribute("repeticion")%></td>
				</tr>
			</table>
		</td>
		<td width="375px" style="border-style: none;">
			<table border="0" style="border-style: none;">
				<tr>
					<td width="300px" style="border-style: none;" align="right"><a onclick="document.forms[0].action='editaracta.do'; document.forms[0].submit();">[Editar]</a></td>
					<td width="75px" style="border-style: none;" align="right"><a onclick="document.forms[0].action='aceptaractaaction.do'; document.forms[0].submit();">[Aceptar]</a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
	
<br>

</body>

