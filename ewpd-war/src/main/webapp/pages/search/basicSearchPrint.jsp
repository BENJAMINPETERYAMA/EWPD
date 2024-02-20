<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform" CONTENT="NO-CACHE" >
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

<TITLE>Search Engine</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:12px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="Search >> Basic Search >> Print">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR><TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message /></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="2">
								<TR >
									<TD >&nbsp;<h:outputText id="nameLabel"
										value="Identifier *" styleClass="#{basicSearchBackingBean.searchCriteriaEntered?'mandatoryNormal':'mandatoryError'}"/>
									</TD>
									<TD colspan="2" style="width:200px">
									<h:outputText id="criteria" value="#{basicSearchBackingBean.searchCriteria}"> </h:outputText>
									<br>
									</TD>
									
								</TR>
								<TR>
									<TD><BR>&nbsp;<h:outputText id="searchFor"
										value="Search For *" />
									</TD>
								</TR>
									<TR>
									<TD>&nbsp;<h:outputText id="align"
										value=" " />
									</TD>
									<TD><h:panelGrid id="panelTable"
								 	binding="#{basicSearchBackingBean.printPanel}"  >    
										</h:panelGrid> </TD>
									<td colspan="2">&nbsp;</td>
								</TR>
								<TR>
									<TD>&nbsp;<h:outputText id="limitedTo" value=" Limited To" />
									</TD>
									<TD><h:outputText id="lobLabel"
										value="Line of Business " />
									</TD>
									<TD>
									<h:outputText id="lob" value="#{basicSearchBackingBean.lobPrintString}"> </h:outputText>
								    </TD>
								</TR>
								<TR>
									<TD>&nbsp;</TD>
									<TD><h:outputText id="businessEntityLabel"
										value="Business Entity "
										 />
									</TD>
									<TD>
									<h:outputText id="be" value="#{basicSearchBackingBean.businessEntityPrintString}"> </h:outputText>
									</TD>
								</TR>
								<TR>
									<TD>&nbsp;</TD>
									<TD><h:outputText id="businessGroupLabel"
										value="Business Group "
										/></TD>
									<TD>
								      <h:outputText id="bg" value="#{basicSearchBackingBean.businessGroupPrintString}"> </h:outputText>
									</TD>
								</TR>
								<TR>
									<TD>&nbsp;</TD>
									<TD><h:outputText id="marketBusinessUnitLabel"
										value="Market Business Unit "
										/></TD>
									<TD>
								      <h:outputText id="mbu" value="#{basicSearchBackingBean.marketBusinessUnitPrintString}"> </h:outputText>
									</TD>
								</TR>

								<TR>
									<TD>&nbsp;</TD>
									<td colspan="3">&nbsp;</td>
								</TR>
						</TABLE>
						</fieldset>
						<!--	End of Page data	--> <BR>



						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
			
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	
	</BODY>
</f:view>

<script language="JavaScript">
	

	

	function showit(e){
		crossobj=document.getElementById("postit");
		var temp = document.getElementById("benefitComponentCreateForm:nameTxt");
		crossobj.style.display="block";
		crossobj.style.visibility="visible"
	}

	function closeit(){
		crossobj=document.getElementById("postit");
		crossobj.style.visibility="hidden"
		crossobj.style.display="none";
	}
window.print();
</script>

<script type="text/javascript">



</script>

</HTML>
