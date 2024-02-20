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
				<TD><h:form styleClass="form" id="adminOptionForm">
				<h:inputHidden binding="#{adminOptionBackingBean.valuesFromSessionForBenefitCompPrint}"></h:inputHidden>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="#{benefitComponentBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>						
<TR>

							<TD colspan="2" valign="top" class="ContentArea">
							
							<!-- Table containing Tabs -->
							
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
								<div id="adminDivHidden" align="center" ><h:outputText
										value="No Benefit Administration Option Available."
										styleClass="dataTableColumnHeader" /></div>
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
											id="headerPanelTable" columns="3" width="900"
											styleClass="outputText" cellpadding="1" cellspacing="1"
											bgcolor="#cccccc"
											binding="#{adminOptionBackingBean.headerPanelForView}"
											rowClasses="dataTableColumnHeader">
										</h:panelGrid></div>
									<div id="paneltable" > <h:panelGrid
											id="panelTable" columns="3" width="900"
											styleClass="outputText" cellpadding="1" cellspacing="1"
											bgcolor="#cccccc"
											binding="#{adminOptionBackingBean.panelForView}"
											rowClasses="dataTableOddRow">
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
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
if(document.getElementById('adminOptionForm:panelTable') != null){   
            if(document.getElementById('adminOptionForm:panelTable').rows.length != 0){
                document.getElementById('adminDiv').style.visibility = 'visible';
                document.getElementById('paneltable').style.visibility = 'visible';
                document.getElementById('resultHeaderDiv').style.visibility = 'visible';
                document.getElementById('adminDivHidden').style.visibility = 'hidden';

		    }
		    else{
                document.getElementById('adminDiv').style.visibility = 'hidden';
                document.getElementById('paneltable').style.visibility = 'hidden';
                document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
                document.getElementById('adminDivHidden').style.visibility = 'visible';

   		 }
}
window.print();
</script>

</html>