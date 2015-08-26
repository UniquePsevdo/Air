package com.company.dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="producers")
public class Producer implements Serializable{	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id") 
	private int id;
	

	@Column(name="companyname")
	private String companyname ;
	
	@Column(name="producerscity")
	private String producerscity ;
	
	@ManyToOne
    @JoinColumn(name="projects_id", insertable=false, nullable=false, updatable=false) 
	Project project;
	
	public Producer() {}

	public Producer(String companyname, String producerscity) {		
		this.companyname = companyname ;
		this.producerscity = producerscity ;		
	}


	public int getId() {
		return id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getProducerscity() {
		return producerscity;
	}

	public void setProducerscity(String producerscity) {
		this.producerscity = producerscity;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Producer [companyname=" + companyname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyname == null) ? 0 : companyname.hashCode());
		result = prime * result
				+ ((producerscity == null) ? 0 : producerscity.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producer other = (Producer) obj;
		if (companyname == null) {
			if (other.companyname != null)
				return false;
		} else if (!companyname.equals(other.companyname))
			return false;
		if (producerscity == null) {
			if (other.producerscity != null)
				return false;
		} else if (!producerscity.equals(other.producerscity))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}

	
}
