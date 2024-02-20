<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

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

<TITLE>Benefit Administration</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>

		<tr>
			<td><h:form styleClass="form" id="benefitAdminForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><jsp:include
							page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --> <w:message
										value="#{benefitAdministrationBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{benefitLevelBackingBean.loadBenefitLevelFromBenefitAdministrationTab}"
											id="linkToBenefitLevel" onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="Benefit Level" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Benefit Administration" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;
							width:100%">

							<script type="text/javascript">
								function RSCustomInterface(tbElementName){
									this.tbName = tbElementName;
									this.getText = getText;
									this.setText = setText;
							
								function getText(){
									if(!document.getElementById(this.tbName)) {
										alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
										return '';
									} else return document.getElementById(this.tbName).value;
								}
								function setText(text){
									if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
								}
							}
							</script>

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="110" valign="top"><h:outputText value="Effective Date*"
										styleClass="#{benefitAdministrationBean.requiredEffectiveDate ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
									<TD valign="top" width="180"><h:inputText styleClass="formInputField"
										id="effectiveDate_txt"
										value="#{benefitAdministrationBean.effectiveDate}"
										tabindex="1" /> <span class="dateFormat">(mm/dd/yyyy)</span></TD>
									<TD align="left" valign="top" width="304"><a href="#"
										onclick="cal1.select('benefitAdminForm:EffectiveDate_txt','Eff_date_icon','MM/dd/yyyy'); return false;"
										title="cal1.select(document.forms[0].benefitAdminForm:EffectiveDate_txt,'Eff_date_icon','MM/dd/yyyy'); return false;"
										name="Eff_date_icon" id="Eff_date_icon" tabindex="2"> <h:graphicImage
										style="border:0" value="../../images/cal.gif" alt="Cal"></h:graphicImage>
									</a></td>
								</TR>
								<TR>
									<TD width="110" valign="top"><h:outputText value="Expiry Date*"
										styleClass="#{benefitAdministrationBean.requiredExpiryDate ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
									<TD valign="top" width="180"><h:inputText styleClass="formInputField"
										id="expiryDate_txt"
										value="#{benefitAdministrationBean.expiryDate}" tabindex="3" />
									<span class="dateFormat">(mm/dd/yyyy)</span></TD>
									<TD align="left" valign="top" width="304"><a href="#"
										onclick="cal1.select('benefitAdminForm:ExpiryDate_txt','Exp_date_icon','MM/dd/yyyy'); return false;"
										title="cal1.select(document.forms[0].benefitAdminForm:ExpiryDate_txt,'Exp_date_icon','MM/dd/yyyy'); return false;"
										name="Exp_date_icon" id="Exp_date_icon" tabindex="4"> <h:graphicImage
										style="border:0" value="../../images/cal.gif" alt="Cal">
									</h:graphicImage> </a></td>
								</TR>
								<TR>
									<TD width="110" valign="top"><h:outputText value="Description"
										styleClass="#{benefitAdministrationBean.requiredDescription ? 'mandatoryError': 'mandatoryNormal'}" /></TD>
									<TD colspan="2"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{benefitAdministrationBean.description}"
										onkeyup="return ismaxlength(this,500)" tabindex="5"></h:inputTextarea><BR>
									</TD>

								</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="benefitAdminForm:txtDescription"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Administration Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
								<TR>
									<TD width="110"><BR>
										<h:commandButton value="Save" id="saveAndAddButton"
											styleClass="wpdButton" onclick="unsavedDataFinder(this.id);return false;" tabindex="6">
										</h:commandButton>
										<h:commandLink id="saveButton"
											style="display:none; visibility: hidden;" action="#{benefitAdministrationBean.saveAndAddBenefitAdministration}">
										</h:commandLink>
									</TD>
								<!-- 	<TD width="181"><BR>
									<h:commandButton value="Save" styleClass="wpdButton"
										action="#{benefitAdministrationBean.saveBenefitAdministration}"
										tabindex="7">
									</h:commandButton></TD>-->
									<TD width="180">&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--> <br />
						<br />

						<div id="panel2">
						<TABLE width="100%" id="displayadmin" cellspacing="0" cellpadding="0" border="0">

							<TR>
								<TD>
								<div id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTableMain" border="0" width="100%">
									<tr>
										<TD><b> <h:outputText
											value="Associated Benefit Administrations"></h:outputText> </b>
										</TD>
									</tr>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="40%"><h:outputText
												value="Description"></h:outputText></td>
											<td align="left" width="20%"><h:outputText
												value="Effective Date"></h:outputText></td>
											<td align="left" width="20%"><h:outputText
												value="Expiry Date"></h:outputText></td>
											<td width="8%">&nbsp;</td>
											<td width="12%"><h:outputText
												value="Delete"></h:outputText></td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								</TD>
							</TR>
							<tr>
								<td>
								<div id="panel2Content"
									style="height:290px;width:100%;position:relative;z-index:1;font-size:10px; overflow:auto;">

								<h:dataTable styleClass="outputText"
									headerClass="dataTableHeader" id="resultsTable"
									var="singleValue" cellpadding="3" width="100%" cellspacing="1"
									bgcolor="#cccccc"
									rendered="#{benefitAdministrationBean.benefitAdministrationList != null}"
									value="#{benefitAdministrationBean.benefitAdministrationList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
									<h:column>
										<h:outputText id="description" 
											value="#{singleValue.description}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="effectiveDate"
											value="#{singleValue.effectiveDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="expiryDate"
											value="#{singleValue.expiryDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
										<h:inputHidden id="benefitAdministrationKey"
											value="#{singleValue.benefitAdministrationKey}"></h:inputHidden>
									</h:column>



									<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<h:commandButton alt="Edit" id="editButton"
											image="../../images/edit.gif" tabindex="7"
											onclick="editAction(); return false;" ></h:commandButton>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<%--<h:commandButton alt="Delete" id="deleteButton"
											image="../../images/delete.gif"
											onclick="confirmDeletion(); return false;"></h:commandButton>
										<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
												id="checkBox" value="">
                                    	</h:selectBooleanCheckbox>--%>
									</h:column>
									<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>										
										<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox" tabindex="7"
												id="checkBox" value="" onclick = 'javascript:validateDelete();' >
                                    	</h:selectBooleanCheckbox>
									</h:column>
								</h:dataTable></div>
								</TD>
							</TR>

							<TR>
								<TD>
									<DIV id='deleteBenefitAdministrations'>
										<h:commandButton id="delete" value="Delete" tabindex="8" 
											styleClass="wpdButton" onclick = "unsavedDataFinder(this.id);return false;"> 
										</h:commandButton>
									</DIV>
								</TD>
							</TR>
						</TABLE>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenBenefitAdministrationKey"
					value="#{benefitAdministrationBean.benefitAdministrationKey}"></h:inputHidden>

				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<tr>
					<h:inputHidden id="selectedBenefitAdministrationKey"
						value="#{benefitAdministrationBean.selectedBenefitAdministrationKey}"></h:inputHidden>
					<h:inputHidden id="targetHiddenToStoreMasterKeyForDelete" 
						value="#{benefitAdministrationBean.benefitAdminstrationsForDelete}"></h:inputHidden> 
					<h:inputHidden id="duplicateData"
					value="#{benefitAdministrationBean.duplicateData}"></h:inputHidden> 
					<h:commandLink id="deleteBenefitAdministrations"
						action="#{benefitAdministrationBean.deleteBenefitAdministration}"
						style="hidden">
						<f:verbatim> &nbsp;&nbsp;</f:verbatim>
					</h:commandLink>
					<h:commandLink id="editButton1"
						action="#{benefitAdministrationBean.editBenefitAdministration}"
						style="hidden">
						<f:verbatim> &nbsp;&nbsp;</f:verbatim>
					</h:commandLink>
					<h:inputHidden id="panelData"  />
					<h:inputHidden id="inputTextData"  />
				</tr>

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
IGNORED_FIELD1 ='benefitAdminForm:duplicateData';
IGNORED_FIELD2 ='benefitAdminForm:panelData';
IGNORED_FIELD3 ='benefitAdminForm:inputTextData';
		var cal1 = new CalendarPopup();
		cal1.showYearNavigation();

		function syncTables(){
			var relTblWidth = document.getElementById('benefitAdminForm:resultsTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth ;
		}
		function runSpellCheck(){
			var rswlCntrls = ["rapidSpellWebLauncher1"];
			var i=0;
			for(var i=0; i<rswlCntrls.length; i++){
				eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
			}
		//return false;
		}

		function spellFin(cancelled){
			
				document.getElementById('benefitAdminForm:saveButton').click();	
			
		}

initialize();
function initialize(){
		var tableObject = document.getElementById('benefitAdminForm:resultsTable');
		if(tableObject != null) {
			var rowlength = document.getElementById('benefitAdminForm:resultsTable').rows.length;
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(rowlength<=11){
				document.getElementById('benefitAdminForm:resultsTable').width = relTblWidth+"px";
				//setColumnWidth('benefitAdminForm:resultsTable','25%:25%:25%:25%');
				//setColumnWidth('resultHeaderTable','25%:25%:25%:25%');
				setColumnWidth('benefitAdminForm:resultsTable','40%:20%:20%:8%:12%');
				setColumnWidth('resultHeaderTable','40%:20%:20%:8%:12%');
			}else{
				syncTables();
				//setColumnWidth('resultHeaderTable','25%:25%:25%:25%');
				//setColumnWidth('benefitAdminForm:resultsTable','25%:25%:25%:25%');	
				setColumnWidth('resultHeaderTable','40%:20%:20%:8%:12%');
				setColumnWidth('benefitAdminForm:resultsTable','40%:20%:20%:8%:12%');	
			}	
		}
}
		function editAction(){
			 getFromDataTableToHidden('benefitAdminForm:resultsTable','benefitAdministrationKey','benefitAdminForm:selectedBenefitAdministrationKey');
			 submitLink('benefitAdminForm:editButton1');
		}
		
		
		function confirmDelete(){
			var message = "Are you sure you want to delete?"
			var admnId = getSelectedAdmnIds();
			if(admnId == ''){
				alert("Please select atleast one administration to delete.");	
				return false;
			}
			else
			{
				if(window.confirm(message)){
					//submitDataTableButton('benefitAdminForm:resultsTable','benefitAdministrationKey','benefitAdminForm:selectedBenefitAdministrationKey', 'benefitAdminForm:deleteButton1');
						document.getElementById('benefitAdminForm:targetHiddenToStoreMasterKeyForDelete').value = admnId;
						document.getElementById('benefitAdminForm:deleteBenefitAdministrations').click();
				}
				else{
					return false;
				}
			}
		}

		// function to get the selected benefit defenitions for delete.
		function getSelectedAdmnIds(){
			// variable declarations
			var tableObject = null;
			var checkBoxObject = null;
			var admnIds = null;
			var status = null;
	
			// get the table object
			tableObject = document.getElementById('benefitAdminForm:resultsTable');
			if(tableObject){
				admnIds = '';
				status = false;
				// iterate the rows
				for(var i = 0; i < tableObject.rows.length; i++){
					// check whether the checkbox is checked
					checkBoxObject = tableObject.rows[i].cells[4].children[0];
					if(checkBoxObject && checkBoxObject.checked){
						if(status){
							admnIds = admnIds + '~';
						}
						status = true;
						// get the administration id and append the ids
						admnIds = admnIds + tableObject.rows[i].cells[2].children[1].value;
					}
				}
			}
			// return the selected ids
			return admnIds;
		}

		// Enable or Disable Delete Button
		function validateDelete(){
			// variable declarations
			var tableObject = null;
			var checkBoxObject = null;
			var status = null;
	
			// get the table object
			tableObject = document.getElementById('benefitAdminForm:resultsTable');
			if(tableObject){
				status = false;
				// iterate the rows
				for(var i = 0; i < tableObject.rows.length; i++){
					// check whether the checkbox is checked
					checkBoxObject = tableObject.rows[i].cells[4].children[0];
					if(checkBoxObject && checkBoxObject.checked){
						status = true;
						break;
					}
				}
				document.getElementById('benefitAdminForm:delete').disabled = !status;
			}
		}

		hideIfNoValue('resultHeaderDiv');
		function hideIfNoValue(divId){
			hiddenIdObj = document.getElementById('benefitAdminForm:resultsTable:0:benefitAdministrationKey');
			if(hiddenIdObj == null){
				document.getElementById(divId).style.visibility = 'hidden';
				document.getElementById('deleteBenefitAdministrations').style.visibility = 'hidden';
			}else{
				document.getElementById(divId).style.visibility = 'visible';
				document.getElementById('deleteBenefitAdministrations').style.visibility = 'visible';
				document.getElementById('benefitAdminForm:delete').disabled = true;
			}
		}

		if(document.getElementById('benefitAdminForm:resultsTable')!= null){
			//setColumnWidth('benefitAdminForm:resultsTable', '25%:25%:25%:25%');
			setColumnWidth('benefitAdminForm:resultsTable', '40%:20%:20%:8%:12%');
			//syncTables('resultHeaderTable','benefitAdminForm:resultsTable');
		}


//checkForOnLoadData();

function checkForOnLoadData(){

	var tableObject = document.getElementById('benefitAdminForm:resultsTable');
	if(null != tableObject){
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('benefitAdminForm:resultsTable');
			document.getElementById('benefitAdminForm:panelData').value = onLoadPanelData;
		}
	}
	
	var inputTexts = checkForTextData();
	document.getElementById('benefitAdminForm:inputTextData').value = inputTexts;
}

function checkForTextData(){
	var effDate = document.getElementById('benefitAdminForm:effectiveDate_txt').value; 
	var expDate = document.getElementById('benefitAdminForm:expiryDate_txt').value; 
	var desc = document.getElementById('benefitAdminForm:txtDescription').value; 

	var inputTexts = effDate+expDate+desc;
	return inputTexts;

}
function getPanelData(list){
      var tagNames = list.split(',');
      var dataOnScreen = "";
      var tableObject = document.getElementById(tagNames[0]);
      var rows = tableObject.rows.length;
      if(rows >0){
            var columns = tableObject.rows[0].cells.length;
            for(var i=0;i<rows;i++){
                  for(var j=0;j<columns;j++){
                        if(null != tableObject.rows[i].cells[j].children[0]){
                              if(tableObject.rows[i].cells[j].children[0].type == 'checkbox'){
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].checked);
                              }else{
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML);       
                              }
                        }
                  }
            }
            return dataOnScreen;
      }else{
            return dataOnScreen;
      }
}


function unsavedDataFinder(objectId){
	var buttonId = objectId;
	if(buttonId == 'benefitAdminForm:saveAndAddButton'){
		var tableObject = document.getElementById('benefitAdminForm:resultsTable');
		if(null != tableObject){
			var panelData = getPanelData('benefitAdminForm:resultsTable');
				if(document.getElementById('benefitAdminForm:panelData').value != panelData){
					var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
					if (retValue){
						runSpellCheck();
						//submitLink('benefitAdminForm:saveButton');	
					}
				}else{
					runSpellCheck();
					//submitLink('benefitAdminForm:saveButton');
				}

			}else{
				runSpellCheck();
				//submitLink('benefitAdminForm:saveButton');
			}

		
	}else if (buttonId == 'benefitAdminForm:delete'){
			var textData = checkForTextData();
			if(document.getElementById('benefitAdminForm:inputTextData').value != textData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
						confirmDelete();
				}
			}else{
				confirmDelete();
			}
		}
			
}		
				
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitAdministration" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitAdminForm:duplicateData').value == ''){
		document.getElementById('benefitAdminForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForOnLoadData();
	}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitAdminForm:duplicateData').value;
		//checkForOnLoadData();
	}

</script>
</HTML>
