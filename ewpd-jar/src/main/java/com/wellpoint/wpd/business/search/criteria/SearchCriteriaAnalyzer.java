/*
 * SearchCriteriaAnalyser.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.criteria;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.search.criteria.AdvancedAttribute;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchObject;
import com.wellpoint.wpd.common.search.criteria.BasicAttribute;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.exception.SearchCriteriaValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchCriteriaAnalyzer {
    
   
    /**
     * This method sets the Query Condition string to the
     * BasicSearchCriteria.basicAttribute
     * 
     * @param basicSearchCriteria
     * @return
     */
    public boolean analyze(BasicSearchCriteria basicSearchCriteria) throws SearchCriteriaValidationException{
        if(basicSearchCriteria == null){
            return false;
        }
    	new SearchCriteriaValidator().validate(basicSearchCriteria);
        BasicAttribute basicAttribute = basicSearchCriteria.getBasicAttribute();
        String criteriaString = basicAttribute.getCriteria();
        if(criteriaString!=null && !"".equalsIgnoreCase(criteriaString.trim())){
        List parsedCriteriaStrings = new CriteriaParser()
                        .parseCriteriaString(criteriaString);
        List queryConditionList = new QueryCreator()
                        .createQueryConditionAsList(parsedCriteriaStrings,null,null,null,null);
        basicAttribute.setQueryList(queryConditionList);
        String queryCondition = new QueryCreator()
                        .createQueryCondition(parsedCriteriaStrings,null,null,null,null);
        basicAttribute.setQuery(queryCondition);
        }
        return true;
       
    }
   
    /**
     * 
     * @param advancedSearchCriteria
     * @return
     * @throws SearchCriteriaValidationException
     */
    public boolean analyze(AdvancedSearchCriteria advancedSearchCriteria)throws SearchCriteriaValidationException{
    	if(advancedSearchCriteria == null){
    		return false;
    	}

    	new SearchCriteriaValidator().validate(advancedSearchCriteria);
    	List advancedObjects = advancedSearchCriteria.getAdvancedSearchObjects();
    	for(int advncdObjCnt = 0 ;advncdObjCnt <advancedObjects.size(); advncdObjCnt++){
			AdvancedSearchObject aso = (AdvancedSearchObject) advancedObjects.get(advncdObjCnt);
			if(aso.isChecked()){
			List attributes = aso.getAdvancedAttributes();
			int lastIndex=getLastAttribute(attributes);
			for(int attrCnt=0;attrCnt<attributes.size();attrCnt++){
				AdvancedAttribute attr = (AdvancedAttribute)attributes.get(attrCnt);
				if(attr.isChecked()){

						String criteriaString = attr.getCriteria();
						List parsedCriteriaStrings=new ArrayList();
						if(null!=attr.getSign()&&!" ".equalsIgnoreCase(attr.getSign())){
							parsedCriteriaStrings.add(criteriaString);
						}else{
							parsedCriteriaStrings= new CriteriaParser()
					                        .parseCriteriaString(criteriaString);
						}
					    String queryCondition ="";
					    if(attr.isSpecial()){
					    	List queryConditions = null;
					    		queryConditions = new QueryCreator()
						                        .createQueryConditionAsList(parsedCriteriaStrings,attr.getSign(),attr.getType(),attr.getName(),aso.getType());
							if(attrCnt == attributes.size()-1||lastIndex==attrCnt){
						    }else{
					    		queryConditions.add(" "+attr.getRelation().toUpperCase()+" ");
						    }
						    attr.setQueryList(queryConditions);
					    }else{
						    if(attrCnt == attributes.size()-1||lastIndex==attrCnt){
						    	queryCondition = new QueryCreator()
						                        .createQueryCondition(parsedCriteriaStrings,attr.getSign(),attr.getType(),attr.getName(),aso.getType());
						    }else{	
						    	queryCondition = new QueryCreator()
			                    .createQueryCondition(parsedCriteriaStrings,attr.getRelation(),attr.getSign(),attr.getType(),attr.getName(),aso.getType());
						    }
						    attr.setQuery(queryCondition);
					    }
				}
			}	
    	}
    	}
    	return true;
    }
    
    private int getLastAttribute(List attributes){
    	int lastIndex=0;
    
    	for(int attrCnt=0;attrCnt<attributes.size();attrCnt++){
			AdvancedAttribute attr = (AdvancedAttribute)attributes.get(attrCnt);
			if(attr.isChecked())
				lastIndex=attrCnt;
    	}
    	return lastIndex;
    }
    
}
