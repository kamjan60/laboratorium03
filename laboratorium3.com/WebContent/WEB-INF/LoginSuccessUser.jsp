<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUCCESS!!!</title>
</head>
<body onload="myFunction()">
	<h1>Witaj użytkowniku, ${user.login}!</h1>
	<div><font color="red">${somethingwentwrong}</font></div>
	<div>Zapis na wydarzenie:</div>
	<form action="/com.laboratorium3/eventRegister.html" method="post">
		<div>Twoje id w systemie:</div>
		<input type="text" name="id_user" id="id_user" readonly>
		<div>Wydarzenie:</div>
		<select id="wydarzenie" name="wydarzenie" onchange="myFunction()"> 
			${listofevents}
		</select>
		<span> id wydarzenia:</span>
		<input type="text" id="id_event" name="id_event" readonly/>
		<div>Agenda:</div>
		<textarea id="agenda" name="agenda" rows="10" cols="80" readonly></textarea>
		<div>Termin wydarzenia:</div>
		<input type="text" id="termin" name="termin" readonly cols="40">
		<div>Typ uczestnictwa:</div>
		<select id="typ_uczestnictwa" name="typ_uczestnictwa"> 
			<option value="Sluchacz">Sluchacz</option>
			<option value="Autor">Autor</option>
			<option value="Sponsor">Sponsor</option>
			<option value="Organizator">Organizator</option>
		</select>
		<div>Wyzywienie:</div>
		<select id="wyzywienie" name="wyzywienie"> 
			<option value="Bez preferencji">Bez preferencji</option>
			<option value="Wegetarianskie">Wegetarianskie</option>
			<option value="Bez glutenu">Bez glutenu</option>
		</select>
		<br/>
		<input type="submit" value="Zapisz na wydarzenie">
	</form>
	<br/>
	<form action="/com.laboratorium3/index.html" method="get">
		<input type="submit" value="Powrót do strony logowania"/>
	</form>
</body>
<script>
function myFunction() {
	  var i=document.getElementById('wydarzenie').value;
	  var agendyTekst="${agenda}";
	  var terminyTekst="${terminy}";
	  var eventyTekst="${id_event}";
	  var agendy=agendyTekst.split(";");
	  var terminy=terminyTekst.split(";");
	  var user_id="${id_user}";
	  var eventy=eventyTekst.split(";");
	  document.getElementById('agenda').innerHTML=agendy[i];
	  document.getElementById('termin').value=terminy[i];
	  document.getElementById('id_user').value=user_id;
	  document.getElementById('id_event').value=eventy[i];
}
</script>
</html>