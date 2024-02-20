<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>View Product Structure Benefit Administration</TITLE>
<%
String browser = request.getHeader("user-agent");
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
<script language="JavaScript" src="../../js/wpd.js"></script>
<%
}
else {
%>
<script language="JavaScript" src="../../js/browserCompatible.js"></script>
<script language="JavaScript" src="../../js/showModalDialog.js"></script>
<%
}
%>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<h:inputHidden value="#{productStructureBenefitAdministrationBackingBean.loadQuestionForView}" />
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitAdmnForm">
				<table width="100%" border="1" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="600" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>

								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabActive">
											<h:outputText value="Benefit Administration" />
										</td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:510">

						<!--	Start of Table for actual Data	--> <BR>
						<div id="adminDiv"><h:outputText
							value="No Benefit Administration Available."
							styleClass="dataTableColumnHeader" /></div>
						<DIV id="benefitAdministrationDiv" >
						<TABLE border="0" cellspacing="1" cellpadding="0" width="100%"
							class="outputText">
							<TBODY>
								<TR>
									<td>
																				<div id="LabelHeaderDiv"
										style="background-color:#cccccc;z-index:1;overflow:auto;height:15;width:100%">
								        <B>&nbsp;<h:outputText value="Selected Questions"></h:outputText> </B>
									</div>
									</td>
									</TR>
									<tr>
									<td>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="40%"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="Answer"></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText value="Reference"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText value="Notes"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</td>
								</TR>
								<TR>
									<td valign="middle">
									
									<DIV id="searchResultdataTableDiv"
										style="height:370px;width:100%;overflow-y:auto;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{productStructureBenefitAdministrationBackingBean.viewQuestionnaireList != null}"
										rowClasses="dataTableOddRow"
										value="#{productStructureBenefitAdministrationBackingBean.viewQuestionnaireList}"
										border="0" width="100%">
										<h:column>
											<h:outputText id="questionName"
												value="#{singleValue.questionName}"></h:outputText>
											<h:inputHidden id="hidden_questionName"
												value="#{singleValue.questionName}"></h:inputHidden>
											<h:inputHidden id="hidden_questionId"
												value="#{singleValue.questionId}"></h:inputHidden> 
										</h:column>
										<h:column>
											<h:outputText id="ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:outputText>
											<h:inputHidden id="hidden_ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="refDesc"
												value="#{singleValue.referenceDesc}"></h:outputText>
											<h:inputHidden id="hidden_level" value="#{singleValue.level}"></h:inputHidden>
										</h:column>

										<h:column id="noteColumn">
										<h:commandButton alt="notes" id="notesButton" 
														 image="../../images/notes_exist.gif"
														onclick="loadNotesPopup(); return false;" 
														rendered="#{singleValue.notesExists== 'Y'}"></h:commandButton>
										<h:commandButton alt="notes" id="notesButton1" 
														 image="../../images/page.gif"
														 onclick="loadNotesPopup(); return false;" 
														rendered="#{singleValue.notesExists== 'N'}"></h:commandButton>		
										</h:column>


									</h:dataTable></DIV> 
									</td>
								</TR>
								<TR>
									<td><BR>
									</td>
								</TR>
								<TR>
								</TR>
								<TR>
								</TR>
							</TBODY>
						</TABLE>
						</DIV>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
					<h:inputHidden id ="questionId" ></h:inputHidden>					
					<h:inputHidden id ="primaryEntityId"  value ="#{productStructureBenefitAdministrationBackingBean.productStructureId}"></h:inputHidden>					
					<h:inputHidden id ="benefitComponentId"  value ="#{productStructureBenefitAdministrationBackingBean.benefitComponentId}"></h:inputHidden>
					<h:inputHidden id ="benefitDefnId"  value ="#{productStructureBenefitAdministrationBackingBean.benefitDefnId}"></h:inputHidden>
					<h:inputHidden id ="adminLvlOptionId"  value ="#{productStructureBenefitAdministrationBackingBean.adminLvlOptionAssnSysId}"></h:inputHidden>					
					
				<!-- End of hidden fields  --> 

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
		var tableObject = document.getElementById('benefitAdmnForm:searchResultTable'); 

	if(tableObject!= null && tableObject.rows.length > 0) {

			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			var rowlength = document.getElementById('benefitAdmnForm:searchResultTable').rows.length;
			document.getElementById('benefitAdmnForm:searchResultTable').width = relTblWidth+"px";
			syncTables('resultHeaderTable','benefitAdmnForm:searchResultTable');
		    setColumnWidth('resultHeaderTable','40%:20%:30%:10%');
			setColumnWidth('benefitAdmnForm:searchResultTable','40%:20%:30%:10%');	
			var divObj = document.getElementById('adminDiv');
			divObj.style.visibility = 'hidden';	
			divObj.style.height = "2px";				
	} else{
		var divHeader = document.getElementById('benefitAdministrationDiv');
		divHeader.style.visibility = 'hidden';	
		divHeader.style.height = "2px";	
	}
	for(var i=0; i<tableObject.rows.length; i++){
		var rowobj = tableObject.rows[i];
		var cellobj = tableObject.rows[i].cells[2];
		var text = cellobj.children[1].value;
		if(text ==1){
			rowobj.className = 'dataTableEvenRow';
		}
	}


function loadNotesPopup(){

	getFromDataTableToHidden('benefitAdmnForm:searchResultTable','hidden_questionId','benefitAdmnForm:questionId');

	var questionId = document.getElementById('benefitAdmnForm:questionId').value;
	var primaryEntityId	= document.getElementById('benefitAdmnForm:primaryEntityId').value;
	var benefitComponentId = document.getElementById('benefitAdmnForm:benefitComponentId').value;
	var benefitDefnId = document.getElementById('benefitAdmnForm:benefitDefnId').value;
	var adminLvlOptionId = document.getElementById('benefitAdmnForm:adminLvlOptionId').value;
	var popupName = '../popups/prodStructQuestionNotesViewPopup.jsp';
	var primaryEntityType = 'ATTACHPRODSTRCT';
	var secondaryEntityType='ATTACHQUESTION';

	var retValue = window.showModalDialog(popupName +getUrl()+ "?"
				 	+ "&temp=" + Math.random() 
					+ "&questionId="+questionId+"&primaryentityId="+primaryEntityId+"&primaryEntytyType="+primaryEntityType
					+"&bcId="+ benefitComponentId+"&benefitDefnId="
					+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType , 
					self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
}
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitAdminProductStructure" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
