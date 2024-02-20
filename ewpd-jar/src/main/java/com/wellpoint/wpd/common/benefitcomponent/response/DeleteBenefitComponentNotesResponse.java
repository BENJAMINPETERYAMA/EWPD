/*
 * DeleteBenefitComponentNotesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteBenefitComponentNotesResponse extends WPDResponse{

    private AttachedNotesBO attachedNotesBO;
    
    /**
     * @return attachedNotesBO
     * 
     * Returns the attachedNotesBO.
     */
    public AttachedNotesBO getAttachedNotesBO() {
        return attachedNotesBO;
    }
    /**
     * @param attachedNotesBO
     * 
     * Sets the attachedNotesBO.
     */
    public void setAttachedNotesBO(AttachedNotesBO attachedNotesBO) {
        this.attachedNotesBO = attachedNotesBO;
    }
}
