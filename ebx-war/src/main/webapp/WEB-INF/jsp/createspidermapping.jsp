<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<title>Enterprise Blue Exchange</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/json.js"></script>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<script type="text/javascript">

var createList = [];
var eb03AutoSuggestionList = [];

function createUmRule() 
{
		var duplicateEb03 = $('#duplicateEb03').val();
		
        var mappingValue=[];
        var eb03Value=[];
        var umRuleId = $('#umRuleId').val();
        var umRuleDesc = $('#umRuleDesc').val();
        var comment =  "";
        var eb03EmptyFalg = false;
        var inValidEb03Flag = false;
 
        $('#ebCodesTable > tbody  > tr').not(":first").each(function(index, tr) 
        {
	        var mappingObject = new Object();
			mappingObject.eb03Value =$(this).find("td:eq(0) input[type='text']").val();
			mappingObject.eb03DefaultValue = $(this).find("td:eq(3) input[type='hidden']").val();
			mappingObject.msgValue = $(this).find("td:eq(4) input[type='text']").val();
			var labelId = $(this).find("td:eq(1) label").html();
			
			mappingValue.push(mappingObject);
			
			if(mappingObject.eb03Value == "" || mappingObject.eb03Value == undefined){
			eb03EmptyFalg = true;
			}else if(labelId == "Invalid Eb03 Value" || labelId == "" || labelId === undefined){
			  inValidEb03Flag = true;
			}
			else{
			 eb03Value.push(mappingObject.eb03Value)  ; 
			}  
        });
        
        comment=  $('#comments').val();
       
        console.log(JSON.stringify(mappingValue, undefined, 4));
      
        if(duplicateEb03 != ""  ||  eb03EmptyFalg )
		{
			if(eb03EmptyFalg){
				alert("Eb03 value is not provided. Please enter valid EB03");
			}else{
				return;	
			}
		}
		else if(inValidEb03Flag){
			alert(" Please enter valid EB03 value");
		}
		else
		{
		$.ajax({
		    url: "${context}/viewcreatemappingpagespider/create.ajax",
			dataType: 'html',
			type: "POST",	
			data: "&umRuleId="+encodeURIComponent(umRuleId)+"&umRuleDesc="+encodeURIComponent(umRuleDesc)+
			"&comment="+encodeURIComponent(comment)+"&mappingValues="+encodeURIComponent(JSON.stringify(mappingValue))+"&eb03Value="+encodeURIComponent(eb03Value),
			success: function(data) {
		
				if(eb03Value=="")
				{
				  alert("Atleast one code to be associated");
				  location.reload();
				}
				else
				{
				  alert("mapping saved successfully");
				  cancel();
				}			
			}
		});
	  }
	  
	  
 }

function deleteRowForRuleEb03(ebTr,eb03)
{
   var contextPath = $('#contextPath').val();
   var tr = $('#'+ebTr);
   var umRuleId = $('#umRuleId').val();
   var rowCount = $('#ebCodesTable > tbody > tr').length;
   if(eb03 !="" ){
   var eb03Value = $('#'+eb03).val();
   }
     
	  if(confirm('Are you sure you want to delete ? '))
	  {	  
	  	//createList.splice(createList.indexOf(eb03Value),1);
	  		
	    var child = $('#'+ebTr).nextAll();				
  		child.each(function () {
 
        var trId =   $(this).attr('id');
        
        var seq = parseInt(trId.split("ebTr")[1])-1;
   
        var eb03 = "";  
        $('#'+trId).find('td').each (function() {
            
          var id = $(this).find('input').attr('id');
  
          if(id !=  undefined ){
          if (id.indexOf('eb03DefaultDesc')>=0){
            $(this).find('input').attr('id', 'eb03DefaultDesc'+seq); 
          }else  if (id.indexOf('eb03')>=0){
            $(this).find('input').attr('id', 'eb03'+seq); 
            eb03 = $(this).find('input').val();
          }else if (id.indexOf('msg')>=0){
            $(this).find('input').attr('id', 'msg'+seq); 
          }
          }else if($(this).find('select').length >=1){
          var id = $(this).find('select').attr('id');
          if (id.indexOf('eb03Default')>=0){
            $(this).find('select').attr('id', 'eb03Default'+seq); 
          } 
          }else{
           var lid = $(this).find('label').attr('id');
           if(lid != undefined){
           if (lid.indexOf('EB03IdLabel')>=0){
            $(this).find('label').attr('id', 'EB03IdLabel'+seq); 
          }
          }else{
             var bid = $(this).find('img').attr('id');
             if(bid !=undefined){
               if (bid.indexOf('delete')>=0){          
		           Q = document.createElement("img");
			       Q.setAttribute("id","delete"+seq);
			       Q.setAttribute("src", contextPath+"/images"+"/delete_button.gif");
			       Q.setAttribute("onclick","deleteRowForRuleEb03('ebTr"+seq+"','"+eb03+"');");
		         
		           $(this).html('<a href="#">'+Q.outerHTML+'</a>' );
         
             }          
           }          
          }
         }                
         console.log(id);              
        });	
       $(this).attr('id', 'ebTr'+seq);
	  });
	  
	    tr.remove();
	    createList = [];
		$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
		{
			var existingEb03 = $(this).find("td:eq(0) input[type='text']").val();
			var labelId = $(this).find("td:eq(1) label").html();
			if(labelId != "" && labelId != undefined && labelId != "Invalid Eb03 Value"){
				createList.push(existingEb03);
			}
		   //createList.push($(this).find("td:eq(0) input[type='text']").val());
		});	
		
		var tmpCount = ebTr.replace("ebTr","");
		var rowCountTmp = $('#ebCodesTable > tbody > tr').length;
		for(var i = tmpCount ; i <= rowCountTmp ; i++){
		    var tmpEb03Label = "EB03IdLabel"+i;
		    var eb03Tmp = "eb03"+i;
			autoCompleteForDynamicEb03(eb03Tmp, i,tmpEb03Label);	
		}		
		alert("Deleted Successfully");
	  }
	  else
	  {
	    return false;
	  }
			
}


var list = new Array();
var eb03List = new Array();
var eb03default = new Array(); //added by Ravi
$(document).ready(function(){
	$('#eb031').blur(function() {
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel1").text('');
	   // $("#eb03Default0").text('');
	   }
	   var eb03 = $('#eb031').val();
			if(eb03 != undefined) {
			    eb03 = eb03.toUpperCase();
			     $('#eb031').val(eb03);
			}
	   
	 //  $('#eb031').val($('#eb031').val().toUpperCase());

	});  
	$("#eb031").autocomplete({ 
		select: function(event, ui) { $("#EB03IdLabel1").text(ui.item.id).removeClass('invalid_hippacode_value');},
		source: function(request, response) {
					$.ajax({
						url:"${context}/ajaxeb03autocompletelistforebx.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term + "&name=EB03",
						success: function(data) {
						var found_names = data.rows;
	                    found_names = found_names.filter(function(val) {
	                    eb03List.push(val.value);
	                    eb03default[val.value] = val.eb03DefaultValue;  //added by Ravi
	                    return createList.indexOf(val.value) == -1;
	                    });
                        console.log("createList"+createList);
					  //found_names = found_names.filter(val => !createList.includes(val.value));
						console.log(found_names);
						cache: false,
						response(found_names);
						list = found_names;
						eb03AutoSuggestionList = list;
						}
					});
				},
		change: function(event, ui) {
			var text = $(this).val();
			var tmpEb03List = [];
			for(var tmp in eb03AutoSuggestionList){
			
				tmpEb03List.push(eb03AutoSuggestionList[tmp].value);
			}
			var positionVal = jQuery.inArray(text, tmpEb03List);
			
			if(!ui.item){
			
				   if(positionVal > -1)
				   {

				     document.getElementById('EB03IdLabel1').innerHTML =  eb03AutoSuggestionList[positionVal].id;
	   
				   }else
				   {
				   if(!(createList.indexOf(text) == -1)){
						console.log("value in if"+text);
						document.getElementById('duplicateEb03').value ="DuplicateEb03";
						alert("EB03 "+$('#eb031').val().toUpperCase()+" value already exist");
						$('#eb031').val("");
						
				   }

				    else
				    {
					   displayLabelForSelectedItem(text,list,"EB03IdLabel1","Invalid Eb03 Value");
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
		close: function(event, ui) {
			//var text = $(this).val();
			document.getElementById('duplicateEb03').value = "";
			createList = [];
			console.log("eb03 value before push method1 ");
			var eb03 = $('#eb031').val();
			if(eb03 != undefined) {
			    eb03 = eb03.toUpperCase();
			}
			console.log("eb03 method1 "+eb03);
			
			$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
			{
				var labelId = $(this).find("td:eq(1) label").html();
				if(labelId != "" && labelId != undefined){
					createList.push($(this).find("td:eq(0) input[type='text']").val());
				}
			});
			
			if(!(eb03List.indexOf(eb03) == -1)){
			createList.push(eb03);
			}
			$('#eb03Default1').val(eb03default[eb03]);   //added by Ravi
			$('#eb03DefaultDesc1').val(eb03default[eb03]); // added by Dipalee
			if(eb03default[eb03] == undefined || eb03default[eb03] ==""){
			alert("Default value not mapped, Please map Default value in reference data");
			$('#eb031').val(eb03);
			//$('#eb03Default1').attr("disabled", false);
			}else{
			$('#eb03Default1').attr("disabled", true);
			}
			
			
			console.log("after push method1 "+createList); 

		}
								
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
	var previousRow = 0;
	var cellId = cellId1;
	
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
	
	B = document.createElement("label");
	B.setAttribute("type","hidden");
	B.setAttribute("id",labelId1);
	B.setAttribute("name",'EB03IdLabel');
	td2.style.width = "100px";
	td2.appendChild(B); 
	
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
	
	E = document.createElement("select");
	E.setAttribute("disabled", true);
	E.setAttribute("id",cellId2);
	E.setAttribute("name",$.trim(cellName2));
	E.setAttribute("onchange","setDefaultVal('"+cellId2+"','"+Eb03DefaultVal+"');");
	E.add(R);
	E.add(S);
	E.add(T);
	E.add(U);
	E.className="dropdown136";
	td3.style.width = "100px";
	td3.appendChild(E);
		
	F = document.createElement("input");
	F.setAttribute("type","hidden");
	F.setAttribute("id","eb03DefaultDesc"+rowCount);
	F.setAttribute("name",'eb03DefaultDesc');
	td4.style.width = "100px";
	td4.appendChild(F); 
	
	if(isComingFromAddButton){
		cellId3 = cellId3+rowCount;
		cellName3 = cellName3+rowCount;
	}
	
	P = document.createElement("input");
	P.setAttribute("type","text");
	P.setAttribute("id",cellId3);
	P.setAttribute("name",$.trim(cellName3));	
	P.style.width="300px"
	P.style.height="20px"
	P.setAttribute("value",text);
	td5.style.width = "300px";
	td5.appendChild(P);
	
	var TrForDelete = "ebTr"+rowCount;
	
	Q = document.createElement("img");
	Q.setAttribute("id","delete"+rowCount);
	Q.setAttribute("src", contextPath+"/images"+"/delete_button.gif");
	Q.setAttribute("onclick","deleteRowForRuleEb03('"+TrForDelete+"','"+cellId1+"');");
	td6.style.width = "50px";
	td6.appendChild(Q);
	
	row.setAttribute("id",TrForDelete);
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	row.appendChild(td5);
	row.appendChild(td6);
	
	tbody.appendChild(row);	
			
		//var clmnCount2 = count-1;
		//autoCompleteForDynamicDefaultEb03(cellId2,count,labelId1); 
		autoCompleteForDynamicEb03(cellId1, rowCount,labelId1,cellId2);
    
	rowCount = $('#ebCodesTable > tbody > tr').length;	
	if(rowCount>=99){
		$('#eb03AddButtonforRule').hide();
	}
	return true;	
}

function autoCompleteForDynamicEb03(cellId,count,labelId1,cellId2)
{
	$('#'+cellId).blur(function() 
	{
	   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
	   	$("#EB03IdLabel"+count).text('');
	   }
	   if($('#'+cellId).val() === undefined){
	      $('#'+cellId).val($('#'+"eb03"+(count -1)).val().toUpperCase());
	   }else{
	   		 $('#'+cellId).val($('#'+cellId).val().toUpperCase());
	   }
	   
	}); 
	$('#'+cellId).autocomplete(
	{
		select: function(event, ui) {$("#EB03IdLabel"+count).text(ui.item.id).removeClass('invalid_hippacode_value');},
		source: function(request, response) {
					$.ajax({
						url: "../ajaxeb03autocompletelistforebx.ajax",
						dataType: "json",
						data: "key="+request.term + "&name=EB03",
						success: function(data) {
						var found_names = data.rows;
				        found_names = found_names.filter(function(val) {
				        eb03List.push(val.value);
				        eb03default[val.value] = val.eb03DefaultValue;	//added by Ravi
				        return createList.indexOf(val.value) == -1;
				        });
                          console.log("createList"+createList);
						console.log(found_names);
						cache: false,
						response(found_names);
						list = found_names;
						eb03AutoSuggestionList = list;
						}
					});
				},
		change: function(event, ui) {
			var text = $(this).val();
			var tmpEb03List = [];
			for(var tmp in eb03AutoSuggestionList){
			
				tmpEb03List.push(eb03AutoSuggestionList[tmp].value);
			}
			var positionVal = jQuery.inArray(text, tmpEb03List);
			
			if(!ui.item){
			
				   if(positionVal > -1)
				   {

				     document.getElementById(labelId1).innerHTML =  eb03AutoSuggestionList[positionVal].id;
	   
				   }else
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
		close: function(event, ui) {
			document.getElementById('duplicateEb03').value = "";
			createList = [];
			//var eb03 = $('#'+cellId).val().toUpperCase();
			
			 var eb03 ;
			if( $('#'+cellId).val() === undefined){
				eb03 = "";
			}else{
				 eb03 = $('#'+cellId).val().toUpperCase();
			}			
			$('#ebCodesTable > tbody > tr').not(":first").each(function(index, tr)
			{
			var existingEb03 = $(this).find("td:eq(0) input[type='text']").val();
				if(!(jQuery.inArray(existingEb03, eb03List)=== -1)){
					createList.push(existingEb03);
				}
			});
			console.log("eb03 method2 "+eb03);
			if(!(eb03List.indexOf(eb03) == -1)){
			if(jQuery.inArray(eb03, createList)=== -1){
			   createList.push(eb03);
			}
			}
		$('#eb03Default'+count).val(eb03default[eb03]);	
		$('#eb03DefaultDesc'+count).val(eb03default[eb03]);
		
		if(eb03default[eb03] == undefined || eb03default[eb03] ==""){
			alert("Default value not mapped, Please map Default value in reference data");
			$('#eb03'+count).val(eb03);
			//$('#eb03Default'+count).attr("disabled", false);
			}else{
			$('#eb03Default'+count).attr("disabled", true);
			}
			console.log("after push method2 "+createList); 
		}		
	});
}

function displayLabelForSelectedEb03(text,list,labelId,defaultId,descForInvalidMsg){
	var valid = false;
	var desc = "";
	var defaultVal = "";
	text = $.trim(text);
		$.each(list , function(index, rows){
			var val = rows.value;
			if(val.toUpperCase()==text.toUpperCase()){
				valid = true;
				desc = rows.id;
				if(rows.eb03DefaultValue !=""){
				defaultVal = rows.eb03DefaultValue; 
				}
			}
			}
		);
		if(text != null && text.length > 0){
			if(valid){
				$('#'+labelId).text(desc).removeClass('invalid_hippacode_value');
				$('#'+defaultId).text(defaultVal).removeClass('invalid_hippacode_value');
				return true;
			}else{
				$('#'+labelId).text(descForInvalidMsg).addClass('invalid_hippacode_value'); 
				return false;
			}
		}
}	
	
	
 
// Cancel action 
function cancel(){
		//document.getElementById("loader-parent").style.visibility = "visible";
		document.forms['submitCreateForm'].action="../ebxspiderworkflow/cancel.html";
        document.forms["submitCreateForm"].submit();

}

function setDefaultVal(eb03Default,defaultVal)
{
   var eb03Default = $('#'+eb03Default).val();
   //var defaultVal = $('#'+defaultVal).val();
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


/***************JAN 2022 Release spider EBX END****************/	
	
</script>
</head>

<body>
<form name="submitCreateForm" >

	<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
	
	<%@include file="/WEB-INF/jspf/pageTop.jspf"%>
				
          <div class="innerContainer" style="height:100%;overflow-y:hidden">
          <div class="titleBar">
			<div class="headerTitle">Create Spider UM Rule Mapping</div>
		  </div>

          <div id="variableInfoDiv" class="overflowContainerVariable">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Pd3">
                <THEAD>
						<tr class="createEditTable1">
		                    <td width="200" class="tableHeader">UM Rule ID</td>
		                    <td width="300" class="tableHeader">Description</td>
		                    <td width="200" class="tableHeader">Status</td>		     						
	                  	</tr>
				</THEAD>
				<TBODY>
					<c:set var="rowCount" value="0" />					
					<tr>
					    <td>${ruleIdWithInfoList[0].umRuleId}</td>
						<td>${ruleIdWithInfoList[0].umRuleDesc}</td>
						<td>${ruleIdWithInfoList[0].umStatus}</td>					
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
	                     <input type="hidden" id="umRuleId" name="umRuleId" value="${ruleIdWithInfoList[0].umRuleId}"/>
	                     <input type="hidden" id="umRuleDesc" name="umRuleDesc" value="${ruleIdWithInfoList[0].umRuleDesc}"/>
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
	                  <tr class="alternate" id="ebTr1">                   
						<td width="100px" >						
							<input type="text" name="eb03Val"  id="eb031"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[0].value}"/>
						</td>		
						<td width="200px">
							<label id="EB03IdLabel1" for="eb03" style="font-size:11px">	
							   <c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}" />					
							</label>					
						</td>
						<td width="100px" >						
							<%-- <input type="text" name="eb03DefaultVal"  id="defaultVal1"  class="inputbox60" value="${spiderUmRuleMapping.umEB03Default}"/> --%>
							<select class="dropdown136" id="eb03Default1" disabled="disabled" name="eb03Default" class="inputbox60" value="${spiderUmRuleMapping.umEB03Default}" onchange="setDefaultVal('eb03Default1','eb03DefaultDesc1')"/>>
										<option value = "" label="Select"></option>			
										<option value = "Y" label="Yes"></option>
										<option value = "N" label="No"></option>
										<option value = "U" label="Unknown"></option>
			                </select>
				   	    </td>
						<td width="100px">											
							<input type="hidden" id="eb03DefaultDesc1" name="eb03DefaultDesc" value="${spiderUmRuleMapping.umEB03Default}"/>
						</td>    
						 <td width="300px" >						
								<input type="text" name="msgVal"  id="msg0"  style="height: 20px; width:300px; " value="${spiderUmRuleMapping.umMessage}"/>
						</td>
						<td width="50px" >						
								<a href="#"><img alt= "Delete Row" title="Delete Row" id="deleteRow0" src="${imageDir}/delete_button.gif" width="22" height="19" onclick="deleteRowForRuleEb03('ebTr1','eb030');"/></a>
						</td>
	                  </tr>				
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
         
</div>
<table style="margin-left: 10px">
	<tr>
	  <td style="margin-left: 10px">Comments:</td>
	</tr>
	<tr></tr>
    <tr>
      <td style="margin-left: 10px">
          <textarea id="comments" rows="3" cols="30" maxlength="250" ></textarea>
          <input type="hidden" id="duplicateEb03" />
      </td>
    </tr>
</table>

<div id="umMappingDeleteDialog" style="display: none;">
	<form name="umMappingDeleteDialogForm">
	 
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td><a><img
						id="sendToTestSaveImg" src="${imageDir}/btn_Save.JPG" alt="Save"
						title="Save" /></a></td>
						<td><a  href="#" name="delCancel" id="delCancel" onclick="cmtDeleteDailogClose();" >
						<img src="${imageDir}/btn_Cancel.JPG" alt="Cancel" title="Cancel"/>
				</a></td>
			</tr>
		</table>
	
	</form>
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
        <td width="20" height="20"><img src="${imageDir}/footer_left.gif" width="20" height="20" /></td>
        <td width="9" height="0"><a href="#"  id="create" onClick='createUmRule()'>Create</a></td>		       
        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
        <td width="0" height="20"><a href="#" onclick ='cancel();'>Cancel</a></td>
		<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
      </tr>
    </table>					
</div>

</form>

</body>
</html>