/**
 * 
 */
package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRule;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRuleCodeSequence;
import com.wellpoint.ets.bx.mapping.domain.entity.ClaimLevelSequence;
import com.wellpoint.ets.bx.mapping.domain.entity.ClaimCodeSequence;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

/**
 * @author U23641
 *
 */
public class AjaxRuleViewController implements Controller {

	private LocateFacade locateFacade;
	private static Logger log = Logger.getLogger(AjaxRuleViewController.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String ruleId = request.getParameter("umRuleId");
		final String GROUP_RULE_INDICATOR = "Y";
		List infoList = new ArrayList();
		Map map = new HashMap();
		int ruleVersion;
		
		if(null == ruleId || ("").equals(ruleId.trim())){
			infoList.add("Rule ID is null.");
			map.put(WebConstants.INFO_MESSAGES, infoList);
			return new ModelAndView("ruleView",map);
		}
		
		UMRule umRuleObj = locateFacade.fetchRuleInfo(ruleId);
		// Added as part of ICD10-October release//
		ruleVersion=umRuleObj.getRuleVerNmbr();
		List grpRuleViewList = null;
		List ruleViewList = null;
		List ruleSequenceNumberList = new ArrayList();
		List ruleCodeSeqnceList = null;
		List ruleClaimLevelSeqnceList = null;
		List ruleClaimCodeSeqnceList = null;
		List ruleViewSequenceList = new ArrayList();
		List ruleViewCodeSequenceList = new ArrayList();
		List ruleViewClaimLevelSequenceList = new ArrayList();
		List ruleViewClaimCodeSequenceList = new ArrayList();
		String ruleSeqNumber = null;
		if(null != umRuleObj ){
			if(GROUP_RULE_INDICATOR.equals(umRuleObj.getRuleGrpInd())){
				//Fetch Group Rule Info
				grpRuleViewList = locateFacade.viewGrpRule(ruleId);
				Collections.sort(grpRuleViewList, new Comparator(){
				public int compare(Object o1, Object o2) {
					int compare = 0;
					if(o1 instanceof UMRule && o2 instanceof UMRule){
						UMRule obj1 = (UMRule)o1;
						UMRule obj2 = (UMRule)o2;
						String name1 = obj1.getHippaSegment().getName();
						String name2 = obj2.getHippaSegment().getName();
						if(null != name1 && null != name2){
							compare = name1.compareToIgnoreCase(name2);
						}
					}
					return compare;
				}
				
			});
				map.put("title","View Group Rule");
				map.put("groupRulesList", grpRuleViewList);
				map.put("ruleInfo", umRuleObj);
				return new ModelAndView("groupRulePage",map);
			}else{
				ruleViewList = locateFacade.viewRuleSequenceIndicators(ruleId);
				List seqList = new ArrayList(0);
				for(Iterator itr = ruleViewList.iterator();itr.hasNext();){
					UMRule umRuleObject = (UMRule)itr.next();
					if(!seqList.contains(umRuleObject.getRuleSqncNbr())){
						seqList.add(umRuleObject.getRuleSqncNbr());
					Map ruleViewMap = new LinkedHashMap();
					SimpleDateFormat sdfOne = new SimpleDateFormat("MM/dd/yy");
					String strDate; 
					
					addToMap(ruleViewMap, WebConstants.RULE_SEQUENCE_NUMBER, umRuleObject.getRuleSqncNbr() );
					
					addToMap(ruleViewMap, WebConstants.EXCLSN_IND, umRuleObject.getExclsnInd() );
					
					addToMap(ruleViewMap, WebConstants.CLM_TYPE, umRuleObject.getClmType() );
					
					addToMap(ruleViewMap, WebConstants.PLC_OF_SRVC, umRuleObject.getPlcOfSrvc() );
					
					addToMap(ruleViewMap, WebConstants.PAT_LOW_AGE, umRuleObject.getPatLowAge() );
					
					addToMap(ruleViewMap, WebConstants.PAT_HIGH_AGE, umRuleObject.getPatHighAge() );
					
					addToMap(ruleViewMap, WebConstants.GNDR_CD, umRuleObject.getGndrCode() );
					
					addToMap(ruleViewMap, WebConstants.PRVDR_ID, umRuleObject.getPrvdrId() );
					
					addToMap(ruleViewMap, WebConstants.PRVDR_SPCLTY_CD, umRuleObject.getPrvdrSpcltyCd() );
					
					addToMap(ruleViewMap, WebConstants.BNFT_ACCUM_NM, umRuleObject.getBnftAccumNm() );
					
					addToMap(ruleViewMap, WebConstants.BNFT_ACCUM_LMT_AMT, umRuleObject.getBnftAccumLmtAmt() );
					
					addToMap(ruleViewMap, WebConstants.BNFT_ACCUM_LMT_NBR, umRuleObject.getBnftAccumLmtNbr() );
					
					addToMap(ruleViewMap, WebConstants.NTFY_ONLY_IND, umRuleObject.getNtfyOnlyInd() );
					
					addToMap(ruleViewMap, WebConstants.CLNL_RVW_IND, umRuleObject.getClnlRvwInd() );
					
					addToMap(ruleViewMap, WebConstants.DLR_LMT, umRuleObject.getDlrLmt() );
					
					addToMap(ruleViewMap, WebConstants.SERIVCE_CD, umRuleObject.getSerivceCd() );
					
					addToMap(ruleViewMap, WebConstants.GRP_ST, umRuleObject.getGrpSt() );
					
					addToMap(ruleViewMap, WebConstants.LEN_OF_STAY, umRuleObject.getLenOfStay() );
					
					addToMap(ruleViewMap, WebConstants.ITS_SPCLTY_CD, umRuleObject.getItsSpcltyCd() );
					
					if(null!= umRuleObject.getSrvcStrtDt() && !"".equals(umRuleObject.getSrvcStrtDt())){
					strDate = sdfOne.format(umRuleObject.getSrvcStrtDt());
					addToMap(ruleViewMap, WebConstants.SRVC_STRT_DT, strDate );
					}
					if(null!= umRuleObject.getSrvcEndDt() && !"".equals(umRuleObject.getSrvcEndDt())){
					strDate = sdfOne.format(umRuleObject.getSrvcEndDt());
					addToMap(ruleViewMap, WebConstants.SRVC_END_DT, strDate);
					}
					
					addToMap(ruleViewMap, WebConstants.MBR_RELSHP_CDE, umRuleObject.getMbrRelshpCde() );
					
					addToMap(ruleViewMap, WebConstants.PRCDR_MODFR_CDE, umRuleObject.getPrcdrModfrCde() );
					
					addToMap(ruleViewMap, WebConstants.EDIT_CDE_1, umRuleObject.getEditCde1() );
					
					addToMap(ruleViewMap, WebConstants.EDIT_CDE_2, umRuleObject.getEditCde2() );
					
					addToMap(ruleViewMap, WebConstants.PRVDR_ST_CD, umRuleObject.getPrvdrStCd() );
					
					addToMap(ruleViewMap, WebConstants.BILL_TYP_CD, umRuleObject.getBillTypCd() );
					
					addToMap(ruleViewMap, WebConstants.TT_CD, umRuleObject.getTtCd() );
					
					addToMap(ruleViewMap, WebConstants.ATCHMT_IND, umRuleObject.getAtchmtInd() );
					
					addToMap(ruleViewMap, WebConstants.PAT_MBR_CD, umRuleObject.getPatMbrCd() );
					
					addToMap(ruleViewMap, WebConstants.HOSP_FCIL_CD, umRuleObject.getHospFcilCd() );
					
					
					
					addToMap(ruleViewMap, WebConstants.DAYS_FROM_INJRY, umRuleObject.getDaysFromInjry() );
					
					addToMap(ruleViewMap, WebConstants.DAYS_FROM_ILNS, umRuleObject.getDaysFromIlns() );
					
					addToMap(ruleViewMap, WebConstants.HMO_CLS_CD, umRuleObject.getHmoClsCd() );
					
					addToMap(ruleViewMap, WebConstants.TOT_CHRG_SIGN, umRuleObject.getTotChrgSign() );
					
					addToMap(ruleViewMap, WebConstants.TOT_CHARGE_AMOUNT, umRuleObject.getTotChargeAmount() );
					
					/*addToMap(ruleViewMap, WebConstants.WPD_DEL_FLAG, umRuleObject.getWpdDelFlag() );
					addToMap(ruleViewMap, WebConstants.CLM_SRVC_CD, umRuleObject.getClmSrvcCd() );
					addToMap(ruleViewMap, WebConstants.CLM_PROC_MDFR_CD, umRuleObject.getClmProcMdfrCd() );
					addToMap(ruleViewMap, WebConstants.CLM_TT_CD, umRuleObject.getClmTtCd() );
					addToMap(ruleViewMap, WebConstants.CLM_PLACE_OF_SRVC, umRuleObject.getClmPlaceOfSrvc() );
					addToMap(ruleViewMap, WebConstants.CLM_HMO_CLS_CD, umRuleObject.getClmHmoClsCd() );
					addToMap(ruleViewMap, WebConstants.CLM_SAME_DAY_SVC_IND, umRuleObject.getClmSameDaySvcInd() );
					if(null != umRuleObject.getClmIcdVrsnIndicator() 
							&& !"".equals(umRuleObject.getClmIcdVrsnIndicator())){
					addToMap(ruleViewMap, WebConstants.CLM_ICD_VRSN_IND, WebConstants.ICD_CODE+umRuleObject.getClmIcdVrsnIndicator() );
					}	
					*/
					
					addToMap(ruleViewMap, WebConstants.CPAY_IND, umRuleObject.getCpayInd() );
					
					addToMap(ruleViewMap, WebConstants.HNDRD_PCT_IND, umRuleObject.getHndrdPctInd() );
					
					addToMap(ruleViewMap, WebConstants.MEDCR_ASGN_IND, umRuleObject.getMedcrAsgnInd() );
					
					addToMap(ruleViewMap, WebConstants.PROC_MDFR_CD2, umRuleObject.getProcMdfrCd2() );
					
					addToMap(ruleViewMap, WebConstants.SPRT_HCPS_IND, umRuleObject.getSprtHcpsInd() );
					
					addToMap(ruleViewMap, WebConstants.DIAG_IND, umRuleObject.getDiagInd() );
					
					addToMap(ruleViewMap, WebConstants.PCP_IND, umRuleObject.getPcpInd() );
					
					addToMap(ruleViewMap, WebConstants.PVDR_LIC_ID, umRuleObject.getPvdrLicId() );
					addToMap(ruleViewMap, WebConstants.PVDR_MDCR_ID, umRuleObject.getPvdrMdcrId() );
					addToMap(ruleViewMap, WebConstants.BLNG_PVDR_NR, umRuleObject.getBlngPvdrNr() );
					addToMap(ruleViewMap, WebConstants.RNDR_PVDR_NR, umRuleObject.getRndrPvdrNr() );
					addToMap(ruleViewMap, WebConstants.BLNG_NPI, umRuleObject.getBlngNpi() );
					addToMap(ruleViewMap, WebConstants.RNDR_NPI, umRuleObject.getRndrNpi() );
					addToMap(ruleViewMap, WebConstants.ELGBL_EXPNS_SIGN_CD, umRuleObject.getElgblExpnsSignCd() );
					addToMap(ruleViewMap, WebConstants.ELGBL_EXPNS_AMT, umRuleObject.getElgblExpnsAmt() );
					addToMap(ruleViewMap, WebConstants.AGE_TYPE_CD, umRuleObject.getAgeTypeCd() );
					addToMap(ruleViewMap, WebConstants.RULE_VRSN_NBR, umRuleObject.getRuleVerNmbr()+"" );
					addToMap(ruleViewMap, WebConstants.DRG_CD, umRuleObject.getDrgCd() );
					addToMap(ruleViewMap, WebConstants.PROV_SPEC_CD_IND, umRuleObject.getProvSpecCdInd() );
					
					
					/*addToMap(ruleViewMap, WebConstants.CLM_SPRT_HCPS_IND, umRuleObject.getClmSprtHcpsInd() );
					if(!("-").equals(umRuleObject.getCodeTypeValue().trim())){
						addToMap(ruleViewMap, umRuleObject.getCodeType(),  umRuleObject.getCodeTypeValue());
					}*/
					
					ruleViewSequenceList.add(ruleViewMap);
					
					// Start-- Added as part of ICD10-October release//
				if(ruleViewMap.get(WebConstants.RULE_SEQUENCE_NUMBER)!= null)
				 ruleSeqNumber = (String) ruleViewMap.get(WebConstants.RULE_SEQUENCE_NUMBER);
				if(null != ruleSeqNumber)
					ruleSequenceNumberList.add(ruleSeqNumber);
					// End-- Added as part of ICD10-October release//
				}
					else{
						for(Iterator reItr = ruleViewSequenceList.iterator();reItr.hasNext();){
							Map seqMap =  (LinkedHashMap)reItr.next();
							String seq = (String)seqMap.get(WebConstants.RULE_SEQUENCE_NUMBER);
							if(seq.equals(umRuleObject.getRuleSqncNbr())){
								//ICD10 Bug Fix 26-09-11 ICD Version Indicator not displaying in the rule view popup
								if(null != umRuleObject.getClmIcdVrsnIndicator() 
										&& !"".equals(umRuleObject.getClmIcdVrsnIndicator())){
								addToMap(seqMap, WebConstants.CLM_ICD_VRSN_IND, WebConstants.ICD_CODE+umRuleObject.getClmIcdVrsnIndicator() );
								}
								//ICD10 Bug Fix End
								if(!("-").equals(umRuleObject.getCodeTypeValue().trim())){
									addToMap(seqMap, umRuleObject.getCodeType(),  umRuleObject.getCodeTypeValue());
								}
							}
						}
					}
				}
				
				// Start-- Added as part of ICD10-October release//
				// Returns CodeSequences corresponding to a Rule ID //
				if((null !=ruleId) || (null !=ruleSequenceNumberList)   )
					if(ruleSequenceNumberList.size() > 0){ 
				ruleCodeSeqnceList = locateFacade.viewRuleCodeSequenceIndicators(ruleId,ruleSequenceNumberList,ruleVersion);
				for(Iterator itr = ruleCodeSeqnceList.iterator();itr.hasNext();){
					UMRuleCodeSequence umRuleCodeSeqObject = (UMRuleCodeSequence)itr.next();
					Map ruleCodeSeqncViewMap = new LinkedHashMap();
					addToMap(ruleCodeSeqncViewMap, WebConstants.RULE_SEQUENCE_NUMBER, umRuleCodeSeqObject.getRuleSqncNbr() );
					addToMap(ruleCodeSeqncViewMap, WebConstants.CODE_SEQUENCES, umRuleCodeSeqObject.getCodeSqncNbr());
					if(umRuleCodeSeqObject.getLineHcptLowVal() !=null && umRuleCodeSeqObject.getLineHcptHighVal()!=null)
					addToMap(ruleCodeSeqncViewMap, WebConstants.LN_HCPT_VAL.concat(umRuleCodeSeqObject.getLineHcptLowVal()).concat("-").concat(umRuleCodeSeqObject.getLineHcptHighVal()), umRuleCodeSeqObject.getLineHcptLowVal().concat("-").concat(umRuleCodeSeqObject.getLineHcptHighVal()));
					if(umRuleCodeSeqObject.getLineDiagLowVal() !=null && umRuleCodeSeqObject.getLineDiagHighVal()!=null)
					addToMap(ruleCodeSeqncViewMap, WebConstants.LN_DIAG_VAL.concat(umRuleCodeSeqObject.getLineDiagLowVal()).concat("-").concat(umRuleCodeSeqObject.getLineDiagHighVal()).concat("(").concat(umRuleCodeSeqObject.getLineDiagVrsnIndicator()).concat(")"), umRuleCodeSeqObject.getLineDiagLowVal().concat("-").concat(umRuleCodeSeqObject.getLineDiagHighVal()));
					if(umRuleCodeSeqObject.getLineRevLowVal() !=null && umRuleCodeSeqObject.getLineRevHighVal()!=null)
                    addToMap(ruleCodeSeqncViewMap, WebConstants.LN_REV_VAL.concat(umRuleCodeSeqObject.getLineRevLowVal()).concat("-").concat(umRuleCodeSeqObject.getLineRevHighVal()), umRuleCodeSeqObject.getLineRevLowVal().concat(umRuleCodeSeqObject.getLineRevHighVal()));
					if(umRuleCodeSeqObject.getLineIcdpLowVal() !=null && umRuleCodeSeqObject.getLineIcdpHighVal()!=null)
                    addToMap(ruleCodeSeqncViewMap, WebConstants.LN_ICDP_VAL.concat(umRuleCodeSeqObject.getLineIcdpLowVal()).concat("-").concat(umRuleCodeSeqObject.getLineIcdpHighVal()).concat("(").concat(umRuleCodeSeqObject.getLineIcdVrsnIndicator()).concat(")"), umRuleCodeSeqObject.getLineIcdpLowVal().concat("-").concat(umRuleCodeSeqObject.getLineIcdpHighVal()));
					if(umRuleCodeSeqObject.getLineSrvcClsLow() !=null && umRuleCodeSeqObject.getLineSrvcClsHigh()!=null)
                    addToMap(ruleCodeSeqncViewMap, WebConstants.LN_SRVC_CLS.concat(umRuleCodeSeqObject.getLineSrvcClsLow()).concat("-").concat(umRuleCodeSeqObject.getLineSrvcClsHigh()), umRuleCodeSeqObject.getLineSrvcClsLow().concat("-").concat(umRuleCodeSeqObject.getLineSrvcClsHigh()));
					if(umRuleCodeSeqObject.getLineLmtClsLow() !=null && umRuleCodeSeqObject.getLineLmtClsHigh()!=null)
					addToMap(ruleCodeSeqncViewMap, WebConstants.LN_LMT_CLS.concat(umRuleCodeSeqObject.getLineLmtClsLow()).concat("-").concat(umRuleCodeSeqObject.getLineLmtClsHigh()), umRuleCodeSeqObject.getLineLmtClsLow().concat("-").concat(umRuleCodeSeqObject.getLineLmtClsHigh()));
                    ruleViewCodeSequenceList.add(ruleCodeSeqncViewMap);
				}
				}
				
				// Returns ClaimLevelSequences corresponding to a Rule ID //
				if((null !=ruleId) || (null !=ruleSequenceNumberList) || (ruleSequenceNumberList.size() > 0)  )
					if(ruleSequenceNumberList.size() > 0){ 
					ruleClaimLevelSeqnceList = locateFacade.viewClaimLevelSequenceIndicators(ruleId,ruleSequenceNumberList,ruleVersion);
				for(Iterator itr = ruleClaimLevelSeqnceList.iterator();itr.hasNext();){
					ClaimLevelSequence claimLevelSeqObject = (ClaimLevelSequence)itr.next();
					Map ruleClaimLevelSeqncViewMap = new LinkedHashMap();
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.RULE_SEQUENCE_NUMBER, claimLevelSeqObject.getRuleSqncNbr() );
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLAIM_LEVEL_SEQUENCE, claimLevelSeqObject.getClaimSqncNbr());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLAIM_SERVICE_CODE, claimLevelSeqObject.getClaimServCd());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_PROC_MDFR_CD1, claimLevelSeqObject.getClaimPrcdrModfrCde1());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_PROC_MDFR_CD2, claimLevelSeqObject.getClaimPrcdrModfrCde2());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_TT_CD, claimLevelSeqObject.getClaimTTCd());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_POS_CD, claimLevelSeqObject.getClaimPOSCd());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_HMO_CLS_CD, claimLevelSeqObject.getClmHmoClsCd());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_SAME_DAY_SVC_IND, claimLevelSeqObject.getClmSameDaySvcInd());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_SPRT_PROC_CD_IND, claimLevelSeqObject.getClmSprtProcCdInd());
					addToMap(ruleClaimLevelSeqncViewMap, WebConstants.CLM_DIAG_IND, claimLevelSeqObject.getClaimDiagIndicator());
					ruleViewClaimLevelSequenceList.add(ruleClaimLevelSeqncViewMap);
				}
				}
				
				// Returns ClaimCodeSequences corresponding to a Rule ID //
				if((null !=ruleId) || (null !=ruleSequenceNumberList) || (ruleSequenceNumberList.size() > 0)  )
					if(ruleSequenceNumberList.size() > 0){ 
					ruleClaimCodeSeqnceList = locateFacade.viewRuleClaimCodeSequenceIndicators(ruleId,ruleSequenceNumberList,ruleVersion);
				int ruleCount=0;
				for(Iterator itr = ruleClaimCodeSeqnceList.iterator();itr.hasNext();){
					ClaimCodeSequence claimCodeSeqObject = (ClaimCodeSequence)itr.next();
					Map ruleClaimCodeSeqncViewMap = new LinkedHashMap();
				    addToMap(ruleClaimCodeSeqncViewMap, WebConstants.RULE_SEQUENCE_NUMBER, claimCodeSeqObject.getRuleSqncNbr() );
					addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_CODE_SEQUENCE, claimCodeSeqObject.getClaimSqncNbr());
					addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_CODE_SEQUENCE_NUMBER, claimCodeSeqObject.getClaimCodeSqncNbr());
					if(claimCodeSeqObject.getClaimHcptLowVal() !=null && claimCodeSeqObject.getClaimHcptHighVal()!=null)
                    addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_HCPT_VAL.concat(claimCodeSeqObject.getClaimHcptLowVal()).concat("-").concat(claimCodeSeqObject.getClaimHcptHighVal()), claimCodeSeqObject.getClaimHcptLowVal().concat("-").concat(claimCodeSeqObject.getClaimHcptHighVal()));
					if(claimCodeSeqObject.getClaimDiagLowVal() !=null && claimCodeSeqObject.getClaimDiagHighVal()!=null)
					addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_DIAG_VAL.concat(claimCodeSeqObject.getClaimDiagLowVal()).concat("-").concat(claimCodeSeqObject.getClaimDiagHighVal()).concat("(").concat(claimCodeSeqObject.getClaimDiagVrsnIndicator()).concat(")"), claimCodeSeqObject.getClaimDiagLowVal().concat("-").concat(claimCodeSeqObject.getClaimDiagHighVal()));
					if(claimCodeSeqObject.getClaimRevLowVal() !=null && claimCodeSeqObject.getClaimRevHighVal()!=null)
					addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_REV_VAL.concat(claimCodeSeqObject.getClaimRevLowVal()).concat("-").concat(claimCodeSeqObject.getClaimRevHighVal()), claimCodeSeqObject.getClaimRevLowVal().concat(claimCodeSeqObject.getClaimRevHighVal()));
					if(claimCodeSeqObject.getClaimIcdpLowVal() !=null && claimCodeSeqObject.getClaimIcdpHighVal()!=null)
					addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_ICDP_VAL.concat(claimCodeSeqObject.getClaimIcdpLowVal()).concat("-").concat(claimCodeSeqObject.getClaimIcdpHighVal()).concat("(").concat(claimCodeSeqObject.getClaimIcdVrsnIndicator()).concat(")"), claimCodeSeqObject.getClaimIcdpLowVal().concat("-").concat(claimCodeSeqObject.getClaimIcdpHighVal()));
					if(claimCodeSeqObject.getClaimSrvcClsLow() !=null && claimCodeSeqObject.getClaimSrvcClsHigh()!=null)
					addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_SRVC_CLS.concat(claimCodeSeqObject.getClaimSrvcClsLow()).concat("-").concat(claimCodeSeqObject.getClaimSrvcClsHigh()), claimCodeSeqObject.getClaimSrvcClsLow().concat("-").concat(claimCodeSeqObject.getClaimSrvcClsHigh()));
					if(claimCodeSeqObject.getClaimLmtClsLow() !=null && claimCodeSeqObject.getClaimLmtClsHigh()!=null)
					addToMap(ruleClaimCodeSeqncViewMap, WebConstants.CLAIM_LMT_CLS.concat(claimCodeSeqObject.getClaimLmtClsLow()).concat("-").concat(claimCodeSeqObject.getClaimLmtClsHigh()), claimCodeSeqObject.getClaimLmtClsLow().concat("-").concat(claimCodeSeqObject.getClaimLmtClsHigh()));
					ruleCount++;
					ruleViewClaimCodeSequenceList.add(ruleClaimCodeSeqncViewMap);
				}
				}
				
				map.put("ruleViewClaimLevelSequenceList", ruleViewClaimLevelSequenceList);
				map.put("ruleViewClaimCodeSequenceList", ruleViewClaimCodeSequenceList);
				map.put("ruleViewCodeSequenceList", ruleViewCodeSequenceList);
				// End-- Added as part of ICD10-October release//
				map.put("title","View Rule");
				map.put("ruleViewSequenceList", ruleViewSequenceList);
				map.put("ruleInfo", umRuleObj);
			}
		}else{
			infoList.add("No results to display.");
			map.put("title","View Rule");
			map.put(WebConstants.INFO_MESSAGES, infoList);
		}
		return new ModelAndView("ruleView",map);
	}
	
	private Map addToMap(Map ruleViewMap, String key, String value){
		if(null !=  value && !"".equals(value.trim()) && !"0".equals(value.trim())){
			ruleViewMap.put(key, value);
		}
		return ruleViewMap;
	}
	
	public LocateFacade getLocateFacade() {
		return locateFacade;
	}

	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}

	

}
