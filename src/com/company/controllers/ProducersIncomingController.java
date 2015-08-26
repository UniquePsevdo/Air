package com.company.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dao.Ahu;
import com.company.dao.Project;
import com.company.service.ProjectsService;

@Controller
public class ProducersIncomingController {
	boolean ready2 ;
	boolean ready3 ;
	
	@Autowired
	private ProjectsService projectsService;
	
	@RequestMapping(value="/incoming", method = RequestMethod.GET)
	public String showIncoming (Principal principal, Model model) {
		
		String username = principal.getName();
		Collection<Project> producersIncomingProjects = projectsService.getProducersIncomingProjects(username);
		model.addAttribute("producersIncomingProjects", producersIncomingProjects) ;
				
		return "incoming" ;
	}
	
	@RequestMapping(value = "/sendprojectid2", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendprojectid2(Principal principal, @RequestBody Map<String, Object> data,
			HttpSession session) {		
		int projects_id = -1;
		if (data != null){
			projects_id = Integer.parseInt((String) data.get("projects_id"));
		}
		String type =(String) data.get("type") ;
		session.setAttribute("type", type);
		Project editedproject2 = projectsService.prooveProjectsAuthority2(principal.getName(), projects_id) ;
		session.setAttribute("editedproject2", editedproject2);		
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("success", true);
		ready2 = true;
		ready3 = true;
		return data2;
	}
	
	@RequestMapping(value = "/geteditedproject3", method = RequestMethod.GET, produces = "application/json")
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
		Project editedproject2 = (Project) session.getAttribute("editedproject2");
		session.removeAttribute("editedproject2");		
		
		data.put("winterairtemp", editedproject2.getWinterairtemp()) ;
		data.put("summerairtemp", editedproject2.getSummerairtemp()) ;
		data.put("winterhumid", editedproject2.getWinterhumid()) ;
		data.put("summerhumid", editedproject2.getSummerhumid()) ;
		
		ready2 = false;		
		return data;
	}
	
	@RequestMapping(value = "/geteditedproject4", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> geteditedproject4(HttpSession session) {
		while (!ready3) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		Project editedproject = (Project) session.getAttribute("editedproject2");
		session.removeAttribute("editedproject2");
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
	
	@RequestMapping("/showahu")
	public String showahu(@RequestParam(value="projects_id") int projects_id, @RequestParam(value="ahusid") int ahusid, Principal principal,
			Model model){
		String username = principal.getName() ;
		Project project = projectsService.prooveProjectsAuthority2(username, projects_id);
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
			model.addAttribute("type", "edit") ;
			model.addAttribute("username", username) ;
			return "showahu" ;
		}
		return "cannotshowahu" ;
	}

}
