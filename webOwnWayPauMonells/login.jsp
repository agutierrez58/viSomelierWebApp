<link rel="stylesheet" href="css/alten.css">
<script src="js/utils.js"></script>

<body<%if(!request.getAttribute("error").equals("")) out.println(" onload=\"alert('"+request.getAttribute("error")+"');\"");%>>

<table width="100%" height="100%">
  <tr>
    <td align="center" valign="middle">
      <table>
        <tr>
          <td><img src="img/portada/db.JPG"></td>
          <td><img src="img/portada/mm.JPG"></td>
          <td><img src="img/portada/mb.JPG"></td>
          <td align="center" valign="middle"><a onclick="window.location='register.do';">[Registrarse]</a><br></td>
        </tr>
          <td><img src="img/portada/jd.JPG"></td>
          <td colspan="2" rowspan="2" align="center" valign="middle">
            <table>
              <form action="loginaction.do" method="post">
              <tr class="par">
                <th>entrenador</th>
                <td><input type="text" name="entrenador"></input></td>
              </tr>
              <tr class="impar">
                <th>password</th>
                <td><input type="password" name="password"></input></td>
              </tr>
              </form>
            </table>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td align="center" valign="middle"><a onclick="document.forms[0].entrenador.value='guest'; document.forms[0].password.value='guest'; document.forms[0].submit();">[Invitado]</a></td>
          <td><img src="img/portada/ro.JPG"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="middle"><a onclick="if(fieldsok(document.forms[0].entrenador,document.forms[0].password)) document.forms[0].submit();">[Entrar]</a></td>
          <td><img src="img/portada/nq.JPG"></td>
          <td><img src="img/portada/zw.JPG"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>


</body>
