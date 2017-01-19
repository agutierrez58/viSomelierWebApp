<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<script src="js/utils.js"></script>

<script>
function change(elem,val) {
	if(val=='-')
		document.getElementById(elem).innerHTML=parseInt(document.getElementById(elem).innerHTML)-1;
	else if(val=='+')
		document.getElementById(elem).innerHTML=parseInt(document.getElementById(elem).innerHTML)+1;
}

function select(element,options,side) {
    var option=options.split('#');
    var num=option.length;
    var actual=0;
    for(var i=0;i<num;i++)
        if(option[i]==element.innerHTML)
            actual=i;
    actual+=side;
    if(actual==num)
        actual=0;
    else if(actual==-1)
        actual=num-1;
    element.innerHTML=option[actual];
}

function envia() {
	document.forms[1].torneo.value=trim(document.getElementById('competicion').value)+'#'+document.getElementById('vueltas').innerHTML+'#'+document.getElementById('v2').innerHTML+'#'+document.getElementById('v1').innerHTML+'#'+document.getElementById('em').innerHTML+'#'+document.getElementById('d1').innerHTML+'#'+document.getElementById('d2').innerHTML;
	document.forms[1].submit();
}

function todook() {
	var errores='';
	document.forms[1].participantes.value='';
	for(var i=0;i<document.forms[0].elements.length;i++)
		if(document.forms[0].elements[i].checked==true)
			document.forms[1].participantes.value+='#'+document.forms[0].elements[i].id;
	if((document.forms[1].participantes.value.split('#').length-1==0)||((document.forms[1].participantes.value.split('#').length-1)%2==1))
		errores+='numero de participantes incorrecto\n';
	if(trim(document.getElementById('competicion').value)=='')
		errores+='falta el nombre de la competicion\n';
    if(errores!='') {
        alert(errores.substring(0,errores.length-1));
		return false;
	} else
		return true;
}
</script>

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>

<%bdatos.ResultSet2 disponibles=(bdatos.ResultSet2)request.getAttribute("disponibles");%>

<form>
<table>
<tr>
<th class="acenter" width="150px">equipo</th>
<th class="acenter" width="100px">raza</th>
<th class="acenter" width="100px">entrenador</th>
<th class="acenter" width="25px">?</th>
</tr>
<%while(disponibles.next()){%>
<tr class="<%if(disponibles.getPointer()%2==0) out.print("par"); else out.print("impar");%>">
<td class="acenter"><a href="viewteam.do?nombreequipo=<%=disponibles.getString("equipo")%>"><%=disponibles.getString("equipo")%></a></td>
<td class="acenter"><img src="img/razas/<%=disponibles.getString("raza")%>.png" alt="<%=disponibles.getString("raza")%>" width="20" height="20"></img></td>
<td class="acenter"><%=disponibles.getString("entrenador")%></td>
<td class="acenter"><input type="checkbox" id="<%=disponibles.getString("equipo")%>"/></td>
</tr>
<%}%>
</table>
</form>

<br>

<table>
<tr class="par"><th width="100px" class="acenter">competicion</th><td width="225px"><input id="competicion" type="text" maxlength="25" name="competicion" style="width: 100%;"/></td></tr>
<tr class="impar"><th class="acenter">vueltas</th><td><a onclick="select(document.getElementById('vueltas'),'1#2',1);">[*]</a> <span id="vueltas">1</span></tr>
</table>

<br>

<table>
<tr class="par"><th width="100px" class="acenter">victoria 2+</th><td width="60px" class="acenter" nowrap><a onclick="change('v2','-');">[-]</a> <span id="v2">3</span> <a onclick="change('v2','+');">[+]</a></td></tr>
<tr class="impar"><th class="acenter">victoria 1</th><td class="acenter" nowrap><a onclick="change('v1','-');">[-]</a> <span id="v1">3</span> <a onclick="change('v1','+');">[+]</a></td></tr>
<tr class="par"><th class="acenter">empate</th><td class="acenter" nowrap><a onclick="change('em','-');">[-]</a> <span id="em">1</span> <a onclick="change('em','+');">[+]</a></td></tr>
<tr class="impar"><th class="acenter">derrota 1</th><td class="acenter" nowrap><a onclick="change('d1','-');">[-]</a> <span id="d1">0</span> <a onclick="change('d1','+');">[+]</a></td></tr>
<tr class="par"><th class="acenter">derrota 2+</th><td class="acenter" nowrap><a onclick="change('d2','-');">[-]</a> <span id="d2">0</span> <a onclick="change('d2','+');">[+]</a></td></tr>
</table>

<br>

<form action="roundrobinaction.do" method="post">
<input type="hidden" name="participantes"/>
<input type="hidden" name="torneo"/>
<a onclick="if(todook()) envia();">[Sortear]</a>
</form>

</body>
