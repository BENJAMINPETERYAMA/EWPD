<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectCheckboxColumn {
      width: 10%;
      text-align: center;
      vertical-align: middle;
}

.descriptionColumn {
      width: 70%;
}
</style>
<TITLE>Select Tier</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
<link rel="stylesheet" href="../../css/wpd.css">
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
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
		<h:form id="tierForm">
			<div id="fullInfo"></div>
				<h:inputHidden value="#{benefitDefinitionBackingBean.tierLookupRecords}"></h:inputHidden> 
				<table border="0" cellpadding="5" cellspacing="0" width="100%">
					<tr>
		               	<td align="left">&nbsp;<input type="button" class="wpdbutton" id="selectButtonId" disabled="disabled"
							name="action" value="Select"
							onClick="getSelected();return false;" />
						</td>
					</tr>
				</table>
				<table width="98%" border="0" align="right" cellpadding="0"
					cellspacing="0" >	
					<tr>
						<td>
						<DIV id="popupTableDiv4" style="height:0px; overflow:auto;" class=popupDataTableDiv> 
							<table id="businessEntityTable2"  width="100%" cellpadding="0"
								cellspacing="0" bgcolor="#cccccc">
								<tr id="tr4">
									<td  width="8%" align="center" valign="middle"><input name="checkbox" type="checkbox"
										id="checkall"
										onClick="checkAll_ewpd(this,'tierForm:searchResultTable');"></td>
									
									<td width="70%" align="center"><strong><h:outputText
										value="Tier Definitions" styleClass="outputText"></h:outputText></strong></td>
								</tr>
	                            
							</table>
							</DIV>
						</td>
					</tr>			
					<tr id="tr1">
						<td colspan="2" width="100%">
							<DIV id="popupTableDiv1" style=" height:253px;overflow:auto;" class=popupDataTableDiv>
								<h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="tierValue" 
									cellpadding="0" width="100%" cellspacing="1"
									value="#{benefitDefinitionBackingBean.tierLookUpList}"
									bgcolor="#cccccc" columnClasses="selectCheckboxColumn,descriptionColumn">

									<h:column>
										
										<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
										id="tierCheckBox" value="#{tierValue.tierCheckBx}" >
                                        </h:selectBooleanCheckbox>
									</h:column>		
									<h:column>
										
										<h:inputHidden id="hiddenTierValue"
											value="#{tierValue.tierDescription}" />
										<h:inputHidden id="hiddenTierValueDesc"
											value="#{tierValue.tierDefnSysId}" />										
										<h:outputText id="tierValue"
											value="#{tierValue.tierDescription}" style="padding-left: 5px"></h:outputText>						
									</h:column>
								</h:dataTable>
							</DIV>
						</td>
					</tr>                    
				</table>  
			 </h:form>
			</BODY>
		</f:view>

<script language="JavaScript">	

document.getElementById('selectButtonId').disabled  = false;

matchCheckboxItems_ewpd('tierForm:searchResultTable',window.dialogArguments.selectedValues,2,1,1);

tigra_tables('tierForm:searchResultTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');

function getSelected(){	
	getCheckedItems_ewpd('tierForm:searchResultTable',2);return false;	
}
</script>
</HTML>

