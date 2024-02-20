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
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 82%;
}
.valueDescriptionColumn {
	width: 70%;
}	
</style>

<TITLE>Term Popup</TITLE>
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
	<h:form id="termForm">
	<div id="fullInfo"></div>
	<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
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
				<!--  <table id="businessEntityTable" width="100%" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>-->
						
						<DIV id="popupTableDiv3" style="height:0px; overflow:auto;"> 
        <table id="businessEntityTable1" width="100%" cellpadding="0"
							cellspacing="0"  bgcolor="#cccccc">

		<tr id="tr3">
                  
								<td width="10%" align="center" valign="middle"><input name="checkbox" type="checkbox"
									id="checkall"
									onClick="checkAll_ewpd(this,'termForm:searchResultTable1');"></td>
								<td width="20%"></td>
								<td width="70%" align="left"><strong><h:outputText
									value="Benefit Term" styleClass="outputText"></h:outputText></strong></td>
							</tr>
                          
						</table>
			
</DIV>
				<!--  <table id="businessEntityTable" width="100%" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>-->
<DIV id="popupTableDiv4" style="height:0px; overflow:auto;" class=popupDataTableDiv> 
						<table id="businessEntityTable2"  width="100%" cellpadding="0"
							cellspacing="0" bgcolor="#cccccc">
							<tr id="tr4">
								<td  width="8%" align="center" valign="middle"><input name="checkbox" type="checkbox"
									id="checkall"
									onClick="checkAll_ewpd(this,'termForm:searchResultTable2');"></td>
								
								<td width="70%" align="left"><strong><h:outputText
									value="Benefit Term" styleClass="outputText"></h:outputText></strong></td>
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
									id="searchResultTable1" var="termValue" 
									cellpadding="0" width="100%" cellspacing="1"
									rendered="#{ReferenceDataBackingBeanCommon.renderFirstList}"
									value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
									bgcolor="#cccccc"
									columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn" >


									<h:column>
										
										 <h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
										id="parCheckBox" value="#{termValue.termCheckBx}" >
                                        </h:selectBooleanCheckbox> 


									</h:column>
									<h:column>
									
										<h:inputHidden id="hiddenTermValue"
											value="#{termValue.description}" />
										<h:inputHidden id="hiddenTermValueDesc"
											value="#{termValue.primaryCode}" />
										<h:outputText id="termValueId"
											value="#{termValue.primaryCode}" style="padding-left: 5px"></h:outputText>
						
					</h:column>
									<h:column>
										
										<h:outputText id="termValue"
											value="#{termValue.description}" style="padding-left: 5px"></h:outputText>
						
					</h:column>
								</h:dataTable></DIV>

								</td>
							</tr>
                    <tr id="tr2">
								<td colspan="2" width="100%">

								<DIV id="popupTableDiv2" style="height:253px; overflow:auto;" class=popupDataTableDiv>
<h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable2" var="termValue" 
									cellpadding="0" width="100%" cellspacing="1"
									rendered="#{!ReferenceDataBackingBeanCommon.renderFirstList}"
									value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
									bgcolor="#cccccc"
									columnClasses="selectOrOptionColumn,valueDescriptionColumn,shortDescriptionColumn" >


									<h:column>
									
										<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
											id="parCheckBox2" value="#{termValue}" >
                                        </h:selectBooleanCheckbox>

									</h:column>
									<h:column>
									
										<h:inputHidden id="hiddenTermValue2"
											value="#{termValue.description}" />
										<h:inputHidden id="hiddenTermValueDesc2"
											value="#{termValue.primaryCode}" />
                                        <h:outputText id="termValue2"
											value="#{termValue.description}" style="padding-left: 5px">
										</h:outputText>
						
					</h:column>
									<h:column>
										
										<h:outputText id="termValueId2"
											value="#{termValue.primaryCode}" style="padding-left: 5px"></h:outputText>
						
					</h:column>
								</h:dataTable></DIV>

								</td>
							</tr>
						</table>
                <h:inputHidden id="hiddensortorder"
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
                	<SCRIPT language="javascript">


		</SCRIPT>
				 </h:form>
	</BODY>
</f:view>
<script language="JavaScript">
document.getElementById('selectButtonId').disabled  = false;	

matchCheckboxItems_ewpd('termForm:searchResultTable1',window.dialogArguments.selectedValues,2,2,2);
matchCheckboxItems_ewpd('termForm:searchResultTable2',window.dialogArguments.selectedValues,2,2,2);
var sort = document.getElementById('termForm:hiddensortorder');
var TR1 = document.getElementById('tr1');
var TR2 = document.getElementById('tr2');
var TR3 = document.getElementById('tr3');
var TR4 = document.getElementById('tr4');
var div1 = document.getElementById('popupTableDiv1');
var div2 = document.getElementById('popupTableDiv2');
var div3 = document.getElementById('popupTableDiv3');
var div4 = document.getElementById('popupTableDiv4');
	
	
if(sort != null && sort.value == 'code'){
			tigra_tables('termForm:searchResultTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div2.style.visibility = 'hidden';
	TR2.style.visibility = 'hidden';
    TR4.style.visibility = 'hidden';
    div4.style.visibility = 'hidden';
    TR4.style.height = '1px';
	div2.style.height = '1px';
	TR2.style.height = '1px';
	div4.style.height = '1px';

}
else if(sort != null && sort.value == 'desc'){
            tigra_tables('termForm:searchResultTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	div1.style.visibility = 'hidden';
	TR1.style.visibility = 'hidden';
    TR3.style.visibility = 'hidden';
    div3.style.visibility = 'hidden';
	div1.style.height = '1px';
	TR1.style.height = '1px';
    TR3.style.height = '1px';
	div3.style.height = '1px';

}
function getSelected(){
if(sort != null && sort.value == 'code'){

getCheckedItems_ewpd('termForm:searchResultTable1',2);return false;
}
else if(sort != null && sort.value == 'desc'){

getCheckedItems_ewpd('termForm:searchResultTable2',2);return false;
}
}
</script>
</HTML>

