package com.company.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dao.Ahu;
import com.company.dao.Producer;
import com.company.dao.Project;
import com.company.dao.User;
import com.company.emails.MySendMailService;
import com.company.service.AhusService;
import com.company.service.ProjectsService;
import com.company.service.UsersService;

@Controller
public class EmailController {

	@Autowired
	private MySendMailService mySendMailService;

	@Autowired
	private ProjectsService projectsService;
	
	@Autowired
	private AhusService ahusService;
	
	@Autowired
	private UsersService usersService ;

	@RequestMapping(value = "/projectsendemail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> projectsendemail(@RequestBody Map<String, Object> data) {
		int projects_id = Integer.parseInt((String) data.get("projects_id"));
		Project project = projectsService.getProjectById(projects_id);
		
		if (project != null) {
			User user = project.getUser();
			String usersemail = user.getEmail();
			String body1 = "Hi! The project consist of following AHUs: \n";
			String body2 = "Hi! You have been sent following AHUs: \n";
			
			List<Ahu> ahus = project.getAhus();
			for (int i = 0; i < ahus.size(); i++) {
				body1 = body1 + ahus.get(i).getSupplysystemname() + ahus.get(i).getExhaustsystemname() + " - " + ahus.get(i).getPremises() + "\n";
				body2 = body2 + ahus.get(i).getSupplysystemname() + ahus.get(i).getExhaustsystemname() + " - " + ahus.get(i).getPremises() + "\n";
			}
			
			body1 = body1 + "Waiting for your select. Cheers! \n";
			body1 = body1 + "\n" + "From: " + user.getUsername() + " (" + user.getName() + " - " + user.getCity()+ ")" ;
			
			List<Producer> producers = project.getProducers();
			body2 = body2 + "To following producers from " + producers.get(0).getProducerscity() + ": \n";
			for (int i = 0; i < producers.size(); i++) {
				body2 = body2 + producers.get(i).getCompanyname() + "\n";
			}
			body2 = body2 + "Cheers!";
			
			List<String> producersemails = new ArrayList<>();
			try{
				producersemails = projectsService.getProducersEmails(producers);	
			}catch(Exception e){}
			
			for (int i = 0; i < producersemails.size(); i++) {
				mySendMailService.sendEmail(producersemails.get(i), usersemail, "AHUs request of project: " + project.getProjectname(), body1);
			}
			mySendMailService.sendEmail(usersemail, usersemail, "AHUs request of project: " + project.getProjectname(), body2);
			Map<String, Object> data2 = new HashMap<String, Object>();
			data2.put("success", true);
			return data2;
		}
		return null;		
	}
	
	@RequestMapping(value = "/projectsendoneahuemail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> projectsendoneahuemail(@RequestBody Map<String, Object> data) {
		int ahusid = Integer.parseInt((String) data.get("ahusid"));
		Ahu ahu = new Ahu() ;
		try{
			ahu = ahusService.getAhuById(ahusid);	
		}catch(Exception e){}
		
		Project project = ahu.getProject() ;		 
		if (project != null) {
			User user = project.getUser();
			String usersemail = user.getEmail();
			String body1 = "Hi! New AHU " + ahu.getSupplysystemname() + ahu.getExhaustsystemname() + " - " + ahu.getPremises() + " has been created. \n";
			body1 = body1 + "\n" + "From: " + user.getUsername() + " (" + user.getName() + " - " + user.getCity()+ ")" ;
			String body2 = "Hi! You have been sent notification about AHU " + ahu.getSupplysystemname() + ahu.getExhaustsystemname() + " - " + ahu.getPremises() + " creation. \n";			
			body2 = body2 + "\n" + "To: " ;
			List<Producer> producers = project.getProducers();
			for (int i = 0; i < producers.size(); i++) {
				body2 = body2 + producers.get(i).getCompanyname() + " " + producers.get(i).getProducerscity() + "\n" ;
			}
			
			
			List<String> producersemails = new ArrayList<>();
			try{
				producersemails = projectsService.getProducersEmails(producers);	
			}catch(Exception e){}
			
				for (int i = 0; i < producersemails.size(); i++) {
					mySendMailService.sendEmail(producersemails.get(i), usersemail, "New AHU request of project: " + project.getProjectname(), body1);
				}
				mySendMailService.sendEmail(usersemail, usersemail, "New AHU request of project: " + project.getProjectname(), body2);
				Map<String, Object> data2 = new HashMap<String, Object>();
				data2.put("success", true);
				return data2;	
			}
		return null;				
	}
	
	
	@RequestMapping(value = "/uploadedahusendemail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> uploadedahusendemail(@RequestBody Map<String, Object> data, Principal principal) {
		int ahusid = Integer.parseInt((String) data.get("ahusid"));
		String note = (String) data.get("note");		
		Ahu ahu = new Ahu() ;
		try{
			ahu = ahusService.getAhuById(ahusid);	
		}catch(Exception e){}
		
		Project project = ahu.getProject() ;		 
		if (project != null) {
			String username = principal.getName() ;
			User user = new User() ;
			try{
				user = usersService.getUser(username) ;	
			}catch(Exception e){}
			
			String produceremail = user.getEmail() ;
			User projectowner = project.getUser();
			String usersemail = projectowner.getEmail();
			String body1 = "Hi! The AHU selection for " + ahu.getSupplysystemname() + ahu.getExhaustsystemname() + " - " + ahu.getPremises() + " has been uploaded.";
			String body2 = "Hey! You uploaded AHU selection for " + ahu.getSupplysystemname() + ahu.getExhaustsystemname() + " - " + ahu.getPremises() + ".";	
			if(note.length()>0){
				body1 = body1 + "\n" + "Note: " + note + "\n";
				body2 = body2 + "\n" + "Note: " + note + "\n";
			}
			
			if(produceremail != null){
				body1 = body1 + "\n" + "From: " + user.getUsername() + " (" + user.getName() + " - " + user.getCity()+ ")" ;
				body2 = body2 + "\n" + "To: " + projectowner.getUsername() + " (" + projectowner.getName() + " - " + projectowner.getCity()+ ")" ;
				mySendMailService.sendEmail(usersemail, produceremail, "AHU selection for " + ahu.getSupplysystemname() + ahu.getExhaustsystemname()+ " of project: " + project.getProjectname(), body1);
			mySendMailService.sendEmail(produceremail, produceremail, "AHU selection for " + ahu.getSupplysystemname() + ahu.getExhaustsystemname()+ " of project: " + project.getProjectname(), body2);
			}
			Map<String, Object> data2 = new HashMap<String, Object>();
			data2.put("success", true);
			return data2;
		}
		return null;
	}
	
	@RequestMapping(value = "/receivedahusendemail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> receivedahusendemail(@RequestBody Map<String, Object> data, Principal principal) {
		int ahusid = Integer.parseInt((String) data.get("ahusid"));
		String companyname = (String) data.get("companyname");
		String city = (String) data.get("city");
		String note = (String) data.get("note");		
		Ahu ahu = new Ahu() ;
		try{
			ahu = ahusService.getAhuById(ahusid);	
		}catch(Exception e){}
		
		Project project = ahu.getProject() ;		 
			if(project != null){
				User designer = project.getUser();
				String designeremail = designer.getEmail();	
			User producer = new User() ;
			try{
				producer = usersService.getProducer(companyname, city) ;	
			}catch(Exception e){}
			String produceremail = producer.getEmail() ; 
			String body1 = "Hi! I have following comment on the selection of " + ahu.getSupplysystemname() + ahu.getExhaustsystemname() + " - " + ahu.getPremises() + ":\n";
			String body2 = "Hey! You've sent following comment on the selection of " + ahu.getSupplysystemname() + ahu.getExhaustsystemname() + " - " + ahu.getPremises() + ":\n";	
			if(note.length()>0){
				body1 = body1 + "Comment: " + note + "\n";
				body2 = body2 + "Comment: " + note + "\n";
			}
			body1 = body1 + "\n" + "From: " + designer.getUsername() + " (" + designer.getName() + " - " + designer.getCity()+ ")" ;
			if(produceremail != null){
				body2 = body2 + "\n" + "To: " + producer.getUsername() + " (" + producer.getName() + " - " + producer.getCity()+ ")" ;
			mySendMailService.sendEmail(produceremail, designeremail, "Comment on AHU selection of " + ahu.getSupplysystemname() + ahu.getExhaustsystemname()+ " of project: " + project.getProjectname(), body1);
			}
				mySendMailService.sendEmail(designeremail, designeremail, "Comment on AHU selection of " + ahu.getSupplysystemname() + ahu.getExhaustsystemname()+ " of project: " + project.getProjectname(), body2);
				Map<String, Object> data2 = new HashMap<String, Object>();
				data2.put("success", true);
				return data2;
			}
			return null;
	}
	
	@RequestMapping(value = "/uploadedahusemail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> uploadedahusemail(@RequestBody Map<String, Object> data, Principal principal) {
		List<String> emptyindexes = (ArrayList<String>) data.get("emptyindexes") ;
		List<String> premises = (ArrayList<String>) data.get("premises") ;
		
		List<String> ahusids = (ArrayList<String>) data.get("ahusids") ;
		System.out.println(ahusids.size());
		
		List<Integer> ahusidsint = new ArrayList<Integer>() ;
		for(int i = 0; i < ahusids.size(); i++){
			ahusidsint.add(Integer.parseInt(ahusids.get(i))) ;
		}
		
		List<String> supnames = (ArrayList<String>) data.get("supnames") ;
		List<String> exnames = (ArrayList<String>) data.get("exnames") ;
		
		String body1 = "Hi! Here is the list of uploaded AHU selections: \n" ;
		String body2 = "Hey! Here is the list of AHU selections you have been uploaded: \n" ;
		
		for(int i = 0; i < premises.size(); i++ ){
			body1 = body1 + supnames.get(i) + exnames.get(i) + " - " + premises.get(i) + " ";
			body2 = body2 + supnames.get(i) + exnames.get(i) + " - " + premises.get(i) + " ";
			if(emptyindexes.contains(i)){
				body1 = body1 + "(not uploaded yet)" ;
				body2 = body2 + "(not uploaded yet)" ;
			}
			body1 = body1 + "\n" ;
			body2 = body2 + "\n" ;
		}
		
		body1 = body1 + "Check if everythig is correct and let me know if not. Cheers! \n" ;
		body2 = body2 + "Good luck! \n" ;
		
		Ahu ahu = new Ahu() ;
		try{			
			ahu = ahusService.getAhuById(ahusidsint.get(0)) ;
		}catch(Exception e){}
		Project project = ahu.getProject() ;
		
		if (project != null) {
			String username = principal.getName() ;
			User user = new User() ;
			try{
				user = usersService.getUser(username) ;	
			}catch(Exception e){}
			
			String produceremail = user.getEmail() ;
			
			User projectowner = project.getUser();
			String usersemail = projectowner.getEmail();
			
			if(produceremail!=null){
				body1 = body1 + "\n" + "From: " + user.getUsername() + " (" + user.getName() + " - " + user.getCity()+ ")" ;
				body2 = body2 + "\n" + "To: " + projectowner.getUsername() + " (" + projectowner.getName() + " - " + projectowner.getCity()+ ")" ;
				mySendMailService.sendEmail(usersemail, usersemail, "AHUs selection for project: " + project.getProjectname(), body1);
				mySendMailService.sendEmail(produceremail, usersemail, "AHUs selection for project: " + project.getProjectname(), body2);
			}
			Map<String, Object> data2 = new HashMap<String, Object>();
			data2.put("success", true);
			return data2;
		}
		return null ;
	}
}
