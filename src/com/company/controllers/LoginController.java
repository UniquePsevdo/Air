package com.company.controllers;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.dao.FormValidationGroup;
import com.company.dao.User;
import com.company.service.UsersService;

@Controller
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/login")
	public String login(){
		return "login" ;
	}
	
	@RequestMapping("/newaccount")
	public String registration (Model model){
		model.addAttribute("user", new User()) ; // related with commandName from newaccount.jsp
		return "newaccount";
	}
	
	@RequestMapping(value="/createaccount", method = RequestMethod.POST)
	public String createaccount(HttpSession session, @Validated(FormValidationGroup.class) User user, BindingResult result){ 
		
		if(result.hasErrors()){	
			return "newaccount";
		}		
		
		user.setEnabled(true); // account activation
		
		if(usersService.exists(user.getUsername())){
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount" ;
		}
		
		if(usersService.companyExists(user.getName(), user.getCity())){
			result.rejectValue("name", "DuplicateKey.user.name");
			return "newaccount" ;
		}
		
		if(usersService.emailExists(user.getEmail())){
			result.rejectValue("email", "DuplicateKey.user.email");
			return "newaccount" ;
		}
		
		usersService.create(user) ;		
		return "accountcreated";
	}
	
	@RequestMapping("/loggedout")
	public String loggedout (){
		return "loggedout";
	}
	
	@RequestMapping("/denied")
	public String denied (){
		return "denied";
	}

}
