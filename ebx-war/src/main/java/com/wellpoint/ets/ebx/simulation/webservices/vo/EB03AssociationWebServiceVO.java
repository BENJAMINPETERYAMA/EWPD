/**
 * 
 */
package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.List;

/**
 * @author U27438
 *
 */
public class EB03AssociationWebServiceVO {

	HippaCodeValueWebServiceVO eb03;
	HippaCodeValueWebServiceVO noteType;
	List<HippaCodeValueWebServiceVO> iii02List;
	String message;
	String msgReqdInd;
	String eb03String;
	Long seq_num;
	/**
	 * 
	 */
	public EB03AssociationWebServiceVO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the eb03
	 */
	public HippaCodeValueWebServiceVO getEb03() {
		return eb03;
	}
	/**
	 * @param eb03 the eb03 to set
	 */
	public void setEb03(HippaCodeValueWebServiceVO eb03) {
		this.eb03 = eb03;
	}
	/**
	 * @return the noteType
	 */
	public HippaCodeValueWebServiceVO getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(HippaCodeValueWebServiceVO noteType) {
		this.noteType = noteType;
	}
	/**
	 * @return the iii02List
	 */
	public List<HippaCodeValueWebServiceVO> getIii02List() {
		return iii02List;
	}
	/**
	 * @param iii02List the iii02List to set
	 */
	public void setIii02List(List<HippaCodeValueWebServiceVO> iii02List) {
		this.iii02List = iii02List;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the msgReqdInd
	 */
	public String getMsgReqdInd() {
		return msgReqdInd;
	}
	/**
	 * @param msgReqdInd the msgReqdInd to set
	 */
	public void setMsgReqdInd(String msgReqdInd) {
		this.msgReqdInd = msgReqdInd;
	}
	/**
	 * @return the eb03String
	 */
	public String getEb03String() {
		return eb03String;
	}
	/**
	 * @param eb03String the eb03String to set
	 */
	public void setEb03String(String eb03String) {
		this.eb03String = eb03String;
	}
	/**
	 * @return the seq_num
	 */
	public Long getSeq_num() {
		return seq_num;
	}
	/**
	 * @param seq_num the seq_num to set
	 */
	public void setSeq_num(Long seq_num) {
		this.seq_num = seq_num;
	}
	
}
