package com.wellpoint.ets.ebx.simulation.util.x12parser.vo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;

public interface SegmentMetaData {
	public String getSementIdentifier() ;
	public int getRepeatCont();
	public X12Segment createSegmentInstance() throws X12ParserException;
	public String getSegmentPosition();
	public X12SegmentTypeEnum getSegmentType();
}
