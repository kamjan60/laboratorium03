package com.laboratorium3.controllers;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	private int id;

	@Pattern(regexp = "[^0-9]*", message = "Imiê nie mo¿e zawieraæ liczby!")
	@Size(min = 1, message = "Nie mo¿e byæ puste!")
	@Size(max = 40, message = "Liczba znaków wykracza poza wymagany zakres (40)")
	private String imie;

	@Pattern(regexp = "[^0-9]*", message = "Nazwisko nie mo¿e zawieraæ liczby!")
	@Size(min = 1, message = "Nie mo¿e byæ puste!")
	@Size(max = 50, message = "Liczba znaków wykracza poza wymagany zakres (40)")
	private String nazwisko;

	@Size(min = 1, message = "Nie mo¿e byæ puste!")
	@Size(max = 50, message = "Liczba znaków wykracza poza wymagany zakres (50)")
	private String stanowisko;

	@Size(min = 1, message = "Nie mo¿e byæ puste!")

	private String plec;

	@Pattern(regexp = "[\\S]*@[\\S]*[.][\\S]*", message = "Email musi zawieraæ znak @ oraz . zaœ zarówno przed nimi i po nich musza wystêpowaæ znaki.")
	@Size(min = 1, message = "Nie mo¿e byæ puste!")
	@Size(max = 100, message = "Liczba znaków wykracza poza wymagany zakres (100)")
	private String email;

	@Size(min = 5, max = 100, message = "Nie mo¿e byæ krótszy ni¿ 5 znaków!")
	@Size(max = 100, message = "Liczba znaków wykracza poza wymagany zakres (100)")
	private String login;

	@Size(min = 6, message = "Nie mo¿e byæ krótszy ni¿ 6 znaków!")
	@Size(max = 100, message = "Liczba znaków wykracza poza wymagany zakres (100)")
	private String haslo;
	
	private String[] listaloginow;
	private String powthaslo;

	private boolean czyAdmin = false;

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getStanowisko() {
		return stanowisko;
	}

	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		this.plec = plec;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getPowthaslo() {
		return powthaslo;
	}

	public void setPowthaslo(String powthaslo) {
		this.powthaslo = powthaslo;
	}

	public boolean isCzyAdmin() {
		return czyAdmin;
	}

	public void setCzyAdmin(boolean czyAdmin) {
		this.czyAdmin = czyAdmin;
	}

	public User(String imie, String nazwisko, String stanowisko, String plec, String email, String login, String haslo,
			int id) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.stanowisko = stanowisko;
		this.plec = plec;
		this.email = email;
		this.login = login;
		this.haslo = haslo;
		this.id = id;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getListaloginow() {
		return listaloginow;
	}

	public void setListaloginow(String[] listaloginow) {
		this.listaloginow = listaloginow;
	}

}
