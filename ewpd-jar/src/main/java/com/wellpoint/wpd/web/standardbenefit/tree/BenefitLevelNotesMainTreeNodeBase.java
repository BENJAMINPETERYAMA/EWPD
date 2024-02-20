/*
 * BenefitLevelNotesMainTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitlevel.builder.BenefitLevelNotesTreeBuilder;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelNotesMainTreeNodeBase extends BaseTreeNode{
    
    public BenefitLevelNotesMainTreeNodeBase() {
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
	 public BenefitLevelNotesMainTreeNodeBase(TreeModel treeModel,
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
    public BenefitLevelNotesMainTreeNodeBase(String type, String identifier, String name,
            boolean leaf) {
        super(type, identifier, name, leaf);
    }
    
    /**
     * Loads the products for the contract when the contract node is clicked
     * @return List
     */
    public List loadChildren(){
        
        Logger.logInfo("entered method loadChildren of BenefitLevelNotesMainTreeNodeBase");
        children = new ArrayList();
        String benefitLineId = null;
        String definitionId= null;
        String identifier = this.getIdentifier();
        String [] identifierArray = identifier.split("~");
        if(null!=identifierArray){
        	benefitLineId = identifierArray[0];
        	if(identifierArray.length>1)
        		definitionId = identifierArray[1];
        }
        List benefitLineAttachedNotes = null;
        
        BenefitLevelNotesTreeBuilder benefitLevelNotesTreeBuilder = new BenefitLevelNotesTreeBuilder();
        
        //an instance of notes BO is created
        AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
        
        //checks if the identifier i.e(notesId) is null
        if(null == benefitLineId ||"0".equals(benefitLineId))
            return null;
        
        attachedNotesBO.setEntityId(Integer.parseInt(benefitLineId));
        attachedNotesBO.setEntityType("ATTACHBNFTLINE");
        if(null!=definitionId && !definitionId.equals(""))
        	attachedNotesBO.setBenefitDefinitionKey(definitionId);
        try {
            //fetches the notes
            benefitLineAttachedNotes = 
            	benefitLevelNotesTreeBuilder.locateBenefitLineAttachedNotes(attachedNotesBO);
        } catch (AdapterException e) {
            // TODO Auto-generated catch block
            return null;
//        	Logger.logError(e);
        }
        
        if(null == benefitLineAttachedNotes || benefitLineAttachedNotes.isEmpty())
            return null;
        // add the notes as a children node
        for(int i = 0; i < benefitLineAttachedNotes.size(); i++){
            AttachedNotesBO noteDetails = (AttachedNotesBO) benefitLineAttachedNotes.get(i);
            children.add(new BenefitLineNotesMainTreeNodeBase(getModel(),
                    this, "Notes", noteDetails.getNoteId()+"~"+noteDetails.getVersion(),
                    noteDetails.getNoteName(), true));
        }
        
        
    	return children;
    }
}
