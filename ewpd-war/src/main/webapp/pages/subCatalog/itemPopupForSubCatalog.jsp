<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectCheckboxColumn {
      width: 8%;  
}

.descriptionColumn {
     width: 10%; 
}
.anotherDescriptionColumn {
	width: 82%; 
}	
</style>	

<TITLE>Item Popup</TITLE>
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
	<BODY><hx:scriptCollector id="scriptCollector1">
	<h:form id="itemSubCatalogForm">
	<h:inputHidden value="#{subCatalogBackingBean.loadItemPopUpList}" />
		<h:outputText value="No Items Available." rendered="#{subCatalogBackingBean.itemList == null}" styleClass="dataTableColumnHeader" />
		<DIV id="itemAssociationDiv">
		<TABLE border="0" cellpadding="5" cellspacing="0" width="100%">
			<TR>
				<TD align="left"><h:commandButton id="selectButton" value="Select" styleClass="wpdbutton" onclick="getCheckedItems_ewpd('itemSubCatalogForm:itemSelectPopupTable',2);return false;"></h:commandButton>
				</TD>
			</TR>
		</TABLE>
		<TABLE width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" id="headerTable" bgcolor="#cccccc">
						<TR>
							<TD align="center"width="4%">
								<h:selectBooleanCheckbox onclick="checkAll_ewpd(this,'itemSubCatalogForm:itemSelectPopupTable'); ">
								</h:selectBooleanCheckbox>
							</TD>
							<TD align="center" width="96%"><STRONG> <h:outputText value="Item">
							</h:outputText> </STRONG></TD>
						</TR>
					</TABLE>


				</TD></TR><TR>
					<TD>
					<DIV id="popupDataTableDiv" class="popupDataTableDiv">
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1" width="100%" 
					id="itemSelectPopupTable" var="itemName" rendered="#{subCatalogBackingBean.itemList != null}" 
					value="#{subCatalogBackingBean.itemList}" cellpadding="0" bgcolor="#cccccc" columnClasses="selectCheckboxColumn,descriptionColumn,anotherDescriptionColumn">
						<h:column>
							
								<h:selectBooleanCheckbox id="businessEntityChkBox">
								</h:selectBooleanCheckbox>
						</h:column>
						<h:column>
								<h:inputHidden value="#{itemName.description}" />
								<h:inputHidden value="#{itemName.primaryCode}" />

								
								<h:outputText value="#{itemName.primaryCode}"></h:outputText>
						</h:column>

							<h:column>
										 
									<h:outputText value="#{itemName.description}"></h:outputText>
							</h:column>
						</h:dataTable></DIV></TD>
				</TR>

			</TBODY>
		</TABLE>
	</DIV>
	</h:form>
	</hx:scriptCollector></BODY>
</f:view>

<script language="javascript">
		initialize();
		function initialize(){
			if(document.getElementById('itemSubCatalogForm:itemSelectPopupTable') != null) {
				setColumnWidth('itemSubCatalogForm:itemSelectPopupTable', '20:450');
				setColumnWidth('headerTable', '20:450');
			}else {
				document.getElementById("itemAssociationDiv").style.visibility = 'hidden';
			}
		}
		window.opener = window.dialogArguments.parentWindow;
		var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
		if(hiddenObj == null || hiddenObj == undefined) {
			alert("Hidden field not available");
		}
		if(hiddenObj.value) {
		
			matchCheckboxItems_ewpd('itemSubCatalogForm:itemSelectPopupTable', window.dialogArguments.selectedValues, 2, 2, 2);
		
		}

</script>
</HTML>
