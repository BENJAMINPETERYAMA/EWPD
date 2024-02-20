package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class REF implements X12Segment{
	private String ref01;
	private String ref02;
	private String ref03;
	private String ref04;
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(REFSegmentMetaDataEnum.values());
	}

	
	public enum REFSegmentMetaDataEnum implements SegmentMetaData{		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		REF01("01",1),
		REF02("02",1),
		REF03("03",1),
		REF04("04",1);	
		
		int repeatCount;
		String position;
		
		REFSegmentMetaDataEnum (String position, int repeatCount) {
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
		if (REFSegmentMetaDataEnum.REF01.equals(segmentMetaData)
				&& value instanceof String) {
			setRef01((String) value);
		}
		if (REFSegmentMetaDataEnum.REF02.equals(segmentMetaData)
				&& value instanceof String) {
			setRef02((String) value);
		}
		if (REFSegmentMetaDataEnum.REF03.equals(segmentMetaData)
				&& value instanceof String) {
			setRef03((String) value);
		}
		if (REFSegmentMetaDataEnum.REF04.equals(segmentMetaData)
				&& value instanceof String) {
			setRef04((String) value);
		}
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getRef01() {
		return ref01;
	}
	public void setRef01(String ref01) {
		this.ref01 = ref01;
	}
	public String getRef02() {
		return ref02;
	}
	public void setRef02(String ref02) {
		this.ref02 = ref02;
	}
	public String getRef03() {
		return ref03;
	}
	public void setRef03(String ref03) {
		this.ref03 = ref03;
	}
	public String getRef04() {
		return ref04;
	}
	public void setRef04(String ref04) {
		this.ref04 = ref04;
	}
	
	
}
