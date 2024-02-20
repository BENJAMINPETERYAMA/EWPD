/*
 * Created on Feb 29, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.vo;

/**
 * @author U15236
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLineForViewVO {
	
	private String benefitLineId;
	private String seq; 
	private String description; 
	private String term; 
	private String qualifier; 
	private String pva; 
	private String format; 
	private String benefitValue; 
	private String reference;
	private boolean notesExist;
	private boolean renderNotesAttachmentImage; 
	//CARS START
	//DESCRIPTION : Variable to hold frequency value 
	private String frequency;
	//CASR END
	/**
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}
	/**
	 * @param benefitValue The benefitValue to set.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
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
	 * @param qualifier The qualifier to set.
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
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return Returns the seq.
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq The seq to set.
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	
	/**
	 * @return Returns the benefitLineId.
	 */
	public String getBenefitLineId() {
		return benefitLineId;
	}
	/**
	 * @param benefitLineId The benefitLineId to set.
	 */
	public void setBenefitLineId(String benefitLineId) {
		this.benefitLineId = benefitLineId;
	}
	/**
	 * @return Returns the notesExist.
	 */
	public boolean isNotesExist() {
		return notesExist;
	}
	/**
	 * @param notesExist The notesExist to set.
	 */
	public void setNotesExist(boolean notesExist) {
		this.notesExist = notesExist;
	}
	/**
	 * @return Returns the renderNotesAttachmentImage.
	 */
	public boolean isRenderNotesAttachmentImage() {
		return renderNotesAttachmentImage;
	}
	/**
	 * @param renderNotesAttachmentImage The renderNotesAttachmentImage to set.
	 */
	public void setRenderNotesAttachmentImage(boolean renderNotesAttachmentImage) {
		this.renderNotesAttachmentImage = renderNotesAttachmentImage;
	}
	/**
	 * @return Returns the frequency.
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency The frequency to set.
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
}
