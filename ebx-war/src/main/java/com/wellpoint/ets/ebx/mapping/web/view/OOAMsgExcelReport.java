/*
 * <OOAMsgExcelReport.java>
 *
 * © 2016 - 2017 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.web.view;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;
import com.wellpoint.ets.ebx.ooamessage.util.OOAMessageConstants;
/*
 * This class is used to generate the excel report for ooa message 
 */
/**
 * @author AF17776
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class OOAMsgExcelReport extends AbstractExcelView {
	
	List messageList = null;
	String exportType=null;
	public OOAMsgExcelReport(List messageList,String exportType){
		this.messageList=messageList;
		this.exportType=exportType;
		
	}
	
	/**
	 * This method is used to create the excel report header
	 * @param String exportType
	 * @return ArrayList  - java.util.ArrayList - 
	 */
		
	public  ArrayList createEbxWpdReportHeader(String exportType){

        ArrayList headerList=new ArrayList();
        if(OOAMessageConstants.contact.equals(exportType)){
        headerList.add("Contract Code");
        }else{
        	 headerList.add("Network Id");
        	 headerList.add("Exchange Indicator");
        }
        headerList.add("Effective Date");
        headerList.add("Exp Date");
        headerList.add("Status");
        if(OOAMessageConstants.contact.equals(exportType)){
        headerList.add("Message Exempt Flag");
        }
        headerList.add("MsgTextOne");
        headerList.add("MsgTextTwo");
        headerList.add("MsgTextThree");
        headerList.add(" ");
		return headerList;        
 	
	}
	
	/**
	 * This method is used to create the bold header of excel report 
	 * @param HSSFWorkbook wb
	 * @return HSSFRow row 
	 */
	public static void makeRowBold(HSSFWorkbook wb, HSSFRow row){
	    HSSFCellStyle style = wb.createCellStyle();//Create style
	    HSSFFont font = wb.createFont();//Create font
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//Make font bold
	    style.setFont(font);//set it to bold

	    for(short i = 0; i < row.getLastCellNum(); i++){//For each cell in the row 
	        row.getCell(i).setCellStyle(style);//Set the style
	    }
	}
	
	/**
	 * This method is used to store the data in the object class
	 * @param String exportType
	 * @return HashMap<Integer,Object[]>  - java.util.HashMap - 
	 */
	
	public HashMap<Integer,Object[]> convortTOObject(List<MaintainOOAMessageRequest> list,String exportType){
		
		ArrayList newList;
		Integer i=0;
		ArrayList headerList=createEbxWpdReportHeader(exportType);
		HashMap<Integer, Object[]> map=new HashMap<Integer, Object[]>();
		map.put(i, headerList.toArray());
		for(MaintainOOAMessageRequest ooaMessageRequest:list){
			
			newList=new ArrayList();		
			newList.add(ooaMessageRequest.getNetworkOrContractCode());
			if(!OOAMessageConstants.contact.equals(exportType)){
				  newList.add(ooaMessageRequest.getExchangeIndicator());
		        }
			newList.add(ooaMessageRequest.getMessageEffectiveDate());
			newList.add(ooaMessageRequest.getMessageExpiryDate());
			newList.add(ooaMessageRequest.getWorkFlowAssosciationStatus());
			if(OOAMessageConstants.contact.equals(exportType)){
				newList.add(ooaMessageRequest.getMessageExempted());
		        }
			newList.add(ooaMessageRequest.getMessageTextOne());
			newList.add(ooaMessageRequest.getMessageTextTwo());
			newList.add(ooaMessageRequest.getMessageTextThree());
			map.put(++i, newList.toArray());
			
		}
		return map;
		
	}
	
	/**
	 * This method is used to build the excel document
	 * @param request
	 * @param response
	 */
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		OutputStream outStream = null;
		
		if(messageList != null){
			 workbook = new HSSFWorkbook();
			 StringBuffer sb=new StringBuffer(exportType);
			 sb.append(" Sheet");
			HSSFSheet sheet = workbook.createSheet(sb.toString());
			HashMap<Integer, Object[]> hashMap=convortTOObject(messageList,exportType);
			Set<Integer> keyset = hashMap.keySet();
			int rownum = 0;
			for (Integer key : keyset) {
				HSSFRow row = sheet.createRow(rownum++);
				Object [] objArr = hashMap.get(key);
				short cellnum = 0;
				for (Object obj : objArr) {
					HSSFCell cell = row.createCell(cellnum++);
					sheet.setColumnWidth(cellnum, (short) 2800);
					if(obj instanceof Date) {
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						sdf.format(obj);
						//cell.setCellValue((Date)obj);
						cell.setCellValue(sdf.format(obj));
					}
					else if(obj instanceof Boolean)
						cell.setCellValue((Boolean)obj);
					else if(obj instanceof String)
						cell.setCellValue((String)obj);
					else if(obj instanceof Integer)
						cell.setCellValue((Integer)obj);
					if(rownum==1){
							makeRowBold(workbook,row); 
					 }
				}
			}
			
		    try {
		        outStream = response.getOutputStream();
		        workbook.write(outStream);
		        outStream.flush();
		    } 
		    catch (IOException e) {
				e.printStackTrace();
			}
		    
		    finally {
		        outStream.close();
		    }       

		}
		
	}
	

}
