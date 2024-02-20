/**
 * 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author U30262
 *
 */
public class ContractWebServiceRequest extends ContractRequest {
	
	private String environment = null;
	private String newComments = "";
	private boolean commentUpdateReqd = true;
	


	public String getNewComments() {
		return newComments;
	}




	public void setNewComments(String newComments) {
		this.newComments = newComments;
	}




	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}




	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}




	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	@Override
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}




	public void setCommentUpdateReqd(boolean commentUpdateReqd) {
		this.commentUpdateReqd = commentUpdateReqd;
	}




	public boolean isCommentUpdateReqd() {
		return commentUpdateReqd;
	}

}
