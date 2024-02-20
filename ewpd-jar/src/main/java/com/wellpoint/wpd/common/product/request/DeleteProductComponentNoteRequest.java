/*
 * Created on May 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteProductComponentNoteRequest extends ProductRequest {
	
		private int benefitComponentId;
		private List noteList;
		private int action;
		private String noteId;
		private String benefitComponentNoteId;

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
		 * @return Returns the benefitComponentId.
		 */
		public int getBenefitComponentId() {
			return benefitComponentId;
		}
		/**
		 * @param benefitComponentId The benefitComponentId to set.
		 */
		public void setBenefitComponentId(int benefitComponentId) {
			this.benefitComponentId = benefitComponentId;
		}
		/**
		 * @return Returns the noteList.
		 */
		public List getNoteList() {
			return noteList;
		}
		/**
		 * @param noteList The noteList to set.
		 */
		public void setNoteList(List noteList) {
			this.noteList = noteList;
		}
	
		/**
		 * @return Returns the noteId.
		 */
		public String getNoteId() {
			return noteId;
		}
		/**
		 * @param noteId The noteId to set.
		 */
		public void setNoteId(String noteId) {
			this.noteId = noteId;
		}
		/**
		 * @return Returns the benefitComponentNoteId.
		 */
		public String getBenefitComponentNoteId() {
			return benefitComponentNoteId;
		}
		/**
		 * @param benefitComponentNoteId The benefitComponentNoteId to set.
		 */
		public void setBenefitComponentNoteId(String benefitComponentNoteId) {
			this.benefitComponentNoteId = benefitComponentNoteId;
		}
}
