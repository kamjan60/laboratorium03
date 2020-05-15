package com.laboratorium3.controllers;

public class Event {
	private int[] id_event;
	private String[] nazwa;
	private String[] agenda;
	private String[] termin_wydarzenia;
	public int[] getId_event() {
		return id_event;
	}
	public void setId_event(int[] id_event) {
		this.id_event = id_event;
	}
	public String[] getNazwa() {
		return nazwa;
	}
	public void setNazwa(String[] nazwa) {
		this.nazwa = nazwa;
	}
	public String[] getAgenda() {
		return agenda;
	}
	public void setAgenda(String[] agenda) {
		this.agenda = agenda;
	}
	public String[] getTermin_wydarzenia() {
		return termin_wydarzenia;
	}
	public void setTermin_wydarzenia(String[] termin_wydarzenia) {
		this.termin_wydarzenia = termin_wydarzenia;
	}
	public Event(int[] id_event,String[] nazwa,String[] agenda,String[] termin_wydarzenia) {
		this.id_event=id_event;
		this.nazwa=nazwa;
		this.agenda=agenda;
		this.termin_wydarzenia=termin_wydarzenia;
	}
	public Event() {
		
	}
}
