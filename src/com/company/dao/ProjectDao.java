package com.company.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("ProjectDao")
@Transactional
public class ProjectDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void create(Project project) {		
		session().save(project);
	}

	public List<Project> getAllProjects() {          //
		Criteria crit = session().createCriteria(Project.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		List<Project> result = crit.list(); 
		return result;		
	}

	/*public Collection<Project> getUsersProjects(String username) { // created by user
		Criteria crit = session().createCriteria(Project.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));
		crit.addOrder(Order.asc("creationdate")) ;
		Collection <Project> result = new LinkedHashSet<Project>(crit.list()); 
		return result;		
	}*/
	
	public Collection<Project> getUsersProjects(String username) { // created by user
		Session session = session() ;
		Criteria crit = session.createCriteria(Project.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));
		/*crit.addOrder(Order.asc("creationdate")) ;*/
		Collection <Project> result = new LinkedHashSet<Project>(crit.list());
		return result;		
	}
	
	public Project prooveProjectsAuthority(String username, int projects_id) {
		Session session = session() ;
		Criteria crit = session.createCriteria(Project.class);		
		crit.add(Restrictions.eq("projects_id", projects_id));
		crit.createAlias("user", "u").add(Restrictions.eq("u.username", username)) ;		
		List<Project> projects = crit.list();
		session.clear();		
		if(projects == null){
			return null ;
		}
		
		if(projects.size() == 0){
			return null ;
		}
		
		Project project = projects.get(0) ;
		
		if(project.getUser().getUsername().equals(username)){
			return project; 
		}
		return null;		
	}
	
	@Transactional
	public Project prooveProjectsAuthority2(String username, int projects_id) {
		Session session = session() ;
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		List<User> users = crit.list() ;
		User user = users.get(0);
		String producerscity = user.getCity() ; 
		String companyname = user.getName() ;
		session.flush();
		session.clear();
		
		Criteria criteria=session.createCriteria(Project.class);
		criteria.add(Restrictions.eq("projects_id", projects_id));
		criteria.createAlias("producers", "producers");
		criteria.add(Restrictions.eq("producers.companyname", companyname).ignoreCase());
		criteria.add(Restrictions.eq("producers.producerscity", producerscity).ignoreCase());
		List <Project> projects = criteria.list();
		Project result = projects.get(0) ;
		return result ; 
	}

	@Transactional
	public void update(Project project) {		
		List<Producer> producers = project.getProducers() ;
		for(Producer producer:producers){
			producer.setProject(project);
		}		
		session().update(project) ;		
	}

	public void cleanProducersList(int projects_id) {
		Query query = session().createSQLQuery(
				"delete from producers where projects_id =" + projects_id);
		query.executeUpdate() ;		
	}

	public boolean projectnameExists(String projectname) {
		Criteria crit = session().createCriteria(Project.class);		
		crit.add(Restrictions.eq("projectname", projectname));
		Collection<Project> projects = new LinkedHashSet<Project>(crit.list());
		return projects.size() == 1 ;
	}

	@Transactional
	public Collection<Project> getProducersIncomingProjects(String username) {
		Session session = session() ;
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		List<User> users = crit.list() ;
		User user = users.get(0);
		String producerscity = user.getCity() ; 
		String companyname = user.getName() ;
		session.flush();
		session.clear();
		
		Criteria criteria=session.createCriteria(Project.class);
		criteria.createAlias("producers", "producers");
		criteria.add(Restrictions.eq("producers.companyname", companyname).ignoreCase());
		criteria.add(Restrictions.eq("producers.producerscity", producerscity).ignoreCase());
		
		return new LinkedHashSet<Project>(criteria.list());
	}

	@Transactional
	public void deleteProjects(List<String> projects_ids) {
			Session session = session() ;
			for(int i = 0; i < projects_ids.size(); i++){
				Project project = (Project) session.get(Project.class, Integer.parseInt(projects_ids.get(i)));
				session.delete(project);
				session.flush();
				session.clear();
		}	
	}

	public Project getProjectById(int projects_id) {
		Session session = session() ;
		Project project = (Project) session.get(Project.class, projects_id);
		return project;
	}

	@Transactional
	public List<String> getProducersEmails(List<Producer> producers) {
		Session session = session() ;
		List<String> emails = new ArrayList<String>();
		for(int i = 0; i < producers.size(); i++){
			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("name", producers.get(i).getCompanyname())) ;
			crit.add(Restrictions.eq("city", producers.get(i).getProducerscity())) ;
			User tempuser =(User) crit.list().get(0) ;
			emails.add(tempuser.getEmail()) ;
			session.flush();
			session.clear();
		}
		return emails;
	}	
}
