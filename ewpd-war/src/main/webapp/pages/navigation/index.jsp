<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

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

<!-- Copied from top.jso -->
<script language="JavaScript" src="../../js/timeout.js"></script>
<script language="JavaScript">
	var timer;
	window.onload = function(){
		startTimer();
	}
</script>
</HEAD>
<f:view>
	<BODY>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>  
            <td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="22%"><img src="../../images/WPLogo.jpg" alt="WellPoint Logo" width="179" height="75" /></td>
			     <td width="78%" align="right" valign="top" class="topRightgraphic"><a href="#"><img src="../../images/print.gif" alt="Print" width="19" height="14" border="0" /> </a>
			
			<a href="../../pages/navigation/logout.jsp?logout=true"> &nbsp;  <span id="ApplicationName"> <img src="../../images/applicationName.jpg" alt="WellPoint Product Database System" width="314" height="31" border="0" /></span>Logout</a>
			
			</td>	
			  </tr>
			</table>
            </td>
		</tr>
		<tr> 
             <td>
				<table width="100%" border="0" cellpadding="5" cellspacing="0" class ="breadcrumb" >
			      <tr>
			        <td>&nbsp;<h:outputText id="breadCrumb1" value=""> </h:outputText></td>
			      </tr>
			    </table>	
             </td>
        </tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="273" valign="top" class="leftPanel"><br />
			<p>Product Configuration</p>
			<p></p>
			<p></p>
			</td>
			<td colspan="2" valign="top" class="ContentArea">
			<H5>Product Configuration</H5>
			<p style="">Description comes here.</p>
			<p class="enterN">Enter Now <img src="../../images/enterArrow.gif"
				alt="Enter" width="9" height="8" /></p>

			<h:form styleClass="form" id="checkOutObjectsForm">
				<h:outputText  value="Items you are working on ... " rendered="#{launchPageBackingBean.businessObjectsList != null}">
                </h:outputText>
				<br />
				<br />
				<div id="panel1Content">
				<DIV id="HeaderDiv1" style=width:100%;>
				<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
					id="resultHeaderTable1" border="0" width="100%">
					<TBODY>
						<TR  class="dataTableColumnHeaderInfo">
							<TD colspan="4">PRODUCT </TD>
                         <TR>
						<TR class="dataTableColumnHeader">
							<TD align="left"><h:outputText value="Id"></h:outputText></TD>
							<TD align="left"><h:outputText value="Checked Out Date"></h:outputText></TD>
							<TD align="left"><h:outputText value="Expires On"></h:outputText></TD>
							<TD align="left">&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</DIV>
				<div id="panel1"
					style="height:70px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td align="left"><h:dataTable headerClass="dataTableHeader"
							id="resultsTable1" border="0" width="100%"
							value="#{launchPageBackingBean.productList}"
							rendered="#{launchPageBackingBean.productList != null}"
							var="eachRow" cellpadding="1" cellspacing="1"  bgcolor="#ccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
								<h:outputText id="checkedOutObjectInfo1"
									styleClass="formOutputColumnField" value="#{eachRow.key}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="CheckOutInfo1"
									styleClass="formOutputColumnField"
									value="#{eachRow.checkoutDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
                                 </h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="ExpiryDateInfo1"
									styleClass="formOutputColumnField"
									value="#{eachRow.expiryDate}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
								</h:outputText>
							</h:column>
							<h:column>
								<h:commandButton alt="View" id="viewButton1"
									image="../../images/view.gif"
									action="#{launchPageBackingBean.getCheckoutObjectForView();return false;}">
								</h:commandButton>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							</h:column>
						</h:dataTable></td>
					</tr>
				</table>
				</div>
				</div>
				<br />
				<div id="panel2Content">				
				<DIV id="HeaderDiv1">
				<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
					id="resultHeaderTable2" border="0" width="100%">
					<TBODY>
						<TR  class="dataTableColumnHeaderInfo">
							<TD colspan="4">PRODUCT STRUCTURE</TD>
                         <TR>
						<TR class="dataTableColumnHeader">
							<TD align="left"><h:outputText value="Id"></h:outputText></TD>
							<TD align="left"><h:outputText value="Checked Out Date"></h:outputText></TD>
							<TD align="left"><h:outputText value="Expires On"></h:outputText></TD>
							<TD align="left">&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</div>
				<div id="panel2"
					style="height:70px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td align="left"><h:dataTable id="resultsTable2" border="0"
							headerClass="dataTableHeader" width="100%"
							value="#{launchPageBackingBean.productStructureList}"
							rendered="#{launchPageBackingBean.productStructureList != null}"
							var="eachRow" cellpadding="1" cellspacing="1"  bgcolor="#ccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
								<h:outputText id="checkedOutObjectInfo2"
									styleClass="formOutputColumnField" value="#{eachRow.key}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="CheckOutInfo2"
									styleClass="formOutputColumnField"
									value="#{eachRow.checkoutDate}">
                                <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
                               </h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="ExpiryDateInfo2"
									styleClass="formOutputColumnField"
									value="#{eachRow.expiryDate}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
								</h:outputText>
							</h:column>
							<h:column>
								<h:commandButton alt="View" id="viewButton2"
									image="../../images/view.gif"
									action="#{launchPageBackingBean.getCheckoutObjectForView();return false;}">
								</h:commandButton>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							</h:column>
						</h:dataTable></td>
					</tr>
				</table>
				</div>
				</div>
				<br />
				<div id="panel3Content">
				<DIV id="HeaderDiv1">
				<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
					id="resultHeaderTable3" border="0" width="100%">
					<TBODY>
							<TR  class="dataTableColumnHeaderInfo">
								<TD colspan="4">BENEFIT </TD>
                         	<TR>
						<TR class="dataTableColumnHeader">
							<TD align="left"><h:outputText value="Id"></h:outputText></TD>
							<TD align="left"><h:outputText value="Checked Out Date"></h:outputText></TD>
							<TD align="left"><h:outputText value="Expires On"></h:outputText></TD>
							<TD align="left">&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</div>
				<div id="panel3"
					style="height:70px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td align="left"><h:dataTable id="resultsTable3" border="0"
							headerClass="dataTableHeader" width="100%"
							value="#{launchPageBackingBean.standardBenefitList}"
							rendered="#{launchPageBackingBean.standardBenefitList != null}"
							var="eachRow" cellpadding="1" cellspacing="1" bgcolor="#ccccc"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							<h:column>
								<h:outputText id="checkedOutObjectInfo3"
									styleClass="formOutputColumnField" value="#{eachRow.key}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="CheckOutInfo3"
									styleClass="formOutputColumnField"
									value="#{eachRow.checkoutDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
								</h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="ExpiryDateInfo3"
									styleClass="formOutputColumnField"
									value="#{eachRow.expiryDate}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss " />
								</h:outputText>
							</h:column>
							<h:column>
								<h:commandButton alt="View" id="viewButton3"
									image="../../images/view.gif"
									action="#{launchPageBackingBean.getCheckoutObjectForView();return false;}">
								</h:commandButton>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							</h:column>
						</h:dataTable></td>
					</tr>
				</table>
				</div>
				</div>
			</h:form></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<%@ include file="../navigation/bottom.jsp"%>
		</tr>
	</table>
	</BODY>
</f:view>
<script>

	setWidth('checkOutObjectsForm:resultsTable1', 'panel1Content', 'resultHeaderTable1');
	setWidth('checkOutObjectsForm:resultsTable2', 'panel2Content', 'resultHeaderTable2');
	setWidth('checkOutObjectsForm:resultsTable3', 'panel3Content', 'resultHeaderTable3');

	function setWidth(resultTable, headerDiv, headerTable){
		if(document.getElementById(resultTable) != null) {		
			setColumnWidth(resultTable,'20%:30%:30%:20%');
			setColumnWidth(headerTable,'20%:30%:30%:20%');
			document.getElementById(resultTable).onresize = syncTables;
			syncTables();
		}else{
			var headerDiv = document.getElementById(headerDiv);
			headerDiv.style.display ='none';
			
		}	
	}
	function syncTables(){
		if(document.getElementById('checkOutObjectsForm:resultsTable1')){
			syncTable('checkOutObjectsForm:resultsTable1','resultHeaderTable1');
		}
		if(document.getElementById('checkOutObjectsForm:resultsTable2')){
			syncTable('checkOutObjectsForm:resultsTable2','resultHeaderTable2');
		}
		if(document.getElementById('checkOutObjectsForm:resultsTable3')){
			syncTable('checkOutObjectsForm:resultsTable3','resultHeaderTable3');
		}
	}
	function syncTable(resultTable,headerTable){
		var relTblWidth = document.getElementById(resultTable).offsetWidth;
		document.getElementById(headerTable).width = relTblWidth + 'px';
	}

</script>
</HTML>
