package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.dao.Ahuselection;
import com.company.dao.SelectionDao;

@Service("selectionsService")
public class SelectionsService {
	
	@Autowired
	private SelectionDao selectionDao;

	public Ahuselection getPdf(int fileid) {
		
		return selectionDao.getPdf(fileid);
	}
	
}
