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

<TITLE>ELIGIBILITY/BENEFIT_INFORMATION</TITLE>
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

	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><h:inputHidden id="dummy"
				value=" #{saveQuestionBackingBean.breadCrumpCreate}"></h:inputHidden>
			<jsp:include page="../navigation/top.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="createQuestionForm">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv" style="height:380px"><!-- Space for Tree  Data	--></DIV>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{saveQuestionBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
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
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Question" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<!-- <td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td class="tabNormal"> 
														<h:commandLink > <h:outputText value="Tab2"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</tr>
											</table>
										</td> -->
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>


								<TR>
									<TD colspan="3" height="39">
									<TABLE id="selectTable" border="0" cellspacing="2"
										cellpadding="0" class="outputText">
											<TR>
												<TD width="39%"><h:outputText id="lineOfBusiness"
													value="EB01 *"
													 />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="53%">
												<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="8%"><h:commandButton alt="lineOfBusiness"
													id="lineOfBusinessButton" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../template/EB01.jsp'+getUrl()+'?spsParameterType='+'ELIGIBILITY/BENEFIT_INFORMATION'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','createQuestionForm:lineOfBusinessHidden',2,2);
																				return false;"
													tabindex="1">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="39%"><h:outputText id="EB02"
													value="EB02 *"
													 />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden1"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="53%">
												<div id="lineOfBusinessDiv1" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="8%"><h:commandButton alt="lineOfBusiness"
													id="lineOfBusinessButton1" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../template/EB02.jsp'+getUrl()+'?spsParameterType='+'COVERAGE LEVEL CODE'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv1','createQuestionForm:lineOfBusinessHidden1',2,2);
																				return false;"
													tabindex="1">
												</h:commandButton></TD>
											</TR>
											<TR>
												<TD width="39%"><h:outputText id="SPSvalue"
													value="SPS value *"
													 />
												</TD>
												<h:inputHidden id="lineOfBusinessHidden2"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="53%">
												<div id="lineOfBusinessDiv2" class="selectDataDisplayDiv"></div>

												</TD>
												<TD width="8%"><h:commandButton alt="lineOfBusiness"
													id="lineOfBusinessButton2" image="../../images/select.gif"
													onclick="ewpdModalWindow_ewpd('../template/spsPopup.jsp'+getUrl()+'?spsParameterType='+'SERVICE TYPE CODE'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv2','createQuestionForm:lineOfBusinessHidden2',2,2);
																				return false;"
													tabindex="1">
												</h:commandButton></TD>
											</TR>

									</TABLE>
									</TD>
								</TR>

								<TR>
									<TD width="138">&nbsp;</TD>
								</TR>

							</TBODY>
						</TABLE>
						
						
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenAnswer"
					value="#{saveQuestionBackingBean.answer}"></h:inputHidden>
				<h:inputHidden id="hiddenAnswerForDelete"
					value="#{saveQuestionBackingBean.deletedAnswer}"></h:inputHidden>
				<h:inputHidden id="hiddenQuestionNumber"
					value="#{saveQuestionBackingBean.questionNumber}"></h:inputHidden>
				<h:inputHidden id="hiddenUpdateFlag"
					value="#{saveQuestionBackingBean.updateFlag}"></h:inputHidden>

				<h:commandLink id="deleteAnswer"
					action="#{saveQuestionBackingBean.deleteAnswer}">
					<f:verbatim />
				</h:commandLink>

				<h:inputHidden id="questionVersion"
					value="#{saveQuestionBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="questionStatus"
					value="#{saveQuestionBackingBean.status}"></h:inputHidden>
				<h:inputHidden id="dataTypeHidden"
					value="#{saveQuestionBackingBean.dataTypeId}"></h:inputHidden>
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
document.getElementById('createQuestionForm:questonText').focus(); // for on load default selection

	if(document.getElementById('createQuestionForm:answerTable') != null) {	
		tigra_tables('createQuestionForm:answerTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
		setColumnWidth('questionHeaderTable','90%:10%');
		setColumnWidth('createQuestionForm:answerTable','90%:10%');
	}
	else{
		var divObj = document.getElementById('associatedAnswersDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	}
	function addAnsToHidden(){
		document.getElementById("createQuestionForm:hiddenAnswer").value =document.getElementById("createQuestionForm:hiddenAnswer").value + '~' + document.getElementById("createQuestionForm:answerText").value ;
	}

	function getValuesForDelete() {
		if(window.confirm("Are you sure you want to delete?")){
		   	var e = window.event;
			var button_id = e.srcElement.id;
			var var1 = button_id.split(':');
			var rowcount = var1[2];
			var ansr = "createQuestionForm:answerTable:"+rowcount+":answer";
			var aswrVal = document.getElementById(ansr).innerText;
			document.getElementById('createQuestionForm:hiddenAnswerForDelete').value = rowcount;
			document.getElementById('createQuestionForm:deleteAnswer').click();
			return true;
		}else{
			return true;
		}
		
	}

	function confirmBeforeClear(){
		if(document.getElementById('createQuestionForm:answerTable') != null){
				var confirmForClear = confirm("This will result in clearing the answers list.");
				selectObj = document.getElementById('createQuestionForm:dataTypeName');
				oldObj = document.getElementById('createQuestionForm:dataTypeHidden');
				if(confirmForClear){
					oldObj.value = selectObj.options[selectObj.selectedIndex].value;
					document.getElementById('createQuestionForm:clearLink').click();
				}
				else{
					document.getElementById('createQuestionForm:dataTypeName').value = document.getElementById('createQuestionForm:dataTypeHidden').value;
				}
		}
	}

	function copyDataType(){
		document.getElementById('createQuestionForm:dataTypeHidden').value = document.getElementById('createQuestionForm:dataTypeName').value;
	}
	function runSpellCheck(){
		document.getElementById('createQuestionForm:dataTypeHidden').value = document.getElementById('createQuestionForm:dataTypeName').value;
		var rswlCntrls = ["rapidSpellWebLauncher1"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
		return false;
	}
	function spellFin(cancelled){
		if(cancelled)
			document.getElementById('createQuestionForm:create').click();	
	}
</script>
</HTML>
