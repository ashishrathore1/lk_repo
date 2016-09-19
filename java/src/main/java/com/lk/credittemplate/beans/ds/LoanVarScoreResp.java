/**
 * 
 */
package com.lk.credittemplate.beans.ds;

/**
 * @author ramamohan
 *
 */
public class LoanVarScoreResp {

	private String status;

	private String message;

	private RespDataBean data;

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public RespDataBean getData() {
		return data;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(RespDataBean data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LoanVarScoreResp [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
