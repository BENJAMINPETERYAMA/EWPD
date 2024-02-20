package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.AAA;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.HL;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.TRN;



public class Loop2000 implements X12Segment{

	private HL hl;
	private List<AAA> aaa;
	
	private List<TRN> trn;
	
	private List<Loop2100> loop2100;
	
	private String expression;

	
	public Loop2000() {

	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(Loop2000SegmentMetaDataEnum.values());
	}

	public enum Loop2000SegmentMetaDataEnum implements SegmentMetaData {	
		/**************************************************
		| Pos   | Seg Id   | Name               | Max Use |
		|-------------------------------------------------|  
		| 01    | HL       | Hierarchical Level |   1     |     
		| 02    | TRN      | Trace              |   9     |
		| 03    | AAA      | Request Validation |   9     |
		| 04    | Loop2100 |                    |         |
		***************************************************/
		
// Syntax - 
// Seg Id (    	  Pos   , Max Use, Data type	  , Segment Type) 
		HL(		 "01" ,     1	 , HL.class		  , X12SegmentTypeEnum.DATA_SEGMENT), 
		TRN(	 "02" , 	9 	 , TRN.class	  , X12SegmentTypeEnum.DATA_SEGMENT), 
		AAA(	 "03" , 	9	 , AAA.class	  , X12SegmentTypeEnum.DATA_SEGMENT), 
		LOOP2100("04" ,	    0	 , Loop2100.class , X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT); 
		
		int repeatCount;
		String position;
		Class<? extends X12Segment> x12SegmentClass;
		X12SegmentTypeEnum segmentTypeEnum;
		
		Loop2000SegmentMetaDataEnum (String position, int maxUse, Class<? extends X12Segment> x12SegmentClass,  X12SegmentTypeEnum X12SegmentTypeEnum) {
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
		public X12Segment createSegmentInstance() throws X12ParserException {
			return X12ParserUtil.createInstance(x12SegmentClass);
		}
		@Override
		public String getSegmentPosition() {			
			return position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return this.segmentTypeEnum;
		}	
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (Loop2000SegmentMetaDataEnum.HL.equals(segmentMetaData)
				&& value instanceof HL) {
			setHl((HL) value);
		}
		if (Loop2000SegmentMetaDataEnum.TRN.equals(segmentMetaData)
				&& value instanceof TRN) {
			if(null == getTrn()) {
				setTrn(new ArrayList<TRN>());
			}
			getTrn().add((TRN)value);
		}
		if (Loop2000SegmentMetaDataEnum.AAA.equals(segmentMetaData)
				&& value instanceof AAA) {
			if(null == getAaa()) {
				setAaa(new ArrayList<AAA>());
			}
			getAaa().add((AAA)value);
		}
		if (Loop2000SegmentMetaDataEnum.LOOP2100.equals(segmentMetaData)
				&& value instanceof Loop2100) { 
			if(null == getLoop2100()) {
				setLoop2100(new ArrayList<Loop2100>());
			}
			getLoop2100().add((Loop2100)value);
		}
	}
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public HL getHl() {
		return hl;
	}
	public void setHl(HL hl) {
		this.hl = hl;
	}
	
	public List<AAA> getAaa() {
		return aaa;
	}

	public void setAaa(List<AAA> aaa) {
		this.aaa = aaa;
	}

	public List<Loop2100> getLoop2100() {
		return loop2100;
	}
	public void setLoop2100(List<Loop2100> loop2100) {
		this.loop2100 = loop2100;
	}
	public List<TRN> getTrn() {
		return trn;
	}

	public void setTrn(List<TRN> trn) {
		this.trn = trn;
	}
	
	
}
