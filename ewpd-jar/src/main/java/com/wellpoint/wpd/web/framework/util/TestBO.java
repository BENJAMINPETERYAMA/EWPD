/*
 * TestBO.java
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
 * @version $Id: TestBO.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class TestBO {
	
	public static final String KEY_INFO = "key1~key2";
	public static final String SEQ_ATTR = "seqnce";
	private String key1 = null;
	private String key2 = null;
	private int seqnce = 0;
	

	/**
	 * @return Returns the key1.
	 */
	public String getKey1() {
		return key1;
	}
	/**
	 * @param key1 The key1 to set.
	 */
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	/**
	 * @return Returns the key2.
	 */
	public String getKey2() {
		return key2;
	}
	/**
	 * @param key2 The key2 to set.
	 */
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	/**
	 * @return Returns the seqnce.
	 */
	public int getSeqnce() {
		return seqnce;
	}
	/**
	 * @param seqnce The seqnce to set.
	 */
	public void setSeqnce(int seqnce) {
		this.seqnce = seqnce;
	}
	
	public String toString(){
		return "key1-"+ key1 + " key2-" + key2 + " seq-" + seqnce;
	}
}
