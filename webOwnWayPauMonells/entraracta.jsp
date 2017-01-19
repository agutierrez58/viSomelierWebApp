<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<script>
function cargadatos()
{
var act;
<%for(int e=1;e<=2;e++){%>
<%if(request.getAttribute("datos"+e)!=null){%>
document.getElementById('touchdowns<%=e-1%>').innerHTML='<%=((java.util.Vector<String>)request.getAttribute("datos"+e)).elementAt(0)%>';
if(document.getElementById('touchdowns<%=e-1%>').innerHTML!='0') document.getElementById('touchdowns<%=e-1%>').className="black";
document.getElementById('heridos<%=e-1%>').innerHTML='<%=((java.util.Vector<String>)request.getAttribute("datos"+e)).elementAt(1)%>';
if(document.getElementById('heridos<%=e-1%>').innerHTML!='0') document.getElementById('heridos<%=e-1%>').className="black";
document.getElementById('fama<%=e-1%>').innerHTML='<%=((java.util.Vector<String>)request.getAttribute("datos"+e)).elementAt(2)%>';
if(document.getElementById('fama<%=e-1%>').innerHTML!='0') document.getElementById('fama<%=e-1%>').className="black";
document.getElementById('gastos<%=e-1%>').innerHTML='<%=((java.util.Vector<String>)request.getAttribute("datos"+e)).elementAt(3)%>';
if(document.getElementById('gastos<%=e-1%>').innerHTML!='0') document.getElementById('gastos<%=e-1%>').className="black";
act='<%=((java.util.Vector<String>)request.getAttribute("datos"+e)).elementAt(4)%>';
for(var i=1;i<act.split('#').length;i++)
{
document.getElementById('cp'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML=act.split('#')[i].split(':')[1].split(',')[0];
if(document.getElementById('cp'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML!='0') document.getElementById('cp'+act.split('#')[i].split(':')[0]+'-<%=e%>').className="black";
document.getElementById('td'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML=act.split('#')[i].split(':')[1].split(',')[1];
if(document.getElementById('td'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML!='0') document.getElementById('td'+act.split('#')[i].split(':')[0]+'-<%=e%>').className="black";
document.getElementById('in'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML=act.split('#')[i].split(':')[1].split(',')[2];
if(document.getElementById('in'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML!='0') document.getElementById('in'+act.split('#')[i].split(':')[0]+'-<%=e%>').className="black";
document.getElementById('cs'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML=act.split('#')[i].split(':')[1].split(',')[3];
if(document.getElementById('cs'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML!='0') document.getElementById('cs'+act.split('#')[i].split(':')[0]+'-<%=e%>').className="black";
document.getElementById('mvp'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML=act.split('#')[i].split(':')[1].split(',')[4];
if(document.getElementById('mvp'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML!='0') document.getElementById('mvp'+act.split('#')[i].split(':')[0]+'-<%=e%>').className="black";
document.getElementById('lesiones'+act.split('#')[i].split(':')[0]+'-<%=e%>').innerHTML=act.split('#')[i].split(':')[1].split(',')[5];
}
<%}%>
<%}%>
<%if(request.getAttribute("repeticion")!=null){%>
document.getElementById('repeticion').innerHTML='<%=request.getAttribute("repeticion")%>';
<%}%>
}

function change(elem,val) {
	if((val=='-')&&(document.getElementById(elem).innerHTML!='0'))
		document.getElementById(elem).innerHTML=parseInt(document.getElementById(elem).innerHTML)-1;
	else if(val=='+')
		document.getElementById(elem).innerHTML=parseInt(document.getElementById(elem).innerHTML)+1;
	if(document.getElementById(elem).innerHTML=='0')
		document.getElementById(elem).className="grey";
	else
		document.getElementById(elem).className="black";
}

function changegastos(elem,val) {
	if(val=='-')
		document.getElementById(elem).innerHTML=parseInt(document.getElementById(elem).innerHTML)-10;
	else if(val=='+')
		document.getElementById(elem).innerHTML=parseInt(document.getElementById(elem).innerHTML)+10;
	if(document.getElementById(elem).innerHTML=='0')
		document.getElementById(elem).className="grey";
	else
		document.getElementById(elem).className="black";
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
	document.forms[0].resjug1.value='';
	document.forms[0].resjug2.value='';
	var actuacion;
	for(var e=1;e<=2;e++)
		for(var i=1;i<=16;i++)
			if(document.getElementById('lesiones'+i+'-'+e)!=null) {
				actuacion=document.getElementById('cp'+i+'-'+e).innerHTML+','+document.getElementById('td'+i+'-'+e).innerHTML+','+document.getElementById('in'+i+'-'+e).innerHTML+','+document.getElementById('cs'+i+'-'+e).innerHTML+','+document.getElementById('mvp'+i+'-'+e).innerHTML+','+document.getElementById('lesiones'+i+'-'+e).innerHTML;
				if(actuacion!='0,0,0,0,0,')
					if(e==1)
						document.forms[0].resjug1.value+='#'+i+':'+actuacion;
					else
						document.forms[0].resjug2.value+='#'+i+':'+actuacion;
			}
	document.forms[0].resequ1.value=document.forms[0].resequ1.value.split("#")[0];
	document.forms[0].resequ2.value=document.forms[0].resequ2.value.split("#")[0];
	document.forms[0].global.value=document.forms[0].global.value.split("#")[0]+'#'+document.forms[0].global.value.split("#")[1];
	document.forms[0].resequ1.value+='#'+document.getElementById('touchdowns0').innerHTML+'#'+document.getElementById('heridos0').innerHTML+'#'+document.getElementById('fama0').innerHTML+'#'+document.getElementById('gastos0').innerHTML;
	document.forms[0].resequ2.value+='#'+document.getElementById('touchdowns1').innerHTML+'#'+document.getElementById('heridos1').innerHTML+'#'+document.getElementById('fama1').innerHTML+'#'+document.getElementById('gastos1').innerHTML;
	document.forms[0].global.value+='#'+document.getElementById('repeticion').innerHTML;
	document.forms[0].submit();
}

function todook() {
    errores='';
	if((document.getElementById('fama0').innerHTML!='0')&&(document.getElementById('fama1').innerHTML!='0')||(parseInt(document.getElementById('fama0').innerHTML)+parseInt(document.getElementById('fama1').innerHTML)>2))
		errores+='fama incorrecta.\n';
	var tdtotal;
	var cstotal;
	var mvptotal;
	for(var e=1;e<=2;e++) {
		tdtotal=parseInt(document.getElementById('td*-'+e).innerHTML);
		cstotal=parseInt(document.getElementById('cs*-'+e).innerHTML);
		mvptotal=parseInt(document.getElementById('mvp*-'+e).innerHTML);
		for(var i=1;i<=16;i++)
			if(document.getElementById('lesiones'+i+'-'+e)!=null) {
				tdtotal+=parseInt(document.getElementById('td'+i+'-'+e).innerHTML);
				cstotal+=parseInt(document.getElementById('cs'+i+'-'+e).innerHTML);
				mvptotal+=parseInt(document.getElementById('mvp'+i+'-'+e).innerHTML);
			}
		if(document.getElementById('touchdowns'+(e-1)).innerHTML!=tdtotal)
			errores+='touchdowns del equipo '+e+' incorrectos.\n';
		if(document.getElementById('heridos'+(e-1)).innerHTML!=cstotal)
			errores+='heridos del equipo '+e+' incorrectos.\n';
		if(mvptotal!=1)
			errores+='mvp del equipo '+e+' incorrecto.\n';
	}
    if(errores!='') {
        alert(errores.substring(0,errores.length-1));
		return false;
	} else {
		//alert('todo ok');
		return true;
	}
}
</script>

<body onload="cargadatos();">

<jsp:include page="menu.jsp"/>
<br><br><br>

<!--parametros-->
<%String[] equipos=(String[])request.getAttribute("equipos");%>
<%String[] hanJugado=(String[])request.getAttribute("hanJugado");%>
<%String[] entrenadores=(String[])request.getAttribute("entrenadores");%>
<%String[] razas=(String[])request.getAttribute("razas");%>



<span class="titulo"><%=request.getAttribute("competicion")%> - jornada <%=request.getAttribute("jornada")%></span>
<br><br>


<table width="750px">
<tr>
<%for(int e=0;e<2;e++){%>
<td style="border-style: none;" valign="top" width="50%">

<table width="350px">
<tr class="par">
<th width="90px">entrenador</th>
<td width="85px" class="acenter"><span style="font-weight: bold;"><%=entrenadores[e]%></span></td>
<th width="90px">raza</th>
<td width="85px" class="acenter"><img src="img/razas/<%=razas[e]%>.png"></td>
</tr>
<tr class="impar">
<th>equipo</th>
<td class="acenter" colspan="3"><a href="viewteam.do?nombreequipo=<%=equipos[e]%>"><%=equipos[e]%></a></td>
</tr>
<tr class="par">
<th>touchdowns</th>
<td nowrap class="acenter"><a onclick="change('touchdowns<%=e%>','-');">-</a> <span class="grey" id="touchdowns<%=e%>">0</span> <a onclick="change('touchdowns<%=e%>','+');">+</a></td>
<th>heridos</th>
<td nowrap class="acenter"><a onclick="change('heridos<%=e%>','-');">-</a> <span class="grey" id="heridos<%=e%>">0</span> <a onclick="change('heridos<%=e%>','+');">+</a></td>
</tr>
<tr class="impar">
<th>fama</th>
<td nowrap class="acenter"><a onclick="change('fama<%=e%>','-');">-</a> <span class="grey" id="fama<%=e%>">0</span> <a onclick="change('fama<%=e%>','+');">+</a></td>
<th>gastos</th>
<td nowrap class="acenter"><a onclick="changegastos('gastos<%=e%>','-');">-</a> <span class="grey" id="gastos<%=e%>">0</span> <a onclick="changegastos('gastos<%=e%>','+');">+</a></td>
</tr>
</table>

<br>

<table width="350px">
<tr>
<th width="20px" class="acenter">#</th>
<th width="50px" class="acenter">ps</th>
<th width="50px" class="acenter">td</th>
<th width="50px" class="acenter">in</th>
<th width="50px" class="acenter">cs</th>
<th width="50px" class="acenter">mvp</th>
<th width="80px">lesiones</th>
</tr>
<%for(int i=0;i<hanJugado[e].split("#").length;i++){%>
<tr class="<%if(i%2==0) out.print("par"); else out.print("impar");%>">
<td class="acenter"><%=hanJugado[e].split("#")[i]%></td>
<td class="acenter" nowrap><a onclick="change('cp<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','-');">-</a> <span class="grey" id="cp<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>">0</span> <a onclick="change('cp<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','+');">+</a></td>
<td class="acenter" nowrap><a onclick="change('td<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','-');">-</a> <span class="grey" id="td<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>">0</span> <a onclick="change('td<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','+');">+</a></td>
<td class="acenter" nowrap><a onclick="change('in<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','-');">-</a> <span class="grey" id="in<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>">0</span> <a onclick="change('in<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','+');">+</a></td>
<td class="acenter" nowrap><a onclick="change('cs<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','-');">-</a> <span class="grey" id="cs<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>">0</span> <a onclick="change('cs<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','+');">+</a></td>
<td class="acenter" nowrap><a onclick="change('mvp<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','-');">-</a> <span class="grey" id="mvp<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>">0</span> <a onclick="change('mvp<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>','+');">+</a></td>
<td><%if(hanJugado[e].split("#")[i].equals("*")){%>-<%}else{%>
<a onclick="select(document.getElementById('lesiones<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>'),'#miss#Lp#-1Mo#-1Fu#-1Ag#-1Ar#RIP',1);">*</a> <span class="red" id="lesiones<%=hanJugado[e].split("#")[i]+"-"+(e+1)%>"></span><%}%></td>
</tr>
<%}%>
</table>
</td>
<%}%>
</tr>
</table>

<br>



<table width="725px">
	<tr>
		<td style="border-style: none;" valign="top" width="350px">
			<table><tr class="par"><th width="325px">ganador repite recaudacion si no saca un:</th><td width="25px"><a onclick="select(document.getElementById('repeticion'),'1#2#3#4#5#6',1);">*</a> <span id="repeticion">1</span></td></tr></table>
		</td>
		<td style="border-style: none;" align="right" width="375px">
			<form action="entraractaaction.do" method="post">
				<input type="hidden" name="resequ1" value="<%=equipos[0]%>"/>
				<input type="hidden" name="resequ2" value="<%=equipos[1]%>"/>
				<input type="hidden" name="resjug1"/>
				<input type="hidden" name="resjug2"/>
				<input type="hidden" name="global" value="<%=request.getAttribute("competicion")%>#<%=request.getAttribute("jornada")%>"/>
				<a onclick="if(todook()) envia();">[Guardar]</a>
			</form>
		</td>
	</tr>
</table>

</body>
