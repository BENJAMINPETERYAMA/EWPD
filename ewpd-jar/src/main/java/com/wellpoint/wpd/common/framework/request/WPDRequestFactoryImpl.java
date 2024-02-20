/*
 * WPDRequestFactoryImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.search.criteria.AdvancedAttribute;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchObject;
import com.wellpoint.wpd.common.search.criteria.BasicAttribute;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicSearchObject;
import com.wellpoint.wpd.common.search.request.AdvancedSearchRequest;
import com.wellpoint.wpd.common.search.request.BasicSearchRequest;
import com.wellpoint.wpd.common.search.util.SearchConstants;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDRequestFactoryImpl.java 53013 2009-06-21 08:31:33Z U17810 $
 */
public class WPDRequestFactoryImpl extends ObjectFactory implements WPDRequestFactory{

    public static int USER_OBJECT_REQUEST = 1;
    
    public static ApplicationContext applicationContext;

    static {
        applicationContext = new ClassPathXmlApplicationContext(
                "com/wellpoint/wpd/common/configfiles/request-context.xml");
    }

    public WPDRequestFactoryImpl() {
        super();
    }
    /**
     * The interface method to be used to create and retrieve a WPDRequest object. 
     * The User object is set by default for all requests except UserObjectRequest
     * @param serviceName
     * @return the requested type of WPDRequest object.s
     */
    public WPDRequest getRequest(String serviceName) {
        /**WPDRequest request = null;
        switch (type) {
        case 1:
            return request = getUserObjectRequest(bean);
        default:
            
        }
        if(type != USER_OBJECT_REQUEST){
            request.setUser(bean.getUser());
        }*/
        
        WPDRequest request = (WPDRequest)applicationContext.getBean(serviceName);
        if(request == null){
            throw new RuntimeException(
                    "Incorrect Request type specified.  No type called "
                            + serviceName);            
        }
        if("basicSearchRequest".equals(serviceName)){
        	fillBasicSearchRequest(request);
        }else if("advancedSearchRequest".equals(serviceName)){
        	initializeAdvancedSearchRequest(request);
        }
        return request;
    }
    
	/**
	 * @param request
	 */
	private void fillBasicSearchRequest(WPDRequest request) {
		BasicSearchRequest basicSearchRequest = (BasicSearchRequest)request;
		
		BasicSearchCriteria criteria = new BasicSearchCriteria();
		criteria.setBasicAttribute(new BasicAttribute());
		
		List basicSearchObjects = new ArrayList();
		
		BasicSearchObject searchObject = new BasicSearchObject();
		searchObject.setType(SearchConstants.BENEFIT);
		basicSearchObjects.add(searchObject);
		
		searchObject = new BasicSearchObject();
		searchObject.setType(SearchConstants.BENEFIT_COMPONENTS);
		basicSearchObjects.add(searchObject);
		
		searchObject = new BasicSearchObject();
		searchObject.setType(SearchConstants.BENEFIT_LEVEL);
		basicSearchObjects.add(searchObject);
		
		searchObject = new BasicSearchObject();
		searchObject.setType(SearchConstants.CONTRACT);
		basicSearchObjects.add(searchObject);
		
		searchObject = new BasicSearchObject();
		searchObject.setType(SearchConstants.NOTES);
		basicSearchObjects.add(searchObject);
		
		searchObject = new BasicSearchObject();
		searchObject.setType(SearchConstants.PRODUCT);
		basicSearchObjects.add(searchObject);
		
		searchObject = new BasicSearchObject();
		searchObject.setType(SearchConstants.PRODUCT_STRUCTURES);
		basicSearchObjects.add(searchObject);
		
		criteria.setBasicSearchObjects(basicSearchObjects);
		
		basicSearchRequest.setBasicSearchCriteria(criteria);
	}
	
	
	private void initializeAdvancedSearchRequest(WPDRequest asr){
		
		AdvancedSearchRequest advancedSearchRequest=(AdvancedSearchRequest)asr;
		AdvancedSearchCriteria advancedSearchCriteria =  new AdvancedSearchCriteria();
		List advancedSearchObjects =  new ArrayList();
		AdvancedSearchObject aso = new AdvancedSearchObject();
		aso.setChecked(false);
		aso.setType(SearchConstants.CONTRACT);
		aso.setAdvancedAttributes(getAdvancedAttributes(SearchConstants.CONTRACT));
		advancedSearchObjects.add(aso);
		aso = new AdvancedSearchObject();
		aso.setChecked(false);
		aso.setType(SearchConstants.PRODUCT);
		aso.setAdvancedAttributes(getAdvancedAttributes(SearchConstants.PRODUCT));
		advancedSearchObjects.add(aso);
		aso = new AdvancedSearchObject();
		aso.setChecked(false);
		aso.setType(SearchConstants.PRODUCT_STRUCTURES);
		aso.setAdvancedAttributes(getAdvancedAttributes(SearchConstants.PRODUCT_STRUCTURES));
		advancedSearchObjects.add(aso);
		aso = new AdvancedSearchObject();
		aso.setChecked(false);
		aso.setType(SearchConstants.BENEFIT_COMPONENTS);
		aso.setAdvancedAttributes(getAdvancedAttributes(SearchConstants.BENEFIT_COMPONENTS));
		advancedSearchObjects.add(aso);
		aso = new AdvancedSearchObject();
		aso.setChecked(false);
		aso.setType(SearchConstants.BENEFIT);
		aso.setAdvancedAttributes(getAdvancedAttributes(SearchConstants.BENEFIT));
		advancedSearchObjects.add(aso);
		aso = new AdvancedSearchObject();
		aso.setChecked(false);
		aso.setType(SearchConstants.BENEFIT_LEVEL);
		aso.setAdvancedAttributes(getAdvancedAttributes(SearchConstants.BENEFIT_LEVEL));
		advancedSearchObjects.add(aso);
		aso = new AdvancedSearchObject();
		aso.setChecked(false);
		aso.setType(SearchConstants.NOTES);
		aso.setAdvancedAttributes(getAdvancedAttributes(SearchConstants.NOTES));
		advancedSearchObjects.add(aso);
		advancedSearchCriteria.setAdvancedSearchObjects(advancedSearchObjects);
		advancedSearchRequest.setAdvancedSearchCriteria(advancedSearchCriteria);
	}
	/**
	 * @param objectType
	 */

	private List getAdvancedAttributes(String objectType){
		List attributeList = new ArrayList();
		if(SearchConstants.CONTRACT.equalsIgnoreCase(objectType)){

			AdvancedAttribute advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CONTRACT_ID);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CONTRACT_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_EFFECTIVE_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_EXPIRY_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_GROUP_SIZE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_PRODUCT_CODE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_STANDARD_PLAN_CODE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_BENEFIT_PLAN);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_PRODUCT_FAMILY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CORPORATE_PLAN_CODE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_BRAND_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_HEAD_QUARTERS_STATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_STATUS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_COB_A_I);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_MED_A_I);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_ITS_A_I);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CASE_NUMBER);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CASE_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CASE_EFFECTIVE_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CASE_CANCEL_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CASE_HEAD_QUARTER_STATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_GROUP_NUMBER);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_GROUP_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_GROUP_EFFECTIVE_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_GROUP_CANCEL_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_FUNDING_ARRANGEMENT_CODE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_FUNDING_ARRANGEMENT_VALUE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_MBU_CODE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);	
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_MBU_VALUE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
//			 START new fileds added for september release 
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_RGLTRY_AGNCY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CMPLNC_STTS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
					
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_PRD_PRJCT_NAME_CODE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_TERM_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_MULTI_PLAN_INDCTR);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_PRFRMNCE_GUARANTEE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_SALES_MARKET_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
//			End new fileds added for september release 
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CREATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_CREATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_LAST_UPDATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.CONTRACT_LAST_UPDATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			


		}else if(SearchConstants.PRODUCT.equalsIgnoreCase(objectType)){
			
			AdvancedAttribute advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_PRODUCT_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_EFFECTIVE_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_EXPIRY_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setName(SearchConstants.PRODUCT_PRODUCT_FAMILY);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_DESCRIPTION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_STATUS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_VERSION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Integer");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_MANDATE_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_STATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_CREATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_CREATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_LAST_UPDATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCT_LAST_UPDATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
		}else if(SearchConstants.PRODUCT_STRUCTURES.equalsIgnoreCase(objectType)){
		
			AdvancedAttribute advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_EFFECTIVE_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_EXPIRY_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_DESCRIPTION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_STATUS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_VERSION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Integer");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);	
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_MANDATE_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_STATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);	
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_CREATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_CREATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_LAST_UPDATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);	
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.PRODUCTSTRUCTURE_LAST_UPDATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);


		}else if(SearchConstants.BENEFIT_COMPONENTS.equalsIgnoreCase(objectType)){


			AdvancedAttribute advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_EFFECTIVE_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_EXPIRY_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_DESCRIPTION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_STATUS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_VERSION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Integer");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);	
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_MANDATE_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_STATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);	
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_CREATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_CREATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_LAST_UPDATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITCOMPONENT_LAST_UPDATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);

			
		}else if(SearchConstants.BENEFIT.equalsIgnoreCase(objectType)){
			
			
			AdvancedAttribute advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_DESCRIPTION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_TERM);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_QUALIFIER);
			//setting special flag
			advancedAttr.setSpecial(true);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_PROVIDER_ARRANGEMENT);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_DATA_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_STATUS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_VERSION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Integer");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);	
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_EFFECTIVENESS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_MANDATE_CATEGORY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_CATEGORY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_TEXT);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_JURISDICTION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_CITATION_NUMBER);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_FUNDING_ARRANGEMENT);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_CREATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_CREATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_LAST_UPDATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFIT_LAST_UPDATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);

			
		}else if(SearchConstants.BENEFIT_LEVEL.equalsIgnoreCase(objectType)){

			
			
			AdvancedAttribute advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITLEVEL_TERM);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setSpecial(true);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITLEVEL_QUALIFIER);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setSpecial(true);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITLEVEL_DESCRIPTION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITLEVEL_PVA);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.BENEFITLEVEL_DATA_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			


		}else if(SearchConstants.NOTES.equalsIgnoreCase(objectType)){

			
			AdvancedAttribute advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_ID);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_NAME);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_TYPE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_TARGET_SYSTEMS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_TEXT);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_STATUS);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_DOMAINED_PRODUCT);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_DOMAINED_BENEFIT_COMPONENT);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_DOMAINED_BENEFIT);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_DOMAINED_QUESTION);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_DOMAINED_TERM);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_DOMAINED_QUALIFIER);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_CREATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_CREATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_LAST_UPDATED_BY);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("String");
			attributeList.add(advancedAttr);
			advancedAttr = new AdvancedAttribute();
			advancedAttr.setChecked(false);
			advancedAttr.setCriteria("");
			advancedAttr.setName(SearchConstants.NOTES_LAST_UPDATED_DATE);
			advancedAttr.setRelation(SearchConstants.ADVANCED_SEARCH_REL_AND);
			advancedAttr.setType("Date");
			attributeList.add(advancedAttr);

		}
		return attributeList;
	}}