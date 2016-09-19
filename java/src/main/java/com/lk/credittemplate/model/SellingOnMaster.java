package com.lk.credittemplate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LK_SELLINGON_MASTER")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries(
{
//@NamedQuery(name = "showCatComments",
//query = "SELECT c FROM LoanComments c where c.appId =:appId order by c.createdDate"),
@NamedQuery(name = "getSellingnamesFromSellGuid",
query = "SELECT c FROM SellingOnMaster c where c.pGuid =:pGuid")
//
	})
public class SellingOnMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "P_GUID")
	private String pGuid;

	@Column(name = "P_NAME")
	private String pName;

	@Column(name = "P_DESCRIPTION")
	private String pDescription;

	@Column(name = "P_IMAGE_FILENAME")
	private String pImageFileName;
	
	@Column(name = "P_IMAGE_FILEPATH")
	private String pImageFilePath;
	
	@Column(name = "P_IMAGE_SRC")
	private long pImageSrc;

	public String getpGuid() {
		return pGuid;
	}

	public void setpGuid(String pGuid) {
		this.pGuid = pGuid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public String getpImageFileName() {
		return pImageFileName;
	}

	public void setpImageFileName(String pImageFileName) {
		this.pImageFileName = pImageFileName;
	}

	public String getpImageFilePath() {
		return pImageFilePath;
	}

	public void setpImageFilePath(String pImageFilePath) {
		this.pImageFilePath = pImageFilePath;
	}

	public long getpImageSrc() {
		return pImageSrc;
	}

	public void setpImageSrc(long pImageSrc) {
		this.pImageSrc = pImageSrc;
	}
	
	
	
}
