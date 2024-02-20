<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
</head>  
<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/simulationInputPage.js"></script>
<script type="text/javascript">
function onCancelClick(search,searchCriteria,exchangeIndicator) {
	
			if(null==exchangeIndicator||(exchangeIndicator != 'ON' 
					&& exchangeIndicator != 'OF' 
					&& exchangeIndicator != 'BT') ){
				exchangeIndicator = "";
			}
			//Overriding as of now will be changing based on the requirement.
			exchangeIndicator = "";
			 $
				.ajax({
					url : "${context}/lockedvariableauditreport/searchOOAMessage.ajax",
					dataType : "html",
					type : "POST",
					data : "searchCriteria=" + searchCriteria
							+ "&search=" + search
							+ "&viewOrSearchFunction=searchFunction"
							+ "&excInd="+exchangeIndicator,
					success : function(data) {
						$("#ooaMessageReportDialog").dialog('close');
						$("#contractBxMappingDialog").html(data);
						$("#contractBxMappingDialog").dialog({
							height : '300',
							width : '950px',
							show : 'slide',
							modal : true,
							resizable : false,
							title : 'OOA Message Search',
							close: function( event, ui ) {
                         	$(".ui-dialog #contractBxMappingDialog #messageViewForCascade").click();
                         	} 

						});
						$("#contractBxMappingDialog").dialog();
					}
				});

		}

</script>

<!-- <div class="innerContainer" >innerContainer Starts
 --> 
 <div class="Level1">
<div class="unmappedooamessage" style="height : 465px;width : 650px;"><!--unmapped Starts-->
<!-- <div class="titleBar"> -->

<form name="oOAMessageCreate" method="POST" );" >
		<div>
			<table border="0" cellspacing="0" width="100%">
			
			<c:if test="${! empty oOAMessageSearchDetailList}">

									<c:forEach items="${oOAMessageSearchDetailList}"
										var="oOAMessageSearchDetailHeader">
					
								
				<tr height="40px">
					<td width ="300px"align="right" id="netWorkOrContractID" >${oOAMessageSearchDetailHeader.search}</td>
					<td width="20px"></td>
					<td id="networkOrContractCodeID">${oOAMessageSearchDetailHeader.networkOrContractCode}</td>
		

				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr><tr height="30px">
				
				
					<td align="right" id="ExcahngeOrExplId">${oOAMessageSearchDetailHeader.exindOrMsgExmt}</td>
					<td width="20px"></td>
					
							<c:choose>
            								 <c:when test="${oOAMessageSearchDetailHeader.search == 'Network'}">
        									<td align="left" id="ExcahngeOrExplValId" >${oOAMessageSearchDetailHeader.exchangeIndicator}</td>
   											 </c:when>    
   											 <c:when test="${oOAMessageSearchDetailHeader.search == 'Contract'}">
        									<td align="left" id="ExcahngeOrExplValId" >${oOAMessageSearchDetailHeader.messageExempted}</td>
   											 </c:when> 
											</c:choose>
				</tr>
				
				<tr height="30px">
				
				
					<td align="right" id="ExcahngeOrExplId">Message Effective Date</td>
					<td width="20px"></td>
					<td align="left" id="ExcahngeOrExplValId" ><fmt:formatDate pattern="MM/dd/yyyy" value="${oOAMessageSearchDetailHeader.messageEffectiveDate}" /></td>
				
				</tr>
				
				<tr height="30px">
				
					<td align="right" id="ExcahngeOrExplId">Message Expiry Date</td>
					<td width="20px"></td>
					<td align="left" id="ExcahngeOrExplValId" ><fmt:formatDate pattern="MM/dd/yyyy" value="${oOAMessageSearchDetailHeader.messageExpiryDate}"/></td>
				
				</tr>
				<tr height="10px">
					<td></td>
					<td width="20px"></td>
					<td></td>
				</tr>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<tr height="80px">
					<td align="right">Message Text</td>
					<td width="20px"></td>

					<td align="left"><textarea name="oOAMessageTextAreaId" disabled = "disabled"
							id="oOAMessageTextAreaId" rows="5" cols="50">${oOAMessageSearchDetailHeader.messageTextOne}</textarea></td>

				</tr>
				<tr height="10px">
					<td></td>
					<td width="20px"></td>
					<td></td>
				</tr>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<tr height="80px">
					<td align="right">User Comments</td>
					<td width="20px"></td>

					<td align="left"><textarea name="oOAUserCommentId" disabled = "disabled"
							id="oOAUserCommentId" rows="5" cols="50" >${oOAMessageSearchDetailHeader.comments}</textarea></td>

				</tr>
				<tr height="10px">
					<td width="20px"></td>
				</tr>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				
				<tr height="30px">
					<td width ="300px"align="right" id="netWorkOrContractID" >Created By</td>
					<td width="20px"></td>
					<td id="networkOrContractCodeID">${oOAMessageSearchDetailHeader.userId}</td>
					</tr>	
					
					<tr height="30px">
					<td width ="300px"align="right" id="netWorkOrContractID" >Created Timestamp</td>
					<td width="20px"></td>
					<td id="networkOrContractCodeID"><fmt:formatDate pattern="MM/dd/yyyy HH:mm:ss" value="${oOAMessageSearchDetailHeader.createdTimeStamp}"/></td>
					</tr>	
					
					<tr height="30px">
					<td width ="300px"align="right" id="netWorkOrContractID" >Last Updated User</td>
					<td width="20px"></td>
					<td id="networkOrContractCodeID">${oOAMessageSearchDetailHeader.lstChgUserId}</td>
					</tr>	
					
					<tr height="30px">
					<td width ="300px"align="right" id="netWorkOrContractID" >Last Updated TimeStamp</td>
					<td width="20px"></td>
					<td id="networkOrContractCodeID"><fmt:formatDate pattern="MM/dd/yyyy HH:mm:ss" value="${oOAMessageSearchDetailHeader.lstChgTimestamp}"/></td>
					</tr>	
					
				<a style="display:none" href="#" name="forCancel" id="forCancel" onclick="onCancelClick('${oOAMessageSearchDetailHeader.search}','${oOAMessageSearchDetailHeader.networkOrContractCode}','${oOAMessageSearchDetailHeader.exchangeIndicator}' );" >
							<img src="${imageDir}/cancel_but.gif" 
							 width="100" height="20" />
					</a>
		
				</c:forEach>
</c:if>
			</table>
		</div>				
	</form>						
		</div></div>
<!--unmapped Ends-->

<!-- Level1 Ends-->
<div class="clear"></div>

</body>
</html>