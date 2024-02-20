package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;



public class AAA implements X12Segment{
	public enum AAASegmentMetaDataEnum implements SegmentMetaData{		
		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		AAA01("01",1),AAA02("02",1),AAA03("03",1),AAA04("04",1);	
		
		int repeatCount;
		String position;
		
		AAASegmentMetaDataEnum (String position, int repeatCount) {
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
	private String aaa01;
	private String aaa02;
	private String aaa03;
	
	private String aaa04;
	
	private String expression;
	
	public AAA() {
		
	}
	
	public String getAaa01() {
		return aaa01;
	}
	public String getAaa02() {
		return aaa02;
	}

	public String getAaa03() {
		return aaa03;
	}

	public String getAaa03Desc() {
		String desc = "";
		if (null != aaa03) {
			desc = X12ParserUtil.getX12Description("AAA03_"+aaa03.trim());
		}
		return desc;
	}
	
	public String getAaa04() {
		return aaa04;
	}
	public String getAaa04Desc() {
		String desc = "";
		if (null != aaa04) {
			desc = X12ParserUtil.getX12Description("AAA04_"+aaa04.trim());
		}
		return desc;
	}
	
	public String getExpression() {
		return expression;
	}
	public SegmentMetaData[] getSegmentMetaData() {		
		return X12ParserUtil.getPositionSortedSegments(AAASegmentMetaDataEnum.values());
	}
	public void setAaa01(String aaa01) {
		this.aaa01 = aaa01;
	}
	public void setAaa02(String aaa02) {
		this.aaa02 = aaa02;
	}
	public void setAaa03(String aaa03) {
		this.aaa03 = aaa03;
	}
	public void setAaa04(String aaa04) {
		this.aaa04 = aaa04;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (AAASegmentMetaDataEnum.AAA01.equals(segmentMetaData)
				&& value instanceof String) {
			setAaa01((String) value);
		}
		if (AAASegmentMetaDataEnum.AAA02.equals(segmentMetaData)
				&& value instanceof String) {
			setAaa02((String) value);
		}
		if (AAASegmentMetaDataEnum.AAA03.equals(segmentMetaData)
				&& value instanceof String) {
			setAaa03((String) value);
		}
		if (AAASegmentMetaDataEnum.AAA04.equals(segmentMetaData)
				&& value instanceof String) {
			setAaa04((String) value);
		}
	}

}
