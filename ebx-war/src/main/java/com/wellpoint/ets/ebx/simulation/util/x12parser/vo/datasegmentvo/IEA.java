package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;



public class IEA implements X12Segment{
	public enum IEASegmentMetaDataEnum implements SegmentMetaData{		
		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		IEA01("01",1),
		IEA02("02",1);
		
		
		int repeatCount;
		String position;
		
		IEASegmentMetaDataEnum (String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		@Override
		public int getRepeatCont() {
			return repeatCount;
		}
		// Create Segment Instance is not Applicable for Data elements
		@Override
		public X12Segment createSegmentInstance() {			
			return null;
		}
		@Override
		public String getSementIdentifier() {
			return this.name();	
		}
		@Override
		public String getSegmentPosition() {
			return position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return X12SegmentTypeEnum.DATA_ELEMENT;
		}
	}
	private String iea01;
	private String iea02;
	
	
	private String expression;
	
	public IEA() {
		
	}
	
	public String getIea01() {
		return iea01;
	}
	public String getIea02() {
		return iea02;
	}

	
	
	public String getExpression() {
		return expression;
	}
	public SegmentMetaData[] getSegmentMetaData() {		
		return X12ParserUtil.getPositionSortedSegments(IEASegmentMetaDataEnum.values());
	}
	public void setIea01(String iea01) {
		this.iea01 = iea01;
	}
	public void setIea02(String iea02) {
		this.iea02 = iea02;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (IEASegmentMetaDataEnum.IEA01.equals(segmentMetaData)
				&& value instanceof String) {
			setIea01((String) value);
		}
		if (IEASegmentMetaDataEnum.IEA02.equals(segmentMetaData)
				&& value instanceof String) {
			setIea02((String) value);
		}
		
	}

	

}
