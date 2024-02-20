package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class HSD implements X12Segment {
	public enum HSDSegmentMetaDataEnum implements SegmentMetaData {
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		HSD01("01",1), 
		HSD02("02",1), 
		HSD03("03",1), 
		HSD04("04",1), 
		HSD05("05",1), 
		HSD06("06",1), 
		HSD07("07",1), 
		HSD08("08",1);

		int repeatCount;
		String position;

		HSDSegmentMetaDataEnum(String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}

		public int getRepeatCont() {
			return repeatCount;
		}

		public X12Segment createSegmentInstance() {
			return null;
		}

		public String getSementIdentifier() {
			return this.name();
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
	private String expression;
	private String hsd01;
	private String hsd02;
	private String hsd03;
	private String hsd04;
	private String hsd05;
	private String hsd06;

	private String hsd07;

	private String hsd08;

	public String getExpression() {
		return expression;
	}

	public String getHsd01() {
		return hsd01;
	}
	public String getHsd01Desc() {
		String desc = "";
		if (null != hsd01) {
			desc = X12ParserUtil.getX12Description("HSD01_"+hsd01.trim());
		}
		return desc;
	}
	public String getHsd02() {
		return hsd02;
	}

	public String getHsd03() {
		return hsd03;
	}
	
	public String getHsd03Desc() {
		String desc = "";
		if (null != hsd03) {
			desc = X12ParserUtil.getX12Description("HSD03_"+hsd03.trim());
		}
		return desc;
	}
	
	public String getHsd04() {
		return hsd04;
	}

	public String getHsd05() {
		return hsd05;
	}
	
	public String getHsd05Desc() {
		String desc = "";
		if (null != hsd05) {
			desc = X12ParserUtil.getX12Description("HSD05_"+hsd05.trim());
		}
		return desc;
	}
	
	public String getHsd06() {
		return hsd06;
	}
	
	public String getHsd07() {
		return hsd07;
	}
	public String getHsd07Desc() {
		String desc = "";
		if (null != hsd07) {
			desc = X12ParserUtil.getX12Description("HSD07_"+hsd07.trim());
		}
		return desc;
	}
	public String getHsd08() {
		return hsd08;
	}
	public String getHsd08Desc() {
		String desc = "";
		if (null != hsd08) {
			desc = X12ParserUtil.getX12Description("HSD08_"+hsd08.trim());
		}
		return desc;
	}
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(HSDSegmentMetaDataEnum.values());
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (HSDSegmentMetaDataEnum.HSD01.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd01((String) value);
		}
		if (HSDSegmentMetaDataEnum.HSD02.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd02((String) value);
		}
		if (HSDSegmentMetaDataEnum.HSD03.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd03((String) value);
		}
		if (HSDSegmentMetaDataEnum.HSD04.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd04((String) value);
		}
		if (HSDSegmentMetaDataEnum.HSD05.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd05((String) value);
		}
		if (HSDSegmentMetaDataEnum.HSD06.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd06((String) value);
		}
		if (HSDSegmentMetaDataEnum.HSD07.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd07((String) value);
		}
		if (HSDSegmentMetaDataEnum.HSD08.equals(segmentMetaData)
				&& value instanceof String) {
			setHsd08((String) value);
		}
	}

	public void setHsd01(String hsd01) {
		this.hsd01 = hsd01;
	}

	public void setHsd02(String hsd02) {
		this.hsd02 = hsd02;
	}

	public void setHsd03(String hsd03) {
		this.hsd03 = hsd03;
	}

	public void setHsd04(String hsd04) {
		this.hsd04 = hsd04;
	}

	public void setHsd05(String hsd05) {
		this.hsd05 = hsd05;
	}

	public void setHsd06(String hsd06) {
		this.hsd06 = hsd06;
	}

	public void setHsd07(String hsd07) {
		this.hsd07 = hsd07;
	}

	public void setHsd08(String hsd08) {
		this.hsd08 = hsd08;
	}

}
