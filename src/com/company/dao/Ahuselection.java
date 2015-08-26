package com.company.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ahuselection")
public class Ahuselection implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id") 
	private int id;
	
	@Column(name="filename")
	private String filename;
	
	@Column(name="producers_username")
	private String producersusername;
	
	@Column(name="companyname")
	private String companyname ;
	
	@Column(name="producerscity")
	private String producerscity ;
	
	@Column(name="pdf")
	private byte[] pdf ;
	
	@Column(name="uploadingdate")
	private Date uploadingdate ;
	
	
	@Transient
    @Column(name="ahus_id", insertable=false, updatable=false)
	private int ahus_id;
	
	@ManyToOne
    @JoinColumn(name="ahus_id")
	private Ahu ahu;
	
	public Ahuselection(){}
	
	public Ahuselection(String filename, String producersusername, int ahus_id, byte[] pdf, Date uploadingdate){
		this.filename = filename;
		this.producersusername = producersusername;
		this.ahus_id = ahus_id;
		this.pdf = pdf;
		this.uploadingdate = uploadingdate;
	}

	public String getProducersusername() {
		return producersusername;
	}

	public void setProducersusername(String producersusername) {
		this.producersusername = producersusername;
	}

	public int getAhus_id() {
		return ahus_id;
	}

	public void setAhus_id(int ahus_id) {
		this.ahus_id = ahus_id;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public Date getUploadingdate() {
		return uploadingdate;
	}

	public void setUploadingdate(Date uploadingdate) {
		this.uploadingdate = uploadingdate;
	}

	public Ahu getAhu() {
		return ahu;
	}

	public void setAhu(Ahu ahu) {
		this.ahu = ahu;
	}

	public int getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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
	
	
	
}
