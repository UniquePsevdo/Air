package com.company.dao;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="projects")
public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int projects_id;
	
	@Size(min=2, max=100, groups={FormValidationGroup.class})
	@Column(name="projectname") 
	private String projectname;
	
	@NotEmpty
	@Pattern(regexp="[+-]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*", groups={FormValidationGroup.class})
	private String winterairtemp;
	
	@Pattern(regexp="[+-]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*", groups={FormValidationGroup.class})
	private String summerairtemp;
	
	@Pattern(regexp="[+]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*", groups={FormValidationGroup.class})
	private String winterhumid;
	
	@Pattern(regexp="[+]*[0-9]{1,2}([,.]{1}[0-9]{1,2})*", groups={FormValidationGroup.class})
	private String summerhumid;
		
	private String producerscity;
	
	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name="projects_id")
	@IndexColumn(name="idx")
	List<Producer> producers;
	
	@Transient
	private List<String> producerslist;
	
	
	public List<String> getProducerslist() {
		return producerslist;
	}

	public void setProducerslist(List<String> producerslist) {
		this.producerslist = producerslist;
	}

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name="projects_id")
	@IndexColumn(name="idx")
	List<Ahu> ahus;
	
	
	public List<Ahu> getAhus() {
		return ahus;
	}

	public void setAhus(List<Ahu> ahus) {
		this.ahus = ahus;
	}

	private Date creationdate ;
	private Date lasteditiondate ;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;            // project creator
	
	public Project() {
		this.user = new User() ;  //!
	}	
	
	public Project(String projectname, String winterairtemp,
			String summerairtemp, String winterhumid, String summerhumid,
			String producerscity, List<Producer> producers, Date creationdate,
			User user) {		
		this.projectname = projectname;
		this.winterairtemp = winterairtemp;
		this.summerairtemp = summerairtemp;
		this.winterhumid = winterhumid;
		this.summerhumid = summerhumid;
		this.producerscity = producerscity;
		this.producers = producers;
		this.creationdate = creationdate;
		this.user = user;
	}
	
	public Project(int projects_id, String projectname, String winterairtemp,
			String summerairtemp, String winterhumid, String summerhumid,
			String producerscity, List<Producer> producers, Date creationdate,
			User user) {
		this.projects_id = projects_id;
		this.projectname = projectname;
		this.winterairtemp = winterairtemp;
		this.summerairtemp = summerairtemp;
		this.winterhumid = winterhumid;
		this.summerhumid = summerhumid;
		this.producerscity = producerscity;
		this.producers = producers;
		this.creationdate = creationdate;
		this.user = user;
	}



	public int getProjects_id() {
		return projects_id;
	}



	public void setProjects_id(int projects_id) {
		this.projects_id = projects_id;
	}



	public String getProjectname() {
		return projectname;
	}



	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}



	public String getWinterairtemp() {
		return winterairtemp;
	}



	public void setWinterairtemp(String winterairtemp) {
		this.winterairtemp = winterairtemp;
	}



	public String getSummerairtemp() {
		return summerairtemp;
	}



	public void setSummerairtemp(String summerairtemp) {
		this.summerairtemp = summerairtemp;
	}



	public String getWinterhumid() {
		return winterhumid;
	}



	public void setWinterhumid(String winterhumid) {
		this.winterhumid = winterhumid;
	}



	public String getSummerhumid() {
		return summerhumid;
	}



	public void setSummerhumid(String summerhumid) {
		this.summerhumid = summerhumid;
	}



	public String getProducerscity() {
		return producerscity;
	}



	public void setProducerscity(String producerscity) {
		this.producerscity = producerscity;
	}



	public List<Producer> getProducers() {
		return producers;
	}



	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}
	
	
	public Date getCreationdate() {
		return creationdate;
	}



	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}



	public Date getLasteditiondate() {
		return lasteditiondate;
	}



	public void setLasteditiondate(Date lasteditiondate) {
		this.lasteditiondate = lasteditiondate;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Project [projects_id=" + projects_id + ", projectname="
				+ projectname + ", winterairtemp=" + winterairtemp
				+ ", summerairtemp=" + summerairtemp + ", winterhumid="
				+ winterhumid + ", summerhumid=" + summerhumid
				+ ", producerscity=" + producerscity 
				+ producers + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationdate == null) ? 0 : creationdate.hashCode());
		result = prime * result
				+ ((lasteditiondate == null) ? 0 : lasteditiondate.hashCode());
		result = prime * result
				+ ((producerscity == null) ? 0 : producerscity.hashCode());
		result = prime * result
				+ ((producerslist == null) ? 0 : producerslist.hashCode());
		result = prime * result
				+ ((projectname == null) ? 0 : projectname.hashCode());
		result = prime * result + projects_id;
		result = prime * result
				+ ((summerairtemp == null) ? 0 : summerairtemp.hashCode());
		result = prime * result
				+ ((summerhumid == null) ? 0 : summerhumid.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((winterairtemp == null) ? 0 : winterairtemp.hashCode());
		result = prime * result
				+ ((winterhumid == null) ? 0 : winterhumid.hashCode());
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
		Project other = (Project) obj;
		if (creationdate == null) {
			if (other.creationdate != null)
				return false;
		} else if (!creationdate.equals(other.creationdate))
			return false;
		if (lasteditiondate == null) {
			if (other.lasteditiondate != null)
				return false;
		} else if (!lasteditiondate.equals(other.lasteditiondate))
			return false;
		if (producerscity == null) {
			if (other.producerscity != null)
				return false;
		} else if (!producerscity.equals(other.producerscity))
			return false;
		if (producerslist == null) {
			if (other.producerslist != null)
				return false;
		} else if (!producerslist.equals(other.producerslist))
			return false;
		if (projectname == null) {
			if (other.projectname != null)
				return false;
		} else if (!projectname.equals(other.projectname))
			return false;
		if (projects_id != other.projects_id)
			return false;
		if (summerairtemp == null) {
			if (other.summerairtemp != null)
				return false;
		} else if (!summerairtemp.equals(other.summerairtemp))
			return false;
		if (summerhumid == null) {
			if (other.summerhumid != null)
				return false;
		} else if (!summerhumid.equals(other.summerhumid))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (winterairtemp == null) {
			if (other.winterairtemp != null)
				return false;
		} else if (!winterairtemp.equals(other.winterairtemp))
			return false;
		if (winterhumid == null) {
			if (other.winterhumid != null)
				return false;
		} else if (!winterhumid.equals(other.winterhumid))
			return false;
		return true;
	}

	

	

}
