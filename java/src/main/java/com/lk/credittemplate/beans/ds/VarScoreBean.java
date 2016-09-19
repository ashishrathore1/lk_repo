/**
 * 
 */
package com.lk.credittemplate.beans.ds;

import java.util.Date;

import com.lk.credittemplate.common.util.LKUtils;

/**
 * @author ramamohan
 *
 */
public class VarScoreBean {

	private Long id;

	private String varName;

	private String expression;

	private double weight;

	private String definition;

	private String version;

	private double varScore;

	public double getVarScore() {
		return varScore;
	}

	public void setVarScore(double varScore) {
		this.varScore = varScore;
	}

	private Date createdDate = LKUtils.getDate();

	private String operatedBy;

	public Long getId() {
		return id;
	}

	public String getVarName() {
		return varName;
	}

	public String getExpression() {
		return expression;
	}

	public double getWeight() {
		return weight;
	}

	public String getDefinition() {
		return definition;
	}

	public String getVersion() {
		return version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getOperatedBy() {
		return operatedBy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	@Override
	public String toString() {
		return "VarScoreBean [id=" + id + ", varName=" + varName + ", expression=" + expression + ", weight=" + weight
				+ ", definition=" + definition + ", version=" + version + ", createdDate=" + createdDate
				+ ", operatedBy=" + operatedBy + "]";
	}
}
