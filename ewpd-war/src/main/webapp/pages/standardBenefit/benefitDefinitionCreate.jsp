<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

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

<TITLE>Benefit Definition Create</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('benefitDefinitionCreateForm:saveAndAddButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>


			<TR>
				<TD><h:form styleClass="form" id="benefitDefinitionCreateForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>




							<TD width="273" valign="top" class="leftPanel">
							<!-- Space for Tree  Data	-->
							<DIV class="treeDiv">
							<jsp:include page="../standardBenefit/standardBenefitTree.jsp"></jsp:include>
							</DIV>



							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>

									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink id="thisId" 
												action="#{standardBenefitBackingBean.loadStandardBenefit}" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:outputText
												value="#{standardBenefitBackingBean.benefitTypeTab}" 
												/></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									
									<TD width="25%" id="noteTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
											<TD width="100%" class="tabNormal" align="center"><h:commandLink id="noteId"
												action="#{standardBenefitNotesBackingBean.loadStandardBenefitNotes}" 
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value=" Notes " />
											</h:commandLink></TD>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" width="4" height="21" /></td>
										</tr>
									</table>
									</TD>
									<TD width="100%"></TD>

								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

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


								<TR>
									<TD width="160" valign="top"><h:outputText
										id="effectiveDateOutText" value="Effective Date* "
										styleClass="#{benefitDefinitionBackingBean.requiredEffectiveDate ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="180" valign="top"><h:inputText 
										styleClass="selectDataDisplayDiv" size="12"
										id="effectiveDate_txt" maxlength="10" tabindex="1"
										value="#{benefitDefinitionBackingBean.effectiveDate}">
									</h:inputText><BR>
									<SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
									<TD width="20" align="left" valign="top"><A href="#"
										onclick="cal1.select('benefitDefinitionCreateForm:effectiveDate_txt','anchor3','MM/dd/yyyy');return false;"
										title="cal1.select(document.forms[0].benefitDefinitionCreateForm:effectiveDate_txt,'anchor3','MM/dd/yyyy'); 
														 return false;"
										name="anchor3" id="anchor3" tabindex="2"> <h:commandButton
										image="../../images/cal.gif" style="cursor: hand" alt="Cal" />
									</A></TD>


								</TR>
								<TR>
									<TD width="160" valign="top"><h:outputText
										id="expiryDateOutText" value="Expiry Date* "
										styleClass="#{benefitDefinitionBackingBean.requiredExpiryDate ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<TD width="180" valign="top"><h:inputText
										styleClass="selectDataDisplayDiv" size="12"
										id="expiryDate_txt" maxlength="10" tabindex="3"
										value="#{benefitDefinitionBackingBean.expiryDate}">
									</h:inputText><BR>
									<SPAN class="dateFormat">(mm/dd/yyyy)</SPAN></TD>
									<TD width="20" align="left" valign="top"><A href="#"
										onclick="cal1.select('benefitDefinitionCreateForm:expiryDate_txt','anchor4','MM/dd/yyyy');return false;"
										title="cal1.select(document.forms[0].benefitDefinitionCreateForm:expiryDate_txt,'anchor4','MM/dd/yyyy'); 
														 return false;"
										name="anchor4" id="anchor4" tabindex="4"> <h:commandButton
										image="../../images/cal.gif" style="cursor: hand" alt="Cal" />
									</A></TD>
								</TR>
								<TR>
									<TD width="160" valign="top"><h:outputText
										id="descriptionOutText" value="Description  ">
									</h:outputText></TD>

									<TD colspan="2"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{benefitDefinitionBackingBean.description}"
										onkeyup="return ismaxlength(this,500)" tabindex="5"></h:inputTextarea><BR>
									</TD>
								</TR>


								<TR>
									<TD width="46%"><h:outputText
										styleClass="mandatoryNormal"
										id="TierStar" value="Tier Definition" /></TD>
									<TD width="48%">
									<DIV id="TierDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtTier"
										value="#{benefitDefinitionBackingBean.tier}"></h:inputHidden>
									</TD>
									<TD width="8%"><h:commandButton
										alt="Select" id="TierButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="getTierDefinitionPopUp('../standardbenefitpopups/tierDefinitionPopup.jsp'+getUrl(),
										'TierDiv','benefitDefinitionCreateForm:txtTier',2,1);return false;"
										tabindex="12"></h:commandButton></TD>
								</TR>
							
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="benefitDefinitionCreateForm:txtDescription"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Definition Description"
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
									<TD width="110">
										<h:commandButton value="Save" id="saveAndAddButton"
											styleClass="wpdButton" onclick="unsavedDataFinder(this.id);return false;" tabindex="6">
										</h:commandButton>
										<h:commandLink id="saveButton"
											style="display:none; visibility: hidden;" action="#{benefitDefinitionBackingBean.saveAndAddBenefitDefinition}">
										</h:commandLink>

									</TD>
								</TR>
							</TABLE>
							<BR>
							<DIV>
							<TABLE width="100%" cellspacing="0" cellpadding="0" border="0">

								<TR>
									<TD>
									<DIV id="resultHeaderDiv" style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width = "100%">
										<TR>
											<TD width="711"><b><h:outputText value="Associated Benefit Definitions"></h:outputText></b>
											</TD>
										</TR>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="30%"><h:outputText
													value="Description"></h:outputText></TD>
												<TD align="left" width="15%"><h:outputText
													value="Effective Date "></h:outputText></TD>
												<TD align="left" width="15%"><h:outputText
													value="Expiry Date"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText
													value="Tier Definition"></h:outputText></TD>
												<TD width="8%">&nbsp;</TD>
												<TD width="12%"><h:outputText
													value="Delete"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:290px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc" style="width:100%"
										rendered="#{benefitDefinitionBackingBean.associatedBenefitDefinitionsList != null}"
										value="#{benefitDefinitionBackingBean.associatedBenefitDefinitionsList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
										<h:column>
											<h:inputHidden id="benefitDefinitionMasterId"
												value="#{singleValue.benefitDefinitionMasterKey}"></h:inputHidden>
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
										</h:column>
										<h:column>
											<h:outputText id="tierDefinitions"
												value="#{singleValue.tierDefinitions}">												
											</h:outputText>
										</h:column>
										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="Edit" id="editButton"
												image="../../images/edit.gif" tabindex="7"
												onclick="submitDataTableButton('benefitDefinitionCreateForm:searchResultTable', 'benefitDefinitionMasterId', 'benefitDefinitionCreateForm:targetHiddenToStoreMasterKey', 'benefitDefinitionCreateForm:editBenefitDefinition'); return false;"></h:commandButton>
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
											<%--<h:commandButton alt="Delete" id="deleteButton"
												image="../../images/delete.gif"
												onclick="confirmDeletion(); return false;"></h:commandButton>--%>
											<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox" tabindex="7"
													id="checkBox" onclick = 'javascript:validateDelete()'> 
                                        	</h:selectBooleanCheckbox>

										</h:column>
									</h:dataTable>										
									</DIV>
									</TD>
								</TR>

								<TR>
									<TD>
										<DIV id='deleteBenefitDefinition'>
											<h:commandButton id="delete" value="Delete" tabindex="8" 
												styleClass="wpdButton" onclick="unsavedDataFinder(this.id);return false;"> 
											</h:commandButton>
										</DIV>
									</TD>
								</TR>

							</TABLE>
							</DIV>
							<BR>
							</FIELDSET>
							<BR>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
								<TR>
									<TD width="15"><h:selectBooleanCheckbox id="checkall"
										tabindex="9" value="#{standardBenefitBackingBean.checkin}">
									</h:selectBooleanCheckbox></TD>
									<TD align="left" width="784"><h:outputText value="Check In" /></TD>
									<TD align="right" width="143">
									<TABLE width="100%">
										<TR>
											<td><h:outputText value="State" /></td>
											<TD><h:outputText value=":" /></TD>
											<td><h:outputText
												value="#{benefitDefinitionBackingBean.state}" /></td>
										</TR>
										<TR>
											<td><h:outputText value="Status" /></td>
											<TD><h:outputText value=":" /></TD>
											<td><h:outputText
												value="#{benefitDefinitionBackingBean.status}" /></td>
										</TR>
										<TR>
											<td><h:outputText value="Version" /></td>
											<TD><h:outputText value=":" /></TD>
											<td><h:outputText
												value="#{benefitDefinitionBackingBean.version}" /></td>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>
							<TABLE>
								<TBODY>
									<TR>
										<TD>&nbsp;<h:commandButton styleClass="wpdbutton" value="Done"
											tabindex="10"
											action="#{standardBenefitBackingBean.doneFromOtherTabs}">
										</h:commandButton></TD>
									</TR>
								</TBODY>
							</TABLE>
							<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
								id="hidden1" value="value1"></h:inputHidden> <h:inputHidden
								id="masterKeyUsedForUpdate"
								value="#{benefitDefinitionBackingBean.masterKeyUsedForUpdate}"></h:inputHidden>
							<h:inputHidden id="targetHiddenToStoreMasterKey"
								value="#{benefitDefinitionBackingBean.benefitDefinitionMasterKey}"></h:inputHidden>
							<h:inputHidden id="listSize"
								value="#{benefitDefinitionBackingBean.listSize}"></h:inputHidden>
							<h:inputHidden id="tabHidden"
								value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>
							<h:inputHidden id="targetHiddenToStoreMasterKeyForDelete" 
								value="#{benefitDefinitionBackingBean.benefitDefenitionsForDelete}"></h:inputHidden>
							<h:inputHidden id="targetHiddenToStoreAdminId" 
								value="#{benefitDefinitionBackingBean.benefitAdminId}"></h:inputHidden>
								<h:inputHidden id="duplicateData"
					value="#{benefitDefinitionBackingBean.duplicateData}"></h:inputHidden> 
							<h:inputHidden id="panelData"  />
							<h:inputHidden id="inputTextData"  />
							<h:commandLink id="editBenefitDefinition"
								style="display:none; visibility: hidden;"
								action="#{benefitDefinitionBackingBean.retrieveBenefitDefinition}">
								<f:verbatim />
							</h:commandLink> <h:commandLink id="deleteBenefitDefinition"
								style="display:none; visibility: hidden;"
								action="#{benefitDefinitionBackingBean.deleteBenefitDefinition}">
								<f:verbatim />
							</h:commandLink> <!-- End of hidden fields  --></TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script language="JavaScript">

copyHiddenToDiv_ewpd('benefitDefinitionCreateForm:txtTier','TierDiv',2,1); 

IGNORED_FIELD1='benefitDefinitionCreateForm:duplicateData';
IGNORED_FIELD2='benefitDefinitionCreateForm:panelData';
IGNORED_FIELD3='benefitDefinitionCreateForm:inputTextData';
	// Space for page realated scripts

		function syncTables(){
			var relTblWidth = document.getElementById('benefitDefinitionCreateForm:benefitDefinitionCreateForm').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth ;
		}
	function runSpellCheck(){
		var rswlCntrls = ["rapidSpellWebLauncher1"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
	return false;
	}
	function spellFin(cancelled){


			document.getElementById('benefitDefinitionCreateForm:saveButton').click();	

	}

	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
	if(document.getElementById('benefitDefinitionCreateForm:searchResultTable')!= null){
		document.getElementById('benefitDefinitionCreateForm:searchResultTable').onresize = syncTables;
		var size = document.getElementById('benefitDefinitionCreateForm:listSize').value;
		var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		if(size<=11){
				document.getElementById('benefitDefinitionCreateForm:searchResultTable').width = relTblWidth+"px";
				//setColumnWidth('benefitDefinitionCreateForm:searchResultTable','40%:25%:25%:10%');
				//setColumnWidth('resultHeaderTable','40%:25%:25%:10%');
				setColumnWidth('benefitDefinitionCreateForm:searchResultTable','30%:15%:15%:20%:8%:12%');
				setColumnWidth('resultHeaderTable','30%:15%:15%:20%:8%:12%');
		}else{
			syncTables();
			//setColumnWidth('resultHeaderTable','38%:24%:24%:10%');
			//setColumnWidth('benefitDefinitionCreateForm:searchResultTable','40%:25%:25%:10%');
			setColumnWidth('resultHeaderTable','30%:15%:15%:20%:8%:12%');
			setColumnWidth('benefitDefinitionCreateForm:searchResultTable','30%:15%:15%:20%:8%:12%');
		}
		// syncTables();
		//setColumnWidth('benefitDefinitionCreateForm:searchResultTable', '40%:25%:25%:10%');
	}
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitDefinitionCreateForm:searchResultTable:0:benefitDefinitionMasterId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('deleteBenefitDefinition').style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('deleteBenefitDefinition').style.visibility = 'visible';
			document.getElementById('benefitDefinitionCreateForm:delete').disabled = true;
		}
	}
	function confirmDelete(){
		var message = "Are you sure you want to delete?"
		var defnId = getSelectedDefnIds();
		if(defnId == ''){
			alert("Please select atleast one definition to delete.");	
			return false;
		}
		else{
			if(window.confirm(message)){
				//submitDataTableButton('benefitDefinitionCreateForm:searchResultTable', 'benefitDefinitionMasterId', 'benefitDefinitionCreateForm:targetHiddenToStoreMasterKey', 'benefitDefinitionCreateForm:deleteBenefitDefinition');
				document.getElementById('benefitDefinitionCreateForm:targetHiddenToStoreMasterKeyForDelete').value = defnId;
				document.getElementById('benefitDefinitionCreateForm:deleteBenefitDefinition').click();
			}
			else{
				return false;
			}
		}
	}

	// function to get the selected benefit defenitions for delete.
	function getSelectedDefnIds(){
		// variable declarations
		var tableObject = null;
		var checkBoxObject = null;
		var defnIds = null;
		var status = null;

		// get the table object
		tableObject = document.getElementById('benefitDefinitionCreateForm:searchResultTable');
		if(tableObject){
			defnIds = '';
			status = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[5].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					if(status){
						defnIds = defnIds + '~';
					}
					status = true;
					// get the defn id and append the ids
					defnIds = defnIds + tableObject.rows[i].cells[0].children[0].value;
				}
			}
		}
		// return the selected ids
		return defnIds;
	}

	// Enable or disable delete button
	function validateDelete(){
		// var declarations
		var tableObject = null;
		var isChecked = null;
		var checkBoxObject = null;

		// get the table object
		tableObject = document.getElementById('benefitDefinitionCreateForm:searchResultTable');
		if(tableObject){
			isChecked = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[5].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					isChecked = true;
					break;
				}
			}
			document.getElementById('benefitDefinitionCreateForm:delete').disabled = !isChecked;			
		}
	}

	hideTab();
	function hideTab(){
		var tab;
		tab = document.getElementById("benefitDefinitionCreateForm:tabHidden").value ;
		if(tab=="Standard Definition"){
			//manTab.style.display='none';
			noteTab.style.display='';
		}
		else{
			//manTab.style.display='';
			noteTab.style.display='none';
		}
	}

//checkForOnLoadData();

function checkForOnLoadData(){

	var tableObject = document.getElementById('benefitDefinitionCreateForm:searchResultTable');
	if(null != tableObject){
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('benefitDefinitionCreateForm:searchResultTable');
			document.getElementById('benefitDefinitionCreateForm:panelData').value = onLoadPanelData;
		}
	}
	var inputTexts = checkForTextData();
	document.getElementById('benefitDefinitionCreateForm:inputTextData').value = inputTexts;
}

function checkForTextData(){
	var effDate = document.getElementById('benefitDefinitionCreateForm:effectiveDate_txt').value; 
	var expDate = document.getElementById('benefitDefinitionCreateForm:expiryDate_txt').value; 
	var desc = document.getElementById('benefitDefinitionCreateForm:txtDescription').value; 
	var tier = document.getElementById('benefitDefinitionCreateForm:txtTier').value; 
	var inputTexts = effDate+expDate+desc+tier;
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
		if(buttonId == 'benefitDefinitionCreateForm:saveAndAddButton'){
			var tableObject = document.getElementById('benefitDefinitionCreateForm:searchResultTable');
			if(null != tableObject){
				var panelData = getPanelData('benefitDefinitionCreateForm:searchResultTable');
				if(document.getElementById('benefitDefinitionCreateForm:panelData').value != panelData){
					var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
					if (retValue){
							runSpellCheck();
							//submitLink('benefitDefinitionCreateForm:saveButton');	
						}
					}else{
						runSpellCheck();
						//submitLink('benefitDefinitionCreateForm:saveButton');
					}
				}else{
					runSpellCheck();
					//submitLink('benefitDefinitionCreateForm:saveButton');
				}
		}else if (buttonId == 'benefitDefinitionCreateForm:delete'){
			var textData = checkForTextData();
			if(document.getElementById('benefitDefinitionCreateForm:inputTextData').value != textData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
						if(confirmDelete()){
							//submitLink('benefitDefinitionCreateForm:deleteBenefitDefinition');	
						}
				}
			}else{
				if(confirmDelete()){
							//submitLink('benefitDefinitionCreateForm:deleteBenefitDefinition');	
				}
			}
		}
			
}	

if(document.getElementById('benefitDefinitionCreateForm:searchResultTable')!= null){

	 var tableObject = document.getElementById('benefitDefinitionCreateForm:searchResultTable');
     var rows = tableObject.rows.length;
     if(rows >0){
//		var columns = tableObject.rows[0].cells.length;
		//alert(columns)
		for(var i=0;i<rows;i++){
			if(null != tableObject.rows[i].cells[0].children[0]){
				var description =(tableObject.rows[i].cells[0].children[1].innerHTML).toString();
				var length = description.length;
				if(length>32){
					var desc = description.substring(0,32);
					tableObject.rows[i].cells[0].children[1].innerHTML=desc+'....';
				}
			}
		}
	}
}	

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="standardBenefitDefinition" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitDefinitionCreateForm:duplicateData').value == ''){
		document.getElementById('benefitDefinitionCreateForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForOnLoadData();
	}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitDefinitionCreateForm:duplicateData').value;
	}

	function getTierDefinitionPopUp(url, targetDiv, targetHidden, attrCount, attrPosn){
		ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
	}

</script>
</HTML>
