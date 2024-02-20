/*
 * <TextReportView.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.web.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.ebx.simulation.vo.ContractDataObjectVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractReportVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;

/**
 * @author UST-GLOBAL This is a class for generating Contract BX, PDF Report
 */
public class TextReportView {
	
	
	/**
	 * Creating constructor
	 * @param contractList
	 */
	public TextReportView(List contractList){
	}
	
	/**
	 * method to display the WPD contract values in contract BX report - Text view
	 * @param contract
	 * @return
	 */
	public static ArrayList getWpdContractBean(List contractDataList) {
		
		ArrayList contractData = new ArrayList();
		ContractReportVO contractReport = new ContractReportVO();
		ArrayList contractReportList = new ArrayList();	
		
		for(int j=0; j<contractDataList.size(); j++) {
			ContractVO contract = (ContractVO)contractDataList.get(j);
			
			if(DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract.getSystem())) {
				contractReport.setContractId(contract.getContractId());
				contractReport.setEffectiveDate(contract.getEffectiveDate());
				contractReport.setRevisionDateHeader("Revision Date");
				contractReport.setRevisionDate(contract.getRevisionDate());	
				
				int eb03Count;
				String eb03;
				Map majorHeadings = contract.getMajorHeadings();  
				if(majorHeadings != null) { 
		    //	Iterator majorHeadingsIterator = majorHeadings.keySet().iterator();
				Iterator majorHeadingsIterator = majorHeadings.entrySet().iterator();
		        if(majorHeadingsIterator != null) {
		            while(majorHeadingsIterator.hasNext()) {
		        //        String majorHeadingDesc = (String)majorHeadingsIterator.next();
		        //        MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO)majorHeadings.get(majorHeadingDesc);
		            	MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) ((Map.Entry) majorHeadingsIterator.next()).getValue();
		                Map minorHeadings = majorHeadingFromMap.getMinorHeadings();
		                if (minorHeadings != null) {                    	
		                //    Iterator minorHeadingsIterator = minorHeadings.keySet().iterator();
		                	Iterator minorHeadingsIterator = minorHeadings.entrySet().iterator();
		                    if(minorHeadingsIterator != null) {
		                        while(minorHeadingsIterator.hasNext()) {
		                        //    String minorHeadingDesc = (String)minorHeadingsIterator.next();
		                        //    MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO)minorHeadings.get(minorHeadingDesc);
		                        	MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) ((Map.Entry) minorHeadingsIterator.next()).getValue();
		                            Map mappings = minorHeadingFromMap.getMappings();    
		                            if(mappings != null) {
		                           //     Iterator mappingsIterator = mappings.keySet().iterator();
		                            	Iterator mappingsIterator = mappings.entrySet().iterator();
		                                if(mappingsIterator != null) {
		                                    while(mappingsIterator.hasNext()) {
		                                   //     String mappingKey = (String)mappingsIterator.next();
		                                   //     Mapping mappingFromMap = (Mapping)mappings.get(mappingKey);
		                                    	Mapping mappingFromMap = (Mapping) ((Map.Entry) mappingsIterator.next()).getValue();
		                                        if(mappingFromMap != null) { 
		                                        	ContractDataObjectVO contractDataObject = null;
		                                        	if(mappingFromMap.isMapped()){
		                                        		if(null != mappingFromMap.getEb03()) {
	                                            			List<String> associatedList = new ArrayList<String>();
	                                                    	/*String unAssnEb03CSV = null;
	                                    					List<String> unAssociatedList = new ArrayList<String>();
	                                    					List<EB03Association> associatedEB03AssnList = new ArrayList<EB03Association>();*/
	                                            			List<EB03Association> associatedEB03AssnList = getAssociatedEB03Codes(mappingFromMap,
	                                            					associatedList,mappingFromMap.getIndvdlEb03AssnIndicator());
	                                                    	/*if(null != unAssociatedList && unAssociatedList.size() != 0) {
	                                                    		unAssnEb03CSV = StringUtils.join(unAssociatedList.toArray(), ",");
	                                                    	}*/
	                                                    	if(null != associatedEB03AssnList && associatedEB03AssnList.size() != 0) {
	                                                    		for(EB03Association eb03AssnValue : associatedEB03AssnList)
	                                                        	{
	                                                    			if(null != contractDataObject)
	                                                    			{
	                                                    				contractReportList.add(contractDataObject);
	                                                    			}
	                                                    			contractDataObject = new ContractDataObjectVO();
					                                        		createWpdISGMappedRows(contract,
																				majorHeadingFromMap,
																				minorHeadingFromMap,
																				mappingFromMap,
																				contractDataObject,
																				eb03AssnValue);
	                                                        	}
	                                                    	}else {
	                                                    		contractDataObject = new ContractDataObjectVO();
	                                                    		createWpdISGMappedRows(
	        															contract,
	        															majorHeadingFromMap,
	        															minorHeadingFromMap,
	        															mappingFromMap,
	        															contractDataObject,
	        															null);
	                                                    	}
		                                        		}else {
	                                                		contractDataObject = new ContractDataObjectVO();
	                                                		createWpdISGMappedRows(
	    															contract,
	    															majorHeadingFromMap,
	    															minorHeadingFromMap,
	    															mappingFromMap,
	    															contractDataObject,
	    															null);
	                                            		}
		                                        	} else {
		                                        					contractDataObject = new ContractDataObjectVO();
		                                                        	 if(null != majorHeadingFromMap.getDescriptionText()){ 
				     		                            					contractDataObject.setMajorHeading(majorHeadingFromMap.getDescriptionText());
		                                                        	 }else{ 
				     		                            					contractDataObject.setMajorHeading(DomainConstants.EMPTY);
		                                                        	 }
				     		                            				if(null != minorHeadingFromMap.getDescriptionText()){ 
				     		                            					contractDataObject.setMinorHeading(minorHeadingFromMap.getDescriptionText());
				     		                            				}else{ 
				     		                            					contractDataObject.setMinorHeading(DomainConstants.EMPTY);
				     		                            				}
				     		                            					
				     		                                        	Variable var = mappingFromMap.getVariable();                                           	
				     		                                              	if(var != null) {
				     		                                              		if(null != var.getDescription()){
				     		                                              			contractDataObject.setVariableDescription(var.getDescription());
				     		                                              		}else{
				     		                                              			contractDataObject.setVariableDescription(DomainConstants.EMPTY);
				     		                                              		}
				     		                                              		if(null != var.getVariableId()){
				     		                                              			contractDataObject.setVariableId(var.getVariableId());
				     		                                              		}else{
				     		                                              			contractDataObject.setVariableId(DomainConstants.EMPTY);
				     		                                              		}
				     		                                              		if(null != var.getPva()){
				     		                                              			contractDataObject.setPva(var.getPva());
				     		                                              		}else{
				     		                                              			contractDataObject.setPva(DomainConstants.EMPTY);
				     		                                              		}
				     		                                              		if(null != var.getVariableFormat()){
				     		                                              			contractDataObject.setVariableFormat(var.getVariableFormat());
				     		                                              		}else{
				     		                                              			contractDataObject.setVariableFormat(DomainConstants.EMPTY);
				     		                                              		}
				     		                                              		if(null != var.getVariableValue()){
				     		                                              			contractDataObject.setVariableValue(var.getVariableValue());
				     		                                              		}else{
				     		                                              			contractDataObject.setVariableValue(DomainConstants.EMPTY);
				     		                                              		}
				     		                                              	
				     		                                                   }
		                                                         		}
		                                        	contractReportList.add(contractDataObject);
                                                    contractReport.setContractReportList(contractReportList);
		                                        		}
		                                    		}
		                                		}
		                            		}
		                            	
		                        		}
		                        
		                    		}
		                		}
		            		}
		            contractData.add(contractReport);
		        		}
		        	}
				
			} else if(DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem())) {
			
			contractReport.setContractId(contract.getContractId());
			contractReport.setEffectiveDate(contract.getEffectiveDate());
			contractReport.setRevisionDate("");
			int eb03Count;
			String eb03;
			Map majorHeadings = contract.getMajorHeadings();  
			if(majorHeadings != null) { 
	   // 	Iterator majorHeadingsIterator = majorHeadings.keySet().iterator();
			Iterator majorHeadingsIterator = majorHeadings.entrySet().iterator();
	        if(majorHeadingsIterator != null) {
	            while(majorHeadingsIterator.hasNext()) {
	            //    String majorHeadingDesc = (String)majorHeadingsIterator.next();
	            //    MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO)majorHeadings.get(majorHeadingDesc);
	            	MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) ((Map.Entry) majorHeadingsIterator.next()).getValue();
	                Map minorHeadings = majorHeadingFromMap.getMinorHeadings();
	                if (minorHeadings != null) {                    	
	                  //  Iterator minorHeadingsIterator = minorHeadings.keySet().iterator();
	                	Iterator minorHeadingsIterator = minorHeadings.entrySet().iterator();
	                    if(minorHeadingsIterator != null) {
	                        while(minorHeadingsIterator.hasNext()) {
	                       //     String minorHeadingDesc = (String)minorHeadingsIterator.next();
	                       //     MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO)minorHeadings.get(minorHeadingDesc);
	                        	MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) ((Map.Entry) minorHeadingsIterator.next()).getValue();
	                            Map mappings = minorHeadingFromMap.getMappings();    
	                            if(mappings != null) {
	                               // Iterator mappingsIterator = mappings.keySet().iterator();
	                            	 Iterator mappingsIterator = mappings.entrySet().iterator();
	                                if(mappingsIterator != null) {
	                                    while(mappingsIterator.hasNext()) {
	                                        //String mappingKey = (String)mappingsIterator.next();
	                                        //Mapping mappingFromMap = (Mapping)mappings.get(mappingKey);
	                                    	Mapping mappingFromMap = (Mapping) ((Map.Entry) mappingsIterator.next()).getValue();
	                                        if(mappingFromMap != null) { 
	                                        	ContractDataObjectVO contractDataObject = new ContractDataObjectVO();
	                                        	if(mappingFromMap.isMapped()) {
                                        			List<String> associatedList = new ArrayList<String>();
                                                	/*String unAssnEb03CSV = null;
                                					List<String> unAssociatedList = new ArrayList<String>();
                                					List<EB03Association> associatedEB03AssnList = new ArrayList<EB03Association>();*/
                                        			List<EB03Association> associatedEB03AssnList = getAssociatedEB03Codes(mappingFromMap,
                                        					associatedList,mappingFromMap.getIndvdlEb03AssnIndicator());
                                                	/*if(null != unAssociatedList && unAssociatedList.size() != 0) {
                                                		unAssnEb03CSV = StringUtils.join(unAssociatedList.toArray(), ",");
                                                	}*/
                                                	if(null != associatedEB03AssnList && associatedEB03AssnList.size() != 0) {
                                                		for(EB03Association eb03AssnValue : associatedEB03AssnList)
                                                    	{
                                                			if(null != contractDataObject)
                                                			{
                                                				contractReportList.add(contractDataObject);
                                                			}
                                                			contractDataObject = new ContractDataObjectVO();
                                                			createWpdLGMappedRows(contract,
                													majorHeadingFromMap,
                													minorHeadingFromMap,
                													mappingFromMap,
                													contractDataObject,
                													eb03AssnValue
                													);
                                                    	}
                                                	}else {
                                                		contractDataObject = new ContractDataObjectVO();
                                                		createWpdLGMappedRows(contract,
            													majorHeadingFromMap,
            													minorHeadingFromMap,
            													mappingFromMap,
            													contractDataObject,
            													null
            													);
                                                	}
                                        		} else {
	                                                        	if(null != majorHeadingFromMap.getDescriptionText()){
	            	                            					contractDataObject.setMajorHeading(majorHeadingFromMap.getDescriptionText());
	                                                        	}else{
	            	                            					contractDataObject.setMajorHeading(DomainConstants.EMPTY);
	                                                        	}
	            	                            				if(null != minorHeadingFromMap.getDescriptionText()){
	            	                            					contractDataObject.setMinorHeading(minorHeadingFromMap.getDescriptionText());
	            	                            				}else{
	            	                            					contractDataObject.setMinorHeading(DomainConstants.EMPTY);
	            	                            				}
	            	                                        	Variable var = mappingFromMap.getVariable();                                           	
	            	                                              	if(var != null) {
	            	                                              		if(null != var.getDescription()){
	            	                                              			contractDataObject.setVariableDescription(var.getDescription());
	            	                                              		}else{
	            	                                              			contractDataObject.setVariableDescription(DomainConstants.EMPTY);
	            	                                              		}
	            	                                              		if(null != var.getVariableId()){
	            	                                              			contractDataObject.setVariableId(var.getVariableId());
	            	                                              		}else{
	            	                                              			contractDataObject.setVariableId(DomainConstants.EMPTY);
	            	                                              		}
	            	                                              		if(null != var.getPva()){
	            	                                              			contractDataObject.setPva(var.getPva());
	            	                                              		}else{
	            	                                              			contractDataObject.setPva(DomainConstants.EMPTY);
	            	                                              		}
	            	                                              		if(null != var.getVariableFormat()){
	            	                                              			contractDataObject.setVariableFormat(var.getVariableFormat());
	            	                                              		}else{
	            	                                              			contractDataObject.setVariableFormat(DomainConstants.EMPTY);
	            	                                              		}
	            	                                              		if(null != var.getVariableValue()){
	            	                                              			contractDataObject.setVariableValue(var.getVariableValue());
	            	                                              		}else{
	            	                                              			contractDataObject.setVariableValue(DomainConstants.EMPTY);
	            	                                              		}
	            	                                              		
	            	                                                   }
	                                                        }
	                                        	contractReportList.add(contractDataObject);
                                                contractReport.setContractReportList(contractReportList);
	                                        		}
	                                    		}
	                                		}
	                            		}
	                            	
	                        		}
	                        
	                    		}
	                		}
	            		}
	            contractData.add(contractReport);
	        		}
	        	}
		
		}
		}  
		return contractData;
		
	}
	
	private static List<EB03Association> getAssociatedEB03Codes(Mapping mappingFromMap,List<String> associatedList,
			String indvdlEb03AssnInd) {
		List<EB03Association> associatedEb03AssnList = new ArrayList<EB03Association>();
		List<String> assnList = new ArrayList<String>();
		if(null!=mappingFromMap.getEb03())
		{
			if((null!=mappingFromMap.getEb03().getEb03Association()) && 
					(mappingFromMap.getEb03().getEb03Association().size() != 0))
					{
						associatedEb03AssnList = mappingFromMap.getEb03().getEb03Association();
						if(null != associatedEb03AssnList && associatedEb03AssnList.size() != 0)
						{
							for (EB03Association eb03Assn : associatedEb03AssnList) {
								if(null != eb03Assn.getEb03() && (null != eb03Assn.getEb03().getValue() || "".equals(eb03Assn.getEb03().getValue())))
								{
									 if (!assnList.contains(eb03Assn.getEb03().getValue())) {
									    	assnList.add(eb03Assn.getEb03().getValue());
									    }
								}
							}
						}
						/*List<EB03Association> eb03AssnList = mappingFromMap.getEb03().getEb03Association();
						for(EB03Association assn : eb03AssnList) {
							if((null != assn.getIii02()) && (null != assn.getIii02().getValue()) 
									&& (!"".equals(assn.getIii02().getValue()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null != assn.getNoteType()) && (null != assn.getNoteType().getValue()) 
									&& (!"".equals(assn.getNoteType().getValue()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null != assn.getMessage()) 
									&& (!"-".equals(assn.getMessage()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null != assn.getMsgReqdInd()) 
									&& (!"-".equals(assn.getMsgReqdInd()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null == assn.getIii02()) && (null == assn.getNoteType()) &&
									("-".equals(assn.getMessage())) && ("-".equals(assn.getMsgReqdInd()))){
								unAssociatedList.add(assn.getEb03String());
							}*/
					}
		}
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		if("Y".equals(indvdlEb03AssnInd))
		{
			if(null != assnList && assnList.size() != 0)
			{
				for(String assnStr : assnList)
				{
					EB03Association eb03AssnObj = new EB03Association();
					for(EB03Association eb03Assoc : associatedEb03AssnList)
					{
						if(assnStr.equals(eb03Assoc.getEb03().getValue()))
						{
							eb03AssnObj.setEb03(eb03Assoc.getEb03());
							if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
								eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
							}
							if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
									&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
								eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
							}
							if((null != eb03Assoc.getMessage()) 
									&& (!"-".equals(eb03Assoc.getMessage()))) {
								eb03AssnObj.setMessage(eb03Assoc.getMessage());
							}
							if((null != eb03Assoc.getMsgReqdInd()) 
									&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
								eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
							}
						}
					}
					eb03AssnList.add(eb03AssnObj);
				}
			}
			//unAssociatedList.removeAll(assnList);
			/*List<String> tempUnassnList = new ArrayList<String>();
			if(null != associatedList && null != unAssociatedList)
			{
				for(String unAssociatedCode : unAssociatedList)
				{
					if(!associatedList.contains(unAssociatedCode))
					{
						tempUnassnList.add(unAssociatedCode);
					}
				}
			}*/
		}else {
			if(null != assnList && assnList.size() != 0)
			{
				EB03Association eb03AssnObj = new EB03Association();
				String eb03Str = StringUtils.join(assnList.toArray(), ",");
				HippaCodeValue eb03Code = new HippaCodeValue();
				eb03Code.setValue(eb03Str);
				eb03AssnObj.setEb03(eb03Code);
				String assnStr = assnList.get(0);
				for(EB03Association eb03Assoc : associatedEb03AssnList)
				{
					if(assnStr.equals(eb03Assoc.getEb03().getValue()))
					{
						if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
							eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
						}
						if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
								&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
							eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
						}
						if((null != eb03Assoc.getMessage()) 
								&& (!"-".equals(eb03Assoc.getMessage()))) {
							eb03AssnObj.setMessage(eb03Assoc.getMessage());
						}
						if((null != eb03Assoc.getMsgReqdInd()) 
								&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
							eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
						}
					}
				}
				eb03AssnList.add(eb03AssnObj);
			}
		}
		
		return eb03AssnList;
	}

	/**
	 * @param contract
	 * @param majorHeadingFromMap
	 * @param minorHeadingFromMap
	 * @param mappingFromMap
	 * @param contractDataObject
	 */
	private static void createWpdLGMappedRows(ContractVO contract,
			MajorHeadingsVO majorHeadingFromMap,
			MinorHeadingsVO minorHeadingFromMap, Mapping mappingFromMap,
			ContractDataObjectVO contractDataObject,EB03Association eb03AssnValue) {
		int eb03Count;
		String eb03;
		HippaSegment accum = mappingFromMap.getAccum();
		if(accum != null) {
		List accumList = accum.getHippaCodeSelectedValues();
		if(null != accumList && !accumList.isEmpty()){
			Iterator accumListIterator = accumList.iterator();
			if(accumListIterator != null){
				List accumValue = new ArrayList();
				while(accumListIterator.hasNext()){
					HippaCodeValue accumCode = (HippaCodeValue)accumListIterator.next();
					accumValue.add(accumCode.getValue());
					}
				if(null != accumValue && !accumValue.isEmpty()) {
					for(int i=0; i<accumValue.size();i++) {
						switch (i) {
						case 0: contractDataObject.setAccum1(accumValue.get(i).toString());
								break;
						case 1: contractDataObject.setAccum2(accumValue.get(i).toString());
								break;
						case 2: contractDataObject.setAccum3(accumValue.get(i).toString());
								break;
						case 3: contractDataObject.setAccum4(accumValue.get(i).toString());
								break;
						case 4: contractDataObject.setAccum5(accumValue.get(i).toString());
								break;
						case 5: contractDataObject.setAccum6(accumValue.get(i).toString());
								break;
						case 6: contractDataObject.setAccum7(accumValue.get(i).toString());
								break;
						case 7: contractDataObject.setAccum8(accumValue.get(i).toString());
								break;
						case 8: contractDataObject.setAccum9(accumValue.get(i).toString());
								break;
						case 9: contractDataObject.setAccum10(accumValue.get(i).toString());
								break;
						default:
							break;
						
						}
						}
					}
				}
			}
		}
		if(null != majorHeadingFromMap.getDescriptionText()){
			contractDataObject.setMajorHeading(majorHeadingFromMap.getDescriptionText());
		}else{
			contractDataObject.setMajorHeading(DomainConstants.EMPTY);
		}
		if(null != minorHeadingFromMap.getDescriptionText()){
			contractDataObject.setMinorHeading(minorHeadingFromMap.getDescriptionText());
		}else{
			contractDataObject.setMinorHeading(DomainConstants.EMPTY);
		}
		Variable var = mappingFromMap.getVariable();                                           	
		  	if(var != null) {
		  		if(null != var.getDescription()){
		  			contractDataObject.setVariableDescription(var.getDescription());
		  		}else{
		  			contractDataObject.setVariableDescription(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getVariableId()){
		  			contractDataObject.setVariableId(var.getVariableId());
		  		}else{
		  			contractDataObject.setVariableId(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getPva()){
		  			contractDataObject.setPva(var.getPva());
		  		}else{
		  			contractDataObject.setPva(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getVariableFormat()){
		  			contractDataObject.setVariableFormat(var.getVariableFormat());
		  		}else{
		  			contractDataObject.setVariableFormat(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getVariableValue()){
		  			contractDataObject.setVariableValue(var.getVariableValue());
		  		}else{
		  			contractDataObject.setVariableValue(DomainConstants.EMPTY);
		  		}
		  		if(null != mappingFromMap.getEB01Value()){
		  			contractDataObject.setEB01(mappingFromMap.getEB01Value());
		  		}else{
		  			contractDataObject.setEB01(DomainConstants.EMPTY);
		  		}
		  		if(null != mappingFromMap.getEB02Value()){
		  			contractDataObject.setEB02(mappingFromMap.getEB02Value());
		  		}else{
		  			contractDataObject.setEB02(DomainConstants.EMPTY);
		  		}
				if(var.isNotApplicable()) {
					contractDataObject.setNotApplicable(DomainConstants.Y);
				}  else {
					contractDataObject.setNotApplicable(DomainConstants.N);
				}
				if(null != var.getSensitiveBenefitIndicator()){
		        	contractDataObject.setSensitiveInd(var.getSensitiveBenefitIndicator());
				}else{
		        	contractDataObject.setSensitiveInd(DomainConstants.EMPTY);
				}
		       }
		  	/*List eb03List = mappingFromMap.getEb03Values();
	        eb03 = "";
	        eb03Count = 0;
	        if(eb03List != null) {
	            Iterator eb03ListIterator = eb03List.iterator();
	            if(eb03ListIterator != null) {
	                while(eb03ListIterator.hasNext()) {
	                    eb03Count++;
	                    if (eb03Count != 1) {
	                        eb03 = eb03+","+(String)eb03ListIterator.next();
	                    } else {
	                        eb03 = 	(String)eb03ListIterator.next();
	                    }
	                }
	            }
	        }
	        if(null != eb03){
	        	contractDataObject.setEb03(eb03);
	        } else {
	        	contractDataObject.setEb03(DomainConstants.EMPTY);
	        }*/
	        
	        if(null != eb03AssnValue)
			{
	        	contractDataObject.setEb03(eb03AssnValue.getEb03().getValue());
	        	if(null != eb03AssnValue.getIii02List() && eb03AssnValue.getIii02List().size() > 0)
    			{
    				List<String> iii02ValueList = new ArrayList<String>();
    				for(HippaCodeValue value : eb03AssnValue.getIii02List())
    				{
    					iii02ValueList.add(value.getValue()); 
    				}
    				String csvIii02 = StringUtils.join(iii02ValueList.toArray(), ",");
    				contractDataObject.setIii02(csvIii02);
    			}else {
					contractDataObject.setIii02(DomainConstants.EMPTY);
				}
				if(null != eb03AssnValue.getMessage() && !"-".equals(eb03AssnValue.getMessage()))
				{
					contractDataObject.setMessage(eb03AssnValue.getMessage());
				}else {
					contractDataObject.setMessage(DomainConstants.EMPTY);
				}
	            if(null != eb03AssnValue.getMsgReqdInd() && !"-".equals(eb03AssnValue.getMsgReqdInd()))
				{
	            	contractDataObject.setMsgReqType(eb03AssnValue.getMsgReqdInd());
				}
	            else {
					contractDataObject.setMsgReqType(DomainConstants.EMPTY);
				}
	            if(null != eb03AssnValue.getNoteType())
				{
	            	contractDataObject.setMedssaeType(eb03AssnValue.getNoteType().getValue());//MESSAGE type
				}
	            else {
					contractDataObject.setMedssaeType(DomainConstants.EMPTY);
				}
			}else {
				if(null != mappingFromMap.getEb03Values() && mappingFromMap.getEb03Values().size() != 0) {
        			contractDataObject.setEb03(StringUtils.join(mappingFromMap.getEb03Values().toArray(), ","));
        		}else {
        			contractDataObject.setEb03(DomainConstants.EMPTY);
        		}
	        	contractDataObject.setIii02(DomainConstants.EMPTY);
	        	contractDataObject.setMessage(DomainConstants.EMPTY);
	        	contractDataObject.setMsgReqType(DomainConstants.EMPTY);
	        	contractDataObject.setMedssaeType(DomainConstants.EMPTY);
	        }
		            List umRuleList = mappingFromMap.getUmRuleValues();
		            String umRule = "";
		            int umRuleCount = 0;
		            if(umRuleList != null) {
		            	//Collections.sort(umRuleList);
		                Iterator umRuleListIterator = umRuleList.iterator();
		                if(umRuleListIterator != null) {
		                    while(umRuleListIterator.hasNext()) {
		                    	umRuleCount++;
		                        if (umRuleCount != 1) {
		                        	umRule = umRule+", "+(String)umRuleListIterator.next();
		                        } else {
		                        	umRule = 	(String)umRuleListIterator.next();
		                        }
		                    }
		                }
		            }
		            if(null != umRule){
		            	contractDataObject.setUmRule(umRule);
		            }else{
		            	contractDataObject.setUmRule(DomainConstants.EMPTY);
		            }
		            if(null !=  mappingFromMap.getEB06Value()){
		            	contractDataObject.setEB06(mappingFromMap.getEB06Value());
		            }else{
		            	contractDataObject.setEB06(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getEB09Value()){
		            	contractDataObject.setEB09(mappingFromMap.getEB09Value());
		            }else{
		            	contractDataObject.setEB09(DomainConstants.EMPTY);
		            }
		            // BXNI - June - Start and End Age
		          
		            
		            String startAge = "";
		            List<String> startAgeList = null;
		            if(!StringUtils.isBlank(mappingFromMap.getStartAgeValue())){
		            	startAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getStartAgeValue());
		            }
		            int startAgeCount = 0;
		            if(startAgeList != null) {
		            	Collections.sort(startAgeList);
		                Iterator startAgeListIterator = startAgeList.iterator();
		                if(startAgeListIterator != null) {
		                    while(startAgeListIterator.hasNext()) {
		                    	startAgeCount++;
		                    	String startAgeTemp = (String)startAgeListIterator.next();
		                    	if (!BxUtil.isInteger(startAgeTemp)) {
		                    		String startAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract, startAgeTemp);
		                    		if (null != startAgeCodedValue && !DomainConstants.EMPTY.equals(startAgeCodedValue)) {
		                    			startAgeTemp = startAgeTemp +  " (" + startAgeCodedValue + ")";
		                    		} 
		                    	}
		                        if (startAgeCount != 1) {
		                        	startAge = startAge+", "+startAgeTemp;
		                        } else {
		                        	startAge = 	startAgeTemp;
		                        }
		                    }
		                }
		            }
		            if(null != startAge && !DomainConstants.EMPTY.equals(startAge)) {
		            	contractDataObject.setStartAge(startAge);
		            } else {
		            	contractDataObject.setStartAge(DomainConstants.EMPTY);
		            }
		            
		            
		            //BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
		            String endAge = "";
		            List<String> endAgeList = null;
		            if(!StringUtils.isBlank(mappingFromMap.getEndAgeValue())){
		            	endAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getEndAgeValue());
		            }
		            int endAgeCount = 0;
		            if(endAgeList != null) {
		            	Collections.sort(endAgeList);
		                Iterator endAgeListIterator = endAgeList.iterator();
		                if(endAgeListIterator != null) {
		                    while(endAgeListIterator.hasNext()) {
		                    	endAgeCount++;
		                    	String endAgeTemp = (String)endAgeListIterator.next();
		                    	if (!BxUtil.isInteger(endAgeTemp)) {
		                    		String endAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract, endAgeTemp);
		                    		if (null != endAgeCodedValue && !DomainConstants.EMPTY.equals(endAgeCodedValue)) {
		                    			endAgeTemp = endAgeTemp +  "(" + endAgeCodedValue + ")";
		                    		} 
		                    	}
		                        if (endAgeCount != 1) {
		                        	endAge = endAge+", "+endAgeTemp;
		                        } else {
		                        	endAge = 	endAgeTemp;
		                        }
		                    }
		                }
		            }
		            if(null != endAge && !DomainConstants.EMPTY.equals(endAge)) {
		            	contractDataObject.setEndAge(endAge);
		            } else {
		            	contractDataObject.setEndAge(DomainConstants.EMPTY);
		            }
		            
		            
		            /*if(null != mappingFromMap.getIi02Value()){
		            	contractDataObject.setIii02(mappingFromMap.getIi02Value());
		            }else{
		            	contractDataObject.setIii02(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getMessage()){
		            	contractDataObject.setMessage(mappingFromMap.getMessage());
		            }else{
		            	contractDataObject.setMessage(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getMsg_type_required()){
		            	contractDataObject.setMsgReqType(mappingFromMap.getMsg_type_required());
		            }else{
		            	contractDataObject.setMsgReqType(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getNoteTypeCodeValue()){
		            	contractDataObject.setMedssaeType(mappingFromMap.getNoteTypeCodeValue());
		            }else{
		            	contractDataObject.setMedssaeType(DomainConstants.EMPTY);
		            }*/
		            
		            if(null != mappingFromMap.getHsd01Value()){
		            	contractDataObject.setHsd1(mappingFromMap.getHsd01Value());
		            }else{
		            	contractDataObject.setHsd1(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd02Value() && mappingFromMap.getHsd02Value().size() != 0){
		            	contractDataObject.setHsd2(StringUtils.join(mappingFromMap.getHsd02Value().toArray(), ","));
		            }else{
		            	contractDataObject.setHsd2(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd03Value()){
		            	contractDataObject.setHsd3(mappingFromMap.getHsd03Value());
		            }else{
		            	contractDataObject.setHsd3(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd04Value()){
		            	contractDataObject.setHsd4(mappingFromMap.getHsd04Value());
		            }else{
		            	contractDataObject.setHsd4(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd05Value()){
		            	contractDataObject.setHsd5(mappingFromMap.getHsd05Value());
		            }else{
		            	contractDataObject.setHsd5(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd06Value()){
		            	contractDataObject.setHsd6(mappingFromMap.getHsd06Value());
		            }else{
		            	contractDataObject.setHsd6(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd07Value()){
		            	contractDataObject.setHsd7(mappingFromMap.getHsd07Value());
		            }else{
		            	contractDataObject.setHsd7(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd08Value()){
		            	contractDataObject.setHsd8(mappingFromMap.getHsd08Value());
		            }else{
		            	contractDataObject.setHsd8(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getMappingComplete()){
		            	contractDataObject.setFinalized(mappingFromMap.getMappingComplete());
		            }else{
		            	contractDataObject.setFinalized(DomainConstants.EMPTY);
		            }
	}

	/**
	 * @param contract
	 * @param majorHeadingFromMap
	 * @param minorHeadingFromMap
	 * @param mappingFromMap
	 * @param contractDataObject
	 */
	private static void createWpdISGMappedRows(ContractVO contract,
			MajorHeadingsVO majorHeadingFromMap,
			MinorHeadingsVO minorHeadingFromMap, Mapping mappingFromMap,
			ContractDataObjectVO contractDataObject,EB03Association eb03AssnValue) {
		int eb03Count;
		String eb03;
		HippaSegment accum = mappingFromMap.getAccum();
		if(accum != null) {
		List accumList = accum.getHippaCodeSelectedValues();
		if(null != accumList && !accumList.isEmpty()){
			Iterator accumListIterator = accumList.iterator();
			if(accumListIterator != null){
				List accumValue = new ArrayList();
				while(accumListIterator.hasNext()){
					HippaCodeValue accumCode = (HippaCodeValue)accumListIterator.next();
					accumValue.add(accumCode.getValue());
					}
				if(null != accumValue && !accumValue.isEmpty()) {
					for(int i=0; i<accumValue.size();i++) {
						switch (i) {
						case 0: contractDataObject.setAccum1(accumValue.get(i).toString());
								break;
						case 1: contractDataObject.setAccum2(accumValue.get(i).toString());
								break;
						case 2: contractDataObject.setAccum3(accumValue.get(i).toString());
								break;
						case 3: contractDataObject.setAccum4(accumValue.get(i).toString());
								break;
						case 4: contractDataObject.setAccum5(accumValue.get(i).toString());
								break;
						case 5: contractDataObject.setAccum6(accumValue.get(i).toString());
								break;
						case 6: contractDataObject.setAccum7(accumValue.get(i).toString());
								break;
						case 7: contractDataObject.setAccum8(accumValue.get(i).toString());
								break;
						case 8: contractDataObject.setAccum9(accumValue.get(i).toString());
								break;
						case 9: contractDataObject.setAccum10(accumValue.get(i).toString());
								break;
						default:
							break;
						
						}
						}
					}
				}
			}
		}
		if(null != majorHeadingFromMap.getDescriptionText()){ 
			contractDataObject.setMajorHeading(majorHeadingFromMap.getDescriptionText());
		} else { 
			contractDataObject.setMajorHeading(DomainConstants.EMPTY);
		}
		if(null != minorHeadingFromMap.getDescriptionText()){ 
			contractDataObject.setMinorHeading(minorHeadingFromMap.getDescriptionText());
		}else{ 
			contractDataObject.setMinorHeading(DomainConstants.EMPTY);
		}
			
		Variable var = mappingFromMap.getVariable();                                           	
		  	if(var != null) {
		  		if(null != var.getDescription()){
		  			contractDataObject.setVariableDescription(var.getDescription());
		  		}else{
		  			contractDataObject.setVariableDescription(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getVariableId()){
		  			contractDataObject.setVariableId(var.getVariableId());
		  		}else{
		  			contractDataObject.setVariableId(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getPva()){
		  			contractDataObject.setPva(var.getPva());
		  		}else{
		  			contractDataObject.setPva(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getVariableFormat()){
		  			contractDataObject.setVariableFormat(var.getVariableFormat());
		  		}else{
		  			contractDataObject.setVariableFormat(DomainConstants.EMPTY);
		  		}
		  		if(null != var.getVariableValue()){
		  			contractDataObject.setVariableValue(var.getVariableValue());
		  		}else{
		  			contractDataObject.setVariableValue(DomainConstants.EMPTY);
		  		}
		  		if(null != mappingFromMap.getEB01Value()){ 
		  			contractDataObject.setEB01(mappingFromMap.getEB01Value());
		  		}else{
		  			contractDataObject.setEB01(DomainConstants.EMPTY);
		  		}
		  		if(null != mappingFromMap.getEB02Value()){ 
		  			contractDataObject.setEB02(mappingFromMap.getEB02Value());
		  		}else{
		  			contractDataObject.setEB02(DomainConstants.EMPTY);
		  		}
				if(var.isNotApplicable()) {
					contractDataObject.setNotApplicable(DomainConstants.Y);
				}  else {
					contractDataObject.setNotApplicable(DomainConstants.N);
				}
				if(null != var.getSensitiveBenefitIndicator()){
		        	contractDataObject.setSensitiveInd(var.getSensitiveBenefitIndicator());
				}else{
		        	contractDataObject.setSensitiveInd(DomainConstants.EMPTY);
				}
		       }
		  	/*List eb03List = mappingFromMap.getEb03Values();
	        eb03 = "";
	        eb03Count = 0;
	        if(eb03List != null) {
	            Iterator eb03ListIterator = eb03List.iterator();
	            if(eb03ListIterator != null) {
	                while(eb03ListIterator.hasNext()) {
	                    eb03Count++;
	                    if (eb03Count != 1) {
	                        eb03 = eb03+","+(String)eb03ListIterator.next();
	                    } else {
	                        eb03 = 	(String)eb03ListIterator.next();
	                    }
	                }
	            }
	        }
	        if(null != eb03){
	        	contractDataObject.setEb03(eb03);
	        } else {
	        	contractDataObject.setEb03(DomainConstants.EMPTY);
	        }*/
	        
	        if(null != eb03AssnValue)
			{
	        	contractDataObject.setEb03(eb03AssnValue.getEb03().getValue());
	        	if(null != eb03AssnValue.getIii02List() && eb03AssnValue.getIii02List().size() > 0)
    			{
    				List<String> iii02ValueList = new ArrayList<String>();
    				for(HippaCodeValue value : eb03AssnValue.getIii02List())
    				{
    					iii02ValueList.add(value.getValue()); 
    				}
    				String csvIii02 = StringUtils.join(iii02ValueList.toArray(), ",");
    				contractDataObject.setIii02(csvIii02);
    			}else {
					contractDataObject.setIii02(DomainConstants.EMPTY);
				}
				if(null != eb03AssnValue.getMessage() && !"-".equals(eb03AssnValue.getMessage()))
				{
					contractDataObject.setMessage(eb03AssnValue.getMessage());
				}else {
					contractDataObject.setMessage(DomainConstants.EMPTY);
				}
	            if(null != eb03AssnValue.getMsgReqdInd() && !"-".equals(eb03AssnValue.getMsgReqdInd()))
				{
	            	contractDataObject.setMsgReqType(eb03AssnValue.getMsgReqdInd());
				}
	            else {
					contractDataObject.setMsgReqType(DomainConstants.EMPTY);
				}
	            if(null != eb03AssnValue.getNoteType())
				{
	            	contractDataObject.setMedssaeType(eb03AssnValue.getNoteType().getValue());//MESSAGE type
				}
	            else {
					contractDataObject.setMedssaeType(DomainConstants.EMPTY);
				}
			}else {
				if(null != mappingFromMap.getEb03Values() && mappingFromMap.getEb03Values().size() != 0) {
        			contractDataObject.setEb03(StringUtils.join(mappingFromMap.getEb03Values().toArray(), ","));
        		}else {
        			contractDataObject.setEb03(DomainConstants.EMPTY);
        		}
	        	contractDataObject.setIii02(DomainConstants.EMPTY);
	        	contractDataObject.setMessage(DomainConstants.EMPTY);
	        	contractDataObject.setMsgReqType(DomainConstants.EMPTY);
	        	contractDataObject.setMedssaeType(DomainConstants.EMPTY);
	        }
		            
		            List umRuleList = mappingFromMap.getUmRuleValues();
		            String umRule = "";
		            int umRuleCount = 0;
		            if(umRuleList != null) {
		            	//Collections.sort(umRuleList);
		                Iterator umRuleListIterator = umRuleList.iterator();
		                if(umRuleListIterator != null) {
		                    while(umRuleListIterator.hasNext()) {
		                    	umRuleCount++;
		                        if (umRuleCount != 1) {
		                        	umRule = umRule+", "+(String)umRuleListIterator.next();
		                        } else {
		                        	umRule = 	(String)umRuleListIterator.next();
		                        }
		                    }
		                }
		            }
		            if(null != umRule){
		            	contractDataObject.setUmRule(umRule);
		            }else{
		            	contractDataObject.setUmRule(DomainConstants.EMPTY);
		            }
		            
		            if(null != mappingFromMap.getEB06Value()){
		            	contractDataObject.setEB06(mappingFromMap.getEB06Value());
		            }else{
		            	contractDataObject.setEB06(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getEB09Value()){
		            	contractDataObject.setEB09(mappingFromMap.getEB09Value());
		            }else{
		            	contractDataObject.setEB09(DomainConstants.EMPTY);
		            }
		            // BXNI - June - Start and End Age
		            /*if(null != mappingFromMap.getStartAgeValue() 
		            		&& !DomainConstants.EMPTY.equals(mappingFromMap.getStartAgeValue())){
		            	String startAge = mappingFromMap.getStartAgeValue();
		            	if (!BxUtil.isInteger(startAge)) {
		            		String startAgeCodedValue =MappingUtil.getVariableCodedValueInContract(contract, startAge);
		            		if (null != startAgeCodedValue && !DomainConstants.EMPTY.equals(startAgeCodedValue)) {
		            			contractDataObject.setStartAge(mappingFromMap.getStartAgeValue() + " (" + startAgeCodedValue + ")");
		            		} else {
		            			contractDataObject.setStartAge(mappingFromMap.getStartAgeValue());
		            		}
		            	} else {
		            		contractDataObject.setStartAge(mappingFromMap.getStartAgeValue());
		            	}
		            }
		            else{
		            	contractDataObject.setStartAge(DomainConstants.EMPTY);
		            }*/
		            String startAge = "";
		            List<String> startAgeList = null;
		            if(!StringUtils.isBlank(mappingFromMap.getStartAgeValue())){
		            	startAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getStartAgeValue());
		            }
		            int startAgeCount = 0;
		            if(startAgeList != null) {
		            	Collections.sort(startAgeList);
		                Iterator startAgeListIterator = startAgeList.iterator();
		                if(startAgeListIterator != null) {
		                    while(startAgeListIterator.hasNext()) {
		                    	startAgeCount++;
		                    	String startAgeTemp = (String)startAgeListIterator.next();
		                    	if (!BxUtil.isInteger(startAgeTemp)) {
		                    		String startAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract, startAgeTemp);
		                    		if (null != startAgeCodedValue && !DomainConstants.EMPTY.equals(startAgeCodedValue)) {
		                    			startAgeTemp = startAgeTemp +  " (" + startAgeCodedValue + ")";
		                    		} 
		                    	}
		                        if (startAgeCount != 1) {
		                        	startAge = startAge+", "+startAgeTemp;
		                        } else {
		                        	startAge = 	startAgeTemp;
		                        }
		                    }
		                }
		            }
		            if(null != startAge && !DomainConstants.EMPTY.equals(startAge)) {
		            	contractDataObject.setStartAge(startAge);
		            } else {
		            	contractDataObject.setStartAge(DomainConstants.EMPTY);
		            }
		            
		            /*if(null != mappingFromMap.getEndAgeValue() 
		            		&& !DomainConstants.EMPTY.equals(mappingFromMap.getEndAgeValue())){
		            	String endAge = mappingFromMap.getEndAgeValue();
		            	if (!BxUtil.isInteger(endAge)) {
		            		String endAgeCodedValue =MappingUtil.getVariableCodedValueInContract(contract, endAge);
		            		if (null != endAgeCodedValue && !DomainConstants.EMPTY.equals(endAgeCodedValue)) {
		            			contractDataObject.setEndAge(mappingFromMap.getEndAgeValue() + " (" + endAgeCodedValue + ")");
		            		} else {
		            			contractDataObject.setEndAge(mappingFromMap.getEndAgeValue());
		            		}
		            	} else {
		            		contractDataObject.setEndAge(mappingFromMap.getEndAgeValue());
		            	}
		            }
		            else{
		            	contractDataObject.setEndAge(DomainConstants.EMPTY);
		            }*/
		            
		            //BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
		            String endAge = "";
		            List<String> endAgeList = null;
		            if(!StringUtils.isBlank(mappingFromMap.getEndAgeValue())){
		            	endAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getEndAgeValue());
		            }
		            int endAgeCount = 0;
		            if(endAgeList != null) {
		            	Collections.sort(endAgeList);
		                Iterator endAgeListIterator = endAgeList.iterator();
		                if(endAgeListIterator != null) {
		                    while(endAgeListIterator.hasNext()) {
		                    	endAgeCount++;
		                    	String endAgeTemp = (String)endAgeListIterator.next();
		                    	if (!BxUtil.isInteger(endAgeTemp)) {
		                    		String endAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract, endAgeTemp);
		                    		if (null != endAgeCodedValue && !DomainConstants.EMPTY.equals(endAgeCodedValue)) {
		                    			endAgeTemp = endAgeTemp +  "(" + endAgeCodedValue + ")";
		                    		} 
		                    	}
		                        if (endAgeCount != 1) {
		                        	endAge = endAge+", "+endAgeTemp;
		                        } else {
		                        	endAge = 	endAgeTemp;
		                        }
		                    }
		                }
		            }
		            if(null != endAge && !DomainConstants.EMPTY.equals(endAge)) {
		            	contractDataObject.setEndAge(endAge);
		            } else {
		            	contractDataObject.setEndAge(DomainConstants.EMPTY);
		            }
		            
		           /* if(null != mappingFromMap.getIi02Value()){
		            	contractDataObject.setIii02(mappingFromMap.getIi02Value());
		            }else{
		            	contractDataObject.setIii02(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getMessage()){
		            	contractDataObject.setMessage(mappingFromMap.getMessage());
		            }else{
		            	contractDataObject.setMessage(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getMsg_type_required()){
		            	contractDataObject.setMsgReqType(mappingFromMap.getMsg_type_required());
		            }else{
		            	contractDataObject.setMsgReqType(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getNoteTypeCodeValue()){
		            	contractDataObject.setMedssaeType(mappingFromMap.getNoteTypeCodeValue());
		            }else{
		            	contractDataObject.setMedssaeType(DomainConstants.EMPTY);
		            }*/
		            
		            if(null != mappingFromMap.getHsd01Value()){
		            	contractDataObject.setHsd1(mappingFromMap.getHsd01Value());
		            }else{
		            	contractDataObject.setHsd1(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd02Value() && mappingFromMap.getHsd02Value().size() != 0){
		            	contractDataObject.setHsd2(StringUtils.join(mappingFromMap.getHsd02Value().toArray(), ","));
		            }else{
		            	contractDataObject.setHsd2(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd03Value()){
		            	contractDataObject.setHsd3(mappingFromMap.getHsd03Value());
		            }else{
		            	contractDataObject.setHsd3(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd04Value()){
		            	contractDataObject.setHsd4(mappingFromMap.getHsd04Value());
		            }else{
		            	contractDataObject.setHsd4(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd05Value()){
		            	contractDataObject.setHsd5(mappingFromMap.getHsd05Value());
		            }else{
		            	contractDataObject.setHsd5(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd06Value()){
		            	contractDataObject.setHsd6(mappingFromMap.getHsd06Value());
		            }else{
		            	contractDataObject.setHsd6(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd07Value()){
		            	contractDataObject.setHsd7(mappingFromMap.getHsd07Value());
		            }else{
		            	contractDataObject.setHsd7(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getHsd08Value()){
		            	contractDataObject.setHsd8(mappingFromMap.getHsd08Value());
		            }else{
		            	contractDataObject.setHsd8(DomainConstants.EMPTY);
		            }
		            if(null != mappingFromMap.getMappingComplete()){
		            	contractDataObject.setFinalized(mappingFromMap.getMappingComplete());
		            }else{
		            	contractDataObject.setFinalized(DomainConstants.EMPTY);	
		            }
	}
/**
 * method to populate the EWPD contract values in Contract BX report - Text view.	
 * @param contract
 * @return
 */
public static ArrayList getEwpdContractBean(ContractVO contract){
		
	ArrayList contractEwpdData = new ArrayList();
	int eb03Count;
    String eb03;
	 Map majorHeadings = contract.getMajorHeadings();
        if (majorHeadings != null) {
     //   Iterator majorHeadingsIterator = majorHeadings.keySet().iterator();
          Iterator majorHeadingsIterator = majorHeadings.entrySet().iterator();
        if (majorHeadingsIterator != null) {
            while (majorHeadingsIterator.hasNext()) {
          //      String majorHeadingDesc = (String) majorHeadingsIterator.next();
         //       MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) majorHeadings.get(majorHeadingDesc);
            	MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) ((Map.Entry) majorHeadingsIterator.next()).getValue();
                Map minorHeadings = majorHeadingFromMap.getMinorHeadings();
                if (minorHeadings != null) {
               //     Iterator minorHeadingsIterator = minorHeadings.keySet().iterator();
                	Iterator minorHeadingsIterator = minorHeadings.entrySet().iterator();
                    if (minorHeadingsIterator != null) {
							while (minorHeadingsIterator.hasNext()) {
					//		String minorHeadingDesc = (String) minorHeadingsIterator.next();
                    //      MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) minorHeadings.get(minorHeadingDesc);
							MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) ((Map.Entry) minorHeadingsIterator.next()).getValue();
                            Map mappings = minorHeadingFromMap.getMappings();
                            Mapping ruleMapping = minorHeadingFromMap.getRuleMapping();
                            if (mappings != null) {
									//Iterator mappingsIterator = mappings.keySet().iterator();
                            	Iterator mappingsIterator = mappings.entrySet().iterator();
									if (mappingsIterator != null) {
										while (mappingsIterator.hasNext()) {
											//String mappingDesc = (String) mappingsIterator.next();
											//ContractMappingVO mappingFromMap = (ContractMappingVO) mappings.get(mappingDesc);
											ContractMappingVO mappingFromMap = (ContractMappingVO) ((Map.Entry) mappingsIterator.next()).getValue();
											if (mappingFromMap != null) {
												ContractDataObjectVO contractEwpdDataObject = new ContractDataObjectVO();
												if (mappingFromMap.isMapped()) {
													if(null != ruleMapping.getEb03()) {
	                                        			List<String> associatedList = new ArrayList<String>();
	                                        			String indEb03Assn;
	                                        			if("Y".equals(ruleMapping.getIndvdlEb03AssnIndicator()) ||
	                                        					"Y".equals(mappingFromMap.getIndvdlEb03AssnIndicator())) {
	                                        				indEb03Assn = "Y";
	                                        			}else {
	                                        				indEb03Assn = "N";
	                                        			}
	                                                	/*String unAssnEb03CSV = null;
	                                					List<String> unAssociatedList = new ArrayList<String>();
	                                					List<EB03Association> associatedEB03AssnList = new ArrayList<EB03Association>();*/
	                                        			List<EB03Association> associatedEB03AssnList = getAssociatedEB03CodesForEwpd(ruleMapping,mappingFromMap,
	                                        					associatedList,indEb03Assn);
	                                                	/*if(null != unAssociatedList && unAssociatedList.size() != 0) {
	                                                		unAssnEb03CSV = StringUtils.join(unAssociatedList.toArray(), ",");
	                                                	}*/
	                                                	if(null != associatedEB03AssnList && associatedEB03AssnList.size() != 0) {
	                                                		for(EB03Association eb03AssnValue : associatedEB03AssnList)
	                                                    	{
	                                                			contractEwpdDataObject = new ContractDataObjectVO();
	                                                			createMappedRowsForEwpd(
	                                                					majorHeadingFromMap,
	                													minorHeadingFromMap,
	                													ruleMapping,
	                													mappingFromMap,
	                													contractEwpdDataObject,
	    																eb03AssnValue);
	                                                			contractEwpdData.add(contractEwpdDataObject);
	                                                        }
	                                                    }else {
	                                                    	contractEwpdDataObject = new ContractDataObjectVO();
	                                                    	createMappedRowsForEwpd(
	                                                    			majorHeadingFromMap,
	            													minorHeadingFromMap,
	            													ruleMapping,
	            													mappingFromMap,
	            													contractEwpdDataObject,
	    															null);
	                                                    	contractEwpdData.add(contractEwpdDataObject);
	                                                    }
	                                                	
	                                        		}else {
	                                        			contractEwpdDataObject = new ContractDataObjectVO();
	                                        			createMappedRowsForEwpd(
	                                        					majorHeadingFromMap,
	        													minorHeadingFromMap,
	        													ruleMapping,
	        													mappingFromMap,
	        													contractEwpdDataObject,
																null);
	                                        			contractEwpdData.add(contractEwpdDataObject);
	                                        		}
                                                         } else {
                                                        	 contractEwpdDataObject = new ContractDataObjectVO();
                                                        	 if (null != majorHeadingFromMap.getDescriptionText()) {
																contractEwpdDataObject.setBenefitComponent(majorHeadingFromMap.getDescriptionText());
															} else {
																contractEwpdDataObject.setBenefitComponent(DomainConstants.EMPTY);
															}
                                             				if (null != minorHeadingFromMap.getDescriptionText()) {
																contractEwpdDataObject.setBenefit(minorHeadingFromMap.getDescriptionText());
															} else {
																contractEwpdDataObject.setBenefit(DomainConstants.EMPTY);
															}
                                             				SPSId sps = mappingFromMap.getSpsId();
                                                			if (sps != null) {
                                                				if (null != sps.getSpsDesc()) {
																	contractEwpdDataObject.setSpsIdDesc(sps.getSpsDesc());
																} else {
																	contractEwpdDataObject.setSpsIdDesc(DomainConstants.EMPTY);
																}
                                                				if (null != sps.getSpsId()) {
																	contractEwpdDataObject.setSpsId(sps.getSpsId());
																} else {
																	contractEwpdDataObject.setSpsId(DomainConstants.EMPTY);
																}
                                                				if (null != sps.getLinePVA()) {
																	contractEwpdDataObject.setSpsPva(sps.getLinePVA());
																} else {
																	contractEwpdDataObject.setSpsPva(DomainConstants.EMPTY);
																}
                                                				if (null != sps.getLineDataType()) {
																	contractEwpdDataObject.setSpsDataType(sps.getLineDataType());
																} else {
																	contractEwpdDataObject.setSpsDataType(DomainConstants.EMPTY);
																}
                                                				if (null != sps.getLineValue()) {
																	contractEwpdDataObject.setLineValue(sps.getLineValue());
																} else {
																	contractEwpdDataObject.setLineValue(DomainConstants.EMPTY);
																}
                                                    			}
	                                                			 if (null != mappingFromMap.getMessage()) {
																	contractEwpdDataObject.setMessage(mappingFromMap.getMessage());
																} else {
																	contractEwpdDataObject.setMessage(DomainConstants.EMPTY);
																}
	                                                             if (null != mappingFromMap.getMsg_type_required()) {
																	contractEwpdDataObject.setMsgReqType(mappingFromMap.getMsg_type_required());
																} else {
																	contractEwpdDataObject.setMsgReqType(DomainConstants.EMPTY);
																}
	                                                             if (null != mappingFromMap.getNoteTypeCodeValue()) {
																	contractEwpdDataObject.setMedssaeType(mappingFromMap.getNoteTypeCodeValue());
																} else {
																	contractEwpdDataObject.setMedssaeType(DomainConstants.EMPTY);
																}
	                                                             // Code change to show the tier description- July release
	     	                                        			if (null != mappingFromMap.getContractMapping() && null != mappingFromMap.getContractMapping().getTierDescription()) {
	     	                                        				contractEwpdDataObject.setTierDescription(mappingFromMap.getContractMapping().getTierDescription());
	     	                                        			} else {
	     	                                        				contractEwpdDataObject.setTierDescription(DomainConstants.EMPTY);
	     	                                        			}
	     	                                        			if(null != ruleMapping){
	     	       												Rule rule = ruleMapping.getRule();
	     	       												if(null != rule){
	     	       													contractEwpdDataObject.setRuleId(rule.getHeaderRuleId());
	     	       												}else {
	     	       	                                    			contractEwpdDataObject.setRuleId(DomainConstants.EMPTY);
	     	       	                                    		}
	     	       											}
	     	                                        		contractEwpdData.add(contractEwpdDataObject);
                                                     }
                                        		}
                                    		}
                                		}
                            		}
                        		}
                    		}
                		}
            		}
        		}
        	}
	return contractEwpdData;
}

private static List<EB03Association> getAssociatedEB03CodesForEwpd(Mapping ruleMapping,Mapping msgMapping,List<String> associatedList,
		String indvdlEb03AssnInd) {
	List<EB03Association> associatedEb03AssnList = new ArrayList<EB03Association>();
	List<String> assnList = new ArrayList<String>();
	if(null!=ruleMapping.getEb03())
	{
		if((null!=ruleMapping.getEb03().getEb03Association()) && 
				(ruleMapping.getEb03().getEb03Association().size() != 0))
				{
					associatedEb03AssnList.addAll(ruleMapping.getEb03().getEb03Association());
					if(null != associatedEb03AssnList && associatedEb03AssnList.size() != 0)
					{
						for (EB03Association eb03Assn : associatedEb03AssnList) {
							if(null != eb03Assn.getEb03() && (null != eb03Assn.getEb03().getValue() || "".equals(eb03Assn.getEb03().getValue())))
							{
								 if (!assnList.contains(eb03Assn.getEb03().getValue())) {
								    	assnList.add(eb03Assn.getEb03().getValue());
								    }
							}
						}
					}
				}
	}
	if(null!=msgMapping.getEb03())
	{
		if((null!=msgMapping.getEb03().getEb03Association()) && 
				(msgMapping.getEb03().getEb03Association().size() != 0))
				{
					associatedEb03AssnList.addAll(msgMapping.getEb03().getEb03Association());
				}
	}
	List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
	if("Y".equals(indvdlEb03AssnInd))
	{
		if(null != assnList && assnList.size() != 0)
		{
			for(String assnStr : assnList)
			{
				EB03Association eb03AssnObj = new EB03Association();
				for(EB03Association eb03Assoc : associatedEb03AssnList)
				{
					if(null != eb03Assoc && null != eb03Assoc.getEb03())
					{
						if(assnStr.equals(eb03Assoc.getEb03().getValue()))
						{
							eb03AssnObj.setEb03(eb03Assoc.getEb03());
							if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
								eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
							}
							if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
									&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
								eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
							}
							if((null != eb03Assoc.getMessage()) 
									&& (!"-".equals(eb03Assoc.getMessage()))) {
								eb03AssnObj.setMessage(eb03Assoc.getMessage());
							}
							if((null != eb03Assoc.getMsgReqdInd()) 
									&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
								eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
							}
						}
					}
				}
				eb03AssnList.add(eb03AssnObj);
			}
		}
	}else {
		if(null != assnList && assnList.size() != 0)
		{
			EB03Association eb03AssnObj = new EB03Association();
			String eb03Str = StringUtils.join(assnList.toArray(), ",");
			HippaCodeValue eb03Code = new HippaCodeValue();
			eb03Code.setValue(eb03Str);
			eb03AssnObj.setEb03(eb03Code);
			String assnStr = assnList.get(0);
			for(EB03Association eb03Assoc : associatedEb03AssnList)
			{
				if(null != eb03Assoc && null != eb03Assoc.getEb03())
				{
					if(assnStr.equals(eb03Assoc.getEb03().getValue()))
					{
						if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
							eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
						}
						if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
								&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
							eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
						}
						if((null != eb03Assoc.getMessage()) 
								&& (!"-".equals(eb03Assoc.getMessage()))) {
							eb03AssnObj.setMessage(eb03Assoc.getMessage());
						}
						if((null != eb03Assoc.getMsgReqdInd()) 
								&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
							eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
						}
					}
				}
			}
			eb03AssnList.add(eb03AssnObj);
		}
	}
	
	return eb03AssnList;
}

/**
 * @param majorHeadingFromMap
 * @param minorHeadingFromMap
 * @param ruleMapping
 * @param mappingFromMap
 * @param contractEwpdDataObject
 */
private static void createMappedRowsForEwpd(
		MajorHeadingsVO majorHeadingFromMap,
		MinorHeadingsVO minorHeadingFromMap, Mapping ruleMapping,
		ContractMappingVO mappingFromMap,
		ContractDataObjectVO contractEwpdDataObject,EB03Association eb03AssnValue) {
	int eb03Count;
	String eb03;
	HippaSegment accum = mappingFromMap.getAccum();
	if (accum != null) {
		List accumList = accum.getHippaCodeSelectedValues();
		if (null != accumList && !accumList.isEmpty()) {
			Iterator accumListIterator = accumList.iterator();
			if (accumListIterator != null) {
				while (accumListIterator.hasNext()) {
					HippaCodeValue accumCode = (HippaCodeValue) accumListIterator.next();
					if (null != accumCode.getValue()) {
						contractEwpdDataObject.setAccum1(accumCode.getValue());
					} else {
						contractEwpdDataObject
								.setAccum1(DomainConstants.EMPTY);
					}
				}
			}
		}
                 				}
                 				if (null != majorHeadingFromMap.getDescriptionText()) {
                					contractEwpdDataObject.setBenefitComponent(majorHeadingFromMap.getDescriptionText());
                 				}
                 				else {
                					contractEwpdDataObject.setBenefitComponent(DomainConstants.EMPTY);
                 				}
                 				if (null != minorHeadingFromMap.getDescriptionText()) {
                					contractEwpdDataObject.setBenefit(minorHeadingFromMap.getDescriptionText());
                 				}
                 				else {
                					contractEwpdDataObject.setBenefit(DomainConstants.EMPTY);
                 				}
                          	if (null != ruleMapping && ruleMapping.isMapped()) {
                         		if (null != ruleMapping.getSensitiveBenefitIndicator()) {
	contractEwpdDataObject.setSensitiveInd(ruleMapping.getSensitiveBenefitIndicator());
                         		}
                         		else {
	contractEwpdDataObject.setSensitiveInd(DomainConstants.EMPTY);
                         		}
                          	}
                          	if(null != ruleMapping){
Rule rule = ruleMapping.getRule();
if(null != rule){
	contractEwpdDataObject.setRuleId(rule.getHeaderRuleId());
}else {
	contractEwpdDataObject.setRuleId(DomainConstants.EMPTY);
                         		}
}
                          	 boolean isSPSNotApplicable = false;
                                  boolean isRuleNotApplicable = false;
                     			 if (null != ruleMapping
			&& null != ruleMapping
					.getVariableMappingStatus()
			&& DomainConstants.STATUS_NOT_APPLICABLE
					.equalsIgnoreCase(ruleMapping
							.getVariableMappingStatus())) {
		isRuleNotApplicable = true;

	}
                     			 if (null != mappingFromMap.getVariableMappingStatus()
	&& DomainConstants.STATUS_NOT_APPLICABLE.equals(mappingFromMap.getVariableMappingStatus())) {
                                 	isSPSNotApplicable = true;
                                  }
                     			 if (isSPSNotApplicable || isRuleNotApplicable) {
                    				 contractEwpdDataObject.setNotApplicable(DomainConstants.Y);
                                  }
                                  else {
                                	 contractEwpdDataObject.setNotApplicable(DomainConstants.N);
                                  }
                     			SPSId sps = mappingFromMap.getSpsId();
                     			if (sps != null) {
                    				if (null != sps.getSpsDesc()) {
	contractEwpdDataObject.setSpsIdDesc(sps.getSpsDesc());
                    				}
                    				else {
	contractEwpdDataObject.setSpsIdDesc(DomainConstants.EMPTY);
                    				}
                    				if (null != sps.getSpsId()) {
	contractEwpdDataObject.setSpsId(sps.getSpsId());
                    				}
                    				else {
	contractEwpdDataObject.setSpsId(DomainConstants.EMPTY);
                    				}
                    				if (null != sps.getLinePVA()) {
	contractEwpdDataObject.setSpsPva(sps.getLinePVA());
                    				}
                    				else {
	contractEwpdDataObject.setSpsPva(DomainConstants.EMPTY);
                    				}
                    				if (null != sps.getLineDataType()) {
	contractEwpdDataObject.setSpsDataType(sps.getLineDataType());
                            		}
                    				else {
	contractEwpdDataObject.setSpsDataType(DomainConstants.EMPTY);
                    				}
                    				if (null != sps.getLineValue()) {
	contractEwpdDataObject.setLineValue(sps.getLineValue());
                    				}
                    				else {
	contractEwpdDataObject.setLineValue(DomainConstants.EMPTY);
                    				}
                         		}
                    				if (null != mappingFromMap.getEB01Value()) {
	contractEwpdDataObject.setEB01(mappingFromMap.getEB01Value());
                    				}
                    				else {
	contractEwpdDataObject.setEB01(DomainConstants.EMPTY);
                    				}
                    				if (null != mappingFromMap.getEB02Value()) {
	contractEwpdDataObject.setEB02(mappingFromMap.getEB02Value());
                    				}
                    				else {
	contractEwpdDataObject.setEB02(DomainConstants.EMPTY);
                    				}
	if (null != ruleMapping) {  
		if(null != eb03AssnValue) {
			contractEwpdDataObject.setEb03(eb03AssnValue.getEb03().getValue());
        	if(null != eb03AssnValue.getIii02List() && eb03AssnValue.getIii02List().size() > 0)
			{
				List<String> iii02ValueList = new ArrayList<String>();
				for(HippaCodeValue value : eb03AssnValue.getIii02List())
				{
					iii02ValueList.add(value.getValue()); 
				}
				String csvIii02 = StringUtils.join(iii02ValueList.toArray(), ",");
				contractEwpdDataObject.setIii02(csvIii02);
			}else {
				contractEwpdDataObject.setIii02(DomainConstants.EMPTY);
			}
        	if(null != eb03AssnValue.getMessage() && !("-".equals(eb03AssnValue.getMessage()) || 
					"".equals(eb03AssnValue.getMessage().trim())))
			{
				contractEwpdDataObject.setMessage(eb03AssnValue.getMessage());
			}else {
				contractEwpdDataObject.setMessage(DomainConstants.EMPTY);
			}
        	 if(null != eb03AssnValue.getMsgReqdInd() && !("-".equals(eb03AssnValue.getMsgReqdInd()) || 
						"".equals(eb03AssnValue.getMsgReqdInd().trim())))
			{
            	contractEwpdDataObject.setMsgReqType(eb03AssnValue.getMsgReqdInd());
			}
            else {
            	contractEwpdDataObject.setMsgReqType(DomainConstants.EMPTY);
			}
            if(null != eb03AssnValue.getNoteType())
			{
            	contractEwpdDataObject.setMedssaeType(eb03AssnValue.getNoteType().getValue());//MESSAGE type
			}
            else {
            	contractEwpdDataObject.setMedssaeType(DomainConstants.EMPTY);
			}				
		}else {
			if(null != ruleMapping.getEb03Values() && ruleMapping.getEb03Values().size() != 0) {
				contractEwpdDataObject.setEb03(StringUtils.join(ruleMapping.getEb03Values().toArray(), ","));
    		}else {
    			contractEwpdDataObject.setEb03(DomainConstants.EMPTY);
    		}
			contractEwpdDataObject.setIii02(DomainConstants.EMPTY);
			contractEwpdDataObject.setMessage(DomainConstants.EMPTY);
			contractEwpdDataObject.setMsgReqType(DomainConstants.EMPTY);
			contractEwpdDataObject.setMedssaeType(DomainConstants.EMPTY);
        }
	}
	
	String umRule = "";
	if (null != ruleMapping) {
		List umRuleList = ruleMapping.getUmRuleValues();
	    if(umRuleList != null) {
	        Iterator umRuleListIterator = umRuleList.iterator();
	        if(umRuleListIterator != null) {
	            while(umRuleListIterator.hasNext()) {
	                umRule = 	(String)umRuleListIterator.next();
	            }
	            if (null != umRule) {
	            	contractEwpdDataObject.setUmRule(umRule);
	            }
	            else {
	            	contractEwpdDataObject.setUmRule(DomainConstants.EMPTY);
	            }
	        }
	    }
	}
	    if (null != mappingFromMap.getEB06Value()) {
	    	contractEwpdDataObject.setEB06(mappingFromMap.getEB06Value());
	    }
	    else {
	    	contractEwpdDataObject.setEB06(DomainConstants.EMPTY);
	    }
	    if (null != mappingFromMap.getEB09Value()) {
	    	contractEwpdDataObject.setEB09(mappingFromMap.getEB09Value());
	    }
	    else {
	    	contractEwpdDataObject.setEB09(DomainConstants.EMPTY);
	    }
	    if (null != mappingFromMap.getHsd01Value()) {
	    	contractEwpdDataObject.setHsd1(mappingFromMap.getHsd01Value());
	    }
	    else {
	    	contractEwpdDataObject.setHsd1(DomainConstants.EMPTY);
	    }
	    if (null != mappingFromMap.getHsd02Value() && null != mappingFromMap.getHsd02Value().get(0) && mappingFromMap.getHsd02Value().size() != 0) {
	    	contractEwpdDataObject.setHsd2(mappingFromMap.getHsd02Value().get(0).toString());
	    }
	    else {
	    	contractEwpdDataObject.setHsd2(DomainConstants.EMPTY);
	    }
	    if (null != mappingFromMap.getHsd03Value()) {
	    	contractEwpdDataObject.setHsd3(mappingFromMap.getHsd03Value());
	    }
	    else {
	    	contractEwpdDataObject.setHsd3(DomainConstants.EMPTY);
	    }
	    if (null != mappingFromMap.getHsd04Value()) {
	    	contractEwpdDataObject.setHsd4(mappingFromMap.getHsd04Value());
	    } else {
			contractEwpdDataObject.setHsd4(DomainConstants.EMPTY);
		}
	    if (null != mappingFromMap.getHsd05Value()) {
			contractEwpdDataObject.setHsd5(mappingFromMap.getHsd05Value());
		} else {
			contractEwpdDataObject.setHsd5(DomainConstants.EMPTY);
		}
	    if (null != mappingFromMap.getHsd06Value()) {
			contractEwpdDataObject.setHsd6(mappingFromMap.getHsd06Value());
		} else {
			contractEwpdDataObject.setHsd6(DomainConstants.EMPTY);
		}
	    if (null != mappingFromMap.getHsd07Value()) {
			contractEwpdDataObject.setHsd7(mappingFromMap.getHsd07Value());
		} else {
			contractEwpdDataObject.setHsd7(DomainConstants.EMPTY);
		}
	    if (null != mappingFromMap.getHsd08Value()) {
			contractEwpdDataObject.setHsd8(mappingFromMap.getHsd08Value());
		} else {
			contractEwpdDataObject.setHsd8(DomainConstants.EMPTY);
		}
	    if (null != mappingFromMap.getMappingComplete()) {
			contractEwpdDataObject.setFinalized(mappingFromMap.getMappingComplete());
		} else {
			contractEwpdDataObject.setFinalized(DomainConstants.EMPTY);
		}
	    //contractEwpdData.add(contractEwpdDataObject);
	    // Code change to show the tier description- July release
		if (null != mappingFromMap.getContractMapping() && null != mappingFromMap.getContractMapping().getTierDescription()) {
			contractEwpdDataObject.setTierDescription(mappingFromMap.getContractMapping().getTierDescription());
		} else {
			contractEwpdDataObject.setTierDescription(DomainConstants.EMPTY);
		}
}
	/**
	 * method to display headings in Contract BX report view based on the System - text view.
	 * @param contractDataList
	 * @return
	 * @throws JRException
	 */
	public Map getContractTextData(List contractDataList) throws JRException {
		Map params = new HashMap();
		if (contractDataList != null) {
			for (int i = 0; i < contractDataList.size(); i++) {
				ContractVO contract = (ContractVO) contractDataList.get(i);
				//Headers
				params.put("contractIdHeader", "Contract ID");
				params.put("dateSegmentHeader", "Date Segment");
				params.put("pvaHeader", "PVA");
				params.put("formatHeader", "Format");
				params.put("codedValueHeader", "Coded Value");
				params.put("eb01Header", "EB01");
				params.put("eb02Header", "EB02");
				params.put("eb03Header", "EB03");
				params.put("eb06Header", "EB06");
				params.put("eb09Header", "EB09");
				params.put("iii02Header", "III02");
				params.put("messageHeader", "MESSAGE");
				params.put("msgReqHeader", "MSG REQ IND");
				params.put("medssaeTypeHeader", "MESSAGE TYPE");
				params.put("accum1Header", "ACCUM 1");
				params.put("accum2Header", "ACCUM 2");
				params.put("accum3Header", "ACCUM 3");
				params.put("accum4Header", "ACCUM 4");
				params.put("accum5Header", "ACCUM 5");
				params.put("accum6Header", "ACCUM 6");
				params.put("accum7Header", "ACCUM 7");
				params.put("accum8Header", "ACCUM 8 ");
				params.put("accum9Header", "ACCUM 9");
				params.put("accum10Header", "ACCUM 10");
				params.put("sensitiveIndHeader", "SENSITIVE IND");
				params.put("hsd01Header", "HSD01");
				params.put("hsd02Header", "HSD02");
				params.put("hsd03Header", "HSD03");
				params.put("hsd04Header", "HSD04");
				params.put("hsd05Header", "HSD05");
				params.put("hsd06Header", "HSD06");
				params.put("hsd07Header", "HSD07");
				params.put("hsd08Header", "HSD08");
				params.put("umRuleHeader", "UM Rule");
				params.put("notFinalizedHeader", "Finalized");
				params.put("notApplicableHeader", "Not Applicable");
				// contract and date
				params.put("contractId", contract.getContractId());
				params.put("effectiveDate", contract.getEffectiveDate());
				
				if (DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem())) {
					JRBeanCollectionDataSource wpdTextSource = new JRBeanCollectionDataSource(TextReportView.getWpdContractBean(contractDataList));
					params.put("contractDataSource", wpdTextSource);
					params.put("majorHeadingHeader", "Major Heading");
					params.put("minorHeadingHeader", "Minor heading");
					params.put("variableDescHeader", "Variable Desc");
					params.put("variableHeader", "Variable");
					params.put("startAgeHeader", "Start Age");
					params.put("endAgeHeader", "End Age");
				} else if (DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract.getSystem())) {
					JRBeanCollectionDataSource wpdTextSource = new JRBeanCollectionDataSource(TextReportView.getWpdContractBean(contractDataList));
					params.put("contractDataSource", wpdTextSource);
					params.put("majorHeadingHeader", "Major Heading");
					params.put("minorHeadingHeader", "Minor heading");
					params.put("variableDescHeader", "Variable Desc");
					params.put("variableHeader", "Variable");
					params.put("startAgeHeader", "Start Age");
					params.put("endAgeHeader", "End Age");
				} else if (DomainConstants.SYSTEM_EWPD.equalsIgnoreCase(contract.getSystem())) {
					JRBeanCollectionDataSource ewpdTextSource = new JRBeanCollectionDataSource(
							TextReportView.getEwpdContractBean(contract));
					params.put("contractDataSource", ewpdTextSource);
					params.put("benefitComponentHeader", "Benefit Component");
					params.put("benefitHeader", "Benefit");
					params.put("ruleIdHeader", "Header Rule");
					params.put("tierDescrptionHeader", "Tier Details");
					params.put("spsIdDescHeader", "SPS Id Description");
					params.put("spsIdHeader", "SPS Id");
					params.put("versionHeader", "Version");
					params.put("version", Integer.toString(contract.getVersion()));
				}
			}
		}
		return params;
	}
}

