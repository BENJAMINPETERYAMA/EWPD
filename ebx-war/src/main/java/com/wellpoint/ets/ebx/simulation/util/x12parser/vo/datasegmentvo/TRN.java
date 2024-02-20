package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class TRN implements X12Segment{
	private String trn01;
	private String trn02;
	private String trn03;
	private String trn04;
	public TRN () {
		
	}
	
	public TRN (String expression) {
		
	}
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(TRNSegmentMetaDataEnum.values());
	}

	
	public enum TRNSegmentMetaDataEnum implements SegmentMetaData{		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		TRN01("01",1),
		TRN02("02",1),
		TRN03("03",1),
		TRN04("04",1);	
		
		int repeatCount;
		String position;
		
		TRNSegmentMetaDataEnum (String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		
		public String getSementIdentifier() {
			return this.name();	
		}
		public int getRepeatCont() {
			return repeatCount;
		}
		public X12Segment createSegmentInstance() {			
			return null;
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

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (TRNSegmentMetaDataEnum.TRN01.equals(segmentMetaData)
				&& value instanceof String) {
			setTrn01((String) value);
		}
		if (TRNSegmentMetaDataEnum.TRN02.equals(segmentMetaData)
				&& value instanceof String) {
			setTrn02((String) value);
		}
		if (TRNSegmentMetaDataEnum.TRN03.equals(segmentMetaData)
				&& value instanceof String) {
			setTrn03((String) value);
		}
		if (TRNSegmentMetaDataEnum.TRN04.equals(segmentMetaData)
				&& value instanceof String) {
			setTrn04((String) value);
		}
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getTrn01() {
		return trn01;
	}
	public void setTrn01(String trn01) {
		this.trn01 = trn01;
	}
	public String getTrn02() {
		return trn02;
	}
	public void setTrn02(String trn02) {
		this.trn02 = trn02;
	}
	public String getTrn03() {
		return trn03;
	}
	public void setTrn03(String trn03) {
		this.trn03 = trn03;
	}
	public String getTrn04() {
		return trn04;
	}
	public void setTrn04(String trn04) {
		this.trn04 = trn04;
	}
	
	
}
