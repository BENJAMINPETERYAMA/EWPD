<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- IS modified -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
</head>

<body>
	<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.dataTables.nightly.js"></script>
	<script type="text/javascript">
	
	var $links; 
		 jQuery(function ($) {
     $links = $('.est_btn').click(function () {
        $(this).addClass('clicked');
    });
	})
	  
		function OOAMessageView(networkOrContractCode, searchType, exchangeInd, messageID) {
			var searchCriteria = networkOrContractCode;
			var search = searchType;
			var viewOrSearchFunction = "viewFunction";
			var exchngeInd = exchangeInd;
			var messId = messageID;
			
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/searchOOAMessage.ajax",
						dataType : "html",
						type : "POST",
						data : "searchCriteria=" + searchCriteria + "&search="
								+ search + "&viewOrSearchFunction="
								+ viewOrSearchFunction+"&excInd="
								+exchngeInd + "&messgId="
								+messId,
						success : function(data) {
							$("#contractBxMappingDialog").dialog('close');
							$("#contractBxMappingDialog").html(data);
							$("#contractBxMappingDialog").dialog({
								height : '500',
								width : '690px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'OOA Message View Screen',
                         		close: function( event, ui ) {
                         		$(".ui-dialog #contractBxMappingDialog #forCancel").click();
                         		}
							});
							$("#contractBxMappingDialog").dialog();
						}
					});

		}

		 function oOAMessageForCascade() {

 		if (!$links.is('.clicked')) { 
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/ooaMessageView.ajax",
						dataType : "html",
						type : "POST",
						success : function(data) {
							$("#ooaMessageReportDialog").html(data);
							$("#ooaMessageReportDialog").dialog({
								height : '500',
								width : '690px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'Out of Area Message Maintenance'

							});
							$("#ooaMessageReportDialog").dialog();
						}
					});
		} 
	}

		function viewOOAMessage() {
			$
					.ajax({
						url : "${context}/lockedvariableauditreport/viewOOAMessage.ajax",
						dataType : "html",
						type : "POST",
						success : function(data) {
							$("#lockAuditReportDialog").html(data);
							$("#lockAuditReportDialog").dialog({
								height : '300',
								width : '900px',
								show : 'slide',
								modal : true,
								resizable : false,
								title : 'OOA Message View'

							});
							$("#lockAuditReportDialog").dialog();
						}
					});
		}

		
		

		function imposeMaxLength(elementId, MaxLen, element) {
			var valueOfTextArea = $.trim($('#' + elementId).val());
			$('#' + elementId).val(valueOfTextArea);
			if (valueOfTextArea != null && valueOfTextArea.length > MaxLen) {
				var message = "Text length cannot be greater than " + MaxLen
						+ " for " + element;
				addErrorToNotificationTray(message);
				openTrayNotification();
				return false;
			}
			return true;
		}

		

	
	</script>
<%-- 	<%@ include file="/WEB-INF/jspf/pageTop.jspf"%> --%>

	<div class="innerContainer" style="height: 96%; width: 96%; ">
		<!-- innerContainer Starts-->
		<div class="Level1">
			<!-- Level1 Starts-->
			<div class=unmappedooamessage>
				<!--unmapped Starts-->
				<div class="titleBar">
					<div class="headerTitle">Search Result Screen</div>
				</div>
				<form name="unmappedVarForm"
					action="${context}/viewcreatemappingpage/viewFromUnMapped.html">
					<div class="ListTableDivOOAMessage" id="unmappedTableDiv">
						<!--Starts submitterContinue-->
						<table border="0" cellpadding="0" cellspacing="0"
							id="searchResultScreenTable" class="mappedTable Pd3 shadedText">
							<THEAD>
							
							
							<c:set var="rowCountHeader" value="0" />
							<c:if test="${! empty oOAMessageSearchDetailList}">

									<c:forEach items="${oOAMessageSearchDetailList}"
										var="oOAMessageSearchDetailHeader">
							
								<c:if test="${rowCountHeader==0}">
							
								<tr class="SearchResultScreen searchResultScreenTable">
								
								<c:if test="${oOAMessageSearchDetailHeader.contractCdDisp == 'block'}">
								 
								<th id="network/contractCodeId" width="4%" class="tableHeader" >
										Contract code <span id="networkIdContractCodeIcon"
										class="ui-widget-header"
										style="padding-left: 100px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
								</c:if>
								
								
									<c:if test="${oOAMessageSearchDetailHeader.contractCdDisp == 'none'}">
								
								
								<th id="network/contractCodeId" width="4%" class="tableHeader" >
										Network Id <span id="networkIdContractCodeIcon"
										class="ui-widget-header"
										style="padding-left: 100px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
								
								</c:if>
								
									
									
									
									<c:if test="${oOAMessageSearchDetailHeader.contractCdDisp == 'none'}">
									<th id="exchangeIndicatorId" width="20%" class="tableHeader">
										Exchange Indicator <span id="exchangeIndicatorIcon"
										class="ui-widget-header"
										style="padding-left: 100px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
									</c:if>
									
									<c:if test="${oOAMessageSearchDetailHeader.contractCdDisp == 'block'}">
									<th id="messageExemptedId" width="13%" class="tableHeader" >
										Message Exempted <span id="messageExemptedIcon"
										class="ui-widget-header"
										style="padding-left: 100px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
									</c:if>
									
									<th id="effectiveDateId" width="20%" class="tableHeader"
										nowrap="nowrap">Effective Date <span
										id="effectiveDateIcon" class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
									
									<th id="expiryDateId" width="20%" class="tableHeader"
										nowrap="nowrap">Expiry Date <span id="expiryDateIcon"
										class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											 <!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
									<th id="statusId" width="20%" class="tableHeader"
										nowrap="nowrap">Status <span id="statusIcon"
										class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
									<th id="actionId" width="40%" class="tableHeader"
										nowrap="nowrap">Action <span id="actionIcon"
										class="ui-widget-header"
										style="padding-left: 50px; display: inline-block; background: none; border: 0px; position: right; top: -4px; left: -7px; height: 10px;">
											<!-- <span class="ui-icon ui-icon-carat-1-s"
											style="position: right; height: 10px;" /> -->
									</span>
									</th>
								</tr>
								</c:if>
								<c:set var="rowCountHeader" value="${rowCountHeader + 1}" />
									</c:forEach>
								</c:if>
							</THEAD>
							<TBODY>
								<c:set var="rowCount" value="0" />

								<c:if test="${! empty oOAMessageSearchDetailList}">

									<c:forEach items="${oOAMessageSearchDetailList}"
										var="oOAMessageSearchDetail">

										<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
											<input type="hidden" id="unmappedVariableId" value="" />
											<input type="hidden" name="variableIdHidden"
												id="variableIdHidden" value="" />
											<td>${oOAMessageSearchDetail.networkOrContractCode}</td>
											
											<c:if test="${oOAMessageSearchDetail.contractCdDisp == 'none'}">
											<td>${oOAMessageSearchDetail.exchangeIndicator}</td>
											</c:if>
											
											<c:if test="${oOAMessageSearchDetail.contractCdDisp == 'block'}">
											<c:choose>
            								 <c:when test="${oOAMessageSearchDetail.messageExempted == 'Y'}">
        									<td>Yes</td>
   											 </c:when>    
   											 <c:when test="${oOAMessageSearchDetail.messageExempted == 'N'}">
        									<td>No</td>
   											 </c:when> 
											</c:choose>
											</c:if>
											
											<td><fmt:formatDate pattern="MM/dd/yyyy"
													value="${oOAMessageSearchDetail.messageEffectiveDate}" /></td>
											<td><fmt:formatDate pattern="MM/dd/yyyy"
													value="${oOAMessageSearchDetail.messageExpiryDate}" /></td>
													
													<td>${oOAMessageSearchDetail.workFlowAssosciationStatus}</td>
													<%-- <c:choose>
            								 <c:when test="${oOAMessageSearchDetail.currentStatus == 'A'}">
        									<td>Active</td>
   											 </c:when>    
   											 <c:when test="${oOAMessageSearchDetail.currentStatus == 'U'}">
        									<td>Updated</td>
   											 </c:when> 
											</c:choose> --%>
											<td nowrap><a href="#" class="est_btn" 
												onclick='OOAMessageView("${oOAMessageSearchDetail.networkOrContractCode}","${oOAMessageSearchDetail.search}","${oOAMessageSearchDetail.exchangeIndicator}","${oOAMessageSearchDetail.messageId}");'>
													<img src="${imageDir}/search_icon.gif" alt="View" id="view"
													title="View" /> <!--alt will not work in IE8 , instead use title -->
											</a>&#160;&#160; 
											<c:if test="${(oOAMessageSearchDetail.workFlowAssosciationStatus != 'DELETED') && (oOAMessageSearchDetail.workFlowAssosciationStatus != 'MARKED_FOR_DELETION')}">
											<a href="#" class="est_btn" 
												onclick='ooaMessageAddDateSegment("${oOAMessageSearchDetail.networkOrContractCode}","${oOAMessageSearchDetail.exchangeIndicator}","${oOAMessageSearchDetail.messageId}","${oOAMessageSearchDetail.search}", "${oOAMessageSearchDetail.messageEffectiveDate.month}"+"/"+"${oOAMessageSearchDetail.messageEffectiveDate.date}"+"/"+"${oOAMessageSearchDetail.messageEffectiveDate.year}", "${oOAMessageSearchDetail.messageExpiryDate.month}"+"/"+"${oOAMessageSearchDetail.messageExpiryDate.date}"+"/"+"${oOAMessageSearchDetail.messageExpiryDate.year}");'>
													<img src="${imageDir}/create_icon.gif" alt="Create"
													title="ADD Date Segment" />
											</a> &#160;&#160; 
											</c:if>
											<c:if test="${(oOAMessageSearchDetail.workFlowAssosciationStatus != 'DELETED') && (oOAMessageSearchDetail.workFlowAssosciationStatus != 'MARKED_FOR_DELETION')}">
											<a href="#" class="est_btn" 
												onclick='openUserCommentsNotApplicableDialogForDelete("${oOAMessageSearchDetail.networkOrContractCode}", "${oOAMessageSearchDetail.messageId}", "${oOAMessageSearchDetail.search}" );'>
													<img src="${imageDir}/markAsNotApp.gif" alt="Delete"
													title="Delete" />
											</a>&#160;&#160; 
											</c:if>
											<c:if test="${(oOAMessageSearchDetail.workFlowAssosciationStatus != 'DELETED') && (oOAMessageSearchDetail.workFlowAssosciationStatus != 'MARKED_FOR_DELETION')}">
											<a href="#" id="Edit" class="est_btn" 
												onclick='oOAMessageEditScreen("${oOAMessageSearchDetail.networkOrContractCode}","${oOAMessageSearchDetail.exchangeIndicator}", "${oOAMessageSearchDetail.messageExempted}", "${oOAMessageSearchDetail.messageEffectiveDate.month}"+"/"+"${oOAMessageSearchDetail.messageEffectiveDate.date}"+"/"+"${oOAMessageSearchDetail.messageEffectiveDate.year}", "${oOAMessageSearchDetail.messageExpiryDate.month}"+"/"+"${oOAMessageSearchDetail.messageExpiryDate.date}"+"/"+"${oOAMessageSearchDetail.messageExpiryDate.year}","${oOOMessageSearchDetail.status}","${oOAMessageSearchDetail.messageId}", "${oOAMessageSearchDetail.messageTextOne}", "${oOAMessageSearchDetail.messageTextTwo}", "${oOAMessageSearchDetail.messageTextThree}", "${oOAMessageSearchDetail.search}","${oOAMessageSearchDetail.workFlowAssosciationStatus}");'>
													<img src="${imageDir}/edit_icon.gif" alt="Edit"
													title="Edit" style="height: 15px;" />
											</a>&#160;&#160; 
											</c:if>
											<c:if test="${(oOAMessageSearchDetail.workFlowAssosciationStatus == 'BUILDING')||(oOAMessageSearchDetail.workFlowAssosciationStatus == 'BEING_MODIFIED')}">
											<a href="#" id="ScheduleToWGS" class="est_btn" 
												onclick='openUserCommentsSendToWGSDialog("${oOAMessageSearchDetail.networkOrContractCode}","${oOAMessageSearchDetail.messageId}","${oOAMessageSearchDetail.search}","TEST");'>
													<img src="${imageDir}/test_icon.gif" alt="ScheduleToTEST"
													title="ScheduleToTEST" style="height: 15px;" />
											</a>&#160;&#160;
											</c:if>
											
											<c:if test="${(oOAMessageSearchDetail.workFlowAssosciationStatus == 'OBJECT_TRANSFERRED') && (oOAMessageSearchDetail.testInd == 'Y')}">
											<a href="#" id="ScheduleToPROD" class="est_btn" 
												onclick='openUserCommentsSendToWGSDialog("${oOAMessageSearchDetail.networkOrContractCode}","${oOAMessageSearchDetail.messageId}","${oOAMessageSearchDetail.search}","PRODUCTION");'>
													<img src="${imageDir}/approve_icon.gif" alt="ScheduleToPROD"
													title="ScheduleToPROD" style="height: 15px;" />
											</a>&#160;&#160;
											</c:if>
											</td>
										</tr>
										<c:set var="rowCount" value="${rowCount + 1}" />
									</c:forEach>
								</c:if>
								<c:if test="${empty oOAMessageSearchDetailList}">
									<tr>
										<td colspan="5" align="center">No Data Found</td>
									</tr>
								</c:if>
							</TBODY>
						</table>
					</div>
					<a style="display:none" href="#" name="messageView" id="messageViewForCascade" onclick="oOAMessageForCascade()" >
							<img src="${imageDir}/cancel_but.gif" 
							 width="100" height="20" />
					</a>
				</form>
				
			</div>
			
			

			<!-- Level1 Ends-->
			<div class="clear"></div>
</body>
</html>