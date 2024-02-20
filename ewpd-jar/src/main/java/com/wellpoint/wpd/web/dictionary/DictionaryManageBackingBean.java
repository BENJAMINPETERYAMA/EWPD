/*
 * DictionaryManageBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DictionaryManageBackingBean extends WPDBackingBean{
	
	private String wordToBeAdded = null;
	
	private String description = null;
	
	private List messagesList = null;
	
	private boolean requiredWord = false;
	
	public DictionaryManageBackingBean(){
		
	}
	/*
	 * Method to add the word to the user dictionary
	 */
	public String addWordToDictionary(){
		try{
			WPDUserDictionary wpdUserDictionary = new WPDUserDictionary();
			InformationalMessage message = null;
			ErrorMessage errorMessage = null;
			messagesList = new ArrayList();
			if(null != this.wordToBeAdded && !"".equals(this.wordToBeAdded))
				this.wordToBeAdded = this.wordToBeAdded.trim();
			if(null==this.wordToBeAdded || "".equals(this.wordToBeAdded)){
				requiredWord=true;
				errorMessage = new ErrorMessage("add.word.valid");
				messagesList.add(errorMessage);						
			}else if(this.wordToBeAdded.trim().indexOf(' ')>0){
				errorMessage = new ErrorMessage("add.only.word");
				messagesList.add(errorMessage);						
			}else{
				if(wpdUserDictionary.addWord(wordToBeAdded.trim(),this.getUser() )){
					message = new InformationalMessage("add.word.success");
					message.setParameters(new String[]{this.wordToBeAdded.trim()});
					this.wordToBeAdded = null;
					messagesList.add(message);
				}else{
					errorMessage = new ErrorMessage("word.already.found");
					errorMessage.setParameters(new String[]{this.wordToBeAdded.trim()});
					messagesList.add(errorMessage);			
				}
			}
			addAllMessagesToRequest(messagesList);
		}
		catch(Exception e){
			com.wellpoint.wpd.common.framework.logging.Logger.logError(e);
			messagesList = new ArrayList();
			ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG);
			messagesList.add(em);
			addAllMessagesToRequest(messagesList);
		}
		return "success";
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the wordToBeAdded.
	 */
	public String getWordToBeAdded() {
		return wordToBeAdded;
	}
	/**
	 * @param wordToBeAdded The wordToBeAdded to set.
	 */
	public void setWordToBeAdded(String wordToBeAdded) {
		this.wordToBeAdded = wordToBeAdded;
	}
	/**
	 * @return Returns the messagesList.
	 */
	public List getMessagesList() {
		return messagesList;
	}
	/**
	 * @param messagesList The messagesList to set.
	 */
	public void setMessagesList(List messagesList) {
		this.messagesList = messagesList;
	}
	/**
	 * @return Returns the requiredWord.
	 */
	public boolean isRequiredWord() {
		return requiredWord;
	}
	/**
	 * @param requiredWord The requiredWord to set.
	 */
	public void setRequiredWord(boolean requiredWord) {
		this.requiredWord = requiredWord;
	}
}
