/*
 * Created on Jun 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveProductQuestionnairRequest  extends ProductRequest{
	
	 private int adminOptSysId;
		
	    
		 private int productId;
		 
		 private int productPrntSysId;
		 private int action;
		 
			private int selectedAnswerId;
			
			private int questionareListIndex;
			
			private List questionnareList;
			
			private  EntityProductAdministration entityProductAdministrationBO;
			
		 
		
		/**
		 * @return Returns the productPrntSysId.
		 */
		public int getProductPrntSysId() {
			return productPrntSysId;
		}
		/**
		 * @param productPrntSysId The productPrntSysId to set.
		 */
		public void setProductPrntSysId(int productPrntSysId) {
			this.productPrntSysId = productPrntSysId;
		}
			/**
			 * @return Returns the entityProductAdministrationBO.
			 */
			public EntityProductAdministration getEntityProductAdministrationBO() {
				return entityProductAdministrationBO;
			}
			/**
			 * @param entityProductAdministrationBO The entityProductAdministrationBO to set.
			 */
			public void setEntityProductAdministrationBO(
					EntityProductAdministration entityProductAdministrationBO) {
				this.entityProductAdministrationBO = entityProductAdministrationBO;
			}
			/**
			 * @return Returns the questionnareList.
			 */
			public List getQuestionnareList() {
				return questionnareList;
			}
			/**
			 * @param questionnareList The questionnareList to set.
			 */
			public void setQuestionnareList(List questionnareList) {
				this.questionnareList = questionnareList;
			}
			/**
			 * @return Returns the questionareListIndex.
			 */
			public int getQuestionareListIndex() {
				return questionareListIndex;
			}
			/**
			 * @param questionareListIndex The questionareListIndex to set.
			 */
			public void setQuestionareListIndex(int questionareListIndex) {
				this.questionareListIndex = questionareListIndex;
			}
			/**
			 * @return Returns the selectedAnswerId.
			 */
			public int getSelectedAnswerId() {
				return selectedAnswerId;
			}
			/**
			 * @param selectedAnswerId The selectedAnswerId to set.
			 */
			public void setSelectedAnswerId(int selectedAnswerId) {
				this.selectedAnswerId = selectedAnswerId;
			}
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
  * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
  * @throws ValidationException
  */
 public void validate() throws ValidationException {
     // TODO Auto-generated method stub
 }

	/**
	 * @return Returns the adminOptSysId.
	 */
	public int getAdminOptSysId() {
		return adminOptSysId;
	}
	/**
	 * @param adminOptSysId The adminOptSysId to set.
	 */
	public void setAdminOptSysId(int adminOptSysId) {
		this.adminOptSysId = adminOptSysId;
	}
		/**
		 * @return Returns the productId.
		 */
		public int getProductId() {
			return productId;
		}
		/**
		 * @param productId The productId to set.
		 */
		public void setProductId(int productId) {
			this.productId = productId;
		}

}
