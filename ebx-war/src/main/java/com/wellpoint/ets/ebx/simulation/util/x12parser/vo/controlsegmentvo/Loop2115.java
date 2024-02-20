package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.III;

public class Loop2115 implements X12Segment{

	private III iii;

	private String expression;
	
	@Override
	public String getExpression() {
		return this.expression;
	}

	@Override
	public void setExpression(String expression) {
		this.expression = expression;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(Loop2115SegmentMetaDataEnum.values());
	} 
	public enum Loop2115SegmentMetaDataEnum implements SegmentMetaData{		
		/**************************************************
		| Pos   | Seg Id   | Name                                  | Max Use |
		|--------------------------------------------------------------------|  
		| 2600  | III      | Information               			   | 	1	 |
		**********************************************************************/
// Syntax - 
//  Seg Id ( Pos  , Max Use , Data type	  , Segment Type) 
//----------------------------------------------------
		III("01",		1	,  III.class  , X12SegmentTypeEnum.DATA_SEGMENT);	   	

		Class<? extends X12Segment> x12SegmentClass;
		int repeatCount;
		String position;
		X12SegmentTypeEnum segmentTypeEnum;
		
		Loop2115SegmentMetaDataEnum (String position, int maxUse, Class<? extends X12Segment> x12SegmentClass,  X12SegmentTypeEnum X12SegmentTypeEnum) {
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
			return this.position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return this.segmentTypeEnum;
		}
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (Loop2115SegmentMetaDataEnum.III.equals(segmentMetaData)
				&& value instanceof III) {
			setIii((III) value);
		}
	}

	public III getIii() {
		return iii;
	}

	public void setIii(III iii) {
		this.iii = iii;
	}
}
