/*
 * BasicSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ReferenceSyntaxException;
import javax.faces.el.ValueBinding;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.search.criteria.BasicAttribute;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicSearchObject;
import com.wellpoint.wpd.common.search.criteria.LimitedTo;
import com.wellpoint.wpd.common.search.request.BasicSearchRequest;
import com.wellpoint.wpd.common.search.response.SearchResponse;
import com.wellpoint.wpd.common.search.result.SearchResult;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.search.pagination.Paginator;
import com.wellpoint.wpd.web.search.util.SearchUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BasicSearchBackingBean extends SearchBackingBean {
    /**
     *  
     */
    public BasicSearchBackingBean() {

        // TODO Auto-generated constructor stub
    }

    private String printBasicSearch;

    private String lobPrintString;

    private String businessEntityPrintString;

    private String businessGroupPrintString;

    private boolean contract = false;

    private boolean product = false;

    private boolean productStructure = false;

    private boolean benefitComponent = false;

    private boolean benefit = false;

    private boolean benefitlevel = false;

    private boolean notes = false;

    private boolean anyCheckboxSelected = true;

    private List lineOfBusinessCodeList;

    private List businessEntityCodeList;

    private List businessGroupCodeList;

    private String lineOfBusiness;

    private String businessEntity;

    private String businessGroup;
	/*START CARS*/
	private String marketBusinessUnit;
	
	private List marketBusinessUnitList;

    private String marketBusinessUnitPrintString;	
	/*END CARS*/
    private List validationMessages = null;

    private boolean requiredField = true;

    private String searchCriteria;

    private boolean searchCriteriaEntered = true;

    private HtmlPanelGrid panel = null;

    private HtmlPanelGrid printPanel = null;
    
    private String printBreadCrumbText;

    /**
     * @return Returns the searchCriteria.
     */
    public String getSearchCriteria() {
        return searchCriteria;
    }

    /**
     * @param searchCriteria
     *            The searchCriteria to set.
     */
    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
    /**
     * 
     * @return SearchSummary
     */
    public String search() {
        try {
            new SearchUtil().resetCache();
            this.lineOfBusinessCodeList = WPDStringUtil.getListFromTildaString(
                    this.getLineOfBusiness(), 2, 2, 2);
            this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessEntity(), 2, 2, 2);
            this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessGroup(), 2, 2, 2);
            /*START CARS*/
            this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(
                    this.getMarketBusinessUnit(), 2, 2, 2);
            /*END CARS*/
            List basicSearchObjects;
            BasicSearchRequest basicSearchRequest = (BasicSearchRequest) getServiceRequest(ServiceManager.BASIC_SEARCH_REQUEST);
            BasicSearchCriteria basicSearchCriteria = basicSearchRequest
                    .getBasicSearchCriteria();
            BasicAttribute basicAttribute = basicSearchCriteria
                    .getBasicAttribute();
            basicAttribute.setCriteria(getSearchCriteria().toUpperCase());
            setSearchCriteria(getSearchCriteria().toUpperCase());
            basicAttribute.setName(SearchConstants.BASIC_SEARCH_IDENTIFIER);
            basicSearchObjects = basicSearchCriteria.getBasicSearchObjects();
            for (int i = 0; i < basicSearchObjects.size(); i++) {
                BasicSearchObject basicSearchObject = (BasicSearchObject) basicSearchObjects
                        .get(i);
                if (SearchConstants.CONTRACT.equalsIgnoreCase(basicSearchObject
                        .getType())) {
                    basicSearchObject.setChecked(isContract());
                } else if (SearchConstants.PRODUCT
                        .equalsIgnoreCase(basicSearchObject.getType())) {
                    basicSearchObject.setChecked(isProduct());
                } else if (SearchConstants.PRODUCT_STRUCTURES
                        .equalsIgnoreCase(basicSearchObject.getType())) {
                    basicSearchObject.setChecked(isProductStructure());
                } else if (SearchConstants.BENEFIT_COMPONENTS
                        .equalsIgnoreCase(basicSearchObject.getType())) {
                    basicSearchObject.setChecked(isBenefitComponent());
                } else if (SearchConstants.BENEFIT
                        .equalsIgnoreCase(basicSearchObject.getType())) {
                    basicSearchObject.setChecked(isBenefit());
                } else if (SearchConstants.BENEFIT_LEVEL
                        .equalsIgnoreCase(basicSearchObject.getType())) {
                    basicSearchObject.setChecked(isBenefitlevel());
                } else if (SearchConstants.NOTES
                        .equalsIgnoreCase(basicSearchObject.getType())) {
                    basicSearchObject.setChecked(isNotes());
                }
            }

            //Check whether any of the objects are selected
            anyCheckboxSelected = false;
            Iterator iterator = basicSearchObjects.iterator();
            while (iterator.hasNext()) {
                BasicSearchObject bso = (BasicSearchObject) iterator.next();
                if (bso.isChecked()) {
                    anyCheckboxSelected = true;
                    break;
                }
            }
            //Check if search criteria field is not entered
            String criteria = getSearchCriteria();
            searchCriteriaEntered = false;
            if ((criteria != null && !"".equals(criteria.trim()))) {
                searchCriteriaEntered = true;
            }
            //&&(this.businessEntityCodeList.size()==0&&
    		//this.businessGroupCodeList.size()==0&&this.lineOfBusinessCodeList.size()==0)
            if (!anyCheckboxSelected || (!searchCriteriaEntered)) {
                ErrorMessage em = new ErrorMessage(
                        "please.enter.all.mandatory.fields");
                this.addMessageToRequest(em);
            	setBreadCrumbText("Search >> Basic Search Criteria");
                return "BasicSearchCriteria";
            }

            LimitedTo limitedTo = new LimitedTo();
            limitedTo.setBusinessEntity(businessEntityCodeList);
            limitedTo.setBusinessGroup(businessGroupCodeList);
			limitedTo.setLineOfBusiness(lineOfBusinessCodeList);
            /*START CARS*/
            //Market Business Unit
            limitedTo.setMarketBusinessUnit(marketBusinessUnitList);
            /*START CARS*/
			basicSearchCriteria.setLimitedTo(limitedTo);

            SearchResponse searchResponse = (SearchResponse) this
                    .executeService(basicSearchRequest);
            List searchResults = searchResponse.getSearchResults();
            int totalResults = 0;
            boolean individualLimitExceeded = false;
            boolean totalLimitExceeded = false;
            int j = 0;
            if (searchResults != null) {
                for (int i = 0; i < searchResults.size(); i++) {
                    SearchResult searchResult = (SearchResult) searchResults
                            .get(i);
                    j += searchResult.getNumberOfResults();
                    getSession().setAttribute(searchResult.getType(),
                            new Paginator().paginate(searchResult));
                }
                if (j > 500) {
                    addMessageToRequest(new ErrorMessage(
                            "search.results.total.large"));
                } else if (j == 0) {
                    addMessageToRequest(new ErrorMessage(
                            "search.results.total.zero"));
                }
            } else {
                return "BasicSearchCriteria";
            }
        } catch (Exception e) {
            return "BasicSearchCriteria";
        }
        // For tree component to expand the nodes
        getRequest().setAttribute(SearchConstants.SEARCH_PERFORMED, "TURE");
        getSession().setAttribute("SEARCH_TYPE",
                String.valueOf(SearchConstants.BASIC_SEARCH));
        setBreadCrumbText("Search >> Search Summary");
        return "SearchSummary";

    }

    /**
     * @return benefitlevel
     * 
     * Returns the benefitlevel.
     */
    public boolean isBenefitlevel() {
        return benefitlevel;
    }

    /**
     * @param benefitlevel
     * 
     * Sets the benefitlevel.
     */
    public void setBenefitlevel(boolean benefitlevel) {
        this.benefitlevel = benefitlevel;
    }

    /**
     * @return contract
     * 
     * Returns the contract.
     */
    public boolean isContract() {
        return contract;
    }

    /**
     * @param contract
     * 
     * Sets the contract.
     */
    public void setContract(boolean contract) {

        this.contract = contract;
    }

    /**
     * @return notes
     * 
     * Returns the notes.
     */
    public boolean isNotes() {
        return notes;
    }

    /**
     * @param notes
     * 
     * Sets the notes.
     */
    public void setNotes(boolean notes) {
        this.notes = notes;
    }

    /**
     * @return product
     * 
     * Returns the product.
     */
    public boolean isProduct() {
        return product;
    }

    /**
     * @param product
     * 
     * Sets the product.
     */
    public void setProduct(boolean product) {
        this.product = product;
    }

    /**
     * @return productStructure
     * 
     * Returns the productStructure.
     */
    public boolean isProductStructure() {
        return productStructure;
    }

    /**
     * @param productStructure
     * 
     * Sets the productStructure.
     */
    public void setProductStructure(boolean productStructure) {
        this.productStructure = productStructure;
    }

    /**
     * @return benefit
     * 
     * Returns the benefit.
     */
    public boolean isBenefit() {
        return benefit;
    }

    /**
     * @param benefit
     * 
     * Sets the benefit.
     */
    public void setBenefit(boolean benefit) {
        this.benefit = benefit;
    }

    /**
     * @return benefitComponent
     * 
     * Returns the benefitComponent.
     */
    public boolean isBenefitComponent() {
        return benefitComponent;
    }

    /**
     * @param benefitComponent
     * 
     * Sets the benefitComponent.
     */
    public void setBenefitComponent(boolean benefitComponent) {
        this.benefitComponent = benefitComponent;
    }

    /**
     * @return businessEntityCodeList
     * 
     * Returns the businessEntityCodeList.
     */
    public List getBusinessEntityCodeList() {
        return businessEntityCodeList;
    }

    /**
     * @param businessEntityCodeList
     * 
     * Sets the businessEntityCodeList.
     */
    public void setBusinessEntityCodeList(List businessEntityCodeList) {
        this.businessEntityCodeList = businessEntityCodeList;
    }

    /**
     * @return businessGroupCodeList
     * 
     * Returns the businessGroupCodeList.
     */
    public List getBusinessGroupCodeList() {
        return businessGroupCodeList;
    }

    /**
     * @param businessGroupCodeList
     * 
     * Sets the businessGroupCodeList.
     */
    public void setBusinessGroupCodeList(List businessGroupCodeList) {
        this.businessGroupCodeList = businessGroupCodeList;
    }

    /**
     * @return lineOfBusinessCodeList
     * 
     * Returns the lineOfBusinessCodeList.
     */
    public List getLineOfBusinessCodeList() {
        return lineOfBusinessCodeList;
    }

    /**
     * @param lineOfBusinessCodeList
     * 
     * Sets the lineOfBusinessCodeList.
     */
    public void setLineOfBusinessCodeList(List lineOfBusinessCodeList) {
        this.lineOfBusinessCodeList = lineOfBusinessCodeList;
    }

    /**
     * @return businessEntity
     * 
     * Returns the businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }

    /**
     * @param businessEntity
     * 
     * Sets the businessEntity.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    /**
     * @return businessGroup
     * 
     * Returns the businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }

    /**
     * @param businessGroup
     * 
     * Sets the businessGroup.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }

    /**
     * @return lineOfBusiness
     * 
     * Returns the lineOfBusiness.
     */
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    /**
     * @param lineOfBusiness
     * 
     * Sets the lineOfBusiness.
     */
    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * @return Returns the advancedSearch.
     */
    public String advancedSearch() {
        return "AdvancedSearchCriteria";
    }

    /**
     * @param advancedSearch
     *            The advancedSearch to set.
     */

    /**
     * @return Returns the anyCheckboxSelected.
     */
    public boolean isAnyCheckboxSelected() {
        return anyCheckboxSelected;
    }

    /**
     * @param anyCheckboxSelected
     *            The anyCheckboxSelected to set.
     */
    public void setAnyCheckboxSelected(boolean anyCheckboxSelected) {
        this.anyCheckboxSelected = anyCheckboxSelected;
    }

    /**
     * @return Returns the searchCriteriaEntered.
     */
    public boolean isSearchCriteriaEntered() {
        return searchCriteriaEntered;
    }

    /**
     * @param searchCriteriaEntered
     *            The searchCriteriaEntered to set.
     */
    public void setSearchCriteriaEntered(boolean searchCriteriaEntered) {
        this.searchCriteriaEntered = searchCriteriaEntered;
    }

    /**
     * @return Returns the panel.
     */
    public HtmlPanelGrid getPanel() {
        panel = new HtmlPanelGrid();
        //panel.setWidth("500");
        panel.setColumns(2);
        panel.setBorder(0);

        panel.setStyleClass("outputText");
        panel.setCellpadding("3");
        panel.setCellspacing("1");

        panel.setHeaderClass("Head");

        try {
            if (getUser().isAuthorized(WebConstants.CONTRACT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.BASIC_SEARCH_TASK)) {

                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                ValueBinding value = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{basicSearchBackingBean.contract}");

                booleanCheckbox.setValueBinding("value", value);
                booleanCheckbox.setId("ID5"+RandomStringUtils.randomAlphanumeric(15));
                booleanCheckbox.setTabindex("5");
                panel.getChildren().add(booleanCheckbox);
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Contract");
                panel.getChildren().add(htmlOutputtext);

            }
            if (getUser().isAuthorized(WebConstants.PRODUCT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.BASIC_SEARCH_TASK)) {

                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                ValueBinding value = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{basicSearchBackingBean.product}");

                booleanCheckbox.setValueBinding("value", value);
                booleanCheckbox.setId("ID6"+RandomStringUtils.randomAlphanumeric(15));
                booleanCheckbox.setTabindex("6");
                panel.getChildren().add(booleanCheckbox);
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Product");
                panel.getChildren().add(htmlOutputtext);

            }
            if (getUser().isAuthorized(WebConstants.PRODUCT_STRUCTURES_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.BASIC_SEARCH_TASK)) {

                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                ValueBinding value = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{basicSearchBackingBean.productStructure}");

                booleanCheckbox.setValueBinding("value", value);
                booleanCheckbox.setId("ID7"+RandomStringUtils.randomAlphanumeric(15));
                booleanCheckbox.setTabindex("7");
                panel.getChildren().add(booleanCheckbox);
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Product Structure");
                panel.getChildren().add(htmlOutputtext);

            }
            if (getUser().isAuthorized(WebConstants.BENEFIT_COMPONENTS_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.BASIC_SEARCH_TASK)) {

                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                ValueBinding value = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{basicSearchBackingBean.benefitComponent}");

                booleanCheckbox.setValueBinding("value", value);
                booleanCheckbox.setId("ID8"+RandomStringUtils.randomAlphanumeric(15));
                booleanCheckbox.setTabindex("8");
                panel.getChildren().add(booleanCheckbox);
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Benefit Component");
                panel.getChildren().add(htmlOutputtext);

            }
            if (getUser().isAuthorized(WebConstants.BENEFIT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.BASIC_SEARCH_TASK)) {

                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                ValueBinding value = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{basicSearchBackingBean.benefit}");

                booleanCheckbox.setValueBinding("value", value);
                booleanCheckbox.setId("ID9"+RandomStringUtils.randomAlphanumeric(15));
                booleanCheckbox.setTabindex("9");
                panel.getChildren().add(booleanCheckbox);
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Benefit");
                panel.getChildren().add(htmlOutputtext);

            }
            if (getUser().isAuthorized(WebConstants.BENEFIT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.BASIC_SEARCH_TASK)) {

                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                ValueBinding value = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{basicSearchBackingBean.benefitlevel}");

                booleanCheckbox.setValueBinding("value", value);
                booleanCheckbox.setId("ID10"+RandomStringUtils.randomAlphanumeric(15));
                booleanCheckbox.setTabindex("10");
                panel.getChildren().add(booleanCheckbox);
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Benefit Level");
                panel.getChildren().add(htmlOutputtext);

            }
            if (getUser().isAuthorized(WebConstants.NOTES_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.BASIC_SEARCH_TASK)) {

                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                ValueBinding value = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{basicSearchBackingBean.notes}");

                booleanCheckbox.setValueBinding("value", value);
                booleanCheckbox.setId("ID11"+RandomStringUtils.randomAlphanumeric(15));
                booleanCheckbox.setTabindex("11");
                panel.getChildren().add(booleanCheckbox);
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Notes");
                panel.getChildren().add(htmlOutputtext);

            }

        } catch (ReferenceSyntaxException e) {
            // TODO Auto-generated catch block
        	Logger.logError(e);
        } catch (SevereException e) {
            // TODO Auto-generated catch block
        	Logger.logError(e);
        }

        return panel;
    }

    /**
     * @param panel
     *            The panel to set.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }

    /**
     * @return Returns the printPanel.
     */
    public HtmlPanelGrid getPrintPanel() {
        printPanel = new HtmlPanelGrid();
        //panel.setWidth("500");
        printPanel.setColumns(1);
        printPanel.setBorder(0);

        printPanel.setStyleClass("outputText");
        printPanel.setCellpadding("3");
        printPanel.setCellspacing("1");

        printPanel.setHeaderClass("Head");

        try {
            if (isContract()) {
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Contract");
                printPanel.getChildren().add(htmlOutputtext);

            }
            if (isProduct()) {

                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Product");
                printPanel.getChildren().add(htmlOutputtext);

            }
            if (isProductStructure()) {

                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Product Structure");
                printPanel.getChildren().add(htmlOutputtext);

            }
            if (isBenefitComponent()) {

                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Benefit Component");
                printPanel.getChildren().add(htmlOutputtext);

            }
            if (isBenefit()) {

                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Benefit");
                printPanel.getChildren().add(htmlOutputtext);

            }
            if (isBenefitlevel()) {

                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Benefit Level");
                printPanel.getChildren().add(htmlOutputtext);

            }
            if (isNotes()) {
                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue("Notes");
                printPanel.getChildren().add(htmlOutputtext);

            }

        } catch (ReferenceSyntaxException e) {
            // TODO Auto-generated catch block
        	Logger.logError(e);
        }

        return printPanel;
    }

    /**
     * @param printPanel
     *            The printPanel to set.
     */
    public void setPrintPanel(HtmlPanelGrid printPanel) {
        this.printPanel = printPanel;
    }

    /**
     * @return Returns the lobPrintString.
     */
    public String getLobPrintString() {
        //lineOfBusiness
        int flag = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (null != lineOfBusiness) {
            StringTokenizer stringTokenizer = new StringTokenizer(
                    lineOfBusiness, "~");
            while (stringTokenizer.hasMoreElements()) {
                stringTokenizer.nextToken();
                String stringVal = stringTokenizer.nextToken();
                if (flag != 0) {
                    stringBuffer.append(",");
                } else {
                    flag = 1;
                }
                stringBuffer.append(stringVal);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * @param lobPrintString
     *            The lobPrintString to set.
     */
    public void setLobPrintString(String lobPrintString) {
        this.lobPrintString = lobPrintString;
    }

    /**
     * @return Returns the businessEntityPrintString.
     */
    public String getBusinessEntityPrintString() {

        int flag = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (null != businessEntity) {
            StringTokenizer stringTokenizer = new StringTokenizer(
                    businessEntity, "~");
            while (stringTokenizer.hasMoreElements()) {
                stringTokenizer.nextToken();
                String stringVal = stringTokenizer.nextToken();
                if (flag != 0) {
                    stringBuffer.append(",");
                } else {
                    flag = 1;
                }
                stringBuffer.append(stringVal);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * @param businessEntityPrintString
     *            The businessEntityPrintString to set.
     */
    public void setBusinessEntityPrintString(String businessEntityPrintString) {
        this.businessEntityPrintString = businessEntityPrintString;
    }

    /**
     * @return Returns the businessGroupPrintString.
     */
    public String getBusinessGroupPrintString() {
        int flag = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (null != businessGroup) {
            StringTokenizer stringTokenizer = new StringTokenizer(
                    businessGroup, "~");
            while (stringTokenizer.hasMoreElements()) {
                stringTokenizer.nextToken();
                String stringVal = stringTokenizer.nextToken();
                if (flag != 0) {
                    stringBuffer.append(",");
                } else {
                    flag = 1;
                }
                stringBuffer.append(stringVal);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * @param businessGroupPrintString
     *            The businessGroupPrintString to set.
     */
    public void setBusinessGroupPrintString(String businessGroupPrintString) {
        this.businessGroupPrintString = businessGroupPrintString;
    }

    /**
     * Returns the printBasicSearch
     * 
     * @return String printBasicSearch.
     */
    public String getPrintBasicSearch() {
        return "basicsearchprint";
    }

    /**
     * Sets the printBasicSearch
     * 
     * @param printBasicSearch.
     */
    public void setPrintBasicSearch(String printBasicSearch) {
        this.printBasicSearch = printBasicSearch;
    }
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Search >> Basic Search Criteria >> Print";
        return printBreadCrumbText;
    }
    /**
     * @param printBreadCrumbText
     * 
     * Sets the printBreadCrumbText.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
	/*START CARS*/
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
	/**
	 * @return Returns the marketBusinessUnitPrintString.
	 */
	public String getMarketBusinessUnitPrintString() {
        int flag = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (null != marketBusinessUnit) {
            StringTokenizer stringTokenizer = new StringTokenizer(
            		marketBusinessUnit, "~");
            while (stringTokenizer.hasMoreElements()) {
                stringTokenizer.nextToken();
                String stringVal = stringTokenizer.nextToken();
                if (flag != 0) {
                    stringBuffer.append(",");
                } else {
                    flag = 1;
                }
                stringBuffer.append(stringVal);
            }
        }
        return stringBuffer.toString();
	}
	/**
	 * @param marketBusinessUnitPrintString The marketBusinessUnitPrintString to set.
	 */
	public void setMarketBusinessUnitPrintString(
			String marketBusinessUnitPrintString) {
		this.marketBusinessUnitPrintString = marketBusinessUnitPrintString;
	}
	/*END CARS*/
}