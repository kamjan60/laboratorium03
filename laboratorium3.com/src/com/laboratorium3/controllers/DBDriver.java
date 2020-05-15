package com.laboratorium3.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBDriver {
	static String imie="DefaultTim";
	static String nazwisko="DefaultJank";
	static String stanowisko="DefaultInf";
	static String plec="Mezczyzna";
	static String email="Default@email.com";
	static String login="Default";
	static String haslo="Default";
	static int id;
	public void pushData() {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("INSERT INTO user (imie,nazwisko,stanowisko,plec,email,login,haslo) VALUES (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			prepStmt.setString(1, imie);
			prepStmt.setString(2, nazwisko);
			prepStmt.setString(3, stanowisko);
			prepStmt.setString(4, plec);
			prepStmt.setString(5, email);
			prepStmt.setString(6, login);
			prepStmt.setString(7, haslo);
			prepStmt.executeUpdate();
			
			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			while(generatedKeys.next()) {
				id=generatedKeys.getInt(1);
			}
			
			prepStmt = myConn.prepareStatement("INSERT INTO hashuserrole (id_user, id_role) VALUES (?,?)");
			prepStmt.setInt(1, id);
			prepStmt.setInt(2, 1);
			prepStmt.executeUpdate();
		}	
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	
	}
	public void getData(String imie, String nazwisko, String stanowisko, String plec, String email, String login, String haslo) {
		DBDriver.imie=imie;
		DBDriver.nazwisko=nazwisko;
		DBDriver.stanowisko=stanowisko;
		DBDriver.plec=plec;
		DBDriver.email=email;
		DBDriver.login=login;
		DBDriver.haslo=haslo;
	}
	
	public User pullSpeciefiedData(String login, String pswd){
		User user=new User();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("SELECT * FROM user WHERE user.login=? AND user.haslo=?");
			prepStmt.setString(1, login);
			prepStmt.setString(2, pswd);
			prepStmt.executeQuery();
			ResultSet results=prepStmt.getResultSet();
			while(results.next()) {
				user = new User(results.getString(2),results.getString(3),results.getString(4),results.getString(5),results.getString(6),login, pswd,results.getInt(1));
			}
			prepStmt = myConn.prepareStatement("SELECT * FROM hashuserrole WHERE hashuserrole.id_user=?");
			prepStmt.setInt(1, user.getId());
			prepStmt.executeQuery();
			ResultSet results2=prepStmt.getResultSet();
			
			while(results2.next()) {
				if(results2.getInt(3)==2) {
					user.setCzyAdmin(true);
				}
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}
	public Event getEventInfo() {
		Event event=new Event();
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("SELECT * FROM event");
			prepStmt.executeQuery();
			ResultSet results=prepStmt.getResultSet();
			results.last();
			int i=0;
			int[] id_event_tab=new int[results.getRow()];
			String[] nazwa_tab=new String[results.getRow()];
			String[] agenda_tab=new String[results.getRow()];
			String[] termin_wydarzenia_tab=new String[results.getRow()];
			results.beforeFirst();
			while(results.next()) {
				id_event_tab[i]=results.getInt(1);
				nazwa_tab[i]=results.getString(2);
				agenda_tab[i]=results.getString(3);
				termin_wydarzenia_tab[i]=results.getString(4);
				i++;
			}
			event=new Event(id_event_tab,nazwa_tab,agenda_tab,termin_wydarzenia_tab);
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return event;
	}
	public void pushEventToDB(int id_event,int id_user,String typ_uczestnictwa,String wyzywienie) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("INSERT INTO hashuserevents (id_event,id_user,typ_uczestnictwa,wyzywienie) VALUES (?,?,?,?)");
			prepStmt.setInt(1, id_event);
			prepStmt.setInt(2, id_user);
			prepStmt.setString(3, typ_uczestnictwa);
			prepStmt.setString(4,wyzywienie);
			prepStmt.executeUpdate();

		}	
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void deleteUserWithId(int id) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("DELETE FROM hashuserrole WHERE id_user=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			prepStmt = myConn.prepareStatement("DELETE FROM hashuserevents WHERE id_user=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			prepStmt = myConn.prepareStatement("DELETE FROM user WHERE id_user=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void changePasswordForUser(int id_user,String haslo) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("UPDATE user SET haslo=? WHERE id_user=?");
			prepStmt.setString(1, haslo);
			prepStmt.setInt(2, id_user);
			prepStmt.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addEvent(String nazwa, String agenda, String termin_wydarzenia) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("INSERT INTO event (nazwa, agenda, termin_wydarzenia) VALUES(?,?,?)");
			prepStmt.setString(1, nazwa);
			prepStmt.setString(2, agenda);
			prepStmt.setString(3, termin_wydarzenia);
			prepStmt.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void deleteEvent(int id_event) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("DELETE FROM event WHERE id_event=?");
			prepStmt.setInt(1, id_event);
			prepStmt.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void modifyEvent(int id_event, String eventnazwa, String eventagenda, String eventtermin) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("UPDATE event SET nazwa=?,agenda=?,termin_wydarzenia=? WHERE id_event=?");
			prepStmt.setString(1, eventnazwa);
			prepStmt.setString(2, eventagenda);
			prepStmt.setString(3, eventtermin);
			prepStmt.setInt(4, id_event);
			prepStmt.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void acceptRegister(int id_hashuserevents) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("UPDATE hashuserevents SET czyZaakceptowano=1 WHERE id_hashuserevents=?");
			prepStmt.setInt(1, id_hashuserevents);
			prepStmt.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	} 
	public void denyRegister(int id_hashuserevents) {
		try {
			Class.forName("com.laboratorium3.controllers.DBDriver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT","user","ZAQ!2wsx");
			PreparedStatement prepStmt = myConn.prepareStatement("UPDATE hashuserevents SET czyZaakceptowano=0 WHERE id_hashuserevents=?");
			prepStmt.setInt(1, id_hashuserevents);
			prepStmt.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	} 
}
