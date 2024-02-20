<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.net.*" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
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
			action = "${context}/referencedata/showCategoryCodeMapping.html";
			window.location.href = action; 
			break; 
	}
}
</script>
</head>
<script type="text/javascript">
function displaydiv() {
var fromaction=$("#fromaction").val();
var categoryCode = $("#categoryCode").val();
		var system = $("#system").val();
			var categorydesc = $("#categorydesc").val();
		var EB03 = $("#EB03").val();
		if(null!=fromaction){
		  if(fromaction=='DELETE'||fromaction=='SELECT'||fromaction=='CREATE'||fromaction=='UPDATE'){
		  document.getElementById('dynamicRenderer').style.display= 'block';
					document.getElementById('exclusionSectionTwo1').style.display= 'block';
					document.getElementById('exclusionSectionTwo1').style.display= 'block';
		  }
		}
		
		
}

function sniffer() {
	   if(screen.height==1024) {
	   
	      document.getElementById('exclusionEditContainer1').style.height = "580px";
	      document.getElementById('exclusionSectionTwo1').style.height = "262px";
	     document.getElementById('exclusionContentSectionTwo1').style.height = "234px";
	   } else if(screen.height==960)
	   {	  
	      document.getElementById("exclusionEditContainer1").style.height= "500px";
	      document.getElementById('exclusionSectionTwo1').style.height = "185px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "157px";
	      
	   }
	   else if(screen.height==864)
	   { 
	      document.getElementById("exclusionEditContainer1").style.height= "497px";
	      document.getElementById('exclusionSectionTwo1').style.height = "185px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "158px";
	   }
	   else if(screen.height==720)
	   {  
	      document.getElementById("exclusionEditContainer1").style.height= "380px";
	      document.getElementById('exclusionSectionTwo1').style.height = "95px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "67px";
	   }
	    else if(screen.height==768)
	   {  
	      document.getElementById("exclusionEditContainer1").style.height= "413 px";
	      document.getElementById('exclusionSectionTwo1').style.height = "104px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "76px";
	   }
	   	else if(screen.height==600)
	   {  
	      document.getElementById("exclusionEditContainer1").style.height= "291 px";
	      document.getElementById('exclusionSectionTwo1').style.height = "50px";
	      document.getElementById('exclusionContentSectionTwo1').style.height = "33px";
	   } else {
	     
	   		var setPixMain = (screen.height * 0.6) + "px";
	   		var setPixSub = ((screen.height * 0.6) - 274) + "px"
	   		var setPixSubTask = ((screen.height * 0.6) - 285) + "px"
	   		 document.getElementById("exclusionEditContainer1").style.height= setPixMain;
	   		 document.getElementById("exclusionSectionTwo1").style.height= setPixSub;
	   		document.getElementById("exclusionContentSectionTwo1").style.height= setPixSubTask;
	   }
}

function fetchCategory() {
		
		var key = "";
		var categoryCode = $("#categoryCode").val();
		var system = $("#system").val();
			var categorydesc = $("#categorydesc").val();
		var EB03 = $("#EB03").val();
		if((categoryCode == null || categoryCode == "")&&(system == null || system == "")&&(categorydesc == null || categorydesc == "")&&(EB03 == null || EB03 == "")){
			addErrorToNotificationTray('Please enter atleast one search criteria');
			openTrayNotification();
			document.getElementById('exclusionSectionTwo1').style.display= 'none';
			document.getElementById('exclusionSectionThree1').style.display= 'none';
			return false;
		}
		$.ajax({
			url: "${context}/referencedata/showCategoryCodeMappingResult.ajax",
			dataType: "html",
			type: "POST",
			data: "categoryCode="+escape(categoryCode)+"&system="+escape(system)+"&categorydesc="+categorydesc+"&EB03="+EB03,
			success: function(data) {
					document.getElementById('dynamicRenderer').style.display= 'block';
					document.getElementById('exclusionSectionTwo1').style.display= 'block';
			document.getElementById('exclusionSectionThree1').style.display= 'none';
			document.getElementById('editpage').style.display= 'none';
			document.getElementById('initialLoad').style.display= 'block';
						document.getElementById('createupdate').style.display= 'none';
					$("#dynamicRenderer").html(data);
					
			}
		});
 }	
 
 
function editMapping(categoryCode,system,serviceType,categoryDesc) {
		
		var key = "";
		$.ajax({
			url: "${context}/referencedata/editMapping.ajax",
			dataType: "html",
			type: "POST",
			data: "categoryCodeedit="+escape(categoryCode)+"&systemedit="+escape(system)+"&serviceTypesave="+escape(serviceType)+"&categoryDescription="+escape(categoryDesc),
			success: function(data) {
					document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
					$("#editpage").html(data);
					
			}
		});
 }	
 
 function createMapping(categoryCode,system,serviceType,categoryDesc) {
		
		var key = "";
		$.ajax({
			url: "${context}/referencedata/editMapping.ajax",
			dataType: "html",
			type: "POST",
			data: "categoryCodeedit="+escape(categoryCode)+"&systemedit="+escape(system)+"&serviceTypesave="+escape(serviceType)+"&categoryDescription="+escape(categoryDesc),
			success: function(data) {
					document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
						document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';
					$("#editpage").html(data);
					
			}
		});
 }	
 
function deleteMapping(categorydelete,systemdelete) {
		var categoryCode = $("#categoryCode").val();
		var system = $("#system").val();
			var categorydesc = $("#categorydesc").val();
		var EB03 = $("#EB03").val();
		
			$.ajax({
			url: "${context}/referencedata/showDeletePopup.ajax",
			dataType: "html",
			type: "POST",
			data: "categorydelete="+escape(categorydelete)+"&systemdelete="+escape(systemdelete)+"&categoryCode="+escape(categoryCode)+"&system="+escape(system)+"&categorydesc="+categorydesc+"&EB03="+EB03+"&key=/showCategoryCodeMappingResult.html",
			success: function(data) {
				$("#referenceDataDialog").html(data);
				$("#referenceDataDialog").dialog({
					height:'350',
					width:'490px',	
					show:'slide',
					modal: true,
					resizable: false,
					title: 'Variables associated with category code '+categorydelete

				});
				$("#referenceDataDialog").dialog();
			}
		});
 }	
 function save(){			
			
				trimTextValues();			
				setDescription();
			if(	null == document.getElementById("serviceTypesave")||document.getElementById("serviceTypesave").value ==""){
			  
			
				document.forms['categoryMappingForm'].action="${context}/referencedata/createCategoryCodeMapping.html";
				
		    }else {
				document.forms['categoryMappingForm'].action="${context}/referencedata/updateCategoryCodeMapping.html";
				}
		        document.forms["categoryMappingForm"].submit();
			
      }	
      
      function backAction(){
      
 
    document.forms['categoryMappingForm'].action="../ebxspiderworkflow/back.html";
    document.forms["categoryMappingForm"].submit();

      
	/* 	var action ;
		action = "../referencedata/closeAction.ajax";
		window.location.href = action; */
}
 function cancelAction(){
		var categoryCode = $("#categorysave").val();
		var system = $("#systemsave").val();
		
		var categoryDescription = $("#categoryDescription").val();
		var serviceTypesave = $("#serviceTypesave").val();
		
		editMapping(categoryCode,system,serviceTypesave,categoryDescription);
}


</script>

<body >
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}"/>




<form name="categoryMappingForm"  method = "POST">
	<input type="hidden" name="exclusionIDForSave" id="exclusionIDForSave" value="" />
	<input type="hidden" name="hiddenURLForPrimary" id="hiddenURLForPrimary" value="" />
	<input type="hidden" name="hiddenURLForType" id="hiddenURLForType" value="" />
	<input type="hidden" name="primaryErrorCode" id="primaryErrorCode" value="" />
	<input type="hidden" id="pageName" value="headerRuleMappingPage"/>
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>

<div class="innerContainer" style="height:98%" class="Pd3">

<div id="exclusionEditContainer1" >

		<div id="exclusionSectionOne1" >
		 		<div id="exclusionTitleBarSectionOne1">
		 			 			 		<div class="headerTitleExclusion">Locate Category Code</div>	
		 			 			 		
		 		</div>
		 		
		 		
			 	<div id="exclusionContentSectionOne1">
					<table style="margin-top:3px;" class="mt10 mL10 Pd3 shadedText" id="exclusionTableWidth1" border="0" cellspacing="0" cellpadding="0" >
						<TBODY>
							<tr class="createEditTable1-Listdata" style="padding-top: 0px">
										<td   width="200px" class="headTextExclusions">Category Code</td>
										<td  width="152px" ><input type="text" class="inputbox60" id="categoryCode" name="categoryCode" value="${categoryCode}" /></td>
											<td    width="152px" >&nbsp;</td>	
										<td    width="152px" class="headTextExclusions">System</td>
										<td  width="152px" ><input type="text" class="inputbox60" id="system" name="system" value="${system}"/></td>
															<td  class="headText"  width="152px">&nbsp;</td>		
							</tr>	
							<tr class="createEditTable1-Listdata" style="padding-top: 0px">
										<td   style="width: 120px" class="headTextExclusions">Category Description</td>
										<td style="width: 76px" > <input type="text" class="inputbox60" id="categorydesc" name="categorydesc" value="${categorydesc}" /></td>
											<td    style="width: 76px" >&nbsp;</td>	
										<td   style="width: 76px" class="headTextExclusions">EB03</td>
										<td style="width: 76px"><input type="text" class="inputbox60" id="EB03" name="EB03"  value="${EB03}" /></td>
												<td    style="width: 76px" >&nbsp;</td>									
							</tr>	
							<tr>
								<td align="center" colspan="6">
											<a href="#"><IMG id="Locate" src="${imageDir}/locate_but.gif" width="70" height="18" alt="Locate"
												onclick="fetchCategory();"></a>
								</td>	
								
							</tr>
							
					 </TBODY>   
				</table>
				</div>
		 	
		</div>
				
			<div id="exclusionSectionTwo1" style="display: none" >
		 		 			 		<div class="exclusionTitleBarSectionTwo1">
		 		 			 		<table  class="mappedTable1 pd3 shadedText" border="0" width="100%">
			 								<THEAD>
												<tr >
													<th id="systemId" width="10%" class="tableHeader">
													Category Code
														
													</th> 
													<th id="systemId" width="29%" class="tableHeader">
													Category Description
														
													</th> 
													<th id="variableWpdId" width="47%" class="tableHeader">
													Service Type
														
													</th>
													<th id="descId" width="7%" class="tableHeader">
													System
														
													</th>
													<th id="createdId" width="7%" class="tableHeader" >
														&nbsp;
														
													</th>
													
												</tr>
											</THEAD> 			
		 			 			 		</table>
								</div>
		 		
		 		
		 		<div id="exclusionContentSectionTwo1" style="width:926px;" >
		 			<div id="dynamicRenderer" >
		 				<jsp:include flush="true" page="categorymappingsearchresult.jsp"></jsp:include>	
		 			</div>
		 			<br>
	</div>
	</div>	 	
	<div id="exclusionSectionThree1" style="display: none;height:185px">
				 		<div class="exclusionTitleBarSectionTwo">
		 			 			 		<div class="headerTitleSub">Manage</div>	
		 				</div>
		 				<!-- <div class="exclusionSectionThreeSub1"> -->
		 				<div id="editpage" >
		 						<jsp:include flush="true" page="editcategorymapping.jsp"></jsp:include>	
		 				</div>
		 				
		 				<!-- </div> -->
		 				
		 				
	</div>
	
	 		 

	
	</div> <!-- Edit container ends here -->
	

		
		


</div> <!-- Inner container ends here -->
</div> <!-- Container ends here -->
	
	
	
<!-- Wrapper ends here -->

 
    <div id="createupdate" class="footer"  style="display: none;">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
				
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='backAction()'>Back</a></td>
				    <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
			        <td width="0" height="20"><a href="#" onclick ='save()'>Save</a></td>
			        
			        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
			        <td width="0" height="20"><a href="#" onclick='cancelAction()'>Cancel</a></td>
			     
		       
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				
		      </tr>
		    </table>					
		</div>	
		
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
				<td width="0" height="20"><a href="#" onclick ='backAction()'>Back</a></td>
				<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</tr>
			</table>

</div>
    </div>
    
   
		 <script>
	       	sniffer();
	    
       	</script>
 </form>  
<c:if test="${ fromaction == 'DELETE' || fromaction == 'SELECT' || fromaction == 'UPDATE' || fromaction == 'CREATE'}">
<script>
document.getElementById('dynamicRenderer').style.display= 'block';
document.getElementById('exclusionSectionTwo1').style.display= 'block';

</script>
</c:if>  
<c:if test="${ fromaction == 'DELETE' || fromaction == 'SELECT' }">
<script>
document.getElementById('initialLoad').style.display= 'block';
						document.getElementById('createupdate').style.display= 'none';

</script>
</c:if>  
<c:if test="${ fromaction == 'CREATE' || fromaction == 'UPDATE' }">
<script>
document.getElementById('initialLoad').style.display= 'none';
						document.getElementById('createupdate').style.display= 'block';

</script>
</c:if>  
<c:if test="${ fromaction == 'UPDATE' || fromaction == 'CREATE'}">
<script>

document.getElementById('exclusionSectionThree1').style.display= 'block';
						document.getElementById('editpage').style.display= 'block';
				

</script>
</c:if>  
<div id="viewHistoryDialog" title="View History">
</div>
<div id="hippaCodePopUpDiv">
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
<div id="referenceDataDialog"></div>

</div>

<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<%@include file="/WEB-INF/jspf/messageTray.jspf"%>


</body>
</html>
