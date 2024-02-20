package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class LE implements X12Segment {
	public enum LESegmentMetaDataEnum implements SegmentMetaData {
	// Syntax - Seg Id ( Pos, Max Use) 
	// Pos will be used for sorting the segments.
		LE01("01",1);

		int repeatCount;
		String position;
		LESegmentMetaDataEnum(String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		@Override
		public int getRepeatCont() {
			return repeatCount;
		}
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
			return this.position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return X12SegmentTypeEnum.DATA_ELEMENT;
		}
	}

	private String expression;

	private String le01;

	public String getExpression() {
		return expression;
	}

	public String getLe01() {
		return le01;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(LESegmentMetaDataEnum.values());
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (LESegmentMetaDataEnum.LE01.equals(segmentMetaData)
				&& value instanceof String) {
			setLe01((String) value);
		}
	}

	public void setLe01(String le01) {
		this.le01 = le01;
	}

}
