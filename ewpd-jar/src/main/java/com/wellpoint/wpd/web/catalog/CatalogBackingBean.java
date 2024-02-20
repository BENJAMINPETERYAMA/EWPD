/*
 * CatalogBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.ibm.ws.jsf.configuration.FacesConfig;
import com.ibm.ws.jsf.util.FacesBeanUtils;
import com.ibm.ws.jsf.util.FacesConfigUtil;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.bo.SrdaCatalogBO;
import com.wellpoint.wpd.common.catalog.request.CatalogRetrieveRequest;
import com.wellpoint.wpd.common.catalog.request.EditCatalogRequest;
import com.wellpoint.wpd.common.catalog.request.SaveCatalogRequest;
import com.wellpoint.wpd.common.catalog.request.SrdaCatalogRetrieveRequest;
import com.wellpoint.wpd.common.catalog.response.CatalogRetrieveResponse;
import com.wellpoint.wpd.common.catalog.response.EditCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SaveCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SrdaCatalogRetrieveResponse;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.util.StringUtil;

/**
 * Base class for all catalog backing beans.
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogBackingBean extends WPDBackingBean {

    private int catalogId;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private String catalogName;

    private String catalogDatatype;

    private String catalogSize;

    private int viewCatalogId;

    private String description;

    private List dataTypeListForCombo;

    private boolean requiredCatalogName = false;

    private boolean requiredDesription = false;

    private boolean requiredCatalogDatatype = false;

    private boolean requiredCatalogSize = false;

    private List validationMessages;

    private String catalogDatatypeOld;
    
    private String descriptionOld;
    
    private String catalogSizeOld;
    
    private int selectedCatalogId;

    private int printCatalogId;
    
    private String printBreadCrumbText;


    public CatalogBackingBean() {
        super();
        setBreadCrump();
    }

     protected void setBreadCrump() {
        this.setBreadCrumbText("Administration >> Catalog >> Create");
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        if (null != description)
            return description.trim();
        return null;
    }


	/**
	 * @return Returns the descriptionOld.
	 */
	public String getDescriptionOld() {
		return descriptionOld;
	}
	/**
	 * @param descriptionOld The descriptionOld to set.
	 */
	public void setDescriptionOld(String descriptionOld) {
		this.descriptionOld = descriptionOld;
	}
    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        if (null != description)
            this.description = description.trim().toUpperCase();
    }


    public String getCatalogName() {
        if (null != catalogName)
            return catalogName.trim();
        return null;
    }


    public void setCatalogName(String catalogName) {
        if (null != catalogName) 
           this.catalogName = catalogName.trim().toUpperCase();
       }


    public String getCatalogDatatype() {
        if (null != catalogDatatype)
            return catalogDatatype.trim();
        return null;
    }


    public void setCatalogDatatype(String catalogDatatype) {
        this.catalogDatatype = catalogDatatype.trim().toUpperCase();
    }
	/**
	 * @return Returns the catalogSize.
	 */
	public String getCatalogSize() {
		if(null != catalogSize){
			return catalogSize.trim();
		}
		return null;
	}
	/**
	 * @param catalogSize The catalogSize to set.
	 */
	public void setCatalogSize(String catalogSize) {
		this.catalogSize = catalogSize;
	}
	/**
	 * @return Returns the catalogId.
	 */
	public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId The catalogId to set.
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public boolean isRequiredCatalogName() {
        return requiredCatalogName;
    }


    public void setRequiredCatalogName(boolean requiredCatalogName) {
        this.requiredCatalogName = requiredCatalogName;
    }


    public boolean isRequiredCatalogDatatype() {
        return requiredCatalogDatatype;
    }


    public void setRequiredCatalogDatatype(boolean requiredCatalogDatatype) {
        this.requiredCatalogDatatype = requiredCatalogDatatype;
    }


    public List getDataTypeListForCombo() {
        HttpSession httpSession = getSession();
        String dataType = (String)httpSession.getAttribute("CATALOG_DATATYPE");
            dataTypeListForCombo = new ArrayList();
            if(null!=dataType)
                if(dataType.equalsIgnoreCase("Integer")){
                    dataTypeListForCombo.add(new SelectItem("Integer"));
    	            dataTypeListForCombo.add(new SelectItem("String"));
                }else if(dataType.equalsIgnoreCase("String")){
                    dataTypeListForCombo.add(new SelectItem("String"));
    	            dataTypeListForCombo.add(new SelectItem("Integer"));
                }
                else if(dataType.equalsIgnoreCase("N/A")){
                    dataTypeListForCombo.add(new SelectItem("N/A"));
    	           // dataTypeListForCombo.add(new SelectItem("Integer"));
                }
            if(null==dataType){
                dataTypeListForCombo.add(new SelectItem(WebConstants.EMPTY_STRING));
	            dataTypeListForCombo.add(new SelectItem("String"));
	            dataTypeListForCombo.add(new SelectItem("Integer"));
            }
        
        return dataTypeListForCombo;
    }


    /**
     * @param dataTypeListForCombo
     *            The dataTypeListForCombo to set.
     */
    public void setDataTypeListForCombo(List dataTypeListForCombo) {
        this.dataTypeListForCombo = dataTypeListForCombo;
    }


    public boolean isRequiredCatalogSize() {
        return requiredCatalogSize;
    }


    public void setRequiredCatalogSize(boolean requiredCatalogSize) {
        this.requiredCatalogSize = requiredCatalogSize;
    }


    /**
     * @return Returns the requiredDesription.
     */
    public boolean isRequiredDesription() {
        return requiredDesription;
    }


    /**
     * @param requiredDesription
     *            The requiredDesription to set.
     */
    public void setRequiredDesription(boolean requiredDesription) {
        this.requiredDesription = requiredDesription;
    }
    
    /**
     * Returns the selectedCatalogId
     * @return int selectedCatalogId.
     */
    public int getSelectedCatalogId() {
        return selectedCatalogId;
    }
    /**
     * Sets the selectedCatalogId
     * @param selectedCatalogId.
     */
    public void setSelectedCatalogId(int selectedCatalogId) {
        this.selectedCatalogId = selectedCatalogId;
    }
    /**
     * @return printCatalogId
     * 
     * Returns the printCatalogId.
     */
    public int getPrintCatalogId() {
        Logger.logInfo("CatalogBackingBean - Entering retrieveCatalogDetails(): Catalog");
        CatalogRetrieveRequest catalogRetrieveRequest = (CatalogRetrieveRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_CATALOG_ID);
        CatalogVO catalogVO = new CatalogVO();
        HttpSession httpSession = getSession();
        String retrieveKey = (String) httpSession.getAttribute("CatalogId");
        catalogVO.setCatalogId((new Integer(retrieveKey)).intValue());
        catalogRetrieveRequest.setCatalogVO(catalogVO);
        CatalogRetrieveResponse catalogRetrieveResponse = (CatalogRetrieveResponse) executeService(catalogRetrieveRequest);
        if (null != catalogRetrieveResponse) {
            CatalogBO catalogBO = catalogRetrieveResponse.getCatalogBO();
            setValuesToBackingBeanFromResponse(catalogBO);
            this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> View");

        }
        Logger.logInfo("CatalogBackingBean - Returning retrieveCatalogDetails(): Catalog");
        return printCatalogId;
    }


    /**
     * @param printCatalogId
     * 
     * Sets the printCatalogId.
     */
    public void setPrintCatalogId(int printCatalogId) {
        this.printCatalogId = printCatalogId;
    }

    /**
     * Method to create catalog
     * @return String
     */
    public String createCatalog() {
//        ArrayList validationMessages = null;
        HttpSession httpSession = getSession();
        if (!validateRequiredFields(1)) {
            addAllMessagesToRequest(this.validationMessages);
            return WebConstants.EMPTY_STRING;
        }

        SaveCatalogRequest saveCatalogRequest = getSaveCatalogRequest();
        SaveCatalogResponse saveCatalogResponse = (SaveCatalogResponse) executeService(saveCatalogRequest);

        if (null != saveCatalogResponse) {
            if (null != saveCatalogResponse.getCatalogBO()) {
                if (saveCatalogResponse.isSuccess()) {
                    CatalogBO catalogBO = saveCatalogResponse.getCatalogBO();
                    setValuesToBackingBeanFromResponse(catalogBO);
                    this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> Edit");
//                  Setting the session attribute for edit print
                    httpSession.setAttribute(WebConstants.CATALOG_ID_STRING,new Integer(saveCatalogResponse.getCatalogBO().getCatalogId()).toString());
                    httpSession.setAttribute("CATALOG_DATATYPE",catalogBO.getCatalogDatatype());
                    return WebConstants.CATALOG_EDIT_FORWARD_STING;
                }
            }
        }
        
        return WebConstants.EMPTY_STRING;
    }
    
    /**
     * Method to set values back to BackingBean from the response
     * @param catalogBO
     */
    private void setValuesToBackingBeanFromResponse(CatalogBO catalogBO) {
        this.catalogId = catalogBO.getCatalogId();
        this.catalogName = catalogBO.getCatalogName();
        this.setCatalogDatatypeOld(catalogBO.getCatalogDatatype());
        this.setCatalogSizeOld(new Integer(catalogBO.getCatalogSize()).toString().trim());
        this.setCatalogDatatype(catalogBO.getCatalogDatatype());
        this.setCatalogSize(new Integer(catalogBO.getCatalogSize()).toString().trim());
        this.setDescription(catalogBO.getDescription());
        this.setDescriptionOld(catalogBO.getDescription());
        this.setCreatedUser(catalogBO.getCreatedUser());
        this.setCreatedTimestamp(catalogBO.getCreatedTimestamp());
        this.setLastUpdatedUser(catalogBO.getLastUpdatedUser());
        this.setLastUpdatedTimestamp(catalogBO.getLastUpdatedTimestamp());
    }

    /**
     * Method to set values back to BackingBean from the response
     * @param catalogBO
     */
    private void setValuesToBackingBeanFromResponse(SrdaCatalogBO catalogBO) {
        this.catalogId = catalogBO.getCatalogId();
        this.catalogName = catalogBO.getCatalogName();
        this.setCatalogDatatypeOld(catalogBO.getCatalogDatatype());
        this.setCatalogSizeOld(new Integer(catalogBO.getCatalogSize()).toString().trim());
        this.setCatalogDatatype(catalogBO.getCatalogDatatype());
        this.setCatalogSize(new Integer(catalogBO.getCatalogSize()).toString().trim());
        this.setDescription(catalogBO.getDescription());
        this.setDescriptionOld(catalogBO.getDescription());
        this.setCreatedUser(catalogBO.getCreatedUser());
        this.setCreatedTimestamp(catalogBO.getCreatedTimestamp());
        this.setLastUpdatedUser(catalogBO.getLastUpdatedUser());
        this.setLastUpdatedTimestamp(catalogBO.getLastUpdatedTimestamp());
    }
    /**
     * Method to edit catalog
     * @return String
     */
    public String editCatalog() {
        Logger.logInfo("CatalogBackingBean - Entering editCatalog(): Catalog Edit");
//        ArrayList validationMessages = null;
        HttpSession httpSession = getSession();
		String srdaFlag = (String) httpSession.getAttribute("SESSION_SRDA_FLAG");
        if (!validateRequiredFields(2)) {
            addAllMessagesToRequest(this.validationMessages);
            return WebConstants.EMPTY_STRING;
        }

        EditCatalogRequest editCatalogRequest = getEditCatalogRequest();
        EditCatalogResponse editCatalogResponse = (EditCatalogResponse) executeService(editCatalogRequest);

        if (null != editCatalogResponse) {
            if (null != editCatalogResponse.getCatalogBO()) {
                   CatalogBO catalogBO = editCatalogResponse.getCatalogBO();
                    setValuesToBackingBeanFromResponse(catalogBO);
                    this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> Edit");

                    Logger.logInfo("CatalogBackingBean - Returning editCatalog(): Catalog Edit");
//                  Setting the session attribute for edit print
                    httpSession.setAttribute(WebConstants.CATALOG_ID_STRING,new Integer(editCatalogResponse.getCatalogBO().getCatalogId()).toString());
                    httpSession.setAttribute("CATALOG_DATATYPE",catalogBO.getCatalogDatatype());
                    return WebConstants.CATALOG_EDIT_FORWARD_STING;
               
            }
        }
        this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> Edit");
        return WebConstants.EMPTY_STRING;
    }

    /**
     * Method to load catalog for edit
     * @return String
     */
    public String loadCatalogForEdit() {
        HttpSession httpSession = getSession();
        Logger.logInfo("CatalogBackingBean - Entering loadCatalogForEdit(): Catalog Edit");
        String retrieveKey = new Integer(this.getSelectedCatalogId()).toString();
        retrieveCatalogDetails(retrieveKey);
        //Setting the session attribute for edit print
        httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, retrieveKey);
        return WebConstants.CATALOG_EDIT_FORWARD_STING;
    }

   
    /**
     * Method to retrieve catalog
     * @param retrieveKey
     */
    private void retrieveCatalogDetails(String retrieveKey) {
        HttpSession session  = getSession();
        Logger.logInfo("CatalogBackingBean - Entering retrieveCatalogDetails(): Catalog");
        CatalogRetrieveRequest catalogRetrieveRequest = (CatalogRetrieveRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_CATALOG_ID);
        CatalogVO catalogVO = new CatalogVO();
        catalogVO.setCatalogId(new Integer(retrieveKey).intValue());
        catalogRetrieveRequest.setCatalogVO(catalogVO);
        CatalogRetrieveResponse catalogRetrieveResponse = (CatalogRetrieveResponse) executeService(catalogRetrieveRequest);
        if (null != catalogRetrieveResponse) {
            CatalogBO catalogBO = catalogRetrieveResponse.getCatalogBO();
            setValuesToBackingBeanFromResponse(catalogBO);
            this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> Edit");
            session.setAttribute("CATALOG_DATATYPE",catalogBO.getCatalogDatatype());
        }
        Logger.logInfo("CatalogBackingBean - Returning retrieveCatalogDetails(): Catalog");
    }

    /**
     * Method to view catalog
     * @return Integer
     */
    public int getViewCatalogId() {
        Logger.logInfo("CatalogBackingBean - Entering retrieveCatalogDetails(): Catalog");
        CatalogRetrieveRequest catalogRetrieveRequest = (CatalogRetrieveRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_CATALOG_ID);
        SrdaCatalogRetrieveRequest srdaCatalogRetrieveRequest = (SrdaCatalogRetrieveRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_SRDA_CATALOG_ID);
        CatalogVO catalogVO = new CatalogVO();
        HttpSession httpSession = getSession();
        String srdaFlag = (String) httpSession.getAttribute("SESSION_SRDA_FLAG");
        String action = getRequest().getParameter(WebConstants.ACTION_STRING);
        if (null != action && !action.equals(WebConstants.EMPTY_STRING)) {
            String retrieveKey = WebConstants.EMPTY_STRING;
            if (action.equals(WebConstants.VIEW_STRING)) {
                retrieveKey = ESAPI.encoder().encodeForHTML(getRequest().getParameter("catalogId"));
                if(StringUtil.regExPatterValidation(retrieveKey)){
                	retrieveKey = retrieveKey;
        		}else{
        			retrieveKey=null;
        		}
                httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, retrieveKey);

            } else if (action.equals(WebConstants.PRINT_STRING)) {
                retrieveKey = (String) httpSession.getAttribute(WebConstants.CATALOG_ID_STRING);
            }
            catalogVO.setCatalogId((new Integer(retrieveKey)).intValue());
            if("eWPD".equalsIgnoreCase(srdaFlag)){ 
            catalogRetrieveRequest.setCatalogVO(catalogVO);
            CatalogRetrieveResponse catalogRetrieveResponse = (CatalogRetrieveResponse) executeService(catalogRetrieveRequest);
            if (null != catalogRetrieveResponse) {
                CatalogBO catalogBO = catalogRetrieveResponse.getCatalogBO();
                setValuesToBackingBeanFromResponse(catalogBO);
                this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> View");

            }
            }
            else{
                srdaCatalogRetrieveRequest.setCatalogVO(catalogVO);
                SrdaCatalogRetrieveResponse srdaCatalogRetrieveResponse = (SrdaCatalogRetrieveResponse) executeService(srdaCatalogRetrieveRequest);
                if (null != srdaCatalogRetrieveResponse) {
                    SrdaCatalogBO srdaCatalogBO = srdaCatalogRetrieveResponse.getSrdaCatalogBO();
                    setValuesToBackingBeanFromResponse(srdaCatalogBO);
                    this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> View");
                }
            }
        }
        Logger.logInfo("CatalogBackingBean - Returning retrieveCatalogDetails(): Catalog");
        return viewCatalogId;
    }


    /**
     * @param viewCatalogId
     *            The viewCatalogId to set.
     */
    public void setViewCatalogId(int viewCatalogId) {
        this.viewCatalogId = viewCatalogId;
    }

    /**
     * Method to validate fields
     * @return boolean
     */
    private boolean validateRequiredFields(int action) {
        validationMessages = new ArrayList();
        boolean requiredField = false;
        this.requiredCatalogName = false;
        this.requiredDesription = false;
        this.requiredCatalogDatatype = false;
        this.requiredCatalogSize = false;
        
        if(action == 2){
        	this.setBreadCrumbText("Administration >> Catalog " + "("+ this.catalogName + ") >> Edit");
        }

        if (this.catalogName == null || WebConstants.EMPTY_STRING.equals(this.catalogName)) {
            requiredCatalogName = true;
            requiredField = true;
        }
       if (this.catalogDatatype == null || WebConstants.EMPTY_STRING.equals(this.catalogDatatype)) {
            requiredCatalogDatatype = true;
            requiredField = true;
        }
        if (this.catalogSize == null || WebConstants.EMPTY_STRING.equals(this.catalogSize)) {
            requiredCatalogSize = true;
            requiredField = true;
        }
        
        if (requiredField) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        if (this.catalogName.length() < 1 || this.catalogName.length() > 30) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.INVALID_NAME));
            return false;
        }
        if(null != this.catalogSize && !this.catalogSize.equals(WebConstants.EMPTY_STRING) && this.catalogSize.trim().matches("^\\d*$")){
        	try{
//        		int size = new Integer(this.catalogSize.trim()).intValue();
        	}catch(Exception exception){
        		this.validationMessages.add(new ErrorMessage(
        				WebConstants.CATALOG_SIZE_INVALID));
        		return false;
        	}
	        if ((new Integer(this.catalogSize.trim())) == null
	                || WebConstants.EMPTY_STRING.equals(new Integer(this.catalogSize.trim()))) {
	            requiredCatalogSize = true;
	            requiredField = true;
	        }
        
	        if (new Integer(this.catalogSize.trim()).intValue() ==0 || new Integer(this.catalogSize.trim()).intValue() > 15 )
	        {
	            this.validationMessages.add(new ErrorMessage(
	                    WebConstants.CATALOG_SIZE_ZERO));
	            return false;  
	        }
        }else{
        	this.validationMessages.add(new ErrorMessage(
        			WebConstants.CATALOG_SIZE_INVALID));
            return false;
        }
        if (!this.description.trim().equals(WebConstants.EMPTY_STRING) && this.description.length() > 240) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_SIZE_1_240));
            return false;
        }
        return true;
    }

    /**
     * Method to get SaveCatalogRequest
     * @return SaveCatalogRequest
     */
    private SaveCatalogRequest getSaveCatalogRequest() {
        SaveCatalogRequest saveCatalogRequest = (SaveCatalogRequest) this
                .getServiceRequest(ServiceManager.SAVE_CATALOG_REQUEST);
        CatalogVO catalogVO = new CatalogVO();
        catalogVO = getValuesToCatalogVO(catalogVO);
        saveCatalogRequest.setCatalogVO(catalogVO);
        return saveCatalogRequest;
    }
    /**
     * Set the values to catalogVO
     * @param catalogVO
     * @return
     */
    private CatalogVO getValuesToCatalogVO(CatalogVO catalogVO){
        catalogVO.setCatalogName(this.getCatalogName());
        catalogVO.setCatalogDatatype(this.getCatalogDatatype());
        catalogVO.setCatalogSize(new Integer(this.getCatalogSize().trim()).intValue());
        if(this.description.trim().equals(WebConstants.EMPTY_STRING)){
        	catalogVO.setDescription(WebConstants.EMPTY_STRING);
        }else{
        	catalogVO.setDescription(this.getDescription());
        }
        return catalogVO;
    }
    /**
     * Method to get EditCatalogRequest
     * @return EditCatalogRequest
     */
    private EditCatalogRequest getEditCatalogRequest() {
        EditCatalogRequest editCatalogRequest = (EditCatalogRequest) this
                .getServiceRequest(ServiceManager.EDIT_CATALOG_REQUEST);
        CatalogVO catalogVO = new CatalogVO();
        catalogVO = getValuesToCatalogVO(catalogVO);
        catalogVO.setCatalogId(new Integer(this.getCatalogId()).intValue());
        catalogVO.setCatalogDatatypeOld(this.getCatalogDatatypeOld());
        catalogVO.setCatalogSizeOld(new Integer(this.getCatalogSizeOld().trim()).intValue());
        catalogVO.setDescription(this.getDescription().trim());
        catalogVO.setDescriptionOld(this.getDescriptionOld().trim());
        editCatalogRequest.setCatalogVO(catalogVO);
        return editCatalogRequest;
    }

	/**
	 * @return Returns the catalogDatatypeOld.
	 */
	public String getCatalogDatatypeOld() {
		return catalogDatatypeOld;
	}
	/**
	 * @param catalogDatatypeOld The catalogDatatypeOld to set.
	 */
	public void setCatalogDatatypeOld(String catalogDatatypeOld) {
		this.catalogDatatypeOld = catalogDatatypeOld;
	}
	/**
	 * @return Returns the catalogSizeOld.
	 */
	public String getCatalogSizeOld() {
		return catalogSizeOld;
	}
	/**
	 * @param catalogSizeOld The catalogSizeOld to set.
	 */
	public void setCatalogSizeOld(String catalogSizeOld) {
		this.catalogSizeOld = catalogSizeOld;
	}
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Administration >> Catalog " + "("+ this.catalogName + ") >> Print";
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
}