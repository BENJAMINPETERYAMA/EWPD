/*
 * AdminMethodBackingBean
 * 
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;

import com.wellpoint.wpd.business.adminmethod.locatecriteria.AdminMethodLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodTierOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBOComparator;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodOverrideRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodValidationStatusRequest;
import com.wellpoint.wpd.common.adminmethod.request.GeneralBenefitAdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.OverrideAdminMethodRequest;
import com.wellpoint.wpd.common.adminmethod.request.SaveAdminMethodRequest;
import com.wellpoint.wpd.common.adminmethod.request.SaveAdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodOverrideResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationStatusResponse;
import com.wellpoint.wpd.common.adminmethod.response.GeneralBenefitAdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.OverrideAdminMethodResponse;
import com.wellpoint.wpd.common.adminmethod.response.SaveAdminMethodResponse;
import com.wellpoint.wpd.common.adminmethod.response.SaveAdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminmethod.vo.AdminMethodVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.tierdefinition.request.TierDefinitionRetrieveRequest;
import com.wellpoint.wpd.common.tierdefinition.response.TierDefinitionRetrieveResponse;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.contract.ContractBasicInfoBackingBean;
import com.wellpoint.wpd.web.contract.ContractCommentBackingBean;
import com.wellpoint.wpd.web.contract.ContractNotesBackingBean;
import com.wellpoint.wpd.web.contract.ContractPricingInfoBackingBean;
import com.wellpoint.wpd.web.contract.ContractProductAdminOptionBackingBean;
import com.wellpoint.wpd.web.contract.ContractRuleBackingBean;
import com.wellpoint.wpd.web.contract.ContractSession;
import com.wellpoint.wpd.web.contract.ContractSpecificInfoBackingBean;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductAdminAssociationBackingBean;
import com.wellpoint.wpd.web.product.ProductComponentAssociationBackingBean;
import com.wellpoint.wpd.web.product.ProductDenialAndExclusionBackingBean;
import com.wellpoint.wpd.web.product.ProductGeneralInformationBackingBean;
import com.wellpoint.wpd.web.product.ProductNoteAssociationBackingBean;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.productstructure.ProductStructureBenefitComponentBackingBean;
import com.wellpoint.wpd.web.productstructure.ProductStructureGeneralInfoBackingBean;
import com.wellpoint.wpd.web.productstructure.ProductStructureSessionObject;
import com.wellpoint.wpd.web.standardbenefit.StandardBenefitSessionObject;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.messages.Message;

/**
 * Backing bean for admin Method
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodBackingBean extends WPDBackingBean {

	private List adminMethodsList = new ArrayList();
	private List adminMethodSaveMessgeList = new ArrayList(); //Added for Informational Messages
	String bcName = "";
	AdminMethodLocateCriteria adminMethodLocateCriteria = new AdminMethodLocateCriteria();
	HtmlPanelGrid panel = new HtmlPanelGrid();
	HtmlPanelGrid panelForValidation = new HtmlPanelGrid(); 
	/*START AM1 CARS */
	HtmlPanelGrid panelForTierValidation = new HtmlPanelGrid(); 
	/*END AM1 CARS */
	HtmlPanelGrid panelForContractValidation = new HtmlPanelGrid();

	HtmlPanelGrid panelView = new HtmlPanelGrid();
	HtmlPanelGrid panelForOverride = new HtmlPanelGrid();
	HtmlPanelGrid panelForOverrideView = new HtmlPanelGrid();
	HtmlPanelGrid panelForOverrideViewContract = new HtmlPanelGrid();
	HtmlPanelGrid panelForOverridePrintContract = new HtmlPanelGrid();
	private Map adminMethodMap = new LinkedHashMap();
	private HtmlPanelGrid displayPanel = new HtmlPanelGrid();
	//CARS:AM2:START:
	private HtmlPanelGrid contractDisplayPanel = new HtmlPanelGrid();
	HtmlPanelGrid contractPanelForOverride = new HtmlPanelGrid();
	private boolean renderPanel = true;
	private boolean printMode= false;
	
	//CARS:AM2:END:
	private int stdbenId;
	private int adminId;
	private String loadForBenefit;
	private String entityType;
	private String componentType;
	private String adminMethodPrint;
	private String adminMethodValidationBreadCrumb;
	//CARS:AM2:START:
	private List tierDefMap = new ArrayList();
	private boolean renderTierPanel = true;     
    private HtmlPanelGrid tierPanel = new HtmlPanelGrid();    
    private List benefitDefinitionsList = new ArrayList();
    private List tierCriteriaList = null;
    private List tieredAdminMethodList = null;
    private List tierDefinitionsList = new ArrayList();
    private Map spsTierNameMap = new HashMap();   
    private Map adminMethodTierMap = new HashMap();
    private HtmlPanelGrid panelForOverrideForPrint = new HtmlPanelGrid(); 
    private HtmlPanelGrid tierDisplayPanel = new HtmlPanelGrid();
    private HtmlPanelGrid displayPanelForPrint = new HtmlPanelGrid();
    private HtmlPanelGrid tierDisplayPanelForPrint = new HtmlPanelGrid();
    private HtmlPanelGrid tierColumnHeaderPanel = new HtmlPanelGrid();
    private HtmlPanelGrid columnHeaderPanelForCheckin = new HtmlPanelGrid();
    private HtmlPanelGrid tierColumnHeaderPanelForCheckin = new HtmlPanelGrid();
    private HtmlPanelGrid tierPanelForView = new HtmlPanelGrid();
    private HtmlPanelGrid tierPanelForPrint = new HtmlPanelGrid();
    private boolean isPOS = false;
    private HtmlPanelGrid tierColumnHeaderPanelForContract = new HtmlPanelGrid();
    private String productFamily = "";
    private HtmlPanelGrid columnHeaderPanel = new HtmlPanelGrid();
    private HtmlPanelGrid contractTierPanel = new HtmlPanelGrid();  
	//CARS:AM2:END
	//CARS:AM1:START:
	 private Map benefitTierAdministrationMap = new HashMap();	
	 private boolean submitFlag=false;
	//CARS:AM1:END	
	 
	 private List changedTierIdsList=new ArrayList();
	 private Map changedTierAmIdsMap=new HashMap();
 
	StandardBenefitSessionObject benefitSessionObject = (StandardBenefitSessionObject) getSession()
			.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);

	ProductStructureSessionObject productStructureSessionObject = new ProductStructureSessionObject();

	ProductSessionObject productSessionObject = new ProductSessionObject();

	public static String PRODUCT_STRUCTURE_SESSION_KEY = "productStructure";

	public static String PRODUCT_SESSION_KEY = WebConstants.PROD_TYPE;

	private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD;

	//PRODUCT_STRUCTURE_SESSION_KEY

	public static String CONTRACT_SESSION_KEY = "contract";

	private Map spsNameMap = new LinkedHashMap();

	private Map benefitAdministrationMap = new LinkedHashMap();

	private List validationMessages;

	//private String valuesFromSessionForContract;
	private HtmlInputHidden valuesFromSessionForContract =new HtmlInputHidden();
	//private String valuesFromSessionForBenefit;
	private HtmlInputHidden valuesFromSessionForBenefit =new HtmlInputHidden();

	//private String valuesFromSessionForBenefitComp;
	private HtmlInputHidden valuesFromSessionForBenefitComp =new HtmlInputHidden();


	//private String valuesFromSessionForProdStruc;
	private HtmlInputHidden valuesFromSessionForProdStruc=new HtmlInputHidden();

	private String breadCrumbText;

	//private String valuesFromSessionForProd;
	private HtmlInputHidden valuesFromSessionForProd= new HtmlInputHidden();

	private String breadCrumpName;

	private String loadContractPageForPrint;

	private String loadBenefitCompPageForPrint;

	private String loadProductStructPageForPrint;

	private String loadProductPageForPrint;

	private String loadBenefitPageForPrint;

	private String adminMethodState;

	private String dummyHiddenVarForWaitPage = "CONTINUE";

	public static String BENEFIT_ADMIN = "Benefit-Administration";

	public static String SESSION_NODE_TYPE = "SESSION_NODE_TYPE_COMP";

	public static String BENEFIT_DATE = "BenefitDate";

	public static String BENEFIT_COMP_KEY = "BNFT_CMPNT_KEY";

	public static String ADMIN_KEY = "ADMIN_KEY";

	public static String PRODUCT_NODE_TYPE = "PRODUCT_NODE_TYPE";

	public static String TILDA = "~";

	public static String PROD_STRUCTURE = "PRODSTRUCTURE";

	public static String PROD_STRUCT = "PRODUCTSTRUCT";

	public static String STANDARD_BENEFIT = "STANDARDBENEFIT";

	private List administrationSPSList;

	private boolean typeFlag = true;

	private String breadCrump;

	private String breadcrumbValue;

	private boolean validationStatus = true;

	private String crrntlyPrcssngBCmpnt;

	private boolean hasValidationErrors = false;
    private List benefitTierDefinitionsList = null; //CARS:AM2
    boolean isTierPOS =false; 
    private String productFamilyForTier = "";	
    public void setTierColumnHeaderPanelForContract(
			HtmlPanelGrid tierColumnHeaderPanelForContract) {
		this.tierColumnHeaderPanelForContract = tierColumnHeaderPanelForContract;
	}	
	
	public boolean isTierPOS() {
		return isTierPOS;
	}
	public void setTierPOS(boolean isTierPOS) {
		this.isTierPOS = isTierPOS;
	}

	public String getProductFamilyForTier() {
		return productFamilyForTier;
	}

	public void setProductFamilyForTier(String productFamilyForTier) {
		this.productFamilyForTier = productFamilyForTier;
	}
	public AdminMethodBackingBean() {

	}
	public HtmlPanelGrid getContractTierPanel() {
		Logger.logInfo("entered method getContractTierPanel");
		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;
		contractTierPanel = new HtmlPanelGrid();
		if (null != tierDefinitionsList) 
		{
			sortTiers(tierDefinitionsList); 
		}		     
		int mode =-1;
	   	if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(entityType))
	   	 {
	   	    mode = getContractSession().getMode();	
	   	 }
	   	 else if(BusinessConstants.ENTITY_TYPE_PRODUCT.equalsIgnoreCase(entityType))
	   	 {
	   	    mode = getProductSessionObject().getMode();	
	   	 }
		contractTierPanel.setColumns(1);
		contractTierPanel.setWidth("100%");
		contractTierPanel.setBorder(0);
		contractTierPanel.setCellpadding("0");
		contractTierPanel.setCellspacing("0");
		contractTierPanel.setStyleClass("outputText");
		contractTierPanel.setBgcolor("#cccccc");		
		if(isPrintMode())
		{
			contractTierPanel.setRowClasses("dataTableOddRow");  
		}			
		StringBuffer rows = new StringBuffer();
		int size = 0;		
		HtmlPanelGrid tierDefPanel = null;
		HtmlPanelGrid tierCritPanel = null;
		HtmlPanelGrid tierHeaderPanel = null;
		HtmlPanelGrid tierHeaderPanel1 = null;
		HtmlPanelGrid tierLevelPanel = null;
		BenefitTierDefinition tierDefinition = null;
		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		isTierPOS = getContractSession().isTierPOS();
		// iterating to get the benefit levels
		for (int l = 0; l < tierDefinitionsList.size(); l++)
		{
			HtmlPanelGrid grid1 = new HtmlPanelGrid();
			grid1.setBgcolor("#FFFFFF");
			contractTierPanel.getChildren().add(grid1);
			tierDefinition = (BenefitTierDefinition) tierDefinitionsList.get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");
			//tierDefPanel.setBorder(0); //todooo
			tierDefPanel.setCellpadding("0");
			tierDefPanel.setCellspacing("0");
			tierDefPanel.setBgcolor("#cccccc");

			HtmlOutputLabel defLabel = new HtmlOutputLabel();	
			defLabel.setId("defLabel11"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			if(isPrintMode())
			{
				tierDefPanel.setBorder(1);
				tierDefPanel.setRowClasses("dataTableOddRow");
			}
			else if(mode == ContractSession.EDIT_MODE || mode == ContractSession.VIEW_MODE)
			{
			   tierDef.setStyleClass("dataTableHeader1");
			   tierDefPanel.setBorder(0);
			}
			
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			tierDef.setId("tierDef_"+l+"_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");
			
			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
			hidDefId.setId("tierDef_"+l+"_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");
			String tempKey = tierDefinition.getBenefitTierDefinitionSysId()+"";
			defLabel.getChildren().add(tierDef);
			tierDefPanel.getChildren().add(defLabel);
			contractTierPanel.getChildren().add(tierDefPanel);
			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++){			
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				tierCritPanel = new HtmlPanelGrid();
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");
				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("0");
				tierCritPanel.setCellspacing("0");
				tierCritPanel.setBgcolor("#cccccc");				
				tierCritPanel.setRowClasses("dataTableOddRow");		
				
				tierHeaderPanel = new HtmlPanelGrid();
				tierHeaderPanel.setCellpadding("0");
				tierHeaderPanel.setCellspacing("0");
				tierHeaderPanel.setColumns(2);
				tierHeaderPanel.setWidth("100%");
				tierHeaderPanel1 = new HtmlPanelGrid();
				tierHeaderPanel1.setCellpadding("0");
				tierHeaderPanel1.setCellspacing("0");
				tierHeaderPanel1.setColumns(2);
				tierHeaderPanel1.setWidth("100%");
				if(isPrintMode()){
					tierHeaderPanel.setBgcolor("#CCCCCC");
					tierHeaderPanel.setRowClasses("dataTableOddRow");
					tierHeaderPanel1.setBgcolor("#CCCCCC");
					tierHeaderPanel1.setRowClasses("dataTableOddRow");
				}
				else if(mode == ContractSession.EDIT_MODE || mode == ContractSession.VIEW_MODE){
					tierHeaderPanel.setBgcolor("#FFFFFF");
					tierHeaderPanel.setStyleClass("headerPanel1");
					tierHeaderPanel1.setBgcolor("#FFFFFF");
					tierHeaderPanel1.setStyleClass("headerPanel1");
				}
				
				
				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel10"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputLabel tierlabel1 = new HtmlOutputLabel();
				tierlabel1.setId("tierlabel11"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:140px;");
				if (null != critList) 
				{
					for (int k = 0; k < critList.size(); k++) 
					{
						if (k < 2 ){
							tierlabel.getChildren().add(dummylabel);
							tierCriteria = new BenefitTierCriteria();
							tierCriteria = (BenefitTierCriteria) critList.get(k);
							HtmlOutputText tierCrit = new HtmlOutputText();
							if(!isPrintMode()){
								tierCrit.setStyle("color:blue");
							}
							tierCrit.setValue(tierCriteria.getBenefitTierCriteriaName()+" : ");
							tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo+"_Id");
							tierlabel.getChildren().add(tierCrit);
							HtmlOutputText critValueView = new HtmlOutputText();
							String critVal = tierCriteria.getBenefitTierCriteriaValue();						
							critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
							tierlabel.getChildren().add(critValueView);
						}
						else if (k >= 2){
							tierlabel1.getChildren().add(dummylabel1);
							tierCriteria = new BenefitTierCriteria();
							tierCriteria = (BenefitTierCriteria) critList.get(k);
							HtmlOutputText tierCrit = new HtmlOutputText();
							if(!isPrintMode()){
								tierCrit.setStyle("color:blue");
							}
							tierCrit.setValue(tierCriteria.getBenefitTierCriteriaName()+" : ");
							tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo+"_Id");
							tierlabel1.getChildren().add(tierCrit);
							HtmlOutputText critValueView = new HtmlOutputText();
							String critVal = tierCriteria.getBenefitTierCriteriaValue();						
							critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
							tierlabel1.getChildren().add(critValueView);
						}
					}
				}
				tierHeaderPanel.getChildren().add(tierlabel);
				tierCritPanel.getChildren().add(tierHeaderPanel);
				if (critList.size() > 2){
					tierHeaderPanel1.getChildren().add(tierlabel1);
					tierCritPanel.getChildren().add(tierHeaderPanel1);
				}
				int sizeOfLevelList = 0;
				tierLevelPanel = new HtmlPanelGrid();			
				tierLevelPanel.setColumns(3);
				
				if(isTierPOS)
				{
					tierLevelPanel.setColumns(3);
					tierLevelPanel.setColumnClasses("column20pct,column40px,column40px");
				}
				else
				{
					tierLevelPanel.setColumns(2);
					tierLevelPanel.setColumnClasses("column40px,column60pct");
				}
				
				tierLevelPanel.setWidth("100%");
				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("1");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc"); 				
				if(isPrintMode()){
					tierLevelPanel.setRowClasses("dataTableOddRow");
				}
				else if(mode == ContractSession.EDIT_MODE || mode == ContractSession.VIEW_MODE){
					tierLevelPanel.setRowClasses("dataTableEvenRow,dataTableOddRow");					
				}
				
				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					BenefitTier benefitTier = (BenefitTier) tieredAdminMethodList.get(i);
					if (benefitTier.getBenefitTierSysId() == tier.getBenefitTierSysId())
					{		
						if(benefitTier.getAdminMethods() == null || benefitTier.getAdminMethods().size() == 0)
						{
							HtmlOutputLabel label =new HtmlOutputLabel();
							label.setId("label12"+RandomStringUtils.randomAlphanumeric(15));
							HtmlOutputText text = new HtmlOutputText();
							text.setValue(WebConstants.NO_SPS_FOR_TERMS_IN_TIER);
							label.getChildren().add(text);
							tierLevelPanel.setColumns(1);
							tierLevelPanel.getChildren().add(label);
						}
						else
						{						  
							prepareSPSAMMappingLinesWithProductFamily(i,benefitTier,tierLevelPanel);
						}
					}																							
				}
			tierCritPanel.getChildren().add(tierLevelPanel);
			tierDefPanel.getChildren().add(tierCritPanel);
			}
		}
		return contractTierPanel;		
	}
	public void setContractTierPanel(HtmlPanelGrid contractTierPanel) {
		this.contractTierPanel = contractTierPanel;
	}
	//CARS|POS|AM2|}
	public List getAdminMethodLists() {
		return adminMethodsList;
	}

	public String checkForEntityCheckinUpdate() {

		AdminMethodValidationStatusRequest request = (AdminMethodValidationStatusRequest) getServiceRequest(ServiceManager.ADMIN_METHOD_CHECKIN_VALIDATION_REQ);
		Integer entityId = (Integer) getSession().getAttribute(
				WebConstants.ENTITY_ID_FOR_CHECKIN);
		Integer contractId = (Integer) getSession().getAttribute(
				WebConstants.CONTRACT_ID_FOR_CHECKIN);
		String entityType = (String) getSession().getAttribute(
				WebConstants.ENTITY_TYPE_FOR_CHECKIN);
		request.setEntityId(entityId.intValue());
		request.setEntityType(entityType);
		if (null != contractId) {
			request.setContractId(contractId.intValue());
		}

		AdminMethodValidationStatusResponse response = (AdminMethodValidationStatusResponse) executeService(request);

		if (null != response) {
			crrntlyPrcssngBCmpnt = response.getBeneftiComponentName();
			switch (response.getStatus()) {
			case AdminMethodValidationStatusResponse.VALIDATION_ERRORS:
				return handleValidationErrors();
			case AdminMethodValidationStatusResponse.VALIDATION_SUCCESS:
				return validationSuccess();
			case AdminMethodValidationStatusResponse.VALIDATION_WAITING:
				if (null != response.getBeneftiComponentName()) {
					bcName = response.getBeneftiComponentName();
				}
				return "";
			default:
				return validationFail();
			}
		} else {
			return validationFail();
		}
	}

	/**
	 * @return
	 */
	private String validationFail() {
		String key = (String) getSession().getAttribute(
				WebConstants.OBJECT_KEY_FOR_CHECKIN);
		String returnValue = key;
		Object object = getSession().getAttribute(
				WebConstants.OBJECT_VALUE_FOR_CHECKIN);
		getRequest().setAttribute(key, object);
		getSession().removeAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN);
		addMessageToRequest(new ErrorMessage(
				WebConstants.CHECK_IN_VALID_SUCCESS_CHECK_FAIL));
		Integer action = (Integer) getSession().getAttribute(
				WebConstants.ACTION_FOR_CHECKIN);
		if (null != action)
			returnValue += action.intValue();
		return returnValue;
	}

	/**
	 * @return
	 */
	private String validationSuccess() {
		String key = (String) getSession().getAttribute(
				WebConstants.OBJECT_KEY_FOR_CHECKIN);
		Object object = getSession().getAttribute(
				WebConstants.OBJECT_VALUE_FOR_CHECKIN);
		Integer action = (Integer) getSession().getAttribute(
				WebConstants.ACTION_FOR_CHECKIN);
		String returnValue = key;
		String tempReturnValue = "";
		getRequest().setAttribute(key, object);
		String entityName = (String) getSession()
				.getAttribute("AM_ENTITY_NAME");
		if ("productGeneralInformationBackingBean".equals(key)) {
			ProductGeneralInformationBackingBean bean = (ProductGeneralInformationBackingBean) object;
			bean.setHasValidationErrors(false);
			tempReturnValue = bean.done();
		} else if ("productComponentAssociationBackingBean".equals(key)) {
			ProductComponentAssociationBackingBean bean = (ProductComponentAssociationBackingBean) object;
			bean.setHasValidationErrors(false);
			tempReturnValue = bean.done();
		} else if ("productAdminAssociationBackingBean".equals(key)) {
			ProductAdminAssociationBackingBean bean = (ProductAdminAssociationBackingBean) object;
			bean.setHasValidationErrors(false);
			tempReturnValue = bean.done();
		} else if ("productNoteAssociationBackingBean".equals(key)) {
			ProductNoteAssociationBackingBean bean = (ProductNoteAssociationBackingBean) object;
			bean.setHasValidationErrors(false);
			tempReturnValue = bean.done();
		} else if ("productDenialAndExclusionBackingBean".equals(key)) {
			ProductDenialAndExclusionBackingBean bean = (ProductDenialAndExclusionBackingBean) object;
			bean.setHasValidationErrors(false);
			tempReturnValue = bean.done();
		} else if ("productStructureGeneralInfoBackingBean".equals(key)) {
			ProductStructureGeneralInfoBackingBean bean = (ProductStructureGeneralInfoBackingBean) object;
			bean.setHasValidationErrors(false);
			if (null != action && action.intValue() == 1) {
				tempReturnValue = bean.checkInGenralInfo();
				returnValue = returnValue + action.intValue();
			}
		} else if ("productStructureBenefitComponentBackingBean".equals(key)) {
			ProductStructureBenefitComponentBackingBean bean = (ProductStructureBenefitComponentBackingBean) object;
			bean.setHasValidationErrors(false);
			if (null != action && action.intValue() == 2) {
				tempReturnValue = bean.checkIn();
				returnValue = returnValue + action.intValue();
			}
		} else if ("contractBasicInfoBackingBean".equals(key) && action != null) {
			ContractBasicInfoBackingBean bean = (ContractBasicInfoBackingBean) object;
			bean.setHasValidationErrors(false);
			getRequest().setAttribute(
					"breadCrumbText",
					"Contract Development >> Contract (" + entityName
							+ ") >> Edit");
			if (action.intValue() == 1)
				tempReturnValue = bean.done();
			else if (action.intValue() == 2)
				tempReturnValue = bean.doneMember();
			returnValue += action.intValue();
		} else if ("contractPricingInfoBackingBean".equals(key)) {
			ContractPricingInfoBackingBean bean = (ContractPricingInfoBackingBean) object;
			bean.setHasValidationErrors(false);
			getRequest().setAttribute(
					"breadCrumbText",
					"Contract Development >> Contract (" + entityName
							+ ") >> Edit");
			tempReturnValue = bean.done();
		} else if ("ContractNotesBackingBean".equals(key)) {
			ContractNotesBackingBean bean = (ContractNotesBackingBean) object;
			bean.setHasValidationErrors(false);
			getRequest().setAttribute(
					"breadCrumbText",
					"Contract Development >> Contract (" + entityName
							+ ") >> Edit");
			tempReturnValue = bean.done();
		} else if ("contractCommentBackingBean".equals(key) && action != null) {
			ContractCommentBackingBean bean = (ContractCommentBackingBean) object;
			bean.setHasValidationErrors(false);
			getRequest().setAttribute(
					"breadCrumbText",
					"Contract Development >> Contract (" + entityName
							+ ") >> Edit");
			tempReturnValue = bean.done();
			returnValue += action.intValue();
		} else if ("contractProductAdminOptionBackingBean".equals(key)) {
			ContractProductAdminOptionBackingBean bean = (ContractProductAdminOptionBackingBean) object;
			bean.setHasValidationErrors(false);
			getRequest().setAttribute(
					"breadCrumbText",
					"Contract Development >> Contract (" + entityName
							+ ") >> Edit");
			tempReturnValue = bean.done();
		} else if ("contractRuleBackingBean".equals(key)) {
			ContractRuleBackingBean bean = (ContractRuleBackingBean) object;
			bean.setHasValidationErrors(false);
			getRequest().setAttribute(
					"breadCrumbText",
					"Contract Development >> Contract (" + entityName
							+ ") >> Edit");
			tempReturnValue = bean.done();
		} else if ("contractSpecificInfoBackingBean".equals(key)
				&& action != null) {
			ContractSpecificInfoBackingBean bean = (ContractSpecificInfoBackingBean) object;
			bean.setHasValidationErrors(false);
			getRequest().setAttribute(
					"breadCrumbText",
					"Contract Development >> Contract (" + entityName
							+ ") >> Edit");
			tempReturnValue = bean.done();
			returnValue += action.intValue();
		}

		if ("createProduct".equals(tempReturnValue)
				|| "productStructureCheckIn".equals(tempReturnValue)
				|| "CONTRACTCREATE".equals(tempReturnValue)) {
			returnValue = tempReturnValue;
		}

		getSession().removeAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ACTION_FOR_CHECKIN);

		return returnValue;
	}

	/**
	 * @return
	 */
	private String handleValidationErrors() {
		String key = (String) getSession().getAttribute(
				WebConstants.OBJECT_KEY_FOR_CHECKIN);
		Object object = getSession().getAttribute(
				WebConstants.OBJECT_VALUE_FOR_CHECKIN);
		Integer action = (Integer) getSession().getAttribute(
				WebConstants.ACTION_FOR_CHECKIN);
		String returnValue = key;
		getRequest().setAttribute(key, object);
		String entityId = "";
		String entityType = "";
		String entityName = "";
		String contractId = "";
		if ("productGeneralInformationBackingBean".equals(key)) {
			ProductGeneralInformationBackingBean bean = (ProductGeneralInformationBackingBean) object;
			entityId = getProductSessionObject().getProductKeyObject()
					.getProductId()
					+ "";
			entityName = getProductSessionObject().getProductKeyObject()
					.getProductName();
			entityType = "product";
			bean.setHasValidationErrors(true);
			bean.setBreadCumbTextForEdit();
		} else if ("productComponentAssociationBackingBean".equals(key)) {
			ProductComponentAssociationBackingBean bean = (ProductComponentAssociationBackingBean) object;
			entityId = getProductSessionObject().getProductKeyObject()
					.getProductId()
					+ "";
			entityName = getProductSessionObject().getProductKeyObject()
					.getProductName();
			entityType = "product";
			bean.setHasValidationErrors(true);
			bean.setBreadCumbTextForEdit();
		} else if ("productAdminAssociationBackingBean".equals(key)) {
			ProductAdminAssociationBackingBean bean = (ProductAdminAssociationBackingBean) object;
			entityId = getProductSessionObject().getProductKeyObject()
					.getProductId()
					+ "";
			entityName = getProductSessionObject().getProductKeyObject()
					.getProductName();
			entityType = "product";
			bean.setHasValidationErrors(true);
			bean.setBreadCumbTextForEdit();
		} else if ("productNoteAssociationBackingBean".equals(key)) {
			ProductNoteAssociationBackingBean bean = (ProductNoteAssociationBackingBean) object;
			entityId = getProductSessionObject().getProductKeyObject()
					.getProductId()
					+ "";
			entityName = getProductSessionObject().getProductKeyObject()
					.getProductName();
			entityType = "product";
			bean.setHasValidationErrors(true);
			bean.setBreadCumbTextForEdit();
		} else if ("productDenialAndExclusionBackingBean".equals(key)) {
			ProductDenialAndExclusionBackingBean bean = (ProductDenialAndExclusionBackingBean) object;
			entityId = getProductSessionObject().getProductKeyObject()
					.getProductId()
					+ "";
			entityName = getProductSessionObject().getProductKeyObject()
					.getProductName();
			entityType = "product";
			bean.setHasValidationErrors(true);
			bean.setBreadCumbTextForEdit();
		} else if ("productStructureGeneralInfoBackingBean".equals(key)) {
			ProductStructureGeneralInfoBackingBean bean = (ProductStructureGeneralInfoBackingBean) object;
			entityId = getProductStructureSessionObject().getId() + "";
			entityName = getProductStructureSessionObject().getName();
			entityType = "ProdStructure";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbTextForEdit();
			if (null != action) {
				returnValue = returnValue + action.intValue();
			}
		} else if ("productStructureBenefitComponentBackingBean".equals(key)) {
			ProductStructureBenefitComponentBackingBean bean = (ProductStructureBenefitComponentBackingBean) object;
			ProductStructureGeneralInfoBackingBean bean2 = (ProductStructureGeneralInfoBackingBean) getSession()
					.getAttribute("productStructureGeneralInfoBackingBean");
			getRequest().setAttribute("productStructureGeneralInfoBackingBean",
					bean2);
			getSession().removeAttribute(
					"productStructureGeneralInfoBackingBean");
			entityId = getProductStructureSessionObject().getId() + "";
			entityName = getProductStructureSessionObject().getName();
			entityType = "ProdStructure";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbTextForEdit();
			if (null != action) {
				returnValue = returnValue + action.intValue();
			}
		} else if ("contractBasicInfoBackingBean".equals(key) && action != null) {
			ContractBasicInfoBackingBean bean = (ContractBasicInfoBackingBean) object;
			entityId = getContractSession().getContractKeyObject()
					.getDateSegmentId()
					+ "";
			entityName = getContractSession().getContractKeyObject()
					.getContractId();
			contractId = getContractSession().getContractKeyObject()
					.getContractSysId()
					+ "";
			entityType = "contract";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbText();
			returnValue = returnValue + action.intValue();
		} else if ("contractPricingInfoBackingBean".equals(key)) {
			ContractPricingInfoBackingBean bean = (ContractPricingInfoBackingBean) object;
			entityId = getContractSession().getContractKeyObject()
					.getDateSegmentId()
					+ "";
			entityName = getContractSession().getContractKeyObject()
					.getContractId();
			contractId = getContractSession().getContractKeyObject()
					.getContractSysId()
					+ "";
			entityType = "contract";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbText();
		} else if ("ContractNotesBackingBean".equals(key)) {
			ContractNotesBackingBean bean = (ContractNotesBackingBean) object;
			entityId = getContractSession().getContractKeyObject()
					.getDateSegmentId()
					+ "";
			entityName = getContractSession().getContractKeyObject()
					.getContractId();
			contractId = getContractSession().getContractKeyObject()
					.getContractSysId()
					+ "";
			entityType = "contract";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbText();
		} else if ("contractCommentBackingBean".equals(key) && action != null) {
			ContractCommentBackingBean bean = (ContractCommentBackingBean) object;
			entityId = getContractSession().getContractKeyObject()
					.getDateSegmentId()
					+ "";
			entityName = getContractSession().getContractKeyObject()
					.getContractId();
			contractId = getContractSession().getContractKeyObject()
					.getContractSysId()
					+ "";
			entityType = "contract";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbText();
			returnValue += action.intValue();
		} else if ("contractProductAdminOptionBackingBean".equals(key)) {
			ContractProductAdminOptionBackingBean bean = (ContractProductAdminOptionBackingBean) object;
			entityId = getContractSession().getContractKeyObject()
					.getDateSegmentId()
					+ "";
			entityName = getContractSession().getContractKeyObject()
					.getContractId();
			contractId = getContractSession().getContractKeyObject()
					.getContractSysId()
					+ "";
			entityType = "contract";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbText();
		} else if ("contractRuleBackingBean".equals(key)) {
			ContractRuleBackingBean bean = (ContractRuleBackingBean) object;
			entityId = getContractSession().getContractKeyObject()
					.getDateSegmentId()
					+ "";
			entityName = getContractSession().getContractKeyObject()
					.getContractId();
			contractId = getContractSession().getContractKeyObject()
					.getContractSysId()
					+ "";
			entityType = "contract";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbText();
		} else if ("contractSpecificInfoBackingBean".equals(key)
				&& action != null) {
			ContractSpecificInfoBackingBean bean = (ContractSpecificInfoBackingBean) object;
			entityId = getContractSession().getContractKeyObject()
					.getDateSegmentId()
					+ "";
			entityName = getContractSession().getContractKeyObject()
					.getContractId();
			contractId = getContractSession().getContractKeyObject()
					.getContractSysId()
					+ "";
			entityType = "contract";
			bean.setHasValidationErrors(true);
			bean.setBreadCrumbText();
			returnValue += action.intValue();
		}

		getSession().setAttribute("AM_ENTITY_ID", entityId);
		getSession().setAttribute("AM_ENTITY_TYPE", entityType);
		getSession().setAttribute("AM_ENTITY_NAME", entityName);
		getSession().setAttribute("AM_CONTRACT_ID", contractId);

		getSession().removeAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN);
		getSession().removeAttribute(WebConstants.ACTION_FOR_CHECKIN);
		return returnValue;
	}

	private ContractSession getContractSession() {
		ContractSession contractSessionObject = (ContractSession) getSession()
				.getAttribute(CONTRACT_SESSION_KEY);
		if (contractSessionObject == null) {
			contractSessionObject = new ContractSession();
			getSession().setAttribute(CONTRACT_SESSION_KEY,
					contractSessionObject);
		}
		return contractSessionObject;
	}

	/**
	 * @param searchResultList
	 * @return
	 */
	private List retrieveBenefitAdminMethodBOList() {
		GeneralBenefitAdminMethodValidationRequest gnrlBenefitAdminMethodRequest = (GeneralBenefitAdminMethodValidationRequest) this
				.getServiceRequest(ServiceManager.ADMIN_METHOD_REQUEST);
		gnrlBenefitAdminMethodRequest
				.setAdminMethodLocateCriteria(adminMethodLocateCriteria);
		GeneralBenefitAdminMethodValidationResponse gnrlBenefitAdminMethodResponse = (GeneralBenefitAdminMethodValidationResponse) this
				.executeService(gnrlBenefitAdminMethodRequest);
		if (null != gnrlBenefitAdminMethodResponse) {
			/**Set the resulting General Benefit from the database
			   from the response to the searchResultList**/
			this.adminMethodsList = gnrlBenefitAdminMethodResponse.getSpsList();
			getBenefitSessionObject().setAdminMethodsList(adminMethodsList);
		}
		/***
      	 * Added for Informational Message display :: performance improvement
      	 */
		if(this.adminMethodSaveMessgeList.size() != 0){
			super.addAllMessagesToRequest(this.adminMethodSaveMessgeList);
		}
		return this.adminMethodsList;
	}

	/**
	 * @return
	 */
	private List retrieveOverrideAdminMethodBOList() {
		AdminMethodOverrideRequest adminMethodOverrideRequest = (AdminMethodOverrideRequest) this
		.getServiceRequest(ServiceManager.ADMIN_METHOD_OVERRIDE_REQUEST);
		adminMethodOverrideRequest
		.setAdminMethodLocateCriteria(adminMethodLocateCriteria);
		AdminMethodOverrideResponse adminMethodOverrideResponse = (AdminMethodOverrideResponse) this
		.executeService(adminMethodOverrideRequest);
		/***
      	 * Added for Informational Message display :: performance improvement
      	 */
		if(this.adminMethodSaveMessgeList.size() != 0){
			super.addAllMessagesToRequest(this.adminMethodSaveMessgeList);
		}
		//CARS:AM2:START
		
		benefitDefinitionsList = new ArrayList();
		tierDefinitionsList = new ArrayList();
		tierCriteriaList = new ArrayList();
		tieredAdminMethodList = new ArrayList();
		adminMethodsList = new ArrayList();
		int mode =-1;
	   	if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(entityType))
	   	{
	   	    mode = getContractSession().getMode();	
	   	}
	   	else if(BusinessConstants.ENTITY_TYPE_PRODUCT.equalsIgnoreCase(entityType))
	   	{
	   	    mode = getProductSessionObject().getMode();	
	   	}
	   	/***
      	 * Commented for performance improvement
      	 * Using adminMethodOverrideResponse.getCriteriaList() instead of adminMethodOverrideResponse.getBenefitDefinitionsList()
      	 * as both result sets are same
      	 */
		/*if (null != adminMethodOverrideResponse && null!= adminMethodOverrideResponse.getBenefitDefinitionsList()
				&& !adminMethodOverrideResponse.getBenefitDefinitionsList().isEmpty() )
		{
			benefitDefinitionsList = adminMethodOverrideResponse.getBenefitDefinitionsList();*/
	   	if(null != adminMethodOverrideResponse.getCriteriaList())
		{
	   		benefitDefinitionsList = adminMethodOverrideResponse.getCriteriaList();
			if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(entityType) )
			{
			  getContractSession().setTierDefinitionsList(benefitDefinitionsList);
			}
			else
			{
				getProductSessionObject().setTierDefinitionsList(benefitDefinitionsList);
			}
				
			tierDefinitionsList = BenefitTierUtil.getTieredList(adminMethodOverrideResponse.getCriteriaList());
				if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(entityType))
				{
				  getContractSession().setTierCriteriaDefinitionList(tierDefinitionsList);
				}
				else
				{
				  getProductSessionObject().setTierCriteriaDefinitionList(tierDefinitionsList);
				}
			}	            
			if(null != adminMethodOverrideResponse.getTieredAdminMethodList())
			{
				tieredAdminMethodList = adminMethodOverrideResponse.getTieredAdminMethodList();				
				if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(entityType))
				{
				  getContractSession().setTieredAdminMethodList(tieredAdminMethodList);
				  getContractSession().setTierPOS(adminMethodOverrideResponse.isTierPOS());
				  if(adminMethodOverrideResponse.getTierProductFamily().contains("HMO") && adminMethodOverrideResponse.getTierProductFamily().contains("PPO")){
				  	 setProductFamilyForTier("POS");
				  }
				  else if(adminMethodOverrideResponse.getTierProductFamily().contains("HMO"))
				  	setProductFamilyForTier("HMO");	
				  else if(adminMethodOverrideResponse.getTierProductFamily().contains("PPO"))	
				  	setProductFamilyForTier("PPO");				  
				}
				else
				{
				  getProductSessionObject().setTieredAdminMethodList(tieredAdminMethodList);
				}
			} 	
			
		//}
	
		//CARS:AM2:END
		if (null != adminMethodOverrideResponse)
		{
			//Set the resulting Product Structure from the database	// from the response to the searchResultList
			this.adminMethodsList = adminMethodOverrideResponse.getSpsList();
			getSession().setAttribute("adminMethodsList", this.adminMethodsList);
			
			if (this.adminMethodsList.size() > 0){
				AdminMethodOverrideBO adminMethodOverrideBO = (AdminMethodOverrideBO)adminMethodsList.get(0);
				isPOS=adminMethodOverrideBO.isPosProductFamily();
				productFamily = adminMethodOverrideBO.getProductFamily();
				this.renderPanel = true;
			}
			else{
				this.renderPanel = false;
			}
			//CARS:AM2:START
			if (null != adminMethodOverrideResponse && null!= adminMethodOverrideResponse.getTieredAdminMethodList() && !adminMethodOverrideResponse.getTieredAdminMethodList().isEmpty()){
				this.renderTierPanel = true;
			}
			else{
				this.renderTierPanel = false;
			}			
			//CARS:AM2:END
		}
		return this.adminMethodsList;
	}

	/**
	 * Returns the entityType
	 * 
	 * @return String entityType.
	 */

	public String getEntityType() {
		return entityType;
	}

	/**
	 * Sets the entityType
	 * 
	 * @param entityType.
	 */

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * Returns the stdbenId
	 * 
	 * @return int stdbenId.
	 */

	public int getStdbenId() {
		return stdbenId;
	}

	/**
	 * Sets the stdbenId
	 * 
	 * @param stdbenId.
	 */

	public void setStdbenId(int stdbenId) {
		this.stdbenId = stdbenId;
	}

	//CARS:AM2:START:
	
	public HtmlPanelGrid getDisplayPanel() {
		HtmlPanelGrid displayPanel = new HtmlPanelGrid();
		if (this.renderPanel) {
			displayPanel.setCellpadding("2");
			displayPanel.setCellspacing("0");
			displayPanel.setWidth("100%");
			displayPanel.setColumns(2);
			displayPanel.setBorder(0);
			displayPanel.setStyle("outputText");
			displayPanel.setStyleClass("dataTableHeader");
			displayPanel.setBgcolor("#cccccc");
			HtmlOutputText outputText = new HtmlOutputText();
			outputText.setValue("Processing Methods");

			displayPanel.getChildren().add(outputText);
		}
		return displayPanel;
	}
	//CARS:AM2:END:

	/**
	 * @param displayPanel
	 *            The displayPanel to set.
	 */
	public void setDisplayPanel(HtmlPanelGrid displayPanel) {
		this.displayPanel = displayPanel;
	}

	private void getHeaderPanel(HtmlPanelGrid panel) {

		HtmlOutputText spsOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText = new HtmlOutputText();
		HtmlOutputText referenceOutputText = new HtmlOutputText();

		spsOutputText.setValue("");
		spsOutputText.setId("spsNames");
		spsOutputText.setStyleClass("dataTableHeader");

		adminMethodOutputText.setValue("Admin Method");
		adminMethodOutputText.setId("AdminMethod");
		adminMethodOutputText.setStyleClass("dataTableHeader");

		referenceOutputText.setValue("Reference");
		referenceOutputText.setId("reference");
		referenceOutputText.setStyleClass("dataTableHeader");

		panel.getChildren().add(spsOutputText);
		panel.getChildren().add(adminMethodOutputText);
		panel.getChildren().add(referenceOutputText);

	}
	/*CARS AM2 START*/
	
	
	/**
	 * @return Returns the tierColumnHeaderPanel.
	 */
	
	
	
	public HtmlPanelGrid getTierColumnHeaderPanel() {
		tierColumnHeaderPanel = new HtmlPanelGrid();
		tierColumnHeaderPanel.setWidth("100%");
		tierColumnHeaderPanel.setColumns(3);
		tierColumnHeaderPanel.setBorder(0);
		tierColumnHeaderPanel.setCellpadding("1");
		tierColumnHeaderPanel.setCellspacing("1");
		tierColumnHeaderPanel.setBgcolor("#CCCCCC");
		tierColumnHeaderPanel.setColumnClasses("column25px,column35px,column40px");
		tierColumnHeaderPanel.setRowClasses("dataTableOddRow");    
		
		HtmlOutputText spsOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText = new HtmlOutputText();
		HtmlOutputText referenceOutputText = new HtmlOutputText();

		spsOutputText.setValue(" ");
		spsOutputText.setId("tieredSpsNames");
		spsOutputText.setStyleClass("dataTableHeader");

		adminMethodOutputText.setValue(" Admin Method");
		adminMethodOutputText.setId("tieredAdminMethod");
		adminMethodOutputText.setStyleClass("dataTableHeader");

		referenceOutputText.setValue(" Reference");
		referenceOutputText.setId("tierReference");
		referenceOutputText.setStyleClass("dataTableHeader");

		tierColumnHeaderPanel.getChildren().add(spsOutputText);
		tierColumnHeaderPanel.getChildren().add(adminMethodOutputText);
		tierColumnHeaderPanel.getChildren().add(referenceOutputText);

		return tierColumnHeaderPanel; 
	}
	/*|CARS|AM2|POS|START|*/
	public HtmlPanelGrid getColumnHeaderPanel()	{
		columnHeaderPanel = new HtmlPanelGrid();
		columnHeaderPanel.setWidth("100%");		
		columnHeaderPanel.setBorder(0);
		columnHeaderPanel.setCellpadding("1");
		columnHeaderPanel.setCellspacing("1");
		columnHeaderPanel.setBgcolor("#CCCCCC");

		if(isPOS())
		{
			columnHeaderPanel.setColumns(3);
			columnHeaderPanel.setColumnClasses("column20pct,column40px,column40px");
		}		
		else 
		{
			columnHeaderPanel.setColumns(2);
			columnHeaderPanel.setColumnClasses("column40px,column60px");
		}	
		HtmlOutputText spsOutputText = new HtmlOutputText();
		spsOutputText.setValue(" SPS Name ");
		spsOutputText.setId("spsNames");
		spsOutputText.setStyleClass("dataTableHeader");
		
		HtmlOutputText adminMethodOutputTextForPPO = new HtmlOutputText();
		adminMethodOutputTextForPPO.setValue(" PPO Admin Method ");
		adminMethodOutputTextForPPO.setId("adminMethodPPO");
		adminMethodOutputTextForPPO.setStyleClass("dataTableHeader");

		HtmlOutputText adminMethodOutputTextForHMO = new HtmlOutputText();
		adminMethodOutputTextForHMO.setValue(" HMO Admin Method ");
		adminMethodOutputTextForHMO.setId("adminMethodHMO");
		adminMethodOutputTextForHMO.setStyleClass("dataTableHeader");
		
		columnHeaderPanel.getChildren().add(spsOutputText);
		if(isPOS())
		{	
			columnHeaderPanel.getChildren().add(adminMethodOutputTextForPPO);
			columnHeaderPanel.getChildren().add(adminMethodOutputTextForHMO);
		}
		else if(BusinessConstants.PRODUCT_FAMILY_PPO.equalsIgnoreCase(productFamily))
		{  			
			columnHeaderPanel.getChildren().add(adminMethodOutputTextForPPO);
		}
		else if(BusinessConstants.PRODUCT_FAMILY_HMO.equalsIgnoreCase(productFamily))
		{   			
			columnHeaderPanel.getChildren().add(adminMethodOutputTextForHMO);
		}
		columnHeaderPanel.setRowClasses("dataTableOddRow");   
		return columnHeaderPanel; 		
	}
	/**
	 * The method creates header panel for AdminMethod SPS table.
	 * @return tierColumnHeaderPanelForContract
	 */
	public HtmlPanelGrid getTierColumnHeaderPanelForContract() 
	{
		tierColumnHeaderPanelForContract = new HtmlPanelGrid();
		tierColumnHeaderPanelForContract.setWidth("100%");		
		tierColumnHeaderPanelForContract.setBorder(0);
		tierColumnHeaderPanelForContract.setCellpadding("1");
		tierColumnHeaderPanelForContract.setCellspacing("1");
		tierColumnHeaderPanelForContract.setBgcolor("#CCCCCC");

		if(getContractSession().isTierPOS())
		{
			tierColumnHeaderPanelForContract.setColumns(3);
			tierColumnHeaderPanelForContract.setColumnClasses("column20pct,column40px,column40px");
		}		
		else 
		{
			tierColumnHeaderPanelForContract.setColumns(2);
			tierColumnHeaderPanelForContract.setColumnClasses("column40px,column60px");
		}	
		HtmlOutputText spsOutputText = new HtmlOutputText();
		spsOutputText.setValue(" SPS Name ");
		spsOutputText.setId("tieredSpsNames");
		spsOutputText.setStyleClass("dataTableHeader");
		
		HtmlOutputText adminMethodOutputTextForPPO = new HtmlOutputText();
		adminMethodOutputTextForPPO.setValue(" PPO Admin Method ");
		adminMethodOutputTextForPPO.setId("tieredAdminMethodPPO");
		adminMethodOutputTextForPPO.setStyleClass("dataTableHeader");

		HtmlOutputText adminMethodOutputTextForHMO = new HtmlOutputText();
		adminMethodOutputTextForHMO.setValue(" HMO Admin Method ");
		adminMethodOutputTextForHMO.setId("tieredAdminMethodHMO");
		adminMethodOutputTextForHMO.setStyleClass("dataTableHeader");
		
		tierColumnHeaderPanelForContract.getChildren().add(spsOutputText);
		if(getContractSession().isTierPOS())
		{	
			tierColumnHeaderPanelForContract.getChildren().add(adminMethodOutputTextForPPO);
			tierColumnHeaderPanelForContract.getChildren().add(adminMethodOutputTextForHMO);
		}
		else if(BusinessConstants.PRODUCT_FAMILY_PPO.equalsIgnoreCase(productFamilyForTier))
		{  			
			tierColumnHeaderPanelForContract.getChildren().add(adminMethodOutputTextForPPO);
		}
		else if(BusinessConstants.PRODUCT_FAMILY_HMO.equalsIgnoreCase(productFamilyForTier))
		{   			
			tierColumnHeaderPanelForContract.getChildren().add(adminMethodOutputTextForHMO);
		}
		tierColumnHeaderPanelForContract.setRowClasses("dataTableOddRow");   
		return tierColumnHeaderPanelForContract; 
	}
	/*|CARS|AM2|POS|END|*/

	private void getHeaderPanelforContract(HtmlPanelGrid panel) {

		HtmlOutputText spsOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText = new HtmlOutputText();
		HtmlOutputText referenceOutputText = new HtmlOutputText();

		spsOutputText.setValue("");
		spsOutputText.setId("spsNamesTier1");
		spsOutputText.setStyleClass("dataTableHeader");

		adminMethodOutputText.setValue("Admin Method");
		adminMethodOutputText.setId("AdminMethodTier1");
		adminMethodOutputText.setStyleClass("dataTableHeader");
	}
	public HtmlPanelGrid getTierColumnHeaderPanelForCheckin() {

		tierColumnHeaderPanelForCheckin = new HtmlPanelGrid();
		tierColumnHeaderPanelForCheckin.setWidth("100%");
		tierColumnHeaderPanelForCheckin.setBorder(0);
		tierColumnHeaderPanelForCheckin.setCellpadding("1");
		tierColumnHeaderPanelForCheckin.setCellspacing("1");
		tierColumnHeaderPanelForCheckin.setBgcolor("#CCCCCC");
		tierColumnHeaderPanelForCheckin.setRowClasses("dataTableOddRow");    
		
		this.isTierPOS = false;
		this.productFamilyForTier = BusinessConstants.PRODUCT_FAMILY_POS;
		if (null != tieredAdminMethodList && tieredAdminMethodList.size() > 0 && entityType.equalsIgnoreCase("CONTRACT")){
			AdminMethodValidationBO adminMethodValidationTieredBO = (AdminMethodValidationBO) tieredAdminMethodList.get(0);
			if (null != entityType && !entityType.equals("") && adminMethodValidationTieredBO.isPosProductFamily()) {
			    this.isTierPOS = true;
            } else if(!adminMethodValidationTieredBO.isPosProductFamily() && 
                    (adminMethodValidationTieredBO.getProductFamName().equalsIgnoreCase(BusinessConstants.PRODUCT_FAMILY_PPO))){
                this.productFamilyForTier = BusinessConstants.PRODUCT_FAMILY_PPO;
            } else if(!adminMethodValidationTieredBO.isPosProductFamily() && 
                    (adminMethodValidationTieredBO.getProductFamName().equalsIgnoreCase(BusinessConstants.PRODUCT_FAMILY_HMO))){
                this.productFamilyForTier = BusinessConstants.PRODUCT_FAMILY_HMO;
            }
		}	
		
		if(this.isTierPOS){
		    tierColumnHeaderPanelForCheckin.setColumns(4);
		    tierColumnHeaderPanelForCheckin.setColumnClasses("column20px,column20px,column30px,column30px");
		}else {
		    tierColumnHeaderPanelForCheckin.setColumns(3);
		    tierColumnHeaderPanelForCheckin.setColumnClasses("column25px,column35px,column40px");
			tierColumnHeaderPanelForCheckin.setRowClasses("dataTableOddRow");
		}
		
		HtmlOutputText spsOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText = new HtmlOutputText();
		HtmlOutputText referenceOutputText = new HtmlOutputText();
		
		spsOutputText.setValue("Benefit Administration");
		spsOutputText.setId("tieredSpsNamesCheckin1");
		spsOutputText.setStyleClass("dataTableHeader");

		adminMethodOutputText.setValue("SPS Name");
		adminMethodOutputText.setId("tieredAdminMethodCheckin1");
		adminMethodOutputText.setStyleClass("dataTableHeader");

		referenceOutputText.setValue("Admin Method");
		referenceOutputText.setId("tierReferenceCheckin1");
		referenceOutputText.setStyleClass("dataTableHeader");

		HtmlOutputText adminMethodOutputTextForPPO = new HtmlOutputText();
		adminMethodOutputTextForPPO.setValue(" PPO Admin Method ");
		adminMethodOutputTextForPPO.setId("tieredAdminMethodPPO1");
		adminMethodOutputTextForPPO.setStyleClass("dataTableHeader");
		adminMethodOutputTextForPPO.setStyle("width:128px;");

		HtmlOutputText adminMethodOutputTextForHMO = new HtmlOutputText();
		adminMethodOutputTextForHMO.setValue(" HMO Admin Method ");
		adminMethodOutputTextForHMO.setId("tieredAdminMethodHMO1");
		adminMethodOutputTextForHMO.setStyleClass("dataTableHeader");
		
		tierColumnHeaderPanelForCheckin.getChildren().add(spsOutputText);
		tierColumnHeaderPanelForCheckin.getChildren().add(adminMethodOutputText);
		if(isTierPOS() && entityType.equalsIgnoreCase("CONTRACT")){
		    tierColumnHeaderPanelForCheckin.getChildren().add(adminMethodOutputTextForPPO);
		    tierColumnHeaderPanelForCheckin.getChildren().add(adminMethodOutputTextForHMO);
		}else if(entityType.equalsIgnoreCase("CONTRACT") && productFamilyForTier.equalsIgnoreCase(BusinessConstants.PRODUCT_FAMILY_HMO)){
		    tierColumnHeaderPanelForCheckin.getChildren().add(adminMethodOutputTextForHMO);
		}else if(entityType.equalsIgnoreCase("CONTRACT") && productFamilyForTier.equalsIgnoreCase(BusinessConstants.PRODUCT_FAMILY_PPO)){
		    adminMethodOutputTextForPPO.setStyleClass("dataTableHeader");
		    tierColumnHeaderPanelForCheckin.getChildren().add(adminMethodOutputTextForPPO);
		}else{
			tierColumnHeaderPanelForCheckin.getChildren().add(referenceOutputText);
		}		
		return tierColumnHeaderPanelForCheckin; 
	
	}
	/*CARS AM2 END*/
	/**
	 * Method to render the panel which has the list of the benefit lines
	 * created.
	 */
	public HtmlPanelGrid getPanel() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");
		getHeaderPanel(panel);
		List adminMethodList = this.getAdminMethodLists();
		AdminMethodBO adminMethodBO = null;
		if (adminMethodList.size() > 0) {
			for (int i = 0; i < adminMethodList.size(); i++) {
				adminMethodBO = (AdminMethodBO) adminMethodList.get(i);
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);
				outputText1.setValue(adminMethodBO.getSpsName());

				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenSpsName" + i);
				hiddenSps.setValue("" + adminMethodBO.getSpsId());

				ValueBinding valBindingForSpsName = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.spsNameMap[" + i
										+ "]}");
				hiddenSps.setValueBinding("value", valBindingForSpsName);

				// HtmlInputTextarea inputText2 = new HtmlInputTextarea();
				HtmlInputText inputText2 = new HtmlInputText();
				inputText2.setId("adminMethod" + i);
				inputText2.setStyleClass("formInputFieldforDiv");
				String toolTipForDesc = adminMethodBO.getAdminMethodDesc();
				inputText2.setTitle(toolTipForDesc);
				if (null == adminMethodBO.getAdminMethodDesc()) {

					inputText2.setValue("");
				} else {
					inputText2
							.setValue("" + adminMethodBO.getAdminMethodDesc());
				}
				inputText2.setOnmouseover("setTitle(" + i + ")");
				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenAdminMethodDetails" + i);
				if (null != adminMethodBO.getAdminMethodDesc()
						&& 0 != adminMethodBO.getAdminMethodSysId()) {
					hiddenAdminDetails.setValue(""
							+ adminMethodBO.getAdminMethodDesc() + "~"
							+ adminMethodBO.getAdminMethodSysId());
				} else {
					hiddenAdminDetails.setValue(" ");
				}

				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace" + i);
				outputText5.setValue(" ");

				ValueBinding valBindingForAdminMethod = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.adminMethodMap[" + i
										+ "]}");
				hiddenAdminDetails.setValueBinding("value",
						valBindingForAdminMethod);

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("reference" + i);
				outputText3.setValue(adminMethodBO.getReference());

				HtmlInputHidden hiddenRef = new HtmlInputHidden();
				hiddenRef.setId("hiddenRefer" + i);
				hiddenRef.setValue("" + adminMethodBO.getReference());

				HtmlCommandButton selectButton = new HtmlCommandButton();
				selectButton.setId("selectButton" + i);
				selectButton.setStyle("border:0;");
				selectButton.setImage("../../images/select.gif");
				selectButton.setTitle("Select1");
				selectButton
						.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
								+ Math.random()
								+ "&spsId="
								+ adminMethodBO.getSpsId()
								+ "&spsName="
								+ adminMethodBO.getSpsName()
								+ "&adminId="
								+ this.adminId
								+ "&stdbenId="
								+ this.stdbenId
								+ "&entityType="
								+ this.entityType
								+ "', 'adminMethodForm:adminMethod"
								+ i
								+ "','adminMethodForm:hiddenAdminMethodDetails"
								+ i + "',2,1);return false;");

				HtmlOutputText outputText6 = new HtmlOutputText();
				outputText6.setId("emptySpaces" + i);
				outputText6.setValue(" ");

				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton" + i);
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("getViewDetails('"
						+ adminMethodBO.getSpsId() + "','" + this.adminId
						+ "','" + this.stdbenId + "','" + this.entityType
						+ "','" + adminMethodBO.getAdminMethodSysId() + "',"
						+ "'adminMethodForm:hiddenAdminMethodDetails" + i + "'"
						+ ");return false;");
				
//			   viewButton.setOnclick("ewpdModalWindow_ewpd('../popups/adminMethodViewPopup.jsp?temp="+Math.random()
//				                		+ "&spsId=" + adminMethodBO.getSpsId()
//										+"&adminId=" + this.adminId
//										+"&stdbenId=" + this.stdbenId
//										+"&entityType=" + this.entityType
//										+"&adminMethodSysId=" + adminMethodBO.getAdminMethodSysId()
//										+"','adminMethodForm:dummyHidden',1,0);return false;");

				HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
				hiddenVariableId.setId("adminMethodHidden" + i);
				hiddenVariableId.setValue("" + adminMethodBO.getAdminMethod());

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + i);
				sps.setId("spssame"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + i);
				ref.setId("adminMethd"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + i);
				val.setId("refernc"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + i);

				sps.getChildren().add(outputText1);
				sps.getChildren().add(hiddenSps);
				ref.getChildren().add(inputText2);
				ref.getChildren().add(hiddenAdminDetails);
				ref.getChildren().add(outputText5);
				ref.getChildren().add(selectButton);
				ref.getChildren().add(outputText6);
				ref.getChildren().add(viewButton);
				val.getChildren().add(outputText3);
				val.getChildren().add(hiddenRef);

				panel.getChildren().add(sps);
				panel.getChildren().add(ref);
				panel.getChildren().add(val);
			}
		}
		return panel;
	}

	/**
	 * Method to render the panel which has the list of the benefit lines
	 * created.
	 */
	public HtmlPanelGrid getPanelView() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");
		getHeaderPanel(panel);
		List adminMethodList = this.getAdminMethodLists();
		AdminMethodBO adminMethodBO = null;
		if (adminMethodList.size() > 0) {
			for (int i = 0; i < adminMethodList.size(); i++) {
				adminMethodBO = (AdminMethodBO) adminMethodList.get(i);
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);
				outputText1.setValue(adminMethodBO.getSpsName());

				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenSpsName" + i);
				hiddenSps.setValue("" + adminMethodBO.getSpsId());

				ValueBinding valBindingForSpsName = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.spsNameMap[" + i
										+ "]}");
				hiddenSps.setValueBinding("value", valBindingForSpsName);

				HtmlOutputText outputText2 = new HtmlOutputText();
				outputText2.setId("adminMethod" + i);
				outputText2.setStyleClass("formInputField");
				if (null == adminMethodBO.getAdminMethodDesc()) {

					outputText2.setValue("");
				} else {
					outputText2.setValue(""
							+ adminMethodBO.getAdminMethodDesc());
				}

				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenAdminMethodDetails" + i);
				hiddenAdminDetails.setValue(""
						+ adminMethodBO.getAdminMethodDesc() + "~"
						+ adminMethodBO.getAdminMethodSysId());

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("reference" + i);
				outputText3.setValue(adminMethodBO.getReference());

				HtmlInputHidden hiddenRef = new HtmlInputHidden();
				hiddenRef.setId("hiddenRefer" + i);
				hiddenRef.setValue("" + adminMethodBO.getReference());

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + i);
				sps.setId("spsnsame2"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + i);
				ref.setId("adminMetod"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + i);
				val.setId("reerence"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + i);

				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton" + i);
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("javascript:getViewDetails('"
						+ adminMethodBO.getSpsId() + "','" + this.adminId
						+ "','" + this.stdbenId + "','" + this.entityType
						+ "','" + adminMethodBO.getAdminMethodSysId() + "',"
						+ "'adminMethodViewForm:hiddenAdminMethodDetails" + i
						+ "'" + ");return false;");

				sps.getChildren().add(outputText1);
				sps.getChildren().add(hiddenSps);
				ref.getChildren().add(outputText2);
				ref.getChildren().add(hiddenAdminDetails);
				ref.getChildren().add(viewButton);
				val.getChildren().add(outputText3);
				val.getChildren().add(hiddenRef);

				panel.getChildren().add(sps);
				panel.getChildren().add(ref);
				panel.getChildren().add(val);
			}
		}
		return panel;
	}

	/**
	 * Method to render the panel which has the list of the benefit lines
	 * created.
	 */
	public HtmlPanelGrid getPanelForOverride() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");
		panel.setColumnClasses("column25px,column35px,column40px");
		getHeaderPanel(panel);
		List adminMethodList = this.getAdminMethodLists();
		storeAdminMethodStates(adminMethodList);
		AdminMethodOverrideBO adminMethodOverrideBO = null;
		if (adminMethodList.size() > 0) {
			for (int i = 0; i < adminMethodList.size(); i++) {

				adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList
						.get(i);
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);
				outputText1.setValue(adminMethodOverrideBO.getSpsName());

				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenSpsName" + i);
				hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());

				ValueBinding valBindingForSpsName = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.spsNameMap[" + i
										+ "]}");
				hiddenSps.setValueBinding("value", valBindingForSpsName);

				HtmlInputText inputText2 = new HtmlInputText();
				inputText2.setId("adminMethod" + i);
				inputText2.setStyleClass("formInputFieldForDiv");
				String toolTipForDesc = adminMethodOverrideBO
						.getAdminMethodDesc();
				inputText2.setTitle(toolTipForDesc);
				if (null == adminMethodOverrideBO.getAdminMethodDesc()) {
					inputText2.setValue("");
				} else {
					inputText2.setValue(""
							+ adminMethodOverrideBO.getAdminMethodDesc());
				}
				//changed the code
				inputText2.setOnmouseover("setTitle(" + i + ")");
				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenAdminMethodDetails" + i);

				if (null != adminMethodOverrideBO.getAdminMethodDesc()
						&& 0 != adminMethodOverrideBO.getAdminMethodSysId()) {
					hiddenAdminDetails.setValue(""
							+ adminMethodOverrideBO.getAdminMethodDesc() + "~"
							+ adminMethodOverrideBO.getAdminMethodSysId());
				} else {
					hiddenAdminDetails.setValue(" ");
				}

				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace" + i);
				outputText5.setValue(" ");

				ValueBinding valBindingForAdminMethod = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.adminMethodMap[" + i
										+ "]}");
				hiddenAdminDetails.setValueBinding("value",
						valBindingForAdminMethod);
				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("reference" + i);
				outputText3.setValue(adminMethodOverrideBO.getReference());

				HtmlInputHidden hiddenRef = new HtmlInputHidden();
				hiddenRef.setId("hiddenRefer" + i);
				hiddenRef.setValue("" + adminMethodOverrideBO.getReference());

				HtmlCommandButton selectButton = new HtmlCommandButton();
				selectButton.setId("selectButton" + i);
				selectButton.setStyle("border:0;");
				selectButton.setImage("../../images/select.gif");
				selectButton.setTitle("Select");

				// Modified - Added super process step name for adminMethodPopUp
				selectButton
						.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
								+ Math.random()
								+ "&spsId="
								+ adminMethodOverrideBO.getSpsId()
								+ "&spsName="
								+ adminMethodOverrideBO.getSpsName()
								+ "&adminId="
								+ this.adminId
								+ "&stdbenId="
								+ this.stdbenId
								+ "&entityType="
								+ this.entityType
								+ "', 'adminMethodForm:adminMethod"
								+ i
								+ "','adminMethodForm:hiddenAdminMethodDetails"
								+ i + "',2,1);return false;");

				HtmlOutputText outputText6 = new HtmlOutputText();
				outputText6.setId("emptySpaces" + i);
				outputText6.setValue(" ");

				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton" + i);
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("getViewDetails('"
						+ adminMethodOverrideBO.getSpsId() + "','"
						+ this.adminId + "','" + this.stdbenId + "','"
						+ this.entityType + "','"
						+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
						+ "'adminMethodForm:hiddenAdminMethodDetails" + i + "'"
						+ ");return false;");

				HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
				hiddenVariableId.setId("adminMethodHidden" + i);
				hiddenVariableId.setValue(""
						+ adminMethodOverrideBO.getAdminMethod());

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + i);
				sps.setId("spsnsame4"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + i);
				ref.setId("adminMethod1"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + i);
				val.setId("reference1"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + i);

				sps.getChildren().add(outputText1);
				sps.getChildren().add(hiddenSps);
				ref.getChildren().add(inputText2);
				ref.getChildren().add(hiddenAdminDetails);
				ref.getChildren().add(outputText5);
				if (!(this.componentType).equals(WebConstants.MANDATE_TYPE)) {
					ref.getChildren().add(selectButton);
				}
				ref.getChildren().add(outputText6);
				ref.getChildren().add(viewButton);

				val.getChildren().add(outputText3);
				val.getChildren().add(hiddenRef);

				panel.getChildren().add(sps);
				panel.getChildren().add(ref);
				panel.getChildren().add(val);
				panel.getChildren().add(val);
			}
		}
		return panel;
	}
//CARS:AM2:START
	private HtmlPanelGrid getAdministrationLine(String administrationDescStr)
	{
		HtmlOutputText textAdm = new HtmlOutputText();
		textAdm.setId("administrationTiered");
		textAdm.setValue(administrationDescStr);					
		HtmlOutputLabel admTierLbl = new HtmlOutputLabel();
		admTierLbl.setFor("admTierLbl");
		admTierLbl.setId("admTierLblId"+RandomStringUtils.randomAlphanumeric(15));
		//admTierLbl.setId("admTierLblId");	
		admTierLbl.getChildren().add(textAdm);
		
		HtmlOutputText textBlank = new HtmlOutputText();
		textBlank.setId("textBlank");
		textBlank.setValue("");					
		HtmlOutputLabel blankTierLbl = new HtmlOutputLabel();
		blankTierLbl.setFor("blankTierLbl");
		blankTierLbl.setId("blankTierLblId"+RandomStringUtils.randomAlphanumeric(15));
		//blankTierLbl.setId("blankTierLblId");	
		blankTierLbl.getChildren().add(textBlank);
		
		HtmlOutputText textBlank1 = new HtmlOutputText();
		textBlank.setId("textBlank1");
		textBlank.setValue("");					
		HtmlOutputLabel blankTierLbl1 = new HtmlOutputLabel();
		blankTierLbl1.setId("blankTierLblId1"+RandomStringUtils.randomAlphanumeric(15));
		blankTierLbl1.setFor("blankTierLbl1");
		//blankTierLbl1.setId("blankTierLblId1");	
		blankTierLbl1.getChildren().add(textBlank1);
		
		HtmlPanelGrid adminLine = new HtmlPanelGrid();			
		adminLine.setColumns(3);
		adminLine.setWidth("100%");

		adminLine.setBorder(0);
		adminLine.setCellpadding("1");
		adminLine.setCellspacing("1");
		adminLine.setBgcolor("#cccccc");  
		adminLine.setColumnClasses("column25px,column35px,column40px");
		adminLine.setRowClasses("dataTableEvenRow"); 
		adminLine.getChildren().add(admTierLbl);
		adminLine.getChildren().add(blankTierLbl);
		adminLine.getChildren().add(blankTierLbl1);
		
		return adminLine;
	}
	public HtmlPanelGrid getPanelForPrint() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#FFFFFF");
		getHeaderPanel(panel);
		List adminMethodList = this.getAdminMethodLists();
		AdminMethodBO adminMethodBO = null;
		if (adminMethodList.size() > 0) {
			for (int i = 0; i < adminMethodList.size(); i++) {
				adminMethodBO = (AdminMethodBO) adminMethodList.get(i);
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);
				outputText1.setValue(adminMethodBO.getSpsName());

				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenSpsName" + i);
				hiddenSps.setValue("" + adminMethodBO.getSpsId());

				HtmlOutputText outputText2 = new HtmlOutputText();
				outputText2.setId("adminMethod" + i);
				//outputText2.setStyleClass("formInputField");
				if (null == adminMethodBO.getAdminMethodDesc()) {

					outputText2.setValue("");
				} else {
					outputText2.setValue(""
							+ adminMethodBO.getAdminMethodDesc());
				}

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("reference" + i);
				outputText3.setValue(adminMethodBO.getReference());


				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + i);
				sps.setId("spsnsame7"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + i);
				ref.setId("adminMethod5"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + i);
				val.setId("reference5"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + i);


				sps.getChildren().add(outputText1);
				sps.getChildren().add(hiddenSps);
				ref.getChildren().add(outputText2);

				val.getChildren().add(outputText3);
				panel.getChildren().add(sps);
				panel.getChildren().add(ref);
				panel.getChildren().add(val);
			}
		}
		return panel;
	}

	public HtmlPanelGrid getTierPanel() {

		Logger.logInfo("entered method getTierPanel");
		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;
 	   tierPanel = new HtmlPanelGrid();
		tierPanel.setColumns(1);
		tierPanel.setWidth("100%");
		tierPanel.setBorder(0);
		tierPanel.setCellpadding("0");
		tierPanel.setCellspacing("0");
		tierPanel.setStyleClass("outputText");
		tierPanel.setBgcolor("#cccccc");		
		HtmlPanelGrid tierColumnHeaderlPanel = new HtmlPanelGrid();			
		tierColumnHeaderlPanel.setColumns(3);
		tierColumnHeaderlPanel.setWidth("100%");
		tierColumnHeaderlPanel.setBorder(0);
		tierColumnHeaderlPanel.setCellpadding("1");
		tierColumnHeaderlPanel.setCellspacing("1");
		tierColumnHeaderlPanel.setBgcolor("#cccccc");  
		tierColumnHeaderlPanel.setColumnClasses("column25px,column35px,column40px");
		StringBuffer rows = new StringBuffer();
		int size = 0;
		if (null != tierDefinitionsList) 
		{
			sortTiers(tierDefinitionsList); 
		}
		HtmlPanelGrid tierDefPanel = null;
		HtmlPanelGrid tierCritPanel = null;
		HtmlPanelGrid tierHeaderPanel = null;
		HtmlPanelGrid tierHeaderPanel1 = null;
		HtmlPanelGrid tierLevelPanel = null;
		BenefitTierDefinition tierDefinition = null;
		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		// iterating to get the benefit levels
		for (int l = 0; l < tierDefinitionsList.size(); l++)
		{
			HtmlPanelGrid grid1 = new HtmlPanelGrid();
			grid1.setBgcolor("#FFFFFF");
			tierPanel.getChildren().add(grid1);
			tierDefinition = (BenefitTierDefinition) tierDefinitionsList.get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");
			tierDefPanel.setBorder(0);
			tierDefPanel.setCellpadding("0");
			tierDefPanel.setCellspacing("0");
			tierDefPanel.setBgcolor("#cccccc");

			HtmlOutputLabel defLabel = new HtmlOutputLabel();
			defLabel.setId("defLabel1"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setStyleClass("dataTableHeader1");		
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			tierDef.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");
			
			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
			hidDefId.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");
			String tempKey = tierDefinition.getBenefitTierDefinitionSysId()+"";
			defLabel.getChildren().add(tierDef);
			tierDefPanel.getChildren().add(defLabel);
        	tierPanel.getChildren().add(tierDefPanel);
			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) 
			{			
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				tierCritPanel = new HtmlPanelGrid();
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");
				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("0");
				tierCritPanel.setCellspacing("0");
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow");
				tierHeaderPanel = new HtmlPanelGrid();
				tierHeaderPanel.setCellpadding("0");
				tierHeaderPanel.setCellspacing("0");
				tierHeaderPanel.setColumns(2);
				tierHeaderPanel.setWidth("100%");
				tierHeaderPanel.setBgcolor("#FFFFFF");
				tierHeaderPanel.setStyleClass("headerPanel1");
				tierHeaderPanel1 = new HtmlPanelGrid();
				tierHeaderPanel1.setCellpadding("0");
				tierHeaderPanel1.setCellspacing("0");
				tierHeaderPanel1.setColumns(2);
				tierHeaderPanel1.setWidth("100%");
				tierHeaderPanel1.setBgcolor("#FFFFFF");
				tierHeaderPanel1.setStyleClass("headerPanel1");
				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel4"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputLabel tierlabel1 = new HtmlOutputLabel();
				tierlabel1.setId("tierlabel1"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:140px;");
				if (null != critList) 
				{
					for (int k = 0; k < critList.size(); k++) 
					{
						if (k < 2 ){
							tierlabel.getChildren().add(dummylabel);
							tierCriteria = new BenefitTierCriteria();
							tierCriteria = (BenefitTierCriteria) critList.get(k);
							HtmlOutputText tierCrit = new HtmlOutputText();
							tierCrit.setStyle("color:blue");
							tierCrit.setValue(tierCriteria.getBenefitTierCriteriaName()+" : ");
							tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo);
							tierlabel.getChildren().add(tierCrit);
							HtmlOutputText critValueView = new HtmlOutputText();
							String critVal = tierCriteria.getBenefitTierCriteriaValue();						
							critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
							tierlabel.getChildren().add(critValueView);
						}
						else if (k >= 2){
							tierlabel1.getChildren().add(dummylabel1);
							tierCriteria = new BenefitTierCriteria();
							tierCriteria = (BenefitTierCriteria) critList.get(k);
							HtmlOutputText tierCrit = new HtmlOutputText();
							tierCrit.setStyle("color:blue");
							tierCrit.setValue(tierCriteria.getBenefitTierCriteriaName()+" : ");
							tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo);
							tierlabel1.getChildren().add(tierCrit);
							HtmlOutputText critValueView = new HtmlOutputText();
							String critVal = tierCriteria.getBenefitTierCriteriaValue();						
							critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
							tierlabel1.getChildren().add(critValueView);
						}
					}
				}
				tierHeaderPanel.getChildren().add(tierlabel);
				tierCritPanel.getChildren().add(tierHeaderPanel);
				if (critList.size() > 2){
					tierHeaderPanel1.getChildren().add(tierlabel1);
					tierCritPanel.getChildren().add(tierHeaderPanel1);
				}
						
				int sizeOfLevelList = 0;
				tierLevelPanel = new HtmlPanelGrid();			
				tierLevelPanel.setColumns(3);
				tierLevelPanel.setWidth("100%");
				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("1");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc");  
				tierLevelPanel.setColumnClasses("column25px,column35px,column40px");
				tierLevelPanel.setRowClasses("dataTableEvenRow,dataTableOddRow");     
				
				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					BenefitTier benefitTier = (BenefitTier) tieredAdminMethodList.get(i);
					if (benefitTier.getBenefitTierSysId() == tier.getBenefitTierSysId())
					{		
						if(benefitTier.getAdminMethods() == null || benefitTier.getAdminMethods().size() == 0)
						{
							HtmlOutputLabel label =new HtmlOutputLabel();
							label.setId("label4"+RandomStringUtils.randomAlphanumeric(15));
							HtmlOutputText text = new HtmlOutputText();
							text.setValue(WebConstants.NO_SPS_FOR_TERMS_IN_TIER);
							label.getChildren().add(text);
							tierLevelPanel.setColumns(1);
							tierLevelPanel.getChildren().add(label);
						}
						else
						{						  
						    setAdminMethodValuesToTierGrid(i,benefitTier,tierLevelPanel);
						}
					}																							
				}
				/*Commented the above code and added this as part of Stabilization 2011*/
				/*for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					AdminMethodTierOverrideBO benefitTier = (AdminMethodTierOverrideBO) tieredAdminMethodList.get(i);
					if (benefitTier.getTierSysId() == tier.getBenefitTierSysId() || benefitTier.getTierSysId() == 0)
					{	
						setAdminMethodValuesToTierGrid(m,i,benefitTier,tierLevelPanel);
					}																							
				}*/
				/*Commented the above code and added this as part of Stabilization 2011 - END*/
			tierCritPanel.getChildren().add(tierLevelPanel);
			tierDefPanel.getChildren().add(tierCritPanel);
			}
		}
		return tierPanel;
	}
	/*CARS|AM2|POS|START*/
	public HtmlPanelGrid getTierPanelForContract(){
		
        isTierPOS = getContractSession().isTierPOS();
		Logger.logInfo("entered method getTierPanelForContract");
		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;
 	    tierPanel = new HtmlPanelGrid();
		tierPanel.setColumns(1);
		tierPanel.setWidth("100%");
		tierPanel.setBorder(0);
		tierPanel.setCellpadding("0");
		tierPanel.setCellspacing("0");
		tierPanel.setStyleClass("outputText");
		tierPanel.setBgcolor("#cccccc");		
		StringBuffer rows = new StringBuffer();
		int size = 0;
		if (null != tierDefinitionsList) 
		{
			sortTiers(tierDefinitionsList); 
		}
		HtmlPanelGrid tierDefPanel = null;
		HtmlPanelGrid tierCritPanel = null;
		HtmlPanelGrid tierHeaderPanel = null;
		HtmlPanelGrid tierLevelPanel = null;
		BenefitTierDefinition tierDefinition = null;
		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		// iterating to get the benefit levels
		for (int l = 0; l < tierDefinitionsList.size(); l++)
		{
			HtmlPanelGrid grid1 = new HtmlPanelGrid();
			grid1.setBgcolor("#FFFFFF");
			tierPanel.getChildren().add(grid1);
			tierDefinition = (BenefitTierDefinition) tierDefinitionsList.get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");
			tierDefPanel.setBorder(0);
			tierDefPanel.setCellpadding("0");
			tierDefPanel.setCellspacing("0");
			tierDefPanel.setBgcolor("#cccccc");

			HtmlOutputLabel defLabel = new HtmlOutputLabel();	
			defLabel.setId("defLabel5"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setId("tierDef"+RandomStringUtils.randomAlphanumeric(15));
			tierDef.setStyleClass("dataTableHeader1");		
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			//tierDef.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");
			
			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
			hidDefId.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");
			String tempKey = tierDefinition.getBenefitTierDefinitionSysId()+"";
			defLabel.getChildren().add(tierDef);
			tierDefPanel.getChildren().add(defLabel);
        	tierPanel.getChildren().add(tierDefPanel);
			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) 
			{			
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				tierCritPanel = new HtmlPanelGrid();
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");
				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("0");
				tierCritPanel.setCellspacing("0");
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow");
				tierHeaderPanel = new HtmlPanelGrid();
				tierHeaderPanel.setCellpadding("0");
				tierHeaderPanel.setCellspacing("0");
				tierHeaderPanel.setColumns(2);
				tierHeaderPanel.setWidth("100%");
				tierHeaderPanel.setBgcolor("#FFFFFF");
				tierHeaderPanel.setStyleClass("headerPanel1");
				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel5"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:250px;");
				if (null != critList) 
				{
					for (int k = 0; k < critList.size(); k++) 
					{
						tierlabel.getChildren().add(dummylabel);
						tierCriteria = new BenefitTierCriteria();
						tierCriteria = (BenefitTierCriteria) critList.get(k);
						HtmlOutputText tierCrit = new HtmlOutputText();
						tierCrit.setStyle("color:blue");
						tierCrit.setValue(tierCriteria.getBenefitTierCriteriaName()+" : ");
						tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo);
						tierlabel.getChildren().add(tierCrit);
						HtmlOutputText critValueView = new HtmlOutputText();
						String critVal = tierCriteria.getBenefitTierCriteriaValue();						
						critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
						tierlabel.getChildren().add(critValueView);						
					}
				}
				tierHeaderPanel.getChildren().add(tierlabel);
				tierCritPanel.getChildren().add(tierHeaderPanel);
				int sizeOfLevelList = 0;
				tierLevelPanel = new HtmlPanelGrid();			
				tierLevelPanel.setColumns(3);
				
				if(isTierPOS)
				{
					tierLevelPanel.setColumns(3);
					tierLevelPanel.setColumnClasses("column25px,column35px,column35px");
				}
				else
				{
					tierLevelPanel.setColumns(2);
					tierLevelPanel.setColumnClasses("column40px,column40px");
				}
				
				tierLevelPanel.setWidth("100%");
				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("1");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc");  
				
				tierLevelPanel.setRowClasses("dataTableEvenRow,dataTableOddRow");     
				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					BenefitTier benefitTier = (BenefitTier) tieredAdminMethodList.get(i);
					if (benefitTier.getBenefitTierSysId() == tier.getBenefitTierSysId())
					{		
						if(benefitTier.getAdminMethods() == null || benefitTier.getAdminMethods().size() == 0)
						{
							HtmlOutputLabel label =new HtmlOutputLabel();
							label.setId("label5"+RandomStringUtils.randomAlphanumeric(15));
							HtmlOutputText text = new HtmlOutputText();
							text.setValue(WebConstants.NO_SPS_FOR_TERMS_IN_TIER);
							label.getChildren().add(text);
							tierLevelPanel.setColumns(1);
							tierLevelPanel.getChildren().add(label);
						}
						else
						{						  
							prepareSPSAMMappingLines(i,benefitTier,tierLevelPanel);
						}
					}																							
				}
			tierCritPanel.getChildren().add(tierLevelPanel);
			tierDefPanel.getChildren().add(tierCritPanel);
			}
		}
		return tierPanel;	
	}
	/*CARS|AM2|POS|END*/
	public HtmlPanelGrid getTierPanelForPrint() {

		Logger.logInfo("entered method getTierPanel");
		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;

		//List benefitDefinitonsList = this.getBenefitDefinitionsList();
		// This method gets the values from the benefit definiton List and sets
		// it to the level list and line list
		//getValuesFromDefinitonList(benefitDefinitonsList);

		tierPanel = new HtmlPanelGrid();
		tierPanel.setColumns(1);
		tierPanel.setWidth("100%");
		tierPanel.setBorder(0);
		tierPanel.setCellpadding("0");
		tierPanel.setCellspacing("0");
		tierPanel.setBgcolor("#cccccc");		
		tierPanel.setRowClasses("dataTableOddRow");  


		StringBuffer rows = new StringBuffer();
		// setting values to benefit levels
		int size = 0;
		if (null != tierDefinitionsList) 
		{
			sortTiers(tierDefinitionsList); 
		}
		HtmlPanelGrid tierDefPanel = null;

		HtmlPanelGrid tierCritPanel = null;
		
		HtmlPanelGrid tierHeaderPanel = null;

		HtmlPanelGrid tierLevelPanel = null;
		
		BenefitTierDefinition tierDefinition = null;

		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		// iterating to get the benefit levels
		for (int l = 0; l < tierDefinitionsList.size(); l++)
		{
			HtmlPanelGrid grid1 = new HtmlPanelGrid();
			tierPanel.getChildren().add(grid1);
			tierDefinition = (BenefitTierDefinition) tierDefinitionsList.get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");
			tierDefPanel.setBorder(1);
			tierDefPanel.setCellpadding("0");
			tierDefPanel.setCellspacing("0");
			tierDefPanel.setBgcolor("#cccccc");
            tierDefPanel.setRowClasses("dataTableOddRow");
			HtmlOutputLabel defLabel = new HtmlOutputLabel();	
			defLabel.setId("defLabel6"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			tierDef.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");
			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
			hidDefId.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");
			String tempKey = tierDefinition.getBenefitTierDefinitionSysId()+"";
			defLabel.getChildren().add(tierDef);
			tierDefPanel.getChildren().add(defLabel);
 			tierPanel.getChildren().add(tierDefPanel);
			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) 
			{
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				tierCritPanel = new HtmlPanelGrid();
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");
				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("0");
				tierCritPanel.setCellspacing("0");
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow");

				tierHeaderPanel = new HtmlPanelGrid();
				tierHeaderPanel.setCellpadding("0");
				tierHeaderPanel.setCellspacing("0");
				tierHeaderPanel.setColumns(2);
				tierHeaderPanel.setWidth("100%");
				tierHeaderPanel.setBgcolor("#CCCCCC");
				tierHeaderPanel.setRowClasses("dataTableOddRow");
		    	tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel6"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:250px;");

				if (null != critList) 
				{
					for (int k = 0; k < critList.size(); k++)
					{
						tierlabel.getChildren().add(dummylabel);
						tierCriteria = new BenefitTierCriteria();
						tierCriteria = (BenefitTierCriteria) critList.get(k);
						HtmlOutputText tierCrit = new HtmlOutputText();
						tierCrit.setValue(tierCriteria.getBenefitTierCriteriaName()+" : ");
						tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo);
						tierlabel.getChildren().add(tierCrit);
						HtmlOutputText critValueView = new HtmlOutputText();
						String critVal = tierCriteria.getBenefitTierCriteriaValue();						
						critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
						tierlabel.getChildren().add(critValueView);						
					}
				}
				tierHeaderPanel.getChildren().add(tierlabel);				
				tierCritPanel.getChildren().add(tierHeaderPanel);				
				int sizeOfLevelList = 0;
				tierLevelPanel = new HtmlPanelGrid();			
				tierLevelPanel.setColumns(3);
				tierLevelPanel.setWidth("100%");
				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("1");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc");
				tierLevelPanel.setRowClasses("dataTableOddRow");
				tierLevelPanel.setColumnClasses("column25px,column35px,column40px");
				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					BenefitTier benefitTier = (BenefitTier) tieredAdminMethodList.get(i);
					if (benefitTier.getBenefitTierSysId() == tier.getBenefitTierSysId())
					{												
						if(benefitTier.getAdminMethods() == null || benefitTier.getAdminMethods().size() == 0)
						{
							HtmlOutputLabel label =new HtmlOutputLabel();
							label.setId("labe"+RandomStringUtils.randomAlphanumeric(15));
							HtmlOutputText text = new HtmlOutputText();
							text.setValue(WebConstants.NO_SPS_FOR_TERMS_IN_TIER);
							label.getChildren().add(text);
							tierLevelPanel.setColumns(1);							
							tierLevelPanel.getChildren().add(label);
						}
						else
						{						  
						    setAdminMethodValuesToTierGridForPrint(i,benefitTier,tierLevelPanel);
						}
					}																							
				}				
			tierCritPanel.getChildren().add(tierLevelPanel);
			tierDefPanel.getChildren().add(tierCritPanel);
			}
		}
		return tierPanel;
	}

	/**
	 * @return Returns the tierDefinitionsList.
	 */
	public List getTierDefinitionsList() {
		return tierDefinitionsList;
	}
	/**
	 * @param tierDefinitionsList The tierDefinitionsList to set.
	 */
	public void setTierDefinitionsList(List tierDefinitionsList) {
		this.tierDefinitionsList = tierDefinitionsList;
	}

	/**
	 * @return Returns the spsTierNameMap.
	 */
	public Map getSpsTierNameMap() {
		return spsTierNameMap;
	}
	/**
	 * @param spsTierNameMap The spsTierNameMap to set.
	 */
	public void setSpsTierNameMap(Map spsTierNameMap) {
		this.spsTierNameMap = spsTierNameMap;
	}
	
	/**
	 * @return Returns the panelForOverrideForPrint.
	 */
	public HtmlPanelGrid getPanelForOverrideForPrint() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setBorder(0);
		//panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1"); 
		panel.setBgcolor("#cccccc");
		panel.setColumnClasses("column25px,column35px,column40px");
		panel.setRowClasses("dataTableOddRow");  
		getHeaderPanel(panel);
		List adminMethodList = this.getAdminMethodLists();
		storeAdminMethodStates(adminMethodList);
		AdminMethodOverrideBO adminMethodOverrideBO = null;
		if (adminMethodList.size() > 0) {
			for (int i = 0; i < adminMethodList.size(); i++) {

				adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList
						.get(i);
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);
				outputText1.setValue(adminMethodOverrideBO.getSpsName());

				
				
				HtmlOutputText outputText2 = new HtmlOutputText();
				outputText2.setId("adminMethod" + i);
		
				if (null == adminMethodOverrideBO.getAdminMethodDesc()) {
					outputText2.setValue("");
				} else {
					outputText2.setValue(""
							+ adminMethodOverrideBO.getAdminMethodDesc());
				}
				//changed the code
				
				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace" + i);
				outputText5.setValue(" ");

				

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("reference" + i);
				outputText3.setValue(adminMethodOverrideBO.getReference());

				HtmlInputHidden hiddenRef = new HtmlInputHidden();
				hiddenRef.setId("hiddenRefer" + i);
				hiddenRef.setValue("" + adminMethodOverrideBO.getReference());

				

				HtmlOutputText outputText6 = new HtmlOutputText();
				outputText6.setId("emptySpaces" + i);
				outputText6.setValue(" ");

				
				HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
				hiddenVariableId.setId("adminMethodHidden" + i);
				hiddenVariableId.setValue(""
						+ adminMethodOverrideBO.getAdminMethod());

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setStyleClass("outputText1");
				sps.setFor("spsName" + i);
				sps.setId("spsnsame6"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setStyleClass("outputText1");
				ref.setFor("adminMethod" + i);
				ref.setId("adminMethod6"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setStyleClass("outputText1");
				val.setFor("reference" + i);
				val.setId("reference6"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + i);

				sps.getChildren().add(outputText1);

				ref.getChildren().add(outputText2);
				
				ref.getChildren().add(outputText5);
				
				ref.getChildren().add(outputText6);
				
				val.getChildren().add(outputText3);
				val.getChildren().add(hiddenRef);

				panel.getChildren().add(sps);
				panel.getChildren().add(ref);
				panel.getChildren().add(val);
				panel.getChildren().add(val);
			}
		}
		return panel;
	}
//CARS:AM2:END	
	/**
	 * @param adminMethodList
	 */
	private void storeAdminMethodStates(List adminMethodList) {
		if (null != adminMethodList && adminMethodList.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			Iterator iterator = adminMethodList.iterator();
			while (iterator.hasNext()) {
				AdminMethodOverrideBO overrideBO = (AdminMethodOverrideBO) iterator
						.next();
				buffer.append(overrideBO.getAdminMethodSysId());
				if (iterator.hasNext())
					buffer.append(":");
			}
			adminMethodState = buffer.toString();
		}
	}

	/**
	 * Method to render the panel which has the list of the benefit lines
	 * created.
	 */
	public HtmlPanelGrid getPanelForOverrideView() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");
		panel.setColumnClasses("column25px,column35px,column40px");
		getHeaderPanel(panel);
		List adminMethodList = this.getAdminMethodLists();
		storeAdminMethodStates(adminMethodList);
		AdminMethodOverrideBO adminMethodOverrideBO = null;
		if (adminMethodList.size() > 0) {
			for (int i = 0; i < adminMethodList.size(); i++) {

				adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList
						.get(i);
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);
				outputText1.setValue(adminMethodOverrideBO.getSpsName());

				HtmlInputText adminMethodText = new HtmlInputText();
				adminMethodText.setId("adminMethod" + i);
				adminMethodText.setStyleClass("formInputFieldForDiv");
				adminMethodText.setReadonly(true);
				String toolTipForDesc = adminMethodOverrideBO.getAdminMethodDesc()==null?" ":adminMethodOverrideBO.getAdminMethodDesc();
				adminMethodText.setTitle(toolTipForDesc);
				if (null == adminMethodOverrideBO.getAdminMethodDesc()) {
					adminMethodText.setValue("");
				} else {
					adminMethodText.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
				}				
				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenAdminMethodDetails" + i);

				if (null != adminMethodOverrideBO.getAdminMethodDesc()
						&& 0 != adminMethodOverrideBO.getAdminMethodSysId()) {
					hiddenAdminDetails.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc() + "~"+ adminMethodOverrideBO.getAdminMethodSysId());
				} else {
					hiddenAdminDetails.setValue(" ");
				}

				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace" + i);
				outputText5.setValue(" ");

				

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("reference" + i);
				outputText3.setValue(adminMethodOverrideBO.getReference());

				HtmlInputHidden hiddenRef = new HtmlInputHidden();
				hiddenRef.setId("hiddenRefer" + i);
				hiddenRef.setValue("" + adminMethodOverrideBO.getReference());

				

				HtmlOutputText outputText6 = new HtmlOutputText();
				outputText6.setId("emptySpaces" + i);
				outputText6.setValue(" ");

				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton" + i);
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("getViewDetails('"
						+ adminMethodOverrideBO.getSpsId() + "','"
						+ this.adminId + "','" + this.stdbenId + "','"
						+ this.entityType + "','"
						+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
						+ "'adminMethodViewForm:hiddenAdminMethodDetails" + i + "'"
						+ ");return false;");

				HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
				hiddenVariableId.setId("adminMethodHidden" + i);
				hiddenVariableId.setValue(""
						+ adminMethodOverrideBO.getAdminMethod());

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + i);
				sps.setId("spsnsame7"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + i);
				ref.setId("adminMethod7"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + i);
				val.setId("reference7"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + i);

				sps.getChildren().add(outputText1);
				//sps.getChildren().add(hiddenSps);
				ref.getChildren().add(adminMethodText);
				ref.getChildren().add(hiddenAdminDetails);
				ref.getChildren().add(outputText5);
				
				ref.getChildren().add(outputText6);
				ref.getChildren().add(viewButton);

				val.getChildren().add(outputText3);
				val.getChildren().add(hiddenRef);

				panel.getChildren().add(sps);
				panel.getChildren().add(ref);
				panel.getChildren().add(val);
				panel.getChildren().add(val);
			}
		}
		return panel;
	}

	/**
	 * @param panelForOverride
	 * 
	 * Sets the panelForOverride.
	 */
	public void setPanelForOverride(HtmlPanelGrid panelForOverride) {
		this.panelForOverride = panelForOverride;
	}

	/**
	 * For displying admin Method tab in benefit component level.
	 * 
	 * @return String
	 */
	public String loadForBenefitComponent() {

		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
				.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		Logger.logInfo("Entering the method for loading  Benefit Components");
		String nodeClicked = (String) getSession().getAttribute(
				"SESSION_NODE_TYPE_COMP");

		this.breadCrumpName = benefitComponentSessionObject
				.getBenefitComponentName();
		if (nodeClicked.equals("Benefit-Administration")
				&& benefitComponentSessionObject.getBenefitComponentMode()
						.equalsIgnoreCase("View")) {

			this
					.setBreadCrumbText("Product Configuration >> BenefitComponent ("
							+ this.breadCrumpName + ") >> View");
			return "success";
		} else {
			this
					.setBreadCrumbText("Product Configuration >> BenefitComponent ("
							+ this.breadCrumpName + ") >> Edit");
			return "success";
		}
	}

	public String loadForBenefitComponentView() {
		return "success";
	}

	private boolean validateAdminMethods() {

		validationMessages = new ArrayList();
		// Get the hash map
		Set keys = adminMethodMap.keySet();
		Iterator valueIter = keys.iterator();
		String adminId = "";
		List adminIdList = new ArrayList();
		while (valueIter.hasNext()) {
			Long valueElement = (Long) valueIter.next();
			String value = (String) adminMethodMap.get(valueElement);
			if (null == value || value.equals("") || value.equals(" ")) {
				this.validationMessages.add(new ErrorMessage(
						"Select.All.AdminMethods"));
				return false;
			}
		}

		return true;
	}

	/**
	 * For displying admin Method tab in product level.
	 * 
	 * @return String
	 */
	public String loadForProduct() {
		//    	Logger.logInfo("Entering the method for loading admin option");
		//        String nodeClicked
		// =(String)getSession().getAttribute(PRODUCT_NODE_TYPE);
		//        getValuesFromSessionForProd();
		//        String mode = adminMethodLocateCriteria.getMode();
		//        String compName =
		// adminMethodLocateCriteria.getBenefitComponentName();
		//		if (nodeClicked.equals(BENEFIT_ADMIN) &&
		// mode.equals(WebConstants.ACTION_BENEFIT)){
		//// this.adminMethodsList = retrieveOverrideAdminMethodBOList();
		//// this.setBreadCrumbText("Product Configuration >>
		// Product("+this.breadCrumpName +") >> View");
		//			return "prodAdminMethodView";
		//		}else{
		//// this.adminMethodsList = retrieveOverrideAdminMethodBOList();
		//// this.setBreadCrumbText("Product Configuration >>
		// Product("+this.breadCrumpName +") >> Edit");
		//		    return "prodadminmethod";
		//		}
		return "success";
	}

	public String loadForProductView() {
		return "success";
	}

	/**
	 * For displying admin Method tab in ProductStructure level.
	 * 
	 * @return String
	 */
	public String loadForProductStructure() {
		//        Logger.logInfo("Entering the method for loading admin option");
		//        String nodeClicked
		// =(String)getSession().getAttribute(SESSION_NODE_TYPE);
		//        getValuesFromSessionForProdStruc();
		//        String mode = adminMethodLocateCriteria.getMode();
		//        String compName =
		// adminMethodLocateCriteria.getBenefitComponentName();
		//		if (nodeClicked.equals(BENEFIT_DATE) &&
		// mode.equals(WebConstants.VIEW)){
		//			this.adminMethodsList = retrieveOverrideAdminMethodBOList();
		//			this.setBreadCrumbText("Product Configuration >> Product
		// Structure("+this.breadCrumpName +") >> View");
		//			return "prodStructureAdminMethodView";
		//		}else{
		//		    this.adminMethodsList = retrieveOverrideAdminMethodBOList();
		//			this.setBreadCrumbText("Product Configuration >> Product
		// Structure("+this.breadCrumpName +") >> Edit");
		//		    return "prodstradminmethod";
		//		}
		return "success";
	}

	public String loadForProductStructureView() {
		return "success";
	}

	/**
	 * For displying admin Method tab in contract level.
	 * 
	 * @return String
	 */
	public String loadForContract() {
		//        Logger
		//                .logInfo("Entering the method for loading admin Methods in contract
		// level for benefits");
		//        getValuesFromSessionForContract();
		//        String mode = adminMethodLocateCriteria.getMode();
		//        if(mode.equals(WebConstants.ACTION_BENEFIT)){
		//// this.adminMethodsList = retrieveOverrideAdminMethodBOList();
		//// this.setBreadCrumbText("Contract Development >>
		// Contract("+this.breadCrumpName +") >> View");
		//    	    return "contractAdminMethodView";
		//        }else{
		//// this.adminMethodsList = retrieveOverrideAdminMethodBOList();
		//// this.setBreadCrumbText("Contract Development >>
		// Contract("+this.breadCrumpName +") >> Edit");
		//		    return "success";
		//        }
		return "success";
	}

	public String loadForContractView() {
		return "success";
	}

	/**
	 * @return valuesFromSessionForContract
	 * 
	 * Returns the valuesFromSessionForContract.
	 */
	//WAS 7.0 Changes - Binding variable ValuesFromSessionForContract modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	public HtmlInputHidden getValuesFromSessionForContract() {

		ContractCommentBackingBean contractCommentBackingBean = new ContractCommentBackingBean();
		contractCommentBackingBean.setBreadCrumbText();
		if ((null != getSession().getAttribute(CONTRACT_SESSION_KEY) && !getSession()
				.getAttribute(CONTRACT_SESSION_KEY).equals(""))) {
			ContractSession contractSessionObject = (ContractSession) getSession()
					.getAttribute(CONTRACT_SESSION_KEY);
			adminMethodLocateCriteria.setEntityId(contractSessionObject
					.getContractKeyObject().getDateSegmentId());

			setEntityType(WebConstants.CONTRACT);
			adminMethodLocateCriteria.setMode(new Integer(contractSessionObject
					.getMode()).toString());

			adminMethodLocateCriteria.setBenefitSysId(contractSessionObject
					.getBenefitId());
			this.adminId = contractSessionObject.getBenefitAdminId();
			adminMethodLocateCriteria.setAdministrationId(this.adminId);
			adminMethodLocateCriteria.setBenefitComponentName(getSession()
					.getAttribute("BENEFIT_COMP_NAME").toString());

			this.breadCrumpName = contractSessionObject.getContractKeyObject()
					.getContractId();

			this.entityType = "contract";
			this.componentType = contractSessionObject.getContractKeyObject()
					.getContractType();
			adminMethodLocateCriteria
					.setBenefitComponentId(contractSessionObject
							.getBenefitComponentId());
			adminMethodLocateCriteria.setEntityType(this.entityType);
			String mode = adminMethodLocateCriteria.getMode();
			if ((null != getSession().getAttribute(
					WebConstants.SESSION_BNFT_DEFN_ID) && !getSession()
					.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))) {
				this.stdbenId = Integer.parseInt(String.valueOf(getSession()
						.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)));
				adminMethodLocateCriteria.setBenefitDefenitionId(stdbenId); //CARS:AM2
			}
			if (mode.equals(WebConstants.ACTION_BENEFIT)) {
				this.adminMethodsList = retrieveOverrideAdminMethodBOList();
				this.setBreadCrumbText("Contract Development >> Contract ("
						+ this.breadCrumpName + ") >> View");
				valuesFromSessionForContract.setValue("contractAdminMethodView");
				//return "contractAdminMethodView";
				return valuesFromSessionForContract;

			} else {
						
				this.adminMethodsList = retrieveOverrideAdminMethodBOList();
				List messages = (List) getRequest()
				.getAttribute("messages");
				if (messages != null && messages.size() > 0)
					addAllMessagesToRequest(messages);
				this.setBreadCrumbText("Contract Development >> Contract ("+ this.breadCrumpName + ") >> Edit");
				valuesFromSessionForContract.setValue("success");
				//return "success";
				return valuesFromSessionForContract;
			}
		}
		valuesFromSessionForContract.setValue("");
		//return "";
		return valuesFromSessionForContract;
	}

	/**
	 * @param valuesFromSessionForContract
	 * 
	 * Sets the valuesFromSessionForContract.
	 */
	public void setValuesFromSessionForContract(
			HtmlInputHidden valuesFromSessionForContract) {
		this.valuesFromSessionForContract = valuesFromSessionForContract;
	}

	/**
	 * For displying admin Method tab in contract level for product.
	 * 
	 * @return String
	 */
	public String loadForContractProduct() {
		Logger
				.logInfo("Entering the method for loading admin Methods in contract level for  product ");

		return "success";
	}

	/**
	 * For displying admin Method tab in contract level for product.(for view)
	 * 
	 * @return String
	 */
	public String loadForContractProductView() {
		Logger
				.logInfo("Entering the method for loading admin Methods view in contract level for product ");

		return "viewSuccess";
	}

	/**
	 * For displying admin method tab in benefit level.
	 * 
	 * @return String
	 */
	public String loadForBenefit() {
		return "success";
	}

	public String loadForBenefitView() {
		return "success";
	}

	public String saveAdminMethodsForBenefit() {

		/*
		 * if(!validateAdminMethods()){
		 * addAllMessagesToRequest(this.validationMessages); return
		 * WebConstants.EMPTY_STRING; }
		 */
		Set keys = adminMethodMap.keySet();
		Set spsKeys = spsNameMap.keySet();
		Iterator valueIter = keys.iterator();
		Iterator spsIter = spsKeys.iterator();
		String adminId = "";
		List adminIdList = new ArrayList();
		List spsIdList = new ArrayList();
		String spsId = "";
		AdminMethodVO adminMethodVO = new AdminMethodVO();
		
		List adminMethodsListOrig=getBenefitSessionObject().getAdminMethodsList();
		
		while (valueIter.hasNext() && spsIter.hasNext()) {
			Long valueElement = (Long) valueIter.next();
			String value = (String) adminMethodMap.get(valueElement);
			if ((null != value && !(" ".equals(value)) && !("".equals(value)))) {
				StringTokenizer stringTokenizer = new StringTokenizer(value,
						"~");
				while (stringTokenizer.hasMoreTokens()) {
					adminId = stringTokenizer.nextToken();
				}
				adminIdList.add(adminId);
			} else {
				adminIdList.add("0");
			}
		}
		adminMethodVO.setAdminIdList(adminIdList);
		while (spsIter.hasNext()) {
			Long spsValueElement = (Long) spsIter.next();
			String spsValue = (String) spsNameMap.get(spsValueElement);
			if (null != spsValue && !("".equals(spsValue))) {
				StringTokenizer spsStringTokenizer = new StringTokenizer(
						spsValue, "~");
				while (spsStringTokenizer.hasMoreTokens()) {
					spsId = spsStringTokenizer.nextToken();
				}
				spsIdList.add(spsId);
			}
		}
		adminMethodVO.setSpsIdList(spsIdList);
		SaveAdminMethodRequest adminMethodRequest = (SaveAdminMethodRequest) this
				.getServiceRequest(ServiceManager.SAVE_ADMIN_METHOD_REQUEST);
		if (null != getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_ID)
				&& !getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).equals("")) {
			int amnId = Integer.parseInt((String) getSession().getAttribute(
					WebConstants.SESSION_BNFT_ADMIN_ID));
			adminMethodRequest.setAdministrationId(amnId);
		}
		if (null != getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_ID)
				&& !getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).equals("")) {
			// int stbBenId =
			// Integer.parseInt(String.valueOf(getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)));
			adminMethodRequest.setStdBenId(benefitSessionObject
					.getStandardBenefitKey());
		}

		adminMethodRequest.setAdminMethodVO(adminMethodVO);
		adminMethodRequest.setOrigAdminMethodsList(adminMethodsListOrig);
		SaveAdminMethodResponse saveAdminMethodResponse = (SaveAdminMethodResponse) this
				.executeService(adminMethodRequest);
		if (null != saveAdminMethodResponse) {
			this.adminMethodsList = saveAdminMethodResponse.getResultList();
			this.adminMethodSaveMessgeList = saveAdminMethodResponse.getMessages(); //Added for Informational Messages Display
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return "success";
	}
	

	//return admin id list
	private List getAdminIdList() {
		Set keys = adminMethodMap.keySet();
		Iterator valueIter = keys.iterator();
		String adminId = "";
		List adminIdList = new ArrayList();
		while (valueIter.hasNext()) {
			Long valueElement = (Long) valueIter.next();
			String value = (String) adminMethodMap.get(valueElement);
			if (null != value && !(" ".equals(value)) && !("".equals(value))) {
				//adminMethodLocateCriteria.setAdminMethod(valueElement);
				StringTokenizer stringTokenizer = new StringTokenizer(value,
						"~");
				while (stringTokenizer.hasMoreTokens()) {
					adminId = stringTokenizer.nextToken();
				}
				adminIdList.add(adminId);
			} else {
				adminIdList.add("0");
			}
		}
		return adminIdList;
	}
	//returns spsid list.
	private List getSpsIdList() {
		Set spsKeys = spsNameMap.keySet();
		Iterator spsIter = spsKeys.iterator();
		List spsIdList = new ArrayList();
		String spsId = "";
		while (spsIter.hasNext()) {
			Long valueElement = (Long) spsIter.next();
			String value = (String) spsNameMap.get(valueElement);
			if (null != valueElement) {
				StringTokenizer stringTokenizer = new StringTokenizer(value,
						"~");
				while (stringTokenizer.hasMoreTokens()) {
					spsId = stringTokenizer.nextToken();
				}
				spsIdList.add(spsId);
			}
		}
		return spsIdList;
	}

	public String saveAdminMethodsForBenefitComp() {
		//    	if(!validateAdminMethods()){
		//   		 	addAllMessagesToRequest(this.validationMessages);
		//            return WebConstants.EMPTY_STRING;
		//    	}
		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
				.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		OverrideAdminMethodRequest overrideAdminMethodRequest = (OverrideAdminMethodRequest) this
				.getServiceRequest(ServiceManager.OVERRIDE_ADMIN_METHOD_REQUEST);
		overrideAdminMethodRequest.setEntityType(WebConstants.BENEFIT_COMP);
		overrideAdminMethodRequest.setEntitySysId(benefitComponentSessionObject
				.getBenefitComponentId());
		overrideAdminMethodRequest
				.setBenefitCompSysId(benefitComponentSessionObject
						.getBenefitComponentId());
		overrideAdminMethodRequest.setAdminMethodsId(getAdminIdList());
		overrideAdminMethodRequest.setSpsId(getSpsIdList());
		String adminId = getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_ID).toString();
		if (0 != Integer.parseInt(adminId)) {
			overrideAdminMethodRequest.setBenefitAdminId(Integer
					.parseInt(adminId));
		}
//		List of General Admin Methods
		List adminMethodsList =(ArrayList) getSession().getAttribute("adminMethodsList");
		overrideAdminMethodRequest.setAdminMethodsList(adminMethodsList);
		
		OverrideAdminMethodResponse overrideAdminMethodResponse = (OverrideAdminMethodResponse) this
				.executeService(overrideAdminMethodRequest);
		if (null != overrideAdminMethodResponse) {
			this.adminMethodsList = overrideAdminMethodResponse.getResultList();
			this.adminMethodSaveMessgeList = overrideAdminMethodResponse.getMessages(); //Added for Informational Messages Display
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return "success";
	}

	public String saveAdminMethodsForProdStructure() {

		getRequest().setAttribute("RETAIN_Value", "");
		//    	if(!validateAdminMethods()){
		//   		 addAllMessagesToRequest(this.validationMessages);
		//            return WebConstants.EMPTY_STRING;
		//    	}

		String benCompId = "";

		ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
				.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);
		String benefitSysId = (String) getSession().getAttribute(
				"STANDARD_BNFT_KEY");

		int benefitId = (new Integer(benefitSysId)).intValue();

		OverrideAdminMethodRequest overrideAdminMethodRequest = (OverrideAdminMethodRequest) this
				.getServiceRequest(ServiceManager.OVERRIDE_ADMIN_METHOD_REQUEST);
		overrideAdminMethodRequest.setEntityType("ProdStructure");
		overrideAdminMethodRequest.setEntitySysId(productStructureSessionObject
				.getId());
		if (null != getSession()
				.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).toString()
				&& !getSession()
						.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)
						.toString().equals("")) {
			StringTokenizer stringTokenizer = new StringTokenizer(
					getSession()
							.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)
							.toString(), "~");
			while (stringTokenizer.hasMoreTokens()) {
				benCompId = stringTokenizer.nextToken();
			}
			overrideAdminMethodRequest.setBenefitCompSysId(new Integer(
					benCompId).intValue());
		}
		if (null != getSession().getAttribute("BNFT_CMPNT_KEY")) {
			overrideAdminMethodRequest.setAdminBcompId(Integer
					.parseInt(getSession().getAttribute("BNFT_CMPNT_KEY")
							.toString()));
		}
		overrideAdminMethodRequest.setAdminMethodsId(getAdminIdList());
		overrideAdminMethodRequest.setSpsId(getSpsIdList());
		overrideAdminMethodRequest.setStdBenId(benefitId);
		overrideAdminMethodRequest.setBenefitAdminId(new Integer(getSession()
				.getAttribute(WebConstants.SESSION_BNFT_ADMIN_ID).toString())
				.intValue());
        
		// List of General Admin Methods
		List adminMethodsList =(ArrayList) getSession().getAttribute("adminMethodsList");
		overrideAdminMethodRequest.setAdminMethodsList(adminMethodsList);
		
		updateAMVValidationForPS(overrideAdminMethodRequest);
		OverrideAdminMethodResponse overrideAdminMethodResponse = (OverrideAdminMethodResponse) this
				.executeService(overrideAdminMethodRequest);
		if (null != overrideAdminMethodResponse) {
			this.adminMethodsList = overrideAdminMethodResponse.getResultList();
		}
		return "success";
	}

	private List getUpdatedListFromScreen(List administrationList) {
		Set adminMethodkeys = adminMethodMap.keySet();
		Iterator valueIter = adminMethodkeys.iterator();
		AdminMethodValidationBO adminMethodValidationBO = null;
		while (valueIter.hasNext()) {
			int adminMethodId = 0;
			int spsId = 0;
			int benefitAdministrationId = 0;
			String adminmethodDesc = "";
			Long valueElement = (Long) valueIter.next();
			String value = (String) adminMethodMap.get(valueElement);
			if ((null != value && !(" ".equals(value)) && !("".equals(value)))) {
				StringTokenizer stringTokenizer = new StringTokenizer(value,
						"~");
				int j = 0;
				while (stringTokenizer.hasMoreTokens()) {
					if (j == 0)
						adminmethodDesc = stringTokenizer.nextToken();
					else
						adminMethodId = Integer.parseInt(stringTokenizer
								.nextToken());
					j++;
				}
			} else {
				adminMethodId = 0;
			}

			String valueForSps = (String) spsNameMap.get(valueElement);
			if (null != valueForSps) {
				StringTokenizer stringTokenizer = new StringTokenizer(
						valueForSps, "~");
				while (stringTokenizer.hasMoreTokens()) {
					spsId = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			String valueForAdminId = (String) benefitAdministrationMap
					.get(valueElement);
			if (null != valueForAdminId) {
				StringTokenizer stringTokenizer = new StringTokenizer(
						valueForAdminId, "~");
				while (stringTokenizer.hasMoreTokens()) {
					benefitAdministrationId = Integer.parseInt(stringTokenizer
							.nextToken());
				}
			}
			if (null != administrationList && administrationList.size() > 0) {

				for (int i = 0; i < administrationList.size(); i++) {
					adminMethodValidationBO = (AdminMethodValidationBO) administrationList
							.get(i);

					if (benefitAdministrationId == adminMethodValidationBO
							.getBenefitAdminSysId()
							&& spsId == adminMethodValidationBO.getSpsId()) {
						adminMethodValidationBO
								.setAdminMethodDesc(adminmethodDesc);
						adminMethodValidationBO.setAdminMethodId(adminMethodId);
					}
				}

			}

		}

		//modification ends
		return administrationList;
	}

	public String saveAdminMethodsForValidation() 
	{
		/*
		 * if(!validateAdminMethods()){
		 * * addAllMessagesToRequest(this.validationMessages);
		 * this.setValidationStatus(false); return WebConstants.EMPTY_STRING; }
		 */
		getSession().removeAttribute("DIRECT_CLICK");
		List adminList = new ArrayList();
		Set adminMethodkeys = adminMethodMap.keySet();
		Set spsKeys = spsNameMap.keySet();
		Set adminKey = benefitAdministrationMap.keySet();
		int entityId = 0;
		int benefitComSysId = 0;
		int benefitComSqnc = 0;
		int benefitSqnc = 0;
		int benefitSysId = 0;
		String entityType = "";
		SaveAdminMethodValidationRequest saveAdminMethodValidationRequest = new SaveAdminMethodValidationRequest();

		String entityName = "";
		boolean samePage = false;

		if (null != getSession().getAttribute("AM_ENTITY_KEY").toString()&& !getSession().getAttribute("AM_ENTITY_KEY").toString().equals("")) 
		{
			entityId = new Integer(getSession().getAttribute("AM_ENTITY_KEY").toString()).intValue();
		}
		if (null != getSession().getAttribute("AM_BC_KEY").toString()&& !getSession().getAttribute("AM_BC_KEY").toString().equals("")) 
		{
			benefitComSysId = new Integer(getSession().getAttribute("AM_BC_KEY").toString()).intValue();
		}
		if (null != getSession().getAttribute("AM_BENEFIT").toString()&& !getSession().getAttribute("AM_BENEFIT").toString().equals(""))
		{
			benefitSysId = new Integer(getSession().getAttribute("AM_BENEFIT").toString()).intValue();
		}
		if (null != getSession().getAttribute("AM_ENTITY_TYPE").toString()&& !getSession().getAttribute("AM_ENTITY_TYPE").toString().equals("")) 
		{
			entityType = getSession().getAttribute("AM_ENTITY_TYPE").toString();
		}
		if (null != getSession().getAttribute("AM_BENEFIT_COMP_NAME").toString()&& !getSession().getAttribute("AM_BENEFIT_COMP_NAME").toString().equals("")) 
		{
			entityName = getSession().getAttribute("AM_BENEFIT_COMP_NAME").toString();
		}
		if (null != getSession().getAttribute("AM_BC_SQNC")&& !getSession().getAttribute("AM_BC_SQNC").toString().equals("")) 
		{
			benefitComSqnc = new Integer(getSession().getAttribute("AM_BC_SQNC").toString()).intValue();
		}
		if (null != getSession().getAttribute("AM_BEN_SQNC")&& !getSession().getAttribute("AM_BEN_SQNC").toString().equals(""))
		{
			benefitSqnc = new Integer(getSession().getAttribute("AM_BEN_SQNC").toString()).intValue();
		}

		if (entityType.equalsIgnoreCase("contract")) {
			if (null != getSession().getAttribute("AM_CONTRACT_ID")
					&& !"".equals(getSession().getAttribute("AM_CONTRACT_ID"))) {
				saveAdminMethodValidationRequest.setContractSysId(new Integer(
						getSession().getAttribute("AM_CONTRACT_ID").toString())
						.intValue());
			}
			// Get the
			if (null != getSession().getAttribute("prodIdForCheckIn")
					&& !""
							.equals(getSession().getAttribute(
									"prodIdForCheckIn"))) {
				saveAdminMethodValidationRequest.setProductId(new Integer(
						getSession().getAttribute("prodIdForCheckIn")
								.toString()).intValue());
			}
			if (null != (ContractSession) getSession().getAttribute(
					WebConstants.CONTRACT)) {
				ContractSession contractSession = (ContractSession) getSession()
						.getAttribute(WebConstants.CONTRACT);
				saveAdminMethodValidationRequest.setProductId(contractSession
						.getContractKeyObject().getProductId());
			}
		}
		saveAdminMethodValidationRequest.setEntityType(entityType);
		saveAdminMethodValidationRequest.setBenefitComSysId(benefitComSysId);
		saveAdminMethodValidationRequest.setEntitySysId(entityId);
		saveAdminMethodValidationRequest.setBenefitSysId(benefitSysId);
		saveAdminMethodValidationRequest.setBenefitComponentName(entityName);
		saveAdminMethodValidationRequest.setBenefitComSqncNumber(benefitComSqnc);
		saveAdminMethodValidationRequest.setBenefitSqncNumber(benefitSqnc);
		Iterator valueIter = adminMethodkeys.iterator();
		Iterator spsIter = spsKeys.iterator();
		List adminIdList = new ArrayList();
		List spsIdList = new ArrayList();
		AdminMethodVO adminMethodVO = new AdminMethodVO();
		List requestList = new ArrayList();
		while (valueIter.hasNext()) 
		{
			SaveAdminMethodValidationRequest request = new SaveAdminMethodValidationRequest();
			String adminMethodId = "";
			String spsId = "";
			String benefitAdministrationId = "";
			Long valueElement = (Long) valueIter.next();
			String value = (String) adminMethodMap.get(valueElement);
			if ((null != value && !(" ".equals(value)) && !("".equals(value)))) 
			{
				StringTokenizer stringTokenizer = new StringTokenizer(value,"~");
				while (stringTokenizer.hasMoreTokens()) 
				{
					adminMethodId = stringTokenizer.nextToken();
				}
			} 
			else
			{
				samePage = true;
				continue;
			}
			String valueForSps = (String) spsNameMap.get(valueElement);
			if (null != valueForSps) 
			{
				StringTokenizer stringTokenizer = new StringTokenizer(valueForSps, "~");
				while (stringTokenizer.hasMoreTokens()) 
				{
					spsId = stringTokenizer.nextToken();
				}
			}

			String valueForAdminId = (String) benefitAdministrationMap.get(valueElement);
			if (null != valueForAdminId) 
			{
				StringTokenizer stringTokenizer = new StringTokenizer(valueForAdminId, "~");
				while (stringTokenizer.hasMoreTokens()) 
				{
					benefitAdministrationId = stringTokenizer.nextToken();
				}
			}
			request.setBenefitAdminSysId(Integer.parseInt(benefitAdministrationId));
			request.setSpsId(Integer.parseInt(spsId));
			request.setAdminMethodId(Integer.parseInt(adminMethodId));

			request.setEntityType(entityType);
			request.setBenefitComSysId(benefitComSysId);
			request.setEntitySysId(entityId);
			request.setBenefitSysId(benefitSysId); 

			requestList.add(request);
		}
		/*START AM1 CARS */
		saveAdminMethodValidationRequest.setAdminMethodValidationList(requestList);
		boolean  tierExist = false;
		if( null != entityType && !"".equals(entityType) && entityType.equalsIgnoreCase("PRODUCT") && renderTierPanel &&
		        null != getProductSessionObject().getTierCriteriaDefinitionList()&& getProductSessionObject().getTierCriteriaDefinitionList().size() > 0){
		    tierExist = true;
		 }
		else if( null != entityType && !"".equals(entityType) && entityType.equalsIgnoreCase("CONTRACT")  && renderTierPanel 
		        && null != getContractSession().getTierDefinitionsList() && getContractSession().getTierDefinitionsList().size() >0){
		    tierExist = true;
		}
		if(tierExist){
		    saveAdminMethodValidationRequest.setTieredAdminMethodList(fetchChangedAdminMethodsInTiersForCheckin(entityType));
		}		 		
		SaveAdminMethodValidationResponse saveAdminMethodValidationResponse = (SaveAdminMethodValidationResponse) this.executeService(saveAdminMethodValidationRequest);
		/*END AM1 CARS */
		getSession().removeAttribute("treeChildren");
		// Set the ids for the next display
		if (saveAdminMethodValidationResponse.getBenefitComponentId() != 0) 
		{
			getSession().setAttribute("AM_BC_KEY",saveAdminMethodValidationResponse.getBenefitComponentId()+ "");
		}else{
			if (entityType.equals("contract"))  {
				/*   WLPRD00444546  changes starts */ 
			 getSession().removeAttribute("AM_BC_KEY");
			 getSession().removeAttribute("AM_BENEFIT");
			 /*   WLPRD00444546  changes starts */ 

			}
		}
		if (saveAdminMethodValidationResponse.getBenefitId() != 0) 
		{
			getSession().setAttribute("AM_BENEFIT",saveAdminMethodValidationResponse.getBenefitId() + "" + "");
		}
		if (entityType.equals("contract"))  
		{
			if (samePage) 
			{
				return "adminmethodContractValidation";
			}
			else
			{
				if (saveAdminMethodValidationResponse.isNextBcExists()) 
				{
					return "adminmethodContractValidation";
				} 
				else 
				{
					return "adminmethodContractValidationView";
				}
			}
		} 
		else
		{
			if(samePage)
				return "adminmethodValidation";
			else
			{
				loadForProductValidation();
				hasValidationErrors = true;
				if (saveAdminMethodValidationResponse.isNextBcExists()) 
				{
					return "adminmethodValidation";
				}
				else
				{
					return "adminmethodValidationView";
				}
			}
		}
	}
	/**
	 * @param overrideAdminMethodRequest
	 */
	private void updateAMVValidationForPS(OverrideAdminMethodRequest req) 
	{
		if (null != req && req.getAdminMethodsId() != null
				&& req.getAdminMethodsId().size() > 0) {
			List sysids = req.getAdminMethodsId();
			List oldIds = loadOldState();
			List spsID = req.getSpsId();
			if (sysids.size() == oldIds.size()) {
				for (int i = 0; i < oldIds.size(); i++) {
					if (!sysids.get(i).equals(oldIds.get(i))) {
						req.setSpsChanged(true);
						if (req.getChangedIds() == null) {
							req.setChangedIds(new ArrayList());
						}
						req.getChangedIds().add(
								new Integer((String) spsID.get(i)));
						req.setBenefitCompName((String) getSession()
								.getAttribute("BENEFIT_COMP_NAME"));
					}
				}
			}
		}
	}

	public String saveAdminMethodsForProduct() {
		getRequest().setAttribute("RETAIN_Value", "");
		ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(PRODUCT_SESSION_KEY);

		OverrideAdminMethodRequest overrideAdminMethodRequest = (OverrideAdminMethodRequest) this.getServiceRequest(ServiceManager.OVERRIDE_ADMIN_METHOD_REQUEST);
		overrideAdminMethodRequest.setEntityType("product");
		overrideAdminMethodRequest.setEntitySysId(productSessionObject.getProductKeyObject().getProductId());
		if (null != getSession().getAttribute(BENEFIT_COMP_KEY).toString() && !getSession().getAttribute(BENEFIT_COMP_KEY).toString().equals("")) {
			overrideAdminMethodRequest.setBenefitCompSysId(new Integer(getSession().getAttribute(BENEFIT_COMP_KEY).toString()).intValue());
		}
		overrideAdminMethodRequest.setBenefitCompName((String) getSession().getAttribute("BENEFIT_COMP_NAME"));
		overrideAdminMethodRequest.setAdminMethodsId(getAdminIdList());
		overrideAdminMethodRequest.setSpsId(getSpsIdList());
		overrideAdminMethodRequest.setBenefitAdminId(new Integer(getSession().getAttribute(ADMIN_KEY).toString()).intValue());


		if (null != getSession().getAttribute("SESSION_BNFT_ID").toString()
				&& !getSession().getAttribute("SESSION_BNFT_ID").toString()
						.equals("")) {
			overrideAdminMethodRequest.setStdBenId(new Integer(getSession()
					.getAttribute("SESSION_BNFT_ID").toString()).intValue());
		}
		
		//List of General Admin Methods
		List adminMethodsList =(ArrayList) getSession().getAttribute("adminMethodsList");
		overrideAdminMethodRequest.setAdminMethodsList(adminMethodsList);
		
		//List of Tiered Admin Methods
		List adminMethodListForDB = fetchChangedAdminMethodsInTiers(overrideAdminMethodRequest.getEntityType()); //CARS:AM2
		//List adminMethodListForDB = fetchChangedAdminMethodsInTiersProduct(overrideAdminMethodRequest.getEntityType());
		overrideAdminMethodRequest.setAdminMethodListForDB(adminMethodListForDB); //CARS:AM2
			
		upudateRequestForAdminMethodValidation(overrideAdminMethodRequest);
		OverrideAdminMethodResponse overrideAdminMethodResponse = (OverrideAdminMethodResponse) this.executeService(overrideAdminMethodRequest);
		if (null != overrideAdminMethodResponse) {
			this.adminMethodsList = overrideAdminMethodResponse.getResultList();
			
		}

		return "success";
	}

	/**
	 * @param overrideAdminMethodRequest
	 */
	private void upudateRequestForAdminMethodValidation(
			OverrideAdminMethodRequest request) {
		List changedIds = new ArrayList();
		boolean isChanged = false;
		if (WebConstants.GEN_BENEFITS.equals(getSession().getAttribute(
				"BENEFIT_COMP_NAME"))) {
			List oldAdminIds = loadOldState();
			List newIds = request.getAdminMethodsId();
			List spsList = request.getSpsId();
			if (oldAdminIds.size() == newIds.size()
					&& newIds.size() == spsList.size()) {
				for (int i = 0; i < oldAdminIds.size(); i++) {
					if (!newIds.get(i).equals(oldAdminIds.get(i))) {
						isChanged = true;
						changedIds.add(spsList.get(i));
					}
				}
			}
		}
		request.setChangedIds(changedIds);
		request.setSpsChanged(isChanged);
	}

	/**
	 * @return
	 */
	private List loadOldState() {
		if (adminMethodState != null && !"".equals(adminMethodState.trim())) {
			String[] ids = adminMethodState.split(":");
			return Arrays.asList(ids);
		}
		return null;
	}

	public String saveAdminMethodsForContract() {
		ContractSession contractSessionObject = (ContractSession) getSession()
				.getAttribute(CONTRACT_SESSION_KEY);

		OverrideAdminMethodRequest overrideAdminMethodRequest = (OverrideAdminMethodRequest) this
				.getServiceRequest(ServiceManager.OVERRIDE_ADMIN_METHOD_REQUEST);
		overrideAdminMethodRequest.setEntityType("contract");
		int dateSegment = contractSessionObject.getContractKeyObject()
				.getDateSegmentId();
		overrideAdminMethodRequest.setEntitySysId(dateSegment);
		overrideAdminMethodRequest.setBenefitCompSysId(contractSessionObject
				.getBenefitComponentId());
		overrideAdminMethodRequest.setAdminMethodsId(getAdminIdList());
		overrideAdminMethodRequest.setSpsId(getSpsIdList());
		overrideAdminMethodRequest.setContractSysId(contractSessionObject
				.getContractKeyObject().getContractSysId());
		overrideAdminMethodRequest.setBenefitAdminId(contractSessionObject
				.getBenefitAdminId());

		overrideAdminMethodRequest.setProductId(contractSessionObject
				.getContractKeyObject().getProductId());

		//		adminMethodLocateCriteria.setBenefitSysId(contractSessionObject.getBenefitId());
		//		adminMethodLocateCriteria.setBenefitComponentName(getSession().getAttribute("BENEFIT_COMP_NAME").toString());
		overrideAdminMethodRequest.setBenefitSysId(contractSessionObject
				.getBenefitId());
		//		overrideAdminMethodRequest.setBenefitCompName(getSession()
		//				.getAttribute("BENEFIT_COMP_NAME").toString());

		overrideAdminMethodRequest.setStdBenId(Integer
				.parseInt((String) getSession().getAttribute(
						"SESSION_BNFT_DEFN_ID")));
		
        //List of General Admin Methods
		List adminMethodsList =(ArrayList) getSession().getAttribute("adminMethodsList");
		overrideAdminMethodRequest.setAdminMethodsList(adminMethodsList);
		
		//List of Tiered Admin Methods
		List adminMethodListForDB = fetchChangedAdminMethodsInTiers(overrideAdminMethodRequest.getEntityType()); //CARS:AM2
		overrideAdminMethodRequest.setAdminMethodListForDB(adminMethodListForDB); //CARS:AM2
		overrideAdminMethodRequest.setChangedAmValTierIdMap(changedTierAmIdsMap);
				
		updateAMVForContract(overrideAdminMethodRequest);
		OverrideAdminMethodResponse overrideAdminMethodResponse = (OverrideAdminMethodResponse) this
				.executeService(overrideAdminMethodRequest);
		if (null != overrideAdminMethodResponse) {
			this.adminMethodsList = overrideAdminMethodResponse.getResultList();
			this.adminMethodSaveMessgeList = overrideAdminMethodResponse.getMessages(); //Added for Informational Messages Display
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return "success";
	}

	/**
	 * @return Returns the breadcrumbValue.
	 */
	public String getBreadcrumbValue() {
		String entityType = getSession().getAttribute("AM_ENTITY_TYPE")
				.toString();
		String entityName = getSession().getAttribute("AM_ENTITY_NAME")
				.toString();
		this.setBreadCrumbText("Product Configuration >> " + entityType + " ("
				+ entityName + ") >> Edit(Admin Process Validation Errors)");
		return breadcrumbValue;
	}

	/**
	 * @param breadcrumbValue
	 *            The breadcrumbValue to set.
	 */
	public void setBreadcrumbValue(String breadcrumbValue) {
		this.breadcrumbValue = breadcrumbValue;
	}

	public String loadForProductValidation() {
		String entityType = getSession().getAttribute("AM_ENTITY_TYPE")
				.toString();
		String entityName = getSession().getAttribute("AM_ENTITY_NAME")
				.toString();
		String entValue = entityType;
		if (entityType != null && entityType.length() > 0)
			entityType = ("" + entityType.charAt(0)).toUpperCase()
					+ entityType.substring(1);
		if ("prodStructure".equalsIgnoreCase(entityType))
			entityType = "Product Structure";
		this.setBreadCrumbText("Product Configuration >> " + entityType + " ("
				+ entityName + ") >> Edit(Admin Process Validation Errors)");
		if (entValue.equals("contract"))
			return "contractpage";
		else
			return "success";

	}

	/**
	 * @return Returns the administrationSPSList.
	 */
	public List getAdministrationSPSList() {
		return administrationSPSList;
	}

	/**
	 * @param administrationSPSList
	 *            The administrationSPSList to set.
	 */
	public void setAdministrationSPSList(List administrationSPSList) {
		this.administrationSPSList = administrationSPSList;
	}
	
	private void getHeaderPanelForContractValidation(
			HtmlPanelGrid panelForValidation,List administrationList) {

		HtmlOutputText spsOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText1 = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText2 = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText4 = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText3 = new HtmlOutputText();

		HtmlOutputText administrationText = new HtmlOutputText();

		administrationText.setValue("Benefit Administration");
		administrationText.setId("reference");
		administrationText.setStyleClass("dataTableHeader");

		spsOutputText.setValue("SPS Name");
		spsOutputText.setId("spsNames");
		spsOutputText.setStyleClass("dataTableHeader");

		panelForValidation.getChildren().add(administrationText);
		panelForValidation.getChildren().add(spsOutputText);

		
		if (administrationList.size() > 0) {

			AdminMethodValidationBO adminMethodValidationBO = (AdminMethodValidationBO) administrationList
					.get(0);
			boolean isPOS = adminMethodValidationBO.isPosProductFamily();
			if (isPOS) {

				adminMethodOutputText1.setValue("PPO Admin Method");
				adminMethodOutputText1.setId("PPOAdminMethod");
				adminMethodOutputText1.setStyleClass("dataTableHeader");

				adminMethodOutputText3.setValue("");
				adminMethodOutputText3.setId("HMOAdminMethod1");
				adminMethodOutputText3.setStyleClass("dataTableHeader");

				adminMethodOutputText2.setValue("HMO Admin Method");
				adminMethodOutputText2.setId("HMOAdminMethod");
				adminMethodOutputText2.setStyleClass("dataTableHeader");

				adminMethodOutputText4.setValue("");
				adminMethodOutputText4.setId("HMOAdminMethod2");
				adminMethodOutputText4.setStyleClass("dataTableHeader");

				panelForValidation.getChildren().add(adminMethodOutputText1);
				panelForValidation.getChildren().add(adminMethodOutputText3);
				panelForValidation.getChildren().add(adminMethodOutputText4);
				panelForValidation.getChildren().add(adminMethodOutputText2);

			} else {

				if (adminMethodValidationBO.getProductFamName()
						.equalsIgnoreCase("HMO")) {
					adminMethodOutputText1.setValue("HMO Admin Method");
					adminMethodOutputText1.setId("HMOAdminMethod");
					adminMethodOutputText1.setStyleClass("dataTableHeader");
				} else {
					adminMethodOutputText1.setValue("PPO Admin Method");
					adminMethodOutputText1.setId("PPOAdminMethod");
					adminMethodOutputText1.setStyleClass("dataTableHeader");
				}

				panelForValidation.getChildren().add(adminMethodOutputText1);

			}

		}
	}	
	/*START AM1 CARS */	
	
	/**
	 * Method to display the header panel for tiered Admin Methods while check in.
	 */
	private HtmlPanelGrid getHeaderPanelForTierValidation() {	
		HtmlPanelGrid headerPanelForTierValidation = new HtmlPanelGrid();
		headerPanelForTierValidation.setWidth("100%");
		headerPanelForTierValidation.setColumns(3);
		headerPanelForTierValidation.setBorder(0);
		headerPanelForTierValidation.setStyleClass("outputText");
		headerPanelForTierValidation.setCellpadding("3");
		headerPanelForTierValidation.setCellspacing("1");
		headerPanelForTierValidation.setBgcolor("#ffffff");
		HtmlOutputText spsTierOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText = new HtmlOutputText();
		HtmlOutputText administrationText = new HtmlOutputText();			
		administrationText.setValue("Benefit Administration");
		administrationText.setId("tierReference");
		administrationText.setStyleClass("dataTableHeader");
		spsTierOutputText.setValue("SPS Name");
		spsTierOutputText.setId("tierSpsNames");
		spsTierOutputText.setStyleClass("dataTableHeader");
		adminMethodOutputText.setValue("Admin Method");
		adminMethodOutputText.setId("tierAdminMethod");
		adminMethodOutputText.setStyleClass("dataTableHeader");
		headerPanelForTierValidation.getChildren().add(administrationText);
		headerPanelForTierValidation.getChildren().add(spsTierOutputText);
		headerPanelForTierValidation.getChildren().add(adminMethodOutputText);
		return headerPanelForTierValidation; 

	}
	
	/*END AM1 CARS */
	
	private void getHeaderPanelForValidation(HtmlPanelGrid panelForValidation) {
		HtmlOutputText spsOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText = new HtmlOutputText();
		HtmlOutputText administrationText = new HtmlOutputText();

		administrationText.setValue("Benefit Administration");
		administrationText.setId("reference");
		administrationText.setStyleClass("dataTableHeader");

		spsOutputText.setValue("SPS Name");
		spsOutputText.setId("spsNames");
		spsOutputText.setStyleClass("dataTableHeader");

		adminMethodOutputText.setValue("Admin Method");
		adminMethodOutputText.setId("AdminMethod");
		adminMethodOutputText.setStyleClass("dataTableHeader");

		panelForValidation.getChildren().add(administrationText);
		panelForValidation.getChildren().add(spsOutputText);
		panelForValidation.getChildren().add(adminMethodOutputText);

	}

	/*
	 *  Admin Method Validation Panel Specific For Contract
	 * 
	 */

	public HtmlPanelGrid getPanelForContractValidation() {

		List administrationList = this.getSPSList();
		boolean isPOS = false;
		/*
		 * if( !this.isValidationStatus()){ administrationList =
		 * getUpdatedListFromScreen(administrationList); }
		 */
		String previousSPS = new String();
		panelForContractValidation = new HtmlPanelGrid();
		boolean isInsertBlankLeftPanel = false;
		boolean isInsertBlankRightPanel = false;
		

		if (null != administrationList && administrationList.size() > 0) {
			this.getDisplayPanel();
			panelForContractValidation.setWidth("100%");

			AdminMethodValidationBO adminMethodValidationBO1 = (AdminMethodValidationBO) administrationList
					.get(0);
			if (adminMethodValidationBO1.isPosProductFamily()) {
				panelForContractValidation.setColumns(6);
				isPOS = true;
			} else {
				isPOS = false;
				panelForContractValidation.setColumns(3);
			}

			panelForContractValidation.setBorder(0);
			panelForContractValidation.setStyleClass("outputTextBordered");
			panelForContractValidation.setCellpadding("3");
			panelForContractValidation.setCellspacing("0");
			panelForContractValidation.setBgcolor("#cccccc");
			panelForContractValidation.setColumnClasses("column25px,column35px,column40px");					
			hasValidationErrors = true;

			getHeaderPanelForContractValidation(panelForContractValidation, administrationList );


		} else {

			//        	HtmlOutputText noAdminMethod = new HtmlOutputText();

			//        	noAdminMethod.setValue("No Admin Methods Found");
			//        	panelForValidation.getChildren().add(noAdminMethod);
		}

		//  List adminMethodList = this.getSPSList();
		int administrationId = 0;

		AdminMethodValidationBO adminMethodValidationBO = null;
		if (null != administrationList &&  administrationList.size() > 0) {
			// Sorting with the SPS Name
			Collections.sort(administrationList,
					new AdminMethodValidationBOComparator());

			for (int i = 0, j = 0; i < administrationList.size(); i++, j++) {
				adminMethodValidationBO = (AdminMethodValidationBO) administrationList
						.get(i);

				if (administrationId != adminMethodValidationBO
						.getBenefitAdminSysId()) {

					HtmlOutputLabel AdministartionDesc = new HtmlOutputLabel();
					AdministartionDesc.setFor("benefitAdministration" + j);
					AdministartionDesc.setId("benefitAdministration"+RandomStringUtils.randomAlphanumeric(15));
					//AdministartionDesc.setId("benefitAdministration" + j);

					HtmlOutputLabel blankLabelForSPSName = new HtmlOutputLabel();
					blankLabelForSPSName.setFor("blankLabelForSPSName" + j);
					blankLabelForSPSName.setId("blankLabelForSPSName"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForSPSName.setId("blankLabelForSPSName" + j);

					HtmlOutputLabel blankLabelForAM = new HtmlOutputLabel();
					blankLabelForAM.setFor("blankLabelForAM" + j);
					blankLabelForAM.setId("blankLabelForAM"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForAM.setId("blankLabelForAM" + j);

					HtmlOutputLabel blankLabelForAM2 = new HtmlOutputLabel();
					blankLabelForAM2.setFor("blankLabelForAM2" + j);
					blankLabelForAM2.setId("blankLabelForAM2"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForAM.setId("blankLabelForAM2" + j);

					HtmlOutputLabel blankLabelForAM3 = new HtmlOutputLabel();
					blankLabelForAM3.setFor("blankLabelForAM3" + j);
					blankLabelForAM3.setId("blankLabelForAM3"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForAM.setId("blankLabelForAM3" + j);

					HtmlOutputLabel blankLabelForAM4 = new HtmlOutputLabel();
					blankLabelForAM4.setFor("blankLabelForAM4" + j);
					blankLabelForAM4.setId("blankLabelForAM4"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForAM.setId("blankLabelForAM4" + j);

					HtmlOutputLabel blankLabelForAM5 = new HtmlOutputLabel();
					blankLabelForAM5.setFor("blankLabelForAM5" + j);
					blankLabelForAM5.setId("blankLabelForAM5"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForAM.setId("blankLabelForAM5" + j);

					HtmlOutputLabel blankLabelForAM6 = new HtmlOutputLabel();
					blankLabelForAM6.setFor("blankLabelForAM6" + j);
					blankLabelForAM6.setId("blankLabelForAM6"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForAM.setId("blankLabelForAM6" + j);

					String startDate = adminMethodValidationBO
							.getEffectiveDate();
					String endDate = adminMethodValidationBO.getExpiryDate();
					String administration = startDate + " - " + endDate;

					HtmlOutputText outputText1 = new HtmlOutputText();
					outputText1.setId("administration" + j);
					outputText1.setValue(administration);

					AdministartionDesc.getChildren().add(outputText1);

					panelForContractValidation.getChildren().add(
							AdministartionDesc);
					panelForContractValidation.getChildren().add(
							blankLabelForSPSName);
					panelForContractValidation.getChildren().add(
							blankLabelForAM);

					if (adminMethodValidationBO.isPosProductFamily()) {
						panelForContractValidation.getChildren().add(
								blankLabelForAM2);
						panelForContractValidation.getChildren().add(
								blankLabelForAM3);
						panelForContractValidation.getChildren().add(
								blankLabelForAM4);

						//						panelForValidation.getChildren().add(blankLabelForAM5);
						//						panelForValidation.getChildren().add(blankLabelForAM6);

					}

					//   panel.getChildren().add(val);
					// panel.getChildren().add(val);

					j++;
				}

				administrationId = adminMethodValidationBO
						.getBenefitAdminSysId();
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + j);
				outputText1.setValue(adminMethodValidationBO.getSpsName());

				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenSpsName" + j);
				hiddenSps.setValue("" + adminMethodValidationBO.getSpsId());

				ValueBinding valBindingForSpsName = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.spsNameMap[" + j
										+ "]}");
				hiddenSps.setValueBinding("value", valBindingForSpsName);

				//-----------

				HtmlInputHidden hiddenAdmin = new HtmlInputHidden();
				hiddenAdmin.setId("hiddenAdmin" + j);
				hiddenAdmin.setValue(""
						+ adminMethodValidationBO.getBenefitAdminSysId());

				ValueBinding valBindingForAdmin = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.benefitAdministrationMap["
										+ j + "]}");
				hiddenAdmin.setValueBinding("value", valBindingForAdmin);

				//-----------

				// HtmlInputTextarea inputText2 = new HtmlInputTextarea();
				HtmlInputText inputText2 = new HtmlInputText();
				inputText2.setId("adminMethod" + j);
				inputText2.setStyleClass("formInputFieldforDiv");
				String toolTipForDesc = adminMethodValidationBO
						.getAdminMethodDesc();
				inputText2.setTitle(toolTipForDesc);
				if (null == adminMethodValidationBO.getAdminMethodDesc()) {

					inputText2.setValue("");
				} else {
					inputText2.setValue(""
							+ adminMethodValidationBO.getAdminMethodDesc());
				}
				inputText2.setReadonly(true);
				inputText2.setOnmouseover("setTitle(" + j + ")");
				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenAdminMethodDetails" + j);
				if (null != adminMethodValidationBO.getAdminMethodDesc()
						&& 0 != adminMethodValidationBO.getAdminMethodId()) {
					hiddenAdminDetails.setValue(""
							+ adminMethodValidationBO.getAdminMethodDesc()
							+ "~" + adminMethodValidationBO.getAdminMethodId());
				} else {
					hiddenAdminDetails.setValue(" ");
				}

				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace" + j);
				outputText5.setValue(" ");

				ValueBinding valBindingForAdminMethod = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.adminMethodMap[" + j
										+ "]}");
				hiddenAdminDetails.setValueBinding("value",
						valBindingForAdminMethod);

				if ("product".equals(adminMethodValidationBO.getEntityType())) {
					adminMethodValidationBO
							.setEntityTypeForValidation("productValidation");
				}

				HtmlCommandButton selectButton = new HtmlCommandButton();
				selectButton.setId("selectButton" + j);
				selectButton.setStyle("border:0;");
				selectButton.setImage("../../images/select.gif");
				selectButton.setTitle("Select");
				selectButton
						.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
								+ Math.random()
								+ "&spsId="
								+ adminMethodValidationBO.getSpsId()
								+ "&spsName="
								+ adminMethodValidationBO.getSpsName()
								+ "&adminId="
								+ adminMethodValidationBO
										.getBenefitAdminSysId()
								+ "&stdbenId="
								+ adminMethodValidationBO.getBenefitSysId()
								+ "&entityType="
								+ adminMethodValidationBO.getEntityType()
								+ "&validation="
								+ "validation"
								+ "', 'adminMethodForm:adminMethod"
								+ j
								+ "','adminMethodForm:hiddenAdminMethodDetails"
								+ j + "',2,1);return false;");

				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton" + i);
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("getViewDetails('"
						+ adminMethodValidationBO.getSpsId() + "','"
						+ adminMethodValidationBO.getBenefitAdminSysId()
						+ "','" + adminMethodValidationBO.getBenefitSysId()
						+ "','" + adminMethodValidationBO.getEntityType()
						+ "','" + adminMethodValidationBO.getAdminMethodId()
						+ "'," + "'adminMethodForm:hiddenAdminMethodDetails"
						+ j + "'" + ");return false;");

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + j);
				sps.setId("spsnsame9"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + j);
				//	sps.setStyle("background-color: #DF0000;border:2;");

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + j);
				ref.setId("adminMethod9"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + j);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + j);
				val.setId("reference9"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + j);

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("administration" + j);
				//outputText3.setValue("---TEST--");
				/*
				 *  If its POS Add the SPS Name only once for a PPO And HMO
				 *  
				 *  Add it for all the PPO
				 *  Dont Add it if the PPO and HMO sps name is Same
				 *  Add it for first HMO --> previousSPS == null
				 */

				if (isPOS) {
					if (previousSPS == null
							|| adminMethodValidationBO.getProductFamName()
									.equalsIgnoreCase("PPO")
							|| (!adminMethodValidationBO.getSpsName()
									.equalsIgnoreCase(previousSPS))) {
						val.getChildren().add(outputText3);
						sps.getChildren().add(outputText1);

					}
				} else {
					val.getChildren().add(outputText3);
					sps.getChildren().add(outputText1);

				}

				sps.getChildren().add(hiddenSps);

				ref.getChildren().add(inputText2);
				ref.getChildren().add(hiddenAdminDetails);
				ref.getChildren().add(outputText5);
				ref.getChildren().add(selectButton);
				ref.getChildren().add(viewButton);
				// Val is for the blank in the left side.One column
				val.getChildren().add(hiddenAdmin);

				HtmlOutputLabel empty1 = new HtmlOutputLabel();
				empty1.setFor("empty1" + j);
				empty1.setId("empty1"+RandomStringUtils.randomAlphanumeric(15));
				//empty1.setId("empty1" + j);

				HtmlOutputLabel empty2 = new HtmlOutputLabel();
				empty2.setFor("empty2" + j);
				empty2.setId("empty2"+RandomStringUtils.randomAlphanumeric(15));
				//empty2.setId("empty2" + j);

				HtmlOutputLabel empty3 = new HtmlOutputLabel();
				empty3.setFor("empty3" + j);
				empty3.setId("empty3"+RandomStringUtils.randomAlphanumeric(15));
				//empty3.setId("empty3" + j);

				HtmlOutputLabel empty4 = new HtmlOutputLabel();
				empty4.setFor("empty4" + j);
				empty4.setId("empty4"+RandomStringUtils.randomAlphanumeric(15));
				//empty4.setId("empty4" + j);

				HtmlOutputLabel empty5 = new HtmlOutputLabel();
				empty5.setFor("empty5" + j);
				empty5.setId("empty5"+RandomStringUtils.randomAlphanumeric(15));
				//empty5.setId("empty5" + j);

				HtmlOutputLabel empty6 = new HtmlOutputLabel();
				empty6.setFor("empty6" + j);
				empty6.setId("empty6"+RandomStringUtils.randomAlphanumeric(15));
				//empty6.setId("empty6" + j);

				/*
				 * The logic for inserting blank space in a row where there is only HMO and NO PPO.
				 * SO empty labels are added in place of PPO
				 * if isInsertBlankRightPanel is true --> insert three rows
				 * 
				 */

				if (isPOS && adminMethodValidationBO.getProductFamName()
							.equalsIgnoreCase("PPO")) {
						previousSPS = adminMethodValidationBO.getSpsName();
						isInsertBlankLeftPanel = true;
						if (isInsertBlankRightPanel) {
							isInsertBlankRightPanel = true;
							panelForContractValidation.getChildren()
									.add(empty1);
							panelForContractValidation.getChildren()
									.add(empty2);
							panelForContractValidation.getChildren()
									.add(empty3);

						}
					}
				

				/*
				 *  The logic for inserting blank space in a row where there is only PPO and HMO.
				 */

				if (isPOS
						&& adminMethodValidationBO.getProductFamName()
								.equalsIgnoreCase("HMO")) {
					isInsertBlankRightPanel = false;
					// This checks for same PPO and HMO . If so , no need of any blank panel
					if (adminMethodValidationBO.getSpsName()
							.equals(previousSPS)) {
						isInsertBlankLeftPanel = false;
					} else {

						if (isInsertBlankLeftPanel) {

							panelForContractValidation.getChildren()
									.add(empty4);
							panelForContractValidation.getChildren()
									.add(empty5);
							panelForContractValidation.getChildren()
									.add(empty6);
						}

					}
				}

				panelForContractValidation.getChildren().add(val);
				panelForContractValidation.getChildren().add(sps);

				/*
				 * No PPO for the corresponding HMO
				 * So there should be space after the SPS name and HMO .
				 * Inserting 3 blank labels
				 * Condition -- previous SPS shouldnt be the current SPS
				 */

				if (isPOS
						&& adminMethodValidationBO.getProductFamName()
								.equalsIgnoreCase("HMO")
						&& !adminMethodValidationBO.getSpsName().equals(
								previousSPS)) {
					panelForContractValidation.getChildren().add(empty1);
					panelForContractValidation.getChildren().add(empty2);
					panelForContractValidation.getChildren().add(empty3);
					
					// Set the  isInsertBlankLeftPanel false since already inserted spaces.
					
					isInsertBlankLeftPanel = false;
					previousSPS = "";
				}

				panelForContractValidation.getChildren().add(ref);

				//  panel.getChildren().add(val);

			}

			/*
			 * If the last value is for PPO , the HMO will be blank and unoccupied.
			 * The space is filled with empty space
			 */
			if (isPOS
					& adminMethodValidationBO.getProductFamName()
							.equalsIgnoreCase("PPO")) {
				HtmlOutputLabel emptyLast1 = new HtmlOutputLabel();
				emptyLast1.setFor("emptyLast1");
				emptyLast1.setId("emptyLast1"+RandomStringUtils.randomAlphanumeric(15));
				//emptyLast1.setId("emptyLast1");
				HtmlOutputLabel emptyLast2 = new HtmlOutputLabel();
				emptyLast2.setFor("emptyLast2");
				emptyLast2.setId("emptyLast2"+RandomStringUtils.randomAlphanumeric(15));
				//emptyLast1.setId("emptyLast2");
				HtmlOutputLabel emptyLast3 = new HtmlOutputLabel();
				emptyLast3.setFor("emptyLast3");
				emptyLast3.setId("emptyLast3"+RandomStringUtils.randomAlphanumeric(15));
				//emptyLast1.setId("emptyLast3");

				panelForContractValidation.getChildren().add(emptyLast1);
				panelForContractValidation.getChildren().add(emptyLast2);
				panelForContractValidation.getChildren().add(emptyLast3);

			}
		}

		return panelForContractValidation;

	}

	public HtmlPanelGrid getPanelForValidation() {
		List administrationList = this.getSPSList();

		/*
		 * if( !this.isValidationStatus()){ administrationList =
		 * getUpdatedListFromScreen(administrationList); }
		 */
		panelForValidation = new HtmlPanelGrid();

		if (null != administrationList && administrationList.size() > 0) {
			this.getDisplayPanel();
			panelForValidation.setWidth("100%");
			panelForValidation.setColumns(3);
			panelForValidation.setBorder(0);
			panelForValidation.setStyleClass("outputText");
			panelForValidation.setCellpadding("3");
			panelForValidation.setCellspacing("1");
			panelForValidation.setBgcolor("#cccccc");
			panelForValidation.setColumnClasses("column25px,column35px,column40px");
			hasValidationErrors = true;
			getHeaderPanelForValidation(panelForValidation);
		} else {

			//        	HtmlOutputText noAdminMethod = new HtmlOutputText();

			//        	noAdminMethod.setValue("No Admin Methods Found");
			//        	panelForValidation.getChildren().add(noAdminMethod);
		}

		//  List adminMethodList = this.getSPSList();
		int administrationId = 0;

		AdminMethodValidationBO adminMethodValidationBO = null;
		if (administrationList.size() > 0) {
			for (int i = 0, j = 0; i < administrationList.size(); i++, j++) {
				adminMethodValidationBO = (AdminMethodValidationBO) administrationList
						.get(i);

				if (administrationId != adminMethodValidationBO
						.getBenefitAdminSysId()) {

					HtmlOutputLabel AdministartionDesc = new HtmlOutputLabel();
					AdministartionDesc.setFor("benefitAdministration" + j);
					AdministartionDesc.setId("AdministartionDesc"+RandomStringUtils.randomAlphanumeric(15));
					//AdministartionDesc.setId("benefitAdministration" + j);

					HtmlOutputLabel blankLabelForSPSName = new HtmlOutputLabel();
					blankLabelForSPSName.setFor("blankLabelForSPSName" + j);
					blankLabelForSPSName.setId("blankLabelForSPSName"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForSPSName.setId("blankLabelForSPSName" + j);

					HtmlOutputLabel blankLabelForAM = new HtmlOutputLabel();
					blankLabelForAM.setFor("blankLabelForAM" + j);
					blankLabelForAM.setId("blankLabelForAM"+RandomStringUtils.randomAlphanumeric(15));
					//blankLabelForAM.setId("blankLabelForAM" + j);

					String startDate = adminMethodValidationBO
							.getEffectiveDate();
					String endDate = adminMethodValidationBO.getExpiryDate();
					String administration = startDate + " - " + endDate;

					HtmlOutputText outputText1 = new HtmlOutputText();
					outputText1.setId("administration" + j);
					outputText1.setValue(administration);

					AdministartionDesc.getChildren().add(outputText1);

					panelForValidation.getChildren().add(AdministartionDesc);
					panelForValidation.getChildren().add(blankLabelForSPSName);
					panelForValidation.getChildren().add(blankLabelForAM);
					//   panel.getChildren().add(val);
					// panel.getChildren().add(val);

					j++;
				}

				administrationId = adminMethodValidationBO
						.getBenefitAdminSysId();
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + j);
				outputText1.setValue(adminMethodValidationBO.getSpsName());

				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenSpsName" + j);
				hiddenSps.setValue("" + adminMethodValidationBO.getSpsId());

				ValueBinding valBindingForSpsName = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.spsNameMap[" + j
										+ "]}");
				hiddenSps.setValueBinding("value", valBindingForSpsName);

				//-----------

				HtmlInputHidden hiddenAdmin = new HtmlInputHidden();
				hiddenAdmin.setId("hiddenAdmin" + j);
				hiddenAdmin.setValue(""
						+ adminMethodValidationBO.getBenefitAdminSysId());

				ValueBinding valBindingForAdmin = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.benefitAdministrationMap["
										+ j + "]}");
				hiddenAdmin.setValueBinding("value", valBindingForAdmin);

				//-----------

				// HtmlInputTextarea inputText2 = new HtmlInputTextarea();
				HtmlInputText inputText2 = new HtmlInputText();
				inputText2.setId("adminMethod" + j);
				inputText2.setStyleClass("formInputFieldforDiv");
				String toolTipForDesc = adminMethodValidationBO
						.getAdminMethodDesc();
				inputText2.setTitle(toolTipForDesc);
				if (null == adminMethodValidationBO.getAdminMethodDesc()) {

					inputText2.setValue("");
				} else {
					inputText2.setValue(""
							+ adminMethodValidationBO.getAdminMethodDesc());
				}
				inputText2.setReadonly(true);
				inputText2.setOnmouseover("setTitle(" + j + ")");
				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenAdminMethodDetails" + j);
				if (null != adminMethodValidationBO.getAdminMethodDesc()
						&& 0 != adminMethodValidationBO.getAdminMethodId()) {
					hiddenAdminDetails.setValue(""
							+ adminMethodValidationBO.getAdminMethodDesc()
							+ "~" + adminMethodValidationBO.getAdminMethodId());
				} else {
					hiddenAdminDetails.setValue(" ");
				}

				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace" + j);
				outputText5.setValue(" ");

				ValueBinding valBindingForAdminMethod = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.adminMethodMap[" + j
										+ "]}");
				hiddenAdminDetails.setValueBinding("value",
						valBindingForAdminMethod);

				if ("product".equals(adminMethodValidationBO.getEntityType())) {
					adminMethodValidationBO
							.setEntityTypeForValidation("productValidation");
				}

				HtmlCommandButton selectButton = new HtmlCommandButton();
				selectButton.setId("selectButton" + j);
				selectButton.setStyle("border:0;");
				selectButton.setImage("../../images/select.gif");
				selectButton.setTitle("Select");
				selectButton
						.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
								+ Math.random()
								+ "&spsId="
								+ adminMethodValidationBO.getSpsId()
								+ "&spsName="
								+ adminMethodValidationBO.getSpsName()
								+ "&adminId="
								+ adminMethodValidationBO
										.getBenefitAdminSysId()
								+ "&stdbenId="
								+ adminMethodValidationBO.getBenefitSysId()
								+ "&entityType="
								+ adminMethodValidationBO.getEntityType()
								+ "&validation="
								+ "validation"
								+ "', 'adminMethodForm:adminMethod"
								+ j
								+ "','adminMethodForm:hiddenAdminMethodDetails"
								+ j + "',2,1);return false;");

				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton" + i);
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("getViewDetails('"
						+ adminMethodValidationBO.getSpsId() + "','"
						+ adminMethodValidationBO.getBenefitAdminSysId()
						+ "','" + adminMethodValidationBO.getBenefitSysId()
						+ "','" + adminMethodValidationBO.getEntityType()
						+ "','" + adminMethodValidationBO.getAdminMethodId()
						+ "'," + "'adminMethodForm:hiddenAdminMethodDetails"
						+ j + "'" + ");return false;");

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + j);
				sps.setId("spsnsame2"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + j);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + j);
				ref.setId("adminMethod2"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + j);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + j);
				val.setId("reference2"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + j);

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("administration" + j);
				outputText3.setValue("");

				sps.getChildren().add(outputText1);
				sps.getChildren().add(hiddenSps);

				ref.getChildren().add(inputText2);
				ref.getChildren().add(hiddenAdminDetails);
				ref.getChildren().add(outputText5);
				ref.getChildren().add(selectButton);
				ref.getChildren().add(viewButton);
				val.getChildren().add(outputText3);
				val.getChildren().add(hiddenAdmin);

				panelForValidation.getChildren().add(val);
				panelForValidation.getChildren().add(sps);
				panelForValidation.getChildren().add(ref);

				//  panel.getChildren().add(val);

			}
		}

		return panelForValidation;	
	}
	/*START AM1 CARS */
	
	/**
	 * @return Returns the panelForTierValidation.
	 */
	public HtmlPanelGrid getPanelForTierValidation() {

		Logger.logInfo("entered method getTierPanel");
		boolean isPOS = false;		
		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;
		
		Set renderedTier = new HashSet(0);	
		panelForTierValidation = new HtmlPanelGrid();
		panelForTierValidation.setColumns(1);
		panelForTierValidation.setWidth("100%");
		panelForTierValidation.setBorder(0);
		panelForTierValidation.setCellpadding("0");
		panelForTierValidation.setCellspacing("0");
		panelForTierValidation.setStyleClass("outputText");
		panelForTierValidation.setBgcolor("#cccccc");		
		StringBuffer rows = new StringBuffer();
		// setting values to benefit levels
		int size = 0;
		if (null != tierDefinitionsList) {
			sortTiers(tierDefinitionsList); 
		}
		HtmlPanelGrid tierDefPanel = null;

		HtmlPanelGrid tierCritPanel = null;
		
		HtmlPanelGrid tierHeaderPanel = null;

		HtmlPanelGrid tierLevelPanel = null;
		
		BenefitTierDefinition tierDefinition = null;

		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		// iterating to get the benefit levels
		if (null != tieredAdminMethodList && tieredAdminMethodList.size() > 0) 
		{
			AdminMethodValidationBO adminMethodValidationTieredBO = (AdminMethodValidationBO) tieredAdminMethodList.get(0);
			if (null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("CONTRACT")
			    	&& adminMethodValidationTieredBO.isPosProductFamily()) {
			    isPOS = true;
            } 
			String administration = adminMethodValidationTieredBO.getEffectiveDate()+ " - " +adminMethodValidationTieredBO.getExpiryDate();
			HtmlPanelGrid adminLine = getAdministrationLine(administration);
			adminLine.setWidth("100%");
			adminLine.setBorder(0);
			adminLine.setCellpadding("0");
			adminLine.setCellspacing("0");
			adminLine.setBgcolor("#cccccc");
			panelForTierValidation.getChildren().add(adminLine);
		}
		for (int l = 0; l < tierDefinitionsList.size(); l++)
		{
			HtmlPanelGrid grid1 = new HtmlPanelGrid();
			grid1.setBgcolor("#FFFFFF");
			tierPanel.getChildren().add(grid1);
			tierDefinition = (BenefitTierDefinition) tierDefinitionsList.get(l);			
			if(null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("CONTRACT")
			        && renderedTier.contains(new Integer(tierDefinition.getBenefitTierDefinitionSysId()))){		           
		        continue;
	        }	
			renderedTier.add(new Integer(tierDefinition.getBenefitTierDefinitionSysId()));
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");
			tierDefPanel.setBorder(0);
			tierDefPanel.setCellpadding("0");
			tierDefPanel.setCellspacing("0");
			tierDefPanel.setBgcolor("#cccccc");

			HtmlOutputLabel defLabel = new HtmlOutputLabel();
			defLabel.setId("defLabel9"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setStyleClass("dataTableHeader1");		
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			tierDef.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");
			
			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
			hidDefId.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");

			String tempKey = tierDefinition.getBenefitTierDefinitionSysId()+"";			
			defLabel.getChildren().add(tierDef);			
			panelForTierValidation.getChildren().add(defLabel);
         
			panelForTierValidation.getChildren().add(tierDefPanel);
		
			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) {
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();				
				tierCritPanel = new HtmlPanelGrid();						
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");

				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("0");
				//tierCritPanel.setStyle("height:100px;");
				tierCritPanel.setCellspacing("0");
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow");

				tierHeaderPanel = new HtmlPanelGrid();
				tierHeaderPanel.setCellpadding("0");
				tierHeaderPanel.setCellspacing("0");
				tierHeaderPanel.setColumns(2);
				tierHeaderPanel.setWidth("100%");
				tierHeaderPanel.setBgcolor("#FFFFFF");

				tierHeaderPanel.setStyleClass("headerPanel1");
				
				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel9"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:250px;");

				if (null != critList) {
					for (int k = 0; k < critList.size(); k++) {
						tierlabel.getChildren().add(dummylabel);
						tierCriteria = new BenefitTierCriteria();
						tierCriteria = (BenefitTierCriteria) critList.get(k);
						HtmlOutputText tierCrit = new HtmlOutputText();
						tierCrit.setStyle("color:blue");
						tierCrit.setValue(tierCriteria
								.getBenefitTierCriteriaName()+" : ");
						tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo);
						tierlabel.getChildren().add(tierCrit);
						HtmlOutputText critValueView = new HtmlOutputText();
						String critVal = tierCriteria.getBenefitTierCriteriaValue();						
						critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
						tierlabel.getChildren().add(critValueView);						
					}
				}
				tierHeaderPanel.getChildren().add(tierlabel);
				
				tierCritPanel.getChildren().add(tierHeaderPanel);
				
				int sizeOfLevelList = 0;
				tierLevelPanel = new HtmlPanelGrid();
                if(isPOS)
                {
                    tierLevelPanel.setColumns(4);
                    tierLevelPanel.setColumnClasses("column20px,column20px,column30px,column30px");
                }
                else
                {
                    tierLevelPanel.setColumns(3);
                    tierLevelPanel.setColumnClasses("column25px,column35px,column40px");
                }
				tierLevelPanel.setWidth("100%");
				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("1");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc");  
				tierLevelPanel.setRowClasses("dataTableEvenRow,dataTableOddRow");     
				boolean termsHaveAdminMethods = false;
				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					AdminMethodValidationBO adminMethodValidationBO = (AdminMethodValidationBO) tieredAdminMethodList.get(i);
					if (adminMethodValidationBO.getBenefitTierSysId() == tier.getBenefitTierSysId()){
					    //Including POS data if the contract is of POS type
					    if(null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("CONTRACT"))
					        prepareSPSAMMappingLinesForCheckin(i, tier, tierLevelPanel, isPOS);
					    else
					        setAdminMethodValuesToTierGridForCheckin(i, tier, tierLevelPanel); 
					    break;
					}																						
				}
			tierCritPanel.getChildren().add(tierLevelPanel);
			tierDefPanel.getChildren().add(tierCritPanel);
			}
		}
		return panelForTierValidation;
	}
	
	/**
	 * This method returns the invalid SPS List of the General Admin Methods and 
	 * Tiered Admin Methods for the selected Product.
	 *  
	 * @return administrationTierSPSList
	 */
	public List getSPSList() {
		int productId = Integer.parseInt((String) getSession().getAttribute(
				"AM_ENTITY_KEY"));
		/*   WLPRD00444546  changes starts */
		int benefitComponentId = Integer.parseInt((String) (null!=getSession()
				.getAttribute("AM_BC_KEY")?getSession()
						.getAttribute("AM_BC_KEY"):"0"));
		int benefitId = Integer.parseInt((String) (null!=getSession().getAttribute(
				"AM_BENEFIT")?getSession().getAttribute(
				"AM_BENEFIT"):"0"));
		/*   WLPRD00444546  changes ends */
		String entityType = ((String) getSession().getAttribute(
				"AM_ENTITY_TYPE"));

		AdminMethodValidationRequest adminMethodValidationRequest = new AdminMethodValidationRequest();
		adminMethodValidationRequest.setEntitySysId(productId);
		adminMethodValidationRequest.setBenefitComSysId(benefitComponentId);
		adminMethodValidationRequest.setBenefitSysId(benefitId);
		adminMethodValidationRequest.setEntityType(entityType);
		AdminMethodValidationResponse adminMethodValidationResponse = (AdminMethodValidationResponse) this
				.executeService(adminMethodValidationRequest);
		this.entityType = entityType;
		if (null != adminMethodValidationResponse) {
		    this.administrationSPSList = adminMethodValidationResponse.getResultList();
			if(null == adminMethodValidationResponse.getResultList() || adminMethodValidationResponse.getResultList().size() < 1)
			{			    
			    this.renderPanel = false;
			}
			if(null != adminMethodValidationResponse.getTieredAdminMethodList() && adminMethodValidationResponse.getTieredAdminMethodList().size() >0)
			{						
				setBenefitAdminMethodTierDataToSession(adminMethodValidationResponse);
			}else{
			    this.renderTierPanel=false;
			    if (null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("CONTRACT")){
					getContractSession().setTierDefinitionsList(null);
					getContractSession().setTierCriteriaDefinitionList(null);
					getContractSession().setTieredAdminMethodList(null);
				}
				else if(null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("PRODUCT")){	
					getProductSessionObject().setTierDefinitionsList(null);
					getProductSessionObject().setTierCriteriaDefinitionList(null);
					getProductSessionObject().setTieredAdminMethodList(null);
				}
			    
			}
		}		
		return administrationSPSList;
	}
	
	/**
	 * This method set the invalid Admin Method Tier data to the session.
	 * @param adminMethodValidationResponse
	 */
	private void setBenefitAdminMethodTierDataToSession(AdminMethodValidationResponse adminMethodValidationResponse) {
		if (null != adminMethodValidationResponse && null!= adminMethodValidationResponse.getBenefitDefinitionsList()&&
				!adminMethodValidationResponse.getBenefitDefinitionsList().isEmpty()){
			this.benefitDefinitionsList = adminMethodValidationResponse.getBenefitDefinitionsList();
			this.tieredAdminMethodList = adminMethodValidationResponse.getTieredAdminMethodList();
			if (null != adminMethodValidationResponse.getTieredAdminMethodList()&& 
			        !adminMethodValidationResponse.getTieredAdminMethodList().isEmpty()){			    
				if (null != adminMethodValidationResponse.getCriteriaList()&& !adminMethodValidationResponse.getCriteriaList().isEmpty()){
					List benefitTierDefinitionsList = BenefitTierUtil.getTieredList(adminMethodValidationResponse.getCriteriaList());
					int benefitTierDefinitionsListSize = benefitTierDefinitionsList.size();
					int tierAdminMethodListSize = adminMethodValidationResponse.getTieredAdminMethodList().size();
					//To remove the unwanted tier data from the list, verifying the tiered admin methods 
					for (int j = 0; j < benefitTierDefinitionsListSize; j++){
						BenefitTierDefinition tierDefinition = (BenefitTierDefinition) benefitTierDefinitionsList.get(j);
						List tierList = tierDefinition.getBenefitTiers();
						boolean hasInvalidCriteria = true;
						boolean invalidAdminMethodsExistsInTier = false;
						int tierListSize = tierList.size();
						BenefitTierDefinition tierTempDefinition = null;
						List tierTempList = new ArrayList(0);
						for (int k = 0; k < tierListSize; k++){
							BenefitTier tier = (BenefitTier) tierList.get(k);
							List invalidAdminMethods = new ArrayList(0);
							for (int i = 0; i < tierAdminMethodListSize; i++){
								AdminMethodValidationBO adminMethodValidationBO = (AdminMethodValidationBO) tieredAdminMethodList.get(i);
								if (adminMethodValidationBO.getBenefitTierSysId() == tier.getBenefitTierSysId()){
									invalidAdminMethods.add(adminMethodValidationBO);
									hasInvalidCriteria = false;
									invalidAdminMethodsExistsInTier = true;
								}
							}
							if(invalidAdminMethods.size()!=0){
								tier.setAdminMethods(invalidAdminMethods);
								tierTempList.add(tier);
							}							
						}
						if (tierTempList.size()!=0) {
							tierDefinition.setBenefitTiers(tierTempList);
							this.tierDefinitionsList.add(tierDefinition);
						}
					}					
					
				}
				if (null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("CONTRACT")){
					getContractSession().setTierDefinitionsList(benefitDefinitionsList);
					getContractSession().setTierCriteriaDefinitionList(tierDefinitionsList);
					getContractSession().setTieredAdminMethodList(tieredAdminMethodList);
				}else if(null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("PRODUCT")){	
					getProductSessionObject().setTierDefinitionsList(benefitDefinitionsList);
					getProductSessionObject().setTierCriteriaDefinitionList(tierDefinitionsList);
					getProductSessionObject().setTieredAdminMethodList(tieredAdminMethodList);
				}				
			}else{
				//Remove tier data from session	
			    if (null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("CONTRACT")) {
					getContractSession().setTierDefinitionsList(null);
					getContractSession().setTierCriteriaDefinitionList(null);
					getContractSession().setTieredAdminMethodList(null);
				}else if(null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("PRODUCT")){	
					getProductSessionObject().setTierDefinitionsList(null);
					getProductSessionObject().setTierCriteriaDefinitionList(null);
					getProductSessionObject().setTieredAdminMethodList(null);
				}
			}

		}
	}
	/*END AM1 CARS */

	/**
	 * @param overrideAdminMethodRequest
	 */
	private void updateAMVForContract(OverrideAdminMethodRequest req) {
		if (req != null && req.getAdminMethodsId() != null) {
			List sysids = req.getAdminMethodsId();
			List oldIds = loadOldState();
			List spsID = req.getSpsId();
			if (null != sysids && !sysids.isEmpty() && null != oldIds
					&& !oldIds.isEmpty() && sysids.size() == oldIds.size()) {
				for (int i = 0; i < oldIds.size(); i++) {
					if (!sysids.get(i).equals(oldIds.get(i))) {
						req.setSpsChanged(true);
						if (req.getChangedIds() == null) {
							req.setChangedIds(new ArrayList());
						}
						req.getChangedIds().add(
								new Integer((String) spsID.get(i)));
						req.setBenefitCompName((String) getSession()
								.getAttribute("BENEFIT_COMP_NAME"));
					}
				}
			}
		}
	}

	/**
	 * Returns the adminMethodLocateCriteria
	 * 
	 * @return AdminMethodLocateCriteria adminMethodLocateCriteria.
	 */

	public AdminMethodLocateCriteria getAdminMethodLocateCriteria() {
		adminMethodLocateCriteria.setEntityId(getBenefitSessionObject()
				.getStandardBenefitParentKey());
		return adminMethodLocateCriteria;
	}

	/**
	 * Sets the adminMethodLocateCriteria
	 * 
	 * @param adminMethodLocateCriteria.
	 */

	public void setAdminMethodLocateCriteria(
			AdminMethodLocateCriteria adminMethodLocateCriteria) {
		this.adminMethodLocateCriteria = adminMethodLocateCriteria;
	}

	/**
	 * Returns the adminMethodMap
	 * 
	 * @return Map adminMethodMap.
	 */

	public Map getAdminMethodMap() {
		return adminMethodMap;
	}

	/**
	 * Sets the adminMethodMap
	 * 
	 * @param adminMethodMap.
	 */

	public void setAdminMethodMap(Map adminMethodMap) {
		this.adminMethodMap = adminMethodMap;
	}

	/**
	 * Returns the benefitSessionObject
	 * 
	 * @return StandardBenefitSessionObject benefitSessionObject.
	 */

	public StandardBenefitSessionObject getBenefitSessionObject() {
		StandardBenefitSessionObject benefitSessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);

		if (benefitSessionObject == null) {
			benefitSessionObject = new StandardBenefitSessionObject();
			getSession().setAttribute(
					WebConstants.STANDARD_BENEFIT_SESSION_KEY,
					benefitSessionObject);
		}
		StandardBenefitSessionObject sessionObject = benefitSessionObject;
		return sessionObject;
	}

	/**
	 * Returns the benefitSessionObject
	 * 
	 * @return StandardBenefitSessionObject benefitSessionObject.
	 */

	public BenefitComponentSessionObject getBenefitCompSessionObject() {
		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
				.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);

		if (benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(
					WebConstants.BENEFIT_COMPONENT_SESSION_KEY,
					benefitComponentSessionObject);
		}
		BenefitComponentSessionObject sessionObject = benefitComponentSessionObject;
		return sessionObject;
	}

	/**
	 * Sets the benefitSessionObject
	 * 
	 * @param benefitSessionObject.
	 */

	public void setBenefitSessionObject(
			StandardBenefitSessionObject benefitSessionObject) {
		this.benefitSessionObject = benefitSessionObject;
	}

	/**
	 * Returns the renderPanel
	 * 
	 * @return boolean renderPanel.
	 */

	public boolean isRenderPanel() {
		return renderPanel;
	}

	/**
	 * Sets the renderPanel
	 * 
	 * @param renderPanel.
	 */

	public void setRenderPanel(boolean renderPanel) {
		this.renderPanel = renderPanel;
	}

	/**
	 * Sets the panel
	 * 
	 * @param panel.
	 */

	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	/**
	 * @return Returns the productStructureSessionObject.
	 */
	public ProductStructureSessionObject getProductStructureSessionObject() {
		ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
				.getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
		if (productStructureSessionObject == null) {
			productStructureSessionObject = new ProductStructureSessionObject();
			getSession().setAttribute(
					WebConstants.PRODUCT_STRUCTURE_SESSION_KEY,
					productStructureSessionObject);
		}
		ProductStructureSessionObject sessionObject = productStructureSessionObject;
		return sessionObject;

	}

	/**
	 * @param productStructureSessionObject
	 *            The productStructureSessionObject to set.
	 */
	public void setProductStructureSessionObject(
			ProductStructureSessionObject productStructureSessionObject) {
		this.productStructureSessionObject = productStructureSessionObject;
	}

	/**
	 * @return Returns the productSessionObject.
	 */
	public ProductSessionObject getProductSessionObject() {
		ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
				.getAttribute(PRODUCT_SESSION_KEY);
		if (productSessionObject == null) {
			productSessionObject = new ProductSessionObject();
			getSession()
					.setAttribute(PRODUCT_SESSION_KEY, productSessionObject);
		}
		ProductSessionObject sessionObject = productSessionObject;
		return sessionObject;
	}

	/**
	 * @param productSessionObject
	 *            The productSessionObject to set.
	 */
	public void setProductSessionObject(
			ProductSessionObject productSessionObject) {
		this.productSessionObject = productSessionObject;
	}

	/**
	 * @param adminMethodsList
	 *            The adminMethodsList to set.
	 */
	public void setAdminMethodsList(List adminMethodsList) {
		this.adminMethodsList = adminMethodsList;
	}

	/**
	 * Returns the spsNameMap
	 * 
	 * @return Map spsNameMap.
	 */

	public Map getSpsNameMap() {
		return spsNameMap;
	}

	/**
	 * Sets the spsNameMap
	 * 
	 * @param spsNameMap.
	 */

	public void setSpsNameMap(Map spsNameMap) {
		this.spsNameMap = spsNameMap;
	}

	/**
	 * To set the values to backing bean for view
	 * 
	 * @param retrieveBenefitDefenitionResponse
	 * @return void
	 */
	private void setValuesToBackingBeanForView(
			SaveAdminMethodResponse saveAdminMethodResponse) {
		if (saveAdminMethodResponse.isSuccess()) {
			getPanel();
		}

	}

	//WAS 7.0 Changes - Binding variable valuesFromSessionForBenefit modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	/**
	 * Returns the valuesFromSessionForBenefit
	 * 
	 * @return HtmlInputHidden valuesFromSessionForBenefit.
	 */

	public HtmlInputHidden getValuesFromSessionForBenefit() {
		if (null != getSession().getAttribute(
				WebConstants.SESSION_BNFT_ADMIN_ID)
				&& !getSession().getAttribute(
						WebConstants.SESSION_BNFT_ADMIN_ID).equals("")) {
			this.adminId = Integer.parseInt((String) getSession().getAttribute(
					WebConstants.SESSION_BNFT_ADMIN_ID));
			adminMethodLocateCriteria.setAdministrationId(this.adminId);
		}
		this.entityType = WebConstants.BENEFIT_TYPE;
		if ((null != getSession().getAttribute(
				WebConstants.SESSION_BNFT_DEFN_ID) && !getSession()
				.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))) {
			this.stdbenId = Integer.parseInt(String.valueOf(getSession()
					.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)));
		}
		adminMethodLocateCriteria.setMode(benefitSessionObject
													.getStandardBenefitMode());
		this.breadCrumpName = benefitSessionObject.getStandardBenefitName();
		String mode = adminMethodLocateCriteria.getMode();
		if (mode!=null && mode.equals(WebConstants.BENEFIT_VIEW)) {
			this.adminMethodsList = retrieveBenefitAdminMethodBOList();
			this.setBreadCrumbText("Product Configuration >> Benefit ("
					+ this.breadCrumpName + ") >> View");
			
			valuesFromSessionForBenefit.setValue("benefitAdminMethodView");
	        return valuesFromSessionForBenefit;  
			//return "benefitAdminMethodView";

		} else {
				this.adminMethodsList = retrieveBenefitAdminMethodBOList();
			this.setBreadCrumbText("Product Configuration >> Benefit ("
					+ this.breadCrumpName + ") >> Edit");
			valuesFromSessionForBenefit.setValue("success");
	        return valuesFromSessionForBenefit;  
			//return "success";
		}
	}

	/**
	 * Sets the valuesFromSessionForBenefit
	 * 
	 * @param valuesFromSessionForBenefit.
	 */

	public void setValuesFromSessionForBenefit(
			HtmlInputHidden valuesFromSessionForBenefit) {
		this.valuesFromSessionForBenefit = valuesFromSessionForBenefit;
	}

	/**
	 * @return Returns the valuesFromSessionForBenefitComp.
	 */
	//WAS 7.0 Changes - Binding variable ValuesFromSessionForBenefitComp modified to HtmlInputHidden instead of String, Since getter method call failed,

	// while the variable defined in String in WAS 7.0
	
	public HtmlInputHidden getValuesFromSessionForBenefitComp() {
		if ((null != getSession().getAttribute(
				WebConstants.BENEFIT_COMPONENT_SESSION_KEY) && !getSession()
				.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY)
				.equals(""))) {
			BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
					.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
			adminMethodLocateCriteria.setEntityId(benefitComponentSessionObject
					.getBenefitComponentId());
			this.breadCrumpName = benefitComponentSessionObject
					.getBenefitComponentName();
			adminMethodLocateCriteria.setEntityType(WebConstants.BENEFIT_COMP);
			adminMethodLocateCriteria
					.setBenefitComponentId(benefitComponentSessionObject
							.getBenefitComponentId());
			adminMethodLocateCriteria.setMode(benefitComponentSessionObject
					.getBenefitComponentMode());
			adminMethodLocateCriteria
					.setBenefitComponentName(benefitComponentSessionObject
							.getBenefitComponentName());
			if ((null != getSession().getAttribute("SESSION_BNFT_ADMIN_ID") && !getSession()
					.getAttribute("SESSION_BNFT_ADMIN_ID").equals(""))) {
				this.adminId = Integer.parseInt((String) getSession()
						.getAttribute("SESSION_BNFT_ADMIN_ID"));
				adminMethodLocateCriteria.setAdministrationId(this.adminId);
			}
			this.componentType = benefitComponentSessionObject
					.getBcComponentType();
			String mode = adminMethodLocateCriteria.getMode();
			this.stdbenId = Integer.parseInt(String.valueOf(getSession()
					.getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)));
			this.entityType = WebConstants.BENEFIT_COMP;
			String nodeClicked = (String) getSession().getAttribute(
					SESSION_NODE_TYPE);
			//String compName =
			// adminMethodLocateCriteria.getBenefitComponentName();
			if (nodeClicked.equals(BENEFIT_ADMIN)
					&& mode.equals(WebConstants.BENEFIT_VIEW)) {
				this.adminMethodsList = retrieveOverrideAdminMethodBOList();
				this
						.setBreadCrumbText("Product Configuration >> BenefitComponent ("
								+ this.breadCrumpName + ") >> View");
				valuesFromSessionForBenefitComp.setValue("benefitComponentView");
				return valuesFromSessionForBenefitComp;
			} else {
				this.adminMethodsList = retrieveOverrideAdminMethodBOList();
				this
						.setBreadCrumbText("Product Configuration >> BenefitComponent ("
								+ this.breadCrumpName + ") >> Edit");
				valuesFromSessionForBenefitComp.setValue("success");
				return valuesFromSessionForBenefitComp;
			}
		}
		valuesFromSessionForBenefitComp.setValue("");
		return valuesFromSessionForBenefitComp;
		
	}

	/**
	 * @param valuesFromSessionForBenefitComp
	 *            The valuesFromSessionForBenefitComp to set.
	 */
	public void setValuesFromSessionForBenefitComp(
			HtmlInputHidden valuesFromSessionForBenefitComp) {
		this.valuesFromSessionForBenefitComp = valuesFromSessionForBenefitComp;
	}

	/**
	 * This method will be called while loading the page.It genearates criteria object inorder to pass information like entity id,benefit
	 * component id,benefit id etc to fetch information of  admin methods form database.																	 
	 */
	//WAS 7.0 Changes - Binding variable valuesFromSessionForProd modified to HtmlInputHidden instead of String, Since getter method call failed,

	// while the variable defined in String in WAS 7.0
	public HtmlInputHidden getValuesFromSessionForProd() {
		if (!(null == getSession().getAttribute(PRODUCT_SESSION_KEY) && getSession().getAttribute(PRODUCT_SESSION_KEY).equals(""))) {
			ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(PRODUCT_SESSION_KEY);
			if (!(null == getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID) && getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))) {
				this.stdbenId = Integer.parseInt(String.valueOf(getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)));
				adminMethodLocateCriteria.setBenefitDefenitionId(stdbenId); //CARS:AM2
			}
			this.entityType = WebConstants.PRODUCT;

			adminMethodLocateCriteria.setEntityId(getProductSessionObject().getProductKeyObject().getProductId());
			if (!(null == getSession().getAttribute(BENEFIT_COMP_KEY) && getSession().getAttribute(BENEFIT_COMP_KEY).equals(""))) {
				adminMethodLocateCriteria.setBenefitComponentId(Integer.parseInt(getSession().getAttribute(BENEFIT_COMP_KEY).toString()));
			}
			adminMethodLocateCriteria.setEntityType(PRODUCT_SESSION_KEY);
			this.breadCrumpName = productSessionObject.getProductKeyObject().getProductName();
			String mode = new Integer(getProductSessionObject().getMode()).toString();
			adminMethodLocateCriteria.setMode(mode);

			adminMethodLocateCriteria
					.setBenefitComponentName(getProductSessionObject()
							.getProductKeyObject().getProductName());
			if ((null != getSession().getAttribute("ADMIN_KEY") && !getSession()
					.getAttribute("ADMIN_KEY").equals(""))) {
				this.adminId = Integer.parseInt((String) getSession()
						.getAttribute("ADMIN_KEY"));

				adminMethodLocateCriteria.setAdministrationId(this.adminId);
			}

			this.componentType = productSessionObject.getProductKeyObject()
					.getProductType();
			if ((null != getSession().getAttribute("PRODUCT_NODE_TYPE") && !getSession()
					.getAttribute("PRODUCT_NODE_TYPE").equals(""))) {
				String nodeClicked = (String) getSession().getAttribute(
						"PRODUCT_NODE_TYPE");
				if (nodeClicked.equals("Benefit-Administration")
						&& mode.equals(WebConstants.ACTION_BENEFIT)) {
					this.adminMethodsList = retrieveOverrideAdminMethodBOList();
					this.setBreadCrumbText("Product Configuration >> Product ("+ this.breadCrumpName + ") >> View");
					valuesFromSessionForProd.setValue("prodAdminMethodView");
					return valuesFromSessionForProd;
					
				} else {
					List messages = (List) getRequest()
							.getAttribute("messages");
					this.adminMethodsList = retrieveOverrideAdminMethodBOList();
					
					this.setBreadCrumbText("Product Configuration >> Product ("	+ this.breadCrumpName + ") >> Edit");
					if (messages != null && messages.size() > 0)
						addAllMessagesToRequest(messages);
					valuesFromSessionForProd.setValue("prodadminmethod");
					return valuesFromSessionForProd;

				}
			}
		}
		valuesFromSessionForProd.setValue("");
		return valuesFromSessionForProd;
	}

	/**
	 * @param valuesFromSessionForProd
	 *            The valuesFromSessionForProd to set.
	 */
	public void setValuesFromSessionForProd(HtmlInputHidden valuesFromSessionForProd) {
		this.valuesFromSessionForProd = valuesFromSessionForProd;
	}

	/**
	 * @return Returns the valuesFromSessionForProdStruc.
	 */
	//WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/**
	 * 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden  getValuesFromSessionForProdStruc() {
		if ((null != getSession().getAttribute(PRODUCT_STRUCTURE_SESSION_KEY) && !getSession()
				.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY).equals(""))) {
			ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
					.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);
			if (null != getSession().getAttribute(
					WebConstants.SESSION_BNFT_ADMIN_ID)
					&& !getSession().getAttribute(
							WebConstants.SESSION_BNFT_ADMIN_ID).equals("")) {
				adminMethodLocateCriteria
						.setEntityId(getProductStructureSessionObject().getId());
				adminMethodLocateCriteria.setMode(productStructureSessionObject
						.getAction());
				adminMethodLocateCriteria
						.setBenefitComponentName(productStructureSessionObject
								.getName());
				if (null != getSession().getAttribute(BENEFIT_COMP_KEY)
						&& !getSession().getAttribute(BENEFIT_COMP_KEY).equals(
								"")) {
					adminMethodLocateCriteria.setBenefitComponentId(Integer
							.parseInt((String) getSession().getAttribute(
									BENEFIT_COMP_KEY)));
				}
				this.entityType = PROD_STRUCTURE;
				String id = String.valueOf(getSession().getAttribute(
						WebConstants.SESSION_BNFT_DEFN_ID));
				String[] idarray = id.split("~");
				this.stdbenId = Integer.parseInt(idarray[0]);
				adminMethodLocateCriteria
						.setEntityType(WebConstants.PROD_STRUCT);
				this.breadCrumpName = productStructureSessionObject.getName();
				this.adminId = Integer.parseInt((String) getSession()
						.getAttribute(WebConstants.SESSION_BNFT_ADMIN_ID));
				adminMethodLocateCriteria.setAdministrationId(this.adminId);
				this.componentType = productStructureSessionObject
						.getStructureType();
			}
			if ((null != getSession().getAttribute("SESSION_NODE_TYPE_COMP") && !getSession()
					.getAttribute("SESSION_NODE_TYPE_COMP").equals(""))) {
				String nodeClicked = (String) getSession().getAttribute(
						"SESSION_NODE_TYPE_COMP");
				String mode = adminMethodLocateCriteria.getMode();
				if (nodeClicked.equals(BENEFIT_DATE)
						&& mode.equals(WebConstants.VIEW)) {
					this.adminMethodsList = retrieveOverrideAdminMethodBOList();
					this
							.setBreadCrumbText("Product Configuration >> Product Structure ("
									+ this.breadCrumpName + ") >> View");
					//return "prodStructureAdminMethodView";
					valuesFromSessionForProdStruc.setValue("prodStructureAdminMethodView");
			        return valuesFromSessionForProdStruc;  
				} else {
					this.adminMethodsList = retrieveOverrideAdminMethodBOList();
					this
							.setBreadCrumbText("Product Configuration >> Product Structure ("
									+ this.breadCrumpName + ") >> Edit");
					//return "prodstradminmethod";
					valuesFromSessionForProdStruc.setValue("prodstradminmethod");
			        return valuesFromSessionForProdStruc; 
				}
			}
		}
		//return "";
		valuesFromSessionForProdStruc.setValue("");
        return valuesFromSessionForProdStruc; 
	}

	/**
	 * @param valuesFromSessionForProdStruc
	 *            The valuesFromSessionForProdStruc to set.
	 */
	public void setValuesFromSessionForProdStruc(
			HtmlInputHidden  valuesFromSessionForProdStruc) {
		this.valuesFromSessionForProdStruc = valuesFromSessionForProdStruc;
	}

	/**
	 * Returns the adminMethodsList
	 * 
	 * @return List adminMethodsList.
	 */

	public List getAdminMethodsList() {
		if (null != adminMethodsList && !adminMethodsList.isEmpty()) {
			return adminMethodsList;
		} else {
			this.adminMethodsList = null;
			return adminMethodsList;
		}

	}

	/**
	 * @return Returns the breadCrumbText.
	 */
	public String getBreadCrumbText() {
		loadForProductValidation();
		return breadCrumbText;
	}

	/**
	 * @param breadCrumbText
	 *            The breadCrumbText to set.
	 */
	public void setBreadCrumbText(String breadCrumbText) {
		this.breadCrumbText = breadCrumbText;
		getRequest().setAttribute("breadCrumbText", breadCrumbText);
	}

	/**
	 * @return Returns the breadCrumpName.
	 */
	public String getBreadCrumpName() {
		return breadCrumpName;
	}

	/**
	 * @param breadCrumpName
	 *            The breadCrumpName to set.
	 */
	public void setBreadCrumpName(String breadCrumpName) {
		this.breadCrumpName = breadCrumpName;
	}

	/**
	 * Sets the panelForOverrideView
	 * 
	 * @param panelForOverrideView.
	 */

	public void setPanelForOverrideView(HtmlPanelGrid panelForOverrideView) {
		this.panelForOverrideView = panelForOverrideView;
	}

	/**
	 * Sets the panelView
	 * 
	 * @param panelView.
	 */

	public void setPanelView(HtmlPanelGrid panelView) {
		this.panelView = panelView;
	}

	/**
	 * @return loadContractPageForPrint
	 * 
	 * Returns the loadContractPageForPrint.
	 */
	public String getLoadContractPageForPrint() {
		this.getLoadPageForPrint(WebConstants.CONTRACT_PRODUCT_ADMIN);
		return loadContractPageForPrint;
	}

	/**
	 * @param loadContractPageForPrint
	 * 
	 * Sets the loadContractPageForPrint.
	 */
	public void setLoadContractPageForPrint(String loadContractPageForPrint) {
		this.loadContractPageForPrint = loadContractPageForPrint;
	}

	/**
	 * @return loadBenefitCompPageForPrint
	 * 
	 * Returns the loadBenefitCompPageForPrint.
	 */
	public String getLoadBenefitCompPageForPrint() {
		this.getLoadPageForPrint(WebConstants.BENEFIT_COMP);
		return loadBenefitCompPageForPrint;
	}

	/**
	 * @param loadBenefitCompPageForPrint
	 * 
	 * Sets the loadBenefitCompPageForPrint.
	 */
	public void setLoadBenefitCompPageForPrint(
			String loadBenefitCompPageForPrint) {
		this.loadBenefitCompPageForPrint = loadBenefitCompPageForPrint;
	}

	/**
	 * @return loadProductPageForPrint
	 * 
	 * Returns the loadProductPageForPrint.
	 */
	public String getLoadProductPageForPrint() {
		this.getLoadPageForPrint(WebConstants.PRODUCT);
		return loadProductPageForPrint;
	}

	/**
	 * @param loadProductPageForPrint
	 * 
	 * Sets the loadProductPageForPrint.
	 */
	public void setLoadProductPageForPrint(String loadProductPageForPrint) {
		this.loadProductPageForPrint = loadProductPageForPrint;
	}

	/**
	 * @return loadProductStructPageForPrint
	 * 
	 * Returns the loadProductStructPageForPrint.
	 */
	public String getLoadProductStructPageForPrint() {
		this.getLoadPageForPrint(PROD_STRUCT);
		return loadProductStructPageForPrint;
	}

	/**
	 * @param loadProductStructPageForPrint
	 * 
	 * Sets the loadProductStructPageForPrint.
	 */
	public void setLoadProductStructPageForPrint(
			String loadProductStructPageForPrint) {
		this.loadProductStructPageForPrint = loadProductStructPageForPrint;
	}

	/**
	 * @return loadBenefitPageForPrint
	 * 
	 * Returns the loadBenefitPageForPrint.
	 */
	public String getLoadBenefitPageForPrint() {
		this.getLoadPageForPrint(STANDARD_BENEFIT);
		return loadBenefitPageForPrint;
	}

	/**
	 * @param loadBenefitPageForPrint
	 * 
	 * Sets the loadBenefitPageForPrint.
	 */
	public void setLoadBenefitPageForPrint(String loadBenefitPageForPrint) {
		this.loadBenefitPageForPrint = loadBenefitPageForPrint;
	}

	/**
	 * @return loadPageForPrint
	 * 
	 * Returns the loadPageForPrint.
	 */
	public void getLoadPageForPrint(String printPage) {
		if (WebConstants.CONTRACT_PRODUCT_ADMIN.equals(printPage)) {
			setPrintMode(true);
			this.getValuesFromSessionForContract();			
		} else if (WebConstants.BENEFIT_COMP.equals(printPage)) {
			this.getValuesFromSessionForBenefitComp();
		} else if (PROD_STRUCT.equals(printPage)) {
			this.getValuesFromSessionForProdStruc();
		} else if (WebConstants.PRODUCT.equals(printPage)) {
			if(submitFlag){
			this.getValuesFromSessionForProd();
			}
		} else if (STANDARD_BENEFIT.equals(printPage)) {
			this.getValuesFromSessionForBenefit();
		}
	}

	/**
	 * Returns the adminId
	 * 
	 * @return int adminId.
	 */

	public int getAdminId() {
		return adminId;
	}

	/**
	 * Sets the adminId
	 * 
	 * @param adminId.
	 */

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * Returns the componentType
	 * 
	 * @return String componentType.
	 */

	public String getComponentType() {
		return componentType;
	}

	/**
	 * Sets the componentType
	 * 
	 * @param componentType.
	 */

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	/**
	 * Returns the typeFlag
	 * 
	 * @return boolean typeFlag.
	 */

	public boolean isTypeFlag() {
		if ((null != this.componentType)
				&& (this.componentType.equals(WebConstants.MANDATE_TYPE)))
			return false;
		else {
			return true;
		}
	}

	/**
	 * @return Returns the benefitAdministrationMap.
	 */
	public Map getBenefitAdministrationMap() {
		return benefitAdministrationMap;
	}

	/**
	 * @param benefitAdministrationMap
	 *            The benefitAdministrationMap to set.
	 */
	public void setBenefitAdministrationMap(Map benefitAdministrationMap) {
		this.benefitAdministrationMap = benefitAdministrationMap;
	}

	/**
	 * Sets the typeFlag
	 * 
	 * @param typeFlag.
	 */

	public void setTypeFlag(boolean typeFlag) {
		this.typeFlag = typeFlag;
	}

	/**
	 * @return Returns the adminMethodState.
	 */
	public String getAdminMethodState() {
		return adminMethodState;
	}

	/**
	 * @param adminMethodState
	 *            The adminMethodState to set.
	 */
	public void setAdminMethodState(String adminMethodState) {
		this.adminMethodState = adminMethodState;
	}

	/**
	 * @return Returns the dummyHiddenVarForWaitPage.
	 */
	public String getDummyHiddenVarForWaitPage() {
		return dummyHiddenVarForWaitPage;
	}

	/**
	 * @param dummyHiddenVarForWaitPage
	 *            The dummyHiddenVarForWaitPage to set.
	 */
	public void setDummyHiddenVarForWaitPage(String dummyHiddenVarForWaitPage) {
		this.dummyHiddenVarForWaitPage = dummyHiddenVarForWaitPage;
	}

	/**
	 * @param panelForValidation
	 *            The panelForValidation to set.
	 */
	public void setPanelForValidation(HtmlPanelGrid panelForValidation) {
		this.panelForValidation = panelForValidation;
	}

	/**
	 * @param panelForValidation
	 *            The panelForValidation to set.
	 */
	public void setPanelForContractValidation(HtmlPanelGrid panelForValidation) {
		this.panelForContractValidation = panelForValidation;
	}

	/**
	 * @return Returns the validationStatus.
	 */
	public boolean isValidationStatus() {
		return validationStatus;
	}

	/**
	 * @param validationStatus
	 *            The validationStatus to set.
	 */
	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
	}

	/**
	 * @return Returns the bcName.
	 */
	public String getBcName() {
		return bcName;
	}

	/**
	 * @param bcName
	 *            The bcName to set.
	 */
	public void setBcName(String bcName) {
		this.bcName = bcName;
	}

	/**
	 * @return Returns the crrntlyPrcssngBCmpnt.
	 */
	public String getCrrntlyPrcssngBCmpnt() {
		return crrntlyPrcssngBCmpnt;
	}

	/**
	 * @param crrntlyPrcssngBCmpnt
	 *            The crrntlyPrcssngBCmpnt to set.
	 */
	public void setCrrntlyPrcssngBCmpnt(String crrntlyPrcssngBCmpnt) {
		this.crrntlyPrcssngBCmpnt = crrntlyPrcssngBCmpnt;
	}

	/**
	 * @return Returns the hasValidationErrors.
	 */
	public boolean isHasValidationErrors() {
		return hasValidationErrors;
	}

	/**
	 * @param hasValidationErrors
	 *            The hasValidationErrors to set.
	 */
	public void setHasValidationErrors(boolean hasValidationErrors) {
		this.hasValidationErrors = hasValidationErrors;
	}

	/**
	 * @return Returns the adminMethodValidationBreadCrumb.
	 */
	public String getAdminMethodValidationBreadCrumb() {
		String entityType = getSession().getAttribute("AM_ENTITY_TYPE")
				.toString();
		String entityName = getSession().getAttribute("AM_ENTITY_NAME")
				.toString();
		if (entityType != null && entityType.length() > 0)
			entityType = ("" + entityType.charAt(0)).toUpperCase()
					+ entityType.substring(1);
		this.setBreadCrumbText("Product Configuration >> " + entityType + " ("
				+ entityName + ") >> Edit(Admin Process Validation Errors)");
		return entityName;
	}

	/**
	 * @param adminMethodValidationBreadCrumb
	 *            The adminMethodValidationBreadCrumb to set.
	 */
	public void setAdminMethodValidationBreadCrumb(
			String adminMethodValidationBreadCrumb) {
		this.adminMethodValidationBreadCrumb = adminMethodValidationBreadCrumb;
	}

	public String loadNextPage() {
		getRequest().setAttribute("clearScrollTop", "true");
		return "Sucess";
	}

	public String loadNextPageContract() {
		getRequest().setAttribute("clearScrollTop", "true");
		return "Sucess";
	}	
	//CARS:AM2:START
	
	public HtmlPanelGrid getContractDisplayPanel() {
		HtmlPanelGrid displayPanel = new HtmlPanelGrid();

		if (this.renderPanel) {

			displayPanel.setCellpadding("2");
			displayPanel.setCellspacing("0");
			displayPanel.setWidth("100%");

			displayPanel.setBorder(0);
			displayPanel.setStyle("outputText");
			displayPanel.setStyleClass("dataTableHeader");
			displayPanel.setBgcolor("#cccccc");

			List adminMethodList = this.getAdminMethodLists();
			if (adminMethodList.size() > 0) {

				AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) adminMethodList
						.get(0);
				//This flag determines the heading , and number of columns in
				// the Header panel.
				boolean isPOS = adminMethodOverrideBO1.isPosProductFamily();

				HtmlOutputText outputText1 = new HtmlOutputText();
				HtmlOutputText outputText2 = new HtmlOutputText();
				HtmlOutputText outputText3 = new HtmlOutputText();
				if (isPOS) {

					displayPanel.setColumns(5);
					outputText1.setValue("Processing Methods");
					outputText2.setValue("PPO Admin Method");
					outputText3.setValue("HMO Admin Method");
					displayPanel.getChildren().add(outputText1);
					displayPanel.getChildren().add(outputText2);
					displayPanel.getChildren().add(outputText3);

				} else {
					displayPanel.setColumns(2);
					outputText1.setValue("Processing Methods");
					displayPanel.getChildren().add(outputText1);
					if (adminMethodOverrideBO1.getProductFamily()
							.equalsIgnoreCase("HMO")) {

						outputText2.setValue("HMO Admin Method");
					} else {
						outputText2.setValue("PPO Admin Method");
					}
					displayPanel.getChildren().add(outputText2);
				}
				
				HtmlCommandButton selectButton = new HtmlCommandButton();
				selectButton.setImage("../../images/select.gif");
				selectButton.setTitle("Select");
				selectButton.setOnclick("window.open('../popups/adminMethodViewAllPopup.jsp'+getUrl());return false;");

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setId("ref9"+RandomStringUtils.randomAlphanumeric(15));
				//ref.getChildren().add(selectButton);			
				
				
//				displayPanel.getChildren().add(outputText1);
	//			displayPanel.getChildren().add(outputText2);
		//		displayPanel.getChildren().add(outputText3);
			//	displayPanel.getChildren().add(ref);
				//displayPanel.getChildren().add(selectButton);
				
				
			}
		}
		return displayPanel;
	}

	/**
	 * @param contractDisplayPanel
	 *            The contractDisplayPanel to set.
	 */
	public void setContractDisplayPanel(HtmlPanelGrid contractDisplayPanel) {
		this.contractDisplayPanel = contractDisplayPanel;
	}

	/**
	 * @param contractPanelForOverride
	 *            The contractPanelForOverride to set.
	 */
	public void setContractPanelForOverride(
			HtmlPanelGrid contractPanelForOverride) {
		this.contractPanelForOverride = contractPanelForOverride;
	}

	public HtmlPanelGrid getContractPanelForOverride() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");

		List adminMethodList = this.getAdminMethodLists();
		storeAdminMethodStates(adminMethodList);				
		int addProcessingMethod = 0;
		AdminMethodOverrideBO adminMethodOverrideBO = null;
		int mode =-1;
	   	if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(entityType))
	   	 {
	   	    mode = getContractSession().getMode();	
	   	 }
	   	 else if(BusinessConstants.ENTITY_TYPE_PRODUCT.equalsIgnoreCase(entityType))
	   	 {
	   	    mode = getProductSessionObject().getMode();	
	   	 }
		if (adminMethodList.size() > 0) {
			

			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setId("ref1"+RandomStringUtils.randomAlphanumeric(15));
			AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) adminMethodList.get(0);
            /*CARS|AM2|POS|START*/
			isPOS = adminMethodOverrideBO1.isPosProductFamily(); //added to instance varaible instead of local
			if(isPrintMode()){
        		panel.setRowClasses("dataTableOddRow");
            }
			else if(mode == ContractSession.EDIT_MODE || mode == ContractSession.VIEW_MODE ){
            	panel.setRowClasses("dataTableEvenRow,dataTableOddRow");
            }
			if(isPOS){
				productFamily = BusinessConstants.PRODUCT_FAMILY_POS;
				panel.setColumns(3);
				panel.setColumnClasses("column20pct,column40px,column40px");
			}
			else{
			   productFamily = adminMethodOverrideBO1.getProductFamily();
			   panel.setColumns(2);
			   panel.setColumnClasses("column40px,column60pct");
			}
			/*CARS|AM2|POS|END*/			
			for (int i = 0; i < adminMethodList.size(); i++) {
				
				HtmlOutputLabel spsNameField = new HtmlOutputLabel();
				spsNameField.setId("spsNameField"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputLabel adminMethodField = new HtmlOutputLabel();
				adminMethodField.setId("adminMethodField"+RandomStringUtils.randomAlphanumeric(15));
				adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList.get(i);
				//first column for SPS Name
				addProcessingMethod = ~addProcessingMethod;
				if (isPOS) {
					if (addProcessingMethod!=0) {
						HtmlOutputText spsName = new HtmlOutputText();
						spsName.setId("spsName" + i);
						spsName.setValue(adminMethodOverrideBO.getSpsName());
						spsNameField.getChildren().add(spsName);
						panel.getChildren().add(spsNameField);
					}
				}else{
					HtmlOutputText spsName = new HtmlOutputText();
					spsName.setId("spsName" + i);
					spsName.setValue(adminMethodOverrideBO.getSpsName());
					spsNameField.getChildren().add(spsName);
					panel.getChildren().add(spsNameField);
				}
				//first column for SPS Name
				//second column for admin method
				if(isPrintMode()){
					HtmlOutputText outputText = new HtmlOutputText();
					outputText.setId("adminMethod" + i);
					if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
					{
						outputText.setValue(" ");
					}
					else
					{
						outputText.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
					}
					adminMethodField.getChildren().add(outputText);
				}
				else if(mode == ContractSession.EDIT_MODE || mode == ContractSession.VIEW_MODE)
				{   
					HtmlInputHidden hiddenSps = new HtmlInputHidden();
					hiddenSps.setId("hiddenSpsName" + i);
					hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());
	
					ValueBinding valBindingForSpsName = FacesContext.getCurrentInstance().getApplication().createValueBinding(
									"#{adminMethodBackingBean.spsNameMap[" + i+ "]}");
					hiddenSps.setValueBinding("value", valBindingForSpsName);
					adminMethodField.getChildren().add(hiddenSps);
					
					HtmlInputText inputText2 = new HtmlInputText();
					inputText2.setId("adminMethod" + i);
					inputText2.setStyleClass("formInputFieldForDiv");				
					String toolTipForDesc = adminMethodOverrideBO.getAdminMethodDesc();				
					inputText2.setTitle(toolTipForDesc);		
					inputText2.setOnmouseover("setTitle(" + i + ")");	
					inputText2.setReadonly(true);
					if (null == adminMethodOverrideBO.getAdminMethodDesc())
					{
					    inputText2.setValue("");
					}
					else
					{
						inputText2.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
				    }	
					adminMethodField.getChildren().add(inputText2);
					
					HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
					hiddenAdminDetails.setId("hiddenAdminMethodDetails" + i);

					if (null != adminMethodOverrideBO.getAdminMethodDesc()
							&& 0 != adminMethodOverrideBO.getAdminMethodSysId()) {
						hiddenAdminDetails.setValue(""
								+ adminMethodOverrideBO.getAdminMethodDesc() + "~"
								+ adminMethodOverrideBO.getAdminMethodSysId());
					} else {
						hiddenAdminDetails.setValue("");
					}
					adminMethodField.getChildren().add(hiddenAdminDetails);
					HtmlOutputText outputText5 = new HtmlOutputText();
					outputText5.setId("emptySpace" + i);
					outputText5.setValue(" ");

					adminMethodField.getChildren().add(outputText5);
					if(mode == ContractSession.EDIT_MODE){
					ValueBinding valBindingForAdminMethod = FacesContext.getCurrentInstance().getApplication().createValueBinding(
									"#{adminMethodBackingBean.adminMethodMap[" + i+ "]}");
					hiddenAdminDetails.setValueBinding("value",valBindingForAdminMethod);				

						HtmlCommandButton selectButton = new HtmlCommandButton();
						selectButton.setId("selectButton" + i);
						selectButton.setStyle("border:0;");
						selectButton.setImage("../../images/select.gif");
						selectButton.setTitle("Select");
	
						// Modified - Added super process step name for adminMethodPopUp
						selectButton.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
										+ Math.random()
										+ "&spsId="
										+ adminMethodOverrideBO.getSpsId()
										+ "&spsName="
										+ adminMethodOverrideBO.getSpsName()
										+ "&adminId="
										+ this.adminId
										+ "&stdbenId="
										+ this.stdbenId
										+ "&entityType="
										+ this.entityType
										+ "', 'adminMethodForm:adminMethod"
										+ i
										+ "','adminMethodForm:hiddenAdminMethodDetails"
										+ i + "',2,1);return false;");
							if (!(this.componentType).equals(WebConstants.MANDATE_TYPE)) {
								adminMethodField.getChildren().add(selectButton);
							}
					}
					HtmlOutputText outputText6 = new HtmlOutputText();
					outputText6.setId("emptySpaces" + i);
					outputText6.setValue(" ");
					adminMethodField.getChildren().add(outputText6);
					
					HtmlCommandButton viewButton = new HtmlCommandButton();
					viewButton.setId("viewButton" + i);
					viewButton.setStyle("border:0;");
					viewButton.setImage("../../images/view.gif");
					viewButton.setTitle("View");
					viewButton.setOnclick("getViewDetails('"
							+ adminMethodOverrideBO.getSpsId() + "','"
							+ this.adminId + "','" + this.stdbenId + "','"
							+ this.entityType + "','"
							+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
							+ "'adminMethodForm:hiddenAdminMethodDetails" + i + "'"
							+ ");return false;");
					adminMethodField.getChildren().add(viewButton);
					
					HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
					hiddenVariableId.setId("adminMethodHidden" + i);
					hiddenVariableId.setValue(""+ adminMethodOverrideBO.getAdminMethod());
				}	
				panel.getChildren().add(adminMethodField);
			}
		}
		return panel;		
	}

	public HtmlPanelGrid getPanelForOverrideViewContract() {

		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("2");
		panel.setCellspacing("0");
		panel.setBgcolor("#cccccc");
		getHeaderPanelforContract(panel);
		List adminMethodList = this.getAdminMethodLists();
		storeAdminMethodStates(adminMethodList);
		int addProcessingMethod = 0;
		AdminMethodOverrideBO adminMethodOverrideBO = null;

		if (adminMethodList.size() > 0) {

			AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) adminMethodList
					.get(0);
			boolean isPOS = adminMethodOverrideBO1.isPosProductFamily();
			if (isPOS)
				panel.setColumns(4);
			else
				panel.setColumns(2);

			for (int i = 0; i < adminMethodList.size(); i++) {

				addProcessingMethod = ~addProcessingMethod;
				adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList
						.get(i);

				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);
				outputText1.setValue(adminMethodOverrideBO.getSpsName());

				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenSpsName" + i);
				hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());

				ValueBinding valBindingForSpsName = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.spsNameMap[" + i
										+ "]}");
				hiddenSps.setValueBinding("value", valBindingForSpsName);
				
				
				HtmlOutputText outputText2 = new HtmlOutputText();
				outputText2.setId("adminMethod" + i);
				outputText2.setStyleClass("formInputFieldForDiv");
				if (null == adminMethodOverrideBO.getAdminMethodDesc()) {
					outputText2.setValue("");
				} else {
					outputText2.setValue(""
							+ adminMethodOverrideBO.getAdminMethodDesc());
				}
				
				String toolTipForDesc = adminMethodOverrideBO
				.getAdminMethodDesc();
				outputText2.setTitle(toolTipForDesc);
				
				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenAdminMethodDetails" + i);

				if (null != adminMethodOverrideBO.getAdminMethodDesc()
						&& 0 != adminMethodOverrideBO.getAdminMethodSysId()) {
					hiddenAdminDetails.setValue(""
							+ adminMethodOverrideBO.getAdminMethodDesc() + "~"
							+ adminMethodOverrideBO.getAdminMethodSysId());
				} else {
					hiddenAdminDetails.setValue("");
				}

				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace" + i);
				outputText5.setValue(" ");

				ValueBinding valBindingForAdminMethod = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.adminMethodMap[" + i
										+ "]}");
				hiddenAdminDetails.setValueBinding("value",
						valBindingForAdminMethod);

				HtmlOutputText outputText3 = new HtmlOutputText();
				outputText3.setId("reference" + i);
				outputText3.setValue(adminMethodOverrideBO.getReference());

				HtmlInputHidden hiddenRef = new HtmlInputHidden();
				hiddenRef.setId("hiddenRefer" + i);
				hiddenRef.setValue("" + adminMethodOverrideBO.getReference());

				HtmlOutputText outputText6 = new HtmlOutputText();
				outputText6.setId("emptySpaces" + i);
				outputText6.setValue(" ");

				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton" + i);
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("getViewDetails('"
						+ adminMethodOverrideBO.getSpsId() + "','"
						+ this.adminId + "','" + this.stdbenId + "','"
						+ this.entityType + "','"
						+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
						+ "'adminMethodViewForm:hiddenAdminMethodDetails" + i
						+ "'" + ");return false;");

				HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
				hiddenVariableId.setId("adminMethodHidden" + i);
				hiddenVariableId.setValue(""
						+ adminMethodOverrideBO.getAdminMethod());

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + i);
				sps.setId("spsnsame10"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + i);
				ref.setId("adminMethod10"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				HtmlOutputLabel val = new HtmlOutputLabel();
				val.setFor("reference" + i);
				val.setId("reference10"+RandomStringUtils.randomAlphanumeric(15));
				//val.setId("referenc" + i);

				if (isPOS) {
					if (addProcessingMethod!=0) {
						sps.getChildren().add(outputText1);
					}
				} else {
					sps.getChildren().add(outputText1);
				}

				sps.getChildren().add(hiddenSps);
				
				ref.getChildren().add(outputText2);
				ref.getChildren().add(hiddenAdminDetails);
				ref.getChildren().add(outputText5);
				ref.getChildren().add(outputText6);
				ref.getChildren().add(viewButton);
				val.getChildren().add(outputText3);
				val.getChildren().add(hiddenRef);
				panel.getChildren().add(sps);
				panel.getChildren().add(ref);
			}
		}
		return panel;
	}

	/**
	 * @return Returns the panelForOverridePrint.
	 */
	public HtmlPanelGrid getPanelForOverridePrintContract() {
		panelForOverridePrintContract = new HtmlPanelGrid();
		panelForOverridePrintContract.setWidth("100%");
		panelForOverridePrintContract.setBorder(0);
		panelForOverridePrintContract.setStyleClass("outputText");
		panelForOverridePrintContract.setCellpadding("3");
		panelForOverridePrintContract.setCellspacing("1");
		//panelForOverridePrint.setBgcolor("#cccccc");
		getHeaderPanelforContract(panelForOverridePrintContract);
		List adminMethodList = this.getAdminMethodLists();
		storeAdminMethodStates(adminMethodList);
		int addProcessingMethod = 0;
		AdminMethodOverrideBO adminMethodOverrideBO = null;

		if (adminMethodList.size() > 0) {

			AdminMethodOverrideBO adminMethodOverrideBO1 = (AdminMethodOverrideBO) adminMethodList
					.get(0);
			boolean isPOS = adminMethodOverrideBO1.isPosProductFamily();
			if (isPOS)
				panelForOverridePrintContract.setColumns(4);
			else
				panelForOverridePrintContract.setColumns(2);

			for (int i = 0; i < adminMethodList.size(); i++) {

				addProcessingMethod = ~addProcessingMethod;
				adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList
						.get(i);

				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsName" + i);

				outputText1.setValue(adminMethodOverrideBO.getSpsName());
				outputText1.setStyleClass("formoutputFieldForDiv");

				HtmlOutputText inputText2 = new HtmlOutputText();
				inputText2.setId("adminMethod" + i);

				inputText2.setStyleClass("formoutputFieldForDiv");
				String toolTipForDesc = adminMethodOverrideBO
						.getAdminMethodDesc();

				if (adminMethodOverrideBO.getAdminMethodDesc() != null) {
					inputText2.setValue(""
							+ adminMethodOverrideBO.getAdminMethodDesc());
				}

				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsName" + i);
				sps.setId("spsnsame11"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsnsame" + i);

				HtmlOutputLabel ref = new HtmlOutputLabel();
				ref.setFor("adminMethod" + i);
				ref.setId("adminMethod11"+RandomStringUtils.randomAlphanumeric(15));
				//ref.setId("adminMetho" + i);

				if (isPOS) {
					if (addProcessingMethod!=0) {
						sps.getChildren().add(outputText1);
					}
				} else {
					sps.getChildren().add(outputText1);
				}

				ref.getChildren().add(inputText2);

				panelForOverridePrintContract.getChildren().add(sps);
				panelForOverridePrintContract.getChildren().add(ref);
			}
		}
		return panelForOverridePrintContract;
	}

	/**
	 * @param panelForOverridePrintContract
	 *            The panelForOverridePrintContract to set.
	 */
	public void setPanelForOverridePrintContract(
			HtmlPanelGrid panelForOverridePrintContract) {
		this.panelForOverridePrintContract = panelForOverridePrintContract;
	}

	/**
	 * @param panelForOverrideViewContract
	 *            The panelForOverrideViewContract to set.
	 */
	public void setPanelForOverrideViewContract(
			HtmlPanelGrid panelForOverrideViewContract) {
		this.panelForOverrideViewContract = panelForOverrideViewContract;
	}

	/**
	 * @return Returns the adminMethodPrint.
	 */
	public String adminMethodPrint() {
		this.getPanelForOverridePrintContract();
		return "adminMethodPrint";
	}

	/**
	 * @param adminMethodPrint
	 *            The adminMethodPrint to set.
	 */
	public void setAdminMethodPrint(String adminMethodPrint) {
		this.adminMethodPrint = adminMethodPrint;
	}

	/**
	 * @return Returns the renderTierPanel.
	 */
	public boolean isRenderTierPanel() {
		return renderTierPanel;
	}
	/**
	 * @param renderTierPanel The renderTierPanel to set.
	 */
	public void setRenderTierPanel(boolean renderTierPanel) {
		this.renderTierPanel = renderTierPanel;
	}
	
	
	/**
	 * @return Returns the tierCriteriaList.
	 */
	public List getTierCriteriaList() {
		return tierCriteriaList;
	}
	/**
	 * @param tierCriteriaList The tierCriteriaList to set.
	 */
	public void setTierCriteriaList(List tierCriteriaList) {
		this.tierCriteriaList = tierCriteriaList;
	}
	/**
	 * @return Returns the tieredAdminMethodList.
	 */
	public List getTieredAdminMethodList() {
		return tieredAdminMethodList;
	}
	/**
	 * @param tieredAdminMethodList The tieredAdminMethodList to set.
	 */
	public void setTieredAdminMethodList(List tieredAdminMethodList) {
		this.tieredAdminMethodList = tieredAdminMethodList;
	}
	/**
	 * @param tierPanel The tierPanel to set.
	 */
	public void setTierPanel(HtmlPanelGrid tierPanel) {
		this.tierPanel = tierPanel;
	}
	private void sortTiers(List benefitDefinitonsList) {
		if(null!=benefitDefinitonsList){
		   for (Iterator iter = benefitDefinitonsList.iterator(); iter.hasNext();) {
			BenefitTierDefinition tierDef = (BenefitTierDefinition) iter.next();
			 if(null != tierDef) {
			 	Collections.sort(tierDef.getBenefitTiers());
			 }
			
		}
		}
	}
	  private String formKeyforTier(int tierDefId, int tierSysId) {
	   	String tierDefIdString = new Integer(tierDefId).toString();
		String tierSysIdString = new Integer(tierSysId).toString();
		String temp = tierDefIdString + ":" + tierSysIdString;
		return temp;
	}
   
   
   private String formKeyforMap(int levelId, int lineId, int tierSysId) {
	   	String levelIdString = new Integer(levelId).toString();
	   	String lineIdString = new Integer(lineId).toString();
		String tierSysIdString = new Integer(tierSysId).toString();
		return (levelIdString).concat(":").concat(lineIdString).concat(":").concat(tierSysIdString);
	}
   private void setAdminMethodValuesToTierGrid(int j,BenefitTier tier, HtmlPanelGrid tierLevelPanel) 
   {
   	
   	List adminMethodList = tier.getAdminMethods();
	AdminMethodOverrideBO adminMethodOverrideBO = null;
	if (adminMethodList.size() > 0) {
		for (int i = 0; i < adminMethodList.size(); i++) {

			adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList.get(i);
			HtmlOutputText outputText1 = new HtmlOutputText();
			outputText1.setId("spsTierName_"+j+"_"+i);
			outputText1.setValue(adminMethodOverrideBO.getSpsName());

			HtmlInputHidden hiddenSps = new HtmlInputHidden();
			hiddenSps.setId("hiddenTieredSpsName_"+j+"_"+ i);
			hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());
			HtmlInputText adminMethodInputText = new HtmlInputText();
			adminMethodInputText.setId("tieredAdminMethod_"+j+"_"+ i);
			adminMethodInputText.setStyleClass("formInputFieldForDiv");
			String toolTipForDesc = adminMethodOverrideBO.getAdminMethodDesc()==null?" ":adminMethodOverrideBO.getAdminMethodDesc();
			adminMethodInputText.setTitle(toolTipForDesc);
			if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
			{
				adminMethodInputText.setValue("");
			}
			else
			{
				adminMethodInputText.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
			} 
			adminMethodInputText.setReadonly(true);
			adminMethodInputText.setOnmouseover("setTierTitle('"+j+"_"+i+"')");
			HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
			hiddenAdminDetails.setId("hiddenTieredAdminMethodDetails_"+j+"_"+i);

			if (null != adminMethodOverrideBO.getAdminMethodDesc() && 0 != adminMethodOverrideBO.getAdminMethodSysId()) 
			{
				hiddenAdminDetails.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc() + "~"+ adminMethodOverrideBO.getAdminMethodSysId());
			} else {
				hiddenAdminDetails.setValue(" ");
			}
			HtmlOutputText outputText5 = new HtmlOutputText();
			outputText5.setId("emptySpace_"  +j+ i);
			outputText5.setValue(" ");
			String keyForTierMap = formKeyforTier(tier.getBenefitTierSysId(), adminMethodOverrideBO.getSpsId());
			keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
			ValueBinding valBindingForAdminMethod = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{adminMethodBackingBean.adminMethodTierMap["+keyForTierMap+"]}");
			hiddenAdminDetails.setValueBinding("value",	valBindingForAdminMethod);

			HtmlOutputText outputText3 = new HtmlOutputText();
			outputText3.setId("reference_"+j+"_"+ i);
			outputText3.setValue(adminMethodOverrideBO.getReference());

			HtmlCommandButton selectButton = new HtmlCommandButton();
			selectButton.setId("selectButton_"+j+"_"+ i);
			selectButton.setStyle("border:0;");
			selectButton.setImage("../../images/select.gif");
			selectButton.setTitle("Select");

			// Modified - Added super process step name for adminMethodPopUp
			selectButton
					.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
							+ Math.random()
							+"&benefitTierSysId="  
							+ tier.getBenefitTierSysId()
							+ "&spsId="
							+ adminMethodOverrideBO.getSpsId()
							+ "&spsName="
							+ adminMethodOverrideBO.getSpsName()
							+ "&adminId="
							+ this.adminId
							+ "&stdbenId="
							+ this.stdbenId
							+ "&entityType="
							+ this.entityType
							+ "', 'adminMethodForm:tieredAdminMethod_"+j+"_"+ i +"','adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+i+"',2,1);return false;");

			HtmlOutputText outputText6 = new HtmlOutputText();
			outputText6.setId("emptySpaces_"+j+"_"+ i);
			outputText6.setValue(" ");

			HtmlCommandButton viewButton = new HtmlCommandButton();
			viewButton.setId("viewButton_"+j+"_"+ i);
			viewButton.setStyle("border:0;");
			viewButton.setImage("../../images/view.gif");
			viewButton.setTitle("View");
			viewButton.setOnclick("getViewDetails('"
					+ adminMethodOverrideBO.getSpsId() + "','"
					+ this.adminId + "','" + this.stdbenId + "','"
					+ this.entityType + "','"
					+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
					+ "'adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+ i + "'"
					+ ");return false;");

			HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
			hiddenVariableId.setId("adminMethodHidden_"+j+"_"+ i);
			hiddenVariableId.setValue(""
					+ adminMethodOverrideBO.getAdminMethod());

			HtmlOutputLabel sps = new HtmlOutputLabel();
			sps.setFor("spsTierNameLabel_"+j+"_"+ i);
			sps.setId("spsTierNameLabel_1"+RandomStringUtils.randomAlphanumeric(15));
			//sps.setId("spsTierNameLabel_"+j+"_"+ i);

			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setFor("tieredAdminMethod_"+j+"_"+ i);
			ref.setId("tieredAdminMetho_1"+RandomStringUtils.randomAlphanumeric(15));
			//ref.setId("tieredAdminMetho_"+j+"_"+ i);

			HtmlOutputLabel val = new HtmlOutputLabel();
			val.setFor("reference_"+j+"_"+ i);
			val.setId("referenc_1"+RandomStringUtils.randomAlphanumeric(15));
			//val.setId("referenc_"+j+"_"+ i);

			sps.getChildren().add(outputText1);
			sps.getChildren().add(hiddenSps);
			ref.getChildren().add(adminMethodInputText);
			ref.getChildren().add(hiddenAdminDetails);
			ref.getChildren().add(outputText5);
			if (!(this.componentType).equals(WebConstants.MANDATE_TYPE)) {
				ref.getChildren().add(selectButton);
			}
			ref.getChildren().add(outputText6);
			ref.getChildren().add(viewButton);

			val.getChildren().add(outputText3);

			tierLevelPanel.getChildren().add(sps);
			tierLevelPanel.getChildren().add(ref);
			tierLevelPanel.getChildren().add(val);
		}
	}   	   	   	
   }
   
   /*Method added to set the tiered admin methos to page as part of Stabilization 2011*/
   
   private void setAdminMethodValuesToTierGrid(int m, int i,AdminMethodTierOverrideBO tier, HtmlPanelGrid tierLevelPanel) 
   {
   	
   	//List adminMethodList = tier.getAdminMethods();
	//AdminMethodOverrideBO adminMethodOverrideBO = null;
	/*if (adminMethodList.size() > 0) {
		for (int i = 0; i < adminMethodList.size(); i++) {*/

			//adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList.get(i);
			HtmlOutputText outputText1 = new HtmlOutputText();
			outputText1.setId("spsTierName_"+m+"_"+i);
			outputText1.setValue(tier.getSpsName());

			HtmlInputHidden hiddenSps = new HtmlInputHidden();
			hiddenSps.setId("hiddenTieredSpsName_"+m+"_"+i);
			hiddenSps.setValue("" + tier.getSpsId());
			HtmlInputText adminMethodInputText = new HtmlInputText();
			adminMethodInputText.setId("tieredAdminMethod_"+m+"_"+i);
			adminMethodInputText.setStyleClass("formInputFieldForDiv");
			String toolTipForDesc = tier.getAdminMethodDesc()==null?" ":tier.getAdminMethodDesc();
			adminMethodInputText.setTitle(toolTipForDesc);
			if (null == tier.getAdminMethodDesc()) 
			{
				adminMethodInputText.setValue("");
			}
			else
			{
				adminMethodInputText.setValue(""+ tier.getAdminMethodDesc());
			} 
			adminMethodInputText.setReadonly(true);
			adminMethodInputText.setOnmouseover("setTierTitle('"+m+"_"+i+"')");
			HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
			hiddenAdminDetails.setId("hiddenTieredAdminMethodDetails_"+m+"_"+i);

			if (null != tier.getAdminMethodDesc() && 0 != tier.getAdminMethodSysId()) 
			{
				hiddenAdminDetails.setValue(""+ tier.getAdminMethodDesc() + "~"+ tier.getAdminMethodSysId());
			} else {
				hiddenAdminDetails.setValue(" ");
			}
			HtmlOutputText outputText5 = new HtmlOutputText();
			outputText5.setId("emptySpace_"  +m+"_"+i);
			outputText5.setValue(" ");
			String keyForTierMap = formKeyforTier(tier.getTierSysId(), tier.getSpsId());
			keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
			ValueBinding valBindingForAdminMethod = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{adminMethodBackingBean.adminMethodTierMap["+keyForTierMap+"]}");
			hiddenAdminDetails.setValueBinding("value",	valBindingForAdminMethod);

			HtmlOutputText outputText3 = new HtmlOutputText();
			outputText3.setId("reference_"+m+"_"+i);
			outputText3.setValue(tier.getReference());

			HtmlCommandButton selectButton = new HtmlCommandButton();
			selectButton.setId("selectButton_"+m+"_"+i);
			selectButton.setStyle("border:0;");
			selectButton.setImage("../../images/select.gif");
			selectButton.setTitle("Select");

			// Modified - Added super process step name for adminMethodPopUp
			selectButton
					.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
							+ Math.random()
							+"&benefitTierSysId="  
							+ tier.getTierSysId()
							+ "&spsId="
							+ tier.getSpsId()
							+ "&spsName="
							+ tier.getSpsName()
							+ "&adminId="
							+ this.adminId
							+ "&stdbenId="
							+ this.stdbenId
							+ "&entityType="
							+ this.entityType
							+ "', 'adminMethodForm:tieredAdminMethod_"+m+"_"+i +"','adminMethodForm:hiddenTieredAdminMethodDetails_"+m+"_"+i+"',2,1);return false;");

			HtmlOutputText outputText6 = new HtmlOutputText();
			outputText6.setId("emptySpaces_"+m+"_"+i);
			outputText6.setValue(" ");

			HtmlCommandButton viewButton = new HtmlCommandButton();
			viewButton.setId("viewButton_"+m+"_"+i);
			viewButton.setStyle("border:0;");
			viewButton.setImage("../../images/view.gif");
			viewButton.setTitle("View");
			viewButton.setOnclick("getViewDetails('"
					+ tier.getSpsId() + "','"
					+ this.adminId + "','" + this.stdbenId + "','"
					+ this.entityType + "','"
					+ tier.getAdminMethodSysId() + "',"
					+ "'adminMethodForm:hiddenTieredAdminMethodDetails_"+m+"_"+i + "'"
					+ ");return false;");

			HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
			hiddenVariableId.setId("adminMethodHidden_"+m+"_"+i);
			hiddenVariableId.setValue(""
					+ tier.getAdminMethod());

			HtmlOutputLabel sps = new HtmlOutputLabel();
			sps.setFor("spsTierNameLabel_"+m+"_"+i);
			sps.setId("spsTierNameLabel_2"+RandomStringUtils.randomAlphanumeric(15));
			//sps.setId("spsTierNameLabel_"+m+"_"+i);

			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setFor("tieredAdminMethod_"+m+"_"+i);
			ref.setId("tieredAdminMetho_2"+RandomStringUtils.randomAlphanumeric(15));
			//ref.setId("tieredAdminMetho_"+m+"_"+i);

			HtmlOutputLabel val = new HtmlOutputLabel();
			val.setFor("reference_"+m+"_"+i);
			val.setId("referenc_2"+RandomStringUtils.randomAlphanumeric(15));
			//val.setId("referenc_"+m+"_"+i);

			sps.getChildren().add(outputText1);
			sps.getChildren().add(hiddenSps);
			ref.getChildren().add(adminMethodInputText);
			ref.getChildren().add(hiddenAdminDetails);
			ref.getChildren().add(outputText5);
			if (!(this.componentType).equals(WebConstants.MANDATE_TYPE)) {
				ref.getChildren().add(selectButton);
			}
			ref.getChildren().add(outputText6);
			ref.getChildren().add(viewButton);

			val.getChildren().add(outputText3);

			tierLevelPanel.getChildren().add(sps);
			tierLevelPanel.getChildren().add(ref);
			tierLevelPanel.getChildren().add(val);
		}
	/*}   	   	   	
   }*/
   
   /*Method added to set the tiered admin methos to page as part of Stabilization 2011 - END*/
   
   //For View
   private void prepareSPSAMMappingLines(int j,BenefitTier tier, HtmlPanelGrid tierLevelPanel) 
   {   	
   	 List adminMethodList = tier.getAdminMethods();
	 AdminMethodOverrideBO adminMethodOverrideBO = null;
	 if (adminMethodList.size() > 0) 
	 {
		Set spsNamesSet = new HashSet(0);		
		for (int i = 0; i < adminMethodList.size(); i++) 
		{
			adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList.get(i);
			if(!spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			{
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsTierName_"+j+"_"+i);
				outputText1.setValue(adminMethodOverrideBO.getSpsName());
				HtmlInputHidden hiddenSps = new HtmlInputHidden();
				hiddenSps.setId("hiddenTieredSpsName_"+j+"_"+ i);
				hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());
				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsTierNameLabel_"+j+"_"+ i);
				sps.setId("spsTierNameLabel_3"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsTierNameLabel_"+j+"_"+ i);
				sps.getChildren().add(outputText1);
				sps.getChildren().add(hiddenSps);	
				tierLevelPanel.getChildren().add(sps);
			}

			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setFor("tieredAdminMethod_"+j+"_"+ i);
			ref.setId("tieredAdminMetho_3"+RandomStringUtils.randomAlphanumeric(15));
			//ref.setId("tieredAdminMetho_"+j+"_"+ i);

			HtmlInputText adminMethodInputText = new HtmlInputText();
			adminMethodInputText.setId("tieredAdminMethod_"+j+"_"+ i);
			adminMethodInputText.setStyleClass("formInputFieldForDiv");
			String toolTipForDesc = adminMethodOverrideBO.getAdminMethodDesc()==null?" ":adminMethodOverrideBO.getAdminMethodDesc();
			adminMethodInputText.setTitle(toolTipForDesc);
			if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
			{
				adminMethodInputText.setValue("");
			}
			else
			{
				adminMethodInputText.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
			} 
			adminMethodInputText.setReadonly(true);
			adminMethodInputText.setOnmouseover("setTierTitle('"+j+"_"+i+"')");
			HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
			hiddenAdminDetails.setId("hiddenTieredAdminMethodDetails_"+j+"_"+i);

			if (null != adminMethodOverrideBO.getAdminMethodDesc() && 0 != adminMethodOverrideBO.getAdminMethodSysId()) 
			{
				hiddenAdminDetails.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc() + "~"+ adminMethodOverrideBO.getAdminMethodSysId());
			} else {
				hiddenAdminDetails.setValue(" ");
			}
			HtmlOutputText outputText5 = new HtmlOutputText();
			outputText5.setId("emptySpace_"  +j+ i);
			outputText5.setValue(" ");
			String keyForTierMap = formKeyforTier(tier.getBenefitTierSysId(), adminMethodOverrideBO.getSpsId());
			keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
			ValueBinding valBindingForAdminMethod = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{adminMethodBackingBean.adminMethodTierMap["+keyForTierMap+"]}");
			hiddenAdminDetails.setValueBinding("value",	valBindingForAdminMethod);
			HtmlCommandButton selectButton = new HtmlCommandButton();
			selectButton.setId("selectButton_"+j+"_"+ i);
			selectButton.setStyle("border:0;");
			selectButton.setImage("../../images/select.gif");
			selectButton.setTitle("Select");

			// Modified - Added super process step name for adminMethodPopUp
			selectButton
					.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
							+ Math.random()
							+"&benefitTierSysId="  
							+ tier.getBenefitTierSysId()
							+ "&spsId="
							+ adminMethodOverrideBO.getSpsId()
							+ "&spsName="
							+ adminMethodOverrideBO.getSpsName()
							+ "&adminId="
							+ this.adminId
							+ "&stdbenId="
							+ this.stdbenId
							+ "&entityType="
							+ this.entityType
							+ "', 'adminMethodForm:tieredAdminMethod_"+j+"_"+ i +"','adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+i+"',2,1);return false;");

			HtmlOutputText outputText6 = new HtmlOutputText();
			outputText6.setId("emptySpaces_"+j+"_"+ i);
			outputText6.setValue(" ");

			HtmlCommandButton viewButton = new HtmlCommandButton();
			viewButton.setId("viewButton_"+j+"_"+ i);
			viewButton.setStyle("border:0;");
			viewButton.setImage("../../images/view.gif");
			viewButton.setTitle("View");
			viewButton.setOnclick("getViewDetails('"
					+ adminMethodOverrideBO.getSpsId() + "','"
					+ this.adminId + "','" + this.stdbenId + "','"
					+ this.entityType + "','"
					+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
					+ "'adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+ i + "'"
					+ ");return false;");

			HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
			hiddenVariableId.setId("adminMethodHidden_"+j+"_"+ i);
			hiddenVariableId.setValue(""
					+ adminMethodOverrideBO.getAdminMethod());
			ref.getChildren().add(adminMethodInputText);
			ref.getChildren().add(hiddenAdminDetails);
			ref.getChildren().add(outputText5);
			if (!(this.componentType).equals(WebConstants.MANDATE_TYPE)) 
			{
				ref.getChildren().add(selectButton);
			}
			ref.getChildren().add(outputText6);
			ref.getChildren().add(viewButton);
			
			//Blank field
			HtmlOutputLabel blank = new HtmlOutputLabel();
			blank.setFor("tieredAdminMethod1_4"+j+"_"+ i);
			blank.setId("tieredAdminMetho1_4"+RandomStringUtils.randomAlphanumeric(15));
			//blank.setId("tieredAdminMetho1_"+j+"_"+ i);

			
			if(isTierPOS)
			{
			    if(BusinessConstants.PRODUCT_FAMILY_PPO.equals(adminMethodOverrideBO.getProductFamily()))
			    {
			    	if(!spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			    	{
			    		tierLevelPanel.getChildren().add(ref);	
			    	}
			    	
			    }
			    if(BusinessConstants.PRODUCT_FAMILY_HMO.equals(adminMethodOverrideBO.getProductFamily()))
			    {
			    	if(spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			    	{
			    		tierLevelPanel.getChildren().add(ref);
			    	}
			    	else
			    	{
			    		tierLevelPanel.getChildren().add(blank);
			    		tierLevelPanel.getChildren().add(ref);
			    	}			    	
			    }
			}
			else 
			{
				tierLevelPanel.getChildren().add(ref);	
			}
			
			if(!spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			{
				spsNamesSet.add(adminMethodOverrideBO.getSpsName());
			}											
		}
	}   	   	   	
   }
   private void prepareSPSAMMappingLinesWithProductFamily(int j,BenefitTier tier, HtmlPanelGrid tierLevelPanel) 
   {  
   	 int mode =-1;
   	 if(BusinessConstants.ENTITY_TYPE_CONTRACT.equalsIgnoreCase(entityType))
   	 {
   	    mode = getContractSession().getMode();	
   	 }
   	 else if(BusinessConstants.ENTITY_TYPE_PRODUCT.equalsIgnoreCase(entityType))
   	 {
   	    mode = getProductSessionObject().getMode();	
   	 }
   	 List adminMethodList = tier.getAdminMethods();
	 AdminMethodOverrideBO adminMethodOverrideBO = null;
	 if (adminMethodList.size() > 0) 
	 {
		Set spsNamesSet = new HashSet(0);		
		for (int i = 0; i < adminMethodList.size(); i++) 
		{
			adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList.get(i);
			if(!spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			{
				HtmlOutputLabel sps = new HtmlOutputLabel();
				sps.setFor("spsTierNameLabel_"+j+"_"+ i);
				sps.setId("spsTierNameLabel_3"+RandomStringUtils.randomAlphanumeric(15));
				//sps.setId("spsTierNameLabel_"+j+"_"+ i+"_Id");
				
				HtmlOutputText outputText1 = new HtmlOutputText();
				outputText1.setId("spsTierName_"+j+"_"+i+"_Id");
				outputText1.setValue(adminMethodOverrideBO.getSpsName());
				sps.getChildren().add(outputText1);
				
				if(mode == ContractSession.EDIT_MODE && !isPrintMode())
				{
					HtmlInputHidden hiddenSps = new HtmlInputHidden();
					hiddenSps.setId("hiddenTieredSpsName_"+j+"_"+ i+"_Id");
					hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());
					sps.getChildren().add(hiddenSps);
				}									
				tierLevelPanel.getChildren().add(sps);
			}
			
			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setFor("tieredAdminMethod_"+j+"_"+ i);
			ref.setId("tieredAdminMetho_3"+RandomStringUtils.randomAlphanumeric(15));
			//ref.setId("tieredAdminMetho_"+j+"_"+ i+"Id");
			if(isPrintMode()){
				HtmlOutputText outputText = new HtmlOutputText();
				outputText.setId("tieredAdminMethod_"+j+"_"+ i+"_Id");
				if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
				{
					outputText.setValue(" ");
				}
				else
				{
					outputText.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
				}
				ref.getChildren().add(outputText);
			}
			else if(mode == ContractSession.EDIT_MODE || mode == ContractSession.VIEW_MODE){
				HtmlInputText adminMethodInputText = new HtmlInputText();
				adminMethodInputText.setId("tieredAdminMethod_"+j+"_"+ i+"_Id");
				
				adminMethodInputText.setStyleClass("formInputFieldForDiv");
				String toolTipForDesc = adminMethodOverrideBO.getAdminMethodDesc()==null?" ":adminMethodOverrideBO.getAdminMethodDesc();
				adminMethodInputText.setTitle(toolTipForDesc);
				if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
				{
					adminMethodInputText.setValue("");
				}
				else
				{
					adminMethodInputText.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
				} 
				adminMethodInputText.setReadonly(true);
				adminMethodInputText.setOnmouseover("setTierTitle('"+j+"_"+i+"')");
				ref.getChildren().add(adminMethodInputText);
				HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
				hiddenAdminDetails.setId("hiddenTieredAdminMethodDetails_"+j+"_"+i);
								
				ref.getChildren().add(hiddenAdminDetails);
				

				if (null != adminMethodOverrideBO.getAdminMethodDesc() && 0 != adminMethodOverrideBO.getAdminMethodSysId()) 
				{
					hiddenAdminDetails.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc() + "~"+ adminMethodOverrideBO.getAdminMethodSysId());
				} else {
					hiddenAdminDetails.setValue("");
				}
				HtmlOutputText outputText5 = new HtmlOutputText();
				outputText5.setId("emptySpace_"  +j+ i+"_Id");
				outputText5.setValue(" ");
				ref.getChildren().add(outputText5);
				
				if(mode == ContractSession.EDIT_MODE)
				{
				   String keyForTierMap = formKeyforTier(tier.getBenefitTierSysId(), adminMethodOverrideBO.getSpsId());
				   keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
				   ValueBinding valBindingForAdminMethod = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{adminMethodBackingBean.adminMethodTierMap["+keyForTierMap+"]}");
				   hiddenAdminDetails.setValueBinding("value",	valBindingForAdminMethod);
				   
				   HtmlCommandButton selectButton = new HtmlCommandButton();
				   selectButton.setId("selectButton_"+j+"_"+ i+"_Id");
				   selectButton.setStyle("border:0;");
				   selectButton.setImage("../../images/select.gif");
				   selectButton.setTitle("Select");

				   // Added super process step name for adminMethodPopUp
				   selectButton.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
								+ Math.random()
								+"&benefitTierSysId="  
								+ tier.getBenefitTierSysId()
								+ "&spsId="
								+ adminMethodOverrideBO.getSpsId()
								+ "&spsName="
								+ adminMethodOverrideBO.getSpsName()
								+ "&adminId="
								+ this.adminId
								+ "&stdbenId="
								+ this.stdbenId
								+ "&entityType="
								+ this.entityType
								+ "', 'adminMethodForm:tieredAdminMethod_"+j+"_"+ i+"_Id" +"','adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+i+"',2,1);return false;");
				   	if (!(this.componentType).equals(WebConstants.MANDATE_TYPE)) 
					{
						ref.getChildren().add(selectButton);
					}
					HtmlOutputText outputText6 = new HtmlOutputText();
					outputText6.setId("emptySpaces_"+j+"_"+ i+"_Id");
					outputText6.setValue(" ");
					ref.getChildren().add(outputText6);
				}
				HtmlCommandButton viewButton = new HtmlCommandButton();
				viewButton.setId("viewButton_"+j+"_"+ i+"_Id");
				viewButton.setStyle("border:0;");
				viewButton.setImage("../../images/view.gif");
				viewButton.setTitle("View");
				viewButton.setOnclick("getViewDetails('"
						+ adminMethodOverrideBO.getSpsId() + "','"
						+ this.adminId + "','" + this.stdbenId + "','"
						+ this.entityType + "','"
						+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
						+ "'adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+ i + "'"
						+ ");return false;");
				ref.getChildren().add(viewButton);
				
				HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
				hiddenVariableId.setId("adminMethodHidden_"+j+"_"+ i+"_Id");
				hiddenVariableId.setValue(""+ adminMethodOverrideBO.getAdminMethod());			
			}
			
			
			//Blank field
			HtmlOutputLabel blank = new HtmlOutputLabel();
			blank.setFor("tieredAdminMethod1_"+j+"_"+ i);
			blank.setId("tieredAdminMetho1_6"+RandomStringUtils.randomAlphanumeric(15));
			//blank.setId("tieredAdminMetho1_"+j+"_"+ i+"_Id");
			
			
			if(isTierPOS)
			{
			    if(BusinessConstants.PRODUCT_FAMILY_PPO.equals(adminMethodOverrideBO.getProductFamily()))
			    {
			    	if(!spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			    	{
			    		tierLevelPanel.getChildren().add(ref);	
			    		if(adminMethodList.size()>i+1)
			    		{
			    			AdminMethodOverrideBO nextBO = (AdminMethodOverrideBO) adminMethodList.get(i+1);
			    			if(!BusinessConstants.PRODUCT_FAMILY_HMO.equals(nextBO.getProductFamily()))
			    			{
			    				tierLevelPanel.getChildren().add(blank);
			    			}			    				
			    		}
			    		else if(adminMethodList.size()==i+1){
			    		    tierLevelPanel.getChildren().add(blank);
			    		}			    		 			    	
			    	}
			    	
			    }
			    if(BusinessConstants.PRODUCT_FAMILY_HMO.equals(adminMethodOverrideBO.getProductFamily()))
			    {
			    	if(spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			    	{			    		
			    		tierLevelPanel.getChildren().add(ref);
			    	}
			    	else
			    	{
			    		tierLevelPanel.getChildren().add(blank);
			    		tierLevelPanel.getChildren().add(ref);
			    	}			    	
			    }
			}
			else 
			{
				tierLevelPanel.getChildren().add(ref);	
			}
			
			if(!spsNamesSet.contains(adminMethodOverrideBO.getSpsName()))
			{
				spsNamesSet.add(adminMethodOverrideBO.getSpsName());
			}											
		}
	}   	   	   	
   }
   private void setAdminMethodValuesToTierGridForView(int j,BenefitTier tier, HtmlPanelGrid tierLevelPanel) 
   {
   	
   	List adminMethodList = tier.getAdminMethods();
	AdminMethodOverrideBO adminMethodOverrideBO = null;
	if (adminMethodList.size() > 0) {
		for (int i = 0; i < adminMethodList.size(); i++) {

			adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList.get(i);
			HtmlOutputText outputText1 = new HtmlOutputText();
			outputText1.setId("spsTierName_"+j+"_"+i);
			outputText1.setValue(adminMethodOverrideBO.getSpsName());

			HtmlInputHidden hiddenSps = new HtmlInputHidden();
			hiddenSps.setId("hiddenTieredSpsName_"+j+"_"+ i);
			hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());
			HtmlInputText adminMethodInputText = new HtmlInputText();
			adminMethodInputText.setId("tieredAdminMethod_"+j+"_"+ i);
			adminMethodInputText.setStyleClass("formInputFieldForDiv");
			String toolTipForDesc = adminMethodOverrideBO.getAdminMethodDesc()==null?" ":adminMethodOverrideBO.getAdminMethodDesc();
			adminMethodInputText.setTitle(toolTipForDesc);
			if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
			{
				adminMethodInputText.setValue("");
			}
			else
			{
				adminMethodInputText.setValue(""
						+ adminMethodOverrideBO.getAdminMethodDesc());
			}
			adminMethodInputText.setReadonly(true);
			HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
			hiddenAdminDetails.setId("hiddenTieredAdminMethodDetails_"+j+"_"+i);

			if (null != adminMethodOverrideBO.getAdminMethodDesc() && 0 != adminMethodOverrideBO.getAdminMethodSysId()) 
			{
				hiddenAdminDetails.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc() + "~"+ adminMethodOverrideBO.getAdminMethodSysId());
			} else {
				hiddenAdminDetails.setValue(" ");
			}
			HtmlOutputText outputText5 = new HtmlOutputText();
			outputText5.setId("emptySpace_"  +j+ i);
			outputText5.setValue(" ");
			
			
			HtmlOutputText outputText3 = new HtmlOutputText();
			outputText3.setId("reference_"+j+"_"+ i);
			outputText3.setValue(adminMethodOverrideBO.getReference());

			
			HtmlOutputText outputText6 = new HtmlOutputText();
			outputText6.setId("emptySpaces_"+j+"_"+ i);
			outputText6.setValue(" ");

			HtmlCommandButton viewButton = new HtmlCommandButton();
			viewButton.setId("viewButton_"+j+"_"+ i);
			viewButton.setStyle("border:0;");
			viewButton.setImage("../../images/view.gif");
			viewButton.setTitle("View");
			viewButton.setOnclick("getViewDetails('"
					+ adminMethodOverrideBO.getSpsId() + "','"
					+ this.adminId + "','" + this.stdbenId + "','"
					+ this.entityType + "','"
					+ adminMethodOverrideBO.getAdminMethodSysId() + "',"
					+ "'adminMethodViewForm:hiddenTieredAdminMethodDetails_"+j+"_"+ i + "'"
					+ ");return false;");

			HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
			hiddenVariableId.setId("adminMethodHidden_"+j+"_"+ i);
			hiddenVariableId.setValue(""
					+ adminMethodOverrideBO.getAdminMethod());

			HtmlOutputLabel sps = new HtmlOutputLabel();
			sps.setFor("spsTierNameLabel_"+j+"_"+ i);
			sps.setId("spsTierNameLabel_7"+RandomStringUtils.randomAlphanumeric(15));
			//sps.setId("spsTierNameLabel_"+j+"_"+ i);

			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setFor("tieredAdminMethod_"+j+"_"+ i);
			ref.setId("tieredAdminMetho_7"+RandomStringUtils.randomAlphanumeric(15));
			//ref.setId("tieredAdminMetho_"+j+"_"+ i);

			HtmlOutputLabel val = new HtmlOutputLabel();
			val.setFor("reference_"+j+"_"+ i);
			val.setId("referenc_7"+RandomStringUtils.randomAlphanumeric(15));
			//val.setId("referenc_"+j+"_"+ i);

			sps.getChildren().add(outputText1);
			sps.getChildren().add(hiddenSps);
			ref.getChildren().add(adminMethodInputText);
			ref.getChildren().add(hiddenAdminDetails);
			ref.getChildren().add(outputText5);
			
			ref.getChildren().add(outputText6);
			ref.getChildren().add(viewButton);

			val.getChildren().add(outputText3);

			tierLevelPanel.getChildren().add(sps);
			tierLevelPanel.getChildren().add(ref);
			tierLevelPanel.getChildren().add(val);
		}
	}   	   	   	
   }
   //ForView}
   //For Print{
   private void setAdminMethodValuesToTierGridForPrint(int j,BenefitTier tier, HtmlPanelGrid tierLevelPanel) 
   {   	
   	  List adminMethodList = tier.getAdminMethods();
	  AdminMethodOverrideBO adminMethodOverrideBO = null;
	  if (adminMethodList.size() > 0) 
	  {
		for (int i = 0; i < adminMethodList.size(); i++) 
		{
			adminMethodOverrideBO = (AdminMethodOverrideBO) adminMethodList.get(i);
			HtmlOutputText outputText1 = new HtmlOutputText();
			outputText1.setId("spsTierName_"+j+"_"+i);
			outputText1.setValue(adminMethodOverrideBO.getSpsName());
			HtmlOutputText inputText2 = new HtmlOutputText();
			inputText2.setId("tieredAdminMethod_"+j+"_"+ i);
			if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
			{
				inputText2.setValue("");
			}
			else
			{
				inputText2.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
			}
			HtmlOutputText outputText5 = new HtmlOutputText();
			outputText5.setId("emptySpace_"  +j+ i);
			outputText5.setValue(" ");
			HtmlOutputText outputText3 = new HtmlOutputText();
			outputText3.setId("reference_"+j+"_"+ i);
			outputText3.setValue(adminMethodOverrideBO.getReference());
			HtmlOutputText outputText6 = new HtmlOutputText();
			outputText6.setId("emptySpaces_"+j+"_"+ i);
			outputText6.setValue(" ");
			HtmlOutputLabel sps = new HtmlOutputLabel();
			sps.setFor("spsTierNameLabel_"+j+"_"+ i);
			sps.setId("spsTierNameLabel_8"+RandomStringUtils.randomAlphanumeric(15));
			//sps.setId("spsTierNameLabel_"+j+"_"+ i);
			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setFor("tieredAdminMethod_"+j+"_"+ i);
			ref.setId("tieredAdminMetho_8"+RandomStringUtils.randomAlphanumeric(15));
			//ref.setId("tieredAdminMetho_"+j+"_"+ i);
			HtmlOutputLabel val = new HtmlOutputLabel();
			val.setFor("reference_"+j+"_"+ i);
			val.setId("referenc_8"+RandomStringUtils.randomAlphanumeric(15));
			//val.setId("referenc_"+j+"_"+ i);
			sps.getChildren().add(outputText1);
			ref.getChildren().add(inputText2);
			ref.getChildren().add(outputText5);
			ref.getChildren().add(outputText6);
			val.getChildren().add(outputText3);
			tierLevelPanel.getChildren().add(sps);
			tierLevelPanel.getChildren().add(ref);
			tierLevelPanel.getChildren().add(val);
		}
	}   	   	   	
   }

   /*START AM1 CARS */
   /**
    * Invalid SPS are set to the tier panel.
    */
   private void setAdminMethodValuesToTierGridForCheckin(int j,BenefitTier tier, HtmlPanelGrid tierLevelPanel) 
   { 	  	   	
	List adminMethodList = tier.getAdminMethods();	
	AdminMethodValidationBO adminMethodOverrideBO = null;
	if (adminMethodList.size() > 0) {	
		int administrationId = 0; 	
		for (int i = 0; i < adminMethodList.size(); i++) {
			
			adminMethodOverrideBO = (AdminMethodValidationBO) adminMethodList.get(i);
			
			administrationId = adminMethodOverrideBO.getBenefitAdminSysId();
			HtmlOutputText outputText1 = new HtmlOutputText();
			outputText1.setId("spsTierName_"+j+"_"+i);
			outputText1.setValue(adminMethodOverrideBO.getSpsName());
			
			HtmlInputHidden hiddenSps = new HtmlInputHidden();
			hiddenSps.setId("hiddenTieredSpsName_"+j+"_"+ i);
			hiddenSps.setValue("" + adminMethodOverrideBO.getSpsId());			
			HtmlInputText adminMethodInputText = new HtmlInputText();
			adminMethodInputText.setId("tieredAdminMethod_"+j+"_"+ i);
			adminMethodInputText.setStyleClass("formInputFieldForDiv");
			String toolTipForDesc = adminMethodOverrideBO.getAdminMethodDesc();
			adminMethodInputText.setTitle("'"+toolTipForDesc+"'");
			if (null == adminMethodOverrideBO.getAdminMethodDesc()) 
			{
				adminMethodInputText.setValue("");
			}
			else
			{
				adminMethodInputText.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc());
			}
			adminMethodInputText.setReadonly(true);
			adminMethodInputText.setOnmouseover("setTierTitle('"+j+"_"+i+"')");
			HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
			hiddenAdminDetails.setId("hiddenTieredAdminMethodDetails_"+j+"_"+i);
			if (null != adminMethodOverrideBO.getAdminMethodDesc() && 0 != adminMethodOverrideBO.getAdminMethodId()) 
			{
				hiddenAdminDetails.setValue(""+ adminMethodOverrideBO.getAdminMethodDesc() + "~"+ adminMethodOverrideBO.getAdminMethodId());
			} else {
				hiddenAdminDetails.setValue(" ");
			}
			
			
			HtmlOutputText outputText5 = new HtmlOutputText();
			outputText5.setId("emptySpace_"  +j+ i);
			outputText5.setValue("");
			
			HtmlOutputText outputText7 = new HtmlOutputText();
			outputText7.setId("emptySpaces_"  +j+ i);
			outputText7.setValue("");
			outputText7.setStyle("width:150px;");	
			String keyForTierMap = formKeyforTier(tier.getBenefitTierSysId(), adminMethodOverrideBO.getSpsId());
			keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
			ValueBinding valBindingForAdminMethod = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{adminMethodBackingBean.adminMethodTierMap["+keyForTierMap+"]}");
			hiddenAdminDetails.setValueBinding("value",	valBindingForAdminMethod);

			HtmlInputHidden hiddenTierAdmin = new HtmlInputHidden();
			hiddenTierAdmin.setId("hiddenAdmin" + j + i);
			hiddenTierAdmin.setValue(""
					+ adminMethodOverrideBO.getBenefitAdminSysId());

			ValueBinding valBindingForTierAdmin = FacesContext
					.getCurrentInstance().getApplication()
					.createValueBinding(
							"#{adminMethodBackingBean.benefitTierAdministrationMap["
									+ keyForTierMap + "]}");
			hiddenTierAdmin.setValueBinding("value", valBindingForTierAdmin);
			this.stdbenId = adminMethodOverrideBO.getBenefitSysId();	
			HtmlCommandButton selectButton = new HtmlCommandButton();
			selectButton.setId("selectButton_"+j+"_"+ i);
			selectButton.setStyle("border:0;");
			selectButton.setImage("../../images/select.gif");
			selectButton.setTitle("Select");

			// Modified - Added super process step name for adminMethodPopUp
			selectButton
					.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
							+ Math.random()
							+"&benefitTierSysId="  
							+ tier.getBenefitTierSysId()
							+ "&spsId="
							+ adminMethodOverrideBO.getSpsId()
							+ "&spsName="
							+ adminMethodOverrideBO.getSpsName()
							+ "&adminId="
							+ adminMethodOverrideBO.getBenefitAdminSysId()
							+ "&stdbenId="
							+ adminMethodOverrideBO.getBenefitSysId()
							+ "&entityType="
							+ this.entityType
							+ "', 'adminMethodForm:tieredAdminMethod_"+j+"_"+ i +"','adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+i+"',2,1);return false;");

			HtmlOutputText outputText6 = new HtmlOutputText();
			outputText6.setId("emptySpaces_"+j+"_"+ i);
			outputText6.setValue(" ");

			HtmlCommandButton viewButton = new HtmlCommandButton();
			viewButton.setId("viewButton_"+j+"_"+ i);
			viewButton.setStyle("border:0;");
			viewButton.setImage("../../images/view.gif");
			viewButton.setTitle("View");
			viewButton.setOnclick("getViewDetailsForTiers('"
					+ adminMethodOverrideBO.getSpsId() + "','"
					+ adminMethodOverrideBO.getBenefitAdminSysId() + "','" + adminMethodOverrideBO.getBenefitSysId() + "','"
					+ this.entityType + "','"
					+ adminMethodOverrideBO.getAdminMethodId() + "',"
					+ "'adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+ i + "'"
					+ ");return false;");

			HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
			hiddenVariableId.setId("adminMethodHidden_"+j+"_"+ i);
			hiddenVariableId.setValue(""
					+ adminMethodOverrideBO.getAdminMethod());

			HtmlOutputLabel sps = new HtmlOutputLabel();
			sps.setFor("spsTierNameLabel_"+j+"_"+ i);
			sps.setId("spsTierNameLabel_9"+RandomStringUtils.randomAlphanumeric(15));
			//sps.setId("spsTierNameLabel_"+j+"_"+ i);

			HtmlOutputLabel ref = new HtmlOutputLabel();
			ref.setFor("tieredAdminMethod_"+j+"_"+ i);
			ref.setId("tieredAdminMetho_9"+RandomStringUtils.randomAlphanumeric(15));
			//ref.setId("tieredAdminMetho_"+j+"_"+ i);

			sps.getChildren().add(outputText1);
			sps.getChildren().add(hiddenSps);
			ref.getChildren().add(adminMethodInputText);
			ref.getChildren().add(hiddenAdminDetails);
			ref.getChildren().add(hiddenTierAdmin);			
			ref.getChildren().add(outputText5);
			if(null != entityType && !entityType.equals("") && entityType.equalsIgnoreCase("product")){
				if (!(null == getSession().getAttribute(PRODUCT_SESSION_KEY) && getSession()
						.getAttribute(PRODUCT_SESSION_KEY).equals(""))) {
					ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
							.getAttribute(PRODUCT_SESSION_KEY);
					this.componentType = productSessionObject.getProductKeyObject()
					.getProductType();
				}	
				if (null != this.componentType && !(this.componentType).equals(WebConstants.MANDATE_TYPE)) {
					ref.getChildren().add(selectButton);
				}
			}else{
				ref.getChildren().add(selectButton);
			}			
			ref.getChildren().add(outputText6);
			ref.getChildren().add(viewButton);
			tierLevelPanel.getChildren().add(outputText7);
			tierLevelPanel.getChildren().add(sps);
			tierLevelPanel.getChildren().add(ref);
		}
	}      	
		}
   
   /**
    * Invalid SPS are set to the tier panel.
    */
   private void prepareSPSAMMappingLinesForCheckin(int j,BenefitTier tier, HtmlPanelGrid tierLevelPanel,boolean isPOS) 
   {           
	     List adminMethodList = tier.getAdminMethods();
	     AdminMethodValidationBO adminMethodValidationBO = null;
	     if (adminMethodList.size() > 0){
	         Set spsNamesSet = new HashSet(0);                      
	         for (int i = 0; i < adminMethodList.size(); i++){                              
	             adminMethodValidationBO = (AdminMethodValidationBO) adminMethodList.get(i);
	            if(!spsNamesSet.contains(adminMethodValidationBO.getSpsName())){
	                //Blank field
	                HtmlOutputLabel firstColumn = new HtmlOutputLabel();
	                HtmlOutputText htmlOutputText = new HtmlOutputText();
	                htmlOutputText.setValue("");
	                firstColumn.getChildren().add(htmlOutputText);
	                firstColumn.setFor("tieredAdminMethodBlank_"+j+"_"+ i);
	                firstColumn.setId("tieredAdminMethodBlank_1"+RandomStringUtils.randomAlphanumeric(15));
	               // firstColumn.setId("tieredAdminMethodBlank_"+j+"_"+ i);
	                tierLevelPanel.getChildren().add(firstColumn);                                                    	
	                HtmlOutputText outputText1 = new HtmlOutputText();
	                outputText1.setId("spsTierName_"+j+"_"+i);
	                outputText1.setValue(adminMethodValidationBO.getSpsName());
	                HtmlInputHidden hiddenSps = new HtmlInputHidden();
	                hiddenSps.setId("hiddenTieredSpsName_"+j+"_"+ i);
	                hiddenSps.setValue("" + adminMethodValidationBO.getSpsId());
	                HtmlOutputLabel sps = new HtmlOutputLabel();
	                sps.setFor("spsTierNameLabel_"+j+"_"+ i);
	                sps.setId("spsTierNameLabel_9"+RandomStringUtils.randomAlphanumeric(15));
	                //sps.setId("spsTierNameLabel_"+j+"_"+ i);
	                sps.getChildren().add(outputText1);
	                sps.getChildren().add(hiddenSps);          
	                tierLevelPanel.getChildren().add(sps);
	            }
	                                                                                                                                                            
	            HtmlOutputLabel ref = new HtmlOutputLabel();
	            ref.setFor("tieredAdminMethod_"+j+"_"+ i);
	            ref.setId("tieredAdminMetho_9"+RandomStringUtils.randomAlphanumeric(15));
	            //ref.setId("tieredAdminMetho_"+j+"_"+ i);
	
	            HtmlInputText adminMethodInputText = new HtmlInputText();
	            adminMethodInputText.setId("tieredAdminMethod_"+j+"_"+ i);
	            adminMethodInputText.setStyleClass("formInputFieldForDiv");
	            String toolTipForDesc = adminMethodValidationBO.getAdminMethodDesc()==null?" ":adminMethodValidationBO.getAdminMethodDesc();
	            adminMethodInputText.setTitle(toolTipForDesc);
	            if (null == adminMethodValidationBO.getAdminMethodDesc()){
	                adminMethodInputText.setValue("");
	            }else{
	                adminMethodInputText.setValue(""+ adminMethodValidationBO.getAdminMethodDesc());
	            } 
	            adminMethodInputText.setReadonly(true);
	            adminMethodInputText.setOnmouseover("setTierTitle('"+j+"_"+i+"')");
	            HtmlInputHidden hiddenAdminDetails = new HtmlInputHidden();
	            hiddenAdminDetails.setId("hiddenTieredAdminMethodDetails_"+j+"_"+i);
	
	            if (null != adminMethodValidationBO.getAdminMethodDesc() && 0 != adminMethodValidationBO.getAdminMethodId()){
	                            hiddenAdminDetails.setValue(""+ adminMethodValidationBO.getAdminMethodDesc() + "~"+ adminMethodValidationBO.getAdminMethodId());
	            }else {
	                            hiddenAdminDetails.setValue(" ");
	            }
	            HtmlOutputText outputText5 = new HtmlOutputText();
	            outputText5.setId("emptySpace_"  +j+ i);
	            outputText5.setValue(" ");
	            String keyForTierMap = formKeyforTier(tier.getBenefitTierSysId(), adminMethodValidationBO.getSpsId());
	            keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
	            ValueBinding valBindingForAdminMethod = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{adminMethodBackingBean.adminMethodTierMap["+keyForTierMap+"]}");
	            hiddenAdminDetails.setValueBinding("value",  valBindingForAdminMethod);
	            
	            HtmlInputHidden hiddenTierAdmin = new HtmlInputHidden();
				hiddenTierAdmin.setId("hiddenAdmin" + j + i);
				hiddenTierAdmin.setValue(""
						+ adminMethodValidationBO.getBenefitAdminSysId());

				ValueBinding valBindingForTierAdmin = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{adminMethodBackingBean.benefitTierAdministrationMap["
										+ keyForTierMap + "]}");
				hiddenTierAdmin.setValueBinding("value", valBindingForTierAdmin);
				this.stdbenId = adminMethodValidationBO.getBenefitSysId();	
	
	            HtmlCommandButton selectButton = new HtmlCommandButton();
	            selectButton.setId("selectButton_"+j+"_"+ i);
	            selectButton.setStyle("border:0;");
	            selectButton.setImage("../../images/select.gif");
	            selectButton.setTitle("Select");
	            selectButton.setOnclick("ewpdModalWindow_ewpd( '../popups/adminMethodPopup.jsp'+getUrl()+'?temp="
										+ Math.random()
										+"&benefitTierSysId="  
										+ tier.getBenefitTierSysId()
										+ "&spsId="
										+ adminMethodValidationBO.getSpsId()
										+ "&spsName="
										+ adminMethodValidationBO.getSpsName()
										+ "&adminId="
										+ adminMethodValidationBO.getBenefitAdminSysId()
										+ "&stdbenId="
										+ adminMethodValidationBO.getBenefitSysId()
										+ "&entityType="
										+ this.entityType
										+ "', 'adminMethodForm:tieredAdminMethod_"+j+"_"+ i +"','adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+i+"',2,1);return false;");
	
	            HtmlOutputText outputText6 = new HtmlOutputText();
	            outputText6.setId("emptySpaces_"+j+"_"+ i);
	            outputText6.setValue(" ");
	
	            HtmlCommandButton viewButton = new HtmlCommandButton();
	            viewButton.setId("viewButton_"+j+"_"+ i);
	            viewButton.setStyle("border:0;");
	            viewButton.setImage("../../images/view.gif");
	            viewButton.setTitle("View");
	            viewButton.setOnclick("getViewDetailsForTiers('"
										+ adminMethodValidationBO.getSpsId() + "','"
										+ adminMethodValidationBO.getBenefitAdminSysId() + "','" + adminMethodValidationBO.getBenefitSysId() + "','"
										+ this.entityType + "','"
										+ adminMethodValidationBO.getAdminMethodId() + "',"
										+ "'adminMethodForm:hiddenTieredAdminMethodDetails_"+j+"_"+ i + "'"
										+ ");return false;");
	            HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
	            hiddenVariableId.setId("adminMethodHidden_"+j+"_"+ i);
	            hiddenVariableId.setValue(""
	                                            + adminMethodValidationBO.getAdminMethod());             
	            ref.getChildren().add(adminMethodInputText);
	            ref.getChildren().add(hiddenAdminDetails);
	            ref.getChildren().add(outputText5);
	            ref.getChildren().add(selectButton);
	            ref.getChildren().add(outputText6);
	            ref.getChildren().add(viewButton);
				ref.getChildren().add(hiddenTierAdmin);			
	            
	            //Blank field
	            HtmlOutputLabel blank = new HtmlOutputLabel();
	            blank.setFor("tieredAdminMethod1_"+j+"_"+ i);
	            blank.setId("tieredAdminMetho1_10"+RandomStringUtils.randomAlphanumeric(15));
	           // blank.setId("tieredAdminMetho1_"+j+"_"+ i);
	            blank.setStyle("width:238px;");
	            if(isPOS){
				    if(null != adminMethodValidationBO && null!= adminMethodValidationBO.getProductFamName()
				            && BusinessConstants.PRODUCT_FAMILY_PPO.equalsIgnoreCase(adminMethodValidationBO.getProductFamName())){
				    	if(!spsNamesSet.contains(adminMethodValidationBO.getSpsName())){
				    		tierLevelPanel.getChildren().add(ref);	
				    		if(adminMethodList.size()>i+1){
				    		    AdminMethodValidationBO nextBO = (AdminMethodValidationBO) adminMethodList.get(i+1);
				    			if(null != nextBO && null!= nextBO.getProductFamName()
							            && !BusinessConstants.PRODUCT_FAMILY_HMO.equalsIgnoreCase(nextBO.getProductFamName())){
				    				tierLevelPanel.getChildren().add(blank);
				    			}
				    		}else if(adminMethodList.size()==i+1){
				    		    tierLevelPanel.getChildren().add(blank);
				    		}
				    	}				    	
				    }
				    if(null != adminMethodValidationBO && null!= adminMethodValidationBO.getProductFamName()
				            && BusinessConstants.PRODUCT_FAMILY_HMO.equals(adminMethodValidationBO.getProductFamName())){
				    	if(spsNamesSet.contains(adminMethodValidationBO.getSpsName())){			    		
				    		tierLevelPanel.getChildren().add(ref);
				    	}else{
				    		tierLevelPanel.getChildren().add(blank);
				    		tierLevelPanel.getChildren().add(ref);
				    	}			    	
				    }
				}
	            else{
	                            tierLevelPanel.getChildren().add(ref);   
	            }                        
	            if(!spsNamesSet.contains(adminMethodValidationBO.getSpsName())){
	                            spsNamesSet.add(adminMethodValidationBO.getSpsName());
	            }                                                                                                                                                                              
	         }
	    }                                              
   }

  /*END AM1 CARS */
   
   private void getPosibleValuesList(List benefitDefinitions){
	
	benefitTierDefinitionsList = this.getTierDefinitionsList(benefitDefinitions);
    }
  
   private List getTierDefinitionsList(List benefitDefinitions) {
	TierDefinitionRetrieveRequest request = getTierDefinitionRetrieveRequest(benefitDefinitions);
	TierDefinitionRetrieveResponse response = (TierDefinitionRetrieveResponse) executeService(request);
	if (null != response) {
		benefitTierDefinitionsList = response.getBenefitTierDefinitonsList();
	}
		return benefitTierDefinitionsList;
   }
   private TierDefinitionRetrieveRequest getTierDefinitionRetrieveRequest(List benefitDefList) {

	BenefitLine entityBenefitLine = (BenefitLine) benefitDefList.get(0);
	int benefitDefinitionId = entityBenefitLine.getBenefitDefinitionId();
	
	TierDefinitionRetrieveRequest tierDefRequest = (TierDefinitionRetrieveRequest) this
	.getServiceRequest(ServiceManager.BENEFIT_TIER_DEFINITION_REQUEST);
	tierDefRequest.setProductSysId(getProductSessionObject().getProductKeyObject().getProductId()); //todo doubt
	tierDefRequest.setBenefitComponentSysId(getProductSessionObject().getBenefitComponentId()); //todo :doubt :compare
	tierDefRequest.setBenefitDefinitionSysId(benefitDefinitionId);
	
	return tierDefRequest;
}

	/**
	 * @return Returns the benefitDefinitionsList.
	 */
	public List getBenefitDefinitionsList() {
		return benefitDefinitionsList;
	}
	/**
	 * @param benefitDefinitionsList The benefitDefinitionsList to set.
	 */
	public void setBenefitDefinitionsList(List benefitDefinitionsList) {
		this.benefitDefinitionsList = benefitDefinitionsList;
	}
	/**
	 * @return Returns the benefitTierDefinitionsList.
	 */
	public List getBenefitTierDefinitionsList() {
		return benefitTierDefinitionsList;
	}
	/**
	 * @param benefitTierDefinitionsList The benefitTierDefinitionsList to set.
	 */
	public void setBenefitTierDefinitionsList(List benefitTierDefinitionsList) {
		this.benefitTierDefinitionsList = benefitTierDefinitionsList;
	}
	/**
	 * This method takes existing admin method numbers for each sps from session and compares with admin method numbers selected by the user 
	 * inorder to check user has changed admin methods in web page.
	 */
	public List fetchChangedAdminMethodsInTiers(String entityType)
   	{				
		List tieredDefinitionsList = null;
		List tieredCriteriaDefinitionList = null;
		List tieredAdminMethodList= null; 
		if( null != entityType && !"".equals(entityType) && entityType.equalsIgnoreCase("PRODUCT"))
		{
			tieredDefinitionsList = getProductSessionObject().getTierDefinitionsList();
			tieredCriteriaDefinitionList = getProductSessionObject().getTierCriteriaDefinitionList(); 
			tieredAdminMethodList = getProductSessionObject().getTieredAdminMethodList();
			
		}
		else if( null != entityType && !"".equals(entityType) && entityType.equalsIgnoreCase("CONTRACT"))
		{
			tieredDefinitionsList = getContractSession().getTierDefinitionsList();
			tieredCriteriaDefinitionList = getContractSession().getTierCriteriaDefinitionList();
			tieredAdminMethodList= getContractSession().getTieredAdminMethodList();			
		}
		 
		List adminMethodListForDB = new ArrayList(0);
		BenefitTierDefinition benefitTierDefinition =null;
		List tierList = null;
		List critList = null;		
		AdminMethodTierOverrideBO adminMethodTierOverrideBO = null;
   		for (int l = 0; l < tieredCriteriaDefinitionList.size(); l++)
		{
   			benefitTierDefinition = (BenefitTierDefinition) tieredCriteriaDefinitionList.get(l);			
			tierList = benefitTierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) 
			{
				String adMethodValue="";
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				//todo : work on commented lines below
				/*if (null != critList) 
				{
					for (int k = 0; k < critList.size(); k++) 
					{
						tierCriteria = new BenefitTierCriteria();
						tierCriteria = (BenefitTikerCriteria) critList.get(k);
					}
				}*/
				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					BenefitTier benefitTier = (BenefitTier) tieredAdminMethodList.get(i);
					if (benefitTier.getBenefitTierSysId() == tier.getBenefitTierSysId())
					{																			
							List adminMethodList = benefitTier.getAdminMethods();
							if (adminMethodList.size() > 0) 
							{
								for (int j= 0; j < adminMethodList.size(); j++) 
								{
									AdminMethodOverrideBO adminMethodOverrideBO = (AdminMethodOverrideBO)adminMethodList.get(j);
									String keyForTierMap = formKeyforTier(tier.getBenefitTierSysId(), adminMethodOverrideBO.getSpsId());
									String newValueForAMInMap = (String)getAdminMethodTierMap().get(keyForTierMap)!=null ? ((String)getAdminMethodTierMap().get(keyForTierMap)).trim() :"";
								    String oldValueForSpsInSession = (String)""+adminMethodOverrideBO.getAdminMethodSysId()!=null ? (((String)""+adminMethodOverrideBO.getAdminMethodSysId()).trim()) :"";								    
									String amValue = "";
									boolean oldValueIsZero = oldValueForSpsInSession.equals("0");
								    if (null != newValueForAMInMap) 
									{
										StringTokenizer stringTokenizer = new StringTokenizer(newValueForAMInMap,"~");
										while (stringTokenizer.hasMoreTokens()) 
										{
											amValue = stringTokenizer.nextToken().trim();
										}
									}
								    else
								    {
								    	continue;
								    }
								    if(!getAdminMethodTierMap().containsKey(keyForTierMap))
								    {
								    	continue;
								    }								    
							    	if(amValue.equals(""))
								    {
							    		if(!(oldValueForSpsInSession.equals("") || oldValueIsZero))
							    		{
							    			adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();								    	
							    			adminMethodTierOverrideBO.setSpsId(adminMethodOverrideBO.getSpsId());
							    			adminMethodTierOverrideBO.setTierSysId(tier.getBenefitTierSysId());
							    			adminMethodTierOverrideBO.setStatus("D");
							    			adminMethodListForDB.add(adminMethodTierOverrideBO);
							    		}
								    }
								    else if(!amValue.equals("") && (oldValueForSpsInSession.equals("") || oldValueForSpsInSession.equals("0")))
								    {
								    	adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();								    	
								    	adminMethodTierOverrideBO.setSpsId(adminMethodOverrideBO.getSpsId());
								    	adminMethodTierOverrideBO.setTierSysId(tier.getBenefitTierSysId());
								    	adminMethodTierOverrideBO.setStatus("I");
								    	adminMethodTierOverrideBO.setAdminMethodNumber(Integer.parseInt(amValue));
								    	adminMethodListForDB.add(adminMethodTierOverrideBO);
								    }
								    else if(!(amValue.equalsIgnoreCase(oldValueForSpsInSession)))
								    {
								    	adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();								    	
								    	adminMethodTierOverrideBO.setSpsId(adminMethodOverrideBO.getSpsId());
								    	adminMethodTierOverrideBO.setTierSysId(tier.getBenefitTierSysId());
								    	adminMethodTierOverrideBO.setStatus("U");
								    	adminMethodTierOverrideBO.setAdminMethodNumber(Integer.parseInt(amValue));
								    	adminMethodListForDB.add(adminMethodTierOverrideBO);
								    	
								    	changedTierIdsList.add(new Integer(tier.getBenefitTierSysId()));
								    	
								    }
							    	adMethodValue=amValue;
								 }
								}
							}
					}	
				if(changedTierIdsList.size()>0){
					changedTierAmIdsMap.put(adMethodValue, changedTierIdsList);
				}
				}
			
			}
		 	 
   		return adminMethodListForDB;
   	}
	
	/*START AM1 CARS */
	
	/*Added the method for fetching changed tiered admin methods in PRODUCT as part of Stabilization 2011*/
	
	public List fetchChangedAdminMethodsInTiersProduct(String entityType)
   	{				
		List tieredDefinitionsList = null;
		List tieredCriteriaDefinitionList = null;
		List tieredAdminMethodList= null; 
		if( null != entityType && !"".equals(entityType) && entityType.equalsIgnoreCase("PRODUCT"))
		{
			tieredDefinitionsList = getProductSessionObject().getTierDefinitionsList();
			tieredCriteriaDefinitionList = getProductSessionObject().getTierCriteriaDefinitionList(); 
			tieredAdminMethodList = getProductSessionObject().getTieredAdminMethodList();
			
		}
				 
		List adminMethodListForDB = new ArrayList(0);
		BenefitTierDefinition benefitTierDefinition =null;
		List tierList = null;
		List critList = null;		
		AdminMethodTierOverrideBO adminMethodTierOverrideBO = null;
   		for (int l = 0; l < tieredCriteriaDefinitionList.size(); l++)
		{
   			benefitTierDefinition = (BenefitTierDefinition) tieredCriteriaDefinitionList.get(l);			
			tierList = benefitTierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) 
			{
				String adMethodValue="";
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();

				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					AdminMethodTierOverrideBO benefitTier = (AdminMethodTierOverrideBO) tieredAdminMethodList.get(i);
					if (benefitTier.getTierSysId() == tier.getBenefitTierSysId())
					{																			
							
									//AdminMethodOverrideBO adminMethodOverrideBO = (AdminMethodOverrideBO)adminMethodList.get(j);
									String keyForTierMap = formKeyforTier(tier.getBenefitTierSysId(), benefitTier.getSpsId());
									String newValueForAMInMap = (String)getAdminMethodTierMap().get(keyForTierMap)!=null ? ((String)getAdminMethodTierMap().get(keyForTierMap)).trim() :"";
								    String oldValueForSpsInSession = (String)""+benefitTier.getAdminMethodSysId()!=null ? (((String)""+benefitTier.getAdminMethodSysId()).trim()) :"";								    
									String amValue = "";
									boolean oldValueIsZero = oldValueForSpsInSession.equals("0");
								    if (null != newValueForAMInMap) 
									{
										StringTokenizer stringTokenizer = new StringTokenizer(newValueForAMInMap,"~");
										while (stringTokenizer.hasMoreTokens()) 
										{
											amValue = stringTokenizer.nextToken().trim();
										}
									}
								    else
								    {
								    	continue;
								    }
								    if(!getAdminMethodTierMap().containsKey(keyForTierMap))
								    {
								    	continue;
								    }								    
							    	if(amValue.equals(""))
								    {
							    		if(!(oldValueForSpsInSession.equals("") || oldValueIsZero))
							    		{
							    			adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();								    	
							    			adminMethodTierOverrideBO.setSpsId(benefitTier.getSpsId());
							    			adminMethodTierOverrideBO.setTierSysId(tier.getBenefitTierSysId());
							    			adminMethodTierOverrideBO.setStatus("D");
							    			adminMethodListForDB.add(adminMethodTierOverrideBO);
							    		}
								    }
								    else if(!amValue.equals("") && (oldValueForSpsInSession.equals("") || oldValueForSpsInSession.equals("0")))
								    {
								    	adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();								    	
								    	adminMethodTierOverrideBO.setSpsId(benefitTier.getSpsId());
								    	adminMethodTierOverrideBO.setTierSysId(tier.getBenefitTierSysId());
								    	adminMethodTierOverrideBO.setStatus("I");
								    	adminMethodTierOverrideBO.setAdminMethodNumber(Integer.parseInt(amValue));
								    	adminMethodListForDB.add(adminMethodTierOverrideBO);
								    }
								    else if(!(amValue.equalsIgnoreCase(oldValueForSpsInSession)))
								    {
								    	adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();								    	
								    	adminMethodTierOverrideBO.setSpsId(benefitTier.getSpsId());
								    	adminMethodTierOverrideBO.setTierSysId(tier.getBenefitTierSysId());
								    	adminMethodTierOverrideBO.setStatus("U");
								    	adminMethodTierOverrideBO.setAdminMethodNumber(Integer.parseInt(amValue));
								    	adminMethodListForDB.add(adminMethodTierOverrideBO);
								    	
								    	changedTierIdsList.add(new Integer(tier.getBenefitTierSysId()));
								    	
								    }
							    	adMethodValue=amValue;
								
							}
					}	
				if(changedTierIdsList.size()>0){
					changedTierAmIdsMap.put(adMethodValue, changedTierIdsList);
				}
				}
			
			}
		 	 
   		return adminMethodListForDB;
   	}
	
	/*Added the method for fetching changed tiered admin methods in PRODUCT as part of Stabilization 2011 - END*/
	
	/**
	 * This method takes existing admin method numbers for each sps from session and compares with admin method numbers selected by the user 
	 * inorder to check user has changed admin methods in check in validation popup.
	 */
	public List fetchChangedAdminMethodsInTiersForCheckin(String entityType)
   	{				
		List tieredDefinitionsListInSession = null;
		List tieredCriteriaDefinitionListInSession = null; 
		List tieredAdminMethodListInSession= null;
		if( null != entityType && !"".equals(entityType) && entityType.equalsIgnoreCase("PRODUCT")){
			tieredDefinitionsListInSession = getProductSessionObject().getTierDefinitionsList();
			tieredCriteriaDefinitionListInSession = getProductSessionObject().getTierCriteriaDefinitionList(); 
			tieredAdminMethodListInSession = getProductSessionObject().getTieredAdminMethodList();			
		}else if( null != entityType && !"".equals(entityType) && entityType.equalsIgnoreCase("CONTRACT")){
			tieredDefinitionsListInSession = getContractSession().getTierDefinitionsList();
			tieredCriteriaDefinitionListInSession = getContractSession().getTierCriteriaDefinitionList();
			tieredAdminMethodListInSession= getContractSession().getTieredAdminMethodList();			
		}		
		List adminMethodListForDB = new ArrayList(0);
		BenefitTierDefinition benefitTierDefinitionInSession =null;
		List tierList = null;
		List critList = null;		
		AdminMethodTierOverrideBO adminMethodTierOverrideBO = null;		
		for (int i = 0; i < tieredAdminMethodListInSession.size(); i++){
			AdminMethodValidationBO adminMethodValidationBO = (AdminMethodValidationBO)tieredAdminMethodListInSession.get(i);
			String keyForTierMap = formKeyforTier(adminMethodValidationBO.getBenefitTierSysId(), adminMethodValidationBO.getSpsId());
			String newValueForAMInMap = (String)getAdminMethodTierMap().get(keyForTierMap)!=null ? ((String)getAdminMethodTierMap().get(keyForTierMap)).trim() :"";	
			String benefitAdministrationId = (String)getBenefitTierAdministrationMap().get(keyForTierMap)!=null ? ((String)getBenefitTierAdministrationMap().get(keyForTierMap)).trim() :"";	
			
			String oldValueForSpsInSession = (String)""+adminMethodValidationBO.getAdminMethodId()!=null ? (((String)""+adminMethodValidationBO.getAdminMethodId()).trim()) :"";								    
			String amValue = "";
			boolean oldValueIsZero = oldValueForSpsInSession.equals("0");
		    if (null != newValueForAMInMap){
				StringTokenizer stringTokenizer = new StringTokenizer(newValueForAMInMap,"~");
				while (stringTokenizer.hasMoreTokens()){
					amValue = stringTokenizer.nextToken().trim();
				}
			}
		    else{
		    	continue;
		    }
		    if(!getAdminMethodTierMap().containsKey(keyForTierMap)){
		    	continue;
		    }								    
	    	if(amValue.equals("")){
	    		if(!(oldValueForSpsInSession.equals("") || oldValueIsZero)){
	    			adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();	
	    			adminMethodTierOverrideBO.setBnftAdmnId(Integer.parseInt(benefitAdministrationId));
	    			adminMethodTierOverrideBO.setSpsId(adminMethodValidationBO.getSpsId());
	    			adminMethodTierOverrideBO.setTierSysId(adminMethodValidationBO.getBenefitTierSysId());
	    			adminMethodTierOverrideBO.setStatus("D");
	    			adminMethodListForDB.add(adminMethodTierOverrideBO);
	    		}
		    }
		    else if(!amValue.equals("") && (oldValueForSpsInSession.equals("") || oldValueForSpsInSession.equals("0"))){
		    	adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();	
		    	adminMethodTierOverrideBO.setBnftAdmnId(Integer.parseInt(benefitAdministrationId));
		    	adminMethodTierOverrideBO.setSpsId(adminMethodValidationBO.getSpsId());
		    	adminMethodTierOverrideBO.setTierSysId(adminMethodValidationBO.getBenefitTierSysId());
		    	adminMethodTierOverrideBO.setStatus("I");
		    	adminMethodTierOverrideBO.setAdminMethodNumber(Integer.parseInt(amValue));
		    	adminMethodListForDB.add(adminMethodTierOverrideBO);
		    }
		    else if(!(amValue.equalsIgnoreCase(oldValueForSpsInSession))){
		    	adminMethodTierOverrideBO = new AdminMethodTierOverrideBO();	
		    	adminMethodTierOverrideBO.setBnftAdmnId(Integer.parseInt(benefitAdministrationId));
		    	adminMethodTierOverrideBO.setSpsId(adminMethodValidationBO.getSpsId());
		    	adminMethodTierOverrideBO.setTierSysId(adminMethodValidationBO.getBenefitTierSysId());
		    	adminMethodTierOverrideBO.setStatus("U");
		    	adminMethodTierOverrideBO.setAdminMethodNumber(Integer.parseInt(amValue));
		    	adminMethodListForDB.add(adminMethodTierOverrideBO);
		    }																													
		}			
			 	 
   		return adminMethodListForDB;
   	}
	/*END AM1 CARS */	
/**
 * @return Returns the tierDefMap.
 */
public List getTierDefMap() {
	return tierDefMap;
}
/**
 * @param tierDefMap The tierDefMap to set.
 */
public void setTierDefMap(List tierDefMap) {
	this.tierDefMap = tierDefMap;
}

	/**
	 * @return Returns the adminMethodTierMap.
	 */
	public Map getAdminMethodTierMap() {
		return adminMethodTierMap;
	}
	/**
	 * @param adminMethodTierMap The adminMethodTierMap to set.
	 */
	public void setAdminMethodTierMap(Map adminMethodTierMap) {
		this.adminMethodTierMap = adminMethodTierMap;
	}//CARS:AM2:END
	/*START AM1 CARS */
	/**
	 * @param panelForTierValidation The panelForTierValidation to set.
	 */
	public void setPanelForTierValidation(HtmlPanelGrid panelForTierValidation) {
		this.panelForTierValidation = panelForTierValidation;
	}
	/**
	 * @return Returns the benefitTierAdministrationMap.
	 */
	public Map getBenefitTierAdministrationMap() {
		return benefitTierAdministrationMap;
	}
	/**
	 * @param benefitTierAdministrationMap The benefitTierAdministrationMap to set.
	 */
	public void setBenefitTierAdministrationMap(Map benefitTierAdministrationMap) {
		this.benefitTierAdministrationMap = benefitTierAdministrationMap;
	}
	/*END AM1 CARS */
	/**
	 * @return Returns the tierDisplayPanel.
	 */
	public HtmlPanelGrid getTierDisplayPanel() {
		HtmlPanelGrid displayPanel = new HtmlPanelGrid();
	if (this.renderTierPanel) {
		displayPanel.setCellpadding("2");
		displayPanel.setCellspacing("0");
		displayPanel.setWidth("100%");
		displayPanel.setColumns(2);
		displayPanel.setBorder(0);
		displayPanel.setStyle("outputText");
		displayPanel.setStyleClass("dataTableHeader");
		displayPanel.setBgcolor("#cccccc");
		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValue("Tiered Processing Methods");
		displayPanel.getChildren().add(outputText);
	}
	return displayPanel;
	}

	/**
	 * @return Returns the displayPanelForPrint.
	 */
	public HtmlPanelGrid getDisplayPanelForPrint() {
		displayPanelForPrint = new HtmlPanelGrid();
		if (this.renderTierPanel) {
			displayPanel.setCellpadding("2");
			displayPanel.setCellspacing("0");
			displayPanel.setWidth("100%");
			displayPanel.setColumns(2);
			displayPanel.setBorder(0);
			//displayPanel.setStyle("outputText");
			//displayPanel.setStyleClass("dataTableHeader");
			//displayPanel.setBgcolor("#cccccc");
			HtmlOutputText outputText = new HtmlOutputText();
			outputText.setValue("Processing Methods");
			displayPanel.getChildren().add(outputText);
		}
		return displayPanelForPrint; 
		}
	public HtmlPanelGrid getTierDisplayPanelForPrint() {
		tierDisplayPanelForPrint = new HtmlPanelGrid();
		if (this.renderTierPanel) {
			displayPanel.setCellpadding("2");
			displayPanel.setCellspacing("0");
			displayPanel.setWidth("100%");
			displayPanel.setColumns(2);
			displayPanel.setBorder(0);
			HtmlOutputText outputText = new HtmlOutputText();
			outputText.setValue("Tiered Processing Methods");
			displayPanel.getChildren().add(outputText);
		}
		return tierDisplayPanelForPrint;
		}
	/**
	 * @return Returns the columnHeaderPanelForCheckin.
	 */
	public HtmlPanelGrid getColumnHeaderPanelForCheckin() {


		columnHeaderPanelForCheckin = new HtmlPanelGrid();
		columnHeaderPanelForCheckin.setWidth("100%");
		columnHeaderPanelForCheckin.setColumns(3);
		columnHeaderPanelForCheckin.setBorder(0);
		columnHeaderPanelForCheckin.setCellpadding("1");
		columnHeaderPanelForCheckin.setCellspacing("1");
		columnHeaderPanelForCheckin.setBgcolor("#CCCCCC");
		columnHeaderPanelForCheckin.setColumnClasses("column25px,column35px,column40px");
		columnHeaderPanelForCheckin.setRowClasses("dataTableOddRow");    
		
		HtmlOutputText spsOutputText = new HtmlOutputText();
		HtmlOutputText adminMethodOutputText = new HtmlOutputText();
		HtmlOutputText referenceOutputText = new HtmlOutputText();
		
		spsOutputText.setValue("Benefit Administration");
		spsOutputText.setId("spsNamesCheckin");
		spsOutputText.setStyleClass("dataTableHeader");

		adminMethodOutputText.setValue("SPS Name");
		adminMethodOutputText.setId("adminMethodCheckin");
		adminMethodOutputText.setStyleClass("dataTableHeader");

		referenceOutputText.setValue("Admin Method");
		referenceOutputText.setId("referenceCheckin");
		referenceOutputText.setStyleClass("dataTableHeader");

		columnHeaderPanelForCheckin.getChildren().add(spsOutputText);
		columnHeaderPanelForCheckin.getChildren().add(adminMethodOutputText);
		columnHeaderPanelForCheckin.getChildren().add(referenceOutputText);

		return columnHeaderPanelForCheckin; 
	}
	/**
	 * @param columnHeaderPanelForCheckin The columnHeaderPanelForCheckin to set.
	 */
	public void setColumnHeaderPanelForCheckin(
			HtmlPanelGrid columnHeaderPanelForCheckin) {
		this.columnHeaderPanelForCheckin = columnHeaderPanelForCheckin;
	}
	/**
	 * @param displayPanelForPrint The displayPanelForPrint to set.
	 */
	public void setDisplayPanelForPrint(HtmlPanelGrid displayPanelForPrint) {
		this.displayPanelForPrint = displayPanelForPrint;
	}
	/**
	 * @param panelForOverrideForPrint The panelForOverrideForPrint to set.
	 */
	public void setPanelForOverrideForPrint(
			HtmlPanelGrid panelForOverrideForPrint) {
		this.panelForOverrideForPrint = panelForOverrideForPrint;
	}
	/**
	 * @param tierColumnHeaderPanel The tierColumnHeaderPanel to set.
	 */
	public void setTierColumnHeaderPanel(HtmlPanelGrid tierColumnHeaderPanel) {
		this.tierColumnHeaderPanel = tierColumnHeaderPanel;
	}
	/**
	 * @param tierColumnHeaderPanelForCheckin The tierColumnHeaderPanelForCheckin to set.
	 */
	public void setTierColumnHeaderPanelForCheckin(
			HtmlPanelGrid tierColumnHeaderPanelForCheckin) {
		this.tierColumnHeaderPanelForCheckin = tierColumnHeaderPanelForCheckin;
	}
	/**
	 * @param tierDisplayPanel The tierDisplayPanel to set.
	 */
	public void setTierDisplayPanel(HtmlPanelGrid tierDisplayPanel) {
		this.tierDisplayPanel = tierDisplayPanel;
	}
	/**
	 * @param tierDisplayPanelForPrint The tierDisplayPanelForPrint to set.
	 */
	public void setTierDisplayPanelForPrint(
			HtmlPanelGrid tierDisplayPanelForPrint) {
		this.tierDisplayPanelForPrint = tierDisplayPanelForPrint;
	}
	/**
	 * @return Returns the tierPanelForView.
	 */
	public HtmlPanelGrid getTierPanelForView() {

		Logger.logInfo("entered method getTierPanel");

		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;

		//List benefitDefinitonsList = this.getBenefitDefinitionsList();
		// This method gets the values from the benefit definiton List and sets
		// it to the level list and line list
		//getValuesFromDefinitonList(benefitDefinitonsList);

		tierPanel = new HtmlPanelGrid();
		tierPanel.setColumns(1);
		tierPanel.setWidth("100%");
		tierPanel.setBorder(0);
		tierPanel.setCellpadding("0");
		tierPanel.setCellspacing("0");
		tierPanel.setStyleClass("outputText");
		tierPanel.setBgcolor("#cccccc");		


		HtmlPanelGrid tierColumnHeaderlPanel = new HtmlPanelGrid();			
		tierColumnHeaderlPanel.setColumns(3);
		tierColumnHeaderlPanel.setWidth("100%");

		tierColumnHeaderlPanel.setBorder(0);
		tierColumnHeaderlPanel.setCellpadding("1");
		tierColumnHeaderlPanel.setCellspacing("1");
		tierColumnHeaderlPanel.setBgcolor("#cccccc");  
		tierColumnHeaderlPanel.setColumnClasses("column25px,column35px,column40px");
		
		StringBuffer rows = new StringBuffer();
		// setting values to benefit levels
		int size = 0;
		if (null != tierDefinitionsList) {
			sortTiers(tierDefinitionsList); 
		}
		HtmlPanelGrid tierDefPanel = null;

		HtmlPanelGrid tierCritPanel = null;
		
		HtmlPanelGrid tierHeaderPanel = null;

		HtmlPanelGrid tierLevelPanel = null;
		
		BenefitTierDefinition tierDefinition = null;

		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		// iterating to get the benefit levels
		for (int l = 0; l < tierDefinitionsList.size(); l++)
		{
			HtmlPanelGrid grid1 = new HtmlPanelGrid();
			grid1.setBgcolor("#FFFFFF");
			tierPanel.getChildren().add(grid1);
			tierDefinition = (BenefitTierDefinition) tierDefinitionsList.get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");
			tierDefPanel.setBorder(0);
			tierDefPanel.setCellpadding("0");
			tierDefPanel.setCellspacing("0");
			tierDefPanel.setBgcolor("#cccccc");

			HtmlOutputLabel defLabel = new HtmlOutputLabel();
			defLabel.setId("defLabel8"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setStyleClass("dataTableHeader1");		
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			tierDef.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");
			
			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
			hidDefId.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");
			String tempKey = tierDefinition.getBenefitTierDefinitionSysId()+"";
			defLabel.getChildren().add(tierDef);
			tierDefPanel.getChildren().add(defLabel);
         
			tierPanel.getChildren().add(tierDefPanel);
		
			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) {
			
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				tierCritPanel = new HtmlPanelGrid();
				
				
				
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");

				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("0");
				tierCritPanel.setCellspacing("0");
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow");

				tierHeaderPanel = new HtmlPanelGrid();
				tierHeaderPanel.setCellpadding("0");
				tierHeaderPanel.setCellspacing("0");
				tierHeaderPanel.setColumns(2);
				tierHeaderPanel.setWidth("100%");
				tierHeaderPanel.setBgcolor("#FFFFFF");

				tierHeaderPanel.setStyleClass("headerPanel1");
				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel9"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:250px;");

				if (null != critList) {
					for (int k = 0; k < critList.size(); k++) {
						tierlabel.getChildren().add(dummylabel);
						tierCriteria = new BenefitTierCriteria();
						tierCriteria = (BenefitTierCriteria) critList.get(k);
						HtmlOutputText tierCrit = new HtmlOutputText();
						tierCrit.setStyle("color:blue");
						tierCrit.setValue(tierCriteria
								.getBenefitTierCriteriaName()+" : ");
						tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo);
						tierlabel.getChildren().add(tierCrit);
						HtmlOutputText critValueView = new HtmlOutputText();
						String critVal = tierCriteria.getBenefitTierCriteriaValue();						
						critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
						tierlabel.getChildren().add(critValueView);						
					}
				}

				tierHeaderPanel.getChildren().add(tierlabel);					
				tierCritPanel.getChildren().add(tierHeaderPanel);
				
				int sizeOfLevelList = 0;
				tierLevelPanel = new HtmlPanelGrid();			
				tierLevelPanel.setColumns(3);
				tierLevelPanel.setWidth("100%");

				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("2");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc");  
				tierLevelPanel.setColumnClasses("column25px,column35px,column40px");
				tierLevelPanel.setRowClasses("dataTableEvenRow,dataTableOddRow");            
				for (int i = 0; i < tieredAdminMethodList.size(); i++) 
				{
					BenefitTier benefitTier = (BenefitTier) tieredAdminMethodList.get(i);
					if (benefitTier.getBenefitTierSysId() == tier.getBenefitTierSysId())
					{												
						if(benefitTier.getAdminMethods() == null || benefitTier.getAdminMethods().size() == 0)
						{
							HtmlOutputLabel label =new HtmlOutputLabel();
							label.setId("label10"+RandomStringUtils.randomAlphanumeric(15));
							HtmlOutputText text = new HtmlOutputText();
							text.setValue(WebConstants.NO_SPS_FOR_TERMS_IN_TIER);
							label.getChildren().add(text);
							tierLevelPanel.setColumns(1);
							tierLevelPanel.getChildren().add(label);
						}
						else
						{						  
						    setAdminMethodValuesToTierGridForView(i,benefitTier,tierLevelPanel);
						}
					}																							
				}
			
			tierCritPanel.getChildren().add(tierLevelPanel);
			tierDefPanel.getChildren().add(tierCritPanel);
			}
		}
		return tierPanel;
	
	}
	
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/**
	 * @param tierPanelForView The tierPanelForView to set.
	 */
	public void setTierPanelForView(HtmlPanelGrid tierPanelForView) {
		this.tierPanelForView = tierPanelForView;
	}
	/**
	 * @param tierPanelForPrint The tierPanelForPrint to set.
	 */
	public void setTierPanelForPrint(HtmlPanelGrid tierPanelForPrint) {
		this.tierPanelForPrint = tierPanelForPrint;
	}


	/**
	 * @return Returns the isPOS.
	 */
	public boolean isPOS() {
		return isPOS;
	}
	/**
	 * @param isPOS The isPOS to set.
	 */
	public void setPOS(boolean isPOS) {
		this.isPOS = isPOS;
	}
	/**
	 * @return Returns the tierPanelForContract.
	 */
	
	/**
	 * @param tierPanelForContract The tierPanelForContract to set.
	 */
	/*public void setTierPanelForContract(HtmlPanelGrid tierPanelForContract) {
		this.tierPanelForContract = tierPanelForContract;
	}*/
	/**
	 * @param columnHeaderPanel The columnHeaderPanel to set.
	 */
	public void setColumnHeaderPanel(HtmlPanelGrid columnHeaderPanel) {
		this.columnHeaderPanel = columnHeaderPanel;
	}
	public boolean isPrintMode() {
		return printMode;
	}
	public void setPrintMode(boolean printMode) {
		this.printMode = printMode;
	}
	public String reloadForPrint() {
		this.submitFlag = true;
		return "loadAdminMethodPrint";
	}
	/**
	 * @return Returns the submitFlag.
	 */
	public boolean isSubmitFlag() {
		return submitFlag;
	}
	/**
	 * @param submitFlag The submitFlag to set.
	 */
	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}
}