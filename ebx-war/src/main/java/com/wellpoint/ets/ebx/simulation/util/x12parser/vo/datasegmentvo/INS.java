package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class INS implements X12Segment {
	public enum INSSegmentMetaDataEnum implements SegmentMetaData {
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		INS01("01",1), 
		INS02("02",1), 
		INS03("03",1), 
		INS04("04",1), 
		INS05("05",1), 
		INS06("06",1), 
		INS07("07",1), 
		INS08("08",1), 
		INS09("09",1), 
		INS10("10",1), 
		INS11("11",1), 
		INS12("12",1), 
		INS13("13",1), 
		INS14("14",1), 
		INS15("15",1), 
		INS16("16",1), 
		INS17("17",1);

		int repeatCount;
		String position;
		INSSegmentMetaDataEnum(String position,int repeatCount) {
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
	private String ins01;
	private String ins02;
	private String ins03;
	private String ins04;
	private String ins05;
	private String ins06;
	private String ins07;
	private String ins08;
	private String ins09;
	private String ins10;
	private String ins11;
	private String ins12;
	private String ins13;
	private String ins14;
	private String ins15;
	private String ins16;
	private String ins17;

	public String getExpression() {
		return expression;
	}

	public String getIns01() {
		return ins01;
	}

	public String getIns02() {
		return ins02;
	}

	public String getIns03() {
		return ins03;
	}

	public String getIns04() {
		return ins04;
	}

	public String getIns05() {
		return ins05;
	}

	public String getIns06() {
		return ins06;
	}

	public String getIns07() {
		return ins07;
	}

	public String getIns08() {
		return ins08;
	}

	public String getIns09() {
		return ins09;
	}

	public String getIns10() {
		return ins10;
	}

	public String getIns11() {
		return ins11;
	}

	public String getIns12() {
		return ins12;
	}

	public String getIns13() {
		return ins13;
	}

	public String getIns14() {
		return ins14;
	}

	public String getIns15() {
		return ins15;
	}

	public String getIns16() {
		return ins16;
	}

	public String getIns17() {
		return ins17;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(INSSegmentMetaDataEnum.values());
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (INSSegmentMetaDataEnum.INS01.equals(segmentMetaData)
				&& value instanceof String) {
			setIns01((String) value);
		}
		if (INSSegmentMetaDataEnum.INS02.equals(segmentMetaData)
				&& value instanceof String) {
			setIns02((String) value);
		}
		if (INSSegmentMetaDataEnum.INS03.equals(segmentMetaData)
				&& value instanceof String) {
			setIns03((String) value);
		}
		if (INSSegmentMetaDataEnum.INS04.equals(segmentMetaData)
				&& value instanceof String) {
			setIns04((String) value);
		}
		if (INSSegmentMetaDataEnum.INS05.equals(segmentMetaData)
				&& value instanceof String) {
			setIns05((String) value);
		}
		if (INSSegmentMetaDataEnum.INS06.equals(segmentMetaData)
				&& value instanceof String) {
			setIns06((String) value);
		}
		if (INSSegmentMetaDataEnum.INS07.equals(segmentMetaData)
				&& value instanceof String) {
			setIns07((String) value);
		}
		if (INSSegmentMetaDataEnum.INS08.equals(segmentMetaData)
				&& value instanceof String) {
			setIns08((String) value);
		}
		if (INSSegmentMetaDataEnum.INS09.equals(segmentMetaData)
				&& value instanceof String) {
			setIns09((String) value);
		}
		if (INSSegmentMetaDataEnum.INS10.equals(segmentMetaData)
				&& value instanceof String) {
			setIns10((String) value);
		}
		if (INSSegmentMetaDataEnum.INS11.equals(segmentMetaData)
				&& value instanceof String) {
			setIns11((String) value);
		}
		if (INSSegmentMetaDataEnum.INS12.equals(segmentMetaData)
				&& value instanceof String) {
			setIns12((String) value);
		}
		if (INSSegmentMetaDataEnum.INS13.equals(segmentMetaData)
				&& value instanceof String) {
			setIns13((String) value);
		}
		if (INSSegmentMetaDataEnum.INS14.equals(segmentMetaData)
				&& value instanceof String) {
			setIns14((String) value);
		}
		if (INSSegmentMetaDataEnum.INS15.equals(segmentMetaData)
				&& value instanceof String) {
			setIns15((String) value);
		}
		if (INSSegmentMetaDataEnum.INS16.equals(segmentMetaData)
				&& value instanceof String) {
			setIns16((String) value);
		}
		if (INSSegmentMetaDataEnum.INS17.equals(segmentMetaData)
				&& value instanceof String) {
			setIns17((String) value);
		}
	}

	public void setIns01(String ins01) {
		this.ins01 = ins01;
	}

	public void setIns02(String ins02) {
		this.ins02 = ins02;
	}

	public void setIns03(String ins03) {
		this.ins03 = ins03;
	}

	public void setIns04(String ins04) {
		this.ins04 = ins04;
	}

	public void setIns05(String ins05) {
		this.ins05 = ins05;
	}

	public void setIns06(String ins06) {
		this.ins06 = ins06;
	}

	public void setIns07(String ins07) {
		this.ins07 = ins07;
	}

	public void setIns08(String ins08) {
		this.ins08 = ins08;
	}

	public void setIns09(String ins09) {
		this.ins09 = ins09;
	}

	public void setIns10(String ins10) {
		this.ins10 = ins10;
	}

	public void setIns11(String ins11) {
		this.ins11 = ins11;
	}

	public void setIns12(String ins12) {
		this.ins12 = ins12;
	}

	public void setIns13(String ins13) {
		this.ins13 = ins13;
	}

	public void setIns14(String ins14) {
		this.ins14 = ins14;
	}

	public void setIns15(String ins15) {
		this.ins15 = ins15;
	}

	public void setIns16(String ins16) {
		this.ins16 = ins16;
	}

	public void setIns17(String ins17) {
		this.ins17 = ins17;
	}

}
