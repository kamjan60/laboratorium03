<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUCCESS!!!</title>
</head>
<body>
<h1>Gratulacje, rejestracja zakończona powodzeniem!</h1>
<h2>Wprowadziłeś następujące dane:</h2>
<br>Imię: ${user.imie}
<br>Nazwisko: ${user.nazwisko}
<br>Stanowisko: ${user.stanowisko}
<br>Płeć: ${user.plec}
<br>Email: ${user.email}
<br>Login: ${user.login}
<br>Hasło: ${user.haslo}
<br>Walidacja hasła: ${user.powthaslo}
<form action="/com.laboratorium3/index.html" method="get">
<input type="submit" value="Powrót do strony logowania"/>
</form>
</body>
</html>