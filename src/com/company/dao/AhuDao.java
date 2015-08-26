package com.company.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("AhuDao")
@Transactional
public class AhuDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void saveAhuToProject(int projects_id, Ahu ahu) {
		Session session = session();
		
		Project project = (Project) session.get(Project.class, projects_id);
		ahu.setProject(project);
		List<Ahu> ahus = project.getAhus();
		ahus.add(ahu);
		project.setAhus(ahus);
		session.update(project);
	}
	
	@Transactional
	public void updateAhu(int projects_id, Ahu ahu, int ahusid) {
		Session session = session();
		Project project = (Project) session.get(Project.class, projects_id);
		ahu.setProject(project);
		List<Ahu> ahus = project.getAhus();
		int index = 0;
		for (int i = 0; i < ahus.size(); i++) {
			if (ahus.get(i).getId() == ahusid) {
				index = i;
				break;
			}
		}
		ahus.remove(index);				
		project.setAhus(ahus);
		session.update(project);
		session.flush();
		session.clear();
		ahus.add(ahu) ;
		project.setAhus(ahus);
		session.update(project);
	}

	public Boolean isUniqueSupplySystem(int projects_id, String supplysystemname) {
		Criteria crit = session().createCriteria(Ahu.class);
		crit.add(Restrictions.eq("supplysystemname", supplysystemname));
		crit.createAlias("project", "p").add(Restrictions.eq("p.projects_id", projects_id));
		List<Ahu> result = crit.list(); 
		return result.size() == 0;
	}

	public Boolean isUniqueExhaustSystem(int projects_id, String exhaustsystemname) {
		Criteria crit = session().createCriteria(Ahu.class);
		crit.add(Restrictions.eq("exhaustsystemname", exhaustsystemname));
		crit.createAlias("project", "p").add(Restrictions.eq("p.projects_id", projects_id));
		List<Ahu> result = crit.list(); 
		return result.size() == 0;
	}

	@Transactional
	public void addAhuselection(Ahuselection ahuselection, int ahusid) {
		Session session = session();
		
		Ahu ahu = (Ahu) session.get(Ahu.class, ahusid);
		session.flush();
		session.clear();
		
		Project project = ahu.getProject() ;
		
		List<Ahu> ahus = project.getAhus();
		int index = 0;
		for (int i = 0; i < ahus.size(); i++) {
			if (ahus.get(i).getId() == ahu.getId()) {
				index = i;
				break;
			}
		}
		List<Ahuselection> ahuselections = ahu.getAhuselections() ;
		ahuselection.setAhu(ahu);
		ahuselections.add(ahuselection) ;
		ahu.setAhuselections(ahuselections);
		ahus.set(index, ahu);				
		project.setAhus(ahus);
		session.update(project);
	}

	@Transactional
	public void deleteAhus(List<String> ahus_ids) {
				Session session = session() ;
				int projects_id = Integer.parseInt(ahus_ids.get(0).split("spl")[0]) ;
				Project project = (Project) session.get(Project.class, projects_id);
				
				List<Ahu> ahus = project.getAhus();
				for (int i = 0; i < ahus_ids.size(); i++) {
					Iterator itr = ahus.iterator() ;
					while(itr.hasNext()){
						Ahu ahu =(Ahu) itr.next() ;
						int ahus_id = Integer.parseInt(ahus_ids.get(i).split("spl")[1]) ;
						if(ahus_id == ahu.getId()){
							itr.remove();
						}
					}
				}
				project.setAhus(ahus);
				session.update(project);
		}

	public Ahu getAhuById(int ahusid) {
		Session session = session() ;
		Ahu ahu = (Ahu) session.get(Ahu.class, ahusid) ;
		return ahu;
	}
}
