/*
 * <MQAdapter.java> © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WellPoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.domain.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.owasp.esapi.ESAPI;
import org.springframework.jms.core.JmsTemplate102;
import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;


public class MQAdapter {

	private static Logger logger = Logger.getLogger(MQAdapter.class);
	
	ResourceBundle rb=ResourceBundle.getBundle(DomainConstants.MQADAPTER, Locale.getDefault());
	
	private String EBSName;// = "Get_27XBenefitAccums";

	//private String EBSVersion;// = "1";
	
	private String EBSVersion4010= rb.getString("ebsVersion4010");
	
	private String EBSVersion5010= rb.getString("ebsVersion5010");

	private String lgclEnvrnmt=rb.getString("lgclEnvrnmt");// = "UNIT";

	private String lgclEnvrnmtTest=rb.getString("lgclEnvrnmtTest");

	private String srcLgcl=rb.getString("srcLgcl");// ="COMAPPS";

	private String trgtLgcl;

	private String pyldRequest;

	private String tid;

	private String rtngVer;

	private String rtngFlds;

	private String pyldFmt;

	private JmsTemplate102 xmlMQSender;

	private JmsTemplate102 xmlMQReceiver;

	private JmsTemplate102 x12MQSender;

	private JmsTemplate102 x12MQReceiver;

	private JmsTemplate102 xmlMQSenderTest;

	private JmsTemplate102 xmlMQReceiverTest;

	private JmsTemplate102 x12MQSenderTest;

	private JmsTemplate102 x12MQReceiverTest;

	private String xmlReplyToQueue=rb.getString("xmlReplyToQueue");// = "CAA.RESPQ";

	private String x12ReplyToQueue=rb.getString("x12ReplyToQueue");// = "";
	
	private String sourceEnvironment;
	
	private String destinationEnvironment;	

	private String header;// =

	
	// "<WlPntHdr><wlpHdrVer>3</wlpHdrVer><srcLgcl>COMAPPS</srcLgcl><srcNvrmt>UNIT</srcNvrmt><destnLgcl></destnLgcl><destnNvrmt>UNIT</destnNvrmt><futrPth></futrPth><pstPth></pstPth><tranTyp>Get_27XBenefitAccums</tranTyp><tranTypVer>1</tranTypVer><srvcTyp></srvcTyp><srvcTypVer></srvcTypVer><msgId>0113075144280_vadwpetsap</msgId><tranId>0113075144280_vadwpetsap</tranId><srvcId>0113075144280_vadwpetsap</srvcId><crltnMsgId></crltnMsgId><crltnTranId></crltnTranId><crltnSrvcId></crltnSrvcId><srvcProtcl>SYNC</srvcProtcl><pyldFmt>cXML</pyldFmt><rtngVer>3</rtngVer><RtngFlds><trgtLgclSytm>WEST</trgtLgclSytm></RtngFlds><crltnVer></crltnVer><crltnFlds></crltnFlds><trgtMethNm></trgtMethNm><trgtObjNm></trgtObjNm><msgTyp>REQ/REP-REQ</msgTyp></WlPntHdr>";

	/**
	 * 
	 * 
	 * @param
	 * @throws JMSException
	 * @throws JMSException
	 * @throws NamingException
	 */
	public String put27xBenefitRequest(String benefitRequest,
			boolean productionFlag,String system, String responseFormat) throws JMSException {
		MQManager mqManager = new MQManager();
		if (productionFlag) {
			mqManager.setMessageSender(xmlMQSender);
		} else {
			mqManager.setMessageSender(xmlMQSenderTest);
		}		
		mqManager.setReplyToQueue(getXmlReplyToQueue());
		String correlationID = newTranId();
		if(DomainConstants.RESPONSE_FORMAT_4010.equals(responseFormat)){
			header = getWlPntHdr(productionFlag,system,getEBSVersion4010());
		}else if(DomainConstants.RESPONSE_FORMAT_5010.equals(responseFormat)){
			header = getWlPntHdr(productionFlag,system,getEBSVersion5010());
		}
		logger.info("Wellpoint Header : "+ESAPI.encoder().encodeForHTML(header));
		return mqManager.sendMessage(benefitRequest, header, correlationID);
	}

	/**
	 * 
	 * 
	 * @param
	 * @throws JMSException
	 * @throws JMSException
	 * @throws NamingException
	 */
	public String putX12Request(String x12Request, boolean productionFlag)
			throws JMSException {
		MQManager mqManager = new MQManager();
		if (productionFlag) {
			mqManager.setMessageSender(x12MQSender);
		} else {
			mqManager.setMessageSender(x12MQSenderTest);
		}
		mqManager.setReplyToQueue(getX12ReplyToQueue());
		String correlationID = newTranId();
		return mqManager.sendMessage(x12Request, null, correlationID);
	}

	/**
	 * 
	 * 
	 * @param
	 * @throws JMSException
	 * @throws NamingException
	 * @return response - Response received from queue
	 * @throws JMSException
	 */
	public String get27xBenefitResponse(String id, boolean productionFlag)
			throws JMSException {
		MQManager mqManager = new MQManager();
		if (productionFlag) {
			mqManager.setMessageReceiver(xmlMQReceiver);
		} else {
			mqManager.setMessageReceiver(xmlMQReceiverTest);
		}
		return mqManager.receiveMessage(id);
	}

	/**
	 * 
	 * 
	 * @param
	 * @throws JMSException
	 * @throws NamingException
	 * @return response - Response received from queue
	 * @throws JMSException
	 */
	public String getX12Response(String msgID, boolean productionFlag)
			throws JMSException {
		MQManager mqManager = new MQManager();
		if (productionFlag) {
			mqManager.setMessageReceiver(x12MQReceiver);
		} else {
			mqManager.setMessageReceiver(x12MQReceiverTest);
		}
		return mqManager.receiveMessage(msgID);
	}

	/**
	 * @return Returns the xmlMQReceiver.
	 */
	public JmsTemplate102 getXmlMQReceiver() {
		return xmlMQReceiver;
	}

	/**
	 * @param xmlMQReceiver
	 *            The xmlMQReceiver to set.
	 */
	public void setXmlMQReceiver(JmsTemplate102 xmlMQReceiver) {
		this.xmlMQReceiver = xmlMQReceiver;
	}

	/**
	 * @return Returns the xmlMQSender.
	 */
	public JmsTemplate102 getXmlMQSender() {
		return xmlMQSender;
	}

	/**
	 * @param xmlMQSender
	 *            The xmlMQSender to set.
	 */
	public void setXmlMQSender(JmsTemplate102 xmlMQSender) {
		this.xmlMQSender = xmlMQSender;
	}

	public String getWlPntHdr(boolean productionFlag,String system, String ebsVersion) {
		//String lgclEnvrnmt = this.lgclEnvrnmt;
		String lgclEnvrnmt = ((this.sourceEnvironment == null) ? "" : this.sourceEnvironment);
		if(DomainConstants.ACES.equals(system)){
			this.trgtLgcl=rb.getString("trgtLgclSytmAces");
		}else if(DomainConstants.FACETS.equals(system)){
			this.trgtLgcl=rb.getString("trgtLgclSytmFacets");
		}else{
			this.trgtLgcl=rb.getString("trgtLgclSytmWgs");
		}
		if (!productionFlag) {
			lgclEnvrnmt = this.lgclEnvrnmtTest;
		}
		this.tid = newTranId();

		if (lgclEnvrnmt.startsWith("x")) {
			return this.rtngFlds;
		}

		if (lgclEnvrnmt.startsWith("4")) {
			lgclEnvrnmt = lgclEnvrnmt.substring(1);
			this.rtngVer = "4";
		} else {
			this.rtngVer = "3";
			this.rtngFlds = ("<RtngFlds><trgtLgclSytm>" + this.trgtLgcl + "</trgtLgclSytm></RtngFlds>");
		}

		if (lgclEnvrnmt.startsWith("m")) {
			lgclEnvrnmt = lgclEnvrnmt.substring(1);
		}

		if (lgclEnvrnmt.startsWith("c")) {
			lgclEnvrnmt = lgclEnvrnmt.substring(1);
			this.pyldFmt = "cCOBOL";
		} else {
			this.pyldFmt = "cXML";
		}
		return "<WlPntHdr><wlpHdrVer>3</wlpHdrVer><srcLgcl>"
				+ this.srcLgcl
				+ "</srcLgcl><srcNvrmt>"
				+ ((this.sourceEnvironment == null) ? "" : this.sourceEnvironment)
				+ "</srcNvrmt><destnLgcl></destnLgcl><destnNvrmt>"
				+ ((this.destinationEnvironment == null) ? "" : this.destinationEnvironment)
				+ "</destnNvrmt><futrPth></futrPth><pstPth></pstPth><tranTyp>"
				+ this.EBSName
				+ "</tranTyp><tranTypVer>"
				+ ebsVersion
				//+ this.EBSVersion
				+ "</tranTypVer><srvcTyp></srvcTyp><srvcTypVer></srvcTypVer><msgId>"
				+ this.tid
				+ "</msgId><tranId>"
				+ this.tid
				+ "</tranId><srvcId>"
				+ this.tid
				+ "</srvcId><crltnMsgId></crltnMsgId><crltnTranId></crltnTranId><crltnSrvcId></crltnSrvcId>"
				+ "<srvcProtcl>SYNC</srvcProtcl><pyldFmt>"
				+ this.pyldFmt
				+ "</pyldFmt><rtngVer>"
				+ this.rtngVer
				+ "</rtngVer>"
				+ this.rtngFlds
				+ "<crltnVer></crltnVer><crltnFlds></crltnFlds><trgtMethNm></trgtMethNm><trgtObjNm></trgtObjNm><msgTyp>REQ/REP-REQ</msgTyp></WlPntHdr>";
	}

	public String getEBSName() {
		return this.EBSName;
	}

	public void setEBSName(String name) {
		this.EBSName = name;
	}

	/*public String getEBSVersion() {
		return this.EBSVersion;
	}

	public void setEBSVersion(String version) {
		this.EBSVersion = version;
	}*/

	public String getSrcLgcl() {
		return this.srcLgcl;
	}

	public void setSrcLgcl(String srcLgcl) {
		this.srcLgcl = srcLgcl;
	}

	public String getTrgtLgcl() {
		return this.trgtLgcl;
	}

	public void setTrgtLgcl(String trgtLgcl) {
		this.trgtLgcl = trgtLgcl;
	}

	public String getPyldRequest() {
		return this.pyldRequest;
	}

	public void setPyldRequest(String pyldRequest) {
		this.pyldRequest = pyldRequest;
	}

	public String getLgclEnvrnmt() {
		return this.lgclEnvrnmt;
	}

	public void setLgclEnvrnmt(String lgclEnvrnmt) {
		this.lgclEnvrnmt = lgclEnvrnmt;
	}

	public String newTranId() {
		String hostname;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (UnknownHostException e) {
			hostname = "Unknown";
		}

		Date today = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmssSSS");

		this.tid = (formatter.format(today) + "_" + hostname);
		if (getLgclEnvrnmt().indexOf("t#") > -1) {
			this.tid = (this.tid + "$$$$$$$$$$$$$$$$$$$$").substring(0,
					new Integer(getLgclEnvrnmt().substring(
							getLgclEnvrnmt().indexOf("t#") + 2).trim())
							.intValue());
		} else {
			this.tid = (this.tid + "$$$$").substring(0, 24);
		}
		return this.tid;
	}

	/**
	 * @return Returns the x12ReplyToQueue.
	 */
	public String getX12ReplyToQueue() {
		return x12ReplyToQueue;
	}

	/**
	 * @param replyToQueue
	 *            The x12ReplyToQueue to set.
	 */
	public void setX12ReplyToQueue(String replyToQueue) {
		x12ReplyToQueue = replyToQueue;
	}

	/**
	 * @return Returns the xmlReplyToQueue.
	 */
	public String getXmlReplyToQueue() {
		return xmlReplyToQueue;
	}

	/**
	 * @param xmlReplyToQueue
	 *            The xmlReplyToQueue to set.
	 */
	public void setXmlReplyToQueue(String xmlReplyToQueue) {
		this.xmlReplyToQueue = xmlReplyToQueue;
	}

	/**
	 * @return Returns the x12MQReceiver.
	 */
	public JmsTemplate102 getX12MQReceiver() {
		return x12MQReceiver;
	}

	/**
	 * @param receiver
	 *            The x12MQReceiver to set.
	 */
	public void setX12MQReceiver(JmsTemplate102 receiver) {
		x12MQReceiver = receiver;
	}

	/**
	 * @return Returns the x12MQSender.
	 */
	public JmsTemplate102 getX12MQSender() {
		return x12MQSender;
	}

	/**
	 * @param sender
	 *            The x12MQSender to set.
	 */
	public void setX12MQSender(JmsTemplate102 sender) {
		x12MQSender = sender;
	}

	/**
	 * @return Returns the x12MQReceiverTest.
	 */
	public JmsTemplate102 getX12MQReceiverTest() {
		return x12MQReceiverTest;
	}

	/**
	 * @param receiverTest
	 *            The x12MQReceiverTest to set.
	 */
	public void setX12MQReceiverTest(JmsTemplate102 receiverTest) {
		x12MQReceiverTest = receiverTest;
	}

	/**
	 * @return Returns the x12MQSenderTest.
	 */
	public JmsTemplate102 getX12MQSenderTest() {
		return x12MQSenderTest;
	}

	/**
	 * @param senderTest
	 *            The x12MQSenderTest to set.
	 */
	public void setX12MQSenderTest(JmsTemplate102 senderTest) {
		x12MQSenderTest = senderTest;
	}

	/**
	 * @return Returns the xmlMQReceiverTest.
	 */
	public JmsTemplate102 getXmlMQReceiverTest() {
		return xmlMQReceiverTest;
	}

	/**
	 * @param xmlMQReceiverTest
	 *            The xmlMQReceiverTest to set.
	 */
	public void setXmlMQReceiverTest(JmsTemplate102 xmlMQReceiverTest) {
		this.xmlMQReceiverTest = xmlMQReceiverTest;
	}

	/**
	 * @return Returns the xmlMQSenderTest.
	 */
	public JmsTemplate102 getXmlMQSenderTest() {
		return xmlMQSenderTest;
	}

	/**
	 * @param xmlMQSenderTest
	 *            The xmlMQSenderTest to set.
	 */
	public void setXmlMQSenderTest(JmsTemplate102 xmlMQSenderTest) {
		this.xmlMQSenderTest = xmlMQSenderTest;
	}

	/**
	 * @return Returns the lgclEnvrnmtTest.
	 */
	public String getLgclEnvrnmtTest() {
		return lgclEnvrnmtTest;
	}

	/**
	 * @param lgclEnvrnmtTest
	 *            The lgclEnvrnmtTest to set.
	 */
	public void setLgclEnvrnmtTest(String lgclEnvrnmtTest) {
		this.lgclEnvrnmtTest = lgclEnvrnmtTest;
	}

	public String getEBSVersion4010() {
		return EBSVersion4010;
	}

	public void setEBSVersion4010(String version4010) {
		EBSVersion4010 = version4010;
	}

	public String getEBSVersion5010() {
		return EBSVersion5010;
	}

	public void setEBSVersion5010(String version5010) {
		EBSVersion5010 = version5010;
	}

	public String getSourceEnvironment() {
		return sourceEnvironment;
	}

	public void setSourceEnvironment(String sourceEnvironment) {
		this.sourceEnvironment = sourceEnvironment;
	}

	public String getDestinationEnvironment() {
		return destinationEnvironment;
	}

	public void setDestinationEnvironment(String destinationEnvironment) {
		this.destinationEnvironment = destinationEnvironment;
	}	
	
}