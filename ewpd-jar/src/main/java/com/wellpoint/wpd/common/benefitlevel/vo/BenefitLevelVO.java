/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.vo;

import java.util.List;

import com.wellpoint.wpd.common.framework.util.StringUtil;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelVO implements Comparable{
	
	private String benefitTerm;
	
	private String benefitQualifier;
	
	private List benefitLines;
	
	private int benefitDefinitionId;
	
	private int benefitLevelId;
	
	private String benefitLevelDesc;
	
	private String reference;
	
	private String referenceCode;
	
	private int benefitLevelSeq;
	
	private int dataTypeId;
	
	private String benefitValue;
	
	private String benefitTermCode;
	
	private String benefitQualifierCode;
	
	private String benefitTermsAsString;
// **Change**
	private List benefitTerms;
// **End**	
	private String benefitQualifiersAsString;
// Change: Aggregate qualifier
	private List benefitQualifiers;
// End	
// Change for performance on 12th dec 2007
	private boolean isModified;
//	end	
	//START CARS
	//DESCRIPTION : Frequency integer value
	private int benefitFrequency;
	//DESCRIPTION : Old Frequency value
	private int oldFrequencyValue;
	//DESCRIPTION : Frequency flag to hold whether it's a number
	private boolean frequencyNumber;
	//DESCRIPTION : Frequency flag to hold whether it's empty
	private boolean frequencyValueEmpty;
	//CARS END
	public String getBenefitValue() {
		return benefitValue;
	}
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}
	public int getDataTypeId() {
		return dataTypeId;
	}
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	/**
	 * @return Returns the benefitLevelSeq.
	 */
	public int getBenefitLevelSeq() {
		return benefitLevelSeq;
	}
	/**
	 * @param benefitLevelSeq The benefitLevelSeq to set.
	 */
	public void setBenefitLevelSeq(int benefitLevelSeq) {
		this.benefitLevelSeq = benefitLevelSeq;
	}
	/**
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * @param benefitDefinitionId The benefitDefinitionId to set.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}
	/**
	 * @return Returns the benefitQualifier.
	 */
	public String getBenefitQualifier() {
		return benefitQualifier;
	}
	/**
	 * @param benefitQualifier The benefitQualifier to set.
	 */
	public void setBenefitQualifier(String benefitQualifier) {
		this.benefitQualifier = benefitQualifier;
	}
	/**
	 * @return Returns the benefitTerm.
	 */
	public String getBenefitTerm() {
		return benefitTerm;
	}
	/**
	 * @param benefitTerm The benefitTerm to set.
	 */
	public void setBenefitTerm(String benefitTerm) {
		this.benefitTerm = benefitTerm;
	}
	
	/**
	 * @return Returns the benefitLines.
	 */
	public List getBenefitLines() {
		return benefitLines;
	}
	/**
	 * @param benefitLines The benefitLines to set.
	 */
	public void setBenefitLines(List benefitLines) {
		this.benefitLines = benefitLines;
	}
	/**
	 * @return Returns the benefitLevelDesc.
	 */
	public String getBenefitLevelDesc() {
		return benefitLevelDesc;
	}
	/**
	 * @param benefitLevelDesc The benefitLevelDesc to set.
	 */
	public void setBenefitLevelDesc(String benefitLevelDesc) {
		this.benefitLevelDesc = benefitLevelDesc.trim();
	}
	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * @param benefitLevelId The benefitLevelId to set.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
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
	 * @return Returns the benefitQualifierCode.
	 */
	public String getBenefitQualifierCode() {
		return benefitQualifierCode;
	}
	/**
	 * @param benefitQualifierCode The benefitQualifierCode to set.
	 */
	public void setBenefitQualifierCode(String benefitQualifierCode) {
		this.benefitQualifierCode = benefitQualifierCode;
	}
	/**
	 * @return Returns the benefitTermCode.
	 */
	public String getBenefitTermCode() {
		return benefitTermCode;
	}
	/**
	 * @param benefitTermCode The benefitTermCode to set.
	 */
	public void setBenefitTermCode(String benefitTermCode) {
		this.benefitTermCode = benefitTermCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		int result = 0;
		BenefitLevelVO benefitLevelVO = (BenefitLevelVO)o;
		if(this.getBenefitLevelSeq() > benefitLevelVO.getBenefitLevelSeq()){
			result = 1;
		}
		if(this.getBenefitLevelSeq() < benefitLevelVO.getBenefitLevelSeq()){
			result = -1;
		}
		if(this.getBenefitLevelSeq() == benefitLevelVO.getBenefitLevelSeq()){
			result = 0;
		}
		return result;
	}
	
	/**
	 * @return Returns the referenceCode.
	 */
	public String getReferenceCode() {
		return referenceCode;
	}
	/**
	 * @param referenceCode The referenceCode to set.
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	/**
	 * @return Returns the benfitTerms.
	 */
	public List getBenefitTerms() {
		return benefitTerms;
	}
	/**
	 * @param benfitTerms The benfitTerms to set.
	 */
	public void setBenefitTerms(List benfitTerms) {
		this.benefitTerms = benfitTerms;
	}
	
//Change: Aggregate qualifier	
/**
 * Returns the benefitQualifiers
 * @return List benefitQualifiers.
 */
public List getBenefitQualifiers() {
    return benefitQualifiers;
}
/**
 * Sets the benefitQualifiers
 * @param benefitQualifiers.
 */
public void setBenefitQualifiers(List benefitQualifiers) {
    this.benefitQualifiers = benefitQualifiers;
}
//end
//Change for Performance

/**
 * @return isModified
 * 
 * Returns the isModified.
 */
public boolean isModified() {
    return isModified;
}
/**
 * @param isModified
 * 
 * Sets the isModified.
 */
public void setModified(boolean isModified) {
    this.isModified = isModified;
}
/**
 * @return Returns the benefitFrequency.
 */
public int getBenefitFrequency() {
	return benefitFrequency;
}
/**
 * @param benefitFrequency The benefitFrequency to set.
 */
public void setBenefitFrequency(int benefitFrequency) {
	this.benefitFrequency = benefitFrequency;
}
	/**
	 * @return Returns the frequencyNumber.
	 */
	public boolean isFrequencyNumber() {
		return frequencyNumber;
	}
	/**
	 * @param frequencyNumber The frequencyNumber to set.
	 */
	public void setFrequencyNumber(boolean frequencyNumber) {
		this.frequencyNumber = frequencyNumber;
	}
	/**
	 * @return Returns the frequencyValueEmpty.
	 */
	public boolean isFrequencyValueEmpty() {
		return frequencyValueEmpty;
	}
	/**
	 * @param frequencyValueEmpty The frequencyValueEmpty to set.
	 */
	public void setFrequencyValueEmpty(boolean frequencyValueEmpty) {
		this.frequencyValueEmpty = frequencyValueEmpty;
	}
	/**
	 * @return Returns the oldFrequencyValue.
	 */
	public int getOldFrequencyValue() {
		return oldFrequencyValue;
	}
	/**
	 * @param oldFrequencyValue The oldFrequencyValue to set.
	 */
	public void setOldFrequencyValue(int oldFrequencyValue) {
		this.oldFrequencyValue = oldFrequencyValue;
	}
	/**
	 * @return Returns the benefitTermsAsString.
	 */
	public String getBenefitTermsAsString() {
		StringBuffer termBuffer = new StringBuffer();
		if (null != benefitTerms && !benefitTerms.isEmpty()) {
			BenefitTermVO benefitTermVO = null;
            for (int k = 0; k <benefitTerms.size(); k++) {
                benefitTermVO = (BenefitTermVO) benefitTerms.get(k);
                termBuffer.append( benefitTermVO.getBenefitTerm());
                if(k < benefitTerms.size()-1){
                	termBuffer.append(",");
                }
            }
        }
		return termBuffer.toString();
	}
	/**
	 * @param benefitTermsAsString The benefitTermsAsString to set.
	 */
	public void setBenefitTermsAsString(String benefitTermsAsString) {
		this.benefitTermsAsString = benefitTermsAsString;
	}
/**
 * @return Returns the benefitQualifiersAsString.
 */
public String getBenefitQualifiersAsString() {
	StringBuffer qualBuffer = new StringBuffer();
	if (null != benefitQualifiers && !benefitQualifiers.isEmpty()) {
		BenefitQualifierVO benefitQualifierVO = null;
        for (int l = 0; l < benefitQualifiers.size(); l++) {
        	benefitQualifierVO = (BenefitQualifierVO)benefitQualifiers.get(l);
        	qualBuffer.append(benefitQualifierVO.getBenefitQualifier());
        	if(l < benefitQualifiers.size()-1){
        		qualBuffer.append(",");
        	}
        }
    }
	return qualBuffer.toString();
}
/**
 * @param benefitQualifiersAsString The benefitQualifiersAsString to set.
 */
	public void setBenefitQualifiersAsString(String benefitQualifiersAsString) {
		this.benefitQualifiersAsString = benefitQualifiersAsString;
	}
}
