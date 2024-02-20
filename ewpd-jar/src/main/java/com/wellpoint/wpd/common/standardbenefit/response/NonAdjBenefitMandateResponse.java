/*
 * Created on Mar 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;


/**
 * @author u13664
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NonAdjBenefitMandateResponse extends WPDResponse {
	private List notesList;
	private BenefitMandateBO benefitMandateBO;
	private CitationNumberBO citationNumberBO;
	

	/**
	 * @return Returns the benefitMandateBO.
	 */
	public BenefitMandateBO getBenefitMandateBO() {
		return benefitMandateBO;
	}
	/**
	 * @param benefitMandateBO The benefitMandateBO to set.
	 */
	public void setBenefitMandateBO(BenefitMandateBO benefitMandateBO) {
		this.benefitMandateBO = benefitMandateBO;
	}
	/**
	 * Returns the citationNumberBO
	 * @return CitationNumberBO citationNumberBO.
	 */
	public CitationNumberBO getCitationNumberBO() {
		return citationNumberBO;
	}
	/**
	 * Sets the citationNumberBO
	 * @param citationNumberBO.
	 */
	public void setCitationNumberBO(CitationNumberBO citationNumberBO) {
		this.citationNumberBO = citationNumberBO;
	}
	/**
	 * @return Returns the notesList.
	 */
	public List getNotesList() {
		return notesList;
	}
	/**
	 * @param notesList The notesList to set.
	 */
	public void setNotesList(List notesList) {
		this.notesList = notesList;
	}
}
