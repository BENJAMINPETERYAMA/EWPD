<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../css/calendar.css" rel="stylesheet" type="text/css">
<LINK href="../../css/wpd.css" rel="stylesheet" type="text/css">
<TITLE>WellPoint Product Database System</TITLE>
</HEAD>

<f:view>
	<jsp:include page="../navigation/header_launch.jsp"></jsp:include>
	
	<body>
		<h:form id = "landingForm">
				<table width="100%" border="0" cellpadding="5" cellspacing="0" class ="breadcrumb" >
			      <tr>
			        <td>&nbsp;<h:outputText id="breadCrumb1" value=""> </h:outputText></td>
			      </tr>
			    </table>	
   
	<table height="80%">
		
		<tr>
			
			<td width="273" valign="top" class="leftPanel" colspan="2"><br />
				<div id='productHeaderDiv'><p><a href="../product/productSearch.jsp"><b>Product Development</b></a></p></div>
				<div id="contractHeaderDiv" ><p><a href="../contract/contractBasicSearch.jsp"><B>Contract Development</B></a></p></div>
				<div id="notesHeaderDiv" ><p><a href="../notes/notesSearch.jsp"><B>Notes Library</B></a></p></div>
				<div id="migrationHeaderDiv" ><p><a href="../migration/migrationWizardStep1.jsp"><B>Migration Wizard</B></a></p></div>
				<div id="searchHeaderDiv" ><p><a href="../search/basicSearch.jsp"><B>Search Engine</B></a></p></div>
			</td>
			<td colspan="2" valign="top" class="ContentArea">
				<p>&nbsp;</p>

 				<div id = "productDiv">
					<B>PRODUCT DEVELOPMENT</B>
					<p>This will take you to Product Locate.</p>
					<p class="enterN"><a href="../product/productSearch.jsp">Enter Now <img src="../../images/enterArrow.gif"
						alt="Enter" width="9" height="8" border="0"/></a></p>
					<hr noshade="noshade" />
				</div>

				<div id = "contractDiv">
					<B>CONTRACT DEVELOPMENT</B>
					<p>This will take you to Contract Locate.</p>
					<p class="enterN"><a href="../contract/contractBasicSearch.jsp">Enter Now <img src="../../images/enterArrow.gif"
						alt="Enter" width="9" height="8" border="0"/></a></p>
					<hr noshade="noshade" />
				</div>

				<div id = "notesDiv">
					<B>NOTES LIBRARY</B>
					<p>This will take you to Notes Locate.</p>
					<p class="enterN"><a href="../notes/notesSearch.jsp">Enter Now <img src="../../images/enterArrow.gif"
						alt="Enter" width="9" height="8" border="0" /></a></p>
					<hr noshade="noshade" />
				</div>

				<div id = "migrationDiv">
					<B>MIGRATION WIZARD</B>
					<p> This will take you to Migration Wizard Step 1.</p>
					<p class="enterN"><a href="../migration/migrationWizardStep1.jsp">Enter Now <img src="../../images/enterArrow.gif"
						alt="Enter" width="9" height="8" border="0" /></a></p>
					<hr noshade="noshade" />
				</div>

				<div id = "searchDiv">
					<B>SEARCH ENGINE</B>
					<p>This will take you to Search Engine - Basic Search.</p>
					<p class="enterN"><a href="../search/basicSearch.jsp">Enter Now <img src="../../images/enterArrow.gif"
						alt="Enter" width="9" height="8" border="0" /></a></p>
				</div>
			</td>
		</tr>
	</table>
<h:inputHidden id = "productHidden" value = "#{launchPageBackingBean.authorizedProductSearch}" />
<h:inputHidden id = "contractHidden" value = "#{launchPageBackingBean.authorizedContractSearch}" />	
<h:inputHidden id = "notesHidden" value = "#{launchPageBackingBean.authorizedNotesSearch}" />
<h:inputHidden id= "migrationHidden" value="#{launchPageBackingBean.authorizedMigrationWizard}" />
<h:inputHidden id = "searchHidden" value = "#{launchPageBackingBean.authorizedSearch}" />





<script>

function hideDivs(divIdToHide) {
   hideDivObj = document.getElementById(divIdToHide);
	hideDivObj.innerHTML = '';
    hideDivObj.style.height = '0px';
	hideDivObj.style.visibility= 'hidden';
	}

var productDiv = document.getElementById('productDiv');
var contractDiv = document.getElementById('contractDiv');
var migrationDiv = document.getElementById('migrationDiv');
var notesDiv = document.getElementById('notesDiv');
var searchDiv = document.getElementById('searchDiv');
var valueForProduct = document.getElementById('landingForm:productHidden').value;
var valueForContract = document.getElementById('landingForm:contractHidden').value;
var valueForMigrationWizard = document.getElementById('landingForm:migrationHidden').value;
var valueForNotes = document.getElementById('landingForm:notesHidden').value;
var valueForSearch = document.getElementById('landingForm:searchHidden').value;

// for product
if(valueForProduct=='true'){
	
	productDiv.style.visibility = 'visible';
	productHeaderDiv.style.visibility = 'visible';
}else if(valueForProduct=='false'){
	'productDiv'.style ='position: absolute';
	'productHeaderDiv'.style ='position: absolute';
	hideDivs('productDiv');
	hideDivs('productHeaderDiv');
}

// for contract
if(valueForContract=='true'){
	contractDiv.style.visibility = 'visible';
	contractHeaderDiv.style.visibility = 'visible';
}else if(valueForContract=='false'){
	'contractHeaderDiv'.style ='position: absolute';
	'contractDiv'.style= 'position: absolute';
	hideDivs('contractDiv');
	hideDivs('contractHeaderDiv');
}

// for Notes
if(valueForNotes=='true'){
	notesDiv.style.visibility = 'visible';
	notesHeaderDiv.style.visibility = 'visible';
}else if(valueForNotes=='false'){
	'notesDiv'.style ='position: absolute';
	'notesHeaderDiv'.style ='position: absolute';
	hideDivs('notesHeaderDiv');
	hideDivs('notesDiv');
	
}

// for migration Wizard
if(valueForMigrationWizard=='true'){
	migrationDiv.style.visibility = 'visible';
	migrationHeaderDiv.style.visibility = 'visible';
}else if(valueForMigrationWizard=='false'){
	'migrationDiv'.style='position: absolute';	
	'migrationHeaderDiv'.style ='position: absolute';	
	hideDivs('migrationDiv');
	hideDivs('migrationHeaderDiv');
}

// for advanced search 
if(valueForSearch=='true'){
	searchDiv.style.visibility = 'visible';
	searchHeaderDiv.style.visibility = 'visible';
}else if(valueForSearch=='false'){
	'searchDiv'.style='position: absolute';
	'searchHeaderDiv'.style ='position: absolute';	
	hideDivs('searchDiv');
	hideDivs('searchHeaderDiv');
}


</script>

</h:form>
	</body>

	<%@ include file="../navigation/pageFooter.jsp"%>
</f:view>
</html>

	