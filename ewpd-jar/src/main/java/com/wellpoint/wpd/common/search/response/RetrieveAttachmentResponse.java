/*
 * Created on Jul 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.response;

import com.wellpoint.wpd.common.search.result.ObjectDetail;

/**
 * @author u12218
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveAttachmentResponse extends SearchResponse {
	
	private ObjectDetail detail;

	/**
	 * @return Returns the detail.
	 */
	public ObjectDetail getDetail() {
		return detail;
	}
	/**
	 * @param detail The detail to set.
	 */
	public void setDetail(ObjectDetail detail) {
		this.detail = detail;
	}
}
