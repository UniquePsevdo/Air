package com.company.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("SelectionDao")
@Transactional
public class SelectionDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public Ahuselection getPdf(int fileid) {
		Session session = session();
		Ahuselection ahuselection =  (Ahuselection) session.get(Ahuselection.class, fileid);
		return ahuselection;
	}

}
