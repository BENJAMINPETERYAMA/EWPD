package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;


import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.BHT;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.SE;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.ST;



public class Transaction implements X12Segment{

	
	private List<Loop2000> loop2000;
	
	private SE se;
	private ST st;
	private BHT bht;
	
	
	public Transaction () {
		
	}	
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(TransactionSegmentMetaDataEnum.values());
	}

	public enum TransactionSegmentMetaDataEnum implements SegmentMetaData{		
		
		/**************************************************
		| Pos   | Seg Id    | Name                 					| Max Use|
		|--------------------------------------------------------------------|  
		| 01 	|ST 		| Transaction Set Header 				| 	1	 |
		| 02 	|BHT 		| Beginning of Hierarchical Transaction | 	1	 |
		| 03	|LOOP2000  |										|		 |									|		 |		
		| 07    |SE 		| Transaction Set Trailer 				| 	1	 |		
		**********************************************************************/
	// Syntax - 
//  Seg Id 		(   Pos  , Max Use ,  Data type		 , Segment Type) 
//----------------------------------------------------	
		ST(		  "01"   ,	  1    ,  ST.class		 , X12SegmentTypeEnum.DATA_SEGMENT), 
		BHT(	  "02"   ,	  1    ,  BHT.class		 , X12SegmentTypeEnum.DATA_SEGMENT), 
		LOOP2000( "03"   ,	  0    ,  Loop2000.class , X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT), // Position for Loop is for internal use. To identify the sort order	
		SE(		  "04"   ,	  1	   ,  SE.class		 , X12SegmentTypeEnum.DATA_SEGMENT);		

		int repeatCount;
		String position;
		Class<? extends X12Segment> x12SegmentClass;
		X12SegmentTypeEnum segmentTypeEnum;
		
		TransactionSegmentMetaDataEnum (String position, int maxUse, Class<? extends X12Segment> x12SegmentClass, X12SegmentTypeEnum X12SegmentTypeEnum) {
			this.repeatCount = maxUse;
			this.position = position;
			this.x12SegmentClass =  x12SegmentClass;
			this.segmentTypeEnum = X12SegmentTypeEnum;
		}
		public String getSementIdentifier() {
			return this.name()+"*";	
		}
		public int getRepeatCont() {
			return repeatCount;
		}
		@Override
		public X12Segment createSegmentInstance() throws X12ParserException {
			return X12ParserUtil.createInstance(x12SegmentClass);
		}
		@Override
		public String getSegmentPosition() {
			return this.position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return this.segmentTypeEnum;
		}
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (TransactionSegmentMetaDataEnum.ST.equals(segmentMetaData)
				&& value instanceof ST) {
			setSt((ST) value);
		}
		if (TransactionSegmentMetaDataEnum.BHT.equals(segmentMetaData)
				&& value instanceof BHT) {
			setBht((BHT) value);
		}
		if (TransactionSegmentMetaDataEnum.LOOP2000.equals(segmentMetaData)
				&& value instanceof Loop2000) {
			if (null == getLoop2000()) {
				setLoop2000(new ArrayList<Loop2000>());
			}
			getLoop2000().add((Loop2000) value);
		}
		if (TransactionSegmentMetaDataEnum.SE.equals(segmentMetaData)
				&& value instanceof SE) {
			setSe((SE) value);
		}
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public SE getSe() {
		return se;
	}

	public void setSe(SE se) {
		this.se = se;
	}

	public ST getSt() {
		return st;
	}

	public void setSt(ST st) {
		this.st = st;
	}

	public BHT getBht() {
		return bht;
	}

	public void setBht(BHT bht) {
		this.bht = bht;
	}
	public List<Loop2000> getLoop2000() {
		return loop2000;
	}
	public void setLoop2000(List<Loop2000> loop2000) {
		this.loop2000 = loop2000;
	}
		
}
