package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class N4 implements X12Segment{
	private String n401;
	private String n402;
	private String n403;
	private String n404;
	private String n405;
	private String n406;
	private String n407;
	
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(N4SegmentMetaDataEnum.values());
	}

	
	public enum N4SegmentMetaDataEnum implements SegmentMetaData{		
		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		N401("01",1),
		N402("02",1),
		N403("03",1),
		N404("04",1),
		N405("05",1),
		N406("06",1),
		N407("07",1);	
		
		int repeatCount;
		String position;
		
		N4SegmentMetaDataEnum (String position, int repeatCount) {
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
		if (N4SegmentMetaDataEnum.N401.equals(segmentMetaData)
				&& value instanceof String) {
			setN401((String) value);
		}
		if (N4SegmentMetaDataEnum.N402.equals(segmentMetaData)
				&& value instanceof String) {
			setN402((String) value);
		}
		if (N4SegmentMetaDataEnum.N403.equals(segmentMetaData)
				&& value instanceof String) {
			setN403((String) value);
		}
		if (N4SegmentMetaDataEnum.N404.equals(segmentMetaData)
				&& value instanceof String) {
			setN404((String) value);
		}
		if (N4SegmentMetaDataEnum.N405.equals(segmentMetaData)
				&& value instanceof String) {
			setN405((String) value);
		}
		if (N4SegmentMetaDataEnum.N406.equals(segmentMetaData)
				&& value instanceof String) {
			setN406((String) value);
		}
		if (N4SegmentMetaDataEnum.N407.equals(segmentMetaData)
				&& value instanceof String) {
			setN407((String) value);
		}		
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getN401() {
		return n401;
	}
	public void setN401(String n401) {
		this.n401 = n401;
	}
	public String getN402() {
		return n402;
	}
	public void setN402(String n402) {
		this.n402 = n402;
	}
	public String getN403() {
		return n403;
	}
	public void setN403(String n403) {
		this.n403 = n403;
	}
	public String getN404() {
		return n404;
	}
	public void setN404(String n404) {
		this.n404 = n404;
	}
	public String getN405() {
		return n405;
	}
	public void setN405(String n405) {
		this.n405 = n405;
	}
	public String getN406() {
		return n406;
	}
	public void setN406(String n406) {
		this.n406 = n406;
	}
	public String getN407() {
		return n407;
	}
	public void setN407(String n407) {
		this.n407 = n407;
	}
	
	
}
