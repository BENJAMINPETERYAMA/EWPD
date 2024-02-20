/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.tree;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.web.adminmethod.AdminMethodTreeNode;
/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodStandardBenefitTreeNodeBase extends AdminMethodTreeNode{
	
	public AdminMethodStandardBenefitTreeNodeBase(){
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
    public AdminMethodStandardBenefitTreeNodeBase(TreeModel treeModel,
    		AdminMethodTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);

    }
    
    /**
     * Loads the administartion folder when a StandardBenefit node is clicked 
     * 
     * 
     * @return List
     *//*
    protected List loadChildren()
    {
        Logger.logInfo("entered method loadChildren of ProductStandardBenefitTreeNodeBase");
        children = new ArrayList();
        
        //adds administration as the child of Standard Benefit
        children.add(new AdminMethodAdministrationTreeNodeBase(getModel(),this,"administration","administrationId","Administration",false));
        return children;
    }*/

}
