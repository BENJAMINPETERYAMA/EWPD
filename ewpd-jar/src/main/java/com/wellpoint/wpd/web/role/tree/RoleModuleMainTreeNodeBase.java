/*
 * RoleModuleMainTreeNodeBase.java
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
import com.wellpoint.wpd.business.security.builder.RoleTreeBuilder;
import com.wellpoint.wpd.business.security.locatecriteria.RoleLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleModuleMainTreeNodeBase extends BaseTreeNode {

    public RoleModuleMainTreeNodeBase() {
        super();
    }
    protected HttpSession getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return session;
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
    public RoleModuleMainTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }

    /**
     * 
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public RoleModuleMainTreeNodeBase(String type, String identifier,
            String name, boolean leaf) {
        super(type, identifier, name, leaf);
    }

    /**
     * Loads the products for the contract when the contract node is clicked
     * 
     * @return List
     */
    public List loadChildren() {

        Logger.logInfo("entered method loadChildren of ModuleMainTreeNodeBase");
        children = new ArrayList(0);
        String moduleId = this.getIdentifier();
        
        List associatedTasks = null;

        RoleTreeBuilder roleTreeBuilder = new RoleTreeBuilder();

        String roleId = this.getParent().getIdentifier();
        String srdaFlag = (String)getSession().getAttribute("SRDA_FLAG");

        //an instance of modules BO is created
        RoleLocateCriteria locateCriteria = new RoleLocateCriteria();

        //checks if the identifier i.e(notesId) is null
        if ((null == moduleId || "0".equals(moduleId))
                && (null == roleId || "0".equals(roleId)))
            return null;

        locateCriteria.setAssociatedModuleId(Integer.parseInt(moduleId));
        locateCriteria.setRoleId(new Integer(roleId).intValue());
        locateCriteria.setSubEntityType("Task");
        locateCriteria.setSrdaFlag(srdaFlag);
        try {
            //fetches the associated tasks
            associatedTasks = (List) roleTreeBuilder
                    .locateAssociatedTasks(locateCriteria);
        } catch (WPDException e) {
            //            return null;
			Logger.logError(e);
        }catch (AdapterException e) {
            //            return null;
			Logger.logError(e);
        }

        if (null == associatedTasks || associatedTasks.isEmpty())
            return null;
        children=new ArrayList(associatedTasks.size());
        // add the notes as a children node
        for (int i = 0; i < associatedTasks.size(); i++) {
        	if(srdaFlag.equalsIgnoreCase("SRDA")){
        		TaskSrdaBO individualTaskBO = (TaskSrdaBO) associatedTasks.get(i);
                children
                        .add(new RoleTaskMainTreeNodeBase(getModel(), this,
                                "Tasks", new Integer(individualTaskBO.getTaskId())
                                        .toString(),
                                individualTaskBO.getTaskName(), false));
        	}else{
        		TaskBO individualTaskBO = (TaskBO) associatedTasks.get(i);
                children
                        .add(new RoleTaskMainTreeNodeBase(getModel(), this,
                                "Tasks", new Integer(individualTaskBO.getTaskId())
                                        .toString(),
                                individualTaskBO.getTaskName(), false));
        	}
        	
            
        }

        return children;
    }

}