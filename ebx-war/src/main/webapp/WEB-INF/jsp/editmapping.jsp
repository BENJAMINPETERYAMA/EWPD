<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hippaSegmentPopUp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umRuleUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hsd02Util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/createeditpage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json.js"></script>
<script type="text/javascript">

	// SSCR19537 April04- Start
	function displayIndvidualEB03AssnPanel() {

		if ($('#indEB03AssnCheckBoxId').is (':checked')) {
			
			$('#iii02Table').hide();
			$('#messageTable').hide()
			$('#eb03AssnTable').show();
		}
		if (!$('#indEB03AssnCheckBoxId').is (':checked')) {
			
			$('#iii02Table').show();
			$('#messageTable').show()
			$('#eb03AssnTable').hide();
		}
	}

	function populateEb03ValuesToEB03AssnList(){
		var newEb03Values = document.getElementById('eb03List').value;
		var existingEb03Values =  document.getElementById('eb03ExistingList').value;
		var result = createEB03AssnObject();
			if(result == true){
				var existingEb03Assn = document.getElementById('eb03AssnJsonObj').value;
				$.ajax({
				url: "${context}/editmapping/populateEb03AssociatedValues.html",
				dataType: "json",
				type: "POST",
				data: "newEb03Values="+newEb03Values+"&existingEb03Values="+existingEb03Values+"&existingEb03Assn="+existingEb03Assn,
				success: function(data) {
					cache: false,
					createEb03AssnTable(data.eb03AssnObjAsJsonArray);
				}
				});
			} else {
				createEb03AssnTable(null);
			}
			
	}
	
	function createEb03AssnTable(eb03AssnList) {
		var eb03AssnObjCount = countProperties(eb03AssnList);
		var table = document.getElementById("eb03AssnTable");
		var rowCount = table.rows.length;
		     
		if (rowCount % 2 == 0) {
			trClass = "alternate";
		} else {
			trClass = "white-bg";
		}
		
		 // Delete all rows:
		if ( rowCount > 0 ) {
			try {             
				for(var i = 0; i <= rowCount; i++) { 
		  		var row = table.rows[i];
				table.deleteRow(i);     
		      	rowCount--;                     
		      	i--;                     
				}          
		 	} catch(e) {                 
		 	} 
		}
		var count = 0;
		var row = table.insertRow(count); 
		row.className = "createEditTable1-Listdata";
		
		var headCell0 = row.insertCell(0);
		headCell0.colSpan = "2";		
		var headElement0 = document.createElement("Label"); 
		headElement0.type = "Label";
		headElement0.className = "headText";
		headElement0.style.fontSize = "11px";             
		headElement0.innerHTML =  "EB03";           
		headCell0.appendChild(headElement0);  
		  
		var headCell1 = row.insertCell(1); 
		headCell1.colSpan = "3";
		var headElement1 = document.createElement("Label"); 
		headElement1.type = "Label";             
		headElement1.className = "headText";
		headElement1.style.fontSize = "11px";
		headElement1.innerHTML =  "III02 ";    
		headCell1.appendChild(headElement1); 
		
		var headElement2 = document.createElement("a"); 
		headElement2.setAttribute("href", "#");            
		headElement2.onclick = function() {displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');};
		headElement2.innerHTML =  "what is this?";   
		headCell1.appendChild(headElement2); 
		
		var headCell2 = row.insertCell(2);
		headCell2.colSpan = "3";
		var headElement3 = document.createElement("Label"); 
		headElement3.type = "Label";             
		headElement3.className = "headText";
		headElement3.style.fontSize = "11px";
		headElement3.innerHTML =  "Message Text ";    
		headCell2.appendChild(headElement3);
		
		var headElement4 = document.createElement("a"); 
		headElement4.setAttribute("href", "#");            
		headElement4.onclick = function() {displayInfo('MSG',event,'../ajaxhippasegmenttooltip.ajax');};
		headElement4.innerHTML =  "what is this?";   
		headCell2.appendChild(headElement4);
		
		var headCell3 = row.insertCell(3);
		headCell3.colSpan = "2";
		headCell3.align = "left";
		var headElement5 = document.createElement("Label"); 
		headElement5.type = "Label";             
		headElement5.className = "headText";
		headElement5.style.fontSize = "11px";
		headElement5.innerHTML =  "Extnd Msg";
		headCell3.appendChild(headElement5); 
		
		var headCell4 = row.insertCell(4);
		headCell4.colSpan = "2";
		headCell4.align = "center";
		var headElement6 = document.createElement("Label"); 
		headElement6.type = "Label";             
		headElement6.className = "headText";
		headElement6.style.fontSize = "11px";
		headElement6.innerHTML =  "Msg Reqd";
		headCell4.appendChild(headElement6);
		  
		var headCell5 = row.insertCell(5); 
		headCell5.colSpan = "3";
		var headElement7 = document.createElement("Label"); 
		headElement7.type = "Label";             
		headElement7.className = "headText";
		headElement7.style.fontSize = "11px";
		headElement7.innerHTML =  "Note Type Code ";    
		headCell5.appendChild(headElement7);
		
		
		var headElement8 = document.createElement("a"); 
		headElement8.setAttribute("href", "#");            
		headElement8.onclick = function() {displayInfo('NOTE_TYPE_CODE',event,'../ajaxhippasegmenttooltip.ajax');};
		headElement8.innerHTML =  "what is this?";   
		headCell5.appendChild(headElement8); 
		
		
		for(var j = 0; j < eb03AssnObjCount; j++){
			count++;
			if (count % 2 != 0) {
				trClass = "alternate";
			}else {
				trClass = "white-bg";
			}
			
			var eb03Value = eb03AssnList[j].eb03.value;
			var iii02Value = eb03AssnList[j].iii02List[0].value;
			var iii02Desc = eb03AssnList[j].iii02List[0].description;
			var msgValue = eb03AssnList[j].message;
			var msgRqdInd = eb03AssnList[j].msgReqdInd;
			var extMsgInd = null;			
			var extndMsgJsonObj = $("#eb03ExtndMsgJsonObj").val();
			if(extndMsgJsonObj !== ""){			
				var msgObj = JSON.parse(extndMsgJsonObj);
				for(index=0; index < msgObj.length; index++) {
					var obj = msgObj[index];
					if((obj.eb03Val == eb03Value) && (obj.changeInd != "D") && (obj.eb03ExtndMessage1 != "" || obj.eb03ExtndMessage2 != "" || obj.eb03ExtndMessage3 != "" )){
						extMsgInd = obj.eb03Val;
						break;
					}
				}
			}
			var noteTypeValue = eb03AssnList[j].noteType.value;
			var noteTypeDesc = eb03AssnList[j].noteType.description;
			
			var row = table.insertRow(count);
			row.className = trClass;
			
			// Insert EB03 Value - Label
			var cell0 = row.insertCell(0); 
			cell0.style.width = "30px";
		 	var element0 = document.createElement("Label"); 
		 	element0.type = "Label";             
		 	element0.name="EB03Label"+j;
		 	element0.id = "EB03Label"+j; 
		 	element0.style.fontSize = "11px";
		 	element0.innerHTML =  eb03Value;	         
		 	cell0.appendChild(element0); 
			
			var cell1 = row.insertCell(1);
			cell1.style.width = "20px";
		 	
		 	// Insert III02 Value - Input Text box
		 	var cell2 = row.insertCell(2);
			cell2.style.width = "90px";
			var element2 = document.createElement("input"); 
		 	element2.name="ii02Val"+j;
			element2.id = "III02Id"+j; 
			element2.className = "inputbox60";
			element2.value =  iii02Value;
			element2.onclick = function() {autocompleteIII02();};        
			cell2.appendChild(element2); 
		 	
	 		// Insert III02 popup icon - Image
			var cell3 = row.insertCell(3);
			cell3.style.width = "25px";
		 	var element3 = document.createElement("img"); 
		 	element3.name = "III02_"+j; 
			element3.setAttribute("width","15"); 
			element3.setAttribute("height","14"); 
			element3.style.paddingLeft = "5px";            
			element3.setAttribute ("src", "${imageDir}/icon-popup.gif");
			element3.onclick =  function() {popupDiv(this.name,'../ajaxpopup.ajax');};	         
			cell3.appendChild(element3);
	
			// Insert III02 Description - Label
			var cell4 = row.insertCell(4);
			cell4.style.width = "100px";
		 	var element4 = document.createElement("Label"); 
		 	element4.type = "Label";             
		 	element4.id = "III02Id"+j+"Label"; 
		 	element4.style.fontSize = "11px";
		 	if($.trim(iii02Desc) == $.trim(invalidHippaCodeValue) || $.trim(iii02Desc) == $.trim(invalidHippaCodeValue).toUpperCase()){
			 	element4.style.color = "#FF0000"
			 	element4.innerHTML =  invalidHippaCodeValue;
			} else {
				element4.innerHTML =  iii02Desc;
			}
		 	cell4.appendChild(element4);
			
			/* var cell5 = row.insertCell(5);
			cell5.style.width = "10px"; */
		 	
			// Insert Message Text - Text Area
		 	var cell5 = row.insertCell(5);
			var element5 = document.createElement("textarea");
			element5.name="messageValue"+j;
			element5.id = "MESSAGEVALUEId"+j; 
			element5.cols = "35";
			element5.border = "1px";
			element5.border = "solid";
			element5.border = "grey";
			element5.colspan = "2";
			element5.rows = "1";
			element5.onclick = function (){this.rows = '2'};
			element5.onblur = function() {this.rows = '1'; changeTextToUpperCase(this.id);};
			element5.value =  msgValue;	         
			cell5.appendChild(element5);
			
			// Insert Message popup icon - Image
			var cell6 = row.insertCell(6);
			cell6.style.width = "25px";
		 	var element6 = document.createElement("img"); 
		 	element6.name = "MESSAGEVALUEId"+j; 
			element6.setAttribute("width","15"); 
			element6.setAttribute("height","14");         
			element6.style.paddingLeft = "5px";    
			element6.setAttribute ("src", "${imageDir}/icon-popup.gif");
			element6.onclick =  function(){openMessageTextDialog(this.name);};	         
			cell6.appendChild(element6);
			
			var cell7 = row.insertCell(7);
			cell7.style.width = "10px";
			
			var div = document.createElement("div");
		 	var cell8 = row.insertCell(8);
			cell8.style.width = "150px";
		 	cell8.align = "left"; 
		 	var element8 = document.createElement("input"); 
		 	element8.type = "checkbox";
		 	element8.id = "extndMsgReq"+j;
		 	element8.name = "extndMsgReq"+j;
		 	element8.onclick = function() {showHideExtndMsgLink(this.id);};
		 	if (extMsgInd != null) {
				element8.checked = true;
			} else if (extMsgInd == null) {
				element8.checked = false;
			}
			div.appendChild(element8);
						
			if (extMsgInd != null) {
				var element9 = document.createElement("a"); 
				element9.setAttribute("href", "#");
				element9.id = "extndMsg"+j;            
				element9.onclick = function() {showExtndMessagesScreen(this.id);};
				element9.innerHTML =  "&#160;&#160;Ext msg";  
				element9.style.fontSize = "11px";
				element9.style.color = "#077eed";
				div.appendChild(element9);
			} else{
				var element9 = document.createElement("a"); 
				element9.setAttribute("href", "#");
				element9.id = "extndMsg"+j;            
				element9.onclick = function() {showExtndMessagesScreen(this.id);};
				element9.innerHTML =  "&#160;&#160;Ext msg";  
				element9.style.display = "none";
				div.appendChild(element9);
			}
			cell8.appendChild(div);
			if(getTheValuesInTextBox("EB01Id","VALUE")[0] != "D"){
				row.cells[8].children[0].style.display = "none";
			} else{
				row.cells[8].children[0].style.display = "block";
			}
			var cell9 = row.insertCell(9);
			cell9.style.width = "10px";
			
			// Insert Message Required indicator -  Check box
		 	var cell10 = row.insertCell(10);
			cell10.style.width = "100px";
		 	cell10.align = "center"; 
		 	var element10 = document.createElement("input"); 
		 	element10.type = "checkbox";
		 	element10.id = "msgReqd"+j;
		 	element10.name = "msgReqd"+j;
			if (msgRqdInd == 'Y') {
				element10.checked = true;
			} else if (msgRqdInd == 'N') {
				element10.checked = false;
			}			
			cell10.appendChild(element10);
			
			var cell11 = row.insertCell(11);
			cell11.style.width = "10px";						
			
			// Insert Note Type Value - Input Text box
		 	var cell12 = row.insertCell(12);
			cell12.style.width = "90px";
			var element12 = document.createElement("input"); 
		 	element12.name="noteType"+j;
			element12.id = "NOTETYPEID"+j; 
			element12.className = "inputbox60";
			element12.value =  noteTypeValue;
			element12.onclick = function() {autocompleteNoteType()};	         
			cell12.appendChild(element12); 
	
			// Insert NOTE TYPE popup icon - Image
			var cell13 = row.insertCell(13);
			cell13.style.width = "25px";
		 	var element13 = document.createElement("img"); 
		 	element13.name = "NOTE_TYPE_CODE_"+j;
			element13.setAttribute("width","15"); 
			element13.setAttribute("height","14");         
			element13.style.paddingLeft = "5px";    
			element13.setAttribute ("src", "${imageDir}/icon-popup.gif");
			element13.onclick =  function() {popupDiv(this.name,'../ajaxpopup.ajax');};	         
			cell13.appendChild(element13);
		
			// Insert NOTE TYPE Description - Label
			var cell14 = row.insertCell(14);
			cell14.style.width = "250px";
		 	var element14 = document.createElement("Label");
		 	element14.type = "Label";
		 	element14.id = "NOTETYPEID"+j+"Label";
		 	element14.style.fontSize = "11px";
			if($.trim(noteTypeDesc) == $.trim(invalidHippaCodeValue) || $.trim(noteTypeDesc) == $.trim(invalidHippaCodeValue).toUpperCase()){
			 	element14.style.color = "#FF0000"
			 	element14.innerHTML =  invalidHippaCodeValue;
			} else {
				element14.innerHTML =  noteTypeDesc;
			}
		 	cell14.appendChild(element14);
		 	};
	}

	function countProperties(obj) {
	    var property;
	    var propCount = 0;
	    for (property in obj) {
	    propCount++;
	    }
	    return propCount;
  	}
  	
	function createEB03AssnObject(){
	
		var eb03AssnTable = document.getElementById('eb03AssnTable');
		var eb03AssnJSON = [];
		var newList = document.getElementById('eb03List').value;
		var newValue = newList.replace(/`/g, '');
		if(newValue != "") {
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
			
				var eb03 = (null != eb03AssnTable.getElementsByTagName("TR").item(i)
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD")
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0)
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText
									? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText
									: ""); 
				
				var iii02 = (null != eb03AssnTable.getElementsByTagName("TR").item(i) && null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD")
					&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(2)
					&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(2).getElementsByTagName("input")
					&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(2).getElementsByTagName("input").item(0)
					&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(2).getElementsByTagName("input").item(0).value
					? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(2).getElementsByTagName("input").item(0).value
					: "");
				
				var iii02Desc = (null != eb03AssnTable.getElementsByTagName("TR").item(i)
									&& null !=  eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD")
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4)
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).innerText
									? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(4).innerText
									: ""); 
				
				var messageText = (null != eb03AssnTable.getElementsByTagName("TR").item(i) 
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD")
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5)
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea")
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0)
									&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0).value
									? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(5).getElementsByTagName("textarea").item(0).value
									: "");		
				var msgRequiredInd = (null != eb03AssnTable.getElementsByTagName("TR").item(i)
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD")
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(10)
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(10).getElementsByTagName("input")
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(10).getElementsByTagName("input").item(0)
										&& eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(10).getElementsByTagName("input").item(0).checked
										? "Y" : "N");
				var noteTypeCode = (null != eb03AssnTable.getElementsByTagName("TR").item(i)
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD")
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(12)
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(12).getElementsByTagName("input")
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(12).getElementsByTagName("input").item(0)
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(12).getElementsByTagName("input").item(0).value
										? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(12).getElementsByTagName("input").item(0).value
										: ""); 
				var noteTypeDesc = (null != eb03AssnTable.getElementsByTagName("TR").item(i)
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD")
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(14)
										&& null != eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(14).innerText
										? eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(14).innerText
										: ""); 
				if ((noteTypeDesc.search("%") != -1) || (noteTypeDesc.search("&") != -1)) {
					noteTypeDesc = escape(noteTypeDesc);
				}					
				if ((messageText.search("%") != -1) || (messageText.search("&") != -1)) {
					messageText = escape(messageText);
				}						
				
				//push the value to the json array
				eb03AssnJSON.push({"EB03": eb03,"III02":iii02, "III02DESC":iii02Desc,"MESSAGE":messageText,"MSG_REQD": msgRequiredInd, "NOTE_TYPE_CODE":noteTypeCode, "NOTETYPEDESC":noteTypeDesc});
			}
		//after the iteration, stringify the json array and assign the same to a hidden request parameter
		eb03AssnJSON = JSON.stringify(eb03AssnJSON);
		document.getElementById('eb03AssnJsonObj').value = eb03AssnJSON;
		return true;
		} else {
			return false;
		}
	}
	
	function validateMaxLengthMsg() {
		var indEb03AssnChd = $('#indEB03AssnCheckBoxId').attr('checked');
		if (indEb03AssnChd) {
			var eb03AssnTable = document.getElementById('eb03AssnTable');
			var eb03ListForMsgLength = "";
		
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
				var eb03Value = eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
				var msgValue = $.trim($('#MESSAGEVALUEId'+(i-1)).val()); 
				$('#MESSAGEVALUEId'+(i-1)).val(msgValue)
				if(msgValue != null && msgValue.length > 75) {
					if(eb03ListForMsgLength == ""){
						eb03ListForMsgLength = eb03Value;
                    } else {
                    	eb03ListForMsgLength = eb03ListForMsgLength +", "+eb03Value;
                    }
				}
			}
			if(eb03ListForMsgLength != ""){
	            var message = "Text length should be 75 characters or less for message mapped to EB03(s) - "+eb03ListForMsgLength;                 
	            addErrorToNotificationTray(message);
	            openTrayNotification(); 
	            return false;
            }
				
		} else if (!indEb03AssnChd) {
			var msgValue = $.trim($('#messageValueId').val());
			$('#messageValueId').val(msgValue);
			if(msgValue != null && msgValue.length > 75) {
				var message = "Text length should be 75 characters or less for message";			
				addErrorToNotificationTray(message);
				openTrayNotification();	
				return false;
	  		}
		}
		return true;
	}


	// SSCR19537 April04- End
	
	
	
	
	
	
	
	
	function sniffer() {
	   if(screen.height==1024) 
	   {
	      document.getElementById('createEditContainer').style.height = "475px";	   
	   }else if(screen.height==960)
	   {
	      document.getElementById("createEditContainer").style.height= "440px";
	   }
	   else if(screen.height==864)
	   {
	      document.getElementById("createEditContainer").style.height= "400px";
	   }
	   else if(screen.height==720)
	   {
	      document.getElementById("createEditContainer").style.height= "290px";
	   }
	}

	function openUserCommentsLockDialog(variableId, systemName, lockUserId, variableDesc, action) {
		$("#variableIdForLockFlow").val(variableId);
		$("#systemForLockFlow").val(systemName);
		$("#variableDescForLockFlow").val(variableDesc);
		$("#actionForLockFlow").val(action);
		$("#userForLockFlow").val(lockUserId);
		$("#lockComments").val('');
		if (action == 'Lock') {
			if (isElementDefined($("[id=buttonUnlock]"))) {
				var lockImage = '<img id="buttonLock" src="${imageDir}/Lock_but.gif" alt="Lock"  title="Lock"/>';
				$("#buttonUnlock").replaceWith(lockImage);
			}
		} else if (action == 'Unlock') {
			if (isElementDefined($("[id=buttonLock]"))) {
				var unlockImage = '<img id="buttonUnlock" src="${imageDir}/Unlock_but.gif" alt="Unlock"  title="Unlock"/>';
				$("#buttonLock").replaceWith(unlockImage);
			}
		}
		$("#lockusercomment").dialog({
					 		height:'auto',
							width:'450px',
							title: action + ' - ' + variableId,
	                        show:'slide',
	                        modal: true
				});
		$("#lockusercomment").dialog('open');
	}

	 function sendToTest(variableId, variableFormat){
	 		// SSCR19537
	 		var result = createEB03AssnObject();
	 		
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			trimTextValues();
			var valid = validateEb01ExtMsg();
			if(!valid){
				return false;
			}
			var returned = checkExtndMsgRqdInd();
			if(!returned){
				return false;
			}
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!validateMaxLengthMsg()){
			$('#changeCommentsDialog').dialog('close');
				return false;
			}
			setDescription();
			var queryString="";
			//code to check the sensitive benefit check box for users to which  it is disabled --July Release T39
			if ($('#accumNtReqdChkBox').attr("disabled")) {
			 	if ($("#accumNtReqdChkBox").is(':checked')) {
			 		queryString = "?accumNtReqdChkBox=checked";
			 		}
				}
			document.forms['stateFlowForm'].action="../stateflow/saveAndsendToTest.html"+queryString;
            document.forms["stateFlowForm"].submit();
      }
	function approve(variableId, variableFormat){
			// SSCR19537
	 		var result = createEB03AssnObject();
	 		
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			trimTextValues();
			var valid = validateEb01ExtMsg();
			if(!valid){
				return false;
			}
			var returned = checkExtndMsgRqdInd();
			if(!returned){
				return false;
			}
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!validateMaxLengthMsg()){
			$('#changeCommentsDialog').dialog('close');
				return false;
			}
			setDescription();
			
			var queryString="";
			//code to check the sensitive benefit check box for users to which  it is disabled --July Release T39
			if ($('#accumNtReqdChkBox').attr("disabled")) {
			 	if ($("#accumNtReqdChkBox").is(':checked')) {
			 		queryString = "?accumNtReqdChkBox=checked";
			 		}
				}
			document.forms['stateFlowForm'].action="../stateflow/saveAndApprove.html"+queryString;
            document.forms["stateFlowForm"].submit();
			
      }
	function notApplicable(variableId){
			var auditLockStatus=$("#auditStatus").val();
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			trimTextValues();
			setDescription();
			var action = "${context}/stateflow/notApplicable.html"
			var formName = "stateFlowForm";
			if(auditLockStatus == 'Y'){
				confirmationDialogForNotApplicable(action,formName);
			}else{
				document.forms['stateFlowForm'].action="../stateflow/notApplicable.html";
	            document.forms["stateFlowForm"].submit();
            }
			
      }
      
      function confirmationDialogForNotApplicable(action,formName){
		$("#confirmationDivDiscardChanges").html("The mapping is audit locked. Do you want to update?");
		$("#confirmationDivDiscardChanges").addClass("UnmappedVariables");
		$("#confirmationDivDiscardChanges").dialog({
				autoOpen: false,
				title : 'Confirm',
				resizable: false,
				height:130,
				modal: true,
				buttons: {
					Cancel: function() {
						$(this).dialog('close');
					},
					Ok: function() {
						document.forms[formName].action= action;
						$(this).dialog('close');
						document.forms[formName].submit();
					}
				}
			});
			$("#confirmationDivDiscardChanges").dialog('open');
	}

	function discardChanges(variableId, mappingSysId){
			$("#selectedvariableId").val(variableId);			
			$("#mappingSysId").val(mappingSysId);
			var action = "${context}/editmapping/discardChanges.html";
			confirmationDialogDiscardChanges(action, 'stateFlowForm');			
	}
	function cancel(variableId){
			var pageFrom = $("#pageFrom").val();
			var action ;
		    if(pageFrom == "advanceSearch"){
				action = "${context}/editmapping/cancelToAdvanceSearchBxResult.html?selectedvariableId="+encodeURIComponent(variableId);
			}else{
				action = "${context}/editmapping/cancelToAdvanceSearchBxResult.html?selectedvariableId="+encodeURIComponent(variableId);
			}
			openConfirmationDialog(action);
	}
	function save(variableId, variableFormat, auditStatus){
			var result = createEB03AssnObject();
			$("#selectedvariableId").val(variableId);
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			$("#auditStatus").val();
			trimTextValues();
			var valid = validateEb01ExtMsg();
			if(!valid){
				return false;
			}
			var returned = checkExtndMsgRqdInd();
			if(!returned){
				return false;
			}
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!validateMaxLengthMsg()){
			$('#changeCommentsDialog').dialog('close');
				return false;
			}
			setDescription();
			var queryString="";
			//code to check the sensitive benefit check box for users to which  it is disabled --July Release T39
			if ($('#accumNtReqdChkBox').attr("disabled")) {
			 	if ($("#accumNtReqdChkBox").is(':checked')) {
			 		queryString = "?accumNtReqdChkBox=checked";
			 		}
				}
			document.forms['stateFlowForm'].action="${context}/editmapping/saveMapping.html"+queryString;
	        document.forms["stateFlowForm"].submit();
			
      }	
	function done(variableId, variableFormat){
			// SSCR 19537
			var result = createEB03AssnObject();
			
			
			$("#selectedvariableId").val(variableId);			
			//$("#selectedvariableDesc").val(variableDesc);
			$("#variableFormat").val(variableFormat);
			trimTextValues();
			var valid = validateEb01ExtMsg();
			if(!valid){
				return false;
			}
			var returned = checkExtndMsgRqdInd();
			if(!returned){
				return false;
			}
			var pass = checkMsgRqdInd();
			if(!pass){
				return false;
			}
			if(!validateMaxLengthMsg()){
			$('#changeCommentsDialog').dialog('close');
				return false;
			}
			setDescription();
			var queryString="";
			//code to check the sensitive benefit check box for users to which  it is disabled --July Release T39
			if ($('#accumNtReqdChkBox').attr("disabled")) {
			 	if ($("#accumNtReqdChkBox").is(':checked')) {
			 		queryString = "?accumNtReqdChkBox=checked";
			 		}
				}
			document.forms['stateFlowForm'].action="${context}/editmapping/done.html"+queryString;
            document.forms["stateFlowForm"].submit();
			
      }	
	function openViewHistoryDialog(variableId) {
		$.ajax({
			url: "${context}/editmapping/viewHistory.ajax",
			dataType: "html",
			type: "POST",
			data: "variableId="+encodeURIComponent(variableId),
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
    function copyTo(){		
				$("#copyToDialog").dialog({
					 		height:'auto',
							width:'450px',
							title: 'Copy to Variable',
	                        show:'slide',
	                        modal: true
				});
				$("#copyToDialog").dialog('open');			
	}
	
	function openAlertDialog(){
      if(compareInputs()){
            alertDialog();
      }else{
            copyTo();
      }
	}
	
	function alertDialog(){
	$("#alertDiv").html(warningMsgForCopyTo);
	$("#alertDiv").addClass("UnmappedVariables");
	$("#alertDiv").dialog({
				autoOpen: false,
				title : 'Alert',
				resizable: false,
				height:150,
				modal: true,
				buttons: {
				Ok: function() {
						$(this).dialog('close');
					}
				}
			});
			$("#alertDiv").dialog('open');
}

	function imposeMaxLength(elementId, MaxLen, element){
		var valueOfTextArea = $.trim($('#'+elementId).val());
		$('#'+elementId).val(valueOfTextArea);
		if(valueOfTextArea!=null && valueOfTextArea.length > MaxLen) {
			var message = "Text length cannot be greater than " +MaxLen+" for "+element;			
			addErrorToNotificationTray(message);
			openTrayNotification();	
			return false;
  		}
		return true;
	}
	
	function validateEb01ExtMsg(){
		if ($('#eb01ExtndMsgChkBox').is(':checked') && getTheValuesInTextBox("EB01Id","VALUE")[0] == "D"){
			var eb01ForExtndMsgRqd = ""
			if($("#eb01ExtndMsgJsonObj").val() == "" && $("#eb01ExtndMsgValSysId").val() == "" ){
				eb01ForExtndMsgRqd = getTheValuesInTextBox("EB01Id","VALUE")[0];
			}
			if(eb01ForExtndMsgRqd != ""){
	            var message = "Enter Extended Message for EB01(s) - "+eb01ForExtndMsgRqd;                 
	            addErrorToNotificationTray(message);
	            openTrayNotification(); 
	            return false;
           	}		
		} else if(!($('#eb01ExtndMsgChkBox').is(':checked'))){
			if($("#eb01ExtndMsgJsonObj").val() != ""){
				$("#eb01ExtndMsgJsonObj").val("");
			}
		}
		return true;
	}
	
	function checkExtndMsgRqdInd(){
		var indEb03AssnChd = $('#indEB03AssnCheckBoxId').attr('checked');
		if (indEb03AssnChd){
			var eb03AssnTable = document.getElementById('eb03AssnTable');
            var eb03ListForExtndMsgRqd = "";		
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
				var eb03Value = eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText.trim();
				var extndMsgRqdChkd = $('#extndMsgReq'+(i-1)).attr('checked');
				var found = false;
				var extndMsgJsonObj = $("#eb03ExtndMsgJsonObj").val();
				if(extndMsgRqdChkd){
					if(extndMsgJsonObj !== ""){			
						var msgObj = JSON.parse(extndMsgJsonObj);
						for(j=0; j< msgObj.length; j++) {
							var obj = msgObj[j];
							if(obj.eb03Val === eb03Value){
								found=true;
								break;
							}
						}
						if(!found){
							if(eb03ListForExtndMsgRqd == ""){
								eb03ListForExtndMsgRqd = eb03Value;
		                    } else {
		                    	eb03ListForExtndMsgRqd = eb03ListForExtndMsgRqd +", "+eb03Value;
		                    }
						} 
					} else{
						if(eb03ListForExtndMsgRqd == ""){
							eb03ListForExtndMsgRqd = eb03Value;
		                } else {
		                   	eb03ListForExtndMsgRqd = eb03ListForExtndMsgRqd +", "+eb03Value;
		                }
					}
				} else{
					if(extndMsgJsonObj != ""){
						var arrayObj = JSON.parse(extndMsgJsonObj);
						for(k=0; k < arrayObj.length; k++) {
							if(arrayObj[k].eb03Val === eb03Value){
								var eb03ExtndMsgSysId = arrayObj[k].eb03ExtndMsgSysId;
								arrayObj.splice(k,1);
								if(eb03ExtndMsgSysId != ""){
									pushObjToEB03HiddenVar(arrayObj, eb03Value, eb03ExtndMsgSysId);
								} else{
									arrayObj = JSON.stringify(arrayObj);
									$("#eb03ExtndMsgJsonObj").val(arrayObj);
								}
							}
						}
					}
				}
			}
			if(eb03ListForExtndMsgRqd != ""){
	            var message = "Enter Extended Message for EB03(s) - "+eb03ListForExtndMsgRqd;                 
	            addErrorToNotificationTray(message);
	            openTrayNotification(); 
	            return false;
            }
		}
		return true;
	}
	
	function pushObjToEB03HiddenVar(arrayObj, eb03Value, eb03ExtndMsgSysId){
		arrayObj.push({"eb03Val": eb03Value, "eb03ExtndMsgSysId": eb03ExtndMsgSysId, "changeInd": "D"});
		arrayObj = JSON.stringify(arrayObj);
		$("#eb03ExtndMsgJsonObj").val(arrayObj);
	}
	
	//Validation for 'Message'
	// SSCR 19537 April04 - modified.
	function checkMsgRqdInd(){
		var indEb03AssnChd = $('#indEB03AssnCheckBoxId').attr('checked');
		if (indEb03AssnChd) {
			var eb03AssnTable = document.getElementById('eb03AssnTable');
            var eb03ListForMsgRqd = "";
		
			for(var i = 1; i < eb03AssnTable.getElementsByTagName("TR").length; i++){
				var eb03Value = eb03AssnTable.getElementsByTagName("TR").item(i).getElementsByTagName("TD").item(0).innerText;
				var msgRqdChkd = $('#msgReqd'+(i-1)).attr('checked');
				
				var msgVal = $.trim($('#MESSAGEVALUEId'+(i-1)).val());
				$('#MESSAGEVALUEId'+(i-1)).val(msgVal)
				
				if(msgRqdChkd && (msgVal == null || msgVal == "")){
					if(eb03ListForMsgRqd == ""){
						eb03ListForMsgRqd = eb03Value;
                    } else {
                    	eb03ListForMsgRqd = eb03ListForMsgRqd +", "+eb03Value;
                    }
				}
			}
			if(eb03ListForMsgRqd != ""){
	            var message = "Enter Message for EB03(s) - "+eb03ListForMsgRqd;                 
	            addErrorToNotificationTray(message);
	            openTrayNotification(); 
	            return false;
            }
				
		} else if (!indEb03AssnChd) {
			var checked = $('#msgRqdChkBox').attr('checked');
			if(checked){
				var msgValue = $.trim($('#messageValueId').val());
				$('#messageValueId').val(msgValue)
				$('#msgRqdChkBox').val('true');
				
				if(msgValue == null || $.trim(msgValue) == "" ){
					addErrorToNotificationTray("Enter message");
					openTrayNotification();	
					$('#messageValueId').focus();
					return false;
				}
			}
			else{	
			$('#msgRqdChkBox').val('false');
			}
		}
		return true;
	}	

// for copy to function for doing save
//openCopyToDialog("${mapping.variable.variableId}","${mapping.variable.description}","${variableList[0].variableFormat}");
$(document).ready(function(){

	$("#variableId").autocomplete({ 
		
		select: function(event, ui) { $("#createIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxvariablelist.ajax",
						dataType: "json",
						type:"POST",
						data: "key="+request.term,
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
		},
		change: function(event, ui) {
			var text = $(this).val();
			if(!ui.item){
				displayLabelForSelectedItem(text,list,"createIdLabel",inValidVariable);
			}
		}

	})
  });

function callCopyTo(){
		var variableIdForCopyTo =$("#variableIdForCopyTo").val();
		var oldVarID= $("#oldVarID").val();
		var oldVarDesc = $("#oldVarDesc").val();
		var oldVarFormate = $("#oldVarFormate").val();
		var pageFrom = $("#pageFrom").val();
		$.ajax({
				url: "${context}/copyto/invalidVariable.ajax",
				dataType: "json",
				type: "POST",			
				data: "variableIdForCopyTo="+variableIdForCopyTo+"&oldVarID="+oldVarID+"&oldVarDesc="+oldVarDesc+"&oldVarFormate="+oldVarFormate+"&pageFrom="+pageFrom,
				success: function(data) {
				cache: false,
				copyToCreate(data,variableIdForCopyTo,oldVarID,oldVarDesc,oldVarFormate);
				}
			});	
}
function copyToCreate(data,variableIdForCopyTo,oldVarID,oldVarDesc,oldVarFormate){
		var errorMessages = data.error_messages;
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
			openTrayNotification();
		}
		else{

			$("#selectedvariableIdCopyTo").val(variableIdForCopyTo);
			$("#selectedvariableId").val(oldVarID);			
			if(!validateMaxLengthMsg()){
				return false;
			}
			trimTextValues();
			setDescription();
			document.forms['stateFlowForm'].action="${context}/createpage/copyTo.html";
	        document.forms["stateFlowForm"].submit();
		    //document.forms["copyToform"].submit();
		}

      }	
 function searchMessageText(currentPageNo, page,context){
	if(page == 'Init'){
		$('#hdMsgTxtId').val($('#msgTxtId').val());
		$('#hdMsgTxtEB03Id').val($('#msgTxtEb03Id').val());
		$('#hdMsgTxtHdrId').val($('#msgTxtVarId').val());
		page = 'First';	
	}
	var message = $('#hdMsgTxtId').val();
	var eb03 = $('#hdMsgTxtEB03Id').val();
	var variableId = $('#hdMsgTxtHdrId').val();
	
	//BXNI CHANGE
	var showOnlyStandardMessages = "false";
	if ($('#showOnlyStandardMessages').is(':checked') ) {
  		showOnlyStandardMessages = "true";
  		}
	
	var fromWhichSection = page;
	var currentPage = currentPageNo;		
	$.ajax({
		url: "${context}/createpage/existingMsgTexts.ajax",
		dataType: "html",
		type: "POST",
		data: "variableId="+variableId+"&eb03="+eb03+"&message="+message+"&currentPage="+currentPage+"&section="+fromWhichSection+"&showOnlyStandardMessages="+showOnlyStandardMessages,
		success: function(data) {
			$("#messageTextResult").html(data);
		}
	});
 }
 
  	var tempVariableId = "";
	var tempVariableFormat = "";
	var tempActionName = "";
	var tempAuditStatus = ""
	
	function runSpellCheck(variableId, variableFormat,actionName,auditStatus){
		tempVariableId = variableId;
		tempVariableFormat = variableFormat;
		tempActionName = actionName;
		tempAuditStatus = auditStatus;
		
		
		var countOfEb03 = document.getElementById('eb03AssnTable').getElementsByTagName("TR").length;
		if(!$('#indEB03AssnCheckBoxId').is(':checked')){
			var rswlCntrls = ["rapidSpellWebLauncher1"];
			var i=0;
			for(var i=0; i<rswlCntrls.length; i++){
				eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
			}
		}else{
			var rswlCntrls = [];
			for(var j = 0; j< (countOfEb03-1) ; j++){

			rswlCntrls = ["rapidSpellWebLauncher_0","rapidSpellWebLauncher_1",
			"rapidSpellWebLauncher_2","rapidSpellWebLauncher_3","rapidSpellWebLauncher_4"
			,"rapidSpellWebLauncher_5","rapidSpellWebLauncher_6","rapidSpellWebLauncher_7"
			,"rapidSpellWebLauncher_8","rapidSpellWebLauncher_9","rapidSpellWebLauncher_10",
			"rapidSpellWebLauncher_11","rapidSpellWebLauncher_12","rapidSpellWebLauncher_13",
			"rapidSpellWebLauncher_14","rapidSpellWebLauncher_15"];
 			var messageText = 	$.trim($('#MESSAGEVALUEId'+j).val());
 			if(null != messageText && messageText != ""){
 			
					eval("popUpCheckSpelling"+rswlCntrls[j]+"('rsTCInt"+rswlCntrls[j]+"')");
			}
			}
			spellFinMappingAction();
		}
	}
	
	function runSpellCheckForExtMsg(){
		//if($('#eb01ExtndMsgChkBox').is(':checked') || $('#eb01ExtndMsgChkBox').is(':checked')){
			var rswlCntrls = ["rapidSpellWebLauncher2","rapidSpellWebLauncher3","rapidSpellWebLauncher4"];
			for(var i=0; i<rswlCntrls.length; i++){
			var messageText = 	$.trim($('#extndMessage'+(i+1)).val());
 			if(null != messageText && messageText != ""){
				eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
			}
		}
		spellFinMappingAction();
		//}
	}
	function spellFinMappingAction(cancelled){
			if(tempActionName == "approve"){
				approve(tempVariableId, tempVariableFormat);
			}else if(tempActionName == "done"){
				done(tempVariableId, tempVariableFormat);
			}else if(tempActionName == "save"){
				save(tempVariableId, tempVariableFormat, tempAuditStatus);
			}else if(tempActionName == "sendToTest"){
				sendToTest(tempVariableId, tempVariableFormat);
			}
			tempVariableId = "";
			tempVariableFormat = "";
			tempActionName = "";
			
	}
	
	function RSCustomInterface(tbElementName){
		this.tbName = tbElementName;
		this.getText = getText;
		this.setText = setText;
	}
	function getText(){
		if(!document.getElementById(this.tbName)) {
			alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
			return '';
		} else return document.getElementById(this.tbName).value;
	}
	function setText(text){
		if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
	}    
//ends copy to for doing save
function printPage(){
  	    	
	var url = "${context}/viewmappingpage/printMapping.html";

	var variableId = $("#variableIdHidden").val();   

  	var eb01Val = getTheValuesInTextBox("EB01Id","VALUE");
  	var EB01Desc = getTheValuesInTextBox("EB01Id","DESC");
  	var eb02Val = getTheValuesInTextBox("EB02Id","VALUE");
  	var EB02Desc = getTheValuesInTextBox("EB02Id","DESC");
  	
  	var eb03Val =  getTheValuesInTextBox("EB03Id","VALUE");
  	var EB03Desc=  getTheValuesInTextBox("EB03Id","DESC");
  	
  	var eb03Length = eb03Val.length;
	var eb03String = "";
	var eb03DescString = "";
	for(var i=0;i<eb03Length;i++) {
		
		eb03String = eb03String+"**"+eb03Val[i];
		eb03DescString = eb03DescString+"**"+EB03Desc[i];
	}
  	
  	
  	var eb06Val = getTheValuesInTextBox("EB06Id","VALUE");
  	var EB06Desc = getTheValuesInTextBox("EB06Id","DESC");
  	var eb09Val = getTheValuesInTextBox("EB09Id","VALUE");
  	var EB09Desc = getTheValuesInTextBox("EB09Id","DESC");
  	var hsd01 = getTheValuesInTextBox("HSD01Id","VALUE");
  	var HSD01Desc = getTheValuesInTextBox("HSD01Id","DESC");
  	var hsd02 = getTheValuesInTextBox("HSD02Id","VALUE");
  	var HSD02Desc = getTheValuesInTextBox("HSD02Id","DESC");
  	var hsd03 = getTheValuesInTextBox("HSD03Id","VALUE");
  	var HSD03Desc = getTheValuesInTextBox("HSD03Id","DESC");
  	var hsd04 = getTheValuesInTextBox("HSD04Id","VALUE");
  	var HSD04Desc = getTheValuesInTextBox("HSD04Id","DESC");
  	var hsd05 = getTheValuesInTextBox("HSD05Id","VALUE");
  	var HSD05Desc = getTheValuesInTextBox("HSD05Id","DESC");
  	var hsd06 = getTheValuesInTextBox("HSD06Id","VALUE");
  	var HSD06Desc = getTheValuesInTextBox("HSD06Id","DESC");
  	var hsd07 = getTheValuesInTextBox("HSD07Id","VALUE");
  	var HSD07Desc = getTheValuesInTextBox("HSD07Id","DESC");
  	var hsd08 =  getTheValuesInTextBox("HSD08Id","VALUE");
  	var HSD08Desc = getTheValuesInTextBox("HSD08Id","DESC");
  	var ii02Val = getTheValuesInTextBox("III02Id","VALUE");
  	var III02Desc = getTheValuesInTextBox("III02Id","DESC");
  	var accumulator = getTheValuesInTextBox("accumId","VALUE");
  	var AccumDesc = getTheValuesInTextBox("accumId","DESC");
  	
  	var startAge = getTheValuesInTextBox("startAge","VALUE");
  	var endAge = getTheValuesInTextBox("endAge","VALUE");
  	
  	var nm1MessageSegment = getTheValuesInTextBox("nm1MessageSegmentId","VALUE");
  	var nm1MessageSegmentDesc = getTheValuesInTextBox("nm1MessageSegmentId","DESC");
  	
  	 var accumLength = accumulator.length;
	  var accumString = "";
	
	  for(var i=0;i<accumLength;i++) {
			accumString = accumString + "&accumulator="+accumulator[i]+"&AccumDesc="+AccumDesc[i];
	  }
  	
  	
  	var umRuleVal = getTheValuesInTextBox("UMRuleId","VALUE");
  	var UMRuleDesc = getTheValuesInTextBox("UMRuleId","DESC");

	  var umruleLength = umRuleVal.length;
	  var unruleString = "";
	
	  for(var i=0;i<umruleLength;i++) {
			unruleString = unruleString + "&umRuleVal="+umRuleVal[i]+"&UMRuleDesc="+UMRuleDesc[i];
	  }
  	
  	
  	var noteType = getTheValuesInTextBox("NOTETYPEID","VALUE");
  	var NoteTypeDesc = getTheValuesInTextBox("NOTETYPEID","DESC");
  	var msgRqdChkBox = "false";
  	if ($('#msgRqdChkBox:checked').val() == 'checked') {
  		msgRqdChkBox = "true";
  	}
 	var accumNtReqdChkBox = "false";
  	if ($('#accumNtReqdChkBox:checked').val() == 'checked') {
  		accumNtReqdChkBox = "true";
  	}
  	var messageValue = $("#messageValueId").val();
  	var variableIdHidden = $("#variableIdHidden").val();
  	var variableDescHidden = $("#variableDescHidden").val();
  	var variableFormat = $("#variableFormat").val();
  	var lgCatagory = $("#lgCatagory").val();
  	var isgCatagory = $("#isgCatagory").val();
  	var system = $("#system").val();
  	var notFinalizedChkBox = $('#notFinalizedChkBox:checked').val();
  	var indEB03AssnCheckBox = "";
	if ($('#indEB03AssnCheckBoxId').is (':checked')) {
		indEB03AssnCheckBox = "on";
	}
	isEb03Modified();
	createEB03AssnObject();
	var eb03AssnJsonObj = document.getElementById('eb03AssnJsonObj').value;
	var hsd02String ="";
		for(var i =1;i<7;i++){ 
			if($.trim($('#HSD02-0'+i+'Id').val()) != ""){
				hsd02String = hsd02String + "**" + $.trim($('#HSD02-0'+i+'Id').val());
			}
		}
		
	var hsd02ValueString = hsd02String;
	
	var param="?currentpage=edit&variableId="+encodeURIComponent(variableId)+"&eb01Val="+eb01Val+"&EB01Desc="+EB01Desc+"&eb02Val="+eb02Val+"&EB02Desc="+EB02Desc+"&eb03String="+eb03String+"&eb03DescString="+eb03DescString+"&eb06Val="+eb06Val+"&EB06Desc="+EB06Desc+"&eb09Val="+eb09Val+"&EB09Desc="+EB09Desc+"&hsd01="
	+hsd01+"&HSD01Desc="+HSD01Desc+"&hsd02="+hsd02+"&HSD02Desc="+HSD02Desc+"&hsd03="+hsd03+"&HSD03Desc="+HSD03Desc+"&hsd04="+hsd04+"&HSD04Desc="+HSD04Desc+"&hsd05="+hsd05+"&HSD05Desc="+HSD05Desc+"&hsd06="+hsd06+"&HSD06Desc="+HSD06Desc+"&hsd07="+hsd07+"&HSD07Desc="+HSD07Desc+"&hsd08="
	+hsd08+"&HSD08Desc="+HSD08Desc+"&ii02Val="+ii02Val+"&III02Desc="+III02Desc+accumString+unruleString+"&noteType="+noteType+"&NoteTypeDesc="+NoteTypeDesc+"&msgRqdChkBox="+msgRqdChkBox+"&accumNtReqdChkBox="+accumNtReqdChkBox+"&messageValue="+messageValue+"&variableIdHidden="+encodeURIComponent(variableIdHidden)+"&variableDescHidden="
	+variableDescHidden+"&variableFormat="+variableFormat+"&lgCatagory="+lgCatagory+"&isgCatagory="+isgCatagory+"&system="+system+"&notFinalizedChkBox="+notFinalizedChkBox+"&startAge="+startAge+"&endAge="+endAge+"&nm1MessageSegment="+nm1MessageSegment+"&nm1MessageSegmentDesc="+nm1MessageSegmentDesc
	+"&indEB03AssnCheckBox="+indEB03AssnCheckBox+"&eb03AssnJsonObj="+eb03AssnJsonObj+"&hsd02ValueString="+hsd02ValueString;
  
	url=url+param;
	//newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
	window.open(url,"","dialogHeight:650px;dialogWidth:890px;resizable=false;status=no;title=Print Mapping;");
  }	
  function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
  }	
  
  var eb03inputsString;
	function storeeb03values() {
	eb03inputsString = "";
	var textbox = $("input[type='text'][id^='EB03Id']"); 
		var text="";
		var seperator="`";
		textbox.each(function(i) {
		var textboxid = textbox[i].id;

			text = $.trim($(this).val());
			eb03inputsString = eb03inputsString+seperator+text;

		});
	}
		
	function isEb03Modified(){
	var eb03Values = new Array();
		var textbox = $("input[type='text'][id^='EB03Id']"); 
		var text="";
		var seperator="`";
		var newInputs ="";
		textbox.each(function(i) {
			var textboxid = textbox[i].id;
			text = $.trim($(this).val());
			newInputs = newInputs+seperator+text;
			//Added as part of 	SSCR 19537		
			document.getElementById('eb03List').value = newInputs;
		});	
		document.getElementById('eb03ExistingList').value = eb03inputsString;
		if(eb03inputsString != newInputs ){
			eb03inputsString = newInputs;
			//Added as part of 	SSCR 19537	
			populateEb03ValuesToEB03AssnList();
			return true;
		}else{
			return false;
		}
	}
  // Function to set the sensitive benefit indicator if a sensitive EB03 is entered
   function sensitiveBenefitCheck(){
		if (isEb03Modified()) {
		   var eb03Val = getTheValuesInTextBox("EB03Id","VALUE");
		   //logic is changed so as to retrieve the sensitive benefits list from the errorvalidator.properties proeprties file. 
		   //BXNI June2012 Release
		   var sensitiveBenefitList = new Array();
		   	$.ajax({
			url: "${context}/createpage/populateSensitiveBeenfitsList.ajax",
			dataType: "json",
			type: "POST",
			success: function(data) {
			cache: false;
				//Commented to remove Sens Bnft Ind -POR Wave 2
				//sensitiveBenefitList = data.sensitive;
			 $("#accumNtReqdChkBox").attr('checked',false);
		 		 for(var j=0;j<sensitiveBenefitList.length;j++){
					if($.inArray(sensitiveBenefitList[j], eb03Val) != -1) {
				 		//$("#accumNtReqdChkBox").attr('checked',true);
						 break;
					 	}
			 	 	}
			  	}
			});
	   }
   }	
  
  	 //senstive benefit warning for 'save/done' button.
function sensitiveBnftConfirmationDialog(variableId, variableFormat,action, auditStatus){
	var auditLockStatus=$("#auditStatus").val();
   var eb03Val = getTheValuesInTextBox("EB03Id","VALUE");
   isEb03Modified()
   var sensitiveBenefitList = new Array();
   var flagEB03 = 0;
   var warningMsgForcefullyUnchecked = "EB03 mapping has at least one Sensitive Benefit."  
 						   +" Do you want to review Accumulator Not Required Indicator ?";
    var warningMsgForcefullyChecked = "There are no Sensitive Benefits mapped to EB03."  
 						   +" Do you want to review Accumulator Not Required Indicator ?";						   

	//checks if the senstitive benefit indicator is checked
	//logic is changed so as to retrieve the sensitive benefits list from the errorvalidator.properties proeprties file. 
    //BXNI June2012 Release
   	$.ajax({
		url: "${context}/createpage/populateSensitiveBeenfitsList.ajax",
		dataType: "json",
		type: "POST",
		success: function(data) {
		cache: false;
		//Commented to remove Sens Bnft iNd -POR Wave 2
		//sensitiveBenefitList = data.sensitive;
		   //checks if the senstitive benefit indicator is checked
	if (($('#accumNtReqdChkBox').is(':checked'))){
		for(var j=0;j<sensitiveBenefitList.length;j++){
			if($.inArray(sensitiveBenefitList[j], eb03Val) != -1) {
				flagEB03 = 1;
				break;
			 }
		  }
		//if forcefully the indicator is removed, the conformation dialog warningMsgForcefullyUnchecked box is called
		  if(flagEB03 != 1 ){
		  		confirmationDialogSensitiveBenefit(variableId, variableFormat,action,warningMsgForcefullyChecked, auditLockStatus);
		  }else if(auditLockStatus=='Y'){
		  		confirmationDialogForEdit(action,variableId, variableFormat,auditLockStatus);
		  }else{
		  runSpellCheck(variableId, variableFormat,action, auditLockStatus);
		  }
		}
		//senstitive benefit indicator is not checked
	else {
		for(var j=0;j<sensitiveBenefitList.length;j++){
			if($.inArray(sensitiveBenefitList[j], eb03Val) != -1) {
					flagEB03 = 1;
					break;
			 }
		  }
		 //if forcefully the indicator is checked, the conformation dialog box with warningMsgForcefullyChecked is called
		  if(flagEB03 == 1 ){
		  	confirmationDialogSensitiveBenefit(variableId, variableFormat,action,warningMsgForcefullyUnchecked, auditStatus);
		  }else if(auditLockStatus=='Y'){
		  		confirmationDialogForEdit(action,variableId, variableFormat,auditLockStatus);
		  }else{
		 		runSpellCheck(variableId, variableFormat,action, auditLockStatus);
		  }
		}
	  }
	});
}

	function confirmationDialogForEdit(action,variableId, variableFormat,auditStatus ){
		if ($("[id=auditLockEditStatus]").val() == 'false') {
			addErrorToNotificationTray('Mapping is already locked');
			openTrayNotification();
		} else {
			$("#confirmationDivDiscardChanges").html("The mapping is audit locked. Do you want to update?");
			$("#confirmationDivDiscardChanges").addClass("UnmappedVariables");
			$("#confirmationDivDiscardChanges").dialog({
					autoOpen: false,
					title : 'Confirm',
					resizable: false,
					height:130,
					modal: true,
					buttons: {
						Cancel: function() {
							$(this).dialog('close');
						},
						Ok: function() {
							runSpellCheck(variableId, variableFormat,action,auditStatus);
							$(this).dialog('close');
						}
					}
				});
				$("#confirmationDivDiscardChanges").dialog('open');
		}
	}
	function confirmationDialogSensitiveBenefit(variableId, variableFormat,action,warningMsg,auditStatus){
	$("#confirmationDivSensitiveBnft").html(warningMsg);
	$("#confirmationDivSensitiveBnft").dialog({
			autoOpen: false,
			title : 'Confirm',
			resizable: false,
			height:140,
			width:400,
			modal: true,
			buttons: {			
			No: function() {
				if(auditStatus=='Y'){
					confirmationDialogForEdit(action,variableId, variableFormat,auditStatus);
				}else{
					runSpellCheck(variableId, variableFormat,action,auditStatus);
				}
				$(this).dialog('close');
				},		
			Yes: function() {
					$(this).dialog('close');
					$('#changeCommentsDialog').dialog('close');
				}
			}
		});
		$("#confirmationDivSensitiveBnft").dialog('open');
	}
	function lockAuditLock (variableId, systemName, lockUserId, variableDesc, usercomment) {
					$.ajax(	{
							url: "${context}/stateflow/auditLockVariable.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&systemName="+escape(systemName)+"&userName="+escape(lockUserId)+"&variableDesc="+escape(variableDesc)+"&userComment="+escape(usercomment),
							success: function(data) {
							cache: false,
							displayUnLockIconForLanding(data);}
						});
	}	
	function displayUnLockIconForLanding(data) {
		var errorMessages = data.error_messages;
		if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
				for(i=0; i< errorMessages.length; i++) {
					addErrorToNotificationTray(errorMessages[i]);
				}
		openTrayNotification();
		}else{
			var unLockIcon = '<a href = "#" id="AuditUnLock_'+data.variableId+ 
				'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Lock\');">Lock</a>';
				$("[id=AuditLock_"+data.variableId+"]").replaceWith(unLockIcon);
				$("[id=AuditUnLockLocateImg_"+data.variableId+"]").hide();
				$("[id=auditStatus]").val("N");
			if(data.isLockedOrUnlocked=='unlocked'){
				addErrorToNotificationTray('Mapping is not locked');
				openTrayNotification();
			}
		}
	}
	function unLockAuditLock (variableId, systemName, lockUserId, variableDesc, usercomment) {
					$.ajax({
							url: "${context}/stateflow/auditUnLockVariable.ajax",
							dataType: "json",
							type: "POST",			
							data: "variableId="+escape(variableId)+"&systemName="+escape(systemName)+"&userName="+escape(lockUserId)+"&variableDesc="+escape(variableDesc)+"&userComment="+escape(usercomment),
							success: function(data) {
							cache: false,
							displayLockIconFromLanding(data);}
						});
	}
	function displayLockIconFromLanding(data) {
	var errorMessages = data.error_messages;
	if(typeof(errorMessages) != 'undefined' && errorMessages.length >0) {
			for(i=0; i< errorMessages.length; i++) {
				addErrorToNotificationTray(errorMessages[i]);
			}
	openTrayNotification();
	}else{
		var lockIcon = '<a href = "#" id="AuditLock_'+data.variableId+ 
			'" onclick="openUserCommentsLockDialog(\''+data.variableId+'\',\''+data.systemName+'\',\''+data.userName+'\',\''+escape(data.variableDesc)+'\',\'Unlock\');">Unlock</a>';
			$("[id=AuditUnLock_"+data.variableId+"]").replaceWith(lockIcon);
		var lockImgIcon = '<img src="${imageDir}/AuditLockIndicatorNew.jpg" id="AuditUnLockLocateImg_'+data.variableId+'" alt="Lock" title="Lock" style="height:9px;width:9px"/>';
			$("[id=AuditUnLockLocateImg_"+data.variableId+"]").replaceWith(lockImgIcon);
			$("[id=AuditUnLockLocateImg_"+data.variableId+"]").show();
			$("[id=auditStatus]").val("Y");
		if(data.isLockedOrUnlocked=='locked'){
			addErrorToNotificationTray('Mapping is already locked');
			openTrayNotification();
		}
		}
	}
/***************BXNI June2012 Release Scripts START****************/
//method is called on load of the page, and disables the change comments popup initially
function disableChangeCommentsDiv(){
document.getElementById('changeCommentsDialog').style.display= 'none';
}
//method called on click fo save/done/send to test/approve and NA functionality to load the change comments popup
function loadChangeCommentsDialog(action){
if(checkHSD02Validation()){
	$("#hiddenChangeCommentsId").val(action);
	if (!$('#ageTieringChkBox').is(':checked') ){
	$('#startAge').val('');
	$('#endAge').val('');
}

var startAge = $("#startAge").val();
var endAge = $("#endAge").val();
ageTierVariableValidation(startAge,endAge);
}
}
//method is called on click of teh confirm button in the change comments popup and does the appropriate action
function changeCommentsConfirm(variableId, variableFormat, auditStatus){
	var changeComments = $("#changeCommentsId").val();
	 changeComments = $.trim(changeComments); 
	var action = $("#hiddenChangeCommentsId").val();
	 $("#changeComments").val(changeComments);
	if(changeComments == null || changeComments == ""){
		var message = "User Comments are mandatory";			
		addErrorToNotificationTray(message);
		openTrayNotification();	
		return false;
	}
	if(!imposeMaxLength('changeCommentsId' ,250,'Change Comments')){
			return false;
	}
	if(action == 'notApplicable'){
		notApplicable(variableId);
	}else{
		sensitiveBnftConfirmationDialog(variableId, variableFormat,action, auditStatus);
	}
	
} 
function showHideExtndMsgLink(id){
	if(id == "eb01ExtndMsgChkBox"){
		if ($('#'+id).is(':checked')){
			$("#eb01ExtndMsgLink").removeAttr("style");
			$("#eb01ExtndMsgLink").css({"color": "#077eed", "font-size": "11px"});
		} else if(!$('#'+id).is(':checked')){
			$("#eb01ExtndMsgLink").hide();
		}
	}else{
		if ($('#'+id).is(':checked')){
			$("#extndMsg" + id.substring(11)).removeAttr("style");
			$("#extndMsg" + id.substring(11)).css({"color": "#077eed", "font-size": "11px"});
		} else if(!$('#'+id).is(':checked')){
			$("#extndMsg" + id.substring(11)).hide();
		}
	}	
}
function enableDisableExtndMsgCheckBox(){
	if(getTheValuesInTextBox("EB01Id","VALUE")[0] !== "D"){
		$("#eb01ExtndMsgChkBox").attr("disabled","true");
		$("#eb01ExtndMsgLink").hide();
		var table = document.getElementById("eb03AssnTable");
		for(var row = 1;row<table.rows.length;row++){
			table.rows[row].cells[8].children[0].style.display = "none";
		}
	} else{
		$("#eb01ExtndMsgChkBox").removeAttr("disabled");
		if ($('#eb01ExtndMsgChkBox').is(':checked')){
			$("#eb01ExtndMsgLink").removeAttr("style");
			$("#eb01ExtndMsgLink").css({"color": "#077eed", "font-size": "11px"});
		} 
		var table = document.getElementById("eb03AssnTable");
		for(var row = 1;row<table.rows.length;row++){
			table.rows[row].cells[8].children[0].style.display = "block";
		}
	}
}

function storeEb01AndEb03ExtMsgsToList(){
	var eb01ExtndMsgJSON = [];
	var eb03ExtndMsgJSON = [];
	var eb01Arr = ${mapping.eb01.extendedMsgsForSelectedValues};
	var eb03Arr = ${mapping.eb03.extendedMsgsForSelectedValues};
	//var inputString = createEb01AndEb03InputString(eb01Arr, eb03Arr);
	if(eb01Arr.length > 0){
		if(eb01Arr[0].extndMsg1 == "null"){eb01Arr[0].extndMsg1 = ""}			
		if(eb01Arr[0].extndMsg2 == "null"){eb01Arr[0].extndMsg2 = ""}			
		if(eb01Arr[0].extndMsg3 == "null"){eb01Arr[0].extndMsg3 = ""}
		if(eb01Arr[0].networkInd == "null"){eb01Arr[0].networkInd = ""}
		eb01ExtndMsgJSON.push({"eb01NetworkInd": eb01Arr[0].networkInd, "changeInd": "", "eb01ExtndMessage1": eb01Arr[0].extndMsg1, "eb01ExtndMessage2":eb01Arr[0].extndMsg2, "eb01ExtndMessage3":eb01Arr[0].extndMsg3});
		if($("#eb01ExtndMsgJsonObj").val() == ""){
			eb01ExtndMsgJSON = JSON.stringify(eb01ExtndMsgJSON);
			$("#eb01ExtndMsgJsonObj").val(eb01ExtndMsgJSON);
		}	
	}
	for (i = 0; i < eb03Arr.length; i++) {
		if(eb03Arr[i].extndMsg1 == "null"){eb03Arr[i].extndMsg1 = ""}			
		if(eb03Arr[i].extndMsg2 == "null"){eb03Arr[i].extndMsg2 = ""}			
		if(eb03Arr[i].extndMsg3 == "null"){eb03Arr[i].extndMsg3 = ""}
		if(eb03Arr[i].networkInd == "null"){eb03Arr[i].networkInd = ""}									
		eb03ExtndMsgJSON.push({"eb03Val": eb03Arr[i].value, "eb03ExtndMsgSysId": eb03Arr[i].extendedMsgValueSysId, "eb03NetworkInd": eb03Arr[i].networkInd, "changeInd": "",
								 "eb03ExtndMessage1": eb03Arr[i].extndMsg1,"eb03ExtndMessage2":eb03Arr[i].extndMsg2, "eb03ExtndMessage3":eb03Arr[i].extndMsg3});
	}
	if($("#eb03ExtndMsgJsonObj").val() == ""){
		eb03ExtndMsgJSON = JSON.stringify(eb03ExtndMsgJSON);
		$("#eb03ExtndMsgJsonObj").val(eb03ExtndMsgJSON);
	}

}

var checkboxId="";
function showExtndMessagesScreen(actionId){		
	checkboxId=actionId;
	$("#extendedMsgScreen").dialog({
	                       height:'530',
						width:'690px',	
						resizable:false,
	                       show:'slide',
						title: 'Extended Message Screen',
	                       modal: true
	});
	$("#extendedMsgScreen").dialog();
	if(actionId=="eb01ExtndMsgLink"){
		if($("#eb01ExtndMsgJsonObj").val()===""){
			setExtndMsgs("","","","");
		} else {
			var obj= $("#eb01ExtndMsgJsonObj").val();
			var msgObj = JSON.parse(obj)[0];
			var ms1 = msgObj.eb01ExtndMessage1;
			if(ms1 === "null"){ms1 = ""}
			var ms2 = msgObj.eb01ExtndMessage2;
			if(ms2 === "null"){ms2 = ""}
			var ms3 = msgObj.eb01ExtndMessage3;
			if(ms3 === "null"){ms3 = ""}
			setExtndMsgs(ms1,ms2,ms3,msgObj.eb01NetworkInd);
		}
	}else{
		var extndMsgsFromUI = $("#eb03ExtndMsgJsonObj").val();
		var eb03Val = $("#EB03Label" + checkboxId.substring(8)).html().trim();
		var flag=true;
		if(extndMsgsFromUI !== ""){			
			var msgObj = JSON.parse(extndMsgsFromUI);
			for(i=0; i< msgObj.length; i++) {
				var obj = msgObj[i];
				if(obj.eb03Val === eb03Val){
					var ms1 = obj.eb03ExtndMessage1;
					if(ms1 === "null"){ms1 = ""}
					var ms2 = obj.eb03ExtndMessage2;
					if(ms2 === "null"){ms2 = ""}
					var ms3 = obj.eb03ExtndMessage3;
					if(ms3 === "null"){ms3 = ""}
					setExtndMsgs(ms1,ms2,ms3,obj.eb03NetworkInd);
					flag=false;
					break;
				}
			}
		}if(flag){
			setExtndMsgs("","","","");
		}
	}		
}

function setExtndMsgs(msg1,msg2,msg3,networkInd){
	$("textarea#extndMessage1").val(msg1);
	$("textarea#extndMessage2").val(msg2);
	$("textarea#extndMessage3").val(msg3);
	$("#networkInd").val(networkInd);
}

function saveExtndMsgs(){	
	var extndMessage1 = $("#extndMessage1").val();
	var extndMessage2 = $("#extndMessage2").val();
	var extndMessage3 = $("#extndMessage3").val();
	var networkInd = $("#networkInd").val();
	
	if(extndMessage1.trim()=="" && extndMessage2.trim()=="" && extndMessage3.trim()==""&&(networkInd=="Y"||networkInd=="N"))
	{
		var warnMessage="Enter Extend Message";
		addErrorToNotificationTray(warnMessage);
 		openTrayNotification(); 
 		return false;
	
	}
	if(extndMessage1.trim()=="" && extndMessage2.trim()=="" && extndMessage3.trim()==""){
		if(checkboxId=="eb01ExtndMsgLink"){
			$("#eb01ExtndMsgChkBox").removeAttr("checked");
			$("#eb01ExtndMsgLink").hide();
		} else{
			$("#"+checkboxId.substring(0,8)+"Req"+checkboxId.substring(8)).removeAttr("checked");
			$("#extndMsg" + checkboxId.substring(8)).hide();
		}
	}
	var pattern=/[\u0080-\uffff]/;
	var patternMsg=/1-/;
	var message = "Enter Valid Text Message for - ";
	if(pattern.test(extndMessage1)){
		message=message.concat("Extend Message 1-A");	
	}
	if(pattern.test(extndMessage2)){
		if(patternMsg.test(message)){
				message=message.concat(", ");
		}
		message=message.concat("Extend Message 1-B");	
	}
	if(pattern.test(extndMessage3)){
		if(patternMsg.test(message)){
				message=message.concat(", ");
		}
		message=message.concat("Extend Message 1-C");	
	}
	if(patternMsg.test(message)){                 
 		addErrorToNotificationTray(message);
 		openTrayNotification(); 
 		return false;
	}
	if(checkboxId=="eb01ExtndMsgLink"){			
		var eb01ExtndMsgJSON = [];
		//push the value to the json array
		var changeInd = "";
		if($("#eb01ExtndMsgValSysId").val() != ""){
			changeInd = "U";
		}
		eb01ExtndMsgJSON.push({"eb01NetworkInd": networkInd, "changeInd": changeInd, "eb01ExtndMessage1": extndMessage1,"eb01ExtndMessage2":extndMessage2, "eb01ExtndMessage3":extndMessage3});
		//stringify the json array and assign the same to a hidden request parameter
		eb01ExtndMsgJSON = JSON.stringify(eb01ExtndMsgJSON);
		$("#eb01ExtndMsgJsonObj").val(eb01ExtndMsgJSON);
	}else{	
		var eb03Val = $("#EB03Label" + checkboxId.substring(8)).html().trim();
		var found = false;
		var changeInd = "";
		var eb03ExtndMsgSysId="";
		var eb03ExtndMsgJSON = $("#eb03ExtndMsgJsonObj").val();
		var arrayObj = JSON.parse(eb03ExtndMsgJSON);
		for(i=0; i< arrayObj.length; i++) {
			if(arrayObj[i].eb03Val === eb03Val){
				eb03ExtndMsgSysId = arrayObj[i].eb03ExtndMsgSysId;
				changeInd = "U";
				if(eb03ExtndMsgSysId == "" || eb03ExtndMsgSysId == null){
					changeInd = "A";
				}
				arrayObj.splice(i,1);
				found = true;
				break;
			}
		}
		if(!found){
			eb03ExtndMsgSysId="";
			changeInd = "A";
		}
		arrayObj.push({"eb03Val": eb03Val, "eb03ExtndMsgSysId": eb03ExtndMsgSysId, "eb03NetworkInd": networkInd, "changeInd": changeInd, "eb03ExtndMessage1": extndMessage1,"eb03ExtndMessage2":extndMessage2, "eb03ExtndMessage3":extndMessage3});
		//stringify the json array and assign the same to a hidden request parameter
		arrayObj = JSON.stringify(arrayObj);
		$("#eb03ExtndMsgJsonObj").val(arrayObj);
	}
	runSpellCheckForExtMsg();
	$("#extendedMsgScreen").dialog('close');	
}

function countChars(msgId){
	var maxLength = 260;
	var len = document.getElementById(msgId).value.length;
	if(msgId == "extndMessage1"){
		document.getElementById('extndMsgForCount1').innerHTML = maxLength-len;
	} else if(msgId == "extndMessage2"){
		document.getElementById('extndMsgForCount2').innerHTML = maxLength-len;
	} else if(msgId == "extndMessage3"){
		document.getElementById('extndMsgForCount3').innerHTML = maxLength-len;
	}
}

function cancelExtndMsgs(){
	$("#extendedMsgScreen").dialog('close');
}
function cancelhpnMapgMsgScreen(){
	$("#hpnMapgMsgScreen").dialog('close');
}
function ageTierRequired(){
if ($('#ageTieringChkBox').is(':checked') ){

$('#ageAgeTier1').show();
$('#ageAgeTier2').hide();

$('#ageAgeTier3').show();
$('#ageAgeTier4').hide();

$('#ageAgeTier5').show();
$('#ageAgeTier6').hide();

$('#ageAgeTier7').show();
$('#ageAgeTier8').hide();

$('#ageAgeTier9').hide();
$('#ageAgeTier10').hide();

}

if (!$('#ageTieringChkBox').is(':checked') ){

$('#ageAgeTier1').hide();
$('#ageAgeTier2').show();

$('#ageAgeTier3').hide();
$('#ageAgeTier4').show();

$('#ageAgeTier5').hide();
$('#ageAgeTier6').show();

$('#ageAgeTier7').hide();
$('#ageAgeTier8').show();

$('#ageAgeTier9').show();
$('#ageAgeTier10').show();


}
}

function loadAgeTierTextBox(){

if($.trim($("#startAge").val())== "" && $.trim($("#endAge").val()) == "" ){

$('#ageAgeTier1').hide();
$('#ageAgeTier2').show();

$('#ageAgeTier3').hide();
$('#ageAgeTier4').show();

$('#ageAgeTier5').hide();
$('#ageAgeTier6').show();

$('#ageAgeTier7').hide();
$('#ageAgeTier8').show();

$('#ageAgeTier9').show();
$('#ageAgeTier10').show();
}else{
$('#ageAgeTier1').show();
$('#ageAgeTier2').hide();

$('#ageAgeTier3').show();
$('#ageAgeTier4').hide();

$('#ageAgeTier5').show();
$('#ageAgeTier6').hide();

$('#ageAgeTier7').show();
$('#ageAgeTier8').hide();

$('#ageAgeTier9').hide();
$('#ageAgeTier10').hide();
}
}

function showHpnMapgMessagesScreen(){
	$("#hpnMapgMsgScreen").dialog({
	                       height:'530',
						width:'690px',	
						resizable:false,
	                       show:'slide',
						title: 'HPN Mapping Message Screen',
	                       modal: true
	});
	$("#hpnMapgMsgScreen").dialog();
 }

/***************BXNI June2012 Release Scripts END****************/
</script>
</head>

<body onload="storeEb01AndEb03ExtMsgsToList();storeinputs(); storeeb03values();disableChangeCommentsDiv(); loadAgeTierTextBox(); displayIndvidualEB03AssnPanel(); enableDisableExtndMsgCheckBox(); " >
	<form name="stateFlowForm"  method = "POST">
	<input type="hidden" id="pageFrom" name="pageFrom" value="${mapping.pageFrom}" /> 
	<input type="hidden" id="pageName" value="variableMappingPage"/>
	<input type="hidden" id="contextPath" value= "<%=request.getContextPath()%>"/>
	<input type="hidden" name="auditLockEditStatus" id="auditLockEditStatus" value="${userUIPermissions.authorizedEditLockVar}" />
	<input type="hidden" id="eb03List" name="eb03List" />
	<input type="hidden" id="eb03ExistingList" name = "eb03ExistingList" />
	<input type="hidden" id="eb03AssnJsonObj" name="eb03AssnJsonObj" />
	<input type="hidden" id="eb01ExtndMsgJsonObj" name="eb01ExtndMsgJsonObj" />
	<input type="hidden" id="eb03ExtndMsgJsonObj" name="eb03ExtndMsgJsonObj" />	
		<%@include file="/WEB-INF/jspf/pageTop.jspf"%>

		<input type="hidden" id="EB01Desc" name="EB01Desc" value=""/>
		<input type="hidden" id="EB02Desc" name="EB02Desc" value=""/>
		<input type="hidden" id="EB06Desc" name="EB06Desc" value=""/>
		<input type="hidden" id="EB09Desc" name="EB09Desc" value=""/>

		<input type="hidden" id="HSD01Desc" name="HSD01Desc" value=""/>
		<input type="hidden" id="HSD02Desc" name="HSD02Desc" value=""/>
		<input type="hidden" id="HSD03Desc" name="HSD03Desc" value=""/>
		<input type="hidden" id="HSD04Desc" name="HSD04Desc" value=""/>
		<input type="hidden" id="HSD05Desc" name="HSD05Desc" value=""/>
		<input type="hidden" id="HSD06Desc" name="HSD06Desc" value=""/>
		<input type="hidden" id="HSD07Desc" name="HSD07Desc" value=""/>
		<input type="hidden" id="HSD08Desc" name="HSD08Desc" value=""/>
		<input type="hidden" id="changeComments" name="changeComments" value=""/>

		<input type="hidden" id="III02Desc" name="III02Desc" value=""/>
		<input type="hidden" id="NoteTypeDesc" name="NoteTypeDesc" value=""/>
		<input type="hidden" id="mappingItem" value="WPD" name="mappingItem" />	
		<input type="hidden" id="mappingHidden" value="${mapping}" name="mappingHidden" />	
		<input type="hidden" id="nm1MessageSegmentDesc" name="nm1MessageSegmentDesc" value=""/>
		 
          <!-- container Starts-->
          <div class="innerContainer" style="height:100%;" style="overflow-y:hidden">
            <!-- innerContainer Starts-->
              <div  id="variableInfoDiv" class="overflowContainerVariable">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="Pd3">
					<THEAD>
						<tr class="createEditTable1">
		                    <td width="100" class="tableHeader">Variable ID</td>
		                    <td width="240" class="tableHeader">Description</td>
		                    <td width="55" class="tableHeader">PVA</td>	
							<td width="70" class="tableHeader">Data Type</td>
							<td width="70" class="tableHeader">Category</td>
							<td width="73" class="tableHeader">System</td>
		                    <td width="165" class="tableHeader">Minor Heading</td>
		                    <td width="165" class="tableHeader">Major Heading</td>
		                    <td width="165" class="tableHeader">WPD Accumulator</td>
		                     <td align="right"><a href="#" onclick='printPage();'><img src="${imageDir}/print.gif" id="printIcon" style="height:15px; padding-right:30px"></a></td>
	                  	</tr>
				</THEAD>
				<TBODY>
					<c:set var="viewHistoryRowCount" value="0" />
					<input type="hidden" id="providerArrangement" value="${variableList[0].pva}" name="providerArrangement"/>
					<input type="hidden" id="variableFormat" value="${variableList[0].variableFormat}" name="variableFormat"/>
					<input type="hidden" name="variableIdHidden" id="variableIdHidden" value="${variableList[0].variableId}"/>
					<input type="hidden" name="variableDescHidden" id="variableDescHidden" value="${variableList[0].description}"/>
					<input type="hidden" name="variableData" id="variableData" value="${variableList}"/>
					<input type="hidden" id="isgCatagory" name="isgCatagory" value="${variableList[0].isgCatagory}"/>
					<input type="hidden" id="lgCatagory" name="lgCatagory" value="${variableList[0].lgCatagory}"/>
					<input type="hidden" id="system" name="system" value="${variableList[0].variableSystem}"/>
                    <input type="hidden" id="auditStatus" value="${mapping.auditLock}" name="auditStatus"/>
                    <input type="hidden" name="hpnMapgList" id="hpnMapgList" value="${hpnMapgList}"/>
					<c:set var="rowCount" value="0"/>
					<tr>
						<td>${variableList[0].variableId}
						<c:if test="${mapping.auditLock == 'Y' && mapping.variableMappingStatus != 'UNMAPPED' }">
						<img src="${imageDir}/AuditLockIndicatorNew.jpg" alt="Lock" title="Lock" id="AuditUnLockLocateImg_${variableList[0].variableId}" style="height:9px;width:9px" />
						</c:if>
						<c:if test="${mapping.auditLock == 'N' && mapping.variableMappingStatus != 'UNMAPPED' }">
						<div id="AuditUnLockLocateImg_${variableList[0].variableId}"  ></div>
						</c:if>
						</td>
						<td>${variableList[0].description}</td>
						<td>${variableList[0].pva}</td>
						<td>${variableList[0].dataType}</td>
						<td>						
							<c:if test="${variableList[0].lgCatagory != null}">
									${variableList[0].lgCatagory}
							</c:if>
							<c:if test="${variableList[0].lgCatagory == null && variableList[0].isgCatagory != null}">
									${variableList[0].isgCatagory}
							</c:if>
						</td>
						<td>${variableList[0].variableSystem}</td>						
						<td>${variableList[0].minorHeadings}</td>
						<td>${variableList[0].majorHeadings}</td>	
						<td>${accumList[0]}</td>
						<td></td>
					</tr>
					<c:set var="counter" value="1" />
					<input type="hidden" id="minorheading0" name="minorheading" value="${variableList[0].minorHeadings}"/>
					<input type="hidden" id="majorheading0" name="majorheading" value="${variableList[0].majorHeadings}"/>
					<c:set var="variableInfoDivScroll" value="false" />	
					<c:forEach items="${variableList}" var="variable" begin="1">
						<c:set var="variableInfoDivScroll" value="true" />
						<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
							<td></td>
							<td></td>
							<td></td>	
							<td></td>										
							<td></td>
							<td></td>
							<td>${variable.minorHeadings}</td>
							<td>${variable.majorHeadings}</td>	
							<td>${accumList[counter]}</td>	
							<td></td>										 
						</tr>
					<c:set var="rowCount" value="${rowCount + 1}"/>
					<c:set var="counter" value="${counter + 1}"/>
					<input type="hidden" id="minorheading${rowCount}" name="minorheading" value="${variable.minorHeadings}"/>
					<input type="hidden" id="majorheading${rowCount}" name="majorheading" value="${variable.majorHeadings}"/>
					</c:forEach>
					
					<c:forEach items="${accumList}" var="accum"  begin="${counter}">
						<c:set var="variableInfoDivScroll" value="true" />
						<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
							<td width="100px"></td>
							<td width="150px"></td>
							<td width="90x"></td>
							<td width="90px"></td>
							<td width="75px"></td>
							<td width="75px"></td>
							<td width="170px"></td>
							<td width="170px"></td>	
							<td width="75px">
								${accum}					
							</td>	
							<td width="75px"></td>		
						</tr>
					<c:set var="rowCount" value="${rowCount + 1}"/>
					<c:set var="counter" value="${counter + 1}"/>
				</c:forEach>
					<c:if test="${variableInfoDivScroll == 'true'}">
						<script>
						$('#variableInfoDiv').height('80px');
						</script>
					</c:if>
				</TBODY>
                </table>
                <input type="hidden" id="valuesOfMinorHeadings" name="valuesOfMinorHeadings"/>
                <input type="hidden" id="valuesOfMajorHeadings" name="valuesOfMajorHeadings"/>
				</div>
			
	<!--BXNI June Change-->
		<!--Check Box Table-->
			<div id="checkBoxContainer" >
				<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 ">
                   <tr>
                    	<td class="headText" colspan="2" align ="left" nowrap>&#160;
                    		<!-- Age Tier Check box conditions -->
                    		<c:set var="ifChecked" value="checked"></c:set>
                    		<c:if test="${(mapping.startAge.hippaCodeSelectedValues[0].value == '' || mapping.startAge.hippaCodeSelectedValues[0].value == null) && 
                    		(mapping.endAge.hippaCodeSelectedValues[0].value == '' || mapping.endAge.hippaCodeSelectedValues[0].value == null)}">
                    		<c:set var="ifChecked" value=""></c:set>
                    		</c:if><!-- Age Tier Check box conditions End-->
                    		
                    		<input type="checkbox" name="ageTieringChkBox" id="ageTieringChkBox" ${ifChecked} onclick="ageTierRequired()"/>&#160;Age Tiering Required&#160;&#160;&#160;&emsp;&emsp;
                    		
                    		<!-- Extended msg Check box condition -->
                    		<c:set var="ifChecked" value=""></c:set>
                    		<c:if test="${mapping.eb01.extendedMsgsForSelectedValues[0].changeInd != 'D' && (mapping.eb01.extendedMsgsForSelectedValues[0].extndMsg1 != null || mapping.eb01.extendedMsgsForSelectedValues[0].extndMsg2 != null || mapping.eb01.extendedMsgsForSelectedValues[0].extndMsg3 != null)}">
                    			<c:set var="ifChecked" value="checked"></c:set>
                    		</c:if><!-- Extended msg Check box condition End-->
                    		<input type="checkbox" name="eb01ExtndMsgChkBox" id="eb01ExtndMsgChkBox" ${ifChecked} onclick="showHideExtndMsgLink(this.id)"/>&#160;Extended Message&#160;&#160;&#160;&emsp;&emsp;
                    		
                    		<c:if test="${mapping.eb01.extendedMsgsForSelectedValues[0].changeInd != 'D' && (mapping.eb01.extendedMsgsForSelectedValues[0].extndMsg1 != null || mapping.eb01.extendedMsgsForSelectedValues[0].extndMsg2 != null || mapping.eb01.extendedMsgsForSelectedValues[0].extndMsg3 != null)}">
								<a href="#" style="color: #077eed; font-size: 11px;" id="eb01ExtndMsgLink" onclick="showExtndMessagesScreen(this.id)">&#160;&#160;Ext msg</a>
							</c:if>
							<a href="#" style="display: none;" id="eb01ExtndMsgLink" onclick="showExtndMessagesScreen(this.id)">&#160;&#160;Ext msg</a>										
                    		<input type="hidden"  name="eb01ExtndMsgValSysId" id="eb01ExtndMsgValSysId" value="${mapping.eb01.extendedMsgsForSelectedValues[0].extendedMsgValueSysId}" />
                    	
                    		<c:if test="${!empty hpnMapgList}">
							<a href="#" style="color: #077eed; font-size: 10px;" id="hpnMapgMsgLink" onclick="showHpnMapgMessagesScreen()">&#160;&#160;HPN Mapg</a>
	                    	</c:if>
                    	</td>
	                    <td  class="headText" colspan="2" align ="right" nowrap  >&#160;
	                  		 <input type="checkbox" <c:if test="${mapping.finalized==true}">checked</c:if> name="notFinalizedChkBox" id="notFinalizedChkBox" value="checked"/>&#160;Not Finalized&#160;&#160;&#160;
						</td>
	                </tr>
				</table>
			</div><!--Check Box Table End -->
			
		<div id="createEditContainer" style="margin-top:5px;">	<!--createEditContainer Starts-->
				<c:if test="${variableInfoDivScroll == 'true'}">
					<script>
						$('#createEditContainer').height('330px');							
					</script>
				</c:if>
					
			<!--EB Codes  Table-->	
				<table width="950px" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5" id="ebCodesTable">
					<tbody id="ebTbody">
					<tr class="createEditTable1-Listdata">
	                    
	                    <td  colspan="2" class="headText">EB01 
	                    <a href="#"  tabIndex="-1" onclick="displayInfo('EB01',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
	                    <a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB01" onclick="popupDiv('EB01','../ajaxpopup.ajax')" /></a>
	                  	</td>
	                  	 <td width="20px">&#160;</td>
	                  	<td  colspan="2" class="headText">EB02 
	                  	<a href="#" tabIndex="-1" onclick="displayInfo('EB02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
	                  	<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB02" onclick="popupDiv('EB02','../ajaxpopup.ajax')"/></a>
	                  	</td>
	                    <td width="20px">&#160;</td>
	                    <td  colspan="2" class="headText">EB03 
	                    <a href="#" tabIndex="-1" onclick="displayInfo('EB03',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
	                     <a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB03" onclick="popupDiv('EB03','../ajaxpopup.ajax')"/></a>
	                    </td>
	                     <td width="10px">&#160;</td>
	                    <td width="190px" colspan="2" align="left" class="headText">
	                    	<input type="checkbox" name="indEB03AssnCheckBox" id="indEB03AssnCheckBoxId" onclick="displayIndvidualEB03AssnPanel();" <c:if test="${mapping.indvdlEb03AssnIndicator == 'Y'}">checked</c:if>/>&#160;Individual EB03 Mapping Reqd&#160;
	                    </td>
	                    <td width="1px;"></td>
	                    
	                  </tr>	
	                  <tr class="createEditTable1-Listdata alternate">
	                    <td width="70px">							
								<input type="text" name="eb01Val" id="EB01Id" onblur="enableDisableExtndMsgCheckBox()" class="inputbox60" value="${mapping.eb01.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb01SysId" id="EB01SysId" value="${mapping.eb01.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="145px">
							&#160;<label id="EB01IdLabel" for="EB01Id" style="font-size:11px">								
									<c:out value="${mapping.eb01.hippaCodeSelectedValues[0].description}" />								
							</label>
							</br>
							<input <c:if test="${mapping.procedureExcludedInd == 'Y'}">checked</c:if> 
								type=checkbox id=excludeProceduresChkBox name=excludeProceduresChkBox value="checked"/>
							<label class=headText noWrap align=left id="lblProceduresExcluded" >Procedures Excluded</label>
							<a id="hrefProcedureExcluded" href="#"  tabIndex="-1" onclick="displayInfo('EXCLUDE PROCEDURE',event,'../ajaxhippasegmenttooltip.ajax');">?</a>&#160;
						</td>
						 <td width="20px">&#160;</td>
	               		<td width="70px" >						
								<input type="text" name="eb02Val" id="EB02Id" class="inputbox60" value="${mapping.eb02.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb02SysId" id="EB02SysId" value="${mapping.eb02.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
						
	                    <td width="100px">
							<label id="EB02IdLabel" for="EB02Id" style="font-size:11px">								
									<c:out value="${mapping.eb02.hippaCodeSelectedValues[0].description}" />								
							</label>
						</td>
						<td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" name="eb03Val"  id="EB03Id0"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[0].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId0" value="${mapping.eb03.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />	
						</td>
						
	                    <td width="100px">
							<label id="EB03IdLabel0" for="EB03Id0" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}" />
							</label>
							<input type="hidden" id="EB03Desc0" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/>
						</td>
						 <td width="20px">&#160;</td>
						 <td width="70px" >						
								<input type="text" name="eb03Val"  id="EB03Id1"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[1].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId1" value="${mapping.eb03.hippaCodeSelectedValues[1].hippaCodeValueSysId}" />	
						</td>
	                    <td width="100px">
							<label id="EB03IdLabel1" for="EB03Id1" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[1].description}" />
							</label>
							<input type="hidden" id="EB03Desc1" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[1].description}"/>
						</td>
						
	                  </tr>
					<tr class="createEditTable1-Listdata">
	                    <td class="headText"  colspan="2">EB06 <a href="#" tabIndex="-1"  onclick="displayInfo('EB06',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB06" onclick="popupDiv('EB06','../ajaxpopup.ajax')"/></a></td>
	                     <td width="20px">&#160;</td>
	                    <td class="headText" colspan="2">EB09 <a href="#"  onclick="displayInfo('EB09',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="EB09" onclick="popupDiv('EB09','../ajaxpopup.ajax')"/></a></td>
	                    <td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" name="eb03Val"  id="EB03Id2"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[2].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId2" value="${mapping.eb03.hippaCodeSelectedValues[2].hippaCodeValueSysId}" />	
						</td>						
						<td width="100px">
							<label id="EB03IdLabel2" for="EB03Id2" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[2].description}" />
							</label>
							<input type="hidden" id="EB03Desc2" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[2].description}"/>
						</td>
						<td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" name="eb03Val"  id="EB03Id3"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[3].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId3" value="${mapping.eb03.hippaCodeSelectedValues[3].hippaCodeValueSysId}" />	
						</td>						
						<td width="100px">
							<label id="EB03IdLabel3" for="EB03Id3" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[3].description}" />
							</label>
							<input type="hidden" id="EB03Desc3" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[3].description}"/>
						</td>
	                  </tr>	
	                  
	                  
	                  
					<tr class="createEditTable1-Listdata alternate">
					     <td width="70px">						
								<input type="text" name="eb06Val" id="EB06Id" class="inputbox60" value="${mapping.eb06.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb06SysId" id="EB06SysId" value="${mapping.eb06.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="100px">
								<label id="EB06IdLabel" for="EB06Id" style="font-size:11px">
										<c:out value="${mapping.eb06.hippaCodeSelectedValues[0].description}" />
								</label>
						</td>
						 <td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" name="eb09Val" id="EB09Id" class="inputbox60" value="${mapping.eb09.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="eb09SysId" id="EB09SysId" value="${mapping.eb09.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
	                   </td>
	                    <td width="100px">
								<label id="EB09IdLabel" for="EB09Id" style="font-size:11px">									
										<c:out value="${mapping.eb09.hippaCodeSelectedValues[0].description}" />
								</label>
						</td>
						<td width="20px">&#160;</td>
	                    <td width="70px" >					
								<input type="text" name="eb03Val"  id="EB03Id4"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[4].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId4" value="${mapping.eb03.hippaCodeSelectedValues[4].hippaCodeValueSysId}" />	
						</td>
	                    <td width="100px">
							<label id="EB03IdLabel4" for="EB03Id4" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[4].description}" />
							</label>
							<input type="hidden" id="EB03Desc4" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[4].description}"/>
						</td>
						 <td width="20px">&#160;</td>
	                    <td width="70px" >							
								<input type="text" name="eb03Val"  id="EB03Id5"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[5].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId5" value="${mapping.eb03.hippaCodeSelectedValues[5].hippaCodeValueSysId}" />	
						</td>
	                    <td width="100px">
							<label id="EB03IdLabel5" for="EB03Id5" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[5].description}" />
							</label>
							<input type="hidden" id="EB03Desc5" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[5].description}"/>
						</td>
	                </tr>	
	                <tr class="createEditTable1-Listdata">
	                    <td id="ageAgeTier1" class="headText"  colspan="2">Start Age <a href="#" tabIndex="-1"  onclick="displayInfo('STARTAGE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
	                    <a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="STARTAGE" onclick="popupAgeTierVariableDiv('startAge','../ajaxagetierpopup.ajax')"/></a></td>
	                    <td id="ageAgeTier2" width="70px" ></td>
	                    <td id="ageAgeTier9" width="100px" ></td>
	                    
	                    <td width="20px">&#160;</td>
	                    <td id="ageAgeTier3" class="headText" colspan="2">End Age <a href="#"  onclick="displayInfo('ENDAGE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
	                    <a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="ENDAGE" onclick="popupAgeTierVariableDiv('endAge','../ajaxagetierpopup.ajax')"/></a></td>
	                    <td id="ageAgeTier4" width="70px" ></td>
	                    <td id="ageAgeTier10" width="100px" ></td>
	                    
	                    <td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" name="eb03Val"  id="EB03Id6"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[6].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId6" value="${mapping.eb03.hippaCodeSelectedValues[6].hippaCodeValueSysId}" />	
						</td>						
						<td width="100px">
							<label id="EB03IdLabel6" for="EB03Id6" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[6].description}" />
							</label>
							<input type="hidden" id="EB03Desc6" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[6].description}"/>
						</td>
						<td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" name="eb03Val"  id="EB03Id7"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[7].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId7" value="${mapping.eb03.hippaCodeSelectedValues[7].hippaCodeValueSysId}" />	
						</td>						
						<td width="100px">
							<label id="EB03IdLabel7" for="EB03Id7" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[7].description}" />
							</label>
							<input type="hidden" id="EB03Desc7" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[7].description}"/>
						</td>
	                  </tr>	
					
					<tr class="createEditTable1-Listdata alternate">
					     <td id="ageAgeTier5" width="70px" colspan = "2">						
								<textarea name="startAge" id="startAge" onchange="autoPopulateHSD01();" cols="28">${mapping.startAge.hippaCodeSelectedValues[0].value}</textarea>
								<input type="hidden"  name="startAgeSysId" id="startAgeSysId" value="${mapping.startAge.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
						<td id="ageAgeTier6" width="80px" colspan = "2">	
	                    <td width="10px">
								<label id="startAgeIdLabel" for="startAge" style="font-size:11px">
								</label>
						</td>
						 
	                    <td id="ageAgeTier7" width="70px" colspan = "2">						
								<textarea name="endAge" id="endAge" onchange="autoPopulateHSD01();" cols="28">${mapping.endAge.hippaCodeSelectedValues[0].value}</textarea>
								<input type="hidden"  name="endAgeSysId" id="endAgeSysId" value="${mapping.endAge.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
	                   </td>
	                   <td id="ageAgeTier8" width="80px" colspan = "2">
	                    
								<label id="endAgeIdLabel" for="endAge" style="font-size:11px">									
								</label>
						
						<td width="10px">&#160;</td>
						
	                    <td width="70px" >					
								<input type="text" name="eb03Val"  id="EB03Id8"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[8].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId8" value="${mapping.eb03.hippaCodeSelectedValues[8].hippaCodeValueSysId}" />	
						</td>
	                    <td width="100px">
							<label id="EB03IdLabel8" for="EB03Id8" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[8].description}" />
							</label>
							<input type="hidden" id="EB03Desc8" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[8].description}"/>
						</td>
						 <td width="20px">&#160;</td>
	                    <td width="70px" >							
								<input type="text" name="eb03Val"  id="EB03Id9"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[9].value}"/>
								<input type="hidden"  name="eb03SysId" id="EB03SysId9" value="${mapping.eb03.hippaCodeSelectedValues[9].hippaCodeValueSysId}" />	
						</td>
	                    <td width="100px">
							<label id="EB03IdLabel9" for="EB03Id9" style="font-size:11px">
								<c:out value="${mapping.eb03.hippaCodeSelectedValues[9].description}" />
							</label>
							<input type="hidden" id="EB03Desc9" name="EB03Desc" value="${mapping.eb03.hippaCodeSelectedValues[9].description}"/>
						</td>
	                </tr>	
	                
	                

					<c:if test="${fn:length(mapping.eb03.hippaCodeSelectedValues) > 10}">
							<c:set var="count" value="10"/>
						<c:forEach items="${mapping.eb03.hippaCodeSelectedValues}"	var="eb03Values" begin="10" >
						<input type="hidden"  name="eb03SysId" id="EB03SysId${count}" value="${eb03Values.hippaCodeValueSysId}" />
							<script type="text/javascript">
								insertVariableEBO3('ebCodesTable','EB03Id${count}','eb03Val','${eb03Values.value}',true,'EB03IdLabel${count}',"${eb03Values.description}",'EB03Desc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</c:if>
				</tbody>
			</table><!--EB Codes  Table End-->	
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
						 <a href="#"><img alt= "Add Row for EB03" title="Add Row for EB03" id="eb03AddButtonforVariable" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForVariableEb03('ebCodesTable','EB03Id','eb03Val','',true,'');"/></a>
						</td>
	                    <td width="100px">
							<label  style="font-size:11px"></label>
						</td>
						</tr>
					</tbody>
				</table>
		</div>
		
		
		<!-- EB03 Association Table - Start -->
			
		<div>
			<table width="950px" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT" id="eb03AssnTable">
				<tbody id = "eb03AssnTableBody">
					<c:if test= "${!empty eB03AssnList}">
						<tr class="createEditTable1-Listdata">
							<td class="headText"  colspan="2" align="left">
								EB03
							</td>
							
							<td class="headText"  colspan="3" align="left"> III02	
								<a href="#"  onclick="displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>
							</td>
							
							<td class="headText"  colspan="3" align="left"> Message Text	
								<a href="#"  onclick="displayInfo('MSG',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>
							</td>
							
							<td class="headText"  colspan="2" align="left"> Extnd Msg	
							</td>
							
							<td class="headText"  colspan="2" align="center"> Msg Reqd	
							</td>
							
							<td class="headText"  colspan="3" align="left"> Note Type Code	
								<a href="#"  onclick="displayInfo('NOTE_TYPE_CODE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>
							</td>
						
						</tr>
						
						<c:set var="count" value="0"/>
						<c:set var="rowCount" value="2"/>
					
						<c:forEach items="${eB03AssnList}" begin="0">
							<c:if test= "${!empty eB03AssnList[count].eb03.value}">
								<tr class="${rowCount mod 2 == 0 ? 'alternate' : 'white-bg'}">
								
									<td width="30px;" align="center">
										<label id="EB03Label${count}" for="EB03Label${count}" style="font-size: 11px">
											${eB03AssnList[count].eb03.value}
										</label>
									</td>
									
									<td width="20px;">
									</td>
									
									<td width="90px;" >
										<input type="text"" name="ii02Val${count}" class="inputbox60" id="III02Id${count}" 
											value="${eB03AssnList[count].iii02List[0].value}" onmousedown="autocompleteIII02();" onclick="autocompleteIII02();"/>
									</td>
									
									<td width="25px;" style="padding-top: 8px;">
										<a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" name="III02_${count}" 
											onclick= "popupDiv(this.name,'../ajaxpopup.ajax');"/></a>
									</td>
									
									<td width="100px;">
										<label id="III02Id${count}Label" style="font-size:11px"> ${eB03AssnList[count].iii02List[0].description}</label>
									</td>				
									
									<td>
										<textarea rows="1" cols="30" onfocus="this.rows=2;" onblur="this.rows=1; changeTextToUpperCase(this.id);" style="resize: none;" 
											id="MESSAGEVALUEId${count}" name="messageValue${count}">${eB03AssnList[count].message}</textarea>
									</td>
									
									<td width="25px;" style="padding-top: 8px;" >
										<a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" name="MESSAGEVALUEId${count}"
											onclick="openMessageTextDialog(this.name)"/></a>
									</td>
									
									<td width="10px;">
									</td>
									
									<td width="150px" align="left">
										<div> 
											<c:set var="ifChecked" value=""></c:set>									
											<c:forEach items="${mapping.eb03.extendedMsgsForSelectedValues}" var="eb03ExtMsgsFromDB" >
												<c:if test="${(eb03ExtMsgsFromDB.value == eB03AssnList[count].eb03.value) && (eb03ExtMsgsFromDB.changeInd != 'D') && (eb03ExtMsgsFromDB.extndMsg1 != '' 
					                    				 || eb03ExtMsgsFromDB.extndMsg2 != '' || eb03ExtMsgsFromDB.extndMsg3 != '' )}">
					                    				 <c:set var="ifChecked" value="checked"></c:set>
												</c:if>
											</c:forEach>
											<input type="checkbox" ${ifChecked} name="extndMsgReq${count}" id="extndMsgReq${count}" onclick="showHideExtndMsgLink(this.id)" />
											
											<c:forEach items="${mapping.eb03.extendedMsgsForSelectedValues}" var="eb03ExtMsgsFromDB" >
												<c:if test="${(eb03ExtMsgsFromDB.value == eB03AssnList[count].eb03.value) && (eb03ExtMsgsFromDB.changeInd != 'D') && (eb03ExtMsgsFromDB.extndMsg1 != '' 
					                    				 || eb03ExtMsgsFromDB.extndMsg2 != '' || eb03ExtMsgsFromDB.extndMsg3 != '' )}">
													<a href="#" style="color: #077eed; font-size: 11px;" id="extndMsg${count}" onclick="showExtndMessagesScreen(this.id)">&#160;&#160;Ext msg</a>
												</c:if>
											</c:forEach>
											<a href="#" style="display: none;" id="extndMsg${count}" onclick="showExtndMessagesScreen(this.id)">&#160;&#160;Ext msg</a>										
										</div>
									</td>
									
									<td width="10px;">
									</td>
									
									<td width="100px" align="center">
									   <input type="checkbox" <c:if test="${eB03AssnList[count].msgReqdInd == 'Y'}">checked</c:if> 
											name="msgReqd${count}" id="msgReqd${count}" />
									</td>
									
									<td width="20px;">
									</td>				
									
									<td width="90px;" >
										<input type="text" name="noteType${count}" class="inputbox60"
											id="NOTETYPEID${count}" value="${eB03AssnList[count].noteType.value}" onselect="autocompleteNoteType();" onclick="autocompleteNoteType();" />
									</td>
									
									<td width="25px;" style="padding-top: 8px;">
										<a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" 
										 name="NOTE_TYPE_CODE_${count}" onclick="popupDiv(this.name,'../ajaxpopup.ajax');"></a>
									</td>
									
									<td width="250px;">
										<label id="NOTETYPEID${count}Label" style="font-size:11px">${eB03AssnList[count].noteType.description}</label>
									</td>
								</tr>
								
								<c:set var="count" value="${count + 1}"/>
								<c:set var="rowCount" value="${rowCount + 1}"/>
									
							</c:if>
						</c:forEach>
					</c:if>
				</tbody>		
			</table>
		</div>
		<!-- EB03 Association Table - End -->
		
		<!--HSD  Table-->		
			<div>
				<table width="950px" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
				
					<tr class="createEditTable1-Listdata">
	                    
	                    <td  colspan="2" class="headText">HSD01 
	                    <a href="#"  tabIndex="-1" onclick="displayInfo('HSD01',event,'../ajaxhippasegmenttooltip.ajax');">
	                    what is this?</a>&#160;
	                    <a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD01" onclick="popupDiv('HSD01','../ajaxpopup.ajax')" /></a>
	                  	</td>
	                  	 <td width="20px">&#160;</td>
	                  	<td  colspan="2" class="headText">HSD02 
	                  	<a href="#" tabIndex="-1" onclick="displayInfo('HSD02',event,'../ajaxhippasegmenttooltip.ajax');">
	                  	what is this?</a>
	                  	</td>
	                    <td width="20px">&#160;</td>
	                    <td  colspan="2" class="headText">HSD03 
	                    <a href="#" tabIndex="-1" onclick="displayInfo('HSD03',event,'../ajaxhippasegmenttooltip.ajax');">
	                    what is this?</a>&#160;
	                    <a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD03" onclick="popupDiv('HSD03','../ajaxpopup.ajax')"/></a>
	                    </td>
	                     <td width="20px">&#160;</td>
	                    <td  colspan="2" class="headText">HSD04 
	                    <a href="#" tabIndex="-1" onclick="displayInfo('HSD04',event,'../ajaxhippasegmenttooltip.ajax');">
	                    what is this?</a>
	                    </td>
	                    
	                  </tr>	
	                  <tr class="createEditTable1-Listdata alternate">
	                    <td width="70px" >							
								<input type="text" name="hsd01" id="HSD01Id" class="inputbox60" value="${mapping.hsd01.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd01SysId" id="HSD01SysId" value="${mapping.hsd01.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="100px">
							<label id="HSD01IdLabel" for="HSD01Id" style="font-size:11px">
									<c:out value="${mapping.hsd01.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						 <td width="20px">&#160;</td>
	               		<td width="70px" >						
								<input type="text" maxlength="5" name="hsd02" id="HSD02Id" class="inputbox60" value="${mapping.hsd02.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd02SysId" id="HSD02SysId" value="${mapping.hsd02.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
						
	                    <td width="100px">
							<label id="HSD02IdLabel" for="HSD02Id" style="font-size:11px">
									<c:out value="${mapping.hsd02.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						<td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" name="hsd03" id="HSD03Id"  class="inputbox60" value="${mapping.hsd03.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd03SysId" id="HSD03SysId" value="${mapping.hsd03.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
						
	                    <td width="100px">
							<label id="HSD03IdLabel" for="HSD03Id" style="font-size:11px">
									<c:out value="${mapping.hsd03.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						 <td width="20px">&#160;</td>
						 <td width="70" >						
								<input type="text" maxlength="5" name="hsd04" id="HSD04Id" class="inputbox60" value="${mapping.hsd04.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd04SysId" id="HSD04SysId" value="${mapping.hsd04.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="100px">
							<label id="HSD04IdLabel" for="HSD04Id" style="font-size:11px">
									<c:out value="${mapping.hsd04.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						
	                  </tr>
					<tr class="createEditTable1-Listdata">
	                    <td class="headText"  colspan="2">HSD05 <a href="#" tabIndex="-1"  onclick="displayInfo('HSD05',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD05" onclick="popupDiv('HSD05','../ajaxpopup.ajax')"/></a></td>
	                     <td width="20px">&#160;</td>
						 
						 <td class="headText" id="HSD02-01Id-td">
							<input type="text" maxlength="12" name="hsd02" id="HSD02-01Id" class="inputbox60 ui-autocomplete-input" value="${mapping.hsd02.hippaCodeSelectedValues[1].value}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
							<input type="hidden" name="HSD02Desc" id="HSD02-01SysId" value="${mapping.hsd02.hippaCodeSelectedValues[1].hippaCodeValueSysId}">
							
						</td>
	                     
	                     <td>
							<label id="HSD02-01IdLabel" for="HSD02-01Id" style="font-size:11px">
									
							</label>
						 </td>
						 <td></td>
	                    <td class="headText"  colspan="2">HSD06 <a href="#" tabIndex="-1" onclick="displayInfo('HSD06',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a></td>
	                    <td width="20px">&#160;</td>
	                    <td class="headText"  colspan="2">HSD07 <a href="#" tabIndex="-1" onclick="displayInfo('HSD07',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD07" onclick="popupDiv('HSD07','../ajaxpopup.ajax')"/></a></td>
						 
	                  	
	                  </tr>	
					<tr class="createEditTable1-Listdata alternate">
					     <td width="70px" >						
								<input type="text" name="hsd05" id="HSD05Id" class="inputbox60" value="${mapping.hsd05.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd05SysId" id="HSD05SysId" value="${mapping.hsd05.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="100px">
							<label id="HSD05IdLabel" for="HSD05Id" style="font-size:11px">
									<c:out value="${mapping.hsd05.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						 <td width="20px">&#160;</td>
						 <td width="70px" align="right" id="HSD02-02Id-td">
								<a href="#" ><img alt="Add Row for EB03" title="Add Row for HSD02" id="hsd02ddButtonforVariable-02"  src="/wsbebx/images/add.gif" width="19" height="19" onclick="addRowForVariableHSD02('hsdTable','HSD02Id','hsd04','',true,'','hsd02ddButtonforVariable-02');" ></a>
								<input type="hidden" maxlength="12" name="hsd02" id="HSD02-02Id" class="inputbox60 ui-autocomplete-input" value="${mapping.hsd02.hippaCodeSelectedValues[2].value}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
								<input type="hidden" name="HSD02Desc" id="HSD02-02SysId" value="${mapping.hsd02.hippaCodeSelectedValues[2].hippaCodeValueSysId}">
						 </td>
	                    <td width="100px">
							<label id="HSD02-02IdLabel" for="HSD02-02Id" style="font-size:11px">
									
							</label>
						</td>
						<td width="20px">&#160;</td>
	                    <td width="70px" >						
								<input type="text" maxlength="5" name="hsd06" id="HSD06Id" class="inputbox60" value="${mapping.hsd06.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd06SysId" id="HSD06SysId" value="${mapping.hsd06.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
	                   </td>
	                    <td width="100px">
							<label id="HSD06IdLabel" for="HSD06Id" style="font-size:11px">
									<c:out value="${mapping.hsd06.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						<td width="20px">&#160;</td>
	                    <td width="70px" >					
								<input type="text" name="hsd07" id="HSD07Id" class="inputbox60" value="${mapping.hsd07.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd07SysId" id="HSD07SysId" value="${mapping.hsd07.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td>
	                    <td width="100px">
							<label id="HSD07IdLabel" for="HSD07Id" style="font-size:11px">
									<c:out value="${mapping.hsd07.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						
	                  </tr>	
					  <tr class="createEditTable1-Listdata">
							<td class="headText"  colspan="2">HSD08 <a href="#" tabIndex="-1" onclick="displayInfo('HSD08',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="HSD08" onclick="popupDiv('HSD08','../ajaxpopup.ajax')"/></a></td>
							<td></td>
							<td width="70px" id="HSD02-03Id-td">
								<a href="#" ><img alt="Add Row for EB03" title="Add Row for HSD02" id="hsd02ddButtonforVariable-03" style="display:none;" src="/wsbebx/images/add.gif" width="19" height="19" onclick="addRowForVariableHSD02('hsdTable','HSD02Id','hsd04','',true,'','hsd02ddButtonforVariable-03');" ></a>
								<input type="hidden" maxlength="12" name="hsd02" id="HSD02-03Id" class="inputbox60 ui-autocomplete-input" value="${mapping.hsd02.hippaCodeSelectedValues[3].value}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
								<input type="hidden" name="HSD02Desc" id="HSD02-03SysId" value="${mapping.hsd02.hippaCodeSelectedValues[3].hippaCodeValueSysId}">
							</td>
							<td width="100px">
								<label id="HSD02-03IdLabel" for="HSD02-03Id" style="font-size:11px">
									
								</label>
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
					  </tr>
					  <tr class="createEditTable1-Listdata alternate">
	                    <td width="70px" >							
								<input type="text" name="hsd08" id="HSD08Id" class="inputbox60" value="${mapping.hsd08.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="hsd08SysId" id="HSD08SysId" value="${mapping.hsd08.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td width="100px">
							<label id="HSD08IdLabel" for="HSD08Id" style="font-size:11px">
									<c:out value="${mapping.hsd08.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						<td></td>
						<td width="70px" id="HSD02-04Id-td">
							<a href="#" ><img alt="Add Row for EB03" title="Add Row for HSD02" id="hsd02ddButtonforVariable-04" style="display:none;" src="/wsbebx/images/add.gif" width="19" height="19" onclick="addRowForVariableHSD02('hsdTable','HSD02Id','hsd04','',true,'','hsd02ddButtonforVariable-04');" ></a>
							<input type="hidden" maxlength="12" name="hsd02" id="HSD02-04Id" class="inputbox60 ui-autocomplete-input" value="${mapping.hsd02.hippaCodeSelectedValues[4].value}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
							<input type="hidden" name="HSD02Desc" id="HSD02-04SysId" value="${mapping.hsd02.hippaCodeSelectedValues[4].hippaCodeValueSysId}">
						</td>
						<td width="100px">
							<label id="HSD02-04IdLabel" for="HSD02-04Id" style="font-size:11px">
									
								</label>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					  </tr>
					  <tr class="createEditTable1-Listdata " >
					  <td></td>
						<td></td>
						<td></td>
						
							<td width="70px" id="HSD02-05Id-td">
								<a href="#" ><img alt="Add Row for EB03" title="Add Row for HSD02" id="hsd02ddButtonforVariable-05" style="display:none;" src="/wsbebx/images/add.gif" width="19" height="19" onclick="addRowForVariableHSD02('hsdTable','HSD02Id','hsd04','',true,'','hsd02ddButtonforVariable-05');" ></a>
								<input type="hidden" maxlength="12" name="hsd02" id="HSD02-05Id" class="inputbox60 ui-autocomplete-input" value="${mapping.hsd02.hippaCodeSelectedValues[5].value}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
								<input type="hidden" name="HSD02Desc" id="HSD02-05SysId" value="${mapping.hsd02.hippaCodeSelectedValues[5].hippaCodeValueSysId}">
						</td>
	                    <td width="100px">
							<label id="HSD02-05IdLabel" for="HSD02-05Id" style="font-size:11px">
									
							</label>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					  </tr>
					  <tr class="createEditTable1-Listdata alternate" >
					  <td></td>
						<td></td>
						<td></td>
						
							<td width="70px" id="HSD02-06Id-td">
								<a href="#" ><img alt="Add Row for EB03" title="Add Row for HSD02" id="hsd02ddButtonforVariable-06" style="display:none;" src="/wsbebx/images/add.gif" width="19" height="19" onclick="addRowForVariableHSD02('hsdTable','HSD02Id','hsd04','',true,'','hsd02ddButtonforVariable-06');" ></a>
								<input type="hidden" maxlength="12" name="hsd02" id="HSD02-06Id" class="inputbox60 ui-autocomplete-input" value="${mapping.hsd02.hippaCodeSelectedValues[6].value}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
								<input type="hidden" name="HSD02Desc" id="HSD02-06SysId" value="${mapping.hsd02.hippaCodeSelectedValues[6].hippaCodeValueSysId}">
						</td>
	                    <td width="100px">
							<label id="HSD02-06IdLabel" for="HSD02-06Id" style="font-size:11px">
									
							</label>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					  </tr>
					</table>	
				</div><!--HSD  Table End -->	
				
			<!--III02 Table  -->	
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 bT mT5" id="iii02Table">
	                  <tr class="createEditTable1-Listdata">
	                    <td nowrap colspan="2" class="headText">III02 <a href="#"  onclick="displayInfo('III02',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"  id="III02" onclick="popupDiv('III02','../ajaxpopup.ajax')"/></a></td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
						<td width="100px">&#160;</td>
	                  </tr>
					  <tr class="createEditTable1-Listdata alternate">
	                    <td width="3%" >						
								<input type="text" name="ii02Val" class="inputbox60" id="III02Id" value="${mapping.ii02.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="ii02SysId" id="II02SysId" value="${mapping.ii02.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
	                    <td >
							<label id="III02IdLabel" for="III02Id" style="font-size:11px">
									<c:out value="${mapping.ii02.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
						<td width="100px">&#160;</td>
	                  </tr>
					</table><!--III02 Table End -->
				
					<!--Message and NoteType Table  -->
		    		<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="messageTable">
		    			<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
									textComponentName="messageValueId"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFinMappingAction"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
						<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher2"
									textComponentName="extndMessage1"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFinMappingAction"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
						<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher3"
									textComponentName="extndMessage2"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFinMappingAction"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
						<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher4"
									textComponentName="extndMessage3"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" finishedListener="spellFinMappingAction"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
									
				<!-- Adding Spell Check Launchers for all Message TextAreas -->
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_0"
									textComponentName="MESSAGEVALUEId0"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>	
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_1"
									textComponentName="MESSAGEVALUEId1"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_2"
									textComponentName="MESSAGEVALUEId2"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_3"
									textComponentName="MESSAGEVALUEId3"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_4"
									textComponentName="MESSAGEVALUEId4"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_5"
									textComponentName="MESSAGEVALUEId5"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_6"
									textComponentName="MESSAGEVALUEId6"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_7"
									textComponentName="MESSAGEVALUEId7"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_8"
									textComponentName="MESSAGEVALUEId8"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_9"
									textComponentName="MESSAGEVALUEId9"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_10"
									textComponentName="MESSAGEVALUEId10"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_11"
									textComponentName="MESSAGEVALUEId11"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_12"
									textComponentName="MESSAGEVALUEId12"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_13"
									textComponentName="MESSAGEVALUEId13"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True"
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_14"
									textComponentName="MESSAGEVALUEId14"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher_15"
									textComponentName="MESSAGEVALUEId15"
									rapidSpellWebPage="${context}/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Message Text"
									modalHelperPage="${context}/RapidSpellModalHelper.jsp"
									showNoErrorsMessage="False" showFinishedMessage="False"
									includeUserDictionaryInSuggestions="True" allowAnyCase="True"
									allowMixedCase="True" 
									windowWidth="570" windowHeight="300" modal="False"
									showButton="False" windowX="-1" warnDuplicates="False"
									textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
				
				<!-- Ends Here -->
	                  	<tr class="createEditTable1-Listdata">
		                    <td width="500" class="headText">Message &#160;
	                        <input type="checkbox" <c:if test="${mapping.msg_type_required == 'true'}">checked</c:if> name="msgRqdChkBox" id="msgRqdChkBox"/>
							&#160;Required <a href="#"  onclick="displayInfo('MSG',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>
                        	<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" onclick="openMessageTextDialog('messageValueId')" id="MESSAGE_TEXT" /></a>
                        	</td>
		                    <td width="92">&#160;</td>
							<td width="69">&#160;</td>
							<td width="49">&#160;</td>
		                    <td colspan="2" nowrap class="headText">Note Type <a href="#"  onclick="displayInfo('NOTE_TYPE_CODE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="NOTE_TYPE_CODE" onclick="popupDiv('NOTE_TYPE_CODE','../ajaxpopup.ajax')"/></a></td>
              			</tr>
					 	<tr class="alternate">
		                    <td width="500">						
									<input type="text" id="messageValueId" name="messageValue" class="inputbox470" value="${mapping.message}" />
							</td>
		                    <td width="92">&#160;</td>
							<td width="69">&#160;</td>
		                    <td width="49">&#160;</td>
		                    <td width="70px" >						
									<input type="text" name="noteTypeCodeVal" id="NOTETYPEID" class="inputbox60" value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].value}" />
									<input type="hidden"  name="noteTypeCodeSysId" id="NOTETYPESysId" value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td>
							<td width="110px" >
								<label id="NOTETYPEIDLabel" for="NOTETYPEID" style="font-size:11px">
										<c:out value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].description}" />								
								</label>
							</td>
              			</tr>
				  </table><!--Message and NoteType Table End -->
				
				<!--Accum Tables  -->	
				<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT10"  id="accumTable1">
					<tbody id="accumTbody1">     
	                  <tr class="createEditTable1-Listdata">
	                    <td colspan="2" nowrap class="headText">Accumulators &#160;
						
							<input type="checkbox" <c:if test="${mapping.sensitiveBenefitIndicator== 'true'}">checked</c:if> 
							<c:if test="${userUIPermissions.authorizedToEditAccumNotRqrdIndctr !='true'}"> disabled </c:if>
							name="accumNtReqdChkBox" id="accumNtReqdChkBox" />
							&#160;Not Required&#160;&#160;&#160;<a href="#"   onclick="displayInfo('ACCUM',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160;
							<a href="#">
								<img src="${imageDir}/icon-popup.gif" width="15" height="14" onclick="popupAccumDiv('ACCUM','../ajaxaccumpopup.ajax')"/>
							</a>
						</td>
						<td width="140px">&#160;</td>
	                    <td width="105px">&#160;</td>
	                    <td width="90px">&#160;</td>
	                    <td width="140px">&#160;</td>
	                    <td width="105px">&#160;</td>
	                    <td width="4%">&#160;</td>
	                    <td width="134px">&#160;</td>
	                  </tr></tbody></table>
	                  
					 <c:set var="accumLength" value="${fn:length(accumValues)}"/>
					 <table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" id="accumTable">
	                  <tbody id="accumTbody">
						<tr class="alternate" >
							<td style= "width: 95px">
								<input type="text" name="accumulator" id="accumId0" class="inputbox60" value="${mapping.accum.hippaCodeSelectedValues[0].value}" />
								<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId0" value="${mapping.accum.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td> 
							<td style= "width: 140px">
								<label id="accumIdLabel0" for="accumId0" style="font-size:11px">
									<c:out value="${mapping.accum.hippaCodeSelectedValues[0].description}" />
								</label>
								<input type="hidden" id="AccumDesc0" name="AccumDesc" value="${mapping.accum.hippaCodeSelectedValues[0].description}"/>
							</td>
							<td></td>
							<td style= "width: 95px"><input type="text" name="accumulator" id="accumId1" class="inputbox60" value="${mapping.accum.hippaCodeSelectedValues[1].value}" />
								<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId1" value="${mapping.accum.hippaCodeSelectedValues[1].hippaCodeValueSysId}" />
							</td> 
							<td style= "width: 140px">
								<label id="accumIdLabel1" for="accumId1" style="font-size:11px">
									<c:out value="${mapping.accum.hippaCodeSelectedValues[1].description}" />
								</label>
								<input type="hidden" id="AccumDesc1" name="AccumDesc" value="${mapping.accum.hippaCodeSelectedValues[1].description}"/>
							</td>
							<td></td>
							<td style= "width: 95px"><input type="textbox" name="accumulator" id="accumId2" class="inputbox60" value="${mapping.accum.hippaCodeSelectedValues[2].value}" />
									<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId2" value="${mapping.accum.hippaCodeSelectedValues[2].hippaCodeValueSysId}" />
							 </td> 
							 
							<td style= "width: 140px">
							<label id="accumIdLabel2" for="accumId2" style="font-size:11px">
								<c:out value="${mapping.accum.hippaCodeSelectedValues[2].description}" />
							</label>
								<input type="hidden" id="AccumDesc2" name="AccumDesc" value="${mapping.accum.hippaCodeSelectedValues[2].description}"/>
							</td>
							<td></td>
						</tr>

					<c:if test="${fn:length(mapping.accum.hippaCodeSelectedValues) > 3}">
							<c:set var="count" value="3"/>
						<c:forEach items="${mapping.accum.hippaCodeSelectedValues}"	var="accumValues" begin="3" >
							<input type="hidden"  name="accumulatorSysId" id="AccumulatorSysId${accumCount}" value="${accumValues.hippaCodeValueSysId}" />
							<script type="text/javascript">
								insertAccum('accumTable','accumId${count}','accumulator','${accumValues.value}',true,'accumIdLabel${count}',"${accumValues.description}",'AccumDesc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</c:if>
				</tbody>					
				</table><!--Accum Tables End -->	
				
				<div class="fL mR10 pT5"><a href="#"><img id="accumAddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForAccummulators('accumTable','accumId','accumulator','',true,'');"/></a></div>
					
			<!--  UM Rule Tables   -->
				<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
					<tr class="createEditTable1-Listdata">
						<td class="headText">UM Rule <a href="#" onclick="displayInfo('UMRULE',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>&#160; <img
							src="${imageDir}/icon-popup.gif" width="15" height="14" id="UMRULE" onclick="popupDiv('UMRULE', '../ajaxpopup.ajax')" /></td>
					</tr>
				</table>
				<table width="950" border="0" cellpadding="0" cellspacing="0"	class="Pd3" id="umRuleTable">
					<tbody id="umRuleTbody">
						<tr class="createEditTable1-Listdata alternate">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId0" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId0" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel0" for="UMRuleId0" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].description}
								</label>
								<input type="hidden" id="UMRuleDesc0" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[0].description}" />
							</td>
							<td width="20px">
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId0" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId1" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId1" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].hippaCodeValueSysId}" />
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel1" for="UMRuleId1" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].description}
								</label>
								<input type="hidden" id="UMRuleDesc1" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[1].description}" />
							</td>
							<td width="20px">
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId1" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId2" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId2" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].hippaCodeValueSysId}" />
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel2" for="UMRuleId2" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].description}
								</label>
								<input type="hidden" id="UMRuleDesc2" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[2].description}" />
							</td>
							<td width="20px">
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId2" title="View Rule" />
							</td>
						</tr>
						<tr class="createEditTable1-Listdata white-bg">
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId3" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId3" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].hippaCodeValueSysId}" />
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel3" for="UMRuleId3" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].description}
								</label>
								<input type="hidden" id="UMRuleDesc3" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[3].description}" />
							</td>
							<td width="20px">
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId3" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId4" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId4" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].hippaCodeValueSysId}" />
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel4" for="UMRuleId4" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].description}
								</label>
								<input type="hidden" id="UMRuleDesc4" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[4].description}" />
							</td>
							<td width="20px">
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId4" title="View Rule" />
							</td>
							<td style="width: 95px">
								<input type="text" name="umRuleVal" class="inputbox60" id="UMRuleId5" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[5].value}"/>
								<input type="hidden"  name="umRuleSysId" id="umRuleSysId5" value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[5].hippaCodeValueSysId}" />
							</td>
							<td style="width: 140px">
								<label id="UMRuleIdLabel5" for="UMRuleId5" style="font-size: 11px">
									${mapping.utilizationMgmntRule.hippaCodeSelectedValues[5].description}
								</label>
								<input type="hidden" id="UMRuleDesc5" name="UMRuleDesc"	value="${mapping.utilizationMgmntRule.hippaCodeSelectedValues[5].description}" />
							</td>
							<td width="20px">
								<img src="${imageDir}/search_icon.gif" alt="View Rule" id="viewRuleId5" title="View Rule" />
							</td>
						</tr>
	<c:if test="${fn:length(mapping.utilizationMgmntRule.hippaCodeSelectedValues) > 6}">
							<c:set var="count" value="6"/>
						<c:forEach items="${mapping.utilizationMgmntRule.hippaCodeSelectedValues}"	var="umRuleValues" begin="6">
							<input type="hidden"  name="umRuleSysId" id="umRuleSysId${count}" value="${umRuleValues.hippaCodeValueSysId}" />
							<script type="text/javascript">
								insertUmRule('umRuleTable','UMRuleId${count}','umRuleVal','${umRuleValues.value}',true,'UMRuleIdLabel${count}',"${umRuleValues.description}",'UMRuleDesc${count}');
							</script>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
						</c:if>
						</tbody>
					</table>
	
					<div class="fL pT5">
						<img id="umRuleAddButton" src="${imageDir}/add.gif" width="19" height="19" onclick="addRowForUmRule('umRuleTable','UMRuleId','umRuleVal','',true,'')" />
					</div>					
					<!--  UM Rule Table ends here -->
					
			  <!-- NM1 Message Segment  Section starts here  -->
		<div>
			<table width="950px" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT" id="nm1MessageSegmentTable">
				<tbody id = "nm1MessageSegmentSection">
						<tr class="createEditTable1-Listdata">
							
							<td class="headText"  colspan="5" align="left"> 2120 Loop NM1 Message Segment
								<a href="#"  onclick="displayInfo('2120_LOOP_NM1_MESSAGE_SEGMENT',event,'../ajaxhippasegmenttooltip.ajax');">what is this?</a>
								  <a href="#" tabIndex="-1"><img src="${imageDir}/icon-popup.gif" width="15" height="14" id="nm1MessageSegmentPopup" onclick="popupDiv('2120_LOOP_NM1_MESSAGE_SEGMENT','../ajaxpopup.ajax')" /></a>
							</td>
							<td class="headText"  colspan="2" align="left"/>

							<td class="headText"  colspan="2" align="left"/> 	
							
							<td class="headText"  colspan="3" align="center"/> 
						
							<td class="headText"  colspan="3" align="left"/> 	
						</tr>
					
					<tr class="alternate">
					
						<td width="30px;" align="center">
							<input type="text" name="nm1MessageSegmentId" id="nm1MessageSegmentId" class="inputbox60" value="${mapping.nm1MessageSegment.hippaCodeSelectedValues[0].value}" />
							<input type="hidden"  name="nm1MessageSegmentSysId" id="nm1MessageSegmentSysId" value="${mapping.nm1MessageSegment.hippaCodeSelectedValues[0].hippaCodeValueSysId}" />
						</td>
						
						<td width="150px;">
						<label id="nm1MessageSegmentIdLabel" for="nm1MessageSegmentId" style="font-size: 11px" >
								<c:out value="${mapping.nm1MessageSegment.hippaCodeSelectedValues[0].description}" />
							</label>
						</td>
						
						<td width="20px;" />
						
						<td width="25px;" style="padding-top: 8px;"/>
						
						<td width="240px;"/>
						
						<td width="10px;"/>
						<td/>
						
						<td width="25px;" style="padding-top: 8px;" />
						
						<td width="10px;"/>
						
						<td width="100px" align="center"/>
						
						<td width="20px;"/>
						
						<td width="90px;" />
						
						<td width="25px;" style="padding-top: 8px;"/>
						
						<td width="250px;"/>
					</tr>
					
				</tbody>		
			</table>
		</div>
		<!-- NM1 Message Segment Section Ends -->
					
				</div><!--createEditContainer Ends-->
           </div>
		  <!-- innerContainer Ends-->
        </div>
		<!-- container Ends-->
		<div class="footer">
			<div class="ajaxIdle" id="ajaxIdleIcon">
				<div class="clear"></div>
			</div>
			<div class="ajaxActive" id="ajaxActiveIcon">
				<div class="clear"></div>
			</div>
				<input type="hidden" id="selectedvariableIdCopyTo" value="" name="selectedvariableIdCopyTo"/>
 				<input type="hidden" id="selectedvariableId" value="" name="selectedvariableId"/>
				<input type="hidden" id="selectedvariableDesc" value="" name="selectedvariableDesc"/>
				<input type="hidden" id="selectedvariableIdForDone" value="" name="selectedvariableIdForDone"/>
				<input type="hidden" id="selectedvariableDescForDone" value="" name="selectedvariableDescForDone"/>
				<input type="hidden" id="systemName" value="" name="systemName"/>
				<input type="hidden" id="lastUpdatedUser" value="" name="lastUpdatedUser"/>
				<input type="hidden" id="mappingSysId" value="${mapping.variableSystemId}" name="mappingSysId" />
				<input type="hidden" id="token" value="${TRANSACTION_TOKEN_KEY}" name="tokenKey" />
				<c:set var="discardChange" value="0"/>				
				<c:if test="${mapping.inTempTable == 'N'}">
					<c:set var="discardChange" value="1"/>
				</c:if>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
				<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
				<td width="0" height="20"><a href="#" onclick ='openAlertDialog();'>Copy To</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>		       
		        <td width="0" height="20"><a href="#" onclick ='openViewHistoryDialog("${mapping.variable.variableId}");'>View history</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='loadChangeCommentsDialog("save")'>Save</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick='loadChangeCommentsDialog("done")'>Done</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" onclick ='cancel("${mapping.variable.variableId}");'>Cancel</a></td>
		       

				<c:if test="${mapping.variableMappingStatus !='NOT_APPLICABLE'}" > 
					<c:if test="${userUIPermissions.authorizedToMarkAsNotApplicable}">
						<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='loadChangeCommentsDialog("notApplicable");'>Not Applicable</a></td>
					</c:if>
				</c:if>
				
				<c:if test="${mapping.variableMappingStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mapping.variableMappingStatus != 'SCHEDULED_TO_TEST' &&
							mapping.variableMappingStatus != 'NOT_APPLICABLE' &&
							mapping.variableMappingStatus != 'PUBLISHED'}">		
						 <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='loadChangeCommentsDialog("sendToTest");'>Send to Test</a></td>
				</c:if>

				<c:if test="${mapping.variableMappingStatus != 'SCHEDULED_TO_PRODUCTION' &&
							mapping.variableMappingStatus != 'SCHEDULED_TO_TEST' &&
							mapping.variableMappingStatus != 'NOT_APPLICABLE' &&
							mapping.variableMappingStatus != 'PUBLISHED'}">						
					<c:if test="${userUIPermissions.authorizedToapprove}">
						<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
						<td width="0" height="20"><a href="#" onclick='loadChangeCommentsDialog("approve");'>Approve</a></td>				
					</c:if>
				</c:if>

				<c:if test="${discardChange != 1}">
					<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
			        <td width="0" height="20"><a href="#" onclick='discardChanges("${mapping.variable.variableId}","${mapping.variableSystemId}");' class="dicard">Discard Changes</a></td>				
					<!--<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				--></c:if>
				<c:if test="${userUIPermissions.authorizedToAuditLock && mapping.auditLock == 'Y'}"> 
				<c:set var="auditLock" value="AuditLock_${mapping.variable.variableId}" />   
				<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" id="${auditLock}" onclick ='openUserCommentsLockDialog("${mapping.variable.variableId}","${variableList[0].variableSystem}","${mapping.user.lastUpdatedUserName}","${variableList[0].description}","Unlock");'>Unlock</a></td>
		         </c:if>
		        <c:if test="${userUIPermissions.authorizedToAuditUnlock && mapping.auditLock == 'N'}">
		        <c:set var="auditUnLock" value="AuditUnLock_${mapping.variable.variableId}" />
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a id="${auditUnLock}" href="#" onclick ='openUserCommentsLockDialog("${mapping.variable.variableId}","${variableList[0].variableSystem}","${mapping.user.lastUpdatedUserName}","${variableList[0].description}","Lock");'>Lock</a></td>
		        </c:if>	
		        <td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
				</tr>
		    </table>					
		</div>	
	</div>
	
	<!-- wrapper Ends-->
<div id="copyToDialog" >
	<%@include file="/WEB-INF/jsp/copyToPage.jsp"%>
</div>
</form>
<div id="viewHistoryDialog" title="View History">
</div>
<div id="hippaCodePopUpDiv"></div>
<div id="accumPopUpDiv"></div>
<div id="ruleViewPopUpDiv"></div>
<div id="groupPagePopUpDiv"></div>
<div id="popupAgeTierVarDiv"></div>
</div>
<script type="text/javascript">
	sniffer();
</script>

<%@include file="/WEB-INF/jspf/whatIsThisToolTip.jspf"%>
<div id="confirmationDiv"></div>
<div id="messageTextDialog">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="Pd3">
        <tr class="createEditTable1-Listdata">
          <td colspan="6" nowrap class="headText">EBO3 &#160;</td>
          <td><input type="text" name="msgTxtEb03" class="inputbox60" id="msgTxtEb03Id" value=""/></td>
          <td colspan="6" nowrap class="headText">&#160;Variable ID &#160;</td>
          <td><input type="text" name="msgTxtVar" class="inputbox60" id="msgTxtVarId" value=""/></td>
          <td colspan="6" nowrap class="headText">&#160;Message Text &#160;</td>
          <td><input type="text" name="msgTxt" class="inputbox60" id="msgTxtId" value=""/></td>
        </tr>
        <tr><td colspan="6"></td><td></td>
        <td colspan="6"></td><td></td>
        <td colspan ="8"nowrap class="headText"><input type="checkbox" id="showOnlyStandardMessages" name ="showOnlyStandardMessages" VALUE = "FALSE">&#160; Show Only Standard Messages&#160;</td>
        </tr>
    </table>     
    <table width="100%" height="20" ><tr><td></td></tr></table>
	<table  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>  
		<td>
			<a href="#" onclick="searchMessageText('1','Init')"><img src="${imageDir}/search_but.png" 
			alt="Search" title="Search"/></a>
		</td>	
      </tr>
	</table>
	<table width="100%" height="20" ><tr><td>
		<input type="hidden" id="hdMsgTxtId" name="hdMsgTxt"/>
		<input type="hidden" id="hdMsgTxtEB03Id" name="hdMsgEB03Txt"/>
		<input type="hidden" id="hdMsgTxtHdrId" name="hdMsgHdrTxt"/>
		<input type="hidden" id="hdMessageId" name="hdMessageName"/>
			</td></tr></table>
	<div id="messageTextResult"></div>
</div>
<div id="alertDiv"></div>
<div id="confirmationDivSensitiveBnft"></div>
<div id="confirmationDivDiscardChanges"></div>
<div id="lockusercomment" style="display:none;" >
	<%@include file = "/WEB-INF/jsp/lockUserCommentPopup.jsp" %>
</div>
<div id="changeCommentsDialog" style="display:none;">
	<table id="changeCommentsDialogTable" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5">          
	  <tr class="">
        <td > <textarea name="changeComments" id="changeCommentsId"
		rows="5" cols="77"></textarea></td>
		<input type="hidden" id="hiddenChangeCommentsId" name="hiddenChangeCommentsId" value="">
	  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
      <tr>       
        <td>
			<a href="#" onclick='changeCommentsConfirm("${mapping.variable.variableId}","${variableList[0].variableFormat}","${mapping.auditLock}");'>
			<img id="confirmAction" src="${imageDir}/confirm_but.png" alt="Confirm" title="Confirm" /></a>
		</td>
      </tr>
	</table>	
</div>
<div id="extendedMsgScreen" style="display:none;">
	<table border="0" cellspacing="0" width="100%">	
		<tr height="30px">
			<td colspan="3"></td>
		</tr>
		<tr height="40px">
			<td width ="200px" align="right" > Variable ID</td>
			<td width="20px"></td>
			<td id="variableId">${mapping.variable.variableId}</td>
		</tr>				
		<tr height="80px">
			<td align="right" style="vertical-align: top;" >Extend Message 1-A</td>
			<td width="20px"></td>
			<td align="left"><textarea name="extndMessage1"
					id="extndMessage1" onblur="changeTextToUpperCase(this.id)" rows="6" cols="50" maxlength="260"  onkeyup="countChars(this.id)" onkeydown="countChars(this.id)" style="overflow:hidden"></textarea></td>
		</tr>
		<tr height="20px">
			<td></td>
			<td width="20px"></td>
			<td><span id="extndMsgForCount1">260</span> characters remaining.</br></td>			
		</tr>
		<tr height="80px">
			<td align="right" style="vertical-align: top;" >Extend Message 1-B</td>
			<td width="20px"></td>
			<td align="left"><textarea name="extndMessage2"
					id="extndMessage2" onblur="changeTextToUpperCase(this.id)" rows="6" cols="50" maxlength="260" onkeyup="countChars(this.id)" onkeydown="countChars(this.id)" style="overflow:hidden"></textarea></td>
		</tr>
		<tr height="20px">
			<td></td>
			<td width="20px"></td>
			<td><span id="extndMsgForCount2">260</span> characters remaining.</br></td>
		</tr>
		<tr height="80px">
			<td align="right" style="vertical-align: top;" >Extend Message 1-C</td>
			<td width="20px"></td>
			<td align="left"><textarea name="extndMessage3"
					id="extndMessage3" onblur="changeTextToUpperCase(this.id)" rows="6" cols="50" maxlength="260" onkeyup="countChars(this.id)" onkeydown="countChars(this.id)" style="overflow:hidden"></textarea></td>
		</tr>				
		<tr height="20px">
			<td></td>
			<td width="20px"></td>
			<td><span id="extndMsgForCount3">260</span> characters remaining.</br></td>
		</tr>		
		<tr height="40px">
			<td align="right" style="vertical-align: top;" >Network Required?</td>
			<td width="20px"></td>
			<td><select class="dropdown136" id="networkInd" name="networkInd">
					<option value = ""> Select</option>			
					<option value = "Y"> Yes</option>
					<option value = "N"> No</option>
				</select>
			</td>
		</tr>
		<tr height="40px">
			<td align="center"></td>
			<td align="center"><a href="#"  onclick="saveExtndMsgs();">
					<img src="${imageDir}/btn_Save.JPG" width="100" height="20" />
			</a></td>
			<td align="center" ><a  href="#"  onclick="cancelExtndMsgs();" >
					<img src="${imageDir}/btn_Cancel.JPG" 
					 width="100" height="20" />
			</a></td>
		</tr>
	</table>
</div>
<div id="hpnMapgMsgScreen" style="display:none;">
	<table border="0" cellspacing="0" width="100%">
		<tr>
		<th>Service TyCd</th>
		<th>HPN Mapping</th>
		</tr>
		<tr><td></br></td></tr>
		<c:forEach items="${hpnMapgList}" var="hpnvariable">
		<tr height="40px">
			<td style="text-align: center;"><span id="serviceTyCdVal">${hpnvariable.serviceTyCd}</span></td>
			<td style="text-align: center;"><span id="highPrfrmnNonTierdMsgTxtVal">${hpnvariable.highPrfrmnNonTierdMsgTxt}</span></br><span id="highPrfrmnTierdMsgTxtVal">${hpnvariable.highPrfrmnTierdMsgTxt}</span></td>
		</tr>
		</c:forEach>
		<tr height="40px">				
			<td align="center" ><a  href="#"  onclick="cancelhpnMapgMsgScreen();" >
					<img src="${imageDir}/btn_Cancel.JPG" 
					 width="100" height="20" />
			</a></td>
		</tr>
	</table>
</div>

<%@include file="/WEB-INF/jspf/messageTray.jspf"%>
</body>

	<script type="text/javascript">
		var umValue = $("#EB01Id").val();
		if(umValue != 'UM' && umValue != 'um' 
			&& umValue != 'Um' && umValue != 'uM' ){
			$("#excludeProceduresChkBox").hide();
			$("#lblProceduresExcluded").hide();
			$("#hrefProcedureExcluded").hide();		
		}	

	</script>

</html>
