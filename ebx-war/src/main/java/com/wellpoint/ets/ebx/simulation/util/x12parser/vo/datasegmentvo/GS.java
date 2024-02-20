package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;



public class GS implements X12Segment{
	public enum GSSegmentMetaDataEnum implements SegmentMetaData{		
		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		GS01("01",1),
		GS02("02",1),
		GS03("03",1),
		GS04("04",1), 
		GS05("05",1),
		GS06("06",1),
		GS07("07",1),
		GS08("08",1);	
		
		int repeatCount;
		String position;
		
		GSSegmentMetaDataEnum (String position, int repeatCount) {
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
	private String gs01;
	private String gs02;
	private String gs03;
	
	private String gs04;
	private String gs05;
	private String gs06;
	private String gs07;
	
	private String gs08;
	
	private String expression;
	
	public GS() {
		
	}
	
	public String getGs01() {
		return gs01;
	}
	public String getGs02() {
		return gs02;
	}

	public String getGs03() {
		return gs03;
	}


	public String getGs04() {
		return gs04;
	}
	
	
	public String getExpression() {
		return expression;
	}
	public SegmentMetaData[] getSegmentMetaData() {		
		return X12ParserUtil.getPositionSortedSegments(GSSegmentMetaDataEnum.values());
	}
	public void setGs01(String gs01) {
		this.gs01 = gs01;
	}
	public void setGs02(String gs02) {
		this.gs02 = gs02;
	}
	public void setGs03(String gs03) {
		this.gs03 = gs03;
	}
	public void setGs04(String gs04) {
		this.gs04 = gs04;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (GSSegmentMetaDataEnum.GS01.equals(segmentMetaData)
				&& value instanceof String) {
			setGs01((String) value);
		}
		if (GSSegmentMetaDataEnum.GS02.equals(segmentMetaData)
				&& value instanceof String) {
			setGs02((String) value);
		}
		if (GSSegmentMetaDataEnum.GS03.equals(segmentMetaData)
				&& value instanceof String) {
			setGs03((String) value);
		}
		if (GSSegmentMetaDataEnum.GS04.equals(segmentMetaData)
				&& value instanceof String) {
			setGs04((String) value);
		}		
		if (GSSegmentMetaDataEnum.GS05.equals(segmentMetaData)
				&& value instanceof String) {
			setGs05((String) value);
		}
		if (GSSegmentMetaDataEnum.GS06.equals(segmentMetaData)
				&& value instanceof String) {
			setGs06((String) value);
		}
		if (GSSegmentMetaDataEnum.GS07.equals(segmentMetaData)
				&& value instanceof String) {
			setGs07((String) value);
		}
		if (GSSegmentMetaDataEnum.GS08.equals(segmentMetaData)
				&& value instanceof String) {
			setGs08((String) value);
		}
	}

	public String getGs05() {
		return gs05;
	}

	public String getGs06() {
		return gs06;
	}

	public String getGs07() {
		return gs07;
	}

	public String getGs08() {
		return gs08;
	}

	public void setGs05(String gs05) {
		this.gs05 = gs05;
	}

	public void setGs06(String gs06) {
		this.gs06 = gs06;
	}

	public void setGs07(String gs07) {
		this.gs07 = gs07;
	}

	public void setGs08(String gs08) {
		this.gs08 = gs08;
	}

}
