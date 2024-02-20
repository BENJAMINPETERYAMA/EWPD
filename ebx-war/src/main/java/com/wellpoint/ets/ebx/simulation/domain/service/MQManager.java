/*
 * <MQManager.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.domain.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate102;
import org.springframework.jms.core.MessageCreator;


public class MQManager {

	private JmsTemplate102 messageSender;

	private String replyToQueue;

	private JmsTemplate102 messageReceiver;

	private String msgRequest;

	private TextMessage message;

	private static final String WLP_HDR = "WlPntHdr";
	
	/** Logger instance for this class */
	private static Logger logger = Logger.getLogger(MQManager.class);

	/**
	 * This method is used to send the request message to queue
	 * @param requestMessage
	 * @param session
	 * @throws JMSException
	 */
	public String sendMessage(String msg, final String header,
			final String correlationID) throws JMSException {
		this.msgRequest = msg;

		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {

				message = session.createTextMessage(msgRequest);

				Destination destination = messageSender
						.getDestinationResolver().resolveDestinationName(
								session, getReplyToQueue(), false);
				message.setJMSReplyTo(destination);
				if (header != null) {
					message.setStringProperty(WLP_HDR, header);
				}
				if (correlationID != null) {
					message
							.setJMSCorrelationIDAsBytes(correlationID
									.getBytes());
				}
				return message;
			}
		};
		
		logger.info("Sending message to MQ with correlationID = " + stringToHex(correlationID));
		long start = System.currentTimeMillis();
		messageSender.send(messageCreator);
		long end = System.currentTimeMillis();
		logger.info("Time taken, in milliseconds, for Sending to MQ for correlationID = " + stringToHex(correlationID) + "= "+ (long)(end -start));
		return "ID:" + stringToHex(correlationID);

	}

	/**
	 * This method is used to receive the response message from queue
	 * @param ResponseQueue
	 * @param session
	 * @return
	 * @throws JMSException
	 */
	public String receiveMessage(String correlationID) throws JMSException {
		String message = null;
		Message msg = null;
		String selector = "JMSCorrelationID = '" + correlationID + "'";
		logger.info("Receiving message from MQ with correlationID = " + stringToHex(correlationID));
		long start = System.currentTimeMillis();
		msg = messageReceiver.receiveSelected(selector);
		long end = System.currentTimeMillis();
		logger.info("Time taken, in milliseconds, for receiving from MQ for correlationID = " + stringToHex(correlationID) + "= "+ (long)(end -start));
		TextMessage textMessage = (TextMessage) msg;
		if (msg != null) {
			message = textMessage.getText();
		}

		return message;
	}

	/**
	 * @return Returns the messageReceiver.
	 */
	public JmsTemplate102 getMessageReceiver() {
		return messageReceiver;
	}

	/**
	 * @param messageReceiver
	 *            The messageReceiver to set.
	 */
	public void setMessageReceiver(JmsTemplate102 messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	/**
	 * @return Returns the messageSender.
	 */
	public JmsTemplate102 getMessageSender() {
		return messageSender;
	}

	/**
	 * @param messageSender
	 *            The messageSender to set.
	 */
	public void setMessageSender(JmsTemplate102 messageSender) {
		this.messageSender = messageSender;
	}

	/**
	 * @return Returns the replyToQueue.
	 */
	public String getReplyToQueue() {
		return replyToQueue;
	}

	/**
	 * @param replyToQueue
	 *            The replyToQueue to set.
	 */
	public void setReplyToQueue(String replyToQueue) {
		this.replyToQueue = replyToQueue;
	}

	/**
	 * This method is used convert string to hex format
	 * @param id
	 * @return
	 */
	private String stringToHex(String id) {
		char[] chars = id.toCharArray();
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(Integer.toHexString((int) chars[i]));
		}
		return strBuffer.toString();
	}
}