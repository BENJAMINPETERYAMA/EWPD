package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import java.util.StringTokenizer;

import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class EB implements X12Segment {
	public enum EBSegmentMetaDataEnum implements SegmentMetaData {
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		EB01("01",1), 
		EB02("02",1), 
		EB03("03",1), 
		EB04("04",1), 
		EB05("05",1), 
		EB06("06",1), 
		EB07("07",1), 
		EB08("08",1), 
		EB09("09",1), 
		EB10("10",1), 
		EB11("11",1), 
		EB12("12",1), 
		EB13("13",1), 
		EB14("14",1);

		int repeatCount;
		String position;
		EBSegmentMetaDataEnum(String position, int repeatCount) {
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
	private String eb01;
	private String eb02;
	private String eb03;
	private String eb04;
	private String eb05;
	private String eb06;
	private String eb07;
	private String eb08;
	private String eb09;
	private String eb10;
	private String eb11;
	private String eb12;
	private String eb13;

	private String eb14;

	private String expression;

	public String getEb01() {
		return eb01;
	}

	public String getEb01Desc() {
		String desc = "";
		if (null != eb01) {
			desc = X12ParserUtil.getX12Description("EB01_"+eb01.trim());
		}
		return desc;
	}
	
	public String getEb02() {
		return eb02;
	}
	public String getEb02Desc() {
		String desc = "";
		if (null != eb02) {
			desc = X12ParserUtil.getX12Description("EB02_"+eb02.trim());
		}
		return desc;
	}
	public String getEb03() {
		return eb03;
	}
	public String getEb03Desc() {
		String desc = "";
		if (null != eb03) {
			StringTokenizer st = new StringTokenizer(eb03,
					"{", false);
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				if (null != token && !token.isEmpty()) {
					if(desc.trim().length()!=0) {
						desc = desc + " , ";
					}
					desc = desc + X12ParserUtil.getX12Description("EB03_"+token.trim());
				}
			}
		}
		return desc;
	}
	public String getEb04() {
		return eb04;
	}
	public String getEb04Desc() {
		String desc = "";
		if (null != eb04) {
			desc = X12ParserUtil.getX12Description("EB04_"+eb04.trim());
		}
		return desc;
	}
	public String getEb05() {
		return eb05;
	}

	public String getEb06() {
		return eb06;
	}

	public String getEb06Desc() {
		String desc = "";
		if (null != eb06) {
			desc = X12ParserUtil.getX12Description("EB06_"+eb06.trim());
		}
		return desc;
	}
	
	public String getEb07() {
		return eb07;
	}

	public String getEb08() {
		return eb08;
	}

	public String getEb09() {
		return eb09;
	}

	public String getEb09Desc() {
		String desc = "";
		if (null != eb09) {
			desc = X12ParserUtil.getX12Description("EB09_"+eb09.trim());
		}
		return desc;
	}
	
	public String getEb10() {
		return eb10;
	}

	public String getEb11() {
		return eb11;
	}
	public String getEb11Desc() {
		String desc = "";
		if (null != eb11) {
			desc = X12ParserUtil.getX12Description("EB11_"+eb11.trim());
		}
		return desc;
	}	
	public String getEb12() {
		return eb12;
	}
	public String getEb12Desc() {
		String desc = "";
		if (null != eb12) {
			desc = X12ParserUtil.getX12Description("EB12_"+eb12.trim());
		}
		return desc;
	}
	public String getEb13() {
		return eb13;
	}

	public String getEb14() {
		return eb14;
	}

	public String getExpression() {
		return expression;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(EBSegmentMetaDataEnum.values());
	}

	public void setEb01(String eb01) {
		this.eb01 = eb01;
	}

	public void setEb02(String eb02) {
		this.eb02 = eb02;
	}

	public void setEb03(String eb03) {
		this.eb03 = eb03;
	}

	public void setEb04(String eb04) {
		this.eb04 = eb04;
	}

	public void setEb05(String eb05) {
		this.eb05 = eb05;
	}

	public void setEb06(String eb06) {
		this.eb06 = eb06;
	}

	public void setEb07(String eb07) {
		this.eb07 = eb07;
	}

	public void setEb08(String eb08) {
		this.eb08 = eb08;
	}

	public void setEb09(String eb09) {
		this.eb09 = eb09;
	}

	public void setEb10(String eb10) {
		this.eb10 = eb10;
	}

	public void setEb11(String eb11) {
		this.eb11 = eb11;
	}

	public void setEb12(String eb12) {
		this.eb12 = eb12;
	}

	public void setEb13(String eb13) {
		this.eb13 = eb13;
	}

	public void setEb14(String eb14) {
		this.eb14 = eb14;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (EBSegmentMetaDataEnum.EB01.equals(segmentMetaData)
				&& value instanceof String) {
			setEb01((String) value);
		}
		if (EBSegmentMetaDataEnum.EB02.equals(segmentMetaData)
				&& value instanceof String) {
			setEb02((String) value);
		}
		if (EBSegmentMetaDataEnum.EB03.equals(segmentMetaData)
				&& value instanceof String) {
			setEb03((String) value);
		}
		if (EBSegmentMetaDataEnum.EB04.equals(segmentMetaData)
				&& value instanceof String) {
			setEb04((String) value);
		}
		if (EBSegmentMetaDataEnum.EB05.equals(segmentMetaData)
				&& value instanceof String) {
			setEb05((String) value);
		}
		if (EBSegmentMetaDataEnum.EB06.equals(segmentMetaData)
				&& value instanceof String) {
			setEb06((String) value);
		}
		if (EBSegmentMetaDataEnum.EB07.equals(segmentMetaData)
				&& value instanceof String) {
			setEb07((String) value);
		}
		if (EBSegmentMetaDataEnum.EB08.equals(segmentMetaData)
				&& value instanceof String) {
			setEb08((String) value);
		}
		if (EBSegmentMetaDataEnum.EB09.equals(segmentMetaData)
				&& value instanceof String) {
			setEb09((String) value);
		}
		if (EBSegmentMetaDataEnum.EB10.equals(segmentMetaData)
				&& value instanceof String) {
			setEb10((String) value);
		}
		if (EBSegmentMetaDataEnum.EB11.equals(segmentMetaData)
				&& value instanceof String) {
			setEb11((String) value);
		}
		if (EBSegmentMetaDataEnum.EB12.equals(segmentMetaData)
				&& value instanceof String) {
			setEb12((String) value);
		}
		if (EBSegmentMetaDataEnum.EB13.equals(segmentMetaData)
				&& value instanceof String) {
			setEb13((String) value);
		}
		if (EBSegmentMetaDataEnum.EB14.equals(segmentMetaData)
				&& value instanceof String) {
			setEb14((String) value);
		}
	}

}
