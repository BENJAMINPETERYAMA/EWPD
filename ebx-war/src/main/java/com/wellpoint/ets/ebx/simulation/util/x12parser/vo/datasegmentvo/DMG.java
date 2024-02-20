package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class DMG implements X12Segment {

	public enum DMGSegmentMetaDataEnum implements SegmentMetaData {
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		DMG01("01",1), DMG02("02",1), DMG03("03",1), DMG04("04",1), DMG05("05",1), DMG06("06",1), DMG07("07",1), 
		DMG08("08",1), DMG09("09",1), DMG10("10",1), DMG11("11",1);

		int repeatCount;
		String position;
		DMGSegmentMetaDataEnum(String position,int repeatCount) {
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
	private String dmg01;
	private String dmg02;
	private String dmg03;
	private String dmg04;
	private String dmg05;
	private String dmg06;
	private String dmg07;
	private String dmg08;
	private String dmg09;
	private String dmg10;

	private String dmg11;

	private String expression;

	public String getDmg01() {
		return dmg01;
	}

	public String getDmg02() {
		return dmg02;
	}

	public String getDmg03() {
		return dmg03;
	}

	public String getDmg04() {
		return dmg04;
	}

	public String getDmg05() {
		return dmg05;
	}

	public String getDmg06() {
		return dmg06;
	}

	public String getDmg07() {
		return dmg07;
	}

	public String getDmg08() {
		return dmg08;
	}

	public String getDmg09() {
		return dmg09;
	}

	public String getDmg10() {
		return dmg10;
	}

	public String getDmg11() {
		return dmg11;
	}

	public String getExpression() {
		return expression;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(DMGSegmentMetaDataEnum.values());
	}

	public void setDmg01(String dmg01) {
		this.dmg01 = dmg01;
	}

	public void setDmg02(String dmg02) {
		this.dmg02 = dmg02;
	}

	public void setDmg03(String dmg03) {
		this.dmg03 = dmg03;
	}

	public void setDmg04(String dmg04) {
		this.dmg04 = dmg04;
	}

	public void setDmg05(String dmg05) {
		this.dmg05 = dmg05;
	}

	public void setDmg06(String dmg06) {
		this.dmg06 = dmg06;
	}

	public void setDmg07(String dmg07) {
		this.dmg07 = dmg07;
	}

	public void setDmg08(String dmg08) {
		this.dmg08 = dmg08;
	}

	public void setDmg09(String dmg09) {
		this.dmg09 = dmg09;
	}

	public void setDmg10(String dmg10) {
		this.dmg10 = dmg10;
	}

	public void setDmg11(String dmg11) {
		this.dmg11 = dmg11;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (DMGSegmentMetaDataEnum.DMG01.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg01((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG02.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg02((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG03.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg03((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG04.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg04((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG05.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg05((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG06.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg06((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG07.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg07((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG08.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg08((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG09.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg09((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG10.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg10((String) value);
		}
		if (DMGSegmentMetaDataEnum.DMG11.equals(segmentMetaData)
				&& value instanceof String) {
			setDmg11((String) value);
		}
	}

}
