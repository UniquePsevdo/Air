package com.company.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dao.Ahu;
import com.company.dao.FormValidationGroup;
import com.company.dao.Producer;
import com.company.dao.Project;
import com.company.dao.User;
import com.company.service.ProjectsService;
import com.company.service.UsersService;

@Controller
public class ProjectsController {	
	
	boolean ready = false;
	boolean ready2 = false;
	boolean ready3 = false;

	@Autowired
	private ProjectsService projectsService;

	@Autowired
	private UsersService usersService;

	@RequestMapping(value="/projects", method = RequestMethod.GET)
	public String showProjects(Principal principal, Model model, HttpSession session) {
		session.setAttribute("valid", true);
		session.setAttribute("cityNotSelected", false);
		session.setAttribute("producersEmptyList", false);
		session.setAttribute("dublicateProjName", false);
		session.setAttribute("type", "main");
		
		String username = principal.getName();		
		Collection<Project> usersProjects = projectsService.getUsersProjects(username);
		model.addAttribute("usersProjects", usersProjects);

		List<String> distinctCities = usersService.getProducersCities();
		session.setAttribute("distinctCities", distinctCities);
		
		if(!model.containsAttribute("project")){			
			model.addAttribute("project", new Project()) ;           // commandName	
		}
		return "projects";
	}

	
	@RequestMapping(value = "/sendcity", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendcity(@RequestBody Map<String, Object> data,
			HttpSession session) {
		List<String> producers = new ArrayList<String>() ;
		
		if (data != null){
		String city = (String) data.get("city");
		producers = usersService.getProducersByCity(city);
		}		
		session.setAttribute("producers", producers);
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		ready = true;
		return data2;
	}

	@RequestMapping(value = "/getproducers", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getproducers(HttpSession session) {
		while (!ready) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		List<String> producers = (List<String>) session.getAttribute("producers");
		data.put("producers", producers);
		ready = false;
		session.removeAttribute("producers"); // эксперимент
		return data;
	}
	
	@RequestMapping(value = "/showerrors", method = RequestMethod.POST)
	public String showerrors (HttpSession session, @Validated(FormValidationGroup.class) Project project, BindingResult result){		
		if((Boolean)session.getAttribute("dublicateProjName") == true
				||(Boolean)session.getAttribute("cityNotSelected") == true
				||(Boolean)session.getAttribute("producersEmptyList") == true
				||result.hasErrors()){
			return "projects" ;
		}		
		return "redirect:/projects" ;
	}
	
	@RequestMapping(value = "/setvalidfalse", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> setValidFalse(HttpSession session){		
		session.setAttribute("valid", false);
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		return data2 ;		
	}
	
	
	@RequestMapping(value = "/saveprojectdata", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> saveprojectdata(Principal principal, @RequestBody Map<String, Object> data, HttpSession session, @ModelAttribute Project project) {		
			
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		
		
		String projectname = ((String) data.get("projectname")).trim();
		if(projectsService.projectnameExists(projectname)){                    
			session.setAttribute("dublicateProjName", true);
		}else{
			session.setAttribute("dublicateProjName", false);
		}
				
		String producerscity = ((String) data.get("producerscity")).trim();
		
		if(producerscity.contains("Select city")){
			session.setAttribute("cityNotSelected", true);			
		}else{
			session.setAttribute("cityNotSelected", false);
		}
		
		ArrayList<String> producerslist = (ArrayList<String>) data.get("producerslist");
		
		if(producerslist.size()==0){
			session.setAttribute("producersEmptyList", true);			
		}else{
			session.setAttribute("producersEmptyList", false);
		}
		
		
		String winterairtemp = ((String) data.get("winterairtemp")).trim();
		String summerairtemp = ((String) data.get("summerairtemp")).trim();
		Pattern temperature = Pattern.compile("[+-]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*") ;
        Matcher tempw = temperature.matcher(winterairtemp) ;
        Matcher temps = temperature.matcher(summerairtemp) ;
		
		String winterhumid = ((String) data.get("winterhumid")).trim();
		String summerhumid = ((String) data.get("summerhumid")).trim();
		Pattern humidity = Pattern.compile("[+]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*") ;
		Matcher humw = humidity.matcher(winterhumid) ;
        Matcher hums = humidity.matcher(summerhumid) ;
		
		if(!(tempw.matches()&&temps.matches()&&humw.matches()&&hums.matches())
			|| producerslist.size()==0
			|| (Boolean) session.getAttribute("dublicateProjName") == true){        	
        	return null;
        }
		
		session.setAttribute("valid", true);
		User user = new User();
		user.setUsername(principal.getName());	
		project.setUser(user);		
		project.setCreationdate(new Date()); //
		project.setProjectname(projectname);
		project.setWinterairtemp(winterairtemp);
		project.setSummerairtemp(summerairtemp);
		project.setWinterhumid(winterhumid);
		project.setSummerhumid(summerhumid);
		project.setProducerscity(producerscity);
		project.setProducers(new ArrayList<Producer>());
		
		List<User> cityproducers = usersService.getProducersByCity2(producerscity) ;		 
		 		 
		 for(int i = 0; i < cityproducers.size(); i++){
			 User temp = cityproducers.get(i) ;
			 for(int j = 0; j < producerslist.size(); j++){				 
				 if(temp.getName().equals(producerslist.get(j))){
					 Producer prodtemp = new Producer(temp.getName(), producerscity);
					 project.getProducers().add(prodtemp) ;
				 }					 
			 }			 
		 }		 
		 projectsService.create(project) ;		
		return data2;
	}
	
	@RequestMapping(value = "/updateprojectdata", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> updateprojectdata(Principal principal, @RequestBody Map<String, Object> data, HttpSession session, @ModelAttribute Project project) {		
		
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		
		Project editedproject = (Project) session.getAttribute("editedproject") ;
		session.removeAttribute("editedproject");
		
		String producerscity = ((String) data.get("producerscity")).trim();
		
		if(producerscity.contains("Select city")){
			session.setAttribute("cityNotSelected", true);			
		}else{
			session.setAttribute("cityNotSelected", false);
		}
		
		ArrayList<String> producerslist = (ArrayList<String>) data.get("producerslist");
		
		if(producerslist.size()==0){
			session.setAttribute("producersEmptyList", true);			
		}else{
			session.setAttribute("producersEmptyList", false);
		}
		
		
		String winterairtemp = ((String) data.get("winterairtemp")).trim();
		String summerairtemp = ((String) data.get("summerairtemp")).trim();
		Pattern temperature = Pattern.compile("[+-]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*") ;
        Matcher tempw = temperature.matcher(winterairtemp) ;
        Matcher temps = temperature.matcher(summerairtemp) ;
		
		String winterhumid = ((String) data.get("winterhumid")).trim();
		String summerhumid = ((String) data.get("summerhumid")).trim();
		Pattern humidity = Pattern.compile("[+]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*") ;
		Matcher humw = humidity.matcher(winterhumid) ;
        Matcher hums = humidity.matcher(summerhumid) ;
		
		if(!(tempw.matches()&&temps.matches()&&humw.matches()&&hums.matches())
			|| producerslist.size()==0){        	
        	return null;
        }
		
		projectsService.cleanProducersList(editedproject.getProjects_id()) ;
		session.setAttribute("valid", true);
		User user = new User();
		user.setUsername(principal.getName());	
		project.setUser(user);		
		project.setCreationdate(new Date());
		project.setProjectname(editedproject.getProjectname());
		project.setProjects_id(editedproject.getProjects_id());
		project.setWinterairtemp(winterairtemp);
		project.setSummerairtemp(summerairtemp);
		project.setWinterhumid(winterhumid);
		project.setSummerhumid(summerhumid);
		project.setProducerscity(producerscity);
		project.setProducers(new ArrayList<Producer>());
		
		List<User> cityproducers = usersService.getProducersByCity2(producerscity) ;		 
		 		 
		 for(int i = 0; i < cityproducers.size(); i++){
			 for(int j = 0; j < producerslist.size(); j++){
				 User temp = cityproducers.get(i) ;
				 if(temp.getName().equals(producerslist.get(j))){
					 Producer prodtemp = new Producer(temp.getName(), producerscity);
					 project.getProducers().add(prodtemp) ;
				 }					 
			 }			 
		 }		 
		 projectsService.update(project) ;		
		 return data2;
	}
	
	@RequestMapping(value = "/deleteprojects", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteProjects(@RequestBody Map<String, Object> data){
		List <String> projects_ids = (List) data.get("projects_ids") ;
		
		if (projects_ids.size() > 0){
			projectsService.deleteProjects(projects_ids);
		}
		
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		return data2;
	}
	
	@RequestMapping(value = "/sendprojectid", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendprojectid(Principal principal, @RequestBody Map<String, Object> data,
			HttpSession session) {
		
		int projects_id = -1;
		if (data != null){
			projects_id = Integer.parseInt((String) data.get("projects_id"));
		}
		String type =(String) data.get("type") ;
		session.setAttribute("type", type);
		Project editedproject = projectsService.prooveProjectsAuthority(principal.getName(), projects_id) ;
		session.setAttribute("editedproject", editedproject);
		
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		ready2 = true;
		ready3 = true;
		return data2;
	}
	
	@RequestMapping(value = "/geteditedproject", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> geteditedproject(HttpSession session) {
		while (!ready2) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		Project editedproject = (Project) session.getAttribute("editedproject");
		List <Producer> chosenproducersP = editedproject.getProducers() ;
		List<String> chosenproducers = new ArrayList<String>();
		
		for(int i = 0; i < chosenproducersP.size(); i++){
			chosenproducers.add(chosenproducersP.get(i).getCompanyname());
			System.out.print(chosenproducersP.get(i).getCompanyname()+" ") ;
		}
		
		String producerscity = editedproject.getProducerscity() ;
		List<String> allcityproducers = usersService.getProducersByCity(producerscity);
		List<String> unchosenproducers = new ArrayList<String>() ;
		
		
		for(int i = 0; i < allcityproducers.size(); i++){
			if(!chosenproducers.contains(allcityproducers.get(i))){
				unchosenproducers.add(allcityproducers.get(i)) ;
				System.out.print(allcityproducers.get(i)+" ");
			}
		}			
		
		data.put("projectname", editedproject.getProjectname()) ;
		data.put("winterairtemp", editedproject.getWinterairtemp()) ;
		data.put("summerairtemp", editedproject.getSummerairtemp()) ;
		data.put("winterhumid", editedproject.getWinterhumid()) ;
		data.put("summerhumid", editedproject.getSummerhumid()) ;
		data.put("producerscity", producerscity) ;
		data.put("chosenproducers", chosenproducers) ;
		data.put("unchosenproducers", unchosenproducers) ;
		
		ready2 = false;		
		return data;
	}
	
	@RequestMapping(value = "/geteditedproject2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> geteditedproject2(HttpSession session) {
		while (!ready3) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		Project editedproject = (Project) session.getAttribute("editedproject");	
		session.removeAttribute("editedproject");
		List<Ahu> projectsAhus = editedproject.getAhus() ;
		List<String> premises = new ArrayList<String>() ;
		List<String> ahusupplynames = new ArrayList<String>() ;
		List<String> ahuexhaustnames = new ArrayList<String>() ;
		List<Integer> ahusids = new ArrayList<Integer>() ;
		if(projectsAhus != null){
		for(int i = 0; i < projectsAhus.size(); i++){
			premises.add(projectsAhus.get(i).getPremises()) ;
			ahusids.add(projectsAhus.get(i).getId()) ;
			ahusupplynames.add(projectsAhus.get(i).getSupplysystemname()) ;
			ahuexhaustnames.add(projectsAhus.get(i).getExhaustsystemname()) ;
		}}
		data.put("projectname", editedproject.getProjectname())	;
		data.put("projects_id", editedproject.getProjects_id())	;
		data.put("premises", premises)	;
		data.put("ahusids", ahusids)	;
		data.put("ahusupplynames", ahusupplynames) ;
		data.put("ahuexhaustnames", ahuexhaustnames) ;
		ready3 = false;		
		return data;
	}
}
