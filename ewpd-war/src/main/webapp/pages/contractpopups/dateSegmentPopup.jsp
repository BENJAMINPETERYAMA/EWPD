<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<base target=_self>
<TITLE>Create Date Segment</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<input type="HIDDEN" name="ISFORMCONTROLSUSED" id="ISFORMCONTROLSUSED" value="false"></input>

<script type="text/javascript">



//	if(document.getElementById('dateSegmentForm:benefitStructureDataTable') != null) {
//			tigra_tables('dateSegmentForm:benefitStructureDataTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)');
//			setColumnWidth('dateSegmentForm:benefitStructureDataTable','14%:14%:17%:16%:25%');	
//	}
</script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
</HEAD>
<f:view>
	<body><hx:scriptCollector id="scriptCollector1">
	<jsp:include page="../navigation/popupHeader.jsp"></jsp:include>
	<h:form id="dateSegmentForm">
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
			<TBODY>
				<TR>
					<TD height="16" valign="middle" bgcolor="#7670B3" class="breadcrumb">Search &gt;&gt; Contract &gt;&gt; Date Segment
					</TD>
					<TD class="breadcrumb" width="55">&nbsp;</TD>
				</TR>
			</TBODY>
		</TABLE>
		
		
<BR>
	<TABLE width="96%" border="0" valign="top" align="center" cellpadding="0" cellspacing="0">
			<TR>
				<TD align="left"><w:message></w:message></TD>
			</TR>
		</TABLE>
		</br>
		<DIV id="popupDataTableDiv1">

<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo" style="width: 617;margin-left: 10">
							<STRONG>
				<h:outputText value="Create Date Segment for Contract : ">
				</h:outputText> </STRONG><h:outputText value="#{dateSegmentBackingBean.selectedContractId}" styleClass="dataTableHeader"></h:outputText>
						</div>

		<BR>
		<div id="panel2Content" class="tabContentBox" style="position:relative;font-size:10px;width:70%;"> 
		<DIV id="popupDataTableDiv1" align="left">
			<TABLE cellpadding="0" cellspacing="0" border="0">
				
					<tr>
										<td>
											<div id="resultHeaderDiv" >
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="617">
													<TBODY>
														<TR class="dataTableColumnHeader">
															<td align="left" >
																		<h:outputText value="Contract ID" ></h:outputText> 
															</td>
															<td  align="left" >
							              								<h:outputText value="Start Date" ></h:outputText>
															</td >
															<%-- 
															<td align="left" >
							              								<h:outputText value="Revision Date"></h:outputText>
															</td>
															--%>
															<td align="left" >
							              								<h:outputText value="End Date" ></h:outputText> 
															</td>
															<td align="left" >
							              								<h:outputText value="Contract Status" ></h:outputText> 
															</td>
															<td align="left" >
							              								<h:outputText value="Type" ></h:outputText> 
															</td>
														 </TR>
													</TBODY>
												</TABLE>
											</div>
										</td>
									</tr>
				
				<TR>
					<TD>		
					<DIV id="popupDataTableDiv" style="height:120px;overflow:auto;width:100%;">
					<h:dataTable headerClass="tableHeader" rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1" id="benefitStructureDataTable" value="#{dateSegmentBackingBean.resultList}" 
						var="eachRow" cellpadding="3" bgcolor="#cccccc"  width="100%">
						<h:column>
							<h:outputText value="#{eachRow.contractId}"></h:outputText>
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.startDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
						</h:column>
						<%-- 
						<h:column>
							<h:outputText value="#{eachRow.revisionDateStr}"></h:outputText>
						</h:column>
						--%>
						<h:column>
							<h:outputText value="#{eachRow.endDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.status}"></h:outputText>
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.description}"></h:outputText>
						</h:column>
					</h:dataTable></DIV></TD></TR></TABLE>
		</DIV>
</div>
		<div>
			<TABLE align="center" valign="top" width="96%">
				<TR>
					<TD width="14%">&nbsp;</TD>
					<TD width="18%"></TD>
					<TD colspan="4"></TD>
				</TR>
				
				<%-- 
				<TR>
					<TD width="14%"><h:outputText value="Select :" rendered="#{!dateSegmentBackingBean.contractLocked}"></h:outputText></TD>
					<TD width="18%"><h:selectOneMenu id="ChooseDate" value="#{dateSegmentBackingBean.dateSelected}" rendered="#{!dateSegmentBackingBean.contractLocked}" styleClass="formInputField">
						<f:selectItem id="startId" itemValue="Start Date" itemLabel="Start Date" />
						<f:selectItem id="revisionId" itemValue="Revision Date" itemLabel="Revision Date" />
					</h:selectOneMenu></TD>
					<TD colspan="4"></TD>
				</TR>
				--%>
				<TR>
									<TD ><h:outputText id="type" 
										value="Select  the  type * :"/>
									</TD>
									<TD valign="top" width="151">
										<h:selectOneRadio id="rdObjectType" value="#{dateSegmentBackingBean.dsType}">
										  	<f:selectItem itemValue="N" itemLabel="Regular" />   
										  	<f:selectItem itemValue="Y" itemLabel="Test"/>   
										</h:selectOneRadio>
								</TR>
				<TR>
					<TD width="16%" valign="top"><h:outputText value="Enter Start Date *:" styleClass="#{dateSegmentBackingBean.requiredStartDate ? 
                                'mandatoryError': 'mandatoryNormal'}"></h:outputText></TD>
					<TD valign="top" width="151"><h:inputText id="startFromDate" styleClass="formInputField" value="#{dateSegmentBackingBean.dateEntered}" size="30" >
					</h:inputText><BR>
					<SPAN class="dateFormat"><h:outputText value="(mm/dd/yyyy)"></h:outputText></SPAN></TD>
					<TD width="70" valign="top"> <A href="#" onclick="cal1.select('dateSegmentForm:startFromDate','anchor1','MM/dd/yyyy');return false" title="cal1.select(document.forms[0].dateSegmentForm:startFromDate,'anchor1','MM/dd/yyyy'); 
					 return false;" name="anchor10" id="anchor10"><h:commandButton image="../../images/cal.gif" style="cursor: hand"  alt="Cal" />
						</A><SPAN class="dateFormat"></SPAN>
					</TD>
					<TD align="left" valign="top" width="115"><A href="#" onclick="cal1.select('dateSegmentForm:startFromDate','anchor1','MM/dd/yyyy');return false" title="cal1.select(document.forms[0].dateSegmentForm:startFromDate,'anchor1','MM/dd/yyyy'); 
					 return false;" name="anchor1" id="anchor1"> 
					</A></TD>
					<TD valign="top" width="476"></TD>
				</TR>
				<TR>
					<TD width="14%"></TD>
					<TD valign="top" width="151"><h:commandButton styleClass="wpdbutton" id="isgBasicSearch" value="Create"  action="#{dateSegmentBackingBean.loadNewList}"></h:commandButton>&nbsp;&nbsp;&nbsp; 
						<h:commandButton onclick="javascript:self.window.close();return false;" styleClass="wpdbutton" id="cancelBtn" value="Cancel" ></h:commandButton></TD>
					<TD width="70">
					&nbsp;<BR></TD>
					<TD align="left" valign="top" width="115"></TD>
					<TD valign="top" width="476"></TD>
					<TD></TD>
				</TR>
			</TABLE>
			</DIV>
					<h:inputHidden id="contractIdForDS"
										value="#{dateSegmentBackingBean.selectedContractSysId}" />
		</h:form>
	<TABLE valign="top" width="96%">
		<TR>
			<TD width="47%"></TD>
			<TD width="188">
			<c:if test="">
				<h:commandButton styleClass="wpdbutton" id="isgCloseWindow" value="Close" onclick="javascript:self.window.close();"></h:commandButton>
			</c:if>
			&nbsp;&nbsp; </TD>
			<TD valign="top" width="395"></TD>
			<TD></TD>
		</TR>
	</TABLE>
	</hx:scriptCollector></BODY>
</f:view>
<script>

		if(document.getElementById('dateSegmentForm:benefitStructureDataTable') != null) {
			setColumnWidth('dateSegmentForm:benefitStructureDataTable','12%:11%:11%:28%:9%');
			setColumnWidth('resultHeaderTable','12%:11%:11%:28%:9%');
			//showResultsTab();
		}else {
			
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	

</script>

</HTML>

