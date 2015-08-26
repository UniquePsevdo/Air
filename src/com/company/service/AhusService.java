package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.dao.Ahu;
import com.company.dao.AhuDao;
import com.company.dao.Ahuselection;

@Service("ahusService")
public class AhusService {
	
	@Autowired
	private AhuDao ahuDao;

	public void saveAhuToProject(int projects_id, Ahu ahu) {
		ahuDao.saveAhuToProject(projects_id, ahu) ;
	}
	
	public Boolean isUniqueSupplySystem(int projects_id, String supplysystemname) {		
		return ahuDao.isUniqueSupplySystem(projects_id, supplysystemname);
	}
	
	public Boolean isUniqueExhaustSystem(int projects_id, String exhaustsystemname) {		
		return ahuDao.isUniqueExhaustSystem(projects_id, exhaustsystemname);
	}

	public void updateAhu(int projects_id, Ahu ahu, int ahusid) {
		ahuDao.updateAhu(projects_id, ahu, ahusid) ;		
	}

	public void addAhuselection(Ahuselection ahuselection, int ahusid) {
		ahuDao.addAhuselection(ahuselection, ahusid) ;
		
	}

	public void deleteAhus(List<String> ahus_ids) {
		ahuDao.deleteAhus(ahus_ids) ;		
	}

	public Ahu getAhuById(int ahusid) {
		return ahuDao.getAhuById(ahusid);
	}

}
