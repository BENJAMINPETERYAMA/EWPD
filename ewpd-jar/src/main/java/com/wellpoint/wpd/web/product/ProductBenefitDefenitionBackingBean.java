/*
 * ProductBenefitDefenitionBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitDefenitionBackingBean extends WPDBackingBean{

	/**
	 * @return Returns the panel.
	 */
	public HtmlPanelGrid getPanel() {
		 for (int i = 0; i < 3; i++) {
            HtmlInputText inputText = new HtmlInputText();
            HtmlInputText inputText1 = new HtmlInputText();
            HtmlOutputText outputText = new HtmlOutputText();
            HtmlOutputText outputText1 = new HtmlOutputText();
            HtmlOutputText outputText2 = new HtmlOutputText();
            HtmlOutputLabel htmlOutputLabel = new HtmlOutputLabel();
            inputText.setStyleClass("formInputFieldForSequenceNo");
            inputText.setId("amount"+i);
            inputText1.setStyleClass("formInputFieldForSequenceNo");
            outputText.setValue("Individual Deductable");
            outputText1.setValue("PAR");
            outputText2.setValue("$");
            inputText1.setValue("XREF_CLM");
            htmlOutputLabel.setFor("amount"+i);
            htmlOutputLabel.getChildren().add(outputText2);
            htmlOutputLabel.getChildren().add(inputText); 
            panel.getChildren().add(outputText);
            panel.getChildren().add(outputText1);
            panel.getChildren().add(htmlOutputLabel);
            panel.getChildren().add(inputText1);
        }
        return panel;
	}
	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}
	 private HtmlPanelGrid panel = new HtmlPanelGrid();
}
