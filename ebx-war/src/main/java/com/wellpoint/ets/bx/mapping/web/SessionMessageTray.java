package com.wellpoint.ets.bx.mapping.web;

import java.util.ArrayList;
import java.util.List;

public class SessionMessageTray {

	private List errorMessages = new ArrayList();
	
	private List informationMessages = new ArrayList();
	
	private List warningMessages = new ArrayList();
	
	
	public void addErrorMessage(String message) {
		errorMessages.add(message);
	}
	
	public void addWarnMessage(String message) {
		warningMessages.add(message);
	}
	
	public void addInfoMessage(String message) {
		informationMessages.add(message);
	}

	public void setErrorMessages(List errorMessages) {
		this.errorMessages = errorMessages;
	}

	public void setInformationMessages(List informationMessages) {
		this.informationMessages = informationMessages;
	}

	public void setWarningMessages(List warningMessages) {
		this.warningMessages = warningMessages;
	}

	public List getAndClearErrorMessages() {
		List list = new ArrayList();
		list.addAll(errorMessages);
		errorMessages.clear();
		return list;
	}

	public List getAndClearInformationMessages() {
		List list = new ArrayList();
		list.addAll(informationMessages);
		informationMessages.clear();
		return list;
	}

	public List getAndClearWarningMessages() {
		List list = new ArrayList();
		list.addAll(warningMessages);
		warningMessages.clear();
		return list;
	}
	
	 
}
