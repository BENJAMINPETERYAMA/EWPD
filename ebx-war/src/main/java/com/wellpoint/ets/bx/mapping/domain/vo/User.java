/*
 * Created on May 29, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.vo;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class User {

		private String createdUserName = "";
		private String lastUpdatedUserName = "";
		
		public User(){
			
			
		}
		/**
		 * @return Returns the createdUserName.
		 */
		public String getCreatedUserName() {
			return createdUserName;
		}
		/**
		 * @param createdUserName The createdUserName to set.
		 */
		public void setCreatedUserName(String createdUserName) {
			this.createdUserName = createdUserName;
		}
		/**
		 * @return Returns the lastUpdatedUserName.
		 */
		public String getLastUpdatedUserName() {
			return lastUpdatedUserName;
		}
		/**
		 * @param lastUpdatedUserName The lastUpdatedUserName to set.
		 */
		public void setLastUpdatedUserName(String lastUpdatedUserName) {
			this.lastUpdatedUserName = lastUpdatedUserName;
		}
}
