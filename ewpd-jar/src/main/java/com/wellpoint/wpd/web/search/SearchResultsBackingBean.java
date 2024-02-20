/*
 * Created on Jun 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.product.bo.ProductSearchResult;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.search.request.BenefitComponentSearchViewRequest;
import com.wellpoint.wpd.common.search.request.BenefitViewRequest;
import com.wellpoint.wpd.common.search.request.ContractViewRequest;
import com.wellpoint.wpd.common.search.request.DomainFetchRequest;
import com.wellpoint.wpd.common.search.request.ProductStructureViewRequest;
import com.wellpoint.wpd.common.search.request.ProductViewRequest;
import com.wellpoint.wpd.common.search.request.RetrieveAssociationRequest;
import com.wellpoint.wpd.common.search.request.RetrieveAttachmentRequest;
import com.wellpoint.wpd.common.search.request.RetrieveRequest;
import com.wellpoint.wpd.common.search.request.SortRequest;
import com.wellpoint.wpd.common.search.response.DomainFetchResponse;
import com.wellpoint.wpd.common.search.response.RetrieveAssociationResponse;
import com.wellpoint.wpd.common.search.response.RetrieveAttachmentResponse;
import com.wellpoint.wpd.common.search.response.SearchResponse;
import com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitComponentViewObject;
import com.wellpoint.wpd.common.search.result.BenefitIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitViewObject;
import com.wellpoint.wpd.common.search.result.ContractIdentifier;
import com.wellpoint.wpd.common.search.result.ContractViewObject;
import com.wellpoint.wpd.common.search.result.NotesIdentifier;
import com.wellpoint.wpd.common.search.result.ObjectDetail;
import com.wellpoint.wpd.common.search.result.ObjectIdentifier;
import com.wellpoint.wpd.common.search.result.ProductIdentifier;
import com.wellpoint.wpd.common.search.result.ProductStructureIdentifier;
import com.wellpoint.wpd.common.search.result.ProductStructureViewObject;
import com.wellpoint.wpd.common.search.result.ProductViewObject;
import com.wellpoint.wpd.common.search.result.SearchResult;
import com.wellpoint.wpd.common.search.result.SearchResultDetail;
import com.wellpoint.wpd.common.search.result.SearchResultSummary;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSearchBackingBean;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.contract.ContractKeyObject;
import com.wellpoint.wpd.web.contract.ContractSearchBackingBean;
import com.wellpoint.wpd.web.contract.ContractSession;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.notes.NotesSearchBackingBean;
import com.wellpoint.wpd.web.product.ProductSearchBackingBean;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.productstructure.ProductStructureBackingBean;
import com.wellpoint.wpd.web.productstructure.ProductStructureSearchBackingBean;
import com.wellpoint.wpd.web.productstructure.ProductStructureSessionObject;
import com.wellpoint.wpd.web.search.pagination.MultipageSearchResult;
import com.wellpoint.wpd.web.search.pagination.Paginator;
import com.wellpoint.wpd.web.search.util.SearchUtil;
import com.wellpoint.wpd.web.standardbenefit.StandardBenefitSearchBackingBean;
import com.wellpoint.wpd.web.standardbenefit.StandardBenefitSessionObject;
import com.wellpoint.wpd.web.util.SearchSortSessionObject;
import com.wellpoint.wpd.web.util.SortDetail;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SearchResultsBackingBean extends SearchResultBackingBean {
	
    private boolean authorizedForView;

	private String effectiveDate;
	
	private String expiryDate;
	
	private String lob;
	
	private String businessEntity;
	
	private String businessGroup;
	
	private String productName;
	
	private String benefitName;
	
	private String productstructurename;
	
	private String contractid;
	
	private int productKey;
	
	private int benefitComponentKey;
	
	private String benefitComponentName;
	
	private int benefitComponentVersion;
	
	private int productStructureId;

	private String query;
	
	private String noteid;
	
	private String notename;
	
	private int benefitKey;
	
	private int checkForView;
	
	private List contracts;

	private List product;

	private List productStructure;

	private List benefit;

	private List benefitComponent;

	private List benefitLevel;
	
	private List benefitLine;

	private List notes;

	private RetrieveRequest retrieveRequest;

	private int numberOfPages;

	private int index = -1;

	private int clickedIndex = -1;

	private String pageValue;

	private String pageStatus;
	
	HtmlPanelGrid panel = null;
	
	private String associationBreadCrumb = "";
	
	private String fieldToSort;
	
	private int contractSortColumn = 1;
	
	private String contractSortOrder = SearchConstants.ASCENDING;
	
	private int productSortColumn = 1;
	
	private String productSortOrder = SearchConstants.ASCENDING;
	
	private int productStructureSortColumn = 1;
	
	private String productStructureSortOrder = SearchConstants.ASCENDING;
	
	private int benefitSortColumn = 1;
	
	private String benefitSortOrder = SearchConstants.ASCENDING;
	
	private int benefitLevelSortColumn = 1;
	
	private String benefitLevelSortOrder = SearchConstants.ASCENDING;
	
	private int notesSortColumn = 1;
	
	private String notesSortOrder = SearchConstants.ASCENDING;
	
	private int benefitComponentSortColumn = 1;
	
	private String benefitComponentSortOrder = SearchConstants.ASCENDING;
		
	//column images
	private String columnOneImageAsc;
	
	private String columnTwoImageAsc;
	
	private String columnThreeImageAsc;
	
	private String columnFourImageAsc;
	
	private String columnFiveImageAsc;
	
	private String columnSixImageAsc;
	
	private String columnSevenImageAsc;

	private String columnOneImageDesc;
	
	private String columnTwoImageDesc;
	
	private String columnThreeImageDesc;
	
	private String columnFourImageDesc;
	
	private String columnFiveImageDesc;
	
	private String columnSixImageDesc;
	
	private String columnSevenImageDesc;
	
	private String association;
	
	private List attachmentObjectTypeList;
	
	private String currentAttachedObject;
	
	private boolean attachedObjectValidate = false;
	
	private String associatedObjectSelectedIndex;
	
	private int selectedIndex = 0;
	
	private boolean benefitLinePva = false;
	
	private List allContracts;

	private List allProduct;

	private List allProductStructure;

	private List allBenefit;

	private List allBenefitComponent;

	private List allBenefitLevel;
	
	private List allBenefitLine;

	private List allNotes;
	
	private String getImageForColumn(int column, String sortOrder){

		SearchSortSessionObject object = (SearchSortSessionObject)
				getSession().getAttribute(SearchConstants.SORT_SESSION_OBJECT);
		String objectType = null;
		if(getViewAssociationFromSession()){
			objectType = (String)getSession().getAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
			objectType += SearchConstants.ASSOCIATION;
		}else if(getViewAttachment()){
			objectType = (String)getSession().getAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE);
			objectType += SearchConstants.ATTACHMENTS;			
		}else{
			objectType = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
		}
		if(null != object){
			SortDetail detail = (SortDetail)object.getSortInfo(objectType);
			if(null != detail){
				if(column == detail.getSortColumn() && detail.getSortDirection().equals(sortOrder)){
					if(SearchConstants.ASCENDING.equals(sortOrder)){
						return SearchConstants.ASCENDING_SELECTED_IMAGE;
					}else{
						return SearchConstants.DESCENDING_SELECTED_IMAGE;
					}
				}
			}else{
				int toClumn = 1;
				if(SearchConstants.BENEFIT_LEVEL.equals(objectType) || (SearchConstants.BENEFIT_LINE + SearchConstants.ATTACHMENTS).equals(objectType)){
					toClumn = 3;
				}
				if(column == toClumn){
					if(SearchConstants.ASCENDING.equals(sortOrder)){
						return SearchConstants.ASCENDING_SELECTED_IMAGE;
					}
				}
				
			}
		}else{
			int toClumn = 1;
			if(SearchConstants.BENEFIT_LEVEL.equals(objectType) || (SearchConstants.BENEFIT_LINE + SearchConstants.ATTACHMENTS).equals(objectType)){
				toClumn = 3;
			}
			if(column == toClumn){
				if(SearchConstants.ASCENDING.equals(sortOrder)){
					return SearchConstants.ASCENDING_SELECTED_IMAGE;
				}
			}
		}
		if(SearchConstants.ASCENDING.equals(sortOrder)){
			return SearchConstants.ASCENDING_IMAGE;
		}else{
			return SearchConstants.DESCENDING_IMAGE;
		}
	}
	
	

	private boolean getViewAssociationFromSession() {
		String association = (String) getSession().getAttribute(
				SearchConstants.VIEW_ASSOCIATION);
		return SearchConstants.VIEW_ASSOCIATION_TRUE.equals(association);
	}
	
	/**
	 * @param panel
	 *            The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	private int retrieveNumberOfPagesForPanel() {
		if(getViewAssociationFromSession()){
			String objectType = (String)getSession()
				.getAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
			MultipageSearchResult result = (MultipageSearchResult) getSession()
				.getAttribute(objectType + SearchConstants.ASSOCIATION);
			return result.getTotalNumberOfPages();
		}
		else if(getViewAttachment()){
			String objectType = (String)getSession()
				.getAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE);
			MultipageSearchResult result = (MultipageSearchResult) getSession()
				.getAttribute(objectType + SearchConstants.ATTACHMENTS);
			return result.getTotalNumberOfPages();
		}
		return 0;
	}

	public HtmlPanelGrid getPanel() {
		if ((getViewAssociationFromSession() || getViewAttachment())
				&& retrieveNumberOfPagesForPanel() > 1) {
		    
			panel = new HtmlPanelGrid();
			//panel.setWidth("30%");
			panel.setColumns(8);
			panel.setBorder(0);
			//panel.setStyleClass("outputText");
			panel.setCellpadding("0");
			panel.setCellspacing("3");
			panel.setStyle("margin-right:auto;margin-left:auto;");
			//panel.setHeaderClass("Head");

			HtmlOutputText htmlOutputText = new HtmlOutputText();

			ValueBinding value = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{searchResultsBackingBean.pageStatus}");

			htmlOutputText.setValueBinding("value", value);

			HtmlOutputText pageText = new HtmlOutputText();
			pageText.setValue("Page ");
			HtmlCommandButton htmlNextbutton = new HtmlCommandButton();
			htmlNextbutton.setImage("../../images/24-arrow-next.png");
			MethodBinding methodBinding;

			methodBinding = FacesContext.getCurrentInstance().getApplication()
					.createMethodBinding(
							"#{searchResultsBackingBean.getNextPage}",
							new Class[] {});
			htmlNextbutton.setAction(methodBinding);

			HtmlCommandButton htmlFirstbutton = new HtmlCommandButton();
			htmlFirstbutton.setImage("../../images/24-arrow-first.png");

			methodBinding = FacesContext.getCurrentInstance().getApplication()
					.createMethodBinding(
							"#{searchResultsBackingBean.getFirstPage}",
							new Class[] {});
			htmlFirstbutton.setAction(methodBinding);

			HtmlCommandButton htmlLastbutton = new HtmlCommandButton();
			htmlLastbutton.setImage("../../images/24-arrow-last.png");

			methodBinding = FacesContext.getCurrentInstance().getApplication()
					.createMethodBinding(
							"#{searchResultsBackingBean.getLastPage}",
							new Class[] {});
			htmlLastbutton.setAction(methodBinding);

			HtmlCommandButton htmlPrevbutton = new HtmlCommandButton();
			htmlPrevbutton.setImage("../../images/24-arrow-previous.png");

			methodBinding = FacesContext.getCurrentInstance().getApplication()
					.createMethodBinding(
							"#{searchResultsBackingBean.getPreviousPage}",
							new Class[] {});
			htmlPrevbutton.setAction(methodBinding);
			HtmlInputText htmlInputText = new HtmlInputText();
			//htmlInputText.setSize(1);
			htmlInputText.setMaxlength(2);
			htmlInputText.setStyleClass("formInputField");
			htmlInputText.setStyle("width:18px;");
			ValueBinding valueBinding = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{searchResultsBackingBean.pageValue}");
			htmlInputText.setValueBinding("value", valueBinding);
			HtmlCommandButton htmlCommandButton = new HtmlCommandButton();
			htmlCommandButton.setValue("Go");
			htmlCommandButton.setStyleClass("wpdbutton");
			//htmlCommandButton.setImage("../../images/Go.bmp");
			methodBinding = FacesContext.getCurrentInstance().getApplication()
					.createMethodBinding(
							"#{searchResultsBackingBean.pageCall}",
							new Class[] {});
			htmlCommandButton.setAction(methodBinding);

			//panel.getChildren().add(htmlOutputText);
			panel.getChildren().add(htmlFirstbutton);
			panel.getChildren().add(htmlPrevbutton);
			panel.getChildren().add(pageText);
			panel.getChildren().add(htmlInputText);
			panel.getChildren().add(htmlOutputText);
			panel.getChildren().add(htmlCommandButton);
			panel.getChildren().add(htmlNextbutton);
			panel.getChildren().add(htmlLastbutton);
			return panel;

		}

		return null;
	}
	
	private List getObjects(String objectType){
		List list = (List) getSession().getAttribute(objectType + SearchConstants.OBJECT_DETAIL);
		Integer setPageNumber = (Integer)getSession().getAttribute(SearchConstants.SET_PAGE_NUMBER);
		if(null == setPageNumber){
			updateDetailsForCurrentObject();
		}
		else{
			int setPageNum = setPageNumber.intValue();
			if(null == list || setPageNum != getCurrentPageNumber()){
				updateDetailsForCurrentObject();
	        }
		}
		list = (List) getSession().getAttribute(objectType + SearchConstants.OBJECT_DETAIL);
		return list;
	}

	/**
	 *  
	 */
	public SearchResultsBackingBean() {
		retrieveRequest = (RetrieveRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_REQUEST);
		String objectType = (String) getSession().getAttribute(
				SearchConstants.OBJECT_TYPE);
		setBreadCrumbText("Search >> Search Results >> "+new SearchUtil().getBreadCrumbForObjectType(objectType));
	}
	
	private List getObjectDetails(String objectType){
		if (getViewAssociationFromSession()) {
				MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
				.getAttribute(
						objectType
								+ SearchConstants.ASSOCIATION);
			if (null != multipageSearchResult) {
				return getObjectList(Arrays.asList((multipageSearchResult
						.getPage(getCurrentPageNumber())).getObjects()));
			}
		}else if(getViewAttachment()){
			MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
			.getAttribute(
					objectType
							+ SearchConstants.ATTACHMENTS);
			if (null != multipageSearchResult) {
				return getObjectList(Arrays.asList((multipageSearchResult
						.getPage(getCurrentPageNumber())).getObjects()));
			}
		}
		else {
			if (null != getSession().getAttribute(SearchConstants.PAGE_NUMBER)
					&& null != getSession().getAttribute(
							SearchConstants.OBJECT_TYPE)) {
				if (objectType.equalsIgnoreCase(String
						.valueOf(getSession().getAttribute(
								SearchConstants.OBJECT_TYPE)))) {
					int pageNo = getCurrentPageNumber();
					MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
							.getAttribute(objectType);
					return getObjectList(Arrays.asList((multipageSearchResult
							.getPage(pageNo)).getObjects()));
				} else {
					MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
							.getAttribute(objectType);
					return getObjectList(Arrays.asList((multipageSearchResult
							.getFirstPage()).getObjects()));
				}
			}
		}
		return null;
	}
	
	private List getAllObjectDetails(String objectType){
		List allObjects=new ArrayList();
		if (getViewAssociationFromSession()) {
				MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
				.getAttribute(
						objectType
								+ SearchConstants.ASSOCIATION);
				if (null != multipageSearchResult) {
					int noOfPages=multipageSearchResult.getPages().length;
					for(int i=1;i<=noOfPages;i++){
						allObjects.addAll(getObjectList(Arrays.asList((multipageSearchResult
								.getPage(i)).getObjects())));
					}
					return allObjects;
				}
		}else if(getViewAttachment()){
			MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
			.getAttribute(
					objectType
							+ SearchConstants.ATTACHMENTS);
			if (null != multipageSearchResult) {
				int noOfPages=multipageSearchResult.getPages().length;
				for(int i=1;i<=noOfPages;i++){
					allObjects.addAll(getObjectList(Arrays.asList((multipageSearchResult
							.getPage(i)).getObjects())));
				}
				return allObjects;
			}
		}
		else {
			if (null != getSession().getAttribute(SearchConstants.PAGE_NUMBER)
					&& null != getSession().getAttribute(
							SearchConstants.OBJECT_TYPE)) {
				if (objectType.equalsIgnoreCase(String
						.valueOf(getSession().getAttribute(
								SearchConstants.OBJECT_TYPE)))) {
					int pageNo = getCurrentPageNumber();
					MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
							.getAttribute(objectType);
					if (null != multipageSearchResult) {
						int noOfPages=multipageSearchResult.getPages().length;
						for(int i=1;i<=noOfPages;i++){
							allObjects.addAll(getObjectList(Arrays.asList((multipageSearchResult
									.getPage(i)).getObjects())));
						}
						return allObjects;
					}
				} else {
					MultipageSearchResult multipageSearchResult = (MultipageSearchResult) getSession()
							.getAttribute(objectType);
					if (null != multipageSearchResult) {
						int noOfPages=multipageSearchResult.getPages().length;
						for(int i=1;i<=noOfPages;i++){
							allObjects.addAll(getObjectList(Arrays.asList((multipageSearchResult
									.getPage(i)).getObjects())));
						}
						return allObjects;
					}
				}
			}
		}
		return null;
	}
	
	private void updateDetailsForCurrentObject(){
		String objectType = "";
		if(getViewAssociationFromSession()){
			objectType = (String)getSession().getAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
		}else if(getViewAttachment()){
			objectType = (String)getSession().getAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE);
		}else{
			objectType = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
		}
		List results = getObjectDetails(objectType);
		
		getSession().setAttribute(objectType +  SearchConstants.OBJECT_DETAIL,results);
		getSession().setAttribute(SearchConstants.SET_PAGE_NUMBER, new Integer(getCurrentPageNumber()));
	}
	private List getDetailsForCurrentObject(){
		String objectType = "";
		if(getViewAssociationFromSession()){
			objectType = (String)getSession().getAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
		}else if(getViewAttachment()){
			objectType = (String)getSession().getAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE);
		}else{
			objectType = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
		}
		List results = getAllObjectDetails(objectType);
		return results;

	}
	/**
	 * @return Returns the benefit.
	 */
	public List getBenefit() {
		return getObjects(SearchConstants.BENEFIT);
	}

	/**
	 * @param benefit
	 *            The benefit to set.
	 */
	public void setBenefit(List benefit) {
		this.benefit = benefit;
	}

	/**
	 * @return Returns the benefitComponent.
	 */
	public List getBenefitComponent() {
		return getObjects(SearchConstants.BENEFIT_COMPONENTS);
	}

	/**
	 * @param benefitComponent
	 *            The benefitComponent to set.
	 */
	public void setBenefitComponent(List benefitComponent) {
		this.benefitComponent = benefitComponent;
	}

	/**
	 * @return Returns the benefitLevel.
	 */
	public List getBenefitLevel() {
		if(getViewAttachment()){
			return getObjects(SearchConstants.BENEFIT_LINE);
		}
		return getObjects(SearchConstants.BENEFIT_LEVEL);
	}

	/**
	 * @param benefitLevel
	 *            The benefitLevel to set.
	 */
	public void setBenefitLevel(List benefitLevel) {
		this.benefitLevel = benefitLevel;
	}

	/**
	 * @return Returns the contracts.
	 */
	public List getContracts() {
		return getObjects(SearchConstants.CONTRACT);
	}

	/**
	 * @param contracts
	 *            The contracts to set.
	 */
	public void setContracts(List contracts) {
		this.contracts = contracts;
	}

	/**
	 * @return Returns the notes.
	 */
	public List getNotes() {
		return getObjects(SearchConstants.NOTES);
	}

	/**
	 * @param notes
	 *            The notes to set.
	 */
	public void setNotes(List notes) {
		this.notes = notes;
	}

	/**
	 * @return Returns the product.
	 */
	public List getProduct() {
		return getObjects(SearchConstants.PRODUCT);
	}

	/**
	 * @param product
	 *            The product to set.
	 */
	public void setProduct(List product) {
		this.product = product;
	}

	/**
	 * @return Returns the productStructure.
	 */
	public List getProductStructure() {
		return getObjects(SearchConstants.PRODUCT_STRUCTURES);
	}

	/**
	 * @param productStructure
	 *            The productStructure to set.
	 */
	public void setProductStructure(List productStructure) {
		this.productStructure = productStructure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.web.search.SearchResultBackingBean#viewResult()
	 */
	public void viewResult() {
		// TODO Auto-generated method stub

	}
	
	private int getCurrentPageNumber(){
		int pno = Integer.valueOf(
				String.valueOf(getSession().getAttribute(
						SearchConstants.PAGE_NUMBER))).intValue();
		return pno;
	}
	
	private void setCurrentPageNumber(int pNo){
		getSession().setAttribute(
						SearchConstants.PAGE_NUMBER, new Integer(pNo));
	}

	/*
	 * (non-Javadoc)20
	 * 
	 * @see com.wellpoint.wpd.web.search.SearchResultBackingBean#association()
	 */
	public String viewAssociation() {
		
		String associationObjectType = "";
		String objType;
		int pno = getCurrentPageNumber();
		if (!getViewAssociationFromSession()) {
			if(getViewAttachment()){
				objType = (String) getSession().getAttribute(
						SearchConstants.ATTACHMENT_OBJECT_TYPE) + SearchConstants.ATTACHMENTS;
			}else{				
				objType = (String) getSession().getAttribute(
						SearchConstants.OBJECT_TYPE);
			}
		}else{
			objType = (String) getSession().getAttribute(
					SearchConstants.ASSOCIATION_OBJECT_TYPE) + SearchConstants.ASSOCIATION;
		}
		SearchUtil searchUtil = new SearchUtil();
		List pageList = searchUtil.getPageListForObjectType(objType,pno);


		ObjectIdentifier identifier = (ObjectIdentifier) pageList
				.get(clickedIndex);

		RetrieveAssociationRequest associationRequest = (RetrieveAssociationRequest) getServiceRequest(ServiceManager.RETRIEVE_ASSOCIATION_REQUEST);

		associationRequest.setObjectIdentifier(identifier);

		RetrieveAssociationResponse response = (RetrieveAssociationResponse) executeService(associationRequest);
		if (null != response && !response.isErrorMessageInList()) {
			List searchResults = response.getSearchResults();

			if (searchResults != null && searchResults.size() > 0) {
				SearchResult searchResult = (SearchResult) searchResults.get(0);

				associationObjectType = searchResult.getType();
				String sessionObejctType = associationObjectType
						+ SearchConstants.ASSOCIATION;

				MultipageSearchResult multipageSearchResult = new Paginator()
						.paginate(searchResult);

				numberOfPages = multipageSearchResult.getTotalNumberOfPages();

				getSession().setAttribute(sessionObejctType,
						multipageSearchResult);
				

				setViewAssociation();
				//clearing attachments once view association is selected
				clearViewAttachments();
				getSession().setAttribute(
						SearchConstants.ASSOCIATION_OBJECT_TYPE,associationObjectType);
				setCurrentPageNumber(1);
			}
		}
		if (!"".equals(associationObjectType)) {
			ObjectDetail objDetail = response.getDetail();
			String objectWithKeyValue = searchUtil
					.getKeyValueForObject(objDetail);
			if (associationBreadCrumb != null
					&& !"".equals(associationBreadCrumb)) {
				associationBreadCrumb = associationBreadCrumb.substring(0,
						associationBreadCrumb.lastIndexOf(">>") + 3);
			}
			associationBreadCrumb = associationBreadCrumb
					+ objectWithKeyValue
					+ " >> "
					+ searchUtil
							.getBreadCrumbForObjectType(associationObjectType);
		}
		new SearchUtil().clearAllDetails();
		updateDetailsForCurrentObject();
		if(null != response.getMessages() && response.getMessages().size() > 0){
			addAllMessagesToRequest(response.getMessages());
		}
		return associationObjectType;
	}
	
	/*
	 * (non-Javadoc)20
	 * 
	 * @see com.wellpoint.wpd.web.search.SearchResultBackingBean#association()
	 */
	public String viewAttachments() {
		
		RetrieveAttachmentRequest request = (RetrieveAttachmentRequest)getServiceRequest(
				ServiceManager.RETRIEVE_ATTACHMENT_REQUEST);
		List errorMessages = null;
		int pNo = getCurrentPageNumber();
		String objectType = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
		if(SearchConstants.NOTES.equals(objectType)){
			List pageList = new SearchUtil().getPageListForObjectType(objectType,pNo);
			NotesIdentifier identifier = (NotesIdentifier) pageList.get(clickedIndex);
			
			request.setIdentifier(identifier);
			
			RetrieveAttachmentResponse searchResponse = (RetrieveAttachmentResponse)executeService(request);
			List searchResults = searchResponse.getSearchResults();
			
			List objectTypes = new ArrayList();
			String resultObjectType = null;
			//paginating and storing in session
			if(searchResults != null){
				for (int i = 0; i < searchResults.size(); i++) {
					SearchResult searchResult = (SearchResult) searchResults.get(i);
					resultObjectType = searchResult.getType();
					objectTypes.add(resultObjectType);
					MultipageSearchResult result = 
						new Paginator().paginate(searchResult);
					//updating information to drop down list
					addSelectItemInDropDown(result);
					getSession().setAttribute(resultObjectType + SearchConstants.ATTACHMENTS,result);
				}
				//updating object type for attachements
				String returnObjectType = updateSessionDetailsForAttachments(objectTypes);
				//setting current page number to 1
				setCurrentPageNumber(1);
				//setting attachment mode in session
				setViewAttachment();
				//setting bread crumb
				SearchUtil searchUtil = new SearchUtil();
				if (!"".equals(returnObjectType)) {
					ObjectDetail objDetail = searchResponse.getDetail();
					String objectWithKeyValue = searchUtil
							.getKeyValueForObject(objDetail);
					if (associationBreadCrumb != null
							&& !"".equals(associationBreadCrumb)) {
						associationBreadCrumb = associationBreadCrumb.substring(0,
								associationBreadCrumb.lastIndexOf(">>") + 3);
					}
					associationBreadCrumb = associationBreadCrumb
							+ objectWithKeyValue
							+ " >> "
							+ searchUtil
									.getBreadCrumbForObjectType(returnObjectType);
				}
				new SearchUtil().clearAllDetails();
				updateDetailsForCurrentObject();
				if(searchResponse.getMessages() != null && searchResponse.getMessages().size() > 0){
					addAllMessagesToRequest(searchResponse.getMessages());
				}
				return returnObjectType;
			}else{
				errorMessages = searchResponse.getMessages();
			}
		}
		if(errorMessages != null && errorMessages.size() > 0){
			addAllMessagesToRequest(errorMessages);
		}
		return SearchConstants.NOTES;
	}
	
	/**
	 * @param result
	 * @param resultObjectType
	 */
	private void addSelectItemInDropDown(MultipageSearchResult result) {
		String type = result.getType();
		int resCount = result.getTotalNumberOfResults();
		SelectItem item = null;

		if(SearchConstants.BENEFIT.equals(type)){
			item = new SelectItem(SearchConstants.BENEFIT, "Benefit ("+resCount + ")");
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			item = new SelectItem(SearchConstants.BENEFIT_COMPONENTS, "Benefit Components ("+resCount + ")");
		}
		else if(SearchConstants.BENEFIT_LEVEL.equals(type) || SearchConstants.BENEFIT_LINE.equals(type)){
			item = new SelectItem(SearchConstants.BENEFIT_LEVEL, "Benefit Level ("+resCount + ")");
		}
		else if(SearchConstants.CONTRACT.equals(type)){
			item = new SelectItem(SearchConstants.CONTRACT, "Contract ("+resCount + ")");
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			item = new SelectItem(SearchConstants.PRODUCT, "Product ("+resCount + ")");
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			item = new SelectItem(SearchConstants.PRODUCT_STRUCTURES, "Product Structures ("+resCount + ")");
		}
		attachmentObjectTypeList = (List)getSession().getAttribute(SearchConstants.ATTACHMENT_RESULT_SUMMARY);
		if(null == attachmentObjectTypeList){
			attachmentObjectTypeList = new ArrayList();
			getSession().setAttribute(SearchConstants.ATTACHMENT_RESULT_SUMMARY,attachmentObjectTypeList);
		}
		attachmentObjectTypeList.add(item);
	}

	private String updateSessionDetailsForAttachments(List objectTypes){
		if(null != objectTypes){
			String objectType = "";
			if(objectTypes.contains(SearchConstants.CONTRACT)){
				objectType = SearchConstants.CONTRACT;
			}else if(objectTypes.contains(SearchConstants.PRODUCT)){
				objectType = SearchConstants.PRODUCT;
			}else if(objectTypes.contains(SearchConstants.PRODUCT_STRUCTURES)){
				objectType = SearchConstants.PRODUCT_STRUCTURES;
			}else if(objectTypes.contains(SearchConstants.BENEFIT_COMPONENTS)){
				objectType = SearchConstants.BENEFIT_COMPONENTS;
			}else if(objectTypes.contains(SearchConstants.BENEFIT)){
				objectType = SearchConstants.BENEFIT;
			}else if(objectTypes.contains(SearchConstants.BENEFIT_LINE)){
				objectType = SearchConstants.BENEFIT_LINE;
				benefitLinePva = true;
			}
			//updating object type
			if(!"".equals(objectType)){
				getSession().setAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE, objectType);
			}
			return objectType;
		}
		return "";
	}
	
	private void clearViewAttachments(){
		attachmentObjectTypeList = null;
		new SearchUtil().clearAllAttachments();
	}
	
	private void setViewAttachment() {
		getSession().setAttribute(SearchConstants.VIEW_ATTACHMENT,
				SearchConstants.VIEW_ATTACHMENT_TRUE);
	}
	
	private boolean getViewAttachment() {
		String attachment = (String)getSession().
				getAttribute(SearchConstants.VIEW_ATTACHMENT);
		if(SearchConstants.VIEW_ATTACHMENT_TRUE.equals(attachment)){
			return true;
		}
		return false;
	}

	public String associatedObjectSelectedIndex(){
		
		List keys = new SearchUtil().getOrderedAttachementKeys();
		String key = null;
		int count = 0;
		Iterator iterator = keys.iterator();
		while(iterator.hasNext()){
			key = (String)iterator.next();
			MultipageSearchResult result = (MultipageSearchResult)getSession().
					getAttribute(key + SearchConstants.ATTACHMENTS);
			if(result != null){
				if(count == selectedIndex){
					getSession().setAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE,
							key);
					setCurrentPageNumber(1);
					if (associationBreadCrumb != null
							&& !"".equals(associationBreadCrumb)) {
						associationBreadCrumb = associationBreadCrumb.substring(0,
								associationBreadCrumb.lastIndexOf(">>") + 3);
						associationBreadCrumb += new SearchUtil().getMenuNameForObjectType(key);
					}
					
					break;
				}
				count ++;
			}
		}
		return key;
	}
	
	private void setViewAssociation() {
		getSession().setAttribute(SearchConstants.VIEW_ASSOCIATION,
				SearchConstants.VIEW_ASSOCIATION_TRUE);
		association = SearchConstants.VIEW_ASSOCIATION_TRUE;
	}
	
	public String getNextPage() {
		String associationObjectType = null;
		int pNo = getCurrentPageNumber();
		if (pNo < retrieveNumberOfPagesForPanel()) {
			setCurrentPageNumber((pNo + 1));
		}
		updateDetailsForCurrentObject();
		return associationObjectType;
	}

	public String getFirstPage() {
		String associationObjectType = null;
		setCurrentPageNumber(1);
		updateDetailsForCurrentObject();
		return "";
	}

	public String getLastPage() {
		String associationObjectType = null;
		setCurrentPageNumber(retrieveNumberOfPagesForPanel());
		updateDetailsForCurrentObject();
		return "";
	}

	public String getPreviousPage() {
		String associationObjectType = "";
		int pNo = getCurrentPageNumber();
		if (pNo > 1) {
			setCurrentPageNumber((pNo-1));
		}
		updateDetailsForCurrentObject();
		return associationObjectType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.web.search.SearchResultBackingBean#viewMoreActions()
	 */
	public void viewMoreActions() {
		// TODO Auto-generated method stub

	}
	
	private String getSortOrderForObjectType(String type){
		if(SearchConstants.BENEFIT.equals(type)){
			return benefitSortOrder;
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			return benefitComponentSortOrder;
		}
		else if(SearchConstants.BENEFIT_LEVEL.equals(type)){
			return benefitLevelSortOrder;
		}
		else if(SearchConstants.BENEFIT_LINE.equals(type)){
			return benefitLevelSortOrder;
		}
		else if(SearchConstants.CONTRACT.equals(type)){
			return contractSortOrder;			
		}
		else if(SearchConstants.NOTES.equals(type)){
			return notesSortOrder;
			
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			return productSortOrder;
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			return productStructureSortOrder;
		}
		return null;
	}
	
	private int getSortColumnForObjectType(String type){
		if(SearchConstants.BENEFIT.equals(type)){
			return benefitSortColumn;
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			return benefitComponentSortColumn;
		}
		else if(SearchConstants.BENEFIT_LEVEL.equals(type)){
			return benefitLevelSortColumn;
		}
		else if(SearchConstants.BENEFIT_LINE.equals(type)){
			return benefitLevelSortColumn;
		}
		else if(SearchConstants.CONTRACT.equals(type)){
			return contractSortColumn;
		}
		else if(SearchConstants.NOTES.equals(type)){
			return notesSortColumn;
			
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			return productSortColumn;
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			return productStructureSortColumn;
		}
		return 0;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.web.search.SearchResultBackingBean#sort()
	 */
	public void sort() {
		String requestObjectType;
		String objType;
		if(getViewAssociationFromSession()){
			requestObjectType = (String)getSession().getAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
			objType = requestObjectType + SearchConstants.ASSOCIATION;
		}else if(getViewAttachment()){
			requestObjectType = (String)getSession().getAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE);
			objType = requestObjectType + SearchConstants.ATTACHMENTS;
		}
		else{
			requestObjectType = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
			objType = requestObjectType;
		}
		SortRequest request = (SortRequest)getServiceRequest(ServiceManager.SORT_REQUEST);
		request.setFieldToSort(fieldToSort);
		request.setObjectType(requestObjectType);
		
		String sortOrder = getSortOrderForObjectType(requestObjectType);
		int sortColumn = getSortColumnForObjectType(requestObjectType);
		request.setSortOrder(sortOrder);
		
		SearchUtil searchUtil = new SearchUtil();
		List indentifiers = null;
		int queryResultCount = 0;
		//updating session
		updateSession(sortOrder, sortColumn, objType);
		
		indentifiers = searchUtil.getWholeResultForObjectType(objType);
		queryResultCount = searchUtil.getQueryResultForObjectType(objType);
		
		request.setObjectIdentifiers(indentifiers);
		
		SearchResponse response=(SearchResponse)executeService(request);
		SearchResultSummary searchResult = (SearchResultSummary)response.getSearchResults().get(0);
		//setting paginated results to session
		MultipageSearchResult multipageSearchResult = new Paginator().paginate(searchResult);
		multipageSearchResult.setQueryResultCount(queryResultCount);
		if(getViewAssociationFromSession()){
			getSession().setAttribute(searchResult.getType()+SearchConstants.ASSOCIATION, multipageSearchResult);
		}else if(getViewAttachment()){
			getSession().setAttribute(searchResult.getType()+SearchConstants.ATTACHMENTS, multipageSearchResult);
		}
		else{
			getSession().setAttribute(searchResult.getType(), multipageSearchResult);
		}
		setCurrentPageNumber(1);
		if(!(getViewAssociationFromSession() || getViewAttachment())){
			// Inorder to update the tree state to make the first page as selected.
			getRequest().setAttribute(SearchConstants.SORT_DONE,"TRUE");
		}
		updateDetailsForCurrentObject();
		
	}

	/**
	 * @param sortOrder
	 * @param sortColumn
	 * @param objectType2
	 */
	private void updateSession(String sortOrder, int sortColumn, String objectType) {
		SearchSortSessionObject object = (SearchSortSessionObject)
				getSession().getAttribute(SearchConstants.SORT_SESSION_OBJECT);
		if(null == object){
			object = new SearchSortSessionObject();
			getSession().setAttribute(SearchConstants.SORT_SESSION_OBJECT, object);
		}
		SortDetail detail = (SortDetail)object.getSortInfo(objectType);
		if(null == detail){
			detail = new SortDetail();
			detail.setSortDirection(sortOrder);
			detail.setSortColumn(sortColumn);
			object.setSortInfo(objectType,detail);
		}else{
			detail.setSortDirection(sortOrder);
			detail.setSortColumn(sortColumn);			
		}
	}
	
	/**
	 * @param sortOrder
	 * @param sortColumn
	 * @param objectType2
	 */
	private SortDetail getSortDetailFromSession(String objectType) {
		SearchSortSessionObject object = (SearchSortSessionObject)
				getSession().getAttribute(SearchConstants.SORT_SESSION_OBJECT);
		if(null != object){
			return (SortDetail)object.getSortInfo(objectType);
		}
		return null;
	}


	private List getObjectList(List searchObjects) {
		retrieveRequest.setObjectIdentifiers(searchObjects);
		SearchResponse searchResponse = (SearchResponse) this
				.executeService(retrieveRequest);
		List searchResults = searchResponse.getSearchResults();
		if (searchResults != null && searchResults.size() > 0) {
			SearchResult result = (SearchResult) searchResults.get(0);
			return result.getResults();
		}
		return null;
	}


	public String pageCall() {
		int pageNo;
		try{
			pageNo = Integer.parseInt(pageValue);
		}catch(Exception e){
			pageNo = 1;
		}
		int numberOfPages = retrieveNumberOfPagesForPanel();
		if(pageNo > numberOfPages){
			pageNo = numberOfPages;
		}else if(pageNo <= 0){
			pageNo = 1;
		}
		setCurrentPageNumber(pageNo);
		updateDetailsForCurrentObject();
		return "";
	}

	/**
	 * @return Returns the clickedIndex.
	 */
	public int getClickedIndex() {
		return clickedIndex;
	}

	/**
	 * @param clickedIndex
	 *            The clickedIndex to set.
	 */
	public void setClickedIndex(int clickedIndex) {
		this.clickedIndex = clickedIndex;
	}

	/**
	 * @return Returns the index.
	 */
	public int getIndex() {
		index++;
		return index;
	}

	/**
	 * @param index
	 *            The index to set.
	 */
	public void setIndex(int index) {

	}

	/**
	 * @return Returns the numberOfPages.
	 */
	public int getNumberOfPages() {
		return numberOfPages;
	}

	/**
	 * @param numberOfPages
	 *            The numberOfPages to set.
	 */
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	/**
	 * @return Returns the association.
	 */
	public boolean isViewAssociation() {
		return getViewAssociationFromSession();
	}

	/**
	 * @param association
	 *            The association to set.
	 */
	public void setViewAssociation(boolean viewAssociation) {

	}

	/**
	 * @return Returns the associationBreadCrumb.
	 */
	public String getAssociationBreadCrumb() {
		if (getViewAssociationFromSession() || getViewAttachment()) {
			return associationBreadCrumb;
		}
		associationBreadCrumb = "";
		return associationBreadCrumb;
	}

	/**
	 * @param associationBreadCrumb
	 *            The associationBreadCrumb to set.
	 */
	public void setAssociationBreadCrumb(String associationBreadCrumb) {
		this.associationBreadCrumb = associationBreadCrumb;
	}

	/**
	 * @return Returns the pageValue.
	 */
	public String getPageValue() {
		return "" + getCurrentPageNumber();
	}

	/**
	 * @param pageValue
	 *            The pageValue to set.
	 */
	public void setPageValue(String pageValue) {
		this.pageValue = pageValue;

	}

	/**
	 * @return Returns the pageStatus.
	 */
	public String getPageStatus() {

		//		return "Page "+pageNumber+" of "+numberOfPages;
		return " of " + retrieveNumberOfPagesForPanel();
	}

	/**
	 * @param pageStatus
	 *            The pageStatus to set.
	 */
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	/**
	 * @return Returns the fieldToSort.
	 */
	public String getFieldToSort() {
		return fieldToSort;
	}
	/**
	 * @param fieldToSort The fieldToSort to set.
	 */
	public void setFieldToSort(String fieldToSort) {
		this.fieldToSort = fieldToSort;
	}
	/**
	 * @return Returns the benefitComponentSortColumn.
	 */
	public int getBenefitComponentSortColumn() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_COMPONENTS + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_COMPONENTS + SearchConstants.ATTACHMENTS);			
		}
		else{
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_COMPONENTS);
		}
		if(detail != null){
			benefitComponentSortColumn = detail.getSortColumn();
		}
		return benefitComponentSortColumn;
	}
	/**
	 * @param benefitComponentSortColumn The benefitComponentSortColumn to set.
	 */
	public void setBenefitComponentSortColumn(int benefitComponentSortColumn) {
		this.benefitComponentSortColumn = benefitComponentSortColumn;
	}
	/**
	 * @return Returns the benefitComponentSortOrder.
	 */
	public String getBenefitComponentSortOrder() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_COMPONENTS + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_COMPONENTS + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_COMPONENTS);
		}
		if(detail != null){
			benefitComponentSortOrder = detail.getSortDirection();
		}
		return benefitComponentSortOrder;
	}
	/**
	 * @param benefitComponentSortOrder The benefitComponentSortOrder to set.
	 */
	public void setBenefitComponentSortOrder(String benefitComponentSortOrder) {
		this.benefitComponentSortOrder = benefitComponentSortOrder;
	}
	/**
	 * @return Returns the benefitLevelSortColumn.
	 */
	public int getBenefitLevelSortColumn() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_LEVEL + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_LEVEL + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_LEVEL);
		}
		if(detail != null){
			benefitLevelSortColumn = detail.getSortColumn();
		}
		return benefitLevelSortColumn;
	}
	/**
	 * @param benefitLevelSortColumn The benefitLevelSortColumn to set.
	 */
	public void setBenefitLevelSortColumn(int benefitLevelSortColumn) {
		this.benefitLevelSortColumn = benefitLevelSortColumn;
	}
	/**
	 * @return Returns the benefitLevelSortOrder.
	 */
	public String getBenefitLevelSortOrder() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_LEVEL + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_LEVEL + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.BENEFIT_LEVEL);
		}
		if(detail != null){
			benefitLevelSortOrder = detail.getSortDirection();
		}
		return benefitLevelSortOrder;
	}
	/**
	 * @param benefitLevelSortOrder The benefitLevelSortOrder to set.
	 */
	public void setBenefitLevelSortOrder(String benefitLevelSortOrder) {
		this.benefitLevelSortOrder = benefitLevelSortOrder;
	}
	/**
	 * @return Returns the benefitSortColumn.
	 */
	public int getBenefitSortColumn() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.BENEFIT);
		}
		if(detail != null){
			benefitSortColumn = detail.getSortColumn();
		}
		return benefitSortColumn;
	}
	/**
	 * @param benefitSortColumn The benefitSortColumn to set.
	 */
	public void setBenefitSortColumn(int benefitSortColumn) {
		this.benefitSortColumn = benefitSortColumn;
	}
	/**
	 * @return Returns the benefitSortOrder.
	 */
	public String getBenefitSortOrder() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.BENEFIT + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.BENEFIT);
		}
		if(detail != null){
			benefitSortOrder = detail.getSortDirection();
		}
		return benefitSortOrder;
	}
	/**
	 * @param benefitSortOrder The benefitSortOrder to set.
	 */
	public void setBenefitSortOrder(String benefitSortOrder) {
		this.benefitSortOrder = benefitSortOrder;
	}
	
	/**
	 * @return Returns the contractSortColumn.
	 */
	public int getContractSortColumn() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.CONTRACT + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.CONTRACT + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.CONTRACT);
		}
		if(detail != null){
			contractSortColumn = detail.getSortColumn();
		}
		return contractSortColumn;
	}
	/**
	 * @param contractSortColumn The contractSortColumn to set.
	 */
	public void setContractSortColumn(int contractSortColumn) {
		this.contractSortColumn = contractSortColumn;
	}
	/**
	 * @return Returns the contractSortOrder.
	 */
	public String getContractSortOrder() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.CONTRACT + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.CONTRACT + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.CONTRACT);
		}
		if(detail != null){
			contractSortOrder = detail.getSortDirection();
		}
		return contractSortOrder;
	}
	/**
	 * @param contractSortOrder The contractSortOrder to set.
	 */
	public void setContractSortOrder(String contractSortOrder) {
		this.contractSortOrder = contractSortOrder;
	}
	
	/**
	 * @return Returns the productSortColumn.
	 */
	public int getProductSortColumn() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.PRODUCT);
		}
		if(detail != null){
			productSortColumn = detail.getSortColumn();
		}
		return productSortColumn;
	}
	/**
	 * @param productSortColumn The productSortColumn to set.
	 */
	public void setProductSortColumn(int productSortColumn) {
		this.productSortColumn = productSortColumn;
	}
	/**
	 * @return Returns the productSortOrder.
	 */
	public String getProductSortOrder() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.PRODUCT);
		}
		if(detail != null){
			productSortOrder = detail.getSortDirection();
		}
		return productSortOrder;
	}
	/**
	 * @param productSortOrder The productSortOrder to set.
	 */
	public void setProductSortOrder(String productSortOrder) {
		this.productSortOrder = productSortOrder;
	}
	/**
	 * @return Returns the productStructureSortColumn.
	 */
	public int getProductStructureSortColumn() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT_STRUCTURES + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT_STRUCTURES + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.PRODUCT_STRUCTURES);
		}
		if(detail != null){
			productStructureSortColumn = detail.getSortColumn();
		}
		return productStructureSortColumn;
	}
	/**
	 * @param productStructureSortColumn The productStructureSortColumn to set.
	 */
	public void setProductStructureSortColumn(int productStructureSortColumn) {
		this.productStructureSortColumn = productStructureSortColumn;
	}
	/**
	 * @return Returns the productStructureSortOrder.
	 */
	public String getProductStructureSortOrder() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT_STRUCTURES + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.PRODUCT_STRUCTURES + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.PRODUCT_STRUCTURES);
		}
		if(detail != null){
			productStructureSortOrder = detail.getSortDirection();
		}
		return productStructureSortOrder;
	}
	/**
	 * @param productStructureSortOrder The productStructureSortOrder to set.
	 */
	public void setProductStructureSortOrder(String productStructureSortOrder) {
		this.productStructureSortOrder = productStructureSortOrder;
	}
	/**
	 * @return Returns the notesSortColumn.
	 */
	public int getNotesSortColumn() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.NOTES + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.NOTES + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.NOTES);
		}
		if(detail != null){
			notesSortColumn = detail.getSortColumn();
		}
		return notesSortColumn;
	}
	/**
	 * @param notesSortColumn The notesSortColumn to set.
	 */
	public void setNotesSortColumn(int notesSortColumn) {
		this.notesSortColumn = notesSortColumn;
	}
	/**
	 * @return Returns the notesSortOrder.
	 */
	public String getNotesSortOrder() {
		SortDetail detail = null;
		if(getViewAssociationFromSession()){
			detail = getSortDetailFromSession(SearchConstants.NOTES + SearchConstants.ASSOCIATION);
		}else if(getViewAttachment()){
			detail = getSortDetailFromSession(SearchConstants.NOTES + SearchConstants.ATTACHMENTS);			
		}else{
			detail = getSortDetailFromSession(SearchConstants.NOTES);
		}
		if(detail != null){
			notesSortOrder = detail.getSortDirection();
		}
		return notesSortOrder;
	}
	/**
	 * @param notesSortOrder The notesSortOrder to set.
	 */
	public void setNotesSortOrder(String notesSortOrder) {
		this.notesSortOrder = notesSortOrder;
	}
	/**
	 * @return Returns the columnFiveImageAsc.
	 */
	public String getColumnFiveImageAsc() {
		columnFiveImageAsc = getImageForColumn(5, SearchConstants.ASCENDING);
		return columnFiveImageAsc;
	}
	/**
	 * @param columnFiveImageAsc The columnFiveImageAsc to set.
	 */
	public void setColumnFiveImageAsc(String columnFiveImageAsc) {
		this.columnFiveImageAsc = columnFiveImageAsc;
	}
	/**
	 * @return Returns the columnFiveImageDesc.
	 */
	public String getColumnFiveImageDesc() {
		columnFiveImageDesc = getImageForColumn(5, SearchConstants.DESCENDING);
		return columnFiveImageDesc;
	}
	/**
	 * @param columnFiveImageDesc The columnFiveImageDesc to set.
	 */
	public void setColumnFiveImageDesc(String columnFiveImageDesc) {
		this.columnFiveImageDesc = columnFiveImageDesc;
	}
	/**
	 * @return Returns the columnFourImageAsc.
	 */
	public String getColumnFourImageAsc() {
		columnFourImageAsc = getImageForColumn(4, SearchConstants.ASCENDING);
		return columnFourImageAsc;
	}
	/**
	 * @param columnFourImageAsc The columnFourImageAsc to set.
	 */
	public void setColumnFourImageAsc(String columnFourImageAsc) {
		this.columnFourImageAsc = columnFourImageAsc;
	}
	/**
	 * @return Returns the columnFourImageDesc.
	 */
	public String getColumnFourImageDesc() {
		columnFourImageDesc = getImageForColumn(4, SearchConstants.DESCENDING);
		return columnFourImageDesc;
	}
	/**
	 * @param columnFourImageDesc The columnFourImageDesc to set.
	 */
	public void setColumnFourImageDesc(String columnFourImageDesc) {
		this.columnFourImageDesc = columnFourImageDesc;
	}
	/**
	 * @return Returns the columnOneImageAsc.
	 */
	public String getColumnOneImageAsc() {
		columnOneImageAsc = getImageForColumn(1, SearchConstants.ASCENDING);
		return columnOneImageAsc;
	}
	/**
	 * @param columnOneImageAsc The columnOneImageAsc to set.
	 */
	public void setColumnOneImageAsc(String columnOneImageAsc) {
		this.columnOneImageAsc = columnOneImageAsc;
	}
	/**
	 * @return Returns the columnOneImageDesc.
	 */
	public String getColumnOneImageDesc() {
		columnOneImageDesc = getImageForColumn(1, SearchConstants.DESCENDING);
		return columnOneImageDesc;
	}
	/**
	 * @param columnOneImageDesc The columnOneImageDesc to set.
	 */
	public void setColumnOneImageDesc(String columnOneImageDesc) {
		this.columnOneImageDesc = columnOneImageDesc;
	}
	/**
	 * @return Returns the columnSevenImageAsc.
	 */
	public String getColumnSevenImageAsc() {
		columnSevenImageAsc = getImageForColumn(7, SearchConstants.ASCENDING);
		return columnSevenImageAsc;
	}
	/**
	 * @param columnSevenImageAsc The columnSevenImageAsc to set.
	 */
	public void setColumnSevenImageAsc(String columnSevenImageAsc) {
		this.columnSevenImageAsc = columnSevenImageAsc;
	}
	/**
	 * @return Returns the columnSevenImageDesc.
	 */
	public String getColumnSevenImageDesc() {
		columnSevenImageDesc = getImageForColumn(7, SearchConstants.DESCENDING);
		return columnSevenImageDesc;
	}
	/**
	 * @param columnSevenImageDesc The columnSevenImageDesc to set.
	 */
	public void setColumnSevenImageDesc(String columnSevenImageDesc) {
		this.columnSevenImageDesc = columnSevenImageDesc;
	}
	/**
	 * @return Returns the columnSixImageAsc.
	 */
	public String getColumnSixImageAsc() {
		columnSixImageAsc = getImageForColumn(6, SearchConstants.ASCENDING);
		return columnSixImageAsc;
	}
	/**
	 * @param columnSixImageAsc The columnSixImageAsc to set.
	 */
	public void setColumnSixImageAsc(String columnSixImageAsc) {
		this.columnSixImageAsc = columnSixImageAsc;
	}
	/**
	 * @return Returns the columnSixImageDesc.
	 */
	public String getColumnSixImageDesc() {
		columnSixImageDesc = getImageForColumn(6, SearchConstants.DESCENDING);
		return columnSixImageDesc;
	}
	/**
	 * @param columnSixImageDesc The columnSixImageDesc to set.
	 */
	public void setColumnSixImageDesc(String columnSixImageDesc) {
		this.columnSixImageDesc = columnSixImageDesc;
	}
	/**
	 * @return Returns the columnThreeImageAsc.
	 */
	public String getColumnThreeImageAsc() {
		columnThreeImageAsc = getImageForColumn(3, SearchConstants.ASCENDING);
		return columnThreeImageAsc;
	}
	/**
	 * @param columnThreeImageAsc The columnThreeImageAsc to set.
	 */
	public void setColumnThreeImageAsc(String columnThreeImageAsc) {
		this.columnThreeImageAsc = columnThreeImageAsc;
	}
	/**
	 * @return Returns the columnThreeImageDesc.
	 */
	public String getColumnThreeImageDesc() {
		columnThreeImageDesc = getImageForColumn(3, SearchConstants.DESCENDING);
		return columnThreeImageDesc;
	}
	/**
	 * @param columnThreeImageDesc The columnThreeImageDesc to set.
	 */
	public void setColumnThreeImageDesc(String columnThreeImageDesc) {
		this.columnThreeImageDesc = columnThreeImageDesc;
	}
	/**
	 * @return Returns the columnTwoImageAsc.
	 */
	public String getColumnTwoImageAsc() {
		columnTwoImageAsc = getImageForColumn(2, SearchConstants.ASCENDING);
		return columnTwoImageAsc;
	}
	/**
	 * @param columnTwoImageAsc The columnTwoImageAsc to set.
	 */
	public void setColumnTwoImageAsc(String columnTwoImageAsc) {
		this.columnTwoImageAsc = columnTwoImageAsc;
	}
	/**
	 * @return Returns the columnTwoImageDesc.
	 */
	public String getColumnTwoImageDesc() {
		columnTwoImageDesc = getImageForColumn(2, SearchConstants.DESCENDING);
		return columnTwoImageDesc;
	}
	/**
	 * @param columnTwoImageDesc The columnTwoImageDesc to set.
	 */
	public void setColumnTwoImageDesc(String columnTwoImageDesc) {
		this.columnTwoImageDesc = columnTwoImageDesc;
	}
	/**
	 * @return Returns the association.
	 */
	public String getAssociation() {
		return association;
	}
	/**
	 * @param association The association to set.
	 */
	public void setAssociation(String viewAssociation) {
		this.association = viewAssociation;
	}

	/**
	 * @return Returns the currentAttachedObject.
	 */
	public String getCurrentAttachedObject() {
		return currentAttachedObject;
	}
	/**
	 * @param currentAttachedObject The currentAttachedObject to set.
	 */
	public void setCurrentAttachedObject(String currentAttachedObject) {
		this.currentAttachedObject = currentAttachedObject;
	}
	/**
	 * @return Returns the attachmentObjectTypeList.
	 */
	public List getAttachmentObjectTypeList() {
		attachmentObjectTypeList = (List)getSession().getAttribute(SearchConstants.ATTACHMENT_RESULT_SUMMARY);
		return attachmentObjectTypeList;
	}
	/**
	 * @param attachmentObjectTypeList The attachmentObjectTypeList to set.
	 */
	public void setAttachmentObjectTypeList(List attachmentObjectTypeList) {
		this.attachmentObjectTypeList = attachmentObjectTypeList;
	}
	/**
	 * @return Returns the attachedObjectValidate.
	 */
	public boolean isAttachedObjectValidate() {
		List list = getAttachmentObjectTypeList();
		if(list != null){
			attachedObjectValidate = true;
		}else{
			attachedObjectValidate = false;
		}
		return attachedObjectValidate;
	}
	/**
	 * @param attachedObjectValidate The attachedObjectValidate to set.
	 */
	public void setAttachedObjectValidate(boolean attachedObjectValidate) {
		this.attachedObjectValidate = attachedObjectValidate;
	}
	/**
	 * @return Returns the associatedObjectSelectedIndex.
	 */
	public String getAssociatedObjectSelectedIndex() {
		
		return associatedObjectSelectedIndex;
	}
	/**
	 * @param associatedObjectSelectedIndex The associatedObjectSelectedIndex to set.
	 */
	public void setAssociatedObjectSelectedIndex(
			String associatedObjectSelectedIndex) {
		this.associatedObjectSelectedIndex = associatedObjectSelectedIndex;
	}
	/**
	 * @return Returns the selectedIndex.
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex The selectedIndex to set.
	 */
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	/**
	 * @return Returns the benefitLinePva.
	 */
	public boolean isBenefitLinePva() {
		MultipageSearchResult result = (MultipageSearchResult)getSession().
					getAttribute(SearchConstants.BENEFIT_LINE + SearchConstants.ATTACHMENTS);
		if(result != null && result.getTotalNumberOfResults() > 0){
			return true;
		}
		return false;
	}
	/**
	 * @param benefitLinePva The benefitLinePva to set.
	 */
	public void setBenefitLinePva(boolean benefitLinePva) {
		
	}
	
	private ObjectIdentifier getIdentifierForCurrentClickedIndex(){
		int pageNumber = getCurrentPageNumber();
		String objType = null;
		if(getViewAssociationFromSession()){
			objType = (String) getSession().getAttribute(
					SearchConstants.ASSOCIATION_OBJECT_TYPE) + SearchConstants.ASSOCIATION;
		}
		else if(getViewAttachment()){
			objType = (String) getSession().getAttribute(
					SearchConstants.ATTACHMENT_OBJECT_TYPE) + SearchConstants.ATTACHMENTS;				
		}else{				
			objType = (String) getSession().getAttribute(
					SearchConstants.OBJECT_TYPE);
		}
		List identifiers = new SearchUtil().getPageListForObjectType(objType,pageNumber);
		if(null != identifiers && identifiers.size() > 0){
			ObjectIdentifier identifier = (ObjectIdentifier)identifiers.get(clickedIndex);
			return identifier;
		}
		return null;
	}
	
	public String viewBenefitObject(){
		BenefitIdentifier identifier = (BenefitIdentifier)getIdentifierForCurrentClickedIndex();
		if(null != identifier){
			updateBenefitViewObject(identifier.getIdentifier());
		}
		return "";
	}
	private String updateBenefitViewObject(int sysid){


		BenefitViewRequest benefitViewRequest = (BenefitViewRequest) getServiceRequest(ServiceManager.BENEFIT_VIEW_REQUEST);
		benefitViewRequest.setStandardBenefitKey(sysid);
		SearchResponse searchResponse = (SearchResponse) this
		.executeService(benefitViewRequest);
		List domainValues=null;
		List bgList=new ArrayList();
		List beList=new ArrayList();
		List lobList=new ArrayList();
		List mbuList=new ArrayList();
		int key=0,versionNo=0,parentKey=0;
		String status=null,name=null;
		List searchResultDetail=searchResponse.getSearchResults();
		SearchResultDetail resultDetail=(SearchResultDetail)searchResultDetail.get(0);
		for(int i=0;i<resultDetail.getResults().size();i++){
			BenefitViewObject benefitViewObject =(BenefitViewObject)resultDetail.getResults().get(i);
			bgList.add(benefitViewObject.getBusinessGroupCode());
			beList.add(benefitViewObject.getBusinessEntityCode());
			lobList.add(benefitViewObject.getLineOfBusinessCode());
			key=benefitViewObject.getIdentifier();
			versionNo=benefitViewObject.getVersionNo();
			parentKey=benefitViewObject.getParent_id();
			status=benefitViewObject.getStatus();
			name=benefitViewObject.getBenefitName();
		}
		StandardBenefitVO standardBenefitVO=new StandardBenefitVO();
		standardBenefitVO.setStandardBenefitKey(key);
		standardBenefitVO.setBenefitIdentifier(name);
		standardBenefitVO.setStandardBenefitParentKey(parentKey);
		standardBenefitVO.setBusinessDomains(BusinessUtil.convertToDomains(lobList,beList,bgList,mbuList));
		standardBenefitVO.setStatus(status);
		standardBenefitVO.setVersion(versionNo);
		List searchResultList = new ArrayList();
		searchResultList.add(standardBenefitVO);
		getSession().putValue("standardBenefitSearchResult",searchResultList);
		StandardBenefitSessionObject sessionObject=new StandardBenefitSessionObject();
		sessionObject.setBusinessDomains(BusinessUtil.convertToDomains(lobList,beList,bgList,mbuList));		
		sessionObject.setStandardBenefitKey(key);
		sessionObject.setStandardBenefitParentKey(parentKey);
		sessionObject.setStandardBenefitStatus(status);
		sessionObject.setStandardBenefitVersionNumber(versionNo);
		sessionObject.setStandardBenefitName(name);
		getSession().putValue("standard",sessionObject);
		checkForView=1;
		setBenefitKey(key);
		return "";
	}
	

	public String viewBenefitComponentObject(){
		BenefitComponentIdentifier identifier = (BenefitComponentIdentifier)getIdentifierForCurrentClickedIndex();
		if(null != identifier){
			updateBenefitComponentViewObject(identifier.getIdentifier());
		}
		return "";
	}
	private String updateBenefitComponentViewObject(int sysid){


		BenefitComponentSearchViewRequest benefitComponentSearchViewRequest = (BenefitComponentSearchViewRequest) getServiceRequest(ServiceManager.BENEFIT_COMPONENT_SEARCH_VIEW_REQUEST);
		benefitComponentSearchViewRequest.setBenefitComponentKey(sysid);
		SearchResponse searchResponse = (SearchResponse) this
		.executeService(benefitComponentSearchViewRequest);
		List domainValues=null;
		List bgList=new ArrayList();
		List beList=new ArrayList();
		List lobList=new ArrayList();
		List mbuList=new ArrayList();
		int key=0,versionNo=0,parentKey=0;
		String status=null,name=null,type=null;
		List searchResultDetail=searchResponse.getSearchResults();
		SearchResultDetail resultDetail=(SearchResultDetail)searchResultDetail.get(0);
		for(int i=0;i<resultDetail.getResults().size();i++){
			BenefitComponentViewObject benefitComponentViewObject =(BenefitComponentViewObject)resultDetail.getResults().get(i);
			bgList.add(benefitComponentViewObject.getBusinessGroupCode());
			beList.add(benefitComponentViewObject.getBusinessEntityCode());
			lobList.add(benefitComponentViewObject.getLineOfBusinessCode());
			key=benefitComponentViewObject.getIdentifier();
			versionNo=benefitComponentViewObject.getBenefitComponentVersion();
			parentKey=benefitComponentViewObject.getParent_id();
			status=benefitComponentViewObject.getBenefitComponentStatus();
			name=benefitComponentViewObject.getBenefitComponentName();
			type=benefitComponentViewObject.getType();
		}
	

		BenefitComponentSessionObject benefitComponentSessionObject = new BenefitComponentSessionObject();
		benefitComponentSessionObject.setBusinessDomainList(BusinessUtil.convertToDomains(lobList,beList,bgList,mbuList));	
		benefitComponentSessionObject.setBenefitComponentId(key);
		benefitComponentSessionObject.setBenefitComponentParentId(parentKey);
		benefitComponentSessionObject.setStatus(status);
		benefitComponentSessionObject.setVersion(versionNo);
		benefitComponentSessionObject.setBenefitComponentName(name);
		benefitComponentSessionObject.setBenefitComponentMode("View");
		if("STANDARD".equalsIgnoreCase(type)){
			benefitComponentSessionObject.setBcComponentType("STANDARD");
		}else if("MANDATE".equalsIgnoreCase(type)){
			benefitComponentSessionObject.setBcComponentType("MANDATE");
		}	
		getSession().putValue(WebConstants.BENEFIT_COMPONENT_SEARCH_SESSION_KEY,benefitComponentSessionObject);
		checkForView=1;
		setBenefitKey(key);
		BenefitComponentBO benefitComponentBO=new BenefitComponentBO();
		benefitComponentBO.setBenefitComponentId(key);
		benefitComponentBO.setBusinessDomainList(benefitComponentSessionObject.getBusinessDomainList());
		List bcList=new ArrayList();
		bcList.add(benefitComponentBO);
		getSession().putValue("benefitComponentSearchResult",bcList);
		setQuery("benefitcomponentkey="+benefitComponentSessionObject.getBenefitComponentId()+"&benfitName="+benefitComponentSessionObject.getBenefitComponentName()+"&benfitVersion="+benefitComponentSessionObject.getBenefitComponentVersionNumber());
		return "";
	}
	
	public String viewProductStructureObject(){
		ProductStructureIdentifier identifier = (ProductStructureIdentifier)getIdentifierForCurrentClickedIndex();
		if(null != identifier){
			updateProductStructureViewObject(identifier.getIdentifier());
		}
		return "";
	}
	private String updateProductStructureViewObject(int sysid){


		ProductStructureViewRequest productStructureViewRequest = (ProductStructureViewRequest) getServiceRequest(ServiceManager.PRODUCT_STRUCTURE_VIEW_REQUEST);
		productStructureViewRequest.setProductStructureId(sysid);
		SearchResponse searchResponse = (SearchResponse) this
		.executeService(productStructureViewRequest);
		List domainValues=null;
		List bgList=new ArrayList();
		List beList=new ArrayList();
		List lobList=new ArrayList();
		List mbuList=new ArrayList();
		int key=0,versionNo=0,parentKey=0;
		String status=null,name=null,state=null,structureType=null,mandateType=null;
		List searchResultDetail=searchResponse.getSearchResults();
		SearchResultDetail resultDetail=(SearchResultDetail)searchResultDetail.get(0);
		for(int i=0;i<resultDetail.getResults().size();i++){
			ProductStructureViewObject productStructureViewObject=(ProductStructureViewObject)resultDetail.getResults().get(i);
			key=productStructureViewObject.getProductStructureId();
			versionNo=productStructureViewObject.getVersion();
			parentKey=productStructureViewObject.getParent_Id();
			status=productStructureViewObject.getStatus();
			name=productStructureViewObject.getProductStructureName();
			state=productStructureViewObject.getState();
			structureType=productStructureViewObject.getStructureType();
			mandateType=productStructureViewObject.getMandateType();
		}
		DomainFetchResponse response = null;
		DomainFetchRequest request = (DomainFetchRequest)getServiceRequest(ServiceManager.DOMAIN_FECTH_REQUEST);
			
		ProductStructureIdentifier identifier = (ProductStructureIdentifier)getIdentifierForCurrentClickedIndex();
		request.setIdentifier(identifier);
		response = (DomainFetchResponse)executeService(request);
		for(int i=0;i<response.getLob().size();i++){
		    DomainItem domainItem=(DomainItem)response.getLob().get(i);
		    lobList.add(domainItem.getItemId());
		}
		for(int i=0;i<response.getBusinessEntity().size();i++){
		    DomainItem domainItem=(DomainItem)response.getLob().get(i);
		    beList.add(domainItem.getItemId());
		}
		for(int i=0;i<response.getBusinessGroup().size();i++){
		    DomainItem domainItem=(DomainItem)response.getLob().get(i);
		    bgList.add(domainItem.getItemId());
		}
		ProductStructureSessionObject productStructureSessionObject = new ProductStructureSessionObject();
		productStructureSessionObject.setBusinessDomains(BusinessUtil.convertToDomains(lobList,beList,bgList,mbuList));	
		productStructureSessionObject.setId(key);
		productStructureSessionObject.setParentId(parentKey);
		productStructureSessionObject.setStatus(status);
		productStructureSessionObject.setVersion(versionNo);
		productStructureSessionObject.setName(name);
		productStructureSessionObject.setState(state);
		productStructureSessionObject.setMandateType(mandateType);
		productStructureSessionObject.setStructureType(structureType);
		//getSession().putValue(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY,productStructureSessionObject);
		checkForView=1;
		setProductStructureId(key);
		ProductStructureBO productStructureBO=new ProductStructureBO();
		productStructureBO.setProductStructureId(key);
		productStructureBO.setBusinessDomains(productStructureSessionObject.getBusinessDomains());
		productStructureBO.setStatus(productStructureSessionObject.getStatus());
		productStructureBO.setVersion(productStructureSessionObject.getVersion());
		productStructureBO.setProductStructureName(productStructureSessionObject.getName());
		productStructureBO.setStateId(productStructureSessionObject.getState());
		productStructureBO.setMandateType(productStructureSessionObject.getMandateType());
		productStructureBO.setStructureType(productStructureSessionObject.getStructureType());
		List psList=new ArrayList();
		psList.add(productStructureBO);
//		getSession().putValue(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY,psList);
		getSession().putValue(ProductStructureBackingBean.PRODUCT_STRUCTURE_SEARCH_SESSION_KEY,psList);
		//setQuery("Id="+productStructureSessionObject.getId());
		return "";
	}
	

	public String viewContractObject(){
		ContractIdentifier identifier = (ContractIdentifier)getIdentifierForCurrentClickedIndex();
		if(null != identifier){
			updateContractViewObject(identifier.getIdentifier(),identifier.getDateSegIdentifier());
		}
		return "";
	}
	private String updateContractViewObject(int sysid,int dataSegIdentifier){


		ContractViewRequest contractViewRequest = (ContractViewRequest) getServiceRequest(ServiceManager.CONTRACT_VIEW_REQUEST);
		contractViewRequest.setContractKey(sysid);
		contractViewRequest.setDataSegIdentifier(dataSegIdentifier);
		SearchResponse searchResponse = (SearchResponse) this
		.executeService(contractViewRequest);
		List searchResultDetail=searchResponse.getSearchResults();
		ContractViewObject contractViewObject=null;
		if(searchResultDetail.size()>0){
			SearchResultDetail resultDetail=(SearchResultDetail)searchResultDetail.get(0);
			contractViewObject=(ContractViewObject)resultDetail.getObjectDetails().get(0);
		}
		checkForView=1;
		getRequest().setAttribute("contractID",contractViewObject.getContractId());
		getRequest().setAttribute("contractSysId",String.valueOf(contractViewObject.getContractSegmentId()));
		getRequest().setAttribute("contractDateSegmentSysId",String.valueOf(contractViewObject.getContractDateSegmentSysId()));
		getRequest().setAttribute("status",contractViewObject.getStatus());
		getRequest().setAttribute("version",String.valueOf(contractViewObject.getVersion()));
		ContractSession contractSession=new ContractSession();
		ContractKeyObject contractKeyObject=new ContractKeyObject();
		contractKeyObject.setContractId(contractViewObject.getContractId());
		contractKeyObject.setContractSysId(contractViewObject.getContractSegmentId());
		contractSession.setContractKeyObject(contractKeyObject);
		getSession().setAttribute("contract",contractSession);
		setQuery("contractID="+contractViewObject.getContractId()+"&contractSysId="+String.valueOf(contractViewObject.getContractSegmentId())+"&contractDateSegmentSysId="+String.valueOf(contractViewObject.getContractDateSegmentSysId())+"&status="+contractViewObject.getStatus()+"&version="+String.valueOf(contractViewObject.getVersion()));
		return "";
	}
	
	public String viewProductObject(){
		ProductIdentifier identifier = (ProductIdentifier)getIdentifierForCurrentClickedIndex();
		if(null != identifier){
			checkForView=1;
			setProductKey(identifier.getIdentifier());
			updateProductViewObject(identifier.getIdentifier());
		}
		return "";
	}
	
	public String viewMoreNotesObject(){
		NotesSearchBackingBean notesSearchBackingBean=new NotesSearchBackingBean();
		notesSearchBackingBean.setNoteIdForLocate(getNoteid());
		notesSearchBackingBean.setNoteName(getNotename());
		
		notesSearchBackingBean.locateNotes();
		getRequest().setAttribute("notesSearchBackingBean",notesSearchBackingBean);		
		return "maintainNotesPage";
	}
	
	public String viewMoreContractObject(){
		DomainFetchResponse response = null;
		ContractSearchBackingBean contractSearchBackingBean=new ContractSearchBackingBean();
		contractSearchBackingBean.setContractId(getContractid());
		DomainFetchRequest request = (DomainFetchRequest)getServiceRequest(ServiceManager.DOMAIN_FECTH_REQUEST);
		ContractIdentifier identifier = (ContractIdentifier)getIdentifierForCurrentClickedIndex();
		request.setIdentifier(identifier);
		response = (DomainFetchResponse)executeService(request);
		updateDomains(response, false);
		contractSearchBackingBean.setBusinessEntity(getBusinessEntity());
		contractSearchBackingBean.setLob(getLob());
		contractSearchBackingBean.setGroupName(getBusinessGroup());
		contractSearchBackingBean.setStartDate(getEffectiveDate());
		contractSearchBackingBean.setEndDate(getExpiryDate());
		contractSearchBackingBean.performLocate();
		getRequest().setAttribute("contractSearchBackingBean",contractSearchBackingBean);		
		return "contractsearchpage";
	}
	
	public String viewMoreProductObject(){
		
		DomainFetchResponse response = null;
		DomainFetchRequest request = (DomainFetchRequest)getServiceRequest(ServiceManager.DOMAIN_FECTH_REQUEST);

		ProductIdentifier identifier = (ProductIdentifier)getIdentifierForCurrentClickedIndex();
		request.setIdentifier(identifier);
		
	
		response = (DomainFetchResponse)executeService(request);
		updateDomains(response, false);
		ProductSearchBackingBean productSearchBackingBean=new ProductSearchBackingBean();
		productSearchBackingBean.setBusinessEntity(getBusinessEntity());
		productSearchBackingBean.setProductName(getProductName());
		productSearchBackingBean.setBusinessGroup(getBusinessGroup());
		productSearchBackingBean.setLineOfBusiness(getLob());
		productSearchBackingBean.setEffectiveDate(getEffectiveDate());
		productSearchBackingBean.setExpiryDate(getExpiryDate());
		productSearchBackingBean.productSearch();
		getRequest().setAttribute("productSearchBackingBean",productSearchBackingBean);		
		return "productsearchpage";
	}

	
	public String viewMoreBenefitComponentObject(){
		DomainFetchResponse response = null;
		DomainFetchRequest request = (DomainFetchRequest)getServiceRequest(ServiceManager.DOMAIN_FECTH_REQUEST);
		 
		BenefitComponentIdentifier identifier = (BenefitComponentIdentifier)getIdentifierForCurrentClickedIndex();
		request.setIdentifier(identifier);
		response = (DomainFetchResponse)executeService(request);
		updateDomains(response,false);
		
		BenefitComponentSearchBackingBean benefitComponentSearchBackingBean = new BenefitComponentSearchBackingBean();
		benefitComponentSearchBackingBean.setName(getBenefitComponentName());
		benefitComponentSearchBackingBean.setBusinessEntity(getBusinessEntity());
		benefitComponentSearchBackingBean.setBusinessGroup(getBusinessGroup());
		benefitComponentSearchBackingBean.setLob(getLob());
		benefitComponentSearchBackingBean.setEffectiveDate(getEffectiveDate());
		benefitComponentSearchBackingBean.setExpiryDate(getExpiryDate());
		benefitComponentSearchBackingBean.performSearch();
		getRequest().setAttribute("BenefitComponentSearchBackingBean",benefitComponentSearchBackingBean);
		return "benefitComponentSearchPage";
	}
	

	public String viewMoreBenefitObject(){
		DomainFetchResponse response = null;
		DomainFetchRequest request = (DomainFetchRequest)getServiceRequest(ServiceManager.DOMAIN_FECTH_REQUEST);
		 
		BenefitIdentifier benefitIdentifier = (BenefitIdentifier)getIdentifierForCurrentClickedIndex();
		request.setIdentifier(benefitIdentifier);
		response = (DomainFetchResponse)executeService(request);
		updateDomains(response,false);
		
		StandardBenefitSearchBackingBean standardBenefitSearchBackingBean = new StandardBenefitSearchBackingBean();
		standardBenefitSearchBackingBean.setName(getBenefitName());
		standardBenefitSearchBackingBean.setBusinessEntity(getBusinessEntity());
		standardBenefitSearchBackingBean.setBusinessGroup(getBusinessGroup());
		standardBenefitSearchBackingBean.setLob(getLob());
		standardBenefitSearchBackingBean.setQualifier("");
		standardBenefitSearchBackingBean.setProviderArrangement("");
		standardBenefitSearchBackingBean.setDataType("");
		standardBenefitSearchBackingBean.setTerm("");
		standardBenefitSearchBackingBean.setBenefitType("ALL");
		standardBenefitSearchBackingBean.performLocate();
		getRequest().setAttribute("StandardBenefitSearchBackingBean",standardBenefitSearchBackingBean);
		return "benefitSearchPage";
	}
	

	
	
	public String viewMoreProductStructureObject(){
		
		DomainFetchResponse response = null;
		DomainFetchRequest request = (DomainFetchRequest)getServiceRequest(ServiceManager.DOMAIN_FECTH_REQUEST);

		ProductStructureIdentifier identifier = (ProductStructureIdentifier)getIdentifierForCurrentClickedIndex();
		request.setIdentifier(identifier);
		response = (DomainFetchResponse)executeService(request);
		updateDomains(response, false);
		ProductStructureSearchBackingBean productStructureSearchBackingBean=new ProductStructureSearchBackingBean();
		productStructureSearchBackingBean.setProductStructureName(getProductstructurename());
		productStructureSearchBackingBean.setBusinessEntity(getBusinessEntity());
		productStructureSearchBackingBean.setBusinessGroup(getBusinessGroup());
		productStructureSearchBackingBean.setLineOfBusiness(getLob());
		productStructureSearchBackingBean.setEffectiveDate(getEffectiveDate());
		productStructureSearchBackingBean.setExpiryDate(getExpiryDate());
		productStructureSearchBackingBean.productStructureSearch();
		getRequest().setAttribute("productStructureSearchBackingBean",productStructureSearchBackingBean);		
		return "productstructuresearchpage";
	}
	private String updateProductViewObject(int sysid){

		ProductViewObject productView=null;
		ProductViewRequest productViewRequest = (ProductViewRequest) getServiceRequest(ServiceManager.PRODUCT_VIEW_REQUEST);
		productViewRequest.setProductKey(sysid);
		SearchResponse searchResponse = (SearchResponse) this
		.executeService(productViewRequest);
		List domainValues=null;
		List bgList=new ArrayList();
		List beList=new ArrayList();
		List lobList=new ArrayList();
		List mbuList=new ArrayList();
		int key=0,versionNo=0,parentKey=0;
		String status=null,name=null;
		List searchResultDetail=searchResponse.getSearchResults();
		SearchResultDetail resultDetail=(SearchResultDetail)searchResultDetail.get(0);
		for(int i=0;i<resultDetail.getResults().size();i++){
			ProductViewObject productViewObject =(ProductViewObject)resultDetail.getResults().get(i);
			bgList.add(productViewObject.getBusinessGroupCode());
			beList.add(productViewObject.getBusinessEntityCode());
			lobList.add(productViewObject.getLineOfBusinessCode());
			productView=productViewObject;
		}
		ProductSearchResult ProductSearchResult=new ProductSearchResult();
		ProductSearchResult.setBusinessDomains(BusinessUtil.convertToDomains(lobList,beList,bgList,mbuList));
		ProductSearchResult.setBusinessEntityList(beList);
		ProductSearchResult.setBusinessGroupList(bgList);
		ProductSearchResult.setEffectiveDate(productView.getEffectiveDate());
		ProductSearchResult.setExpiryDate(productView.getExpiryDate());
		ProductSearchResult.setLineOfBusinessList(lobList);
		ProductSearchResult.setParentKey(productView.getParentKey());
		ProductSearchResult.setProductKey(sysid);
		ProductSearchResult.setProductName(productView.getProductName());
		ProductSearchResult.setVersion(productView.getVersion());
		

	    List productSearchResultList=new ArrayList();
	    productSearchResultList.add(ProductSearchResult);
		ProductSessionObject sessionObject=new ProductSessionObject();
		sessionObject.setSearchResultList(productSearchResultList);
		getSession().putValue("product",sessionObject);
		return "";
	}


	/**
	 * @return Returns the checkForView.
	 */
	public int getCheckForView() {
		return checkForView;
	}
	/**
	 * @param checkForView The checkForView to set.
	 */
	public void setCheckForView(int checkForView) {
	}
		
	
	/**
	 * @return Returns the benefitKey.
	 */
	public int getBenefitKey() {
		return benefitKey;
	}
	/**
	 * @param benefitKey The benefitKey to set.
	 */
	public void setBenefitKey(int benefitKey) {
		this.benefitKey = benefitKey;
	}

	/**
	 * @return Returns the benefitComponentKey.
	 */
	public int getBenefitComponentKey() {
		return benefitComponentKey;
	}
	/**
	 * @param benefitComponentKey The benefitComponentKey to set.
	 */
	public void setBenefitComponentKey(int benefitComponentKey) {
		this.benefitComponentKey = benefitComponentKey;
	}
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
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
	 * @return Returns the query.
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query The query to set.
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return Returns the productStructureId.
	 */
	public int getProductStructureId() {
		return productStructureId;
	}
	/**
	 * @param productStructureId The productStructureId to set.
	 */
	public void setProductStructureId(int productStructureId) {
		this.productStructureId = productStructureId;
	}
	/**
	 * @return Returns the productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * @param productKey The productKey to set.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}
	/**
	 * @return Returns the noteid.
	 */
	public String getNoteid() {
		return noteid;
	}
	/**
	 * @param noteid The noteid to set.
	 */
	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}
	/**
	 * @return Returns the notename.
	 */
	public String getNotename() {
		return notename;
	}
	/**
	 * @param notename The notename to set.
	 */
	public void setNotename(String notename) {
		this.notename = notename;
	}
	
	
	/**
	 * @return Returns the contractid.
	 */
	public String getContractid() {
		return contractid;
	}
	/**
	 * @param contractid The contractid to set.
	 */
	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	
	private void updateDomains(DomainFetchResponse response, boolean codeFirst) {
		lob = getAppendedBusinessDomains( response.getLob(),codeFirst);
		businessEntity = getAppendedBusinessDomains( response.getBusinessEntity(),codeFirst);
		businessGroup = getAppendedBusinessDomains( response.getBusinessGroup(),codeFirst);
	}
	private String getAppendedBusinessDomains(List domains, boolean codeFirst){
		Iterator iterator = domains.iterator();
		boolean enteredFlag = false;
		DomainItem domain = null;
		String entry = "";
		while(iterator.hasNext()){
			domain = (DomainItem)iterator.next();
			String first;
			String second;
			if(codeFirst){
				first = domain.getItemId();
				second = domain.getItemDesc();
			}else{
				second = domain.getItemId();
				first = domain.getItemDesc();
			}
			entry = entry +  first + "~";
			entry = entry +  second + "~";
			enteredFlag = true;
		}
		if(enteredFlag){
			entry = entry.substring(0,entry.length() - 1);
		}
		return entry;
	}
	
	
	/**
	 * @return Returns the businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}
	
	
	/**
	 * @return Returns the productstructurename.
	 */
	public String getProductstructurename() {
		return productstructurename;
	}
	/**
	 * @param productstructurename The productstructurename to set.
	 */
	public void setProductstructurename(String productstructurename) {
		this.productstructurename = productstructurename;
	}
	/**
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return Returns the authorizedForAssociation.
	 */
	public boolean isAuthorizedForAssociation() {
		boolean flag = false;
		String objectType = "";
		if(getViewAssociationFromSession()){
			objectType = (String)getSession().getAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
		}else if(getViewAttachment()){
			objectType = (String)getSession().getAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE);
		}else{
			objectType = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
		}
		
		String objectAssociationModuleType = new SearchUtil().getAssociationModule(objectType);
		if(null == objectAssociationModuleType){
			return false;
		}
		try {
			User user = getUser();
			return user.isAuthorized(objectAssociationModuleType, WebConstants.MAINTAIN_TASK);
		} catch (SevereException e) {
			return false;
		}
	}


    /**
     * Returns the authorizedForView
     * @return boolean authorizedForView.
     */
	public boolean isAuthorizedForView() {
		boolean flag = false;
		String objectType = "";
		if(getViewAssociationFromSession()){
			objectType = (String)getSession().getAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
		}else if(getViewAttachment()){
			objectType = (String)getSession().getAttribute(SearchConstants.ATTACHMENT_OBJECT_TYPE);
		}else{
			objectType = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
		}
		
		try {
			User user = getUser();
			String objectModule = new SearchUtil().getModule(objectType);
			if(null != objectModule){
				return user.isAuthorized(objectModule, WebConstants.VIEW);
			}
		} catch (SevereException e) {
		}
		return false;
	}
		
    /**
     * Sets the authorizedForView
     * @param authorizedForView.
     */
    public void setAuthorizedForView(boolean authorizedForView) {
        this.authorizedForView = authorizedForView;
    }
	/**
	 * @param authorizedForAssociation The authorizedForAssociation to set.
	 */
	public void setAuthorizedForAssociation(boolean authorizedForAssociation) {
		
	}
	/**
	 * @return Returns the authorizedForAttachments.
	 */
	public boolean isAuthorizedForAttachments() {
		try {
			User user = getUser();
			if(null != user){
				if(user.isAuthorized(WebConstants.CONTRACT_MODULE,WebConstants.MAINTAIN_TASK) || 
						user.isAuthorized(WebConstants.PRODUCT_MODULE,WebConstants.MAINTAIN_TASK) || 
						user.isAuthorized(WebConstants.BENEFIT_COMPONENTS_MODULE,WebConstants.MAINTAIN_TASK) ||
						user.isAuthorized(WebConstants.BENEFIT_MODULE,WebConstants.MAINTAIN_TASK)){
					return true;
				}
			}
		} catch (SevereException e) {
			return false;
		}
		return false;
		
	}
	/**
	 * @param authorizedForAttachments The authorizedForAttachments to set.
	 */
	public void setAuthorizedForAttachments(boolean authorizedForAttachments) {
	}
	

	/**
	 * @return Returns the allBenefit.
	 */
	public List getAllBenefit() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allBenefit The allBenefit to set.
	 */
	public void setAllBenefit(List allBenefit) {
		this.allBenefit = allBenefit;
	}
	/**
	 * @return Returns the allBenefitComponent.
	 */
	public List getAllBenefitComponent() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allBenefitComponent The allBenefitComponent to set.
	 */
	public void setAllBenefitComponent(List allBenefitComponent) {
		this.allBenefitComponent = allBenefitComponent;
	}
	/**
	 * @return Returns the allBenefitLevel.
	 */
	public List getAllBenefitLevel() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allBenefitLevel The allBenefitLevel to set.
	 */
	public void setAllBenefitLevel(List allBenefitLevel) {
		this.allBenefitLevel = allBenefitLevel;
	}
	/**
	 * @return Returns the allBenefitLine.
	 */
	public List getAllBenefitLine() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allBenefitLine The allBenefitLine to set.
	 */
	public void setAllBenefitLine(List allBenefitLine) {
		this.allBenefitLine = allBenefitLine;
	}
	/**
	 * @return Returns the allContracts.
	 */
	public List getAllContracts() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allContracts The allContracts to set.
	 */
	public void setAllContracts(List allContracts) {
		this.allContracts = allContracts;
	}
	/**
	 * @return Returns the allNotes.
	 */
	public List getAllNotes() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allNotes The allNotes to set.
	 */
	public void setAllNotes(List allNotes) {
		this.allNotes = allNotes;
	}
	/**
	 * @return Returns the allProduct.
	 */
	public List getAllProduct() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allProduct The allProduct to set.
	 */
	public void setAllProduct(List allProduct) {
		this.allProduct = allProduct;
	}
	/**
	 * @return Returns the allProductStructure.
	 */
	public List getAllProductStructure() {
		return getDetailsForCurrentObject();
	}
	/**
	 * @param allProductStructure The allProductStructure to set.
	 */
	public void setAllProductStructure(List allProductStructure) {
		this.allProductStructure = allProductStructure;
	}
}