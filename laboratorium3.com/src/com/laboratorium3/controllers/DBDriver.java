package com.laboratorium3.controllers;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBDriver {
	static String imie = "DefaultTim";
	static String nazwisko = "DefaultJank";
	static String stanowisko = "DefaultInf";
	static String plec = "Mezczyzna";
	static String email = "Default@email.com";
	static String login = "Default";
	static String haslo = "Default";
	static int id;

	public void pushData() {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement(
					"INSERT INTO user (imie,nazwisko,stanowisko,plec,email,login,haslo) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			prepStmt.setString(1, imie);
			prepStmt.setString(2, nazwisko);
			prepStmt.setString(3, stanowisko);
			prepStmt.setString(4, plec);
			prepStmt.setString(5, email);
			prepStmt.setString(6, login);
			prepStmt.setString(7, haslo);
			prepStmt.executeUpdate();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			while (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			prepStmt = myConn.prepareStatement("INSERT INTO hashuserrole (id_user, id_role) VALUES (?,?)");
			prepStmt.setInt(1, id);
			prepStmt.setInt(2, 1);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public void getData(String imie, String nazwisko, String stanowisko, String plec, String email, String login,
			String haslo) {
		DBDriver.imie = imie;
		DBDriver.nazwisko = nazwisko;
		DBDriver.stanowisko = stanowisko;
		DBDriver.plec = plec;
		DBDriver.email = email;
		DBDriver.login = login;
		DBDriver.haslo = haslo;
	}
	
	public void AddNoteOwnerAsViewer(int id_user, String nazwa, String opis, String termin) {
		Note note = new Note();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt;
			if(termin!="") {
				prepStmt = myConn
						.prepareStatement("SELECT * FROM note WHERE note.nazwa=? AND note.opis=? AND note.termin=?");
				prepStmt.setString(1, nazwa);
				prepStmt.setString(2, opis);
				prepStmt.setString(3, termin);
			}
			else {
				prepStmt = myConn
						.prepareStatement("SELECT * FROM note WHERE note.nazwa=? AND note.opis=?");
				prepStmt.setString(1, nazwa);
				prepStmt.setString(2, opis);
			}
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			while (results.next()) {
				note = new Note(results.getInt(1), results.getString(2), results.getString(3), results.getString(4));
			}
			prepStmt = myConn.prepareStatement("INSERT INTO hashusernotes (id_user,id_note) VALUES (?,?)");
			prepStmt.setInt(1, id_user);
			prepStmt.setInt(2, note.getPojid_note());
			prepStmt.executeUpdate();
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public User pullSpeciefiedData(String login, String pswd) {
		User user = new User();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("SELECT * FROM user WHERE user.login=? AND user.haslo=?");
			prepStmt.setString(1, login);
			prepStmt.setString(2, pswd);
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			while (results.next()) {
				user = new User(results.getString(2), results.getString(3), results.getString(4), results.getString(5),
						results.getString(6), login, pswd, results.getInt(1));
			}
			prepStmt = myConn.prepareStatement("SELECT * FROM hashuserrole WHERE hashuserrole.id_user=?");
			prepStmt.setInt(1, user.getId());
			prepStmt.executeQuery();
			ResultSet results2 = prepStmt.getResultSet();

			while (results2.next()) {
				if (results2.getInt(3) == 2) {
					user.setCzyAdmin(true);
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}
	
	public User pullSpeciefiedData(int id_user) {
		User user = new User();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("SELECT * FROM user WHERE user.id_user=?");
			prepStmt.setInt(1, id_user);
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			while (results.next()) {
				user = new User(results.getString(2), results.getString(3), results.getString(4), results.getString(5),
						results.getString(6), results.getString(7), results.getString(8), id_user);
			}
			prepStmt = myConn.prepareStatement("SELECT * FROM hashuserrole WHERE hashuserrole.id_user=?");
			prepStmt.setInt(1, user.getId());
			prepStmt.executeQuery();
			ResultSet results2 = prepStmt.getResultSet();

			while (results2.next()) {
				if (results2.getInt(3) == 2) {
					user.setCzyAdmin(true);
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}
	public User getUserLoginInfo() {
		User user = new User();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("SELECT * FROM user", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			results.last();
			int i = 0;
			int[] id_user_tab = new int[results.getRow()];
			String[] login_tab = new String[results.getRow()];
			results.beforeFirst();
			while (results.next()) {
				id_user_tab[i] = results.getInt(1);
				login_tab[i] = results.getString(7);
				i++;
				
			}
			user.setListaloginow(login_tab);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}
	public HashUserNotes getUsersNotes(int id_user) {
		HashUserNotes hashusernotes = new HashUserNotes();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("SELECT * FROM hashusernotes WHERE hashusernotes.id_user=?", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			prepStmt.setInt(1, id_user);
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			results.last();
			int i=0;
			int[] id_tab = new int[results.getRow()];
			results.beforeFirst();
			while (results.next()) {
				id_tab[i]=results.getInt(2);
				i++;
			}
			hashusernotes.setId_note(id_tab);
			hashusernotes.setId_user(id_user);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return hashusernotes;
	}
	
	
	public int getUserIdByLogin(String login) {
		int id_user = 0;
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("SELECT * FROM user WHERE user.login=?");
			prepStmt.setString(1, login);
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			while (results.next()) {
				id_user=results.getInt(1);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return id_user;
	}
	public int getNoteIdByNazwa(String nazwa) {
		int id_note = 0;
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("SELECT * FROM note WHERE note.nazwa=?");
			prepStmt.setString(1, nazwa);
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			while (results.next()) {
				id_note=results.getInt(1);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return id_note;
	}
	public Note getNoteInfo() {
		Note note = new Note();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("SELECT * FROM note", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			prepStmt.executeQuery();
			ResultSet results = prepStmt.getResultSet();
			results.last();
			int i = 0;
			int[] id_note_tab = new int[results.getRow()];
			String[] nazwa_tab = new String[results.getRow()];
			String[] opis_tab = new String[results.getRow()];
			String[] termin_tab = new String[results.getRow()];
			results.beforeFirst();
			while (results.next()) {
				id_note_tab[i] = results.getInt(1);
				nazwa_tab[i] = results.getString(2);
				opis_tab[i] = results.getString(3);
				termin_tab[i] = results.getString(4);
				i++;
				
			}
			note = new Note(id_note_tab, nazwa_tab, opis_tab, termin_tab);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return note;
	}

	public void deleteUserWithId(int id) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("DELETE FROM hashuserrole WHERE id_user=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			prepStmt = myConn.prepareStatement("DELETE FROM hashusernotes WHERE id_user=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			prepStmt = myConn.prepareStatement("DELETE FROM user WHERE id_user=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void changePasswordForUser(int id_user, String haslo) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("UPDATE user SET haslo=? WHERE id_user=?");
			prepStmt.setString(1, haslo);
			prepStmt.setInt(2, id_user);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addNote(String nazwa, String opis, String termin) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("INSERT INTO note (nazwa, opis, termin) VALUES(?,?,?)");
			prepStmt.setString(1, nazwa);
			prepStmt.setString(2, opis);
			prepStmt.setString(3, termin);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addNote(String nazwa, String opis) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("INSERT INTO note (nazwa, opis) VALUES(?,?)");
			prepStmt.setString(1, nazwa);
			prepStmt.setString(2, opis);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addNote(String nazwa) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("INSERT INTO note (nazwa) VALUES(?)");
			prepStmt.setString(1, nazwa);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void addNoteViewer(int id_user, int id_note) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("INSERT INTO hashusernotes (id_note, id_user) VALUES(?,?)");
			prepStmt.setInt(1, id_note);
			prepStmt.setInt(2, id_user);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void addNoteDate(String nazwa, String termin) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("INSERT INTO note (nazwa, termin) VALUES(?,?)");
			prepStmt.setString(1, nazwa);
			prepStmt.setString(2, termin);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void deleteNote(int id_note) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("DELETE FROM hashusernotes WHERE id_note=?");
			prepStmt.setInt(1, id_note);
			prepStmt.executeUpdate();
			PreparedStatement prepStmt2 = myConn.prepareStatement("DELETE FROM note WHERE id_note=?");
			prepStmt2.setInt(1, id_note);
			prepStmt2.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void modifyNoteTermin(int id_note, String notetermin) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("UPDATE note SET termin=? WHERE id_note=?");
			prepStmt.setString(1, notetermin);
			prepStmt.setInt(2, id_note);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void modifyNoteOpis(int id_note, String noteopis) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("UPDATE note SET opis=? WHERE id_note=?");
			prepStmt.setString(1, noteopis);
			prepStmt.setInt(2, id_note);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void modifyNoteNazwa(int id_note, String notenazwa) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
					"user", "ZAQ!2wsx");
			PreparedStatement prepStmt = myConn
					.prepareStatement("UPDATE note SET nazwa=? WHERE id_note=?");
			prepStmt.setString(1, notenazwa);
			prepStmt.setInt(2, id_note);
			prepStmt.executeUpdate();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
