package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.AAA;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.DTP;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.EB;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.HSD;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.LE;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.LS;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.MSG;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.REF;

public class Loop2110 implements X12Segment{

	private EB eb;
	private List<HSD> hsd;
	private List<REF> ref;
	private List<DTP> dtp;
	private List<AAA> aaa;
	private List<MSG> msg;
	private List<Loop2115> loop2115;
	private LS ls;
	private List<Loop2120> loop2120;
	private LE le;

	private String expression;
	
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(Loop2110SegmentMetaDatEnum.values());
	}

	public enum Loop2110SegmentMetaDatEnum implements SegmentMetaData{		
		/**************************************************
		| Pos   | Seg Id    |  Name                                 | Max Use |
		|---------------------------------------------------------------------|  
		| 01 	| EB		|  Eligibility or Benefit Information	| 	1	  |
		| 02 	| HSD		|  Health Care Services Delivery		|  	9	  |
		| 03 	| REF		|  Reference Information				|  	9	  |
		| 04    | DTP		|  Date or Time or Period				| 	20	  |
		| 05    | AAA		|  Request Validation					| 	9	  |
		| 06    | MSG		|  Message Text							| 	10	  |
		| 07    | LOOP2115	| 										| 	  	  |
		| 08    | LS		|  Loop Header 							|  	1	  |
		| 09    | LOOP2120	| 										|    	  |
		| 10    | LE		|  Loop Trailer							|  	1	  |
		***********************************************************************/
		
// Syntax - 
// 		Seg Id (  Pos   ,Max Use, Data type	  	 , Segment Type) 
//-----------------------------------------------------------
		EB(		 "01"	,	1	, EB.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT), 
		HSD(	 "02"	,	9	, HSD.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT), 
		REF(	 "03"	,	9	, REF.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT), 
		DTP(	 "04"	,	20	, DTP.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT), 
		AAA(	 "05"	,	9	, AAA.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT), 
		MSG(	 "06"	,	10	, MSG.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT),
		LOOP2115("07"   ,	0	, Loop2115.class , X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT),
		LS(		 "08"	,	1	, LS.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT),
		LOOP2120("09"   ,	0	, Loop2120.class , X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT),
		LE(		 "10"	,	1	, LE.class	  	 , X12SegmentTypeEnum.DATA_SEGMENT);	
		
		int repeatCount;
		String position;
		
		Class<? extends X12Segment> x12SegmentClass;
		X12SegmentTypeEnum segmentTypeEnum;

		Loop2110SegmentMetaDatEnum (String position, int maxUse, Class<? extends X12Segment> x12SegmentClass , X12SegmentTypeEnum X12SegmentTypeEnum) {
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
		if (Loop2110SegmentMetaDatEnum.EB.equals(segmentMetaData)
				&& value instanceof EB) {
			setEb((EB) value);
		}
		if (Loop2110SegmentMetaDatEnum.HSD.equals(segmentMetaData)
				&& value instanceof HSD) {
			if(null == getHsd()) {
				setHsd(new ArrayList<HSD>());
			}
			getHsd().add((HSD)value);
		}
		if (Loop2110SegmentMetaDatEnum.REF.equals(segmentMetaData)
				&& value instanceof REF) {
			if(null == getRef()) {
				setRef(new ArrayList<REF>());
			}
			getRef().add((REF)value);
		}
		if (Loop2110SegmentMetaDatEnum.DTP.equals(segmentMetaData)
				&& value instanceof DTP) {
			if(null == getDtp()) {
				setDtp(new ArrayList<DTP>());
			}
			getDtp().add((DTP)value);
		}
		if (Loop2110SegmentMetaDatEnum.AAA.equals(segmentMetaData)
				&& value instanceof AAA) {
			if(null == getAaa()) {
				setAaa(new ArrayList<AAA>());
			}
			getAaa().add((AAA)value);
		}
		if (Loop2110SegmentMetaDatEnum.MSG.equals(segmentMetaData)
				&& value instanceof MSG) {
			if(null == getMsg()) {
				setMsg(new ArrayList<MSG>());
			}
			getMsg().add((MSG)value);
		}
		if (Loop2110SegmentMetaDatEnum.LOOP2115.equals(segmentMetaData)
				&& value instanceof Loop2115) {
			if(null == getLoop2115()) {
				setLoop2115(new ArrayList<Loop2115>());
			}
			getLoop2115().add((Loop2115)value);
		}
		if (Loop2110SegmentMetaDatEnum.LS.equals(segmentMetaData)
				&& value instanceof LS) {
			setLs((LS) value);
		}
		if (Loop2110SegmentMetaDatEnum.LOOP2120.equals(segmentMetaData)
				&& value instanceof Loop2120) {
			if(null == getLoop2120()) {
				setLoop2120(new ArrayList<Loop2120>());
			}
			getLoop2120().add((Loop2120)value);
		}
		if (Loop2110SegmentMetaDatEnum.LE.equals(segmentMetaData)
				&& value instanceof LE) {
			setLe((LE) value);
		}
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}


	public EB getEb() {
		return eb;
	}


	public void setEb(EB eb) {
		this.eb = eb;
	}


	public List<HSD> getHsd() {
		return hsd;
	}


	public void setHsd(List<HSD> hsd) {
		this.hsd = hsd;
	}


	public List<REF> getRef() {
		return ref;
	}


	public void setRef(List<REF> ref) {
		this.ref = ref;
	}


	public List<DTP> getDtp() {
		return dtp;
	}


	public void setDtp(List<DTP> dtp) {
		this.dtp = dtp;
	}


	public List<AAA> getAaa() {
		return aaa;
	}


	public void setAaa(List<AAA> aaa) {
		this.aaa = aaa;
	}


	public List<MSG> getMsg() {
		return msg;
	}


	public void setMsg(List<MSG> msg) {
		this.msg = msg;
	}


	public List<Loop2115> getLoop2115() {
		return loop2115;
	}


	public void setLoop2115(List<Loop2115> loop2115) {
		this.loop2115 = loop2115;
	}


	public LS getLs() {
		return ls;
	}


	public void setLs(LS ls) {
		this.ls = ls;
	}


	public List<Loop2120> getLoop2120() {
		return loop2120;
	}


	public void setLoop2120(List<Loop2120> loop2120) {
		this.loop2120 = loop2120;
	}


	public LE getLe() {
		return le;
	}


	public void setLe(LE le) {
		this.le = le;
	}


	
	
}
