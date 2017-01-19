<link rel="stylesheet" href="css/alten.css">

<body>

<jsp:include page="menu.jsp"/>
<br><br><br>

<table width="750px">
<tr>
<th>star players</th>
</tr>
<%for(int i=0;i<((java.util.Vector<String>)request.getAttribute("stars")).size();i++){%>
<tr class="<%=i%2==0?"par":"impar"%>">
<td style="padding: 3px;"<%=i%2==0?"":" class=\"blue\""%>">
<img src="img/iconos/star.png"/>
<%=((java.util.Vector<String>)request.getAttribute("stars")).elementAt(i)%>
</td>
</tr>
<%}%>
</table>

<br>

</body>

