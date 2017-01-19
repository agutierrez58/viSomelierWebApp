<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>

<%bdatos.ResultSet2 jugadores=(bdatos.ResultSet2)request.getAttribute("jugadores");%>
<%bdatos.ResultSet2 equipo=(bdatos.ResultSet2)request.getAttribute("equipo");%>
<%equipo.next();%>

<%--
<%if("view".equals(request.getAttribute("mode"))){%>
<br>
<%while(jugadores.next()) {%>
<img src="img/players/<%=equipo.getString("raza")%>/<%=jugadores.getString("posicion")%>.gif">
<%}%>
<%jugadores.beforeFirst();%>
<br><br>
<%}%>
--%>

<table width="750px">
	<tr>
		<th width="15px" class="acenter">#</th>
		<th width="125px">nombre</th>
		<th width="110px">posicion</th>
		<th width="15px" class="acenter">mo</th>
		<th width="15px" class="acenter">fu</th>
		<th width="15px" class="acenter">ag</th>
		<th width="15px" class="acenter">ar</th>
		<th width="320px">habilidades</th>
		<th width="15px" class="acenter">cp</th>
		<th width="15px" class="acenter">td</th>
		<th width="15px" class="acenter">in</th>
		<th width="15px" class="acenter">cs</th>
		<th width="15px" class="acenter">mvp</th>
		<th width="15px" class="acenter">total</th>
		<th width="30px" class="acenter">coste</th>
		<%if("edit".equals(request.getAttribute("mode"))){%>
		<th width="30px" class="acenter">fire</th>
		<%}%>
	</tr>
	<%while(jugadores.next()) {%>
	<tr class="<%if(jugadores.getPointer()%2==0) out.print("par"); else out.print("impar");%>">
		<td class="acenter" height="20px"><%=jugadores.getString("numero")%></td>
		<td><%=jugadores.getString("nombre")%></td>
		<td>
		<%=jugadores.getString("posicion")%>
		<%--<img src="img/players/<%=equipo.getString("raza")%>/<%=jugadores.getString("posicion")%>.gif">--%>
		</td>
		<td class="acenter"><%=jugadores.getString("mov")%></td>
		<td class="acenter"><%=jugadores.getString("fue")%></td>
		<td class="acenter"><%=jugadores.getString("agi")%></td>
		<td class="acenter"><%=jugadores.getString("arm")%></td>
		<td><%=jugadores.getString("habilidades")%><span class="blue"><%=jugadores.getString("subidas")%></span><span class="red"><%=jugadores.getString("lesiones")%></span></td>
		<td class="acenter"><span<%if(jugadores.getString("pxcp").equals("0")) out.print(" class=\"grey\""); else out.print(" class=\"blue\"");%>><%=jugadores.getString("pxcp")%></span></td>
		<td class="acenter"><span<%if(jugadores.getString("pxtd").equals("0")) out.print(" class=\"grey\""); else out.print(" class=\"blue\"");%>><%=jugadores.getString("pxtd")%></span></td>
		<td class="acenter"><span<%if(jugadores.getString("pxin").equals("0")) out.print(" class=\"grey\""); else out.print(" class=\"blue\"");%>><%=jugadores.getString("pxin")%></span></td>
		<td class="acenter"><span<%if(jugadores.getString("pxcs").equals("0")) out.print(" class=\"grey\""); else out.print(" class=\"blue\"");%>><%=jugadores.getString("pxcs")%></span></td>
		<td class="acenter"><span<%if(jugadores.getString("pxmvp").equals("0")) out.print(" class=\"grey\""); else out.print(" class=\"blue\"");%>><%=jugadores.getString("pxmvp")%></span></td>
		<td class="acenter"><span<%if(jugadores.getString("pxtotal").equals("0")) out.print(" class=\"grey\""); else out.print(" class=\"blue\"");%>><%=jugadores.getString("pxtotal")%></span></td>
		<td class="acenter"><span<%if(jugadores.getString("coste").equals("miss")) out.print(" class=\"red\"");%>><%=jugadores.getString("coste")%></span></td>
		<%if("edit".equals(request.getAttribute("mode"))){%>
		<td class="acenter"><a onclick="if(confirm('Fire player?')) window.location='fireplayer.do?equipo=<%=equipo.getString("equipo")%>&numero=<%=jugadores.getString("numero")%>';"><img src="img/iconos/borrar.png"></a></td>
		<%}%>
	</tr>
	<%}%>
</table>

<br>

<table width="<%="edit".equals(request.getAttribute("mode"))?"460px":"400px"%>">
<tr class="par">
<th class="acenter" width="100px" height="20px">equipo</th><td class="acenter" width="150px"><%=equipo.getString("equipo")%></td>
<th class="acenter" width="100px">rerolls</th><td class="acenter" width="50px"><%=equipo.getString("rerolls")%></td>
<%if("edit".equals(request.getAttribute("mode"))){%>
<td class="acenter" width="30px"><a onclick="if(confirm('Buy ReRoll?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=reroll';"><img src="img/iconos/comprar.png"></a></td>
<td class="acenter" width="30px"><a onclick="if(confirm('Fire reroll?')) window.location='fireothers.do?equipo=<%=equipo.getString("equipo")%>&what=reroll';"><img src="img/iconos/borrar.png"></a></td>
<%}%>
</tr>
<tr class="impar">
<th class="acenter" height="20px">entrenador</th><td class="acenter"><%=equipo.getString("entrenador")%></td>
<th class="acenter">fanfactor</th><td class="acenter"><%=equipo.getString("fanfactor")%></td>
<%if("edit".equals(request.getAttribute("mode"))){%>
<td class="acenter" colspan="2">&nbsp;</td>
<%}%>
</tr>
<tr class="par">
<th class="acenter" height="20px">raza</th><td class="acenter"><%=equipo.getString("raza")%></td>
<th class="acenter">ayudantes</th><td class="acenter"><%=equipo.getString("ayudantes")%></td>
<%if("edit".equals(request.getAttribute("mode"))){%>
<td class="acenter"><a onclick="if(confirm('Buy ayudante?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=ayudante';"><img src="img/iconos/comprar.png"></a></td>
<td class="acenter"><a onclick="if(confirm('Fire ayudante?')) window.location='fireothers.do?equipo=<%=equipo.getString("equipo")%>&what=ayudante';"><img src="img/iconos/borrar.png"></a></td>
<%}%>
</tr>
<tr class="impar">
<th class="acenter" height="20px">valoracion</th><td class="acenter"><%=equipo.getString("teamrating")%></td>
<th class="acenter">animadoras</th><td class="acenter"><%=equipo.getString("animadoras")%></td>
<%if("edit".equals(request.getAttribute("mode"))){%>
<td class="acenter"><a onclick="if(confirm('Buy animadora?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=animadora';"><img src="img/iconos/comprar.png"></a></td>
<td class="acenter"><a onclick="if(confirm('Fire animadora?')) window.location='fireothers.do?equipo=<%=equipo.getString("equipo")%>&what=animadora';"><img src="img/iconos/borrar.png"></a></td>
<%}%>
</tr>
<tr class="par">
<th class="acenter" height="20px">tesoreria</th><td class="acenter"><%=equipo.getString("tesoreria")%></td>
<th class="acenter">medico</th><td class="acenter"><%=equipo.getString("medico")%></td>
<%if("edit".equals(request.getAttribute("mode"))){%>
<td class="acenter"><a onclick="if(confirm('Buy medico?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=medico';"><img src="img/iconos/comprar.png"></a></td>
<td class="acenter"><a onclick="if(confirm('Fire medico?')) window.location='fireothers.do?equipo=<%=equipo.getString("equipo")%>&what=medico';"><img src="img/iconos/borrar.png"></a></td>
<%}%>
</tr>
</table>


<%if("edit".equals(request.getAttribute("mode"))){%>

<%java.util.Vector<Integer> numerosdisp=(java.util.Vector<Integer>)request.getAttribute("numerosdisp");%>
<%java.util.Vector<Integer> posicionesdisp=(java.util.Vector<Integer>)request.getAttribute("posicionesdisp");%>
<%java.util.Vector<String[]> subidas=(java.util.Vector<String[]>)request.getAttribute("subidas");%>


<br>
<table>
<form>
<tr><th colspan="4">new player</th></tr>
<tr class="par">
<td><select id="numero"><option value=""></option><%for(int i=0;i<numerosdisp.size();i++){%><option value="<%=numerosdisp.elementAt(i)%>"><%=numerosdisp.elementAt(i)%></option><%}%></select></td>
<td><input type="text" id="nombre" style="width: 125px;"></td>
<td><select id="posicion"><option value=""></option><%for(int i=0;i<posicionesdisp.size();i++){%><option value="<%=posicionesdisp.elementAt(i)%>"><%=posicionesdisp.elementAt(i)%></option><%}%></select></td>
<td><a onclick="if((document.getElementById('numero').value!='')&&(document.getElementById('nombre').value!='')&&(document.getElementById('posicion').value!='')) if(confirm('Buy player?')) window.location='buyplayer.do?equipo=<%=equipo.getString("equipo")%>&numero='+document.getElementById('numero').value+'&nombre='+document.getElementById('nombre').value+'&posicion='+document.getElementById('posicion').value;"><img src="img/iconos/comprar.png"></a></td>
</tr>
</form>
</table>

<%--
<br>
<table>
<tr>
<th colspan="4">compras varias</th>
</tr>
<tr class="par">
<td><a onclick="if(confirm('Buy ReRoll?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=reroll';">[reroll]</a></td>
<td><a onclick="if(confirm('Buy ayudante?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=ayudante';">[ayudante]</a></td>
<td><a onclick="if(confirm('Buy animadora?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=animadora';">[animadora]</a></td>
<td><a onclick="if(confirm('Buy medico?')) window.location='buyothers.do?equipo=<%=equipo.getString("equipo")%>&what=medico';">[medico]</a></td>
</tr>
</table>
--%>

<%if(subidas.size()>0){%>
<br>
<table border="1">
<tr>
<th colspan="5">new habilities</th>
</tr>
<%for(int i=0;i<subidas.size();i++){%>
<tr class="<%=i%2==0?"par":"impar"%>">
<td><%=subidas.elementAt(i)[0]%></td>
<td><%=subidas.elementAt(i)[1]%></td>
<td><%=subidas.elementAt(i)[2]%></td>
<td><select id="sel<%=i%>"><option value=""></option><%for(int a=1;a<subidas.elementAt(i)[3].split("#").length;a++){%><option value="<%=subidas.elementAt(i)[3].split("#")[a]%>"><%=subidas.elementAt(i)[3].split("#")[a]%></option><%}%></select></td>
<td><a onclick="if(document.getElementById('sel<%=i%>').value!='') if(confirm('Pick hability?')) window.location='pickhability.do?equipo=<%=equipo.getString("equipo")%>&numero=<%=subidas.elementAt(i)[0]%>&tirada=<%=subidas.elementAt(i)[2]%>&hability='+document.getElementById('sel<%=i%>').value;"><img src="img/iconos/aceptar.png"></a></td>
</tr>
<%}%>
</table>
<%}%>

<%}else{%>

<br>
<table>
<tr>
<td style="border-style: none;"><img src="img/razas/<%=equipo.getString("raza")%>.png"></td>
<td style="border-style: none;" width="25px"></td>
<td style="border-style: none;">
total players
<span class="blue"><%=jugadores.rows()%></span>
</td>
<td style="border-style: none;" width="25px"></td>
<td style="border-style: none;">
total px
<span class="blue"><%=request.getAttribute("totalpx")%></span>
</td>
<%if(equipo.getString("competicion").length()>0){%>
<td style="border-style: none;" width="25px"></td>
<td style="border-style: none;">
competicion
<a href="verclasificacion.do?competicion=<%=equipo.getString("competicion")%>"><%=equipo.getString("competicion")%></a>
</td>
<%}%>
</tr>
</table>

<br>
<br>
<a href="historial.do?nombreequipo=<%=equipo.getString("equipo")%>">[partidos]</a>

<%}%>

<br>

</body>



