<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contractpopups/TestDate.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<TITLE>Test Data</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js" ></script>
<script language="JavaScript" src="../../js/PopupWindow.js" ></script>
<script language="JavaScript" src="../../js/date.js" ></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="contractTestForm">
	<table >
	<tr><td colspan="3" align ="center"><B>Enter Test Data</B></td></tr>
			<tr height='50'>
				<td colspan="3" width ="683"><w:messageForPopup value ="#{contractBasicInfoBackingBean.validationMessages}"></w:messageForPopup></td>
			</tr>
			<TR valign="top">									
		<TD width="259">&nbsp;&nbsp;<h:outputText value="Test Effective Date*"  styleClass="#{contractBasicInfoBackingBean.testDateInvalid ? 'mandatoryError': 'mandatoryNormal'}" /> </TD>
		<TD width="201"><h:inputText styleClass="formInputField" id="startDate_txt" maxlength="10" tabindex = "5"
			value="#{contractBasicInfoBackingBean.testDate}"/> 
			<span class="dateFormat">(mm/dd/yyyy)</span></TD>
			<TD valign="top" width="223">
			<A href="#"
				onclick="cal1.select('contractTestForm:startDate_txt','anchor1','MM/dd/yyyy'); return false;"
				name="anchor1" id="anchor1" title="cal1.select('contractTestForm:startDate_txt','anchor1','MM/dd/yyyy'); return false;">
				<h:commandButton  image="../../images/cal.gif" style="cursor: hand"  alt="Cal" tabindex="6"/>
			</A>									
		</TD>
	 </TR>
	<TR>
											<TD width="259">
												&nbsp;&nbsp;<h:commandButton value="Save"  styleClass="wpdButton" tabindex="10" action="#{contractBasicInfoBackingBean.saveTestInfo}"> </h:commandButton>
											</TD>
											<TD width="201">&nbsp;</TD>
											<TD width="223"></TD>
										</TR>
	</table>
    </h:form>
	</BODY>
</f:view>
</HTML>
