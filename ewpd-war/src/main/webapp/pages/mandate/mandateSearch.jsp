<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
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
<TITLE>Mandate Search</TITLE>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="javascript">
            var cal1 = new CalendarPopup();
            cal1.showYearNavigation();
	</script>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('MandateForm:locateButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy" value="#{searchMandateBackingBean.mandateId}"></h:inputHidden>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="MandateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --> <w:message
										value="#{searchMandateBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
						<!-- Table containing Tabs -->
						<div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable"
							style="position:relative; top:25px; left:5px; z-index:120;">
							<tr>
								<td width="200"></td>
								<td width="200"></td>
							</TR>
						</table>
						<!-- End of Tab table -->
						<div id="accordionTest" style="margin:5px;">
						<div id="searchPanel">
						<div id="searchPanelHeader" class="tabTitleBar"
							style="position:relative; cursor:hand;"><b>Locate Criteria</b></div>
						<div id="searchPanelContent" class="tabContentBox"
							style="position:relative;"><br />
						<!--  Start of Table for actual Data      -->
						<TABLE width="50%" border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="180"><h:outputText value="Citation Number" /></TD>
									<TD width="229">
									<DIV id="CitationNumDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtCitationNumber"
										value="#{searchMandateBackingBean.citationNumber}"></h:inputHidden></TD>
									<TD><h:commandButton alt="Select" title="Select" id="citationNumberButton"
										image="../../images/select.gif" style="cursor: hand"
										onclick="modalWindow11('../standardbenefitpopups/citationNumberPopup.jsp'+getUrl(),'CitationNumDiv','MandateForm:txtCitationNumber'); return false;"
										tabindex="3"></h:commandButton></TD>
								</TR>
								<TR>
									<TD width="180"><h:outputText value="Effective Date" /></TD>
									<TD width="229"><h:inputText styleClass="formInputField"
										id="txtEffectiveDate"
										value="#{searchMandateBackingBean.effectiveDate}" /> <span
										class="dateFormat">(mm/dd/yyyy)</span></td>
									<td align="left" valign="top" width="176"><A href="#"
										onclick="cal1.select('MandateForm:txtEffectiveDate','anchor1','MM/dd/yyyy'); return false;"
										name="anchor1" id="anchor1"
										title="cal1.select('MandateForm:txtEffectiveDate','anchor1','MM/dd/yyyy'); return false;">
									<h:commandButton image="../../images/cal.gif"
										style="cursor: hand" alt="Cal" tabindex="4" /> </A></td>
								</TR>
								<TR>
									<TD width="180"><h:outputText value="Expiry Date" /></TD>
									<TD width="229"><h:inputText styleClass="formInputField"
										id="txtExpiryDate"
										value="#{searchMandateBackingBean.expiryDate}" /> <span
										class="dateFormat">(mm/dd/yyyy)</span></td>
									<td align="left" valign="top" width="176"><A href="#"
										onclick="cal1.select('MandateForm:txtExpiryDate','expiryDate_cal','MM/dd/yyyy'); return false;"
										title="cal1.select(document.forms[0].MandateForm:txtExpiryDate,'expiryDate_cal','MM/dd/yyyy'); return false;"
										name="expiryDate_cal" id="expiryDate_cal"> <IMG
										src="../../images/cal.gif" alt="Cal" border="0"> </A></td>
								</TR>

								<TR>
									<TD width="180"><h:outputText value="Mandate Type" /></TD>
									<TD width="229"><h:selectOneMenu id="selectMandateType"
										styleClass="formInputField"
										value="#{searchMandateBackingBean.mandateType}">
										<f:selectItems
											value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
									</h:selectOneMenu></TD>

								</TR>

								<TR>
									<TD width="180"><h:outputText value="Jurisdiction" /></TD>
									<TD width="229">
									<DIV id="jurisdictionDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtJurisdiction"
										value="#{searchMandateBackingBean.jurisdiction}"></h:inputHidden></TD>
									<TD><h:commandButton alt="Select" title="Select" id="jurisdictionButton"
										image="../../images/select.gif" style="cursor: hand"
										onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/jurisdictionPopup.jsp'+getUrl(), 'jurisdictionDiv','MandateForm:txtJurisdiction',2,2);return false;"
										tabindex="3">
									</h:commandButton></TD>
								</TR>

								<TR>
									<TD width="180"><h:outputText value="Group Size" /></TD>
									<TD width="229"><h:selectOneMenu id="selectGroupSize"
										styleClass="formInputField"
										value="#{searchMandateBackingBean.groupSize}">
										<f:selectItems
											value="#{ReferenceDataBackingBeanCommon.groupSizeListForCombo}" />
									</h:selectOneMenu></TD>

								</TR>

								<TR>
									<TD width="180"><h:outputText value="Funding Arrangement" /></TD>
									<TD width="229"><h:selectOneMenu id="selectFundArgmnt"
										styleClass="formInputField"
										value="#{searchMandateBackingBean.fundingArrangement}">
										<f:selectItems
											value="#{ReferenceDataBackingBeanCommon.fundingArrangementListForCombo}" />
									</h:selectOneMenu></TD>

								</TR>

								<TR>
									<TD width="180"><h:commandButton value="Locate"
										styleClass="wpdButton"
										id="locateButton"
										action="#{searchMandateBackingBean.locateMandate}">
									</h:commandButton></TD>
									<TD width="229">&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</div>
						<div id="panel2">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative; cursor:hand;">Locate Results</div>
						<div id="panel2Content" class="tabContentBox"
							style="position:relative;font-size:10px;width:100%;"><br />
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td>
								<div id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
										<td align="left"><h:outputText
												value="Mandate Name"></h:outputText></td>
											<td align="left"><h:outputText
												value="Type"></h:outputText></td>
											<td align="left"><h:outputText
												value="Jurisdiction"></h:outputText></td>
											<td align="left"><h:outputText value="Group Size"></h:outputText></td>
											<td align="left"><h:outputText
												value="Funding Arrangement"></h:outputText></td>
											<td align="left"><h:outputText value="Version"></h:outputText></td>
											<td align="left"><h:outputText
												value="Effective Date"></h:outputText></td>
											<td align="left"><h:outputText
												value="Expiry Date"></h:outputText></td>
											<td align="left"><h:outputText
												value="Status"></h:outputText></td>
											<td align="left"><h:outputText value=""></h:outputText></td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								</td>
							</tr>
							<tr>
								<TD>
								<div id="searchResultdataTableDiv"
									style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									rowClasses="dataTableEvenRow,dataTableOddRow"
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="Mandate" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{searchMandateBackingBean.mandateSearchResultList != null}"
									value="#{searchMandateBackingBean.mandateSearchResultList}">
									<h:column>
										<h:outputText id="mandatename"
											value="#{Mandate.mandateName}"></h:outputText></h:column>
										<h:column><h:outputText id="mandateTypeOP"
											value="#{Mandate.mandateTypeDesc}"></h:outputText>
										<h:inputHidden id="hiddenMandateId"
											value="#{Mandate.mandateId}"></h:inputHidden>
										<h:inputHidden id="hiddenVersionNo"
											value="#{Mandate.version}"></h:inputHidden>
										<h:inputHidden id="hiddenMandateName"
											value="#{Mandate.mandateName}"></h:inputHidden>
									</h:column>
									<h:column>
										<h:outputText id="jurisdictionOP"
											value="#{Mandate.jurisdictionDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="groupSizeOP"
											value="#{Mandate.groupSizeDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="fundArranmentOP"
											value="#{Mandate.fundingArrangementDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="versionOP" value="#{Mandate.version}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="effectiveDateOP"
											value="#{Mandate.effectiveDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>

									</h:column>
									<h:column>
										<h:outputText id="expiryDateOP" value="#{Mandate.expiryDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="statusType" value="#{Mandate.status}">
									</h:outputText>
								</h:column>
									<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<h:commandButton alt="View" title="Select" id="viewButton"
											image="../../images/view.gif"
											rendered="#{Mandate.state.validForView}"
											onclick="viewAction(); return false;"></h:commandButton>
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="View All Versions" title="View All Versions" id="basicViewAll" 
											image="../../images/notes.gif" 
											rendered="#{Mandate.state.validForView}"
											onclick="viewAllAction(); return false;"></h:commandButton>
										
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="Copy" title="Copy" id="basicCopy"
											image="../../images/copy.gif" value="Copy"
											rendered="#{Mandate.state.validForCopy}"
											onclick="getValuesForCopy();return false;"></h:commandButton>
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="Edit" title="Edit" id="basicEdit"
											image="../../images/edit.gif" value="Edit"
											rendered="#{Mandate.state.editableByUser}"
											onclick="getValuesForEdit();return false;"></h:commandButton>
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="CheckOut" title="CheckOut" id="checkOut" 
											image="../../images/checkOut.gif" value="CheckOut"
											onclick="checkOutAction();return false;"
											rendered="#{Mandate.state.validForCheckOut}">
										</h:commandButton>
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="Send For Test" title="Send For Test" id="sendForTest" 
										rendered="#{Mandate.state.validForTest && Mandate.status != 'SCHEDULED_FOR_TEST'}"
											image="../../images/schedule_test.gif" value="Send For Test"
											onclick="sendForTest();return false;">
										</h:commandButton>
										<h:commandButton alt="Publish" title="Publish" id="publish" 
										rendered="#{Mandate.state.validForPublish}"
											image="../../images/publish.gif" value="Publish"
											onclick="publish();return false;">
										</h:commandButton>
										<h:commandButton alt="testPass" title="testPass"
											id="testPass" image="../../images/test_successful.gif" 
											value="testPass" onclick="getValuesForTestPass();return false;"
											rendered ="#{Mandate.status == 'SCHEDULED_FOR_TEST' ? true : false}">
										</h:commandButton>
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="testFail" title="testFail"
											id="testFail" image="../../images/test_failed.gif" 
											value="testFail" onclick="getValuesForTestFail();return false;"
											rendered ="#{Mandate.status == 'SCHEDULED_FOR_TEST' ? true : false}"></h:commandButton>
										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="Delete" title="Delete" id="basicDelete"
											image="../../images/delete.gif" value="Delete"
											rendered="#{Mandate.state.validForDelete}"
											onclick="getValuesForDelete();return false;"></h:commandButton>
									</h:column>

								</h:dataTable></div>
								</TD>
							</tr>
							<TR>
								<TD><h:inputHidden id="mandateId"
									value="#{searchMandateBackingBean.mandateId}" />
								</TD>
								<td><h:inputHidden id="mandateName"
									value="#{searchMandateBackingBean.mandateName}" />
								</td>
								<td><h:inputHidden id="versionNo"
									value="#{searchMandateBackingBean.versionNo}" />
								</td>
							</TR>
						</table>
						</div>
						</div>
						</div>
						</div>
						<!--  End of Page data  --></TD>
					</TR>
				</table>
				<!-- Space for hidden fields -->

				<h:inputHidden id="hiddenMandateIdForDelete"
					value="#{searchMandateBackingBean.mandateId}"></h:inputHidden>
				<h:inputHidden id="hiddenMandateNameForDelete"
					value="#{searchMandateBackingBean.mandateName}"></h:inputHidden>
				<h:inputHidden id="hiddenVersionNoForDelete"
					value="#{searchMandateBackingBean.versionNo}"></h:inputHidden>
				<h:commandLink id="deleteMandate"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.deleteMandate}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="hiddenMandateIdForEdit"
					value="#{mandateBackingBean.mandateId}"></h:inputHidden>
				<h:inputHidden id="hiddenMandateNameForEdit"
					value="#{mandateBackingBean.mandateName}"></h:inputHidden>
				<h:inputHidden id="hiddenVersionNoForEdit"
					value="#{mandateBackingBean.version}"></h:inputHidden>
				<h:commandLink id="editMandate"
					style="display:none; visibility: hidden;"
					action="#{mandateBackingBean.retrieveMandate}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="copyMandate"
					style="display:none; visibility: hidden;"
					action="#{mandateBackingBean.copyMandate}">
					<f:verbatim />
				</h:commandLink>

				<h:commandLink id="checkOutMandate"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.checkOutMandate}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="mandateSendForTest"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.sendForTest}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="mandatePublish"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.publish}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="mandateTestPass"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.testPass}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="mandateTestFail"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.testFail}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="searchLink"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.locateMandate}">
					<f:verbatim />
				</h:commandLink>

				<!-- End of hidden fields  -->
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script>
			copyHiddenToDiv_ewpd('MandateForm:txtJurisdiction','jurisdictionDiv',2,2);
			

			parseForDiv(document.getElementById('CitationNumDiv'), document.getElementById('MandateForm:txtCitationNumber')); 
            var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
            if(document.getElementById('MandateForm:searchResultTable') != null) {
            /*      tigra_tables('MandateForm:searchResultTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)'); */
                  setColumnWidth('MandateForm:searchResultTable','14%:6%:10%:10%:10%:6%:10%:8%:10%:16%');
                  setColumnWidth('resultHeaderTable','14%:6%:10%:10%:10%:6%:10%:8%:10%:16%');      
                  showResultsTab();
            }else {
                  var headerDiv = document.getElementById('resultHeaderDiv');
                  headerDiv.style.visibility = 'hidden';
                  var headerDiv2 = document.getElementById('searchResultdataTableDiv');
                  headerDiv2.visibility ='hidden';
            }     
            if(document.getElementById('MandateForm:searchResultTable') != null){
                  document.getElementById('MandateForm:searchResultTable').onresize = syncTables;
                  syncTables();
            }
            function syncTables(){
                  var relTblWidth = document.getElementById('MandateForm:searchResultTable').offsetWidth;
                  document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
            } 

			
			function copyValuesToHidden() {
				var e = window.event;
				var button_id = e.srcElement.id;
				var var1 = button_id.split(':');
				var rowcount = var1[2];
				var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
				var mandateIdValue = document.getElementById(mandateId).value;
				var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
				var mandateNameValue = document.getElementById(mandateName).value;
				var versionNo = "MandateForm:searchResultTable:"+rowcount+":hiddenVersionNo";
				var versionNoValue = document.getElementById(versionNo).value;	
				document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateIdValue;
				document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateNameValue;
				document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNoValue;
			}

			function getValuesForDelete() {
				var message = 'Are you sure you want to delete the selected mandate version?';
				if (confirm(message) ){
			   	copyValuesToHidden();
				document.getElementById('MandateForm:deleteMandate').click();
				return true;
				}
				return false;
			}

			function getValuesForEdit() {
			   	var e = window.event;
				var button_id = e.srcElement.id;
				var var1 = button_id.split(':');
				var rowcount = var1[2];
				var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
				var mandateIdValue = document.getElementById(mandateId).value;
				var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
				var mandateNameValue = document.getElementById(mandateName).value;
				var versionNo = "MandateForm:searchResultTable:"+rowcount+":hiddenVersionNo";
				var versionNoValue = document.getElementById(versionNo).value;	
				document.getElementById('MandateForm:hiddenMandateIdForEdit').value = mandateIdValue;
				document.getElementById('MandateForm:hiddenMandateNameForEdit').value = mandateNameValue;
				document.getElementById('MandateForm:hiddenVersionNoForEdit').value = versionNoValue;
				document.getElementById('MandateForm:editMandate').click();
				return false;
			}

		function viewAction(){
				var currentDate = new Date();
				var currentTime = currentDate.getTime();
			 	var e = window.event;
				var button_id = e.srcElement.id;
				var var1 = button_id.split(':');
				var rowcount = var1[2];
				var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
				var mandateIdValue = document.getElementById(mandateId).value;
				var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
				var mandateNameValue = document.getElementById(mandateName).value;
			 	var url = '../mandate/mandateView.jsp'+getUrl()+'?date='+currentTime+'&mandatekey='+mandateIdValue+'&&'+ 'mandatename='+mandateNameValue;
				newWinForView=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
		}

	function checkOutAction(){
				copyValuesToHidden();
				document.getElementById('MandateForm:checkOutMandate').click();
				return false;
	}

	function parentCheckout(mandateId,mandateName,mandateVersion){
				document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateId;
				document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateName;
				document.getElementById('MandateForm:hiddenVersionNoForDelete').value = mandateVersion;
				document.getElementById('MandateForm:checkOutMandate').click();
				return false;

	}
	function viewAllAction(){
		 	var e = window.event;
			var button_id = e.srcElement.id;
			var var1 = button_id.split(':');
			var rowcount = var1[2];
			var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
			var mandateIdValue = document.getElementById(mandateId).value;
			var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
			var mandateNameValue = document.getElementById(mandateName).value;

			getFromDataTableToHidden('MandateForm:searchResultTable','hiddenMandateId','MandateForm:mandateId');
			getFromDataTableToHidden('MandateForm:searchResultTable','hiddenMandateName','MandateForm:mandateName');
			getFromDataTableToHidden('MandateForm:searchResultTable','hiddenVersionNo','MandateForm:versionNo');
			var url = '../mandate/mandateViewAll.jsp'+getUrl()+'?mandatekey='+document.getElementById('MandateForm:mandateId').value+'&action=viewAll'+ '&'+'mandatename='+ document.getElementById('MandateForm:mandateName').value;
			var retValueFromVersion = window.showModalDialog(url, window, "dialogHeight:850px;dialogWidth:1050px;resizable=false;status=no");
			if(	retValueFromVersion == undefined)
						return true;
			else{
				var values = retValueFromVersion.split("~");
				if(values[0] == 'Copy'){
					parentCopy(values[1]);
				}else if(values[0] == 'Edit'){
					parentValuesForEdit(values[1],values[2],values[3])
				}else if(values[0] == 'CheckOut'){
					parentCheckout(values[1],values[2],values[3]);
				}else if(values[0] == 'search'){
					document.getElementById('MandateForm:hiddenMandateIdForEdit').value = values[1];
					document.getElementById('MandateForm:hiddenMandateNameForEdit').value = values[2];
					document.getElementById('MandateForm:searchLink').click();
				}
			}
	}
	 function parentValuesForEdit(mandateId,mandateName,versionNo) {
			

			document.getElementById('MandateForm:hiddenMandateIdForEdit').value = mandateId;
			document.getElementById('MandateForm:hiddenMandateNameForEdit').value = mandateName;
			document.getElementById('MandateForm:hiddenVersionNoForEdit').value = versionNo;
			document.getElementById('MandateForm:editMandate').click();
			return false;
       }
	function parentValuesForDelete(mandateId,mandateName,versionNo) {
			document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateId;
			document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateName;
			document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNo;
			document.getElementById('MandateForm:deleteMandate').click();
	return false;

	}
	function getValuesForCopy() {
			   	var e = window.event;
				var button_id = e.srcElement.id;
				var var1 = button_id.split(':');
				var rowcount = var1[2];
				var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
				var mandateIdValue = document.getElementById(mandateId).value;
				document.getElementById('MandateForm:hiddenMandateIdForEdit').value = mandateIdValue;
				document.getElementById('MandateForm:copyMandate').click();
				return false;
			}

	function parentCopy(mandateId){
		document.getElementById('MandateForm:hiddenMandateIdForEdit').value = mandateId;
		document.getElementById('MandateForm:copyMandate').click();
		return false;
	}

	function sendForTest(){
		var e = window.event;
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
		var mandateIdValue = document.getElementById(mandateId).value;
		var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
		var mandateNameValue = document.getElementById(mandateName).value;
		var versionNo = "MandateForm:searchResultTable:"+rowcount+":hiddenVersionNo";
		var versionNoValue = document.getElementById(versionNo).value;	
		document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateIdValue;
		document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateNameValue;
		document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNoValue;
		document.getElementById('MandateForm:mandateSendForTest').click();
		return false;
	}
	function publish(){
		var e = window.event;
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
		var mandateIdValue = document.getElementById(mandateId).value;
		var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
		var mandateNameValue = document.getElementById(mandateName).value;
		var versionNo = "MandateForm:searchResultTable:"+rowcount+":hiddenVersionNo";
		var versionNoValue = document.getElementById(versionNo).value;	
		document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateIdValue;
		document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateNameValue;
		document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNoValue;
		document.getElementById('MandateForm:mandatePublish').click();
		return false;
	}
	function getValuesForTestPass() {
		var e = window.event;
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
		var mandateIdValue = document.getElementById(mandateId).value;
		var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
		var mandateNameValue = document.getElementById(mandateName).value;
		var versionNo = "MandateForm:searchResultTable:"+rowcount+":hiddenVersionNo";
		var versionNoValue = document.getElementById(versionNo).value;	
		document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateIdValue;
		document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateNameValue;
		document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNoValue;
		document.getElementById('MandateForm:mandateTestPass').click();
		return false;
	}
	function getValuesForTestFail() {
		var e = window.event;
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		var mandateId = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateId";
		var mandateIdValue = document.getElementById(mandateId).value;
		var mandateName = "MandateForm:searchResultTable:"+rowcount+":hiddenMandateName";
		var mandateNameValue = document.getElementById(mandateName).value;
		var versionNo = "MandateForm:searchResultTable:"+rowcount+":hiddenVersionNo";
		var versionNoValue = document.getElementById(versionNo).value;	
		document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateIdValue;
		document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateNameValue;
		document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNoValue;
		document.getElementById('MandateForm:mandateTestFail').click();
		return false;
	}
	function parentPublish(mandateId,mandateName,versionNo) {
			document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateId;
			document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateName;
			document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNo;
			document.getElementById('MandateForm:mandatePublish').click();
	return false;

	}
	function parentTestPass(mandateId,mandateName,versionNo) {
			document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateId;
			document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateName;
			document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNo;
			document.getElementById('MandateForm:mandateTestPass').click();
	return false;

	}
	function parentTestFail(mandateId,mandateName,versionNo) {
			document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateId;
			document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateName;
			document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNo;
			document.getElementById('MandateForm:mandateTestFail').click();
	return false;

	}
	function parentSendForTest(mandateId,mandateName,versionNo) {
			document.getElementById('MandateForm:hiddenMandateIdForDelete').value = mandateId;
			document.getElementById('MandateForm:hiddenMandateNameForDelete').value = mandateName;
			document.getElementById('MandateForm:hiddenVersionNoForDelete').value = versionNo;
			document.getElementById('MandateForm:mandateSendForTest').click();
	return false;

	}


</script>
</HTML>

