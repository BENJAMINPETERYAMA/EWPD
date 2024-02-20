/*
 * RoleMainTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.role.tree;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.security.builder.ModuleBusinessObjectBuilder;
import com.wellpoint.wpd.business.security.builder.RoleTreeBuilder;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleMainTreeNodeBase extends BaseTreeNode {
	 protected HttpSession getSession() {
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	                .getExternalContext().getSession(true);
	        return session;
	    }

    public RoleMainTreeNodeBase() {
        super();
    }
    
    /**
	 * 
	 * @param treeModel
	 * @param parent
	 * @param type
	 * @param identifier
	 * @param name
	 * @param leaf
	 */
	 public RoleMainTreeNodeBase(TreeModel treeModel,
	         BaseTreeNode parent, String type, String identifier,
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
    public RoleMainTreeNodeBase(String type, String identifier, String name,
            boolean leaf) {
        super(type, identifier, name, leaf);
    }
    
    /**
     * Loads the products for the contract when the contract node is clicked
     * @return List
     */
    public List loadChildren(){
        
        Logger.logInfo("entered method loadChildren of BenefitLevelNotesMainTreeNodeBase");
        children = new ArrayList(0);
        String roleId = this.getIdentifier();
        List associatedModules = null;
        
        RoleTreeBuilder roleTreeBuilder = new RoleTreeBuilder();
       
        String srdaFlag = (String)getSession().getAttribute("SRDA_FLAG");
        
        //an instance of modules BO is created
        ModuleBO moduleBO = new ModuleBO();
        ModuleSrdaBO moduleSrdaBO= new ModuleSrdaBO();
        
        //checks if the identifier i.e(notesId) is null
        if(null == roleId ||"0".equals(roleId))
            return null;
    
        try {
            //fetches the notes
        	if(srdaFlag.equalsIgnoreCase("SRDA")){
        		associatedModules = (List) 
        				roleTreeBuilder.locateModules(moduleSrdaBO);
        	}else{
        		 moduleBO.setRoleId(new Integer(roleId).intValue());
        		associatedModules = (List) 
        				roleTreeBuilder.locateModules(moduleBO);
        	}
        } catch (WPDException e) {
			Logger.logError(e);
        } catch(AdapterException ex){
        	Logger.logError(ex);
        }
        
        if(null == associatedModules || associatedModules.isEmpty())
            return null;
        children=new ArrayList(associatedModules.size());
        // add the notes as a children node
        for(int i = 0; i < associatedModules.size(); i++){
        	if(srdaFlag.equalsIgnoreCase("SRDA")){
        		 ModuleSrdaBO individualModuleBO = (ModuleSrdaBO) associatedModules.get(i);
                 children.add(new RoleModuleMainTreeNodeBase(getModel(),
                         this, "Modules", new Integer(individualModuleBO.getModuleId()).toString(),
                         individualModuleBO.getModuleName(), false));
        	}else{
        		 ModuleBO individualModuleBO = (ModuleBO) associatedModules.get(i);
                 children.add(new RoleModuleMainTreeNodeBase(getModel(),
                         this, "Modules", new Integer(individualModuleBO.getModuleId()).toString(),
                         individualModuleBO.getModuleName(), false));
        	}
           
        }
        
    	return children;
    }
    
}
