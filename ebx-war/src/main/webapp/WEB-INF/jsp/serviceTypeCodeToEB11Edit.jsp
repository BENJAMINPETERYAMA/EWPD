	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>


<script type="text/javascript" >


</script>
<table  width="800" border="0" cellpadding="0" cellspacing="0" class="Pd3" ></table>
		 <table width="900" border="0" cellpadding="0" cellspacing="0" class="Pd3" > 
				<tr class="createEditTable1-Listdata">					
	                   <td  class="headTextExclusions" width="50" >LOB/State:  </td>	
	                   <td width="150" align="left" >
							<input type="hidden" value="${resultList[0].lineOfBusiness}"id="lobNamehidden">
							<input type="hidden" value="${resultList[0].lobId}"id="lobidhidden">
							<input type="hidden" value="${resultList[0].action}"id="actionhidden">
							<label id="lobLabel" >${resultList[0].lineOfBusiness}</label>
							<input type="text" id="lobText" name = lobText" value="${resultList[0].lineOfBusiness}"  style="display:none;width: 95%;" maxlength="25" >			
					   </td>
					   <td width="10">&nbsp;</td>	   
	                   <td  class="headTextExclusions" width="40" >MBU:  </td>	
	                   <td  width="400">
	                     	<input type="hidden" value="${resultList[0].commaSeperatedMbu}" id="mbuIdHidden" >
							<label id="mbuLabel" >${resultList[0].commaSeperatedMbu}</label>	
							<textarea  id="mbuText" style="display:none;width: 95%;" maxlength="25" >${resultList[0].commaSeperatedMbu}</textarea>		
	                   </td>
	                    <td width="10">&nbsp;</td>	
	                   <td width="60" class="headTextExclusions" align="left">
	                   		<input type="checkbox" <c:if test="${resultList[0].ssbIndicator== 'Y'}">checked</c:if> disabled name="isSSB" id="isSSB" value="${resultList[0].ssbIndicator}"/>&nbsp;SSB
	                
	                   </td>
	                   	<td width="20">&nbsp;</td>	                
	                   <td id = "editTD" width="160" align="right">
	                   <a href="#" style="color:blue;" onclick="editLobMbu();" >Edit</a>
	                   </td>
	                  </tr>
	                  </table>
	
	<table width="925" border="0" cellpadding="0" cellspacing="0" class="Pd3" >	
		<c:if test="${servicetypeMappingsList[0].serviceTypeCode == null}" >
			<tr  style="position: fixed; top:0; background-color: #D5DFE5">					
	                    	<td width="100px"  class="headTextExclusions">EB03</td>
	                    	<td width="140px"></td>
							<td width="100px"></td>
							<td width="100px" class="headTextExclusions" >EB11</td>
							<td width="140px"></td>	
							<td width="100px"></td>
							<td width="120px" class="headTextExclusions">Place of Service</td>
	                   		<td width="140px"></td> 
							<td width="50px"></td>           
	       </tr>
	       </c:if>
	      <c:if test="${servicetypeMappingsList[0].serviceTypeCode != null}" >
	       		<tr  style="position: fixed; top:0; background-color: #D5DFE5">					
	                    	<td width="100px"  class="headTextExclusions">EB03</td>
	                    	<td width="140px"></td>
							<td width="160px"></td>
							<td width="100px" class="headTextExclusions" >EB11</td>
							<td width="140px"></td>	
							<td width="100px"></td>
							<td width="120px" class="headTextExclusions">Place of Service</td>
	                   		<td width="120px"></td> 
							<td width="50px"></td>           
	     	  </tr>
	       </c:if>
	</table>
	  <div id="serviceTypeCreateEditContainer" style="height:160px" >	<!--createEditContainer Starts-->			
			<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="serviceTypeMappingsTable">
						<tbody id="serviceTypeMappingsTbody">
						
						<tr class="createEditTable1-Listdata alternate">												                
	                    <td style="width: 95px">
							<input type="text" name="serviceTypeCodeVal" class="inputbox60" id="serviceTypeCodeId0" value="${servicetypeMappingsList[0].serviceTypeCode}" />
						</td>
	                    <td style="width: 140px">
							<label id="serviceTypeCodeIdLabel0" for="serviceTypeCodeId0" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[0].serviceTypeCodeDesc}" />
							</label>
							<input type="hidden" id="serviceTypeCodeDesc0" name="serviceTypeCodeDesc" value="${servicetypeMappingsList[0].serviceTypeCodeDesc}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="eb11Val" class="inputbox60" id="eb11Id0" value="${servicetypeMappingsList[0].eb11}"/>
						</td>
						<td style="width: 140px">
							<label id="eb11IdLabel0" for="eb11Id0" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[0].eb11Desc}" />
							</label>
							<input type="hidden" id="eb11Desc0" name="eb11Desc" value="${servicetypeMappingsList[0].eb11Desc}"/>
							
						</td>	
						<td width="120px"></td>
						<td style="width: 95px">
		              		<input type="text" name="placeOfServiceVal"  class="inputbox60" id="placeOfServiceId0" value="${servicetypeMappingsList[0].placeOfService}"/>
						</td>
	                    <td style="width: 140px">
							<label id="placeOfServiceIdLabel0" for="placeOfServiceId0" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[0].placeOfServiceDesc}" />
							</label>
								<input type="hidden" id="placeOfServiceDesc0" name="placeOfServiceDesc" value="${servicetypeMappingsList[0].placeOfServiceDesc}"/>
						</td> 
						<td width="120px"></td>           
	                  </tr>

	              
	              
	              
	              	<tr class="createEditTable1-Listdata">												                
	                    <td style="width: 95px">
							<input type="text" name="serviceTypeCodeVal" class="inputbox60" id="serviceTypeCodeId1" value="${servicetypeMappingsList[1].serviceTypeCode}" />
						</td>
	                    <td style="width: 140px">
							<label id="serviceTypeCodeIdLabel1" for="serviceTypeCodeId1" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[1].serviceTypeCodeDesc}" />
							</label>
							<input type="hidden" id="serviceTypeCodeDesc1" name="serviceTypeCodeDesc" value="${servicetypeMappingsList[1].serviceTypeCodeDesc}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="eb11Val" class="inputbox60" id="eb11Id1" value="${servicetypeMappingsList[1].eb11}"/>
						</td>
						<td style="width: 140px">
							<label id="eb11IdLabel1" for="eb11Id1" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[1].eb11Desc}" />
							</label>
							<input type="hidden" id="eb11Desc1" name="eb11Desc" value="${servicetypeMappingsList[1].eb11Desc}"/>
						</td>	
						<td width="120px"></td>
						<td style="width: 95px">
		              		<input type="text" name="placeOfServiceVal"  class="inputbox60" id="placeOfServiceId1" value="${servicetypeMappingsList[1].placeOfService}"/>
						</td>
	                    <td style="width: 140px">
							<label id="placeOfServiceIdLabel1" for="placeOfServiceId1" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[1].placeOfServiceDesc}" />
							</label>
								<input type="hidden" id="placeOfServiceDesc1" name="placeOfServiceDesc" value="${servicetypeMappingsList[1].placeOfServiceDesc}"/>
						</td> 
						<td width="120px"></td>           
	                  </tr>
	                  
	                  
	                  <tr class="createEditTable1-Listdata alternate">												                
	                    <td style="width: 95px">
							<input type="text" name="serviceTypeCodeVal" class="inputbox60" id="serviceTypeCodeId2" value="${servicetypeMappingsList[2].serviceTypeCode}" />
						</td>
	                    <td style="width: 140px">
							<label id="serviceTypeCodeIdLabel2" for="serviceTypeCodeId2" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[2].serviceTypeCodeDesc}" />
							</label>
							<input type="hidden" id="serviceTypeCodeDesc2" name="serviceTypeCodeDesc" value="${servicetypeMappingsList[2].serviceTypeCodeDesc}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="eb11Val" class="inputbox60" id="eb11Id2" value="${servicetypeMappingsList[2].eb11}"/>
						</td>
						<td style="width: 140px">
							<label id="eb11IdLabel2" for="eb11Id2" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[2].eb11Desc}" />
							</label>
							<input type="hidden" id="eb11Desc2" name="eb11Desc" value="${servicetypeMappingsList[2].eb11Desc}"/>
						</td>	
						<td width="120px"></td>
						<td style="width: 95px">
		              		<input type="text" name="placeOfServiceVal"  class="inputbox60" id="placeOfServiceId2" value="${servicetypeMappingsList[2].placeOfService}"/>
						</td>
	                    <td style="width: 140px">
							<label id="placeOfServiceIdLabel2" for="placeOfServiceId2" style="font-size:11px">
									<c:out value="${servicetypeMappingsList[2].placeOfServiceDesc}" />
							</label>
								<input type="hidden" id="placeOfServiceDesc2" name="placeOfServiceDesc" value="${servicetypeMappingsList[2].placeOfServiceDesc}"/>
						</td> 
						<td width="120px"></td>           
	                  </tr>
	                  
	                  
	                  
			<c:if test="${fn:length(servicetypeMappingsList) > 2}">
							<c:set var="count" value="3"/>
						<c:forEach items="${servicetypeMappingsList}"	var="servicetypeMappingsListValues" begin="3">
							<script type="text/javascript">
								insertServiceTypeMappingsRow('serviceTypeMappingsTable',true,'serviceTypeCodeId${count}','serviceTypeCodeVal','${servicetypeMappingsListValues.serviceTypeCode}','serviceTypeCodeIdLabel${count}','${servicetypeMappingsListValues.serviceTypeCodeDesc}','serviceTypeCodeDesc${count}',
																'eb11Id${count}','eb11Val','${servicetypeMappingsListValues.eb11}','eb11IdLabel${count}','${servicetypeMappingsListValues.eb11Desc}','eb11Desc${count}',
																'placeOfServiceId${count}','placeOfServiceVal','${servicetypeMappingsListValues.placeOfService}','placeOfServiceIdLabel${count}','${servicetypeMappingsListValues.placeOfServiceDesc}','placeOfServiceDesc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</c:if>
					</tbody>
					</table>
					<div>
						<table border="0">
							<tr>								
								<td width="90px"><a href="#" onclick="addRowForServiceTypeMappings('serviceTypeMappingsTable',true,'serviceTypeCodeId','serviceTypeCodeVal','','','eb11Id','eb11Val','','','placeOfServiceId','placeOfServiceVal','','');"><img id="serviceTypeMappingsAddButton" src="${imageDir}/add.gif" width="19" height="19" /></a></td>
							</tr>
							<tr></tr>
						</table>
					</div>
					

				
				</div><!--createEditContainer Ends-->		
