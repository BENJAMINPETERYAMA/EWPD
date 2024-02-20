<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
</head>
<body>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/simulationInputPage.js"></script>
	<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<script type="text/javascript">
	
	function ooaMessExportScreen(){
        document.forms['ooaMessLandingPage'].action="${context}/ooamessage/exportOOAMessageExcelFile.html";
               document.forms['ooaMessLandingPage'].submit();
               }

		function searchOOAMessage() {
			var searchCriteria = $("#myText").val();
			var search;
			var exchangeInd;
			var viewOrSearchFunction = "searchFunction";

			if (document.getElementById('Network').checked) {

				search = document.getElementById('Network').value;
				
				console.log('inside network flow');
				
				exchangeInd = $("input:radio[name='Exchange']:checked").val();

if(exchangeInd===undefined){
console.log('going inside the loop1');
exchangeInd = "";
}


console.log('exc value is :'+exchangeInd);
			} else if (document.getElementById('Contract').checked) {
			console.log('inside contract flow');
				search = document.getElementById('Contract').value;
			}

			if (searchCriteria == null || searchCriteria == "") {
				addErrorToNotificationTray('Please enter the Contractcode/networkId');
				openTrayNotification();
			} else {
				if(search == "Contract" && searchCriteria.length>4){
			  	addErrorToNotificationTray('Please enter the contract code Max of 4 character');
				openTrayNotification();
			  }else if(search == "Network" && searchCriteria.length>8){
			  addErrorToNotificationTray('Please enter the Network id Max of 8 character');
				openTrayNotification();
			  }else{
				  
				 
			  $
						.ajax({
							url : "${context}/lockedvariableauditreport/searchOOAMessage.ajax",
							dataType : "html",
							type : "POST",
							data : "searchCriteria=" + searchCriteria
									+ "&search=" + search
									+ "&viewOrSearchFunction="
									+ viewOrSearchFunction
									+ "&excInd="
									+ exchangeInd 
									+ "&isSearch=Yes",
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
			
			}
		}

		function changeLabel() {
			var radios = document.getElementsByName('Search');

			for (var i = 0, length = radios.length; i < length; i++) {
				if (radios[i].checked) {
					if (radios[i].value == "Contract") {
						document.getElementById('dynamicLabel').innerHTML = "Contract Code";
						document.getElementById("exchangeRadio").style.visibility = "hidden";
						document.getElementById("exchangeRadio1").style.visibility = "hidden";
						document.getElementById("exchangeRadio2").style.visibility = "hidden";
						 
						document.getElementById("myText").value = '';
					} else {
						document.getElementById('dynamicLabel').innerHTML = "Network Id";
						document.getElementById("exchangeRadio").style.visibility = "visible";
						document.getElementById("exchangeRadio1").style.visibility = "visible";
						document.getElementById("exchangeRadio2").style.visibility = "visible";
						 
						document.getElementById("myText").value = '';
					}
				}

			}

		}

		function ooaMessCreateScreen() {

			console.log($("input:radio[name='Exchange']:checked").val());

			if ($("input:radio[name='Exchange']:checked").val() == '') {
				alert('Please select any exchange indicator');
			} else {
				alert($("input:radio[name='Exchange']:checked").val());
			}

		}

		function ooaMessCreateScreen() {
			var textVal = document.getElementById("myText").value;
			var search;
			if (document.getElementById("Network").checked)
				search = "Network";
			else
				search = "Contract";
			var exchange;
			if (document.getElementById("off").checked)
				exchange = "Off";
			else if (document.getElementById("on").checked)
				exchange = "On";
			else
				exchange = "Both";
			if (textVal == '') {
				alert("Text Field is blank. Please enter some value");
				return false;
			} else {
				window.location = "create.html?Search=" + search
						+ "&searchCriteria=" + textVal + "&Exchange="
						+ exchange;
			}

		}
	</script>


	<form name="ooaMessLandingPage" method="POST">
		<div>
			<table border="0" cellspacing="0" width="100%">

				<tr height="40px">
					<td width="100px"><input type="radio" id="Network"
						name="Search" value="Network" onclick="changeLabel();" checked>
						Network Id<br></td>
					<td width="100px"><input type="radio" id="Contract"
						name="Search" value="Contract" onclick="changeLabel();">
						Contract Code<br></td>
					<td width="100px"></td>

				</tr>
				&nbsp;&nbsp;&nbsp;
				<tr height="40px">
					<td width="100px"><Label id="dynamicLabel"> Network Id
							<Label></td>
					<td width="100px"><input type="text" id="myText"
						name="searchCriteria" value="" ></td>
					<td width="100px"></td>
				</tr>
				&nbsp;&nbsp;&nbsp;
				<tr height="40px">
					<td width="100px" id="exchangeRadio"><input type="radio"
						id="off" name="Exchange" value="OF" title="Off Exchange"> Off
						Exchange<br></td>
					<td width="100px" id="exchangeRadio1"><input type="radio"
						id="on" name="Exchange" value="ON" title="On Exchange"> On
						Exchange<br></td>
					<td width="100px" id="exchangeRadio2"><input type="radio"
						id="Both" name="Exchange" value="BT" title="Both"> Both<br>
					</td>
				</tr>



				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<tr height="40px">
					<td align="center"><img src="${imageDir}/btn_Create.JPG"
						width="100" height="20" onClick="oOAMessageCreateScreen();" /></td>
					<td align="center"><img src="${imageDir}/btn_Search.JPG"
						width="100" height="20" onClick="searchOOAMessage();" /></td>
					<td align="center"><img src="${imageDir}/btn_Download.JPG"
						width="100" height="20" onClick="ooaMessExportScreen();" /></td>

				</tr>

			</table>
		</div>

	</form>
</body>
</html>


