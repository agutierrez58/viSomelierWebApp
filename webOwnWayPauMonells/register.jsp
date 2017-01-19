<link rel="stylesheet" href="css/alten.css">
<script src="js/utils.js"></script>

<body<%if(!request.getAttribute("error").equals("")) out.println(" onload=\"alert('"+request.getAttribute("error")+"');\"");%>>

<form action="registeraction.do" method="post">
<table>
  <tr class="par">
    <th>entrenador</th>
    <td><input type="text" name="entrenador"></input></td>
  </tr>
  <tr class="impar">
    <th>password</th>
    <td><input type="password" name="password"></input></td>
  </tr>
</table>
<br>
<a onclick="if(fieldsok(document.forms[0].entrenador,document.forms[0].password)) document.forms[0].submit();">[Registrarse]</a>

</form>

</body>
