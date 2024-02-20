/*
 * Created on May 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductNotesLocateCriteria extends LocateCriteria {
	 
		private String entityType;

	 	private int entityId;
	   
	 	private int action ;
		
		
		/**
		 * @return Returns the action.
		 */
		public int getAction() {
			return action;
		}
		/**
		 * @param action The action to set.
		 */
		public void setAction(int action) {
			this.action = action;
		}
		/**
		 * @return Returns the entityId.
		 */
		public int getEntityId() {
			return entityId;
		}
		/**
		 * @param entityId The entityId to set.
		 */
		public void setEntityId(int entityId) {
			this.entityId = entityId;
		}
		/**
		 * @return Returns the entityType.
		 */
		public String getEntityType() {
			return entityType;
		}
		/**
		 * @param entityType The entityType to set.
		 */
		public void setEntityType(String entityType) {
			this.entityType = entityType;
		}
}
