/*
 * ContractComponentGeneralInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import org.apache.commons.lang.RandomStringUtils;


import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.contract.request.BenefitCustomizationRequest;
import com.wellpoint.wpd.common.contract.request.RetreiveBenefitsContractRequest;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.contract.bo.BenefitCustomizationBO;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitComponents;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitComponentRequest;
import com.wellpoint.wpd.common.contract.response.BenefitCustomizationResponse;
import com.wellpoint.wpd.common.contract.response.RetreiveBenefitsContractResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitComponentResponse;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractComponentGeneralInfoBackingBean extends ContractBackingBean{

    private String businessDomain;

    private String effectiveDate;

    private String expiryDate;

    private String name;

    private String description;

    private String createdBy;

    private Date createdDate;

	private String componentType;
	
    private String updatedBy;

    private Date lastUpdatedDate;

    private String lineOfBusiness;

    private String businessEntity;

    private String businessGroup;
	/*START CARS*/
	private String marketBusinessUnit;
	/*END CARS*/
    private String ruleId;
    
    protected String dummyVariable;
    
    protected String valueForPrint;
    
    //private String renderer;
    private HtmlInputHidden renderer =new HtmlInputHidden();
    
    private HtmlPanelGrid headerPanelForBenefit = new HtmlPanelGrid();
    
    private HtmlPanelGrid panelForBenefit = new HtmlPanelGrid();
    
    private boolean showHiddenSelectedForBenefit = false;
    
    private List benefitList;
    
    private Map benefitMap = new HashMap();
    
    private Map benefitHideUnhideFlagMap = new HashMap();
    
  //  private String List;
    private HtmlInputHidden List = new HtmlInputHidden();

    protected List benefitComponentHideUnhideList;
    
	private boolean showHiddenSelected = false;
	
	private List benefitComponentList;
	
	private HtmlPanelGrid headerPanel;

	private HtmlPanelGrid panel;
	
	private Map datafieldMapForBenefitComponentId= new LinkedHashMap();
	private Map datafieldMapForBenComponentHideFlag = new LinkedHashMap();
	private String stringForCache;
	
	private String breadCrumpText;
    private boolean printMode;
    private String printGeneralInfo;
    
    private boolean viewSave = true;
    
    private String printValue;

    private boolean contractTypeMandate;
    
    private List validationMessages = new ArrayList();
    
    private String flasgStatus;
    private String flasgStatusForBC;
    
    private String ruleDesc;
    
    private String ruleIdNameComb;
    
    private String ruleIdForView;
    
    private String ruleType;
    
    private boolean checkMode;
    //CR - benefit component version added
    
    private int benefitComponentVersion;
	
    public ContractComponentGeneralInfoBackingBean() {
        //checks if it is a view mode or edit mode
      	this.setBreadCrumbText();
    }
    
    

    
    
    public String loadBenefitComponentListTab(){
    	
    	retrieveBenefitComponentsHiddenAndUnhidden(false);
    	 if(this.isPrintMode())
			return "";
    	 else if(!isViewMode())
			return "componentListHideOrUnhide";
		else
           return "componentListHideOrUnhideView";
    }
	/*
	 * method to invoke when the show Hidden Checkbox is selected.
	 */
	public String loadWithHiddenValues() {

		this.setShowHiddenSelected(true);
		// call the method to load values
		this.benefitComponentList = retrieveBenefitComponentsHiddenAndUnhidden(true);
		this.benefitComponentList = listToDisplay(this.benefitComponentList);

		if(!isViewMode()){
		 	getRequest().setAttribute("RETAIN_Value", "");	
			return "componentListHideOrUnhide";
		}else {
           return "componentListHideOrUnhideView";
       }
	}

	/*
	 * method to invoke when the show Hidden Checkbox is unselected.
	 */
	public String loadWithoutHiddenValues() {

		this.setShowHiddenSelected(false);
		// call the method to load values
		this.benefitComponentList = retrieveBenefitComponentsHiddenAndUnhidden(false);
		this.benefitComponentList = listToDisplay(this.benefitComponentList);

		//return "";
		if(!isViewMode()){
		 	getRequest().setAttribute("RETAIN_Value", "");	
			return "componentListHideOrUnhide";
		}else 
           return "componentListHideOrUnhideView";
	}

	/**
	 * method to populate list according to the checkbox
	 * 
	 * @param benefitComponentList
	 * @return
	 */
	public List listToDisplay(List benefitComponentList) {
		if (!showHiddenSelected) {
//			Iterator itr = benefitComponentList.iterator();
			List listWithoutHiddenQuestions = new ArrayList();
			ProductTreeBenefitComponents benCompBO = new ProductTreeBenefitComponents();
			for (int i = 0; i < benefitComponentList.size(); i++) {
				benCompBO = (ProductTreeBenefitComponents) benefitComponentList
						.get(i);
				//if the questin is not hidden then add it to another list
				if (benCompBO.getBenefitComponentHideFlag().equals("F")) {
					listWithoutHiddenQuestions.add(benCompBO);
				}
			}
			return listWithoutHiddenQuestions;
		} else {
			return benefitComponentList;
		}

	}
	/**
	 * Returns the headerPanel
	 * 
	 * @return HtmlPanelGrid headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanel() {
		headerPanel = new HtmlPanelGrid();
		headerPanel.setWidth("100%");
		
        if(this.isPrintMode() || isViewMode() || this.isContractTypeMandate()){
        	headerPanel.setColumns(1);
       }
		else{
			headerPanel.setColumns(2);
		}
		

		
		headerPanel.setBgcolor("#cccccc");
		headerPanel.setCellpadding("4");
		headerPanel.setCellspacing("1");
		headerPanel.setStyleClass("dataTableHeader");
		HtmlOutputText otxtType1 = new HtmlOutputText();
		HtmlOutputText otxtType2 = new HtmlOutputText();
		HtmlOutputText otxtType3 = new HtmlOutputText();
		
		
//		HtmlInputHidden inputHiddenForCheckBox = new HtmlInputHidden();
		// check box for enhancement
		
		HtmlSelectBooleanCheckbox htmlCheckbox = new HtmlSelectBooleanCheckbox();
		HtmlOutputLabel olablType1 = new HtmlOutputLabel();


		otxtType3.setValue("                       ");
		otxtType3.setId("otxtType3");
		
		otxtType1.setValue("Benefit Components");
		otxtType1.setId("benefitComponent");
		otxtType1.setStyle("font-weight: bold;");
		
		otxtType2.setValue("Show Hidden");
		// enhancement starts

		htmlCheckbox.setId("showHidden");
		htmlCheckbox.setTitle("showHidden");
		 ValueBinding checkboxValue = FacesContext.getCurrentInstance()
         .getApplication().createValueBinding(
                 "#{contractComponentGeneralInfoBackingBean.showHiddenSelected}");
		 htmlCheckbox.setValueBinding("value", checkboxValue);
		if (!showHiddenSelected) {
			//if(inputHiddenForCheckBox.getValue().equals("false")){
			htmlCheckbox.setSelected(false);
			this.setShowHiddenSelected(false);
		} else {
			htmlCheckbox.setSelected(true);
			this.setShowHiddenSelected(true);
		}
		
		if(!(this.isPrintMode() || isViewMode() || this.isContractTypeMandate())){
			htmlCheckbox.setOnclick("unsavedDataFinder()");
		}

		//enhancement ends

		olablType1.getChildren().add(htmlCheckbox);
		olablType1.getChildren().add(otxtType2);

		
		headerPanel.getChildren().add(otxtType1);


    	if(!(this.isPrintMode() || isViewMode() || this.isContractTypeMandate())){
    		//if it is not (print or view or mandate) means need checkbox
    		headerPanel.getChildren().add(olablType1);
   		}
	

		return headerPanel;
	}

	/**
	 * Sets the headerPanel
	 * 
	 * @param headerPanel.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}
	
	public HtmlPanelGrid getPanelFromList(List benefitComponentList,
            boolean hide) {
    	//final String DELETE_IMAGE_PATH = "../../images/delete.gif";
        String type = this.getContractKeyObject().getContractType();
        if(null != type && type.equals("MANDATE")){
        	this.setContractTypeMandate(true);
        }
        panel = new HtmlPanelGrid();
        panel.setWidth("100%");
        if(this.isPrintMode() || isViewMode() || this.isContractTypeMandate()){
        	 panel.setColumns(1);
        }
		else{
			panel.setColumns(2);
		}
        panel.setBorder(0);
       
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        panel.setBgcolor("#cccccc");

        if(!(this.isPrintMode() ) || this.isContractTypeMandate()){
        	panel.setBgcolor("#cccccc");
        }
        
        StringBuffer rows = new StringBuffer();//For setting row style
        int count =0;
        if (benefitComponentList != null) {
        	storeFlagStatusForBC(benefitComponentList);
            for (int i = 0; i < benefitComponentList.size(); i++) {
            ProductTreeBenefitComponents benefitComponentBO = (ProductTreeBenefitComponents) benefitComponentList
                        .get(i);
            
            /**Putting benefit component hide unhide flag into Map :: by KK**/
            Object keyvalue=benefitComponentBO.getBenefitComponentId()+"||"+getContractKeyObject().getDateSegmentId();
            Object valueflag =benefitComponentBO.getBenefitComponentHideFlag();
            benefitHideUnhideFlagMap.put(keyvalue,valueflag);
            
            /**end :: by KK**/
            
             HtmlSelectBooleanCheckbox checkBox = new HtmlSelectBooleanCheckbox(); 
             HtmlOutputText htmlOutputText1 = new HtmlOutputText();
                //htmlOutputText1.setStyleClass("mandatoryNormal");
                htmlOutputText1.setId("benefitComponentDesc" + i);
                htmlOutputText1.setValue(benefitComponentBO.getBenefitComponentDesc());
                HtmlInputHidden htmlInputHiddenForBenefitComponentId = new HtmlInputHidden();
                if(! ("GENERAL BENEFITS".equals( benefitComponentBO.getBenefitComponentDesc())
                		|| ("GENERAL PROVISIONS".equals( benefitComponentBO.getBenefitComponentDesc())))){
	                
	                htmlInputHiddenForBenefitComponentId.setValue(new Integer(
	                		benefitComponentBO.getBenefitComponentId()));
	                htmlInputHiddenForBenefitComponentId
	                        .setId("htmlInputHiddenForBenefitComponentID" + i);
	                ValueBinding optionItem = FacesContext.getCurrentInstance()
	                        .getApplication().createValueBinding(
	                                "#{contractComponentGeneralInfoBackingBean.datafieldMapForBenefitComponentId["
	                                        + i + "]}");
	                htmlInputHiddenForBenefitComponentId.setValueBinding("value", optionItem);
                }

                checkBox.setId("showHidden"+i);
                //if the hide check box is selected
                if((null == benefitComponentBO.getBenefitComponentHideFlag())||(benefitComponentBO.getBenefitComponentHideFlag().equals("T"))){
                	
                		checkBox.setSelected(true);
                }
                else{
                	checkBox.setSelected(false);
                }
                
               
                
                ValueBinding hiddenFlag = FacesContext.getCurrentInstance()
							.getApplication().createValueBinding(
							"#{contractComponentGeneralInfoBackingBean.datafieldMapForBenComponentHideFlag["
                               + i + "]}");	
                				               
                checkBox.setValueBinding("value",hiddenFlag);
                     
                HtmlOutputLabel htmlOutputLabel = new HtmlOutputLabel();
                htmlOutputLabel.setId("htmlOutputLabel"+RandomStringUtils.randomAlphanumeric(15));
                
                if(hide == false && benefitComponentBO.getBenefitComponentHideFlag().equals("T") ){
                	//t
                	
                	
                }
               else{
               	
               
               	count++;
               	if(hide == false && benefitComponentBO.getBenefitComponentHideFlag().equals("F")){
        			
        			if(count%2==1)
        				rows.append("dataTableEvenRow");
        			else
        				rows.append("dataTableOddRow");
        			
        			if(i != benefitComponentList.size()-1)
        				rows.append(",");
               	}
               	
               	if(hide == true){
               		
               		if((benefitComponentBO.getBenefitComponentHideFlag().equals("T")) && i%2!=1)
        				rows.append("hiddenFieldLevelDisplay");
        			else if(benefitComponentBO.getBenefitComponentHideFlag().equals("T"))
        				rows.append("hiddenFieldDisplay");
        			else if(i%2==1)
        				rows.append("dataTableOddRow");
        			else
        				rows.append("dataTableEvenRow");
        			
        			if(i != benefitComponentList.size()-1)
        				rows.append(",");
               		
               	}
               		

               	htmlOutputLabel.getChildren().add(htmlOutputText1);
               	panel.getChildren().add(htmlOutputLabel);
                if( "GENERAL BENEFITS".equals( benefitComponentBO.getBenefitComponentDesc())
                		|| ("GENERAL PROVISIONS".equals( benefitComponentBO.getBenefitComponentDesc())))
						{
                	HtmlOutputLabel htmlOutputLabelOne = new HtmlOutputLabel();
                	 if(!(this.isPrintMode() || isViewMode() || this.isContractTypeMandate())){
                	 	panel.getChildren().add(htmlOutputLabelOne);
	           		}
                }else{
                	htmlOutputLabel.getChildren().add(htmlInputHiddenForBenefitComponentId);
                	if(!(this.isPrintMode() || isViewMode() || this.isContractTypeMandate())){
	           			panel.getChildren().add(checkBox);
	           		}
	                
                } 	
                
               }

            }
            getContractSession().setBenefitHideShowFlagMap(benefitHideUnhideFlagMap);
        }
        panel.setRowClasses(rows.toString()); 
        return panel;
    }
   



	/**
	 * @param benefitComponentList2 
	 */
	private void storeFlagStatusForBC(List list) {
		if(null != list && list.size() > 0){
			StringBuffer buffer = new StringBuffer();
			for(Iterator i=list.iterator();i.hasNext();){
				ProductTreeBenefitComponents benefits = (ProductTreeBenefitComponents)i.next();
				buffer.append(benefits.getBenefitComponentId());
				buffer.append("~");
				buffer.append(benefits.getBenefitComponentHideFlag());
				if(i.hasNext())
					buffer.append(":");
			}
			flasgStatusForBC = buffer.toString();
		}
	}
	
	private Map loadFlagStatusForBC(){
		Map map = new HashMap();
		if(null != flasgStatusForBC && !"".equals(flasgStatusForBC.trim())){
			String[] flags = flasgStatusForBC.split(":");
			for(int i =0;i< flags.length;i++){
				String[] array = flags[i].split("~");
				if(array.length == 2){
					map.put(array[0],array[1]);
				}
			}
		}
		return map;
	}






	public List retrieveBenefitComponentsHiddenAndUnhidden(boolean hide){
    	
//    	BenefitComponentBO benefitComponent=null;
        
      //sets the required values to the request
        RetrieveContractBenefitComponentRequest retrieveContractBenefitComponentRequest = (RetrieveContractBenefitComponentRequest)
		this.getServiceRequest(ServiceManager.CONTRACT_BENEFIT_COMPONENT);
        //retrieveContractBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_COMPONENT_RETRIEVE);
        
        int productId = getContractKeyObject().getProductId();
        int dateSegmentId = getContractKeyObject().getDateSegmentId();
       
        retrieveContractBenefitComponentRequest.setDateSegmentId(dateSegmentId);
        retrieveContractBenefitComponentRequest.setProductId(productId);
        retrieveContractBenefitComponentRequest.setAction(RetrieveContractBenefitComponentRequest.RETRIEVE_CONTRACT_BENEFITCOMPONENT_ALL);
        
        //executes the service and fetches the response
        RetrieveContractBenefitComponentResponse contractBenefitComponentResponse = null;     
        contractBenefitComponentResponse =(RetrieveContractBenefitComponentResponse)this.executeService(retrieveContractBenefitComponentRequest);
       
        //gets the benefit component details from the response
        if(null != contractBenefitComponentResponse){
        	 if(null != contractBenefitComponentResponse.getContract()
        	 		&& null != contractBenefitComponentResponse.getContract().getComponentList()){
		        	this.benefitComponentList = contractBenefitComponentResponse.getContract().getComponentList();
        	 }
        	 else{
        	 	this.benefitComponentList = new ArrayList();
        	 }
        }
    	addAllMessagesToRequest( this.validationMessages );
        getPanelFromList(this.benefitComponentList, hide);
        return benefitComponentList;

    }
    
    
    
    public String saveBenefitComponentList(){
    	
    	List benefitComponentList = getBenefitComponentListHideUnhide();
    	RetrieveContractBenefitComponentRequest retrieveContractBenefitComponentRequest = (RetrieveContractBenefitComponentRequest)
		this.getServiceRequest(ServiceManager.CONTRACT_BENEFIT_COMPONENT);
        //retrieveContractBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_COMPONENT_RETRIEVE);
    	BenefitComponentVO componentVO = new BenefitComponentVO();
        int productId = getContractKeyObject().getProductId();
        int dateSegmentId = getContractKeyObject().getDateSegmentId();
        int contractSysId = getContractKeyObject().getContractSysId();
        retrieveContractBenefitComponentRequest.setProductId(productId);
        retrieveContractBenefitComponentRequest.setDateSegmentId(dateSegmentId);
        componentVO.setBenefitComponentFlagList(benefitComponentList);
        retrieveContractBenefitComponentRequest.setBenefitComponentVO(componentVO);
        retrieveContractBenefitComponentRequest.setAction(RetrieveContractBenefitComponentRequest.UPDATE_CONTRACT_BENEFITCOMPONENT_FLAGS);
        retrieveContractBenefitComponentRequest.setContractKeyObject(getContractSession().getContractKeyObject());
        
        /** Setting the Map containing Benefit Hide Unhide flag while loading to request object :: by KK**/
        	retrieveContractBenefitComponentRequest.setBenefitHideUnhideFlagMap(getContractSession().getBenefitHideShowFlagMap());
        /**end :: by KK**/
        
        //executes the service and fetches the response
        RetrieveContractBenefitComponentResponse contractBenefitComponentResponse = null;    
        
        if(null != benefitComponentList){
        	updateAMVForBCList(retrieveContractBenefitComponentRequest);
        	contractBenefitComponentResponse =(RetrieveContractBenefitComponentResponse)this.executeService(retrieveContractBenefitComponentRequest);
        	 if(null != contractBenefitComponentResponse){
           	 if(null != contractBenefitComponentResponse.getContract()
           	 		&& null != contractBenefitComponentResponse.getContract().getComponentList()){
   		        	this.benefitComponentList = contractBenefitComponentResponse.getContract().getComponentList();
   		        	super.updateTreeStructure();
           	 }
           	 else{
           	 	this.benefitComponentList = new ArrayList();
           	 }
           	addAllMessagesToRequest(contractBenefitComponentResponse.getMessages());
           	getRequest().setAttribute("RETAIN_Value", "");	
           }
        	
        }
        getPanelFromList(this.benefitComponentList, showHiddenSelected);
        if(!isViewMode())
			return "componentListHideOrUnhide";
       else 
           return "componentListHideOrUnhideView";
    }
    /**
	 * @param retrieveContractBenefitComponentRequest
	 */
	private void updateAMVForBCList(RetrieveContractBenefitComponentRequest req) {
		if(null != req  && req.getBenefitComponentVO().getBenefitComponentFlagList().size() > 0 ){
			Map map = loadFlagStatusForBC();
			boolean isChanged = false;
			List changedIds = new ArrayList();
			for(Iterator i=req.getBenefitComponentVO().getBenefitComponentFlagList().iterator();i.hasNext();){
				ContractBenefitComponents components = (ContractBenefitComponents)i.next();
				if("T".equals(map.get(components.getBenefitComponentId() + "")) && "F".equals(components.getBenefitComponentHideFlag())){
					isChanged = true;
					changedIds.add(new Integer(components.getBenefitComponentId()));
				}
			}
			if(isChanged){
				req.setChanged(true);
				req.setChangedIds(changedIds);
			}
		}
		
	}

	/**
     * This function is to get the over ridden list 
     * @return
     */
    private List getBenefitComponentListHideUnhide() {
		
		
		List benefitComponentList = new ArrayList();
		

		Set benefitComponentIdSet = datafieldMapForBenefitComponentId .keySet();
		Iterator benCompIdIter = benefitComponentIdSet.iterator();

		Set hideFlagSet = datafieldMapForBenComponentHideFlag  .keySet();
		Iterator hideFlagIterator = hideFlagSet.iterator();

		
		while(hideFlagIterator.hasNext() && benCompIdIter.hasNext() ){
			
			Long iterationId = (Long)benCompIdIter.next();
			ContractBenefitComponents componentVO = new ContractBenefitComponents();
			

			String benefitComponentId = (String) datafieldMapForBenefitComponentId.get(iterationId);
			componentVO.setBenefitComponentId(new Integer(benefitComponentId).intValue());
			
			Long iterationIdFlag= (Long)hideFlagIterator.next();
			String hiddenFlag;
			Boolean hideStatus = (Boolean)datafieldMapForBenComponentHideFlag.get(iterationIdFlag);
			if("true".equals(hideStatus.toString())){
				hiddenFlag = "T";
			}else{
				hiddenFlag = "F";
			}
			//String hiddenFlag = (String) datafieldmapForQuestionHideFlag.get(iterationId);
			
			// Set the value of the question id
			componentVO.setBenefitComponentHideFlag(hiddenFlag);
			
			
			benefitComponentList.add(componentVO);
		}
		
		// Return the list
		return benefitComponentList;
		
	}
    /**
     * This method retrieves the benefit component information of the benefit component
     * clicked from the tree
     * 
     * @return String
     */
    public String retrieveBenefitComponent(){ 
        BenefitComponentBO benefitComponent=null;
        
        //sets the required values to the request
        RetrieveContractBenefitComponentRequest retrieveContractBenefitComponentRequest = (RetrieveContractBenefitComponentRequest)
		this.getServiceRequest(ServiceManager.CONTRACT_BENEFIT_COMPONENT);
        //retrieveContractBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_COMPONENT_RETRIEVE);
        retrieveContractBenefitComponentRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
        retrieveContractBenefitComponentRequest.setAction(RetrieveContractBenefitComponentRequest.RETRIEVE_BENEFITCOMPONENT_INFORMATION);
       // retrieveContractBenefitComponentRequest.setBenefitComponentId(17);
        
        //executes the service and fetches the response
        RetrieveContractBenefitComponentResponse contractBenefitComponentResponse = null;     
        contractBenefitComponentResponse =(RetrieveContractBenefitComponentResponse)this.executeService(retrieveContractBenefitComponentRequest);
        
        //gets the benefit component details from the response
        if(contractBenefitComponentResponse.getContract().getComponentList().get(0)!=null){
         benefitComponent = (BenefitComponentBO)contractBenefitComponentResponse.getContract().getComponentList().get(0);
        }
        
        //sets values for displaying to the front
        setValuesToBackingBeanForBenefitComponent(benefitComponent);
        
        //fetches the domain details
        DomainDetail domainDetail = contractBenefitComponentResponse.getDetail();
                        
        if (domainDetail != null) {
            this.lineOfBusiness = WPDStringUtil.getTildaString(domainDetail
                    .getLineOfBusiness());
            this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessEntity());
            this.businessGroup = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessGroup());
            /*START CARS*/
            this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                    .getMarketBusinessUnit());
            /*END CARS*/
        }
        
       //checks for view mode or editmode
       if(!isViewMode())
			return "componentGenInfo";
       else 
           return "componentGenInfoView";
		
    }

    public String loadWithHiddenValuesForBenefits(){
    	this.setShowHiddenSelectedForBenefit(true);
    	RetreiveBenefitsContractRequest retreiveBenefitsContractRequest =(RetreiveBenefitsContractRequest)this.getServiceRequest(ServiceManager.CONTRACT_BENEFIT_RETREIVE);
    	retreiveBenefitsContractRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
    	retreiveBenefitsContractRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
    	retreiveBenefitsContractRequest.setShowHidden(true);
    	RetreiveBenefitsContractResponse retreiveBenefitsContractResponse=(RetreiveBenefitsContractResponse)this.executeService(retreiveBenefitsContractRequest);
    	this.benefitList =retreiveBenefitsContractResponse.getBenefitList();
     	getRequest().setAttribute("RETAIN_Value", "");	
    	 return "benefitCustomization";
    }
    
    public String loadWithoutHiddenValuesForBenefits(){
    	this.setShowHiddenSelectedForBenefit(false);
    	RetreiveBenefitsContractRequest retreiveBenefitsContractRequest =(RetreiveBenefitsContractRequest)this.getServiceRequest(ServiceManager.CONTRACT_BENEFIT_RETREIVE);
    	retreiveBenefitsContractRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
    	retreiveBenefitsContractRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
    	retreiveBenefitsContractRequest.setShowHidden(false);
    	RetreiveBenefitsContractResponse retreiveBenefitsContractResponse=(RetreiveBenefitsContractResponse)this.executeService(retreiveBenefitsContractRequest);
    	this.benefitList =retreiveBenefitsContractResponse.getBenefitList();
     	getRequest().setAttribute("RETAIN_Value", "");	
    	return "benefitCustomization";
    }
    
    
    
    /**
     * Sets values to the backing bean for displaying
     * @param benefitComponent
     */
    private void setValuesToBackingBeanForBenefitComponent(
            BenefitComponentBO benefitComponent) {
        this.name = benefitComponent.getName();
        this.description = benefitComponent.getDescription();
        this.effectiveDate = WPDStringUtil.convertDateToString(benefitComponent
                .getEffectiveDate());
        this.expiryDate = WPDStringUtil.convertDateToString(benefitComponent
                .getExpiryDate());
        this.createdBy = benefitComponent.getCreatedUser();
        this.createdDate = benefitComponent.getCreatedTimestamp();
        this.lastUpdatedDate = benefitComponent.getLastUpdatedTimestamp();
        this.updatedBy = benefitComponent.getLastUpdatedUser();
        this.componentType = benefitComponent.getComponentType();
        this.ruleId = benefitComponent.getRuleId();
    	
    	this.ruleDesc = benefitComponent.getRuleDesc();
    	this.ruleType = benefitComponent.getRuleType();
    	
    	if(null!=ruleId && !("").equals(ruleId)&& null!=ruleDesc && !("").equals(ruleDesc)){
    		this.ruleIdNameComb = ruleDesc+'~'+ruleId;
    		this.ruleIdForView = ruleId+'-'+ruleDesc;
    	}else{
    		this.ruleIdNameComb = "";
    		this.ruleIdForView ="";
    	}
//        if(null != benefitComponent.getRuleList()&& benefitComponent.getRuleList().size()>0){
//        	this.ruleId =(String)(benefitComponent.getRuleList().get(0));// .getRuleNameList().get(0));
//        }
        // CR added for benefit component version 
        
        this.benefitComponentVersion = benefitComponent.getVersion();
    }
//    public static String getTildaStringFromRuleTypeList(List ruleTypeItems){
//        
//        if(ruleTypeItems == null)
//            return "";
//        
//        StringBuffer buffer = new StringBuffer();
//        for (int i=0; i<ruleTypeItems.size(); i++) {
//            String element = (String) ruleTypeItems.get(i);
//            if(i!=0){
//                buffer.append("~");
//            }
//            buffer.append(element);
//           // buffer.append("~" + element.getCodeDescText());
//        }
//        return buffer.toString();
//    }
//    
    /**
     * Returns the dummyVariable
     * @return String dummyVariable.
     */
    public String getDummyVariable() {
        return dummyVariable;
    }
    
    /**
     * Sets the dummyVariable
     * @param dummyVariable.
     */
    public void setDummyVariable(String dummyVariable) {
        this.dummyVariable = dummyVariable;
    }

    /**
     * 
     * Returns the businessDomain
     * 
     * @return String businessDomain.
     *  
     */

    public String getBusinessDomain() {
        return businessDomain;
    }

    /**
     * 
     * Returns the businessEntity
     * 
     * @return String businessEntity.
     *  
     */

    public String getBusinessEntity() {
        return businessEntity;
    }

    /**
     * 
     * Returns the businessGroup
     * 
     * @return String businessGroup.
     *  
     */

    public String getBusinessGroup() {
        return businessGroup;
    }

    /**
     * 
     * Returns the createdBy
     * 
     * @return String createdBy.
     *  
     */

    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 
     * Returns the createdDate
     * 
     * @return String createdDate.
     *  
     */

    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * Returns the description
     * 
     * @return String description.
     *  
     */

    public String getDescription() {
        return description;
    }

    /**
     * 
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     *  
     */

    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 
     * Returns the expiryDate
     * 
     * @return String expiryDate.
     *  
     */

    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * 
     * Returns the lastUpdatedDate
     * 
     * @return String lastUpdatedDate.
     *  
     */

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * 
     * Returns the lineOfBusiness
     * 
     * @return String lineOfBusiness.
     *  
     */

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    /**
     * 
     * Returns the name
     * 
     * @return String name.
     *  
     */

    public String getName() {
        return name;
    }

    /**
     * 
     * Returns the updatedBy
     * 
     * @return String updatedBy.
     *  
     */

    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 
     * Sets the businessDomain
     * 
     * @param businessDomain.
     *  
     */

    public void setBusinessDomain(String businessDomain) {
        this.businessDomain = businessDomain;
    }

    /**
     * 
     * Sets the businessEntity
     * 
     * @param businessEntity.
     *  
     */

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    /**
     * 
     * Sets the businessGroup
     * 
     * @param businessGroup.
     *  
     */

    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }

    /**
     * 
     * Sets the createdBy
     * 
     * @param createdBy.
     *  
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 
     * Sets the createdDate
     * 
     * @param createdDate.
     *  
     */

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 
     * Sets the description
     * 
     * @param description.
     *  
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     *  
     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 
     * Sets the expiryDate
     * 
     * @param expiryDate.
     *  
     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 
     * Sets the lastUpdatedDate
     * 
     * @param lastUpdatedDate.
     *  
     */

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * 
     * Sets the lineOfBusiness
     * 
     * @param lineOfBusiness.
     *  
     */

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    /**
     * 
     * Sets the name
     * 
     * @param name.
     *  
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * Sets the updatedBy
     * 
     * @param updatedBy.
     *  
     */

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    
    /**
	 * @return Returns the valueForPrint.
	 */
	public String getValueForPrint() {
		this.retrieveBenefitComponent();
		return valueForPrint;
	}
	/**
	 * @param valueForPrint The valueForPrint to set.
	 */
	public void setValueForPrint(String valueForPrint) {
		this.valueForPrint = valueForPrint;
	}
	/**
	 * @return Returns the componentType.
	 */
	public String getComponentType() {
		return componentType;
	}
	/**
	 * @param componentType The componentType to set.
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	   
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String loadBenefits(){
		if(this.isViewMode()){
			return "loadBenefitsView";
		}
		return "loadBenefits";
	}
	/**
	 * @return Returns the renderer.
	 */
	public HtmlInputHidden getRenderer() {
		headerPanelForBenefit = new HtmlPanelGrid();
		headerPanelForBenefit.setWidth("100%");
		if("GENERAL BENEFITS".equals(this.getContractSession().getBenefitComponentDesc()) || "GENERAL PROVISIONS".equals(this.getContractSession().getBenefitComponentDesc()) 
				|| ("MANDATE".equals(this.getContractKeyObject().getContractType()))){
			headerPanelForBenefit.setColumns(1);
			this.viewSave = false;
		}else{
		headerPanelForBenefit.setColumns(2);
		}
		headerPanelForBenefit.setBgcolor("#cccccc");
		headerPanelForBenefit.setCellpadding("4");
		headerPanelForBenefit.setCellspacing("1");
		headerPanelForBenefit.setStyleClass("dataTableHeader");
		HtmlOutputText otxtType1 = new HtmlOutputText();
		HtmlOutputText otxtType2 = new HtmlOutputText();
		
		// check box for enhancement
		
		HtmlSelectBooleanCheckbox htmlCheckbox = new HtmlSelectBooleanCheckbox();
		HtmlOutputLabel olablType1 = new HtmlOutputLabel();

		
		otxtType1.setValue("Benefits");
		otxtType1.setId("benefit");
		//otxtType1.setStyleClass("formOutputColumnField");
		
		otxtType2.setValue("Show Hidden");
		// enhancement starts
	//	otxtType4.setValue("Show Hidden");

		htmlCheckbox.setId("showHidden");
		htmlCheckbox.setTitle("showHidden");
		 ValueBinding checkboxValue = FacesContext.getCurrentInstance()
         .getApplication().createValueBinding(
                 "#{contractComponentGeneralInfoBackingBean.showHiddenSelectedForBenefit}");
		 htmlCheckbox.setValueBinding("value", checkboxValue);
		if (!showHiddenSelectedForBenefit) {
			htmlCheckbox.setSelected(false);
			this.setShowHiddenSelectedForBenefit(false);
		} else {
			htmlCheckbox.setSelected(true);
			this.setShowHiddenSelectedForBenefit(true);
		}
		htmlCheckbox.setOnclick("unsavedDataFinder()");
		
		olablType1.getChildren().add(htmlCheckbox);
		olablType1.getChildren().add(otxtType2);
		//enhancement ends

		//headerPanel.setStyleClass("dataTableHeader");
/*		headerPanel.getChildren().add(otxtType1);
		headerPanel.getChildren().add(otxtType2);*/
	

//		olablType1.getChildren().add(htmlCheckbox);
//		olablType1.getChildren().add(otxtType2);
//		olablType1.getChildren().add(otxtType1);
		headerPanelForBenefit.getChildren().add(otxtType1);
		if((!("GENERAL BENEFITS".equals(this.getContractSession().getBenefitComponentDesc()))) && (!("MANDATE".equals(this.getContractKeyObject().getContractType()))) &&
				(!("GENERAL PROVISIONS".equals(this.getContractSession().getBenefitComponentDesc())))){
			headerPanelForBenefit.getChildren().add(olablType1);
		}		
		this.setHeaderPanelForBenefit(headerPanelForBenefit);
		
		// rendering the panel with benefit values
		panelForBenefit = new HtmlPanelGrid();
		panelForBenefit.setWidth("100%");
		if("GENERAL BENEFITS".equals(this.getContractSession().getBenefitComponentDesc()) || ("MANDATE".equals(this.getContractKeyObject().getContractType()))
				|| "GENERAL PROVISIONS".equals(this.getContractSession().getBenefitComponentDesc()) ){
			panelForBenefit.setColumns(1);
		}
		else{
			panelForBenefit.setColumns(2);
		}
		panelForBenefit.setBorder(0);
	       // panel.setStyleClass("outputText");
		panelForBenefit.setCellpadding("3");
		panelForBenefit.setCellspacing("1");
		panelForBenefit.setBgcolor("#cccccc");
	   
	       StringBuffer rows = new StringBuffer(); 
	        
	        if (benefitList != null) {
	        	storeFlagStatus(benefitList);
	            for (int i = 0; i < benefitList.size(); i++) {
	            	ProductTreeStandardBenefits benefitBO = (ProductTreeStandardBenefits) benefitList
	                        .get(i);
	      /**Added for getting benefit hide flag for comparing while updating back to DB*/
	      
	               /* Object keyvalue=benefitBO.getBenefitComponentId()+"||"+getContractKeyObject().getDateSegmentId();
	                Object valueflag =benefitBO.getIsHidden();
	                benefitHideUnhideFlagMap.put(keyvalue,valueflag);*/
	        /**end**/
	                
	            	if((benefitBO.getIsHidden().equals("T")) && i%2!=1)
        				rows.append("hiddenFieldLevelDisplay");
        			else if(benefitBO.getIsHidden().equals("T"))
        				rows.append("hiddenFieldDisplay");
        			else if(i%2==1)
        				rows.append("dataTableOddRow");
        			else
        				rows.append("dataTableEvenRow");
        			
        			if(i != benefitList.size()-1)
        				rows.append(",");
	             HtmlSelectBooleanCheckbox checkBox = new HtmlSelectBooleanCheckbox(); 
	             HtmlOutputText htmlOutputText1 = new HtmlOutputText();
	               // htmlOutputText1.setStyleClass("formOutputColumnField");
	                htmlOutputText1.setId("benefit" + i);
	                htmlOutputText1.setValue(benefitBO.getStandardBenefitDesc());

	                HtmlInputHidden htmlInputHiddenForBenefitId = new HtmlInputHidden();
	                htmlInputHiddenForBenefitId.setValue(new Integer(
	                		benefitBO.getStandardBenefitId()));
	                htmlInputHiddenForBenefitId
	                        .setId("htmlInputHiddenForBenefitId" + i);
	                panelForBenefit.getChildren().add(htmlOutputText1);
	                if((!"GENERAL BENEFITS".equals(this.getContractSession().getBenefitComponentDesc())) && (!("MANDATE".equals(this.getContractKeyObject().getContractType())))
	                		&& (!"GENERAL PROVISIONS".equals(this.getContractSession().getBenefitComponentDesc()))){
		                checkBox.setId("Bnft" +i);
		                checkBox.setValue(Boolean.valueOf((benefitBO.getIsHidden().equals("T"))? true:false));
		                ValueBinding valueItem = FacesContext.getCurrentInstance()
		                 .getApplication().createValueBinding(
		                         "#{contractComponentGeneralInfoBackingBean.benefitMap["
		                                +benefitBO.getStandardBenefitId()+"]}");
		                checkBox.setValueBinding("value", valueItem);
		                panelForBenefit.getChildren().add(checkBox);
	                }
	               
	               
	            }
	            getContractSession().setBenefitHideShowFlagMap(benefitHideUnhideFlagMap);
	        }
	        this.setPanelForBenefit(panelForBenefit);
	        panelForBenefit.setRowClasses(rows.toString());
	        renderer.setValue("benefitCustomization");
	        return renderer;
		//return "benefitCustomization";
	}
	
	/**
	 * @param benefitList2
	 */
	private void storeFlagStatus(List list) {
		if(null != list && list.size() > 0){
			StringBuffer buffer = new StringBuffer();
			for(Iterator i=list.iterator();i.hasNext();){
				ProductTreeStandardBenefits benefits = (ProductTreeStandardBenefits)i.next();
				buffer.append(benefits.getStandardBenefitId());
				buffer.append("~");
				buffer.append(benefits.getIsHidden());
				if(i.hasNext())
					buffer.append(":");
			}
			flasgStatus = buffer.toString();
		}
		
	}
	
	private Map loadFlagStatus(){
		Map map = new HashMap();
		if(null != flasgStatus && !"".equals(flasgStatus.trim())){
			String[] flags = flasgStatus.split(":");
			for(int i =0;i< flags.length;i++){
				String[] array = flags[i].split("~");
				if(array.length == 2){
					map.put(array[0],array[1]);
				}
			}
		}
		return map;
	}





	/**
	 * @return Updates the benefits.
	 */
	public String updateBenefits(){
		
			Iterator itr = benefitMap.keySet().iterator();
			List benefitListForUpdate = new ArrayList();
			
			Map map = loadFlagStatus();
			boolean isChanged = false;
			List changedIds = new ArrayList();
			
			while (itr.hasNext()){
				int key = (int)((Long)itr.next()).longValue(); 
				boolean oldFlag = "T".equals(map.get(key + ""));
				boolean isHidden = ((Boolean)benefitMap.get(new Long(key))).booleanValue();
				if(oldFlag && !isHidden){
					isChanged = true;
					changedIds.add(new Integer(key));
				}
				BenefitCustomizationBO bo = new BenefitCustomizationBO();
				bo.setBenefitId(key);
				bo.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
				bo.setBenefitComponentId(this.getBenefitComponentIdFromSession());
				bo.setProductId(getContractKeyObject().getProductId());
				bo.setIsHidden(isHidden? "T":"F");
				benefitListForUpdate.add(bo);
			}
			if(benefitListForUpdate.size() > 0){
				BenefitCustomizationRequest benefitCustomizationRequest =(BenefitCustomizationRequest) this.getServiceRequest(ServiceManager.BENEFIT_CUSTOMIZATION);
				benefitCustomizationRequest.setBenefitList(benefitListForUpdate);
				benefitCustomizationRequest.setBenefitComponentId(this.getBenefitComponentIdFromSession());
				benefitCustomizationRequest.setBenefitComponentDesc(this.getContractSession().getBenefitComponentDesc());
				benefitCustomizationRequest.setDateSegmentId(this.getContractKeyObject().getDateSegmentId());
				benefitCustomizationRequest.setShowHidden(this.showHiddenSelectedForBenefit);
				benefitCustomizationRequest.setContractKeyObject(getContractSession().getContractKeyObject());
				
				/** Added for Flag check for Benefit **/
				//benefitCustomizationRequest.setBenefitHiddenFlagMap(getContractSession().getBenefitHideShowFlagMap());
				/**end**/
				
				if(isChanged){
					benefitCustomizationRequest.setChanged(true);
					benefitCustomizationRequest.setChangedIds(changedIds);
				}
				BenefitCustomizationResponse response = (BenefitCustomizationResponse)this.executeService(benefitCustomizationRequest);
				if(response != null && (response.isSuccess())){
					updateTreeStructure();
					this.setShowHiddenSelectedForBenefit(response.isShowHidden());
					if(response.isBenefitComponentHide()){
						/*Application application =
							FacesContext.getCurrentInstance().getApplication();
						
						ContractBasicInfoBackingBean contractBasicInfoBackingBean =
						((ContractBasicInfoBackingBean) application.createValueBinding("#{contractBasicInfoBackingBean}").
						getValue(FacesContext.getCurrentInstance()));
						return contractBasicInfoBackingBean.getBasicInfo();*/
						this.validationMessages = response.getMessages();
						return loadBenefitComponentListTab();
					}
					getRequest().setAttribute("RETAIN_Value", "");	
					return "benefitCustomizationUpdated";
				}
				
			}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * @param renderer The renderer to set.
	 */
	public void setRenderer(HtmlInputHidden renderer) {
		this.renderer = renderer;
	}
	
	/**
	 * @return Returns the headerPanelForBenefit.
	 */
	public HtmlPanelGrid getHeaderPanelForBenefit() {
		return headerPanelForBenefit;
	}
	/**
	 * @param headerPanelForBenefit The headerPanelForBenefit to set.
	 */
	public void setHeaderPanelForBenefit(HtmlPanelGrid headerPanelForBenefit) {
		this.headerPanelForBenefit = headerPanelForBenefit;
	}
	/**
	 * @return Returns the panelForBenefit.
	 */
	public HtmlPanelGrid getPanelForBenefit() {
		return panelForBenefit;
	}
	/**
	 * @param panelForBenefit The panelForBenefit to set.
	 */
	public void setPanelForBenefit(HtmlPanelGrid panelForBenefit) {
		this.panelForBenefit = panelForBenefit;
	}

	/**
	 * @return Returns the showHiddenSelectedForBenefit.
	 */
	public boolean isShowHiddenSelectedForBenefit() {
		return showHiddenSelectedForBenefit;
	}
	/**
	 * @param showHiddenSelectedForBenefit The showHiddenSelectedForBenefit to set.
	 */
	public void setShowHiddenSelectedForBenefit(
			boolean showHiddenSelectedForBenefit) {
		this.showHiddenSelectedForBenefit = showHiddenSelectedForBenefit;
	}
	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	/**
	 * @return Returns the benefitMap.
	 */
	public Map getBenefitMap() {
		return benefitMap;
	}
	/**
	 * @param benefitMap The benefitMap to set.
	 */
	public void setBenefitMap(Map benefitMap) {
		this.benefitMap = benefitMap;
	}
	/**
	 * @return Returns the list.
	 */
	public HtmlInputHidden getList() {
		
    	RetreiveBenefitsContractRequest retreiveBenefitsContractRequest =(RetreiveBenefitsContractRequest)this.getServiceRequest(ServiceManager.CONTRACT_BENEFIT_RETREIVE);
    	retreiveBenefitsContractRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
    	retreiveBenefitsContractRequest.setBenefitComponentId(getBenefitComponentIdFromSession());    	
    	retreiveBenefitsContractRequest.setShowHidden(showHiddenSelectedForBenefit);
    	RetreiveBenefitsContractResponse retreiveBenefitsContractResponse=(RetreiveBenefitsContractResponse)this.executeService(retreiveBenefitsContractRequest);
    	if(null !=retreiveBenefitsContractResponse && null != retreiveBenefitsContractResponse.getBenefitList() 
    			&& !retreiveBenefitsContractResponse.getBenefitList().isEmpty()){
	    	this.benefitList =retreiveBenefitsContractResponse.getBenefitList();
    	}else{
    		this.benefitList = null;
    	}
    	List.setValue("benefitCustomization");
    	return List;
		//return "benefitCustomization";
	}
	/**
	 * @param list The list to set.
	 */
	public void setList(HtmlInputHidden list) {
		List = list;
	}
		/**
	 * @return Returns the benefitComponentHideUnhideList.
	 */
	public List getBenefitComponentHideUnhideList() {
		return benefitComponentHideUnhideList;
	}
	/**
	 * @param benefitComponentHideUnhideList The benefitComponentHideUnhideList to set.
	 */
	public void setBenefitComponentHideUnhideList(
			List benefitComponentHideUnhideList) {
		this.benefitComponentHideUnhideList = benefitComponentHideUnhideList;
	}
	/**
	 * @return Returns the showHiddenSelected.
	 */
	public boolean isShowHiddenSelected() {
		return showHiddenSelected;
	}
	/**
	 * @param showHiddenSelected The showHiddenSelected to set.
	 */
	public void setShowHiddenSelected(boolean showHiddenSelected) {
		this.showHiddenSelected = showHiddenSelected;
	}
	/**
	 * @return Returns the benefitComponentList.
	 */
	public List getBenefitComponentList() {
			
		return benefitComponentList;
	}
	/**
	 * @param benefitComponentList The benefitComponentList to set.
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}
	/**
	 * @return Returns the panel.
	 */
	public HtmlPanelGrid getPanel() {
		return panel;
	}
	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}
	/**
	 * @return Returns the datafieldMapForBenComponentHideFlag.
	 */
	public Map getDatafieldMapForBenComponentHideFlag() {
		return datafieldMapForBenComponentHideFlag;
	}
	/**
	 * @param datafieldMapForBenComponentHideFlag The datafieldMapForBenComponentHideFlag to set.
	 */
	public void setDatafieldMapForBenComponentHideFlag(
			Map datafieldMapForBenComponentHideFlag) {
		this.datafieldMapForBenComponentHideFlag = datafieldMapForBenComponentHideFlag;
	}
	/**
	 * @return Returns the datafieldMapForBenefitComponentId.
	 */
	public Map getDatafieldMapForBenefitComponentId() {
		return datafieldMapForBenefitComponentId;
	}
	/**
	 * @param datafieldMapForBenefitComponentId The datafieldMapForBenefitComponentId to set.
	 */
	public void setDatafieldMapForBenefitComponentId(
			Map datafieldMapForBenefitComponentId) {
		this.datafieldMapForBenefitComponentId = datafieldMapForBenefitComponentId;
	}
	/**
	 * @return Returns the stringForCache.
	 */
	public String getStringForCache() {
		if(!isViewMode())
			return "componentListHideOrUnhide";
       else 
           return "componentListHideOrUnhideView";
	
	}
	/**
	 * @param stringForCache The stringForCache to set.
	 */
	public void setStringForCache(String stringForCache) {
		this.stringForCache = stringForCache;
	}
	/**
	 * @return Returns the printMode.
	 */
	public boolean isPrintMode() {
		return printMode;
	}
	/**
	 * @param printMode The printMode to set.
	 */
	public void setPrintMode(boolean printMode) {
		this.printMode = printMode;
	}
	
	/**
	 * @return Returns the breadCrumpText.
	 */
	public String getBreadCrumpText() {
		this.breadCrumpText = "Contract Development >> Contract ("+getContractNameFromSession()+ ") >> Print"; 
		return breadCrumpText;
	}
	/**
	 * @param breadCrumpText The breadCrumpText to set.
	 */
	public void setBreadCrumpText(String breadCrumpText) {
		this.breadCrumpText = breadCrumpText;
	}
	
	/**
	 * @return Returns the printGeneralInfo.
	 */
	public String getPrintGeneralInfo() {
		this.printMode = true;
		this.setShowHiddenSelected(false);
		// call the method to load values
		this.benefitComponentList = retrieveBenefitComponentsHiddenAndUnhidden(false);
		this.benefitComponentList = listToDisplay(this.benefitComponentList);
		
		return "";
	}
	/**
	 * @param printGeneralInfo The printGeneralInfo to set.
	 */
	public void setPrintGeneralInfo(String printGeneralInfo) {
		this.printGeneralInfo = printGeneralInfo;
	}
	
	/**
	 * Returns the printValue
	 * @return String printValue.
	 */
	public String getPrintValue() {
		
        String requestForPrint = getRequest().getParameter("printValueForBenefitComponentList");
        if(null != requestForPrint && !requestForPrint.equals(WebConstants.EMPTY_STRING)){
            printValue = requestForPrint;
        }else{
            printValue = WebConstants.EMPTY_STRING;
        }
        return printValue;
	}
	/**
	 * Sets the printValue
	 * @param printValue.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}
	/**
	 * @return Returns the viewSave.
	 */
	public boolean isViewSave() {
		return viewSave;
	}
	/**
	 * @param viewSave The viewSave to set.
	 */
	public void setViewSave(boolean viewSave) {
		this.viewSave = viewSave;
	}
	/**
	 * @return Returns the contractTypeMandate.
	 */
	public boolean isContractTypeMandate() {
		return contractTypeMandate;
	}
	/**
	 * @param contractTypeMandate The contractTypeMandate to set.
	 */
	public void setContractTypeMandate(boolean contractTypeMandate) {
		this.contractTypeMandate = contractTypeMandate;
	}
	
	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}
	/**
	 * @return Returns the flasgStatus.
	 */
	public String getFlasgStatus() {
		return flasgStatus;
	}
	/**
	 * @param flasgStatus The flasgStatus to set.
	 */
	public void setFlasgStatus(String flasgStatus) {
		this.flasgStatus = flasgStatus;
	}
	/**
	 * @return Returns the flasgStatusForBC.
	 */
	public String getFlasgStatusForBC() {
		return flasgStatusForBC;
	}
	/**
	 * @param flasgStatusForBC The flasgStatusForBC to set.
	 */
	public void setFlasgStatusForBC(String flasgStatusForBC) {
		this.flasgStatusForBC = flasgStatusForBC;
	}
	/**
	 * @return Returns the benefitComponentVersion.
	 */
	public int getBenefitComponentVersion() {
		return benefitComponentVersion;
	}
	/**
	 * @param benefitComponentVersion The benefitComponentVersion to set.
	 */
	public void setBenefitComponentVersion(int benefitComponentVersion) {
		this.benefitComponentVersion = benefitComponentVersion;
	}
	/**
	 * @return Returns the ruleDesc.
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}
	/**
	 * @param ruleDesc The ruleDesc to set.
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	/**
	 * @return Returns the ruleIdNameComb.
	 */
	public String getRuleIdNameComb() {
		return ruleIdNameComb;
	}
	/**
	 * @param ruleIdNameComb The ruleIdNameComb to set.
	 */
	public void setRuleIdNameComb(String ruleIdNameComb) {
		this.ruleIdNameComb = ruleIdNameComb;
	}
	/**
	 * @return Returns the ruleIdForView.
	 */
	public String getRuleIdForView() {
		return ruleIdForView;
	}
	/**
	 * @param ruleIdForView The ruleIdForView to set.
	 */
	public void setRuleIdForView(String ruleIdForView) {
		this.ruleIdForView = ruleIdForView;
	}
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
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
}

