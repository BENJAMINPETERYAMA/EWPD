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
<base target=_self>
<TITLE>Admin Options</TITLE>
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
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD>
				<!-- WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
					<h:inputHidden binding="#{adminOptionBackingBean.valuesFromSessionForBenefitCompPrint}"></h:inputHidden>
					<h:form styleClass="form" id="adminOptionForm">
			
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"> <DIV class="treeDiv"><jsp:include
								page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>

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
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Administration Option" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												onmousedown="return false;"
												action="#{adminMethodBackingBean.loadForBenefitComponentView}">
												<h:outputText value="Administration Process" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>

									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:510">

							<!--	Start of Table for actual Data	-->
								<div id="adminDivHidden" ><h:outputText 
										value="No Benefit Administration Option Available."
										styleClass="dataTableColumnHeader"/></div>
							<DIV id="searchResultdataTableDiv" >
								<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><b><h:outputText value="Associated Admin Options"></h:outputText></b>
											</TD>
										</TR>
									</TABLE>
									
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<div id="adminDiv" ><h:panelGrid
											id="headerPanelTable" columns="3" 
											styleClass="outputText" cellpadding="1" cellspacing="1"
											bgcolor="#cccccc"
											binding="#{adminOptionBackingBean.headerPanelForView}"
											rowClasses="dataTableColumnHeader">
										</h:panelGrid></div>
									<div id="paneltable" style="height:217px;position:relative;z-index:1; overflow:auto;"> <h:panelGrid
											id="panelTable" columns="3" 
											styleClass="outputText" cellpadding="1" cellspacing="1"
											bgcolor="#cccccc"
											binding="#{adminOptionBackingBean.panelForView}"
											rowClasses="dataTableEvenRow,dataTableOddRow">
										</h:panelGrid> 
									</div>
									</TD>
								</TR>
							</TABLE>
								
							</DIV>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->	
					<h:inputHidden value="#{adminOptionBackingBean.adminOptionExists}"
								id="adminOptionExists"></h:inputHidden>			
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
//initialize();
	/*	function initialize(){
			if(document.getElementById('adminOptionForm:searchResultTable') != null) {
				setColumnWidth('adminOptionForm:searchResultTable','35%:35%:30%');
			}else {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
			}
		}*/
if(document.getElementById('adminOptionForm:adminOptionExists').value == "true"){   
		 // 	alert('adminOptions present');
			
           document.getElementById('adminDiv').style.visibility = 'visible';
           document.getElementById('paneltable').style.visibility = 'visible';
           document.getElementById('resultHeaderDiv').style.visibility = 'visible';
           document.getElementById('adminDivHidden').style.visibility = 'hidden';
	    }
  		else{
			//  alert('show message - no admin options');	      
           document.getElementById('adminDiv').style.visibility = 'hidden';
           document.getElementById('paneltable').style.visibility = 'hidden';
           document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
           document.getElementById('adminDivHidden').style.visibility = 'visible';
    	}


			var relTblWidth = document.getElementById('adminOptionForm:paneltable').offsetWidth;
			setColumnWidth('adminOptionForm:paneltable','40%:20%:20%:20%');
	 		setColumnWidth('adminOptionForm:headerPanelTable', '40%:20%:20%:20%')
			if(document.getElementById('adminOptionForm:paneltable').rows.length >11){
			 document.getElementById('adminOptionForm:headerPanelTable').width = (relTblWidth-17) +"px";
			}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponentAdminOption" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</html>