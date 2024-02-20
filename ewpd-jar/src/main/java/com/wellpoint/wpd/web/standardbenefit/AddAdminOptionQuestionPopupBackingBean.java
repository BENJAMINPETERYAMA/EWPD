/*
 * AddAdminOptionQuestionPopupBackingBean.java
 * 
 * Created on Feb 20, 2007
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.common.standardbenefit.bo.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

public class AddAdminOptionQuestionPopupBackingBean {

    private HtmlPanelGrid openQuestionHeaderPanel = new HtmlPanelGrid();

    private HtmlPanelGrid openQuestionPanel = new HtmlPanelGrid();

    private HtmlPanelGrid hiddenQuesHeaderPanel = new HtmlPanelGrid();

    private HtmlPanelGrid hiddenQuesPanel = new HtmlPanelGrid();

    private Map openQuesSelectMap = new HashMap();

    private Map hiddenQuesSelectMap = new HashMap();

    private Map openAnswerMap = new HashMap();

    private Map hiddenAnswerMap = new HashMap();

    List openQuestionList = new ArrayList();

    List hiddenQuestionList = new ArrayList();

    List selectedQuestionList;

    private Question question;


    public AddAdminOptionQuestionPopupBackingBean() {

    }


    /**
     * @return Returns the hiddenAnswerMap.
     */
    public Map getHiddenAnswerMap() {
        return hiddenAnswerMap;
    }


    /**
     * @param hiddenAnswerMap
     *            The hiddenAnswerMap to set.
     */
    public void setHiddenAnswerMap(Map hiddenAnswerMap) {
        this.hiddenAnswerMap = hiddenAnswerMap;
    }


    /**
     * @return Returns the hiddenQuesHeaderPanel.
     */
    public HtmlPanelGrid getHiddenQuesHeaderPanel() {

        HtmlPanelGrid hiddenQuesHeaderPanel = new HtmlPanelGrid();
        hiddenQuesHeaderPanel.setWidth("570");
        hiddenQuesHeaderPanel.setColumns(3);
        hiddenQuesHeaderPanel.setBorder(0);
        hiddenQuesHeaderPanel.setCellpadding("3");
        hiddenQuesHeaderPanel.setCellspacing("1");
        hiddenQuesHeaderPanel.setBgcolor("#cccccc");
        hiddenQuesHeaderPanel.setStyleClass("dataTableHeader");

        HtmlSelectBooleanCheckbox htmlSelectBooleanCheckbox = new HtmlSelectBooleanCheckbox();
        HtmlOutputText htmlOutputText2 = new HtmlOutputText();
        HtmlOutputText htmlOutputText3 = new HtmlOutputText();
        HtmlInputHidden htmlInputHidden = new HtmlInputHidden();

        htmlSelectBooleanCheckbox
                .setOnclick("return checkAll('addQuesPopupForm:hidQuesPanelTable', this);");
        htmlInputHidden.setValue("hidden");
        htmlOutputText2.setValue("Domained Questions ");
        htmlOutputText3.setValue("Answer");

        hiddenQuesHeaderPanel.getChildren().add(htmlSelectBooleanCheckbox);
        hiddenQuesHeaderPanel.getChildren().add(htmlOutputText2);
        hiddenQuesHeaderPanel.getChildren().add(htmlOutputText3);

        return hiddenQuesHeaderPanel;
    }


    /**
     * @param hiddenQuesHeaderPanel
     *            The hiddenQuesHeaderPanel to set.
     */
    public void setHiddenQuesHeaderPanel(HtmlPanelGrid hiddenQuesHeaderPanel) {
        this.hiddenQuesHeaderPanel = hiddenQuesHeaderPanel;
    }


    /**
     * @return Returns the hiddenQuesPanel.
     */
    public HtmlPanelGrid getHiddenQuesPanel() {

        HtmlPanelGrid hiddenQuesPanel = new HtmlPanelGrid();
        hiddenQuesPanel.setWidth("570");
        hiddenQuesPanel.setColumns(3);
        hiddenQuesPanel.setBorder(0);
        hiddenQuesPanel.setCellpadding("3");
        hiddenQuesPanel.setCellspacing("1");
        hiddenQuesPanel.setStyleClass("outputText");
        hiddenQuesPanel.setBgcolor("#cccccc");

        List list = this.getHiddenQuestionList();
        Iterator iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            HtmlSelectBooleanCheckbox htmlSelectBooleanCheckbox = new HtmlSelectBooleanCheckbox();
            HtmlOutputText htmlOutputText2 = new HtmlOutputText();
            HtmlOutputText htmlOutputText3 = new HtmlOutputText();

            HtmlSelectOneMenu seloneMenu = new HtmlSelectOneMenu();

            Question question = (Question) iterator.next();

            

            String ques = question.getQuestion();
            String ans = question.getAnswer();
            htmlOutputText2.setValue(ques);
            htmlOutputText3.setValue(ans);

            List list1 = new ArrayList(3);
            UISelectItems selectItems = new UISelectItems();
            list1.add(new SelectItem(" ", " "));
            list1.add(new SelectItem("YES", "YES"));
            list1.add(new SelectItem("NO", "NO"));

            selectItems.setValue(list1);
            seloneMenu.getChildren().add(selectItems);
            seloneMenu.setValue(ans);

            UIComponent object;

            object = (HtmlSelectOneMenu) seloneMenu;

            ValueBinding hidAns = FacesContext.getCurrentInstance()
                    .getApplication().createValueBinding(
                            "#{AddAdminOptionQuestionPopupBackingBean.hiddenAnswerMap["
                                    + i + "]}");

            ValueBinding hidChkBox = FacesContext.getCurrentInstance()
                    .getApplication().createValueBinding(
                            "#{AddAdminOptionQuestionPopupBackingBean.hiddenQuesSelectMap["
                                    + i + "]}");

            object.setValueBinding("value", hidAns);
            htmlSelectBooleanCheckbox.setValueBinding("value", hidChkBox);
            hiddenQuesPanel.getChildren().add(htmlSelectBooleanCheckbox);
            hiddenQuesPanel.getChildren().add(htmlOutputText2);
            hiddenQuesPanel.getChildren().add(object);

        }

        return hiddenQuesPanel;
    }


    /**
     * @param hiddenQuesPanel
     *            The hiddenQuesPanel to set.
     */
    public void setHiddenQuesPanel(HtmlPanelGrid hiddenQuesPanel) {
        this.hiddenQuesPanel = hiddenQuesPanel;
    }


    /**
     * @return Returns the hiddenQuesSelectMap.
     */
    public Map getHiddenQuesSelectMap() {
        return hiddenQuesSelectMap;
    }


    /**
     * @param hiddenQuesSelectMap
     *            The hiddenQuesSelectMap to set.
     */
    public void setHiddenQuesSelectMap(Map hiddenQuesSelectMap) {
        this.hiddenQuesSelectMap = hiddenQuesSelectMap;
    }


    /**
     * @return Returns the hiddenQuestionList.
     */
    public List getHiddenQuestionList() {

        List list = new ArrayList(3);
        for (int i = 5; i < 9; i++) {
            question = new Question();
            question.setSeqNumber(i);
            question.setQuestion("Question" + i);

            list.add(question);
        }
        return list;
    }


    /**
     * @param hiddenQuestionList
     *            The hiddenQuestionList to set.
     */
    public void setHiddenQuestionList(List hiddenQuestionList) {
        this.hiddenQuestionList = hiddenQuestionList;
    }


    /**
     * @return Returns the openAnswerMap.
     */
    public Map getOpenAnswerMap() {
        return openAnswerMap;
    }


    /**
     * @param openAnswerMap
     *            The openAnswerMap to set.
     */
    public void setOpenAnswerMap(Map openAnswerMap) {
        this.openAnswerMap = openAnswerMap;
    }


    /**
     * @return Returns the openQuesSelectMap.
     */
    public Map getOpenQuesSelectMap() {
        return openQuesSelectMap;
    }


    /**
     * @param openQuesSelectMap
     *            The openQuesSelectMap to set.
     */
    public void setOpenQuesSelectMap(Map openQuesSelectMap) {
        this.openQuesSelectMap = openQuesSelectMap;
    }


    /**
     * @return Returns the openQuestionHeaderPanel.
     */
    public HtmlPanelGrid getOpenQuestionHeaderPanel() {

        HtmlPanelGrid openQuestionHeaderPanel = new HtmlPanelGrid();

        openQuestionHeaderPanel.setWidth("570");
        openQuestionHeaderPanel.setColumns(3);
        openQuestionHeaderPanel.setBorder(0);
        openQuestionHeaderPanel.setCellpadding("3");
        openQuestionHeaderPanel.setCellspacing("1");
        openQuestionHeaderPanel.setBgcolor("#cccccc");
        openQuestionHeaderPanel.setStyleClass("dataTableHeader");

        HtmlSelectBooleanCheckbox htmlSelectBooleanCheckbox = new HtmlSelectBooleanCheckbox();
        HtmlOutputText htmlOutputText2 = new HtmlOutputText();
        HtmlOutputText htmlOutputText3 = new HtmlOutputText();

        htmlSelectBooleanCheckbox
                .setOnclick("return checkAll( 'addQuesPopupForm:quesPanelPopupTable', this );");
        htmlOutputText2.setValue("Open Questions ");
        htmlOutputText3.setValue("Answer");

        openQuestionHeaderPanel.getChildren().add(htmlSelectBooleanCheckbox);
        openQuestionHeaderPanel.getChildren().add(htmlOutputText2);
        openQuestionHeaderPanel.getChildren().add(htmlOutputText3);

        return openQuestionHeaderPanel;
    }


    /**
     * @param openQuestionHeaderPanel
     *            The openQuestionHeaderPanel to set.
     */
    public void setOpenQuestionHeaderPanel(HtmlPanelGrid openQuestionHeaderPanel) {
        this.openQuestionHeaderPanel = openQuestionHeaderPanel;
    }


    /**
     * @return Returns the openQuestionList.
     */
    public List getOpenQuestionList() {

        List list = new ArrayList(4);
        for (int i = 1; i < 5; i++) {
            question = new Question();
            question.setSeqNumber(i);
            question.setQuestion("Question" + i);

            list.add(question);
        }
        return list;
    }


    /**
     * @param openQuestionList
     *            The openQuestionList to set.
     */
    public void setOpenQuestionList(List openQuestionList) {
        this.openQuestionList = openQuestionList;
    }


    /**
     * @return Returns the openQuestionPanel.
     */
    public HtmlPanelGrid getOpenQuestionPanel() {

        HtmlPanelGrid openQuestionPanel = new HtmlPanelGrid();

        openQuestionPanel.setWidth("570");
        openQuestionPanel.setColumns(3);
        openQuestionPanel.setBorder(0);
        openQuestionPanel.setCellpadding("3");
        openQuestionPanel.setCellspacing("1");
        openQuestionPanel.setStyleClass("outputText");
        openQuestionPanel.setBgcolor("#cccccc");

        List list = this.getOpenQuestionList();
        Iterator iterator = list.iterator();
        int openQuestion = 0;
        while (iterator.hasNext()) {
            openQuestion++;
            HtmlSelectBooleanCheckbox htmlSelectBooleanCheckbox = new HtmlSelectBooleanCheckbox();
            HtmlOutputText htmlOutputText2 = new HtmlOutputText();
            HtmlOutputText htmlOutputText3 = new HtmlOutputText();

            HtmlSelectOneMenu seloneMenu = new HtmlSelectOneMenu();

            Question question = (Question) iterator.next();

           

            String ques = question.getQuestion();
            String ans = question.getAnswer();
            htmlOutputText2.setValue(ques);
            htmlOutputText3.setValue(ans);

            List list1 = new ArrayList(3);
            UISelectItems selectItems = new UISelectItems();
            list1.add(new SelectItem(" ", " "));
            list1.add(new SelectItem("YES", "YES"));
            list1.add(new SelectItem("NO", "NO"));

            selectItems.setValue(list1);
            seloneMenu.getChildren().add(selectItems);
            seloneMenu.setValue(ans);

            UIComponent object;

            object = (HtmlSelectOneMenu) seloneMenu;

            ValueBinding myItem = FacesContext.getCurrentInstance()
                    .getApplication().createValueBinding(
                            "#{AddAdminOptionQuestionPopupBackingBean.openAnswerMap["
                                    + openQuestion + "]}");

            ValueBinding myItem1 = FacesContext.getCurrentInstance()
                    .getApplication().createValueBinding(
                            "#{AddAdminOptionQuestionPopupBackingBean.openQuesSelectMap["
                                    + openQuestion + "]}");

            object.setValueBinding("value", myItem);
            htmlSelectBooleanCheckbox.setValueBinding("value", myItem1);
            openQuestionPanel.getChildren().add(htmlSelectBooleanCheckbox);
            openQuestionPanel.getChildren().add(htmlOutputText2);
            openQuestionPanel.getChildren().add(object);

        }

        return openQuestionPanel;
    }


    /**
     * @param openQuestionPanel
     *            The openQuestionPanel to set.
     */
    public void setOpenQuestionPanel(HtmlPanelGrid openQuestionPanel) {
        this.openQuestionPanel = openQuestionPanel;
    }


    /**
     * @return Returns the selectedQuestionList.
     */
    public List getSelectedQuestionList() {
        return selectedQuestionList;
    }


    /**
     * @param selectedQuestionList
     *            The selectedQuestionList to set.
     */
    public void setSelectedQuestionList(List selectedQuestionList) {
        this.selectedQuestionList = selectedQuestionList;
    }

}