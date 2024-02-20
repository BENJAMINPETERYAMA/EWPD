/*
 * Created on Apr 7, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.response;

import java.util.List;

import com.wellpoint.wpd.common.migration.bo.BenefitComponentNote;


/**
 * @author u13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveBenefitDetailsResponse  extends MigrationContractResponse{
	
	private List list; 
	private BenefitComponentNote benefitComponentNote;


	/**
	 * @return Returns the list.
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * @return Returns the benefitComponentNote.
	 */
	public BenefitComponentNote getBenefitComponentNote() {
		return benefitComponentNote;
	}
	/**
	 * @param benefitComponentNote The benefitComponentNote to set.
	 */
	public void setBenefitComponentNote(
			BenefitComponentNote benefitComponentNote) {
		this.benefitComponentNote = benefitComponentNote;
	}
}
