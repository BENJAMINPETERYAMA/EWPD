	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<!-- 

<script type="text/javascript" src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hippaSegmentPopUp.js"></script>

 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>


<script type="text/javascript" >


</script>

		 <table width="800" border="0" cellpadding="0" cellspacing="0" class="Pd3" > 
			<tr class="createEditTable1-Listdata">					
	                  	
	                    <td  class="headTextExclusions" width="60" >EB01 :
	                    </td>	
	                     <td class="headTextExclusions" width="70">
	                     	<c:if test="${! empty eb01 }" >
								<c:out value="${eb01}" />
							</c:if> &#160;
	                    </td>	   
	                        <td  class="headTextExclusions" width="80" >Description :
	                    </td>	
	                     <td class="headTextExclusions" width="400">
	                     	<c:if test="${! empty eb01Desc }" >
								<c:out value="${eb01Desc}" />
							</c:if> &#160;
	                    </td>	                
	                  </tr>
	                  </table>
	  <div id="createEditContainer1" style="margin-top:10px; height: 200px">	<!--createEditContainer Starts-->			
				<!--First Table-->
				<input type="hidden" id="pageName" value="headerRuleMappingPage"/>
				<input type="hidden" name="eb01IdFromPage" id="eb01IdFromPage" value="${eb01}"/>
				<input type="hidden" name="eb01DescriptionFromPage" id="eb01DescriptionFromPage" value="${eb01Desc}"/>

					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" >	
					

					<tr class="createEditTable1-Listdata">					
	                    <td class="headText" width="134">Data Type &#160;
	                    <a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="dataType" onclick="popupDivReferanceData('DATATYPE');"/></a></td>	                    
	                  </tr>
					</table>
					
			<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="dataTypeTable">
					<tbody id="dataTypeTbody">
						<tr class="createEditTable1-Listdata alternate">												                
	                    <td style="width: 95px">
							<input type="text" name="dataTypeVal" class="inputbox60" id="dataTypeId0" value="${dataTypeVOEdit[0].dataTypeValue}" />
						</td>
	                    <td style="width: 140px">
							<label id="dataTypeIdLabel0" for="dataTypeId0" style="font-size:11px">
									<c:out value="${dataTypeVOEdit[0].dataTypeDescription}" />
							</label>
							<input type="hidden" id="dataTypeDesc0" name="dataTypeDesc" value="${dataTypeVOEdit[0].dataTypeDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="dataTypeVal" class="inputbox60" id="dataTypeId1" value="${dataTypeVOEdit[1].dataTypeValue}"/>
						</td>
						<td style="width: 140px">
							<label id="dataTypeIdLabel1" for="dataTypeId1" style="font-size:11px">
									<c:out value="${dataTypeVOEdit[1].dataTypeDescription}" />
							</label>
							<input type="hidden" id="dataTypeDesc1" name="dataTypeDesc" value="${dataTypeVOEdit[1].dataTypeDescription}"/>
						</td>	
						<td width="120px"></td>
						<td style="width: 95px">
		              		<input type="text" name="dataTypeVal"  class="inputbox60" id="dataTypeId2" value="${dataTypeVOEdit[2].dataTypeValue}"/>
						</td>
	                    <td style="width: 140px">
							<label id="dataTypeIdLabel2" for="dataTypeId2" style="font-size:11px">
									<c:out value="${dataTypeVOEdit[2].dataTypeDescription}" />
							</label>
								<input type="hidden" id="dataTypeDesc2" name="dataTypeDesc" value="${dataTypeVOEdit[2].dataTypeDescription}"/>
						</td> 
						<td width="120px"></td>           
	                  </tr>
					 <tr class="createEditTable1-Listdata">
						<td style="width: 95px">
							<input type="text" name="dataTypeVal" class="inputbox60" id="dataTypeId3" value="${dataTypeVOEdit[3].dataTypeValue}"/>
						</td>
	                    <td style="width: 140px">
							<label id="dataTypeIdLabel3" for="dataTypeId3" style="font-size:11px">
									<c:out value="${dataTypeVOEdit[3].dataTypeDescription}" />
							</label>
								<input type="hidden" id="dataTypeDesc3" name="dataTypeDesc" value="${dataTypeVOEdit[3].dataTypeDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="dataTypeVal" class="inputbox60" id="dataTypeId4" value="${dataTypeVOEdit[4].dataTypeValue}"/>
						</td>
						<td style="width: 140px">
							<label id="dataTypeIdLabel4" for="dataTypeId4" style="font-size:11px">
									<c:out value="${dataTypeVOEdit[4].dataTypeDescription}" />
							</label>
							<input type="hidden" id="dataTypeDesc4" name="dataTypeDesc" value="${dataTypeVOEdit[4].dataTypeDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="dataTypeVal" class="inputbox60" id="dataTypeId5" value="${dataTypeVOEdit[5].dataTypeValue}"/>
						</td>
						<td style="width: 140px">
							<label id="dataTypeIdLabel5" for="dataTypeId5" style="font-size:11px">
									<c:out value="${dataTypeVOEdit[5].dataTypeDescription}" />
							</label>
							<input type="hidden" id="dataTypeDesc5" name="dataTypeDesc" value="${dataTypeVOEdit[6].dataTypeDescription}"/>
						</td>
						<td width="120px"></td>
	              	</tr>
	              
			<c:if test="${fn:length(dataTypeVOEdit) > 6}">
							<c:set var="count" value="6"/>
						<c:forEach items="${dataTypeVOEdit}"	var="dataTypeValues" begin="6">
							<script type="text/javascript">
								insertDataType('dataTypeTable','dataTypeId${count}','dataTypeVal','${dataTypeValues.dataTypeValue}',true,'dataTypeIdLabel${count}','${dataTypeValues.dataTypeDescription}','dataTypeDesc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</c:if>
					</tbody>
					</table>
					<div>
						<table border="0">
							<tr>								
								<td width="90px"><a href="#"><img id="dataTypeAddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowDataTypeForEB01('dataTypeTable','dataTypeId','dataTypeVal','',true,'');"/></a></td>
							</tr>
							<tr></tr>
						</table>
					</div>
					

				
				</div><!--createEditContainer Ends-->		
