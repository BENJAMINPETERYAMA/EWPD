/*
 * ContractDFQuestionnaire.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContractDFQuestionnaire {
	
	private int dateSegmentSysId;
	private int componentSysId;
	private int benefitSysId;
	private String benefitName;
	private int benefitDefnId;
	private int benefitAdminId;
	private int cntrctTierSysId;
	private String tierCode;
	private int adminOptionAssnId; //ADMIN_LVL_OPT_ASSN_SYS_ID
	private int adminOptionId;
	private String adminOptionName;
	
	private int questionnaireId;
	private int parentQuestionnaireId;
	private String parentRequiredFlag;
	private int quesitonId;
	private String questionDesc;
	private String dataType;
	private String pva;
	private int answerId;
	private String answerDesc;
	private String referenceId;
	private String referenceDesc;
	private String noteText;
	
	private List children;
	private ContractDFQuestionnaire parent;
	private char dataFeedFlag;
	
	private boolean[] funcationalDomain;
	
	public ContractDFQuestionnaire() {
		funcationalDomain = new boolean[8];
	}

	public boolean isRootQuestion() {
		if(questionnaireId == parentQuestionnaireId)
			return true;
		else
			return false;
	}
	
	public boolean parentRequired() {
		if ( parentRequiredFlag != null && "Y".equals(parentRequiredFlag))
			return true;
		else
			return false;
	}
	
	public boolean isAdjudicating() {
		if(isAccumQuestion() ||  isEOBQuestion() || isAdjudConfig() ||isAdjudManul())
			return true;
		return false;
	}
	
	public void addChild(ContractDFQuestionnaire child) {
		if(this.children == null)
			this.children = new ArrayList();
		
		this.children.add(child);
	}
	
	public boolean haveChildren() {
		if(this.children != null && this.children.size() > 0)
			return true;
		return false;
	}
	
	public static final int ACCUM_DOMN = 0;
	public static final int ADJUD_CONFIG_DOMN = 1;
	public static final int EOB_DOMN = 2;
	public static final int ADJUD_MANUAL_DOMN = 3;
	public static final int INFO_DOMN = 4;
	public static final int CUSTOMER_CARE_INFO_DOMN = 5;
	public static final int ADJUD_INFO_DOMN = 6;
	public static final int PROD_CONFIG_DOMN = 7;
	
	public boolean isAccumQuestion() {
		return getDomainFlag(ACCUM_DOMN);
	}
	
	public boolean isAdjudConfig() {
		return getDomainFlag(ADJUD_CONFIG_DOMN);
	}
	
	public boolean isEOBQuestion() {
		return getDomainFlag(EOB_DOMN);
	}
	
	public boolean isAdjudManul() {
		return getDomainFlag(ADJUD_MANUAL_DOMN);
	}
	
	public boolean isCustomerCareInfo() {
		return getDomainFlag(CUSTOMER_CARE_INFO_DOMN);
	}
	
	public boolean isAdjudInfo() {
		return getDomainFlag(ADJUD_INFO_DOMN);
	}
	
	public boolean isProdConfig() {
		return getDomainFlag(PROD_CONFIG_DOMN);
	}
	
	private boolean getDomainFlag(int configType) {
		return funcationalDomain[configType];
	}
	
	/**
	 * @param funcationalDomains The funcationalDomains to set.
	 */
	public void setFuncationalDomains(String funcationalDomains) {
		
		if(funcationalDomains == null || funcationalDomains.length() != 8)
			throw new IllegalArgumentException ("Funcation Domain values is not correct. Length is not Eight");
		
		funcationalDomain[ACCUM_DOMN] = funcationalDomains.charAt(ACCUM_DOMN) == 'Y' ? true : false;
		funcationalDomain[ADJUD_CONFIG_DOMN] = funcationalDomains.charAt(ADJUD_CONFIG_DOMN) == 'Y' ? true : false;
		funcationalDomain[EOB_DOMN] = funcationalDomains.charAt(EOB_DOMN) == 'Y' ? true : false;
		funcationalDomain[ADJUD_MANUAL_DOMN] = funcationalDomains.charAt(ADJUD_MANUAL_DOMN) == 'Y' ? true : false;
		funcationalDomain[INFO_DOMN] = funcationalDomains.charAt(INFO_DOMN) == 'Y' ? true : false;
		funcationalDomain[CUSTOMER_CARE_INFO_DOMN] = funcationalDomains.charAt(CUSTOMER_CARE_INFO_DOMN) == 'Y' ? true : false;
		funcationalDomain[ADJUD_INFO_DOMN] = funcationalDomains.charAt(ADJUD_INFO_DOMN) == 'Y' ? true : false;
		funcationalDomain[PROD_CONFIG_DOMN] = funcationalDomains.charAt(PROD_CONFIG_DOMN) == 'Y' ? true : false;
	}	
	
	
	public void deriveFeedFlag() {
		char feedFlag = 'N';
		
		if(haveChildren()) {
			boolean childrenCustomerCareInfo = false;
			boolean childrenAdjudInfo = false;
			boolean parentRequiredForChildren = false;
			
			List chidren = getChildren();
			for (Iterator iterator = chidren.iterator(); iterator.hasNext();) {
				ContractDFQuestionnaire child = (ContractDFQuestionnaire) iterator.next();
				
				if(child.parentRequired()) 
					parentRequiredForChildren = true;
				
				if(child.isCustomerCareInfo()) 
					childrenCustomerCareInfo = true;
				
				if(child.isAdjudInfo()) 
					childrenAdjudInfo = true;
				
				child.deriveFeedFlag();
			}
			
			if(parentRequiredForChildren) {
				if(isAdjudicating() && isCustomerCareInfo() && isAdjudInfo()) {
					feedFlag = 'B';
				}else if (isAdjudicating() && isCustomerCareInfo()) {
					feedFlag = 'T';
				}else if (isAdjudicating() && isAdjudInfo()) {
					feedFlag = 'P';
				}else if (isAdjudicating()) {
					feedFlag = 'A';
				}else if (isCustomerCareInfo() && isAdjudInfo()) {
					feedFlag = 'I';
				}else if (isCustomerCareInfo()) {
					feedFlag = 'C';
				}else if (isAdjudInfo()) {
					feedFlag = 'S';
				}else if (isProdConfig()) {
					feedFlag = 'B';
				}
			} else {
				if(isAdjudicating()) {
					if(isAdjudicating() && (isCustomerCareInfo() || isAdjudInfo())) {
						if(childrenCustomerCareInfo && childrenAdjudInfo) {
							feedFlag = 'A';
						}else if (childrenCustomerCareInfo) {
							feedFlag = 'P';
						}else if (childrenAdjudInfo) {
							feedFlag = 'T';
						}else {
							feedFlag = 'B';
						}
					}else if(isAdjudicating()) {
						feedFlag = 'A';
					}
				}else {
					if(childrenCustomerCareInfo || childrenAdjudInfo || isProdConfig()) {
						feedFlag = 'N';
					}else {
						if(isCustomerCareInfo() && isAdjudInfo()) {
							feedFlag = 'I';
						}else if (isCustomerCareInfo()) {
							feedFlag = 'C';
						}else if (isAdjudInfo()) {
							feedFlag = 'S';
						}
					}
				}
			}
		} else {
			if(isAdjudicating() || isCustomerCareInfo() || isAdjudInfo()) {
				if(isAdjudicating() && isCustomerCareInfo() && isAdjudInfo()) {
					feedFlag = 'B';
				}else if (isAdjudicating() && isCustomerCareInfo()) {
					feedFlag = 'T';
				}else if (isAdjudicating() && isAdjudInfo()) {
					feedFlag = 'P';
				}else if (isAdjudicating()) {
					if ( isRootQuestion() && isAdjudConfig()) {
						feedFlag = 'B';
					} else {
						feedFlag = 'A';
					}
				}else if (isCustomerCareInfo() && isAdjudInfo()) {
					feedFlag = 'I';
				}else if (isCustomerCareInfo()) {
					feedFlag = 'C';
				}else if (isAdjudInfo()) {
					feedFlag = 'S';
				}
			} else {
				if(isRootQuestion())
					feedFlag = 'B';
				else
					feedFlag = 'N';
			}
		}
		setDataFeedFlag(feedFlag);
	}
	
	/**
	 * @return Returns the dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}

	/**
	 * @param dateSegmentSysId The dateSegmentSysId to set.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}

	/**
	 * @return Returns the componentSysId.
	 */
	public int getComponentSysId() {
		return componentSysId;
	}

	/**
	 * @param componentSysId The componentSysId to set.
	 */
	public void setComponentSysId(int componentSysId) {
		this.componentSysId = componentSysId;
	}

	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}

	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}

	/**
	 * @return Returns the benefitDefnId.
	 */
	public int getBenefitDefnId() {
		return benefitDefnId;
	}

	/**
	 * @param benefitDefnId The benefitDefnId to set.
	 */
	public void setBenefitDefnId(int benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}

	/**
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}

	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
	}

	/**
	 * @return Returns the cntrctTierSysId.
	 */
	public int getCntrctTierSysId() {
		return cntrctTierSysId;
	}

	/**
	 * @param cntrctTierSysId The cntrctTierSysId to set.
	 */
	public void setCntrctTierSysId(int cntrctTierSysId) {
		this.cntrctTierSysId = cntrctTierSysId;
	}

	/**
	 * @return Returns the tierCode.
	 */
	public String getTierCode() {
		return tierCode;
	}

	/**
	 * @param tierCode The tierCode to set.
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}

	/**
	 * @return Returns the adminOptionAssnId.
	 */
	public int getAdminOptionAssnId() {
		return adminOptionAssnId;
	}

	/**
	 * @param adminOptionAssnId The adminOptionAssnId to set.
	 */
	public void setAdminOptionAssnId(int adminOptionAssnId) {
		this.adminOptionAssnId = adminOptionAssnId;
	}

	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}

	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}

	/**
	 * @return Returns the questionnaireId.
	 */
	public int getQuestionnaireId() {
		return questionnaireId;
	}

	/**
	 * @param questionnaireId The questionnaireId to set.
	 */
	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	/**
	 * @return Returns the parentQuestionnaireId.
	 */
	public int getParentQuestionnaireId() {
		return parentQuestionnaireId;
	}

	/**
	 * @param parentQuestionnaireId The parentQuestionnaireId to set.
	 */
	public void setParentQuestionnaireId(int parentQuestionnaireId) {
		this.parentQuestionnaireId = parentQuestionnaireId;
	}

	/**
	 * @return Returns the parentRequiredFlag.
	 */
	public String getParentRequiredFlag() {
		return parentRequiredFlag;
	}

	/**
	 * @param parentRequiredFlag The parentRequiredFlag to set.
	 */
	public void setParentRequiredFlag(String parentRequiredFlag) {
		this.parentRequiredFlag = parentRequiredFlag;
	}

	/**
	 * @return Returns the quesitonId.
	 */
	public int getQuesitonId() {
		return quesitonId;
	}

	/**
	 * @param quesitonId The quesitonId to set.
	 */
	public void setQuesitonId(int quesitonId) {
		this.quesitonId = quesitonId;
	}

	/**
	 * @return Returns the questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}

	/**
	 * @param questionDesc The questionDesc to set.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return Returns the referenceId.
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}

	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}

	/**
	 * @return Returns the noteText.
	 */
	public String getNoteText() {
		return noteText;
	}

	/**
	 * @param noteText The noteText to set.
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	/**
	 * @return Returns the children.
	 */
	public List getChildren() {
		return children;
	}

	/**
	 * @param children The children to set.
	 */
	public void setChildren(List children) {
		this.children = children;
	}

	/**
	 * @return Returns the parent.
	 */
	public ContractDFQuestionnaire getParent() {
		return parent;
	}

	/**
	 * @param parent The parent to set.
	 */
	public void setParent(ContractDFQuestionnaire parent) {
		this.parent = parent;
	}

	/**
	 * @return Returns the dataFeedFlag.
	 */
	public char getDataFeedFlag() {
		return dataFeedFlag;
	}

	/**
	 * @param dataFeedFlag The dataFeedFlag to set.
	 */
	public void setDataFeedFlag(char dataFeedFlag) {
		this.dataFeedFlag = dataFeedFlag;
	}

	/**
	 * @return Returns the answerDesc.
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}

	/**
	 * @param answerDesc The answerDesc to set.
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	/**
	 * @return Returns the adminOptionName.
	 */
	public String getAdminOptionName() {
		return adminOptionName;
	}

	/**
	 * @param adminOptionName The adminOptionName to set.
	 */
	public void setAdminOptionName(String adminOptionName) {
		this.adminOptionName = adminOptionName;
	}

	/**
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}

	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
}
