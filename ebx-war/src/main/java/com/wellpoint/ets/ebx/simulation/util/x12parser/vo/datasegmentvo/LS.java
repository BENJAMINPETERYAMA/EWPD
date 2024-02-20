package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class LS implements X12Segment{
	private String ls01;

	public String getLs01() {
		return ls01;
	}

	public void setLs01(String ls01) {
		this.ls01 = ls01;
	}
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(LSSegmentMetaDataEnum.values());
	}

	 
	public enum LSSegmentMetaDataEnum implements SegmentMetaData{		
	// Syntax - Seg Id ( Pos, Max Use) 
	// Pos will be used for sorting the segments.
		LS("01", 1);		
		
		int repeatCount;
		String position;
		
		LSSegmentMetaDataEnum (String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		public String getSementIdentifier() {
			return this.name();	
		}
		public int getRepeatCont() {
			return repeatCount;
		}	
		public X12Segment createSegmentInstance() {			
			return null;
		}
		@Override
		public String getSegmentPosition() {
			return this.position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return X12SegmentTypeEnum.DATA_ELEMENT;
		}
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (LSSegmentMetaDataEnum.LS.equals(segmentMetaData)
				&& value instanceof String) {
			setLs01((String) value);
		}
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
}
