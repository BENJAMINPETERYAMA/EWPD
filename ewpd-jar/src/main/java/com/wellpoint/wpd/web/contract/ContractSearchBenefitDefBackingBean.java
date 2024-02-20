/*
 * ContractSearchBenefitDefBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitDefinitionRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitDefenition;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSearchBenefitDefBackingBean extends ContractBackingBean{


        
        private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

        private HtmlPanelGrid panel=new HtmlPanelGrid();
        
        private Map benefitValueMap = new LinkedHashMap();
        
        private EntityBenefitDefenition benefitDefinition = null;
        
        List benefitLevelList;
        
        List benefitLineList;
        
        List benefitDefinitionsList;
        
        List deleteLevelList;
        
        private String printValue;
        
        //private String dummyVar;
        
        private HtmlInputHidden dummyVar;
    	
    	public ContractSearchBenefitDefBackingBean(){
        	//checks for view mode or edit mode to set the bread crumb text
  /*  	    if(isViewMode()){
        		this.setBreadCumbTextForView();
        	}else{
        		this.setBreadCumbTextForEdit();
        	}*/
    	   
        }
       
        /**
         * This method returns the value which is required for Benefit Definitions page print
         * @return Returns the printValue.
         */
        public String getPrintValue() {
            
            Logger.logInfo("entered method getPrintValue");
            
            String requestForPrint = (String)getRequest().getAttribute("printValueForBenDet");
            if(null != requestForPrint && !requestForPrint.equals("")){
                printValue = requestForPrint;
            }else{
                printValue = "";
            }
            return printValue;
        }
        /**
         * @param printValue The printValue to set.
         */
        public void setPrintValue(String printValue) {
            this.printValue = printValue;
        }
    	/**
    	 * @return Returns the deleteLevelList.
    	 */
    	public List getDeleteLevelList() {
    		return deleteLevelList;
    	}
    	/**
    	 * @param deleteLevelList The deleteLevelList to set.
    	 */
    	public void setDeleteLevelList(List deleteLevelList) {
    		this.deleteLevelList = deleteLevelList;
    	}
        private Map levelIdMap = new LinkedHashMap();
        
        private String levelsToDelete;
        
        
        
        
    	/**
    	 * @return Returns the levelIdMap.
    	 */
    	public Map getLevelIdMap() {
    		return levelIdMap;
    	}
    	/**
    	 * @param levelIdMap The levelIdMap to set.
    	 */
    	public void setLevelIdMap(Map levelIdMap) {
    		this.levelIdMap = levelIdMap;
    	}
    	/**
    	 * @return Returns the levelsToDelete.
    	 */
    	public String getLevelsToDelete() {
    		return levelsToDelete;
    	}
    	/**
    	 * @param levelsToDelete The levelsToDelete to set.
    	 */
    	public void setLevelsToDelete(String levelsToDelete) {
    		this.levelsToDelete = levelsToDelete;
    	}
    	
        /** This method fetches the benefitDefinition List which contains levels and its corresponding lines
         * Returns the benefitDefinitiosList
         * @return List benefitDefinitiosList.
         */
        public List getBenefitDefinitionsList() {
            
            Logger.logInfo("entered method getBenefitDefinitionsList");
            
            RetrieveContractBenefitDefinitionRequest retrieveContractBenefitDefinitionRequest=(RetrieveContractBenefitDefinitionRequest)this.
    							getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_BENEFIT_DEFINITION_REQUEST);
            
            retrieveContractBenefitDefinitionRequest.setBenefitId(getContractSearchSession().getBenefitId());
            retrieveContractBenefitDefinitionRequest.setBenefitComponentId(getContractSearchSession().getBenefitComponentId());
            retrieveContractBenefitDefinitionRequest.setProductId(getContractSearchSession().getProductId());
            
            
            RetrieveContractBenefitDefinitionResponse retrieveContractBenefitDefinitionResponse = null;
            retrieveContractBenefitDefinitionResponse = (RetrieveContractBenefitDefinitionResponse) executeService(retrieveContractBenefitDefinitionRequest);
            
            List benefitDefinitions=new ArrayList();
            if(null!=retrieveContractBenefitDefinitionResponse)
            benefitDefinitions=retrieveContractBenefitDefinitionResponse.getBenefitDefinitionsList();
            
                    
            return benefitDefinitions;
        }
        
      
        /**
         * Sets the benefitDefinitionsList
         * @param benefitDefinitionsList.
         */
        public void setBenefitDefinitionsList(List benefitDefinitionsList) {
            this.benefitDefinitionsList = benefitDefinitionsList;
        }
        /**
         * Returns the benefitDefinition
         * @return EntityBenefitDefenition benefitDefinition.
         */
        
        public EntityBenefitDefenition getBenefitDefinition() {        
            return benefitDefinition;
        }
        
        /**
         * Sets the benefitDefinition
         * @param benefitDefinition.
         */
        public void setBenefitDefinition(EntityBenefitDefenition benefitDefinition) {
            this.benefitDefinition = benefitDefinition;
        }
        
        /**
         * Returns the benefitLevelList
         * @return List benefitLevelList.
         */
        public List getBenefitLevelList() {
            return benefitLevelList;
        }
        
        /**
         * Sets the benefitLevelList
         * @param benefitLevelList.
         */
        public void setBenefitLevelList(List benefitLevelList) {
            this.benefitLevelList = benefitLevelList;
        }
        
        /**
         * Returns the benefitLineList
         * @return List benefitLineList.
         */
        public List getBenefitLineList() {
            return benefitLineList;
        }
        
        /**
         * Sets the benefitLineList
         * @param benefitLineList.
         */
        public void setBenefitLineList(List benefitLineList) {
            this.benefitLineList = benefitLineList;
        }
        
        /**
    	 * Returns the headerPanel
    	 * @return HtmlPanelGrid headerPanel.
    	 */
        public HtmlPanelGrid getHeaderPanel() {
            
           Logger.logInfo("entered method getHeaderPanel");
           
            //sets the string which contains the levels to delete to null
          if(null!=this.levelsToDelete){
                this.levelsToDelete=null;
            }   
                
            headerPanel = new HtmlPanelGrid();
            headerPanel.setWidth("850");
            headerPanel.setColumns(7);
            headerPanel.setBorder(0);
            headerPanel.setCellpadding("3");
            headerPanel.setCellspacing("1");
            headerPanel.setBgcolor("#cccccc");
            
           
            HtmlOutputText headerText1 = new HtmlOutputText();
            HtmlOutputText headerText2 = new HtmlOutputText();
            HtmlOutputText headerText3 = new HtmlOutputText();
            HtmlOutputText headerText4 = new HtmlOutputText();
            HtmlOutputText headerText5 = new HtmlOutputText();
            HtmlOutputText headerText6 = new HtmlOutputText();
            HtmlOutputText headerText7 = new HtmlOutputText();
           
            
            headerText1.setValue("Description");
            headerText1.setId("desc");

            headerText2.setValue("Term");
            headerText2.setId("term");

            headerText3.setValue("Qualifier");
            headerText3.setId("qualifier");

            headerText4.setValue("PVA");
            headerText4.setId("pva");
            
            headerText5.setValue("Benefit Value");
            headerText5.setId("bnftValue");
            
            headerText6.setValue("Reference");
            headerText6.setId("ref");
            headerText7.setValue(" ");
            headerPanel.setStyleClass("dataTableHeader");
            headerPanel.getChildren().add(headerText1);
            headerPanel.getChildren().add(headerText2);
            headerPanel.getChildren().add(headerText3);
            headerPanel.getChildren().add(headerText4);
            headerPanel.getChildren().add(headerText5);
            headerPanel.getChildren().add(headerText6);
            headerPanel.getChildren().add(headerText7);
           
            return headerPanel;
        }
        /**
         * Sets the headerPanel
         * @param headerPanel.
         */
        public void setHeaderPanel(HtmlPanelGrid headerPanel) {
            this.headerPanel = headerPanel;
        }
        
        
        /**
    	 * This method returns the panel for benefit definitions.
    	 * 
    	 * Returns the Panel
    	 * @return HtmlPanelGrid Panel.
    	 */
        public HtmlPanelGrid getPanel(){
        	
          Logger.logInfo("entered method getPanel");  
          
          int rowNumber = 0;
          //  EntityBenefitDefenition benefitDefinitionWrapper = this.getBenefitDefinition();
            List benefitDefinitonsList=this.getBenefitDefinitionsList();
            
            
            //This method gets the values from the benefit definiton List and sets it to the level list and line list
            getValuesFromDefinitonList(benefitDefinitonsList);
            
           
          
           
            panel = new HtmlPanelGrid();
            panel.setWidth("850");
            panel.setColumns(7);
            panel.setBorder(0);
            panel.setCellpadding("3");
            panel.setCellspacing("1");
            panel.setBgcolor("#cccccc");
            
            StringBuffer rows=new StringBuffer();
            
            
            //setting values to benefit levels
          
            
            int size = benefitLevelList.size();

            //iterating to get the benefit levels
            for (int i = 0; i < size; i++) {
            	rowNumber++;
                rows.append("dataTableEvenRow");
                
                //a benefit level is selected
                BenefitLevel benefitLevelValues = (BenefitLevel) benefitLevelList
                        .get(i);

                          
                //gets the benefit lines of a benefit level
                List benefitLines=benefitLevelValues.getBenefitLines();
                
                //setting the benefit level values to the panel grid
                setBenefitLevelValuesToGrid(i, benefitLevelValues, benefitLines.size(),rowNumber);
                
                
                
                if(benefitLines.size()!=0)
                    rows.append(",");
                //iterating to get the individual benefit lines
                for(int j=0;j<benefitLines.size();j++){
                	rowNumber++;
                   
                    rows.append("dataTableOddRow");
                    
                    
                    if(i<(size-1))
                        rows.append(",");
                    else if(j<(benefitLines.size()-1))
                        rows.append(",");
               
                    //selects an individual benefit line
                    BenefitLine benefitLineValues = (BenefitLine)benefitLines.get(j);
                    
                                    
                    //sets the benefit lines of a benefit level to the panle grid
                    setBenefitLineValuesToGrid(benefitLevelValues, j, benefitLineValues,i);
                    
                }
                
              
            }
            
            panel.setRowClasses(rows.toString());

            return panel;
            
        }
        
        
        /** This method gets the values from the benefit definiton List and sets it to the level list and line list
         * @param testList
         */
        private void getValuesFromDefinitonList(List benefitDefinitionsList) {
            
            Logger.logInfo("entered method getValuesFromDefinitionList");
            
            // TODO Auto-generated method stub
            benefitLevelList=new ArrayList();
            for(int i=0;i<benefitDefinitionsList.size();i++){
                BenefitLine entityBenefitLine=(BenefitLine)benefitDefinitionsList.get(i);
                //sets values to the benefitLevel List
                setValuesToBenefitLevel(entityBenefitLine,benefitLevelList);
               
            }
        }

        
        /**This method sets values to the benefit level List
         * @param entityBenefitLine
         * @param benefitLevelList
         */
        private void setValuesToBenefitLevel(BenefitLine entityBenefitLine, List benefitLevelList) {
            
            Logger.logInfo("entered method setValuesToBenefitLevel");
            
            // TODO Auto-generated method stub
            BenefitLevel benefitLevelBO=null;
            
            //checks if the benefit level list size is not equal to zero
            if(benefitLevelList.size()!= 0){
                benefitLevelBO=(BenefitLevel)benefitLevelList.get(benefitLevelList.size()-1);
            }
            
            //checks if the benefit LevelList size is 0 or if the previous levelId is equal to the present levelId
            if(benefitLevelList.size()==0 || entityBenefitLine.getLevelSysId()!=benefitLevelBO.getLevelId()){
            BenefitLevel entityBenefitLevel=new BenefitLevel();
            entityBenefitLevel.setLevelDesc(entityBenefitLine.getLevelDesc());
            entityBenefitLevel.setLevelId(entityBenefitLine.getLevelSysId());
            entityBenefitLevel.setTermDesc(entityBenefitLine.getTermDesc());
            entityBenefitLevel.setQualifierDesc(entityBenefitLine.getQualifierDesc());
            entityBenefitLevel.setReferenceDesc(entityBenefitLine.getReferenceDesc());
            
            //sets benefit lines to the benefit Levels
            entityBenefitLevel.setBenefitLines(new ArrayList());
            entityBenefitLevel.getBenefitLines().add(getBenefitLineBO(entityBenefitLine));
            benefitLevelList.add(entityBenefitLevel);
            
           
            }
            else{
                //add benefit lines to the existing benefit level
               benefitLevelBO.getBenefitLines().add(getBenefitLineBO(entityBenefitLine));
          
                
            }
            
        }

      
        
        
        /**
         * This method returns the benefit line bo
         * @param entityBenefitLine
         * @return
         */
        private BenefitLine getBenefitLineBO(BenefitLine entityBenefitLine) {
            
            Logger.logInfo("entered method getBenefitLineBO");
            
        	BenefitLine entityBenefitLineToSet = new BenefitLine();
            entityBenefitLineToSet.setBenefitValue(entityBenefitLine.getBenefitValue());
            entityBenefitLineToSet.setProviderArrangementDesc(entityBenefitLine.getProviderArrangementDesc());
            entityBenefitLineToSet.setLineSysId(entityBenefitLine.getLineSysId());
            if(null != entityBenefitLine.getDataTypeDesc() && !(entityBenefitLine.getDataTypeDesc()).equals("")){
//    	        if(entityBenefitLine.getDataTypeId().equals("2")){
//    	        	entityBenefitLineToSet.setDataTypeDesc("$");
//    	        }
//    	        else if(entityBenefitLine.getDataTypeId().equals("3")){
    	            entityBenefitLineToSet.setDataTypeDesc(entityBenefitLine.getDataTypeDesc());  
//    	        }else if(entityBenefitLine.getDataTypeId().equals("1")){
//    	        	entityBenefitLineToSet.setDataTypeDesc("boolean");  
//    	        }else if(entityBenefitLine.getDataTypeId().equals("4")){
//    	        	entityBenefitLineToSet.setDataTypeDesc("string");  
//    	        }
            }
            return entityBenefitLineToSet;
        }
        
        
        

        /**
         * This method sets the benefitLineValues to the panel Grid
         * 
         * @param benefitLevelValues
         * @param j
         * @param benefitLineValues
         * @param i
         */
        private void setBenefitLineValuesToGrid(BenefitLevel benefitLevelValues, int j,BenefitLine benefitLineValues,int i) {
            
            Logger.logInfo("entered method setBenefitLineValuesToGrid");
            
            HtmlOutputText lineDesc=new HtmlOutputText();
            lineDesc.setValue(" ");
            
            HtmlOutputText lineTerm=new HtmlOutputText();
            lineTerm.setValue(benefitLevelValues.getTermDesc());
            
            HtmlOutputText lineQualifier=new HtmlOutputText();
            lineQualifier.setValue(benefitLevelValues.getQualifierDesc());
            
            HtmlOutputText linePVA=new HtmlOutputText();
            linePVA.setValue(benefitLineValues.getProviderArrangementDesc());
            linePVA.setId(benefitLineValues.getProviderArrangementId());
            
            HtmlOutputText lineDataType=new HtmlOutputText();
            lineDataType.setValue(benefitLineValues.getDataTypeDesc());
            lineDataType.setId(benefitLineValues.getDataTypeId());
            
            
            
//          line BenefitValue
            HtmlInputText lineBnftValue =  new HtmlInputText();
            HtmlSelectOneMenu seloneMenuForBnftValue = new HtmlSelectOneMenu();
            UIComponent object = null;
            if(null != benefitLineValues.getDataTypeDesc() && 
            		!(benefitLineValues.getDataTypeDesc()).equals("")){
    	        if(benefitLineValues.getDataTypeDesc().equals("$") || 
    	        		benefitLineValues.getDataTypeDesc().equals("%")|| 
    	        		benefitLineValues.getDataTypeDesc().equals("String")){
    		        lineBnftValue.setSize(10);
    		        lineBnftValue.setId("lineBnftValue" + j + i);
    		        
    		        //checks if the benefitDefinitionMap is null or not
    		        if(getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap()!=null){
    		            String keyForCheck=getContractSearchSession().getBenefitComponentId()+"~"+benefitLineValues.getLineSysId();

    		            
    		            //retains the value stored in the hash map as the benefit values
    		            if(getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap().get(keyForCheck)!=null)
    		                lineBnftValue.setValue(getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap().get(keyForCheck));
    		            else
    		          //      lineBnftValue.setValue(benefitLineValues.getBenefitValue()); 
    		            	lineBnftValue.setValue(""); 
    		            
    		        }    
    		        else
    		     //   lineBnftValue.setValue(benefitLineValues.getBenefitValue());
    		            lineBnftValue.setValue("");  
    		        
    		        if(!benefitLineValues.getDataTypeDesc().equals("String")){
    		        	lineBnftValue.setOnkeydown("return isNumberKey(event);");
    		        }
    		        lineBnftValue.setTitle("BenefitValue"+benefitLineValues.getDataTypeDesc());

    		        ValueBinding valueItem = FacesContext.getCurrentInstance()
    		        .getApplication().createValueBinding(
    		                "#{contractSearchBenefitDefBackingBean.benefitValueMap["
    		                        +benefitLineValues.getLineSysId()+"]}");
    		        lineBnftValue.setValueBinding("value", valueItem);
    		        lineBnftValue.setStyleClass("formInputField");
    		        lineBnftValue.setStyle("width:75px;");
    		    
    		        
    	        }else{
    				UISelectItems selectItems = new UISelectItems();
    				List possibleBnftVal = new ArrayList();
    				possibleBnftVal.add(new SelectItem("",""));
    				possibleBnftVal.add(new SelectItem("Yes", "Yes"));
    				possibleBnftVal.add(new SelectItem("No", "No"));			
    				selectItems.setValue(possibleBnftVal);
    				seloneMenuForBnftValue.getChildren().add(selectItems);
    				if(null != benefitLineValues.getBenefitValue() && 
    						!benefitLineValues.getBenefitValue().equals("")){
    				    
    				    //checks if the benefitDefinitionMap is null or not
    				    if(getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap()!=null){
        		            String keyForCheck=getContractSearchSession().getBenefitComponentId()+"~"+benefitLineValues.getLineSysId();
        		           
        		            
        		            //retains the value stored in the hash map as the benefit values
        		            if(getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap().get(keyForCheck)!=null)
        		            seloneMenuForBnftValue.setValue(getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap().get(keyForCheck));
        		            else{
        		                seloneMenuForBnftValue.setValue("");
        		             /*   if(benefitLineValues.getBenefitValue().toLowerCase().charAt(0) == 'y'){
            						seloneMenuForBnftValue.setValue("Yes");
            					}else{
            						seloneMenuForBnftValue.setValue("No");
            					}*/
        		            }
        		                
        		                
    				    }
    				    else{
    				        seloneMenuForBnftValue.setValue("");
	    				/*	if(benefitLineValues.getBenefitValue().toLowerCase().charAt(0) == 'y'){
	    						seloneMenuForBnftValue.setValue("Yes");
	    					}else{
	    						seloneMenuForBnftValue.setValue("No");
	    					}*/
    				    }
    				}
//    				seloneMenuForBnftValue.setValue(benefitLineValues.getBenefitValue());
    				seloneMenuForBnftValue.setId("lineBnftValue" + j + i);
    				object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
    				// set the value to the map
    				ValueBinding valueItem = FacesContext.getCurrentInstance()
    		        .getApplication().createValueBinding(
    		                "#{contractSearchBenefitDefBackingBean.benefitValueMap["
    		                        +benefitLineValues.getLineSysId()+"]}");
    				seloneMenuForBnftValue.setValueBinding("value",valueItem);
    	        }
            }
            

            
            //output text for view
            HtmlOutputText lineBnftValueView=new HtmlOutputText();
            lineBnftValueView.setId("lineBnftValueView"+j+i);
            lineBnftValueView.setValue(benefitLineValues.getBenefitValue());
            lineBnftValueView.setStyleClass("formInputFieldReadOnly");
           
            HtmlOutputText lineEmptyString=new HtmlOutputText();
            lineEmptyString.setValue(" ");
            
            
            HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
            lblBnftValue.setId("lblBnftValue" + j+i);
            lblBnftValue.setFor("lineBnftValue" + j+i);
            
            
            if(null != benefitLineValues.getDataTypeDesc() && 
            		!(benefitLineValues.getDataTypeDesc()).equals("")){
    	        if(benefitLineValues.getDataTypeDesc().equals("$")){
    	        	
    	        	if(!isViewMode()){
    	        	lblBnftValue.getChildren().add(lineDataType);
    	        	lblBnftValue.getChildren().add(lineBnftValue);
    	        	lblBnftValue.getChildren().add(lineEmptyString);
    	        	}
    	        	else{
    	  	          lblBnftValue.getChildren().add(lineDataType);
    	  	          lblBnftValue.getChildren().add(lineBnftValueView);
    	  	          lblBnftValue.getChildren().add(lineEmptyString);
    	  	      }
    	        	
    	        }
    	        else if(benefitLineValues.getDataTypeDesc().equals("%")){
    	        	if(!isViewMode()){ 
    	                lblBnftValue.getChildren().add(lineEmptyString);
    	                lblBnftValue.getChildren().add(lineBnftValue);
    	                lblBnftValue.getChildren().add(lineDataType);
    	               }
    	        	 else{
    	                lblBnftValue.getChildren().add(lineEmptyString);
    	                lblBnftValue.getChildren().add(lineBnftValueView);
    	                lblBnftValue.getChildren().add(lineDataType);
    	                
    	            }
    	        		
    	            
    	        }else if(benefitLineValues.getDataTypeDesc().equals("String")){
    	            if(!isViewMode()){ 
    	            lblBnftValue.getChildren().add(lineEmptyString);
    	            lblBnftValue.getChildren().add(lineBnftValue);
    	            }
    	            else {
    	                lblBnftValue.getChildren().add(lineEmptyString);
    	                lblBnftValue.getChildren().add(lineBnftValueView);
    	            }
    	        }else{
    	        	if(!isViewMode()){ 
    	        	lblBnftValue.getChildren().add(lineEmptyString);
    	            lblBnftValue.getChildren().add(object);
    	            //lblBnftValue.getChildren().add(lineDataType);
    	        	}
    	        	else{
    	        		lblBnftValue.getChildren().add(lineEmptyString);
    	                lblBnftValue.getChildren().add(lineBnftValueView);
    	        	}
    	            
    	        }
            }
            

            HtmlOutputText lineReference=new HtmlOutputText();
            lineDesc.setValue(" ");
            
            HtmlOutputText lineImage=new HtmlOutputText();
            lineDesc.setValue(" ");
            
            
            panel.getChildren().add(lineDesc);
            panel.getChildren().add(lineTerm);
            panel.getChildren().add(lineQualifier);
            panel.getChildren().add(linePVA);
            panel.getChildren().add(lblBnftValue);
            panel.getChildren().add(lineReference);
            panel.getChildren().add(lineImage);
        }
        
        
        
        /** This method sets the benefitLevelValues to the PanelGrid
         * @param i
         * @param benefitLevelValues
         */
        private void setBenefitLevelValuesToGrid(int i, BenefitLevel benefitLevelValues, int lineSize,int rowNum) {
            
            Logger.logInfo("entered method setBenefitLevelValuesToGrid");
            
            HtmlOutputText levelDesc = new HtmlOutputText();
            if (null != benefitLevelValues.getLevelDesc()) {
                levelDesc.setValue(benefitLevelValues.getLevelDesc().trim());
            } else {
                levelDesc.setValue(WebConstants.EMPTY_STRING);
            }
            levelDesc.setId("levelDesc" +benefitLevelValues.getLevelId());

            
            HtmlOutputText levelTerm = new HtmlOutputText();
            if (null != benefitLevelValues.getTermDesc()) {
                levelTerm.setValue(benefitLevelValues.getTermDesc().trim());
            } else {
                levelTerm.setValue(WebConstants.EMPTY_STRING);
            }
               
            HtmlOutputText levelQualifier = new HtmlOutputText();
            if (null != benefitLevelValues.getQualifierDesc()) {
                levelQualifier.setValue(benefitLevelValues.getQualifierDesc().trim());
            } else {
                levelQualifier.setValue(WebConstants.EMPTY_STRING);
            }
            
            HtmlOutputText levelPVA = new HtmlOutputText();
            levelPVA.setValue(" ");
            
            HtmlOutputText levelBnftValue = new HtmlOutputText();
            levelBnftValue.setValue(" ");
            
            HtmlOutputText levelReference = new HtmlOutputText();
            if (null != benefitLevelValues.getReferenceDesc()) {
                levelReference.setValue(benefitLevelValues.getReferenceDesc().trim());
            } else {
                levelReference.setValue(WebConstants.EMPTY_STRING);
            }     
            
            
            HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
            hiddenLevelId.setId("hiddenLevelId" + i);
            hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
            // Change start
            // set the value to the map
    		ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
    		.getApplication().createValueBinding(
    				"#{contractSearchBenefitDefBackingBean.levelIdMap[" + i
    						+ "]}");
    		hiddenLevelId.setValueBinding("value", levelIdValBind);
    		// change end
            
//            HtmlGraphicImage deleteImage = new HtmlGraphicImage();
//            deleteImage.setUrl("../../images/delete.gif");
//            deleteImage.setStyle("cursor:hand;");
//            deleteImage.setId("deleteImage" + i);
//            
//            
//            HtmlCommandButton deleteButton = new HtmlCommandButton();
//    		
//    		deleteButton.setValue("DeleteButton");
//    		deleteButton.setImage("../../images/delete.gif");
//    		deleteButton.setTitle("Delete");
//    		deleteButton.setStyle("border:0;");
//    		deleteButton.setAlt("Delete");
//    		deleteButton.setOnclick("changeColour(\'"+ i +"','" +lineSize +"','"+rowNum+"\');return false;");
//    		deleteButton.setId("deleteButton" + i);


            HtmlOutputLabel lblImage = new HtmlOutputLabel();
            lblImage.setFor("levelDesc" + i);
            lblImage.setId("lblImage" + i);
            
            //sets the size to a hidden variable
            HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
            hiddenLineSize.setId("hiddenLineSize"+i);
            hiddenLineSize.setValue(new Integer(lineSize));
            
//          sets the size to a hidden variable
            HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
            hiddenRowSize.setId("hiddenRowNum"+i);
            hiddenRowSize.setValue(new Integer(rowNum));
            
            //checks if it is a view mode
            if(!isViewMode())
//            lblImage.getChildren().add(deleteButton);
//            lblImage.getChildren().add(hiddenLevelId);
//            lblImage.getChildren().add(hiddenLineSize);
//            lblImage.getChildren().add(hiddenRowSize);
            
            panel.getChildren().add(levelDesc);
            panel.getChildren().add(levelTerm);
            panel.getChildren().add(levelQualifier);
            panel.getChildren().add(levelPVA);
            panel.getChildren().add(levelBnftValue);
            panel.getChildren().add(levelReference);
            panel.getChildren().add(lblImage);
           
        }
        
        
       
        
        /**
         * Returns the benefitValueMap
         * @return Map benefitValueMap.
         */
        public Map getBenefitValueMap() {
            return benefitValueMap;
        }
        
        
        /**
         * Sets the benefitValueMap
         * @param benefitValueMap.
         */
        public void setBenefitValueMap(Map benefitValueMap) {
            this.benefitValueMap = benefitValueMap;
        }
        /**
         * Sets the panel
         * @param panel.
         */
        public void setPanel(HtmlPanelGrid panel) {
            this.panel = panel;
        }
        
        public String getBenefitDefinitionsPage(){
            //checks for view mode
            if(!isViewMode())
                return "benefitDefinitionsPage";
            else
                return "benefitDefinitionsViewPage";
        }
    	/**
    	 * Returns the dummyVar
    	 * @return String dummyVar.
    	 */
      //WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
    	// while the variable defined in String in WAS 7.0
    	
    	/**
    	 * 
    	 * @return HtmlInputHidden
    	 */

    	public HtmlInputHidden getDummyVar() {
    		return dummyVar;
    	}
    	/**
    	 * Sets the dummyVar
    	 * @param dummyVar.
    	 */

    	public void setDummyVar(HtmlInputHidden dummyVar) {
    		this.dummyVar = dummyVar;
    	}
    	
    	
    	//adds the benefit values as a criteria for search
    	public String addToSearch(){
    	    
    	    int bnftComponentId=getContractSearchSession().getBenefitComponentId();
    	    String key;
    	   
    	    //for iterating the benefitValueMap
            Set keys = benefitValueMap.keySet();
            Iterator valueIter = keys.iterator();
            
            HashMap benefitDefinitionMap=getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap();
            if(benefitDefinitionMap==null){
	             benefitDefinitionMap=new HashMap();
	             getContractSearchSession().getContractSearchScreenValueObject().setBenefitDefinitionsMap(benefitDefinitionMap);
	            
           }   
           
            //iterates the benefit value map and puts the values into a map in the session
            while (valueIter.hasNext()) {
                Long valueElement = (Long) valueIter.next();
                
                String value = (String) benefitValueMap.get(valueElement);
                key=bnftComponentId+"~"+valueElement.intValue();
                
                benefitDefinitionMap.put(key,value.trim());
                
     

            }
            

    	    return "contractSearchPage";
    	}
    	
    	public String done(){
    	    
    	    return "contractSearchPage";
    	}
    	
    	
    }

    
    
    
    

