package com.company.supportive;

import java.util.List;

import com.company.dao.Ahuselection;

public class SelectionProducer {
	private String selectionpremises;
	private String selectionsupname;
	private String selectionexname;
	private String producerscompanyname;
	private String producerscity ;
	private String producersusername ;
	private String uniquekey ;
	private int ahusid ;
	private List<Ahuselection> versions ;
	
	public SelectionProducer() {}
	
	public SelectionProducer(String selectionpremises, String selectionsupname,
			String selectionexname, String producerscompanyname,
			String producerscity, String producersusername,
			List<Ahuselection> versions) {
		this.selectionpremises = selectionpremises;
		this.selectionsupname = selectionsupname;
		this.selectionexname = selectionexname;
		this.producerscompanyname = producerscompanyname;
		this.producerscity = producerscity;
		this.producersusername = producersusername;
		this.versions = versions;
	}

	public String getSelectionpremises() {
		return selectionpremises;
	}
	public void setSelectionpremises(String selectionpremises) {
		this.selectionpremises = selectionpremises;
	}
	public String getSelectionsupname() {
		return selectionsupname;
	}
	public void setSelectionsupname(String selectionsupname) {
		this.selectionsupname = selectionsupname;
	}
	public String getSelectionexname() {
		return selectionexname;
	}
	public void setSelectionexname(String selectionexname) {
		this.selectionexname = selectionexname;
	}
	public String getProducerscompanyname() {
		return producerscompanyname;
	}
	public void setProducerscompanyname(String producerscompanyname) {
		this.producerscompanyname = producerscompanyname;
	}
	public String getProducerscity() {
		return producerscity;
	}
	public void setProducerscity(String producerscity) {
		this.producerscity = producerscity;
	}
	public String getProducersusername() {
		return producersusername;
	}
	public void setProducersusername(String producersusername) {
		this.producersusername = producersusername;
	}
	public List<Ahuselection> getVersions() {
		return versions;
	}
	public void setVersions(List<Ahuselection> versions) {
		this.versions = versions;
	}

	public String getUniquekey() {
		return uniquekey;
	}

	public void setUniquekey(String uniquekey) {
		this.uniquekey = uniquekey;
	}

	public int getAhusid() {
		return ahusid;
	}

	public void setAhusid(int ahusid) {
		this.ahusid = ahusid;
	}

}
