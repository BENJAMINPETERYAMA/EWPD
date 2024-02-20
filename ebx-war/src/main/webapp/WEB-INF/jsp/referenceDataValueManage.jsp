<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/referenceData.js"></script>

<script type="text/javascript">
document.attachEvent("onkeydown", my_onkeydown_handler); 
function my_onkeydown_handler() 
{ 
	switch (event.keyCode) 
	{ 
		case 116 : 
			event.returnValue = false;
			event.keyCode = 0;
			var action ;
			action = "${context}/referencedata/showReferenceDataManagePage.html";
			window.location.href = action; 
			break; 
	}
}
</script>
<script type="text/javascript">



function closeDataValueAction()
{
    document.forms['referenceDataValueForm'].action="../ebxspiderworkflow/back.html";
    document.forms["referenceDataValueForm"].submit();
}
function fetchReferenceDataValue(isAdmin) {
		resetMessages();
		$('#catalogNameLocate').val($.trim($('#catalogNameLocate').val()));
		var key = $("#catalogNameLocate").val().toUpperCase();
		if(key == null || key == ""){
			addErrorToNotificationTray('Please enter a Catalog Name');
			openTrayNotification();
			document.getElementById('referenceDataValueSectionTwo').style.display= 'none';
			if("true" == isAdmin) {
				document.getElementById('referenceDataValueSectionThree').style.display= 'none';
			}
			
			document.getElementById('userBasedForCreate').style.display= 'none';
			document.getElementById('userBasedForUpdate').style.display= 'none';
			document.getElementById('initialLoad').style.display= 'block';
			return false;
		}
		$.ajax({
			url: "${context}/referencedata/refreshDataValuePageAction.ajax",
			dataType: "html",
			type: "POST",
			data: "catalogNameLocate="+key,
			success: function(data) {
					document.getElementById('userBasedForCreate').style.display= 'block';
					document.getElementById('referenceDataValueSectionTwo').style.display= 'block';
					if("true" == isAdmin) {
						document.getElementById('referenceDataValueSectionThree').style.display= 'block';
					}	
									
					document.getElementById('initialLoad').style.display= 'none';
					document.getElementById('userBasedForUpdate').style.display= 'none';
					$("#dynamicRenderer").html(data);
					dataValueSniffer();
			}
		});
}

 </script>

</head>

<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>

<form name="referenceDataValueForm"  method = "POST">
<input type="hidden" name="userComments" id="userComments" value="" />
<input type="hidden" name="valueForSave" id="valueForSave" value="" />
<%@include file="/WEB-INF/jspf/pageTop.jspf"%>

<div class="innerContainer" style="height:98%" class="Pd3">

	<div id="referenceDataValueContainer">
	
		<div id="referenceDataValueSectionOne">
	 		<div id="referenceDataValueTitleBarOne">
	 			<div class="headerTitleDataValue">Reference Data Value</div>		 			 			 		
			</div>
		 		
			<div id="referenceDataValueContentOne">
				<table style="margin-top:5px;" width="100%" border="0" cellspacing="0" cellpadding="0" class="Pd3">
					<TBODY>
						<tr class="createEditTable1-Listdata" style="padding-top: 0px">
									<td style="width: 76px" class="headText">Catalog Name</td>
									<td style="width: 80px"><input type="text" class="inputbox90" id="catalogNameLocate" name="catalogNameLocate" value="${catalogNameLocate}"/></td>
									<td>
									<A href="#"><IMG id="refreshCatalogName" src="${imageDir}/refreshExclusion.gif" width="19" height="19" alt="Refresh"
										onclick='viewRefreshedPage("${userUIPermissions.authroizedToManageDataValues}");fetchReferenceDataValue("${userUIPermissions.authroizedToManageDataValues}");'></A>
									</td>
																	
						</tr>	
					</TBODY>             
	            </table>
			</div>		 	
		</div> <!-- End of referenceDataValueSectionOne -->
		
		<div id="referenceDataValueSectionTwo"  style="display: none">
	 		<div class="dataValueTitleBarSectionTwo">
		 		<table class="dvmappedTable" border="0" cellpadding="0" cellspacing="0" width="100%">
					<THEAD>
						<tr>
							<th id="systemId" width="5%" class="tableHeader">
							Value								
							</th> 
							<th id="variableWpdId" width="15%" class="tableHeader">
							Description								
							</th>
							<th id="variableWpdId" width="40%" class="tableHeader">
							Definition							
							</th>
							<c:if test="${userUIPermissions.authroizedToManageDataValues}"> 
							<th id="createdId" width="40%" class="tableHeader" nowrap="nowrap">
								&nbsp;
							</th>
							</c:if>
						</tr>
					</THEAD> 			
			 	</table>
	 		</div>
	 		<div id="referenceDataValueContentTwo">
	 			<div id="dynamicRenderer">
	 				<jsp:include flush="true" page="dataValueSearchResult.jsp"></jsp:include>	
				</div>
			</div>
			
			<div class="clearDiv">
			</div>
				
		</div>	 <!-- End of  referenceDataValueSectionTwo -->	
	<c:if test="${userUIPermissions.authroizedToManageDataValues}"> 
	<div id="referenceDataValueSectionThree" style="display: none;">
				
	 		<div class="dataValueTitleBarSectionTwo">
				<div class="dvheaderTitleSub">Manage</div>	
			</div>
			<div class="exclusionSectionThreeSub">		
				<table class="dvcriteriatable" border="0" cellpadding="0" cellspacing="0" width="100%">
					
					<tr>
						<td width="80px" class="headText">Value<span class="star">*</span></td>
						<td style="width: 80px"><input type="text" class="inputbox60" id="primaryCodeForSave" name=primaryCodeForSave maxlength="10" /></td>
						<td>&#160;</td>		 									
					</tr>
					<tr>
						<td colspan="3">&#160;</td>
					</tr>
					<tr>
						<td width="80px" class="headText">Description<span class="star">*</span></td>
						<td style="width: 80px"><input type="text" class="inputbox65" id="secondaryCodeForSave" name="secondaryCodeForSave" maxlength="25"/></td>
						<td>&#160;</td>		 									
					</tr>
					<tr>
						<td colspan="3">&#160;</td>
					</tr>
					<tr>
						<td width="80px" class="headText">Definition<span class="star">*</span></td>
						<td width="395px" colspan="3">
							<textarea name="descriptionForSave" class="dataValuesText" id="descriptionForSave" maxlength="240"></textarea>
						</td>
						<td>&#160;</td>		 									
					</tr>
					<tr>
						<td colspan="3">&#160;</td>
					</tr>	
							
				</table>
			</div>	
		
			
	</div> <!-- End of referenceDataValueSectionThree -->
	</c:if>		
	</div> <!-- End of referenceDataValueContainer -->


</div> <!-- End of innerContainer -->
</div> <!-- Container ends here -->
<div id="initialLoad" class="footer">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
				
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='closeDataValueAction();'>Close</a></td>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</tr>
			</table>
</div>

<div id="userBasedForCreate" class="footer" style="display: none;">
	<div class="ajaxIdle" id="ajaxIdleIcon">
		<div class="clear"></div>
	</div>
	<div class="ajaxActive" id="ajaxActiveIcon">
		<div class="clear"></div>
	</div>
		
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>
		<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick ='closeDataValueAction();'>Close</a></td>
		<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
		<c:if test="${userUIPermissions.authroizedToManageDataValues}">	
	        <td width="0" height="20"><a href="#" 
	        	onclick ='clearOnDataValuePageReLoad("${userUIPermissions.authroizedToManageDataValues}");'>Cancel</a></td>
	        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
	        <td width="0" height="20"><a href="#" 
	        	onclick='clearOnDataValuePageReLoad("${userUIPermissions.authroizedToManageDataValues}");'>Clear</a></td>
			<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		</c:if>	        
        <td width="0" height="20"><a href="#" onclick ='openViewHistoryPopUp();'>View History</a></td>
        <c:if test="${userUIPermissions.authroizedToManageDataValues}">
        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		<td width="0" height="20"><a href="#" onclick ='createDataValue();'>Create</a></td>
		</c:if>
		<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
     </tr>
    </table>					
</div>

<div id="userBasedForUpdate" class="footer" style="display: none;">
	<div class="ajaxIdle" id="ajaxIdleIcon">
		<div class="clear"></div>
	</div>
	<div class="ajaxActive" id="ajaxActiveIcon">
		<div class="clear"></div>
	</div>
		
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>
		<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
		<td width="0" height="20"><a href="#" onclick ='closeDataValueAction();'>Close</a></td>
		<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	
		<c:if test="${userUIPermissions.authroizedToManageDataValues}">	
	        <td width="0" height="20"><a href="#" 
	        	onclick ='clearOnDataValuePageReLoad("${userUIPermissions.authroizedToManageDataValues}");'>Cancel</a></td>
	        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
	        <td width="0" height="20"><a href="#" onclick='clearDataValueEdit();'>Clear</a></td>
			<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		</c:if>	       
        <td width="0" height="20"><a href="#" onclick ='openViewHistoryPopUp();'>View History</a></td>
        <c:if test="${userUIPermissions.authroizedToManageDataValues}">
        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		<td width="0" height="20"><a href="#" onclick ='openAdditonalCommentsDialog("update", "null");'>Update</a></td>
		</c:if>
		<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
     </tr>
    </table>					
</div>

<div id="additionalCommentsDialog" style="display: none">
	<input type="hidden" name="primaryCodeInDialog" id="primaryCodeInDialog" value="" />
  	<input type="hidden" id="eventNameInDialog" name="eventNameInDialog" value=""/>
	<table id="additionalCommentsTable" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">          
	  <tr class="">
        <td ><span class="star">*</span><textarea name="additionalComments"  id="additionalComments" 
		rows="5" cols="77"></textarea></td>
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td >
			<a
			href="#" onclick="saveAdditionalComments();"><img  id="additionalCommentsImg" src="${imageDir}/save_but.gif" 
			alt="Save"  title="Save"/></a>
		</td>		
      </tr>
	</table>	
</div>
<div id="viewHistoryDiv">
</div>
</form>   

<c:if test = "${ openCommentsDialog == 'YES'}">
	<script>
		var primaryCode = document.getElementById('valueForSave').value;
		//viewRefreshedPage();
		openAdditonalCommentsDialog('delete', "${valueForSave}");
		
	</script>
</c:if>
<c:if test = "${refresh == 'YES'}">
	<script>
		viewRefreshedPage("${userUIPermissions.authroizedToManageDataValues}");
	</script>
</c:if>
	
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>

</body>
</html>
