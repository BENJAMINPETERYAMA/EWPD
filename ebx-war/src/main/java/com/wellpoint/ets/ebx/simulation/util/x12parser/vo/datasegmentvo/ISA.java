package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;



public class ISA implements X12Segment{
	public enum ISASegmentMetaDataEnum implements SegmentMetaData{		
		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		ISA01("01",1),
		ISA02("02",1),
		ISA03("03",1),
		ISA04("04",1), 
		ISA05("05",1),
		ISA06("06",1),
		ISA07("07",1),
		ISA08("08",1),
		ISA09("09",1),
		ISA10("10",1),
		ISA11("11",1),
		ISA12("12",1), 
		ISA13("13",1),
		ISA14("14",1),
		ISA15("15",1),
		ISA16("16",1);	
		
		int repeatCount;
		String position;
		
		ISASegmentMetaDataEnum (String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		@Override
		public int getRepeatCont() {
			return repeatCount;
		}
		// Create Segment Instance is not Applicable for Data elements
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
			return position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return X12SegmentTypeEnum.DATA_ELEMENT;
		}
	}
	private String isa01;
	private String isa02;
	private String isa03;
	
	private String isa04;
	private String isa05;
	private String isa06;
	private String isa07;
	
	private String isa08;
	
	private String isa09;
	private String isa10;
	private String isa11;
	
	private String isa12;
	private String isa13;
	private String isa14;
	private String isa15;
	
	private String isa16;
	private String expression;
	
	public ISA() {
		
	}
	
	public String getIsa01() {
		return isa01;
	}
	public String getIsa02() {
		return isa02;
	}

	public String getIsa03() {
		return isa03;
	}


	public String getIsa04() {
		return isa04;
	}
	
	
	public String getExpression() {
		return expression;
	}
	public SegmentMetaData[] getSegmentMetaData() {		
		return X12ParserUtil.getPositionSortedSegments(ISASegmentMetaDataEnum.values());
	}
	public void setIsa01(String isa01) {
		this.isa01 = isa01;
	}
	public void setIsa02(String isa02) {
		this.isa02 = isa02;
	}
	public void setIsa03(String isa03) {
		this.isa03 = isa03;
	}
	public void setIsa04(String isa04) {
		this.isa04 = isa04;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (ISASegmentMetaDataEnum.ISA01.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa01((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA02.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa02((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA03.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa03((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA04.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa04((String) value);
		}		
		if (ISASegmentMetaDataEnum.ISA05.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa05((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA06.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa06((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA07.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa07((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA08.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa08((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA09.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa09((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA10.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa10((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA11.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa11((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA12.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa12((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA13.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa13((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA14.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa14((String) value);
		}		
		if (ISASegmentMetaDataEnum.ISA15.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa15((String) value);
		}
		if (ISASegmentMetaDataEnum.ISA16.equals(segmentMetaData)
				&& value instanceof String) {
			setIsa16((String) value);
		}
		
	}

	public String getIsa05() {
		return isa05;
	}

	public String getIsa06() {
		return isa06;
	}

	public String getIsa07() {
		return isa07;
	}

	public String getIsa08() {
		return isa08;
	}

	public void setIsa05(String isa05) {
		this.isa05 = isa05;
	}

	public void setIsa06(String isa06) {
		this.isa06 = isa06;
	}

	public void setIsa07(String isa07) {
		this.isa07 = isa07;
	}

	public void setIsa08(String isa08) {
		this.isa08 = isa08;
	}

	public String getIsa09() {
		return isa09;
	}

	public String getIsa10() {
		return isa10;
	}

	public String getIsa11() {
		return isa11;
	}

	public String getIsa12() {
		return isa12;
	}

	public String getIsa13() {
		return isa13;
	}

	public String getIsa14() {
		return isa14;
	}

	public String getIsa15() {
		return isa15;
	}

	public String getIsa16() {
		return isa16;
	}

	public void setIsa09(String isa09) {
		this.isa09 = isa09;
	}

	public void setIsa10(String isa10) {
		this.isa10 = isa10;
	}

	public void setIsa11(String isa11) {
		this.isa11 = isa11;
	}

	public void setIsa12(String isa12) {
		this.isa12 = isa12;
	}

	public void setIsa13(String isa13) {
		this.isa13 = isa13;
	}

	public void setIsa14(String isa14) {
		this.isa14 = isa14;
	}

	public void setIsa15(String isa15) {
		this.isa15 = isa15;
	}

	public void setIsa16(String isa16) {
		this.isa16 = isa16;
	}

}
