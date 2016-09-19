package com.lk.credittemplate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.lk.credittemplate.cibilmodel.DirectorMaster;

@Entity
@Table(name="LOAN_COMMENTS_CIBIL")
@DiscriminatorValue("CIBIL")
@PrimaryKeyJoinColumn(name="id")
public class LoanCommentsCibil extends LoanComments {

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = DirectorMaster.class)
	@JoinColumn(name = "DIRECTORID")
	private DirectorMaster directorMaster;

	public LoanCommentsCibil(){
		super("CIBIL");
	}
	
	
	public DirectorMaster getDirectorMaster() {
		return directorMaster;
	}

	public void setDirectorMaster(DirectorMaster directorMaster) {
		this.directorMaster = directorMaster;
	}

}
