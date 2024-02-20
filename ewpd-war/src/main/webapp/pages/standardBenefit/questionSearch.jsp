<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Search Question</TITLE>
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
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/header.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><jsp:include page="../navigation/MenuComponent.jsp"></jsp:include>
				</TD>
			</TR>
			<TR>
				<TD>
				<TABLE width="100%" id="breadCumbTable" bgcolor="#7670B3">
					<TR>
						<TD height="16" valign="middle" bgcolor="#7670B3"
							class="breadcrumb">Product Configuration
						&nbsp;&gt;&gt;&nbsp;Question&nbsp;&gt;&gt;&nbsp;Maintenance</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="questionSearchForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{QuestionSearchBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Questions" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<!--  <td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td class="tabNormal"> 
														<h:commandLink > <h:outputText value="Tab2"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</tr>
											</table>
										</td>-->
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:410">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD width="110"><h:outputText id="questionCriteria"
											value="Question *"
											styleClass="#{QuestionSearchBackingBean.requiredQuestion ? 'mandatoryError' : 'mandatoryNormal'}" />
										</TD>
										<TD width="229"><h:inputText styleClass="formInputField"
											style="width:200"
											value="#{QuestionSearchBackingBean.criteriaQuestion}" /></TD>
									</TR>
									<TR>
										<TD width="141" height="13">&nbsp;</TD>
									</TR>
									<TR>
										<TD width="110"><h:commandButton value="Locate"
											styleClass="wpdButton"
											action="#{QuestionSearchBackingBean.search}">
										</h:commandButton></TD>
										<TD width="229">&nbsp;</TD>
									</TR>
									<TR>
										<TD width="141" height="13">&nbsp;</TD>
									</TR>
								</TBODY>
							</TABLE>
							<TABLE width="100%" cellspacing="0" cellpadding="0" border="0">

								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TR>
											<TD><strong><h:outputText value="Search Results"></h:outputText></strong></TD>
										</TR>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="33%"><h:outputText value="Question"></h:outputText>
												</TD>
												<TD align="left" width="33%"><h:outputText value="Data Type"></h:outputText>
												</TD>
												<TD width="34%">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:200px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{QuestionSearchBackingBean.questionSearchResultList != null}"
										value="#{QuestionSearchBackingBean.questionSearchResultList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

										<h:column>
											<h:outputText id="description"
												value="#{singleValue.question}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="effectiveDate"
												value="#{singleValue.dataType}"></h:outputText>
										</h:column>
										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="Edit" id="editButton"
												image="../../images/edit.gif" onclick=""
												action="#{QuestionSearchBackingBean.editQuestion}"></h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="Delete" id="deleteButton"
												image="../../images/delete.gif"
												onclick=""
												action="#{QuestionSearchBackingBean.deleteQuestion}"></h:commandButton>

										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>

							</TABLE>

							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->

					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@ include file ="../navigation/pageFooter.jsp" %></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
		function syncTables(){
			var relTblWidth = document.getElementById('questionSearchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';

		}
		if(document.getElementById('questionSearchForm:searchResultTable')!= null){
		document.getElementById('questionSearchForm:searchResultTable').onresize = syncTables;
		syncTables();
		setColumnWidth('questionSearchForm:searchResultTable', '33%:33%:34%');
		}
		else{
		var HeaderDivObj = document.getElementById('resultHeaderDiv');
		var divObj = document.getElementById('searchResultdataTableDiv');
		divObj.style.visibility = 'hidden';
		HeaderDivObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		HeaderDivObj.style.height = "0px";
		divObj.style.position = 'absolute';
		HeaderDivObj.style.position = 'absolute';
		}
	
</script>
</HTML>
