/*
 * Created on Apr 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.framework.custom;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Link {

	private String link;
	private String src="../../images/select.gif";
	private String alt="Select";
	
	
	
	/**
	 * @return Returns the link.
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * @return Returns the src.
	 */
	public String getSrc() {
		return src;
	}
	/**
	 * @param src The src to set.
	 */
	public void setSrc(String src) {
		this.src = src;
	}
	/**
	 * @return Returns the alt.
	 */
	public String getAlt() {
		return alt;
	}
	/**
	 * @param alt The alt to set.
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}
}
