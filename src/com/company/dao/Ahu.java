package com.company.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="ahus")
public class Ahu implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id") 
	private int id;
	
	@ManyToOne
    @JoinColumn(name="projects_id", insertable=false, updatable=false, nullable=false)
	Project project;
	
	@Column(name="premises")
	private String premises;
	
	private String supplysystemname ;
	private String exhaustsystemname ;
	private String supplywinterairflow ;
	private String supplysummerairflow ;
	private String supplywinterpressuredrop ;
	private String supplysummerpressuredrop ;
	private String exhaustwinterairflow ;
	private String exhaustsummerairflow ;
	private String exhaustwinterpressuredrop ;
	private String exhaustsummerpressuredrop ;
	private String topleftresult;
	private String toprightresult;
	private String bottomleftresult;
	private String bottomrightresult;
	private String descriptions;
	private String summary;
	
	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name="ahus_id")
	@IndexColumn(name="idx")
	List<Ahuselection> ahuselections;
	
	public List<Ahuselection> getAhuselections() {
		return ahuselections;
	}

	
	public void setAhuselections(List<Ahuselection> ahuselections) {
		this.ahuselections = ahuselections;
	}

	public Ahu(){}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getPremises() {
		return premises;
	}

	public void setPremises(String premises) {
		this.premises = premises;
	}

	public int getId() {
		return id;
	}
	
	@Transient
	public void setId(int id) {
		this.id = id;
	}


	public String getSupplysystemname() {
		return supplysystemname;
	}

	public void setSupplysystemname(String supplysystemname) {
		this.supplysystemname = supplysystemname;
	}

	public String getExhaustsystemname() {
		return exhaustsystemname;
	}

	public void setExhaustsystemname(String exhaustsystemname) {
		this.exhaustsystemname = exhaustsystemname;
	}

	public String getSupplywinterairflow() {
		return supplywinterairflow;
	}

	public void setSupplywinterairflow(String supplywinterairflow) {
		this.supplywinterairflow = supplywinterairflow;
	}

	public String getSupplysummerairflow() {
		return supplysummerairflow;
	}

	public void setSupplysummerairflow(String supplysummerairflow) {
		this.supplysummerairflow = supplysummerairflow;
	}

	public String getSupplywinterpressuredrop() {
		return supplywinterpressuredrop;
	}

	public void setSupplywinterpressuredrop(String supplywinterpressuredrop) {
		this.supplywinterpressuredrop = supplywinterpressuredrop;
	}

	public String getSupplysummerpressuredrop() {
		return supplysummerpressuredrop;
	}

	public void setSupplysummerpressuredrop(String supplysummerpressuredrop) {
		this.supplysummerpressuredrop = supplysummerpressuredrop;
	}

	public String getExhaustwinterairflow() {
		return exhaustwinterairflow;
	}

	public void setExhaustwinterairflow(String exhaustwinterairflow) {
		this.exhaustwinterairflow = exhaustwinterairflow;
	}

	public String getExhaustsummerairflow() {
		return exhaustsummerairflow;
	}

	public void setExhaustsummerairflow(String exhaustsummerairflow) {
		this.exhaustsummerairflow = exhaustsummerairflow;
	}

	public String getExhaustwinterpressuredrop() {
		return exhaustwinterpressuredrop;
	}

	public void setExhaustwinterpressuredrop(String exhaustwinterpressuredrop) {
		this.exhaustwinterpressuredrop = exhaustwinterpressuredrop;
	}

	public String getExhaustsummerpressuredrop() {
		return exhaustsummerpressuredrop;
	}

	public void setExhaustsummerpressuredrop(String exhaustsummerpressuredrop) {
		this.exhaustsummerpressuredrop = exhaustsummerpressuredrop;
	}

	public String getTopleftresult() {
		return topleftresult;
	}

	public void setTopleftresult(String topleftresult) {
		this.topleftresult = topleftresult;
	}

	public String getToprightresult() {
		return toprightresult;
	}

	public void setToprightresult(String toprightresult) {
		this.toprightresult = toprightresult;
	}

	public String getBottomleftresult() {
		return bottomleftresult;
	}

	public void setBottomleftresult(String bottomleftresult) {
		this.bottomleftresult = bottomleftresult;
	}

	public String getBottomrightresult() {
		return bottomrightresult;
	}

	public void setBottomrightresult(String bottomrightresult) {
		this.bottomrightresult = bottomrightresult;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	
	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Transient
	public List<Ahuselection> getAhuselectionsByProducer(String producersusername){
		List<Ahuselection> result = getAhuselections() ;
		Iterator itr = result.iterator() ;
		while(itr.hasNext()){
			Ahuselection ahuselection =(Ahuselection) itr.next();
			if(!producersusername.equals(ahuselection.getProducersusername())){
				itr.remove();
			}
		}
		return result; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((bottomleftresult == null) ? 0 : bottomleftresult.hashCode());
		result = prime
				* result
				+ ((bottomrightresult == null) ? 0 : bottomrightresult
						.hashCode());
		result = prime * result
				+ ((descriptions == null) ? 0 : descriptions.hashCode());
		result = prime
				* result
				+ ((exhaustsummerairflow == null) ? 0 : exhaustsummerairflow
						.hashCode());
		result = prime
				* result
				+ ((exhaustsummerpressuredrop == null) ? 0
						: exhaustsummerpressuredrop.hashCode());
		result = prime
				* result
				+ ((exhaustsystemname == null) ? 0 : exhaustsystemname
						.hashCode());
		result = prime
				* result
				+ ((exhaustwinterairflow == null) ? 0 : exhaustwinterairflow
						.hashCode());
		result = prime
				* result
				+ ((exhaustwinterpressuredrop == null) ? 0
						: exhaustwinterpressuredrop.hashCode());
		result = prime * result
				+ ((premises == null) ? 0 : premises.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime
				* result
				+ ((supplysummerairflow == null) ? 0 : supplysummerairflow
						.hashCode());
		result = prime
				* result
				+ ((supplysummerpressuredrop == null) ? 0
						: supplysummerpressuredrop.hashCode());
		result = prime
				* result
				+ ((supplysystemname == null) ? 0 : supplysystemname.hashCode());
		result = prime
				* result
				+ ((supplywinterairflow == null) ? 0 : supplywinterairflow
						.hashCode());
		result = prime
				* result
				+ ((supplywinterpressuredrop == null) ? 0
						: supplywinterpressuredrop.hashCode());
		result = prime * result
				+ ((topleftresult == null) ? 0 : topleftresult.hashCode());
		result = prime * result
				+ ((toprightresult == null) ? 0 : toprightresult.hashCode());
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
		Ahu other = (Ahu) obj;
		if (bottomleftresult == null) {
			if (other.bottomleftresult != null)
				return false;
		} else if (!bottomleftresult.equals(other.bottomleftresult))
			return false;
		if (bottomrightresult == null) {
			if (other.bottomrightresult != null)
				return false;
		} else if (!bottomrightresult.equals(other.bottomrightresult))
			return false;
		if (descriptions == null) {
			if (other.descriptions != null)
				return false;
		} else if (!descriptions.equals(other.descriptions))
			return false;
		if (exhaustsummerairflow == null) {
			if (other.exhaustsummerairflow != null)
				return false;
		} else if (!exhaustsummerairflow.equals(other.exhaustsummerairflow))
			return false;
		if (exhaustsummerpressuredrop == null) {
			if (other.exhaustsummerpressuredrop != null)
				return false;
		} else if (!exhaustsummerpressuredrop
				.equals(other.exhaustsummerpressuredrop))
			return false;
		if (exhaustsystemname == null) {
			if (other.exhaustsystemname != null)
				return false;
		} else if (!exhaustsystemname.equals(other.exhaustsystemname))
			return false;
		if (exhaustwinterairflow == null) {
			if (other.exhaustwinterairflow != null)
				return false;
		} else if (!exhaustwinterairflow.equals(other.exhaustwinterairflow))
			return false;
		if (exhaustwinterpressuredrop == null) {
			if (other.exhaustwinterpressuredrop != null)
				return false;
		} else if (!exhaustwinterpressuredrop
				.equals(other.exhaustwinterpressuredrop))
			return false;
		if (premises == null) {
			if (other.premises != null)
				return false;
		} else if (!premises.equals(other.premises))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (supplysummerairflow == null) {
			if (other.supplysummerairflow != null)
				return false;
		} else if (!supplysummerairflow.equals(other.supplysummerairflow))
			return false;
		if (supplysummerpressuredrop == null) {
			if (other.supplysummerpressuredrop != null)
				return false;
		} else if (!supplysummerpressuredrop
				.equals(other.supplysummerpressuredrop))
			return false;
		if (supplysystemname == null) {
			if (other.supplysystemname != null)
				return false;
		} else if (!supplysystemname.equals(other.supplysystemname))
			return false;
		if (supplywinterairflow == null) {
			if (other.supplywinterairflow != null)
				return false;
		} else if (!supplywinterairflow.equals(other.supplywinterairflow))
			return false;
		if (supplywinterpressuredrop == null) {
			if (other.supplywinterpressuredrop != null)
				return false;
		} else if (!supplywinterpressuredrop
				.equals(other.supplywinterpressuredrop))
			return false;
		if (topleftresult == null) {
			if (other.topleftresult != null)
				return false;
		} else if (!topleftresult.equals(other.topleftresult))
			return false;
		if (toprightresult == null) {
			if (other.toprightresult != null)
				return false;
		} else if (!toprightresult.equals(other.toprightresult))
			return false;
		return true;
	}
}
