/*
 * <DynamicExcelView.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wellpoint.ets.ebx.simulation.util.ExcelGenerator;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;

/**
 * @author UST-GLOBAL
 * 
 * DynamicExcelView class. This class will create the Error Report Excel,
 * X12 Response Excel and Benefit Accum Service Response Excel Report
 * 
 */
public class DynamicExcelView extends AbstractExcelView {

    String view=null;
    String formattedResponse=null;
    List contractList=null;
    String backEndRegion = "";

    public DynamicExcelView() {
    }
    
    public DynamicExcelView(ContractVO contarctObj){
        //contract = contarctObj;
    }
    /**
     * Check whether the request is for XML excel Generation or X12 ExcelGeneration or Error Report Generation
     * @param view
     * @param contract
     */
    public DynamicExcelView(String view, List contractList){
    	this.contractList = contractList;
        this.view=view;
    }
    
    /**
     * 
     * @param view
     * @param formattedResponse
     */
    public DynamicExcelView(List contarctList){
    	this.contractList = contarctList;
    }
    
    public DynamicExcelView(String view, String formattedResponse){
        this.view=view;
        this.formattedResponse=formattedResponse;
    }

    public String getBackEndRegion() {
		return backEndRegion;
	}

	public void setBackEndRegion(String backEndRegion) {
		this.backEndRegion = backEndRegion;
	}

	protected void buildExcelDocument(Map model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	if(view!=null){
    		if(view.equals("XML")){
                ExcelGenerator.generate27xBenefitAccumExcel(contractList, workbook);
            }else if(view.equals("X12")){
                //ExcelGenerator.generate27xHIPAABXExcel(formattedResponse, workbook);
            	HSSFSheet sheet = workbook.createSheet();
    	        setReportProperties(sheet);
            	ExcelGenerator.populate271Report(formattedResponse, workbook, sheet);
            }
    	} else{
            ExcelGenerator.generateErrorReport(contractList, workbook, this.backEndRegion);
        }
    }
	private static void setReportProperties(HSSFSheet sheet) {
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth((short) 11);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
    }


}
