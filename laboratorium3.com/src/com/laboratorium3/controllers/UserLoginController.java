package com.laboratorium3.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.*;

@Controller
public class UserLoginController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		DBDriver driver = new DBDriver();
		return model;
	}

	@RequestMapping(value = "/submitLoginForm.html", method = RequestMethod.POST)
	public ModelAndView ModelAndView(@ModelAttribute("user") User user, BindingResult result) {

		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("index");
			model.addObject("somethingnotmatch", "Nieznany b³¹d logowania.");
			return model;
		} else {
			DBDriver driver = new DBDriver();
			User userDB = driver.pullSpeciefiedData(user.getLogin(), user.getHaslo());
			if (userDB.getImie() == null) {
				ModelAndView model = new ModelAndView("index");
				if (Login.licznikProb < 3) {
					model.addObject("somethingnotmatch", "Has³o lub login niepoprawne. Proszê spróbowaæ ponownie!");
					Login.licznikProb += 1;
					return model;

				} else {
					model.addObject("somethingnotmatch", "Zablokowano dalsze próby logowania w ramach bezpieczeñstwa!");
					model.addObject("disabled", "disabled");
					return model;
				}

			} else {
				user.setImie(userDB.getImie());
				user.setNazwisko(userDB.getNazwisko());
				user.setStanowisko(userDB.getStanowisko());
				user.setEmail(userDB.getEmail());
				user.setPlec(userDB.getPlec());
				user.setCzyAdmin(userDB.isCzyAdmin());
				user.setId(userDB.getId());
				Login.licznikProb = 0;
				if (user.isCzyAdmin()) {
					ModelAndView model = new ModelAndView("LoginSuccessAdmin");
					model.addObject("userlogin", user.getLogin());
					Note note = driver.getNoteInfo();
					String listaopcji = "";
					String listaopisow = "";
					String listaterminow = "";
					String listaidnote = "";
					String listofnotes2="";
					String listofusers="";

					for (int i = 0; i < note.getId_Note().length; i++) {
						listaopcji += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
					}
					for (int i = 0; i < note.getAgenda().length; i++) {
						listaopisow += note.getAgenda()[i] + ";";
					}
					for (int i = 0; i < note.getTermin_wydarzenia().length; i++) {
						listaterminow += note.getTermin_wydarzenia()[i] + ";";
					}
					for (int i = 0; i < note.getId_Note().length; i++) {
						listaidnote += note.getId_Note()[i] + ";";
					}
					
					User user2 = new User();
					user2=driver.getUserLoginInfo();
					user2.setListaloginow(driver.getUserLoginInfo().getListaloginow());
					for (int i = 0; i < note.getId_Note().length; i++) {
						listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
					}
					for (int i = 0; i < user2.getListaloginow().length; i++) {
						listofusers += "<option value=" + i + ">" + user2.getListaloginow()[i].toString() + "</option>";
					}
					model.addObject("listofnotes2",listofnotes2);
					model.addObject("listofusers",listofusers);
					
					
					model.addObject("listofnotes", listaopcji);
					model.addObject("opis", listaopisow);
					model.addObject("terminy", listaterminow);
					model.addObject("id_user", user.getId());
					model.addObject("id_user2", user.getId());
					model.addObject("id_user3", user.getId());
					model.addObject("id_user4", user.getId());
					model.addObject("id_user5", user.getId());
					model.addObject("id_user6", user.getId());
					model.addObject("id_user7", user.getId());
					model.addObject("id_note", listaidnote);
					return model;
				} else {
					ModelAndView model = new ModelAndView("LoginSuccessUser");
					model.addObject("userlogin", user.getLogin());
					Note note = driver.getNoteInfo();
					user.setListaloginow(driver.getUserLoginInfo().getListaloginow());
					String listaopcji = "";
					String listaopisow = "";
					String listaterminow = "";
					String listaidnote = "";
					String listofnotes2="";
					String listofusers="";
					HashUserNotes hun = new HashUserNotes();
					if(driver.getUsersNotes(user.getId()).getId_note()!=null) {
						hun = driver.getUsersNotes(user.getId());
						for (int i = 0; i < note.getId_Note().length; i++) {
							for(int j=0;j<hun.getId_note().length; j++) {
								if(hun.id_note[j]==note.getId_Note()[i]) {
									listaopcji += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
								}
							}
						}
					}
					for (int i = 0; i < note.getAgenda().length; i++) {
						listaopisow += note.getAgenda()[i] + ";";
					}
					for (int i = 0; i < note.getTermin_wydarzenia().length; i++) {
						listaterminow += note.getTermin_wydarzenia()[i] + ";";
					}
					for (int i = 0; i < note.getId_Note().length; i++) {
						listaidnote += note.getId_Note()[i] + ";";
					}
					if(driver.getUsersNotes(user.getId()).getId_note()!=null) {
						hun = driver.getUsersNotes(user.getId());
						for (int i = 0; i < note.getId_Note().length; i++) {
							for(int j=0;j<hun.getId_note().length; j++) {
								if(hun.id_note[j]==note.getId_Note()[i]) {
									listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
								}
							}
						}
					}
					for (int i = 0; i < user.getListaloginow().length; i++) {
						listofusers += "<option value=" + i + ">" + user.getListaloginow()[i].toString() + "</option>";
					}
					model.addObject("listofnotes", listaopcji);
					model.addObject("listofnotes2",listofnotes2);
					model.addObject("listofusers",listofusers);
					model.addObject("opis", listaopisow);
					model.addObject("terminy", listaterminow);
					model.addObject("id_user", user.getId());
					model.addObject("id_user2", user.getId());
					model.addObject("id_user3", user.getId());
					model.addObject("id_user4", user.getId());
					model.addObject("id_user5", user.getId());
					model.addObject("id_note", listaidnote);
					return model;
				}
			}
		}
	}

}
