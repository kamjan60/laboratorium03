package com.laboratorium3.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.sql.*;

@Controller
public class AdminOperationsController {

	@RequestMapping(value = "/deleteUser.html", method = RequestMethod.POST)
	public ModelAndView deleteUser(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("id_user2") int id_user2) {
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("LoginSuccessAdmin");
			model.addObject("somethingwentwrong", "Nie uda³o siê wykonaæ operacji.");
			DBDriver driver = new DBDriver();
			User user2 = driver.pullSpeciefiedData(id_user2);
			String userLogin = user2.getLogin();
			
			Note note = driver.getNoteInfo();
			String listaopcji = "";
			String listaopisow = "";
			String listaterminow = "";
			String listaidnote = "";
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
			
			String listofnotes2="";
			String listofusers="";
			User user3 = new User();
			user3=driver.getUserLoginInfo();
			user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
			for (int i = 0; i < note.getId_Note().length; i++) {
				listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
			}
			for (int i = 0; i < user3.getListaloginow().length; i++) {
				listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
			}
			model.addObject("listofnotes2",listofnotes2);
			model.addObject("listofusers",listofusers);
			
			
			
			model.addObject("userlogin", userLogin);
			model.addObject("listofnotes", listaopcji);
			model.addObject("opis", listaopisow);
			model.addObject("terminy", listaterminow);
			model.addObject("id_note", listaidnote);
			model.addObject("id_user", id_user2);
			model.addObject("id_user2", id_user2);
			model.addObject("id_user3", id_user2);
			model.addObject("id_user4", id_user2);
			model.addObject("id_user5", id_user2);
			model.addObject("id_user6", id_user2);
			model.addObject("id_user7", id_user2);
			return model;
		} else {
			DBDriver driver = new DBDriver();
			driver.deleteUserWithId(user.getId());
			ModelAndView model = new ModelAndView("LoginSuccessAdmin");
			model.addObject("a", "Operacja zakoñczona powodzeniem!");
			User user2 = driver.pullSpeciefiedData(id_user2);
			String userLogin = user2.getLogin();
			
			Note note = driver.getNoteInfo();
			String listaopcji = "";
			String listaopisow = "";
			String listaterminow = "";
			String listaidnote = "";
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
			
			String listofnotes2="";
			String listofusers="";
			User user3 = new User();
			user3=driver.getUserLoginInfo();
			user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
			for (int i = 0; i < note.getId_Note().length; i++) {
				listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
			}
			for (int i = 0; i < user3.getListaloginow().length; i++) {
				listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
			}
			model.addObject("listofnotes2",listofnotes2);
			model.addObject("listofusers",listofusers);
			
			
			
			model.addObject("userlogin", userLogin);
			model.addObject("listofnotes", listaopcji);
			model.addObject("opis", listaopisow);
			model.addObject("terminy", listaterminow);
			model.addObject("id_note", listaidnote);
			model.addObject("id_user", id_user2);
			model.addObject("id_user2", id_user2);
			model.addObject("id_user3", id_user2);
			model.addObject("id_user4", id_user2);
			model.addObject("id_user5", id_user2);
			model.addObject("id_user6", id_user2);
			model.addObject("id_user7", id_user2);
			return model;
		}
	}

	@RequestMapping(value = "/modifyUser.html", method = RequestMethod.POST)
	public ModelAndView ModifyUser(@Valid @ModelAttribute("user") User user, BindingResult result, 
			@RequestParam("id_2") int id_2, @RequestParam("id_user3") int id_user3) {
		if(id_2==0) {
			ModelAndView model = new ModelAndView("LoginSuccessAdmin");
			model.addObject("somethingwentwrong", "Nie uda³o siê wykonaæ operacji.");
			DBDriver driver = new DBDriver();
			User user2 = driver.pullSpeciefiedData(id_user3);
			String userLogin = user2.getLogin();
			
			Note note = driver.getNoteInfo();
			String listaopcji = "";
			String listaopisow = "";
			String listaterminow = "";
			String listaidnote = "";
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
			
			String listofnotes2="";
			String listofusers="";
			User user3 = new User();
			user3=driver.getUserLoginInfo();
			user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
			for (int i = 0; i < note.getId_Note().length; i++) {
				listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
			}
			for (int i = 0; i < user3.getListaloginow().length; i++) {
				listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
			}
			model.addObject("listofnotes2",listofnotes2);
			model.addObject("listofusers",listofusers);
			
			
			
			model.addObject("userlogin", userLogin);
			model.addObject("listofnotes", listaopcji);
			model.addObject("opis", listaopisow);
			model.addObject("terminy", listaterminow);
			model.addObject("id_note", listaidnote);
			model.addObject("id_user", id_user3);
			model.addObject("id_user2", id_user3);
			model.addObject("id_user3", id_user3);
			model.addObject("id_user4", id_user3);
			model.addObject("id_user5", id_user3);
			model.addObject("id_user6", id_user3);
			model.addObject("id_user7", id_user3);
			return model;
		} else {
			DBDriver driver = new DBDriver();
			user.setId(id_2);
			driver.changePasswordForUser(user.getId(), user.getHaslo());
			ModelAndView model = new ModelAndView("LoginSuccessAdmin");
			model.addObject("a", "Operacja zakoñczona powodzeniem!");
			User user2 = driver.pullSpeciefiedData(id_user3);
			String userLogin = user2.getLogin();
			
			Note note = driver.getNoteInfo();
			String listaopcji = "";
			String listaopisow = "";
			String listaterminow = "";
			String listaidnote = "";
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
			
			String listofnotes2="";
			String listofusers="";
			User user3 = new User();
			user3=driver.getUserLoginInfo();
			user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
			for (int i = 0; i < note.getId_Note().length; i++) {
				listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
			}
			for (int i = 0; i < user3.getListaloginow().length; i++) {
				listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
			}
			model.addObject("listofnotes2",listofnotes2);
			model.addObject("listofusers",listofusers);
			
			
			
			model.addObject("userlogin", userLogin);
			model.addObject("listofnotes", listaopcji);
			model.addObject("opis", listaopisow);
			model.addObject("terminy", listaterminow);
			model.addObject("id_note", listaidnote);
			model.addObject("id_user", id_user3);
			model.addObject("id_user2", id_user3);
			model.addObject("id_user3", id_user3);
			model.addObject("id_user4", id_user3);
			model.addObject("id_user5", id_user3);
			model.addObject("id_user6", id_user3);
			model.addObject("id_user7", id_user3);
			return model;
		}
	}

	@RequestMapping(value = "/addNote.html", method = RequestMethod.POST)
	public ModelAndView AddNote(@RequestParam("nazwa") String notenazwa, @RequestParam("opis") String noteopis,
			@RequestParam("termin") String notetermin, @RequestParam("id_user4") int id_user4) {
		DBDriver driver = new DBDriver();
		if(notenazwa!="" && noteopis!="" && notetermin!="") {
			driver.addNote(notenazwa, noteopis, notetermin);
		}
		if(notenazwa!="" && noteopis!="" && notetermin=="") {
			driver.addNote(notenazwa, noteopis);
		}
		if(notenazwa!="" && notetermin=="" && noteopis=="") {
			driver.addNote(notenazwa);
		}
		if(notenazwa!="" && notetermin!="" && noteopis=="") {
			driver.addNoteDate(notenazwa, notetermin);
		}
		driver.AddNoteOwnerAsViewer(id_user4,notenazwa,noteopis,notetermin);
		
		ModelAndView model = new ModelAndView("LoginSuccessAdmin");
		model.addObject("a", "Operacja zakoñczona powodzeniem!");
		User user2 = driver.pullSpeciefiedData(id_user4);
		String userLogin = user2.getLogin();
		
		Note note = driver.getNoteInfo();
		String listaopcji = "";
		String listaopisow = "";
		String listaterminow = "";
		String listaidnote = "";
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
		
		
		
		String listofnotes2="";
		String listofusers="";
		User user3 = new User();
		user3=driver.getUserLoginInfo();
		user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
		for (int i = 0; i < note.getId_Note().length; i++) {
			listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
		}
		for (int i = 0; i < user3.getListaloginow().length; i++) {
			listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
		}
		model.addObject("listofnotes2",listofnotes2);
		model.addObject("listofusers",listofusers);
		
		
		model.addObject("userlogin", userLogin);
		model.addObject("listofnotes", listaopcji);
		model.addObject("opis", listaopisow);
		model.addObject("terminy", listaterminow);
		model.addObject("id_note", listaidnote);
		model.addObject("id_user", id_user4);
		model.addObject("id_user2", id_user4);
		model.addObject("id_user3", id_user4);
		model.addObject("id_user4", id_user4);
		model.addObject("id_user5", id_user4);
		model.addObject("id_user6", id_user4);
		model.addObject("id_user7", id_user4);
		return model;
	}

	@RequestMapping(value = "/deleteNote.html", method = RequestMethod.POST)
	public ModelAndView deleteNote(@RequestParam("id_note") int id_note, @RequestParam("id_user5") int id_user5) {
		DBDriver driver = new DBDriver();
		driver.deleteNote(id_note);
		ModelAndView model = new ModelAndView("LoginSuccessAdmin");
		model.addObject("a", "Operacja zakoñczona powodzeniem!");
		User user2 = driver.pullSpeciefiedData(id_user5);
		String userLogin = user2.getLogin();
		
		Note note = driver.getNoteInfo();
		String listaopcji = "";
		String listaopisow = "";
		String listaterminow = "";
		String listaidnote = "";
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
		
		
		String listofnotes2="";
		String listofusers="";
		User user3 = new User();
		user3=driver.getUserLoginInfo();
		user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
		for (int i = 0; i < note.getId_Note().length; i++) {
			listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
		}
		for (int i = 0; i < user3.getListaloginow().length; i++) {
			listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
		}
		model.addObject("listofnotes2",listofnotes2);
		model.addObject("listofusers",listofusers);
		
		
		model.addObject("userlogin", userLogin);
		model.addObject("listofnotes", listaopcji);
		model.addObject("opis", listaopisow);
		model.addObject("terminy", listaterminow);
		model.addObject("id_note", listaidnote);
		model.addObject("id_user", id_user5);
		model.addObject("id_user2", id_user5);
		model.addObject("id_user3", id_user5);
		model.addObject("id_user4", id_user5);
		model.addObject("id_user5", id_user5);
		model.addObject("id_user6", id_user5);
		model.addObject("id_user7", id_user5);
		return model;
	}

	@RequestMapping(value = "/modifyNote.html", method = RequestMethod.POST)
	public ModelAndView modifyNote(@RequestParam("id_note_2") int id_note,
			@RequestParam("nazwa_2") String notenazwa, @RequestParam("opis_2") String noteopis,
			@RequestParam("termin_2") String notetermin, @RequestParam("id_user6") int id_user6) {
		DBDriver driver = new DBDriver();
		ModelAndView model = new ModelAndView("LoginSuccessAdmin");
		model.addObject("a", "Operacja zakoñczona powodzeniem!");
		User user2 = driver.pullSpeciefiedData(id_user6);
		String userLogin = user2.getLogin();
		
		Note note = driver.getNoteInfo();
		String listaopcji = "";
		String listaopisow = "";
		String listaterminow = "";
		String listaidnote = "";

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

		
		String listofnotes2="";
		String listofusers="";
		User user3 = new User();
		user3=driver.getUserLoginInfo();
		user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
		
		for (int i = 0; i < note.getId_Note().length; i++) {
			listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
		}
		for (int i = 0; i < user3.getListaloginow().length; i++) {
			listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
		}
		model.addObject("listofnotes2",listofnotes2);
		model.addObject("listofusers",listofusers);
		
		
		model.addObject("userlogin", userLogin);
		model.addObject("listofnotes", listaopcji);
		model.addObject("opis", listaopisow);
		model.addObject("terminy", listaterminow);
		model.addObject("id_note", listaidnote);
		model.addObject("id_user", id_user6);
		model.addObject("id_user2", id_user6);
		model.addObject("id_user3", id_user6);
		model.addObject("id_user4", id_user6);
		model.addObject("id_user5", id_user6);
		model.addObject("id_user6", id_user6);
		model.addObject("id_user7", id_user6);
		
		
		if(notenazwa!="") {
			driver.modifyNoteNazwa(id_note, notenazwa);
		}
		if(notetermin!="") {
			driver.modifyNoteTermin(id_note, notetermin);
		}
		if(noteopis!="") {
			driver.modifyNoteOpis(id_note, noteopis);
		}
		
		
		
		return model;
	}

	
	@RequestMapping(value = "/addNoteViewer.html", method = RequestMethod.POST)
	public ModelAndView AddNoteViewer(@RequestParam("viewnotatka") int viewnotatka, @RequestParam("viewer") int viewer, @RequestParam("id_user7") int id_user7) {
		DBDriver driver = new DBDriver();
		User user2 = new User();
		user2=driver.getUserLoginInfo();
		ModelAndView model = new ModelAndView("LoginSuccessAdmin");
		model.addObject("a", "Operacja zakoñczona powodzeniem!");	
		Note note = driver.getNoteInfo();
		String listaopcji = "";
		String listaopisow = "";
		String listaterminow = "";
		String listaidnote = "";

		user2.setListaloginow(driver.getUserLoginInfo().getListaloginow());
		
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
		
		String listofnotes2="";
		String listofusers="";
		User user3 = new User();
		user3=driver.getUserLoginInfo();
		user3.setListaloginow(driver.getUserLoginInfo().getListaloginow());
		for (int i = 0; i < note.getId_Note().length; i++) {
			listofnotes2 += "<option value=" + i + ">" + note.getNazwa()[i].toString() + "</option>";
		}
		for (int i = 0; i < user3.getListaloginow().length; i++) {
			listofusers += "<option value=" + i + ">" + user3.getListaloginow()[i].toString() + "</option>";
		}
		model.addObject("listofnotes2",listofnotes2);
		model.addObject("listofusers",listofusers);
		
		User user = driver.pullSpeciefiedData(id_user7);
		String userLogin = user.getLogin();
		model.addObject("userlogin", userLogin);
		model.addObject("listofnotes", listaopcji);
		model.addObject("listofnotes2",listofnotes2);
		model.addObject("listofusers",listofusers);
		model.addObject("opis", listaopisow);
		model.addObject("terminy", listaterminow);
		model.addObject("id_note", listaidnote);
		model.addObject("id_user", id_user7);
		model.addObject("id_user2", id_user7);
		model.addObject("id_user3", id_user7);
		model.addObject("id_user4", id_user7);
		model.addObject("id_user5", id_user7);
		
		int id_user_viewer=driver.getUserIdByLogin(user2.getListaloginow()[viewer]);
		int id_note_viewer=driver.getNoteIdByNazwa(note.getNazwa()[viewnotatka]);
		driver.addNoteViewer(id_user_viewer, id_note_viewer);
		
		
		return model;
	}
}
