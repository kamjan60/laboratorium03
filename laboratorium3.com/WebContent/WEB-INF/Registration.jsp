<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rejestracja</title>
</head>
<body>
	<h1>${errorPassMatchMsg}</h1>
	<form action="/com.laboratorium3/submitRegisterForm.html" method="post">
		<label for="imie">Imię: </label>
		<br/>
		<input type="text" id="imie" name="imie"> <div ><font color="red"><form:errors path="user.imie"/></font></div>
		<br/>
		<label for="nazwisko">Nazwisko: </label> 
		<br/>
		<input type="text" id="nazwisko" name="nazwisko"> <div ><font color="red"><form:errors path="user.nazwisko"/></font></div>
		<br/>
		<label for="stanowisko">Stanowisko: </label> 
		<br/>
		<input type="text" id="stanowisko" name="stanowisko"> <div ><font color="red"><form:errors path="user.stanowisko"/></font></div>
		<br/>
		<label for="plec">Płeć: </label> 
		<br/>
		<select id=plec name=plec> 
			<option value=Mezczyzna>Mezczyzna</option>
			<option value=Kobieta>Kobieta</option>
		</select> <div><font color="red"><form:errors path="user.plec"/></font></div>
		<br/>
		<label for="email">Email: </label> 
		<br/>
		<input type="text" id="email" name="email"> <div ><font color="red"><form:errors path="user.email"/></font></div>
		<br/>
		<label for="login">Login: </label> 
		<br/>
		<input type="text" id="login" name="login"> <div ><font color="red"><form:errors path="user.login"/></font></div>
		<br/>
		<label for="haslo">Hasło: </label> 
		<br/>
		<input type="password" id="haslo" name="haslo"> <div ><font color="red"><form:errors path="user.haslo"/></font></div>
		<br/>
		<label for="powthaslo">Powtórz hasło: </label> 
		<br/>
		<input type="password" id="powthaslo" name="powthaslo"> <div ><font color="red">${pwdnotmatch}</font></div>
		<br/><br/>
		<input type="submit" value="Zarejestruj">
	</form>
</body>
</html>