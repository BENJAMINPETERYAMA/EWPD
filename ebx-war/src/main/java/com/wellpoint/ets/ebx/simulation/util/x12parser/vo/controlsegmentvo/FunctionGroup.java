package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;


import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.GE;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.GS;



public class FunctionGroup implements X12Segment{

	
	private List<Transaction> transaction;
	
	private GS gs;

	private GE ge;
	
	
	public FunctionGroup () {
		
	}	
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(FunctionGroupMetaDataEnum.values());
	}

	public enum FunctionGroupMetaDataEnum implements SegmentMetaData{		
		
		/**************************************************
		| Pos   | Seg Id    | Name                 					| Max Use|
		|--------------------------------------------------------------------|  
		| 01 	|GS 		| Functional Group Header 				| 	1	 |
		| 02 	|Transaction| 										| 		 |
		| 03	|GE			| Functional Group Trailer				|	1	 |
		**********************************************************************/
	// Syntax - 
//  Seg Id 		  (   Pos  , Max Use ,  Data type		 , Segment Type) 
//----------------------------------------------------	
		GS(		    "01" ,	  1    ,  GS.class		     , X12SegmentTypeEnum.DATA_SEGMENT), // Position is for internal use. To identify the order
		TRANSACTION("02",	  0    ,  Transaction.class  , X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT), // Position is for internal use. To identify the order	
		GE(	        "03" ,	  1    ,  GE.class		     , X12SegmentTypeEnum.DATA_SEGMENT); // Position is for internal use. To identify the order
		
		

		int repeatCount;
		String position;
		Class<? extends X12Segment> x12SegmentClass;
		X12SegmentTypeEnum segmentTypeEnum;
		
		FunctionGroupMetaDataEnum (String position, int maxUse, Class<? extends X12Segment> x12SegmentClass, X12SegmentTypeEnum X12SegmentTypeEnum) {
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
		if (FunctionGroupMetaDataEnum.GS.equals(segmentMetaData)
				&& value instanceof GS) {
			setGs((GS) value);
		}
		if (FunctionGroupMetaDataEnum.GE.equals(segmentMetaData)
				&& value instanceof GE) {
			setGe((GE) value);
		}
		if (FunctionGroupMetaDataEnum.TRANSACTION.equals(segmentMetaData)
				&& value instanceof Transaction) {
			if (null == getTransaction()) {
				setTransaction(new ArrayList<Transaction>());
			}
			getTransaction().add((Transaction) value);
		}
		
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public GS getGs() {
		return gs;
	}
	public GE getGe() {
		return ge;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	public void setGs(GS gs) {
		this.gs = gs;
	}
	public void setGe(GE ge) {
		this.ge = ge;
	}
	
	
		
}
