package com.wellpoint.wpd.business.accumulator.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import com.wellpoint.wpd.db.ReferenceDataDao;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.accumulator.adapter.AccumulatorAdapterManager;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.ReferenceDataFactory;
import com.wellpoint.wpd.common.accumulator.bo.Accumulator;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSet;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSetImpl;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSet;
public class AccumulatorBuilderImpl implements AccumulatorBuilder {

	private ReferenceDataDao referenceDataDao;
	
	public AccumulatorAdapterManager getAccumulatorAdapterManager() {
		AccumulatorAdapterManager accumulatorAdapterManager = new AccumulatorAdapterManager();
		return accumulatorAdapterManager;
	}
	

	private void throwException(Object obj, AdapterException adapterException)
			throws ServiceException {
		List params = new ArrayList();
		params.add(obj);
		SevereException se = new SevereException(
				"Adapter exception occured while executing  ", params,
				adapterException);
		Logger.logException(se);
		throw new ServiceException("AdapterManager", params, adapterException);
	}

	public List filterAccumValues(String queryName, Accumulator accumulator)
			throws ServiceException {

		return getAccumulatorAdapterManager().filterAccumValues(queryName,
				accumulator);

	}
	
	public List getReferenceDataSetExactOrder(int CDCI_CD_ITM_ID) {
        ReferenceDataFactory referenceDataFactory = (ReferenceDataFactory) ObjectFactory.getFactory("referenceData");
        ReferenceDataSet referenceDataSet = null;
        referenceDataSet = referenceDataFactory.getReferenceData(CDCI_CD_ITM_ID);
        return referenceDataSet.getReferenceData();
    }
	
	

	

	public void createStdAccumulator(StandardAccumulator stdAccumulator)
			throws SevereException {
		
        SearchResultSet searchResultSet1 = new SearchResultSetImpl();
		searchResultSet1.setSearchResult(this.getAccumulatorAdapterManager()
				.getBusinessDomain(stdAccumulator.getLineOfBusiness(),
						stdAccumulator.getBe().toString(),
						stdAccumulator.getGroup(), stdAccumulator.getMbu())
				.getSearchResults());
		StandardAccumulator stdaccum = new StandardAccumulator();
		List searchReslt = new ArrayList();
		searchReslt = searchResultSet1.getSearchResult();
		if (searchResultSet1.getSearchResult() != null
				&& searchResultSet1.getSearchResult().size() != 0) {
			stdaccum = (StandardAccumulator) searchReslt.get(0);

			stdAccumulator.setBusinessdomain(stdaccum.getBusinessdomain());
		}
		// int bd=searchReslt.get(0);
		try {
			this.getAccumulatorAdapterManager().createStdAccumulator(
					stdAccumulator);

		} catch (AdapterException adapterException) {
			throwException(stdAccumulator, adapterException);
		}
	}
	
	public SearchResultSet searchMappedStandardAccumulator(List benefit,List question,
			List lob, List businessEntity, List group, List mbu,
			String byOrCy, List accumName) throws ServiceException {

		SearchResultSet searchResultSet = new SearchResultSetImpl();
		searchResultSet.setSearchResult(this.getAccumulatorAdapterManager()
				.searchMappedStandardAccumulator(benefit,question, lob, businessEntity,
						group, mbu, byOrCy, accumName).getSearchResults());
		return searchResultSet;
	}

	public SearchResultSet searchStandardAccumulator(int benefit,int question,
			String lob, List businessEntity, String group, String mbu,
			String byOrCy, List accumName) throws ServiceException {

		SearchResultSet searchResultSet = new SearchResultSetImpl();
		searchResultSet.setSearchResult(this.getAccumulatorAdapterManager()
				.searchStandardAccumulator(benefit,question, lob, businessEntity,
						group, mbu, byOrCy, accumName).getSearchResults());
		return searchResultSet;
	}

}
