package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HippaSegment {

	String name;
	
	String description;//This description is displayed in a tooltip?	
	
	String hippaSegmentName;
	String hippaSegmentDefinition;
	List<EB03Association> eb03Association = new ArrayList<EB03Association>();
	
	/**
	 * @uml.annotations for <code>extendedMsgsForSelectedValues</code>
	 *                  collection_type="com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue"
	 */
	private List extendedMsgsForSelectedValues = new ArrayList();
	
	/**
	 * @uml.annotations for <code>hippaCodeSelectedValues</code>
	 *                  collection_type="com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue"
	 */
	private List hippaCodeSelectedValues = new ArrayList();

	/**
	 * @uml.annotations for <code>hippaCodePossibleValues</code>
	 *                  collection_type="com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue"
	 */
	private List hippaCodePossibleValues = new ArrayList();//will contin the values in popup.

	public HippaSegment(String hippaSegmentname) {

		name = hippaSegmentname;
	}

	public HippaSegment() {

	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the hippaCodePossibleValues.
	 */
	public List getHippaCodePossibleValues() {
		return hippaCodePossibleValues;
	}

	/**
	 * @param hippaCodePossibleValues
	 *            The hippaCodePossibleValues to set.
	 */
	public void setHippaCodePossibleValues(List hippaCodePossibleValues) {
		this.hippaCodePossibleValues = hippaCodePossibleValues;
	}

	/**
	 * @return Returns the hippaCodeSelectedValues.
	 */
	public List getHippaCodeSelectedValues() {
		return hippaCodeSelectedValues;
	}

	/**
	 * @param hippaCodeSelectedValues
	 *            The hippaCodeSelectedValues to set.
	 */
	public void setHippaCodeSelectedValues(List hippaCodeSelectedValues) {
		this.hippaCodeSelectedValues = hippaCodeSelectedValues;
	}
	
	/**
	 * @return Returns the extendedMsgsForSelectedValues.
	 */
	public List getExtendedMsgsForSelectedValues() {
		return extendedMsgsForSelectedValues;
	}

	/**
	 * @param extendedMsgsForSelectedValues
	 *            The extendedMsgsForSelectedValues to set.
	 */
	public void setExtendedMsgsForSelectedValues(List extendedMsgsForSelectedValues) {
		this.extendedMsgsForSelectedValues = extendedMsgsForSelectedValues;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the hippaSegmentDefinition.
	 */
	public String getHippaSegmentDefinition() {
		return hippaSegmentDefinition;
	}
	/**
	 * @param hippaSegmentDefinition The hippaSegmentDefinition to set.
	 */
	public void setHippaSegmentDefinition(String hippaSegmentDefinition) {
		this.hippaSegmentDefinition = hippaSegmentDefinition;
	}
	/**
	 * @return Returns the hippaSegmentName.
	 */
	public String getHippaSegmentName() {
		return hippaSegmentName;
	}
	/**
	 * @param hippaSegmentName The hippaSegmentName to set.
	 */
	public void setHippaSegmentName(String hippaSegmentName) {
		this.hippaSegmentName = hippaSegmentName;
	}

	public List<EB03Association> getEb03Association() {
		return eb03Association;
	}

	public void setEb03Association(List<EB03Association> eb03Association) {
		this.eb03Association = eb03Association;
	}

	
}
