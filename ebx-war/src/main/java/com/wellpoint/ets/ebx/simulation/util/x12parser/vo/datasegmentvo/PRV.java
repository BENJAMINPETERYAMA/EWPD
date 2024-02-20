package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class PRV implements X12Segment{

	private String prv01;
	private String prv02;
	private String prv03;
	private String prv04;
	private String prv05;
	private String prv06;
	
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(PRVSegmentMetaDataEnum.values());
	}

	
	public enum PRVSegmentMetaDataEnum implements SegmentMetaData{	
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		PRV01("01",1),
		PRV02("02",1),
		PRV03("03",1),
		PRV04("04",1),
		PRV05("05",1),
		PRV06("06",1);	
		
		int repeatCount;
		String position;
		
		PRVSegmentMetaDataEnum (String position, int repeatCount) {
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
		if (PRVSegmentMetaDataEnum.PRV01.equals(segmentMetaData)
				&& value instanceof String) {
			setPrv01((String) value);
		}
		if (PRVSegmentMetaDataEnum.PRV02.equals(segmentMetaData)
				&& value instanceof String) {
			setPrv02((String) value);
		}
		if (PRVSegmentMetaDataEnum.PRV03.equals(segmentMetaData)
				&& value instanceof String) {
			setPrv03((String) value);
		}
		if (PRVSegmentMetaDataEnum.PRV04.equals(segmentMetaData)
				&& value instanceof String) {
			setPrv04((String) value);
		}
		if (PRVSegmentMetaDataEnum.PRV05.equals(segmentMetaData)
				&& value instanceof String) {
			setPrv05((String) value);
		}
		if (PRVSegmentMetaDataEnum.PRV06.equals(segmentMetaData)
				&& value instanceof String) {
			setPrv06((String) value);
		}
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	public String getPrv01() {
		return prv01;
	}
	public void setPrv01(String prv01) {
		this.prv01 = prv01;
	}
	public String getPrv02() {
		return prv02;
	}
	public void setPrv02(String prv02) {
		this.prv02 = prv02;
	}
	public String getPrv03() {
		return prv03;
	}
	public void setPrv03(String prv03) {
		this.prv03 = prv03;
	}
	public String getPrv04() {
		return prv04;
	}
	public void setPrv04(String prv04) {
		this.prv04 = prv04;
	}
	public String getPrv05() {
		return prv05;
	}
	public void setPrv05(String prv05) {
		this.prv05 = prv05;
	}
	public String getPrv06() {
		return prv06;
	}
	public void setPrv06(String prv06) {
		this.prv06 = prv06;
	}
	
	
}
