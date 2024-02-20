/*
 * SearchResultSummaryBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search;

import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.search.pagination.MultipageSearchResult;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchResultSummaryBackingBean.java 34764 2007-09-21 16:32:14Z
 *          U14659 $
 */
public class SearchResultSummaryBackingBean extends WPDBackingBean {



    private int totalCount = 200;
    /**
     * 
     * @return
     */
    public String contract() {
        removeViewAssociationFromSession();
        setBreadCrumbText("Search >> Search Results >> Contracts");
        getSession().setAttribute(SearchConstants.PAGE_NUMBER, new Integer(1));
        getSession().setAttribute(SearchConstants.OBJECT_TYPE,
                SearchConstants.CONTRACT);
        getRequest().setAttribute(SearchConstants.FROM_SUMMARY_PAGE,
                SearchConstants.FROM_SUMMARY_PAGE);
        return getCount(SearchConstants.CONTRACT) == 0 ? ""
                : SearchConstants.CONTRACT;
    }
    /**
     * 
     * @return
     */
    public String product() {
        removeViewAssociationFromSession();
        setBreadCrumbText("Search >> Search Results >> Products");
        getSession().setAttribute(SearchConstants.PAGE_NUMBER, new Integer(1));
        getSession().setAttribute(SearchConstants.OBJECT_TYPE,
                SearchConstants.PRODUCT);
        getRequest().setAttribute(SearchConstants.FROM_SUMMARY_PAGE,
                SearchConstants.FROM_SUMMARY_PAGE);
        return getCount(SearchConstants.PRODUCT) == 0 ? ""
                : SearchConstants.PRODUCT;

    }
    /**
     * 
     * @return
     */
    public String productStructure() {
        removeViewAssociationFromSession();
        setBreadCrumbText("Search >> Search Results >> Product Structures");
        getSession().setAttribute(SearchConstants.PAGE_NUMBER, new Integer(1));
        getSession().setAttribute(SearchConstants.OBJECT_TYPE,
                SearchConstants.PRODUCT_STRUCTURES);
        getRequest().setAttribute(SearchConstants.FROM_SUMMARY_PAGE,
                SearchConstants.FROM_SUMMARY_PAGE);
        return getCount(SearchConstants.PRODUCT_STRUCTURES) == 0 ? ""
                : SearchConstants.PRODUCT_STRUCTURES;

    }
    /**
     * 
     * @return
     */
    public String benefitComponent() {
        removeViewAssociationFromSession();
        setBreadCrumbText("Search >> Search Results >> Benefit Components");
        getSession().setAttribute(SearchConstants.PAGE_NUMBER, new Integer(1));
        getSession().setAttribute(SearchConstants.OBJECT_TYPE,
                SearchConstants.BENEFIT_COMPONENTS);
        getRequest().setAttribute(SearchConstants.FROM_SUMMARY_PAGE,
                SearchConstants.FROM_SUMMARY_PAGE);
        return getCount(SearchConstants.BENEFIT_COMPONENTS) == 0 ? ""
                : SearchConstants.BENEFIT_COMPONENTS;

    }
    /**
     * 
     * @return
     */
    public String benefit() {
        removeViewAssociationFromSession();
        setBreadCrumbText("Search >> Search Results >> Benefits");
        getSession().setAttribute(SearchConstants.PAGE_NUMBER, new Integer(1));
        getSession().setAttribute(SearchConstants.OBJECT_TYPE,
                SearchConstants.BENEFIT);
        getRequest().setAttribute(SearchConstants.FROM_SUMMARY_PAGE,
                SearchConstants.FROM_SUMMARY_PAGE);
        return getCount(SearchConstants.BENEFIT) == 0 ? ""
                : SearchConstants.BENEFIT;

    }
    /**
     * 
     * @return
     */
    public String benefitLevel() {
        removeViewAssociationFromSession();
        setBreadCrumbText("Search >> Search Results >> Benefit Levels");
        getSession().setAttribute(SearchConstants.PAGE_NUMBER, new Integer(1));
        getSession().setAttribute(SearchConstants.OBJECT_TYPE,
                SearchConstants.BENEFIT_LEVEL);
        getRequest().setAttribute(SearchConstants.FROM_SUMMARY_PAGE,
                SearchConstants.FROM_SUMMARY_PAGE);
        return getCount(SearchConstants.BENEFIT_LEVEL) == 0 ? ""
                : SearchConstants.BENEFIT_LEVEL;

    }
    /**
     * 
     * @return
     */
    public String notes() {
        removeViewAssociationFromSession();
        setBreadCrumbText("Search >> Search Results >> Notes");
        getSession().setAttribute(SearchConstants.PAGE_NUMBER, new Integer(1));
        getSession().setAttribute(SearchConstants.OBJECT_TYPE,
                SearchConstants.NOTES);
        getRequest().setAttribute(SearchConstants.FROM_SUMMARY_PAGE,
                SearchConstants.FROM_SUMMARY_PAGE);
        return getCount(SearchConstants.NOTES) == 0 ? ""
                : SearchConstants.NOTES;

    }

    private HtmlSelectBooleanCheckbox contract = null;

    private HtmlPanelGrid panel = null;

    private HtmlPanelGrid printPanel = null;
    /**
     * 
     * @param panel
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }
    /**
     * 
     * @return
     */
    public HtmlPanelGrid getPanel() {
        panel = new HtmlPanelGrid();
        //panel.setWidth("500");
        panel.setColumns(2);
        panel.setBorder(0);

        panel.setStyleClass("outputText");
        panel.setCellpadding("3");
        panel.setCellspacing("1");

        panel.setHeaderClass("Head");

        HtmlOutputText norLabel = new HtmlOutputText();
        norLabel.setValue("Number of Results");
        HtmlOutputText norValue = new HtmlOutputText();
        norValue.setValue("" + getTotalCount());
        panel.getChildren().add(norLabel);
        panel.getChildren().add(norValue);
        if (null != getSession().getAttribute(SearchConstants.CONTRACT)) {

            HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
            HtmlOutputText htmlOutputtext = new HtmlOutputText();
            MethodBinding methodBinding = FacesContext.getCurrentInstance()
                    .getApplication().createMethodBinding(
                            "#{searchResultSummaryBackingBean.contract}",
                            new Class[] {});
            htmlCommandLink.setAction(methodBinding);

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contract");
            htmlOutputtext.setEscape(false);
            htmlCommandLink.getChildren().add(htmlOutputtext);
            panel.getChildren().add(htmlCommandLink);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.CONTRACT));
            int actualCount = getActualCount(SearchConstants.CONTRACT);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            panel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.PRODUCT)) {
            HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
            HtmlOutputText htmlOutputtext = new HtmlOutputText();
            MethodBinding methodBinding = FacesContext.getCurrentInstance()
                    .getApplication().createMethodBinding(
                            "#{searchResultSummaryBackingBean.product}",
                            new Class[] {});
            htmlCommandLink.setAction(methodBinding);

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Product");
            htmlCommandLink.getChildren().add(htmlOutputtext);
            htmlOutputtext.setEscape(false);
            panel.getChildren().add(htmlCommandLink);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.PRODUCT));
            int actualCount = getActualCount(SearchConstants.PRODUCT);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            panel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(
                SearchConstants.PRODUCT_STRUCTURES)) {

            HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
            HtmlOutputText htmlOutputtext = new HtmlOutputText();
            MethodBinding methodBinding = FacesContext
                    .getCurrentInstance()
                    .getApplication()
                    .createMethodBinding(
                            "#{searchResultSummaryBackingBean.productStructure}",
                            new Class[] {});
            htmlCommandLink.setAction(methodBinding);

            htmlOutputtext
                    .setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Product Structure");
            htmlCommandLink.getChildren().add(htmlOutputtext);
            htmlOutputtext.setEscape(false);
            panel.getChildren().add(htmlCommandLink);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.PRODUCT_STRUCTURES));
            int actualCount = getActualCount(SearchConstants.PRODUCT_STRUCTURES);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            panel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(
                SearchConstants.BENEFIT_COMPONENTS)) {

            HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
            HtmlOutputText htmlOutputtext = new HtmlOutputText();
            MethodBinding methodBinding = FacesContext
                    .getCurrentInstance()
                    .getApplication()
                    .createMethodBinding(
                            "#{searchResultSummaryBackingBean.benefitComponent}",
                            new Class[] {});
            htmlCommandLink.setAction(methodBinding);

            htmlOutputtext
                    .setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Benefit Component");
            htmlCommandLink.getChildren().add(htmlOutputtext);
            htmlOutputtext.setEscape(false);
            panel.getChildren().add(htmlCommandLink);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.BENEFIT_COMPONENTS));
            int actualCount = getActualCount(SearchConstants.BENEFIT_COMPONENTS);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);
            panel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.BENEFIT)) {
            HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
            HtmlOutputText htmlOutputtext = new HtmlOutputText();
            MethodBinding methodBinding = FacesContext.getCurrentInstance()
                    .getApplication().createMethodBinding(
                            "#{searchResultSummaryBackingBean.benefit}",
                            new Class[] {});
            htmlCommandLink.setAction(methodBinding);

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Benefit");
            htmlCommandLink.getChildren().add(htmlOutputtext);
            htmlOutputtext.setEscape(false);
            panel.getChildren().add(htmlCommandLink);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.BENEFIT));
            int actualCount = getActualCount(SearchConstants.BENEFIT);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            panel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.BENEFIT_LEVEL)) {

            HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
            HtmlOutputText htmlOutputtext = new HtmlOutputText();
            MethodBinding methodBinding = FacesContext.getCurrentInstance()
                    .getApplication().createMethodBinding(
                            "#{searchResultSummaryBackingBean.benefitLevel}",
                            new Class[] {});
            htmlCommandLink.setAction(methodBinding);

            htmlOutputtext
                    .setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Benefit Level");
            htmlCommandLink.getChildren().add(htmlOutputtext);
            htmlOutputtext.setEscape(false);
            panel.getChildren().add(htmlCommandLink);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.BENEFIT_LEVEL));
            int actualCount = getActualCount(SearchConstants.BENEFIT_LEVEL);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            panel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.NOTES)) {

            HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
            HtmlOutputText htmlOutputtext = new HtmlOutputText();
            MethodBinding methodBinding = FacesContext.getCurrentInstance()
                    .getApplication().createMethodBinding(
                            "#{searchResultSummaryBackingBean.notes}",
                            new Class[] {});
            htmlCommandLink.setAction(methodBinding);

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes");
            htmlCommandLink.getChildren().add(htmlOutputtext);
            htmlOutputtext.setEscape(false);
            panel.getChildren().add(htmlCommandLink);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String.valueOf(getCount(SearchConstants.NOTES));
            int actualCount = getActualCount(SearchConstants.NOTES);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            panel.getChildren().add(htmlOutputValue);
        }

        return panel;
    }
    /**
     *   method for setting BreadCrumb text
     *
     */
    public SearchResultSummaryBackingBean() {
        setBreadCrumbText("Search >> Search Summary");
    }
    /**
     * 
     * @param objectType
     * @return
     */
    public int getCount(String objectType) {
        if (objectType != null) {
            try {
                MultipageSearchResult msr = ((MultipageSearchResult) getSession()
                        .getAttribute(objectType));
                if (msr != null) {
                    return msr.getTotalNumberOfResults();
                }
            } catch (Exception e) {
                Logger.logError(e);
            }
        }
        return 0;
    }
    /**
     * 
     * @param objectType
     * @return
     */
    public int getActualCount(String objectType) {
        if (objectType != null) {
            try {
                MultipageSearchResult msr = ((MultipageSearchResult) getSession()
                        .getAttribute(objectType));
                if (msr != null) {
                    return msr.getQueryResultCount();
                }
            } catch (Exception e) {
                Logger.logError(e);
            }
        }
        return 0;
    }

    /**
     * @return Returns the totalCount.
     */
    public int getTotalCount() {
        totalCount = 0;

        totalCount += getCount(SearchConstants.BENEFIT_COMPONENTS);
        totalCount += getCount(SearchConstants.BENEFIT);
        totalCount += getCount(SearchConstants.BENEFIT_LEVEL);
        totalCount += getCount(SearchConstants.CONTRACT);
        totalCount += getCount(SearchConstants.NOTES);
        totalCount += getCount(SearchConstants.PRODUCT);
        totalCount += getCount(SearchConstants.PRODUCT_STRUCTURES);

        return totalCount;
    }

    /**
     * @param totalCount
     *            The totalCount to set.
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @param contract
     *            The contract to set.
     */
    public void setContract(HtmlSelectBooleanCheckbox contract) {
        this.contract = contract;
    }
    /**
     * 
     * @return
     */
    public String refineSearchCriteria() {
        return "BasicSearchCriteria";
    }
    /**
     * method to remove ASSOCIATION Constant form session
     *
     */
    private void removeViewAssociationFromSession() {
        getSession().removeAttribute(SearchConstants.VIEW_ASSOCIATION);
    }

    /**
     * @return Returns the printPanel.
     */
    public HtmlPanelGrid getPrintPanel() {

        printPanel = new HtmlPanelGrid();
   
        printPanel.setColumns(2);
        printPanel.setBorder(0);

        printPanel.setStyleClass("outputText");
        printPanel.setCellpadding("3");
        printPanel.setCellspacing("1");

        printPanel.setHeaderClass("Head");

        HtmlOutputText norLabel = new HtmlOutputText();
        norLabel.setValue("Number of Results");
        HtmlOutputText norValue = new HtmlOutputText();
        norValue.setValue("" + getTotalCount());
        printPanel.getChildren().add(norLabel);
        printPanel.getChildren().add(norValue);
        if (null != getSession().getAttribute(SearchConstants.CONTRACT)) {

            HtmlOutputText htmlOutputtext = new HtmlOutputText();

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contract");
            htmlOutputtext.setEscape(false);

            printPanel.getChildren().add(htmlOutputtext);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.CONTRACT));
            int actualCount = getActualCount(SearchConstants.CONTRACT);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            printPanel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.PRODUCT)) {

            HtmlOutputText htmlOutputtext = new HtmlOutputText();

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Product");
            htmlOutputtext.setEscape(false);
            printPanel.getChildren().add(htmlOutputtext);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.PRODUCT));
            int actualCount = getActualCount(SearchConstants.PRODUCT);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            printPanel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(
                SearchConstants.PRODUCT_STRUCTURES)) {

            HtmlOutputText htmlOutputtext = new HtmlOutputText();

            htmlOutputtext
                    .setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Product Structure");

            htmlOutputtext.setEscape(false);
            printPanel.getChildren().add(htmlOutputtext);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.PRODUCT_STRUCTURES));
            int actualCount = getActualCount(SearchConstants.PRODUCT_STRUCTURES);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            printPanel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(
                SearchConstants.BENEFIT_COMPONENTS)) {

            HtmlOutputText htmlOutputtext = new HtmlOutputText();

            htmlOutputtext
                    .setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Benefit Component");

            htmlOutputtext.setEscape(false);
            printPanel.getChildren().add(htmlOutputtext);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.BENEFIT_COMPONENTS));
            int actualCount = getActualCount(SearchConstants.BENEFIT_COMPONENTS);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);
            printPanel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.BENEFIT)) {

            HtmlOutputText htmlOutputtext = new HtmlOutputText();

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Benefit");

            htmlOutputtext.setEscape(false);
            printPanel.getChildren().add(htmlOutputtext);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.BENEFIT));
            int actualCount = getActualCount(SearchConstants.BENEFIT);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            printPanel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.BENEFIT_LEVEL)) {

            HtmlOutputText htmlOutputtext = new HtmlOutputText();

            htmlOutputtext
                    .setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Benefit Level");

            htmlOutputtext.setEscape(false);
            printPanel.getChildren().add(htmlOutputtext);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String
                    .valueOf(getCount(SearchConstants.BENEFIT_LEVEL));
            int actualCount = getActualCount(SearchConstants.BENEFIT_LEVEL);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            printPanel.getChildren().add(htmlOutputValue);
        }
        if (null != getSession().getAttribute(SearchConstants.NOTES)) {

            HtmlOutputText htmlOutputtext = new HtmlOutputText();

            htmlOutputtext.setValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes");

            htmlOutputtext.setEscape(false);
            printPanel.getChildren().add(htmlOutputtext);
            HtmlOutputText htmlOutputValue = new HtmlOutputText();

            String countValue = String.valueOf(getCount(SearchConstants.NOTES));
            int actualCount = getActualCount(SearchConstants.NOTES);
            if (actualCount > 300) {
                countValue += " of " + actualCount;
            }
            htmlOutputValue.setValue(countValue);

            printPanel.getChildren().add(htmlOutputValue);
        }

        return printPanel;
    }

    /**
     * @param printPanel
     *            The printPanel to set.
     */
    public void setPrintPanel(HtmlPanelGrid printPanel) {
        this.printPanel = printPanel;
    }
   /**
    * 
    * @return printSummary
    */
    public String getPrintSummary() {
        return "printSummary";
    }
}