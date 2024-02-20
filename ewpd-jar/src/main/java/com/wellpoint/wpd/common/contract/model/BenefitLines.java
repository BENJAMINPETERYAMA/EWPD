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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BenefitLines implements Serializable {

	private String term;

	private String qualifier;

	private String pva;

	private String format;

	private String reference;

	private String benefitValue;

	private String benefitLevelDescription;

	private Note note;

	private int levelId;

	/**
	 * @return Returns the benefitLevelDescription.
	 */
	public String getBenefitLevelDescription() {
		return benefitLevelDescription;
	}

	/**
	 * @param benefitLevelDescription
	 *            The benefitLevelDescription to set.
	 */
	public void setBenefitLevelDescription(String benefitLevelDescription) {
		this.benefitLevelDescription = benefitLevelDescription;
	}

	/**
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}

	/**
	 * @param benefitValue
	 *            The benefitValue to set.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}

	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return Returns the note.
	 */
	public Note getNote() {
		return note;
	}

	/**
	 * @param note
	 *            The note to set.
	 */
	public void setNote(Note note) {
		this.note = note;
	}

	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}

	/**
	 * @param pva
	 *            The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
	}

	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}

	/**
	 * @param qualifier
	 *            The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 *            The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return Returns the levelId.
	 */
	public int getLevelId() {
		return levelId;
	}

	/**
	 * @param levelId
	 *            The levelId to set.
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
}