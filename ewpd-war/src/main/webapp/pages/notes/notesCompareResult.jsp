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
<script language="JavaScript">

</script>
<TITLE>Compare Notes</TITLE>
</HEAD>
<f:view>
	<BODY>

	<h:form styleClass="form" id="notesCompareResultForm">
		<h:inputHidden id="typeList"
			value="#{ReferenceDataBackingBeanCommon.noteTypeListForCombo}"></h:inputHidden>
		<h:inputHidden id="loadPage"
			value="#{notesCompareBackingBean.loadCompareResults}"></h:inputHidden>
		<TABLE width="100%" border="0" cellpadding="5" cellspacing="0"
			class="breadcrumb">
			<TBODY>
				<tr valign="bottom">
					<TD>&nbsp;<h:outputText id="breadCrumb1"
						value="#{requestScope.breadCrumbText}">
					</h:outputText></TD>
				</tr>
			</TBODY>
		</TABLE>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">

			<tr>
				<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD valign="top" class="ContentArea" height="450">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>


						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200"></td>
								<td width="25%"></td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<table width="100%">
							<tr>
								<td width="45%">
								<FIELDSET><LEGEND>Base Note</LEGEND>
								<TABLE border="0" cellspacing="0" cellpadding="3"
									class="outputText">
									<TBODY>

										<TR>
											<TD width="169"><h:outputText value="ID " /></TD>
											<TD width="220"><h:outputText id="noteId"
												value="#{notesCompareBackingBean.noteId}" /></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText value="Name " styleClass="" />
											</TD>
											<TD width="220"><h:outputText id="noteName"
												value="#{notesCompareBackingBean.name}" /></TD>
										</TR>

										<TR>
											<TD width="169"><h:outputText id="Type" value="Type "
												styleClass="" /></TD>
											<TD width="220"><h:outputText id="noteType"
												value="#{notesCompareBackingBean.type}" /></TD>
										</TR>


										<TR>
											<TD width="169" valign="top" height="120"><h:outputText
												value="Text " styleClass="" /></TD>
											<TD width="220" valign="top" height="120">
											<DIV id="textDiv"
												style="overflow-y:scroll;overflow-x:hidden;"
												class="formTxtAreaField_NoteCompare"></DIV>
											<h:inputHidden id="textHidden"
												value="#{notesCompareBackingBean.text}"></h:inputHidden></TD>
										</TR>

										<TR>
											<TD width="169"><h:outputText value="Target Systems" /></TD>
											<TD width="220">
											<DIV id="systemDomainDiv"
												class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="txtSystemDomain"
												value="#{notesCompareBackingBean.systemDomain}"></h:inputHidden></TD>
										</TR>

										<TR>
											<TD width="169"><h:outputText id="productLabel"
												value="Product" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="productDiv" class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="productTxtHidden"
												value="#{notesCompareBackingBean.product}"></h:inputHidden></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText id="componentLabel"
												value="Benefit Component" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="componentDiv" class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="componentTxtHidden"
												value="#{notesCompareBackingBean.benefitComponent}"></h:inputHidden></TD>

										</TR>
										<TR>
											<TD width="169"><h:outputText id="benefitLabel"
												value="Benefit" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="benefitDiv" class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="benefitTxtHidden"
												value="#{notesCompareBackingBean.benefit}"></h:inputHidden></TD>

										</TR>
										<TR>
											<TD width="169"><h:outputText id="questionLabel"
												value="Question" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="questionDiv" class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="questionTxtHidden"
												value="#{notesCompareBackingBean.question}"></h:inputHidden></TD>
 
										</TR>
										<TR>
											<TD width="169"><h:outputText id="termLabel" value="Term"
												styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="termDiv" class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="termTxtHidden"
												value="#{notesCompareBackingBean.term}"></h:inputHidden></TD>

										</TR>
										<TR>
											<TD width="169"><h:outputText id="qualifierLabel"
												value="Qualifier" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="qualifierDiv" class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="qualifierTxtHidden"
												value="#{notesCompareBackingBean.qualifier}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText value="Last Updated Date" /></TD>
											<TD width="220">
											<DIV id="lastUpdatedDiv"></DIV>
											<h:inputHidden id="lastUpdatedHidden"
												value="#{notesCompareBackingBean.lastUpdatedTimestamp} #{requestScope.timezone}"></h:inputHidden></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText value="Status " /></TD>
											<TD width="220"><h:outputText id="noteStatus"
												value="#{notesCompareBackingBean.status}" /></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText value="Version " styleClass="" />
											</TD>
											<TD width="220"><h:outputText id="noteVersion"
												value="#{notesCompareBackingBean.version}" /></TD>
										</TR>


									</TBODY>
								</TABLE>
								</FIELDSET>
								</td>
								<td width="45%">
								<FIELDSET><LEGEND>Note</LEGEND>
								<TABLE border="0" cellspacing="0" cellpadding="3"
									class="outputText">
									<TBODY>

										<TR>
											<TD width="169"><h:outputText value="ID" /></TD>
											<TD width="220">
											<DIV id="noteIdCompDiv"></DIV>
											<h:inputHidden id="noteIdComp"
												value="#{notesCompareBackingBean.noteIdCompare}" /></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText value="Name " styleClass="" />
											</TD>
											<TD width="220">
											<DIV id="noteNameCompDiv"></DIV>
											<h:inputHidden id="noteNameComp"
												value="#{notesCompareBackingBean.nameCompare}" /></TD>
										</TR>

										<TR>
											<TD width="169"><h:outputText id="TypeComp" value="Type "
												styleClass="" /></TD>
											<TD width="220">
											<DIV id="typeDivComp"></DIV>
											<h:inputHidden id="typeCompHidden"
												value="#{notesCompareBackingBean.typeCompare}"></h:inputHidden>
											</TD>
										</TR>

										<TR>
											<TD width="169" valign="top" height="120"><h:outputText
												value="Text " styleClass="" /></TD>
											<TD width="220" valign="top" height="120">
											<DIV id="textCompDiv"
												style="overflow-y:scroll;overflow-x:hidden;"
												class="formTxtAreaField_NoteCompare"></DIV>
											<h:inputHidden id="textComphidden"
												value="#{notesCompareBackingBean.textCompare}"></h:inputHidden>
											</TD>
										</TR>

										<TR>
											<TD width="169"><h:outputText value="Target Systems" /></TD>
											<TD width="220">
											<DIV id="systemDomainDivComp"
												class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="txtSystemDomainComp"
												value="#{notesCompareBackingBean.systemDomainCompare}"></h:inputHidden></TD>
										</TR>

										<TR>
											<TD width="169"><h:outputText id="productLabelComp"
												value="Product" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="productDivComp"
												class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="productTxtHiddenComp"
												value="#{notesCompareBackingBean.productCompare}"></h:inputHidden></TD>

										</TR>
										<TR>
											<TD width="169"><h:outputText id="componentLabelComp"
												value="Benefit Component" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="componentDivComp"
												class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="componentTxtHiddenComp"
												value="#{notesCompareBackingBean.benefitComponentCompare}"></h:inputHidden></TD>

										</TR>
										<TR>
											<TD width="169"><h:outputText id="benefitLabelComp"
												value="Benefit" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="benefitDivComp"
												class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="benefitTxtHiddenComp"
												value="#{notesCompareBackingBean.benefitCompare}"></h:inputHidden></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText id="questionLabelComp"
												value="Question" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="questionDivComp"
												class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="questionTxtHiddenComp"
												value="#{notesCompareBackingBean.questionCompare}"></h:inputHidden></TD>
										</TR> 
										<TR>
											<TD width="169"><h:outputText id="termLabelComp"
												value="Term" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="termDivComp" class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="termTxtHiddenComp"
												value="#{notesCompareBackingBean.termCompare}"></h:inputHidden></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText id="qualifierLabelComp"
												value="Qualifier" styleClass="mandatoryNormal" /></TD>
											<TD width="220">
											<DIV id="qualifierDivComp"
												class="formInputFieldForNotesCompare"></DIV>
											<h:inputHidden id="qualifierTxtHiddenComp"
												value="#{notesCompareBackingBean.qualifierCompare}"></h:inputHidden>
											</TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText value="Last Updated Date" /></TD>
											<TD width="220">
											<DIV id="lastUpdatedCompDiv"></DIV>
											<h:inputHidden id="lastUpdatecompdHidden"
												value="#{notesCompareBackingBean.lastUpdatedTimestampCompare} #{requestScope.timezone}"></h:inputHidden></TD>
										</TR>
										<TR>
											<TD width="169"><h:outputText value="Status" /></TD>
											<TD width="220">
											<DIV id="noteStatusCompDiv"></DIV>
											<h:inputHidden id="noteStatusComp"
												value="#{notesCompareBackingBean.statusCompare}" /></TD>
										</TR>
										<TR id="row1" style="display:none;">
											<TD width="169"><h:outputText value="Version " styleClass="" />
											</TD>
											<TD width="220"><STRONG><h:outputText style='BACKGROUND-COLOR: yellow' id="noteVersionComp"
												value="#{notesCompareBackingBean.versionCompare}" /></STRONG></TD>
										</TR>
										<TR id="row2" style="display:none;">
											<TD width="169"><h:outputText value="Version " styleClass="" />
											</TD>
											<TD width="220"><h:outputText id="noteVersionCompare"
												value="#{notesCompareBackingBean.versionCompare}" /></TD>
										</TR>
									</TBODY>
								</TABLE>
								</FIELDSET>
								</td>

							</tr>
						</table>
						<h:inputHidden id="versionCompareFlag"
								value="#{notesCompareBackingBean.versionCompareFlag}"></h:inputHidden>
						<!--	End of Page data	--></fieldset>
						<br>

						</TD>
					</TR>
				</table>
				</td>
			</tr>
		</table>


	</h:form>
	</BODY>
</f:view>

<script language="JavaScript">
	copyHiddenToDiv('notesCompareResultForm:productTxtHidden','productDiv');	
	copyHiddenToDiv('notesCompareResultForm:termTxtHidden','termDiv');	
	copyHiddenToDiv('notesCompareResultForm:benefitTxtHidden','benefitDiv');	
	copyHiddenToDiv('notesCompareResultForm:questionTxtHidden','questionDiv');	
	copyHiddenToDiv('notesCompareResultForm:qualifierTxtHidden','qualifierDiv');	
	copyHiddenToDiv('notesCompareResultForm:componentTxtHidden','componentDiv');
	copyHiddenToDiv('notesCompareResultForm:txtSystemDomain','systemDomainDiv'); 
	copyHiddenToDivLocal('notesCompareResultForm:lastUpdatedHidden','lastUpdatedDiv'); 
	copyWithFont('textDiv','notesCompareResultForm:textHidden');	

	adjustHieght('productDiv');
	adjustHieght('termDiv');
	adjustHieght('benefitDiv');
	adjustHieght('questionDiv');
	adjustHieght('qualifierDiv');
	adjustHieght('componentDiv');
	adjustHieght('systemDomainDiv');
	
	copyHiddenToDiv('notesCompareResultForm:productTxtHiddenComp','productDivComp');	
	copyHiddenToDiv('notesCompareResultForm:termTxtHiddenComp','termDivComp');	
	copyHiddenToDiv('notesCompareResultForm:benefitTxtHiddenComp','benefitDivComp');	
	copyHiddenToDiv('notesCompareResultForm:questionTxtHiddenComp','questionDivComp');	
	copyHiddenToDiv('notesCompareResultForm:qualifierTxtHiddenComp','qualifierDivComp');	
	copyHiddenToDiv('notesCompareResultForm:componentTxtHiddenComp','componentDivComp');
	copyHiddenToDiv('notesCompareResultForm:componentTxtHiddenComp','componentDivComp');
	copyHiddenToDiv('notesCompareResultForm:txtSystemDomainComp','systemDomainDivComp');

	adjustHieght('productDivComp');
	adjustHieght('termDivComp');
	adjustHieght('benefitDivComp');
	adjustHieght('questionDivComp');
	adjustHieght('qualifierDivComp');
	adjustHieght('componentDivComp');
	adjustHieght('systemDomainDivComp');
	

	function copyWithFont(div,hidden){		
	divObj = document.getElementById(div);
	hiddenObj = document.getElementById(hidden);
	divObj.innerHTML = hiddenObj.value+" "; 
	}
	copyWithFont('noteIdCompDiv','notesCompareResultForm:noteIdComp');
	copyWithFont('noteNameCompDiv','notesCompareResultForm:noteNameComp');
	copyWithFont('textCompDiv','notesCompareResultForm:textComphidden');
	copyWithFont('typeDivComp','notesCompareResultForm:typeCompHidden');
	copyWithFont('lastUpdatedCompDiv','notesCompareResultForm:lastUpdatecompdHidden');
	copyWithFont('noteStatusCompDiv','notesCompareResultForm:noteStatusComp');
	copyWithFontForList('productDivComp','notesCompareResultForm:productTxtHiddenComp');
	copyWithFontForList('componentDivComp','notesCompareResultForm:componentTxtHiddenComp');
	copyWithFontForList('benefitDivComp','notesCompareResultForm:benefitTxtHiddenComp');
	copyWithFontForList('questionDivComp','notesCompareResultForm:questionTxtHiddenComp');
	copyWithFontForList('termDivComp','notesCompareResultForm:termTxtHiddenComp');
	copyWithFontForList('qualifierDivComp','notesCompareResultForm:qualifierTxtHiddenComp');
	copyWithFontForList('systemDomainDivComp','notesCompareResultForm:txtSystemDomainComp');

	function copyWithFontForList(div,hidden){
		divObj = document.getElementById(div);
		hiddenObj = document.getElementById(hidden);
		textValue = hiddenObj.value;
		var values = "";
		var divHtml = "";
		if(textValue != null && textValue.length > 0){
			values = textValue.split("~");
			if(values != null && values.length > 0)
			{
				for(var i=0, n = values.length; i<n-1; i+2)
				{
					divHtml += values[i] + "<br>";
	  				i= i+2;
				}
			}
			divObj.innerHTML = divHtml;
		}	
	}	

	function adjustHieght(divId){
		document.getElementById(divId).style.height="30px";
	}
function copyHiddenToDivLocal(hiddenId,divId) {
	var hiddenObj = document.getElementById(hiddenId);
	var divObj = document.getElementById(divId);
	if(hiddenObj.value == null || hiddenObj.value == ''){
			divObj.innerHTML = '';
	}
	parseForDivLocal(divObj, hiddenObj);
}	
function parseForDivLocal(divObject, textObject)
{
	var divHtml = "";
	var textValue = textObject.value;
	var values = "";
	if(textValue != null && textValue.length > 0){
		textValue = replaceString(textValue, '<', '&lt;')
		textValue = replaceString(textValue, '>', '&gt;')
		values = textValue.split("~");
	}
	if(values != null && values.length > 0)
	{
		for(var i=0, n = values.length; i<n; i++)
		{
			divHtml += values[i] + "<br>";
	  		i++;
		}
	}
	displayWithStyleLocal(divObject, divHtml);
}
function displayWithStyleLocal(targetDiv, divHtml)
{
	
	if(targetDiv != 'undefined' && targetDiv != null)
	{
		if(divHtml != "")
		{
			var splited = divHtml.split("<br>");
			var count = splited.length;

		if(divHtml != '' && divHtml !='undefined'){
		targetDiv.style.overflow="auto";
		}
	else{
		}
			targetDiv.innerHTML = divHtml;
			targetDiv.style.visibility = 'visible';
			targetDiv.style.display = 'block';
			}else{
		}
	}
}
checkVersionFlag();
function checkVersionFlag(){
	var compare = document.getElementById('notesCompareResultForm:versionCompareFlag').value;
	if(compare=='true'){
		row1.style.display = '';
		row2.style.display = 'none';
	}else{
		row1.style.display = 'none';
		row2.style.display = '';
	}
}
</script>
</HTML>
