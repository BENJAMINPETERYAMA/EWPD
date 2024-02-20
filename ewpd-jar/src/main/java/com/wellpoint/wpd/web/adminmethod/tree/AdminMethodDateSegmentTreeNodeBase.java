/*
 * Created on Mar 8, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.tree;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.adminmethod.AdminMethodTreeNode;



/**
 * @author U12238
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodDateSegmentTreeNodeBase extends AdminMethodTreeNode {

    public AdminMethodDateSegmentTreeNodeBase(String type, String identifier, String name,
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
        productId = this.getIdentifier();
    //   
       
        List dateSegmentList=new ArrayList();
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        .getExternalContext().getSession(true);
        
        String entityType = session.getAttribute("AM_ENTITY_TYPE").toString();
        
        
        AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
        // an instance of Standard Benefit BO is created
        AdminMethodValidationBO adminMethodValidationBO = new AdminMethodValidationBO();
       
        //checks if the identifier is null
        if(null==productId ||"0".equals(productId))
            return children;
        
       
       // change for new enhancement -- Start
        adminMethodValidationBO.setEntitySysId(Integer.parseInt(productId));
        
        adminMethodValidationBO.setEntityType(entityType);
       // change for new enhancement -- End
    
        
        try {
            //fteches the standard benefit details
        	dateSegmentList=adminMethodBusinessObjectBuilder.getDateSegments(adminMethodValidationBO);
            
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
        for(int i=0;i< dateSegmentList.size();i++)
        {
        	adminMethodValidationBO=(AdminMethodValidationBO)dateSegmentList.get(i);
            String dateSegmentId=Integer.toString(adminMethodValidationBO.getEntitySysId());
            String dateSegmentName=adminMethodValidationBO.getEffectiveDate()+"-"+adminMethodValidationBO.getExpiryDate();
            
            
            
            //standard benefits added as the children of Benefit Component	
            children.add(new AdminMethodTreeNodeBase(getModel(),
                    this, "DateSegement",dateSegmentId,dateSegmentName, false));
            
            
        }
        return children;
        

  
    }

}
