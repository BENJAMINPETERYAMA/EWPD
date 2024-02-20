package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo;


import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.util.x12parser.exception.X12ParserException;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.IEA;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.ISA;



public class Interchange implements X12Segment{

	
	private List<FunctionGroup> functionGroup;
	
	private ISA isa;

	private IEA iea;
	
	
	public Interchange () {
		
	}	
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(InterchangeMetaDataEnum.values());
	}

	public enum InterchangeMetaDataEnum implements SegmentMetaData{		
		
		/**************************************************
		| Pos   | Seg Id    | Name                 					| Max Use|
		|--------------------------------------------------------------------|  
		| 01 	|ISA 		| Functional Group Header 				| 	1	 |
		| 02 	|Transaction| 										| 		 |
		| 03	|IEA		| Functional Group Trailer				|	1	 |
		**********************************************************************/
	// Syntax - 
//  Seg Id 		  (   Pos  , Max Use ,  Data type		   , Segment Type) 
//----------------------------------------------------	
		ISA(		"01" ,	  1    ,  ISA.class		       , X12SegmentTypeEnum.DATA_SEGMENT), // Position is for internal use. To identify the order
		FUNCTION(   "02",	  0    ,  FunctionGroup.class  , X12SegmentTypeEnum.LOOP_CONTROL_SEGMENT), // Position is for internal use. To identify the order	
		IEA(	    "03" ,	  1    ,  IEA.class		       , X12SegmentTypeEnum.DATA_SEGMENT); // Position is for internal use. To identify the order
		
		

		int repeatCount;
		String position;
		Class<? extends X12Segment> x12SegmentClass;
		X12SegmentTypeEnum segmentTypeEnum;
		
		InterchangeMetaDataEnum (String position, int maxUse, Class<? extends X12Segment> x12SegmentClass, X12SegmentTypeEnum X12SegmentTypeEnum) {
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
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (InterchangeMetaDataEnum.ISA.equals(segmentMetaData)
				&& value instanceof ISA) {
			setIsa((ISA) value);
		}
		if (InterchangeMetaDataEnum.IEA.equals(segmentMetaData)
				&& value instanceof IEA) {
			setIea((IEA) value);
		}
		if (InterchangeMetaDataEnum.FUNCTION.equals(segmentMetaData)
				&& value instanceof FunctionGroup) {
			if (null == getFunctionGroup()) {
				setFunctionGroup(new ArrayList<FunctionGroup>());
			}
			getFunctionGroup().add((FunctionGroup) value);
		}
		
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	public ISA getIsa() {
		return isa;
	}
	public IEA getIea() {
		return iea;
	}

	public void setIsa(ISA isa) {
		this.isa = isa;
	}
	public void setIea(IEA iea) {
		this.iea = iea;
	}
	public List<FunctionGroup> getFunctionGroup() {
		return functionGroup;
	}
	public void setFunctionGroup(List<FunctionGroup> functionGroup) {
		this.functionGroup = functionGroup;
	}
	
	
		
}
