<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Strona główna logowania</title>
</head>
<body>
	<h1>${errorPassMatchMsg}</h1>
	<form action="/com.laboratorium3/submitLoginForm.html" method="post">
		<label for="login">Login: </label> 
		<br/>
		<input type="text" id="login" name="login"  ${disabled}>
		<br/>
		<label for="haslo" >Hasło: </label>

		<br/>
		<input type="password" id="haslo" name="haslo" ${disabled}><span><input type="checkbox" onclick="myFunction()"><span>pokaż hasło</span></span> 
		<div><font color="red">${somethingnotmatch}</font></div>
		<br/>
		<br/>
		<input type="submit" value="Zaloguj">
	</form>
	<form action="/com.laboratorium3/Registration.html" method="post">
		<input type="submit" value="Zarejestruj">
	</form>
</body>
<script>
function myFunction() {
	  var x = document.getElementById("haslo");
	  if (x.type === "password") {
	    x.type = "text";
	  } 
	  else {
	    x.type = "password";
	  }
}
</script>
</html>