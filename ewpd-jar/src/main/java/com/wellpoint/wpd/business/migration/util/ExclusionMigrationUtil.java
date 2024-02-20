/*
 * ExclusionMigrationUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.migration.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyVariable;
import com.wellpoint.wpd.common.legacycontract.bo.LimitClassExclusion;
import com.wellpoint.wpd.common.legacycontract.bo.ServiceClassExclusion;
import com.wellpoint.wpd.common.legacycontract.bo.ServiceCodeExclusion;
import com.wellpoint.wpd.common.legacycontract.bo.SpecialityCodeExclusion;
import com.wellpoint.wpd.common.migration.bo.ProductRule;
import com.wellpoint.wpd.common.migration.bo.Rule;
import com.wellpoint.wpd.common.migration.bo.RuleSequence;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class ExclusionMigrationUtil {
	
	private static final String GROUP_IND = "Y";
	
	private static final int SRV_CLS_EXCLN = 1;
	private static final int LMT_CLS_EXCLN = 2;
	private static final int SRV_CODE_EXCLN = 3;
	private static final int SPEC_CODE_EXCLN = 4;
	
	private static final int RULE_TYPE_POS = 0;
	private static final int RULE_PVA_POS = 1;
	
	private int totalExclusionCount=0;
	// Set this flag to true for getting details to be logged.
	private boolean debug = false;
	// Inputs
	private List ruleDetails = new ArrayList();
	private List serviceClassExclusions = new ArrayList();
	private List limitClassExclusions = new ArrayList();
	private List serviceCodeExclusions = new ArrayList();
	private List specialityCodeExclusions = new ArrayList();
	private List codedVariables = new ArrayList();
	private List generatedRules = new ArrayList();	
	private List validRules = new ArrayList();
	
	// Results
	// List of Rules(ProductRule Objects) matching to exclusion variables.
	private List exclusionMappingRules = new ArrayList();
	// List of Rules(Rule Ids) matching to legacy exclusions. 
	private List variableMappingRules = new ArrayList();
	// Final List of Rules(Product Rule objects)
	private List resultMappingRules = new ArrayList();
	
	// If number of valid rules is greater than this value, the combination
	// Logic will be skipped and all valid rules will be mapped. This is retrieved 
	// From property file.
	private static int MAX_VAL_RULE_SIZE_VAL;
	private static final String BOUNDARIES_RESOURCE_FILE = "boundaries";
	private static final String MAX_VALID_RULES_ID = "valid.rules.max";
	
	static {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BOUNDARIES_RESOURCE_FILE,Locale.getDefault());
		String rulesLimit = resourceBundle.getString(MAX_VALID_RULES_ID);
		MAX_VAL_RULE_SIZE_VAL = Integer.parseInt(rulesLimit);
	}
	
	public ExclusionMigrationUtil(LegacyContract contract, List rules, List genertedRules){
		this.serviceCodeExclusions = contract.getServiceCodeExclusions();
		this.serviceClassExclusions = contract.getServiceClassExclusions();
		this.limitClassExclusions = contract.getLimitClassExclusions();
		this.specialityCodeExclusions = contract.getSpecialityCodeExclusions();
		this.codedVariables = contract.getCodedVariables();
		this.ruleDetails = rules;
		this.generatedRules = genertedRules;
	}
	/**
	 * inner rule mapping class
	 * @author US Technology Resources - www.ustri.com <br />
	 * @version $Id: $
	 */
	public class RuleMapping {
		String ruleId;
		int[] exclusionInd; 
		/**
		 * 
		 * @param ruleId
		 * @param exclusionInd
		 */
		RuleMapping(String ruleId, int[] exclusionInd){
			this.ruleId = ruleId;
			this.exclusionInd = exclusionInd;
		}
		/**
		 * 
		 * @see java.lang.Object#toString()
		 * @return
		 */
		public String toString() {
			StringBuffer buffer = new StringBuffer(30);
			buffer.append("Rule Id = ").append(this.ruleId).append(", Indicators = [");
			for(int i=0; i<exclusionInd.length; i++) {
				buffer.append(exclusionInd[i]);
			}
			buffer.append("]");
			return buffer.toString();
		}
		/**
		 * 
		 * @return
		 */
		public String getRuleId() {
			return ruleId;
		}
		/**
		 * 
		 * @return
		 */
		public int[] getExclusionInd() {
			return exclusionInd;
		}
	}

	/**
	 * 
	 *
	 */
	private void mapCodedVariables(){
		List result = new ArrayList();
		LegacyVariable variable;
		ProductRule prodRule;
		Iterator iterator2;
		ProductRule tempRule;
		if(codedVariables != null)
		{
			for (Iterator iterator = codedVariables.iterator(); iterator.hasNext();) {
				variable = (LegacyVariable) iterator.next();
				if(isExclusionVariable(variable)) {
					if(generatedRules != null)
					{
						for (iterator2 = generatedRules.iterator(); iterator2.hasNext();) {
							prodRule = (ProductRule) iterator2.next();
							if(isVariableRuleMatching(variable,prodRule)) {
								tempRule = new ProductRule(prodRule);
								tempRule.setValue(variable.getCodedValue());
								result.add(tempRule);
							}
						}
					}
				}
			}
		}
		variableMappingRules = result;
	}
	/**
	 * 
	 * @param variable
	 * @return
	 */
	private boolean isExclusionVariable(LegacyVariable variable) {
		String varibleId = variable.getVariableId();
		if (varibleId != null && varibleId.length() > 2) {
			char legacyRuleTypeInd = varibleId.charAt(RULE_TYPE_POS);
			if(legacyRuleTypeInd == BusinessConstants.SPECIAL_CHAR_RULE_TYPE_EXCLUSION || 
					legacyRuleTypeInd == BusinessConstants.SPECIAL_CHAR_RULE_TYPE_UM ||
					legacyRuleTypeInd == BusinessConstants.SPECIAL_CHAR_RULE_TYPE_DENIAL ||
					legacyRuleTypeInd == BusinessConstants.SPECIAL_CHAR_RULE_TYPE_PNR) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param variable
	 * @param productRule
	 * @return
	 */
	private boolean isVariableRuleMatching(LegacyVariable variable, ProductRule productRule){
		String varibleId = variable.getVariableId();
		char legacyRuleTypeInd = varibleId.charAt(RULE_TYPE_POS);
		char legacyPVA = varibleId.charAt(RULE_PVA_POS);
		
		String ewpdRuleId = productRule.getRuleId();
		String ewpdGeneratedId = productRule.getGeneratedId();
		char ewpdRuleTypeInd = ewpdGeneratedId.charAt(RULE_TYPE_POS);
		char ewpdPVA = ewpdGeneratedId.charAt(RULE_PVA_POS);
		
		if(varibleId.substring(2).equals(ewpdRuleId)) {
			if(legacyRuleTypeInd == ewpdRuleTypeInd && legacyPVA == ewpdPVA ) 
				return true;
		}
		return false;
	}
	/**
	 * 
	 * @return
	 */
	public List generateRuleMapping(){
		totalExclusionCount = getTotalExlnCount();
		loadValidRules();
		if(debug) {
			Logger.logInfo("Total exclusions  = " + totalExclusionCount );			
			Logger.logInfo("Valid rules are");
			if(validRules != null)
			{
				for (Iterator iterator = validRules.iterator(); iterator.hasNext();) {
					RuleMapping name = (RuleMapping) iterator.next();
					Logger.logInfo(name);
				}	
			}
		}
		mapCodedVariables();
		this.exclusionMappingRules = getExlusionMapping();
		formateResultMapping();
		return resultMappingRules;
	}
	/**
	 * 
	 *
	 */
	private void formateResultMapping(){
		Set set = new HashSet();
		set.addAll(variableMappingRules);
		resultMappingRules = new ArrayList();
		resultMappingRules.addAll(set);
		
		String ruleId;	
		List list;
		Iterator iterator2;
		int index ;
		ProductRule productRule;
		if(exclusionMappingRules != null)
		{
			for (Iterator iterator = exclusionMappingRules.iterator(); iterator.hasNext();) {
				ruleId = (String) iterator.next();
				list = getMatchingProductRules(ruleId);
				if(list != null)
				{
					for (iterator2 = list.iterator(); iterator2
							.hasNext();) {
						productRule = (ProductRule) iterator2.next();
						index = resultMappingRules.indexOf(productRule);
						if(index == -1) {
							resultMappingRules.add(productRule);
						}
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param ruleId
	 * @return
	 */
	private List getMatchingProductRules(String ruleId) {
		List list = new ArrayList();
		ProductRule productRule ;
		if(generatedRules != null)
		{
			for (Iterator iterator = generatedRules.iterator(); iterator.hasNext();) {
				productRule = (ProductRule) iterator.next();
				if(ruleId.equals(productRule.getRuleId()))
					list.add(productRule);
			}
		}
		return list;
	}
	/**
	 * 
	 * @return
	 */
	private int getTotalExlnCount() {
		
		int cnt = 0;
		if(serviceCodeExclusions != null)
			cnt += serviceCodeExclusions.size();
		if(serviceClassExclusions != null)
			cnt += serviceClassExclusions.size();
		if(limitClassExclusions != null)
			cnt += limitClassExclusions.size();
		if(specialityCodeExclusions != null)
			cnt += specialityCodeExclusions.size();
		return 	cnt;
	}
	/**
	 * 
	 *
	 */
	private void loadValidRules(){
		Rule rule;
		validRules = new ArrayList(); 
		int [] temp;
		if(ruleDetails != null)
		{
			for (Iterator iterator = ruleDetails.iterator(); iterator.hasNext();) {
				 rule = (Rule) iterator.next();
				 if(isRuleValid(rule)){
					 temp = getExclsnIndicators(rule);
					 if(temp != null) {
					 	if(calculateMatchCount(temp) > 0) {
					 		RuleMapping ruleMapping = new RuleMapping(rule.getRuleId(),temp);
					 		validRules.add(ruleMapping);
					 	}
					 }
				 }
			}
		}
	}
	/**
	 * 
	 * @return
	 */
	private List getExlusionMapping(){
		if(validRules == null || validRules.size() == 0)
			return new ArrayList();
		
		if(validRules.size() > MAX_VAL_RULE_SIZE_VAL) {
			List matchingRules = new ArrayList();
			for(int j=0; j<validRules.size(); j++) {
				matchingRules.add(((RuleMapping)validRules.get(j)).ruleId);
			}
			return matchingRules;
		}
		
		int [] matchRuleList = new int[0];
		int matchFound = 0;
		int maxMatch = getMaxMatchPossible();
		int tempMatchCount = 0;
		int [] tempMatchRules;

		if(maxMatch == 0) {
			return new ArrayList();
		}
		
		int totalRuleCount = validRules.size();
		
		bigLoop:
		for (int i= 1; i <= totalRuleCount; i++) {
			CombinationGenerator comGenerator = new CombinationGenerator(totalRuleCount,i);
			while(comGenerator.hasMore()) {
				tempMatchRules = comGenerator.getNext();
				tempMatchCount = getMatchCount(tempMatchRules);
				if(tempMatchCount > matchFound) {
					matchRuleList = tempMatchRules;
					matchFound = tempMatchCount;
					if(matchFound == maxMatch) {
						break bigLoop;
					}
				}
			}
		}
		return getRuleIds(matchRuleList);
	}
	/**
	 * 
	 * @param matchRuleList1
	 * @return
	 */
	private List getRuleIds(int []matchRuleList1) {
		List list = new ArrayList();
		for(int j=0; j<matchRuleList1.length; j++) {
			list.add(((RuleMapping)validRules.get(matchRuleList1[j])).ruleId);
		}
		return list;
	}
	/**
	 * 
	 * @return
	 */	
	private int getMaxMatchPossible(){
		int [] tempMatch = new int[totalExclusionCount];
		RuleMapping ruleMapping;
		if(validRules != null)
		{
			for (Iterator iterator = validRules.iterator(); iterator.hasNext();) {
				ruleMapping = (RuleMapping) iterator.next();
				copyIndicators(ruleMapping.exclusionInd,tempMatch);
			}
		}
		
		int count = 0;
		for(int i =0; i<tempMatch.length; i++) {
			if(tempMatch[i] >= 1)
				count ++;
		}
		return count;
	}
	/**
	 * 
	 * @param ruleComb
	 * @return
	 */	
	private int getMatchCount(int [] ruleComb) {
		int [] tempMatch = new int[totalExclusionCount];
		RuleMapping ruleMapping;
		StringBuffer buffer = new StringBuffer(20);
		for (int i=0; i< ruleComb.length; i++) {
			ruleMapping = (RuleMapping) validRules.get(ruleComb[i]);
			copyIndicators(ruleMapping.exclusionInd,tempMatch);
			if(debug) {
				if(i !=0 )
					buffer.append(",");
				buffer.append(ruleMapping.ruleId);
			}
		}
		int count = calculateMatchCount(tempMatch);
		if(debug) {
			Logger.logInfo("Matching Rules [" + buffer + "] - Match Count = [" + count + "]");			
		}
		return count;
	}
	/**
	 * 
	 * @param source
	 * @param target
	 */	
	private void copyIndicators(int [] source, int [] target){
		for(int i=0; i<source.length; i++) {
			target[i] += source[i];	
		}
	}
	/**
	 * 
	 * @param exclusionInd
	 * @return
	 */	
	private int calculateMatchCount(int[] exclusionInd){
		int count = 0;
		for(int i =0; i<exclusionInd.length; i++) {
			if(exclusionInd[i] > 1)
				return -1;
			if(exclusionInd[i] == 1)
				count ++;
		}
		return count;
	}
	/**
	 * 
	 * @param rule
	 * @return
	 */	
	private int [] getExclsnIndicators(Rule rule){
		int [] exclusionIndicators = new int[totalExclusionCount];
		if(!isGroupRule(rule)) {
			int srvCodeIndx = 0;
			int srvClasIndx = srvCodeIndx + serviceCodeExclusions.size();
			int lmtClasIndx = srvClasIndx + serviceClassExclusions.size();
			int spcCodeIndx = lmtClasIndx + limitClassExclusions.size();
			for (Iterator iterator = rule.getSequences().iterator(); iterator.hasNext();) {
				RuleSequence ruleSequence = (RuleSequence) iterator.next();
				switch (getExclusionType(ruleSequence)) {
				case SRV_CODE_EXCLN:
					exclusionIndicators[srvCodeIndx + getLegacyExclnIndex(ruleSequence)] += 1;
					break;
				case SRV_CLS_EXCLN:
					exclusionIndicators[srvClasIndx + getLegacyExclnIndex(ruleSequence)] += 1;
					break;
				case LMT_CLS_EXCLN:
					exclusionIndicators[lmtClasIndx + getLegacyExclnIndex(ruleSequence)] += 1;
					break;
				case SPEC_CODE_EXCLN:
					exclusionIndicators[spcCodeIndx + getLegacyExclnIndex(ruleSequence)] += 1;
					break;
				default:
					return null;
				}
			}
		} else {
			// For Group Rules
			for (Iterator groupIterator = rule.getChildRules().iterator(); groupIterator.hasNext();) {
				Rule childRule = (Rule) groupIterator.next();
				copyIndicators(getExclsnIndicators(childRule),exclusionIndicators);
			}
		}
		
		// Setting all values to one even if exclusion are duplicated.
		for(int k=0; k < exclusionIndicators.length; k++) {
			if(exclusionIndicators[k] >= 1)
				exclusionIndicators[k] = 1;
		}
		return exclusionIndicators;
	}
	/**
	 * 
	 * @param rule
	 * @return
	 */
	private boolean isGroupRule(Rule rule){
		 if(GROUP_IND.equalsIgnoreCase(rule.getGroupInd()))
			 return true;
		 else
			 return false;
	}
	/**
	 * 
	 * @param rule
	 * @return
	 */
	private boolean isRuleValid(Rule rule){

		if(isGroupRule(rule)) {
			// Validating group rule.
			for (Iterator groupIterator = rule.getChildRules().iterator(); groupIterator.hasNext();) {
				Rule childRule = (Rule) groupIterator.next();
				if(isGroupRule(childRule))
					return false;
				if(!isRuleValid(childRule))
					return false;
			}
		} else { 
			// Validating normal rule.
			RuleSequence ruleSequence;
			Iterator iterator;
			int ruleExclnType ;
			for (iterator = rule.getSequences().iterator(); iterator.hasNext();) {
				ruleSequence = (RuleSequence) iterator.next();
				ruleExclnType = getExclusionType(ruleSequence);
	
				// Sequence contains more than one exclusions.
				if(ruleExclnType == -1)
					return false;
	
				// Sequence does not have any exclusions.
				if(ruleExclnType == 0)
					iterator.remove();
				else {
					// legacy contract does not have the corresponding exclusion.
					if(getLegacyExclnIndex(ruleSequence) == -1)
						return false;
				}

			}
		}
		return true;
	}
	/**
	 * 
	 * @param ruleSequence
	 * @return
	 */	
	private int getLegacyExclnIndex(RuleSequence ruleSequence){
		int exclnType = getExclusionType(ruleSequence);
		int i;
		switch (exclnType) {
		case SRV_CODE_EXCLN:
			ServiceCodeExclusion legacyExln;
			for (i=0; i<serviceCodeExclusions.size(); i++) {
				 legacyExln = (ServiceCodeExclusion) serviceCodeExclusions.get(i);
				 if(legacyExln.getServiceCode().equals(ruleSequence.getServiceCode()))
					 return i;
			}
			return -1;
		case SRV_CLS_EXCLN:
			ServiceClassExclusion srvClassExcln;
			for (i=0; i<serviceClassExclusions.size(); i++) {
				srvClassExcln = (ServiceClassExclusion) serviceClassExclusions.get(i);
				 if(srvClassExcln.getSrvClassFrom().equals(ruleSequence.getServiceClassLow()) && 
						 srvClassExcln.getSrClassThrough().equals(ruleSequence.getServiceClassHigh()))
					 return i;
			}
			return -1;
		case LMT_CLS_EXCLN:
			LimitClassExclusion lmtClassExcln;
			for (i=0; i<limitClassExclusions.size(); i++) {
				lmtClassExcln = (LimitClassExclusion) limitClassExclusions.get(i);
				 if(lmtClassExcln.getLimitClass().equals(ruleSequence.getLimitClassLow()) &&
						 lmtClassExcln.getLimitClass().equals(ruleSequence.getLimitClassHigh()) )
					 return i;
			}
			return -1;
		case SPEC_CODE_EXCLN:
			SpecialityCodeExclusion specCodeExclusion;
			for (i=0; i<specialityCodeExclusions.size(); i++) {
				specCodeExclusion = (SpecialityCodeExclusion) specialityCodeExclusions.get(i);
				 if(specCodeExclusion.getSpecialityCode().equals(ruleSequence.getSpecialityCode()))
					 return i;
			}
			return -1;
		default:
			return -1;
		}
	}
	/**
	 * 
	 * @param ruleSequence
	 * @return
	 */
	private int getExclusionType(RuleSequence ruleSequence) {
		boolean exclusionFound = false;
		int exclutionType = 0;
		if(ruleSequence.getServiceCode() != null && !ruleSequence.getServiceCode().equals("")) {
			exclusionFound = true;
			exclutionType = SRV_CODE_EXCLN;
		}
		if((ruleSequence.getServiceClassLow() != null && !ruleSequence.getServiceClassLow().equals("")) ||
				(ruleSequence.getServiceClassHigh() != null && !ruleSequence.getServiceClassHigh().equals(""))) {
			if(exclusionFound)
				return -1;
			exclusionFound = true;			
			exclutionType = SRV_CLS_EXCLN;
		}
		if( (ruleSequence.getLimitClassLow() != null && !ruleSequence.getLimitClassLow().equals("")) ||
				(ruleSequence.getLimitClassHigh() != null && !ruleSequence.getLimitClassHigh().equals(""))) {
			if(exclusionFound)
				return -1;
			exclusionFound = true;
			exclutionType = LMT_CLS_EXCLN;
		}
		if(ruleSequence.getSpecialityCode() != null && !ruleSequence.getSpecialityCode().equals("")) {
			if(exclusionFound)
				return -1;
			exclusionFound = true;		
			exclutionType = SPEC_CODE_EXCLN;
		}
		return exclutionType;
	}

	/**
	 * Returns the totalExclusionCount.
	 * @return int totalExclusionCount.
	 */
	public int getTotalExclusionCount() {
		return totalExclusionCount;
	}

	/**
	 * Returns the codedVariables.
	 * @return List codedVariables.
	 */
	public List getCodedVariables() {
		return codedVariables;
	}

	/**
	 * Returns the validRules.
	 * @return List validRules.
	 */
	public List getValidRules() {
		return validRules;
	}

	/**
	 * Returns the exclusionMappingRules.
	 * @return List exclusionMappingRules.
	 */
	public List getExclusionMappingRules() {
		return exclusionMappingRules;
	}

	/**
	 * Returns the variableMappingRules.
	 * @return List variableMappingRules.
	 */
	public List getVariableMappingRules() {
		return variableMappingRules;
	}

	/**
	 * Returns the resultMappingRules.
	 * @return List resultMappingRules.
	 */
	public List getResultMappingRules() {
		return resultMappingRules;
	}
}
