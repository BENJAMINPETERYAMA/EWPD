<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="X-UA-Compatible" content="IE=edge" />
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 20%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>
<TITLE>Business Group Popup</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<h:form id="businessGroupForm">
	<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<h:inputHidden value="#{ReferenceDataBackingBeanCommon.refDataLookupRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
		
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton"  disabled="true" 
					value="Select" styleClass="wpdbutton"
					onclick="getSelected();return false;"></h:commandButton>
				</td>
			</tr>
		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
			     <tr> 
			         <td align="center"><b>Business Group</b></td>
			     </tr>
			 </table>
			
                  <%-- <TR>
					<TD>
                    <DIV id="popupDataTableDiv3" style="height:0px; overflow:auto;"> 
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR id="tr3">
							<TD width="10%" align="center" valign="middle"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'businessGroupForm:businessGroupDataTable1'); ">
							</h:selectBooleanCheckbox></TD>
                            <td width="20%"></td>
							<TD align="left" width="70%"><strong><h:outputText
								value="Business Group"></h:outputText></strong></TD>
						</TR>
					</table>
                    </DIV>
                    <DIV id="popupDataTableDiv4" style="height:0px; overflow:auto;"> 
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR id="tr4">
							<TD width="10%" align="center" valign="middle"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'businessGroupForm:businessGroupDataTable2'); ">
							</h:selectBooleanCheckbox></TD>
                            <td width="20%"></td>
							<TD align="left" width="70%"><strong><h:outputText
								value="Business Group"></h:outputText></strong></TD>
						</TR>
					</table>
                    </DIV>
					</TD>
				</TR> --%>
				<tr id="tr1">
					<td>
					<DIV id="popupDataTableDiv1" style="height:253px; overflow:auto;" onclick="disableCheckBox()" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"
						cellspacing="1" width="100%" id="businessGroupDataTable1"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}" 
                        rendered="#{ReferenceDataBackingBeanCommon.renderFirstList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<h:selectBooleanCheckbox id="businessGroupChkBox">
							</h:selectBooleanCheckbox>

						</h:column>
				        <h:column>
                             <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
                             <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                             <h:inputHidden value="#{eachRow.parentCatalogId}"></h:inputHidden>
							 <f:verbatim>&nbsp;</f:verbatim>
                             <h:outputText value="#{eachRow.primaryCode}"></h:outputText>
                        </h:column>
					   <h:column>
							<f:verbatim>&nbsp;</f:verbatim>
						 	<h:outputText value="#{eachRow. description }"></h:outputText>
					
					</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
             <tr id="tr2">
					<td>
					<DIV id="popupDataTableDiv2" style="height:253px; overflow:auto;" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn"
						cellspacing="1" width="100%" id="businessGroupDataTable2"
						value="#{ReferenceDataBackingBeanCommon.refDataLookUpList}"
                        rendered="#{!ReferenceDataBackingBeanCommon.renderFirstList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<h:selectBooleanCheckbox id="businessGroupChkBox2">
							</h:selectBooleanCheckbox>

						</h:column>
				        <h:column>
                            <h:inputHidden value="#{eachRow.description}"></h:inputHidden>
                            <h:inputHidden value="#{eachRow.primaryCode}"></h:inputHidden>
                            <h:inputHidden value="#{eachRow.parentCatalogId}"></h:inputHidden>
							<f:verbatim>&nbsp;</f:verbatim>
                            <h:outputText value="#{eachRow. description }"></h:outputText>
                        </h:column>
					    <h:column>
							<f:verbatim>&nbsp;</f:verbatim>
                        	<h:outputText value="#{eachRow.primaryCode}"></h:outputText>
										
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
		</table>
        <h:inputHidden id="hiddensortorder"   
										value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">

function disableCheckBox(){
	var el =document.getElementById("popupDataTableDiv1");
	
	var tops = el.getElementsByTagName("input");
	var groupNumber;
	var elementID;
	 var arrayGroup=[];
	 var count = 0;
	 var longDescriptionColumn = el.getElementsByClassName('longDescriptionColumn');
	 for(var j=0; j<longDescriptionColumn.length;j++){
		var innerArray =[];
		innerArray.push(longDescriptionColumn.item(j).textContent);
		elementID = 'businessGroupForm:businessGroupDataTable1:'+j+':businessGroupChkBox';
		innerArray.push(document.getElementById(elementID).checked);
		if(innerArray[1] == false)
			count = count+1;
		if(innerArray[0].trim()=='LG and SG COMBINED - ABF ONLY'){
			groupNumber = j;
			if(innerArray[1]==true){
				disableRest(groupNumber);
			}
			else{
				disableOne(groupNumber);
			}
		}
		arrayGroup.push(innerArray);
	 }
	 if(count == longDescriptionColumn.length)
		 enableAll();
  }
  function enableAll(){
	 var el =document.getElementById("popupDataTableDiv1");
	 var longDescriptionColumn = el.getElementsByClassName('longDescriptionColumn');
	 for(var j=0; j<longDescriptionColumn.length;j++){
		elementID = 'businessGroupForm:businessGroupDataTable1:'+j+':businessGroupChkBox';
		document.getElementById(elementID).disabled = false;
		}
  }
  function disableOne(groupId){
	  var el =document.getElementById("popupDataTableDiv1");
	 var longDescriptionColumn = el.getElementsByClassName('longDescriptionColumn');
	 for(var j=0; j<longDescriptionColumn.length;j++){
		elementID = 'businessGroupForm:businessGroupDataTable1:'+j+':businessGroupChkBox';
		document.getElementById(elementID).disabled = false;
		}
		elementID = 'businessGroupForm:businessGroupDataTable1:'+groupId+':businessGroupChkBox';
		document.getElementById(elementID).disabled = true;
		document.getElementById(elementID).checked = false;
  }
  function disableRest(groupId){
	 var el =document.getElementById("popupDataTableDiv1");
	 var longDescriptionColumn = el.getElementsByClassName('longDescriptionColumn');
	 for(var j=0; j<longDescriptionColumn.length;j++){
		elementID = 'businessGroupForm:businessGroupDataTable1:'+j+':businessGroupChkBox';
		document.getElementById(elementID).disabled = true;
		document.getElementById(elementID).checked = false;
		}
		elementID = 'businessGroupForm:businessGroupDataTable1:'+groupId+':businessGroupChkBox';
		document.getElementById(elementID).disabled = false;
		document.getElementById(elementID).checked = true;
	 }



	document.getElementById('businessGroupForm:selectButton').disabled  = false; 
	
    matchCheckboxItems_ewpd('businessGroupForm:businessGroupDataTable1',window.dialogArguments.selectedValues,2,2,2);
    matchCheckboxItems_ewpd('businessGroupForm:businessGroupDataTable2',window.dialogArguments.selectedValues,2,2,2);
	var sort = document.getElementById('businessGroupForm:hiddensortorder');
	var TR1 = document.getElementById('tr1');
	var TR2 = document.getElementById('tr2');
	var TR3 = document.getElementById('tr3');
	var TR4 = document.getElementById('tr4');
	var div1 = document.getElementById('popupDataTableDiv1');
	var div2 = document.getElementById('popupDataTableDiv2');
	var div3 = document.getElementById('popupDataTableDiv3');
	var div4 = document.getElementById('popupDataTableDiv4');
		
		
	if(sort != null && sort.value == 'code'){
		tigra_tables('businessGroupForm:businessGroupDataTable1',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
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
	    tigra_tables('businessGroupForm:businessGroupDataTable2',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
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
	
	getCheckedItems_ewpd('businessGroupForm:businessGroupDataTable1',2);return false;
	}
	else if(sort != null && sort.value == 'desc'){
	
	getCheckedItems_ewpd('businessEntityForm:businessGroupDataTable2',2);return false;
	}
	}
</script>
</HTML>

