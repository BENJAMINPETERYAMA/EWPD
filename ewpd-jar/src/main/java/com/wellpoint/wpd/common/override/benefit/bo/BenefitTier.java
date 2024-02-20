/*
 * Created on Aug 6, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.util.BenefitTierUtil;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitTier implements Comparable,Cloneable{
	
	private int benefitTierSysId;
	
	private List benefitTierCriteriaList;
	
	private BenefitTierDefinition benefitTierDefinition;
		
	private int displaySequenceNo;
	
	private String criteriaIndicator;
	
	private List questionnaireList;

	private List termList; //CARS:AM2
	
	private String termsQuery = ""; //CARS:AM2
	//CARS:AM2:START
	private List adminMethods;
	/*CARS|AM2|POS|{*/
	private Map termsPVAMap = new HashMap(0);
	private String productFamily="";
	private boolean isPOS=false;
	
	
	/*CARS|AM2|POS|}*/
	
	/*CARS|AM2|POS|{*/
	public Map getTermsPVAMap() {
		return termsPVAMap;
	}
	public void setTermsPVAMap(Map termsPVAMap) {
		this.termsPVAMap = termsPVAMap;
	}		
	public boolean isPOS() {
		return isPOS;
	}
	public void setPOS(boolean isPOS) {
		this.isPOS = isPOS;
	}
	
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/*CARS|AM2|POS|}*/
	/**
	 * @return Returns the adminMethods.
	 */
	public List getAdminMethods() {
		return adminMethods;
	}
	/**
	 * @param adminMethods The adminMethods to set.
	 */
	public void setAdminMethods(List adminMethods) {
		this.adminMethods = adminMethods;
	}
	/**
	 * @return Returns the termsQuery.
	 */
	public String getTermsQuery() {
		return termsQuery;
	}
	/**
	 * @param termsQuery The termsQuery to set.
	 */
	public void setTermsQuery(String termsQuery) {
		this.termsQuery = termsQuery;
	}
	/**
	 * @return Returns the termList.
	 */
	public List getTermList() {
		return termList;
	}
	/**
	 * @param termList The termList to set.
	 */
	public void setTermList(List termList) {
		this.termList = termList;
	}
	//CARS:AM2:END
	public List getBenefitTierCriteriaList() {
		return benefitTierCriteriaList;
	}
		
	public void setBenefitTierCriteriaList(List benefitTierCriteriaList) {
		this.benefitTierCriteriaList = benefitTierCriteriaList;
	}

	public BenefitTierDefinition getBenefitTierDefinition() {
		return benefitTierDefinition;
	}

	public void setBenefitTierDefinition(BenefitTierDefinition benefitTierDefinition) {
		this.benefitTierDefinition = benefitTierDefinition;
	}

	public int getBenefitTierSysId() {
		return benefitTierSysId;
	}

	public void setBenefitTierSysId(int benefitTierSysId) {
		this.benefitTierSysId = benefitTierSysId;
	}
	
	
	/**
	 * @param benefitTierSysId
	 */
	public BenefitTier(int benefitTierSysId) {
		super();
		this.benefitTierSysId = benefitTierSysId;
	}
	
	
	public BenefitTier(BenefitTierDefinition benefitTierDefinition) {
		this.benefitTierDefinition = benefitTierDefinition;
	}
	
	/**
	 * @return Returns the questionnaireList.
	 */
	public List getQuestionnaireList() {
		return questionnaireList;
	}
	/**
	 * @param questionnaireList The questionnaireList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
	
	public int compareTo(Object benefitTier) {
		/*BenefitTierCriteria benefitTierCriteriaArg = (BenefitTierCriteria)benefitTierCriteriaParam; 
		BenefitTierCriteria benefitTierCriteria = (BenefitTierCriteria)benefitTierCriteriaList.get(0);*/
		BenefitTier benefitTierArg = (BenefitTier)benefitTier; 
		
		BenefitTierCriteria benefitTierArgStartCriteria =  (BenefitTierCriteria)benefitTierArg.getStartCriteria();		
		BenefitTierCriteria benefitTierStartCriteria =  (BenefitTierCriteria)this.getStartCriteria();
		BenefitTierCriteria benefitTierArgStartCriteria_wclc = null;
		BenefitTierCriteria benefitTierStartCriteria_wclc = null;
		int result = 0;
		int result_wclc = 0;
		if (this.getBenefitTierCriteriaList().size() > 2){
			benefitTierArgStartCriteria_wclc = (BenefitTierCriteria)benefitTierArg.getStartCriteria_wclc();
			benefitTierStartCriteria_wclc = (BenefitTierCriteria)this.getStartCriteria_wclc();
			result_wclc = BenefitTierUtil.compareCriteria(benefitTierStartCriteria_wclc,benefitTierArgStartCriteria_wclc,this.getBenefitTierDefinition().getDataType());
		}
		result = BenefitTierUtil.compareCriteria(benefitTierStartCriteria,benefitTierArgStartCriteria,this.getBenefitTierDefinition().getDataType());
		if(benefitTierArgStartCriteria_wclc == null )
			return result;
		else
			return result == 0 ? result_wclc : result;
	}
	
	public boolean isRangeTier(){
		if(benefitTierCriteriaList != null && benefitTierCriteriaList.size() == 2
				&& ((BenefitTierCriteria)benefitTierCriteriaList.get(0)).isRangeCriteria())
			return true;
		else
			return false;
	}
	
	public List getCriteriaListInSequence() {
		List list = new ArrayList(getBenefitTierCriteriaList());
		Collections.sort(list);
		return list;
	}
	/**
	 * Compares start range and end range.
	 * @return False for invalid range.
	 */
	public boolean isRangeValid() {
		
		BenefitTierCriteria startCriteria = getStartCriteria();
		BenefitTierCriteria endCriteria = getEndCriteria();
		BenefitTierCriteria startCriteria_wclc = null;
		BenefitTierCriteria endCriteria_wclc = null;
		List rangeList = getCriteriaListInSequence();
		int size = rangeList.size();
		if(size == 4){
			startCriteria_wclc = getStartCriteria_wclc();
			endCriteria_wclc = getEndCriteria_wclc();
		}
		if(BenefitTierUtil.compareCriteria(startCriteria, endCriteria, getBenefitTierDefinition().getDataType()) > 0)
			return false;
		else if(size == 4 && BenefitTierUtil.compareCriteria(startCriteria_wclc, endCriteria_wclc, getBenefitTierDefinition().getDataType()) > 0)
			return false;
		return true;
	}
	
	public BenefitTierCriteria getStartCriteria() {
		List rangeList = getCriteriaListInSequence();
		int size = rangeList.size();
		if(size == 0) return null;
		return (BenefitTierCriteria)rangeList.get(0);
	}
	
	public BenefitTierCriteria getEndCriteria() {
		List rangeList = getCriteriaListInSequence();
		int size = rangeList.size();
		if(size == 0) return null;
		if(size <= 2)
			return (BenefitTierCriteria)rangeList.get(size -1);
		return (BenefitTierCriteria)rangeList.get(1);
	}
	
	public BenefitTierCriteria getStartCriteria_wclc() {
		List rangeList = getCriteriaListInSequence();
		int size = rangeList.size();
		if(size == 0) return null;
		return (BenefitTierCriteria)rangeList.get(2);
	}
	
	public BenefitTierCriteria getEndCriteria_wclc() {
		List rangeList = getCriteriaListInSequence();
		int size = rangeList.size();
		if(size == 0) return null;
		return (BenefitTierCriteria)rangeList.get(3);
	}
	
	/**
	 * @return Returns the criteriaIndicator.
	 */
	public String getCriteriaIndicator() {
		return criteriaIndicator;
	}
	/**
	 * @param criteriaIndicator The criteriaIndicator to set.
	 */
	public void setCriteriaIndicator(String criteriaIndicator) {
		this.criteriaIndicator = criteriaIndicator;
	}
	/**
	 * @return Returns the displaySequenceNo.
	 */
	public int getDisplaySequenceNo() {
		return displaySequenceNo;
	}
	/**
	 * @param displaySequenceNo The displaySequenceNo to set.
	 */
	public void setDisplaySequenceNo(int displaySequenceNo) {
		this.displaySequenceNo = displaySequenceNo;
	}
	
	public Object clone() {
	    BenefitTier benefitTier = null;
        try {
            benefitTier = (BenefitTier)super.clone();           
        }
        catch (CloneNotSupportedException e) {
           return null;
        }
        List benefitTiersCriteriasList = benefitTier.benefitTierCriteriaList;
        List clonedBenefitTiersCriteriasList = new ArrayList();
        for(int i=0;i<benefitTiersCriteriasList.size();i++){
            BenefitTierCriteria benefitTierCriteria = (BenefitTierCriteria)benefitTiersCriteriasList.get(i);
            BenefitTierCriteria clonedBenefitTierCriteria = (BenefitTierCriteria)benefitTierCriteria.clone();
            clonedBenefitTiersCriteriasList.add(clonedBenefitTierCriteria);
        }
        benefitTier.setBenefitTierCriteriaList(clonedBenefitTiersCriteriasList);
        return benefitTier;
    }
	
	public boolean equals(Object benefitTier){
		BenefitTier benefitTierArg = (BenefitTier)benefitTier; 
		
		BenefitTierCriteria benefitTierArgStartCriteria =  (BenefitTierCriteria)benefitTierArg.getStartCriteria();		
		BenefitTierCriteria benefitTierStartCriteria =  (BenefitTierCriteria)this.getStartCriteria();
		return BenefitTierUtil.checkSameCriteria(benefitTierStartCriteria,benefitTierArgStartCriteria,this.getBenefitTierDefinition().getDataType());
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer("benefitTierSysId "+benefitTierSysId);
		buffer.append(" displaySequenceNo "+displaySequenceNo);
		buffer.append(" criteriaIndicator "+criteriaIndicator);
		String def = null;
		if(null != benefitTierDefinition ) 
				def = benefitTierDefinition.toString();
		buffer.append(" \n{");
		buffer.append("Def :"+def);
		buffer.append(" }");
		buffer.append(" [");
		for (Iterator iter = benefitTierCriteriaList.iterator(); iter.hasNext();) {
			BenefitTierCriteria element = (BenefitTierCriteria) iter.next();
			buffer.append("Criteria: "+element.toString()+"\n");
		}
		buffer.append(" }");
		return buffer.toString();
	}
	
	/**
	 * @return Returns the productFamily.
	 */
	
}
