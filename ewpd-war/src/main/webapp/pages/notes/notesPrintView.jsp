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

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->
<style type="text/css">
.selectsystemDomainIdColumn {
	width: 50%;
}

.selectsystemDomainIdColumn1 {
	width: 51%;
}
</style>
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
	
</script>

<TITLE></TITLE>
</HEAD>
<f:view>
	<BODY>
	<%--<h:inputHidden id="loadBnftDefnForPrint" value="#{productBenefitDetailBackingBean.forPrint}"></h:inputHidden>--%>
	<h:inputHidden id="typeList"
		value="#{ReferenceDataBackingBeanCommon.noteTypeListForCombo}"></h:inputHidden>
	<h:inputHidden id="viewNotesKey" value="#{notesBackingBean.printEdit}" />
	<h:inputHidden id="noteID" value="#{notesBackingBean.noteId}"></h:inputHidden>
	<h:form styleClass="form" id="notesPrintForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left: 92px; margin-right: -1px; padding-bottom: 1px; padding-top: 1px; width: 89%">
				<h:outputText id="breadcrumb"
					value="#{notesBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>
		<TABLE width="100%">
			<TR>
				<TD width="100%" colspan="2"><w:message></w:message></TD>
			</TR>
		</TABLE>
		<table width="80%" border="0" align="center" cellspacing="0"
			cellpadding="0">

			<tr>
				<td width="80%">
				<div id="notesGenInfo"><!--	Start of Table for actual Data	-->
				<fieldset>
				<TABLE border="0" align="center" width="80%" cellspacing="0"
					cellpadding="3">
					<TBODY>
						<TR>
							<TD width="40%"><h:outputText value="Note ID" /></TD>
							<TD width="60%"><h:outputText
								value="#{notesBackingBean.noteId}" /></TD>
						</TR>
						<TR>
							<TD width="40%"><h:outputText value="Note Name" /></TD>
							<TD width="60%"><h:outputText
								value="#{notesBackingBean.name}" /></TD>
						</TR>
						<TR>
							<TD width="40%"><h:outputText value="Note Type" /></TD>
							<TD width="60%"><h:outputText
								value="#{notesBackingBean.noteTypeDesc}" /></TD>
						</TR>
						<TR>
							<TD width="40%"><h:outputText value="Target Systems" /></TD>
							<TD width="60%"><h:outputText id="systemDomainDiv"
								value="#{notesBackingBean.systemDomain}" /> <h:inputHidden
								id="txtSystemDomain" value="#{notesBackingBean.systemDomain}"></h:inputHidden></TD>
						</TR>
						<TR>
							<TD width="40%" valign="top"><h:outputText value="Note Text" /></TD>
							<TD width="60%" colspan="2"><h:outputText
								id="description_txtarea"
								value="#{notesBackingBean.formattedNotes}" /></TD>
						</TR>
						<TR>
							<TD width="40%"><h:outputText value="Created By" /></TD>
							<TD width="60%"><h:outputText
								value="#{notesBackingBean.createdUser}" /></TD>
						</TR>

						<TR>
							<TD width="40%"><h:outputText value="Created Date" /></TD>
							<TD width="60%"><h:outputText
								value="#{notesBackingBean.createdTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText></TD>
						</TR>

						<TR>
							<TD width="40%"><h:outputText value="Last Updated By" /></TD>
							<TD width="60%"><h:outputText
								value="#{notesBackingBean.lastUpdatedUser}" /></TD>
						</TR>

						<TR>
							<TD width="40%"><h:outputText value="Last Updated Date" /></TD>
							<TD width="60%"><h:outputText
								value="#{notesBackingBean.lastUpdatedTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText></TD>
						</TR>


					</TBODY>
				</TABLE>
				</fieldset>



				</div>
				</TD>


			</TR>


			<TR>
				<td width="80%">
				<div id="messageDiv" align="center">Note is not domained.</div>
				<div id="notesDataDomain"><h:inputHidden
					id="loadNotesDataDomainForPrint"
					value="#{notesBackingBean.printDataDomain}"></h:inputHidden> <!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="0" align="center" width="100%"
					cellpadding="3" class="outputText">
					<TBODY>
						<TR height="4%">
							<TD></TD>
						</TR>



						<TR>
							<TD colspan="10"><!-- Product table -->
							<div id="ProductHeaderDiv">
							<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr bgcolor="#cccccc">
									<td colspan="6"><span id="stateCodeStar"><strong>Domained
									Product</strong></span></td>
								</tr>


								<tr>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height: 50px; width: 100%; position: relative; z-index: 1; font-size: 10px;">
									<h:dataTable columnClasses="selectsystemDomainIdColumn"
										cellspacing="1" width="100%" id="productTable"
										rendered="#{notesBackingBean.productList!= null}"
										value="#{notesBackingBean.productList}" var="eachRow"
										cellpadding="0">
										<h:column>

											<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
											<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</td>
								</tr>
							</TABLE>
							</div>
							</TD>
						</TR>

						<TR>
							<TD></TD>
						</TR>
						<!--  BenefitComponent Table -->
						<TR>
							<TD colspan="10">
							<div id="CompHeaderDiv">
							<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr bgcolor="#cccccc">
									<td colspan="6"><span id="stateCodeStar"><strong>Domained
									Benefit Component</strong></span></td>
								</tr>


								<tr>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height: 50px; width: 100%; position: relative; z-index: 1; font-size: 10px;">
									<h:dataTable columnClasses="selectsystemDomainIdColumn1"
										rendered="#{notesBackingBean.componentList!= null}"
										cellspacing="1" width="100%" id="benfcompTable"
										value="#{notesBackingBean.componentList}" var="eachRow"
										cellpadding="0">
										<h:column>

											<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
											<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</td>
								</tr>
							</TABLE>
							</div>
							</TD>
						</TR>

						<TR>
							<TD></TD>
						</TR>
						<!--  Benefit Table -->
						<TR>
							<TD colspan="10">
							<div id="BenefitHeaderDiv">
							<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr bgcolor="#cccccc">
									<td colspan="6"><span id="stateCodeStar"><strong>Domained
									Benefit</strong></span></td>
								</tr>


								<tr>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height: 50px; width: 100%; position: relative; z-index: 1; font-size: 10px;">
									<h:dataTable columnClasses="selectsystemDomainIdColumn1"
										cellspacing="1" width="100%" id="benfTable"
										rendered="#{notesBackingBean.benefitList!= null}"
										value="#{notesBackingBean.benefitList}" var="eachRow"
										cellpadding="0">
										<h:column>

											<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
											<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</td>
								</tr>
							</TABLE>
							</div>
							</TD>
						</TR>

						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD colspan="10">
							<div id="QuestionHeaderDiv">
							<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr bgcolor="#cccccc">
									<td colspan="6"><span id="stateCodeStar"><strong>Domained
									Question</strong></span></td>
								</tr>


								<tr>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height: 50px; width: 100%; position: relative; z-index: 1; font-size: 10px;">
									<h:dataTable columnClasses="selectsystemDomainIdColumn1"
										cellspacing="1" width="100%" id="questionTable"
										rendered="#{notesBackingBean.questionList!= null}"
										value="#{notesBackingBean.questionList}" var="eachRow"
										cellpadding="0">
										<h:column>

											<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
											<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</td>
								</tr>
							</TABLE>
							</div>
							</TD>
						</TR>

						<TR>
							<TD></TD>
						</TR>
						<!--  Term Table -->
						<TR>
							<TD colspan="10">
							<div id="TermHeaderDiv">
							<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr bgcolor="#cccccc">
									<td colspan="6"><span id="stateCodeStar"><strong>Domained
									Term</strong></span></td>
								</tr>


								<tr>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height: 50px; width: 100%; position: relative; z-index: 1; font-size: 10px;">
									<h:dataTable columnClasses="selectsystemDomainIdColumn1"
										cellspacing="1" width="100%" id="termTable"
										rendered="#{notesBackingBean.termList!= null}"
										value="#{notesBackingBean.termList}" var="eachRow"
										cellpadding="0">
										<h:column>

											<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
											<h:outputText value="#{eachRow.systemDomainDescription}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</td>
								</tr>
							</TABLE>
							</div>
							</td>
						</tr>
						<TR>
							<TD></TD>
						</TR>
						<!--  Qualifier Table -->
						<TR>
							<TD colspan="10">
							<div id="QualifierHeaderDiv">
							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr bgcolor="#cccccc">
									<td colspan="6"><span id="stateCodeStar"><strong>Domained
									Qualifier</strong></span></td>
								</tr>


								<tr>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height: 50px; width: 100%; position: relative; z-index: 1; font-size: 10px;">
									<h:dataTable columnClasses="selectsystemDomainIdColumn1"
										rendered="#{notesBackingBean.qualifierList!= null}"
										cellspacing="1" width="100%" id="qualifierTable"
										value="#{notesBackingBean.qualifierList}" var="eachRow"
										cellpadding="0">
										<h:column>

											<h:inputHidden value="#{eachRow.systemDomainId}"></h:inputHidden>
											<h:outputText value="#{eachRow.systemDomainId}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</td>
								</tr>
							</TABLE>
							</div>

							</TD>
						</TR>

					</TBODY>
				</TABLE>

				</div>
				<fieldset>
				<TABLE border="0" align="center" width="80%" cellspacing="0"
					cellpadding="3">
					<TR>
						<TD width="40%"><h:outputText value="State" /></TD>
						<TD width="60%"><h:outputText
							value="#{notesBackingBean.state}" /></TD>
					</TR>

					<TR>
						<TD width="40%"><h:outputText value="Status" /></TD>
						<TD width="60%"><h:outputText
							value="#{notesBackingBean.status}" /></TD>
					</TR>

					<TR>
						<TD width="40%"><h:outputText value="Version" /></TD>
						<TD width="60%"><h:outputText
							value="#{notesBackingBean.version}" /></TD>
					</TR>
				</table>
				</fieldset>


				</TD>
			</TR>

		</table>
		<h:inputHidden id="printNotesGeneralInformation"
			value="#{notesBackingBean.printValueForGenInfo}"></h:inputHidden>
		<h:inputHidden id="printNotesDataDomain"
			value="#{notesBackingBean.printValueForDataDomain}"></h:inputHidden>
		<h:inputHidden id="notetypeList"
			value="#{notesBackingBean.noteTypeList}"></h:inputHidden>

	</h:form>
	</BODY>

</f:view>

<script>
	document.getElementById('messageDiv').style.visibility = 'hidden';
	copyToHidden('typeList', 'notesPrintForm:notetypeList');
	hideIfNoValue('ProductHeaderDiv', 'productTable');
	hideIfNoValue('CompHeaderDiv', 'benfcompTable');
	hideIfNoValue('BenefitHeaderDiv', 'benfTable');
	hideIfNoValue('TermHeaderDiv', 'termTable');
	hideIfNoValue('QualifierHeaderDiv', 'qualifierTable');
	hideIfNoValue('QuestionHeaderDiv', 'questionTable');
	function formatTildaToComma(objName) {

		var formattedString = "";
		var obj = document.getElementById(objName);

		var val = obj.innerHTML;

		if (val == null || val == '')
			return;

		var values = val.split('~');
		if (values != null) {

			for (i = 0, n = values.length; i < n; i += 2) {
				formattedString += values[i];
				if (i < n - 2)
					formattedString += ", ";
			}
			obj.innerHTML = formattedString;
		}
		return;
	}
	function hideIfNoValue(divId, tableName) {
		hiddenIdObj = document.getElementById('notesPrintForm:' + tableName);
		hideDiv = document.getElementById(divId);
		if (hiddenIdObj == null) {
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		} else {
			document.getElementById(divId).style.visibility = 'visible';
		}
	}
	if ("" != printForGenInfo) {
		formatTildaToComma('notesPrintForm:systemDomainDiv');
	}
	var printForGenInfo = document
			.getElementById("notesPrintForm:printNotesGeneralInformation").value;
	var printForDataDom = document
			.getElementById("notesPrintForm:printNotesDataDomain").value;

	if (null == printForGenInfo || "" == printForGenInfo) {
		var genInfoDivObj = document.getElementById('notesGenInfo');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
		document.title = "Current Page Print";
		if (document.getElementById('notesPrintForm:productTable') == null
				&& document.getElementById('notesPrintForm:benfcompTable') == null
				&& document.getElementById('notesPrintForm:benfTable') == null
				&& document.getElementById('notesPrintForm:termTable') == null
				&& document.getElementById('notesPrintForm:questionTable') == null
				&& document.getElementById('notesPrintForm:qualifierTable') == null) {
			document.getElementById('messageDiv').style.visibility = 'visible';
		} else {
			document.getElementById('messageDiv').style.visibility = 'hidden';
		}
	}
	if (null == printForDataDom || "" == printForDataDom) {
		var benDefDivObj = document.getElementById('notesDataDomain');
		benDefDivObj.style.visibility = "hidden";
		benDefDivObj.style.height = "0px";
		benDefDivObj.innerText = null;
		document.title = "Current Page Print";
	} else if ("" != printForGenInfo && "" != printForDataDom) {
		document.title = "Detailed Print";
		if (document.getElementById('notesPrintForm:productTable') == null
				&& document.getElementById('notesPrintForm:benfcompTable') == null
				&& document.getElementById('notesPrintForm:questionTable') == null
				&& document.getElementById('notesPrintForm:benfTable') == null
				&& document.getElementById('notesPrintForm:termTable') == null
				&& document.getElementById('notesPrintForm:qualifierTable') == null) {
			document.getElementById('messageDiv').style.visibility = 'visible';
		} else {
			document.getElementById('messageDiv').style.visibility = 'hidden';
		}
	}
</script>
<script>
	window.print();
</script>

</HTML>
