<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body class="normal">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>

<form name="viewForm"  method = "post">
<div id="sequenceViewDiv" style="height:60px;">
	<input id="titleName" type="hidden" value="${title}"/>
	<table border="0" cellpadding="0" cellspacing="0" width="100%" heigth="100%" class="Pd3">
	<THEAD>
		<tr class="createEditTable1">
				<td width="90px" class="tableHeader">Rule ID</td>
				<td class="tableHeader">Description</td>
				<td class="tableHeader">Long Description</td>
				<td class="tableHeader">Rule Version</td>
				<td class="tableHeader">Tag</td>			
		</tr>
	</THEAD>
	<TBODY>
		<c:if test="${not empty info_messages}">
			<tr>
				<td nowrap colspan="5" style="vertical-align: middle;text-align:center;">
					<c:forEach items="${info_messages}" var="info_message">
			   			  <p ><b><font color='blue'> # <c:out value="${info_message}" /></font></b></p>
			        </c:forEach> 
				</td>
			</tr>
		</c:if>
		<tr>
			<td>${ruleInfo.hippaSegment.name}</td>
			<td style ="word-wrap: break-word; WORD-BREAK:BREAK-ALL;width:220px">${ruleInfo.hippaSegment.description}</td>
			<td>${ruleInfo.longDescription}</td>
			<td>${ruleInfo.ruleVerNmbr}</td>
			<td>${ruleInfo.tag}</td>
		</tr>	
	</TBODY>
	</table>
</div>
<br />
<br />
<c:if test="${! empty ruleViewSequenceList}">
<c:set var="rulesequence" value="" />
	<div id="sequenceViewDiv" wi>
		<c:forEach items="${ruleViewSequenceList}" var="sequenceMapObj" begin ="0">
			<c:set var="rowCount" value="0" />
			
			<table cellpadding="0" cellspacing="0" width="100%" class="Pd3 ruleIndicatorTable" id="ruleIndicatorTable">
				<c:forEach items="${sequenceMapObj}" var="sequenceMap" begin ="0"  varStatus="status">
					<c:if test="${status.index == 0}">
						<tr  class="createEditTableShade" >
							<td colspan="6">
								${sequenceMap.key}&nbsp;:&nbsp;${sequenceMap.value}
							    <c:set var="rulesequence" value="${sequenceMap.value}" />
							</td>
						</tr>
					</c:if>
					<c:if test="${status.index == 1}">
						<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
							<c:set var="rowCountIncrementFlag" value="true"/>
							
							<td class="styleForTd135">${sequenceMap.key}&nbsp;</td>
							<td class="styleForTd105">${sequenceMap.value}</td>
					</c:if>
					
					<c:if test="${status.index == 2}">
							<td class="styleForTd135">${sequenceMap.key}&nbsp;</td>		
							<td class="styleForTd105">${sequenceMap.value}</td>		
					</c:if>
					<c:if test="${status.index == 3}">
							<td class="styleForTd135">${sequenceMap.key}&nbsp;</td>
							<td class="styleForTd105">${sequenceMap.value}</td>
							</tr>
							<c:set var="rowCount" value="${rowCount + 1}"/>
							<c:set var="rowCountIncrementFlag" value="false"/>
					</c:if>
					
	
	
					<c:if test="${status.index == 4}">
						<c:set var="rowCountIncrementFlag" value="true"/>
						<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
							<td>${sequenceMap.key}&nbsp;</td>
							<td>${sequenceMap.value}</td>
					</c:if>
					<c:if test="${status.index == 5}">
							<td style="white-space:nowrap;">${sequenceMap.key}&nbsp;</td>		
							<td>${sequenceMap.value}</td>		
					</c:if>
					<c:if test="${status.index == 6}">	
							<td>${sequenceMap.key}&nbsp;</td>
							<td>${sequenceMap.value}</td>
							</tr>
							<c:set var="rowCount" value="${rowCount + 1}"/>
							<c:set var="rowCountIncrementFlag" value="false"/>
					</c:if>
					
					<c:if test="${status.index == 7}">
						<c:set var="rowCountIncrementFlag" value="true"/>
						<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
							<td>${sequenceMap.key}&nbsp;</td>
							<td>${sequenceMap.value}</td>
					</c:if>
					<c:if test="${status.index == 8}">
							<td>${sequenceMap.key}&nbsp;</td>		
							<td>${sequenceMap.value}</td>		
					</c:if>
					<c:if test="${status.index == 9}">	
							<td>${sequenceMap.key}&nbsp;</td>
							<td>${sequenceMap.value}</td>
							</tr>
							<c:set var="rowCount" value="${rowCount + 1}"/>
							<c:set var="rowCountIncrementFlag" value="false"/>
					</c:if>
					
					<c:if test="${status.index == 10}">
						<c:set var="rowCountIncrementFlag" value="true"/>
						<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
							<td>${sequenceMap.key}&nbsp;</td>
							<td>${sequenceMap.value}</td>
					</c:if>
					<c:if test="${status.index == 11}">
							<td>${sequenceMap.key}&nbsp;</td>		
							<td>${sequenceMap.value}</td>		
					</c:if>
					<c:if test="${status.index == 12}">	
							<td>${sequenceMap.key}&nbsp;</td>
							<td>${sequenceMap.value}</td>
							</tr>
							<c:set var="rowCount" value="${rowCount + 1}"/>
							<c:set var="rowCountIncrementFlag" value="false"/>
					</c:if>
					
					<c:if test="${status.last}">
						<c:if test="${rowCountIncrementFlag}">
							<c:set var="rowCount" value="${rowCount + 1}"/>
							
						</c:if>
						<c:set var="canGenerateColumnsUpto" value="${rowCount * 6}"/>
						<c:set var="count" value="${status.count - 1}"/>
						<c:set var="currentColumnCount" value="${count*2}"/>
						<c:set var="diffInColumnCount" value="${canGenerateColumnsUpto - currentColumnCount}"/>
					
						<c:if test="${diffInColumnCount != 0}">
							<c:forEach  step="1" begin="0" end="${diffInColumnCount-1}">
								<td class="" style="">&nbsp;</td>	
							</c:forEach>
						</tr>
						</c:if>
					</c:if>
					
				</c:forEach>
			</table>
			
			<c:if test="${! empty ruleViewCodeSequenceList}">
			<c:set var="lineCount" value="-1" />
				<div id="sequenceCodeViewDiv" >
	<c:set var="codeRulesequence" value="" />
		<c:forEach items="${ruleViewCodeSequenceList}" var="sequenceCodeMapObj" begin ="0">
			<c:set var="rowCount1" value="0" />
			<c:set var="lineCount" value="${lineCount + 1}"/>	
			<table cellpadding="0" cellspacing="0" class="Pd3 ruleIndicatorTable" id="ruleCodeIndicatorTable">
				<c:forEach items="${sequenceCodeMapObj}" var="sequenceCodeMap" begin ="0"  varStatus="codeStatus">
				
					<c:if test="${(codeStatus.index == 0)}"> 
					<c:set var="codeRulesequence" value="${sequenceCodeMap.value}" />
					</c:if>	
					<c:if test="${(rulesequence != codeRulesequence)}"> 
					<c:set var="lineCount" value="-1" />
					</c:if>	
					 
					<c:if test="${(rulesequence == codeRulesequence)}"> 
					<c:if test="${(codeStatus.index == 1)  && (lineCount == 0)}">
					<tr  class="createEditTableShade" >
							<td colspan="7">
								&nbsp;&nbsp;&nbsp;${sequenceCodeMap.key}
							</td>
							</tr>
                    </c:if>
                    
					<c:if test="${codeStatus.index == 1}">
						<tr class="${lineCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
							<c:set var="rowCountIncrementFlag1" value="true"/>
							<td  width="25" ><b>&nbsp;&nbsp;&nbsp;${sequenceCodeMap.value}&nbsp;</b></td>  
									
					</c:if>
					<c:if test="${codeStatus.index == 2}">
							<td class="styleForTd105">${sequenceCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${codeStatus.index == 3}">
							<td class="styleForTd105">${sequenceCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${codeStatus.index == 4}">
							<td class="styleForTd105">${sequenceCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${codeStatus.index == 5}">
							<td class="styleForTd105">${sequenceCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${codeStatus.index == 6}">
							<td class="styleForTd105">${sequenceCodeMap.key}&nbsp;</td>		
					</c:if>
					
					<c:if test="${codeStatus.index == 7}">
							<td class="styleForTd105">${sequenceCodeMap.key}&nbsp;</td>
							</tr>
							<c:set var="rowCount1" value="${rowCount1 + 1}"/>
							<c:set var="rowCountIncrementFlag1" value="false"/>
					</c:if>
					<c:if test="${codeStatus.last}">
						<c:if test="${rowCountIncrementFlag1}">
							<c:set var="rowCount1" value="${rowCount1 + 1}"/>
											</c:if>
						<c:set var="canGenerateColumnsUpto1" value="${rowCount1 * 7}"/>
						<c:set var="count1" value="${codeStatus.count - 1}"/>
						<c:set var="currentColumnCount1" value="${count1}"/>
						<c:set var="diffInColumnCount1" value="${canGenerateColumnsUpto1 - currentColumnCount1}"/>
					<c:if test="${diffInColumnCount1 > 0}">
							<c:forEach  step="1" begin="0" end="${diffInColumnCount1-1}">
								<td class="styleForTd105" style="">&nbsp;</td>	
							</c:forEach>
						</tr>
						</c:if>
						
					</c:if>
					</c:if>	
                
		</c:forEach>
			</table>
			</c:forEach>   
	</div>
</c:if>	
		
			<c:if test="${! empty ruleViewClaimLevelSequenceList}">
			<c:set var="claimCount" value="0" />
	<div id="claimLevelCodeViewDiv" >
	<c:set var="claimLevelRulesequence" value="" />
	<c:set var="claimLevelsequence" value="" />
		<c:forEach items="${ruleViewClaimLevelSequenceList}" var="claimLevelCodeMapObj" begin ="0">
			<c:set var="rowCount2" value="0" />
		
			<c:set var="claimCount" value="${claimCount + 1}"/>	
			<table cellpadding="0" cellspacing="0" class="Pd3 ruleIndicatorTable" id="claimLevelCodeIndicatorTable">
				<c:forEach items="${claimLevelCodeMapObj}" var="sequenceclaimLevelMap" begin ="0"  varStatus="claimLevelStatus">
				
					<c:if test="${(claimLevelStatus.index == 0)}"> 
					<c:set var="claimLevelRulesequence" value="${sequenceclaimLevelMap.value}" />
					</c:if>	
					<c:if test="${(claimLevelStatus.index == 1)}"> 
					<c:set var="claimLevelsequence" value="${sequenceclaimLevelMap.value}" />
					</c:if>
					<c:if test="${(rulesequence != claimLevelRulesequence)}"> 
					<c:set var="claimCount" value="0" />
					</c:if>
					<c:if test="${(rulesequence == claimLevelRulesequence)}"> 
					<c:if test="${(claimLevelStatus.index == 1) && (claimCount == 1) }">
					
					<tr  class="createEditTableShade" >
							<td colspan="7">
								&nbsp;&nbsp;&nbsp;${sequenceclaimLevelMap.key}
							</td>
						</tr>
					</c:if>
					<c:if test="${claimLevelStatus.index == 1}">
						<tr class="${rowCount2 mod 2 == 0 ? 'white-bg' : 'alternate'}">
							<c:set var="rowCountIncrementFlag2" value="true"/>
							<td  width="25" ><b>&nbsp;&nbsp;&nbsp;${sequenceclaimLevelMap.value}&nbsp;</b></td>  
						    
					</c:if>
					<c:if test="${claimLevelStatus.index == 2}">
							<td class="styleForTd135">${sequenceclaimLevelMap.key}&nbsp;</td>		
							<td class="styleForTd105">${sequenceclaimLevelMap.value}</td>		
					</c:if>
					<c:if test="${claimLevelStatus.index == 3}">
							<td class="styleForTd135">${sequenceclaimLevelMap.key}&nbsp;</td>		
							<td class="styleForTd105">${sequenceclaimLevelMap.value}</td>		
					</c:if>
					<c:if test="${claimLevelStatus.index == 4}">
							<td class="styleForTd135">${sequenceclaimLevelMap.key}&nbsp;</td>
							<td class="styleForTd105">${sequenceclaimLevelMap.value}</td>
							</tr>
							<c:set var="rowCount2" value="${rowCount2 + 1}"/>
							<c:set var="rowCountIncrementFlag2" value="false"/>
					</c:if>
					
	
	
					<c:if test="${claimLevelStatus.index == 5}">
						<c:set var="rowCountIncrementFlag2" value="true"/>
						<tr class="${rowCount2 mod 2 == 0 ? 'white-bg' : 'alternate'}">
						<td  width="25" >&nbsp;&nbsp;&nbsp;${sequenceCodeMap.value}&nbsp;</td>  
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>
							<td>${sequenceclaimLevelMap.value}</td>
					</c:if>
					<c:if test="${claimLevelStatus.index == 6}">
							<td style="white-space:nowrap;">${sequenceclaimLevelMap.key}&nbsp;</td>		
							<td>${sequenceclaimLevelMap.value}</td>		
					</c:if>
					<c:if test="${claimLevelStatus.index == 7}">	
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>
							<td>${sequenceclaimLevelMap.value}</td>
							</tr>
							<c:set var="rowCount2" value="${rowCount2 + 1}"/>
							<c:set var="rowCountIncrementFlag2" value="false"/>
					</c:if>
					
					<c:if test="${claimLevelStatus.index == 8}">
						<c:set var="rowCountIncrementFlag2" value="true"/>
						<tr class="${rowCount2 mod 2 == 0 ? 'white-bg' : 'alternate'}">
						<td  width="25" >&nbsp;&nbsp;&nbsp;${sequenceCodeMap.value}&nbsp;</td>  
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>
							<td>${sequenceclaimLevelMap.value}</td>
					</c:if>
					<c:if test="${claimLevelStatus.index == 9}">
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>		
							<td>${sequenceclaimLevelMap.value}</td>		
					</c:if>
					<c:if test="${claimLevelStatus.index == 10}">	
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>
							<td>${sequenceclaimLevelMap.value}</td>
							</tr>
							<c:set var="rowCount2" value="${rowCount + 1}"/>
							<c:set var="rowCountIncrementFlag2" value="false"/>
					</c:if>
					
					
					
					<c:if test="${claimLevelStatus.index == 11}">
						<c:set var="rowCountIncrementFlag2" value="true"/>
						<tr class="${rowCount2 mod 2 == 0 ? 'white-bg' : 'alternate'}">
						<td  width="25" >&nbsp;&nbsp;&nbsp;${sequenceCodeMap.value}&nbsp;</td>  
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>
							<td>${sequenceclaimLevelMap.value}</td>
					</c:if>
					<c:if test="${claimLevelStatus.index == 12}">
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>		
							<td>${sequenceclaimLevelMap.value}</td>		
					</c:if>
					<c:if test="${claimLevelStatus.index == 13}">	
							<td>${sequenceclaimLevelMap.key}&nbsp;</td>
							<td>${sequenceclaimLevelMap.value}</td>
							</tr>
							<c:set var="rowCount2" value="${rowCount2 + 1}"/>
							<c:set var="rowCountIncrementFlag2" value="false"/>
					</c:if>
					<c:if test="${claimLevelStatus.last}">
						<c:if test="${rowCountIncrementFlag2}">
							<c:set var="rowCount2" value="${rowCount2 + 1}"/>
							</c:if>
						<c:set var="canGenerateColumnsUpto2" value="${rowCount2 * 7}"/>
						<c:set var="count2" value="${(claimLevelStatus.count-2)*2}"/>
						<c:set var="currentColumnCount2" value="${count2+rowCount2}"/>
						<c:set var="diffInColumnCount2" value="${canGenerateColumnsUpto2 - currentColumnCount2}"/>
					  
						<c:if test="${diffInColumnCount2 > 0}">
							<c:forEach  step="1" begin="0" end="${diffInColumnCount2-1}">
								<td class="" style="">&nbsp;</td>	
							</c:forEach>
						</tr>
						</c:if>
					</c:if>
					</c:if>	
					
					</c:forEach>
					
			</table>
			<c:if test="${! empty ruleViewClaimCodeSequenceList}">
			<c:set var="claimCodeCount" value="-1" />
				   <div id="claimCodeViewDiv">
	   <c:set var="claimCodeRulesequence" value="" />
	<c:set var="claimCodeequence" value="" />
<c:set var="claimCodeequenceNumber" value="" />
		<c:forEach items="${ruleViewClaimCodeSequenceList}" var="claimCodeMapObj" begin ="0">
						<c:set var="rowCount3" value="0" />
					<table cellpadding="0" cellspacing="0" class="Pd3 ruleIndicatorTable" id="ruleClaimCodeIndicatorTable">
				<c:forEach items="${claimCodeMapObj}" var="claimCodeMap" begin ="0"  varStatus="claimCodeStatus">
					<c:set var="claimCodeCount" value="${claimCodeCount + 1}"/>	
				<c:if test="${(claimCodeStatus.index == 0)}"> 
					<c:set var="claimCodeRulesequence" value="${claimCodeMap.value}" />
				</c:if>	
					<c:if test="${claimCodeStatus.index == 1 }">
					<c:set var="claimCodeequence" value="${claimCodeMap.value}" />
					</c:if>	
					<c:if test="${claimCodeStatus.index == 2 }">
					<c:set var="claimCodeequenceNumber" value="${claimCodeMap.value}" />
					</c:if>	
						<c:if test="${(claimLevelsequence != claimCodeequence) }">
						<c:set var="claimCodeCount" value="-1" />
						
                    </c:if>	
					<c:if test="${((claimLevelRulesequence == claimCodeRulesequence && rulesequence == claimCodeRulesequence )  && (claimLevelsequence == claimCodeequence))}">
					<c:if test="${(claimCodeStatus.index == 1) && (claimCodeCount == 0) }">
					<tr  class="createEditTableShade" >
							<td colspan="7">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${claimCodeMap.key}
							</td>
					</tr>
					</c:if>
					<c:if test="${claimCodeStatus.index == 2}">
						<tr class="${claimCodeCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
						<c:set var="rowCountIncrementFlag3" value="true"/>
							<td  width="25" ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${claimCodeMap.value}&nbsp;</b></td>  
							
					</c:if>
					<c:if test="${claimCodeStatus.index == 3}">
							<td class="styleForTd105">${claimCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${claimCodeStatus.index == 4}">
							<td class="styleForTd105">${claimCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${claimCodeStatus.index == 5}">
							<td class="styleForTd135">${claimCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${claimCodeStatus.index == 6}">
							<td class="styleForTd105">${claimCodeMap.key}&nbsp;</td>		
					</c:if>
					<c:if test="${claimCodeStatus.index == 7}">
							<td class="styleForTd135">${claimCodeMap.key}&nbsp;</td>		
					</c:if>
					
					<c:if test="${claimCodeStatus.index == 8}">
							<td class="styleForTd105">${claimCodeMap.key}&nbsp;</td>
							</tr>
							<c:set var="rowCount3" value="${rowCount3 + 1}"/>
							<c:set var="rowCountIncrementFlag3" value="false"/>
					</c:if>
					
					<c:if test="${claimCodeStatus.last}">
						<c:if test="${rowCountIncrementFlag3}">
							<c:set var="rowCount3" value="${rowCount3 + 1}"/>
											</c:if>
						<c:set var="canGenerateColumnsUpto3" value="${rowCount3 * 7}"/>
						<c:set var="count3" value="${claimCodeStatus.count - 2}"/>
						<c:set var="currentColumnCount3" value="${count3}"/>
						<c:set var="diffInColumnCount3" value="${canGenerateColumnsUpto3 - currentColumnCount3}"/>
							
					    <c:if test="${diffInColumnCount3 > 0}">
							<c:forEach  step="1" begin="0" end="${diffInColumnCount3-1}">
								<td class="" style="">&nbsp;</td>	
							</c:forEach>
						</tr>
						</c:if>
						
					</c:if>
					</c:if>	
								
					
				
		</c:forEach>
			</table>
			</c:forEach>   
	</div>
</c:if>		
			
			</c:forEach>   
	</div>
</c:if>	
				
			
		
<br/><br/><br/>
		
</c:forEach>   
	</div>
</c:if>	

</form>
</body>
</html>