/*
 * Sequensable.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.util;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Sequensable.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class Sequensable implements Comparable {

	private String key;
	private int oldSequenceNumber;
	private int newSequenceNumber;
	private boolean moveUp;
	private boolean moveDown;
	
    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }
    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @return Returns the moveDown.
     */
    public boolean isMoveDown() {
        return moveDown;
    }
    /**
     * @param moveDown The moveDown to set.
     */
    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }
    /**
     * @return Returns the moveUp.
     */
    public boolean isMoveUp() {
        return moveUp;
    }
    /**
     * @param moveUp The moveUp to set.
     */
    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }
    /**
     * @return Returns the newSequenceNumber.
     */
    public int getNewSequenceNumber() {
        return newSequenceNumber;
    }
    /**
     * @param newSequenceNumber The newSequenceNumber to set.
     */
    public void setNewSequenceNumber(int newSequenceNumber) {
        this.newSequenceNumber = newSequenceNumber;
    }
    /**
     * @return Returns the oldSequenceNumber.
     */
    public int getOldSequenceNumber() {
        return oldSequenceNumber;
    }
    /**
     * @param oldSequenceNumber The oldSequenceNumber to set.
     */
    public void setOldSequenceNumber(int oldSequenceNumber) {
        this.oldSequenceNumber = oldSequenceNumber;
    }
    /* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
    public int compareTo(Object o) {
	    
	    Sequensable newSequensable = (Sequensable) o;
	    boolean moveUp1 = this.moveUp;
	    boolean moveUp2 = newSequensable.isMoveUp();
	    boolean moveDown1 = this.moveDown;
	    boolean moveDown2 = newSequensable.isMoveDown();
	    int seqNo1 = this.newSequenceNumber;
	    int seqNo2 = newSequensable.getNewSequenceNumber();
	    if( seqNo1 == seqNo2 ){
	        if( moveUp1 || moveDown1 ){
	            if(moveUp1)
	                return -1;
	            else
	                return 1;
	        }
	        else if( moveUp2 || moveDown2 ){
	            if(moveUp2)
	                return 1;
	            else
	                return -1;
	        }
	        return 0;
	    }
	    if(seqNo1 < seqNo2)
	        return -1;
	    else
	        return 1;
	}
    
    
    /* old code for reference */
//	public int compareTo(Object o) {
//	    
//	    Sequensable newSequensable = (Sequensable) o;
//	    boolean moveUp1 = this.moveUp;
//	    boolean moveUp2 = newSequensable.isMoveUp();
//	    boolean moveDown1 = this.moveDown;
//	    boolean moveDown2 = newSequensable.isMoveDown();
//	    int seqNo1 = this.newSequenceNumber;
//	    int seqNo2 = newSequensable.getNewSequenceNumber();
//	    if( seqNo1 == seqNo2 ){
//	        if( moveUp1 || moveDown1 ){
//	            if(moveUp1)
//	                return 1;
//	            else
//	                return -1;
//	        }
//	        else if( moveUp2 || moveDown2 ){
//	            if(moveUp2)
//	                return 1;
//	            else
//	                return -1;
//	        }
//	        return 0;
//	    }
//	    if(seqNo1 < seqNo2)
//	        return 1;
//	    else
//	        return -1;
//	}
    
  
}
