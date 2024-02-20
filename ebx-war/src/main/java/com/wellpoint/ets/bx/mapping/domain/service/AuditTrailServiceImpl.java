/*
 * Created on May 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentMappingVO;
import com.wellpoint.ets.bx.mapping.repository.AuditTrailRepository;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AuditTrailServiceImpl implements AuditTrailService{
	
	private AuditTrailRepository auditTrailRepository;
	
	public List retrieveAuditTrail(String variableId, Integer rowNum){
		
		return auditTrailRepository.retrieveAuditTrail(variableId,rowNum);
		
	}
	public List retrieveAllAuditTrail(String variableId){
		
		return auditTrailRepository.retrieveAllAuditTrail(variableId);
	}
	public boolean logMapping(Mapping mapping, String userComments) throws DomainException{
		return logMapping(mapping, userComments,null);
	}
	public AuditTrail retrieveLatestAuditInfo(String mappingStatus, String varaiableId){
		
		return auditTrailRepository.retrieveLatestAuditInfo(mappingStatus, varaiableId);
	}
	/**
	 * @return Returns the auditTrailRepository.
	 */
	public AuditTrailRepository getAuditTrailRepository() {
		return auditTrailRepository;
	}
	/**
	 * @param auditTrailRepository The auditTrailRepository to set.
	 */
	public void setAuditTrailRepository(
			AuditTrailRepository auditTrailRepository) {
		this.auditTrailRepository = auditTrailRepository;
	}
	public boolean logMappingInDetail(Mapping mapping, String userComments) {
		if(mapping == null || mapping.getVariableMappingStatus()== null) {
			throw new DomainException("Invalid Mapping Object! Cannot log Audit in detail.");
		}
		HippaSegmentMappingVO hippaSegmentMappingVO = createHippaSegmentMappingVO(mapping);
		XStream stream = new XStream();
		stream.allowTypes(new Class[] {HippaSegmentMappingVO.class});
		stream.alias("mapping", HippaSegmentMappingVO.class);
		String mapingXml = stream.toXML(hippaSegmentMappingVO);
		AuditTrail auditTrail = createAuditTrailObject(mapping, null, userComments, mapingXml);
		return auditTrailRepository.insertAuditTrail(auditTrail);
	}
	private HippaSegmentMappingVO createHippaSegmentMappingVO(Mapping mapping) {
		HippaSegmentMappingVO hippaSegmentMappingVO = new HippaSegmentMappingVO();
		hippaSegmentMappingVO.setEb01(mapping.getEB01Value());
		hippaSegmentMappingVO.setEb02(mapping.getEB02Value());
		hippaSegmentMappingVO.setEb03(BxUtil.getAsCSV(mapping.getEb03Values()));
		hippaSegmentMappingVO.setEb06(mapping.getEB06Value());
		hippaSegmentMappingVO.setEb09(mapping.getEB09Value());
		hippaSegmentMappingVO.setIi02(mapping.getIi02Value());
		hippaSegmentMappingVO.setHsd01(mapping.getHsd01Value());
		hippaSegmentMappingVO.setHsd02(BxUtil.getAsCSV(mapping.getHsd02Value()));
		hippaSegmentMappingVO.setHsd03(mapping.getHsd03Value());
		hippaSegmentMappingVO.setHsd04(mapping.getHsd04Value());
		hippaSegmentMappingVO.setHsd05(mapping.getHsd05Value());
		hippaSegmentMappingVO.setHsd06(mapping.getHsd06Value());
		hippaSegmentMappingVO.setHsd07(mapping.getHsd07Value());
		hippaSegmentMappingVO.setHsd08(mapping.getHsd08Value());
		hippaSegmentMappingVO.setAccum(BxUtil.getAsCSV(mapping.getAccumValues()));
		hippaSegmentMappingVO.setUtilizationMgmntRule(BxUtil.getAsCSV(mapping.getUmRuleValues()));
		hippaSegmentMappingVO.setMessage(mapping.getMessage());
		hippaSegmentMappingVO.setMessageTypeRequired(mapping.getMsg_type_required());
		hippaSegmentMappingVO.setSensitiveBenefitIndicator(mapping.getSensitiveBenefitIndicator());
		hippaSegmentMappingVO.setNoteTypeCode(mapping.getNoteTypeCodeValue());
		hippaSegmentMappingVO.setNm1MessageSegment(mapping.getNM1MessageSegmentValue());
		return hippaSegmentMappingVO;
	}
	public boolean logMapping(Mapping mapping, String userComments,
			String auditStatus) {
		if(mapping == null || mapping.getVariableMappingStatus()== null) {
			throw new DomainException("Invalid Mapping Object! Cannot log Audit details.");
		}
		AuditTrail auditTrail = createAuditTrailObject( mapping,auditStatus, userComments);
		return auditTrailRepository.insertAuditTrail(auditTrail);
	}
	
	public boolean logMappingInDetail(int inClauseSize, int batchSize, List<Mapping> mappingList, String userComments) {
		AuditTrail auditTrail = null;
		List<AuditTrail> auditList = new ArrayList<AuditTrail>();
		if(null != mappingList && mappingList.size() != 0) {
			for(Mapping mapping : mappingList) {
				if(mapping == null || mapping.getVariableMappingStatus()== null) {
					throw new DomainException("Invalid Mapping Object! Cannot log Audit in detail.");
				}
				List<HippaSegmentMappingVO> hippaSegmentMappingVOList = createHippaSegmentMappingVOList(mapping);
				XStream stream = new XStream();
				stream.allowTypes(new Class[] {HippaSegmentMappingVO.class});
				stream.alias("mapping", HippaSegmentMappingVO.class);
				for(HippaSegmentMappingVO hippaSegmentMappingVO : hippaSegmentMappingVOList) {
					String mapingXml = stream.toXML(hippaSegmentMappingVO);
					auditTrail = createAuditTrailObject(mapping, null, userComments, mapingXml);
					auditList.add(auditTrail);
				}
			}
		}
		return auditTrailRepository.batchInsertAuditTrail(inClauseSize, batchSize, auditList);
	}
	
	private List<HippaSegmentMappingVO> createHippaSegmentMappingVOList(Mapping mapping) {
		List<HippaSegmentMappingVO> hippaSegmentMappingVOList = new ArrayList<HippaSegmentMappingVO>();
		if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association() &&
				mapping.getEb03().getEb03Association().size() != 0) {
			HippaSegmentMappingVO hippaSegmentMappingVO = new HippaSegmentMappingVO();
			hippaSegmentMappingVO = createNonEb03AssociatedHippaSegmentVO(mapping);
			StringBuffer eb03 = new StringBuffer();
			StringBuffer iii02 = new StringBuffer();
			StringBuffer message = new StringBuffer();
			StringBuffer messageRqdInd = new StringBuffer();
			StringBuffer noteType = new StringBuffer();
			for(EB03Association eb03Assn : mapping.getEb03().getEb03Association()) {
				
				if(null != eb03Assn.getEb03()) {
					eb03.append(eb03Assn.getEb03().getValue()).append(",");
				}
				if(null != eb03Assn.getIii02List() && eb03Assn.getIii02List().size() != 0 && null != eb03Assn.getIii02List().get(0)) {
					iii02.append(eb03Assn.getIii02List().get(0).getValue()).append(",");
				}
				if(null != eb03Assn.getNoteType()) {
					noteType.append(eb03Assn.getNoteType().getValue()).append(",");
				}
				if(null != eb03Assn.getMessage()) {
					message.append(eb03Assn.getMessage()).append(",");
				}
				if(null != eb03Assn.getMsgReqdInd()) {
					messageRqdInd.append(eb03Assn.getMsgReqdInd()).append(",");
				}
			}
			
			String eb03String = "";
			if (eb03.length() > 0) {
				eb03String = eb03.substring(0, eb03.length() -1);
			}
			
			String iii02String = "";
			if (iii02.length() > 0) {
				iii02String = iii02.substring(0, iii02.length() -1);
			}
			
			String noteTypeString = "";
			if (noteType.length() > 0) {
				noteTypeString = noteType.substring(0, noteType.length() -1);
			}
			
			String messageString = "";
			if (message.length() > 0) {
				messageString = message.substring(0, message.length() -1);
			}
			
			String messageRqdIndString = "";
			if (messageRqdInd.length() > 0) {
				messageRqdIndString = messageRqdInd.substring(0, messageRqdInd.length() -1);
			}
			
			hippaSegmentMappingVO.setEb03(eb03String);
			hippaSegmentMappingVO.setIi02(iii02String);
			hippaSegmentMappingVO.setNoteTypeCode(noteTypeString);
			hippaSegmentMappingVO.setMessage(messageString);
			hippaSegmentMappingVO.setMessageTypeRequired(messageRqdIndString);
			
			hippaSegmentMappingVOList.add(hippaSegmentMappingVO);
		}
		return hippaSegmentMappingVOList;
	}
	private HippaSegmentMappingVO createNonEb03AssociatedHippaSegmentVO(Mapping mapping) {
		HippaSegmentMappingVO hippaSegmentMappingVO = new HippaSegmentMappingVO();
		hippaSegmentMappingVO.setEb01(mapping.getEB01Value());
		hippaSegmentMappingVO.setEb02(mapping.getEB02Value());
		//hippaSegmentMappingVO.setEb03(BxUtil.getAsCSV(mapping.getEb03Values()));
		hippaSegmentMappingVO.setEb06(mapping.getEB06Value());
		hippaSegmentMappingVO.setEb09(mapping.getEB09Value());
		//hippaSegmentMappingVO.setIi02(mapping.getIi02Value());
		hippaSegmentMappingVO.setHsd01(mapping.getHsd01Value());
		hippaSegmentMappingVO.setHsd02(BxUtil.getAsCSV(mapping.getHsd02Value()));
		hippaSegmentMappingVO.setHsd03(mapping.getHsd03Value());
		hippaSegmentMappingVO.setHsd04(mapping.getHsd04Value());
		hippaSegmentMappingVO.setHsd05(mapping.getHsd05Value());
		hippaSegmentMappingVO.setHsd06(mapping.getHsd06Value());
		hippaSegmentMappingVO.setHsd07(mapping.getHsd07Value());
		hippaSegmentMappingVO.setHsd08(mapping.getHsd08Value());
		hippaSegmentMappingVO.setAccum(BxUtil.getAsCSV(mapping.getAccumValues()));
		hippaSegmentMappingVO.setUtilizationMgmntRule(BxUtil.getAsCSV(mapping.getUmRuleValues()));
		hippaSegmentMappingVO.setSensitiveBenefitIndicator(mapping.getSensitiveBenefitIndicator());
		hippaSegmentMappingVO.setNm1MessageSegment(mapping.getNM1MessageSegmentValue());
		return hippaSegmentMappingVO;
	}
	
	public boolean logMapping(int inClauseSize, int batchSize,List<Mapping> mappingList, String userComments,
			String auditStatus) {
		AuditTrail auditTrail = null;
		List<AuditTrail> auditList = new ArrayList<AuditTrail>();
		if(null != mappingList && mappingList.size() != 0) {
			for(Mapping mapping : mappingList) {
				if(mapping == null || mapping.getVariableMappingStatus()== null) {
					throw new DomainException("Invalid Mapping Object! Cannot log Audit details.");
				}
				auditTrail = createAuditTrailObject( mapping,auditStatus, userComments);
				auditList.add(auditTrail);
			}
		}
		boolean status = auditTrailRepository.batchInsertAuditTrail(inClauseSize, batchSize,auditList);
		return true;
	}
	
	public boolean removeExistingAuditTrail(String variableId, String system) {
		boolean success = (auditTrailRepository.updateEBXAuditTrail(variableId)
				&& auditTrailRepository.removeExistingLGAuditTrail(variableId) 
				&& auditTrailRepository.removeExistingISGAuditTrail(variableId));

		return success;
	}

	private AuditTrail createAuditTrailObject(Mapping mapping,
			String auditStatus, String userCmnts) {
		return createAuditTrailObject(mapping, auditStatus, userCmnts, null);
	}
	
	private AuditTrail createAuditTrailObject(Mapping mapping,
			String auditStatus, String userCmnts, String systemComments) {

		AuditTrail auditTrail = new AuditTrail();
		String auditUser = null;
		String status = mapping.getVariableMappingStatus();
		if (auditStatus == null) {

			if (status.equals(DomainConstants.STATUS_BUILDING)) {
				auditStatus = DomainConstants.AUDIT_STATUS_ADDED;
				auditUser = mapping.getUser().getCreatedUserName();
			}
			else if (status.equals(DomainConstants.STATUS_BEING_MODIFIED)){
				auditStatus = DomainConstants.AUDIT_STATUS_BEING_MODIFIED;
			}else if (status.equals(DomainConstants.STATUS_SCHEDULED_TO_TEST)){
				auditStatus = DomainConstants.AUDIT_STATUS_SEND_TO_TEST;
			}else if (status
					.equals(DomainConstants.AUDIT_STATUS_DISCARD_CHANGES)){
				auditStatus = DomainConstants.AUDIT_STATUS_DISCARD_CHANGES;
			}else if (status
					.equals(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION)){
				auditStatus = DomainConstants.AUDIT_STATUS_APPROVE;
			}else if (status.equals(DomainConstants.STATUS_NOT_APPLICABLE)){
				auditStatus = DomainConstants.AUDIT_STATUS_NOT_APPLICABLE;
			}else if (status.equals(DomainConstants.STATUS_PUBLISHED)){
				auditStatus = DomainConstants.AUDIT_STATUS_PUBLISHED;
			}else if (status.equals(DomainConstants.STATUS_OBJECT_TRANSFERRED)){
				auditStatus = DomainConstants.AUDIT_STATUS_OBJECT_TRANSFERRED;
			}else if (status.equals(DomainConstants.LOCKED)){
				auditStatus = DomainConstants.LOCKED;
			}else if (status.equals(DomainConstants.UNLOCKED)){
				auditStatus = DomainConstants.UNLOCKED;
			}else {
				throw new DomainException(
						"Cannot log Mapping! Invalid Mapping Status");
			}
		}
		auditTrail.setVariableId(mapping.getVariable().getVariableId());
		auditTrail.setVariableDesc(mapping.getVariable().getDescription());
		auditTrail.setMappingStatus(auditStatus);
		auditTrail.setUserComments(userCmnts);
		auditTrail.setSystemComments(systemComments);
		/**
		 * MTM Code Changes
		 */
		if(mapping.isFinalizedFlagModified()){
			if (mapping.isFinalized()){
				auditTrail.setSystemComments("FINALIZED");
			}
			else{
				auditTrail.setSystemComments("NOT_FINALIZED");
			}	
		}
		/**
		 * END
		 */
		
		if(!StringUtils.isBlank(auditStatus)){
			if(DomainConstants.LOCKED.equals(auditStatus)){
				auditTrail.setSystemComments(DomainConstants.LOCKEDMSG);
			}else if(DomainConstants.UNLOCKED.equals(auditStatus)){
				auditTrail.setSystemComments(DomainConstants.UNLOCKEDMSG);
				/* November release - Audit trail for unlocking a mapping
				need to be displayed in eBX audit report */
				mapping.setVariableStatusForAuditTrail(2);
			}
		}
	
		if(auditUser == null)
			auditUser = mapping.getUser().getLastUpdatedUserName();
		if(auditUser == null)
			throw new DomainException(
			"Cannot log Mapping! User not found");
		auditTrail.setCreatedUser(auditUser);
		Integer variableStatusObj = Integer.valueOf(mapping.getVariableStatusForAuditTrail());
		auditTrail.setVariableAuditStatus(variableStatusObj);
		return auditTrail;
	}
	public List retreiveAuditTrailInDetail(String variableId) {
		List auditTrailList = auditTrailRepository.retrieveAuditInfo(variableId, DomainConstants.AUDIT_STATUS_PUBLISHED);
		XStream stream = new XStream();
		stream.allowTypes(new Class[] {HippaSegmentMappingVO.class});
		stream.alias("mapping", HippaSegmentMappingVO.class);
		for (Iterator iterator = auditTrailList.iterator(); iterator.hasNext();) {
			AuditTrail auditTrail = (AuditTrail) iterator.next();
			String systemCommentXml = auditTrail.getSystemComments();
			if(systemCommentXml != null && systemCommentXml.trim().length() != 0) {
				HippaSegmentMappingVO hippaSegmentMappingVO = (HippaSegmentMappingVO)stream.fromXML(systemCommentXml);
				auditTrail.setHippaSegmentMappingVO(hippaSegmentMappingVO);
			}
		}
		return auditTrailList;
	}
}