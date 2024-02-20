/*
 * Sequence.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.sequence.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class Sequence {
    private int nextSequenceNumber;

    /**
     * Returns the nextSequenceNumber
     * @return int nextSequenceNumber.
     */
    public int getNextSequenceNumber() {
        return nextSequenceNumber;
    }
    
    /**
     * Sets the nextSequenceNumber
     * @param nextSequenceNumber.
     */
    public void setNextSequenceNumber(int nextSequenceNumber) {
        this.nextSequenceNumber = nextSequenceNumber;
    }
}
