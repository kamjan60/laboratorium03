package com.laboratorium3.controllers;

public class Note {
	private int[] id_note;
	private String[] nazwa;
	private String[] opis;
	private String[] termin;
	private int pojid_note;
	private String pojnazwa;
	private String pojtermin;
	private String pojopis;
	public int[] getId_Note() {
		return id_note;
	}

	public void setId_note(int[] id_note) {
		this.id_note = id_note;
	}

	public String[] getNazwa() {
		return nazwa;
	}

	public void setNazwa(String[] nazwa) {
		this.nazwa = nazwa;
	}

	public String[] getAgenda() {
		return opis;
	}

	public void setAgenda(String[] opis) {
		this.opis = opis;
	}

	public String[] getTermin_wydarzenia() {
		return termin;
	}

	public void setTermin_wydarzenia(String[] termin) {
		this.termin = termin;
	}

	public Note(int[] id_note, String[] nazwa, String[] opis, String[] termin) {
		this.id_note = id_note;
		this.nazwa = nazwa;
		this.opis = opis;
		this.termin = termin;
	}
	
	public Note(int id_note, String nazwa, String opis, String termin) {
		this.setPojid_note(id_note);
		this.setPojnazwa(nazwa);
		this.setPojopis(opis);
		this.setPojtermin(termin);
	}
	public Note() {

	}

	public int getPojid_note() {
		return pojid_note;
	}

	public void setPojid_note(int pojid_note) {
		this.pojid_note = pojid_note;
	}

	public String getPojnazwa() {
		return pojnazwa;
	}

	public void setPojnazwa(String pojnazwa) {
		this.pojnazwa = pojnazwa;
	}

	public String getPojopis() {
		return pojopis;
	}

	public void setPojopis(String pojopis) {
		this.pojopis = pojopis;
	}

	public String getPojtermin() {
		return pojtermin;
	}

	public void setPojtermin(String pojtermin) {
		this.pojtermin = pojtermin;
	}
}
