/**
 * 
 */
package com.lk.credittemplate.model.ds;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author ramamohan
 *
 */
@Entity
@Table(name = "LK_VARSCORE_MASTER")
@NamedQueries({

		@NamedQuery(name = "getVarScores", query = "SELECT vs FROM VarScoreModel AS vs WHERE vs.version = (SELECT max(vm.version) FROM VarScoreModel AS vm)")

})
public class VarScoreModel {

	@Id
	@GeneratedValue
	@Column(name = "VS_GUID", nullable = false)
	private Long id;

	@Column(name = "VS_VARIABLE_NAME", nullable = false)
	private String varName;

	@Column(name = "VS_EXPRESSION")
	private String expression;

	@Column(name = "VS_WEIGHT")
	private double weight;

	@Column(name = "VS_DEFINITION")
	private String definition;

	@Column(name = "VS_VERSION", nullable = false)
	private String version;

	@Column(name = "VS_CREATED_DATE", nullable = false)
	private Date createdDate;

	@Column(name = "VS_OPERATED_BY")
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
		return "VarScoreModel [id=" + id + ", varName=" + varName + ", expression=" + expression + ", weight=" + weight
				+ ", definition=" + definition + ", version=" + version + ", createdDate=" + createdDate
				+ ", operatedBy=" + operatedBy + "]";
	}
}
