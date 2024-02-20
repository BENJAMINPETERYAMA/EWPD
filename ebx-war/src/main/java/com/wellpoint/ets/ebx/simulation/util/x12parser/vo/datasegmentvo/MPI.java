package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;



public class MPI implements X12Segment{
	private String mpi01;
	private String mpi02;
	private String mpi03;
	private String mpi04;
	private String mpi05;
	private String mpi06;
	private String mpi07;
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(MPISegmentMetaDataEnum.values());
	}
	
	public enum MPISegmentMetaDataEnum implements SegmentMetaData{		
		
	// Syntax - Seg Id ( Pos, Max Use) 
	// Pos will be used for sorting the segments.
		MPI01("01",1),
		MPI02("02",1),
		MPI03("03",1),
		MPI04("04",1),
		MPI05("05",1),
		MPI06("06",1),
		MPI07("07",1);	
		
		int repeatCount;
		String position;
		
		MPISegmentMetaDataEnum (String position, int repeatCount) {
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
		if (MPISegmentMetaDataEnum.MPI01.equals(segmentMetaData)
				&& value instanceof String) {
			setMpi01((String) value);
		}
		if (MPISegmentMetaDataEnum.MPI02.equals(segmentMetaData)
				&& value instanceof String) {
			setMpi02((String) value);
		}
		if (MPISegmentMetaDataEnum.MPI03.equals(segmentMetaData)
				&& value instanceof String) {
			setMpi03((String) value);
		}
		if (MPISegmentMetaDataEnum.MPI04.equals(segmentMetaData)
				&& value instanceof String) {
			setMpi04((String) value);
		}
		if (MPISegmentMetaDataEnum.MPI05.equals(segmentMetaData)
				&& value instanceof String) {
			setMpi05((String) value);
		}
		if (MPISegmentMetaDataEnum.MPI06.equals(segmentMetaData)
				&& value instanceof String) {
			setMpi06((String) value);
		}
		if (MPISegmentMetaDataEnum.MPI07.equals(segmentMetaData)
				&& value instanceof String) {
			setMpi07((String) value);
		}		
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getMpi01() {
		return mpi01;
	}
	public void setMpi01(String mpi01) {
		this.mpi01 = mpi01;
	}
	public String getMpi02() {
		return mpi02;
	}
	public void setMpi02(String mpi02) {
		this.mpi02 = mpi02;
	}
	public String getMpi03() {
		return mpi03;
	}
	public void setMpi03(String mpi03) {
		this.mpi03 = mpi03;
	}
	public String getMpi04() {
		return mpi04;
	}
	public void setMpi04(String mpi04) {
		this.mpi04 = mpi04;
	}
	public String getMpi05() {
		return mpi05;
	}
	public void setMpi05(String mpi05) {
		this.mpi05 = mpi05;
	}
	public String getMpi06() {
		return mpi06;
	}
	public void setMpi06(String mpi06) {
		this.mpi06 = mpi06;
	}
	public String getMpi07() {
		return mpi07;
	}
	public void setMpi07(String mpi07) {
		this.mpi07 = mpi07;
	}

	
}
