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
public class EventRegisterController {
	
	@RequestMapping(value="/eventRegister.html", method=RequestMethod.POST)
	public ModelAndView ModelAndView(@Valid @ModelAttribute("hashuserevents") HashUserEvents hashuserevents, BindingResult result){
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("eventRegister");
			model.addObject("somethingwentwrong","Nie uda�o si� zarejestrowa� wydarzenia.");
			return model;
		}
		else {
			DBDriver driver = new DBDriver();
			ModelAndView model=new ModelAndView("eventRegister");
			model.addObject("a","Rejestracja wydarzenia zako�czona powodzeniem!");
			driver.pushEventToDB(hashuserevents.getId_event(),hashuserevents.getId_user(),hashuserevents.getTyp_uczestnictwa(),hashuserevents.getWyzywienie());
			return model;
		}
	}
	
	
}
