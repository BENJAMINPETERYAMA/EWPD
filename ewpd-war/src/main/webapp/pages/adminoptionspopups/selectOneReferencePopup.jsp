<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/wpd.js"></script>
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 90%;
}

</style>
<TITLE>Reference Select</TITLE>
</HEAD>
<f:view>
	<BODY>
	<script language="JavaScript" src="../../js/tigra_tables.js"></script>

	<h:form id="referenceSelectPopupForm">
	<w:message/>
		<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
		
			<tr>
				<td align="left">&nbsp;<h:commandButton id="txtSelect"
					styleClass="wpdbutton" value="Select"
					onclick="getCheckedItems_ewpd('referenceSelectPopupForm:referenceSelectPopupTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>


		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					bgcolor="#cccccc">
					<tr>

						<TD align="center" valign="middle" width="10%"></TD>
						<TD align="center" width="90%"><strong><h:outputText
							value="Reference "></h:outputText></strong></TD>

					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>
				<DIV id="popupDataTableDiv" class="popupDataTableDiv"
					style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
				<h:dataTable id="referenceSelectPopupTable" cellspacing="1"
					width="100%" var="eachRow"
					value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
					cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn" >
					<h:column>
						
						<f:verbatim>
							<wpd:singleRowSelect groupName="reference" id="minor1"
								rendered="true"></wpd:singleRowSelect>
						</f:verbatim>
					</h:column>
					<h:column>
						
						<h:inputHidden id="hiddenReference" value="#{eachRow.description}"></h:inputHidden>
						<h:inputHidden id="hiddenReferenceCode"
							value="#{eachRow.primaryCode}"></h:inputHidden>
						<h:outputText value="#{eachRow.description}"></h:outputText>
					</h:column>
				</h:dataTable></DIV>
				</td>
			</tr>
		</table>
	</h:form>
	</BODY>

</f:view>
<script language="javascript">

	tigra_tables('referenceSelectPopupForm:referenceSelectPopupTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
 	
	matchCheckboxItems_ewpd('referenceSelectPopupForm:referenceSelectPopupTable', window.dialogArguments.selectedValues, 2, 2, 2);
	// setColumnWidth('referenceSelectPopupForm:referenceSelectPopupTable', '5:250');
	

</script>
</HTML>


