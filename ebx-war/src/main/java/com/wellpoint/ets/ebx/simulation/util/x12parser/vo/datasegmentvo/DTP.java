package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class DTP implements X12Segment {
	public enum DTPSegmentMetaDataEnum implements SegmentMetaData {
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		DTP01("01",1), 
		DTP02("02",1), 
		DTP03("03",1);

		int repeatCount;
		String position;
		DTPSegmentMetaDataEnum(String position, int repeatCount) {
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
	private String dtp01;
	private String dtp02;

	private String dtp03;

	private String expression;

	public String getDtp01() {
		return dtp01;
	}
	public String getDtp01Desc() {
		String desc = "";
		if (null != dtp01) {
			desc = X12ParserUtil.getX12Description("DTP01_"+dtp01.trim());
		}
		return desc;
	}
	public String getDtp02() {
		return dtp02;
	}

	public String getDtp03() {
		return dtp03;
	}

	public String getExpression() {
		return expression;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(DTPSegmentMetaDataEnum.values());
	}

	public void setDtp01(String dtp01) {
		this.dtp01 = dtp01;
	}

	public void setDtp02(String dtp02) {
		this.dtp02 = dtp02;
	}

	public void setDtp03(String dtp03) {
		this.dtp03 = dtp03;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (DTPSegmentMetaDataEnum.DTP01.equals(segmentMetaData)
				&& value instanceof String) {
			setDtp01((String) value);
		}
		if (DTPSegmentMetaDataEnum.DTP02.equals(segmentMetaData)
				&& value instanceof String) {
			setDtp02((String) value);
		}
		if (DTPSegmentMetaDataEnum.DTP03.equals(segmentMetaData)
				&& value instanceof String) {
			setDtp03((String) value);
		}

	}

}
