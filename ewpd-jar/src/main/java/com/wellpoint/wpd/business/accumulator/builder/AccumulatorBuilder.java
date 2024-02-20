package com.wellpoint.wpd.business.accumulator.builder;

import java.util.List;
import com.wellpoint.wpd.common.accumulator.bo.Accumulator;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSet;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSet;


public interface AccumulatorBuilder {

	
	

   public SearchResultSet searchMappedStandardAccumulator(List benefit,List question,
				List lob, List businessEntity, List group, List mbu,
				String type, List accumName) throws ServiceException;

	 
	 public List getReferenceDataSetExactOrder(int type);

	public SearchResultSet searchStandardAccumulator(int benefit,int question,
			String lob, List businessEntity, String group, String mbu,
			String type, List accumName) throws ServiceException;

	public void createStdAccumulator(StandardAccumulator stdAccumulator)
			throws ServiceException, SevereException;
	
	public List filterAccumValues(String queryName, Accumulator accumulator) throws ServiceException;

	// public void mapStdAccumulator(StandardAccumulator stdAccumulator) throws
	// ServiceException;

	
}
