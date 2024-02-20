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
			action = "${context}/referencedata/showExclusionManagePage.html";
			window.location.href = action; 
			break; 
	}
}
</script>
<script type="text/javascript">


function closeAction()
{
    document.forms['exclusionForm'].action="../ebxspiderworkflow/back.html";
    document.forms["exclusionForm"].submit();
}

function fetchExclusion() {
		$('#errorCodeLocate').val($.trim($('#errorCodeLocate').val()));
		var key = $("#errorCodeLocate").val().toUpperCase();
		$('#primaryErrorCode').val(key);
		if(key == null || key == ""){
			addErrorToNotificationTray('Error Code is required to locate');
			openTrayNotification();
			document.getElementById('exclusionSectionTwo').style.display= 'none';
			document.getElementById('exclusionSectionThree').style.display= 'none';
			return false;
		}
		$.ajax({
			url: "${context}/referencedata/refreshExclusionPageAction.ajax",
			dataType: "html",
			type: "POST",
			data: "errorCodeLocate="+key,
			success: function(data) {
					document.getElementById('userBased').style.display= 'block';
					document.getElementById('initialLoad').style.display= 'none';
					document.getElementById('exclusionSectionTwo').style.display= 'block';
					if ("${userUIPermissions.authroizedToManageExclusion}" == 'true') {
						document.getElementById('exclusionSectionThree').style.display= 'block';
					}
					$("#dynamicRenderer").html(data);
					sniffer();
					$('#actionIdentifier').val('ActionCompleted');
			}
		});
 }	
 </script>

</head>

<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>


<form name="exclusionForm"  method = "POST">
<input type="hidden" name="exclusionIDForSave" id="exclusionIDForSave" value="" />
<input type="hidden" name="hiddenURLForPrimary" id="hiddenURLForPrimary" value="" />
<input type="hidden" name="hiddenURLForType" id="hiddenURLForType" value="" />
<input type="hidden" name="primaryErrorCode" id="primaryErrorCode" value="" />
<input type="hidden" name="userCommentsExclusion" id="userCommentsExclusion" value="" />
<input type="hidden" name="actionIdentifier" id="actionIdentifier" value="" />

<%@include file="/WEB-INF/jspf/pageTop.jspf"%>



<div class="innerContainer" style="height:98%" class="Pd3">

<div id="exclusionEditContainer">


		<div id="exclusionSectionOne">
		 		<div id="exclusionTitleBarSectionOne">
		 			 			 		<div class="headerTitleExclusion">Exclusion</div>	
		 			 			 		
		 		</div>
		 		
			 	<div id="exclusionContentSectionOne">
					<table style="margin-top:4px;" width="100%" border="0" cellspacing="0" cellpadding="0" class="Pd3">
						<TBODY>
							<tr class="createEditTable1-Listdata" style="padding-top: 0px">
										<td style="width: 76px" class="headTextExclusions">Error Code</td>
										<td style="width: 80px"><input type="text" class="inputbox60 nonshadedTextExclude" id="errorCodeLocate" name="errorCodeLocate" /></td>
										<td>
										<A href="#"><IMG id="refreshError" src="${imageDir}/refreshExclusion.gif" width="19" height="19" alt="Refresh"
											onclick="clearOnPageReLoad();fetchExclusion();"></A>
										</td>								
							</tr>	
						</TBODY>             
               	 </table>
				</div>
		 	
		</div>
		
		
		
		<div id="exclusionSectionTwo"  style="display: none">
		 		<div class="exclusionTitleBarSectionTwo">
		 			 			 		<div class="headerTitleSub">Exclusions</div>	

		 		</div>
		 		
		 		<div id="exclusionContentSectionTwo">
		 			<div id="dynamicRenderer">
		 				<jsp:include flush="true" page="exclusionSearchResult.jsp"></jsp:include>	
					</div>
		 			

				</div>
				
				<div class="clearDiv">
				
				</div>
				
	</div>	 	
	
	<div id="exclusionSectionThree" style="display: none;">
				 		<div class="exclusionTitleBarSectionTwo">
		 			 			 		<div class="headerTitleSub">Manage</div>	
		 				</div>
		 				<div class="exclusionSectionThreeSub">
		 						<div id="primaryCriteriaDiv">
		 							<table class="criteriatable" border="0" cellpadding="0" cellspacing="0" width="395px" >
										<tr>
											
		 									<td class="shadedTextExclude" width="74px">First Criteria</td>
		 									<td width="242px">
		 											<select id="criteriaSelectOne" name="criteriaSelectOne" class="dropdown200 nonshadedTextExclude" onchange="primaryCategorySelectActions();" >
		 												<option value="">&#160;</option>
		 												<option value="CONTRACT">Contract</option>
		 												<option value="PRODUCT">Product Code</option>
		 												<option value="PRODUCTLINE">Product Line</option>
		 												<option value="VARIABLE">Variable</option>
		 												<option value="SPS">SPS</option>
		 												<option value="HEADERRULE">Header Rule</option>
		 												<option value="ACCUM">Accum</option>
	 												</select>
		 									</td>
		 									<td>&#160;</td>
		 								</tr>
		 								<tr>
		 									<td colspan="3">&#160;</td>
		 								</tr>
		 							</table>
		 							
		 							<table class="criteriatable" border="0" cellpadding="0" cellspacing="0" width="395px">
		 								<tr>
		 									<td  width="395px" colspan="3">
		 										<textarea name="valuesTextOne" class="exclusionsText nonshadedTextExclude" id="valuesTextOne"  ></textarea>
					 						</td>
		 								
		 								</tr>
		 								<tr>
		 									<td colspan="3">&#160;</td>
		 								</tr>
		 							</table>
		 							<table class="criteriatable" border="0" cellpadding="0" cellspacing="0" width="395px">
		 								<tr>
		 									<td class="shadedTextExclude"  width="83px">Select Values</td>
		 									<td width="242px">
		 											<input type="text"  class="inputbox200Exclude nonshadedTextExclude" id="primeValueAuto" name="primeValueAuto" />
		 											<input type="text" class="inputbox200Exclude nonshadedTextExclude" id="primeValue" name="primeValue" style="display: none;"/>
		 									</td>
		 									<td>
		 										<A href="#"><IMG id="addPrimary" src="${imageDir}/addExclusion.gif" width="19" height="19" alt="Add Exclusion"
												onclick="populateToTextPrimary();"></A>
		 									</td>
		 								</tr>
		 							</table>
		 						</div>
		 						
					 			<div id="secondaryCriteriaDiv">
					 			
		 							<table class="criteriatable" border="0" cellpadding="0" cellspacing="0" width="395px">
		 								<tr>
		 									<td class="shadedTextExclude" width="89px">Second Criteria</td>
		 									<td  width="242px">
		 											<select id="criteriaSelectTwo" name="criteriaSelectTwo" disabled="disabled" class="dropdown200Disabled nonshadedTextExclude" onchange="secondaryCategorySelectActions();">
		 												<option value="">&#160;</option>
		 												<option value="VARIABLE">Variable</option>
		 												<option value="SPS">SPS</option>
		 												<option value="HEADERRULE">Header Rule</option>
		 												<option value="ACCUM">Accum</option>
		 											</select>
		 									</td width="242px">
		 									<td>&#160;</td>
		 								</tr>
		 								<tr>
		 									<td colspan="3">&#160;</td>
		 								</tr>
		 							</table>

									<table class="criteriatable" border="0" cellpadding="0" cellspacing="0" width="395px">			 								
		 								<tr>
		 									<td width="395px" colspan="3">
		 										 <textarea name="valuesTextTwo"  class="exclusionsTextDisabled nonshadedTextExclude" disabled="disabled" id="valuesTextTwo"  ></textarea> 
					 						</td>
		 								</tr>
		 								<tr>
		 									<td colspan="3">&#160;</td>
		 								</tr>
		 							</table>
		 								

		 							<table class="criteriatable" border="0" cellpadding="0" cellspacing="0" width="395px">			 									
		 								<tr>
		 									<td class="shadedTextExclude"  width="83px">Select Values</td>
		 									<td width="242px">
		 											<input type="text" class="inputbox200ExcludeDisabled nonshadedTextExclude" disabled="disabled" id="secValueAuto" name="secValueAuto" />
		 											<input type="text" class="inputbox200ExcludeDisabled nonshadedTextExclude" disabled="disabled" id="secValue" name="secValue" style="display: none;"/>
		 									</td>
		 									<td>
		 										<A href="#"><IMG id="addSec" src="${imageDir}/addExclusion.gif" disabled="disabled" width="19" height="19" alt="Add Exclusion"
												onclick="populateToTextSecondary();"></A>
		 									</td>
		 								</tr>
		 							</table>	 			
	 							</div>
		 				
		 				
		 				</div>
		 				
		 				
	</div>


	
	
		 		
		 		
	
	</div> <!-- Edit container ends here -->
	

		
		


</div> <!-- Inner container ends here -->
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
				<td width="0" height="20"><a href="#" onclick ='closeAction();'>Close</a></td>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</tr>
			</table>

</div>

<div id="userBased" class="footer" style="display: none;">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
				
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='closeAction();'>Close</a></td>
				

<c:if test="${userUIPermissions.authroizedToManageExclusion}">
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>		       
		        <td width="0" height="20"><a href="#" onclick ='clearOnPageReLoad();'>Cancel</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='clearTextArea();'>Clear</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='deleteAllErrorCode();'>Delete</a></td>
		        
</c:if>

		        
				<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>	        
		        <td width="0" height="20"><a href="#" onclick ='openCommentsLogPopUp();'>Comment Log</a></td>
		        
<c:if test="${userUIPermissions.authroizedToManageExclusion}">		        
		        
		       	<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick ='openExclusionUpdateConfirmation();'>Update</a></td>
</c:if>	 
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
		     </tr>
		    </table>					
		</div>

</div>





<!-- Wrapper ends here -->




<div id="exclusionUpdateConfirm" style="display: none;">
	<div id="exclusionConfirmTitle" class ="shadedTextExclude" style="padding-left: 9px;">

	</div>

	<div id="exclusionConfirmMessage" style="padding-top:10px; text-align: center">
		<table width="380" height="116" border="0" cellpadding="0" align="center" cellspacing="0" class="shadedTextExclude" >
		<tr>
		<td >
		<textarea id="updateComments" name="updateComments"  class="updatedeletetextclass"  ></textarea>
		</td>
		</tr>
		</table>
	</div>

	<div id="exclusionConfirmButtton" align="center" style="padding-top: 10px; padding-bottom: 10px;">
		<a onclick="closeExclusionUpdateConfirmation();" href="#" >
		<img id="doneExclusion" src="${imageDir}/done_but.gif" alt="Confirm" title="Confirm" /></a>
	</div>
</div>


<div id="exclusionCommentsLogDiv">
</div>

<div id="confirmationDivDelete"></div>





	<c:choose>
      <c:when test="${requestScope.actions == 'Actions'}">
            <script>
 	          	  		$('#errorCodeLocate').val("${requestScope.errorCode}");
						$('#primaryErrorCode').val("${requestScope.errorCode}"); 
						$('#exclusionIDForSave').val("0"); 
						$('#primaryErrorCode').val(""); 
						$('#criteriaSelectOne').val(""); 
						$('#criteriaSelectTwo').val(""); 
						$('#userCommentsExclusion').val(""); 
						$('#valuesTextOne').val(""); 
						$('#valuesTextTwo').val(""); 
						$('#actionIdentifier').val('ActionCompleted');
						fetchExclusion();
            </script>
      </c:when>

      <c:otherwise>
       <script>
	       	sniffer();
	      	$('#actionIdentifier').val('NoAction');
       	</script>
      </c:otherwise>
      </c:choose>
      
 </form>   

<%@include file="/WEB-INF/jspf/messageTray.jspf"%>


</body>
</html>
