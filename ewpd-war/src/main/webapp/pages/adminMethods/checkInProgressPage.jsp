<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
	<head>
		<title>Wellpoint Product Database</title>
		<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
			title="Style">
	</head>
	<f:view>
	<body>
		
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
    		<td width="22%"><img src="../../images/WPLogo.jpg" alt="WellPoint Logo" width="179" height="75" /></td>
     		<td width="78%" align="right" valign="top" class="topRightgraphic">
				&nbsp;  <span id="ApplicationName"> <img src="../../images/applicationName.jpg" alt="WellPoint Product Database System" width="314" height="31" border="0" /></span>
			</td>	
  		</tr>
	</table>
	</body>
	<h:form styleClass="form" id="checkInProgress">
		<h:inputHidden id="hidden2"
			value="#{adminMethodBackingBean.dummyHiddenVarForWaitPage}"></h:inputHidden>
	<table height="80%" align="center">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><span class="pagetitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The application is validating the Admin Methods selected in the Benefit Component, 
				<h:outputText  value="#{adminMethodBackingBean.crrntlyPrcssngBCmpnt}"></h:outputText> </span>
			</td>
			<td>
				<img src="../../images/wait.gif" alt="Please wait.." width="80" height="10" border="0" />
			</td>
		</tr>
		<tr>
			<td>
  				<span class="pagetitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please do not close this window.</span>
			</td>
		</tr>
		<tr height="60%">
			<td>&nbsp;</td>
		</tr>
	</table>
	<h:commandLink id="checkUpdate"  style="display:none; visibility: hidden;"
									 action="#{adminMethodBackingBean.checkForEntityCheckinUpdate}">
	</h:commandLink>
	</h:form>
	<%@ include file="../navigation/bottom.jsp" %>
	</f:view>
<script language="javascript">
	if(document.getElementById('checkInProgress:hidden2').value == 'CONTINUE'){
		setTimeout(submit,1000);
	}else if(document.getElementById('checkInProgress:hidden2').value == 'STOP'){
		alert('done');
	}


	function submit(){
		document.getElementById('checkInProgress:checkUpdate').click();
	}
</script>
</html>