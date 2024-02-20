/*
 * Created on Sep 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.commons.lang.RandomStringUtils;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminoption.request.SaveAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionHideVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.product.bo.HideAdminOptionBO;
import com.wellpoint.wpd.common.product.request.HideProductAdminOptionRequest;
import com.wellpoint.wpd.common.product.response.HideProductAdminOptionResponse;
import com.wellpoint.wpd.common.productstructure.request.SaveAdminOptionRequestForPS;
import com.wellpoint.wpd.common.productstructure.response.SaveAdminOptionResponseForPS;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionHideBO;
import com.wellpoint.wpd.common.standardbenefit.request.HideAdminOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateAdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.response.HideAdminOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.AdministrationOptionVO;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.contract.ContractSession;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.productstructure.ProductStructureSessionObject;
import com.wellpoint.wpd.web.standardbenefit.StandardBenefitSessionObject;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U15701
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminOptionBackingBean extends WPDBackingBean {

    public static String PRODUCT_STRUCTURE_SESSION_KEY = "productStructure";

    private String benefitAdminName;

    private List adminOptionList;

    public static String CONTRACT_SESSION_KEY = "contract";

    private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD_BENEFIT_SESSION_KEY;

    private String breadCrumbName;

    private String breadCrumbText;

    private String valuesFromSessionForContract;

    private String valuesFromSessionForBenefit;

    private String valuesFromSessionForBenefitComp;

    private String valuesFromSessionForProdStruc;

    private String valuesFromSessionForProd;

    private String adminLevelOptionAssnSystemId;

    // ** enhancement nov-23-2007//
    private HtmlPanelGrid panelForBenefitComp = new HtmlPanelGrid();

    private HtmlPanelGrid headerPanelForBenefitComp = new HtmlPanelGrid();

    private Map adminOptionKeyMap = new LinkedHashMap();

    private Map checkBoxKeyMap = new HashMap();

    private List checkedItemsList;

    private boolean renderPanel;

    private String checkBoxValue = "false";

    private HtmlPanelGrid panelForView = new HtmlPanelGrid();

    private HtmlPanelGrid headerPanelForView = new HtmlPanelGrid();

   // private String valuesFromSessionForBenefitCompPrint;
    private HtmlInputHidden valuesFromSessionForBenefitCompPrint=new HtmlInputHidden();

    private boolean isAllHidden;

    BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
            .getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);

    //	Added For Enhancement
    private HtmlPanelGrid headerPanel;

    private HtmlPanelGrid panel;

    private boolean showHiddenSelected = false;

    private String componentTypeTab;

    private Map datafieldMapForAdminLevelAsscMap = new LinkedHashMap();

    private Map datafieldmapForQuestionHideFlag = new LinkedHashMap();

    private Map datafieldmapForAOHideFlag = new HashMap();

    private List adminOptionListForPrint = null;

    private boolean isPSorProductorBenefit = false;

    private String checkBoxValues;

    private boolean saveButtonRender = false;

    private String benefitCompName;

    private boolean generalBenefit = true;

    private List validationMessages = null;

    private HashMap hiddenAdminOptionFlagMap = new HashMap();
    
    private boolean adminOptionExists;
    
    private String adminOptionStates;


	
	private String hiddenForAdmin;
	
	private List adminOptionListForContract;
//	private boolean adminOptionListRetrieved;
	
	private HtmlInputHidden loadProductAdminOptions =new HtmlInputHidden();
    private HtmlInputHidden loadProductStructureAdminOptions =new HtmlInputHidden();
	
    public AdminOptionBackingBean() {
	    
        super();
        if(null != getSession()
				.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY)){
    		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
					.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
			Logger
					.logInfo("Entering the method for loading admin Methods in Benefit Component");
			String nodeClicked = (String) getSession().getAttribute(
					"SESSION_NODE_TYPE_COMP");
			this.breadCrumbName = benefitComponentSessionObject
					.getBenefitComponentName();
			if (nodeClicked.equals("Benefit-Administration")
					&& benefitComponentSessionObject.getBenefitComponentMode()
							.equalsIgnoreCase("View")) {
			
				this
						.setBreadCrumbText("Product Configuration >> BenefitComponent ("
								+ this.breadCrumbName + ") >> View");
			} 
			else{
		
			    this.setBreadCrumbText("Product Configuration >> BenefitComponent ("+this.breadCrumbName +") >> Edit");
			}
        }
    }    //added new hashmap for solving performance issue



    /**
     * @return hiddenAdminOptionFlagMap
     * 
     * Returns the hiddenAdminOptionFlagMap.
     */
    public HashMap getHiddenAdminOptionFlagMap() {
        return hiddenAdminOptionFlagMap;
    }
    /**
     * @param hiddenAdminOptionFlagMap
     * 
     * Sets the hiddenAdminOptionFlagMap.
     */
    public void setHiddenAdminOptionFlagMap(HashMap hiddenAdminOptionFlagMap) {
        this.hiddenAdminOptionFlagMap = hiddenAdminOptionFlagMap;
    }
    //End of Enhancement

    //end

    /**
     * For displying admin Method tab in benefit component.(in view)
     * 
     * @return String
     */
    public String loadForBenefitComponent() {
        BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
                .getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        Logger
                .logInfo("Entering the method for loading admin Methods in Benefit Component");
        String nodeClicked = (String) getSession().getAttribute(
                "SESSION_NODE_TYPE_COMP");
       
        this.breadCrumbName = benefitComponentSessionObject
                .getBenefitComponentName();
        if (nodeClicked.equals("Benefit-Administration")
                && benefitComponentSessionObject.getBenefitComponentMode()
                        .equalsIgnoreCase("View")) {

            this
                    .setBreadCrumbText("Product Configuration >> BenefitComponent ("
                            + this.breadCrumbName + ") >> View");
            return "benefitComponentAdminOptionView";
        } else {
        	this.getAdminOptionList();
            preparePanel(this.adminOptionList);
            this
                    .setBreadCrumbText("Product Configuration >> BenefitComponent ("
                            + this.breadCrumbName + ") >> Edit");
            getRequest().setAttribute("RETAIN_Value", "");
            return "benefitComponentAdminOption";
        }

    }


    /**
     * For loading Product Structure benefit administration page.
     * 
     * @return String.
     */
    public String loadForProductStructure() {

        this.setPSorProductorBenefit(true);

        ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
                .getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

        String nodeClicked = (String) getSession().getAttribute(
                "SESSION_NODE_TYPE_COMP");
        this.breadCrumbName = productStructureSessionObject.getName();
        if (nodeClicked.equals("BenefitDate")
                && productStructureSessionObject.getAction().equalsIgnoreCase(
                        "View")) {

            this
                    .setBreadCrumbText("Product Configuration >> Product Structure ("
                            + this.breadCrumbName + ") >> View");
            return WebConstants.PS_ADMIN_OPTION_VIEW;
        } else {

            this
                    .setBreadCrumbText("Product Configuration >> Product Structure ("
                            + this.breadCrumbName + ") >> Edit");
            return WebConstants.PS_ADMIN_OPTION;
        }

    }


    /**
     * For loading Product Benefit administration page.
     * 
     * @return String.
     */

    public String loadForProduct() {

        this.setPSorProductorBenefit(true);

        ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
                .getAttribute(WebConstants.PROD_TYPE);

        String mode = new Integer(productSessionObject.getMode()).toString();
        this.breadCrumbName = productSessionObject.getProductKeyObject()
                .getProductName();
        String nodeClicked = (String) getSession().getAttribute(
                "PRODUCT_NODE_TYPE");
        //		loads the admin option list.

        if (nodeClicked.equals("Benefit-Administration")
                && mode.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)) {

            this.setBreadCrumbText("Product Configuration >> Product ("
                    + this.breadCrumbName + ") >> View");
            return WebConstants.PRODUCT_ADMIN_OPTION_VIEW;
        } else {

            this.setBreadCrumbText("Product Configuration >>  Product ("
                    + this.breadCrumbName + ") >> Edit");
            return WebConstants.PRODUCT_ADMIN_OPTION;
        }

    }


    public String loadForContract() {
        ContractSession contractSessionObject = (ContractSession) getSession()
                .getAttribute(CONTRACT_SESSION_KEY);
        String mode = new Integer(contractSessionObject.getMode()).toString();
        this.breadCrumbName = contractSessionObject.getContractKeyObject()
                .getContractId();
        Logger
                .logInfo("Entering the method for loading admin Methods in Benefit Component");
        if (mode.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)) {

            this.setBreadCrumbText("Contract Development >> Contract ("
                    + this.breadCrumbName + ") >> View");
            return "contractAdminOptionView";
        } else {

            this.setBreadCrumbText("Contract Development >> Contract ("
                    + this.breadCrumbName + ") >> Edit");
            return "contractAdminOption";
        }

    }


    public String loadForBenefitView() {
        StandardBenefitSessionObject benefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);

        this.breadCrumbName = benefitSessionObject.getStandardBenefitName();
        Logger
                .logInfo("Entering the method for loading admin Methods in Benefit Component");
        //		if ( mode.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){

        this.setBreadCrumbText("Contract Development >> Contract ("
                + this.breadCrumbName + ") >> View");
        return "adminOptionView";
        //		}
        //		else{
        //		    
        //			this.setBreadCrumbText("Contract Development >>
        // Contract("+this.breadCrumbName +") >> Edit");
        //			return "contractAdminOption";
        //		}

    }


    public String hideProductAdminOption() {
    	getRequest().setAttribute("RETAIN_Value", "");
        ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
                .getAttribute(WebConstants.PROD_TYPE);
        //ContractSession contractSessionObject =
        // (ContractSession)getSession().getAttribute(CONTRACT_SESSION_KEY);
        BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
                .getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        HideProductAdminOptionRequest hideProductAdminOptionRequest = (HideProductAdminOptionRequest) this
                .getServiceRequest(ServiceManager.HIDE_PRODUCT_ADMIN_OPTION_REQUEST);

        hideProductAdminOptionRequest.setAdminLevelOptionAssnId(Integer
                .parseInt(this.getAdminLevelOptionAssnSystemId()));
        hideProductAdminOptionRequest.setBenefitComponentId(Integer
                .parseInt(getSession().getAttribute("BNFT_CMPNT_KEY")
                        .toString()));
        hideProductAdminOptionRequest.setEntityId(productSessionObject
                .getProductKeyObject().getProductId());
        hideProductAdminOptionRequest.setProductKeyObject(productSessionObject
                .getProductKeyObject());
        HideProductAdminOptionResponse hideProductAdminOptionResponse = (HideProductAdminOptionResponse) executeService(hideProductAdminOptionRequest);

        return "";
    }
  //WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
    public HtmlInputHidden getLoadProductAdminOptions(){
    	
        this.setPSorProductorBenefit(true);
        ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE);
        List msgList = null;
        if(null != getRequest().getAttribute("messages"))
        	msgList =(List)getRequest().getAttribute("messages");
        boolean viewMode = productSessionObject.getMode() == ProductSessionObject.VIEW_MODE;
        if(viewMode) {
        	super.setBreadCrumbText("Product Configuration >> Product ("+productSessionObject.getProductKeyObject().getProductName()+") >> " + "View");
        } else {
        	super.setBreadCrumbText("Product Configuration >> Product ("+productSessionObject.getProductKeyObject().getProductName()+") >> " + "Edit");
        }

        List adminList = null;
        panel = null;
        String productType = null;

        productType = productSessionObject.getProductKeyObject().getProductType();

        adminList = this.loadAdminOptiosForProduct();
        storeAdminOptionState(adminList);
        // For print
        this.adminOptionListForPrint = listForPrint(adminList);
        
        if (productType.equalsIgnoreCase(WebConstants.STANDARD)) {
            panel = getPanelForStandard(adminList);
        } else if (productType.equalsIgnoreCase(WebConstants.MANDATE)) {
            panel = getPanelForMandate(adminList);
        }
        
        if(!this.showHiddenSelected)
        	datafieldmapForQuestionHideFlag.clear();
        addAllMessagesToRequest(msgList);
        loadProductAdminOptions.setValue("");
        return loadProductAdminOptions;
    	//return "";
    }
    
    public void setLoadProductAdminOptions(HtmlInputHidden temp){
    	this.loadProductAdminOptions =temp;
    }
	//WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
    public HtmlInputHidden getLoadProductStructureAdminOptions(){
        this.setPSorProductorBenefit(true);
        List msgList = null;
        if(null != getRequest().getAttribute("messages"))
        	msgList =(List)getRequest().getAttribute("messages");

        ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
				.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

        this.breadCrumbName = productStructureSessionObject.getName();
        if ( productStructureSessionObject.getAction().equalsIgnoreCase(
                        "View")) {

            this.setBreadCrumbText("Product Configuration >> Product Structure ("
                            + this.breadCrumbName + ") >> View");
        } else {

            this.setBreadCrumbText("Product Configuration >> Product Structure ("
                            + this.breadCrumbName + ") >> Edit");
        }
        
        List adminList = null;

        String structureType = null;

        panel = null;
        structureType = productStructureSessionObject.getStructureType();

        adminList = this.loadAdminOptiosForProductStructure();
        storeAdminOptionState(adminList);
        
        // For print
        this.adminOptionListForPrint = listForPrint(adminList);
        
        if (structureType.equalsIgnoreCase(WebConstants.STANDARD)) {
              panel = getPanelForStandard(adminList);
        } else if (structureType.equalsIgnoreCase(WebConstants.MANDATE)) {
            panel = getPanelForMandate(adminList);
        }
        addAllMessagesToRequest(msgList);
    	//return WebConstants.LOAD_ADMIN_OPTION;
        loadProductStructureAdminOptions.setValue(WebConstants.LOAD_ADMIN_OPTION);
        return loadProductStructureAdminOptions;
    }
    
    public void setLoadProductStructureAdminOptions(HtmlInputHidden temp){
    	this.loadProductStructureAdminOptions = temp;
    }

    private List loadAdminOptiosForProductStructure() {

		List adminList = new ArrayList();
		LocateAdministrationOptionRequest request = (LocateAdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.LOCATE_ADMIN_OPTION_REQUEST);

		ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
				.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

		if (null != getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_DESC)) {
			this.setBenefitAdminName(getSession().getAttribute(
					WebConstants.SESSION_BNFT_ADMIN_DESC).toString());
		}

		request.setBenefitAdminSysId(Integer.parseInt(getSession()
				.getAttribute(WebConstants.SESSION_BNFT_ADMIN_ID).toString()));

		String bcId = (String) getSession().getAttribute("BNFT_CMPNT_KEY");
		request.setBenefitComponentId(Integer.parseInt(bcId));

		request.setEntityId(productStructureSessionObject.getId());
		request.setEntityType(WebConstants.PROD_STRUCTURE_NAME);
		request.setPSorProductorBenefit(this.getPSorProductorBenefit());

		LocateAdministrationOptionResponse response = (LocateAdministrationOptionResponse) executeService(request);

		if (null != response.getAssociatedBenefitAdministrationOptionList()
				&& !response.getAssociatedBenefitAdministrationOptionList()
						.isEmpty()) {
			this.adminOptionList = response
					.getAssociatedBenefitAdministrationOptionList();

			// Added - Jan 23rd
			if (response.getHiddenAdminOptionCount() != 0) {
				this.setAdminOptionExists(true);
			}

			return response.getAssociatedBenefitAdministrationOptionList();
		} else {
			return null;
		}
	}
    
    private List loadAdminOptiosForProduct() {

        List adminList = new ArrayList();
		LocateAdministrationOptionRequest request = (LocateAdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.LOCATE_ADMIN_OPTION_REQUEST);

		ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE);

		if (null != getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_DESC)) {
			this.setBenefitAdminName(getSession().getAttribute(
					WebConstants.SESSION_BNFT_ADMIN_DESC).toString());
		}

		request.setBenefitAdminSysId(Integer.parseInt(getSession().getAttribute("ADMIN_KEY").toString()));

		request.setBenefitComponentId(Integer.parseInt(getSession().getAttribute("BNFT_CMPNT_KEY").toString()));
		request.setEntityId(productSessionObject.getProductKeyObject().getProductId());
		request.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
		request.setPSorProductorBenefit(this.getPSorProductorBenefit());
			
		LocateAdministrationOptionResponse response = (LocateAdministrationOptionResponse) executeService(request);

		if (null != response.getAssociatedBenefitAdministrationOptionList()
				&& !response.getAssociatedBenefitAdministrationOptionList().isEmpty()) {
			this.adminOptionList = response.getAssociatedBenefitAdministrationOptionList();

			// Added - Jan 23rd
			if (response.getHiddenAdminOptionCount() != 0) {
				this.setAdminOptionExists(true);
			}
			return response.getAssociatedBenefitAdministrationOptionList();
		} else {
			return null;
		}

    }
    /**
	 * @return Returns the adminOptionList.
	 */
    public List getAdminOptionList() {
//    	if(!this.adminOptionListRetrieved) {
//    		this.adminOptionListRetrieved = true;
            List adminList = new ArrayList();
            LocateAdministrationOptionRequest locateAdministrationOptionRequest = (LocateAdministrationOptionRequest) this
                    .getServiceRequest(ServiceManager.LOCATE_ADMIN_OPTION_REQUEST);

            ContractSession contractSessionObject = (ContractSession) getSession()
                    .getAttribute(CONTRACT_SESSION_KEY);
            BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
                    .getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
            ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
                    .getAttribute(WebConstants.PROD_TYPE);
            ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
                    .getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

            if (null != getSession().getAttribute(
                    WebConstants.SESSION_BNFT_ADMIN_DESC)) {
                this.setBenefitAdminName(getSession().getAttribute(
                        WebConstants.SESSION_BNFT_ADMIN_DESC).toString());
            }
            if (null != getSession().getAttribute(
                    WebConstants.SESSION_BNFT_ADMIN_ID)) {
                locateAdministrationOptionRequest.setBenefitAdminSysId(Integer
                        .parseInt(getSession().getAttribute(
                                WebConstants.SESSION_BNFT_ADMIN_ID).toString()));
                //to get the definition key from session changed on 16th Dec 2007
                if (null != getSession().getAttribute("SESSION_BNFT_DEFN_ID")) {
                    String benefitKeyString = getSession().getAttribute(
                            "SESSION_BNFT_DEFN_ID").toString();
                    String[] benefitKeyArray = benefitKeyString.split("~");
                    String benefitKey = benefitKeyArray[0];
                    locateAdministrationOptionRequest
                            .setBenefitDefinitionKey(Integer.parseInt(benefitKey));
                }
                //change ends
            } else if (null != getSession().getAttribute("ADMIN_KEY")) {
                locateAdministrationOptionRequest
                        .setBenefitAdminSysId(Integer.parseInt(getSession()
                                .getAttribute("ADMIN_KEY").toString()));
            } else {
                if (null != contractSessionObject)
                    locateAdministrationOptionRequest
                            .setBenefitAdminSysId(contractSessionObject
                                    .getBenefitAdminId());
            }
            locateAdministrationOptionRequest
                    .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
    		// Checked whether product module or not by checking the value for Product_Node_Type
            if (null != contractSessionObject && null == getSession().getAttribute("PRODUCT_NODE_TYPE") && null != contractSessionObject
                    .getContractKeyObject() ) {
            	
                    locateAdministrationOptionRequest.setEntityId(contractSessionObject
                            .getContractKeyObject().getDateSegmentId());
                    locateAdministrationOptionRequest
                            .setEntityType(BusinessConstants.ENTITY_TYPE_CONTRACT);
                    locateAdministrationOptionRequest
                            .setBenefitComponentId(contractSessionObject
                                    .getBenefitComponentId());
            } else if (null != productSessionObject
                    && null != productSessionObject.getProductKeyObject()) {

                locateAdministrationOptionRequest.setBenefitComponentId(Integer
                        .parseInt(getSession().getAttribute("BNFT_CMPNT_KEY")
                                .toString()));

                locateAdministrationOptionRequest.setEntityId(productSessionObject
                        .getProductKeyObject().getProductId());
                locateAdministrationOptionRequest
                        .setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
                locateAdministrationOptionRequest.setPSorProductorBenefit(this
                        .getPSorProductorBenefit());
                locateAdministrationOptionRequest.setBenefitDefinitionKey(Integer
                        .parseInt(getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)
                                .toString()));
            } else if (null != productStructureSessionObject) {

                String bcId = (String) getSession().getAttribute("BNFT_CMPNT_KEY");
                locateAdministrationOptionRequest.setBenefitComponentId(Integer
                        .parseInt(bcId));

                locateAdministrationOptionRequest
                        .setEntityId(productStructureSessionObject.getId());
                locateAdministrationOptionRequest
                        .setEntityType(WebConstants.PROD_STRUCTURE_NAME);
                locateAdministrationOptionRequest.setPSorProductorBenefit(this
                        .getPSorProductorBenefit());
                locateAdministrationOptionRequest.setBenefitDefinitionKey(Integer
                        .parseInt(getSession().getAttribute(WebConstants.STANDARD_BNFT_KEY)
                                .toString()));
            } else if (null != benefitComponentSessionObject) {
                locateAdministrationOptionRequest
                        .setEntityId(benefitComponentSessionObject
                                .getBenefitComponentId());
                locateAdministrationOptionRequest
                        .setBenefitComponentId(benefitComponentSessionObject
                                .getBenefitComponentId());
                locateAdministrationOptionRequest.setEntityType("BENEFITCOMP");
            }
            LocateAdministrationOptionResponse response = (LocateAdministrationOptionResponse) executeService(locateAdministrationOptionRequest);

            if (null != response.getAssociatedBenefitAdministrationOptionList()
                    && !response.getAssociatedBenefitAdministrationOptionList()
                            .isEmpty()) {
                this.adminOptionList = response
                        .getAssociatedBenefitAdministrationOptionList();

//     Added - Jan 23rd            
                if(response.getHiddenAdminOptionCount() !=0){
                	this.setAdminOptionExists(true);            	
                }
                	
                return response.getAssociatedBenefitAdministrationOptionList();
            } else {
                return null;
            }
//    	} else {
//    		return this.adminOptionList;
//    	}
    }


    public HtmlPanelGrid preparePanel(List adminOptionList) {
        panelForBenefitComp = new HtmlPanelGrid();
        if (benefitComponentSessionObject.getBcComponentType()
                .equalsIgnoreCase("standard")) {
        	panelForBenefitComp.setColumns(4);

        }else{
        	panelForBenefitComp.setColumns(3);
        }
        panelForBenefitComp.setWidth("100%");
        StringBuffer rows = new StringBuffer();//for setting row classes
        checkBoxValues = "";
        StringBuffer rowClass = new StringBuffer();
        if (null != adminOptionList && adminOptionList.size() > 0) {
            for (int i = 0; i < this.adminOptionList.size(); i++) {
                HtmlOutputText outputText1 = new HtmlOutputText();
                HtmlOutputText outputText2 = new HtmlOutputText();
                HtmlOutputText outputText3 = new HtmlOutputText();
                HtmlInputHidden htmlInputHidden = new HtmlInputHidden();
                AdminOptionHideBO adminOptionHideBO = (AdminOptionHideBO) this.adminOptionList
                        .get(i);
                htmlInputHidden.setValue(adminOptionHideBO.getHideFlag());
                HtmlSelectBooleanCheckbox booleanCheckbox = new HtmlSelectBooleanCheckbox();
                booleanCheckbox.setId("checkbox" + i);
               booleanCheckbox.setStyle("width:105;align:left");
                if (adminOptionHideBO.getHideFlag().equalsIgnoreCase("T")) {
                    if (("true").equalsIgnoreCase(checkBoxValue)) {
                        checkBoxValues += "T";
                    }
                 
                    booleanCheckbox.setValue(Boolean.TRUE);
                } else {
                    booleanCheckbox.setValue(Boolean.FALSE);
                    checkBoxValues += "F";
                }
                ValueBinding checkBox = FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .createValueBinding(
                                "#{adminOptionBackingBean.adminOptionKeyMap["
                                        + adminOptionHideBO
                                                .getAdminLevelOptionAssnSystemId()
                                        + "]}");
                booleanCheckbox.setValueBinding("value", checkBox);

                //Change for Performance on Dec 11th 2007
                HtmlInputHidden hiddenAdminOptionFlag = new HtmlInputHidden();

                hiddenAdminOptionFlag.setId("hiddenAdminOptionFlag" + i);
                hiddenAdminOptionFlag.setValue(""
                        + adminOptionHideBO.getHideFlag());
                ValueBinding valForhiddenAdminOptionFlag = FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .createValueBinding(
                                "#{adminOptionBackingBean.hiddenAdminOptionFlagMap["
                                        + adminOptionHideBO
                                                .getAdminLevelOptionAssnSystemId()
                                        + "]}");
                hiddenAdminOptionFlag.setValueBinding("value",
                        valForhiddenAdminOptionFlag);
                // end

                if (null != adminOptionHideBO.getAdminOptionDesc()) {
                    outputText1.setValue(adminOptionHideBO.getAdminOptionDesc()
                            .trim());
                }
                outputText1.setStyle("width:140;height:15");
                if (null != adminOptionHideBO.getAdminLevelDesc()) {
                    outputText2.setValue(adminOptionHideBO.getAdminLevelDesc());
                }
                outputText2.setStyle("width:140");
                if (null != adminOptionHideBO.getBenefitLevelDesc()) {
                    outputText3.setValue(adminOptionHideBO
                            .getBenefitLevelDesc());
                }
                outputText3.setStyle("width:140");
                booleanCheckbox.getChildren().add(hiddenAdminOptionFlag);
                HtmlOutputLabel lbl = new HtmlOutputLabel();
                lbl.setId("lbl"+RandomStringUtils.randomAlphanumeric(15));
                lbl.getChildren().add(booleanCheckbox);
                if (this.checkBoxValue.equalsIgnoreCase("true")) {
                    panelForBenefitComp.getChildren().add(outputText1);
                    panelForBenefitComp.getChildren().add(outputText2);
                    panelForBenefitComp.getChildren().add(outputText3);
                    if (benefitComponentSessionObject.getBcComponentType()
                            .equalsIgnoreCase("standard")) {
                        panelForBenefitComp.getChildren().add(lbl);
                        this.saveButtonRender = true;
                    }                   
                    if (i % 2 == 0) {
                        if (i < adminOptionList.size() - 1) {
                            if (adminOptionHideBO.getHideFlag()
                                    .equalsIgnoreCase("T")) {
                                rowClass.append("hiddenFieldLevelDisplay,");
                            } else {
                                rowClass.append("dataTableEvenRow,");
                            }

                        } else {
                            if (adminOptionHideBO.getHideFlag()
                                    .equalsIgnoreCase("T")) {
                                rowClass.append("hiddenFieldLevelDisplay");
                            } else {
                                rowClass.append("dataTableEvenRow");
                            }
                        }
                    } else {
                        if (i < adminOptionList.size() - 1) {
                            if (adminOptionHideBO.getHideFlag()
                                    .equalsIgnoreCase("T")) {
                                rowClass.append("hiddenFieldDisplay,");
                            } else {
                                rowClass.append("dataTableOddRow,");
                            }

                        } else {
                            if (adminOptionHideBO.getHideFlag()
                                    .equalsIgnoreCase("T")) {
                                rowClass.append("hiddenFieldDisplay");
                            } else {
                                rowClass.append("dataTableOddRow");
                            }
                        }
                    }

                } else {
                    if (adminOptionHideBO.getHideFlag().equalsIgnoreCase("F")) {
                        panelForBenefitComp.getChildren().add(outputText1);
                        panelForBenefitComp.getChildren().add(outputText2);
                        panelForBenefitComp.getChildren().add(outputText3);
                        if (benefitComponentSessionObject.getBcComponentType()
                                .equalsIgnoreCase("standard")) {
                            panelForBenefitComp.getChildren().add(
                            		lbl);
                            this.saveButtonRender = true;
                        }
                    }
                    if (i % 2 == 0) {
                        if (i < adminOptionList.size() - 1) {
                            rowClass.append("dataTableEvenRow,");
                        } else {
                            rowClass.append("dataTableEvenRow");
                        }
                    } else {
                        if (i < adminOptionList.size() - 1) {
                            rowClass.append("dataTableOddRow,");
                        } else {
                            rowClass.append("dataTableOddRow");
                        }
                    }
                }

                panelForBenefitComp.setRowClasses(rowClass.toString());
            }
            return panelForBenefitComp;
        } else
            return null;

    }


    public HtmlPanelGrid preparePanelForView(List adminOptionList) {
   		panelForView = new HtmlPanelGrid();	
        panelForView.setWidth("100%");
        if (isAllHidden(adminOptionList)) {
            HtmlOutputText outputText4 = new HtmlOutputText();
            outputText4.setValue("No Admin Option Available");
            outputText4.setStyleClass("dataTableColumnHeader");
//            outputText4.setStyle("width:900");
            panelForView.getChildren().add(outputText4);

        }
        this.adminOptionExists = false;
        if (null != adminOptionList && adminOptionList.size() > 0) {
        	this.adminOptionExists = true;
            for (int i = 0; i < adminOptionList.size(); i++) {

                HtmlOutputText outputText1 = new HtmlOutputText();
                HtmlOutputText outputText2 = new HtmlOutputText();
                HtmlOutputText outputText3 = new HtmlOutputText();

                AdminOptionHideBO adminOptionHideBO = (AdminOptionHideBO) adminOptionList
                        .get(i);
                if (null != adminOptionHideBO.getAdminOptionDesc()) {
                    outputText1.setValue(adminOptionHideBO.getAdminOptionDesc()
                            .trim());
                }
                outputText1.setStyle("width:200;height:15");
                if (null != adminOptionHideBO.getAdminLevelDesc()) {
                    outputText2.setValue(adminOptionHideBO.getAdminLevelDesc());
                }
                outputText2.setStyle("width:150");
                if (null != adminOptionHideBO.getBenefitLevelDesc()) {
                    outputText3.setValue(adminOptionHideBO
                            .getBenefitLevelDesc());
                }
                outputText3.setStyle("width:150");
                if (this.checkBoxValue.equalsIgnoreCase("true")) {
                    panelForView.getChildren().add(outputText1);
                    panelForView.getChildren().add(outputText2);
                    panelForView.getChildren().add(outputText3);

                } else {
                    if (adminOptionHideBO.getHideFlag().equalsIgnoreCase("F")) {
                        panelForView.getChildren().add(outputText1);
                        panelForView.getChildren().add(outputText2);
                        panelForView.getChildren().add(outputText3);
                    }
                }
            }
        }

        return panelForView;

    }


    /**
     * Method for Session Object
     * 
     * @return standardBenefitSessionObject
     */
    protected StandardBenefitSessionObject getStandardBenefitSessionObject() {
        StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(STANDARD_BENEFIT_SESSION_KEY);

        if (standardBenefitSessionObject == null) {
            standardBenefitSessionObject = new StandardBenefitSessionObject();
            getSession().setAttribute(STANDARD_BENEFIT_SESSION_KEY,
                    standardBenefitSessionObject);
        }
        return standardBenefitSessionObject;
    }


    /*
     * For loading administration List.
     * 
     * @return String.
     */
    private List loadAdministrationOptionList() {

        return this.getAdminOptionList();
    }


    /*
     * Save for Product Admin Options.
     */

    public String saveAdminOptions() {
    	getRequest().setAttribute("RETAIN_Value", "");
        this.setPSorProductorBenefit(true);
        //	    Session objects.
        ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
                .getAttribute(WebConstants.PROD_TYPE);

        SaveAdminOptionResponse adminResponse = null;
        SaveAdminOptionRequest adminRequest = null;

        this.setBreadCrumbText("Product Configuration >>  Product ("
                + this.breadCrumbName + ") >> Edit");

        //		loads the admin list.
        List adminOptionList = loadAdministrationOptionList();
        // 		check admin page status.
        String status = checkHideStatus();
        if (status.equalsIgnoreCase("T")) {
            this.setShowHiddenSelected(true);
        } else {
            this.setShowHiddenSelected(false);
        }
        if (null != adminOptionList && adminOptionList.size() > 0) {

            //		    List Containing the value objects.

            List adminOptionOverriddenList = getAdminOptionOverriddenList();
            
            List adminListForSave = listToUpdate (adminOptionList,adminOptionOverriddenList);

            if (null != adminListForSave
                    && adminListForSave.size() > 0) {

                //			    Request
                adminRequest = (SaveAdminOptionRequest) this
                        .getServiceRequest(ServiceManager.SAVE_ADMIN_OPTION);

                //				Populating the request.
                //			    adminRequest.setAdminList(adminOptionList);
                adminRequest.setAdminOveriddenList(adminListForSave);
                setChagedAdminOptions(adminRequest, adminOptionOverriddenList);
                //adminRequest.setAdminOveriddenList(adminOptionOverriddenList);
                adminRequest.setBenefitComponentId(Integer
                        .parseInt(getSession().getAttribute("BNFT_CMPNT_KEY")
                                .toString()));
                adminRequest.setProductType("product");
                adminRequest.setProductSysId(productSessionObject
                        .getProductKeyObject().getProductId());
                adminRequest.setBenefitAdminId(Integer.parseInt(getSession()
                        .getAttribute("ADMIN_KEY").toString()));
                adminRequest.setProductKeyObject(productSessionObject
                        .getProductKeyObject());
                adminRequest.setBenefitSysId(Integer.parseInt((String)getSession().getAttribute("SESSION_BNFT_DEFN_ID")));
                adminRequest
                        .setPSorProductorBenefit(this.isPSorProductorBenefit);
                adminRequest.setBenefitCompName((String)getSession().getAttribute("BENEFIT_COMP_NAME"));

                //				Service method call
                adminResponse = (SaveAdminOptionResponse) this
                        .executeService(adminRequest);
            } else {
                //		    	this.addNotSavedMessage();
                validationMessages = new ArrayList();
                this.validationMessages.add(new InformationalMessage(
                        WebConstants.NO_ADMN_OPTION));
                addAllMessagesToRequest(this.validationMessages);
            }
        } else {
            //			this.addNotSavedMessage();
            validationMessages = new ArrayList();
            this.validationMessages.add(new InformationalMessage(
                    WebConstants.NO_ADMN_OPTION));
            addAllMessagesToRequest(this.validationMessages);
        }

        if (null != adminResponse) {
            List administrationListSaved = adminResponse.getAdminOptionList();
            if (administrationListSaved != null
                    && !administrationListSaved.isEmpty())
                this.adminOptionList = administrationListSaved;
            //Code change for product tree rendering optimization
            updateTreeStructure();
            
        }
        //		Return the forward String
        return WebConstants.LOAD_ADMIN_OPTION;
    }

    //Code change for product tree rendering optimization
	public boolean isTreeStructureUpdated() {
		return ( (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE) )
		.isTreeStructureUpdated();
	}
	
	public void updateTreeStructure() {
		setTreeStructureUpdated(true);
	}
	
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		( (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE) )
		.setTreeStructureUpdated(treeStructureUpdated);
	}

    /**
	 * @param adminRequest
	 * @param adminOptionOverriddenList
	 */
	private void setChagedAdminOptions(SaveAdminOptionRequest adminRequest, List adminOptionOverriddenList) {
		if(null != adminRequest && null != adminOptionOverriddenList && adminOptionOverriddenList.size() > 0){
			Map oldStates = loadAdminOptionsState();
			List changedAdminOptions = new ArrayList();
			Iterator iterator = adminOptionOverriddenList.iterator();
			while(iterator.hasNext()){
				AdminOptionHideVO adminOptionHideVO = (AdminOptionHideVO)iterator.next();
				String flag = (String)((Object[]) oldStates.get(new Integer(adminOptionHideVO.getAdmnLvlAsscId())))[1];
				if(null != flag && !flag.
						equals(adminOptionHideVO.getQuestionHideFlag())){
					adminRequest.setAdminOptionsChanged(true);
					changedAdminOptions.add(new Integer((String)((Object[]) oldStates.get(new Integer(adminOptionHideVO.getAdmnLvlAsscId())))[0]));
				}
			}
			adminRequest.setChangedAdminOptions(changedAdminOptions);
		}
		
	}



	public String saveAdminOptionsForProdStructure() {
		getRequest().setAttribute("RETAIN_Value", "");  
        this.setPSorProductorBenefit(true);
        //	    Session Objects
        ContractSession contractSessionObject = (ContractSession) getSession()
                .getAttribute(CONTRACT_SESSION_KEY);
        ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
                .getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

        this.setBreadCrumbText("Product Configuration >> Product Structure ("
                + this.breadCrumbName + ") >> Edit");

        SaveAdminOptionRequestForPS adminRequest = null;

        SaveAdminOptionResponseForPS adminResponse = null;

        //		loads the admin list.
        List adminOptionList = loadAdministrationOptionList();
        // 		check admin page status.
        String status = checkHideStatus();
        if (status.equalsIgnoreCase("T")) {
            this.setShowHiddenSelected(true);
        } else {
            this.setShowHiddenSelected(false);
        }
        //		Creating the productStructureVO and populating the VO.
        if (null != adminOptionList && adminOptionList.size() > 0) {

            ProductStructureVO productStructureVO = getProductStructureVO(productStructureSessionObject);

            //			List Containing the value objects.
            List adminOptionOverriddenList = getAdminOptionOverriddenList();

            List adminListForSave = listToUpdate(adminOptionList,adminOptionOverriddenList);

            if (null != adminListForSave
                    && adminListForSave.size() > 0) {

                adminRequest = (SaveAdminOptionRequestForPS) this
                        .getServiceRequest(ServiceManager.SAVE_ADMIN_OPTION_FOR_PS);

                //				Setting the request object.
                //				adminRequest.setAdminList(adminOptionList);
                adminRequest.setAdminOveriddenList(adminListForSave);
                String bcId = (String) getSession().getAttribute(
                        "BNFT_CMPNT_KEY");
            	String benefitSysId = (String)getSession().getAttribute("STANDARD_BNFT_KEY");
        		
        		int benefitId =(new Integer(benefitSysId)).intValue();
        		
                adminRequest.setBenefitComponentId(Integer.parseInt(bcId));
                adminRequest.setEntityType(WebConstants.PROD_STRUCTURE_NAME);
                adminRequest.setProductStructureVO(productStructureVO);
                adminRequest.setBenefitSysId(benefitId);
                adminRequest
                        .setPSorProductorBenefit(this.isPSorProductorBenefit);
                updateAMVForProdStructure(adminRequest);

                if (null != getSession().getAttribute(
                        WebConstants.SESSION_BNFT_ADMIN_ID)) {
                    adminRequest.setBenefitAdminId(Integer
                            .parseInt(getSession().getAttribute(
                                    WebConstants.SESSION_BNFT_ADMIN_ID)
                                    .toString()));
                } else if (null != getSession().getAttribute("ADMIN_KEY")) {
                    adminRequest.setBenefitAdminId(Integer
                            .parseInt(getSession().getAttribute("ADMIN_KEY")
                                    .toString()));
                } else {
                    adminRequest.setBenefitAdminId(contractSessionObject
                            .getBenefitAdminId());

                }
                //				Service call.
                adminResponse = (SaveAdminOptionResponseForPS) this
                        .executeService(adminRequest);

                if (null != adminResponse) {
                    List adminListSaved = adminResponse.getAdminOptionList();
                    updateTreeProductStructure();
                    if (adminListSaved != null && !adminListSaved.isEmpty())
                        this.adminOptionList = adminListSaved;
                }
            } else {
                //this.addNotSavedMessage();
                validationMessages = new ArrayList();
                this.validationMessages.add(new InformationalMessage(
                        WebConstants.NO_ADMN_OPTION));
                addAllMessagesToRequest(this.validationMessages);
            }
        } else {
            //this.addNotSavedMessage();
            validationMessages = new ArrayList();
            this.validationMessages.add(new InformationalMessage(
                    WebConstants.NO_ADMN_OPTION));
            addAllMessagesToRequest(this.validationMessages);
        }

        return WebConstants.LOAD_ADMIN_OPTION;
    }
	
	//Code change for product structure tree rendering optimization
		
	public void updateTreeProductStructure() {
		setTreeProductStructureUpdated(true);
	}
	
	public void setTreeProductStructureUpdated(boolean treeStructureUpdated) {
		( (ProductStructureSessionObject) getSession().getAttribute(WebConstants.PROD_STRUCTURE_TYPE) )
		.setTreeStructureUpdated(treeStructureUpdated);
	}
    
    /**
	 * 
	 *  
	 */
	private List listToUpdate(List adminOptionList,
			List adminOptionOverriddenList) {

		List listToUpdate = new ArrayList();
		
		boolean adminHideFlag = false;

		Iterator dbListIter = adminOptionList.iterator();
		Iterator pageListIter = adminOptionOverriddenList.iterator();

		AdminOptionHideVO pageObject = null;
		HideAdminOptionBO dbObject = null;

		while (dbListIter.hasNext()) {

			dbObject = (HideAdminOptionBO) dbListIter
					.next();

			while (pageListIter.hasNext()) {

				pageObject = (AdminOptionHideVO) pageListIter
						.next();

				if (dbObject.getAdminLevelOptionAssnSystemId() == pageObject
						.getAdmnLvlAsscId()) {

					
					if (!dbObject.getAdminQuestionHideFlag().equals(
							pageObject.getQuestionHideFlag())) {
						adminHideFlag = true;
						//this.setHideStatusFlag(true);

					}
					
					if(adminHideFlag){
						listToUpdate.add(pageObject);
						adminHideFlag = false;
					}

				}
			}

			pageListIter = adminOptionOverriddenList.iterator();
		}

		return listToUpdate;
	}
	
    /**
	 * @param adminRequest
     * @param adminOptionOverriddenList
	 */
	private void updateAMVForProdStructure(SaveAdminOptionRequestForPS req) {
		if(null != req && req.getAdminOveriddenList() != null && req.getAdminOveriddenList().size() > 0){
			Iterator iterator = req.getAdminOveriddenList().iterator();
			boolean isChanged = false;
			List changedIds = new ArrayList();
			Map map = loadAdminOptionsState();
			while(iterator.hasNext()){
				AdminOptionHideVO hideVO = (AdminOptionHideVO)iterator.next();
				if(!(((Object[])map.get(new Integer(hideVO.getAdmnLvlAsscId())))[1]
				        .equals(hideVO.getQuestionHideFlag()) )){
					isChanged = true;
					changedIds.add(new Integer((String)((Object[])map.get(new Integer(hideVO.getAdmnLvlAsscId())))[0]));
				}
			}
			
			if(isChanged){
				req.setChanged(true);
				req.setChangedIds(changedIds);
				req.setBCompName((String)getSession().getAttribute("BENEFIT_COMP_NAME"));
			}
		}
	}



	String checkHideStatus() {

        Long flagId = new Long(0);
        Boolean hideStatus = (Boolean) datafieldmapForAOHideFlag.get(flagId);
        if ("true".equals(hideStatus.toString())) {
            return "T";
        } else {
            return "F";
        }
    }


    /**
     * Method to add the information message when there is no admin opion for
     * update
     *  
     */
//    private void addNotSavedMessage() {
//      validationMessages = new ArrayList();
//        this.validationMessages.add(new ErrorMessage(
//                WebConstants.NO_ADMN_OPTION));
//        addAllMessagesToRequest(this.validationMessages);
//    }


    /*
     * Creates the ProductStructure VO Object
     */
    private ProductStructureVO getProductStructureVO(
            ProductStructureSessionObject productStructureSessionObject) {

        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO.setProductStructureId(productStructureSessionObject
                .getId());
        productStructureVO
                .setProductStructureName(productStructureSessionObject
                        .getName());
        productStructureVO.setBusinessDomains(productStructureSessionObject
                .getBusinessDomains());
        productStructureVO.setVersion(productStructureSessionObject
                .getVersion());
        productStructureVO.setState(productStructureSessionObject.getState());
        productStructureVO.setStatus(productStructureSessionObject.getStatus());

        return productStructureVO;

    }


    /**
     * This function is to get the over ridden list
     * 
     * @return
     */
    private List getAdminOptionOverriddenList() {

        ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
                .getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

        ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
                .getAttribute(WebConstants.PROD_TYPE);

        List adminVOList = new ArrayList();

        //Iterator adminIter = adminOptionList.iterator();

        Set adminLevelAssc = datafieldMapForAdminLevelAsscMap.keySet();
        Iterator admnAsscIter = adminLevelAssc.iterator();

        while (admnAsscIter.hasNext()) {

            AdminOptionHideVO adminOptionHideVO = new AdminOptionHideVO();
            Long adminAsscId = (Long) admnAsscIter.next();

            String asscId = (String) datafieldMapForAdminLevelAsscMap
                    .get(adminAsscId);

            adminOptionHideVO.setAdmnLvlAsscId(new Integer(asscId).intValue());

            String hiddenFlag;
            Boolean hideStatus = (Boolean) datafieldmapForQuestionHideFlag
                    .get(adminAsscId);
            if ("true".equals(hideStatus.toString())) {
                hiddenFlag = "T";
            } else {
                hiddenFlag = "F";
            }

            adminOptionHideVO.setQuestionHideFlag(hiddenFlag);

            if (null != productSessionObject
                    && null != productSessionObject.getProductKeyObject()) {
                adminOptionHideVO.setEntityId(productSessionObject
                        .getProductKeyObject().getProductId());
                adminOptionHideVO.setEntityType(WebConstants.PROD_TYPE);

                adminOptionHideVO.setBenefitComponentId(Integer
                        .parseInt(getSession().getAttribute("BNFT_CMPNT_KEY")
                                .toString()));
            } else if (null != productStructureSessionObject) {
                adminOptionHideVO.setEntityId(productStructureSessionObject
                        .getId());
                adminOptionHideVO
                        .setEntityType(WebConstants.PROD_STRUCTURE_NAME);

                adminOptionHideVO.setBenefitComponentId(Integer
                        .parseInt(getSession().getAttribute("BNFT_CMPNT_KEY")
                                .toString()));
            }

            adminVOList.add(adminOptionHideVO);

        }

        return adminVOList;
    }


    /**
     * Returns the headerPanel
     * 
     * @return HtmlPanelGrid headerPanel.
     */
    public HtmlPanelGrid getHeaderPanel() {

        List adminList = null;

        String structureType = null;

        ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
                .getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

        ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
                .getAttribute(WebConstants.PROD_TYPE);

//        benefitCompName = (String) getSession().getAttribute(
//                "BENEFIT_COMP_NAME");

        headerPanel = null;


            if (null != productSessionObject
                    && null != productSessionObject.getProductKeyObject()) {
                structureType = productSessionObject.getProductKeyObject()
                        .getProductType();

            } else if (null != productStructureSessionObject) {
                structureType = productStructureSessionObject
                        .getStructureType();
            }

            if (structureType.equalsIgnoreCase(WebConstants.STANDARD)) {
//                if (benefitCompName.equals("GENERAL BENEFITS")) {
//                    headerPanel = getHeaderPanelForMandate();
//                } else
                    headerPanel = getHeaderPanelForStandard();
            } else if (structureType.equalsIgnoreCase(WebConstants.MANDATE)) {
                headerPanel = getHeaderPanelForMandate();
            }

            return headerPanel;

    }


    private HtmlPanelGrid getHeaderPanelForStandard() {

        HtmlPanelGrid headerPanelForStandard = new HtmlPanelGrid();

        headerPanelForStandard.setWidth(WebConstants.PANEL_WIDTH);
        headerPanelForStandard.setColumns(4);

        headerPanelForStandard.setBorder(0);
        headerPanelForStandard.setCellpadding("4");
        headerPanelForStandard.setCellspacing("1");
        headerPanelForStandard.setBgcolor("#cccccc");
        headerPanelForStandard.setStyleClass("dataTableHeader");
        HtmlOutputText otxtType1 = new HtmlOutputText();
        HtmlOutputText otxtType2 = new HtmlOutputText();
        HtmlOutputText otxtType3 = new HtmlOutputText();
        HtmlOutputText otxtType4 = new HtmlOutputText();

        // check box for enhancement

        HtmlSelectBooleanCheckbox htmlCheckbox = new HtmlSelectBooleanCheckbox();
        HtmlOutputLabel olablType1 = new HtmlOutputLabel();
        olablType1.setId("olablType1"+RandomStringUtils.randomAlphanumeric(15));
        otxtType1.setValue("Name");
        otxtType1.setId("name");

        otxtType2.setValue("Admin Level");
        otxtType2.setId("adminLevel");

        otxtType3.setValue("Benefit Level");
        otxtType3.setId("benefitLevel");

        // enhancement starts
        otxtType4.setValue("Show Hidden");

        htmlCheckbox.setId("showHidden");
        //		htmlCheckbox.setTitle("showHidden");
        htmlCheckbox.setValue(Boolean.FALSE);
        int i = 0;
        ValueBinding hiddenFlag = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{adminOptionBackingBean.datafieldmapForAOHideFlag["
                                + i + "]}");
        htmlCheckbox.setValueBinding("value", hiddenFlag);

        if (!this.showHiddenSelected) {
            //if(inputHiddenForCheckBox.getValue().equals("false")){
            htmlCheckbox.setSelected(false);
            this.setShowHiddenSelected(false);
        } else {
            htmlCheckbox.setSelected(true);
            this.setShowHiddenSelected(true);
        }
        htmlCheckbox.setOnclick("unsavedDataFinder()");

        //enhancement ends

        headerPanelForStandard.setStyleClass("dataTableHeader");
        headerPanelForStandard.getChildren().add(otxtType1);
        headerPanelForStandard.getChildren().add(otxtType2);
        headerPanelForStandard.getChildren().add(otxtType3);

        olablType1.getChildren().add(htmlCheckbox);
        olablType1.getChildren().add(otxtType4);
        headerPanelForStandard.getChildren().add(olablType1);

        return headerPanelForStandard;
    }


    private HtmlPanelGrid getHeaderPanelForMandate() {

        HtmlPanelGrid headerPanelForMandate = new HtmlPanelGrid();

        headerPanelForMandate.setWidth(WebConstants.PANEL_WIDTH);
        headerPanelForMandate.setColumns(3);

        headerPanelForMandate.setBorder(0);
        headerPanelForMandate.setCellpadding("4");
        headerPanelForMandate.setCellspacing("1");
        headerPanelForMandate.setBgcolor("#cccccc");
        headerPanelForMandate.setStyleClass("dataTableHeader");
        HtmlOutputText otxtType1 = new HtmlOutputText();
        HtmlOutputText otxtType2 = new HtmlOutputText();
        HtmlOutputText otxtType3 = new HtmlOutputText();

        otxtType1.setValue("Name");
        otxtType1.setId("name");

        otxtType2.setValue("Admin Level");
        otxtType2.setId("adminLevel");

        otxtType3.setValue("Benefit Level");
        otxtType3.setId("benefitLevel");

        headerPanelForMandate.setStyleClass("dataTableHeader");
        headerPanelForMandate.getChildren().add(otxtType1);
        headerPanelForMandate.getChildren().add(otxtType2);
        headerPanelForMandate.getChildren().add(otxtType3);

        return headerPanelForMandate;
    }


    /**
     * this method returns the panel when we give a list of benfit admin
     * 
     * @return
     */

    public HtmlPanelGrid getPanel() {
            return panel;
    }


    /**
	 * @param adminList
	 */
	private void storeAdminOptionState(List adminList) {
		StringBuffer states = new StringBuffer();
		if(null != adminList && adminList.size() > 0){
			Iterator iterator = adminList.iterator();
			while(iterator.hasNext()){
				HideAdminOptionBO adminOptionBO = (HideAdminOptionBO)iterator.next();
				states.append(adminOptionBO.getAdminLevelOptionAssnSystemId());
				states.append("~");
				states.append(adminOptionBO.getAdminOptionSystemId());
				states.append("~");
				states.append(adminOptionBO.getAdminQuestionHideFlag());
				if(iterator.hasNext()){
					states.append(":");
				}
			}
		}
		adminOptionStates = states.toString();
		
	}
	
	private Map loadAdminOptionsState(){
		Map states = new HashMap();
		if(null != adminOptionStates && !"".equals(adminOptionStates.trim())){
			String[] keyValuePairs = adminOptionStates.split(":");
			for(int i =0;i<keyValuePairs.length;i++){
				String keyValuePair = keyValuePairs[i];
				String[] keyAndValue = keyValuePair.split("~");
				if(keyAndValue.length == 3)
					states.put(new Integer(keyAndValue[0]),new Object[]{keyAndValue[1],keyAndValue[2]});
			}
		}
		return states;
	}



	private HtmlPanelGrid getPanelForStandard(List adminList) {

        HtmlPanelGrid panelForStandard = new HtmlPanelGrid();
        StringBuffer rowClass = new StringBuffer();
        panelForStandard.setWidth(WebConstants.PANEL_WIDTH);
        panelForStandard.setColumns(4);
        panelForStandard.setBorder(0);
        panelForStandard.setStyleClass("outputText");
        panelForStandard.setCellpadding("4");
        panelForStandard.setCellspacing("1");
        panelForStandard.setBgcolor("#cccccc");

        if (adminList != null) {

            for (int i = 0; i < adminList.size(); i++) {

                HtmlOutputText htmlOutputText1 = new HtmlOutputText();
                HtmlOutputText htmlOutputText2 = new HtmlOutputText();
                HtmlOutputText htmlOutputText3 = new HtmlOutputText();
                HtmlOutputLabel htmlOutputLabel = new HtmlOutputLabel();
                htmlOutputLabel.setId("htmlOutputLabel"+RandomStringUtils.randomAlphanumeric(15));
                HtmlSelectBooleanCheckbox checkBox = new HtmlSelectBooleanCheckbox();

                HideAdminOptionBO adminOption = (HideAdminOptionBO) adminList
                        .get(i);

                //				htmlOutputText1.setStyleClass("mandatoryNormal");
                htmlOutputText1.setId("adminOption" + i);
                htmlOutputText1.setValue(adminOption.getAdminOptionDesc());

                HtmlInputHidden htmlInputHiddenForAdminLevelAsscId = new HtmlInputHidden();
                htmlInputHiddenForAdminLevelAsscId.setValue(new Integer(
                        adminOption.getAdminLevelOptionAssnSystemId()));
                htmlInputHiddenForAdminLevelAsscId
                        .setId("htmlInputHiddenForAdminOptionID" + i);
                ValueBinding asscId = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{adminOptionBackingBean.datafieldMapForAdminLevelAsscMap["
                                        + i + "]}");
                htmlInputHiddenForAdminLevelAsscId.setValueBinding("value",
                        asscId);

                //				htmlOutputText2.setStyleClass("mandatoryNormal");
                htmlOutputText2.setId("adminLevel" + i);
                htmlOutputText2.setValue(adminOption.getAdminLevelDesc());

                //				htmlOutputText3.setStyleClass("mandatoryNormal");
                htmlOutputText3.setId("benefitlevel" + i);
                htmlOutputText3.setValue(adminOption.getBenefitLevelDesc());

                checkBox.setId("showHidden" + i);
                ValueBinding hiddenFlag = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{adminOptionBackingBean.datafieldmapForQuestionHideFlag["
                                        + i + "]}");
                checkBox.setValueBinding("value", hiddenFlag);

                htmlOutputLabel.getChildren().add(
                        htmlInputHiddenForAdminLevelAsscId);

                htmlOutputLabel.getChildren().add(checkBox);

                if ((!this.showHiddenSelected)
                        && (adminOption.getAdminQuestionHideFlag().equals("F"))) {

                    panelForStandard.getChildren().add(htmlOutputText1);
                    panelForStandard.getChildren().add(htmlOutputText2);
                    panelForStandard.getChildren().add(htmlOutputText3);
                    panelForStandard.getChildren().add(htmlOutputLabel);
                }
                if (!this.showHiddenSelected) {
                    if (i % 2 == 0) {
                        if (i < adminOptionList.size() - 1) {
                            rowClass.append("dataTableEvenRow,");
                        } else {
                            rowClass.append("dataTableEvenRow");
                        }
                    } else {
                        if (i < adminOptionList.size() - 1) {
                            rowClass.append("dataTableOddRow,");
                        } else {
                            rowClass.append("dataTableOddRow");
                        }
                    }
                }
                if ((this.showHiddenSelected)) {
                    if ((adminOption.getAdminQuestionHideFlag().equals("T"))) {
                        if (i % 2 == 0) {
                            if (i < adminOptionList.size() - 1) {
                                rowClass.append("hiddenFieldLevelDisplay,");
                            } else {
                                rowClass.append("hiddenFieldLevelDisplay");
                            }
                        } else {
                            if (i < adminOptionList.size() - 1) {
                                rowClass.append("hiddenFieldDisplay,");
                            } else {
                                rowClass.append("hiddenFieldDisplay");
                            }
                        }
                        checkBox.setSelected(true);
                    } else {
                        if (i % 2 == 0) {
                            if (i < adminOptionList.size() - 1) {
                                rowClass.append("dataTableEvenRow,");
                            } else {
                                rowClass.append("dataTableEvenRow");
                            }
                        } else {
                            if (i < adminOptionList.size() - 1) {
                                rowClass.append("dataTableOddRow,");
                            } else {
                                rowClass.append("dataTableOddRow");
                            }
                        }
                        checkBox.setSelected(false);
                    }

                    panelForStandard.getChildren().add(htmlOutputText1);
                    panelForStandard.getChildren().add(htmlOutputText2);
                    panelForStandard.getChildren().add(htmlOutputText3);
                    panelForStandard.getChildren().add(htmlOutputLabel);
                }
            }
            panelForStandard.setRowClasses(rowClass.toString());
            return panelForStandard;
        } else
            return null;

    }


    private HtmlPanelGrid getPanelForMandate(List adminList) {

        HtmlPanelGrid panelForMandate = new HtmlPanelGrid();

        panelForMandate.setWidth(WebConstants.PANEL_WIDTH);
        panelForMandate.setColumns(3);
        panelForMandate.setBorder(0);
        panelForMandate.setStyleClass("outputText");
        panelForMandate.setCellpadding("4");
        panelForMandate.setCellspacing("1");
        panelForMandate.setBgcolor("#cccccc");

        if (adminList != null) {

            for (int i = 0; i < adminList.size(); i++) {

                HtmlOutputText htmlOutputText1 = new HtmlOutputText();
                HtmlOutputText htmlOutputText2 = new HtmlOutputText();
                HtmlOutputText htmlOutputText3 = new HtmlOutputText();

                HideAdminOptionBO adminOption = (HideAdminOptionBO) adminList
                        .get(i);

                htmlOutputText1.setStyleClass("mandatoryNormal");
                htmlOutputText1.setId("adminOption" + i);
                htmlOutputText1.setValue(adminOption.getAdminOptionDesc());

                htmlOutputText2.setStyleClass("mandatoryNormal");
                htmlOutputText2.setId("adminLevel" + i);
                htmlOutputText2.setValue(adminOption.getAdminLevelDesc());

                htmlOutputText3.setStyleClass("mandatoryNormal");
                htmlOutputText3.setId("benefitlevel" + i);
                htmlOutputText3.setValue(adminOption.getBenefitLevelDesc());

                panelForMandate.getChildren().add(htmlOutputText1);
                panelForMandate.getChildren().add(htmlOutputText2);
                panelForMandate.getChildren().add(htmlOutputText3);
            }
            return panelForMandate;
        }
        return null;
    }


    /*
     * Method to retrieve list for print and view.
     */
    private List listForPrint(List adminList) {

        List adminListForPrint = new ArrayList();
        HideAdminOptionBO newBO = null;
        if(adminList != null ) {
	        Iterator listIterForPrint = adminList.iterator();
	
	        while (listIterForPrint.hasNext()) {
	
	            newBO = new HideAdminOptionBO();
	            newBO = (HideAdminOptionBO) listIterForPrint.next();
	
	            if (newBO.getAdminQuestionHideFlag().equals("F")) {
	                adminListForPrint.add(newBO);
	            }
	
	        }
        }
        return adminListForPrint;

    }


    /*
     * method to invoke when the show Hidden Checkbox is selected.
     */
    public String loadWithHiddenValues() {

    	getRequest().setAttribute("RETAIN_Value", "");  
        List adminList = null;
        this.setShowHiddenSelected(true);
        this.setPSorProductorBenefit(true);
        // call the method to load values
        adminList = loadAdministrationOptionList();
        this.adminOptionList = adminList;

        return WebConstants.LOAD_ADMIN_OPTION;
    }


    /*
     * method to invoke when the show Hidden Checkbox is unselected.
     */
    public String loadWithoutHiddenValues() {

    	getRequest().setAttribute("RETAIN_Value", "");  
        List adminList;
        this.setShowHiddenSelected(false);
        this.setPSorProductorBenefit(true);
        // call the method to load values
        adminList = loadAdministrationOptionList();
        this.adminOptionList = adminList;

        return WebConstants.LOAD_ADMIN_OPTION;
    }


    /**
     * 
     * @param associatedAdministrationList.
     */
    public void setAdminOptionList(List associatedAdministrationList) {
        this.adminOptionList = associatedAdministrationList;
    }


    /**
     * @return Returns the benefitAdminName.
     */
    public String getBenefitAdminName() {
        return benefitAdminName;
    }


    /**
     * @param benefitAdminName
     *            The benefitAdminName to set.
     */
    public void setBenefitAdminName(String benefitAdminName) {
        this.benefitAdminName = benefitAdminName;
    }


    /**
     * @return Returns the breadCrumbText.
     */
    public String getBreadCrumbText() {
        return breadCrumbText;
    }


    /**
     * @param breadCrumbText
     *            The breadCrumbText to set.
     */
    public void setBreadCrumbText(String breadCrumbText) {
        getRequest().setAttribute("breadCrumbText", breadCrumbText);
        this.breadCrumbText = breadCrumbText;
    }


    /**
     * @return Returns the sTANDARD_BENEFIT_SESSION_KEY.
     */
    public String getSTANDARD_BENEFIT_SESSION_KEY() {
        return STANDARD_BENEFIT_SESSION_KEY;
    }


    /**
     * @param standard_benefit_session_key
     *            The sTANDARD_BENEFIT_SESSION_KEY to set.
     */
    public void setSTANDARD_BENEFIT_SESSION_KEY(
            String standard_benefit_session_key) {
        STANDARD_BENEFIT_SESSION_KEY = standard_benefit_session_key;
    }


    /**
     * @return Returns the breadCrumbName.
     */
    public String getBreadCrumbName() {
        return breadCrumbName;
    }


    /**
     * @param breadCrumbName
     *            The breadCrumbName to set.
     */
    public void setBreadCrumbName(String breadCrumbName) {
        this.breadCrumbName = breadCrumbName;
    }


    /**
     * @return Returns the valuesFromSessionForBenefit.
     */
    public String getValuesFromSessionForBenefit() {
        return valuesFromSessionForBenefit;
    }


    /**
     * @param valuesFromSessionForBenefit
     *            The valuesFromSessionForBenefit to set.
     */
    public void setValuesFromSessionForBenefit(
            String valuesFromSessionForBenefit) {
        this.valuesFromSessionForBenefit = valuesFromSessionForBenefit;
    }


    /**
     * @return Returns the valuesFromSessionForBenefitComp.
     */
    public String getValuesFromSessionForBenefitComp() {
        loadForBenefitComponent();
        return valuesFromSessionForBenefitComp;
    }


    /**
     * @param valuesFromSessionForBenefitComp
     *            The valuesFromSessionForBenefitComp to set.
     */
    public void setValuesFromSessionForBenefitComp(
            String valuesFromSessionForBenefitComp) {
        this.valuesFromSessionForBenefitComp = valuesFromSessionForBenefitComp;
    }


    /**
     * @return Returns the valuesFromSessionForContract.
     */
    public String getValuesFromSessionForContract() {
        loadForContract();
        return valuesFromSessionForContract;
    }


    /**
     * @param valuesFromSessionForContract
     *            The valuesFromSessionForContract to set.
     */
    public void setValuesFromSessionForContract(
            String valuesFromSessionForContract) {
        this.valuesFromSessionForContract = valuesFromSessionForContract;
    }


    /**
     * @return Returns the valuesFromSessionForProd.
     */
    public String getValuesFromSessionForProd() {
        loadForProduct();
        if (null != this.adminOptionList && !this.adminOptionList.isEmpty())
            this.adminOptionListForPrint = listForPrint(this.adminOptionList);
        return valuesFromSessionForProd;
    }


    /**
     * @param valuesFromSessionForProd
     *            The valuesFromSessionForProd to set.
     */
    public void setValuesFromSessionForProd(String valuesFromSessionForProd) {
        this.valuesFromSessionForProd = valuesFromSessionForProd;
    }


    /**
     * @return Returns the valuesFromSessionForProdStruc.
     */
    public String getValuesFromSessionForProdStruc() {
        loadForProductStructure();
        if (null != this.adminOptionList && !this.adminOptionList.isEmpty())
            this.adminOptionListForPrint = listForPrint(this.adminOptionList);
        return valuesFromSessionForProdStruc;
    }


    /**
     * @param valuesFromSessionForProdStruc
     *            The valuesFromSessionForProdStruc to set.
     */
    public void setValuesFromSessionForProdStruc(
            String valuesFromSessionForProdStruc) {
        this.valuesFromSessionForProdStruc = valuesFromSessionForProdStruc;
    }


    /**
     * @return Returns the adminLevelOptionAssnSystemId.
     */
    public String getAdminLevelOptionAssnSystemId() {
        return adminLevelOptionAssnSystemId;
    }


    /**
     * @param adminLevelOptionAssnSystemId
     *            The adminLevelOptionAssnSystemId to set.
     */
    public void setAdminLevelOptionAssnSystemId(
            String adminLevelOptionAssnSystemId) {
        this.adminLevelOptionAssnSystemId = adminLevelOptionAssnSystemId;
    }


    /**
     * @return Returns the headerPanel.
     */
    public HtmlPanelGrid getHeaderPanelForBenefitComp() {
        headerPanelForBenefitComp = new HtmlPanelGrid();
        headerPanelForBenefitComp.setWidth("100%");
        HtmlOutputText outputText4 = new HtmlOutputText();
        HtmlOutputText outputText5 = new HtmlOutputText();
        HtmlOutputText outputText6 = new HtmlOutputText();
        HtmlOutputText outputText7 = new HtmlOutputText();
        outputText7.setValue("Show Hidden");
        HtmlOutputLabel lbl = new HtmlOutputLabel();
        lbl.setId("lbl"+RandomStringUtils.randomAlphanumeric(15));
        HtmlSelectBooleanCheckbox booleanHeaderCheckbox = new HtmlSelectBooleanCheckbox();
        booleanHeaderCheckbox.setId("checkboxHeaderId");
        booleanHeaderCheckbox.setOnclick("unsavedDataFinder();return false;");
        if (null != this.checkBoxValue
                && this.checkBoxValue.equalsIgnoreCase("true")) {
            booleanHeaderCheckbox.setValue(Boolean.TRUE);
        }
        outputText4.setValue("Name");
        outputText4.setStyle("width:140");
        outputText5.setValue("Admin Level");
        outputText5.setStyle("width:140");
        outputText6.setValue("Benefit Level");
        outputText6.setStyle("width:140");
        headerPanelForBenefitComp.getChildren().add(outputText4);
        headerPanelForBenefitComp.getChildren().add(outputText5);
        headerPanelForBenefitComp.getChildren().add(outputText6);
        lbl.getChildren().add(booleanHeaderCheckbox);
        lbl.getChildren().add(outputText7);
        lbl.setStyle("width:105");
        if (benefitComponentSessionObject.getBcComponentType()
                .equalsIgnoreCase("standard")) {
            headerPanelForBenefitComp.getChildren().add(lbl);
        }
        return headerPanelForBenefitComp;
    }


    public String hideAdminOption() {
        BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
                .getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        Set adminKeys = adminOptionKeyMap.keySet();
        List adminOptionList = new ArrayList();
        Iterator iterator = adminKeys.iterator();
        HideAdminOptionRequest hideAdminOptionRequest = (HideAdminOptionRequest) this
                .getServiceRequest(ServiceManager.HIDE_ADMIN_OPTION_REQUEST);
        if (isCheckBoxValueNotChanged()) {
            List validationMessages = new ArrayList();
            validationMessages.add(new InformationalMessage(
                    "benefitcomp.adminoption.nochange"));
            loadForBenefitComponent();
            addAllMessagesToRequest(validationMessages);
            return "success";
        }
        while (iterator.hasNext()) {
            Long adminLevelOptionAssnId = (Long) iterator.next();
            Boolean checkBoxValue = (Boolean) adminOptionKeyMap
                    .get(adminLevelOptionAssnId);
            AdministrationOptionVO administrationOptionVO = new AdministrationOptionVO();
            administrationOptionVO
                    .setAdminLevelOptionAssnSystemId(adminLevelOptionAssnId
                            .intValue());
            administrationOptionVO.setEntityType("BENEFITCOMP");
            administrationOptionVO.setEntitySysId(benefitComponentSessionObject
                    .getBenefitComponentId());
            if (checkBoxValue.booleanValue()) {
                administrationOptionVO.setHideFlag("T");
                administrationOptionVO.setQuestionHideFlag("T");
            } else {
                administrationOptionVO.setHideFlag("F");
                administrationOptionVO.setQuestionHideFlag("F");
            }
            //	 change for performace issue on 13th Dec 2007
            Object oldAdminOptionFlag = hiddenAdminOptionFlagMap
                    .get(adminLevelOptionAssnId);
            if (checkBoxValue.booleanValue()) {
                if (null != oldAdminOptionFlag
                        && oldAdminOptionFlag.toString().equals("F")) {
                    administrationOptionVO.setModified(true);

                }
            } else {
                if (null != oldAdminOptionFlag
                        && oldAdminOptionFlag.toString().equals("T")) {
                    administrationOptionVO.setModified(true);
                }
            }
            //end
            /*
             * BenefitComponentSessionObject benefitComponentSessionObject1 =
             * (BenefitComponentSessionObject) getSession()
             * .getAttribute("BENEFIT_COMPONENT_SESSION_KEY");
             */
            benefitCompName = benefitComponentSessionObject
                    .getBenefitComponentName();
            List businessDomain = benefitComponentSessionObject
                    .getBusinessDomainList();
            //String status=(String) benefitComponentSessionObject.getStatus();
            int version = benefitComponentSessionObject
                    .getBenefitComponentVersionNumber();
            administrationOptionVO.setStandardBenefitName(benefitCompName);
            administrationOptionVO.setBusinessDomains(businessDomain);
            //administrationOptionVO.setStandardBenefitStatus(status);
            administrationOptionVO.setMasterVersion(version);
            adminOptionList.add(administrationOptionVO);
        }
        hideAdminOptionRequest.setAdminOptionList(adminOptionList);
        HideAdminOptionResponse response = (HideAdminOptionResponse) executeService(hideAdminOptionRequest);
        if (null != response) {
        	//Code change for product structure tree rendering optimization
        	updateTreeBenefitComponent();
            List messageList = new ArrayList();
            messageList = response.getMessages();
            getSession().setAttribute("adminDeleteMessage", messageList);
        }
        this.loadForBenefitComponent();
        addAllMessagesToRequest((List) getSession().getAttribute(
                "adminDeleteMessage"));
        getSession().removeAttribute("adminDeleteMessage");
        getRequest().setAttribute("RETAIN_Value", "");
        return "success";
    }
    
    //  Code change for product structure tree rendering optimization
	
    public void updateTreeBenefitComponent() {
    	setTreeBenefitComponentUpdated(true);
    }

    public void setTreeBenefitComponentUpdated(boolean treeStructureUpdated) {
	( (BenefitComponentSessionObject) getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY) )
	.setTreeStructureUpdated(treeStructureUpdated);
    }

    /**
     * @return Returns the adminOptionKeyMap.
     */
    public Map getAdminOptionKeyMap() {
        return adminOptionKeyMap;
    }


    /**
     * @param adminOptionKeyMap
     *            The adminOptionKeyMap to set.
     */
    public void setAdminOptionKeyMap(Map adminOptionKeyMap) {
        this.adminOptionKeyMap = adminOptionKeyMap;
    }


    /**
     * @return Returns the checkedItemsList.
     */
    public List getCheckedItemsList() {
        return checkedItemsList;
    }


    /**
     * @param checkedItemsList
     *            The checkedItemsList to set.
     */
    public void setCheckedItemsList(List checkedItemsList) {
        this.checkedItemsList = checkedItemsList;
    }


    /**
     * @return Returns the checkBoxKeyMap.
     */
    public Map getCheckBoxKeyMap() {
        return checkBoxKeyMap;
    }


    /**
     * @param checkBoxKeyMap
     *            The checkBoxKeyMap to set.
     */
    public void setCheckBoxKeyMap(Map checkBoxKeyMap) {
        this.checkBoxKeyMap = checkBoxKeyMap;
    }


    /**
     * @return Returns the renderPanel.
     */
    public boolean isRenderPanel() {
        return renderPanel;
    }


    /**
     * @param renderPanel
     *            The renderPanel to set.
     */
    public void setRenderPanel(boolean renderPanel) {
        this.renderPanel = renderPanel;
    }


    /**
     * @return Returns the checkBoxValue.
     */
    public String getCheckBoxValue() {
        return checkBoxValue;
    }


    /**
     * @param checkBoxValue
     *            The checkBoxValue to set.
     */
    public void setCheckBoxValue(String checkBoxValue) {
        this.checkBoxValue = checkBoxValue;
    }


    /**
     * @return Returns the headerPanelForView.
     */
    public HtmlPanelGrid getHeaderPanelForView() {
        if (!this.isAllHidden) {
            headerPanelForView = new HtmlPanelGrid();
            headerPanelForView.setWidth("100%");
            HtmlOutputText outputText4 = new HtmlOutputText();
            HtmlOutputText outputText5 = new HtmlOutputText();
            HtmlOutputText outputText6 = new HtmlOutputText();
            HtmlOutputLabel htmlOutputLabel = new HtmlOutputLabel();
            htmlOutputLabel.setId("htmlOutputLabel"+RandomStringUtils.randomAlphanumeric(15));
            htmlOutputLabel.setValue("Show Hidden");
            outputText4.setValue("Name");
            outputText4.setStyle("width:200");
            outputText5.setValue("Admin Level");
            outputText5.setStyle("width:150");
            outputText6.setValue("Benefit Level");
            outputText6.setStyle("width:150");
            headerPanelForView.getChildren().add(outputText4);
            headerPanelForView.getChildren().add(outputText5);
            headerPanelForView.getChildren().add(outputText6);
        }
        return headerPanelForView;
    }


    /**
     * @param headerPanelForView
     *            The headerPanelForView to set.
     */
    public void setHeaderPanelForView(HtmlPanelGrid headerPanelForView) {
        this.headerPanelForView = headerPanelForView;
    }


    /**
     * @return Returns the panelForView.
     */
    public HtmlPanelGrid getPanelForView() {
        return panelForView;
    }


    /**
     * @param panelForView
     *            The panelForView to set.
     */
    public void setPanelForView(HtmlPanelGrid panelForView) {
        this.panelForView = panelForView;
    }


    /**
     * @return Returns the valuesFromSessionForBenefitCompPrint.
     */

	//WAS 7.0 Changes - Binding variable ValuesFromSessionForBenefitCompPrin modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/**
	 * 
	 * @return HtmlInputHidden
	 */
    public HtmlInputHidden  getValuesFromSessionForBenefitCompPrint() {
        this.getAdminOptionList();
        //		List adminOptionList =
        // (List)getSession().getAttribute("adminOptionList");
//        if (null != adminOptionList) {
            preparePanelForView(adminOptionList);
//        }
        //        getSession().removeAttribute("adminOptionList");
       // return "success";
            valuesFromSessionForBenefitCompPrint.setValue("success");
            return valuesFromSessionForBenefitCompPrint;  
    }


    /**
     * @param valuesFromSessionForBenefitCompPrint
     *            The valuesFromSessionForBenefitCompPrint to set.
     */
    public void setValuesFromSessionForBenefitCompPrint(
    		HtmlInputHidden  valuesFromSessionForBenefitCompPrint) {
        this.valuesFromSessionForBenefitCompPrint = valuesFromSessionForBenefitCompPrint;
    }


    public String loadPrintPage() {

        return "loadAdminOptionPrint";
    }


    private boolean isAllHidden(List adminList) {
        if (null != adminList && adminList.size() > 0) {
            for (int i = 0; i < adminList.size(); i++) {
                AdminOptionHideBO adminOptionHideBO = (AdminOptionHideBO) adminList
                        .get(i);
                if (adminOptionHideBO.getHideFlag().equalsIgnoreCase("F")) {
                    this.isAllHidden = false;
                    return false;
                }
            }
        }
        this.isAllHidden = true;
        return true;

    }


    /**
     * @param headerPanelForBenefitComp
     *            The headerPanelForBenefitComp to set.
     */
    public void setHeaderPanelForBenefitComp(
            HtmlPanelGrid headerPanelForBenefitComp) {
        this.headerPanelForBenefitComp = headerPanelForBenefitComp;
    }


    /**
     * @return Returns the panelForBenefitComp.
     */
    public HtmlPanelGrid getPanelForBenefitComp() {
        return panelForBenefitComp;
    }


    /**
     * @param panelForBenefitComp
     *            The panelForBenefitComp to set.
     */
    public void setPanelForBenefitComp(HtmlPanelGrid panelForBenefitComp) {
        this.panelForBenefitComp = panelForBenefitComp;
    }


    /**
     * @return Returns the adminOptionListForPrint.
     */
    public List getAdminOptionListForPrint() {
        return adminOptionListForPrint;
    }


    /**
     * @param adminOptionListForPrint
     *            The adminOptionListForPrint to set.
     */
    public void setAdminOptionListForPrint(List adminOptionListForPrint) {
        this.adminOptionListForPrint = adminOptionListForPrint;
    }


    /**
     * @return Returns the benefitComponentSessionObject.
     */
    public BenefitComponentSessionObject getBenefitComponentSessionObject() {
        return benefitComponentSessionObject;
    }


    /**
     * @param benefitComponentSessionObject
     *            The benefitComponentSessionObject to set.
     */
    public void setBenefitComponentSessionObject(
            BenefitComponentSessionObject benefitComponentSessionObject) {
        this.benefitComponentSessionObject = benefitComponentSessionObject;
    }


    /**
     * @return Returns the componentTypeTab.
     */
    public String getComponentTypeTab() {
        return "Administration Option";
    }


    /**
     * @param componentTypeTab
     *            The componentTypeTab to set.
     */
    public void setComponentTypeTab(String componentTypeTab) {
        this.componentTypeTab = componentTypeTab;
    }


    /**
     * @return Returns the datafieldmapForQuestionHideFlag.
     */
    public Map getDatafieldmapForQuestionHideFlag() {
        return datafieldmapForQuestionHideFlag;
    }


    /**
     * @param datafieldmapForQuestionHideFlag
     *            The datafieldmapForQuestionHideFlag to set.
     */
    public void setDatafieldmapForQuestionHideFlag(
            Map datafieldmapForQuestionHideFlag) {
        this.datafieldmapForQuestionHideFlag = datafieldmapForQuestionHideFlag;
    }


    /**
     * @param headerPanel
     *            The headerPanel to set.
     */
    public void setHeaderPanel(HtmlPanelGrid headerPanel) {
        this.headerPanel = headerPanel;
    }


    /**
     * @return Returns the isAllHidden.
     */
    public boolean isAllHidden() {
        return isAllHidden;
    }


    /**
     * @param isAllHidden
     *            The isAllHidden to set.
     */
    public void setAllHidden(boolean isAllHidden) {
        this.isAllHidden = isAllHidden;
    }


    /**
     * @param panel
     *            The panel to set.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    /**
     * @return Returns the showHiddenSelected.
     */
    public boolean isShowHiddenSelected() {
        return showHiddenSelected;
    }


    /**
     * @param showHiddenSelected
     *            The showHiddenSelected to set.
     */
    public void setShowHiddenSelected(boolean showHiddenSelected) {
        this.showHiddenSelected = showHiddenSelected;
    }


    /**
     * @return isPSorProductorBenefit
     * 
     * Returns the isPSorProductorBenefit.
     */
    public boolean getPSorProductorBenefit() {
        return isPSorProductorBenefit;
    }


    /**
     * @param isPSorProductorBenefit
     * 
     * Sets the isPSorProductorBenefit.
     */
    public void setPSorProductorBenefit(boolean isPSorProductorBenefit) {
        this.isPSorProductorBenefit = isPSorProductorBenefit;
    }


    /**
     * @return Returns the checkBoxValues.
     */
    public String getCheckBoxValues() {
        return checkBoxValues;
    }


    /**
     * @param checkBoxValues
     *            The checkBoxValues to set.
     */
    public void setCheckBoxValues(String checkBoxValues) {
        this.checkBoxValues = checkBoxValues;
    }


    private boolean isCheckBoxValueNotChanged() {
        Set adminKeys = adminOptionKeyMap.keySet();
        Iterator iterator = adminKeys.iterator();
        String temp = "";
        while (iterator.hasNext()) {
            Long adminLevelOptionAssnId = (Long) iterator.next();
            Boolean checkBoxValue = (Boolean) adminOptionKeyMap
                    .get(adminLevelOptionAssnId);
            if (checkBoxValue.booleanValue()) {
                temp += "T";
            } else {
                temp += "F";
            }
        }
        return temp.equals(checkBoxValues);
    }


    /**
     * @return Returns the saveButtonRender.
     */
    public boolean isSaveButtonRender() {
        return saveButtonRender;
    }


    /**
     * @param saveButtonRender
     *            The saveButtonRender to set.
     */
    public void setSaveButtonRender(boolean saveButtonRender) {
        this.saveButtonRender = saveButtonRender;
    }


    /**
     * @return generalBenefit
     * 
     * Returns the generalBenefit.
     */
    public boolean getGeneralBenefit() {
        return generalBenefit;
    }


    /**
     * @param generalBenefit
     * 
     * Sets the generalBenefit.
     */
    public void setGeneralBenefit(boolean generalBenefit) {
        this.generalBenefit = generalBenefit;
    }


    /**
     * @return Returns the datafieldMapForAdminLevelAsscMap.
     */
    public Map getDatafieldMapForAdminLevelAsscMap() {
        return datafieldMapForAdminLevelAsscMap;
    }


    /**
     * @param datafieldMapForAdminLevelAsscMap
     *            The datafieldMapForAdminLevelAsscMap to set.
     */
    public void setDatafieldMapForAdminLevelAsscMap(
            Map datafieldMapForAdminLevelAsscMap) {
        this.datafieldMapForAdminLevelAsscMap = datafieldMapForAdminLevelAsscMap;
    }


    /**
     * @return datafieldmapForAOHideFlag
     * 
     * Returns the datafieldmapForAOHideFlag.
     */
    public Map getDatafieldmapForAOHideFlag() {
        return datafieldmapForAOHideFlag;
    }


    /**
     * @param datafieldmapForAOHideFlag
     * 
     * Sets the datafieldmapForAOHideFlag.
     */
    public void setDatafieldmapForAOHideFlag(Map datafieldmapForAOHideFlag) {
        this.datafieldmapForAOHideFlag = datafieldmapForAOHideFlag;
    }
	/**
	 * @return Returns the adminOptionExists.
	 */
	public boolean isAdminOptionExists() {
		return adminOptionExists;
	}
	/**
	 * @param adminOptionExists The adminOptionExists to set.
	 */
	public void setAdminOptionExists(boolean adminOptionExists) {
		this.adminOptionExists = adminOptionExists;
	}
	/**
	 * @return Returns the adminOptionStates.
	 */
	public String getAdminOptionStates() {
		return adminOptionStates;
	}
	/**
	 * @param adminOptionStates The adminOptionStates to set.
	 */
	public void setAdminOptionStates(String adminOptionStates) {
		this.adminOptionStates = adminOptionStates;
	}
	/**
	 * @return Returns the hiddenForAdmin.
	 */
	public String getHiddenForAdmin() {
		if(null == this.adminOptionListForContract){
			this.adminOptionListForContract = loadAdminOptionsForContract();
		}
		return hiddenForAdmin;
	}
	/**
	 * @param hiddenForAdmin The hiddenForAdmin to set.
	 */
	public void setHiddenForAdmin(String hiddenForAdmin) {
		this.hiddenForAdmin = hiddenForAdmin;
	}
	
	private List loadAdminOptionsForContract() {

		List adminList = new ArrayList();
		LocateAdministrationOptionRequest request = (LocateAdministrationOptionRequest) this
				.getServiceRequest(ServiceManager.LOCATE_ADMIN_OPTION_REQUEST);

		ContractSession contractSessionObject = (ContractSession) getSession()
				.getAttribute(CONTRACT_SESSION_KEY);

		if (null != getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_DESC)) {
			this.setBenefitAdminName(getSession().getAttribute(
					WebConstants.SESSION_BNFT_ADMIN_DESC).toString());
		}
		int productSysId=  contractSessionObject.getContractKeyObject().getProductId();
		request.setBenefitAdminSysId(contractSessionObject.getBenefitAdminId());
		//becoze we are going to querry from the product table.
		request.setEntityId(productSysId);
		request.setEntityType(BusinessConstants.ENTITY_TYPE_CONTRACT);
		request.setBenefitComponentId(contractSessionObject.getBenefitComponentId());
		request.setDateSegmentId(contractSessionObject.getContractKeyObject().getDateSegmentId());
		
		LocateAdministrationOptionResponse response = (LocateAdministrationOptionResponse) executeService(request);

		if (null != response.getAssociatedBenefitAdministrationOptionList()
				&& !response.getAssociatedBenefitAdministrationOptionList()
						.isEmpty()) {
			this.adminOptionListForContract = response
					.getAssociatedBenefitAdministrationOptionList();

			//     Added - Jan 23rd            
			if (response.getHiddenAdminOptionCount() != 0) {
				this.setAdminOptionExists(true);
			}

			return response.getAssociatedBenefitAdministrationOptionList();
		} else {
			return null;
		}

	}
	
	/**
	 * @return Returns the adminOptionListForContract.
	 */
	public List getAdminOptionListForContract() {
		return adminOptionListForContract;
	}
	/**
	 * @param adminOptionListForContract The adminOptionListForContract to set.
	 */
	public void setAdminOptionListForContract(List adminOptionListForContract) {
		this.adminOptionListForContract = adminOptionListForContract;
	}
}