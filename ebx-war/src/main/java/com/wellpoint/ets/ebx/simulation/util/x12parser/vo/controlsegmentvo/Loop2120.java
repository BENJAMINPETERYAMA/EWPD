package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.N3;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.N4;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.NM1;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.PER;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.PRV;

public class Loop2120 implements X12Segment{

	private NM1 nm1;
	private N3 n3;
	private N4 n4;
	private List<PER> per;
	private PRV prv;
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(Loop2120SegmentMetaDataEnum.values());
	}

	public enum Loop2120SegmentMetaDataEnum implements SegmentMetaData{		
		
		/**************************************************
		| Pos   | Seg Id    | Name                              	| Max Use|
		|--------------------------------------------------------------------|  
		| 01 	|	NM1		| Individual or Organizational Name 	|	1	 |
		| 02 	|	N3 		| Party Location 						|	1	 |
		| 03 	|	N4 		| Geographic Location 					|	1	 |
		| 04 	|	PER 	| Administrative Communications Contact |	3	 |
		| 05 	|	PRV 	| Provider Information 					|	1	 |
		**********************************************************************/

// Syntax - 
//  Seg Id ( Pos   , Max Use,  Data type , Segment Type) 
		NM1("01" ,    1   ,  NM1.class	 ,  X12SegmentTypeEnum.DATA_SEGMENT), 
		N3( "02" ,    1   ,  N3.class	 ,  X12SegmentTypeEnum.DATA_SEGMENT), 
		N4( "03" ,    1   ,  N4.class	 ,  X12SegmentTypeEnum.DATA_SEGMENT), 
		PER("04" ,    3   ,  PER.class	 ,  X12SegmentTypeEnum.DATA_SEGMENT), 
		PRV("05" ,    1   ,  PRV.class	 ,  X12SegmentTypeEnum.DATA_SEGMENT);	
		
		int repeatCount;
		String position;
		Class<? extends X12Segment> x12SegmentClass;
		X12SegmentTypeEnum segmentTypeEnum;
		
		Loop2120SegmentMetaDataEnum (String position, int maxUse, Class<? extends X12Segment> x12SegmentClass,  X12SegmentTypeEnum X12SegmentTypeEnum) {
			this.repeatCount = maxUse;
			this.position = position;
			this.x12SegmentClass =  x12SegmentClass;
			this.segmentTypeEnum = X12SegmentTypeEnum;
		}
		public String getSementIdentifier() {
			return this.name()+"*";	
		}
		public int getRepeatCont() {
			return repeatCount;
		}
		@Override
		public X12Segment createSegmentInstance() throws X12ParserException {
			return X12ParserUtil.createInstance(x12SegmentClass);
		}
		@Override
		public String getSegmentPosition() {
			return this.position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return this.segmentTypeEnum;
		}
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (Loop2120SegmentMetaDataEnum.NM1.equals(segmentMetaData)
				&& value instanceof NM1) {
			setNm1((NM1) value);
		}
		if (Loop2120SegmentMetaDataEnum.N3.equals(segmentMetaData)
				&& value instanceof N3) {
			setN3((N3) value);
		}
		if (Loop2120SegmentMetaDataEnum.N4.equals(segmentMetaData)
				&& value instanceof N4) {
			setN4((N4) value);
		}
		if (Loop2120SegmentMetaDataEnum.PER.equals(segmentMetaData)
				&& value instanceof PER) {
			if(null == getPer()) {
				setPer(new ArrayList<PER>());
			}
			getPer().add((PER)value);
		}
		if (Loop2120SegmentMetaDataEnum.PRV.equals(segmentMetaData)
				&& value instanceof PRV) {
			setPrv((PRV) value);
		}
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public NM1 getNm1() {
		return nm1;
	}
	public void setNm1(NM1 nm1) {
		this.nm1 = nm1;
	}
	public N3 getN3() {
		return n3;
	}
	public void setN3(N3 n3) {
		this.n3 = n3;
	}
	public N4 getN4() {
		return n4;
	}
	public void setN4(N4 n4) {
		this.n4 = n4;
	}
	public List<PER> getPer() {
		return per;
	}
	public void setPer(List<PER> per) {
		this.per = per;
	}
	public PRV getPrv() {
		return prv;
	}
	public void setPrv(PRV prv) {
		this.prv = prv;
	}

	
}
