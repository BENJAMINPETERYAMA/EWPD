<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
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

<TITLE>Migration Wizard-Step 1</TITLE>
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
</HEAD>

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>



<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><h:inputHidden id="dummy"></h:inputHidden> <jsp:include
					page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="migrationform">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
<%--
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Migration Wizard" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="100%"></TD>
								</TR>
--%>									
										<tr>
											<td width="100%"><b> Step 2 : Date Segment </b> </td>
										</tr>
										<tr>
											 <td>Display all the Date Segments present in the legacy system for this particular contract.
											</br>Select one of the Date Segment based on which the new Date Segment will be created and there will be an option for the user to enter an effective date (this will be the effective date of the new Date Segment).</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>

							</TABLE>
							<!-- End of Tab table -->

<!--							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
-->
							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" " cellpadding="3" id="heading"
										border="0" width="100%" bgcolor="#cccccc">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" bgcolor="#cccccc"><h:outputText
													value="Contract Date Segments"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</td>
								</tr>

								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" cellpadding="3" id="subHeading"
										bgcolor="#cccccc" border="0" width="100%">
										<TBODY>

											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value=""></h:outputText></TD>
												<TD align="left"><h:outputText value="Contract ID"></h:outputText></TD>
												<TD align="left"><h:outputText value="Start Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="End Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="Status"></h:outputText></TD>

											</TR>
										</TBODY>
									</TABLE>
									</div>
									</td>
								</tr>
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:%;"><h:dataTable
										headerClass="dataTableHeader" id="dataTable" var="singleValue"
										cellpadding="5" cellspacing="1" bgcolor="#cccccc"
										rendered="#{legacyContractBackingBean.cp2000ContractList != null}"
										value="#{legacyContractBackingBean.cp2000ContractList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
											<f:verbatim>
												<wpd:singleRowSelect groupName="ProductCode" id="minor1"
													rendered="true"></wpd:singleRowSelect>
											</f:verbatim>
										</h:column>
										<h:column>
											<h:inputHidden id="StartDate1"
												value="#{singleValue.startDateString}"></h:inputHidden>
											<h:inputHidden id="ContractID1"
												value="#{singleValue.contractId}"></h:inputHidden>
											<h:outputText id="ContractID"
												value="#{singleValue.contractId}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="StartDate"
												value="#{singleValue.startDateString}">
											</h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="EndDate"
												value="#{singleValue.endDateString}">
											</h:outputText>
											<h:inputHidden id="EndDate1"
												value="#{singleValue.endDateString}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="Status" value="#{singleValue.contractStat}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									<div id="newDate">
									<TABLE>
										<tr>
											<td valign="top" width="100"><h:outputText styleClass="#{legacyContractBackingBean.effectiveDateInvalid? 'mandatoryError': 'mandatoryNormal'}"
												id="effectiveDateStar" value ="Effective Date *"></h:outputText></td>
											<td valign="top" width="150"><h:inputText
												styleClass="formInputField" id="Start_date"
												value="#{legacyContractBackingBean.newDate}" size="10">
											</h:inputText></td>
											<td valign="top" width="50"><a href="#"
												onclick="cal1.select('migrationform:Start_date','Strt_date_icon','MM/dd/yyyy'); return false;"
												title="cal1.select(document.forms[0].Start_date,'Strt_date_icon','MM/dd/yyyy'); return false;"
												name="Strt_date_icon" id="Strt_date_icon" > <h:commandButton
												image="../../images/cal.gif" style="cursor: hand" alt="Cal"
												 /></a>
										</tr>
									</TABLE>
									</div>
									<BR>
									</td>
								</TR>
								<TR>
									<TD width="189"><SPAN style="margin-left:0px;margin-right:0px;">
									<h:commandButton value="Back" styleClass="wpdButton"
										id="nextButton" action="#{legacyContractBackingBean.goToStep1}"></h:commandButton></SPAN>
									 <h:commandButton value="Next" styleClass="wpdButton"
										 id="backButton"
										onclick="onSelectCall();return false;"
										action="#{legacyContractBackingBean.goToStep3}"
										></h:commandButton></TD>
								</TR>
							</TABLE>
<!--							</FIELDSET>-->
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							</TD>
						</TR>
					</TABLE>
					<!-- Space for hidden fields -->
					<!-- End of hidden fields  -->
<h:inputHidden id="selectedStartDate" value="#{legacyContractBackingBean.selectedDateSegmentFromStep2}"/>
<h:inputHidden id="selectedNewDate" value="#{legacyContractBackingBean.newDate}"/>
		<h:inputHidden id="pageBreadcrumb" value="#{legacyContractBackingBean.pageBreadcrumb}"></h:inputHidden>
		<h:commandLink id="nextMigrationLink"
				style="display:none; visibility: hidden;"
				action="#{legacyContractBackingBean.goToStep3}">
				<f:verbatim />
			</h:commandLink>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	<SCRIPT>

	// checkLatest();


	function checkLatest(){
		var id='migrationform:dataTable';	
		if (document.getElementById(id))
		{
			var tableObject = document.getElementById(id);
			var checkboxObject = tableObject.rows[0].cells[0].children[0];
			checkboxObject.checked = 'true';
			for (var i=0;i<tableObject.rows.length;i++)
			{
				var checkboxObject = tableObject.rows[i].cells[0].children[0];
				checkboxObject.disabled='true';
			}
		}
	}

	function onSelectCall(){
				var selectVal = getCheckedItems_ewpd_local('migrationform:dataTable',2);
				document.getElementById('migrationform:selectedStartDate').value = selectVal;
				document.getElementById('migrationform:selectedNewDate').value = 
					document.getElementById('migrationform:Start_date').value;
				freezeScreen();
				document.getElementById('migrationform:nextMigrationLink').click();
	}

	var vSelectedStartDate = document.getElementById('migrationform:selectedStartDate').value
	matchCheckboxItems_ewpd('migrationform:dataTable', vSelectedStartDate, 2, 1, 1);
	function getCheckedItems_ewpd_local(id, attrCount)
	{
		var tableObject;	
		if (document.getElementById(id))
		{
			tableObject = document.getElementById(id);
			var selectedValues = '';
			var cnt = 0;
			var currentCell;
			for (var i=0;i<tableObject.rows.length;i++)
			{
				var checkboxObject = tableObject.rows[i].cells[0].children[0];
				currentCell = tableObject.rows[i].cells[1];
			
				if (checkboxObject && checkboxObject.checked) {
					if(cnt > 0)
						selectedValues += '~';

				switch(attrCount){
					case 1: selectedValues += currentCell.children[0].value; 
							break;
					case 2: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
				}
			}	
		}
	//	window.returnValue = selectedValues;
	//	window.close();	
		if(cnt > 0)
			return selectedValues;
		return false;
	}
	setColumnWidth('heading','25%:25%:25%:25%');
	setColumnWidth('subheading','6%:18%:21%:21%;34%');
	setColumnWidth('migrationform:dataTable','6%:18%:21%:21%;34%');
		</SCRIPT>
		<%@include file="../template/freeze.html" %>
	</BODY>
</f:view>
</HTML>

