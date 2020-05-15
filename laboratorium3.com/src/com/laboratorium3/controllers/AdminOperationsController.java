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
	
	@RequestMapping(value="/addUser.html", method=RequestMethod.POST)
	public ModelAndView addUser(){
		ModelAndView model = new ModelAndView("Registration");
		return model;
	}
	
	@RequestMapping(value="/deleteUser.html", method=RequestMethod.POST)
	public ModelAndView deleteUser(@Valid @ModelAttribute("user") User user, BindingResult result){
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("AnyOperation");
			model.addObject("somethingwentwrong","Nie uda³o siê wykonaæ operacji.");
			return model;
		}
		else {
			DBDriver driver = new DBDriver();
			driver.deleteUserWithId(user.getId());
			ModelAndView model=new ModelAndView("AnyOperation");
			model.addObject("a","Operacja zakoñczona powodzeniem!");
			return model;
		}
	}
	
	@RequestMapping(value="/modifyUser.html", method=RequestMethod.POST)
	public ModelAndView ModifyUser(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("id_2") int id_2){
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("AnyOperation");
			model.addObject("somethingwentwrong","Nie uda³o siê wykonaæ operacji.");
			return model;
		}
		else {
			DBDriver driver = new DBDriver();
			user.setId(id_2);
			driver.changePasswordForUser(user.getId(),user.getHaslo());
			ModelAndView model=new ModelAndView("AnyOperation");
			model.addObject("a","Operacja zakoñczona powodzeniem!");
			return model;
		}
	}
	@RequestMapping(value="/addEvent.html", method=RequestMethod.POST)
	public ModelAndView AddEvent(@RequestParam("nazwa") String eventnazwa, @RequestParam("agenda") String eventagenda, @RequestParam("termin_wydarzenia") String eventtermin){
			DBDriver driver = new DBDriver();
			driver.addEvent(eventnazwa, eventagenda, eventtermin);
			ModelAndView model=new ModelAndView("AnyOperation");
			model.addObject("a","Operacja zakoñczona powodzeniem!");
			return model;
	}
	@RequestMapping(value="/deleteEvent.html", method=RequestMethod.POST)
	public ModelAndView deleteEvent(@RequestParam("id_event") int id_event){
			DBDriver driver = new DBDriver();
			driver.deleteEvent(id_event);
			ModelAndView model=new ModelAndView("AnyOperation");
			model.addObject("a","Operacja zakoñczona powodzeniem!");
			return model;
	}
	@RequestMapping(value="/modifyEvent.html", method=RequestMethod.POST)
	public ModelAndView modifyEvent(@RequestParam("id_event_2") int id_event, @RequestParam("nazwa_2") String eventnazwa, @RequestParam("agenda_2") String eventagenda, @RequestParam("termin_wydarzenia_2") String eventtermin){
			DBDriver driver = new DBDriver();
			driver.modifyEvent(id_event,eventnazwa,eventagenda,eventtermin);
			ModelAndView model=new ModelAndView("AnyOperation");
			model.addObject("a","Operacja zakoñczona powodzeniem!");
			return model;
	}
	@RequestMapping(value="/acceptRegister.html", method=RequestMethod.POST)
	public ModelAndView acceptRegister(@RequestParam("id_event_a") int id_event_a){
			DBDriver driver = new DBDriver();
			driver.acceptRegister(id_event_a);
			ModelAndView model=new ModelAndView("AnyOperation");
			model.addObject("a","Operacja zakoñczona powodzeniem!");
			return model;
	}
	@RequestMapping(value="/denyRegister.html", method=RequestMethod.POST)
	public ModelAndView denyRegister(@RequestParam("id_event_d") int id_event_d){
			DBDriver driver = new DBDriver();
			driver.denyRegister(id_event_d);
			ModelAndView model=new ModelAndView("AnyOperation");
			model.addObject("a","Operacja zakoñczona powodzeniem!");
			return model;
	}
	
	
	
}
