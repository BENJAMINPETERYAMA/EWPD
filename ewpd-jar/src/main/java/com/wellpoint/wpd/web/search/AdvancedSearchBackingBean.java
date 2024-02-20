/*
 * AdvancedSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.el.ReferenceSyntaxException;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.search.criteria.AdvancedAttribute;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchObject;
import com.wellpoint.wpd.common.search.criteria.LimitedTo;
import com.wellpoint.wpd.common.search.request.AdvancedSearchRequest;
import com.wellpoint.wpd.common.search.response.SearchResponse;
import com.wellpoint.wpd.common.search.result.SearchResult;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.search.pagination.Paginator;
import com.wellpoint.wpd.web.search.util.SearchUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdvancedSearchBackingBean extends SearchBackingBean {

    private String stateCode;

    private String lobPrintString;

    private String businessEntityPrintString;

    private String businessGroupPrintString;

    private HtmlPanelGrid typePanel = null;

    private HtmlPanelGrid panel = null;

    private HtmlPanelGrid mandatePanel = null;

    private HtmlPanelGrid contractMandatePanel = null;

    private HtmlPanelGrid psTypePanel = null;

    private HtmlPanelGrid psMandatePanel = null;

    private HtmlPanelGrid federalPanel = null;

    private HtmlPanelGrid bcTypePanel = null;

    private HtmlPanelGrid bcMandatePanel = null;

    private HtmlPanelGrid bcFederalPanel = null;

    private HtmlPanelGrid benefitTypePanel = null;

    private HtmlPanelGrid benefitMandatePanel = new HtmlPanelGrid();;

    private HtmlPanelGrid psFederalPanel = null;

    private HtmlPanelGrid printCriteriaPanel = null;

    private HtmlPanelGrid selectObjectPanel = null;

    private HtmlPanelGrid printPanel = null;

    AdvancedSearchRequest advancedSearchRequest;

    private HtmlPanelGrid contractPanel = null;

    private HtmlPanelGrid productPanel = null;

    private HtmlPanelGrid productstructurePanel = null;

    private HtmlPanelGrid benefitcomponentPanel = null;

    private HtmlPanelGrid benefitPanel = null;

    private HtmlPanelGrid benefitlevelPanel = null;

    private HtmlPanelGrid notesPanel = null;

    private AdvancedAttribute contractAdvancedAttribute[];

    private AdvancedAttribute productAdvancedAttribute[];

    private AdvancedAttribute productstructureAdvancedAttribute[];

    private AdvancedAttribute benefitcomponentAdvancedAttribute[];

    private AdvancedAttribute benefitAdvancedAttribute[];

    private AdvancedAttribute benefitlevelAdvancedAttribute[];

    private AdvancedAttribute notesAdvancedAttribute[];

    private String contract = null;

    private String product = null;

    private String productStructure = null;

    private String benefitComponent = null;

    private String benefit = null;

    private String benefitLevel = null;

    private String notes = null;

    private String selectedObject;

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
    private String objectString;

    /**
     * @return Returns the selectedObject.
     */
    public String getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject
     *            The selectedObject to set.
     */
    public void setSelectedObject(String selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * @return Returns the businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }

    /**
     * @param businessEntity
     *            The businessEntity to set.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    /**
     * @return Returns the businessEntityCodeList.
     */
    public List getBusinessEntityCodeList() {
        return businessEntityCodeList;
    }

    /**
     * @param businessEntityCodeList
     *            The businessEntityCodeList to set.
     */
    public void setBusinessEntityCodeList(List businessEntityCodeList) {
        this.businessEntityCodeList = businessEntityCodeList;
    }

    /**
     * @return Returns the businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }

    /**
     * @param businessGroup
     *            The businessGroup to set.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }

    /**
     * @return Returns the businessGroupCodeList.
     */
    public List getBusinessGroupCodeList() {
        return businessGroupCodeList;
    }

    /**
     * @param businessGroupCodeList
     *            The businessGroupCodeList to set.
     */
    public void setBusinessGroupCodeList(List businessGroupCodeList) {
        this.businessGroupCodeList = businessGroupCodeList;
    }

    /**
     * @return Returns the lineOfBusiness.
     */
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    /**
     * @param lineOfBusiness
     *            The lineOfBusiness to set.
     */
    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    /**
     * @return Returns the lineOfBusinessCodeList.
     */
    public List getLineOfBusinessCodeList() {
        return lineOfBusinessCodeList;
    }

    /**
     * @param lineOfBusinessCodeList
     *            The lineOfBusinessCodeList to set.
     */
    public void setLineOfBusinessCodeList(List lineOfBusinessCodeList) {
        this.lineOfBusinessCodeList = lineOfBusinessCodeList;
    }
    /**
     * 
     * @param valid
     * @return
     */
    public boolean validate(String valid) {
        return true;
    }

    /**
     *  method for setting panel
     *
     */
    public AdvancedSearchBackingBean() {
        int i = 0;
        try {
            advancedSearchRequest = (AdvancedSearchRequest) getServiceRequest(ServiceManager.ADVANCED_SEARCH_REQUEST);
            AdvancedSearchCriteria advancedSearchCriteria = advancedSearchRequest
                    .getAdvancedSearchCriteria();
            List searchObjects = advancedSearchCriteria
                    .getAdvancedSearchObjects();
            typePanel = new HtmlPanelGrid();
            for (i = 0; i < searchObjects.size(); i++) {
                AdvancedSearchObject advancedSearchObject = (AdvancedSearchObject) searchObjects
                        .get(i);
                if (SearchConstants.CONTRACT
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    List sample = advancedSearchObject.getAdvancedAttributes();
                    Object object[] = sample.toArray();
                    contractAdvancedAttribute = (getAdvancedAttributeArray(object));
                    contractPanel = new HtmlPanelGrid();
                    setPanel("contractAdvancedAttribute",
                            contractAdvancedAttribute, contractPanel,
                            typePanel, SearchConstants.CONTRACT);

                } else if (SearchConstants.PRODUCT
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    List sample = advancedSearchObject.getAdvancedAttributes();
                    Object object[] = sample.toArray();
                    productAdvancedAttribute = (getAdvancedAttributeArray(object));
                    productPanel = new HtmlPanelGrid();
                    setPanel("productAdvancedAttribute",
                            productAdvancedAttribute, productPanel, typePanel,
                            SearchConstants.PRODUCT);

                } else if (SearchConstants.PRODUCT_STRUCTURES
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    List sample = advancedSearchObject.getAdvancedAttributes();
                    Object object[] = sample.toArray();
                    productstructureAdvancedAttribute = (getAdvancedAttributeArray(object));
                    productstructurePanel = new HtmlPanelGrid();
                    setPanel("productstructureAdvancedAttribute",
                            productstructureAdvancedAttribute,
                            productstructurePanel, typePanel,
                            SearchConstants.PRODUCT_STRUCTURES);

                } else if (SearchConstants.BENEFIT_COMPONENTS
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    List sample = advancedSearchObject.getAdvancedAttributes();
                    Object object[] = sample.toArray();
                    benefitcomponentAdvancedAttribute = (getAdvancedAttributeArray(object));
                    benefitcomponentPanel = new HtmlPanelGrid();
                    setPanel("benefitcomponentAdvancedAttribute",
                            benefitcomponentAdvancedAttribute,
                            benefitcomponentPanel, typePanel,
                            SearchConstants.BENEFIT_COMPONENTS);

                } else if (SearchConstants.BENEFIT
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    List sample = advancedSearchObject.getAdvancedAttributes();
                    Object object[] = sample.toArray();
                    benefitAdvancedAttribute = (getAdvancedAttributeArray(object));
                    benefitPanel = new HtmlPanelGrid();
                    setPanel("benefitAdvancedAttribute",
                            benefitAdvancedAttribute, benefitPanel, typePanel,
                            SearchConstants.BENEFIT);

                } else if (SearchConstants.BENEFIT_LEVEL
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    List sample = advancedSearchObject.getAdvancedAttributes();
                    Object object[] = sample.toArray();
                    benefitlevelAdvancedAttribute = (getAdvancedAttributeArray(object));
                    benefitlevelPanel = new HtmlPanelGrid();
                    setPanel("benefitlevelAdvancedAttribute",
                            benefitlevelAdvancedAttribute, benefitlevelPanel,
                            typePanel, SearchConstants.BENEFIT_LEVEL);

                } else if (SearchConstants.NOTES
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    List sample = advancedSearchObject.getAdvancedAttributes();
                    Object object[] = sample.toArray();
                    notesAdvancedAttribute = (getAdvancedAttributeArray(object));
                    notesPanel = new HtmlPanelGrid();
                    setPanel("notesAdvancedAttribute", notesAdvancedAttribute,
                            notesPanel, typePanel, SearchConstants.NOTES);

                }
            }
        } catch (Exception e) {
        	Logger.logError(e);
            Logger.logError(e.getMessage());
        }
    }

    /**
     * @return Returns the productPanel.
     */
    public HtmlPanelGrid getProductPanel() {
        return productPanel;
    }

    /**
     * @param productPanel
     *            The productPanel to set.
     */
    public void setProductPanel(HtmlPanelGrid productPanel) {
        this.productPanel = productPanel;
    }
    /**
     * 
     * @param object
     * @return
     */
    private AdvancedAttribute[] getAdvancedAttributeArray(Object object[]) {
        AdvancedAttribute[] advancedAttributes = new AdvancedAttribute[object.length];
        for (int i = 0; i < object.length; i++) {
            advancedAttributes[i] = (AdvancedAttribute) object[i];
        }
        return advancedAttributes;
    }

    /**
     * @return Returns the productAdvancedAttribute.
     */
    public AdvancedAttribute[] getProductAdvancedAttribute() {
        return productAdvancedAttribute;
    }

    /**
     * @param productAdvancedAttribute
     *            The productAdvancedAttribute to set.
     */
    public void setProductAdvancedAttribute(
            AdvancedAttribute[] productAdvancedAttribute) {
        this.productAdvancedAttribute = productAdvancedAttribute;
    }
    /**
     * 
     * @param attrbArray
     * @param advanceattr
     * @param htmlPanelGrid
     * @param typePanelGrid
     * @param selectedObjectForPanel
     */
    private void setPanel(String attrbArray, AdvancedAttribute[] advanceattr,
            HtmlPanelGrid htmlPanelGrid, HtmlPanelGrid typePanelGrid,
            String selectedObjectForPanel) {
        htmlPanelGrid.setColumns(5);
        htmlPanelGrid.setBorder(0);
        htmlPanelGrid.setStyleClass("outputText");
        htmlPanelGrid.setCellpadding("2");
        //htmlPanelGrid.setCellspacing("1");
        htmlPanelGrid.setHeaderClass("Head");

        benefitMandatePanel.setColumns(5);
        benefitMandatePanel.setBorder(0);
        benefitMandatePanel.setStyleClass("outputText");
        benefitMandatePanel.setCellpadding("2");
        int stateVal = 0;
        for (int i = 0; i < advanceattr.length; i++) {

            addInputForAttribute(htmlPanelGrid, attrbArray, i, advanceattr,
                    selectedObjectForPanel);

        }

    }

    /**
     * @param searchCriteria
     *            The searchCriteria to set.
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
            LimitedTo limitedTo = new LimitedTo();
            limitedTo.setBusinessEntity(businessEntityCodeList);
            limitedTo.setBusinessGroup(businessGroupCodeList);
            limitedTo.setLineOfBusiness(lineOfBusinessCodeList);
            /*START CARS*/
            //Market Business Unit
            limitedTo.setMarketBusinessUnit(marketBusinessUnitList);
            /*START CARS*/
            AdvancedSearchCriteria advancedSearchCriteria = advancedSearchRequest
                    .getAdvancedSearchCriteria();
            advancedSearchCriteria.setLimitedTo(limitedTo);
            List searchObjects = advancedSearchCriteria
                    .getAdvancedSearchObjects();
            for (int i = 0; i < searchObjects.size(); i++) {
                AdvancedSearchObject advancedSearchObject = (AdvancedSearchObject) searchObjects
                        .get(i);
                if (SearchConstants.CONTRACT
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    advancedSearchObject.setChecked(getSelectedObject()
                            .equalsIgnoreCase("CONTRACT") ? true : false);
                   
                } else if (SearchConstants.PRODUCT
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    advancedSearchObject.setChecked(getSelectedObject()
                            .equalsIgnoreCase("PRODUCT") ? true : false);
                    
                } else if (SearchConstants.PRODUCT_STRUCTURES
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    advancedSearchObject.setChecked(getSelectedObject()
                            .equalsIgnoreCase("PRODUCT_STRUCTURES") ? true
                            : false);
                    
                } else if (SearchConstants.BENEFIT_COMPONENTS
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    advancedSearchObject.setChecked(getSelectedObject()
                            .equalsIgnoreCase("BENEFIT_COMPONENTS") ? true
                            : false);
                    
                } else if (SearchConstants.BENEFIT
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    advancedSearchObject.setChecked(getSelectedObject()
                            .equalsIgnoreCase("BENEFIT") ? true : false);
                    
                } else if (SearchConstants.BENEFIT_LEVEL
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    advancedSearchObject.setChecked(getSelectedObject()
                            .equalsIgnoreCase("BENEFIT_LEVEL") ? true : false);
                    
                } else if (SearchConstants.NOTES
                        .equalsIgnoreCase(advancedSearchObject.getType())) {
                    advancedSearchObject.setChecked(getSelectedObject()
                            .equalsIgnoreCase("NOTES") ? true : false);
                   
                }

                if (advancedSearchObject.isChecked()) {
                    //changeCriteria(advancedSearchObject);
                }

            }

            SearchResponse searchResponse = (SearchResponse) this
                    .executeService(advancedSearchRequest);
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
                return "AdvancedSearchCriteria";
            }
        } catch (Exception e) {
            return "AdvancedSearchCriteria";
        }
        // For tree component to expand the nodes
        getRequest().setAttribute("SEARCH_PERFORMED", "SEARCH_PERFORMED");
        getSession().setAttribute("SEARCH_TYPE",
                String.valueOf(SearchConstants.ADVANCED_SEARCH));
        String s = String.valueOf(getSession().getAttribute("SEARCH_TYPE"));
        setBreadCrumbText("Search >> Search Summary");
        return "SearchSummary";

    }

    /**
     * @return Returns the benefitAdvancedAttribute.
     */
    public AdvancedAttribute[] getBenefitAdvancedAttribute() {
        return benefitAdvancedAttribute;
    }

    /**
     * @param benefitAdvancedAttribute
     *            The benefitAdvancedAttribute to set.
     */
    public void setBenefitAdvancedAttribute(
            AdvancedAttribute[] benefitAdvancedAttribute) {
        this.benefitAdvancedAttribute = benefitAdvancedAttribute;
    }

    /**
     * @return Returns the benefitcomponentAdvancedAttribute.
     */
    public AdvancedAttribute[] getBenefitcomponentAdvancedAttribute() {
        return benefitcomponentAdvancedAttribute;
    }

    /**
     * @param benefitcomponentAdvancedAttribute
     *            The benefitcomponentAdvancedAttribute to set.
     */
    public void setBenefitcomponentAdvancedAttribute(
            AdvancedAttribute[] benefitcomponentAdvancedAttribute) {
        this.benefitcomponentAdvancedAttribute = benefitcomponentAdvancedAttribute;
    }

    /**
     * @return Returns the benefitcomponentPanel.
     */
    public HtmlPanelGrid getBenefitcomponentPanel() {
        return benefitcomponentPanel;
    }

    /**
     * @param benefitcomponentPanel
     *            The benefitcomponentPanel to set.
     */
    public void setBenefitcomponentPanel(HtmlPanelGrid benefitcomponentPanel) {
        this.benefitcomponentPanel = benefitcomponentPanel;
    }

    /**
     * @return Returns the benefitlevelAdvancedAttribute.
     */
    public AdvancedAttribute[] getBenefitlevelAdvancedAttribute() {
        return benefitlevelAdvancedAttribute;
    }

    /**
     * @param benefitlevelAdvancedAttribute
     *            The benefitlevelAdvancedAttribute to set.
     */
    public void setBenefitlevelAdvancedAttribute(
            AdvancedAttribute[] benefitlevelAdvancedAttribute) {
        this.benefitlevelAdvancedAttribute = benefitlevelAdvancedAttribute;
    }

    /**
     * @return Returns the benefitlevelPanel.
     */
    public HtmlPanelGrid getBenefitlevelPanel() {
        return benefitlevelPanel;
    }

    /**
     * @param benefitlevelPanel
     *            The benefitlevelPanel to set.
     */
    public void setBenefitlevelPanel(HtmlPanelGrid benefitlevelPanel) {
        this.benefitlevelPanel = benefitlevelPanel;
    }

    /**
     * @return Returns the benefitPanel.
     */
    public HtmlPanelGrid getBenefitPanel() {
        return benefitPanel;
    }

    /**
     * @param benefitPanel
     *            The benefitPanel to set.
     */
    public void setBenefitPanel(HtmlPanelGrid benefitPanel) {
        this.benefitPanel = benefitPanel;
    }

    /**
     * @return Returns the contractAdvancedAttribute.
     */
    public AdvancedAttribute[] getContractAdvancedAttribute() {
        return contractAdvancedAttribute;
    }

    /**
     * @param contractAdvancedAttribute
     *            The contractAdvancedAttribute to set.
     */
    public void setContractAdvancedAttribute(
            AdvancedAttribute[] contractAdvancedAttribute) {
        this.contractAdvancedAttribute = contractAdvancedAttribute;
    }

    /**
     * @return Returns the contractPanel.
     */
    public HtmlPanelGrid getContractPanel() {
        return contractPanel;
    }

    /**
     * @param contractPanel
     *            The contractPanel to set.
     */
    public void setContractPanel(HtmlPanelGrid contractPanel) {
        this.contractPanel = contractPanel;
    }

    /**
     * @return Returns the notesAdvancedAttribute.
     */
    public AdvancedAttribute[] getNotesAdvancedAttribute() {
        return notesAdvancedAttribute;
    }

    /**
     * @param notesAdvancedAttribute
     *            The notesAdvancedAttribute to set.
     */
    public void setNotesAdvancedAttribute(
            AdvancedAttribute[] notesAdvancedAttribute) {
        this.notesAdvancedAttribute = notesAdvancedAttribute;
    }

    /**
     * @return Returns the notesPanel.
     */
    public HtmlPanelGrid getNotesPanel() {
        return notesPanel;
    }

    /**
     * @param notesPanel
     *            The notesPanel to set.
     */
    public void setNotesPanel(HtmlPanelGrid notesPanel) {
        this.notesPanel = notesPanel;
    }

    /**
     * @return Returns the productstructureAdvancedAttribute.
     */
    public AdvancedAttribute[] getProductstructureAdvancedAttribute() {
        return productstructureAdvancedAttribute;
    }

    /**
     * @param productstructureAdvancedAttribute
     *            The productstructureAdvancedAttribute to set.
     */
    public void setProductstructureAdvancedAttribute(
            AdvancedAttribute[] productstructureAdvancedAttribute) {
        this.productstructureAdvancedAttribute = productstructureAdvancedAttribute;
    }

    /**
     * @return Returns the productstructurePanel.
     */
    public HtmlPanelGrid getProductstructurePanel() {
        return productstructurePanel;
    }

    /**
     * @param productstructurePanel
     *            The productstructurePanel to set.
     */
    public void setProductstructurePanel(HtmlPanelGrid productstructurePanel) {
        this.productstructurePanel = productstructurePanel;
    }

    /**
     * @return Returns the objectString.
     */
    public String getObjectString() {
        return objectString;
    }

    /**
     * @param objectString
     *            The objectString to set.
     */

    /**
     * @return Returns the benefit.
     */
    public String getBenefit() {
        return benefit;
    }

    /**
     * @param benefit
     *            The benefit to set.
     */
    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    /**
     * @return Returns the benefitComponent.
     */
    public String getBenefitComponent() {
        return benefitComponent;
    }

    /**
     * @param benefitComponent
     *            The benefitComponent to set.
     */
    public void setBenefitComponent(String benefitComponent) {
        this.benefitComponent = benefitComponent;
    }

    /**
     * @return Returns the benefitLevel.
     */
    public String getBenefitLevel() {
        return benefitLevel;
    }

    /**
     * @param benefitLevel
     *            The benefitLevel to set.
     */
    public void setBenefitLevel(String benefitLevel) {
        this.benefitLevel = benefitLevel;
    }

    /**
     * @return Returns the contract.
     */
    public String getContract() {
        return contract;
    }

    /**
     * @param contract
     *            The contract to set.
     */
    public void setContract(String contract) {
        this.contract = contract;
    }

    /**
     * @return Returns the notes.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes
     *            The notes to set.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return Returns the product.
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product
     *            The product to set.
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return Returns the productStructure.
     */
    public String getProductStructure() {
        return productStructure;
    }

    /**
     * @param productStructure
     *            The productStructure to set.
     */
    public void setProductStructure(String productStructure) {
        this.productStructure = productStructure;
    }

    /**
     * @param objectString
     *            The objectString to set.
     */
    public void setObjectString(String objectString) {
        this.objectString = objectString;
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
        ValueBinding value = null;
        HtmlSelectOneRadio htmlSelectOneRadio = new HtmlSelectOneRadio();
        htmlSelectOneRadio.setLayout("pageDirection");
        htmlSelectOneRadio.setOnclick("show(this)");
        htmlSelectOneRadio.setId("rdObjectType");
        ArrayList radioBtnOptionsList = new ArrayList();

        try {

            if (getUser().isAuthorized(WebConstants.CONTRACT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.ADVANCED_SEARCH_TASK)) {
                radioBtnOptionsList.add(new SelectItem(new String("CONTRACT"),
                        "Contract"));
            }
            if (getUser().isAuthorized(WebConstants.PRODUCT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.ADVANCED_SEARCH_TASK)) {
                radioBtnOptionsList.add(new SelectItem(new String("PRODUCT"),
                        "Product"));
            }
            if (getUser().isAuthorized(WebConstants.PRODUCT_STRUCTURES_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.ADVANCED_SEARCH_TASK)) {
                radioBtnOptionsList.add(new SelectItem(new String(
                        "PRODUCT_STRUCTURES"), "Product Structure"));
            }
            if (getUser().isAuthorized(WebConstants.BENEFIT_COMPONENTS_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.ADVANCED_SEARCH_TASK)) {
                radioBtnOptionsList.add(new SelectItem(new String(
                        "BENEFIT_COMPONENTS"), "Benefit Component"));
            }
            if (getUser().isAuthorized(WebConstants.BENEFIT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.ADVANCED_SEARCH_TASK)) {
                radioBtnOptionsList.add(new SelectItem(new String("BENEFIT"),
                        "Benefit"));
            }
            if (getUser().isAuthorized(WebConstants.BENEFIT_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.ADVANCED_SEARCH_TASK)) {
                radioBtnOptionsList.add(new SelectItem(new String(
                        "BENEFIT_LEVEL"), "Benefit Level/Line"));
            }
            if (getUser().isAuthorized(WebConstants.NOTES_MODULE,
                    WebConstants.MAINTAIN_TASK)
                    && getUser().isAuthorized(
                            WebConstants.SEARCH_ENGINE_MODULE,
                            WebConstants.ADVANCED_SEARCH_TASK)) {
                radioBtnOptionsList.add(new SelectItem(new String("NOTES"),
                        "Notes"));
            }

        } catch (ReferenceSyntaxException e) {
            // TODO Auto-generated catch block
        	Logger.logError(e);
        } catch (SevereException e) {
            // TODO Auto-generated catch block
        	Logger.logError(e);
        }
        UISelectItems radioBtnOptions = new UISelectItems();
        radioBtnOptions.setValue(radioBtnOptionsList);
        value = FacesContext.getCurrentInstance().getApplication()
                .createValueBinding(
                        "#{advancedSearchBackingBean.selectedObject}");
        htmlSelectOneRadio.setValueBinding("value", value);
        htmlSelectOneRadio.getChildren().add(radioBtnOptions);
        panel.getChildren().add(htmlSelectOneRadio);
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
        printPanel.setColumns(2);
        printPanel.setBorder(0);

        printPanel.setStyleClass("outputText");
        printPanel.setCellpadding("3");
        printPanel.setCellspacing("1");

        printPanel.setHeaderClass("Head");
        ValueBinding value = null;
        HtmlSelectOneRadio htmlSelectOneRadio = new HtmlSelectOneRadio();
        htmlSelectOneRadio.setLayout("pageDirection");
        htmlSelectOneRadio.setOnclick("show(this)");
        htmlSelectOneRadio.setId("rdObjectType");
        ArrayList radioBtnOptionsList = new ArrayList();
        SelectItem selectItem = null;
        try {
            if (getSelectedObject().equalsIgnoreCase("CONTRACT")) {

                selectItem = new SelectItem(new String("CONTRACT"), "Contract");
                //selectItem.setDisabled(true);
                radioBtnOptionsList.add(selectItem);
            } else if (getSelectedObject().equalsIgnoreCase("PRODUCT")) {
                selectItem = new SelectItem(new String("PRODUCT"), "Product");
                selectItem.setDisabled(true);
                radioBtnOptionsList.add(selectItem);
            } else if (getSelectedObject().equalsIgnoreCase(
                    "PRODUCT_STRUCTURES")) {
                selectItem = new SelectItem(new String("PRODUCT_STRUCTURES"),
                        "Product Structure");
                selectItem.setDisabled(true);
                radioBtnOptionsList.add(selectItem);
            } else if (getSelectedObject().equalsIgnoreCase(
                    "BENEFIT_COMPONENTS")) {
                selectItem = new SelectItem(new String("BENEFIT_COMPONENTS"),
                        "Benefit Component");
                selectItem.setDisabled(true);
                radioBtnOptionsList.add(selectItem);
            } else if (getSelectedObject().equalsIgnoreCase("BENEFIT")) {
                selectItem = new SelectItem(new String("BENEFIT"), "Benefit");
                selectItem.setDisabled(true);
                radioBtnOptionsList.add(selectItem);
            } else if (getSelectedObject().equalsIgnoreCase("BENEFIT_LEVEL")) {
                selectItem = new SelectItem(new String("BENEFIT_LEVEL"),
                        "Benefit Level");
                selectItem.setDisabled(true);
                radioBtnOptionsList.add(selectItem);
            } else if (getSelectedObject().equalsIgnoreCase("NOTES")) {
                selectItem = new SelectItem(new String("NOTES"), "Notes");
                selectItem.setDisabled(true);
                radioBtnOptionsList.add(selectItem);
            }
        } catch (ReferenceSyntaxException e) {
            // TODO Auto-generated catch block
        	Logger.logError(e);
        }
        UISelectItems radioBtnOptions = new UISelectItems();
        radioBtnOptions.setValue(radioBtnOptionsList);
        htmlSelectOneRadio.getChildren().add(radioBtnOptions);
        printPanel.getChildren().add(htmlSelectOneRadio);

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
     * @return Returns the printCriteriaPanel.
     */
    public HtmlPanelGrid getPrintCriteriaPanel() {
        setPrintCritPanel();
        return printCriteriaPanel;

    }

    /**
     * @param printCriteriaPanel
     *            The printCriteriaPanel to set.
     */
    public void setPrintCriteriaPanel(HtmlPanelGrid printCriteriaPanel) {

        this.printCriteriaPanel = printCriteriaPanel;
    }
    /**
     * method for creating Print panel
     *
     */
            
    private void setPrintCritPanel() {
        printCriteriaPanel = new HtmlPanelGrid();

        printCriteriaPanel.setColumns(4);
        printCriteriaPanel.setBorder(0);

        printCriteriaPanel.setStyleClass("outputText");
        printCriteriaPanel.setCellpadding("2");
        //htmlPanelGrid.setCellspacing("1");
        AdvancedAttribute advanceattr[] = null;

        
        if ("CONTRACT".equalsIgnoreCase(getSelectedObject())) {
            advanceattr = contractAdvancedAttribute;
        } else if ("PRODUCT".equalsIgnoreCase(getSelectedObject())) {
            advanceattr = productAdvancedAttribute;
        } else if ("PRODUCT_STRUCTURES".equalsIgnoreCase(getSelectedObject())) {
            advanceattr = productstructureAdvancedAttribute;
        } else if ("BENEFIT_COMPONENTS".equalsIgnoreCase(getSelectedObject())) {
            advanceattr = benefitcomponentAdvancedAttribute;
        } else if ("BENEFIT".equalsIgnoreCase(getSelectedObject())) {
            advanceattr = benefitAdvancedAttribute;
        } else if ("BENEFIT_LEVEL".equalsIgnoreCase(getSelectedObject())) {
            advanceattr = benefitlevelAdvancedAttribute;
        } else if ("NOTES".equalsIgnoreCase(getSelectedObject())) {
            advanceattr = notesAdvancedAttribute;
        }

        printCriteriaPanel.setHeaderClass("Head");
        int lastIndex = getLastAttribute(advanceattr);
        for (int i = 0; (advanceattr!=null&&i < advanceattr.length); i++) {
            if (advanceattr[i].isChecked()) {

                HtmlOutputText htmlOutputtext = new HtmlOutputText();
                htmlOutputtext.setValue(advanceattr[i].getName());
                printCriteriaPanel.getChildren().add(htmlOutputtext);
                HtmlOutputText htmlSigntext = new HtmlOutputText();
                htmlSigntext.setValue(advanceattr[i].getSign());
                printCriteriaPanel.getChildren().add(htmlSigntext);
                HtmlOutputText htmlCriteriatext = new HtmlOutputText();
                htmlCriteriatext.setValue(advanceattr[i].getCriteria());
                //htmlCriteriatext.setStyle("width:312px");
                htmlCriteriatext.setStyleClass("formInputField");
                printCriteriaPanel.getChildren().add(htmlCriteriatext);
                if (i != lastIndex) {
                    HtmlOutputText htmlRelationtext = new HtmlOutputText();
                    htmlRelationtext.setValue(advanceattr[i].getRelation());
                    printCriteriaPanel.getChildren().add(htmlRelationtext);
                }
            }
        }
    }

    /**
     * @return Returns the selectObjectPanel.
     */
    public HtmlPanelGrid getSelectObjectPanel() {
        selectObjectPanel = new HtmlPanelGrid();
        //panel.setWidth("500");
        selectObjectPanel.setColumns(2);
        selectObjectPanel.setBorder(0);

        selectObjectPanel.setStyleClass("outputText");
        selectObjectPanel.setCellpadding("3");
        selectObjectPanel.setCellspacing("1");

        selectObjectPanel.setHeaderClass("Head");
        ValueBinding value = null;
        HtmlOutputText htmlOutputtext = new HtmlOutputText();
        if ("CONTRACT".equalsIgnoreCase(getSelectedObject())) {
            htmlOutputtext.setValue("Contract");
        } else if ("PRODUCT".equalsIgnoreCase(getSelectedObject())) {
            htmlOutputtext.setValue("Product");
        } else if ("PRODUCT_STRUCTURES".equalsIgnoreCase(getSelectedObject())) {
            htmlOutputtext.setValue("product Structure");
        } else if ("BENEFIT_COMPONENTS".equalsIgnoreCase(getSelectedObject())) {
            htmlOutputtext.setValue("Benefit Components");
        } else if ("BENEFIT".equalsIgnoreCase(getSelectedObject())) {
            htmlOutputtext.setValue("Benefit");
        } else if ("BENEFIT_LEVEL".equalsIgnoreCase(getSelectedObject())) {
            htmlOutputtext.setValue("Benefit Level");
        } else if ("NOTES".equalsIgnoreCase(getSelectedObject())) {
            htmlOutputtext.setValue("Notes");
        }

        selectObjectPanel.getChildren().add(htmlOutputtext);

        return selectObjectPanel;
    }

    /**
     * @param selectObjectPanel
     *            The selectObjectPanel to set.
     */
    public void setSelectObjectPanel(HtmlPanelGrid selectObjectPanel) {
        this.selectObjectPanel = selectObjectPanel;
    }

    /**
     * @return Returns the lobPrintString.
     */
    public String getLobPrintString() {
        //lineOfBusiness
        int flag = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (lineOfBusiness != null) {
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
        if (businessEntity != null) {
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
        if (businessGroup != null) {
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
     * 
     * @param advancedAttribute
     * @return
     */
    private int getLastAttribute(AdvancedAttribute advancedAttribute[]) {
        int lastIndex = 0;
     
        for (int attrCnt = 0;( advancedAttribute!=null&&attrCnt < advancedAttribute.length); attrCnt++) {
            AdvancedAttribute attr = advancedAttribute[attrCnt];
            if (attr.isChecked())
                lastIndex = attrCnt;
        }
        return lastIndex;
    }

    /**
     * @return Returns the typePanel.
     */
    public HtmlPanelGrid getTypePanel() {
        return typePanel;
    }

    /**
     * @param typePanel
     *            The typePanel to set.
     */
    public void setTypePanel(HtmlPanelGrid typePanel) {
        this.typePanel = typePanel;
    }

    /**
     * @return Returns the federalPanel.
     */
    public HtmlPanelGrid getFederalPanel() {
        return federalPanel;
    }

    /**
     * @param federalPanel
     *            The federalPanel to set.
     */
    public void setFederalPanel(HtmlPanelGrid federalPanel) {
        this.federalPanel = federalPanel;
    }

    /**
     * @return Returns the mandatePanel.
     */
    public HtmlPanelGrid getMandatePanel() {
        return mandatePanel;
    }

    /**
     * @param mandatePanel
     *            The mandatePanel to set.
     */
    public void setMandatePanel(HtmlPanelGrid mandatePanel) {
        this.mandatePanel = mandatePanel;
    }

    /**
     * @return Returns the stateCode.
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * @param stateCode
     *            The stateCode to set.
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * @return Returns the psTypePanel.
     */
    public HtmlPanelGrid getPsTypePanel() {
        return psTypePanel;
    }

    /**
     * @param psTypePanel
     *            The psTypePanel to set.
     */
    public void setPsTypePanel(HtmlPanelGrid psTypePanel) {
        this.psTypePanel = psTypePanel;
    }

    /**
     * @return Returns the psMandatePanel.
     */
    public HtmlPanelGrid getPsMandatePanel() {
        return psMandatePanel;
    }

    /**
     * @param psMandatePanel
     *            The psMandatePanel to set.
     */
    public void setPsMandatePanel(HtmlPanelGrid psMandatePanel) {
        this.psMandatePanel = psMandatePanel;
    }

    /**
     * @return Returns the psFederalPanel.
     */
    public HtmlPanelGrid getPsFederalPanel() {
        return psFederalPanel;
    }

    /**
     * @param psFederalPanel
     *            The psFederalPanel to set.
     */
    public void setPsFederalPanel(HtmlPanelGrid psFederalPanel) {
        this.psFederalPanel = psFederalPanel;
    }

    /**
     * @return Returns the bcFederalPanel.
     */
    public HtmlPanelGrid getBcFederalPanel() {
        return bcFederalPanel;
    }

    /**
     * @param bcFederalPanel
     *            The bcFederalPanel to set.
     */
    public void setBcFederalPanel(HtmlPanelGrid bcFederalPanel) {
        this.bcFederalPanel = bcFederalPanel;
    }

    /**
     * @return Returns the bcMandatePanel.
     */
    public HtmlPanelGrid getBcMandatePanel() {
        return bcMandatePanel;
    }

    /**
     * @param bcMandatePanel
     *            The bcMandatePanel to set.
     */
    public void setBcMandatePanel(HtmlPanelGrid bcMandatePanel) {
        this.bcMandatePanel = bcMandatePanel;
    }

    /**
     * @return Returns the bcTypePanel.
     */
    public HtmlPanelGrid getBcTypePanel() {
        return bcTypePanel;
    }

    /**
     * @param bcTypePanel
     *            The bcTypePanel to set.
     */
    public void setBcTypePanel(HtmlPanelGrid bcTypePanel) {
        this.bcTypePanel = bcTypePanel;
    }

    /**
     * @return Returns the contractMandatePanel.
     */
    public HtmlPanelGrid getContractMandatePanel() {
        return contractMandatePanel;
    }

    /**
     * @param contractMandatePanel
     *            The contractMandatePanel to set.
     */
    public void setContractMandatePanel(HtmlPanelGrid contractMandatePanel) {
        this.contractMandatePanel = contractMandatePanel;
    }

    /**
     * @return Returns the benefitMandatePanel.
     */
    public HtmlPanelGrid getBenefitMandatePanel() {
        return benefitMandatePanel;
    }

    /**
     * @param benefitMandatePanel
     *            The benefitMandatePanel to set.
     */
    public void setBenefitMandatePanel(HtmlPanelGrid benefitMandatePanel) {
        this.benefitMandatePanel = benefitMandatePanel;
    }

    /**
     * @return Returns the benefitTypePanel.
     */
    public HtmlPanelGrid getBenefitTypePanel() {
        return benefitTypePanel;
    }

    /**
     * @param benefitTypePanel
     *            The benefitTypePanel to set.
     */
    public void setBenefitTypePanel(HtmlPanelGrid benefitTypePanel) {
        this.benefitTypePanel = benefitTypePanel;
    }
    /**
     * 
     * @param htmlPanelGrid
     * @param attrbArray
     * @param i
     * @param advanceattr
     * @param selectedObject
     */
    private void addInputForAttribute(HtmlPanelGrid htmlPanelGrid,
            String attrbArray, int i, AdvancedAttribute advanceattr[],
            String selectedObject) {
        HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
        ValueBinding value = FacesContext.getCurrentInstance().getApplication()
                .createValueBinding(
                        "#{advancedSearchBackingBean." + attrbArray + "[" + i
                                + "].checked}");

        booleanCheckbox.setValueBinding("value", value);
        booleanCheckbox.setId("markChecked"+selectedObject+"_"+i);
        htmlPanelGrid.getChildren().add(booleanCheckbox);
        HtmlOutputText htmlOutputtext = new HtmlOutputText();
        value = FacesContext.getCurrentInstance().getApplication()
                .createValueBinding(
                        "#{advancedSearchBackingBean." + attrbArray + "[" + i
                                + "].name}");
        htmlOutputtext.setValueBinding("value", value);
        // WAS 7 Migration changes - UI components ID set for the missing items
        htmlOutputtext.setId("htmlOutputtext_"+selectedObject+"_"+i);
        htmlPanelGrid.getChildren().add(htmlOutputtext);

        List dropDown = new ArrayList();
        dropDown.add(new SelectItem(" "));
        dropDown.add(new SelectItem("="));
        dropDown.add(new SelectItem(">"));
        dropDown.add(new SelectItem("<"));
        dropDown.add(new SelectItem("<>"));
        dropDown.add(new SelectItem("[]"));

        UISelectItems selectitem = new UISelectItems();
        selectitem.setValue(dropDown);
        selectitem.setId("selectitem_"+selectedObject+"_"+i);
        
        
        HtmlSelectOneMenu hsol = new HtmlSelectOneMenu();
        value = FacesContext.getCurrentInstance().getApplication()
                .createValueBinding(
                        "#{advancedSearchBackingBean." + attrbArray + "[" + i
                                + "].sign}");
        hsol.setValueBinding("value", value);
        hsol.setId("hsol_"+selectedObject+"_"+i);
        hsol.getChildren().add(selectitem);
        htmlPanelGrid.getChildren().add(hsol);
        HtmlInputText htmlInputText = new HtmlInputText();
        value = FacesContext.getCurrentInstance().getApplication()
                .createValueBinding(
                        "#{advancedSearchBackingBean." + attrbArray + "[" + i
                                + "].criteria}");
        htmlInputText.setValueBinding("value", value);
        htmlInputText.setStyle("width:232px");
        htmlInputText.setMaxlength(200);
        
        htmlInputText.setId("textBoxCriteria"+selectedObject+"_"+i);
        String add = "'"+selectedObject + "_"+i+"'";
//      htmlInputText.setOnchange("markAsChecked("+add+");return false;");
        htmlInputText.setOnkeyup("markAsChecked("+add+");return false;");
//      htmlInputText.setOnmouseover("markAsChecked("+add+");return false;");
        htmlInputText.setStyleClass("formInputField");
        htmlPanelGrid.getChildren().add(htmlInputText);
        if (i != ((advanceattr.length) - 1)) {
            HtmlSelectOneRadio htmlSelectOneRadio = new HtmlSelectOneRadio();

            ArrayList radioBtnOptionsList = new ArrayList();
            radioBtnOptionsList.add(new SelectItem(new String("AND"), "AND"));
            radioBtnOptionsList.add(new SelectItem(new String("OR"), "OR"));
            UISelectItems radioBtnOptions = new UISelectItems();
            
            radioBtnOptions.setValue(radioBtnOptionsList);
            radioBtnOptions.setId("radioBtnOptions_"+selectedObject+"_"+i);
            value = FacesContext.getCurrentInstance().getApplication()
                    .createValueBinding(
                            "#{advancedSearchBackingBean." + attrbArray + "["
                                    + i + "].relation}");
            htmlSelectOneRadio.setValueBinding("value", value);
            htmlSelectOneRadio.getChildren().add(radioBtnOptions);
            htmlSelectOneRadio.setId("relation_htmlSelectOneRadio_"+selectedObject+"_"+i);
            htmlPanelGrid.getChildren().add(htmlSelectOneRadio);
        }
    }
    /**
     * 
     * @return printAdvancedSearch
     */
    public String getPrintAdvancedSearch() {
        return "printAdvancedSearch";
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
	/*END CARS*/
	/**
	 * @return Returns the marketBusinessUnitPrintString.
	 */
	public String getMarketBusinessUnitPrintString() {
        int flag = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (businessGroup != null) {
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
}