/*
 * SequenceUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.util.ReflectionUtil;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SequenceUtil.java 41102 2008-01-04 19:36:56Z U11087 $
 */
public class SequenceUtil {

	/**
	 * The key name for storing the key sequence information of the
	 * registered value objects in session.
	 */
	private static final String PREV_KEY_SEQ_INFO_ATTR_NAME = "seq_prevKeySeqInfo";

	public static final String GETTER_METHOD_NAME_APPENDER = "get";

	/**
	 * The KEY_INFO_ATTR_NAME is the key name for storing the key attributes of the
	 * registered VO objects in session.
	 */
	private static final String KEY_INFO_ATTR_NAME = "seq_keyInfo";

	/**
	 * The SEQ_ATTR_ATTR_NAME is the key name which stores the sequence
	 * attribute of the registered VO list in the session.
	 */
	private static final String SEQ_ATTR_ATTR_NAME = "seq_seqAttribute";

	/**
	 * The String which separates the different attributes in the key.
	 */
	public static final String KEY_ATTRIBUTE_SEPERATOR = "~";

	/**
	 * The String which separates a pack of key and sequence.
	 */
	public static final String KEY_SEQ_SEPERATOR = "'";

	/**
	 * The string which separates the key attributes group and the sequence
	 * number.
	 */
	public static final String SEQ_SEPERATOR = ":";

	/**
	 * This method is used to register a list of value objects with the
	 * SequenceUtil. The SequenceUtil remebers the sequence of each value object
	 * in it and can be used to reorder the list later. *
	 * 
	 * @param busObjList
	 *            List which contains the value objects.
	 * @param keyInfo
	 *            This is String which contains the attributes which make up the
	 *            unique key so that we could identify between value objects.
	 * @param seqAttribute
	 *            The attribute in which the sequence information is stored in
	 *            the value object.
	 */
	public void registerObjects(List busObjList, String keyInfo,
			String seqAttribute) {
		if (busObjList != null && busObjList.size() > 0 && null != keyInfo
				&& !"".equals(keyInfo) && null != seqAttribute
				&& !"".equals(seqAttribute)) {
			setAttributeToSession(KEY_INFO_ATTR_NAME, keyInfo);
			setAttributeToSession(SEQ_ATTR_ATTR_NAME,seqAttribute);
			
			manageExistingState(busObjList);
		}
	}
	
	/**
	 * This method is used to register a list of value objects with the
	 * SequenceUtil. The SequenceUtil remebers the sequence of each value object
	 * in it and can be used to reorder the list later. *
	 * 
	 * @param busObjList
	 *            List which contains the value objects.
	 * @param keyInfo
	 *            This is String which contains the attributes which make up the
	 *            unique key so that we could identify between value objects.
	 * @param seqAttribute
	 *            The attribute in which the sequence information is stored in
	 *            the value object.
	 * 
	 * @param extention
	 *            The attribute which gives a extention name to bind keyInfo
	 * 			  and seqAttribute with the session.
	 * 
	 */
	public void registerObjects(List busObjList, String keyInfo,
			String seqAttribute,String extention) {
		if (busObjList != null && busObjList.size() > 0 && null != keyInfo
				&& !"".equals(keyInfo) && null != seqAttribute
				&& !"".equals(seqAttribute)) {
			setAttributeToSession(KEY_INFO_ATTR_NAME+extention, keyInfo);
			setAttributeToSession(SEQ_ATTR_ATTR_NAME+extention,seqAttribute);
			
			manageExistingState(busObjList,extention);
		}
	}
	
	private void setAttributeToSession(String key, Object value){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        .getExternalContext().getSession(true);
		if(null != session){
			session.setAttribute(key,value);
		}
	}
	
	private Object getAttributeFromSession(String key){
		Object value = null;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        .getExternalContext().getSession(true);
		if(null != session){
			value = session.getAttribute(key);
		}
		return value;
	}

	/**
	 * 
	 * @param busObjList
	 * @param keyInfo
	 * @param seqAttribute
	 */
	private void manageExistingState(List busObjList) {
		String keyValue = "";
		String prevKeySeqInfo = "";
		int seqValue = 0;
		Class clazz;
		Object individualVO = null;
		Iterator busObjListIterator = busObjList.iterator();
		while (busObjListIterator.hasNext()) {
			individualVO = busObjListIterator.next();
			clazz = individualVO.getClass();
			seqValue = getSequenceNumber(individualVO);
			keyValue = getKeyValues(individualVO);
			if (!"".equals(prevKeySeqInfo)) {
				prevKeySeqInfo = prevKeySeqInfo + KEY_SEQ_SEPERATOR;
			}
			prevKeySeqInfo = prevKeySeqInfo + keyValue + SEQ_SEPERATOR
					+ seqValue;
		}
		setAttributeToSession(PREV_KEY_SEQ_INFO_ATTR_NAME,prevKeySeqInfo);
	}
	
	/**
	 * 
	 * @param busObjList
	 * @param keyInfo
	 * @param seqAttribute
	 */
	private void manageExistingState(List busObjList,String extention) {
		String keyValue = "";
		String prevKeySeqInfo = "";
		int seqValue = 0;
		Class clazz;
		Object individualVO = null;
		Iterator busObjListIterator = busObjList.iterator();
		while (busObjListIterator.hasNext()) {
			individualVO = busObjListIterator.next();
			clazz = individualVO.getClass();
			seqValue = getSequenceNumber(individualVO,extention);
			keyValue = getKeyValues(individualVO,extention);
			if (!"".equals(prevKeySeqInfo)) {
				prevKeySeqInfo = prevKeySeqInfo + KEY_SEQ_SEPERATOR;
			}
			prevKeySeqInfo = prevKeySeqInfo + keyValue + SEQ_SEPERATOR
					+ seqValue;
		}
		setAttributeToSession(PREV_KEY_SEQ_INFO_ATTR_NAME+extention,prevKeySeqInfo);
	}

	/**
	 * 
	 * @param individualVO
	 * @return
	 */
	private int getSequenceNumber(Object individualVO) {
		int seqValue = 0;
		try {
			String seqAttribute = (String)getAttributeFromSession(SEQ_ATTR_ATTR_NAME);
			Object returnedObject = ReflectionUtil
					.getValueOfBusinessAttributeByReflection(individualVO,
							seqAttribute);

			if (returnedObject != null) {
				seqValue = Integer.parseInt(returnedObject.toString());
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			Logger.logError(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			Logger.logError(e);
		} catch (WPDException e) {
			// TODO Auto-generated catch block
			Logger.logError(e);
		}
		return seqValue;
	}
	
	/**
	 * 
	 * @param individualVO
	 * @return
	 */
	private int getSequenceNumber(Object individualVO,String extention) {
		int seqValue = 0;
		try {
			String seqAttribute = (String)getAttributeFromSession(SEQ_ATTR_ATTR_NAME+extention);
			Object returnedObject = ReflectionUtil
					.getValueOfBusinessAttributeByReflection(individualVO,
							seqAttribute);

			if (returnedObject != null) {
				seqValue = Integer.parseInt(returnedObject.toString());
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			Logger.logError(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			Logger.logError(e);
		} catch (WPDException e) {
			// TODO Auto-generated catch block
			Logger.logError(e);
		}
		return seqValue;
	}

	/**
	 * 
	 * @param individualVO
	 * @return
	 */
	private String getKeyValues(Object individualVO) {
		String keyAttribute = "";
		String keyAttributeValue = "";
		String keyValue = "";

		Object returnedObject = null;
		String keyInfo = (String)getAttributeFromSession(KEY_INFO_ATTR_NAME);
		StringTokenizer keyInfoTokenizer = new StringTokenizer(keyInfo,
				KEY_ATTRIBUTE_SEPERATOR);
		while (keyInfoTokenizer.hasMoreTokens()) {
			keyAttribute = keyInfoTokenizer.nextToken();
			try {
				returnedObject = ReflectionUtil
						.getValueOfBusinessAttributeByReflection(individualVO,
								keyAttribute);
				if (null != returnedObject) {
					keyAttributeValue = "" + returnedObject;
				}

				if (!"".equals(keyValue)) {
					keyValue = keyValue + KEY_ATTRIBUTE_SEPERATOR;
				}
				keyValue = keyValue + keyAttributeValue;
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			} catch (WPDException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			}
		}
		return keyValue;
	}

	/**
	 * 
	 * @param individualVO
	 * @return
	 */
	private String getKeyValues(Object individualVO,String extention) {
		String keyAttribute = "";
		String keyAttributeValue = "";
		String keyValue = "";

		Object returnedObject = null;
		String keyInfo = (String)getAttributeFromSession(KEY_INFO_ATTR_NAME+extention);
		StringTokenizer keyInfoTokenizer = new StringTokenizer(keyInfo,
				KEY_ATTRIBUTE_SEPERATOR);
		while (keyInfoTokenizer.hasMoreTokens()) {
			keyAttribute = keyInfoTokenizer.nextToken();
			try {
				returnedObject = ReflectionUtil
						.getValueOfBusinessAttributeByReflection(individualVO,
								keyAttribute);
				if (null != returnedObject) {
					keyAttributeValue = "" + returnedObject;
				}

				if (!"".equals(keyValue)) {
					keyValue = keyValue + KEY_ATTRIBUTE_SEPERATOR;
				}
				keyValue = keyValue + keyAttributeValue;
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			} catch (WPDException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			}
		}
		return keyValue;
	}
	/**
	 * 
	 * @param newObjList
	 * @return
	 */
	public List reOrderObjects(List newObjList) {
		List sequensableList = generateSequensables(newObjList);
		reorderList(sequensableList);
		return sortObjects(newObjList, sequensableList);
	}
	
	/**
	 * 
	 * @param newObjList
	 * @return
	 */
	public List reOrderFilterObjects(List newObjList) {
		List sequensableList = generateSequensables(newObjList);
		reorderList(sequensableList);
		return sortFilterObjects(newObjList, sequensableList);
	}
	
	/**
	 * 
	 * @param newObjList
	 * @return
	 */
	public List reOrderObjects(List newObjList,String extention) {
		List sequensableList = generateSequensables(newObjList,extention);
		reorderList(sequensableList);
		return sortObjects(newObjList, sequensableList,extention);
	}

	/**
	 * @param newObjList
	 * @param sequensableList
	 * @return
	 */
	private List sortObjects(List newObjList, List sequensableList) {
		int count = 1;
		List sortedObjList = null;
		if (null != newObjList && null != sequensableList
				&& newObjList.size() > 0 && sequensableList.size() > 0) {
			Object individualVO = null;
			Sequensable sequensable = null;
			sortedObjList = new ArrayList();
			Iterator seqIterator = sequensableList.iterator();

			try {
				String seqAttribute = (String) getAttributeFromSession(SEQ_ATTR_ATTR_NAME);
				while (seqIterator.hasNext()) {
					sequensable = (Sequensable) seqIterator.next();
					individualVO = getVOWithKeyFromList(sequensable.getKey(),
							newObjList);
					ReflectionUtil.setValueOfBusinessAttributeByReflection(
							individualVO, seqAttribute,
							new Class[] { Integer.TYPE },
							new Object[] { new Integer(count) });
					count++;
					sortedObjList.add(individualVO);
				}
			} catch (WPDException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			}
		}
		return sortedObjList;
	}
	
	private List sortFilterObjects(List newObjList, List sequensableList) {
		List sortedObjList = null;
		if (null != newObjList && null != sequensableList
				&& newObjList.size() > 0 && sequensableList.size() > 0) {
			Object individualVO = null;
			Sequensable sequensable = null;
			sortedObjList = new ArrayList();
			Iterator seqIterator = sequensableList.iterator();

			try {
				String seqAttribute = (String) getAttributeFromSession(SEQ_ATTR_ATTR_NAME);
				while (seqIterator.hasNext()) {
					sequensable = (Sequensable) seqIterator.next();
					individualVO = getVOWithKeyFromList(sequensable.getKey(),
							newObjList);
					ReflectionUtil.setValueOfBusinessAttributeByReflection(
							individualVO, seqAttribute,
							new Class[] { Integer.TYPE },
							new Object[] { new Integer(sequensable
									.getNewSequenceNumber()) });
					sortedObjList.add(individualVO);
				}
			} catch (WPDException e) {
				Logger.logError(e);
			}
		}
		return sortedObjList;
	}
	/**
	 * @param newObjList
	 * @param sequensableList
	 * @return
	 */
	private List sortObjects(List newObjList, List sequensableList,String extention) {
		int count = 1;
		List sortedObjList = null;
		if (null != newObjList && null != sequensableList
				&& newObjList.size() > 0 && sequensableList.size() > 0) {
			Object individualVO = null;
			Sequensable sequensable = null;
			sortedObjList = new ArrayList();
			Iterator seqIterator = sequensableList.iterator();

			try {
				String seqAttribute = (String) getAttributeFromSession(SEQ_ATTR_ATTR_NAME+extention);
				while (seqIterator.hasNext()) {
					sequensable = (Sequensable) seqIterator.next();
					individualVO = getVOWithKeyFromList(sequensable.getKey(),
							newObjList,extention);
					ReflectionUtil.setValueOfBusinessAttributeByReflection(
							individualVO, seqAttribute,
							new Class[] { Integer.TYPE },
							new Object[] { new Integer(count) });
					count++;
					sortedObjList.add(individualVO);
				}
			} catch (WPDException e) {
				// TODO Auto-generated catch block
				Logger.logError(e);
			}
		}
		return sortedObjList;
	}

	/**
	 * 
	 * @param key
	 * @param objList
	 * @return
	 */
	private Object getVOWithKeyFromList(String key, List objList) {
		Object individualVO = null;
		if (null != objList && objList.size() > 0 && null != key
				&& !"".equals(key)) {
			Iterator objListIterator = objList.iterator();
			while (objListIterator.hasNext()) {
				individualVO = objListIterator.next();
				if (key.equals(getKeyValues(individualVO))) {
					break;
				}
			}
		}
		return individualVO;
	}
	
	/**
	 * 
	 * @param key
	 * @param objList
	 * @return
	 */
	private Object getVOWithKeyFromList(String key, List objList,String extention) {
		Object individualVO = null;
		if (null != objList && objList.size() > 0 && null != key
				&& !"".equals(key)) {
			Iterator objListIterator = objList.iterator();
			while (objListIterator.hasNext()) {
				individualVO = objListIterator.next();
				if (key.equals(getKeyValues(individualVO,extention))) {
					break;
				}
			}
		}
		return individualVO;
	}

	/**
	 * 
	 * @return
	 */
	private Map getKeySequenceAsMap() {

		Map keySeqMap = new HashMap();
		String keySeqPair = "";
		String key = "";
		String sequence = "";
		StringTokenizer keySeqTokenizer = null;
		String prevKeySeqInfo = (String)getAttributeFromSession(PREV_KEY_SEQ_INFO_ATTR_NAME);
		StringTokenizer keySeqPairTokenizer = new StringTokenizer(
				prevKeySeqInfo, KEY_SEQ_SEPERATOR);
		while (keySeqPairTokenizer.hasMoreTokens()) {
			keySeqPair = keySeqPairTokenizer.nextToken();
			keySeqTokenizer = new StringTokenizer(keySeqPair, SEQ_SEPERATOR);
			if (keySeqTokenizer.hasMoreTokens()) {
				key = keySeqTokenizer.nextToken();
			}
			if (keySeqTokenizer.hasMoreTokens()) {
				sequence = keySeqTokenizer.nextToken();
			}
			keySeqMap.put(key, sequence);
		}

		return keySeqMap;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private Map getKeySequenceAsMap(String extention) {

		Map keySeqMap = new HashMap();
		String keySeqPair = "";
		String key = "";
		String sequence = "";
		StringTokenizer keySeqTokenizer = null;
		String prevKeySeqInfo = (String)getAttributeFromSession(PREV_KEY_SEQ_INFO_ATTR_NAME+extention);
		StringTokenizer keySeqPairTokenizer = new StringTokenizer(
				prevKeySeqInfo, KEY_SEQ_SEPERATOR);
		while (keySeqPairTokenizer.hasMoreTokens()) {
			keySeqPair = keySeqPairTokenizer.nextToken();
			keySeqTokenizer = new StringTokenizer(keySeqPair, SEQ_SEPERATOR);
			if (keySeqTokenizer.hasMoreTokens()) {
				key = keySeqTokenizer.nextToken();
			}
			if (keySeqTokenizer.hasMoreTokens()) {
				sequence = keySeqTokenizer.nextToken();
			}
			keySeqMap.put(key, sequence);
		}

		return keySeqMap;
	}

	/**
	 * 
	 * @param newObjList
	 * @return
	 */
	private List generateSequensables(List newObjList) {
		List sequensableList = null;
		if (null != newObjList && newObjList.size()>0) {
			int seqSize = newObjList.size();
			Object individualVO = null;
			int newSeqNumber;
			String newKey = "";
			Sequensable sequensable = null;
			sequensableList = new ArrayList();
			Map oldKeySequenceMap = getKeySequenceAsMap();
			Iterator newObjIterator = newObjList.iterator();
			while (newObjIterator.hasNext()) {
				individualVO = newObjIterator.next();
				newSeqNumber = getSequenceNumber(individualVO);
				newKey = getKeyValues(individualVO);
				sequensable = new Sequensable();
				int oldVal = Integer.parseInt((String) oldKeySequenceMap.get(newKey));
				sequensable.setOldSequenceNumber(oldVal);
				if(newSeqNumber > seqSize || newSeqNumber < 1){
					newSeqNumber = oldVal;
				}
				sequensable.setNewSequenceNumber(newSeqNumber);
				sequensable.setKey(newKey);
				sequensableList.add(sequensable);
			}
		}
		return sequensableList;
	}
	
	/**
	 * 
	 * @param newObjList
	 * @return
	 */
	private List generateSequensables(List newObjList,String extention) {
		List sequensableList = null;
		if (null != newObjList && newObjList.size()>0) {
			int seqSize = newObjList.size();
			Object individualVO = null;
			int newSeqNumber;
			String newKey = "";
			Sequensable sequensable = null;
			sequensableList = new ArrayList();
			Map oldKeySequenceMap = getKeySequenceAsMap(extention);
			Iterator newObjIterator = newObjList.iterator();
			while (newObjIterator.hasNext()) {
				individualVO = newObjIterator.next();
				newSeqNumber = getSequenceNumber(individualVO,extention);
				newKey = getKeyValues(individualVO,extention);
				sequensable = new Sequensable();
				int oldVal = Integer.parseInt((String) oldKeySequenceMap.get(newKey));
				sequensable.setOldSequenceNumber(oldVal);
				if(newSeqNumber > seqSize || newSeqNumber < 1){
					newSeqNumber = oldVal;
				}
				sequensable.setNewSequenceNumber(newSeqNumber);
				sequensable.setKey(newKey);
				sequensableList.add(sequensable);
			}
		}
		return sequensableList;
	}


	/**
	 * 
	 * @param sequensableList
	 */
	public void reorderList(List sequensableList) {
		if (null != sequensableList && sequensableList.size()>0) {
			int seqSize = sequensableList.size();
			Sequensable sequeneArray [] = new Sequensable[seqSize];
			List seqSubList = new ArrayList();
			List changedVOs = new ArrayList();
			Sequensable sequensable = null;
			Iterator iterator = sequensableList.iterator();
			int newSeqNumber = 0;
			while (iterator.hasNext()) {
				sequensable = (Sequensable) iterator.next();
				newSeqNumber = sequensable.getNewSequenceNumber();
				if (newSeqNumber != sequensable.getOldSequenceNumber()) {
					if (changedVOs.contains(new Integer(newSeqNumber))) {
						sequensable.setNewSequenceNumber(sequensable
								.getOldSequenceNumber());
						seqSubList.add(sequensable);
					} else {
						changedVOs.add(new Integer(newSeqNumber));
						if (newSeqNumber > sequensable.getOldSequenceNumber()) {
							sequensable.setMoveDown(true);
						} else {
							sequensable.setMoveUp(true);
						}	
						sequeneArray[newSeqNumber-1] = sequensable;
					}
				}else{
					seqSubList.add(sequensable);
				}
			}
			Collections.sort(seqSubList);
			Iterator it = seqSubList.iterator();
			for(int i=0;i<seqSize;i++){
				sequensableList.set(i,sequeneArray[i]==null?it.next():sequeneArray[i]);
			}	
		}
	}
}