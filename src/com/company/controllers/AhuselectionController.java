package com.company.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.company.dao.Ahu;
import com.company.dao.Ahuselection;
import com.company.dao.Producer;
import com.company.dao.Project;
import com.company.dao.User;
import com.company.service.AhusService;
import com.company.service.ProjectsService;
import com.company.service.SelectionsService;
import com.company.service.UsersService;
import com.company.supportive.SelectionProducer;

@Controller
public class AhuselectionController {

	@Autowired
	private AhusService ahusService;

	@Autowired
	private SelectionsService selectionsService;

	@Autowired
	private ProjectsService projectsService;
	
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "ahusid") int ahusid, @RequestParam(value = "projects_id") int projects_id,
			@RequestParam("pdf") MultipartFile pdf, Model model, Principal principal) {		
		String producersusername = (String) principal.getName();
		User producer = usersService.getUser(producersusername) ;

		try {
			if (pdf.getOriginalFilename().length() > 100) {
				return "longfilename";
			}

			if (pdf.isEmpty()) {
				return "notuploaded";
			}

			Ahuselection ahuselection = new Ahuselection(pdf.getOriginalFilename(), producersusername, ahusid,
					pdf.getBytes(), new Date());
			
			ahuselection.setProducerscity(producer.getCity());
			ahuselection.setCompanyname(producer.getName());
			
			ahusService.addAhuselection(ahuselection, ahusid);
			Project editedproject = projectsService.prooveProjectsAuthority2(
					principal.getName(), projects_id);
			List<Ahu> ahus = editedproject.getAhus();
			String projectname = editedproject.getProjectname() ;
			Map<String, Boolean> isSelectionsEmpty = new HashMap<String, Boolean>();
			Map<String, List<Ahuselection>> ahusselections = new HashMap<String, List<Ahuselection>>();
			
			for (int i = 0; i < ahus.size(); i++) {
				ahusselections.put(Integer.toString(ahus.get(i).getId()), ahus.get(i).getAhuselectionsByProducer(principal.getName()));
				isSelectionsEmpty.put(Integer.toString(ahus.get(i).getId()),
						ahus.get(i).getAhuselectionsByProducer(principal.getName()).size() == 0);
			}
			
			model.addAttribute("projectname", projectname);
			model.addAttribute("ahus", ahus);
			model.addAttribute("ahusselections", ahusselections);
			model.addAttribute("isSelectionsEmpty", isSelectionsEmpty);
			return "uploadedahus";

		} 
		catch (IOException e) {
			return "smthnghappened";
		}
		catch(org.hibernate.exception.DataException e){
			return "heavyfile"; 
		}

	}

	@RequestMapping(value = "/uploadedahus", method = RequestMethod.GET)
	public String uploadedahus(
			@RequestParam(value = "projects_id") int projects_id, Model model, Principal principal) {
		System.out.println("hi");
		Project editedproject = projectsService.prooveProjectsAuthority2(
				principal.getName(), projects_id);
		List<Ahu> ahus = editedproject.getAhus();
		String projectname = editedproject.getProjectname() ;
		Map<String, Boolean> isSelectionsEmpty = new HashMap<String, Boolean>();
		Map<String, List<Ahuselection>> ahusselections = new HashMap<String, List<Ahuselection>>();
		for (int i = 0; i < ahus.size(); i++) {
			ahusselections.put(Integer.toString(ahus.get(i).getId()),
					ahus.get(i).getAhuselectionsByProducer(principal.getName()));
			isSelectionsEmpty.put(Integer.toString(ahus.get(i).getId()), ahus
					.get(i).getAhuselectionsByProducer(principal.getName()).size() == 0);
		}
		model.addAttribute("projectname", projectname);
		model.addAttribute("ahus", ahus);
		model.addAttribute("ahusselections", ahusselections);
		model.addAttribute("isSelectionsEmpty", isSelectionsEmpty);
		return "uploadedahus";
	}

	@RequestMapping(value="/pdf/{file_id}", produces="application/pdf")
	public ResponseEntity<byte[]> getFile(HttpServletResponse resp, @PathVariable("file_id") int fileid) {
		
		Ahuselection ahuselection = selectionsService.getPdf(fileid);
		String filename = ahuselection.getFilename() ;
		byte [] pdfContents = ahuselection.getPdf() ;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("content-disposition", "inline; filename=" + filename) ;
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0") ;
		
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	
	@RequestMapping(value = "/received", method = RequestMethod.GET)
	public String recieved(@RequestParam(value = "projects_id") int projects_id, Model model, Principal principal) {
		Project editedproject = projectsService.prooveProjectsAuthority(principal.getName(), projects_id);
		List<Ahu> ahus = editedproject.getAhus();
		List<Producer> producers = editedproject.getProducers() ;
		List<SelectionProducer> selectionsProducers = new ArrayList<SelectionProducer>() ;
		
		for(int i = 0; i< ahus.size(); i++){
			for(int j = 0; j < producers.size(); j++){
				List<Ahuselection> ahuselections = ahus.get(i).getAhuselections() ;
				SelectionProducer temp = new SelectionProducer() ;
				temp.setSelectionpremises(ahus.get(i).getPremises());
				temp.setSelectionsupname(ahus.get(i).getSupplysystemname());
				temp.setSelectionexname(ahus.get(i).getExhaustsystemname());
				temp.setAhusid(ahus.get(i).getId());
				temp.setProducerscompanyname(producers.get(j).getCompanyname());				
				temp.setProducerscity(producers.get(j).getProducerscity());
				temp.setUniquekey(ahus.get(i).getPremises()+producers.get(j).getCompanyname()+producers.get(j).getProducerscity()+i+j);
				List<Ahuselection> versions = new ArrayList<Ahuselection>() ;
				temp.setVersions(versions);
				for(int x = 0; x < ahuselections.size(); x++){
					if(ahuselections.get(x).getProducerscity().equals(producers.get(j).getProducerscity()) && 
					   ahuselections.get(x).getCompanyname().equals(producers.get(j).getCompanyname())){
						versions.add(ahuselections.get(x)) ;
					}
				}
				selectionsProducers.add(temp) ;
			}
		}
		Map<String, List<Ahuselection>> versionsmap = new LinkedHashMap<String, List<Ahuselection>>();
		Map<String, Boolean> isVersionsEmpty = new LinkedHashMap<String, Boolean>();
		
		for (int i = 0; i < selectionsProducers.size(); i++) {
			versionsmap.put(selectionsProducers.get(i).getUniquekey(), selectionsProducers.get(i).getVersions());
			isVersionsEmpty.put(selectionsProducers.get(i).getUniquekey(), selectionsProducers.get(i).getVersions().size() == 0);
		}
		
		model.addAttribute("projectname", editedproject.getProjectname());
		model.addAttribute("selectionsProducers", selectionsProducers);
		model.addAttribute("versionsmap", versionsmap);
		model.addAttribute("isVersionsEmpty", isVersionsEmpty);		
		return "receivedselections";
	}
}
