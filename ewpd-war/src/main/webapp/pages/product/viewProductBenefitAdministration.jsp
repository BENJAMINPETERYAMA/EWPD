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
<STYLE type="text/css">
  
.gridColumn40{
	width: 40%;		
}
.gridColumn20{
	width: 20%;	
}
.gridColumn30{
	width: 30%;	    
}
.gridColumn10{
	width: 10%;	    
}
 </STYLE>
<TITLE>Product Administration View</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="productBenefitAdministrationViewForm">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="../product/productTree.jsp"></jsp:include></DIV>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea"><!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="150">
								<table width="150" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Active"
											width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:commandLink>
											<h:outputText value="Benefit Administration" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Active"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:510">
						<div id="messageTextForNoQuestionsDiv"><br />
						<STRONG>&nbsp;<h:outputText value="No Question available." /></STRONG>
						</div>
						<div id="resultDiv">
						<TABLE border="0" cellspacing="1" cellpadding="0" width="98%"
							class="outputText">
							<TBODY>
								<TR>
									<td valign="middle">
									<div id="LabelHeaderDiv"
										style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
									<B> <h:outputText value="Associated Questionnaire"></h:outputText> </B>
									</div>
									</td>
								</TR>
								<tr>
									<td>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="40%"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="Answer"></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText value="Reference"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText value="Notes"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
								</TR>
								<TR>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height:200px;width:100%;overflow-y:auto;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{productBenefitAdministrationBackingBean.questionareList != null}"
										rowClasses="dataTableOddRow"
										value="#{productBenefitAdministrationBackingBean.questionareList}"
										border="0" width="100%">
										<h:column>
											<h:outputText id="questionName"
												value="#{singleValue.questionName}"></h:outputText>
											<h:inputHidden id="hidden_questionName"
												value="#{singleValue.questionName}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:outputText>
											<h:inputHidden id="hidden_ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="refDesc"
												value="#{singleValue.referenceDesc}"></h:outputText>
											<h:inputHidden id="hidden_level" value="#{singleValue.level}"></h:inputHidden>
										</h:column>
										<h:column>
										<h:inputHidden id="noteStat" value="#{singleValue.notes_exists}"></h:inputHidden>
										<h:inputHidden id="questnId" value="#{singleValue.questionId}"></h:inputHidden>
										<h:inputHidden id="adminLvlOpnId" value="#{singleValue.admnLvlAsscId}"></h:inputHidden>
										<h:commandButton alt="View" id="viewButton" 
														 image="../../images/notes_exist.gif"
														onclick="viewAction(); return false;" rendered="#{singleValue.noteAttached}"></h:commandButton>
										<h:commandButton alt="View" id="viewButton1" 
														 image="../../images/page.gif"
														 onclick="viewAction(); return false;" rendered="#{singleValue.noNoteAttached}"></h:commandButton>				
										</h:column>
									</h:dataTable></DIV>
									</td>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE border="0" cellspacing="1" cellpadding="0" width="98%"
							class="outputText">
							<TBODY>
								<TR>
									<td valign="middle">
									<div id="LabelTierHeaderDiv"
										style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
									<B> <h:outputText value="Associated Tiered Questionnaire"></h:outputText> </B>
									</div>
									</td>
								</TR>
								<TR>
									<td>
									<div id ="displayTierHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="40%"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="Answer"></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText value="Reference"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText value="Notes"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</TD>
								</TR>								
								<TR>
									<td valign="middle">									
									<div id="displayPanelContent12"
										style="position:relative;overflow:auto;height:300px;width:100%">
									<h:panelGrid id="panelTierTable" width="100%"
										binding="#{productBenefitAdministrationBackingBean.tierQuestionarePanel}">
									</h:panelGrid></div>
									</td>
							  	</TR>
							</TBODY>
						</TABLE>
						</div>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>
					<h:inputHidden id="qstnId"
								value="#{productBenefitAdministrationBackingBean.questionId}"></h:inputHidden>
					<h:inputHidden id="prmyId"
								value="#{productBenefitAdministrationBackingBean.primaryEntityID}"></h:inputHidden>
					<h:inputHidden id="bcId"
								value="#{productBenefitAdministrationBackingBean.benefitComponentId}"></h:inputHidden>
					<h:inputHidden id="admmLvlOptId"
								value="#{productBenefitAdministrationBackingBean.adminLvlOptionAssnSysId}"></h:inputHidden>	
				<!-- Space for hidden fields --> <h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					
				</h:commandLink> <!-- End of hidden fields  --> </td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>
		</h:form>
	</BODY>
</f:view>

<script>


var tableObject = document.getElementById('productBenefitAdministrationViewForm:searchResultTable'); 
var relTblWidth = 846 ; //946 , document.getElementById('benefitAdmnForm:displayHeaderTable').offsetWidth;
if(tableObject!= null && tableObject.rows.length > 0) {
if(document.getElementById('productBenefitAdministrationViewForm:searchResultTable').offsetHeight <= 250)
{
	document.getElementById('productBenefitAdministrationViewForm:searchResultTable').width = relTblWidth+"px";
	document.getElementById('resultHeaderTable').width = relTblWidth+"px";
	setColumnWidth('resultHeaderTable','40%:20%:30%:10%');
	setColumnWidth('productBenefitAdministrationViewForm:searchResultTable','40%:20%:30%:10%');
}
else
{
	document.getElementById('productBenefitAdministrationViewForm:searchResultTable').width = (relTblWidth-17)+"px";
	document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('resultHeaderTable','40%:20%:30%:10%');
	setColumnWidth('productBenefitAdministrationViewForm:searchResultTable','40%:20%:30%:10%');
}

if(null!= document.getElementById('productBenefitAdministrationViewForm:panelTierTable')){
var relTblWidth = 846 ;
if(document.getElementById('productBenefitAdministrationViewForm:panelTierTable').offsetHeight <= 250)
{
	document.getElementById('productBenefitAdministrationViewForm:panelTierTable').width = relTblWidth+"px";
	document.getElementById('resultHeaderTable1').width = relTblWidth+"px";
	setColumnWidth('resultHeaderTable1','40%:20%:30%:10%');
	setColumnWidth('productBenefitAdministrationViewForm:panelTierTable','40%:20%:30%:10%');
}
else
{
	document.getElementById('productBenefitAdministrationViewForm:panelTierTable').width = (relTblWidth-17)+"px";
	document.getElementById('resultHeaderTable1').width = (relTblWidth-17)+"px";
	setColumnWidth('resultHeaderTable1','40%:20%:30%:10%');
	setColumnWidth('productBenefitAdministrationViewForm:panelTierTable','40%:20%:30%:10%');
}}

	for(var i=0; i<tableObject.rows.length; i++){
		var rowobj = tableObject.rows[i];
		var cellobj = tableObject.rows[i].cells[2];
		var text = cellobj.children[1].value;
		if(text ==1){
			rowobj.className = 'dataTableEvenRow';
		}
	}

}
 else{
		var divHeader = document.getElementById('resultDiv');
		divHeader.style.visibility = 'hidden';	
		divHeader.style.height = "2px";	
  }



	if(getObj('productBenefitAdministrationViewForm:panelTierTable') == null || getObj('productBenefitAdministrationViewForm:panelTierTable').rows.length == 0) {
			getObj('LabelTierHeaderDiv').style.visibility = 'hidden';	
			getObj('displayTierHeaderDiv').style.visibility = 'hidden';
	}
	
function viewAction(){


getFromDataTableToHidden('productBenefitAdministrationViewForm:searchResultTable','questnId','productBenefitAdministrationViewForm:qstnId');
getFromDataTableToHidden('productBenefitAdministrationViewForm:searchResultTable','adminLvlOpnId','productBenefitAdministrationViewForm:admmLvlOptId');

var param = new Object();

var questionId=document.getElementById('productBenefitAdministrationViewForm:qstnId').value;
var primaryentityId=document.getElementById('productBenefitAdministrationViewForm:prmyId').value;
var primaryEntytyType="ATTACHPRODUCT";
var bcId=document.getElementById('productBenefitAdministrationViewForm:bcId').value;
var adminLvlOptionId=document.getElementById('productBenefitAdministrationViewForm:admmLvlOptId').value;
var secondaryEntityType="ATTACHQUESTION";
var retValue = window.showModalDialog('../popups/productQuestionNotesViewPopup.jsp'+getUrl()+'?&questionId='+questionId+'&primaryentityId='+primaryentityId+'&primaryEntytyType='+primaryEntytyType+'&bcId='+bcId+'&adminLvlOptionId='+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType,param,"dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");

					

}
function loadTierNotes(popupName,tierSysId,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i)
{
secondaryEntityType="ATTACHQUESTION";
var retValue = window.showModalDialog(popupName +getUrl()+ "?"
												 	+ "&temp=" + Math.random()
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType+"&tierSysId="+tierSysId
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType , 
													self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
//oldStatusValue = document.getElementById('productBenefitAdministrationViewForm:tildaTierNoteStatusId').value;
//		var imageObj = document.getElementById('productBenefitAdministrationViewForm:'+imageId+'tier'+tierSysId);
//if(retValue == "notes_exists"){
//		imageObj.src = "../../images/notes_exist.gif";
//		document.getElementById('productBenefitAdministrationViewForm:tildaTierNoteStatusId').value = oldStatusValue+'~'+tierSysId+i+'Y';
	//}else if(retValue == "no_notes"){
	//	imageObj.src = "../../images/page.gif";
//		document.getElementById('productBenefitAdministrationViewForm:tildaTierNoteStatusId').value = oldStatusValue+'~'+tierSysId+i+'N';
//	}
}



</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitAdministration" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
