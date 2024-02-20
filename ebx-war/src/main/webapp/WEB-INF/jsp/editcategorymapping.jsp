	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hippaSegmentPopUp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		 <table width="800" border="0" cellpadding="0" cellspacing="0" class="Pd3" > 
			<tr class="createEditTable1-Listdata">					
	                  	
	                    <td  class="headTextExclusions" width="90" >Category Code :
	                    </td>	
	                     <td class="headTextExclusions" width="135">
	                     	<c:if test="${! empty categorysave }" >
								<c:out value="${categorysave}" />
							</c:if> &#160;
	                    </td>	   
	                        <td  class="headTextExclusions" width="130" >Category Description :
	                    </td>	
	                     <td class="headTextExclusions" width="290">
	                     	<c:if test="${! empty categoryDescription }" >
								<c:out value="${categoryDescription}" />
							</c:if> &#160;
	                    </td>	                
	                  </tr>
	                  </table>
	  <div id="createEditContainer1" style="margin-top:10px;">	<!--createEditContainer Starts-->			
				<!--First Table-->
				<input type="hidden" id="pageName" value="headerRuleMappingPage"/>
			<input type="hidden" name="serviceTypesave" id="serviceTypesave" value="${serviceTypesave}"/>
			<input type="hidden" name="categoryDescription" id="categoryDescription" value="${categoryDescription}"/>
			<input type="hidden" name="categorysave" id="categorysave" value="${categorysave}"/>
			<input type="hidden" name="systemsave" id="systemsave" value="${systemsave}"/>
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" >	
					

					<tr class="createEditTable1-Listdata">					
	                    <td class="headText" width="134">EB03 <a href="#"  onclick="displayInfo('EB03',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
	                    <a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB03" onclick="popupDivHippaSegment('EB03');"/></a></td>	                    
	                  </tr>
					</table>
					
										<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="ebCodesTable">
					<tbody id="ebCodeTbody">
						<tr class="createEditTable1-Listdata alternate">												                
	                    <td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id0" value="${mapping.eb03.hippaCodeSelectedValues[0].value}" />
						</td>
	                    <td style="width: 140px">
							<label id="EB03IdLabel0" for="EB03Id0" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}" />
							</label>
							<input type="hidden" id="EB03Desc0" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id1" value="${mapping.eb03.hippaCodeSelectedValues[1].value}"/>
						</td>
						<td style="width: 140px">
							<label id="EB03IdLabel1" for="EB03Id1" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[1].description}" />
							</label>
							<input type="hidden" id="EB03Desc1" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[1].description}"/>
						</td>	
						<td width="120px"></td>
						<td style="width: 95px">
		              		<input type="text" name="eb03Val"  class="inputbox60" id="EB03Id2" value="${mapping.eb03.hippaCodeSelectedValues[2].value}"/>
						</td>
	                    <td style="width: 140px">
							<label id="EB03IdLabel2" for="EB03Id2" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[2].description}" />
							</label>
								<input type="hidden" id="EB03Desc2" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[2].description}"/>
						</td> 
						<td width="120px"></td>           
	                  </tr>
					 <tr class="createEditTable1-Listdata">
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id3" value="${mapping.eb03.hippaCodeSelectedValues[3].value}"/>
						</td>
	                    <td style="width: 140px">
							<label id="EB03IdLabel3" for="EB03Id3" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[3].description}" />
							</label>
								<input type="hidden" id="EB03Desc3" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[3].description}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id4" value="${mapping.eb03.hippaCodeSelectedValues[4].value}"/>
						</td>
						<td style="width: 140px">
							<label id="EB03IdLabel4" for="EB03Id4" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[4].description}" />
							</label>
							<input type="hidden" id="EB03Desc4" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[4].description}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="eb03Val" class="inputbox60" id="EB03Id5" value="${mapping.eb03.hippaCodeSelectedValues[5].value}"/>
						</td>
						<td style="width: 140px">
							<label id="EB03IdLabel5" for="EB03Id5" style="font-size:11px">
									<c:out value="${mapping.eb03.hippaCodeSelectedValues[5].description}" />
							</label>
							<input type="hidden" id="EB03Desc5" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[5].description}"/>
						</td>
						<td width="120px"></td>
	              	</tr>
	              
			<c:if test="${fn:length(mapping.eb03.hippaCodeSelectedValues) > 6}">
							<c:set var="count" value="6"/>
						<c:forEach items="${mapping.eb03.hippaCodeSelectedValues}"	var="eb03Values" begin="6">
							<script type="text/javascript">
								insertEB03('ebCodesTable','EB03Id${count}','eb03Val','${eb03Values.value}',true,'EB03IdLabel${count}','${eb03Values.description}','EB03Desc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</c:if>
					</tbody>
					</table>
					<div>
						<table border="0">
							<tr>								
								<td width="90px"><a href="#"><img id="eb03AddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowEB03ForRule('ebCodesTable','EB03Id','eb03Val','',true,'');"/></a></td>
							</tr>
							<tr></tr>
						</table>
					</div>
					

				
				</div><!--createEditContainer Ends-->		