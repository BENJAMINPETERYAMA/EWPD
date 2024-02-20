package com.wellpoint.ets.ebx.simulation.util.x12parser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Interchange;

public class X12Parser {
	private static Logger logger = Logger.getLogger(X12Parser.class);
	public X12Parser() {
	}
	
	public Interchange parse271Response(String x12Response) throws X12ParserException {
		boolean result = false;
		Interchange x12InterchangeSegment = new Interchange();;
		if (null != x12Response) {		
			StringTokenizer st = new StringTokenizer(x12Response,"~");	
			StringBuffer sb = new StringBuffer(st.nextToken().trim());	
			result = parseX12Segment(x12InterchangeSegment,sb, st); 
		}
		if (!result) {
			//System.out.println("Invalid X12 271 response -->" + x12Response);
			logger.info("Invalid X12 271 response -->" +ESAPI.encoder().encodeForHTML(x12Response));
		}		
		return x12InterchangeSegment;
	}
	
	private boolean parseX12Segment(X12Segment x12Segment, StringBuffer segment, StringTokenizer st) throws X12ParserException {	
		SegmentMetaData[] segmentMetaDataList = x12Segment.getSegmentMetaData();
		String segmentExpression = "";
		String identifier;
		boolean isSegmentsPresent = false;
		
		for (SegmentMetaData segmentMetaData : segmentMetaDataList) {
			identifier = segmentMetaData.getSementIdentifier();
			if (segmentMetaData.getSegmentType().equals(X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT)
					|| segmentMetaData.getSegmentType().equals(X12SegmentTypeEnum.FUNCTIONAL_GROUP_CONTROL_SEGMENT)
					|| segmentMetaData.getSegmentType().equals(X12SegmentTypeEnum.TRANSACTION_SET_CONTROL_SEGMENT)) {				
			
				boolean loopPresent = false;
				do {
					X12Segment loopSegment = segmentMetaData.createSegmentInstance();
					loopPresent = parseX12Segment(loopSegment, segment, st);					
					if (loopPresent) {
						x12Segment.setFieldValue(segmentMetaData, loopSegment);
						segmentExpression = this.concatenateSegmentExpression(segmentExpression, loopSegment.getExpression());
					}
				}while(loopPresent);
			}
			else {			
				if (null != segment && segment.toString().startsWith(identifier)) {
					segmentExpression = this.concatenateSegmentExpression(segmentExpression, segment.toString(), "~");
					isSegmentsPresent = true;					
					this.parseX12DataSegment(segmentMetaData,x12Segment, segment);								
					if (st.hasMoreTokens()) {
						this.setNextToken(segment,st);						
						for (int j=1; j<segmentMetaData.getRepeatCont(); j++) {
							if (segment.toString().startsWith(identifier)) {
								segmentExpression = this.concatenateSegmentExpression(segmentExpression, segment.toString(), "~");
								isSegmentsPresent = true;								
								this.parseX12DataSegment(segmentMetaData,x12Segment, segment);								
								if (st.hasMoreTokens()) {
									this.setNextToken(segment,st);								
								}
							}
							else {
								break;
							}
						}						
					}
					else {
						segment.delete(0, segment.length());
					}
				}
			}
		}

		if (isSegmentsPresent) {
			//System.out.println("*******Segment Present*************"+x12Segment);
			//System.out.println(" Cumulative expression "+segmentExpression);
			logger.info("*******Segment Present*************"+x12Segment);
			logger.info(" Cumulative expression "+ESAPI.encoder().encodeForHTML(segmentExpression));
			
			x12Segment.setExpression(segmentExpression);
		}
		return isSegmentsPresent;		
	}

	private String concatenateSegmentExpression(String ... segmentExpressions) {
		String expression = "";
		for (String segmentExpression : segmentExpressions) {
			expression += segmentExpression;
		}
		return expression;
	}

	private void setNextToken(StringBuffer segment, StringTokenizer st) {
		segment.delete(0, segment.length());
		segment.append(st.nextToken().trim());		
	}

	private void parseX12DataSegment(SegmentMetaData segmentInfoEnum,
			X12Segment x12Segment, StringBuffer segment) throws X12ParserException {
		String segmentDetail = segment.toString();
		if (null != segmentDetail) {
			X12Segment baseSegment = parseX12DataElements(segmentDetail,segmentInfoEnum);						
			x12Segment.setFieldValue(segmentInfoEnum, baseSegment);
		}	
		
	}

	private X12Segment parseX12DataElements(String segmentDetail,
			SegmentMetaData segmentInfoEnum) throws X12ParserException {
		X12Segment baseSegment = segmentInfoEnum.createSegmentInstance();
		baseSegment.setExpression(segmentDetail);
		String identifier = segmentInfoEnum.getSementIdentifier();
		segmentDetail = StringUtils.removeStart(segmentDetail, identifier);
		
		String[] fieldArray =  segmentDetail.split("\\*");
		if (null != fieldArray && fieldArray.length != 0) {				
			Iterator<String> fieldIterator = Arrays.asList(fieldArray).iterator();
			SegmentMetaData[] internalSegInfos  = baseSegment.getSegmentMetaData();
			for (SegmentMetaData internalSegInfo : internalSegInfos) {
				if (fieldIterator.hasNext()) {	
					String val = fieldIterator.next();
					baseSegment.setFieldValue(internalSegInfo, val);
					//System.out.println("internalSegInfo -"+ internalSegInfo);
					//System.out.println("segmentToken - "+ val);
					logger.info("internalSegInfo -"+ internalSegInfo);
					logger.info("segmentToken - "+ESAPI.encoder().encodeForHTML(val));
				}
			}
		}		
		return baseSegment;
		
	}
	

}
