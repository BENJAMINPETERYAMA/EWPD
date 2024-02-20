/*
 * Created on Jun 14, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.entity;

/**
 * @author u19278
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Page {

	
	private int currentPage = 0;
	
	private int totalNoOfPages;
	
	private int firstPage;
	
	private int lastPage;
	
	private double totalNoOfRecords;
	
	private double noOfRecordsPerPage = 50;
	
	private int startRowNum = 1;
	
	private int endRowNum;
	
	
	
	
	/**
	 * @return Returns the currentPage.
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage The currentPage to set.
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return Returns the firstPage.
	 */
	public int getFirstPage() {
		return firstPage;
	}
	/**
	 * @param firstPage The firstPage to set.
	 */
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	/**
	 * @return Returns the lastPage.
	 */
	public int getLastPage() {
		return lastPage;
	}
	/**
	 * @param lastPage The lastPage to set.
	 */
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	/**
	 * @return Returns the totalNoOfPages.
	 */
	public int getTotalNoOfPages() {
		if(totalNoOfRecords >50){
			totalNoOfPages = (int)Math.ceil(totalNoOfRecords/noOfRecordsPerPage);
		}else if(totalNoOfRecords <=50 && totalNoOfRecords>0){
			totalNoOfPages = 1;
		}
		return totalNoOfPages;
	}
	/**
	 * @param totalNoOfPages The totalNoOfPages to set.
	 */
	public void setTotalNoOfPages(int totalNoOfPages) {
		this.totalNoOfPages = totalNoOfPages;
	}
	
	/**
	 * @return Returns the endRowNum.
	 */
	public int getEndRowNum() {
		endRowNum = (currentPage*50);
		return endRowNum;
	}
	/**
	 * @param endRowNum The endRowNum to set.
	 */
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	
	/**
	 * @param noOfRecordsPerPage The noOfRecordsPerPage to set.
	 */
	public void setNoOfRecordsPerPage(int noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
	}
	/**
	 * @return Returns the startRowNum.
	 */
	public int getStartRowNum() {
		startRowNum = ((currentPage*50)-50)+1;
		return startRowNum;
	}
	/**
	 * @param startRowNum The startRowNum to set.
	 */
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	/**
	 * @param noOfRecordsPerPage The noOfRecordsPerPage to set.
	 */
	public void setNoOfRecordsPerPage(double noOfRecordsPerPage) {
		this.noOfRecordsPerPage = noOfRecordsPerPage;
	}
	
	/**
	 * @return Returns the totalNoOfRecords.
	 */
	public double getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	/**
	 * @param totalNoOfRecords The totalNoOfRecords to set.
	 */
	public void setTotalNoOfRecords(double totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	/**
	 * @return Returns the noOfRecordsPerPage.
	 */
	public double getNoOfRecordsPerPage() {
		return noOfRecordsPerPage;
	}
}
