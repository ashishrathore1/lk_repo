/**
 * 
 */
package com.lk.credittemplate.beans;

/**
 * @author prashanth
 * @param <T>
 *
 */
public class ResponseBean<RestData> {

	private String status;

	private String message;

	private RestData data;
	
	private long count;

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public RestData getData() {
		return data;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(RestData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LoanVarScoreResp [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

}
