/*
 * QueryCreator.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.search.exception.SearchCriteriaValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: QueryCreator.java 37839 2007-11-05 05:00:54Z U14659 $
 */
public class QueryCreator {
	private final String COLUMN_NAME = "COLUMN_NAME";

	private final String AND = " AND ";

	private final String OR = " OR ";

	boolean signFlag = false;

	public String createQueryCondition(List parsedCriteriaStrings,
			String relation, String sign, String type, String name,
			String objType) throws SearchCriteriaValidationException {
		String queryPart = createQueryCondition(parsedCriteriaStrings, sign,
				type, name, objType);
		return queryPart + " " + relation.toUpperCase() + " ";
	}

	public List createQueryConditionAsList(List parsedCriteriaStrings,
			String relation, String sign, String type, String name,
			String objType) throws SearchCriteriaValidationException {
		String queryPart = createQueryCondition(parsedCriteriaStrings, sign,
				type, name, objType);
		List results = new ArrayList();
		results.add(queryPart);
		results.add(queryPart + " " + relation.toUpperCase() + " ");
		return results;
	}

	public String createQueryCondition(List parsedCriteriaStrings, String sign,
			String type, String name, String objType)
			throws SearchCriteriaValidationException {
		if (parsedCriteriaStrings == null || parsedCriteriaStrings.size() == 0)
			throw new SearchCriteriaValidationException(
					"The parsed criteria strings list is null or empty", null,
					null);
		StringBuffer queryCondition = new StringBuffer(" ( ");
		Iterator criteriaIterator = parsedCriteriaStrings.iterator();
		while (criteriaIterator.hasNext()) {
			queryCondition.append(getQueryString(criteriaIterator.next()
					.toString(), sign, type, name, objType));
		}
		queryCondition.append(" ) ");
		return queryCondition.toString();

	}

	public List createQueryConditionAsList(List parsedCriteriaStrings, String sign,
			String type, String name, String objType)
			throws SearchCriteriaValidationException {
		if (parsedCriteriaStrings == null || parsedCriteriaStrings.size() == 0)
			throw new SearchCriteriaValidationException(
					"The parsed criteria strings list is null or empty", null,
					null);
		List results = new ArrayList();
		results.add(" ( ");
		Iterator criteriaIterator = parsedCriteriaStrings.iterator();
		while (criteriaIterator.hasNext()) {
			results.add(getQueryString(criteriaIterator.next()
					.toString(), sign, type, name, objType));
		}
		results.add(" ) ");
		return results;

	}

	public String getQueryString(String queryString, String sign, String type,
			String name, String objType)
			throws SearchCriteriaValidationException {

		boolean signPresent = false;

		if (null != sign && !"".equalsIgnoreCase(sign.trim())) {
			signPresent = true;
		}
		if ("(".equals(queryString) || ")".equals(queryString)) {
			return queryString;
		} else if ("+".equals(queryString)) {
			return AND;
		} else if ("|".equals(queryString)) {
			return OR;
		} else if (",".equals(queryString)) {
			return AND;
		} else if (queryString.startsWith("'") && queryString.endsWith("'")) {
			if (signPresent) {
				// This can be an error conditions. Since selecting a sign
				// operator suggests that
				// The value enetered are exact match
			}
			if (type != null) {
				String tempQuery = queryString.substring(1, queryString
						.length() - 1);
				validateType(replaceBackSlash(tempQuery), type, name);
			}
			if ("Date".equalsIgnoreCase(type))
				return COLUMN_NAME + " = " + "to_date("
						+ replaceBackSlash(queryString) + ",'MM/DD/YYYY')";
			else {
				if ("String".equalsIgnoreCase(type)) {
					return COLUMN_NAME + " = " + "upper("
							+ replaceBackSlash(queryString) + ") ";
				} else {
					return COLUMN_NAME + " = " + replaceBackSlash(queryString);
				}
			}
		} else {
			if (signPresent) {
				if ("[]".equalsIgnoreCase(sign)) {
					String searchWords[] = getBetweenWords(queryString);
					if (!signFlag) {
						signFlag = true;
						validateType(replaceBackSlash(searchWords[0]), type,
								name);
						validateType(replaceBackSlash(searchWords[1]), type,
								name);
						if ("Date".equalsIgnoreCase(type)) {
							return COLUMN_NAME + " " + "BETWEEN"
									+ " to_date( '"
									+ replaceBackSlash(searchWords[0])
									+ "','MM/DD/YYYY')" + " AND " + "to_date('"
									+ replaceBackSlash(searchWords[1])
									+ "','MM/DD/YYYY')";
						} else {
							if ("String".equalsIgnoreCase(type)) {
								return COLUMN_NAME + " " + "BETWEEN"
										+ " upper('"
										+ replaceBackSlash(searchWords[0])
										+ "') " + " AND " + "upper('"
										+ replaceBackSlash(searchWords[1])
										+ "') ";
							} else {
								return COLUMN_NAME + " " + "BETWEEN" + " '"
										+ replaceBackSlash(searchWords[0])
										+ "'" + " AND " + "'"
										+ replaceBackSlash(searchWords[1])
										+ "'";
							}

						}
					} else {
						if ("String".equalsIgnoreCase(type)) {
							return " upper('"
									+ escapeWildCharacter(replaceBackSlash(queryString))
									+ "') escape '\\'";
						} else {
							return " '" + replaceBackSlash(queryString) + "'";
						}

					}
				}
				validateType(replaceBackSlash(queryString), type, name);
				if ("Date".equalsIgnoreCase(type))
					return COLUMN_NAME + " " + sign + " to_date( '"
							+ replaceBackSlash(queryString) + "','MM/DD/YYYY')";
				else {
					if ("String".equalsIgnoreCase(type)) {
						return COLUMN_NAME + " " + sign + " upper('"
								+ replaceBackSlash(queryString) + "')";
					} else {
						return COLUMN_NAME + " " + sign + " '"
								+ replaceBackSlash(queryString) + "'";
					}

				}

			}
			if (type != null)
				validateType(replaceBackSlash(queryString), type, name);
			if ("Date".equalsIgnoreCase(type))
				return COLUMN_NAME + " = " + " to_date( '"
						+ replaceBackSlash(queryString) + "','MM/DD/YYYY')";
			else {
				if ("String".equalsIgnoreCase(type)) {
					return COLUMN_NAME
							+ " LIKE upper('%"
							+ escapeWildCharacter(replaceBackSlash(queryString))
							+ "%') escape '\\' ";
				} else {
					return COLUMN_NAME
							+ " LIKE '%"
							+ escapeWildCharacter(replaceBackSlash(queryString))
							+ "%' escape '\\'";
				}
			}

		}
	}

	private String[] getBetweenWords(String queryPart) {
		String searchWords[] = new String[2];
		char[] searchChars = queryPart.toCharArray();
		for (int i = 0; i < searchChars.length; i++) {
			if (searchChars[i] == ',' && (i > 0 && searchChars[i - 1] != '\\')) {
				searchWords[0] = queryPart.substring(0, i).trim();
				searchWords[1] = queryPart.substring(i + 1).trim();
				break;
			}
		}
		return searchWords;
	}

	private String replaceBackSlash(String queryPart) {
		StringBuffer queryString = new StringBuffer();
		int index = 0;
		do {
			index = queryPart.indexOf("\\");
			if (index != -1) {
				queryString.append(queryPart.substring(0, index));
				char ch = queryPart.charAt(index + 1);
				switch (ch) {
				case '\'':
					queryString.append("''");
					break;
				default:
					queryString.append(ch);
				}
				queryPart = queryPart.substring(index + 2);
			}
		} while (index != -1);
		queryString.append(queryPart);
		return queryString.toString();
	}

	private String escapeWildCharacter(String queryPart) {
		StringBuffer queryString = new StringBuffer();
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
			default:
				queryString.append(String.valueOf(wildChar));
			}
		}
		return queryString.toString();
	}

	private boolean validateType(String criteria, String type, String name)
			throws SearchCriteriaValidationException {
		String errorString = "";
		if ("Integer".equalsIgnoreCase(type)) {
			errorString = "Number";
			if (StringUtil.isInteger(criteria)) {
				return true;
			}
		} else if ("Date".equalsIgnoreCase(type)) {
			errorString = "Date Format (MM/DD/YYYY)";
			if (StringUtil.isDate(criteria)) {
				return true;
			}
		} else if ("Long".equalsIgnoreCase(type)) {
			errorString = "Number";
			if (StringUtil.isLong(criteria)) {
				return true;
			}
		} else if ("Double".equalsIgnoreCase(type)) {
			errorString = "Number";
			if (StringUtil.isDouble(criteria)) {
				return true;
			}

		} else if ("Float".equalsIgnoreCase(type)) {
			errorString = "Number";
			if (StringUtil.isFloat(criteria)) {
				return true;
			}
		} else if ("String".equalsIgnoreCase(type)) {
			errorString = "String";
			return true;
		}

		List logParams = new ArrayList();
		logParams.add(criteria);
		String msg = "Invalid criteria ";
		logParams.add(msg);
		String[] userParams = new String[2];
		userParams[0] = name; // need to pass in the name too
		userParams[1] = errorString;
		throw new SearchCriteriaValidationException(msg, logParams, null,
				"advanced.search.criteria.type.error", userParams);
	}
}