package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class HI implements X12Segment {
	public enum HISegmentMetaDataEnum implements SegmentMetaData {
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		HI01("01",1), 
		HI02("02",1),
		HI03("03",1), 
		HI04("04",1), 
		HI05("05",1), 
		HI06("06",1), 
		HI07("07",1), 
		HI08("08",1), 
		HI09("09",1), 
		HI10("10",1), 
		HI11("11",1), 
		HI12("12",1);

		int repeatCount;
		String position;
		HISegmentMetaDataEnum(String position, int repeatCount) {
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
	private String hi01;
	private String hi02;
	private String hi03;
	private String hi04;
	private String hi05;
	private String hi06;
	private String hi07;
	private String hi08;
	private String hi09;
	private String hi10;
	private String hi11;
	private String hi12;

	public String getExpression() {
		return expression;
	}

	public String getHi01() {
		return hi01;
	}

	public String getHi02() {
		return hi02;
	}

	public String getHi03() {
		return hi03;
	}

	public String getHi04() {
		return hi04;
	}

	public String getHi05() {
		return hi05;
	}

	public String getHi06() {
		return hi06;
	}

	public String getHi07() {
		return hi07;
	}

	public String getHi08() {
		return hi08;
	}

	public String getHi09() {
		return hi09;
	}

	public String getHi10() {
		return hi10;
	}

	public String getHi11() {
		return hi11;
	}

	public String getHi12() {
		return hi12;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(HISegmentMetaDataEnum.values());
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (HISegmentMetaDataEnum.HI01.equals(segmentMetaData)
				&& value instanceof String) {
			setHi01((String) value);
		}
		if (HISegmentMetaDataEnum.HI02.equals(segmentMetaData)
				&& value instanceof String) {
			setHi02((String) value);
		}
		if (HISegmentMetaDataEnum.HI03.equals(segmentMetaData)
				&& value instanceof String) {
			setHi03((String) value);
		}
		if (HISegmentMetaDataEnum.HI04.equals(segmentMetaData)
				&& value instanceof String) {
			setHi04((String) value);
		}
		if (HISegmentMetaDataEnum.HI05.equals(segmentMetaData)
				&& value instanceof String) {
			setHi05((String) value);
		}
		if (HISegmentMetaDataEnum.HI06.equals(segmentMetaData)
				&& value instanceof String) {
			setHi06((String) value);
		}
		if (HISegmentMetaDataEnum.HI07.equals(segmentMetaData)
				&& value instanceof String) {
			setHi07((String) value);
		}
		if (HISegmentMetaDataEnum.HI08.equals(segmentMetaData)
				&& value instanceof String) {
			setHi08((String) value);
		}
		if (HISegmentMetaDataEnum.HI09.equals(segmentMetaData)
				&& value instanceof String) {
			setHi09((String) value);
		}
		if (HISegmentMetaDataEnum.HI10.equals(segmentMetaData)
				&& value instanceof String) {
			setHi10((String) value);
		}
		if (HISegmentMetaDataEnum.HI11.equals(segmentMetaData)
				&& value instanceof String) {
			setHi11((String) value);
		}
		if (HISegmentMetaDataEnum.HI12.equals(segmentMetaData)
				&& value instanceof String) {
			setHi12((String) value);
		}
	}

	public void setHi01(String hi01) {
		this.hi01 = hi01;
	}

	public void setHi02(String hi02) {
		this.hi02 = hi02;
	}

	public void setHi03(String hi03) {
		this.hi03 = hi03;
	}

	public void setHi04(String hi04) {
		this.hi04 = hi04;
	}

	public void setHi05(String hi05) {
		this.hi05 = hi05;
	}

	public void setHi06(String hi06) {
		this.hi06 = hi06;
	}

	public void setHi07(String hi07) {
		this.hi07 = hi07;
	}

	public void setHi08(String hi08) {
		this.hi08 = hi08;
	}

	public void setHi09(String hi09) {
		this.hi09 = hi09;
	}

	public void setHi10(String hi10) {
		this.hi10 = hi10;
	}

	public void setHi11(String hi11) {
		this.hi11 = hi11;
	}

	public void setHi12(String hi12) {
		this.hi12 = hi12;
	}

}
