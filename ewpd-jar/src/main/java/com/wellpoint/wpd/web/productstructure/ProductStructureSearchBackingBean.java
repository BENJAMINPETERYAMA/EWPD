/*
 * ProductStructureSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary
 *  information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the express 
 * written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.ArrayList;
import java.util.List;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.request.DeleteProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.ProductStructureLifeCycleRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProductStructureVersionsRequest;
import com.wellpoint.wpd.common.productstructure.request.SearchProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.response.DeleteProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureLifeCycleResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureVersionsResponse;
import com.wellpoint.wpd.common.productstructure.response.SearchProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureLocateCriteriaVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.util.SessionCleanUp;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for search page.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $$Id: $$
 */

public class ProductStructureSearchBackingBean extends
        ProductStructureBackingBean {

    private ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO;

    private List validationMessage;

    private String lob;

    private String entity;

    private String group;

    private String name;

    private String productStructureName;

    private String lineOfBusiness;

    private String businessEntity;

    private String businessGroup;

    private List searchResultList;

    private List versionList;

    private String selectedStructureId;

    private String effectiveDate;

    private String expiryDate;

    private int productStructureId;

    private String fetchAllVersions;

    private String actionForTest;

    private String breadCrumbViewAllVersions;
    
    private String productStructureSearchPrint;
    
    private boolean productStructurePrint;
    
    private String printBreadCrumbText;
    //CARS START
    private String marketBusinessUnit;
    //CARS END
    /**
     * @return productStructurePrint
     * 
     * Returns the productStructurePrint.
     */
    public boolean isProductStructurePrint() {
        return productStructurePrint;
    }
    /**
     * @param productStructurePrint
     * 
     * Sets the productStructurePrint.
     */
    public void setProductStructurePrint(boolean productStructurePrint) {
        this.productStructurePrint = productStructurePrint;
    }
    /**
     * @return productStructureSearchPrint
     * 
     * Returns the productStructureSearchPrint.
     */
    public String getProductStructureSearchPrint() {
        
        this.setProductStructurePrint(true);
        SearchProductStructureRequest searchProductStructureRequest = getSearchProductStructureRequest();
        // Call the executeService method to get the response
        SearchProductStructureResponse searchProductStructureResponse = (SearchProductStructureResponse) this
                .executeService(searchProductStructureRequest);
        if (null != searchProductStructureResponse) {
            //Set the resulting Product Structure from the database
            // from the response to the searchResultList
            searchResultList = searchProductStructureResponse
                    .getProductStructureList();
            if (searchResultList != null && searchResultList.size() > 0) {
                this.setSearchResultToSession(searchResultList);
            } else {
                searchResultList = null;
            }
        }
        return productStructureSearchPrint;
    }
    /**
     * @param productStructureSearchPrint
     * 
     * Sets the productStructureSearchPrint.
     */
    public void setProductStructureSearchPrint(
            String productStructureSearchPrint) {
        this.productStructureSearchPrint = productStructureSearchPrint;
    }
    /**
     * constructor.
     */
    public ProductStructureSearchBackingBean() {
        this.setBreadCrumbText("Product Configuration >> Product Structure "
                + ">> Locate");
    }


    /**
     * This method is used to search a Product structure according to the Search
     * Criteria.
     * 
     * @return String
     */
    public String productStructureSearch() {
        Logger.logInfo("Entering the method for searching the "
                + "product structure");
        SessionCleanUp.cleanUp();
        //Check for valid date
        if (isValidDate()) {
            //Get the request object
            SearchProductStructureRequest searchProductStructureRequest = getSearchProductStructureRequest();
            // Call the executeService method to get the response
            SearchProductStructureResponse searchProductStructureResponse = (SearchProductStructureResponse) this
                    .executeService(searchProductStructureRequest);
            if (null != searchProductStructureResponse) {
                //Set the resulting Product Structure from the database
                // from the response to the searchResultList
                searchResultList = searchProductStructureResponse
                        .getProductStructureList();
                if (searchResultList != null && searchResultList.size() > 0) {
                    this.setSearchResultToSession(searchResultList);
                } else {
                    searchResultList = null;
                }
                //Set breadcrumb text
                this.setBreadCrumbText("Product Configuration >>"
                        + " Product Structure >> Locate");
            }
        }
        Logger.logInfo("Returning the method for searching"
                + " the product structure");
        return "";
    }


    /**
     * Method to get All versions of Product Structure
     * 
     * @return String.
     */
    public String getFetchAllVersions() {
        Logger.logInfo("Entering the method for getting all versions");
        RetrieveProductStructureVersionsRequest retrieveProductStructureVersionsRequest = getViewAllVersionsRequest();
        RetrieveProductStructureVersionsResponse retrieveProductStructureVersionsResponse = (RetrieveProductStructureVersionsResponse) this
                .executeService(retrieveProductStructureVersionsRequest);
        if (null != retrieveProductStructureVersionsResponse) {
            this.versionList = retrieveProductStructureVersionsResponse
                    .getVersionList();
        }
        if (versionList != null && versionList.size() > 0) {
            this.setSearchResultToSessionForViewAllVersions(versionList);
        } else {
            versionList = null; 
        }
        this.setBreadCrumbText("Product Configuration >> Product "
                + "Structure >> Locate");
        Logger.logInfo("Returning the method for getting all versions");
        return "viewAllVersions";

    }


    /**
     * Sets the fetchAllVersions
     * 
     * @param fetchAllVersions.
     */
    public void setFetchAllVersions(String fetchAllVerns) {
        this.fetchAllVersions = fetchAllVerns;
    }


    /**
     * This method is used to check whether a given date is valid or not.
     * 
     * @return boolean
     */
    private boolean isValidDate() {
        boolean valid = false;
        //Date effectiveDate = null;
        //Date expiryDate = null;
        validationMessage = new ArrayList(2);
        valid = true;
        if (null!=this.effectiveDate && !(this.effectiveDate.trim().equals("")) && !(StringUtil.isDate(this.effectiveDate))) {
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Effective Date" });
            validationMessage.add(errorMessage);
            valid = false;
        }
        if (null!=this.expiryDate && !(this.expiryDate.trim().equals("")) && !(StringUtil.isDate(this.expiryDate))) {
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Expiry Date" });
            validationMessage.add(errorMessage);
            valid = false;
        }
        addAllMessagesToRequest(validationMessage);
        return valid;
    }


    /**
     * 
     * @return RetrieveProductStructureVersionsRequest
     */
    private RetrieveProductStructureVersionsRequest getViewAllVersionsRequest() {
        RetrieveProductStructureVersionsRequest retrieveProductStructureVersionsRequest = (RetrieveProductStructureVersionsRequest) this
                .getServiceRequest(ServiceManager.VIEW_PRODUCT_STRUCTURE_VERSIONS_REQUEST);
        String id = getRequest().getParameter("id");
        ProductStructureBO productStructureBO; 
        if (id != null) {
            retrieveProductStructureVersionsRequest
                    .setProductStructureId(Integer.parseInt(id));
            productStructureBO = new ProductStructureBO();        
            productStructureBO.setProductStructureId(Integer.parseInt(id));
            this.setProductStructureSessionObject(productStructureBO);            
        }else {
        	retrieveProductStructureVersionsRequest
            .setProductStructureId(this.getProductStructureSessionObject().getId());
        }
        return retrieveProductStructureVersionsRequest;
    }


    /**
     * This method is used to get the request object to which the values from VO
     * is added.
     * 
     * @return SearchProductStructureRequest
     */
    private SearchProductStructureRequest getSearchProductStructureRequest() {
        SearchProductStructureRequest searchProductStructureRequest = (SearchProductStructureRequest) this
                .getServiceRequest(ServiceManager.SEARCH_PRODUCT_STRUCTURE);
        if(!isProductStructurePrint()){
            //function call to create VO
            createProductStructureLocateCriteriaVO();
            //set values from VO to request
            searchProductStructureRequest
                    .setProductStructureLocateCriteriaVO(productStructureLocateCriteriaVO);
            getRequest().getSession().removeAttribute("productStructureSearchCriteriaVO");
            getRequest().getSession().setAttribute("productStructureSearchCriteriaVO",searchProductStructureRequest.getProductStructureLocateCriteriaVO());
        }else{
            if(null != getRequest().getSession().getAttribute("productStructureSearchCriteriaVO")){
                searchProductStructureRequest.setProductStructureLocateCriteriaVO((ProductStructureLocateCriteriaVO)getRequest().getSession().getAttribute("productStructureSearchCriteriaVO"));
    		}
        }
        return searchProductStructureRequest;
    }


    /**
     * This method will set the values from backing bean to the VO.
     * 
     * @return void.
     */
    private void createProductStructureLocateCriteriaVO() {
        productStructureLocateCriteriaVO = new ProductStructureLocateCriteriaVO();
        if (!(null == this.getLineOfBusiness() || "".equals(this
                .getLineOfBusiness()))) {
            List lobList = WPDStringUtil.getListFromTildaString(this
                    .getLineOfBusiness(), 2, 2, 2);
            productStructureLocateCriteriaVO.setLineOfBusiness(lobList);
        }
        if (!(null == this.getBusinessEntity() || "".equals(this
                .getBusinessEntity()))) {
            List entityList = WPDStringUtil.getListFromTildaString(this
                    .getBusinessEntity(), 2, 2, 2);
            productStructureLocateCriteriaVO.setBusinessEntity(entityList);
        }
        if (!(null == this.getBusinessGroup() || "".equals(this
                .getBusinessGroup()))) {
            List groupList = WPDStringUtil.getListFromTildaString(this
                    .getBusinessGroup(), 2, 2, 2);
            productStructureLocateCriteriaVO.setBusinessGroup(groupList);
        }
        //CARS START
        if (!(null == this.getMarketBusinessUnit() || "".equals(this
                .getMarketBusinessUnit()))) {
            List marketBusinessUnit = WPDStringUtil.getListFromTildaString(this
                    .getMarketBusinessUnit(), 2, 2, 2);
            productStructureLocateCriteriaVO.setMarketBusinessUnit(marketBusinessUnit);
        }
        //CARS END
        productStructureLocateCriteriaVO.setProductStructureName(this
                .getProductStructureName().trim());
        if (WPDStringUtil.isValidDate(this.getEffectiveDate())) {
            if (this.getEffectiveDate().trim().equals("")) {
                productStructureLocateCriteriaVO.setEffectiveDate(null);
            } else {
                productStructureLocateCriteriaVO.setEffectiveDate(this
                        .getEffectiveDate().trim());
            }
        }
        if (WPDStringUtil.isValidDate(this.getExpiryDate())) {
            if (this.getExpiryDate().trim().equals("")) {
                productStructureLocateCriteriaVO.setExpiryDate(null);
            } else {
                productStructureLocateCriteriaVO.setExpiryDate(this
                        .getExpiryDate().trim());
            }
        }
    }


    /**
     * This method is used to delete a Product Structure selected by the user
     * 
     * @return String
     */
    public String deleteProductStructure() {
        Logger.logInfo("Entering the method for deleting product structure");
        // Get the request object
        DeleteProductStructureRequest deleteProductStructureRequest = getDeleteProductStructureRequest();

        // Call the executeService method to get the response
        DeleteProductStructureResponse deleteProductStructureResponse = (DeleteProductStructureResponse) this
                .executeService(deleteProductStructureRequest);
        if (null != deleteProductStructureResponse) {
            // Set the resulting Product Structure from the database
            // from the response to the searchResultList
            searchResultList = deleteProductStructureResponse
                    .getProductStructureList();
            this.setSearchResultToSession(searchResultList);
            if (!(searchResultList != null && searchResultList.size() > 0)) {
                searchResultList = null;
            }

            this.setBreadCrumbText("Product Configuration >> Product Structure"
                    + " >> Locate");
        }
        Logger.logInfo("Returning the method for deleting product structure");
        return "";
    }


    /**
     * This method is used to delete a Product Structure selected by the user.
     * 
     * @return String
     */
    public String deleteProductStructureVersion() {
        Logger.logInfo("Entering the method for deleting product"
                + " structure version");
        // Get the request object
        DeleteProductStructureRequest deleteProductStructureRequest = getDeleteProductStructureVersionRequest();

        // Call the executeService method to get the response
        DeleteProductStructureResponse deleteProductStructureResponse = (DeleteProductStructureResponse) this
                .executeService(deleteProductStructureRequest);
        if (null != deleteProductStructureResponse) {
            // Set the resulting Product Structure from the database
            // from the response to the searchResultList
            searchResultList = deleteProductStructureResponse
                    .getProductStructureList();
            this.setSearchResultToSessionForViewAllVersions(searchResultList);
            if (!(searchResultList != null && searchResultList.size() > 0)) {
                searchResultList = null;
            }
            this.setBreadCrumbText("Product Configuration >> Product "
                    + "Structure >> Locate");
        }
        Logger.logInfo("Returning the method for deleting product"
                + " structure version");
        return "";
    }


    /**
     * This method is used to set the id of the productStructure in the VO which
     * is then set to the DeleteProductStructurerequest.
     * 
     * @return DeleteProductStructureRequest
     */
    private DeleteProductStructureRequest getDeleteProductStructureRequest() {

        // Get the Product Structure id from the backingbean property
        int productStructureId = this.getProductStructureId();

        // Get the locateCriteria from the backingBean
        createProductStructureLocateCriteriaVO();

        // Set the key values from session to the productStructureVO
        ProductStructureVO productStructureVO = this
                .getproductureKeyValuesFromSession(productStructureId);

        // Get the instance of the DeleteProductStructureRequest
        DeleteProductStructureRequest deleteProductStructureRequest = (DeleteProductStructureRequest) this
                .getServiceRequest(ServiceManager.DELETE_PRODUCT_STRUCTURE);

        // Set the VO to the Delete Request Object
        deleteProductStructureRequest.setProductStructureVO(productStructureVO);

        // Set the locateCriteriaVO to the request
        deleteProductStructureRequest
                .setProductStructureLocateCriteriaVO(productStructureLocateCriteriaVO);

        // Set the action of the request to indicate that the deletion of
        // Product Structure
        deleteProductStructureRequest
                .setAction(DeleteProductStructureRequest.DELETE_PRODUCT_STRUCTURE);

        return deleteProductStructureRequest;
    }


    /**
     * 
     * @return DeleteProductStructureRequest.
     */
    private DeleteProductStructureRequest getDeleteProductStructureVersionRequest() {

        int productStructureId = this.getProductStructureId();

        // Create an instance of ProductStructureVO
        ProductStructureVO productStructureVO = null;

        productStructureVO = this
                .getproductureKeyValuesFromSessionForViewAllVersions(productStructureId);

        DeleteProductStructureRequest deleteProductStructureRequest = (DeleteProductStructureRequest) this
                .getServiceRequest(ServiceManager.DELETE_PRODUCT_STRUCTURE);

        // Set the VO to the Delete Request Object
        deleteProductStructureRequest.setProductStructureVO(productStructureVO);

        // Set the locateCriteriaVO to the request
        deleteProductStructureRequest.setProductStructureId(productStructureId);

        // Set the action of the request to indicate that the deletion of
        // Product Structure
        deleteProductStructureRequest
                .setAction(DeleteProductStructureRequest.DELETE_PRODUCT_STRUCTURE);

        return deleteProductStructureRequest;
    }


    /**
     * Returns the businessEntity.
     * 
     * @return String businessEntity.
     */

    public String getBusinessEntity() {
        return businessEntity;
    }


    /**
     * Sets the businessEntity.
     * 
     * @param businessEntity.
     */

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }


    /**
     * Returns the businessGroup.
     * 
     * @return String businessGroup.
     */

    public String getBusinessGroup() {
        return businessGroup;
    }


    /**
     * Sets the businessGroup.
     * 
     * @param businessGroup.
     */

    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     */

    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(String effectiveDate) {
        if (null != effectiveDate)
            this.effectiveDate = effectiveDate.trim();
    }


    /**
     * Returns the entity.
     * 
     * @return String entity.
     */

    public String getEntity() {
        return entity;
    }


    /**
     * Sets the entity.
     * 
     * @param entity.
     */

    public void setEntity(String entity) {
        this.entity = entity;
    }


    /**
     * Returns the expiryDate.
     * 
     * @return String expiryDate.
     */

    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate.
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(String expiryDate) {
        if (null != expiryDate) {
            this.expiryDate = expiryDate.trim();
        }
    }


    /**
     * Returns the group.
     * 
     * @return String group.
     */

    public String getGroup() {
        return group;
    }


    /**
     * Sets the group.
     * 
     * @param group.
     */

    public void setGroup(String group) {
        this.group = group;
    }


    /**
     * Returns the lineOfBusiness.
     * 
     * @return String lineOfBusiness.
     */

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }


    /**
     * Sets the lineOfBusiness.
     * 
     * @param lineOfBusiness.
     */

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * Returns the lob.
     * 
     * @return String lob.
     */

    public String getLob() {
        return lob;
    }


    /**
     * Sets the lob.
     * 
     * @param lob.
     */

    public void setLob(String lob) {
        this.lob = lob;
    }


    /**
     * Returns the name.
     * 
     * @return String name.
     */

    public String getName() {
        return name;
    }


    /**
     * Sets the name.
     * 
     * @param name.
     */

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the productStructureLocateCriteriaVO.
     * 
     * @return ProductStructureLocateCriteriaVO
     *         productStructureLocateCriteriaVO.
     */

    public ProductStructureLocateCriteriaVO getProductStructureLocateCriteriaVO() {
        return productStructureLocateCriteriaVO;
    }


    /**
     * Sets the productStructureLocateCriteriaVO.
     * 
     * @param productStructureLocateCriteriaVO.
     */

    public void setProductStructureLocateCriteriaVO(
            ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO) {
        this.productStructureLocateCriteriaVO = productStructureLocateCriteriaVO;
    }


    /**
     * Returns the productStructureName.
     * 
     * @return String productStructureName.
     */

    public String getProductStructureName() {
        return productStructureName;
    }


    /**
     * Sets the productStructureName.
     * 
     * @param productStructureName.
     */

    public void setProductStructureName(String productStructureName) {
        if (null != productStructureName)
            this.productStructureName = productStructureName.trim();
    }


    /**
     * Returns the searchResultList.
     * 
     * @return List searchResultList.
     */

    public List getSearchResultList() {
        return searchResultList;
    }


    /**
     * Sets the searchResultList.
     * 
     * @param searchResultList.
     */

    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }


    /**
     * Returns the selectedStructureId.
     * 
     * @return String selectedStructureId.
     */

    public String getSelectedStructureId() {
        return selectedStructureId;
    }


    /**
     * Sets the selectedStructureId.
     * 
     * @param selectedStructureId.
     */

    public void setSelectedStructureId(String selectedStructureId) {
        this.selectedStructureId = selectedStructureId;
    }


    /**
     * Returns the validationMessage.
     * 
     * @return List validationMessage.
     */

    public List getValidationMessage() {
        return validationMessage;
    }


    /**
     * Sets the validationMessage.
     * 
     * @param validationMessage.
     */

    public void setValidationMessage(List validationMessage) {
        this.validationMessage = validationMessage;
    }


    /**
     * Returns the productStructureId.
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * Sets the productStructureId.
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }


    /**
     * Returns the versionList.
     * 
     * @return List versionList.
     */

    public List getVersionList() {
        return versionList;
    }


    /**
     * Sets the versionList.
     * 
     * @param versionList.
     */

    public void setVersionList(List versionList) {
        this.versionList = versionList;
    }


    /**
     * @return Returns the actionForTest.
     */
    public String getActionForTest() {
        return actionForTest;
    }


    /**
     * @param actionForTest
     *            The actionForTest to set.
     */
    public void setActionForTest(String actionForTest) {
        this.actionForTest = actionForTest;
    }
    
    /**
     * 
     * @return String
     */
    public String scheduleProductStructureForTest() {
        Logger.logInfo("Entering the method for sheduling product "
                + "structure for test");
        ProductStructureLifeCycleRequest productStructureLifeCycleRequest = getProductStructureLifeCycleRequest();

        ProductStructureLifeCycleResponse productStructureLifeCycleResponse = null;

        createProductStructureLocateCriteriaVO();

        productStructureLifeCycleRequest
                .setProductStructureLocateCriteriaVO(productStructureLocateCriteriaVO);

        if (this.getActionForTest().equals("SCHEDULE_TEST")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.SCHEDULE_FOR_TEST_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("TEST_PASS")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.TEST_PASS_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("TEST_FAIL")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.TEST_FAIL_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("APPROVE")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.APPROVE_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("REJECT")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.REJECT_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("PUBLISH")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.PUBLISH_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("UNLOCK")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.UNLOCK_PRODUCT_STRUCTURE);
        }else {
            throw new IllegalArgumentException("Unknown Life cycle type");
        }
        productStructureLifeCycleResponse = (ProductStructureLifeCycleResponse) this
                .executeService(productStructureLifeCycleRequest);
        Logger.logInfo("Returning the method for sheduling product "
                + "structure for test");
        if (productStructureLifeCycleResponse != null
                && productStructureLifeCycleResponse.isSuccess()) {

            searchResultList = productStructureLifeCycleResponse
                    .getProductStructureList();
            if (!(searchResultList != null && searchResultList.size() > 0)) {
                searchResultList = null;
            }
            this.setSearchResultToSession(searchResultList);
            this
                    .setBreadCrumbText("Product Configuration >> Product Structure "
                            + ">> Locate");
            return "success";

        }
        return "";

    }


    /**
     * Method to schedule test and publish.
     * 
     * @return String
     */

    public String scheduleForTestView() {
        Logger.logInfo("Entering the method for sheduling for test view");
        ProductStructureLifeCycleRequest productStructureLifeCycleRequest = (ProductStructureLifeCycleRequest) getProductStructureLifeCycleRequest();

        productStructureLifeCycleRequest
                .setProductStructureId(productStructureId);

        ProductStructureLifeCycleResponse productStructureLifeCycleResponse = null;

        if (this.getActionForTest().equals("SCHEDULE_TEST")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.SCHEDULE_FOR_TEST_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("TEST_PASS")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.TEST_PASS_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("TEST_FAIL")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.TEST_FAIL_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("APPROVE")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.APPROVE_PRODUCT_STRUCTURE);
        } else if (this.getActionForTest().equals("REJECT")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.REJECT_PRODUCT_STRUCTURE);
        }else if (this.getActionForTest().equals("UNLOCK")) {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.UNLOCK_PRODUCT_STRUCTURE);
        } else {

            productStructureLifeCycleRequest
                    .setAction(ProductStructureLifeCycleRequest.PUBLISH_PRODUCT_STRUCTURE);
        }

        productStructureLifeCycleResponse = (ProductStructureLifeCycleResponse) this
                .executeService(productStructureLifeCycleRequest);
        Logger.logInfo("Returning the method for sheduling for test view");
        if (productStructureLifeCycleResponse != null
                && productStructureLifeCycleResponse.isSuccess()) {

            searchResultList = productStructureLifeCycleResponse
                    .getProductStructureList();
            this.setSearchResultToSessionForViewAllVersions(searchResultList);
            if (!(searchResultList != null && searchResultList.size() > 0)) {
                searchResultList = null;
            }
            this.setBreadCrumbText("Product Configuration >> Product"
                    + " Structure >> Locate");
            return "success";

        }
        return "";

    }


    /**
     * Returns ProductStructureLifeCycleRequest.
     * 
     * @return ProductStructureLifeCycleRequest
     */
    private ProductStructureLifeCycleRequest getProductStructureLifeCycleRequest() {

        int productStructureId = this.getProductStructureId();
        ProductStructureVO productStructureVO = null;
        productStructureVO = this
                .getproductureKeyValuesFromSessionForViewAllVersions(productStructureId);
        if (productStructureVO == null) {
            productStructureVO = this
                    .getproductureKeyValuesFromSession(productStructureId);
        }
        ProductStructureLifeCycleRequest productStructureLifeCycleRequest = (ProductStructureLifeCycleRequest) this
                .getServiceRequest(ServiceManager.LIFE_CYCLE_PRODUCT_STRUCTURE);
        productStructureLifeCycleRequest
                .setProductStructureVO(productStructureVO);
        return productStructureLifeCycleRequest;

    }


    /**
     * Returns the breadCrumbViewAllVersions
     * 
     * @return String breadCrumbViewAllVersions.
     */

    public String getBreadCrumbViewAllVersions() {
        String keyString = (String)(ESAPI.encoder().encodeForHTML(getRequest().getParameter("PSName")));
        if(null!=keyString  && keyString.matches("^[0-9a-zA-Z_]+$")){
        	keyString = keyString.toString();
       }else{
    	   keyString=null;
       }
        if(null!=keyString)
        	getRequest().getSession().setAttribute("keyString",keyString);
        if(null == keyString || keyString.equals(""))
        	keyString =(String)getRequest().getSession().getAttribute("keyString");
        this.setBreadCrumbText("Product Configuration >> Product"
                + " Structure ("+keyString+") >> View All Versions");
        return "dummy";
    }


    /**
     * Sets the breadCrumbViewAllVersions
     * 
     * @param breadCrumbViewAllVersions.
     */

    public void setBreadCrumbViewAllVersions(String breadCrumbViewAllVersions) {
        String keyString = (String)(ESAPI.encoder().encodeForHTML(getRequest().getParameter("PSName")));
        if(null!=keyString && keyString.matches("^[0-9a-zA-Z_]+$")){
        	keyString = keyString;
       }else{
    	   keyString=null;
       }
        this.setBreadCrumbText("Product Configuration >> Product"
                + " Structure ("+keyString+") >> View All Versions");
    }
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Product Structure >> Locate >> Print";
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
}