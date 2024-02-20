package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.util.BenefitTierUtil;

public class BenefitTierDefinition implements Cloneable{

	private int benefitTierDefinitionSysId;
	
	private String benefitTierDefinitionName;
	
	private List benefitTiers;

	private String dataType;
	
	/*CARS|AM2|POS|{*/
	private String productFamily="";
	/*CARS|AM2|POS|}*/
	/*CARS|AM2|POS|{*/
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
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getBenefitTierDefinitionName() {
		return benefitTierDefinitionName;
	}

	public void setBenefitTierDefinitionName(String benefitTierDefinitionName) {
		this.benefitTierDefinitionName = benefitTierDefinitionName;
	}

	public int getBenefitTierDefinitionSysId() {
		return benefitTierDefinitionSysId;
	}

	public void setBenefitTierDefinitionSysId(int benefitTierDefinitionSysId) {
		this.benefitTierDefinitionSysId = benefitTierDefinitionSysId;
	}

	public List getBenefitTiers() {
		return benefitTiers;
	}

	public void setBenefitTiers(List benefitTiers) {
		this.benefitTiers = benefitTiers;
	}

	public BenefitTierDefinition(int benefitTierDefinitionSysId) {
		super();
		this.benefitTierDefinitionSysId = benefitTierDefinitionSysId;
	}
	
	public BenefitTierDefinition() {
		super();
	}
	
	public List getTiersInSequence() {
		List list = new ArrayList(getBenefitTiers());
		Collections.sort(list);
		return list;
	}
	
	/**
	 * Check if the ranges are overlapping. If the argument is given as true, same range wiil be allowed. </br>
	 * For eg:- 10 -20 and 11 - 30 are overlapping</br>
	 * 10 - 20 and 21 - 30 are not overlapping. </br>
	 * If same range is allowed, 10 - 20 and 10 - 20 are not overlapping.
	 * @param allowSameRange True if Same Range should be allowed.
	 * @return Returns a list of overlapping tiers.
	 */
	public List validateRangeOverlapping(boolean allowSameRange) {
		List resultList = new ArrayList();
		List orderedTiers = getTiersInSequence();
		if (org.apache.log4j.Logger.getLogger(BenefitTierDefinition.class.getName()).isDebugEnabled()){
			Logger.logDebug(orderedTiers);
		}
		if(orderedTiers != null && orderedTiers.size() >= 2 ) {
			for (int i = 0; i < orderedTiers.size() - 1; i++) {
				BenefitTier tierFirst = (BenefitTier)orderedTiers.get(i);
				BenefitTier tierSecond = (BenefitTier)orderedTiers.get(i + 1);
				if(allowSameRange && checkIfSameRange(tierFirst, tierSecond)){
					continue;
				}
				int result = BenefitTierUtil.compareCriteria(tierFirst.getEndCriteria(), tierSecond.getStartCriteria(), dataType);
				int result_wclc = 0;
				int result_wclcopp = 0;
				if(tierFirst.getBenefitTierCriteriaList().size() == 4){
					result_wclc = BenefitTierUtil.compareCriteria(tierFirst.getEndCriteria_wclc(), tierSecond.getStartCriteria_wclc(), dataType);
					if(result == 1 && result_wclc == 1 && 
							(BenefitTierUtil.compareCriteria(tierFirst.getStartCriteria(), tierSecond.getStartCriteria(), dataType) == 0) && 
							(BenefitTierUtil.compareCriteria(tierFirst.getEndCriteria(), tierSecond.getEndCriteria(), dataType) == 0)){
						result_wclc = BenefitTierUtil.compareCriteria(tierSecond.getEndCriteria_wclc(), tierFirst.getStartCriteria_wclc(), dataType);
					}
					else if (result == 1 && result_wclc == 0) {
						result_wclc = 0;
					}
				}
				
				if(result >= 0 && result_wclc >= 0) {
					resultList.add(tierFirst);
					resultList.add(tierSecond);
				}
			}
		}
		return resultList;
	}
	
	private boolean checkIfSameRange(BenefitTier tierFirst, BenefitTier tierSecond) {
		List orderedTiers = getTiersInSequence();
		List criteriaList = ((BenefitTier)orderedTiers.get(0)).getCriteriaListInSequence();
		int startMatch = BenefitTierUtil.compareCriteria(tierFirst.getStartCriteria(),tierSecond.getStartCriteria(), dataType);
		int endMatch = BenefitTierUtil.compareCriteria(tierFirst.getEndCriteria(),tierSecond.getEndCriteria(), dataType);
		int startMatch_wclc = 0;
		int endMatch_wclc = 0;
		if(criteriaList != null && criteriaList.size() > 2){
			startMatch_wclc = BenefitTierUtil.compareCriteria(tierFirst.getStartCriteria_wclc(),tierSecond.getStartCriteria_wclc(), dataType);
			endMatch_wclc = BenefitTierUtil.compareCriteria(tierFirst.getEndCriteria_wclc(),tierSecond.getEndCriteria_wclc(), dataType);
		}
		
		if((startMatch == 0 && endMatch == 0) && (startMatch_wclc == 0 && endMatch_wclc == 0)) {
			return true;
		}
		return false;
	}

	public boolean isTierExists(String startCriteriaValue, String endCriteriaValue) {
		startCriteriaValue = startCriteriaValue.trim();
		endCriteriaValue = endCriteriaValue.trim();
		for (Iterator iter = benefitTiers.iterator(); iter.hasNext();) {
			BenefitTier benefitTier = (BenefitTier) iter.next();
			if(benefitTier.getStartCriteria().getBenefitTierCriteriaValue().equals(startCriteriaValue)
					&& benefitTier.getEndCriteria().getBenefitTierCriteriaValue().equals(endCriteriaValue)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isTierExists_wclc(String startCriteriaValue, String endCriteriaValue, String startCriteriaValue_wclc, String endCriteriaValue_wclc ) {
		startCriteriaValue = startCriteriaValue.trim();
		endCriteriaValue = endCriteriaValue.trim();
		startCriteriaValue_wclc = startCriteriaValue_wclc.trim();
		endCriteriaValue_wclc = endCriteriaValue_wclc.trim();
		for (Iterator iter = benefitTiers.iterator(); iter.hasNext();) {
			BenefitTier benefitTier = (BenefitTier) iter.next();
			if((benefitTier.getStartCriteria().getBenefitTierCriteriaValue().equals(startCriteriaValue)
					&& benefitTier.getEndCriteria().getBenefitTierCriteriaValue().equals(endCriteriaValue)) && (benefitTier.getStartCriteria_wclc().getBenefitTierCriteriaValue().equals(startCriteriaValue_wclc)
							&& benefitTier.getEndCriteria_wclc().getBenefitTierCriteriaValue().equals(endCriteriaValue_wclc))) {
				return true;
			}
		}
		return false;
	}
	
	public Object clone() {
	    BenefitTierDefinition benefitTierDefn = null;
        try {
            benefitTierDefn = (BenefitTierDefinition)super.clone();           
        }
        catch (CloneNotSupportedException e) {
           return null;
        }
        List benefitTiersList = benefitTierDefn.benefitTiers;
        List clonedBenefitTiersList = new ArrayList();
        for(int i=0;i<benefitTiersList.size();i++){
            BenefitTier benefitTier = (BenefitTier)benefitTiersList.get(i);
            BenefitTier clonedBenefitTier = (BenefitTier)benefitTier.clone();
            clonedBenefitTiersList.add(clonedBenefitTier);
        }
        benefitTierDefn.setBenefitTiers(clonedBenefitTiersList);
        return benefitTierDefn;
    }

	public int compareTo(Object benefitDefinition) 
	{
		BenefitTierDefinition benefitTierDefinition = (BenefitTierDefinition)benefitDefinition;
		   // WAS 6.0 Migration changes - The method compareTo(String) in the type String is not applicable for the arguments (Object)in 1.6 JSE Standard 
		return this.benefitTierDefinitionName.compareTo(benefitTierDefinition.getBenefitTierDefinitionName());
	}

	public boolean equals(Object benefitDefinition){
		BenefitTierDefinition benefitTierDefinition = (BenefitTierDefinition)benefitDefinition;
	   	return this.benefitTierDefinitionName.equals(benefitTierDefinition.getBenefitTierDefinitionName());
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer(benefitTierDefinitionName);
		buffer.append(" {");
		buffer.append("Data type: "+this.getDataType());
		buffer.append(" }");
		return buffer.toString();
	}

	public List validateRangeOverlapping() {
		return validateRangeOverlapping(false);
	}

	
	 


}
