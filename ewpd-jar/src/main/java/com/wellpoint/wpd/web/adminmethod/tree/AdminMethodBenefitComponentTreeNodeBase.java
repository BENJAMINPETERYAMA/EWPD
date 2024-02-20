/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.tree;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.adminmethod.AdminMethodTreeNode;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodBenefitComponentTreeNodeBase extends AdminMethodTreeNode {
	
	

	
	/**
	 * 
	 * @param treeModel
	 * @param parent
	 * @param type
	 * @param identifier
	 * @param name
	 * @param leaf
	 */
	 public AdminMethodBenefitComponentTreeNodeBase(TreeModel treeModel,
	 		AdminMethodTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }
	 
	 
	 /**
	  * 
	  * @param type
	  * @param identifier
	  * @param name
	  * @param leaf
	  */
    public AdminMethodBenefitComponentTreeNodeBase(String type, String identifier, String name,
            boolean leaf) {
        super(type, identifier, name, leaf);
    } 
    
    /**
     * Loads the standard benefits for a Benefit Component when its node is clicked 
     * 
     * 
     * @return List
     */
    protected List loadChildren() {
        
        Logger.logInfo("entered method loadChildren of ProductBenefitComponentTreeNodeBase");
        
        children = null;
        
        productId = this.getParent().getIdentifier();
       
        //gets the benefit component id
        String[] benefitComponentDet = this.getIdentifier().split("~");
        String benefitComponentId = benefitComponentDet[0];
        List standardBenefitList=new ArrayList();
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        .getExternalContext().getSession(true);
        
        String entityType = session.getAttribute("AM_ENTITY_TYPE").toString();
        
        
        AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
        // an instance of Standard Benefit BO is created
        AdminMethodValidationBO adminMethodValidationBO = new AdminMethodValidationBO();
       
        //checks if the identifier is null
        if(null==benefitComponentId ||"0".equals(benefitComponentId))
            return children;
        
        //sets the benefit component id to the BO
        adminMethodValidationBO.setBenefitComSysId(Integer.parseInt(benefitComponentId));
       
       // change for new enhancement -- Start
        adminMethodValidationBO.setEntitySysId(Integer.parseInt(productId));
        
        adminMethodValidationBO.setEntityType(entityType);
       // change for new enhancement -- End
    
        
        try {
            //fteches the standard benefit details
            standardBenefitList=adminMethodBusinessObjectBuilder.getStandardBenefits(adminMethodValidationBO);
            
        }  catch(SevereException e)
        {
			Logger.logError(e);
        }
        catch(AdapterException e)
        {
			Logger.logError(e);
        }
        children = new ArrayList();
        //the standard benefit list is iterated to get the standard benefits
        for(int i=0;i< standardBenefitList.size();i++)
        {
        	adminMethodValidationBO=(AdminMethodValidationBO)standardBenefitList.get(i);
            String standardBenefitId=Integer.toString(adminMethodValidationBO.getBenefitSysId());
            standardBenefitId = standardBenefitId + "~" + adminMethodValidationBO.getAdminMethodId();
            String standardBnftName=adminMethodValidationBO.getBenefitName();
            
            //standard benefits added as the children of Benefit Component	
            children.add(new AdminMethodStandardBenefitTreeNodeBase(getModel(),
                    this, "Standard-Benefit",standardBenefitId,standardBnftName, true));
            
            
        }
        return children;
        

  
    }

}
