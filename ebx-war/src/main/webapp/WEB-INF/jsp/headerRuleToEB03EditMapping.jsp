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
	                  	
	                    <td  class="headTextExclusions" width="40" >EB03 :
	                    </td>	
	                     <td class="headTextExclusions" width="135">
	                     	<c:if test="${! empty eb03Id }" >
								<c:out value="${eb03Id}" />
							</c:if> &#160;
	                    </td>	    
	                        <td  class="headTextExclusions" width="70" > Description :
	                    </td>	
	                     <td class="headTextExclusions" width="290">
	                     	<c:if test="${! empty eb03Description }" >
								<c:out value="${eb03Description}" />
							</c:if> &#160;
	                    </td>	                
	                  </tr>
	                  </table>
	  <div id="createEditContainer1" style="margin-top:10px; height: 170px"">	<!--createEditContainer Starts-->			
				<!--First Table-->
				<input type="hidden" id="pageName" value="headerRuleMappingPage"/>
			<input type="hidden" name="eb03IdForPage" id="eb03IdForPage" value="${eb03Id}"/>
			<input type="hidden" name="eb03DescriptionForPage" id="eb03DescriptionForPage" value="${eb03Description}"/>
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" >	
					

					<tr class="createEditTable1-Listdata">					
	                    <td class="headText" width="134">Header Rule &#160;
	                    <a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB03" onclick="popupDivReferanceData('RULEID');"/></a></td>	                    
	                  </tr>
					</table>
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="headerRuletable">
					<tbody id="headerRulebody">
						<tr class="createEditTable1-Listdata alternate">	
		                    <td style="width: 95px">
								<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId0" value="${headerRuleList[0].headerRuleValue}" alt="${headerRuleList[0].headerRuleDescription}" title="${headerRuleList[0].headerRuleDescription}" />
								<input type="hidden" id="headerRuleDesc0" name="headerRuleDesc" value="${headerRuleList[0].headerRuleDescription}"/>
							</td>
							<td width="120px"></td>
							<td style="width: 95px">
								<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId1" value="${headerRuleList[1].headerRuleValue}" alt="${headerRuleList[1].headerRuleDescription}" title="${headerRuleList[1].headerRuleDescription}"/>
								<input type="hidden" id="headerRuleDesc1" name="headerRuleDesc" value="${headerRuleList[1].headerRuleDescription}"/>							
							</td>
							<td width="120px"></td>
							<td style="width: 95px">
								<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId2" value="${headerRuleList[2].headerRuleValue}" alt="${headerRuleList[2].headerRuleDescription}" title="${headerRuleList[2].headerRuleDescription}"/>
								<input type="hidden" id="headerRuleDesc2" name="headerRuleDesc" value="${headerRuleList[2].headerRuleDescription}"/>
							</td>
							<td width="120px"></td> 
							<td style="width: 95px">
								<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId3" value="${headerRuleList[3].headerRuleValue}" alt="${headerRuleList[3].headerRuleDescription}" title="${headerRuleList[3].headerRuleDescription}"/>
								<input type="hidden" id="headerRuleDesc3" name="headerRuleDesc" value="${headerRuleList[3].headerRuleDescription}"/>
							</td>
							<td width="120px"></td>
							<td style="width: 95px">
								<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId4" value="${headerRuleList[4].headerRuleValue}" alt="${headerRuleList[4].headerRuleDescription}" title="${headerRuleList[4].headerRuleDescription}"/>
								<input type="hidden" id="headerRuleDesc4" name="headerRuleDesc" value="${headerRuleList[4].headerRuleDescription}"/>
							</td>
							<td width="120px"></td>
							<td style="width: 95px">
								<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId5" value="${headerRuleList[5].headerRuleValue}" alt="${headerRuleList[5].headerRuleDescription}" title="${headerRuleList[5].headerRuleDescription}"/>
								<input type="hidden" id="headerRuleDesc5" name="headerRuleDesc" value="${headerRuleList[5].headerRuleDescription}"/>
							</td>
							<td width="120px"></td>
	                  </tr>
						<tr class="createEditTable1-Listdata">
	                    <td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId6" value="${headerRuleList[6].headerRuleValue}" alt="${headerRuleList[6].headerRuleDescription}" title="${headerRuleList[6].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc6" name="headerRuleDesc" value="${headerRuleList[6].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId7" value="${headerRuleList[7].headerRuleValue}" alt="${headerRuleList[7].headerRuleDescription}" title="${headerRuleList[7].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc7" name="headerRuleDesc" value="${headerRuleList[7].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId8" value="${headerRuleList[8].headerRuleValue}" alt="${headerRuleList[8].headerRuleDescription}" title="${headerRuleList[8].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc8" name="headerRuleDesc" value="${headerRuleList[8].headerRuleDescription}"/>
						</td>
						<td width="120px"></td> 
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId9" value="${headerRuleList[9].headerRuleValue}" alt="${headerRuleList[9].headerRuleDescription}" title="${headerRuleList[9].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc9" name="headerRuleDesc" value="${headerRuleList[9].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId10" value="${headerRuleList[10].headerRuleValue}" alt="${headerRuleList[10].headerRuleDescription}" title="${headerRuleList[10].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc10" name="headerRuleDesc" value="${headerRuleList[10].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId11" value="${headerRuleList[11].headerRuleValue}" alt="${headerRuleList[11].headerRuleDescription}" title="${headerRuleList[11].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc11" name="headerRuleDesc" value="${headerRuleList[11].headerRuleDescription}"/>
						</td>
						<td width="60px"></td>
	                  </tr>
	                  <tr class="createEditTable1-Listdata alternate">	
	                    <td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId12" value="${headerRuleList[12].headerRuleValue}" alt="${headerRuleList[12].headerRuleDescription}" title="${headerRuleList[12].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc12" name="headerRuleDesc" value="${headerRuleList[12].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId13" value="${headerRuleList[13].headerRuleValue}" alt="${headerRuleList[13].headerRuleDescription}" title="${headerRuleList[13].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc13" name="headerRuleDesc" value="${headerRuleList[13].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId14" value="${headerRuleList[14].headerRuleValue}" alt="${headerRuleList[14].headerRuleDescription}" title="${headerRuleList[14].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc14" name="headerRuleDesc" value="${headerRuleList[14].headerRuleDescription}"/>
						</td>
						<td width="120px"></td> 
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId15" value="${headerRuleList[15].headerRuleValue}" alt="${headerRuleList[15].headerRuleDescription}" title="${headerRuleList[15].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc15" name="headerRuleDesc" value="${headerRuleList[15].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId16" value="${headerRuleList[16].headerRuleValue}" alt="${headerRuleList[16].headerRuleDescription}" title="${headerRuleList[16].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc16" name="headerRuleDesc" value="${headerRuleList[16].headerRuleDescription}"/>
						</td>
						<td width="120px"></td>
						<td style="width: 95px">
							<input type="text" name="headerRuleVal" class="inputbox60" id="ruleId17" value="${headerRuleList[17].headerRuleValue}" alt="${headerRuleList[17].headerRuleDescription}" title="${headerRuleList[17].headerRuleDescription}"/>
							<input type="hidden" id="headerRuleDesc17" name="headerRuleDesc" value="${headerRuleList[17].headerRuleDescription}"/>
						</td>
						<td width="60px"></td>
	                  </tr>
	              
			<c:if test="${fn:length(headerRuleList) > 18}">
							<c:set var="count" value="18"/>
						<c:forEach items="${headerRuleList}"var="hValue" begin="18">
							<script type="text/javascript">
								insertHeaderRule('headerRuletable','ruleId${count}','headerRuleVal','${hValue.headerRuleValue}',true,'${hValue.headerRuleDescription}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</c:if>
					</tbody>
					</table>
					<div>
						<table border="0">
							<tr>								
								<td width="90px"><a href="#"><img id="headerRuleAddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForHeaderRule('headerRuletable','ruleId','headerRuleVal','',true,'');"/></a></td>
							</tr>
							<tr></tr>
						</table>
					</div>
					

				
				</div><!--createEditContainer Ends-->	
			
