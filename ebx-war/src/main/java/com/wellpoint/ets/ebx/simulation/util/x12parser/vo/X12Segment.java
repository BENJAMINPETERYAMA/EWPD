package com.wellpoint.ets.ebx.simulation.util.x12parser.vo;


public interface X12Segment {
	public String getExpression();

	public void setExpression(String expression);
	
	public SegmentMetaData[] getSegmentMetaData();
	
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value);
	
}
