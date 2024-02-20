/*
 * Created on Jan 12, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;

import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodSPSValidationBO;
import com.wellpoint.wpd.common.adminmethod.bo.SPSParameterBO;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSPSRetrieveRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSPSRetrieveResponse;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author u18739
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodSPSValidationBackingBean extends WPDBackingBean {

	private String breadCrumbText;

	private List amRefParamList;
	
	//	CARS START
	private List generalMethodList;
	
	private List tieredMethodList;
	
	private HtmlPanelGrid tierDisplayPanel = new HtmlPanelGrid();	
		
	private List tierCriteriaList; /*CARS 23/3/2010*/
	
	private Map tierSPSMap ;
	 
	private String tierHeading ="";
	//	CARS END
	
	private HtmlPanelGrid displayPanel =new HtmlPanelGrid();	

	private HtmlPanelGrid panelForValidation = new HtmlPanelGrid();

    	
	/**
	 * @return Returns the tierHeading.
	 */
	public String getTierHeading() {
		return tierHeading;
	}
	/**
	 * @param tierHeading The tierHeading to set.
	 */
	public void setTierHeading(String tierHeading) {
		this.tierHeading = tierHeading;
	}
	/**
	 * @return Returns the generalMethodList.
	 */
	public List getGeneralMethodList() {
		return generalMethodList;
	}
	/**
	 * @param generalMethodList The generalMethodList to set.
	 */
	public void setGeneralMethodList(List generalMethodList) {
		this.generalMethodList = generalMethodList;
	}
	/**
	 * @return Returns the tierSPSMap.
	 */
	public Map getTierSPSMap() {
		return tierSPSMap;
	}
	/**
	 * @param tierSPSMap The tierSPSMap to set.
	 */
	public void setTierSPSMap(Map tierSPSMap) {
		this.tierSPSMap = tierSPSMap;
	}
	/**
	 * Method to render the panel grid
	 * @param amRefParamList
	 */
	//CARS START
	private void getSPSValidationPanel(List amRefParamList, HtmlPanelGrid displayPanel) {
		//	CARS END
		AdminMethodSPSValidationBO adminMethodSPSValidationBO = new AdminMethodSPSValidationBO();
		SPSParameterBO sPSParameterBO = new SPSParameterBO();

		displayPanel.setCellpadding("3");
		displayPanel.setCellspacing("1");
		displayPanel.setWidth("100%");
		displayPanel.setColumns(1);
		displayPanel.setBorder(0);
		for (int i = 0; i < amRefParamList.size(); i++) {
			
			HtmlPanelGrid displayPanel1 = new HtmlPanelGrid();
			displayPanel1.setCellpadding("1");
			displayPanel1.setWidth("100%");
			displayPanel1.setColumns(3);
			displayPanel1.setStyle("border:3px solid;color:black;");
			
			HtmlPanelGroup group1 = new HtmlPanelGroup();
			HtmlOutputText outputTextColumn2 = new HtmlOutputText();
			adminMethodSPSValidationBO = (AdminMethodSPSValidationBO) amRefParamList
					.get(i);

			if (0 != adminMethodSPSValidationBO.getSprPrcssStpSysId()) {
				outputTextColumn2 = new HtmlOutputText();
				outputTextColumn2.setValue("SPS Name            : "
						+ adminMethodSPSValidationBO.getSprPrcssStpNm());
				outputTextColumn2.setStyle("font-weight:bold ;width: 200px;");
				group1.getChildren().add(outputTextColumn2);
				displayPanel1.getChildren().add(group1);

				outputTextColumn2 = new HtmlOutputText();
				
				outputTextColumn2.setValue(adminMethodSPSValidationBO.getProductFamily()+" "+"Admin Method       :  "
						+ adminMethodSPSValidationBO.getAdmnMthdDesc());
				outputTextColumn2.setStyle("font-weight:bold ;;width: 400px;");
				group1 = new HtmlPanelGroup();
				group1.getChildren().add(outputTextColumn2);
				displayPanel1.getChildren().add(group1);

				outputTextColumn2 = new HtmlOutputText();
				outputTextColumn2.setValue("");
				group1 = new HtmlPanelGroup();
				group1.getChildren().add(outputTextColumn2);
				displayPanel1.getChildren().add(group1);
			}
						
			Map amRefParamMap = adminMethodSPSValidationBO.getAmRefParamMap();
			Iterator it = amRefParamMap.entrySet().iterator();
			HtmlOutputText outputTextColumn6 = new HtmlOutputText();
			int j = 1;
			while (it.hasNext()) {
				Map.Entry mp = (Map.Entry) it.next();
				List list = (List) mp.getValue();

				HtmlPanelGrid htmlPanelGrid = new HtmlPanelGrid();
				htmlPanelGrid.setWidth("100%");
				htmlPanelGrid.setColumns(1);
				htmlPanelGrid.setBorder(0);
				HtmlPanelGroup group3 = new HtmlPanelGroup();
				HtmlOutputText outputTextColumn5 = new HtmlOutputText();
				
				outputTextColumn5.setValue("Group " + j++);
				outputTextColumn5.setStyle("font-weight:bold ;");
				group3.getChildren().add(outputTextColumn5);
				htmlPanelGrid.getChildren().add(group3);
				for (int k = 0; k < list.size(); k++) {
					sPSParameterBO = (SPSParameterBO) list.get(k);

					HtmlPanelGroup group2 = new HtmlPanelGroup();
					HtmlOutputText outputTextColumn1 = new HtmlOutputText();
					String referenceType=" [ "+sPSParameterBO.getType()+" ] ";
					if("ALL".equalsIgnoreCase(sPSParameterBO.getType()))
						referenceType=" ";
					
					outputTextColumn1.setValue(sPSParameterBO
							.getReferenceDesc()
							+  referenceType );
					HtmlOutputText outputTextColumn3 = new HtmlOutputText();

					group2.getChildren().add(outputTextColumn1);
					outputTextColumn3.setValue("   ");
					HtmlGraphicImage graphicImage = new HtmlGraphicImage();
					if(sPSParameterBO.isCoded())
						graphicImage.setUrl("../../images/action_check.gif");
					else 
						graphicImage.setUrl("../../images/action_delete.gif");
					group2.getChildren().add(graphicImage);

					htmlPanelGrid.getChildren().add(group2);
				}
				displayPanel1.getChildren().add(htmlPanelGrid);
			}
			int mod=amRefParamMap.size()%3;
			for(int ij = 0 ; ij< (3-mod); ij++){
        		group1 = new HtmlPanelGroup();
        		
        		outputTextColumn6 = new HtmlOutputText();
        		outputTextColumn6.setValue("  ");
        		group1.getChildren().add(outputTextColumn6);
        		displayPanel1.getChildren().add(group1);
        	}
			displayPanel.getChildren().add(displayPanel1);
		}
	}
	/*CARS START 23/3/2010*/
	
	
	private void getTierSPSValidationPanel(Map amRefParamMap, HtmlPanelGrid displayPanel2) {
		//	CARS END
		boolean tierExists=false;
		Map tierCriteriaMap = new HashMap(0);
		List tierSysIdList = new ArrayList(0);
		for(int j =0; j<this.tierCriteriaList.size() ; j++){
			   TierDefinitionBO tierDefinitionBO = (TierDefinitionBO)this.tierCriteriaList.get(j);
			   if(!tierCriteriaMap.containsKey(""+tierDefinitionBO.getTierSysId()))
			   {
			   	   tierCriteriaMap.put(""+tierDefinitionBO.getTierSysId(),tierDefinitionBO.getTierDesc()+" "+tierDefinitionBO.getCriteriaName()+" : "+tierDefinitionBO.getCriteriaVal());
			   }
			   else
			   {
			   	   String existingValue = (String)tierCriteriaMap.get(""+tierDefinitionBO.getTierSysId());
			   	   tierCriteriaMap.put(""+tierDefinitionBO.getTierSysId(),existingValue+" - "+tierDefinitionBO.getCriteriaName()+" : "+tierDefinitionBO.getCriteriaVal());
			   }
			}	
		
		displayPanel2.setCellpadding("3");
		displayPanel2.setCellspacing("1");
		displayPanel2.setWidth("100%");
		displayPanel2.setColumns(1);
		displayPanel2.setBorder(0);
		Set tierIdsInSPSMap =new TreeSet(amRefParamMap.keySet());
		Iterator iter1 = tierIdsInSPSMap.iterator();
		while(iter1.hasNext())
		{
			HtmlPanelGrid displayPanel = new HtmlPanelGrid();
		   String tierId = (String)iter1.next();	
		   if(!tierId.equals("-1") )
		   {
		   		AdminMethodSPSValidationBO adminMethodSPSValidationBO = new AdminMethodSPSValidationBO();
		   		SPSParameterBO sPSParameterBO = new SPSParameterBO();
		   		List amRefParamList = (List)amRefParamMap.get(tierId);
		   		
		   		displayPanel.setCellpadding("0");
				displayPanel.setCellspacing("0");
				displayPanel.setWidth("100%");
				displayPanel.setColumns(1);
				displayPanel.setBorder(0);
				
				
				
				

				
			for (int i = 0; i < amRefParamList.size(); i++) {
				
				if(!tierExists){
					tierExists=true;	
				}
				HtmlPanelGrid displayPanel1 = new HtmlPanelGrid();
				displayPanel1.setCellpadding("1");
				displayPanel1.setWidth("100%");
				displayPanel1.setColumns(3);
				displayPanel1.setColumnClasses("column33pct,column33pct,column33pct");
				displayPanel1.setStyle("border:3px solid;color:black;");
				
				HtmlPanelGroup group1 = new HtmlPanelGroup();
				HtmlOutputText outputTextColumn2 = new HtmlOutputText();
				adminMethodSPSValidationBO = (AdminMethodSPSValidationBO) amRefParamList
						.get(i);

				if (0 != adminMethodSPSValidationBO.getSprPrcssStpSysId()) {
					
					String criteriaDetails = (String)tierCriteriaMap.get(String.valueOf(adminMethodSPSValidationBO.getTierSysId()));
					
					group1 = new HtmlPanelGroup();
					outputTextColumn2 = new HtmlOutputText();
					outputTextColumn2.setStyle("font-weight:bold;");
					outputTextColumn2.setValue(" "+criteriaDetails+" ");
					group1.getChildren().add(outputTextColumn2);
					displayPanel1.getChildren().add(group1);
					
					group1 = new HtmlPanelGroup();
					outputTextColumn2 = new HtmlOutputText();
					outputTextColumn2.setStyle("font-weight:bold;");
					outputTextColumn2.setValue("SPS Name : "+ adminMethodSPSValidationBO.getSprPrcssStpNm());
					group1.getChildren().add(outputTextColumn2);
					displayPanel1.getChildren().add(group1);

					outputTextColumn2 = new HtmlOutputText();					
					outputTextColumn2.setValue(adminMethodSPSValidationBO.getProductFamily()+" "+"Admin Method : "+ adminMethodSPSValidationBO.getAdmnMthdDesc());
					outputTextColumn2.setStyle("font-weight:bold;");
					group1 = new HtmlPanelGroup();
					group1.getChildren().add(outputTextColumn2);
					displayPanel1.getChildren().add(group1);

					/*outputTextColumn2 = new HtmlOutputText();
					outputTextColumn2.setValue("");
					group1 = new HtmlPanelGroup();
					group1.getChildren().add(outputTextColumn2);
					displayPanel1.getChildren().add(group1);*/
				}
							
				Map amRefParamMap1 = adminMethodSPSValidationBO.getAmRefParamMap();
				Iterator it = amRefParamMap1.entrySet().iterator();
				HtmlOutputText outputTextColumn6 = new HtmlOutputText();
				int j = 1;
				while (it.hasNext()) {
					Map.Entry mp = (Map.Entry) it.next();
					List list = (List) mp.getValue();

					HtmlPanelGrid htmlPanelGrid = new HtmlPanelGrid();
					htmlPanelGrid.setWidth("100%");
					htmlPanelGrid.setColumns(1);
					htmlPanelGrid.setBorder(0);
					HtmlPanelGroup group3 = new HtmlPanelGroup();
					HtmlOutputText outputTextColumn5 = new HtmlOutputText();
					
					outputTextColumn5.setValue("Group " + j++);
					outputTextColumn5.setStyle("font-weight:bold ;");
					group3.getChildren().add(outputTextColumn5);
					htmlPanelGrid.getChildren().add(group3);
					for (int k = 0; k < list.size(); k++) {
						sPSParameterBO = (SPSParameterBO) list.get(k);

						HtmlPanelGroup group2 = new HtmlPanelGroup();
						HtmlOutputText outputTextColumn1 = new HtmlOutputText();
						String referenceType=" [ "+sPSParameterBO.getType()+" ] ";
						if("ALL".equalsIgnoreCase(sPSParameterBO.getType()))
							referenceType=" ";
						
						outputTextColumn1.setValue(sPSParameterBO
								.getReferenceDesc()
								+  referenceType );
						HtmlOutputText outputTextColumn3 = new HtmlOutputText();

						group2.getChildren().add(outputTextColumn1);
						outputTextColumn3.setValue("   ");
						HtmlGraphicImage graphicImage = new HtmlGraphicImage();
						if(sPSParameterBO.isCoded())
							graphicImage.setUrl("../../images/action_check.gif");
						else 
							graphicImage.setUrl("../../images/action_delete.gif");
						group2.getChildren().add(graphicImage);

						htmlPanelGrid.getChildren().add(group2);
					}
					displayPanel1.getChildren().add(htmlPanelGrid);
				}
				int mod=amRefParamMap1.size()%3;
				for(int ij = 0 ; ij< (3-mod); ij++){
	        		group1 = new HtmlPanelGroup();
	        		
	        		outputTextColumn6 = new HtmlOutputText();
	        		outputTextColumn6.setValue("  ");
	        		group1.getChildren().add(outputTextColumn6);
	        		displayPanel1.getChildren().add(group1);
	        	}
				displayPanel.getChildren().add(displayPanel1);
			}
				
			displayPanel2.getChildren().add(displayPanel);
				
				
				
				
				
				
		   		
		   }
		}
		/*for( int j= 0 ;j<amRefParamMap.size() ;j++ )
		{
			List spsList = (List)amRefParamMap.get(j);
			if(amRefParamMap.)
		}
		//amRefParamList
		AdminMethodSPSValidationBO adminMethodSPSValidationBO = new AdminMethodSPSValidationBO();
		*/
		

		if(tierExists){
			tierHeading=" Tiered Processing Methods ";
		}
		
	}
	
	
	
	/*CARS END 23/3/2010*/
	/**
	 * Method to get the Panel grid
	 * @return
	 */
	public HtmlPanelGrid getPanelForPage() {
		displayPanel = new HtmlPanelGrid();
		String s = validateSPS();
		tierHeading="";
		//CARS START
		if(null != generalMethodList && generalMethodList.size() > 0){ 
			getSPSValidationPanel(this.generalMethodList, displayPanel);
		}
		if(null !=tierSPSMap && tierSPSMap.size()>0)
		{			
			//Set tierIdsInSPSMap =new HashSet(tierSPSMap.keySet());
			//Iterator iter1 = tierIdsInSPSMap.iterator();
			//while(iter1.hasNext())
		//	{
		//	   String tierId = (String)iter1.next();	
	   	//	   List amRefParamList = (List)tierSPSMap.get(tierId);
	   	//	   if(amRefParamList!=null || amRefParamList.size()>0)
			//}
			   getTierSPSValidationPanel(this.tierSPSMap, tierDisplayPanel);	
		}
/*		if(null != tieredMethodList && tieredMethodList.size() > 0){		
			getTierSPSValidationPanel(this.tierNonTierSPSMap, tierDisplayPanel);
		}
*/		
		//CARS END
		return this.displayPanel;
	}
	/**
	 * Method which gets the Admin Method Validation information for a
	 * Standard benefit 
	 * @return
	 */
	private String validateSPS() {

		AdminMethodSPSRetrieveRequest adminMethodSPSRetrieveRequest = (AdminMethodSPSRetrieveRequest) this
				.getServiceRequest(ServiceManager.ADMIN_METHOD_SPS_VALIDATION);
		
		int setEntityId = Integer.parseInt((String)getSession().
				getAttribute("AM_ENTITY_KEY"));
		int productId = Integer.parseInt((String)getSession().
				getAttribute("AM_PRODUCT_ID"));
		int benefitComponentId = Integer.parseInt((String)getSession().
					getAttribute("AM_BC_KEY"));
		int benefitId = Integer.parseInt((String)getSession().
					getAttribute("AM_BENEFIT"));
		int  benAdminId=Integer.parseInt((String)getSession().
				getAttribute("AM_BEN_ADMIN_ID"));
 
		adminMethodSPSRetrieveRequest.setEntityId(setEntityId);
		adminMethodSPSRetrieveRequest.setProdSysId(productId);
		adminMethodSPSRetrieveRequest.setBenCompSysId(benefitComponentId);
		adminMethodSPSRetrieveRequest.setBenSysyId(benefitId);
		adminMethodSPSRetrieveRequest.setBenAdminId(benAdminId);

		AdminMethodSPSRetrieveResponse adminMethodSPSRetrieveResponse = (AdminMethodSPSRetrieveResponse) this
				.executeService(adminMethodSPSRetrieveRequest);
		this.generalMethodList = adminMethodSPSRetrieveResponse.getAdminMethodSPSParameterList();
		this.tierSPSMap =adminMethodSPSRetrieveResponse.getAdminMethodSPSParameterMap();
		if(tierSPSMap!=null && tierSPSMap.size()==0){
			tierSPSMap=null;
		}
		this.tierCriteriaList = adminMethodSPSRetrieveResponse.getTierCriteriaList();
		//this.amRefParamList = adminMethodSPSRetrieveResponse.getAdminMethodSPSParameterList();
		//		CARS START
		/*if(null != tierNonTierSPSMap && tierNonTierSPSMap.size()>0){
			//setProcessingMethodList();
			if(tierNonTierSPSMap.containsKey(String.valueOf(-1)))
			{
				generalMethodList = (List)tierNonTierSPSMap.get(String.valueOf(-1));
			}
		}*/
		
		//		CARS END

		return "";
	}

	//	CARS START
	/**
	 * Method to split the common processing methods to 'General' and 'Tier'
	 *
	 */
	
	private void setProcessingMethodList(){
		for (Iterator iter = amRefParamList.iterator(); iter.hasNext();) {
			AdminMethodSPSValidationBO adminMethodSPSValidationBO = (AdminMethodSPSValidationBO) iter.next();
			if(adminMethodSPSValidationBO.getMethodType().equals(WebConstants.GEN_METHOD)){
				if(null == generalMethodList){
					generalMethodList = new ArrayList();
				}
				generalMethodList.add(adminMethodSPSValidationBO);
			}else{
				if(null == tieredMethodList){
					tieredMethodList = new ArrayList();
				}
				tieredMethodList.add(adminMethodSPSValidationBO);
			}
		}
	}
	//	CARS END
	
	/**
	 * @return Returns the panelForValidation.
	 */
	public HtmlPanelGrid getPanelForValidation() {
		return getPanelForPage();
	}

	/**
	 * @param panelForValidation
	 *            The panelForValidation to set.
	 */
	public void setPanelForValidation(HtmlPanelGrid panelForValidation) {
		this.panelForValidation = panelForValidation;
	}
	/**
	 * @return Returns the breadCrumbText.
	 */
	public String getBreadCrumbText() {
		
		return getBreadCrumData();
	}
	/**
	 * Method to set the BreadCrumText
	 * @return 
	 */
	public void setBreadCrumbText(String breadCrumbText) {
		this.breadCrumbText = breadCrumbText;
		getRequest().setAttribute("breadCrumbText", breadCrumbText);
	}
	/**
	 * Method to get the BreadCrumData
	 * @return 
	 */
	private String getBreadCrumData(){
		
		String entityName ="";
		if(null!=getSession().getAttribute("AM_ENTITY_NAME"))
		entityName = getSession().getAttribute("AM_ENTITY_NAME")
					.toString();
		
		this.setBreadCrumbText("Contract Development >> "+"Contract "+" ("+entityName+") >> Edit (Admin Method Validation Errors)");
		
		return (this.breadCrumbText);
	}
	/**
	 * Method to navigate to intermediate page
	 * @return
	 */
	 public String loadAMValidation() {
    	String   entityType =     getSession().getAttribute("AM_ENTITY_TYPE").toString();
    	String 	entityName = getSession().getAttribute("AM_ENTITY_NAME").toString();
    	this.setBreadCrumbText("Contract Development >> "+"Contract "+"  ("+entityName+") >> Edit (Admin Method Validation Errors)");
    	return "success";
    	
    }
	/**
	 * Method to navigate to AdminMethodSPSValidationpopup page
	 * @return
	 */
	 public String loadNextPageContract(){
	 	return "success";
	 }
	 
	 /**
		 * Method to navigate to AdminMethodSPSValidationPrint page
		 * @return
		 */
		 public String loadValidationContract(){
		 	return "success";
		 }
		 
		 //		CARS START	 
	/**
	 * @return Returns the tierDisplayPanel.
	 */
	public HtmlPanelGrid getTierDisplayPanel() {
		return tierDisplayPanel;
	}
	/**
	 * @param tierDisplayPanel The tierDisplayPanel to set.
	 */
	public void setTierDisplayPanel(HtmlPanelGrid tierDisplayPanel) {
		this.tierDisplayPanel = tierDisplayPanel;
	}
//	CARS END
    /**
     * @return Returns the tieredMethodList.
     */
    public List getTieredMethodList() {
        return tieredMethodList;
    }
    /**
     * @param tieredMethodList The tieredMethodList to set.
     */
    public void setTieredMethodList(List tieredMethodList) {
        this.tieredMethodList = tieredMethodList;
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
}