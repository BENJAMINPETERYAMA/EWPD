package com.wellpoint.ets.bx.mapping.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;


/**
 * @author UST-GLOBAL
 * This is a bean which has scope of session and used to keep thing and track progress.
 * 
 */
public class MassUpdateTracker {
	
	public static final String SPS_TYPE = "SPS";
	public static final String RULE_TYPE = "RULE";
	public static final String VARIABLE_TYPE = "VARIABLE";
	public static final String MSG_TYPE = "MESSAGE";
	public static final String RECORDS_SELECTED = "Records selected on ";
	public static final String CHECKBOX_CHECKED = "checked";
	
	private List lastMassUpdate;
	
	private Map lastMassupdateResult;
	
	private int totalCount = 0;
	private int completedCount = 0;
	
	private Map selectedRecords = new HashMap();
	
	private String lastSearchedType;
	
	private List lastFetchedIds  = new ArrayList();
	
	
	public String getLastSearchedType() {
		return lastSearchedType;
	}

	public void setLastSearchedType(String lastSearchedType) {
		this.lastSearchedType = lastSearchedType;
	}

	public List getLastMassUpdate() {
		return lastMassUpdate;
	}

	public void setLastMassUpdate(List lastMassUpdate) {
		this.lastMassUpdate = lastMassUpdate;
	}

	public Map getLastMassupdateResult() {
		return lastMassupdateResult;
	}

	public void setLastMassupdateResult(Map lastMassupdateResult) {
		this.lastMassupdateResult = lastMassupdateResult;
	}
	
	public void setLastMassupdateResult(List lastMassupdateResult) {
		this.lastMassupdateResult = new HashMap();
		if(null == lastMassupdateResult){
			return;
		}
		Iterator itr = lastMassupdateResult.iterator();
		while(itr.hasNext()){
			Object o = itr.next();
			if(o instanceof MappingResult){
				MappingResult mappingResult = (MappingResult)o ;
				this.lastMassupdateResult.put(findKey(mappingResult.getMapping()), mappingResult);
			}else if(o instanceof CreateOrEditMappingResult){
				CreateOrEditMappingResult mappingResult = (CreateOrEditMappingResult)o;
				this.lastMassupdateResult.put(findKey(mappingResult.getMapping()), mappingResult);
			}
		}
	}
	
	public String findKey(MassUpdateCriteria muc){
		if(BxUtil.hasText(muc.getVariableId())){
			return muc.getVariableId().trim();
		}
		
		if(BxUtil.hasText(muc.getSpsId()) && BxUtil.hasText(muc.getRuleId())){
			return muc.getSpsId().trim()+"-"+muc.getRuleId().trim();
		}
		
		if(BxUtil.hasText(muc.getSpsId())){
			return muc.getSpsId().trim();
		}
		
		if(BxUtil.hasText(muc.getRuleId())){
			return muc.getRuleId().trim();
		}
		
		return null;
	}
	
	private String findKey(Mapping mapping){
		if(mapping == null){
			return null;
		}
		if(null != mapping.getVariable() && BxUtil.hasText(mapping.getVariable().getVariableId())){
			return mapping.getVariable().getVariableId().trim();
		}
		
		if(null != mapping.getSpsId() && null != mapping.getRule() 
				&& BxUtil.hasText(mapping.getSpsId().getSpsId()) && BxUtil.hasText(mapping.getRule().getHeaderRuleId())){
			return mapping.getSpsId().getSpsId().trim()+"-"+mapping.getRule().getHeaderRuleId().trim();
		}
		
		if(null != mapping.getSpsId() && BxUtil.hasText(mapping.getSpsId().getSpsId())){
			return mapping.getSpsId().getSpsId().trim();
		}
		
		if(null != mapping.getRule() && BxUtil.hasText(mapping.getRule().getHeaderRuleId())){
			return mapping.getRule().getHeaderRuleId().trim();
		}
		return null;
	}	
	
	public int getCompletedPercentage() {
		if(totalCount == 0){
			return 100;
		}
		int completedPerCentage = new Double(Math.floor((completedCount*100)/((double)totalCount))).intValue();
		return completedPerCentage;
	}

	public int getCompletedCount() {
		return completedCount;
	}

	public void setCompletedCount(int completedCount) {
		this.completedCount = completedCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public void clearSelectedRecords(){
		selectedRecords.clear();
	}
	
	public Set getSelectedPages(){
		return selectedRecords.keySet();
	}
	
	private void fillLastFetchedIds(List list){
		lastFetchedIds.clear();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			SearchResult searchResult = (SearchResult)itr.next();
			if(null == searchResult){
				continue;
			}
			addIntoLastFetchedIds(searchResult);
		}
	}
	
	private void addIntoLastFetchedIds(SearchResult searchResult){
		MassUpdateCriteria muc = new MassUpdateCriteria();
		if(BxUtil.hasText(searchResult.getVariableId())){
			muc.setVariableId(searchResult.getVariableId().trim());
		}else{
			muc.setVariableId(null);
		}
		if(BxUtil.hasText(searchResult.getSpsId())){
			muc.setSpsId(searchResult.getSpsId().trim());		
		}else{
			muc.setSpsId(null);		
		}
		if(BxUtil.hasText(searchResult.getHeaderRuleId())){
			muc.setRuleId(searchResult.getHeaderRuleId().trim());
		}else{
			muc.setRuleId(null);
		}
		lastFetchedIds.add(muc);
	}
	
	public void markSelected(List list,Integer page, String type){
		fillLastFetchedIds(list);
		if(null == list || list.size() == 0){
			return;
		}
		List recordsInPage = getSelectedRecords(page);
		if(null == recordsInPage || recordsInPage.size() == 0){
			return;
		}
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			SearchResult searchResult = (SearchResult)itr.next();
			if(null == searchResult){
				continue;
			}
			if(hasSearchResultInSelected(searchResult, recordsInPage, type)){
				searchResult.setChecked(CHECKBOX_CHECKED);
			}else{
				searchResult.setChecked("");
			}
			
		}
	}
	
	private boolean hasSearchResultInSelected(SearchResult searchResult, List recordsInPage, String type){
		Iterator itr = recordsInPage.iterator();
		while(itr.hasNext()){
			MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
			if(null == muc){
				continue;
			}
			if(type.equals("VARIABLE")){
				if(BxUtil.hasText(muc.getVariableId()) && BxUtil.hasText(searchResult.getVariableId()) && 
						muc.getVariableId().trim().equals((searchResult.getVariableId().trim()))){
					return true;
				}
			}
			if(type.equals("MSG")){
				if(BxUtil.hasText(muc.getSpsId()) && BxUtil.hasText(searchResult.getSpsId()) && 
						muc.getSpsId().trim().equals((searchResult.getSpsId().trim())) && 
						BxUtil.hasText(muc.getRuleId()) && BxUtil.hasText(searchResult.getHeaderRuleId()) && 
						muc.getRuleId().trim().equals((searchResult.getHeaderRuleId().trim()))){
					return true;
				}
			}
			if(type.equals("SPS")){
				if(BxUtil.hasText(muc.getSpsId()) && BxUtil.hasText(searchResult.getSpsId()) && 
						muc.getSpsId().trim().equals((searchResult.getSpsId().trim())) ){
					return true;
				}
			}
			if(type.equals("RULE")){
				if(BxUtil.hasText(muc.getRuleId()) && BxUtil.hasText(searchResult.getHeaderRuleId()) && 
						muc.getRuleId().trim().equals((searchResult.getHeaderRuleId().trim()))){
					return true;
				}
			}
		}
		return false;
	}
	
	
	public String getSelectedPagesString(){
		if(null != selectedRecords && null != selectedRecords.keySet() && selectedRecords.keySet().size() > 0){
			List pages = new ArrayList(selectedRecords.keySet());
			Collections.sort(pages);
			return  RECORDS_SELECTED+pages.toString();
		}
		return "";
	}
	
	public void addAllSelectedRecord(Integer pageNo, boolean checked){
		Iterator itr = lastFetchedIds.iterator();
		while(itr.hasNext()){
			MassUpdateCriteria muc = (MassUpdateCriteria) itr.next();
			if(null == muc){
				continue;
			}
			addSelectedRecord(pageNo, muc.getSpsId(), muc.getRuleId(), muc.getVariableId(), checked);
		}
		
	}
	public void addSelectedRecord(Integer pageNo, String spsId, String ruleId, String varId, boolean checked){
		
		List recordsInPage = (List)selectedRecords.get(pageNo);
		MassUpdateCriteria newMUC = getMassUpdateCriteria(spsId, ruleId, varId);
		if(checked){
			if(null == recordsInPage){
				recordsInPage = new ArrayList();
				recordsInPage.add(newMUC);
				selectedRecords.put(pageNo, recordsInPage);
			}else{
				Iterator itr = recordsInPage.iterator();
				while(itr.hasNext()){
					MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
					if(checkCriteriaEquals(muc, newMUC)){
						return;
					}
				}
				recordsInPage.add(newMUC);
			}
		}else{
			if(null == recordsInPage){
				return;
			}else{
				Iterator itr = recordsInPage.iterator();
				MassUpdateCriteria deletingMUC = null;
				while(itr.hasNext()){
					MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
					if(checkCriteriaEquals(muc, newMUC)){
						deletingMUC = muc;
						break;
					}
				}
				if(null != deletingMUC){
					recordsInPage.remove(deletingMUC);
				}
				if(recordsInPage.size() == 0){
					selectedRecords.remove(pageNo);
				}
				
			}
		}
		
	}
	
	public List getSelectedRecords(Integer pageNo){
		List recordsInPage = (List)selectedRecords.get(pageNo);
		return recordsInPage;
	}
	
	private MassUpdateCriteria getMassUpdateCriteria( String spsId, String ruleId, String varId ){
		MassUpdateCriteria muc = new MassUpdateCriteria();
		muc.setSpsId(spsId);
		muc.setRuleId(ruleId);
		muc.setVariableId(varId);
		return muc;
	}
	
	private boolean checkCriteriaEquals(MassUpdateCriteria a, MassUpdateCriteria b){
		if(null == a || null == b){
			return false;
		}
		boolean sps = false;
		boolean rule = false;
		boolean var = false;
		
		if((!BxUtil.hasText(a.getSpsId()) && !BxUtil.hasText(b.getSpsId())) || 
				(BxUtil.hasText(a.getSpsId()) && BxUtil.hasText(b.getSpsId()) && a.getSpsId().trim().equals(b.getSpsId().trim()))){
			sps = true;
		}
		
		if((!BxUtil.hasText(a.getRuleId()) && !BxUtil.hasText(b.getRuleId())) || 
				(BxUtil.hasText(a.getRuleId()) && BxUtil.hasText(b.getRuleId()) && a.getRuleId().trim().equals(b.getRuleId().trim()))){
			rule = true;
		}
		
		if((!BxUtil.hasText(a.getVariableId()) && !BxUtil.hasText(b.getVariableId())) || 
				(BxUtil.hasText(a.getVariableId()) && BxUtil.hasText(b.getVariableId()) && a.getVariableId().trim().equals(b.getVariableId().trim()))){
			var = true;
		}
		
		
		if(sps && rule && var){
			return true;
		}
		
		return false;
	}
}
