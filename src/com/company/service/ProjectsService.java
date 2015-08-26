package com.company.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dao.Producer;
import com.company.dao.Project;
import com.company.dao.ProjectDao;
import com.company.dao.UsersDao;

@Service("projectsService")
public class ProjectsService {
	
	@Autowired
	private ProjectDao projectDao ;

	public Collection<Project> getUsersProjects(String username) {
		
		return projectDao.getUsersProjects(username);
	}

	public boolean projectnameExists(String projectname) {
		return projectDao.projectnameExists(projectname);
	}
	
	public void create(Project project) {		
		projectDao.create(project);		
	}

	public Project prooveProjectsAuthority(String username, int projects_id) {		
		return projectDao.prooveProjectsAuthority(username, projects_id) ;
	}


	public void update(Project project) {
		projectDao.update(project) ;
		
	}

	public void cleanProducersList(int projects_id) {
		projectDao.cleanProducersList(projects_id) ;		
	}

	public Collection<Project> getProducersIncomingProjects(String username) {		
		return projectDao.getProducersIncomingProjects(username);
	}

	public Project prooveProjectsAuthority2(String username, int projects_id) {		
		return projectDao.prooveProjectsAuthority2(username, projects_id) ;
	}

	public void deleteProjects(List<String> projects_ids) {
		projectDao.deleteProjects(projects_ids) ;		
	}

	public Project getProjectById(int projects_id) {
		return projectDao.getProjectById(projects_id);
	}

	public List<String> getProducersEmails(List<Producer> producers) {
		return projectDao.getProducersEmails(producers);
	}

}
