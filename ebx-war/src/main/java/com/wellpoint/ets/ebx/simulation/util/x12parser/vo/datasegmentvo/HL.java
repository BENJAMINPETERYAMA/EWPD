package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;



public class HL implements X12Segment{
	
	private String hl01;
	private String hl02;
	private String hl03;
	private String hl04;
	
	public HL() {
		
	}
	public HL (String x12Expr) {
		
	}
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(HLSegmentMetaDataEnum.values());
	}

	
	public enum HLSegmentMetaDataEnum implements SegmentMetaData{		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		HL01("01",1),
		HL02("02",1),
		HL03("03",1),
		HL04("04",1);	
		
		int repeatCount;
		String position;
		HLSegmentMetaDataEnum (String position ,int repeatCount) {
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
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (HLSegmentMetaDataEnum.HL01.equals(segmentMetaData)
				&& value instanceof String) {
			setHl01((String) value);
		}
		if (HLSegmentMetaDataEnum.HL02.equals(segmentMetaData)
				&& value instanceof String) {
			setHl02((String) value);
		}
		if (HLSegmentMetaDataEnum.HL03.equals(segmentMetaData)
				&& value instanceof String) {
			setHl03((String) value);
		}
		if (HLSegmentMetaDataEnum.HL04.equals(segmentMetaData)
				&& value instanceof String) {
			setHl04((String) value);
		}
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getHl01() {
		return hl01;
	}
	public void setHl01(String hl01) {
		this.hl01 = hl01;
	}
	public String getHl02() {
		return hl02;
	}
	public void setHl02(String hl02) {
		this.hl02 = hl02;
	}
	public String getHl03() {
		return hl03;
	}
	public void setHl03(String hl03) {
		this.hl03 = hl03;
	}
	public String getHl04() {
		return hl04;
	}
	public void setHl04(String hl04) {
		this.hl04 = hl04;
	}
	


}
