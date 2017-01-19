<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1 align='center'>Selecció de vins</h1>

	<p>Selecciona opció:</p>
	
	<form action="SelectVi.do" method="POST" focus="color">

		Color: <select name="color">
			<option value="negre">negre</option>
			<option value="rosat">rosat</option>
			<option value="blanc">blanc</option>			
		</select> 
		<br />
		<p style="margin-top:50px;">
			<button type="submit" value="Submit">D'acord</button>
		</p>
	</form>
	
		<%
	//Prints out to console
	System.out.println("Hello World in Console!");
 
	//Prints out to HTML page
	out.println("Hello World!");
    %>
    
</body>
</html>

