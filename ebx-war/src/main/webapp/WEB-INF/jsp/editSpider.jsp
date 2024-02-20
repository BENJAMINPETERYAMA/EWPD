<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<title>Enterprise Blue Exchange</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<style type="text/css">
 input.inputbox460{
                width:300px;
                border: 1px solid #868686;
                height:20px;
                padding-left:3px;
                }
</style>
<script type="text/javascript">

function loadComments()
{  
    document.getElementById("comments").innerHTML = document.getElementById("comment").value;
    $('#ebCodesTable > tbody  > tr').not(":first").each(function(index, tr) 
    {
            var mappingObject = new Object();
			mappingObject.eb03Default =$(this).find("td:eq(2) select").val();
			if(mappingObject.eb03DefaultValue == "N")
			{
			   $("#eb03Default"+index).val("N");
			}
    }); 
}

function cancel(ruleId)
{
		//document.getElementById("loader-parent").style.visibility = "visible"; // Added for processing Spinner
		document.forms['ebxspiderworkflowForm'].action="../ebxspiderworkflow/cancel.html";
        document.forms["ebxspiderworkflowForm"].submit();
}
 
function openViewHistoryDialog(ruleId)
{
		$.ajax({
			url: "${context}/locatespiderresults/viewCommentHistory.ajax",
			dataType: "html",
			type: "POST",
			data: "ruleId="+encodeURIComponent(ruleId),
			success: function(data) {
				$("#viewHistoryDialog").html(data);
				$("#viewHistoryDialog").dialog({
					height:'450',
					width:'600',
					resizable : 'false',
					modal: true
				});
				$("#viewHistoryDialog").dialog();
			}
		});
}   

var createList = [];
var eb03AutoSuggestionList = [];

function editUMRuleMapping(ruleId,status) 
{
        document.getElementById('duplicateEb03').value = "";
        var duplicateEb03 = $('#duplicateEb03').val();
        var eb03EmptyFalg = false;
        var inValidEb03Flag = false;
 
        
        var mappingValue=[];
        
        var umRuleId = $('#umRuleId').val();
        var umRuleDesc = $('#umRuleDesc').val();
        var comment =  "";
       
        $('#ebCodesTable > tbody  > tr').not(":first").each(function(index, tr) 
        {
	        var mappingObject = new Object();
			mappingObject.eb03Value =$(this).find("td:eq(0) input[type='text']").val();
			mappingObject.eb03DefaultValue = $(this).find("td:eq(3) input[type='hidden']").val();
			mappingObject.msgValue = $(this).find("td:eq(4) input[type='text']").val();
			var labelId = $(this).find("td:eq(1) label").html();
			
			if(mappingObject.eb03Value == "" || mappingObject.eb03Value == undefined){
			eb03EmptyFalg = true;
			}else if(labelId == "Invalid Eb03 Value" || labelId == ""){
			  inValidEb03Flag = true;
			}
			
			mappingValue.push(mappingObject);
	                    
        });
        
        comment=  $('#comments').val();
      
      	if(duplicateEb03 != ""  ||  eb03EmptyFalg )
		{
			if(eb03EmptyFalg){
			alert("Eb03 value is not provided. Please enter valid EB03");
			}else{
			return;	
			}
		
		}
		else if(inValidEb03Flag)
		{
			alert(" Please enter valid EB03 value");
		}
		else
		{
			$.ajax({
			    url: "${context}/viewcreatemappingpagespider/edit.ajax",
				dataType: 'html',
				type: "POST",
				data: "&status="+encodeURIComponent(status)+"&umRuleId="+encodeURIComponent(umRuleId)+"&umRuleDesc="+encodeURIComponent(umRuleDesc)+
				"&comment="+encodeURIComponent(comment)+"&mappingValues="+encodeURIComponent(JSON.stringify(mappingValue)),
				success: function(data) {
				    if(status=='NOT_APPLICABLE')
					{
					  cancel();			
					}
					else
					{
						if(status=='BUILDING')
						{
						  alert("mapping updated successfully");			
						}
						else if(status=='SCHEDULED_TO_TEST')
						{
						  alert("mapping sent to test successfully");			
						}
						else if(status=='SCHEDULED_TO_PRODUCTION')
						{
						  alert("mapping approved successfully");			
						}
						location.reload();
					}
				}
			});
		}
 }

function deleteRowForRuleEb03(ebTr,eb03,count)
{
   var contextPath = $('#contextPath').val();
   var tr = $('#'+ebTr);
   var umRuleId = $('#umRuleId').val();
   var eb03Value = $('#'+eb03).val();
   var rowCount = $('#ebCodesTable > tbody > tr').length;
   
   var nextEb03value = $('#eb03'+count).val();
   if(rowCount==2 || nextEb03value=="")
   {
		if(confirm('If you delete this mapping then entire um rule mapping will be deleted'))
        {
          $.ajax({
				url: "${context}/viewcreatemappingpagespider/deleteSpiderMapping.ajax",
				dataType: "html",
				type: "POST",
				data: "&ruleId="+encodeURIComponent(umRuleId)+"&eb03Value="+encodeURIComponent(eb03Value)+"&rowCount="+encodeURIComponent(rowCount),
				success: function(data) {
				
				}
		  });
          alert("Deleted Successfully"); 
		  cancel();
		} 
		else
		 return false;
   }		
   else if(confirm('Are you sure you want to delete ? '))
   {	   			
	    var child = $('#'+ebTr).nextAll();	
	    			
  		child.each(function () 
  		{
        var trId =   $(this).attr('id');      
        var seq = parseInt(trId.split("ebTr")[1])-1;
   
        var eb03 = ""; 
         
        $('#'+trId).find('td').each (function() 
        {            
          var id = $(this).find('input').attr('id');
  
          if(id !=  undefined )
          {
	          if (id.indexOf('eb03DefaultDesc')>=0)
	          {
	            $(this).find('input').attr('id', 'eb03DefaultDesc'+seq); 
	          }
	          else if (id.indexOf('eb03')>=0)
	          {
	            $(this).find('input').attr('id', 'eb03'+seq); 
	            eb03 = $(this).find('input').val();
	          }
	          else if (id.indexOf('msg')>=0)
	          {
	            $(this).find('input').attr('id', 'msg'+seq); 
	          }
          }
          else if($(this).find('select').length >=1)
          {
            var id = $(this).find('select').attr('id');
            if (id.indexOf('eb03Default')>=0)
            {
              $(this).find('select').attr('id', 'eb03Default'+seq); 
            } 
          }
          else
          {
            var lid = $(this).find('label').attr('id');
            if(lid != undefined){
            if (lid.indexOf('EB03IdLabel')>=0)
            {
              $(this).find('label').attr('id', 'EB03IdLabel'+seq); 
            }
          }
          else
          {
             var bid = $(this).find('img').attr('id');
             if(bid !=undefined)
             {
               if (bid.indexOf('delete')>=0)
               {          
		           Q = document.createElement("img");
			       Q.setAttribute("id","delete"+seq);
			       Q.setAttribute("src", contextPath+"/images"+"/delete_button.gif");
			       Q.setAttribute("onclick","deleteRowForRuleEb03('ebTr"+seq+"','"+eb03+"');");
		         
		           $(this).html('<a href="#">'+Q.outerHTML+'</a>' );
         
	           }          
	         }          
	      }
	    }                            
	  });	
	  $(this).attr('id', 'ebTr'+seq);
   });
   
   $.ajax({
		url: "${context}/viewcreatemappingpagespider/deleteSpiderMapping.ajax",
		dataType: "html",
		type: "POST",
		data: "&ruleId="+encodeURIComponent(umRuleId)+"&eb03Value="+encodeURIComponent(eb03Value)+"&rowCount="+encodeURIComponent(rowCount),
		success: function(data) {
		
		}
	});
	
	tr.remove();
	createList = [];
	
	$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
	{
	    var existingEb03 = $(this).find("td:eq(0) input[type='text']").val();
		var labelId = $(this).find("td:eq(1) label").html();
		var tmpEb03Label = "EB03IdLabel"+(index + 1);
		$(this).find("td:eq(1) label").attr('id',tmpEb03Label);
		
		if(labelId != "" && labelId != undefined && labelId != "Invalid Eb03 Value")
		{
			createList.push(existingEb03);
		}
	});	
	alert("Deleted Successfully");
	
	var tmpCount = ebTr.replace("ebTr","");
	var rowCountTmp = $('#ebCodesTable > tbody > tr').length;
	for(var i = tmpCount; i <= rowCountTmp; i++)
	{
	    var tmpEb03Label = "EB03IdLabel"+i;
	    var eb03Tmp = "eb03"+i;
		autoCompleteForDynamicEb03(eb03Tmp, i,tmpEb03Label);	
	}
   }
   else
   {
     return false;
   }
		
}

var list = new Array();
var eb03List = new Array();
var eb03default = new Array();
$(document).ready(function(){
	$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
	{
		var count = index+1;
		$('#eb03'+count).blur(function() 
		{
			if( $(this).val()== null || $.trim($(this).val()).length < 1 ) 
			{
			  $("#EB03IdLabel"+count).text('');
			}
			$('#eb03'+count).val($('#eb03'+count).val().toUpperCase());		
		});
		$("#eb03"+count).autocomplete({
			select: function(event, ui) { $("#EB03IdLabel"+count).text(ui.item.id).removeClass('invalid_hippacode_value'); },
			source: function(request, response) {
			$.ajax({
				url: "${context}/ajaxeb03autocompletelistforebx.ajax",
				dataType: "json",
				type:"POST",
				data: "key="+request.term + "&name=EB03",
				success: function(data) 
				{
					var found_names = data.rows;
					found_names = found_names.filter(function(val) {
						eb03List.push(val.value);
						eb03default[val.value] = val.eb03DefaultValue;
						return createList.indexOf(val.value) == -1;
			        });
	
					cache: false,
					response(found_names);
					
					list = found_names;
					eb03AutoSuggestionList = list;
				}
			});
		},
		change: function(event, ui) 
		{
			var text = $(this).val();
			var tmpEb03List = [];
			for(var tmp in eb03AutoSuggestionList)
			{
			  tmpEb03List.push(eb03AutoSuggestionList[tmp].value);
	  		}
			var positionVal = jQuery.inArray(text, tmpEb03List);
			if(!ui.item)
			{
				if(positionVal > -1)
				{
					document.getElementById("EB03IdLabel"+count).innerHTML = eb03AutoSuggestionList[positionVal].id;
				}else
				{
					if(!(createList.indexOf(text) == -1)){
					document.getElementById('duplicateEb03').value ="DuplicateEb03";
					alert("EB03 "+$('#eb03'+count).val().toUpperCase()+" value already exist");
					$('#eb03'+count).val("");					
					}		
					else
					{
						displayLabelForSelectedItem(text,list,"EB03IdLabel"+count,"Invalid Eb03 Value");
					}
				}
			}
			
			createList = [];
			$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
			{
			  var existingEb03 = $(this).find("td:eq(0) input[type='text']").val();
			  createList.push(existingEb03);
			});
		
		},
		close: function(event, ui) 
		{
				document.getElementById('duplicateEb03').value = "";
				createList = [];
				
				var eb03 ;
				if( $('#eb03'+count).val() === undefined)
				{
				  eb03 = "";
				}
				else{
				  eb03 = $('#eb03'+count).val().toUpperCase();
				}
			
				$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
				{
					var existingEb03 = $(this).find("td:eq(0) input[type='text']").val();
					var labelId = $(this).find("td:eq(1) label").html();
					if(labelId != "" && labelId != undefined)
					{
					  createList.push(existingEb03);
					}
				});
				if(!(eb03List.indexOf(eb03) == -1))
				{
					if(jQuery.inArray(eb03, createList)=== -1)
					{
					  createList.push(eb03);
					}
				}
				
				$('#eb03Default'+count).val(eb03default[eb03]); //added by Ravi
				$('#eb03DefaultDesc'+count).val(eb03default[eb03]); // added by Dipalee
				if( (eb03default[eb03] == undefined || eb03default[eb03] ==""))
				{
				  alert("Default value not mapped, Please map Default value in reference data");
				  $('#eb03'+count).val(eb03);				
				}  
				else
				{
				  $('#eb03Default'+count).attr("disabled", true);
				}	
		}
		
	  })
	});
  });
		
function addRowForRuleEb03(tableID,cellId1,cellName1,cellId2,cellName2,cellId3,cellName3,text,isComingFromAddButton)
{
	var contextPath = $('#contextPath').val();
	var table = document.getElementById(tableID);
	var tbody = document.getElementById("ebTbody");
	var trClass = "";
	var labelId1= "";
	
	var rowCount = $('#ebCodesTable > tbody > tr').length;
	var clmnLength = $('#ebCodesTable > tbody > tr > td').length;
	if (rowCount >= 99) {
		$('#eb03AddButtonforRule').hide();
		return false;
	}
	
	if (rowCount % 2 != 0) {
		trClass = "alternate";
	} else {
		trClass = "white-bg";
	}
	var row = document.createElement("TR");
	row.className=trClass;
	var td1 = document.createElement("TD");
	var td2 = document.createElement("TD");
	var td3 = document.createElement("TD");
	var td4 = document.createElement("TD");
	var td5 = document.createElement("TD");
	var td6 = document.createElement("TD");
	
	var count = 0;
	var totalTdcount = 0;
	
	
	if(isComingFromAddButton){    
		//clmnCount = clmnLength/5;
		//count = Math.round(clmnCount);
		cellId1 = cellId1+rowCount;
		cellName1 = cellName1+rowCount;
		labelId1 = "EB03IdLabel"+rowCount;
	}

	A = document.createElement("input");
	A.setAttribute("type","text");
	A.setAttribute("id",cellId1);
	A.setAttribute("name",$.trim(cellName1));
	A.className="inputbox60";
	A.setAttribute("value",text);
	td1.style.width = "100px";
	td1.appendChild(A);
	
	B = document.createElement("input");
	B.setAttribute("type","hidden");
	B.setAttribute("id","EB03Desc"+rowCount);
	B.setAttribute("for",cellId1);
	td1.style.width = "150px";
	td1.appendChild(B); 
	
	C = document.createElement("label");
	C.setAttribute("type","hidden");
	C.setAttribute("id",labelId1);
	C.setAttribute("name",'EB03IdLabel');
	td2.style.width = "100px";
	td2.appendChild(C); 
	
	if(isComingFromAddButton){
		cellId2 = cellId2+rowCount;
		cellName2 = cellName2+rowCount;
	}
	
	R = document.createElement("option");
	R.setAttribute("value","");
	R.setAttribute("label","Select");
	td3.style.width = "100px";
	td3.appendChild(R);
	
	S = document.createElement("option");
	S.setAttribute("value","Y");
	S.setAttribute("label","Yes");
	td3.style.width = "100px";
	td3.appendChild(S);
	
	T = document.createElement("option");
	T.setAttribute("value","N");
	T.setAttribute("label","No");
	td3.style.width = "100px";
	td3.appendChild(T);
	
	U = document.createElement("option");
	U.setAttribute("value","U");
	U.setAttribute("label","Unknown");
	td3.style.width = "100px";
	td3.appendChild(U);
	
	var Eb03DefaultVal = "eb03DefaultDesc"+rowCount;
	
	D = document.createElement("select");
	D.setAttribute("disabled", true);
	D.setAttribute("id",cellId2);
	D.setAttribute("name",$.trim(cellName2));
	D.setAttribute("onchange","setDefaultVal('"+cellId2+"','"+Eb03DefaultVal+"');");
	D.add(R);
	D.add(S);
	D.add(T);
	D.add(U);
	D.className="dropdown136";
	td3.style.width = "100px";
	td3.appendChild(D);
	
	E = document.createElement("input");
	E.setAttribute("type","hidden");
	E.setAttribute("id","eb03DefaultDesc"+rowCount);
	E.setAttribute("name",'eb03DefaultDesc');
	td4.style.width = "100px";
	td4.appendChild(E); 
	
	if(isComingFromAddButton){
		cellId3 = cellId3+rowCount;
		cellName3 = cellName3+rowCount;
	}
	
	P = document.createElement("input");
	P.setAttribute("type","text");
	P.setAttribute("id",cellId3);
	P.setAttribute("name",$.trim(cellName3));
	P.className="inputbox460";
	P.setAttribute("value",text);
	td5.style.width = "250px";
	td5.appendChild(P);
	
	var TrForDelete = "ebTr"+rowCount;
	var countForDelete = rowCount+1;
	
	F = document.createElement("img");
	F.setAttribute("id","delete"+rowCount);
	F.setAttribute("src", contextPath+"/images"+"/delete_button.gif");
	F.setAttribute("onclick","deleteRowForRuleEb03('"+TrForDelete+"','"+cellId1+"','"+countForDelete+"');");
	td6.style.width = "50px";
	td6.appendChild(F);
	row.setAttribute("id",TrForDelete);
	
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	row.appendChild(td5);
	row.appendChild(td6);
	
	tbody.appendChild(row);	

	autoCompleteForDynamicEb03(cellId1, rowCount,labelId1);

	rowCount = $('#ebCodesTable > tbody > tr').length;	
	if(rowCount>=99){
		$('#eb03AddButtonforRule').hide();
	}
	return true;	
}

function autoCompleteForDynamicEb03(cellId,count,labelId1)
{
	$('#'+cellId).blur(function() 
	{
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) 
	   {
	   	  $("#EB03IdLabel"+count).text('');
	   }
	   if($('#'+cellId).val() === undefined)
	   {
	      $('#'+cellId).val($('#'+"eb03"+(count -1)).val().toUpperCase());
	   }
	   else
	   {
	      $('#'+cellId).val($('#'+cellId).val().toUpperCase());
	   }
	}); 
	$('#'+cellId).autocomplete(
	{
		select: function(event, ui) {$("#EB03IdLabel"+count).text(ui.item.id).removeClass('invalid_hippacode_value');},
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxeb03autocompletelistforebx.ajax",
						dataType: "json",
						data: "key="+request.term + "&name=EB03",
						success: function(data) {
						var found_names = data.rows;
				        found_names = found_names.filter(function(val) {
				        eb03List.push(val.value);
				        eb03default[val.value] = val.eb03DefaultValue;
				        return createList.indexOf(val.value) == -1;
				        });
                        
						cache: false,
						response(found_names);
						list = found_names;
						eb03AutoSuggestionList = list;
						}
					});
				},
		change: function(event, ui) 
		{
			var text = $(this).val();
			var tmpEb03List = [];
			for(var tmp in eb03AutoSuggestionList)
			{		
				tmpEb03List.push(eb03AutoSuggestionList[tmp].value);
			}
			var positionVal = jQuery.inArray(text, tmpEb03List);
			
			if(!ui.item){
			
				   if(positionVal > -1)
				   {
				     document.getElementById(labelId1).innerHTML =  eb03AutoSuggestionList[positionVal].id;	   
				   }
				   else
				   {
					   if(!(createList.indexOf(text) == -1)){
							console.log("value in if"+text);
							document.getElementById('duplicateEb03').value ="DuplicateEb03";
							alert("EB03 "+$('#'+cellId).val().toUpperCase()+" value already exist");
							$('#'+cellId).val("");
							
					   }
	
					   else
					   {
						   displayLabelForSelectedItem(text,list,"EB03IdLabel"+count,"Invalid Eb03 Value");
					   }
				   }
			}
				createList = [];   
				$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
				{
				  var existingEb03 = $(this).find("td:eq(0) input[type='text']").val();
				  createList.push(existingEb03);
				
			    });
			
		},
		close: function(event, ui)
        {
			
			document.getElementById('duplicateEb03').value = "";
			createList = [];
			
			var eb03 ;
			if( $('#'+cellId).val() === undefined)
			{
				eb03 = "";
			}
			else
			{
				eb03 = $('#'+cellId).val().toUpperCase();
			} 
			
			$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
			{
				var existingEb03 = $(this).find("td:eq(0) input[type='text']").val();
				var labelId = $(this).find("td:eq(1) label").html();
				if(labelId != "" && labelId != undefined)
				{
					createList.push(existingEb03);
				}
			});
			if(!(eb03List.indexOf(eb03) == -1))
			{
				if(jQuery.inArray(eb03, createList)=== -1)
				{
				   createList.push(eb03);
				}
			}
			
			$('#eb03Default'+count).val(eb03default[eb03]);	
			$('#eb03DefaultDesc'+count).val(eb03default[eb03]);
		
		   if( (eb03default[eb03] == undefined || eb03default[eb03] =="") )		   {
		    alert("Default value not mapped, Please map Default value in reference data");
			$('#eb03'+count).val(eb03);
		   }
		   else
		   {
			$('#eb03Default'+count).attr("disabled", true);
		   }			
		}			
	});		
}


function setDefaultVal(eb03Default,defaultVal)
{
   var eb03Default = $('#'+eb03Default).val();
   
   if(eb03Default == "N")
   {
     $('#'+defaultVal).val("N");
   }
   else if(eb03Default == "Y")
   {
     $('#'+defaultVal).val("Y");
   }
   else if(eb03Default == "U")
   {
     $('#'+defaultVal).val("U");
   }
} 

   
</script>

</head>
<body onload="loadComments()">

	<form name="ebxspiderworkflowForm">
	<input type="hidden" id="pageName" value="ruleMappingPage"/>
	<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
	
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>
		
          <div class="innerContainer" style="height:100%;overflow-y:hidden">
          <div class="titleBar">
			 <div class="headerTitle">Edit Spider UM Rule Mapping</div>
		  </div>
    
          <div id="variableInfoDiv" class="overflowContainerVariable">
              
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Pd3">
                  <THEAD>
						<tr class="createEditTable1">
		                    <td width="200" class="tableHeader">UM Rule ID</td>
		                    <td width="320" class="tableHeader">Description</td>
		                    <td width="200" class="tableHeader">Status</td>
		                 
	                  	</tr>
				</THEAD>
				<TBODY>
					<c:set var="rowCount" value="0" />					
					<tr>
					    <td>${mappedRules.umRuleId}</td>
						<td>${mappedRules.umRuleDesc}</td>
					    <td>${mappedRules.umStatus}</td>
					    
					</tr>
					
				</TBODY>             
                </table>
                
		  </div>
			
		  <div id="createEditContainer" style="margin-top:5px;">
				<c:if test="${variableInfoDivScroll == 'true'}">
					<script>
						$('#createEditContainer').height('335px');							
					</script>
				</c:if>
				<table>
				  <tr>
				      <td>
	                     <input type="hidden" id="umRuleId" name="umRuleId" value="${mappedRules.umRuleId}"/>
	                     <input type="hidden" id="umRuleDesc" name="umRuleDesc" value="${mappedRules.umRuleDesc}"/>
	                  </td>
				  </tr>
				</table>
			
				<table width="950px" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5" id="ebCodesTable">
					
					<tr class="createEditTable1-Listdata">            
	                    <td colspan="2" class="headText">BX - EB03</td>	                 
	                  	<td  colspan="2" class="headText">EB03 Default </td>	                  
	                    <td width="190px" colspan="2" align="left" class="headText"> BX - MSG(Optional)</td>
                    </tr>	
	                <tbody id="ebTbody">
	                  <c:if test="${! empty editResultsList}">
	                    <c:forEach items="${editResultsList}" var="editResults" varStatus="editresult">			      
		                    <tr class="createEditTable1-Listdata alternate" id="ebTr${editresult.index+1}">
								<td width="100px" >						
										<input type="text" name="eb03Val"  id="eb03${editresult.index+1}"  class="inputbox60" value="${editResults.eb03Value}"/>
								</td>
										
								<td width="200px">
									
									<label id="EB03IdLabel${editresult.index+1}" for="eb03" style="font-size:11px">	
									   <c:out value="${editResults.eb03Desc}" />					
									</label>					
									<input type="hidden" id="EB03Desc0" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/>
								</td>
			                   
								<td width="100px" >						
										<%-- <input type="text" name="eb03DefaultVal"  id="eb03Default${editresult.index+1}"  class="inputbox60" value="${editResults.eb03DefaultValue}"/> --%>								
								        <select class="dropdown136" id="eb03Default${editresult.index+1}" disabled="disabled"  name="eb03DefaultVal" ${(editResults.eb03DefaultValue != '' && editResults.eb03DefaultValue !=undefined) ? 'disabled="disabled"' : ''} class="inputbox60" value="${editResults.eb03DefaultValue}" onchange="setDefaultVal('eb03Default${editresult.index+1}','eb03DefaultDesc${editresult.index+1}')"/>>
												<option value = "" label="Select"></option>			
												<option value = "Y" ${editResults.eb03DefaultValue == 'Y' ? 'selected="selected"' : ''} label="Yes"></option>
												<option value = "N" ${editResults.eb03DefaultValue == 'N' ? 'selected="selected"' : ''} label="No"></option>
												<option value = "U" ${editResults.eb03DefaultValue == 'U' ? 'selected="selected"' : ''} label="Unknown"></option>
					                        </select>
								</td>
								<td width="100px">
									<input type="hidden" id="eb03DefaultDesc${editresult.index+1}" name="eb03DefaultDesc" value="${editResults.eb03DefaultValue}"/>
								</td>
								
								<td width="300px" >						
										<input type="text" name="msgVal"  id="msg${editresult.index+1}"  class="inputbox460" value="${editResults.msgValue}" />
								</td>
								<td width="50px" >						
										<a href="#"><img alt= "Delete Row" title="Delete Row" id="deleteRow" src="${imageDir}/delete_button.gif" width="19" height="19" onclick="deleteRowForRuleEb03('ebTr${editresult.index+1}','eb03${editresult.index+1}','${editresult.index+2}');" /></a>
								</td>
	                      </tr>
					
					  </c:forEach>
					  </c:if>
				     
				  </tbody>
			    </table>	
						
				<div>	
					<table width="950px" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5" id="ebCodesTable1">
						<tbody id="ebTbody">
							<tr class="createEditTable1-Listdata">	
		                    <td width="70px" >							
							</td>
		                    <td width="100px">
								<label  style="font-size:11px"></label>
							</td>
							 <td width="20px">&#160;</td>
		               		<td width="70px" >						
							</td>
							
		                    <td width="100px">
								<label  style="font-size:11px"></label>
							</td>
							<td width="20px">&#160;</td>
		                    <td width="70px" >						
							</td>
		                    <td width="100px">
								<label style="font-size:11px"></label>
							</td>
							 <td width="20px">&#160;</td>
							 <td width="70px" align="right" >
							 <a href="#"><img alt= "Add Row for EB03" title="Add Row for EB03" id="eb03AddButtonforRule" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForRuleEb03('ebCodesTable','eb03','eb03Val','eb03Default','eb03DefaultVal','msg','msgVal','','true');"/></a>
							</td>
		                    <td width="100px">
								<label  style="font-size:11px"></label>
							</td>
							</tr>
						</tbody>
					</table>
		        </div>	
           </div>
           
           <table style="margin-left: 10px">
	          <tr>
	            <td style="margin-left: 10px">Comments:</td>
	          </tr>
	          <tr></tr>
              <tr>
                <td style="margin-left: 10px">
                   <textarea id="comments" rows="3" cols="30" maxlength="250" value="${mappedRules.comment}" ></textarea></td>
                <td>
                   <input type="hidden" id="comment"  value="${mappedRules.comment}"/></td>
                   <input type="hidden" id="duplicateEb03" />
                </td>
              </tr>
           </table>
		  <!-- innerContainer Ends-->
		</div>
		
		<div class="footer">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
											
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>

		        <td width="0" height="20"><a href="#" id="save" onclick='editUMRuleMapping("${mappedRules.umRuleId}","BUILDING")'>Save</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick ='cancel("${mappedRules.umRuleId}");'>Cancel</a></td>
		       
				<c:if test="${mappedRules.umStatus !='NOT_APPLICABLE'}" > 		
						<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='editUMRuleMapping("${mappedRules.umRuleId}","NOT_APPLICABLE");'>Not Applicable</a></td>
				</c:if>
				
				<c:if test="${mappedRules.umStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mappedRules.umStatus != 'SCHEDULED_TO_TEST' &&
							mappedRules.umStatus != 'OBJECT_TRANSFERRED' &&
							mappedRules.umStatus != 'NOT_APPLICABLE' &&
							mappedRules.umStatus != 'PUBLISHED'}">		
						 <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='editUMRuleMapping("${mappedRules.umRuleId}","SCHEDULED_TO_TEST");'>Send to Test</a></td>
				</c:if>

				<c:if test="${mappedRules.umStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mappedRules.umStatus != 'NOT_APPLICABLE' &&
							mappedRules.umStatus != 'PUBLISHED'}">						
						<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='editUMRuleMapping("${mappedRules.umRuleId}","SCHEDULED_TO_PRODUCTION");'>Approve</a></td>				
				</c:if>
				
		        <td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</tr>
		    </table>					
		</div>
</form>

</body>

<script type="text/javascript">
var createList = [] ;
var tableId = document.getElementById('ebTbody');
var tableRow = tableId.getElementsByTagName('tr');
for (var t = 0; t < tableRow.length; t++){
var eb03TdValue = tableRow[t].getElementsByTagName('td')[0].getElementsByTagName('input')[0].value;
createList.push(eb03TdValue);
console.log(eb03TdValue+" push list "+createList);
}
</script>
</html>