package com.wellpoint.ets.bx.mapping.web;

import com.wellpoint.ets.bx.mapping.application.security.Environment;

public class AppInfo {

	private String appVersion;
	
	private String buildNumber;
	
	private String environment = Environment.getCurrentEnvironment();
	
	private String svnRevision;
	
	private String buildDate;

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getSvnRevision() {
		if(Environment.isDevelopment()){
			return svnRevision;
		} else {
			return null;
		}
	}

	public void setSvnRevision(String svnRevision) {
		this.svnRevision = svnRevision;
	}

	public String getBuildDate() {
		if(Environment.isDevelopment()){
			return buildDate;
		} else {
			return null;
		}
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	
	
}
