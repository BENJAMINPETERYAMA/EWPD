/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.tree;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodFetchInvalidDatesegmantRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationResponse;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodTreeBackingBean  extends WPDBackingBean {
	
	private TreeState treeState;
	
	private TreeModelBase treeDataModeltemp;
	
	private TreeModelBase treeDataModel;
	
	private AdminMethodTreeNodeBase root;
	
	private boolean withChildren;
	
	private String scrollTop;
	
	private HtmlInputHidden loadTree;
	
	
	public AdminMethodTreeBackingBean(){
		  Logger.logInfo("entered constructor of ProductTreeBackingBean");

	        treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute("ADMINMETHOD_SESSION_TREE_STATE", treeState);   
	}
	
	
	 /**
     * This method returns the treeDataModel.
     * @return TreeModel treeDataModel.
     */
    public TreeModel getTreeDataModel() {
        
        Logger.logInfo("entered method getTreeDataModel");
        //gets the product Id from session
        
       
        
        String entityId ="";
        String entityType ="";
        String entityName = "";
        if(null != getSession().getAttribute("AM_ENTITY_ID"))
        	entityId = getSession().getAttribute("AM_ENTITY_ID").toString();
        
        if(null != getSession().getAttribute("AM_ENTITY_TYPE"))
        	entityType = getSession().getAttribute("AM_ENTITY_TYPE").toString();
        
        if(null != getSession().getAttribute("AM_ENTITY_NAME"))
        	entityName = getSession().getAttribute("AM_ENTITY_NAME").toString();
      /*   WLPRD00444546  changes starts */ 
      
        if (null == getSession().getAttribute("DIRECT_CLICK")) {
        if("contract".equals(entityType)){
        	
        	List dateSegmentList=null;
        	AdminMethodValidationBO adminMethodValidationBO = null;
        	AdminMethodFetchInvalidDatesegmantRequest datesegmantRequest =new AdminMethodFetchInvalidDatesegmantRequest();
        	String contractId = (String) getSession().getAttribute("AM_CONTRACT_ID");
        	datesegmantRequest.setContractId(contractId);
        	datesegmantRequest.setEntityType(entityType);
        	AdminMethodValidationResponse adminMethodValidationResponse = (AdminMethodValidationResponse) this
			.executeService(datesegmantRequest);
        	dateSegmentList =adminMethodValidationResponse.getResultList();
        	boolean check = false; 
        	if (null != dateSegmentList && dateSegmentList.size()>0){
        		int i=0;
        		while (i<dateSegmentList.size()){
	         		adminMethodValidationBO=(AdminMethodValidationBO)dateSegmentList.get(i);
	                 
	                 if (entityId.equals(Integer.toString(adminMethodValidationBO.getEntitySysId()))){
	                	 check = true;
	                	 break;
	                 }
	                 i++;
        		}
        		if (!check){
        			adminMethodValidationBO=(AdminMethodValidationBO)dateSegmentList.get(0);
        			entityId = Integer.toString(adminMethodValidationBO.getEntitySysId());
        			 getSession().setAttribute("AM_ENTITY_ID", entityId);
                // getSession().setAttribute("AM_ENTITY_KEY", dateSegmentId);
        		}
                
         	}
        	
        }
        }
        /*   WLPRD00444546  changes ends */ 
        
       List benefitComponents = null;
        //gets the product Name from session
        
       
        boolean rootFlag = false; 
        
        if(entityType.equals("product")){
        
	        if (null != entityId) {
	        		root = new AdminMethodTreeNodeBase(WebConstants.ROOT, entityId,
	            		entityName, false);
	        	
	            rootFlag = true;
	        } else {
	        	
	        		root = new AdminMethodTreeNodeBase(WebConstants.ROOT,
	                    WebConstants.ZERO_STRING, "Product", false);
	        	
	        }
        }else if (entityType.equals("ProdStructure")){
        	
        	  if (null != entityId) {
        	  	
        	  		root = new AdminMethodTreeNodeBase(WebConstants.ROOT, entityId,
	            		entityName, false);
        	  	
	            rootFlag = true;
	        } else {
	        	
	            root = new AdminMethodTreeNodeBase(WebConstants.ROOT,
	                    WebConstants.ZERO_STRING, "ProductStructure", false);
	        	
	        }
        }else if (entityType.equals("contract")){
        	
      	  if (null != entityId) {
      	  		
      	  			root = new AdminMethodTreeNodeBase(WebConstants.ROOT, entityId,
	            		entityName, false);
      	  		
	            rootFlag = true;
	        } else {
	        	
	        		root = new AdminMethodTreeNodeBase(WebConstants.ROOT,
	                    WebConstants.ZERO_STRING, "contract", false);
	        	
	        }
        }   
           
        treeDataModel = new TreeModelBase(root);
        root.setModel(treeDataModel);
        treeDataModel.setTreeState(treeState);
        /*   WLPRD00444546  changes starts */ 
        
       // int m = -1;
        if(rootFlag){
	        getSession().setAttribute("AM_ENTITY_KEY", entityId + "");
			String expandPath[] = new String[3];
			expandPath[0] = root.getNodePath();
			  //   joe
			benefitComponents = root.loadChildren();
			if(!benefitComponents.isEmpty()){
				//AdminMethodBenefitComponentTreeNodeBase subRootNode = (AdminMethodBenefitComponentTreeNodeBase) root.loadChildren()
					//	.get(0);
				AdminMethodBenefitComponentTreeNodeBase subRootNode = (AdminMethodBenefitComponentTreeNodeBase) benefitComponents
				.get(0);
		/*   WLPRD00444546  changes ends */ 
		
				expandPath[1] = subRootNode.getNodePath();
				List listObjectNodesProduct = subRootNode.loadChildren();
				String selectNode = WebConstants.EMPTY_STRING;
				if(null == getSession().getAttribute("AM_BC_KEY")){
					getSession().setAttribute("AM_BC_KEY", subRootNode.getIdentifier().split("~")[0] + "");
					getSession().setAttribute(WebConstants.BNFT_CMPNT_KEY, subRootNode.getIdentifier().split("~")[0] + "");
				}
				
				//Added For AdminMethodPrint popup
				
				getSession().setAttribute("AM_BENEFIT_COMP_NAME", subRootNode.getDescription() + "");
				//Added For AdminMethodPrint popup
				getSession().setAttribute(WebConstants.BENEFIT_COMP_NAME, subRootNode.getDescription() + "");
				AdminMethodStandardBenefitTreeNodeBase objectNodeProduct = (AdminMethodStandardBenefitTreeNodeBase) listObjectNodesProduct
							.get(0);
				expandPath[2] = objectNodeProduct.getNodePath();

				if(null == getSession().getAttribute("AM_BENEFIT")){
					getSession().setAttribute("AM_BENEFIT", objectNodeProduct.getIdentifier().split("~")[0] + "");
					getSession().setAttribute(WebConstants.SESSION_BNFT_DEFN_ID, objectNodeProduct.getIdentifier().split("~")[0] + "");
					selectNode = (objectNodeProduct).getNodePath();
					treeState.expandPath(expandPath);
					treeState.setSelected(selectNode);
				}
				/*   WLPRD00444546  changes starts */ 
				
			//	String productName = objectNodeProduct.getDescription();
				
			//	m = 1;
			/*   WLPRD00444546  changes ends */ 
			
			}
			
        }
        
        List list = (List)getSession().getAttribute("treeChildren");
        if(null == list || list.isEmpty()){
          /*   WLPRD00444546  changes starts */ 
        	if (null == benefitComponents){
        	list = root.loadChildren();
        	getSession().setAttribute("treeChildren",list);
        	}else{
        		getSession().setAttribute("treeChildren",benefitComponents);
        	}
        		
        	/*   WLPRD00444546  changes ends */ 
        	
        }
        if(null == list || list.isEmpty()){
        	withChildren = false;
        }else{
        	withChildren = true;
        }
        
        return treeDataModel;

    }
    
	//This method processes action for Standard Benefit click in the tree.
    public void processActionForBenefit(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForStandardBenefit");
        
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
	
        }
		if (component != null) {
		    HtmlTree tree = (HtmlTree) component;
		    TreeNodeBase node = (AdminMethodStandardBenefitTreeNodeBase) tree.getNode();
		    tree.setNodeSelected(event);
		    String type = node.getType();
		    String[] key = node.getIdentifier().split("~");
		    String benefitKey = key[0];
		    String seqNo = key[1];
		    String benefitComponentId=((AdminMethodStandardBenefitTreeNodeBase) node).getParent().getIdentifier();
		    
		    String benefitComponentName=((AdminMethodStandardBenefitTreeNodeBase) node).getParent().getDescription();

		    String entityId = ((AdminMethodStandardBenefitTreeNodeBase) node).getParent().getParent().getIdentifier();
		    
		    String[] benefitCompDetails = ((AdminMethodStandardBenefitTreeNodeBase) node).getParent().getIdentifier().split("~");
		    String benefitCompId = benefitCompDetails[0];
		    String sqncNumber = benefitCompDetails[1]; 
		    
		    getSession().setAttribute("AM_ENTITY_KEY",entityId);
		    getSession().setAttribute("AM_BC_KEY", benefitCompId);
		    getSession().setAttribute("AM_BC_SQNC", sqncNumber);
		    getSession().setAttribute("AM_BENEFIT",benefitKey);
		    getSession().setAttribute("AM_BEN_SQNC", seqNo);
		    getSession().setAttribute("AM_BENEFIT_COMP_NAME",benefitComponentName);
		}
	}
	/**
	 * @return Returns the withChildren.
	 */
	public boolean isWithChildren() {
		return withChildren;
	}
	/**
	 * @param withChildren The withChildren to set.
	 */
	public void setWithChildren(boolean withChildren) {
		this.withChildren = withChildren;
	}
	/**
	 * @return Returns the scrollTop.
	 */
	public String getScrollTop() {
		Object clearScrollTop = getRequest().getAttribute("clearScrollTop");  
    	
    	if(clearScrollTop !=null && clearScrollTop.toString().equals("true")) {
    		scrollTop = null;    		
    	}
		return scrollTop;
	}
	/**
	 * @param scrollTop The scrollTop to set.
	 */
	public void setScrollTop(String scrollTop) {
		this.scrollTop = scrollTop;
	}

	//Add a new attribute to load the tree, due to component load order change on JSF 1.2. 
	public HtmlInputHidden getLoadTree() {
		getTreeDataModel();
		return loadTree;
	}


	public void setLoadTree(HtmlInputHidden loadTree) {
		this.loadTree = loadTree;
	}
}
