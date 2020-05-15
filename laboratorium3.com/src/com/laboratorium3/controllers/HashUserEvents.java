package com.laboratorium3.controllers;

public class HashUserEvents {
	private int id_user;
	private int id_event;
	private String typ_uczestnictwa;
	private String wyzywienie;
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_event() {
		return id_event;
	}
	public void setId_event(int id_event) {
		this.id_event = id_event;
	}
	public String getTyp_uczestnictwa() {
		return typ_uczestnictwa;
	}
	public void setTyp_uczestnictwa(String typ_uczestnictwa) {
		this.typ_uczestnictwa = typ_uczestnictwa;
	}
	public String getWyzywienie() {
		return wyzywienie;
	}
	public void setWyzywienie(String wyzywienie) {
		this.wyzywienie = wyzywienie;
	}
}
