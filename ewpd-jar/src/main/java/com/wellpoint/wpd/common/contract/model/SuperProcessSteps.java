/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SuperProcessSteps implements Serializable{
	
	private String superProcessStep;
	
	private String adminMethod;
	
	private String reference;
		

	/**
	 * @return Returns the adminMethod.
	 */
	public String getAdminMethod() {
		return adminMethod;
	}
	/**
	 * @param adminMethod The adminMethod to set.
	 */
	public void setAdminMethod(String adminMethod) {
		this.adminMethod = adminMethod;
	}
	/**
	 * @return Returns the superProcessStep.
	 */
	public String getSuperProcessStep() {
		return superProcessStep;
	}
	/**
	 * @param superProcessStep The superProcessStep to set.
	 */
	public void setSuperProcessStep(String superProcessStep) {
		this.superProcessStep = superProcessStep;
	}
	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
}
