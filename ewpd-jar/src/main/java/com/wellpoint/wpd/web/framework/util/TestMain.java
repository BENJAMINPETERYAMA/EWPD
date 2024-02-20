/*
 * TestMain.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: TestMain.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class TestMain {

	public static void main(String[] args) {
		List list = new ArrayList();
		
		TestBO testBO = new TestBO();
		testBO.setKey1("A");
		testBO.setKey2("1");
		testBO.setSeqnce(1);
		list.add(testBO);
		
		testBO = new TestBO();
		testBO.setKey1("B");
		testBO.setKey2("2");
		testBO.setSeqnce(2);
		list.add(testBO);
		
		testBO = new TestBO();
		testBO.setKey1("C");
		testBO.setKey2("3");
		testBO.setSeqnce(3);
		list.add(testBO);
		
		testBO = new TestBO();
		testBO.setKey1("D");
		testBO.setKey2("4");
		testBO.setSeqnce(4);
		list.add(testBO);
		
		SequenceUtil util = new SequenceUtil();
		util.registerObjects(list,TestBO.KEY_INFO,TestBO.SEQ_ATTR);
		
		((TestBO)list.get(2)).setSeqnce(1);
		((TestBO)list.get(3)).setSeqnce(1);
		
	}
}
