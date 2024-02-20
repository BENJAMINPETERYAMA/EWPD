/*
 * Created on Apr 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.product;

/**
 * @author U14231
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductPrintInfoBackingBean extends ProductBackingBean {

    private String printSubGenInf;

    private String printSubBenDef;

    private String printSubManInfo;

    private String printSubNotes;

    private String printProductAdminQuestions;


    /**
     * @return Returns the printSubBenDef.
     */
    public String getPrintSubBenDef() {
        if (null != getRequest().getParameter("printValueForBenDet") && getRequest().getParameter("printValueForBenDet").matches("^[0-9a-zA-Z_]+$")){
        	String printValueForBenDet = getRequest().getParameter("printValueForBenDet");
            printSubBenDef = printValueForBenDet;
        }
        return printSubBenDef;
    }


    /**
     * @param printSubBenDef
     *            The printSubBenDef to set.
     */
    public void setPrintSubBenDef(String printSubBenDef) {
        this.printSubBenDef = printSubBenDef;
    }


    /**
     * @return Returns the printSubGenInf.
     */
    public String getPrintSubGenInf() {
        
        if (null != getRequest().getParameter("printValueForGenInfo") && getRequest().getParameter("printValueForGenInfo").matches("^[0-9a-zA-Z_]+$")){
        	String printValueForGenInf = getRequest().getParameter("printValueForGenInfo");
            printSubGenInf = printValueForGenInf;
        }
        return printSubGenInf;
    }


    /**
     * @param printSubGenInf
     *            The printSubGenInf to set.
     */
    public void setPrintSubGenInf(String printSubGenInf) {
        this.printSubGenInf = printSubGenInf;
    }


    public String forwardPrint() {
        String printValueForBenDet = this.getPrintSubBenDef();
        String printValueForGenInfo = this.getPrintSubGenInf();
        String printValueForManInfo = this.getPrintSubManInfo();
        String printValueForNotes = this.getPrintSubNotes();
        getRequest().setAttribute("printValueForBenDet", printValueForBenDet);
        getRequest().setAttribute("printValueForGenInfo", printValueForGenInfo);
        getRequest().setAttribute("printValueForManInfo", printValueForManInfo);
        getRequest().setAttribute("printValueForNotes", printValueForNotes);
        return "forwardDetailedPrint";
    }


    public String benefitAdminPrint() {
        return "forwardBenefitAdminPrint";
    }


    public String contractProductAdminPrint() {
        return "forwardContractProductAdminPrint";
    }


    public String contractBenefitAdminPrint() {
        return "forwardContractBenefitAdminPrint";
    }


    /**
     * @return printProductAdminQuestions
     * 
     * Returns the printProductAdminQuestions.
     */
    public String getPrintProductAdminQuestions() {
        if (null != getRequest().getParameter("printValueForAdminQuestion") && getRequest().getParameter("printValueForAdminQuestion").matches("^[0-9a-zA-Z_]+$")){
        	String printValueForAdminQuestion = getRequest().getParameter("printValueForAdminQuestion");
            printProductAdminQuestions = printValueForAdminQuestion;
        }
        return printProductAdminQuestions;
    }


    /**
     * @param printProductAdminQuestions
     * 
     * Sets the printProductAdminQuestions.
     */
    public void setPrintProductAdminQuestions(String printProductAdminQuestions) {
        this.printProductAdminQuestions = printProductAdminQuestions;
    }


    public String forwardProdQuestionAdminPrint() {
        String printValueForAdminQuestion = this
                .getPrintProductAdminQuestions();
        getRequest().setAttribute("printValueForAdminQuestion",
                printValueForAdminQuestion);
        return "forwardProdQuestionAdminPrint";
    }


    /**
     * @return printSubManInfo
     * 
     * Returns the printSubManInfo.
     */
    public String getPrintSubManInfo() {
        if (null != getRequest().getParameter("printValueForManInfo") && getRequest().getParameter("printValueForManInfo").matches("^[0-9a-zA-Z_]+$")){
        	String printValueForManInfo = getRequest().getParameter("printValueForManInfo");
            printSubManInfo = printValueForManInfo;
        }
        return printSubManInfo;
    }


    /**
     * @param printSubManInfo
     * 
     * Sets the printSubManInfo.
     */
    public void setPrintSubManInfo(String printSubManInfo) {
        this.printSubManInfo = printSubManInfo;
    }


    /**
     * @return printSubNotes
     * 
     * Returns the printSubNotes.
     */
    public String getPrintSubNotes() {
        if (null != getRequest().getParameter("printValueForNotes") && getRequest().getParameter("printValueForNotes").matches("^[0-9a-zA-Z_]+$")){
        	String printValueForNotes = getRequest().getParameter("printValueForNotes");
            printSubNotes = printValueForNotes;
        }
        return printSubNotes;
    }


    /**
     * @param printSubNotes
     * 
     * Sets the printSubNotes.
     */
    public void setPrintSubNotes(String printSubNotes) {
        this.printSubNotes = printSubNotes;
    }
}