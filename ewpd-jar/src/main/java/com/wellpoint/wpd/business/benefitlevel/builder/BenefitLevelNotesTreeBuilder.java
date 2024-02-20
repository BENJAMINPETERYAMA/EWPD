/*
 * BenefitLevelNotesTreeBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitlevel.builder;

import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.notes.adapter.NotesAttachmentAdapterManager;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelNotesTreeBuilder {
    /**
     * This method to retrieve the Product Details for the corresponding Contract.
     * @param businessObject
     * @return
     * @throws WPDException
     */
    public List locateBenefitLineAttachedNotes(AttachedNotesBO businessObject)throws AdapterException {
       /*BenefitLevelNotesTreeAdapterManager adapterManager = new BenefitLevelNotesTreeAdapterManager();
       return adapterManager.locateBenefitLineAttachedNotes(businessObject);*/
       List associatedNotesForBnftLine = null;
       NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
       int entityId = businessObject.getEntityId();
       String entityType = businessObject.getEntityType();
       String definitionId = businessObject.getBenefitDefinitionKey();
       try{
       LocateResults associatedNotesLocateResults = notesAttachmentAdapterManager.locateAttachedNotes(entityId,
               entityType, definitionId);
       associatedNotesForBnftLine = associatedNotesLocateResults.getLocateResults();
       }catch (Exception ex) {
		
		throw new AdapterException(
				"Exception occured in locateBenefitLineAttachedNotes AttachedNotesBO method in BenefitLevelNotesTreeBuilder",
				ex);
       }
       return associatedNotesForBnftLine;
    }
}
