
package com.wellpoint.wpd.common.accumulator.bo;

public interface SearchCriteria {
	
	 public String getAccumDescription();
	 
	 public void setAccumDescription(String accumDescription);
	 
	 public String getAccumName();
	 
	 public void setAccumName(String accumName);
	 
	 public int getMaxSearchResult();
	    
	    public void setMaxSearchResult(int maxSearchResult);
	    
	    public String getBusinessEntity();
	    
		public void setBusinessEntity(String businessEntity);
		
		public String getVariableId();
		
		public void setVariableId(String variableId);
		
		public String getSystem();
		
		public void setSystem(String system);
}
