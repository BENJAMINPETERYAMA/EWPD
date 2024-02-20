/*
 * WPDStringUtil.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.util;

import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.web.refdata.ReferenceDataBackingBean;

/**
 * Utilty class for eWPD Application
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class WPDStringUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			WebConstants.DATE_FORMAT_STRING);

	public static String getAppendedString(List list, String sperator) {
		StringBuffer buffer = new StringBuffer();

		if(list != null) {
	        for(int i=0; i<list.size() ; i++) {
	        	buffer.append((String)list.get(i)).append("~");
	        }
		}
		
        if(buffer.length() > 0)
        	buffer.setLength(buffer.length() - 1);
        return buffer.toString();
	}
	
	public static List getSplittedValues(String input, String seperator) {
        List resultList = new ArrayList();
        
        if(input != null && !"".equals(input.trim())) {
    		input = input.trim().toUpperCase();

            if("\n".equals(seperator)) {
            	input.replaceAll("\r\n", "\n");
            	input.replaceAll("\r", "\n");
            }

            String [] splittedValues = input.split(seperator);
            
            for (int i = 0; i < splittedValues.length; i++) {
    			String value = splittedValues[i];
    			if(value != null && !"".equals(value.trim())) {
    				resultList.add(value.trim());
    			}
    		}
        }
        
        List uniqueList = new ArrayList();
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
			String val = (String) iterator.next();
			if(!uniqueList.contains(val)) {
				uniqueList.add(val);
			}
		}
        
        return uniqueList;
	}
	
	/**
	 * checks the string represents a valid date in the format MM/dd/yyyy.
	 * returns true if valid false otherwise.
	 * 
	 * @param dateString String to be checked.
	 * @return boolean true if dateString is valid.
	 */
	public static boolean isValidDate(String dateString) {

		if (dateString == null || dateString.trim().equals(""))
			return true;

		dateString = dateString.trim();
		if (!dateString.matches(WebConstants.REGEX_DATE_FORMAT_STRING)) {
			return false;
		}
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateString);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String getCodeFromTildaString(String hyphenString) {
		if (hyphenString == null || hyphenString.trim().equals(""))
			return null;
		String[] array = hyphenString.split("~");
		return array[1].trim();
	}

	/**
	 * Converts the date to String in MM/dd/yyyy format.
	 * 
	 * @param date
	 * @return String
	 */
	public static String convertDateToString(Date date) {
		String dateString = null;
		if (null != date) {
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			dateString = df.format(date);
		}
		return dateString;
	}

	/**
	 * Convert dateString to a Date object. If dateString is not valid function
	 * returns null.
	 * 
	 * @param dateString String to be converted.
	 * @return Date Date object corresponding to dateString.
	 */
	public static Date getDateFromString(String dateString) {
		Date date = null;
		if (dateString == null || dateString.trim().equals("")) {
			return null;
		}
		dateString = dateString.trim();
		if (isValidDate(dateString)) {
			try {
				dateFormat.setLenient(false);
				date = dateFormat.parse(dateString);
			} catch (ParseException e) {
				date = null;
			}
		}
		return date;
	}

	public static List convertDomainItemsToList(List domainItems) {
		List codeList = new ArrayList();
		String code;
		DomainItem domainItem;
		Object obj;

		if (domainItems == null || domainItems.size() == 0)
			return codeList;

		for (Iterator iter = domainItems.iterator(); iter.hasNext();) {
			obj = iter.next();
			if (!(obj instanceof DomainItem))
				throw new IllegalArgumentException(
						"Expected DomainItem object... List contains Illegal Objects..");
			domainItem = (DomainItem) obj;
			codeList.add(domainItem.getItemId());
		}
		return codeList;
	}

	/**
	 * Returns String corresponding to date.
	 * 
	 * @param date date to be converted.
	 * @return String String corresponding to date.
	 */
	public static String getStringDate(Date date) {
		String dateString = null;
		if (null != date) {
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			dateString = df.format(date);
		}
		return dateString;
	}

	/**
	 * Returns String corresponding to date.
	 * 
	 * @param date date to be converted.
	 * @return String String corresponding to date.
	 */
	public static String getStringDateFormat(Date date) {
		String dateString = null;
		if (null != date) {
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			dateString = df.format(date);
		}
		return dateString;
	}

	public static String getTildaString(List domainItems) {

		if (domainItems == null)
			return "";

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < domainItems.size(); i++) {
			DomainItem element = (DomainItem) domainItems.get(i);
			if (i != 0) {
				buffer.append("~");
			}
			buffer.append(element.getItemDesc());
			buffer.append("~" + element.getItemId());
		}
		return buffer.toString();
	}

	public static String getTildaStringFromList(List list) {
		if (null == list || list.isEmpty()) {
			throw new IllegalArgumentException(
					"List is Null or contain no element");
		}
		StringBuffer buffer = new StringBuffer();
		for (Iterator listItr = list.iterator(); listItr.hasNext();) {
			buffer.append(listItr.next().toString());
			buffer.append("~");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	/**
	 * Returns List corresponding to the type we pass
	 * 
	 * @param tildaString String to be converted to list.
	 * @param attrCount int the count of repeating tidas.
	 * @param attrPosition int the count of position from which we need the
	 *        list.
	 * @param type int the type of list it returns.
	 * @return List List of the type specified.
	 */
	public static List getListFromTildaString(String tildaString,
			int attrCount, int attrPosition, int type) {
		List list = new ArrayList();
		if (tildaString == null || "".equals(tildaString.trim()))
			return list;

		String[] arrayTilda = tildaString.split("~");

		int count = arrayTilda.length;

		if (count > 0) {
			if (count % attrCount > 0) {
				throw new IllegalArgumentException(
						"In correct pattern found in the given string "
								+ tildaString);
			}

			if (attrPosition > attrCount || attrPosition < 0) {
				throw new IllegalArgumentException(
						"In correct pattern needed from the the given string "
								+ tildaString);

			}
			for (int i = 0; i < count; i += attrCount) {

				if (type == 1) {
					// type= integer
					list.add(new Integer(arrayTilda[i + attrPosition - 1]));
				} else if (type == 2) {
					//type = String
					// replace '~'
					String correctValue = new String(arrayTilda[i
							+ attrPosition - 1]);
					correctValue.replaceAll("/w0p8d", "~");
					list.add(correctValue);
				} else if (type == 3) {
					//type = Float
					list.add(new Float(arrayTilda[i + attrPosition - 1]));
				}

			}

		}
		return list;
	}

	/**
	 * Returns List corresponding to the type we pass
	 * 
	 * @param tildaString String to be converted to list.
	 * @param attrCount int the count of repeating tidas.
	 * @param attrPosition int the count of position from which we need the
	 *        list.
	 * @param type int the type of list it returns.
	 * @return List List of the type specified.
	 */
	public static String getStringFromTildaString(String tildaString,
			int attrCount, int attrPosition, int type) {
		List list = new ArrayList();
		if (tildaString == null || "".equals(tildaString.trim()))
			return "";

		StringBuffer stringID = new StringBuffer();
		String[] arrayTilda = tildaString.split("~");

		int count = arrayTilda.length;

		if (count > 0) {
			if (count % attrCount > 0) {
				throw new IllegalArgumentException(
						"In correct pattern found in the given string "
								+ tildaString);
			}

			if (attrPosition > attrCount || attrPosition < 0) {
				throw new IllegalArgumentException(
						"In correct pattern needed from the the given string "
								+ tildaString);

			}
			for (int i = 0; i < count; i += attrCount) {

				if (type == 1) {
					// type= integer
					list.add(new Integer(arrayTilda[i + attrPosition - 1]));
				} else if (type == 2) {
					//type = String
					// replace '~'
					String correctValue = new String(arrayTilda[i
							+ attrPosition - 1]);
					correctValue.replaceAll("/w0p8d", "~");
					stringID.append(correctValue).append('~');

				} else if (type == 3) {
					//type = Float
					list.add(new Float(arrayTilda[i + attrPosition - 1]));
				}

			}

		}
		stringID.deleteCharAt(stringID.length() - 1);
		return stringID.toString();
	}

	/**
	 * Returns List corresponding to the type we pass
	 * 
	 * @param tildaString String to be converted to list.
	 * @param attrCount int the count of repeating tidas.
	 * @param attrPosition int the count of position from which we need the
	 *        list.
	 * @param type int the type of list it returns.
	 * @return List List of the type specified.
	 */
	public static List getListFromTildaString(String tildaString,
			int attrCount, int attrPosition, int type, String separator) {
		List list = new ArrayList();
		if (tildaString == null || "".equals(tildaString.trim()))
			return list;

		String[] arrayTilda = tildaString.split(separator);

		int count = arrayTilda.length;

		if (count > 0) {
			if (count % attrCount > 0) {
				throw new IllegalArgumentException(
						"In correct pattern found in the given string "
								+ tildaString);
			}

			if (attrPosition > attrCount || attrPosition < 0) {
				throw new IllegalArgumentException(
						"In correct pattern needed from the the given string "
								+ tildaString);

			}
			for (int i = 0; i < count; i += attrCount) {

				if (type == 1) {
					// type= integer
					list.add(new Integer(arrayTilda[i + attrPosition - 1]));
				} else if (type == 2) {
					//type = String
					// replace '~'
					String correctValue = new String(arrayTilda[i
							+ attrPosition - 1]);
					correctValue.replaceAll("/w0p8d", "~");
					list.add(correctValue);
				} else if (type == 3) {
					//type = Float
					list.add(new Float(arrayTilda[i + attrPosition - 1]));
				}

			}

		}
		return list;
	}

	/**
	 * Returns List corresponding to the type we pass
	 * 
	 * @param tildaString String to be converted to list.
	 * @param attrCount int the count of repeating tidas.
	 * @param attrPosition int the count of position from which we need the
	 *        list.
	 * @param type int the type of list it returns.
	 * @return List List of the type specified.
	 */
	public static List getListFromCommaString(String tildaString,
			int attrCount, int attrPosition, int type) {
		List list = new ArrayList();
		if (tildaString == null || "".equals(tildaString.trim()))
			return list;

		String[] arrayTilda = tildaString.split(",");

		int count = arrayTilda.length;

		if (count > 0) {
			if (count % attrCount > 0) {
				throw new IllegalArgumentException(
						"In correct pattern found in the given string "
								+ tildaString);
			}

			if (attrPosition > attrCount || attrPosition < 0) {
				throw new IllegalArgumentException(
						"In correct pattern needed from the the given string "
								+ tildaString);

			}
			for (int i = 0; i < count; i += attrCount) {

				if (type == 1) {
					// type= integer
					list.add(new Integer(arrayTilda[i + attrPosition - 1]));
				} else if (type == 2) {
					//type = String
					// replace '~'
					String correctValue = new String(arrayTilda[i
							+ attrPosition - 1]);
					correctValue.replaceAll("/w0p8d", "~");
					list.add(correctValue);
				} else if (type == 3) {
					//type = Float
					list.add(new Float(arrayTilda[i + attrPosition - 1]));
				}

			}

		}
		return list;
	}

	/** List order not maintained **/

	public static void removeDuplicate(List list) {
		java.util.HashSet hashSet = new java.util.HashSet(list);
		list.clear();
		list.addAll(hashSet);
	}

	/** List order maintained **/

	public static void removeDuplicateWithOrder(List list) {
		java.util.Set set = new java.util.HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			java.lang.Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
	}

	// For Single value select fields
	public static String getCodeFromTildaString(String tildaString,
			int attrCount, int attrPosition, int type) {
		if (tildaString == null || "".equals(tildaString.trim()))
			return "";
		String[] arrayTilda = tildaString.split("~");
		String code = new String(arrayTilda[attrPosition - 1]);
		return code;
	}

	public static Date getDefaultEffectiveDate() {
		GregorianCalendar newGregorianCalendar = new GregorianCalendar(1900, 0,
				1);
		Date currDate = newGregorianCalendar.getTime();
		return currDate;
	}

	/**
	 * Function to process the text entered for notes
	 * @param inputText
	 * @return
	 */
	public static String processNoteText(String inputText) {
		int MAX_LENGTH = 74;
		final String TAB = "\t";
		final String SPACES = "    ";
		final String NEW_LINE_CHAR = "\n";

		if (inputText == null)
			return null;
		if ("".equals(inputText.trim()))
			return "";

		inputText = inputText.replaceAll(TAB, SPACES);
		inputText = inputText.replaceAll("\r\n", NEW_LINE_CHAR);
		inputText = inputText.replaceAll("\r", NEW_LINE_CHAR);

		String[] inputLines = inputText.split(NEW_LINE_CHAR);

		String line = null;
		StringBuffer resultString = new StringBuffer();
		for (int i = 0; i < inputLines.length; i++) {
			line = inputLines[i];
			if (line.length() <= MAX_LENGTH) {
				resultString.append(line + NEW_LINE_CHAR);
			} else {
				splitLineTo75LengthLines(line, resultString);
			}
		}
		return resultString.toString();
	}

	private static void splitLineTo75LengthLines(String target,
			StringBuffer noteLines) {
		boolean firstLine = true;
		int MAX_LENGTH = 74;
		BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
		String str74 = "";
		String temp;
		String word = "";
		wordIterator.setText(target);
		int start = wordIterator.first();
		int end = wordIterator.next();
		if (target == null || "".equals(target.trim())) {
			noteLines.append("\n");
			return;
		}
		while (true) {
			if (str74.length() < MAX_LENGTH) {
				if (end == BreakIterator.DONE) {
					if (!"".equals(str74)) {
						noteLines.append(str74 + "\n");
						firstLine = false;
					}
					break;
				}
				word = target.substring(start, end);
				if ("".equals(str74) && !firstLine)
					temp = str74 + word.trim();
				else
					temp = str74 + word;
				start = end;
				end = wordIterator.next();

				if (temp.length() > MAX_LENGTH) {
					if (str74.length() > 0) {
						noteLines.append(str74 + "\n");
						firstLine = false;
					}
					str74 = word.trim();
				}
				if (temp.length() == MAX_LENGTH) {
					noteLines.append(temp + "\n");
					firstLine = false;
					str74 = "";
				}
				if (temp.length() < MAX_LENGTH) {
					str74 = temp;
				}
			} else if (str74.length() >= MAX_LENGTH) {
				noteLines.append(str74.substring(0, MAX_LENGTH) + "\n");
				firstLine = false;
				str74 = str74.substring(MAX_LENGTH);
			}
		}
	}

	/**
	 * This method is to get the dateTypeList from the Table.
	 * @return Returns the dataTypeList.
	 */
	public static List getUniverseDataTypeList() {
		// get the instance of the application
		Application application = FacesContext.getCurrentInstance()
				.getApplication();
		// get the instance of the ReferenceDataBackingBeanCommon
		ReferenceDataBackingBean referenceDataBackingBean = ((ReferenceDataBackingBean) application
				.createValueBinding("#{ReferenceDataBackingBeanCommon}")
				.getValue(FacesContext.getCurrentInstance()));
		// get the datatype list
		return referenceDataBackingBean.getDataTypeList();
	}

	/**
	 * This method is to the get the type of the DataType 
	 * for the corresponding DataTypeId.
	 * @param universeDataTypeList
	 * @param dataTypeId
	 */
	/*public static String getSysDatatype(List universeDataTypeList, int dataTypeId) {
	 String dataTypeType = null;
	 // iterate the list and get the type of the 
	 // datatype for the corresponding data type id
	 if(null != universeDataTypeList && !universeDataTypeList.isEmpty()){
	 Iterator iterator = universeDataTypeList.iterator();
	 while(iterator.hasNext()){
	 DataTypeLocateResult 
	 dataTypeLocateResult = (DataTypeLocateResult)iterator.next();
	 if(dataTypeLocateResult.getDataTypeId() == (dataTypeId)){
	 if(null != dataTypeLocateResult.getDataTypeDesc())
	 dataTypeType = dataTypeLocateResult.
	 getDataTypeDesc().trim().toUpperCase();
	 }
	 }            
	 }
	 return dataTypeType;
	 }*/

	/**
	 * This method is to the get the type of the DataType 
	 * for the corresponding DataTypeId.
	 * @param universeDataTypeList
	 * @param dataTypeId
	 */
	/*public static String getDataTypeName(List universeDataTypeList, int dataTypeId) {
	 String dataTypeName = null;
	 // iterate the list and get the type of the 
	 // datatype for the corresponding data type id
	 if(null != universeDataTypeList && !universeDataTypeList.isEmpty()){
	 Iterator iterator = universeDataTypeList.iterator();
	 while(iterator.hasNext()){
	 DataTypeLocateResult 
	 dataTypeLocateResult = (DataTypeLocateResult)iterator.next();
	 if(dataTypeLocateResult.getDataTypeId() == (dataTypeId)){
	 if(null != dataTypeLocateResult.getDataTypeName())
	 dataTypeName = dataTypeLocateResult.
	 getDataTypeName().trim().toUpperCase();
	 }
	 }            
	 }
	 return dataTypeName;
	 }*/

	/**
	 * This method is to the get the type of the DataType 
	 * for the corresponding DataTypeId.
	 * @param universeDataTypeList
	 * @param dataTypeId
	 */
	public static DataTypeLocateResult getDataTypeDetails(
			List universeDataTypeList, int dataTypeId) {
		DataTypeLocateResult dataTypeDetails = null;
		// iterate the list and get the type of the 
		// datatype for the corresponding data type id
		if (null != universeDataTypeList && !universeDataTypeList.isEmpty()) {
			Iterator iterator = universeDataTypeList.iterator();
			while (iterator.hasNext()) {
				DataTypeLocateResult dataTypeLocateResult = (DataTypeLocateResult) iterator
						.next();
				if (dataTypeLocateResult.getDataTypeId() == (dataTypeId)) {
					dataTypeDetails = dataTypeLocateResult;
				}
			}
		}
		return dataTypeDetails;
	}

	/**
	 * This method is to validate whether it is a boolean value.
	 * @param dataTypeName
	 * @param benefitValue
	 * @return
	 */
	public static boolean isValidBoolean(String value) {
		boolean isBooleanFlag = true;
		if ("yes".equalsIgnoreCase(value) || "no".equalsIgnoreCase(value)
				|| "y".equalsIgnoreCase(value) || "n".equalsIgnoreCase(value)) {
		} else {
			isBooleanFlag = false;
		}
		return isBooleanFlag;
	}

	/**
	 * This method is to validate whether the value is a Number.
	 * @param dataTypeName
	 * @param benefitValue
	 * @return
	 */
	public static boolean isNumber(String value) {
		boolean isNumber = true;
		byte[] bytes = value.getBytes();
		for (int p = 0; p < bytes.length; p++) {
			byte byteValue = bytes[p];
			if (!(byteValue > 47 && byteValue < 58)) {
				isNumber = false;
				break;
			}
		}
		return isNumber;
	}

	/**
	 * This method is to validate whether the value is within the limit of an integer.
	 * @param dataTypeName
	 * @param benefitValue
	 * @return
	 */
	public static boolean isMaxInteger(String value) {
		boolean isMaxInteger = false;
		if (null != value) {
			if (Double.parseDouble(value) > Integer.MAX_VALUE)
				isMaxInteger = true;
		}
		return isMaxInteger;
	}

	/**
	 * This method is to validate whether there are only two precisions in the decimal number.
	 * @param dataTypeName
	 * @param benefitValue
	 * @return
	 */
	public static boolean isValidPrecision(String value, int numberOfPrecisions) {
		if (value.charAt(0) == '.') {
			value = "0".concat(value);
		}
		boolean isValidPrecision = true;
		StringTokenizer tokenizer = null;
		String integerPart = null;
		String decimalPart = null;
		if (null != value) {
			tokenizer = new StringTokenizer(value, ".");
			if (tokenizer.countTokens() >= 2) {
				integerPart = tokenizer.nextToken();
				decimalPart = tokenizer.nextToken();
			} else
				integerPart = tokenizer.nextToken();
			if (null != decimalPart
					&& decimalPart.length() > numberOfPrecisions) {
				isValidPrecision = false;
			}
		}
		return isValidPrecision;
	}

	/**
	 * This method is to validate whether there are only two precisions in the decimal number.
	 * @param dataTypeName
	 * @param benefitValue
	 * @return
	 */
	public static String removeUnwantedZeroes(String value) {
		StringTokenizer tokenizer = null;
		String integerPart = null;
		String decimalPart = null;
		String correctValue = null;
		int leadingZeroCount = 0;
		if (null != value) {
			if (value.charAt(0) == '.')
				value = "0".concat(value);
			tokenizer = new StringTokenizer(value, ".");
			if (tokenizer.countTokens() >= 2) {
				integerPart = tokenizer.nextToken();
				decimalPart = tokenizer.nextToken();
			} else
				integerPart = tokenizer.nextToken();
			if (null != integerPart) {
				byte[] bytes = integerPart.getBytes();
				for (int p = 0; p < bytes.length; p++) {
					byte byteValue = bytes[p];
					if (byteValue == 48) {
						leadingZeroCount++;
					} else {
						break;
					}
				}
				integerPart = integerPart.substring(leadingZeroCount);
				if (integerPart.equals("")) {
					integerPart = "0";
				}
				if (null != decimalPart) {
					correctValue = integerPart + "." + decimalPart;
				} else {
					correctValue = integerPart;
				}
			}
		}

		return correctValue;
	}

	/**
	 * This method is to validate comparison between two numbers of same value with different precision.
	 * @param value
	 * @return
	 */
	public static String compareDecimal(String value) {

		String correctValue = null;
		double integerValue = Double.parseDouble(value);
		long roundValue = Math.round(integerValue);
		if (roundValue == integerValue) {
			correctValue = Long.toString(roundValue);
		} else
			correctValue = Double.toString(integerValue);
		return correctValue;
	}

	/**
	 * This method is to validate whether there are only two precisions in the decimal number.
	 * @param dataTypeName
	 * @param benefitValue
	 * @return
	 */
	public static boolean isGreaterThanHundred(String value) {
		boolean isGreaterThanHundred = true;
		if (null != value) {
			double doubleValue = Double.parseDouble(value);
			if (doubleValue > 100) {
				isGreaterThanHundred = false;
			}
		}
		return isGreaterThanHundred;
	}

	/**
	 * This method is to validate whether the value is a Decimal Number.
	 * @param dataTypeName
	 * @param benefitValue
	 * @return
	 */
	public static boolean isDecimalNumber(String value) {
		if (value.charAt(0) == '.') {
			value = "0".concat(value);
		}
		boolean isDecimalNumber = true;
		int decimalPointCount = 0;
		if ("0.".equals(value.trim())) {
			isDecimalNumber = false;
		} else {
			byte[] bytes = value.getBytes();
			for (int p = 0; p < bytes.length; p++) {
				byte byteValue = bytes[p];
				if (!((byteValue > 47 && byteValue < 58) || byteValue == 46)) {
					isDecimalNumber = false;
					break;
				}
				if (byteValue == 46) {
					decimalPointCount++;
				}
				if (decimalPointCount > 1) {
					isDecimalNumber = false;
					break;
				}
			}
		}
		return isDecimalNumber;
	}

	/**
	 * Method to iterate the domain items list and generation the list of itemIds.
	 * @param domainItems
	 * @return
	 */
	public static List getListOfDomainIds(List domainItems) {
		List domainIds = null;
		DomainItem element = null;

		if (null != domainItems) {
			for (int i = 0; i < domainItems.size(); i++) {
				element = (DomainItem) domainItems.get(i);
				if (null == domainIds)
					domainIds = new ArrayList();
				domainIds.add(element.getItemId());
			}
		}

		return domainIds;
	}

	/**
	 * Method to generate the comma seperated string of domain descriptions.
	 * @param domainItems
	 * @return
	 */
	public static String getCommaSeperatedString(List domainItems) {

		if (domainItems == null)
			return "";

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < domainItems.size(); i++) {
			DomainItem element = (DomainItem) domainItems.get(i);
			if (i != 0) {
				buffer.append(", ");
			}
			buffer.append(element.getItemId());
		}
		return buffer.toString();
	}

	/**
	 * method for getting the comma seperated values from  a list of string objects
	 * @param List
	 * @return String of comma seperated values
	 */
	public static String getCommaSeperatedValuesFromList(List list) {
		if (null == list || list.isEmpty()) {
			throw new IllegalArgumentException(
					"List is Null or contain no element");
		}
		StringBuffer buffer = new StringBuffer();
		for (Iterator listItr = list.iterator(); listItr.hasNext();) {
			buffer.append(listItr.next().toString());
			buffer.append(" , ");
		}
		buffer.deleteCharAt(buffer.lastIndexOf(","));
		return buffer.toString();
	}

	/*
	 * 
	 * @author US Technology Resources - www.ustri.com <br />
	 * @version $Id: $
	 */
	public static String escapeString(String mainString) {

		StringBuffer queryString = new StringBuffer();
		String queryPart = mainString;
		int index = 0;
		while (index < queryPart.length()) {
			char wildChar = queryPart.charAt(index++);
			switch (wildChar) {

			case '_':
				queryString.append("\\_");
				break;
			case '%':
				queryString.append("\\%");
				break;
			case '\\':
				queryString.append("\\\\");
				break;
			case '&':
				queryString.append("&");
				break;
			case '-':
				queryString.append("-");
				break;
			case '/':
				queryString.append("/");	
				break;
				
			default:
				queryString.append(String.valueOf(wildChar));
			}
		}
		return queryString.toString();
	}

	
	/**
	 * This method separates string with space.
	 * Inserts already if there is no space.
	 * @param mainString - Input string
	 * @param spacePos - recurring character size for inserting space. 
	 * @return
	 */
	public static String spaceSeparatedString(String mainString, int spacePos) {

		if (null != mainString && spacePos > 0) {

			StringBuffer str = new StringBuffer(mainString);
			int startIndex = 0;
			int pos = 0;
			int endIndex = spacePos;
			
			// loops every spacePos number of  characters
			while (endIndex < str.length()) {
				
				// Check if space already - from  startIndex to endIndex
				pos = str.substring(startIndex, endIndex + 1).indexOf(
						WebConstants.SPACE_STRING);

				if (pos == -1) { // No space exists.Insert a space
					
					str.insert(endIndex, WebConstants.SPACE_STRING);
					startIndex = endIndex + 1;
					endIndex = startIndex + spacePos;
					
				} else { // Space already exists.No need of extra space
					
					startIndex = startIndex + pos + 1;
					endIndex = startIndex + spacePos;
					
				}
			}
			mainString = str.toString();
    	}
    	return mainString;
    }   
    /**
	 * This method separates a comma separated string for a giveb string
	 * @param text
	 * @return
	 */
	public static String commaSeparatedString(String text){
		int noOfTokens;
		boolean flag = true;
		if(!StringUtil.isEmpty(text)){
			StringTokenizer tokenizer = new StringTokenizer(
					text, BusinessConstants.COMMA);
			 noOfTokens = tokenizer.countTokens();
			 if(noOfTokens > 1){
			 	 for (int index = 0; index < noOfTokens; index++) {
                    if (tokenizer.hasMoreTokens()) {
                        String str = tokenizer.nextToken();
                        if(flag == true){
                        	text = str.trim();                    		
                    		flag = false;//Flag is set false to restrict the entry for the second time
                    	}else{//Setting the term description value after the comma seperation
                    		text = text+BusinessConstants.SPACE_STRING+ str.trim();
                    	}
                    }
			 	 }   
			 }
		}
		return text;		
	}
	
	/**
	 * Method used for special character search for admin method modules
	 * @param Admin Method Desc
	 * @return
	 */
	public static String addDelimiterForAMDesc(String descText) {
		
		if(null!=descText && !"".equals(descText)){
	
//	   & _ - /
		int indexOfUnderScore = descText.indexOf('_');
		int indexOfHifen = descText.indexOf('-');
		int indexOfAmberSign = descText.indexOf('&');
		int indexOfSlash = descText.indexOf('/');
		
		
		// Will add the Delimiter to the description if it contains a special character.
		if(indexOfUnderScore != -1 ){
			String tempChar="";
		for(int i=0;i<descText.length();i++ ){			
			String tempChar1="";
			tempChar1 = descText.substring(i,i+1);
			if(tempChar1.equals("_"))tempChar1 = "\\_";
			tempChar = tempChar + tempChar1;
		 }	
		return tempChar;
		}
		
		if(indexOfHifen != -1 ){
			descText=descText.substring(0,indexOfHifen)+"-"+descText.substring(indexOfHifen+1,descText.length());
			return descText;
		}
		
		if(indexOfAmberSign != -1 ){
			descText=descText.substring(0,indexOfAmberSign)+"&"+descText.substring(indexOfAmberSign+1,descText.length());
			return descText;
		}
		
		if(indexOfSlash != -1 ){
			descText=descText.substring(0,indexOfSlash)+"/"+descText.substring(indexOfSlash+1,descText.length());
			return descText;
		}
		return descText;
	}
		return descText;
	}	
	
	/**
	 * method to check whether a string has value other than blank.
	 * @param s
	 * @return
	 */
	public static boolean hasText(String s){
		if(s != null && !"".equalsIgnoreCase(s.trim())){
			return true;
		}
		return false;
	}
	/**
	 * method converts an integer value to string
	 * @param int
	 * @return string
	 */
	public static String convertIntToString( int value){
		return Integer.toString(value);
	}
	
}