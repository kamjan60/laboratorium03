package com.laboratorium3.controllers;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.*;
import java.util.Properties;

@Controller
public class UserRegisterController {


	@RequestMapping(value="/submitRegisterForm.html", method=RequestMethod.POST)
	public ModelAndView submitRegisterForm( @Valid @ModelAttribute("user") User user, BindingResult result){
		if(result.hasErrors() || !user.getHaslo().equals(user.getPowthaslo())) {
			ModelAndView model = new ModelAndView("Registration");
			if(!user.getHaslo().equals(user.getPowthaslo())) {
				model.addObject("pwdnotmatch","Has³a nie pasuj¹!");
			}
			else {
				model.addObject("pwdnotmatch","");
			}
			return model;
		}
		else { 
			//Po³¹czenie do bazy danych (MYSQL)
			DBDriver driver = new DBDriver();
			driver.getData(user.getImie(), user.getNazwisko(), user.getStanowisko(), user.getPlec(), user.getEmail(), user.getLogin(), user.getHaslo());
			driver.pushData();
			ModelAndView model=new ModelAndView("RegisterSuccess");
			String from = "labinterfejsybd@gmail.com";
			String to = user.getEmail();
			String host = "smtp.gmail.com";
			Properties properties = System.getProperties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {

	                return new PasswordAuthentication(from, "labint1060");

	            }

	        });

	        session.setDebug(true);

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject("Rejestracja zakonoñczona powodzeniem!");

	            // Now set the actual message
	            message.setText("Uda³o Ci siê zarejestrowaæ, "+user.getLogin());

	            System.out.println("sending...");
	            // Send message
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
			return model;
		}
	}
	
	@RequestMapping(value="/Registration.html", method=RequestMethod.POST)
	public ModelAndView Registration() {
		ModelAndView model=new ModelAndView("Registration");
		return model;
	}
}
