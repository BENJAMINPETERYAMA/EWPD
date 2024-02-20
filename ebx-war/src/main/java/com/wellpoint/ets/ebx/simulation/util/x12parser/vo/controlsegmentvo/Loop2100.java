package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.AAA;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.DMG;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.DTP;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.HI;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.INS;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.MPI;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.N3;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.N4;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.NM1;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.PER;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.PRV;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.REF;

public class Loop2100 implements X12Segment {

	public enum Loop2100SegmentMetaDataEnum implements SegmentMetaData {
		/**************************************************
		| Pos   | Seg Id   | Name                                  | Max Use |
		|--------------------------------------------------------------------|  
		| 01    | NM1      | Individual or Organizational Name     | 	1	 |
		| 02    | REF      | Reference Information                 | 	9    |
		| 03    | N3 	   | Party Location                        |	1    |
		| 04    | N4       | Geographic Location               	   |	1    |
		| 05    | PER      | Administrative Communications Contact | 	3    |
		| 06    | AAA      | Request Validation 				   | 	9    |
		| 07    | PRV      | Provider Information                  | 	1    |
		| 08    | DMG      | Demographic Information               | 	1    |
		| 09    | INS      | Insured Benefit                       | 	1    |
		| 10    | HI       | Health Care Information Codes         | 	1    |
		| 11    | DTP      | Date or Time or Period                | 	9    |
		| 12    | MPI      | Military Personnel Information        | 	9    |
		| 13    | LOOP2110 |                                       |         |
		**********************************************************************/
// Syntax - 
//  Seg Id ( Pos   , Max Use,  Data type	, Segment Type) 
		NM1("01" , 	1 	,  NM1.class	, X12SegmentTypeEnum.DATA_SEGMENT),
		REF("02" , 	9 	,  REF.class	, X12SegmentTypeEnum.DATA_SEGMENT),	 
		N3("03"  , 	1 	,  N3.class		, X12SegmentTypeEnum.DATA_SEGMENT),
		N4("04"  , 	1 	,  N4.class		, X12SegmentTypeEnum.DATA_SEGMENT), 
		PER("05" , 	9 	,  PER.class	, X12SegmentTypeEnum.DATA_SEGMENT),
		AAA("06" , 	9 	,  AAA.class	, X12SegmentTypeEnum.DATA_SEGMENT), 
		PRV("07" , 	1 	,  PRV.class	, X12SegmentTypeEnum.DATA_SEGMENT),
		DMG("08" , 	1 	,  DMG.class	, X12SegmentTypeEnum.DATA_SEGMENT), 
		INS("09" , 	1 	,  INS.class	, X12SegmentTypeEnum.DATA_SEGMENT),
		HI( "10"  , 1 	,  HI.class		, X12SegmentTypeEnum.DATA_SEGMENT),
		DTP("11" , 	9 	,  DTP.class	, X12SegmentTypeEnum.DATA_SEGMENT),   
		MPI("12" , 	9 	,  MPI.class	, X12SegmentTypeEnum.DATA_SEGMENT), 
   LOOP2110("13" ,  0   ,  Loop2110.class, X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT);
		
		Class<? extends X12Segment> x12SegmentClass;
		int repeatCount;
		String position;
		X12SegmentTypeEnum segmentTypeEnum;

		Loop2100SegmentMetaDataEnum(String position, int maxUse, Class<? extends X12Segment> x12SegmentClass, X12SegmentTypeEnum X12SegmentTypeEnum) {
			this.repeatCount = maxUse;
			this.position = position;
			this.x12SegmentClass =  x12SegmentClass;
			this.segmentTypeEnum = X12SegmentTypeEnum;
		}
		@Override
		public int getRepeatCont() {
			return repeatCount;
		}
		@Override
		public X12Segment createSegmentInstance() throws X12ParserException {
			return X12ParserUtil.createInstance(x12SegmentClass);
		}
		@Override
		public String getSementIdentifier() {
			return this.name() + "*";
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
	private List<AAA> aaa;
	private DMG dmg;
	private List<DTP> dtp;
	private String expression;
	private HI hi;
	private INS ins;
	private List<Loop2110> loop2110;
	private List<MPI> mpi;
	private N3 n3;
	private N4 n4;
	private NM1 nm1;

	private List<PER> per;

	private PRV prv;

	private List<REF> ref;

	public List<AAA> getAaa() {
		return aaa;
	}

	public DMG getDmg() {
		return dmg;
	}

	public List<DTP> getDtp() {
		return dtp;
	}

	public String getExpression() {
		return expression;
	}

	public HI getHi() {
		return hi;
	}

	public INS getIns() {
		return ins;
	}

	public List<Loop2110> getLoop2110() {
		return loop2110;
	}

	public List<MPI> getMpi() {
		return mpi;
	}

	public N3 getN3() {
		return n3;
	}

	public N4 getN4() {
		return n4;
	}

	public NM1 getNm1() {
		return nm1;
	}

	public List<PER> getPer() {
		return per;
	}

	public PRV getPrv() {
		return prv;
	}

	public List<REF> getRef() {
		return ref;
	}

	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(Loop2100SegmentMetaDataEnum.values());
	}

	public void setAaa(List<AAA> aaa) {
		this.aaa = aaa;
	}

	public void setDmg(DMG dmg) {
		this.dmg = dmg;
	}

	public void setDtp(List<DTP> dtp) {
		this.dtp = dtp;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (Loop2100SegmentMetaDataEnum.NM1.equals(segmentMetaData)
				&& value instanceof NM1) {
			setNm1((NM1) value);
		}
		if (Loop2100SegmentMetaDataEnum.REF.equals(segmentMetaData)
				&& value instanceof REF) {
			if (null == getRef()) {
				setRef(new ArrayList<REF>());
			}
			getRef().add((REF) value);
		}
		if (Loop2100SegmentMetaDataEnum.N3.equals(segmentMetaData)
				&& value instanceof N3) {
			setN3((N3) value);
		}
		if (Loop2100SegmentMetaDataEnum.N4.equals(segmentMetaData)
				&& value instanceof N4) {
			setN4((N4) value);
		}
		if (Loop2100SegmentMetaDataEnum.PER.equals(segmentMetaData)
				&& value instanceof PER) {
			if (null == getPer()) {
				setPer(new ArrayList<PER>());
			}
			getPer().add((PER) value);
		}
		if (Loop2100SegmentMetaDataEnum.AAA.equals(segmentMetaData)
				&& value instanceof AAA) {
			if (null == getAaa()) {
				setAaa(new ArrayList<AAA>());
			}
			getAaa().add((AAA) value);
		}
		if (Loop2100SegmentMetaDataEnum.PRV.equals(segmentMetaData)
				&& value instanceof PRV) {
			setPrv((PRV) value);
		}
		if (Loop2100SegmentMetaDataEnum.DMG.equals(segmentMetaData)
				&& value instanceof DMG) {
			setDmg((DMG) value);
		}
		if (Loop2100SegmentMetaDataEnum.INS.equals(segmentMetaData)
				&& value instanceof INS) {
			setIns((INS) value);
		}
		if (Loop2100SegmentMetaDataEnum.HI.equals(segmentMetaData)
				&& value instanceof HI) {
			setHi((HI) value);
		}
		if (Loop2100SegmentMetaDataEnum.DTP.equals(segmentMetaData)
				&& value instanceof DTP) {
			if (null == getDtp()) {
				setDtp(new ArrayList<DTP>());
			}
			getDtp().add((DTP) value);
		}
		if (Loop2100SegmentMetaDataEnum.MPI.equals(segmentMetaData)
				&& value instanceof MPI) {
			if (null == getMpi()) {
				setMpi(new ArrayList<MPI>());
			}
			getMpi().add((MPI) value);
		}
		if (Loop2100SegmentMetaDataEnum.LOOP2110.equals(segmentMetaData)
				&& value instanceof Loop2110) {
			if (null == getLoop2110()) {
				setLoop2110(new ArrayList<Loop2110>());
			}
			getLoop2110().add((Loop2110) value);
		}
	}

	public void setHi(HI hi) {
		this.hi = hi;
	}

	public void setIns(INS ins) {
		this.ins = ins;
	}

	public void setLoop2110(List<Loop2110> loop2110) {
		this.loop2110 = loop2110;
	}

	public void setMpi(List<MPI> mpi) {
		this.mpi = mpi;
	}

	public void setN3(N3 n3) {
		this.n3 = n3;
	}

	public void setN4(N4 n4) {
		this.n4 = n4;
	}

	public void setNm1(NM1 nm1) {
		this.nm1 = nm1;
	}

	public void setPer(List<PER> per) {
		this.per = per;
	}

	public void setPrv(PRV prv) {
		this.prv = prv;
	}

	public void setRef(List<REF> ref) {
		this.ref = ref;
	}

}
