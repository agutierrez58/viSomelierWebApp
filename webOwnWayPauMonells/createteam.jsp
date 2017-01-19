<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<script src="js/utils.js"></script>

<script>
function clear() {
    for(var c=1;c<=16;c++) {
        document.forms[0].elements['nombre'+c].value='';
        document.forms[0].elements['posicion'+c].value='';
    }
    document.forms[0].elements['nombreequipo'].value='';
    document.forms[0].elements['medico'].checked=false;
}

function comprajugador(num) {
    var eleccion=document.forms[0].elements['posicion'+num].value;
    if(eleccion=='')
        borrajugador(num);
    else {
        var repetidos=0;
        for(var r=1;r<=16;r++)
            if(document.forms[0].elements['posicion'+r].value==eleccion)
                repetidos++;
        if(repetidos>document.getElementById(eleccion).value.split('#')[1]) {
            borrajugador(num);
            alert('maximo por posicion alcanzado');
        } else {
            var coste=parseInt(document.getElementById(eleccion).value.split('#')[0]);
            if(document.getElementById('precio'+num).innerHTML!='')
                coste-=parseInt(document.getElementById('precio'+num).innerHTML);
            if(haydinero(coste))
                insertajugador(num,eleccion);
            else
                borrajugador(num);
        }
    }
    recontar();
}

function insertajugador(num,eleccion) {
    document.getElementById('mov'+num).innerHTML=document.getElementById(eleccion).value.split('#')[2];
    document.getElementById('fue'+num).innerHTML=document.getElementById(eleccion).value.split('#')[3];
    document.getElementById('agi'+num).innerHTML=document.getElementById(eleccion).value.split('#')[4];
    document.getElementById('arm'+num).innerHTML=document.getElementById(eleccion).value.split('#')[5];
    document.getElementById('habilidades'+num).innerHTML=document.getElementById(eleccion).value.split('#')[6];
    document.getElementById('precio'+num).innerHTML=document.getElementById(eleccion).value.split('#')[0];
}

function borrajugador(num) {
    document.forms[0].elements['posicion'+num].value='';
    document.getElementById('mov'+num).innerHTML='';
    document.getElementById('fue'+num).innerHTML='';
    document.getElementById('agi'+num).innerHTML='';
    document.getElementById('arm'+num).innerHTML='';
    document.getElementById('habilidades'+num).innerHTML='';
    document.getElementById('precio'+num).innerHTML='';
}

function comprareroll(valor) {
    if(((valor>0)&&(document.getElementById('rerolls').innerHTML<8))||((valor<0)&&(document.getElementById('rerolls').innerHTML>0)))
        if(haydinero(parseInt(valor)*parseInt(document.getElementById('preciorerolls').value)))
            document.getElementById('rerolls').innerHTML=parseInt(document.getElementById('rerolls').innerHTML)+valor;
    recontar();
}

function compravarios(objetivo,valor) {
    if(((valor>0)&&(document.getElementById(objetivo).innerHTML<9))||((valor<0)&&(document.getElementById(objetivo).innerHTML>0)))
        if(haydinero(parseInt(valor)*10))
            document.getElementById(objetivo).innerHTML=parseInt(document.getElementById(objetivo).innerHTML)+valor;
    recontar();
}

function compramedico() {
    if(document.getElementById('medicodisp').value=='no')
        document.getElementById('medico').checked=false;
    if((document.getElementById('medico').checked==true)&&(haydinero(50)==false))
        document.getElementById('medico').checked=false;
    recontar();
}

function haydinero(dinero) {
    if(parseInt(document.getElementById('tesoreria').innerHTML)<parseInt(dinero)) {
        alert('no hay dinero');
        return false;
    } else
        return true;
}

function recontar() {
    var total=0;
    if(document.getElementById('medico').checked==true) total+=50;
    for(var j=1;j<=16;j++)
        if(document.getElementById('precio'+j).innerHTML!='')
            total+=parseInt(document.getElementById('precio'+j).innerHTML);
    total+=parseInt(document.getElementById('rerolls').innerHTML)*parseInt(document.getElementById('preciorerolls').value);
    total+=parseInt(document.getElementById('fanfactor').innerHTML)*10;
    total+=parseInt(document.getElementById('ayudantes').innerHTML)*10;
    total+=parseInt(document.getElementById('animadoras').innerHTML)*10;
    document.getElementById('tesoreria').innerHTML=1000-total;
    document.getElementById('teamrating').innerHTML=0+total/10;
}

function todook() {
    var ret=true;
    errores='';
    var numjugadores=0;
    for(var j=1;j<=16;j++)
        if(document.forms[0].elements['posicion'+j].value!='') {
            document.forms[0].elements['nombre'+j].value=trim(document.forms[0].elements['nombre'+j].value);
            numjugadores++;
            if(document.forms[0].elements['nombre'+j].value=='')
                ret=false;
        } else
            document.forms[0].elements['nombre'+j].value='';
    document.forms[0].nombreequipo.value=trim(document.forms[0].nombreequipo.value);
    if(ret==false)
        errores+='hay jugadores sin nombre.\n';
    if(document.forms[0].nombreequipo.value=='') {
        ret=false;
        errores+='el equipo no tiene nombre.\n';
    }
    if(document.forms[0].nombreequipo.value.indexOf('\'')!=-1) {
        ret=false;
        errores+='el nombre del equipo no puede contener comillas simples.\n';
    }
    if(numjugadores<11) {
        ret=false;
        errores+='no hay 11 jugadores.\n';
    }
    if(errores!='')
        alert(errores.substring(0,errores.length-1));
    return ret;
}

function envia() {
    document.forms[0].extras.value=document.getElementById('rerolls').innerHTML+'#'+document.getElementById('fanfactor').innerHTML+'#'+document.getElementById('ayudantes').innerHTML+'#'+document.getElementById('animadoras').innerHTML+'#';
    if(document.getElementById('medico').checked==true)
        document.forms[0].extras.value=document.forms[0].extras.value+'si';
    else
        document.forms[0].extras.value=document.forms[0].extras.value+'no';
    document.forms[0].extras.value=document.forms[0].extras.value+'#'+document.getElementById('raza').innerHTML+'#'+document.getElementById('tesoreria').innerHTML
    document.forms[0].submit();
}
function ajax(url)
{
  if(window.XMLHttpRequest)
    var obj=new XMLHttpRequest();
  else if(window.ActiveXObject)
    var obj=new ActiveXObject("Microsoft.XMLHTTP");
  obj.onreadystatechange=
  function()
  {
    if((obj.readyState==4)&&(obj.status==200))
      callback(obj.responseText);
  }
  url+='&seed='+new Date().getTime();
  obj.open("post",url,true);
  obj.send(null);
}
function callback(text)
{
  if(text=='0')
    envia();
  else
    alert('el nombre del equipo ya existe.');
}
</script>



<body onload="clear();">

<jsp:include page="menu.jsp"/>
<br><br><br>

<form action="createteamaction.do" method="post">

	<%bdatos.ResultSet2 rset=(bdatos.ResultSet2)request.getAttribute("rsetJugadores");%>
	<%while(rset.next()) {%>
		<input type="hidden" id="<%=rset.getString("posicion")%>" value="<%=rset.getString("coste")%>#<%=rset.getString("cantidad")%>#<%=rset.getString("mov")%>#<%=rset.getString("fue")%>#<%=rset.getString("agi")%>#<%=rset.getString("arm")%>#<%=rset.getString("habilidades")%>"/>
	<%}%>
	<input type="hidden" id="preciorerolls" value="<%=request.getAttribute("preciorerolls")%>"/>
	<input type="hidden" id="medicodisp" value="<%=request.getAttribute("medicodisp")%>"/>

    <table width="700px">
        <tr>
            <th width="20px" class="acenter">#</th>
            <th width="125px">nombre</th>
            <th width="100px">posicion</th>
            <th width="20px" class="acenter">mo</th>
            <th width="20px" class="acenter">fu</th>
            <th width="20px" class="acenter">ag</th>
            <th width="20px" class="acenter">ar</th>
            <th width="350px">habilidades</th>
            <th width="25px" class="acenter">cost</th>
        </tr>
        <%for(int i=1;i<=16;i++) {%>
        <tr class="<%=i%2==0?"impar":"par"%>">
            <td class="acenter"><%=i%></td>
            <td><input type="text" maxlength="15" name="nombre<%=i%>" style="width: 125px;"/></td>
            <td><select name="posicion<%=i%>" onchange="comprajugador(<%=i%>);" style="width: 120px;">
                <option value=""></option>
                <%rset.beforeFirst();%>
                <%while(rset.next()) {%>
                <option value="<%=rset.getString("posicion")%>"><%=rset.getString("posicion")%></option>
                <%}%>
            </select></td>
            <td id="mov<%=i%>" class="acenter"></td>
            <td id="fue<%=i%>" class="acenter"></td>
            <td id="agi<%=i%>" class="acenter"></td>
            <td id="arm<%=i%>" class="acenter"></td>
            <td id="habilidades<%=i%>"></td>
            <td id="precio<%=i%>" class="acenter"></td>
        </tr> <%}%>
    </table>

	<br>

<table>
<tr>
<td style="border-style: none;">
    <table width="500px">
        <tr class="par">
            <th width="125px">entrenador</th>
            <td width="150px"><%=request.getSession().getAttribute("entrenador")%><input type="hidden" name="entrenador" value="<%=request.getSession().getAttribute("entrenador")%>"></td>
            <th width="125px">rerolls</th>
            <td width="100px" class="acenter" nowrap><a style="cursor: pointer;" onclick="comprareroll(-1);">[-]</a> <span id="rerolls">0</span> <a style="cursor: pointer;" onclick="comprareroll(1);">[+]</a></td>
        </tr>
        <tr class="impar">
            <th>equipo</th>
            <td><input type="text" maxlength="20" name="nombreequipo" class="aleft" style="width: 150px;"/></td>
            <th>fan factor</th>
            <td class="acenter" nowrap><a style="cursor: pointer;" onclick="compravarios('fanfactor',-1);">[-]</a> <span id="fanfactor">0</span> <a style="cursor: pointer;" onclick="compravarios('fanfactor',1);">[+]</a></td>
        </tr>
        <tr class="par">
            <th>raza</th>
            <td id="raza"><%=request.getAttribute("raza")%></td>
            <th>ayudantes</th>
            <td class="acenter" nowrap><a style="cursor: pointer;" onclick="compravarios('ayudantes',-1);">[-]</a> <span id="ayudantes">0</span> <a style="cursor: pointer;" onclick="compravarios('ayudantes',1);">[+]</a></td>
        </tr>
        <tr class="impar">
            <th>team rating</th>
            <td id="teamrating" class="aright">0</td>
            <th>animadoras</th>
            <td class="acenter" nowrap><a style="cursor: pointer;" onclick="compravarios('animadoras',-1);">[-]</a> <span id="animadoras">0</span> <a style="cursor: pointer;" onclick="compravarios('animadoras',1);">[+]</a></td>
        </tr>
        <tr class="par">
            <th>tesoreria</th>
            <td id="tesoreria" class="aright">1000</td>
            <th>medico</th>
            <td class="acenter"><input type="checkbox" id="medico" onclick="compramedico();"/></td>
        </tr>
    </table>
</td>
<td style="border-style: none;" width="195px" align="right" valign="bottom">
    <a onclick="if(todook()) ajax('ajaxequipo.jsp?equipo='+document.forms[0].nombreequipo.value);">[Guardar]</a>
</td>
</tr>
</table>


    <input type="hidden" name="extras"/>

    </form>
<br>

</body>
