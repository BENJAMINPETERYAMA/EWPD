<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
	<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
	<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
	<%@ taglib uri="/wpd.tld" prefix="w"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
	<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" title="Style">
	<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
	<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" title="Style">
	<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
	<BASE target="_self" />
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
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>
	<TITLE>Upload xls file</TITLE>
	<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
</HEAD>

<f:view>
<BODY>
	<h:form id="importpopupform" enctype="multipart/form-data">
		<div id="importInfo">
			<TABLE>
				<TBODY>
					<TR>
						<TD><w:message>
							</w:message></TD>
					</TR>
				</TBODY>
			</TABLE>
			<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<TR bgcolor="#cccccc" width="100%">
			<TD align="center" colspan="2">
								<strong><h:outputText value="Input path to Import Excel"></h:outputText></strong>
							</TD>
			</TR>
				<tr>
					<TD width="30%"><h:outputText id="file"
							value="Select Input File">
						</h:outputText></TD>
					<TD width="96%" align="left" height="13">
						<x:inputFileUpload id="uploadFile" styleClass="uploadbutton" 
						value="#{indicativeLayoutMappingBackingBean.upload}" size="40"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp; 
						<h:commandButton id="btnImportIndicativeSegment" value="Upload"
							styleClass="wpdbutton"  tabindex ="2" 
							onclick = "uploadFile();return false;"/>
					</td>
				</tr>
			</table>
		</div>
		<h:commandLink id="lnkUpload" style="display:none; visibility: hidden;"
			action="#{indicativeLayoutMappingBackingBean.indicativeSegmentImportOrCopyToProd}">
			<f:verbatim />
		</h:commandLink>
		
	</h:form>

	<iframe width="100%" height="300"
		style="display: none; visibility: hidden" name="targetIframe"
		id="targetIframe" onreadystatechange="checkIframStatus();"> </iframe>
	<div class="transparent" id="transparentDiv"></div>
	<div class="picDiv" id="loadingImageDiv">
		<table width="100%" height="100%">
			<tr>
				<td align="center"><img src="../../images/wait.gif"></td>
			</tr>
		</table>
	</div>
</BODY>
</f:view>
<script>
	enablePage();
	function uploadFile() {
		
		document.getElementById('importpopupform:lnkUpload').click();
		disablePage();
	}	
	
</script>
</HTML>