package com.company.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dao.Ahu;
import com.company.dao.Project;
import com.company.service.AhusService;
import com.company.service.ProjectsService;

@Controller
public class AhuController {
	@Autowired
	private ProjectsService projectsService;
	
	@Autowired
	private AhusService ahusService;
	
	@RequestMapping("/createnewahu")
	public String createnewahu(@RequestParam(value="projects_id") int projects_id, Principal principal, Model model){
		
		String username = principal.getName() ;
		Project project = projectsService.prooveProjectsAuthority(username, projects_id);
		if(project!=null){
			model.addAttribute("projects_id", projects_id) ;
			model.addAttribute("type", "create") ;
		}		
		return "createnewahu" ;
	}
	
	@RequestMapping(value = "/saveahu", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> saveahu(@RequestBody Map<String, Object> data, @ModelAttribute Ahu ahu) {
		Map<String, Object> data2 = new HashMap<String, Object>();
		String projects_id = (String) data.get("projects_id") ;
		
		String premises = ((String)data.get("premises")).trim() ;
		
		String supplysystemname = ((String)data.get("supplysystemname")).trim() ;
		String exhaustsystemname = ((String)data.get("exhaustsystemname")).trim() ;
				
		String supplywinterairflow = ((String)data.get("supplywinterairflow")).trim() ;
		String supplysummerairflow = ((String)data.get("supplysummerairflow")).trim() ;
		String exhaustwinterairflow = ((String)data.get("exhaustwinterairflow")).trim() ;
		String exhaustsummerairflow = ((String)data.get("exhaustsummerairflow")).trim() ;
		
		String supplywinterpressuredrop = ((String)data.get("supplywinterpressuredrop")).trim() ;
		String supplysummerpressuredrop = ((String)data.get("supplywinterairflow")).trim() ;
		String exhaustwinterpressuredrop = ((String)data.get("exhaustwinterpressuredrop")).trim() ;
		String exhaustsummerpressuredrop = ((String)data.get("exhaustsummerpressuredrop")).trim() ;
		String type = (String)data.get("type") ;
		
		if(isAhuDataValid(projects_id, premises, supplysystemname, exhaustsystemname, supplywinterairflow, supplysummerairflow,
				exhaustwinterairflow, exhaustsummerairflow, supplywinterpressuredrop,  supplysummerpressuredrop,
				exhaustwinterpressuredrop, exhaustsummerpressuredrop, type).containsValue(false)){
			return null ;
		}	
		
		String topleftresult = (String) data.get("topleftresult");
		String toprightresult = (String) data.get("toprightresult");
		String bottomleftresult = (String) data.get("bottomleftresult");
		String bottomrightresult = (String) data.get("bottomrightresult");
		String descriptions = (String) data.get("descriptions");
		String summary = (String) data.get("summary");
		
		data2.put("success", true);
		ahu.setPremises(premises);		
		ahu.setSupplysystemname(supplysystemname);		
		ahu.setExhaustsystemname(exhaustsystemname);		
		ahu.setSupplywinterairflow(supplywinterairflow);
		ahu.setSupplysummerairflow(supplysummerairflow);
		ahu.setSupplywinterpressuredrop(supplywinterpressuredrop);
		ahu.setSupplysummerpressuredrop(supplysummerpressuredrop);
		ahu.setExhaustwinterairflow(exhaustwinterairflow);
		ahu.setExhaustsummerairflow(exhaustsummerairflow);
		ahu.setExhaustwinterpressuredrop(exhaustwinterpressuredrop);
		ahu.setExhaustsummerpressuredrop(exhaustsummerpressuredrop);
		ahu.setTopleftresult(topleftresult);
		ahu.setToprightresult(toprightresult);
		ahu.setBottomleftresult(bottomleftresult);
		ahu.setBottomrightresult(bottomrightresult);
		ahu.setDescriptions(descriptions);
		ahu.setSummary(summary);
		
		ahusService.saveAhuToProject(Integer.parseInt(projects_id), ahu) ;		
		return data2 ; 
	}
	
	@RequestMapping(value = "/updateahu", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> updateahu(@RequestBody Map<String, Object> data, @ModelAttribute Ahu ahu) {
		Map<String, Object> data2 = new HashMap<String, Object>();
		String projects_id = (String) data.get("projects_id") ;
		Integer ahusid = Integer.parseInt((String) data.get("ahusid")) ;
		
		String premises = ((String)data.get("premises")).trim() ;
		
		String supplysystemname = ((String)data.get("supplysystemname")).trim() ;
		String exhaustsystemname = ((String)data.get("exhaustsystemname")).trim() ;
				
		String supplywinterairflow = ((String)data.get("supplywinterairflow")).trim() ;
		String supplysummerairflow = ((String)data.get("supplysummerairflow")).trim() ;
		String exhaustwinterairflow = ((String)data.get("exhaustwinterairflow")).trim() ;
		String exhaustsummerairflow = ((String)data.get("exhaustsummerairflow")).trim() ;
		
		String supplywinterpressuredrop = ((String)data.get("supplywinterpressuredrop")).trim() ;
		String supplysummerpressuredrop = ((String)data.get("supplywinterairflow")).trim() ;
		String exhaustwinterpressuredrop = ((String)data.get("exhaustwinterpressuredrop")).trim() ;
		String exhaustsummerpressuredrop = ((String)data.get("exhaustsummerpressuredrop")).trim() ;
		String type = (String)data.get("type") ;
		
		if(isAhuDataValid(projects_id, premises, supplysystemname, exhaustsystemname, supplywinterairflow, supplysummerairflow,
				exhaustwinterairflow, exhaustsummerairflow, supplywinterpressuredrop,  supplysummerpressuredrop,
				exhaustwinterpressuredrop, exhaustsummerpressuredrop, type).containsValue(false)){
			return null ;
		}	
		
		String topleftresult = (String) data.get("topleftresult");
		String toprightresult = (String) data.get("toprightresult");
		String bottomleftresult = (String) data.get("bottomleftresult");
		String bottomrightresult = (String) data.get("bottomrightresult");
		String descriptions = (String) data.get("descriptions");
		
		data2.put("success", true);
		ahu.setPremises(premises);		
		ahu.setSupplysystemname(supplysystemname);		
		ahu.setExhaustsystemname(exhaustsystemname);		
		ahu.setSupplywinterairflow(supplywinterairflow);
		ahu.setSupplysummerairflow(supplysummerairflow);
		ahu.setSupplywinterpressuredrop(supplywinterpressuredrop);
		ahu.setSupplysummerpressuredrop(supplysummerpressuredrop);
		ahu.setExhaustwinterairflow(exhaustwinterairflow);
		ahu.setExhaustsummerairflow(exhaustsummerairflow);
		ahu.setExhaustwinterpressuredrop(exhaustwinterpressuredrop);
		ahu.setExhaustsummerpressuredrop(exhaustsummerpressuredrop);
		ahu.setTopleftresult(topleftresult);
		ahu.setToprightresult(toprightresult);
		ahu.setBottomleftresult(bottomleftresult);
		ahu.setBottomrightresult(bottomrightresult);
		ahu.setDescriptions(descriptions);
		
		ahusService.updateAhu(Integer.parseInt(projects_id), ahu, ahusid) ;		
		return data2 ; 
	}
	
	@RequestMapping("/saveasahu")
	public String saveasahu(@RequestParam(value="projects_id") int projects_id, @RequestParam(value="ahusid") int ahusid, Principal principal,
			Model model){
		String username = principal.getName() ;
		Project project = projectsService.prooveProjectsAuthority(username, projects_id);
		if (project != null){
			List<Ahu> ahus = project.getAhus() ;
			Ahu ahu = null;
			for(Ahu temp : ahus){
				if(temp.getId() == ahusid){
					ahu = temp ; 
					break ;
				}
			}
			model.addAttribute("projects_id", projects_id) ;
			model.addAttribute("ahu", ahu) ;
			model.addAttribute("type", "saveas") ;
			return "saveasahu" ;
		}
		return "cannoteditahu" ;
	}
	
	@RequestMapping("/showeditahu")
	public String showeditahu(@RequestParam(value="projects_id") int projects_id, @RequestParam(value="ahusid") int ahusid, Principal principal,
			Model model){
		String username = principal.getName() ;
		Project project = projectsService.prooveProjectsAuthority(username, projects_id);
		if (project != null){
			List<Ahu> ahus = project.getAhus() ;
			Ahu ahu = null;
			for(Ahu temp : ahus){
				if(temp.getId() == ahusid){
					ahu = temp ; 
				}
			}
			model.addAttribute("projects_id", projects_id) ;
			model.addAttribute("ahu", ahu) ;
			model.addAttribute("type", "edit") ;
			return "showeditahu" ;
		}
		return "cannoteditahu" ;
	}
	
	
	@RequestMapping(value = "/deleteahus", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteProjects(@RequestBody Map<String, Object> data){
		List <String> ahus_ids = (List) data.get("ahus_ids") ;
		
		if (ahus_ids.size() > 0){
			ahusService.deleteAhus(ahus_ids);
		}
		
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		return data2;
	}
	
	public Map<String, Boolean> isAhuDataValid (String projects_id, String premises, String supplysystemname, 
			String exhaustsystemname, String supplywinterairflow, String supplysummerairflow,
			String exhaustwinterairflow, String exhaustsummerairflow,
			String supplywinterpressuredrop, String supplysummerpressuredrop,
			String exhaustwinterpressuredrop, String exhaustsummerpressuredrop, String type){
		
		Map<String, Boolean> ahusvalidationmap = new HashMap<String, Boolean>() ;
		
		Pattern premisespat = Pattern.compile("[а-яА-ЯёЁa-zA-Z0-9\"\'.,:;/_\\s-]{3,100}") ; 
		Matcher premisesm = premisespat.matcher(premises) ;
		
		Pattern sysnampat = Pattern.compile("[а-яА-ЯёЁa-zA-Z0-9\"\'.,:;/_\\s-]{0,10}") ;
		Matcher supsysnamem = sysnampat.matcher(supplysystemname) ;
		Matcher exsysnamem = sysnampat.matcher(exhaustsystemname) ;
		
		Pattern airflowpat = Pattern.compile("[+]?[0-9-]{2,6}([.,]{1}[0-9]{0,2})?") ;
		Matcher supplywinterairflowm =  airflowpat.matcher(supplywinterairflow) ;
		Matcher supplysummerairflowm =  airflowpat.matcher(supplysummerairflow) ;
		Matcher exhaustwinterairflowm =  airflowpat.matcher(exhaustwinterairflow) ;
		Matcher exhaustsummerairflowm =  airflowpat.matcher(exhaustsummerairflow) ;
		
		Pattern prdroppat = Pattern.compile("[+]?[0-9-]{2,4}([.,]{1}[0-9]{0,2})?") ;
		Matcher supplywinterpressuredropm =  prdroppat.matcher(supplywinterpressuredrop) ;
		Matcher supplysummerpressuredropm =  prdroppat.matcher(supplysummerpressuredrop) ;
		Matcher exhaustwinterpressuredropm =  prdroppat.matcher(exhaustwinterpressuredrop) ;
		Matcher exhaustsummerpressuredropm =  prdroppat.matcher(exhaustsummerpressuredrop) ;
		
		ahusvalidationmap.put("premises", premisesm.matches()) ;
		ahusvalidationmap.put("supplysystemname", supsysnamem.matches()) ;
		ahusvalidationmap.put("exhaustsystemname", exsysnamem.matches()) ;
		ahusvalidationmap.put("supplywinterairflow", supplywinterairflowm.matches()) ;
		ahusvalidationmap.put("supplysummerairflow", supplysummerairflowm.matches()) ;
		ahusvalidationmap.put("exhaustwinterairflow", exhaustwinterairflowm.matches()) ;
		ahusvalidationmap.put("exhaustsummerairflow", exhaustsummerairflowm.matches()) ;
		ahusvalidationmap.put("supplywinterpressuredrop", supplywinterpressuredropm.matches()) ;
		ahusvalidationmap.put("supplysummerpressuredrop", supplysummerpressuredropm.matches()) ;
		ahusvalidationmap.put("exhaustwinterpressuredrop", exhaustwinterpressuredropm.matches()) ;
		ahusvalidationmap.put("exhaustsummerpressuredrop", exhaustsummerpressuredropm.matches()) ;
		
		if(supplysystemname.length()== 0 && exhaustsystemname.length()==0){
			ahusvalidationmap.put("bothnotemptynames", false) ;
		}else{
			ahusvalidationmap.put("bothnotemptynames", true) ;
		}
		
		if(supplysystemname.length()>0 && (!"edit".equals(type))){
			ahusvalidationmap.put("supplyunique", (ahusService.isUniqueSupplySystem(Integer.parseInt(projects_id), supplysystemname))) ;	
		}else{
			ahusvalidationmap.put("supplyunique", true) ;
		}
		
		
		if(exhaustsystemname.length()>0 && (!"edit".equals(type))){
			ahusvalidationmap.put("exhaustunique", (ahusService.isUniqueExhaustSystem(Integer.parseInt(projects_id), exhaustsystemname))) ;	
		}else{
			ahusvalidationmap.put("exhaustunique", true) ;
		}			
		return ahusvalidationmap ;
	}
	
	@RequestMapping(value = "/sendahusfields", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendahusfields(@RequestBody Map<String, Object> data,
			HttpSession session) {
		Map<String, String> ahusfields = new HashMap<String, String>() ;		
		if (data != null){
			ahusfields.put("premises", (String) data.get("premises")) ;
			ahusfields.put("supplysystemname", ((String) data.get("supplysystemname")).trim()) ;
			ahusfields.put("exhaustsystemname", ((String) data.get("exhaustsystemname")).trim()) ;
			ahusfields.put("supplywinterairflow", ((String) data.get("supplywinterairflow")).trim()) ;
			ahusfields.put("supplysummerairflow", ((String) data.get("supplysummerairflow")).trim()) ;
			ahusfields.put("exhaustwinterairflow", ((String) data.get("exhaustwinterairflow")).trim()) ;
			ahusfields.put("exhaustsummerairflow", ((String) data.get("exhaustsummerairflow")).trim()) ;
			ahusfields.put("supplywinterpressuredrop", ((String) data.get("supplywinterpressuredrop")).trim()) ;
			ahusfields.put("supplysummerpressuredrop", ((String) data.get("supplysummerpressuredrop")).trim()) ;
			ahusfields.put("exhaustwinterpressuredrop", ((String) data.get("exhaustwinterpressuredrop")).trim()) ;
			ahusfields.put("exhaustsummerpressuredrop", ((String) data.get("exhaustsummerpressuredrop")).trim()) ;
			ahusfields.put("type", (String) data.get("type")) ;
		}
		String projects_id = (String) data.get("projects_id") ;
		session.setAttribute("projects_id", projects_id);
		session.setAttribute("ahusfields", ahusfields);
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		return data2;
	}
	
	@RequestMapping(value = "/getahuerrors", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getahuerrors(HttpSession session) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> ahusfields = (Map<String, String>) session.getAttribute("ahusfields") ;
		session.removeAttribute("ahusfields");
		String projects_id = (String) session.getAttribute("projects_id") ;				
		session.removeAttribute("projects_id");
		Map<String, Boolean> errors = isAhuDataValid(projects_id, ahusfields.get("premises"), ahusfields.get("supplysystemname"), ahusfields.get("exhaustsystemname"), 
				ahusfields.get("supplywinterairflow"), ahusfields.get("supplysummerairflow"),
				ahusfields.get("exhaustwinterairflow"), ahusfields.get("exhaustsummerairflow"), 
				ahusfields.get("supplywinterpressuredrop"),  ahusfields.get("supplysummerpressuredrop"),
				ahusfields.get("exhaustwinterpressuredrop"), ahusfields.get("exhaustsummerpressuredrop"), ahusfields.get("type")) ;
		
		String systemnameerror = "System name should contain from 1 to 10 latin, cyrilic or digit characters." ;
		String airflowerror = "Air flow should be integer or real number; 2-6 digits before and 0-2 digits after comma." ;
		String prdroperror = "Pressure drop should be integer or real number; 2-4 digits before and 0-2 digits after comma." ;
		String emptynames = "Both system names cannot be empty" ;
		String supplyduplicate = "Supply system with this name already exist in this project." ;
		String exhaustduplicate = "Exhaust system with this name already exist in this project." ;
		
		for(Map.Entry<String, Boolean> pair: errors.entrySet()){
			if(pair.getValue().equals(false) && pair.getKey().equals("premises")){
				data.put("premises", "Premises should contain from 3 to 100 latin, cyrilic or digit characters.") ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("supplysystemname")){
				data.put("supplysystemname", systemnameerror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("exhaustsystemname")){
				data.put("exhaustsystemname", systemnameerror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("supplyunique")){
				data.put("supplyunique", supplyduplicate) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("exhaustunique")){
				data.put("exhaustunique", exhaustduplicate) ;
				continue ;
			}		
			if(pair.getValue().equals(false) && pair.getKey().equals("bothnotemptynames")){
				data.put("bothnotemptynames", emptynames) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("supplywinterairflow")){
				data.put("supplywinterairflow", airflowerror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("supplysummerairflow")){
				data.put("supplysummerairflow", airflowerror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("exhaustwinterairflow")){
				data.put("exhaustwinterairflow", airflowerror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("exhaustsummerairflow")){
				data.put("exhaustsummerairflow", airflowerror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("supplywinterpressuredrop")){
				data.put("supplywinterpressuredrop", prdroperror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("supplysummerpressuredrop")){
				data.put("supplysummerpressuredrop", prdroperror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("exhaustwinterpressuredrop")){
				data.put("exhaustwinterpressuredrop", prdroperror) ;
				continue ;
			}
			if(pair.getValue().equals(false) && pair.getKey().equals("exhaustsummerpressuredrop")){
				data.put("exhaustsummerpressuredrop", prdroperror) ;
			}
		}
		return data;
	}
	
}
