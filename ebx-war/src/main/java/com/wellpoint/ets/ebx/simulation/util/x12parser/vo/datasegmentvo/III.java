package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class III implements X12Segment {
	public enum IIISegmentMetaDataEnum implements SegmentMetaData {
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		III01("01",1), 
		III02("02",1), 
		III03("03",1), 
		III04("04",1), 
		III05("05",1), 
		III06("06",1), 
		III07("07",1), 
		III08("08",1), 
		III09("09",1);

		int repeatCount;
		String position;
		IIISegmentMetaDataEnum(String position, int repeatCount) {
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
	private String iii01;
	private String iii02;
	private String iii03;
	private String iii04;
	private String iii05;
	private String iii06;
	private String iii07;

	private String iii08;

	private String iii09;

	public String getExpression() {
		return expression;
	}

	public String getIii01() {
		return iii01;
	}

	public String getIii02() {
		return iii02;
	}
	public String getIii02Desc() {
		String desc = "";
		if (null != iii02) {
			desc = X12ParserUtil.getX12Description("III02_"+iii02.trim());
		}
		return desc;
	}
	public String getIii03() {
		return iii03;
	}

	public String getIii04() {
		return iii04;
	}

	public String getIii05() {
		return iii05;
	}

	public String getIii06() {
		return iii06;
	}

	public String getIii07() {
		return iii07;
	}

	public String getIii08() {
		return iii08;
	}

	public String getIii09() {
		return iii09;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(IIISegmentMetaDataEnum.values());
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (IIISegmentMetaDataEnum.III01.equals(segmentMetaData)
				&& value instanceof String) {
			setIii01((String) value);
		}
		if (IIISegmentMetaDataEnum.III02.equals(segmentMetaData)
				&& value instanceof String) {
			setIii02((String) value);
		}
		if (IIISegmentMetaDataEnum.III03.equals(segmentMetaData)
				&& value instanceof String) {
			setIii03((String) value);
		}
		if (IIISegmentMetaDataEnum.III04.equals(segmentMetaData)
				&& value instanceof String) {
			setIii04((String) value);
		}
		if (IIISegmentMetaDataEnum.III05.equals(segmentMetaData)
				&& value instanceof String) {
			setIii05((String) value);
		}
		if (IIISegmentMetaDataEnum.III06.equals(segmentMetaData)
				&& value instanceof String) {
			setIii06((String) value);
		}
		if (IIISegmentMetaDataEnum.III07.equals(segmentMetaData)
				&& value instanceof String) {
			setIii07((String) value);
		}
		if (IIISegmentMetaDataEnum.III08.equals(segmentMetaData)
				&& value instanceof String) {
			setIii08((String) value);
		}
		if (IIISegmentMetaDataEnum.III09.equals(segmentMetaData)
				&& value instanceof String) {
			setIii09((String) value);
		}
	}

	public void setIii01(String iii01) {
		this.iii01 = iii01;
	}

	public void setIii02(String iii02) {
		this.iii02 = iii02;
	}

	public void setIii03(String iii03) {
		this.iii03 = iii03;
	}

	public void setIii04(String iii04) {
		this.iii04 = iii04;
	}

	public void setIii05(String iii05) {
		this.iii05 = iii05;
	}

	public void setIii06(String iii06) {
		this.iii06 = iii06;
	}

	public void setIii07(String iii07) {
		this.iii07 = iii07;
	}

	public void setIii08(String iii08) {
		this.iii08 = iii08;
	}

	public void setIii09(String iii09) {
		this.iii09 = iii09;
	}

}
