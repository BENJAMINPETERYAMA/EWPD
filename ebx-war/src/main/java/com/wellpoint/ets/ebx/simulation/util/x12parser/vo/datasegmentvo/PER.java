package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class PER implements X12Segment{

	private String per01;
	private String per02;
	private String per03;
	private String per04;
	private String per05;
	private String per06;
	private String per07;
	private String per08;
	private String per09;
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(PERSegmentMetaDataEnum.values());
	}

	
	public enum PERSegmentMetaDataEnum implements SegmentMetaData{
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		PER01("01",1),
		PER02("02",1),
		PER03("03",1),
		PER04("04",1),
		PER05("05",1),
		PER06("06",1),
		PER07("07",1),
		PER08("08",1),
		PER09("09",1);
		
		int repeatCount;
		String position;
		
		PERSegmentMetaDataEnum (String position, int repeatCount) {
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
		if (PERSegmentMetaDataEnum.PER01.equals(segmentMetaData)
				&& value instanceof String) {
			setPer01((String) value);
		}
		if (PERSegmentMetaDataEnum.PER02.equals(segmentMetaData)
				&& value instanceof String) {
			setPer02((String) value);
		}
		if (PERSegmentMetaDataEnum.PER03.equals(segmentMetaData)
				&& value instanceof String) {
			setPer03((String) value);
		}
		if (PERSegmentMetaDataEnum.PER04.equals(segmentMetaData)
				&& value instanceof String) {
			setPer04((String) value);
		}
		if (PERSegmentMetaDataEnum.PER05.equals(segmentMetaData)
				&& value instanceof String) {
			setPer05((String) value);
		}
		if (PERSegmentMetaDataEnum.PER06.equals(segmentMetaData)
				&& value instanceof String) {
			setPer06((String) value);
		}
		if (PERSegmentMetaDataEnum.PER07.equals(segmentMetaData)
				&& value instanceof String) {
			setPer07((String) value);
		}
		if (PERSegmentMetaDataEnum.PER08.equals(segmentMetaData)
				&& value instanceof String) {
			setPer08((String) value);
		}
		if (PERSegmentMetaDataEnum.PER09.equals(segmentMetaData)
				&& value instanceof String) {
			setPer09((String) value);
		}
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getPer01() {
		return per01;
	}
	public void setPer01(String per01) {
		this.per01 = per01;
	}
	public String getPer02() {
		return per02;
	}
	public void setPer02(String per02) {
		this.per02 = per02;
	}
	public String getPer03() {
		return per03;
	}
	public String getPer03Desc() {
		String desc = "";
		if (null != per03) {
			desc = X12ParserUtil.getX12Description("PER_QUALIFIER_"+per03.trim());
		}
		return desc;
	}
	public void setPer03(String per03) {
		this.per03 = per03;
	}
	public String getPer04() {
		return per04;
	}
	public void setPer04(String per04) {
		this.per04 = per04;
	}
	public String getPer05() {
		return per05;
	}
	public String getPer05Desc() {
		String desc = "";
		if (null != per05) {
			desc = X12ParserUtil.getX12Description("PER_QUALIFIER_"+per05.trim());
		}
		return desc;
	}
	public void setPer05(String per05) {
		this.per05 = per05;
	}
	public String getPer06() {
		return per06;
	}
	public void setPer06(String per06) {
		this.per06 = per06;
	}
	public String getPer07() {
		return per07;
	}
	public void setPer07(String per07) {
		this.per07 = per07;
	}
	public String getPer07Desc() {
		String desc = "";
		if (null != per07) {
			desc = X12ParserUtil.getX12Description("PER_QUALIFIER_"+per07.trim());
		}
		return desc;
	}
	public String getPer08() {
		return per08;
	}
	public void setPer08(String per08) {
		this.per08 = per08;
	}
	public String getPer09() {
		return per09;
	}
	public void setPer09(String per09) {
		this.per09 = per09;
	}
	
	
}
