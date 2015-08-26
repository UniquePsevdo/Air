package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;







import com.company.dao.User;
import com.company.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	@Autowired
	private UsersDao usersDao ;

	public boolean exists(String username) {
		
		return usersDao.exist(username);
	}
	
	public void create(User user) {
		usersDao.create(user) ;		
	}

	@Secured("ROLE_ADMIN") // alternative way of security access
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public User getUser(String username){
		return usersDao.getUser(username) ;
	}

	public boolean companyExists(String name, String city) {
		return usersDao.getByNameByCity(name, city) == 1 ;
	}

	public List<String> getProducersCities() {		
		return usersDao.getProducersCities();
	}

	public List<String> getProducersByCity(String city) {
		return usersDao.getProducersByCity(city);
	}
	
	public List<User> getProducersByCity2(String city) {
		return usersDao.getProducersByCity2(city);
	}

	public User getProducer(String companyname, String city) {
		return usersDao.getProducer(companyname, city);
	}

	public boolean emailExists(String email) {
		return usersDao.emailExists(email);
	}

}
