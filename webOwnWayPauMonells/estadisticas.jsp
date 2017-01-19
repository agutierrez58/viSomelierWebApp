<%@ page contentType="text/html;charset=windows-1252"%>

<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>

<table width="750px">
<form action="estadisticas.do" method="get">
<tr>
<td style="border-style: none;">
<select name="competicion"><option value="">(todas)</option><%for(int i=0;i<((java.util.Vector)request.getAttribute("competiciones")).size();i++){%><option value="<%=((java.util.Vector)request.getAttribute("competiciones")).elementAt(i)%>"<%=((java.util.Vector)request.getAttribute("competiciones")).elementAt(i).equals(request.getParameter("competicion"))?" selected":""%>><%=((java.util.Vector)request.getAttribute("competiciones")).elementAt(i)%></option><%}%></select>
&nbsp;&nbsp;&nbsp;&nbsp;
<select name="raza"><option value="">(todas)</option><%for(int i=0;i<((java.util.Vector)request.getAttribute("razas")).size();i++){%><option value="<%=((java.util.Vector)request.getAttribute("razas")).elementAt(i)%>"<%=((java.util.Vector)request.getAttribute("razas")).elementAt(i).equals(request.getParameter("raza"))?" selected":""%>><%=((java.util.Vector)request.getAttribute("razas")).elementAt(i)%></option><%}%></select>
<input type="hidden" name="orderby" value="totalpx"/>
</td>
<td align="right" style="border-style: none;">
<a onclick="document.forms[0].submit();">[mostrar]</a>
</td>
</tr>
</form>
</table>

<br><br>

<table width="750px">
<tr>
<th class="acenter" width="20px">#</th>
<th width="100px">nombre</th>
<th width="100px">posicion</th>
<th width="150px">equipo</th>
<th class="acenter" width="25px">raza</th>
<th width="200px">subidas+bajadas</th>
<th class="acenter" width="20px"><a onclick="document.forms[0].orderby.value='pxcp';document.forms[0].submit();">cp</a></th>
<th class="acenter" width="20px"><a onclick="document.forms[0].orderby.value='pxtd';document.forms[0].submit();">td</a></th>
<th class="acenter" width="20px"><a onclick="document.forms[0].orderby.value='pxin';document.forms[0].submit();">in</a></th>
<th class="acenter" width="20px"><a onclick="document.forms[0].orderby.value='pxcs';document.forms[0].submit();">cs</a></th>
<th class="acenter" width="20px"><a onclick="document.forms[0].orderby.value='pxmvp';document.forms[0].submit();">mvp</a></th>
<th class="acenter" width="30px"><a onclick="document.forms[0].orderby.value='totalpx';document.forms[0].submit();">total</a></th>
<th class="acenter" width="25px">sta</th>
</tr>
<%for(int i=0;i<((java.util.Vector)request.getAttribute("mejores")).size();i++){%>
<tr class="<%=i%2==0?"par":"impar"%>">
<td class="acenter"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[0]%></td>
<td><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[1]%></td>
<td><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[2]%></td>
<td><a href="viewteam.do?nombreequipo=<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[3]%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[3]%></a></td>
<td class="acenter"><img src="img/razas/<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[4]%>.png" width="20" height="20"/></td>
<td>
<span class="blue"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[5]%></span>
<span class="red"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[6]%></span>
</td>
<td class="acenter"><span class="<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[7].equals("0")?"grey":"blue"%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[7]%></span></td>
<td class="acenter"><span class="<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[8].equals("0")?"grey":"blue"%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[8]%></span></td>
<td class="acenter"><span class="<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[9].equals("0")?"grey":"blue"%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[9]%></span></td>
<td class="acenter"><span class="<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[10].equals("0")?"grey":"blue"%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[10]%></span></td>
<td class="acenter"><span class="<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[11].equals("0")?"grey":"blue"%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[11]%></span></td>
<td class="acenter"><span class="<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[12].equals("0")?"grey":"blue"%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[12]%></span></td>
<td class="acenter"><span class="<%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[13].equals("ok")?"grey":"red"%>"><%=((String[])((java.util.Vector)request.getAttribute("mejores")).elementAt(i))[13]%></span></td>
</tr>
<%}%>
</table>

</body>

