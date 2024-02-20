/*
 * Created on Jun 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainBO;
import com.wellpoint.wpd.common.notes.request.NoteDomainRequest;
import com.wellpoint.wpd.common.notes.request.RetrieveNotesRequest;
import com.wellpoint.wpd.common.notes.response.NoteDomainResponse;
import com.wellpoint.wpd.common.notes.response.RetrieveNotesResponse;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author u13541
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NotesCompareBackingBean extends WPDBackingBean {

    private String noteId;

    private String name;

    private String type;

    private String systemDomain;

    private String text;

    private String lastUpdatedTimestamp;

    private String product;

    private String benefit;
    
    private String question;

    private String benefitComponent;

    private String term;

    private String qualifier;

    private String noteIdCompare;

    private String nameCompare;

    private String typeCompare;

    private String systemDomainCompare;

    private String textCompare;

    private String lastUpdatedTimestampCompare;

    private String productCompare;

    private String benefitCompare;
    
    private String questionCompare;

    private String benefitComponentCompare;

    private String termCompare;

    private String qualifierCompare;

    private String loadCompare;

    private String status;

    private String statusCompare;

    private int version;

    private int versionCompare;

    private boolean notesCompare;

    private String loadCompareResults;

    private List productList;

    private List benefitList;

    private List componentList;
    
    private List questionList;

    private List termList;

    private List qualifierList;

    private List systemDomainList;

    private List systemDomainListCompare;

    private boolean compareClicked = false;
    
    //value to change the font color in page according to comparison
    private boolean versionCompareFlag = false;

    Application application = FacesContext.getCurrentInstance()
            .getApplication();

    //Creating Notes Backing Bean
    NotesBackingBean notesBackingBean = ((NotesBackingBean) application
            .createValueBinding("#{notesBackingBean}").getValue(
                    FacesContext.getCurrentInstance()));

    private String validateNoteName = "false";


    /**
     * @return Returns the benefit.
     */
    public String getBenefit() {
        return benefit;
    }


    /**
     * @param benefit
     *            The benefit to set.
     */
    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }


    /**
     * @return Returns the benefitCompare.
     */
    public String getBenefitCompare() {
        return benefitCompare;
    }


    /**
     * @param benefitCompare
     *            The benefitCompare to set.
     */
    public void setBenefitCompare(String benefitCompare) {
        this.benefitCompare = benefitCompare;
    }


    /**
     * @return Returns the benefitComponent.
     */
    public String getBenefitComponent() {
        return benefitComponent;
    }


    /**
     * @param benefitComponent
     *            The benefitComponent to set.
     */
    public void setBenefitComponent(String benefitComponent) {
        this.benefitComponent = benefitComponent;
    }


    /**
     * @return Returns the benefitComponentCompare.
     */
    public String getBenefitComponentCompare() {
        return benefitComponentCompare;
    }


    /**
     * @param benefitComponentCompare
     *            The benefitComponentCompare to set.
     */
    public void setBenefitComponentCompare(String benefitComponentCompare) {
        this.benefitComponentCompare = benefitComponentCompare;
    }


    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public String getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * @return Returns the lastUpdatedTimestampCompare.
     */
    public String getLastUpdatedTimestampCompare() {
        return lastUpdatedTimestampCompare;
    }


    /**
     * @param lastUpdatedTimestampCompare
     *            The lastUpdatedTimestampCompare to set.
     */
    public void setLastUpdatedTimestampCompare(
            String lastUpdatedTimestampCompare) {
        this.lastUpdatedTimestampCompare = lastUpdatedTimestampCompare;
    }


    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }


    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return Returns the nameCompare.
     */
    public String getNameCompare() {
        return nameCompare;
    }


    /**
     * @param nameCompare
     *            The nameCompare to set.
     */
    public void setNameCompare(String nameCompare) {
        this.nameCompare = nameCompare;
    }


    /**
     * @return Returns the noteId.
     */
    public String getNoteId() {
        return noteId;
    }


    /**
     * @param noteId
     *            The noteId to set.
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }


    /**
     * @return Returns the noteIdCompare.
     */
    public String getNoteIdCompare() {
        return noteIdCompare;
    }


    /**
     * @param noteIdCompare
     *            The noteIdCompare to set.
     */
    public void setNoteIdCompare(String noteIdCompare) {
        this.noteIdCompare = noteIdCompare;
    }


    /**
     * @return Returns the product.
     */
    public String getProduct() {
        return product;
    }


    /**
     * @param product
     *            The product to set.
     */
    public void setProduct(String product) {
        this.product = product;
    }


    /**
     * @return Returns the productCompare.
     */
    public String getProductCompare() {
        return productCompare;
    }


    /**
     * @param productCompare
     *            The productCompare to set.
     */
    public void setProductCompare(String productCompare) {
        this.productCompare = productCompare;
    }


    /**
     * @return Returns the qualifier.
     */
    public String getQualifier() {
        return qualifier;
    }


    /**
     * @param qualifier
     *            The qualifier to set.
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }


    /**
     * @return Returns the qualifierCompare.
     */
    public String getQualifierCompare() {
        return qualifierCompare;
    }


    /**
     * @param qualifierCompare
     *            The qualifierCompare to set.
     */
    public void setQualifierCompare(String qualifierCompare) {
        this.qualifierCompare = qualifierCompare;
    }


    /**
     * @return Returns the systemDomain.
     */
    public String getSystemDomain() {
        return systemDomain;
    }


    /**
     * @param systemDomain
     *            The systemDomain to set.
     */
    public void setSystemDomain(String systemDomain) {
        this.systemDomain = systemDomain;
    }


    /**
     * @return Returns the systemDomainCompare.
     */
    public String getSystemDomainCompare() {
        return systemDomainCompare;
    }


    /**
     * @param systemDomainCompare
     *            The systemDomainCompare to set.
     */
    public void setSystemDomainCompare(String systemDomainCompare) {
        this.systemDomainCompare = systemDomainCompare;
    }


    /**
     * @return Returns the term.
     */
    public String getTerm() {
        return term;
    }


    /**
     * @param term
     *            The term to set.
     */
    public void setTerm(String term) {
        this.term = term;
    }


    /**
     * @return Returns the termCompare.
     */
    public String getTermCompare() {
        return termCompare;
    }


    /**
     * @param termCompare
     *            The termCompare to set.
     */
    public void setTermCompare(String termCompare) {
        this.termCompare = termCompare;
    }


    /**
     * @return Returns the text.
     */
    public String getText() {
        return text;
    }


    /**
     * @param text
     *            The text to set.
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * @return Returns the textCompare.
     */
    public String getTextCompare() {
        return textCompare;
    }


    /**
     * @param textCompare
     *            The textCompare to set.
     */
    public void setTextCompare(String textCompare) {
        this.textCompare = textCompare;
    }


    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }


    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * @return Returns the typeCompare.
     */
    public String getTypeCompare() {
        return typeCompare;
    }


    /**
     * @param typeCompare
     *            The typeCompare to set.
     */
    public void setTypeCompare(String typeCompare) {
        this.typeCompare = typeCompare;
    }


    /**
     * @return Returns the loadCompare.
     */

    public String getLoadCompare() {
        if (null != getRequest().getParameter("noteName")) {
        	String noteName=ESAPI.encoder().encodeForHTML(getRequest().getParameter("noteName"));
            this.setName(noteName);
            getSession().setAttribute("noteName",
            		noteName);
        } else {
            this.setName((String) getSession().getAttribute("noteName"));
        }
        return "";
    }


    /**
     * @param loadCompare
     *            The loadCompare to set.
     */
    public void setLoadCompare(String loadCompare) {
        this.loadCompare = loadCompare;
    }


    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }


    /**
     * @param version
     *            The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * @return Returns the notesCompare.
     */
    public boolean isNotesCompare() {
        return notesCompare;
    }


    /**
     * @param notesCompare
     *            The notesCompare to set.
     */
    public void setNotesCompare(boolean notesCompare) {
        this.notesCompare = notesCompare;
    }


    /**
     * @return Returns the loadCompareResults.
     */
    public String getLoadCompareResults() {
        this.compareClicked = true;
        retrieveNotesForCompare();
        return "";
    }


    private void copyValuesToBackingBeanFromResponse(NoteBO noteBO) {
        this.setNoteId(noteBO.getNoteId());
        this.setName(noteBO.getNoteName());
        this.setText(noteBO.getNoteText());
        this.setType(noteBO.getNoteType());
        this.setSystemDomainList((List) noteBO.getSystemDomain());
        this
                .setSystemDomain(getTildaStringFromList(this
                        .getSystemDomainList()));
        this.setLastUpdatedTimestamp(WPDStringUtil.getStringDateFormat(noteBO
                .getLastUpdatedTimestamp()));
        this.setStatus(noteBO.getStatus());
    }


    private String getTildaStringFromList(List list) {
        NoteDomainBO noteDomainBO = new NoteDomainBO();
        String domainString = new String();
        for (int i = 1; i <= list.size(); i++) {
            noteDomainBO = (NoteDomainBO) list.get(i - 1);
            if (i == list.size()) {
                domainString = domainString
                        + noteDomainBO.getSystemDomainDescription() + "~"
                        + noteDomainBO.getSystemDomainId();
            } else {
                domainString = domainString
                        + noteDomainBO.getSystemDomainDescription() + "~"
                        + noteDomainBO.getSystemDomainId() + "~";
            }
        }
        return domainString;
    }


    /**
     * @param loadCompareResults
     *            The loadCompareResults to set.
     */
    public void setLoadCompareResults(String loadCompareResults) {
        this.loadCompareResults = loadCompareResults;
    }


    /**
     * @return Returns the benefitList.
     */
    public List getBenefitList() {
        return benefitList;
    }


    /**
     * @param benefitList
     *            The benefitList to set.
     */
    public void setBenefitList(List benefitList) {
        this.benefitList = benefitList;
    }


    /**
     * @return Returns the componentList.
     */
    public List getComponentList() {
        return componentList;
    }


    /**
     * @param componentList
     *            The componentList to set.
     */
    public void setComponentList(List componentList) {
        this.componentList = componentList;
    }


    /**
     * @return Returns the productList.
     */
    public List getProductList() {
        return productList;
    }


    /**
     * @param productList
     *            The productList to set.
     */
    public void setProductList(List productList) {
        this.productList = productList;
    }


    /**
     * @return Returns the qualifierList.
     */
    public List getQualifierList() {
        return qualifierList;
    }


    /**
     * @param qualifierList
     *            The qualifierList to set.
     */
    public void setQualifierList(List qualifierList) {
        this.qualifierList = qualifierList;
    }


    /**
     * @return Returns the termList.
     */
    public List getTermList() {
        return termList;
    }


    /**
     * @param termList
     *            The termList to set.
     */
    public void setTermList(List termList) {
        this.termList = termList;
    }


    /**
     * @return Returns the systemDomainList.
     */
    public List getSystemDomainList() {
        return systemDomainList;
    }


    /**
     * @param systemDomainList
     *            The systemDomainList to set.
     */
    public void setSystemDomainList(List systemDomainList) {
        this.systemDomainList = systemDomainList;
    }


    public String retrieveNotesForCompare() {
        List noteTypeList = (List) this.getSession().getAttribute(
                "noteTypeList");
        RetrieveNotesRequest retrieveNotesRequest = new RetrieveNotesRequest();
        if (null != this.getNameCompare()) {
            retrieveNotesRequest.setNoteName(this.getNameCompare());
            getSession().setAttribute("noteNameforCompare",
                    this.getNameCompare());
            getSession().setAttribute("baseNoteName", this.getName());
        }
        if (null == this.getNameCompare()) {
            retrieveNotesRequest.setNoteName((String) getSession()
                    .getAttribute("noteNameforCompare"));
        }
        retrieveNotesRequest.setAction("compare");
        RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse) executeService(retrieveNotesRequest);
        if (null == retrieveNotesResponse) {
            List validationMessages = new ArrayList();
            validationMessages.add(new ErrorMessage("noteName.field.invalid"));
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (this.compareClicked != true && null != retrieveNotesResponse) {
            this.validateNoteName = "true";
            return "";
        }
        List systemDomain = (List) retrieveNotesResponse.getNoteBO()
                .getSystemDomain();
        NoteDomainBO noteSystemDomainBO = null;
        String systemDomainString = "";
        for (int i = 1; i <= systemDomain.size(); i++) {
            noteSystemDomainBO = (NoteDomainBO) systemDomain.get(i - 1);
            if (i == systemDomain.size()) {
                systemDomainString = systemDomainString
                        + noteSystemDomainBO.getSystemDomainDescription() + "~"
                        + noteSystemDomainBO.getSystemDomainIds().get(0);
            } else {
                systemDomainString = systemDomainString
                        + noteSystemDomainBO.getSystemDomainDescription() + "~"
                        + noteSystemDomainBO.getSystemDomainIds().get(0) + "~";
            }
        }
        this.systemDomain = systemDomainString;
        copyValuesToBackingBean(retrieveNotesResponse.getNoteBO());

        if (null != noteTypeList) {
            Iterator iterator = noteTypeList.listIterator();
            while (iterator.hasNext()) {
                SelectItem referenceData = (SelectItem) iterator.next();
                if (this.getTypeCompare().equals(referenceData.getValue())) {
                    this.typeCompare = referenceData.getLabel();
                }
            }
        }
        this.validateNoteName = "true";
        this.setVersionCompare(retrieveNotesResponse.getNoteBO().getVersion());
        retrieveNoteDomainsForCompare();
        return "";
    }


    public void retireveBaseNoteForCompare() {
        List noteTypeList = (List) this.getSession().getAttribute(
                "noteTypeList");
        RetrieveNotesRequest retrieveNotesRequest = new RetrieveNotesRequest();
        if (null != this.getName()) {
            retrieveNotesRequest.setNoteName(this.getName());
            getSession().setAttribute("baseNoteName", this.getName());
        }
        if (null == this.getName()) {
            retrieveNotesRequest.setNoteName((String) getSession()
                    .getAttribute("baseNoteName"));
        }
        retrieveNotesRequest.setAction("compare");
        RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse) executeService(retrieveNotesRequest);
        
        if(null != retrieveNotesResponse){

        List systemDomain = (List) retrieveNotesResponse.getNoteBO()
                .getSystemDomain();
        NoteDomainBO noteSystemDomainBO = null;
        String systemDomainString = "";
        if(null!=systemDomain && systemDomain.size()>0){
	        for (int i = 1; i <= systemDomain.size(); i++) {
	            noteSystemDomainBO = (NoteDomainBO) systemDomain.get(i - 1);
	            if (i == systemDomain.size()) {
	                systemDomainString = systemDomainString
	                        + noteSystemDomainBO.getSystemDomainDescription() + "~"
	                        + noteSystemDomainBO.getSystemDomainIds().get(0);
	            } else {
	                systemDomainString = systemDomainString
	                        + noteSystemDomainBO.getSystemDomainDescription() + "~"
	                        + noteSystemDomainBO.getSystemDomainIds().get(0) + "~";
	            }
	        }
        }
        this.systemDomain = systemDomainString;
    
        copyValuesToBackingBeanFromResponse(retrieveNotesResponse.getNoteBO());
        if (null != noteTypeList) {
            Iterator iterator = noteTypeList.listIterator();
            while (iterator.hasNext()) {
                SelectItem referenceData = (SelectItem) iterator.next();
                if (this.getType().equals(referenceData.getValue())) {
                    this.type = referenceData.getLabel();
                }
            }
        }
        this.setVersion(retrieveNotesResponse.getNoteBO().getVersion());
       
        retrieveBaseNoteDomainsForCompare();
        }
    }


    private void copyValuesToBackingBean(NoteBO noteBO) {
        this.setNoteIdCompare(noteBO.getNoteId());
        this.setNameCompare(noteBO.getNoteName());
        this.setTextCompare(noteBO.getNoteText());
        this.setTypeCompare(noteBO.getNoteType());
        this.setSystemDomainListCompare((List) noteBO.getSystemDomain());
        this.setSystemDomainCompare(getTildaStringFromList(this
                .getSystemDomainListCompare()));
        this.setLastUpdatedTimestampCompare(WPDStringUtil
                .getStringDateFormat(noteBO.getLastUpdatedTimestamp()));
        this.setStatusCompare(noteBO.getStatus());
    }


    /**
     * @return Returns the systemDomainListCompare.
     */
    public List getSystemDomainListCompare() {
        return systemDomainListCompare;
    }


    /**
     * @param systemDomainListCompare
     *            The systemDomainListCompare to set.
     */
    public void setSystemDomainListCompare(List systemDomainListCompare) {
        this.systemDomainListCompare = systemDomainListCompare;
    }


    public void retrieveNoteDomainsForCompare() {
        NoteDomainRequest noteDomainRequest = (NoteDomainRequest) this
                .getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
        noteDomainRequest.setAction("dataDomains");
        noteDomainRequest.setNoteId(this.noteIdCompare);      
        noteDomainRequest.setVersion(this.getVersionCompare());
        //Creating the response object
        NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);
        if (null != noteDomainResponse) {
            if (null != noteDomainResponse.getResults()) {
                HashMap map = (HashMap) noteDomainResponse.getResults().get(0);                
                setProductList(getCurrentVersionList((List) map.get("product"),getVersionCompare()));                
                setBenefitList(getCurrentVersionList((List) map.get("benefit"),getVersionCompare()));                
                setComponentList(getCurrentVersionList((List) map.get("benefitcomp"),getVersionCompare()));                
                setTermList(getCurrentVersionList((List) map.get("term"),getVersionCompare()));                
                setQualifierList(getCurrentVersionList((List) map.get("qualifier"),getVersionCompare()));                
                setQuestionList(getCurrentVersionList((List) map.get("question"),getVersionCompare()));              
            }
        }
      
        
        if (null != this.getProductList() || this.getProductList().size() != 0) {
            String prodCompare = getTildaStringFromList(this.getProductList());
            this.setProductCompare(prodCompare);
        }
        if (null != this.getBenefitList() || this.getBenefitList().size() != 0) {
            String benCompare = getTildaStringFromList(this.getBenefitList());
            this.setBenefitCompare(benCompare);
        }
        if (null != this.getComponentList()
                || this.getComponentList().size() != 0) {
            String benCompCompare = getTildaStringFromList(this
                    .getComponentList());
            this.setBenefitComponentCompare(benCompCompare);
        }
        if (null != this.getQuestionList() || this.getQuestionList().size() != 0) {
            String question = getTildaStringFromList(this.getQuestionList());
            this.setQuestionCompare(question);
        }
        if (null != this.getTermList() || this.getTermList().size() != 0) {
            String termCompare = getTildaStringFromList(this.getTermList());
            this.setTermCompare(termCompare);
        }
        if (null != this.getQualifierList()
                || this.getQualifierList().size() != 0) {
            String qualifierCompare = getTildaStringFromList(this
                    .getQualifierList());
            this.setQualifierCompare(qualifierCompare);
        }
        retireveBaseNoteForCompare();
    }


    public void retrieveBaseNoteDomainsForCompare() {
        NoteDomainRequest noteDomainRequest = (NoteDomainRequest) this
                .getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
        noteDomainRequest.setAction("dataDomains");
        noteDomainRequest.setNoteId(this.noteId);       
        noteDomainRequest.setVersion(this.getVersion());
        //Creating the response object
        NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);
        if (null != noteDomainResponse) {
            if (null != noteDomainResponse.getResults()) {
                HashMap map = (HashMap) noteDomainResponse.getResults().get(0);
                setProductList(getCurrentVersionList((List) map.get("product"),getVersion()));                
                setBenefitList(getCurrentVersionList((List) map.get("benefit"),getVersion()));                
                setComponentList(getCurrentVersionList((List) map.get("benefitcomp"),getVersion()));                
                setTermList(getCurrentVersionList((List) map.get("term"),getVersion()));                
                setQualifierList(getCurrentVersionList((List) map.get("qualifier"),getVersion()));                
                setQuestionList(getCurrentVersionList((List) map.get("question"),getVersion()));              
            }
        }
        if (null != this.getProductList() || this.getProductList().size() != 0) {
            this.setProduct(getTildaStringFromList(this.getProductList()));
        }
        if (null != this.getBenefitList() || this.getBenefitList().size() != 0) {
            this.setBenefit(getTildaStringFromList(this.getBenefitList()));
        }
        if (null != this.getQuestionList() || this.getQuestionList().size() != 0) {
            this.setQuestion(getTildaStringFromList(this.getQuestionList()));
        }
        if (null != this.getComponentList()
                || this.getComponentList().size() != 0) {
            this.setBenefitComponent(getTildaStringFromList(this
                    .getComponentList()));
        }
        if (null != this.getTermList() || this.getTermList().size() != 0) {
            this.setTerm(getTildaStringFromList(this.getTermList()));
        }
        if (null != this.getQualifierList()
                || this.getQualifierList().size() != 0) {
            this.setQualifier(getTildaStringFromList(this.getQualifierList()));
        }
        this.setBreadCrumbText("Notes Library >> Notes (" + this.name + " & "
                + this.nameCompare + ") >> Compare");
        renderWithFont();

    }


    private void renderWithFont() {
        //		if(!this.noteIdCompare.equals(this.noteId)){
        //			this.setNoteIdCompare(renderText(this.noteIdCompare,this.noteId));
        //		}
        if (!this.nameCompare.equals(this.name)) {
            this.setNameCompare(renderText(this.nameCompare, this.name,false));
        }
        if (!this.typeCompare.equals(this.type)) {
            this.setTypeCompare(renderType(this.typeCompare, this.type));
        }
        if (!this.lastUpdatedTimestampCompare.equals(this.lastUpdatedTimestamp)) {
            this
                    .setLastUpdatedTimestampCompare(renderText(
                            this.lastUpdatedTimestampCompare,
                            this.lastUpdatedTimestamp,false));
        }
        if (!this.statusCompare.equals(this.status)) {
            this.setStatusCompare(renderText(this.statusCompare, this.status,false));
        }
        if (!this.textCompare.equals(this.text)) {
            this.setTextCompare(renderText(this.textCompare, this.text,true));
        }
        if (!this.systemDomainCompare.equals(this.systemDomain)) {
            this.setSystemDomainCompare(renderTextForList(
                    this.systemDomainCompare, this.systemDomain));
        }
        if (!this.benefitCompare.equals(this.benefit)) {
            this.setBenefitCompare(renderTextForList(this.benefitCompare,
                    this.benefit));
        }
        if (!this.questionCompare.equals(this.question)) {
            this.setQuestionCompare(renderTextForList(this.questionCompare,
                    this.question));
        }
        if (!this.productCompare.equals(this.product)) {
            this.setProductCompare(renderTextForList(this.productCompare,
                    this.product));
        }
        if (!this.benefitComponentCompare.equals(this.benefitComponent)) {
            this.setBenefitComponentCompare(renderTextForList(
                    this.benefitComponentCompare, this.benefitComponent));
        }
        if (!this.termCompare.equals(this.term)) {
            this.setTermCompare(renderTextForList(this.termCompare, this.term));
        }
        if (!this.qualifierCompare.equals(this.qualifier)) {
            this.setQualifierCompare(renderTextForList(this.qualifierCompare,
                    this.qualifier));
        }
//        if(this.version != this.versionCompare){
//        	this.setVersionCompareFlag(true);
//        }
    }


    private String renderText(String textForCompare, String text,boolean textFlag) {
    	//textFlag=false;
    	
        String textForDisplay = "";
        if (null != textForCompare) {
	        if(textFlag==false){
	            text = " " + text.trim() + " ";
	            StringTokenizer stringTokenizer = new StringTokenizer(
	                    textForCompare, " ");
		            while (stringTokenizer.hasMoreTokens()) {
		                String word = " " + stringTokenizer.nextToken() + " ";
		                int firstIndex = text.indexOf(word);
		                if (firstIndex != -1) {
		                    textForDisplay += word;
		                } else {
		                    textForDisplay += "<font style='BACKGROUND-COLOR: yellow'><strong>"
		                            + word + "</strong></font>";
		                }
		            }
	        	}
	    	
	        else {
				boolean flag = false;
				textForCompare = " " + textForCompare.trim() + " ";
				int firstIndex = textForCompare.indexOf(text);
				if (firstIndex != -1) {
					flag = true;
				}
				if (!flag) {
					textForDisplay = "<font style='BACKGROUND-COLOR: yellow'><strong>"
							+ textForCompare + "</strong></font>";
				} else {
					String newString = "<font style='BACKGROUND-COLOR: white'>"
							+ text + "</font>";
					
					
					textForDisplay = "<font style='BACKGROUND-COLOR: yellow'><strong>"
						+ textForCompare.replaceAll(text, newString) + "</strong></font>"; 
				}
			}
        }
        return textForDisplay;
    }
    private String renderType(String textForCompare, String text) {
    	
    	  String textForDisplay = "";
    	  if (null != textForCompare) {
    	  	text = " " + text.trim() + " ";
    	  	if(!textForCompare.equals(text))
    	  		textForDisplay= "<font style='BACKGROUND-COLOR: yellow'><strong>"
					+ textForCompare + "</strong></font>";
			else
				textForDisplay=textForCompare;
    	  }
    	  return textForDisplay;
    }
    


    private String renderTextForList(String textForCompare, String text) {
        String textForDisplay = "";
        if (null != textForCompare) {
            StringTokenizer stringTokenizer = new StringTokenizer(
                    textForCompare, "~");
            while (stringTokenizer.hasMoreTokens()) {
                String word = "~" + stringTokenizer.nextToken();
                text = "~" + text;
                int firstIndex = text.indexOf(word);
                if (firstIndex != -1) {
                    word = word.substring(1);
                    textForDisplay += word + "~";
                } else {
                    word = word.substring(1, word.length());
                    textForDisplay += "<font style='BACKGROUND-COLOR: yellow'><strong>"
                            + word + "</strong></font>" + "~";
                }
            }
        }
        return textForDisplay;
    }


    /**
     * @return Returns the compareClicked.
     */
    public boolean isCompareClicked() {
        return compareClicked;
    }


    /**
     * @param compareClicked
     *            The compareClicked to set.
     */
    public void setCompareClicked(boolean compareClicked) {
        this.compareClicked = compareClicked;
    }


    /**
     * @return Returns the validateNoteName.
     */
    public String getValidateNoteName() {
        return validateNoteName;
    }


    /**
     * @param validateNoteName
     *            The validateNoteName to set.
     */
    public void setValidateNoteName(String validateNoteName) {
        this.validateNoteName = validateNoteName;
    }


    /**
     * @return Returns the versionCompare.
     */
    public int getVersionCompare() {
        return versionCompare;
    }


    /**
     * @param versionCompare
     *            The versionCompare to set.
     */
    public void setVersionCompare(int versionCompare) {
        this.versionCompare = versionCompare;
    }


    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return Returns the statusCompare.
     */
    public String getStatusCompare() {
        return statusCompare;
    }


    /**
     * @param statusCompare
     *            The statusCompare to set.
     */
    public void setStatusCompare(String statusCompare) {
        this.statusCompare = statusCompare;
    }
	/**
	 * @return Returns the versionCompareFlag.
	 */
	public boolean isVersionCompareFlag() {
		return versionCompareFlag;
	}
	/**
	 * @param versionCompareFlag The versionCompareFlag to set.
	 */
	public void setVersionCompareFlag(boolean versionCompareFlag) {
		this.versionCompareFlag = versionCompareFlag;
	}
    /**
     * @return Returns the questionList.
     */
    public List getQuestionList() {
        return questionList;
    }
    /**
     * @param questionList The questionList to set.
     */
    public void setQuestionList(List questionList) {
        this.questionList = questionList;
    }
    /**
     * @return Returns the questionCompare.
     */
    public String getQuestionCompare() {
        return questionCompare;
    }
    /**
     * @param questionCompare The questionCompare to set.
     */
    public void setQuestionCompare(String questionCompare) {
        this.questionCompare = questionCompare;
    }
    /**
     * @return Returns the question.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * @param question The question to set.
     */
    public void setQuestion(String question) {
        this.question = question;
    }
   
   
    /**
     * The method takes a list of NoteDomainBO and groups the objects in the given 
     * version into a list and returns the list
     * @param inputList - list of all versions
     * @param version
     * @return
     */
    private List getCurrentVersionList(List inputList,int version){       
        List currVersionList = new ArrayList();       
        
        for(int i=0;i<inputList.size();i++){
            NoteDomainBO noteDomainBO = (NoteDomainBO)inputList.get(i);
            if(noteDomainBO.getVersion() == version){
                // This list will contain all the domained objects in the current version of the note
                currVersionList.add(noteDomainBO); 
            }           
        }       
        return currVersionList;
    }
}