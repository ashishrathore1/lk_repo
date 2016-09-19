package com.lk.credittemplate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="LOAN_COMMENTS_CAT")
@DiscriminatorValue("CAT")
@PrimaryKeyJoinColumn(name="id")
public class LoanCommentsCat extends LoanComments{

	
	public LoanCommentsCat(){
		super("CAT");
	}
	
	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}
	
	

	public void setStatus(String status) {
		this.status = status;
	}
	
}
