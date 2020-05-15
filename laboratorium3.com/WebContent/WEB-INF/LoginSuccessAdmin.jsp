<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUCCESS!!!</title>
</head>
<body>
<h1>Witaj administratorze, ${user.login}!</h1>
	<br/>
	<div><b>*****Obsługa użytkowników*****</b></div>
	<br/>
	<div><b>Dodanie użytkownika:</b></div>
	<form action="/com.laboratorium3/addUser.html" method="post">
		<input type="submit" value="Dodaj użytkownika">
	</form>
	<div><b>Usunięcie użytkownika:</b></div>
	<form action="/com.laboratorium3/deleteUser.html" method="post">
		<div>Podaj ID użytkownika do usunięcia:</div>
		<input type=text id=id name=id>
		<input type="submit" value="Usuń użytkownika">
	</form>
	<div><b>Modyfikacja hasła użytkownika:</b></div>
	<form action="/com.laboratorium3/modifyUser.html" method="post">
		<div>Podaj nowe haslo dla uzytkownika od konkretnym id:</div>
		<div>ID:</div>
		<input type=text id=id_2 name=id_2>
		<div>Hasło:</div>
		<input type=text id=haslo name=haslo>
		<input type="submit" value="Modyfikuj dane użytkownika">
	</form>
	<br/>
	<div><b>*****Obsługa wydarzeń*****</b></div>
	<br/>
	<div><b>Dodanie wydarzenia:</b></div>
	<form action="/com.laboratorium3/addEvent.html" method="post">
		<div>Podaj nazwę wydarzenia:</div>
		<input type="text" id="nazwa" name="nazwa">
		<div>Podaj agendę:</div>
		<input type="text" id="agenda" name="agenda">
		<div>Podaj termin wydarzenia (rrrr-mm-dd):</div>
		<input type="text" id="termin_wydarzenia" name="termin_wydarzenia">
		<input type="submit" value="Dodaj wydarzenie">
	</form>
	<div><b>Usunięcie wydarzenia:</b></div>
	<form action="/com.laboratorium3/deleteEvent.html" method="post">
		<div>Podaj id wydarzenia ktore chcesz usunąć:</div>
		<input type="text" id="id_event" name="id_event">
		<input type="submit" value="Usuń wydarzenie">
	</form>
	<div><b>Modyfikacja wydarzenia:</b></div>
	<form action="/com.laboratorium3/modifyEvent.html" method="post">
		<div>Podaj id wydarzenia które chcesz modyfikować:</div>
		<input type="text" id="id_event_2" name="id_event_2">
		<div>Podaj nazwę wydarzenia:</div>
		<input type="text" id="nazwa_2" name="nazwa_2">
		<div>Podaj agendę:</div>
		<input type="text" id="agenda_2" name="agenda_2">
		<div>Podaj termin wydarzenia (rrrr-mm-dd):</div>
		<input type="text" id="termin_wydarzenia_2" name="termin_wydarzenia_2">
		<input type="submit" value="Modyfikuj wydarzenie">
	</form>
	<br/>
	<div><b>*****Obsługa zapisów na wydarzenie*****</b></div>
	<br/>
	<div><b>Id zgłoszenia, które chcesz zaakceptować:</b></div>
	<form action="/com.laboratorium3/acceptRegister.html" method="post">
		<input type="text" id="id_event_a" name="id_event_a">
		<input type="submit" value="Potwierdz zapis">
	</form>
	<div><b>Id zgłoszenia, które chcesz odrzucić:</b></div>
	<form action="/com.laboratorium3/denyRegister.html" method="post">
	<input type="text" id="id_event_d" name="id_event_d">
		<input type="submit" value="Odrzuć zapis">
	</form>
	<br/>
<form action="/com.laboratorium3/index.html" method="get">
<input type="submit" value="Powrót do strony logowania"/>
</form>
</body>
</html>