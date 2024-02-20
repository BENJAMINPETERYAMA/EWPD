package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.List;

public class HippaSegmentWebServiceVO {

	private String name;

	private String description;// This description is displayed in a tooltip?

	private String hippaSegmentName;
	private String hippaSegmentDefinition;
	
	//SSCR 19537 Change
	private List<EB03AssociationWebServiceVO> eb03Association;

	/**
	 * @uml.annotations for <code>hippaCodeSelectedValues</code>
	 *                  collection_type=
	 *                  "com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue"
	 */
	private List<HippaCodeValueWebServiceVO> hippaCodeSelectedValuesForEwpd = null;

	/**
	 * @uml.annotations for <code>hippaCodeSelectedValues</code>
	 *                  collection_type=
	 *                  "com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue"
	 */
	private List<String> hippaCodeSelectedValuesForLgIsg = null;

	/**
	 * @uml.annotations for <code>hippaCodePossibleValues</code>
	 *                  collection_type=
	 *                  "com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue"
	 */
	private List<HippaCodeValueWebServiceVO> hippaCodePossibleValuesForEwpd = null;

	/**
	 * @uml.annotations for <code>hippaCodePossibleValues</code>
	 *                  collection_type=
	 *                  "com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue"
	 */
	private List<String> hippaCodePossibleValuesForLgIsg = null;

	/**
	 * @return the hippaCodeSelectedValuesForEwpd
	 */
	public List<HippaCodeValueWebServiceVO> getHippaCodeSelectedValuesForEwpd() {
		return hippaCodeSelectedValuesForEwpd;
	}

	/**
	 * @param hippaCodeSelectedValuesForEwpd the hippaCodeSelectedValuesForEwpd to set
	 */
	public void setHippaCodeSelectedValuesForEwpd(
			List<HippaCodeValueWebServiceVO> hippaCodeSelectedValuesForEwpd) {
		this.hippaCodeSelectedValuesForEwpd = hippaCodeSelectedValuesForEwpd;
	}

	/**
	 * @return the hippaCodeSelectedValuesForLgIsg
	 */
	public List<String> getHippaCodeSelectedValuesForLgIsg() {
		return hippaCodeSelectedValuesForLgIsg;
	}

	/**
	 * @param hippaCodeSelectedValuesForLgIsg the hippaCodeSelectedValuesForLgIsg to set
	 */
	public void setHippaCodeSelectedValuesForLgIsg(
			List<String> hippaCodeSelectedValuesForLgIsg) {
		this.hippaCodeSelectedValuesForLgIsg = hippaCodeSelectedValuesForLgIsg;
	}

	/**
	 * @return the hippaCodePossibleValuesForEwpd
	 */
	public List<HippaCodeValueWebServiceVO> getHippaCodePossibleValuesForEwpd() {
		return hippaCodePossibleValuesForEwpd;
	}

	/**
	 * @param hippaCodePossibleValuesForEwpd the hippaCodePossibleValuesForEwpd to set
	 */
	public void setHippaCodePossibleValuesForEwpd(
			List<HippaCodeValueWebServiceVO> hippaCodePossibleValuesForEwpd) {
		this.hippaCodePossibleValuesForEwpd = hippaCodePossibleValuesForEwpd;
	}

	/**
	 * @return the hippaCodePossibleValuesForLgIsg
	 */
	public List<String> getHippaCodePossibleValuesForLgIsg() {
		return hippaCodePossibleValuesForLgIsg;
	}

	/**
	 * @param hippaCodePossibleValuesForLgIsg the hippaCodePossibleValuesForLgIsg to set
	 */
	public void setHippaCodePossibleValuesForLgIsg(
			List<String> hippaCodePossibleValuesForLgIsg) {
		this.hippaCodePossibleValuesForLgIsg = hippaCodePossibleValuesForLgIsg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHippaSegmentName() {
		return hippaSegmentName;
	}

	public void setHippaSegmentName(String hippaSegmentName) {
		this.hippaSegmentName = hippaSegmentName;
	}

	public String getHippaSegmentDefinition() {
		return hippaSegmentDefinition;
	}

	public void setHippaSegmentDefinition(String hippaSegmentDefinition) {
		this.hippaSegmentDefinition = hippaSegmentDefinition;
	}

	/**
	 * @return the eb03Association
	 */
	public List<EB03AssociationWebServiceVO> getEb03Association() {
		return eb03Association;
	}

	/**
	 * @param eb03Association the eb03Association to set
	 */
	public void setEb03Association(List<EB03AssociationWebServiceVO> eb03Association) {
		this.eb03Association = eb03Association;
	}

	
}
