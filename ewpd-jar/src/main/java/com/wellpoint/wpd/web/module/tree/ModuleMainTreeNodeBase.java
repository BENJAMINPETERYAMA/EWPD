/*
 * ModuleMainTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.module.tree;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.security.builder.ModuleTreeBuilder;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
import com.wellpoint.wpd.common.security.vo.ModuleVO;
import com.wellpoint.wpd.common.security.vo.TaskVO;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleMainTreeNodeBase extends BaseTreeNode {

	 protected HttpSession getSession() {
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	                .getExternalContext().getSession(true);
	        return session;
	    }

    public ModuleMainTreeNodeBase() {
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
	 public ModuleMainTreeNodeBase(TreeModel treeModel,
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
    public ModuleMainTreeNodeBase(String type, String identifier, String name,
            boolean leaf) {
        super(type, identifier, name, leaf);
    }
    
    /**
     * Loads the products for the contract when the contract node is clicked
     * @return List
     */
    public List loadChildren(){
    	
        Logger.logInfo("entered method loadChildren of ModuleMainTreeNodeBase");
        children = new ArrayList(0);
        String moduleId = this.getIdentifier();
        List associatedTasks = null;
        
        ModuleTreeBuilder moduleTreeBuilder = new ModuleTreeBuilder();
        
        //an instance of modules BO is created
        TaskBO taskBO = new TaskBO();
        TaskSrdaBO taskSrdaBO = new TaskSrdaBO();
        
        //checks if the identifier i.e(notesId) is null
        if(null == moduleId ||"0".equals(moduleId))
            return null;
         
        
        
        try {
            //fetches the associated tasks
        	if(getSession().getAttribute("SRDA_FLAG").equals("SRDA")){
        		taskSrdaBO.setModuleId(Integer.parseInt(moduleId));
        		associatedTasks = (List) 
                    	moduleTreeBuilder.locateAssociatedTasks(taskSrdaBO);
        	}else{
        	     taskBO.setModuleId(Integer.parseInt(moduleId));
        		associatedTasks = (List) 
                    	moduleTreeBuilder.locateAssociatedTasks(taskBO);
        	}
        	
        } catch (WPDException e) {
            // TODO Auto-generated catch block
            return null;
//			Logger.logError(e);
        }catch(AdapterException ex){
        	return null;
        }
        
        if(null == associatedTasks || associatedTasks.isEmpty())
            return null;
        children=new ArrayList(associatedTasks.size());
        // add the notes as a children node
        for(int i = 0; i < associatedTasks.size(); i++){
        	if(getSession().getAttribute("SRDA_FLAG").equals("SRDA")){
        		 TaskSrdaBO individualTaskBO = (TaskSrdaBO) associatedTasks.get(i);
                 children.add(new TaskMainTreeNodeBase(getModel(),
                         this, "Tasks", new Integer(individualTaskBO.getTaskId()).toString(),
                         individualTaskBO.getTaskName(), true));
        	}else{
            TaskBO individualTaskBO = (TaskBO) associatedTasks.get(i);
            children.add(new TaskMainTreeNodeBase(getModel(),
                    this, "Tasks", new Integer(individualTaskBO.getTaskId()).toString(),
                    individualTaskBO.getTaskName(), true));
        	}
        }
        
        
    	return children;
    }
    
}
