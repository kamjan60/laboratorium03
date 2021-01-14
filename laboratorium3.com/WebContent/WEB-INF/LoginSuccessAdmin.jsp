<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Strona administratora</title>
</head>
<body onload="myFunction()">
<h1>Witaj administratorze, ${userlogin}!</h1>
	<br/>
	<div><b>Twoje id w systemie:</b></div>
	<input type="text" name="id_user" id="id_user" readonly>
	<div><font color="red">${somethingwentwrong}</font></div>
	<div><font color="green">${a}</font></div>
	<br/>
	<div><b>*****Obsługa użytkowników*****</b></div>
	<br/>
	<div><b>Usunięcie użytkownika:</b></div>
	<form action="/com.laboratorium3/deleteUser.html" method="post" accept-charset="UTF-8">
		<input type="hidden" name="id_user2" id="id_user2" readonly>
		<div>Podaj ID użytkownika do usunięcia:</div>
		<input type=text id=id name=id value="0">
		<input type="submit" value="Usuń użytkownika">
	</form>
	<br/>
	<div><b>Modyfikacja hasła użytkownika:</b></div>
	<form action="/com.laboratorium3/modifyUser.html" method="post" accept-charset="UTF-8">
		<input type="hidden" name="id_user3" id="id_user3" readonly>
		<div>Podaj nowe haslo dla uzytkownika o konkretnym id:</div>
		<div>ID:</div>
		<input type=text id=id_2 name=id_2 value=0>
		<div>Hasło:</div>
		<input type=text id=haslo name=haslo>
		<input type="submit" value="Modyfikuj dane użytkownika">
	</form>
	<br/>
	</div><b>*****Wyświetlanie notatek*****</b><div>
	<br/>
	<form action="/com.laboratorium3/noteRegister.html" method="post" accept-charset="UTF-8">
		<div><b>Notatka:</b></div>
		<select id="notatka" name="notatka" onchange="myFunction()"> 
			${listofnotes}
		</select>
		<span>id notatki:</span>
		<input type="text" id="id_note" name="id_note" readonly/>
		<div>Opis:</div>
		<textarea id="opis" name="opis" rows="10" cols="80" readonly></textarea>
		<div>Termin notatki:</div>
		<input type="text" id="termin" name="termin" readonly cols="40">
	</form>
	<br/>
	<form action ="/com.laboratorium3/addNoteViewer.html" method="post" accept-charset="UTF-8">
		<input type="hidden" name="id_user7" id="id_user7" readonly>
		<div><b>Dodawanie do wyświetlania notatek:</b></div>
		<div>Wybierz notatkę którą chcesz pokazać:</div>
		<select id="viewnotatka" name="viewnotatka" id="viewnotatka">
			${listofnotes2}
		</select>
		<div>Wybierz użytkownika któremu ją pokazać:</div>
		<select id="viewer" name="viewer" id="viewer">
			${listofusers}
		</select>
		<input type="submit" value="Udostępnij widok notatki">
	</form>
	<br/>
	<div><b>*****Obsługa notatek*****</b></div>
	<br/>
	<div><b>Dodanie notatki:</b></div>
	<form action="/com.laboratorium3/addNote.html" method="post" accept-charset="UTF-8">
		<input type="hidden" name="id_user4" id="id_user4" readonly>
		<div>Podaj nazwę notatki:</div>
		<input type="text" id="nazwa" name="nazwa">
		<div>Podaj opis:</div>
		<textarea id="opis" name="opis" rows="10" cols="80"></textarea>
		<div>Podaj termin notatki (rrrr-mm-dd):</div>
		<input type="text" id="termin" name="termin">
		<input type="submit" value="Dodaj notatkę">
	</form>
	<br/>
	<div><b>Usunięcie notatki:</b></div>
	<form action="/com.laboratorium3/deleteNote.html" method="post" accept-charset="UTF-8">
		<input type="hidden" name="id_user5" id="id_user5" readonly>
		<div>Podaj id notatki ktorą chcesz usunąć:</div>
		<input type="text" id="id_note" name="id_note" value="0">
		<input type="submit" value="Usuń notatkę">
	</form>
	<br/>
	<div><b>Modyfikacja notatki:</b></div>
	<form action="/com.laboratorium3/modifyNote.html" method="post" accept-charset="UTF-8">
		<input type="hidden" name="id_user6" id="id_user6" readonly>
		<div>Podaj id notatki którą chcesz modyfikować:</div>
		<input type="text" id="id_note_2" name="id_note_2" value="0">
		<div>Podaj nazwę notatki:</div>
		<input type="text" id="nazwa_2" name="nazwa_2">
		<div>Podaj opis:</div>
		<textarea id="opis_2" name="opis_2" rows="10" cols="80"></textarea>
		<div>Podaj termin notatki (rrrr-mm-dd):</div>
		<input type="text" id="termin_2" name="termin_2">
		<input type="submit" value="Modyfikuj notatkę">
	</form>
	<br/>
<form action="/com.laboratorium3/index.html" method="get">
<input type="submit" value="Powrót do strony logowania"/>
</form>
</body>
<script>
function myFunction() {
	  var i=document.getElementById('notatka').value;
	  var opisTekst="${opis}";
	  var terminyTekst="${terminy}";
	  var noteyTekst="${id_note}";
	  var opis=opisTekst.split(";");
	  var terminy=terminyTekst.split(";");
	  var user_id="${id_user}";
	  var user_id2="${id_user2}";
	  var user_id3="${id_user3}";
	  var user_id4="${id_user4}";
	  var user_id5="${id_user5}";
	  var user_id6="${id_user6}";
	  var user_id7="${id_user7}";
	  var notey=noteyTekst.split(";");
	  document.getElementById('opis').innerHTML=opis[i];
	  document.getElementById('termin').value=terminy[i];
	  document.getElementById('id_user').value=user_id;
	  document.getElementById('id_user2').value=user_id2;
	  document.getElementById('id_user3').value=user_id3;
	  document.getElementById('id_user4').value=user_id4;
	  document.getElementById('id_user5').value=user_id5;
	  document.getElementById('id_user6').value=user_id6;
	  document.getElementById('id_user7').value=user_id7;
	  document.getElementById('id_note').value=notey[i];
}
</script>
</html>