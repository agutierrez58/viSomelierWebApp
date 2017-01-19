<link rel="stylesheet" href="css/alten.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 

<body>

<%bdatos.ResultSet2 ultimos=(bdatos.ResultSet2)request.getAttribute("ultimos");%>

<jsp:include page="menu.jsp"/>
<br><br><br>


<table width="500px">
  <tr>
    <th colspan="2">11/05/2009 Bienvenidos</th>
  </tr>
  <tr class="par">
    <td width="50px">
      <img src="img/pauman.gif" width="50" height="50">
    </td>
    <td valign="top" style="padding: 10px;">
      Queda inaugurado mi gestor.
    </td>
  </tr>
</table>

<br><br><br>

<table width="500px">
  <tr>
    <th colspan="6">Ultimos 10 Partidos</th>
  </tr>
  <%for(int i=0;i<ultimos.rows();i++){%>
  <%ultimos.next();%>
  <tr class="<%=i%2==0?"par":"impar"%>">
    <td width="25px" class="acenter">
      <img src="img/razas/<%=ultimos.getString("raza1")%>.png" height="20" width="20">
    </td>
    <td width="150px" class="aright">
      <a href="viewteam.do?nombreequipo=<%=ultimos.getString("equipo1")%>"><%=ultimos.getString("equipo1")%></a>
      <%=ultimos.getString("touchdowns1")%>
    </td>
    <td width="25px" class="acenter">
      <span class="red">VS</span>
    </td>
    <td width="150px">
      <%=ultimos.getString("touchdowns2")%>
      <a href="viewteam.do?nombreequipo=<%=ultimos.getString("equipo2")%>"><%=ultimos.getString("equipo2")%></a>
    </td>
    <td width="25px" class="acenter">
      <img src="img/razas/<%=ultimos.getString("raza2")%>.png" height="20" width="20">
    </td>
    <td width="125px" class="acenter">
      <a href="veracta.do?competicion=<%=ultimos.getString("competicion")%>&jornada=<%=ultimos.getString("jornada")%>&equipo1=<%=ultimos.getString("equipo1")%>&equipo2=<%=ultimos.getString("equipo2")%>">
        <%=new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date(new Long(ultimos.getString("fecha"))))%>
      </a>
    </td>
  </tr>
  <%}%>
</table>

<br>
<br>

 <!-- Begin BidVertiser code -->
<SCRIPT LANGUAGE="JavaScript1.1" SRC="http://bdv.bidvertiser.com/BidVertiser.dbm?pid=215121&bid=570925" type="text/javascript"></SCRIPT>
<noscript><a href="http://www.bidvertiser.com">make money online</a></noscript>
<!-- End BidVertiser code --> 

<br>

</body>

