package com.company.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exist(String username) {
		return getUser(username) != null;
	}

	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	}

	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username)); // for primary key
		return (User) crit.uniqueResult();
	}

	public int getByNameByCity(String name, String city) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("name", name));
		crit.add(Restrictions.eq("city", city));
		return crit.list().size();
	}

	public List<String> getProducersCities() {
		Query query = session().createSQLQuery(
				"select distinct city from users where authority = 'ROLE_PRODUCER'");
		List<Object> cities = query.list();
		List<String> result = new ArrayList<String>();
		if (cities != null) {
			for (int i = 0; i < cities.size(); i++) {
				result.add((String) cities.get(i));
			}
		}
		cities = null;
		return result;
	}

	public List<String> getProducersByCity(String city) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("city", city));
		crit.add(Restrictions.eq("authority", "ROLE_PRODUCER"));
		crit.setProjection(Projections.property("name"));		
		List<Object> producers = crit.list();		
		
		List<String> result = new ArrayList<String>();
		if (producers != null) {
			for (int i = 0; i < producers.size(); i++) {
				result.add((String) producers.get(i));
			}
		}
		producers = null;
		return result;
	}
	
	public List<User> getProducersByCity2(String city) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("city", city));
		crit.add(Restrictions.eq("authority", "ROLE_PRODUCER"));
		
		crit.setProjection(Projections.projectionList()
			      .add(Projections.property("username"), "username")
			      .add(Projections.property("name"), "name"))
			    .setResultTransformer(Transformers.aliasToBean(User.class));
		
		List<User> producers = crit.list();
		if(producers == null){
			producers = new ArrayList<User>() ; 
		}		
		return producers;
	}

	public User getProducer(String companyname, String city) {
		Session session = session() ;
		Criteria crit = session.createCriteria(User.class) ;
		crit.add(Restrictions.eq("name", companyname)) ;
		crit.add(Restrictions.eq("city", city)) ; 
		return (User) crit.uniqueResult();
	}

	public boolean emailExists(String email) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("email", email));
		return crit.list().size() > 0;
	}

}
