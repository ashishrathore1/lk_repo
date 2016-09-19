package com.lk.credittemplate.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;



@Entity
@Table(name="LK_ROLE_MASTER")
@NamedQueries(
		{
			@NamedQuery(name = "getRoleIdUsingRoleName",
					query = "SELECT r FROM RoleMaster r WHERE r.name =:name")
		}
	)
public class RoleMaster 
{
	
	@Id
	@Column(name="R_GUID")
	private String roleId;

	@Column(name="R_NAME")
	private String name;
	
	@Column(name="R_DESCRIPTION")
	private String description;
	
	@Column(name = "R_CREATED_DATE")
	private Date created;
	
	@Column(name = "R_UPDATED_DATE")
	private Date modified;
	
	@Column(name="R_OPERATED_BY")
	private String operatedBy;
	
	@Column(name="R_LEVEL")
	private int level;
	
	public RoleMaster() {
		if(StringUtils.isEmpty(this.roleId)){
			this.roleId = UUID.randomUUID().toString();
		}
		
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the operatedBy
	 */
	public String getOperatedBy() {
		return operatedBy;
	}

	/**
	 * @param operatedBy the operatedBy to set
	 */
	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
