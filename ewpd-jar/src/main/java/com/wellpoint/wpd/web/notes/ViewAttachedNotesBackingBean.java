/*
 * Created on Jun 15, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.notes;

import java.util.Iterator;
import java.util.List;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

/**
 * @author U18739
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ViewAttachedNotesBackingBean extends WPDBackingBean {

	private String viewNotes;

	private List notes;

	private String noteId;

	private String noteName;

	private String version;

	
	/**
	 * Mehtod used to return the Notes list- The List contains only the selected note data
	 * @return
	 */
	public List getNotes() {

		if (null != this.notes) {
			for (Iterator iter = this.notes.iterator(); iter.hasNext();) {
				NotesAttachmentOverrideBO notesAttachmentOverrideBO = (NotesAttachmentOverrideBO) iter
						.next();
				if ("N".equals(notesAttachmentOverrideBO.getOverrideStatus())) {
					iter.remove();
				}
			}
		}
		return notes;
	}

	public void setNotes(List notes) {
		this.notes = notes;
	}

	/**
	 * Mehtod used to populate the notes list
	 * @return
	 */
	public String getViewNotes() {

		QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();

		QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
		if (null != response) {
			this.setNotes(response.getNotesList());
		}

		return viewNotes;
	}

	public void setViewNotes(String viewNotes) {
		this.viewNotes = viewNotes;
	}

	/**
	 * This method is used to set all the values in the NotesLookUpRequest
	 * object
	 * 
	 * @return NotesLookUpRequest
	 */
	private QuestionNotesPopUpRequest getQuestionNotesPopUpRequest() {

		QuestionNotesPopUpRequest request = (QuestionNotesPopUpRequest) this
				.getServiceRequest(ServiceManager.QUESTION_NOTES_POPUP_REQUEST);

		String questionId;
		String primaryEntityID;
		String primaryType = "";
		String bcId;
		String adminLvlOptionAssnSysId;
		String secondaryEntityType="";

		if (null != getRequest().getParameter("questionId")
				&& !("").equals(getRequest().getParameter("questionId"))) {
			if(null!=getRequest().getParameter("questionId")  && getRequest().getParameter("questionId").matches("^[0-9a-zA-Z_]+$")){
				questionId = getRequest().getParameter("questionId");
				this.getSession().setAttribute("questionId", questionId);
				}
		}
		if (null != getRequest().getParameter("primaryentityId")
				&& !("").equals(getRequest().getParameter("primaryentityId"))) {
			if(null!=getRequest().getParameter("primaryentityId")  && getRequest().getParameter("primaryentityId").matches("^[0-9a-zA-Z_]+$")){
				primaryEntityID = getRequest().getParameter("primaryentityId");
				this.getSession().setAttribute("primaryEntityID", primaryEntityID);
				}
		}

		if (null != getRequest().getParameter("primaryEntytyType")
				&& !("").equals(getRequest().getParameter("primaryEntytyType"))) {
			if(null!=getRequest().getParameter("primaryEntytyType")  && getRequest().getParameter("primaryEntytyType").matches("^[0-9a-zA-Z_]+$")){
				primaryType = getRequest().getParameter("primaryEntytyType");
				this.getSession().setAttribute("primaryType", primaryType);
				}
		}
		if (null != getRequest().getParameter("bcId")
				&& !("").equals(getRequest().getParameter("bcId"))) {
			if(null!=getRequest().getParameter("bcId") && getRequest().getParameter("bcId").matches("^[0-9a-zA-Z_]+$")){
				bcId = getRequest().getParameter("bcId");
				this.getSession().setAttribute("benefitComponentId", bcId);
				}
		}
		if (null != getRequest().getParameter("adminLvlOptionId")
				&& !("").equals(getRequest().getParameter("adminLvlOptionId"))) {
			if(null!=getRequest().getParameter("adminLvlOptionId")  && getRequest().getParameter("adminLvlOptionId").matches("^[0-9a-zA-Z_]+$")){
				adminLvlOptionAssnSysId = getRequest().getParameter("adminLvlOptionId");
				this.getSession().setAttribute("adminLvlOptionAssnSysId",
						adminLvlOptionAssnSysId);
				}
		}

		if (null != getRequest().getParameter("secondaryEntityType")
				&& !("").equals(getRequest().getParameter("secondaryEntityType"))) {
			if(null!=getRequest().getParameter("secondaryEntityType")  && getRequest().getParameter("secondaryEntityType").matches("^[0-9a-zA-Z_]+$")){
				secondaryEntityType = getRequest().getParameter("secondaryEntityType");
				this.getSession().setAttribute("secondaryEntityType", secondaryEntityType);
				}
		}

		request.setPrimaryEntityID((this.getSession()
				.getAttribute("primaryEntityID")).toString());
		request.setPrimaryType(this.getSession().getAttribute("primaryType")
				.toString());
		request.setSecondaryId(this.getSession().getAttribute(
				"adminLvlOptionAssnSysId").toString());
		//		if (null !=
		// this.getSession().getAttribute("benefitDefnId").toString()
		//				&& !("").equals(this.getSession().getAttribute("benefitDefnId")
		//						.toString())
		//				&& !("null").equals(this.getSession().getAttribute(
		//						"benefitDefnId").toString())) {
		//			request.setBenefitDenId(this.getSession().getAttribute(
		//					"benefitDefnId").toString());
		//		}
		if (null != this.getSession().getAttribute("benefitComponentId")
				.toString()
				&& !("").equals(this.getSession().getAttribute(
						"benefitComponentId").toString())
				&& !("null").equals(this.getSession().getAttribute(
						"benefitComponentId").toString())) {
			request.setBenefitComponentId(Integer
					.parseInt(getRequest().getSession().getAttribute(
							"benefitComponentId").toString()));
		}
		if (null != this.getSession().getAttribute("questionId").toString()
				&& !("").equals(this.getSession().getAttribute("questionId")
						.toString())
				&& !("null").equals(this.getSession()
						.getAttribute("questionId").toString())) {
			request.setQuestionId(Integer.parseInt(this.getSession()
					.getAttribute("questionId").toString()));
		}
		request.setSearchAction(1);

		request.setSecondaryEntityType(getRequest().getSession().getAttribute(
		"secondaryEntityType").toString());
		return request;

	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
