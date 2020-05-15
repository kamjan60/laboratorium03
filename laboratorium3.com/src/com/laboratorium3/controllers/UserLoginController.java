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

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value="/submitLoginForm.html", method=RequestMethod.POST)
	public ModelAndView ModelAndView( @ModelAttribute("user") User user, BindingResult result){
		
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("index");
			model.addObject("somethingnotmatch","Nieznany b³¹d logowania.");
			return model;
		}
		else { 
			DBDriver driver = new DBDriver();
			User userDB=driver.pullSpeciefiedData(user.getLogin(), user.getHaslo());
			if(userDB.getImie()==null) {
				ModelAndView model=new ModelAndView("index");
				if(Login.licznikProb<3) {
					model.addObject("somethingnotmatch","Has³o lub login niepoprawne. Proszê spróbowaæ ponownie!");
					Login.licznikProb+=1;	
					return model;
					
				}
				else {
					model.addObject("somethingnotmatch", "Zablokowano dalsze próby logowania w ramach bezpieczeñstwa!");
					model.addObject("disabled", "disabled");
					return model;
				}
				
			}
			else {
				user.setImie(userDB.getImie());
				user.setNazwisko(userDB.getNazwisko());
				user.setStanowisko(userDB.getStanowisko());
				user.setEmail(userDB.getEmail());
				user.setPlec(userDB.getPlec());
				user.setCzyAdmin(userDB.isCzyAdmin());
				user.setId(userDB.getId());
				Login.licznikProb=0;
				if(user.isCzyAdmin()) {
					ModelAndView model=new ModelAndView("LoginSuccessAdmin");
					model.addObject("user.login",user.getLogin());
					return model;
				}
				else {
					ModelAndView model=new ModelAndView("LoginSuccessUser");
					model.addObject("user.login",user.getLogin());
					Event event=driver.getEventInfo();
					String listaopcji="";
					String listaagend="";
					String listaterminow="";
					String listaidevent="";
					for(int i=0;i<event.getId_event().length;i++) {
						listaopcji+="<option value="+i+">"+event.getNazwa()[i].toString()+"</option>";
					}
					for(int i=0;i<event.getAgenda().length;i++) {
						listaagend+=event.getAgenda()[i]+";";
					}
					for(int i=0;i<event.getTermin_wydarzenia().length;i++) {
						listaterminow+=event.getTermin_wydarzenia()[i]+";";
					}
					for(int i=0;i<event.getId_event().length;i++) {
						listaidevent+=event.getId_event()[i]+";";
					}
					model.addObject("listofevents",listaopcji);
					model.addObject("agenda",listaagend);
					model.addObject("terminy",listaterminow);
					model.addObject("id_user",user.getId());
					model.addObject("id_event",listaidevent);
					return model;
			}
			}
		}
	}
	
	
}
