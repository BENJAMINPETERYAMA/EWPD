/*
 * Created on Aug 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.product.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.bo.ProductAdminBO;


/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductAdministrationHeadTreeNodeBase extends ProductTreeNode{
	  public ProductAdministrationHeadTreeNodeBase() {
        super();
    }

    /**
     * 
     * 
     * @param treeModel
     * @param parent
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ProductAdministrationHeadTreeNodeBase(TreeModel treeModel,
            ProductTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
    	  super(treeModel, parent, type, identifier, name, leaf);
    }

    /**
     * Loads the benefit Components for the product when the product node is clicked
     * 
     * 
     * @return List
     */
    public List loadChildren(){
        
        Logger.logInfo("entered method loadChildren of ContractProductMainTreeNodeBase");
        children= new ArrayList();
        int productkey= Integer.parseInt(this.getIdentifier());
        ProductAdapterManager adapterManager = new ProductAdapterManager();
        List adminList = null;
        try{
         adminList = adapterManager.getAdminList(productkey);
          
        }catch(WPDException ex){
			Logger.logError(ex);
        }
        Iterator itr = adminList.iterator();
        while(itr.hasNext()){
        ProductAdminBO  AdminBO = (ProductAdminBO)itr.next();
        String AdminId = Integer.toString(AdminBO.getAdminKey());
        String AdminDesc =AdminBO.getAdminDesc();
        children.add(new  ProductAdminNodeTreeNodeBase(getModel(),this,"Admin-Node",AdminId,AdminDesc,true));
        }
        
        
    	return children;
    }

}
