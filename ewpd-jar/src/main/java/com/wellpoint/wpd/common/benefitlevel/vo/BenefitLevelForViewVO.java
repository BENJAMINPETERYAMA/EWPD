/*
 * Created on Feb 23, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.vo;

import java.util.List;

/**
 * @author UST Global
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelForViewVO {
	
	private boolean benefitLine;
	
	private boolean selectForDelete;
	
	private boolean renderNotesAttachmentImage;
	
	private String notePopUpURl;
	
	private boolean noteExist;
	
	private int numberOfLines;
	
	private String referencePopUpUrl;
	
	private String freequencyAsString;
	/**
	 * Wrappter obejct variables
	 */
	private int wrapperBenefitDefinitionId;
	
	private int standardBenefitKey;
	
	private int masterVersion;
	
	private String benefitIdentifier;
	
	private String masterStatus;
	
	private List businessDomains;
	
	private String selectedBenefitTerm;
	
	private boolean descriptionFlag = false;
	
	private String descriptionValue;
	
	private int standardBenefitParentKey;

	private boolean deleteFlag = false;

	/**
	 * @return Returns the benefitLineDataTypeIdAsString.
	 */
	public String getBenefitLineDataTypeIdAsString() {
		return String.valueOf(benefitLineDataTypeId);
	}
	/**
	 * @param benefitLineDataTypeIdAsString The benefitLineDataTypeIdAsString to set.
	 */
	public void setBenefitLineDataTypeIdAsString(
			String benefitLineDataTypeIdAsString) {
		this.benefitLineDataTypeId = Integer.parseInt(benefitLineDataTypeIdAsString);
		this.benefitLineDataTypeIdAsString = benefitLineDataTypeIdAsString;
	}
	/**
	 * @return Returns the dataTypeAsString.
	 */
	public String getDataTypeAsString() {
		return String.valueOf(dataTypeId);
	}
	/**
	 * @param dataTypeAsString The dataTypeAsString to set.
	 */
	public void setDataTypeAsString(String dataTypeAsString) {
		this.dataTypeAsString = dataTypeAsString;
	}
	/**
	 * @return Returns the hiddenReference.
	 */
	public String getHiddenReference() {
		return hiddenReference;
	}
	/**
	 * @param hiddenReference The hiddenReference to set.
	 */
	public void setHiddenReference(String hiddenReference) {
		this.hiddenReference = hiddenReference;
	}
	/**
	 * @return Returns the referencePopUpUrl.
	 */
	public String getReferencePopUpUrl() {
		return referencePopUpUrl;
	}
	/**
	 * @param referencePopUpUrl The referencePopUpUrl to set.
	 */
	public void setReferencePopUpUrl(String referencePopUpUrl) {
		this.referencePopUpUrl = referencePopUpUrl;
	}
	/**
	 * @return Returns the benefitLevelIdAsString.
	 */
	public String getBenefitLevelIdAsString() {
		return String.valueOf(benefitLevelId);
	}
	/**
	 * @param benefitLevelIdAsString The benefitLevelIdAsString to set.
	 */
	public void setBenefitLevelIdAsString(String benefitLevelIdAsString) {
		this.benefitLevelIdAsString = benefitLevelIdAsString;
	}
	/**
	 * @return Returns the numberOfLines.
	 */
	public int getNumberOfLines() {
		return numberOfLines;
	}
	/**
	 * @param numberOfLines The numberOfLines to set.
	 */
	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}
	/**
	 * @return Returns the wrapperBenefitDefinitionId.
	 */
	public int getWrapperBenefitDefinitionId() {
		return wrapperBenefitDefinitionId;
	}
	/**
	 * @param wrapperBenefitDefinitionId The wrapperBenefitDefinitionId to set.
	 */
	public void setWrapperBenefitDefinitionId(int wrapperBenefitDefinitionId) {
		this.wrapperBenefitDefinitionId = wrapperBenefitDefinitionId;
	}
	/**
	 * @return Returns the benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * @param benefitIdentifier The benefitIdentifier to set.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return Returns the descriptionFlag.
	 */
	public boolean isDescriptionFlag() {
		return descriptionFlag;
	}
	/**
	 * @param descriptionFlag The descriptionFlag to set.
	 */
	public void setDescriptionFlag(boolean descriptionFlag) {
		this.descriptionFlag = descriptionFlag;
	}
	/**
	 * @return Returns the descriptionValue.
	 */
	public String getDescriptionValue() {
		return descriptionValue;
	}
	/**
	 * @param descriptionValue The descriptionValue to set.
	 */
	public void setDescriptionValue(String descriptionValue) {
		this.descriptionValue = descriptionValue;
	}
	/**
	 * @return Returns the masterStatus.
	 */
	public String getMasterStatus() {
		return masterStatus;
	}
	/**
	 * @param masterStatus The masterStatus to set.
	 */
	public void setMasterStatus(String masterStatus) {
		this.masterStatus = masterStatus;
	}
	/**
	 * @return Returns the masterVersion.
	 */
	public int getMasterVersion() {
		return masterVersion;
	}
	/**
	 * @param masterVersion The masterVersion to set.
	 */
	public void setMasterVersion(int masterVersion) {
		this.masterVersion = masterVersion;
	}
	/**
	 * @return Returns the selectedBenefitTerm.
	 */
	public String getSelectedBenefitTerm() {
		return selectedBenefitTerm;
	}
	/**
	 * @param selectedBenefitTerm The selectedBenefitTerm to set.
	 */
	public void setSelectedBenefitTerm(String selectedBenefitTerm) {
		this.selectedBenefitTerm = selectedBenefitTerm;
	}
	/**
	 * @return Returns the standardBenefitKey.
	 */
	public int getStandardBenefitKey() {
		return standardBenefitKey;
	}
	/**
	 * @param standardBenefitKey The standardBenefitKey to set.
	 */
	public void setStandardBenefitKey(int standardBenefitKey) {
		this.standardBenefitKey = standardBenefitKey;
	}
	/**
	 * @return Returns the standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * @param standardBenefitParentKey The standardBenefitParentKey to set.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	/**
	 * @return Returns the noteExist.
	 */
	public boolean isNoteExist() {
		return noteExist;
	}
	/**
	 * @param noteExist The noteExist to set.
	 */
	public void setNoteExist(boolean noteExist) {
		this.noteExist = noteExist;
	}
	/**
	 * @return Returns the notePopUpURl.
	 */
	public String getNotePopUpURl() {
		return notePopUpURl;
	}
	/**
	 * @param notePopUpURl The notePopUpURl to set.
	 */
	public void setNotePopUpURl(String notePopUpURl) {
		this.notePopUpURl = notePopUpURl;
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
	 * @return Returns the selectForDelete.
	 */
	public boolean isSelectForDelete() {
		return selectForDelete;
	}
	/**
	 * @param selectForDelete The selectForDelete to set.
	 */
	public void setSelectForDelete(boolean selectForDelete) {
		this.selectForDelete = selectForDelete;
	}
	/**
	 * @return Returns the benefitLine.
	 */
	public boolean isBenefitLine() {
		return benefitLine;
	}
	/**
	 * @param benefitLine The benefitLine to set.
	 */
	public void setBenefitLine(boolean benefitLine) {
		this.benefitLine = benefitLine;
	}
	
	/**
	 * BenefitLineVO
	 */
	private String pvaCode;
	
	private String PVA;
	
	private int benefitLineId;
	
	private int benefitLineDataTypeId;
	
	private String benefitLineBenefitValue;	
	
	private String benefitLineDataTypeIdAsString;
	
	private String dataTypeName;
	
	private String benefitLineReference;
	
	private String benefitLineReferenceCode;
	
	private String bnftLineNotesExist;

	private int benefitDefenitionId;

	private boolean benefitLineIsModified;
	
	private int benefitLineLevelId;
	
	private String hiddenReference;
	
	/**
	 * @return Returns the benefitLineLevelId.
	 */
	public int getBenefitLineLevelId() {
		return benefitLineLevelId;
	}
	/**
	 * @param benefitLineLevelId The benefitLineLevelId to set.
	 */
	public void setBenefitLineLevelId(int benefitLineLevelId) {
		this.benefitLineLevelId = benefitLineLevelId;
	}
	/**
	 * @return Returns the benefitDefenitionId.
	 */
	public int getBenefitDefenitionId() {
		return benefitDefenitionId;
	}
	/**
	 * @param benefitDefenitionId The benefitDefenitionId to set.
	 */
	public void setBenefitDefenitionId(int benefitDefenitionId) {
		this.benefitDefenitionId = benefitDefenitionId;
	}
	/**
	 * @return Returns the benefitLineBenefitValue.
	 */
	public String getBenefitLineBenefitValue() {
		return benefitLineBenefitValue;
	}
	/**
	 * @param benefitLineBenefitValue The benefitLineBenefitValue to set.
	 */
	public void setBenefitLineBenefitValue(String benefitLineBenefitValue) {
		this.benefitLineBenefitValue = benefitLineBenefitValue;
	}
	/**
	 * @return Returns the benefitLineDataTypeId.
	 */
	public int getBenefitLineDataTypeId() {
		return benefitLineDataTypeId;
	}
	/**
	 * @param benefitLineDataTypeId The benefitLineDataTypeId to set.
	 */
	public void setBenefitLineDataTypeId(int benefitLineDataTypeId) {
		this.benefitLineDataTypeId = benefitLineDataTypeId;
	}
	/**
	 * @return Returns the benefitLineId.
	 */
	public int getBenefitLineId() {
		return benefitLineId;
	}
	/**
	 * @param benefitLineId The benefitLineId to set.
	 */
	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
	}
	/**
	 * @return Returns the benefitLineIsModified.
	 */
	public boolean isBenefitLineIsModified() {
		return benefitLineIsModified;
	}
	/**
	 * @param benefitLineIsModified The benefitLineIsModified to set.
	 */
	public void setBenefitLineIsModified(boolean benefitLineIsModified) {
		this.benefitLineIsModified = benefitLineIsModified;
	}
	/**
	 * @return Returns the benefitLineReference.
	 */
	public String getBenefitLineReference() {
		return benefitLineReference;
	}
	/**
	 * @param benefitLineReference The benefitLineReference to set.
	 */
	public void setBenefitLineReference(String benefitLineReference) {
		this.benefitLineReference = benefitLineReference;
	}
	/**
	 * @return Returns the benefitLineReferenceCode.
	 */
	public String getBenefitLineReferenceCode() {
		return benefitLineReferenceCode;
	}
	/**
	 * @param benefitLineReferenceCode The benefitLineReferenceCode to set.
	 */
	public void setBenefitLineReferenceCode(String benefitLineReferenceCode) {
		this.benefitLineReferenceCode = benefitLineReferenceCode;
	}
	/**
	 * @return Returns the bnftLineNotesExist.
	 */
	public String getBnftLineNotesExist() {
		return bnftLineNotesExist;
	}
	/**
	 * @param bnftLineNotesExist The bnftLineNotesExist to set.
	 */
	public void setBnftLineNotesExist(String bnftLineNotesExist) {
		this.bnftLineNotesExist = bnftLineNotesExist;
	}
	/**
	 * @return Returns the dataTypeName.
	 */
	public String getDataTypeName() {
		return dataTypeName;
	}
	/**
	 * @param dataTypeName The dataTypeName to set.
	 */
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}
	/**
	 * @return Returns the pVA.
	 */
	public String getPVA() {
		return PVA;
	}
	/**
	 * @param pva The pVA to set.
	 */
	public void setPVA(String pva) {
		PVA = pva;
	}
	/**
	 * @return Returns the pvaCode.
	 */
	public String getPvaCode() {
		return pvaCode;
	}
	/**
	 * @param pvaCode The pvaCode to set.
	 */
	public void setPvaCode(String pvaCode) {
		this.pvaCode = pvaCode;
	}
	
	/**
	 * BenefitLevelVO
	 */
	
	private String benefitTerm;
	
	private String benefitQualifier;
	
	private List benefitLines;
	
	private int benefitDefinitionId;
	
	private int benefitLevelId;
	
	private String benefitLevelIdAsString;
	
	private String benefitLevelDesc;
	
	private String reference;
	
	private String referenceCode;
	
	private int benefitLevelSeq;
	
	private int dataTypeId;
	
	private String dataTypeAsString;
	
	private String benefitValue;
	
	private String benefitTermCode;
	
	private String benefitQualifierCode;
	
	private String benefitTermsAsString;

	private List benefitTerms;

	private String benefitQualifiersAsString;

	private List benefitQualifiers;

	private boolean isModified;
	
	private int benefitFrequency;
	
	private boolean frequencyZero;

	private int oldFrequencyValue;

	private boolean frequencyNumber;

	private boolean frequencyValueEmpty;
	
	/**
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * @param benefitDefinitionId The benefitDefinitionId to set.-
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}
	/**
	 * @return Returns the benefitFrequency.
	 */
	public int getBenefitFrequency() {
		return benefitFrequency;
	}
	/**
	 * @param benefitFrequency The benefitFrequency to set.-
	 */
	public void setBenefitFrequency(int benefitFrequency) {
		this.benefitFrequency = benefitFrequency;
	}
	/**
	 * @return Returns the benefitLevelDesc.
	 */
	public String getBenefitLevelDesc() {
		return benefitLevelDesc;
	}
	/**
	 * @param benefitLevelDesc The benefitLevelDesc to set.-
	 */
	public void setBenefitLevelDesc(String benefitLevelDesc) {
		this.benefitLevelDesc = benefitLevelDesc;
	}
	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * @param benefitLevelId The benefitLevelId to set.-
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}
	/**
	 * @return Returns the benefitLevelSeq.
	 */
	public int getBenefitLevelSeq() {
		return benefitLevelSeq;
	}
	/**
	 * @param benefitLevelSeq The benefitLevelSeq to set.-
	 */
	public void setBenefitLevelSeq(int benefitLevelSeq) {
		this.benefitLevelSeq = benefitLevelSeq;
	}
	
	
	/**
	 * @return Returns the benefitLines.
	 */
	public List getBenefitLines() {
		return benefitLines;
	}
	/**
	 * @param benefitLines The benefitLines to set.-
	 */
	public void setBenefitLines(List benefitLines) {
		this.benefitLines = benefitLines;
	}
	/**
	 * @return Returns the benefitQualifier.
	 */
	public String getBenefitQualifier() {
		return benefitQualifier;
	}
	/**
	 * @param benefitQualifier The benefitQualifier to set.-
	 */
	public void setBenefitQualifier(String benefitQualifier) {
		this.benefitQualifier = benefitQualifier;
	}
	/**
	 * @return Returns the benefitQualifierCode.
	 */
	public String getBenefitQualifierCode() {
		return benefitQualifierCode;
	}
	/**
	 * @param benefitQualifierCode The benefitQualifierCode to set.-
	 */
	public void setBenefitQualifierCode(String benefitQualifierCode) {
		this.benefitQualifierCode = benefitQualifierCode;
	}
	/**
	 * @return Returns the benefitQualifiers.
	 */
	public List getBenefitQualifiers() {
		return benefitQualifiers;
	}
	/**
	 * @param benefitQualifiers The benefitQualifiers to set.-
	 */
	public void setBenefitQualifiers(List benefitQualifiers) {
		this.benefitQualifiers = benefitQualifiers;
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
	 * @param benefitQualifiersAsString The benefitQualifiersAsString to set.-
	 */
	public void setBenefitQualifiersAsString(String benefitQualifiersAsString) {
		this.benefitQualifiersAsString = benefitQualifiersAsString;
	}
	/**
	 * @return Returns the benefitTerm.
	 */
	public String getBenefitTerm() {
		return benefitTerm;
	}
	/**
	 * @param benefitTerm The benefitTerm to set.-
	 */
	public void setBenefitTerm(String benefitTerm) {
		this.benefitTerm = benefitTerm;
	}
	/**
	 * @return Returns the benefitTermCode.
	 */
	public String getBenefitTermCode() {
		return benefitTermCode;
	}
	/**
	 * @param benefitTermCode The benefitTermCode to set.-
	 */
	public void setBenefitTermCode(String benefitTermCode) {
		this.benefitTermCode = benefitTermCode;
	}
	/**
	 * @return Returns the benefitTerms.
	 */
	public List getBenefitTerms() {
		return benefitTerms;
	}
	/**
	 * @param benefitTerms The benefitTerms to set.-
	 */
	public void setBenefitTerms(List benefitTerms) {
		this.benefitTerms = benefitTerms;
	}
	/**
	 * @return Returns the benefitTermsAsString.
	 */
	public String getBenefitTermsAsString() {
		StringBuffer termBuffer = new StringBuffer();
		StringBuffer termCodeBuffer = new StringBuffer();
		if (null != benefitTerms && !benefitTerms.isEmpty()) {
			BenefitTermVO benefitTermVO = null;
            for (int k = 0; k <benefitTerms.size(); k++) {
                benefitTermVO = (BenefitTermVO) benefitTerms.get(k);
                termBuffer.append( benefitTermVO.getBenefitTerm());
                termCodeBuffer.append("~");
                termCodeBuffer.append(benefitTermVO.getBenefitTerm());
                if(k < benefitTerms.size()-1){
                	termBuffer.append(",");
                }
            }
        }
		return termBuffer.toString();
	}
	/**
	 * @param benefitTermsAsString The benefitTermsAsString to set.-
	 */
	public void setBenefitTermsAsString(String benefitTermsAsString) {
		this.benefitTermsAsString = benefitTermsAsString;
	}
	/**
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}
	/**
	 * @param benefitValue The benefitValue to set.-
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}
	
	/**
	 * @return Returns the dataTypeId.
	 */
	public int getDataTypeId() {
		return dataTypeId;
	}
	/**
	 * @param dataTypeId The dataTypeId to set.-
	 */
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
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
	 * @return Returns the isModified.
	 */
	public boolean isModified() {
		return isModified;
	}
	/**
	 * @param isModified The isModified to set.
	 */
	public void setModified(boolean isModified) {
		this.isModified = isModified;
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
	 * @return Returns the frequencyZero.
	 */
	public boolean isFrequencyZero() {
		return benefitFrequency == 0;
	}
	/**
	 * @param frequencyZero The frequencyZero to set.
	 */
	public void setFrequencyZero(boolean frequencyZero) {
		this.frequencyZero = frequencyZero;
	}
	/**
	 * @return Returns the freequencyAsString.
	 */
	public String getFreequencyAsString() {
		return freequencyAsString;
	}
	/**
	 * @param freequencyAsString The freequencyAsString to set.
	 */
	public void setFreequencyAsString(String freequencyAsString) {
		this.freequencyAsString = freequencyAsString;
	}
}
