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
.gridColumn30{
	width: 30%;				
}
.gridColumn20{
	width: 20%;		
}
.gridColumn25{
	width: 25%;		
}
.gridColumn15{
	width: 15%;		
}
.gridColumn10{
	width: 10%;		
}
 </STYLE>
<TITLE>Contract Benefit Administration View</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
			<tr><!--  WAS 7.0 Changes - Hidden id rootQuestionLoad value loaded using binding instead of value -->
			<td><h:inputHidden binding="#{contractBenefitAdministrationBackingBean.loadQuestionForView}" />
	<h:form styleClass="form" id="benefitAdmnForm">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include page="contractTree.jsp"></jsp:include>	</DIV>
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
						<STRONG>&nbsp;<h:outputText value="No Questions available." /></STRONG>
						</div>
						<div id="resultDiv">
						<TABLE border="0" cellspacing="1" cellpadding="0" width="98%"
							class="outputText">
						 <TBODY>
						   <tr>
							<td>
							 <TABLE width="100%" cellpadding="1" border="0" id="tabheader"
								class="smallfont">
							  <tr>	
                                <td>
								 <TABLE>
								  <TR>
								   <TD width="5%" height="25" align="right">
									<a href="#" onclick="viewQuestionnaire();return false;">
									 <h:outputText value="[View Questionnaire]" styleClass="variableLink"/>
									</a>
								   </TD>
								  </TR>
								 </TABLE>
								</td>
                               </tr>
							  </TABLE>
                             </td>
							</tr>
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
												<TD align="left" width="30%"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="Answer"></h:outputText></TD>
												<TD align="left" width="25%"><h:outputText value="Reference"></h:outputText></TD>
												<TD align="left" width="15%"><h:outputText value="PVA"></h:outputText></TD>
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
										rendered="#{contractBenefitAdministrationBackingBean.viewQuestionnaireList != null}"
										rowClasses="dataTableOddRow"
										value="#{contractBenefitAdministrationBackingBean.viewQuestionnaireList}"
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
											<h:outputText id="providerArrangement"
												value="#{singleValue.providerArrangement}"></h:outputText>
											<h:inputHidden id="hidden_providerArrangement"
												value="#{singleValue.providerArrangement}"></h:inputHidden>
										</h:column>
										<h:column>
										<h:inputHidden id="noteStat" value="#{singleValue.noteExist}"></h:inputHidden>
										<h:inputHidden id="questnId" value="#{singleValue.questionId}"></h:inputHidden>
										<h:inputHidden id="adminLvlOpnId" value="#{singleValue.adminLevelOptionSysId}"></h:inputHidden>
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
									<div id="displayTierHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="30%"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="Answer"></h:outputText></TD>
												<TD align="left" width="25%"><h:outputText value="Reference"></h:outputText></TD>
												<TD align="left" width="15%"><h:outputText value="PVA"></h:outputText></TD>												
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
										style="height:200px;width:100%;overflow-y:auto;">
									<h:panelGrid id="panelTierTable" width="100%"
										binding="#{contractBenefitAdministrationBackingBean.tierQuestionarePanel}">
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
								value="#{contractBenefitAdministrationBackingBean.questionId}"></h:inputHidden>
				<h:inputHidden id="prmyId"
								value="#{contractBenefitAdministrationBackingBean.primaryEntityID}"></h:inputHidden>
				<h:inputHidden id="bcId"
								value="#{contractBenefitAdministrationBackingBean.benefitComponentId}"></h:inputHidden>
				<h:inputHidden id="admmLvlOptId"
								value="#{contractBenefitAdministrationBackingBean.adminLvlOptionAssnSysId}"></h:inputHidden>	

				<!-- Space for hidden fields --> <h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					
				</h:commandLink> <!-- End of hidden fields  --> 

						<!-- End of Tab table -->

						</td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	<!-- WAS 6.0 migration changes - Javascript error Invalid arguement thrown when html tags are not closed properly -->
	</h:form>
	</td>
	</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
	var relTblWidth = 846 ; //946 , document.getElementById('benefitAdmnForm:displayHeaderTable').offsetWidth;
	var tableObject = document.getElementById('benefitAdmnForm:searchResultTable');
 	if(tableObject!= null && tableObject.rows.length > 0) 
	{	
		if(document.getElementById('benefitAdmnForm:searchResultTable').offsetHeight <= 250)
		{
			document.getElementById('benefitAdmnForm:searchResultTable').width = relTblWidth+"px";
			document.getElementById('resultHeaderTable').width = relTblWidth+"px";
			setColumnWidth('resultHeaderTable','30%:20%:25%:15%:10%');
			setColumnWidth('benefitAdmnForm:searchResultTable','30%:20%:25%:15%:10%');
		}
		else
		{
			document.getElementById('benefitAdmnForm:searchResultTable').width = (relTblWidth-17)+"px";
			document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";
			setColumnWidth('resultHeaderTable','30%:20%:25%:15%:10%');
			setColumnWidth('benefitAdmnForm:searchResultTable','30%:20%:25%:15%:10%');
		}
		var divObj = document.getElementById('messageTextForNoQuestionsDiv');
		divObj.style.visibility = 'hidden';	
		divObj.style.height = "2px";
	}
	else
	{
		var divHeader = document.getElementById('benefitAdmnForm:resultDiv');		
		if(divHeader !=null )
		{
			divHeader.style.visibility = 'hidden';	
			divHeader.style.height = "2px";	
		}
	}
	if(null!= document.getElementById('benefitAdmnForm:panelTierTable'))
	{
		var relTblWidth = 846 ;
		if(document.getElementById('benefitAdmnForm:panelTierTable').offsetHeight <= 250)
		{
			document.getElementById('benefitAdmnForm:panelTierTable').width = relTblWidth+"px";
			document.getElementById('resultHeaderTable1').width = relTblWidth+"px";
			setColumnWidth('resultHeaderTable1','30%:20%:25%:15%:10%');
			setColumnWidth('benefitAdmnForm:panelTierTable','30%:20%:25%:15%:10%');
		}
		else
		{
			document.getElementById('benefitAdmnForm:panelTierTable').width = (relTblWidth-17)+"px";
			document.getElementById('resultHeaderTable1').width = (relTblWidth-17)+"px";
			setColumnWidth('resultHeaderTable1','30%:20%:25%:15%:10%');
			setColumnWidth('benefitAdmnForm:panelTierTable','30%:20%:25%:15%:10%');
		}
	}/*
	var tableObject = document.getElementById('benefitAdmnForm:searchResultTable');
 	if(tableObject!= null && tableObject.rows.length > 0) {

			var relTblWidth = document.getElementById('resultHeaderTable').width;
			var rowlength = document.getElementById('benefitAdmnForm:searchResultTable').rows.length;
			document.getElementById('benefitAdmnForm:searchResultTable').width = relTblWidth;
			
			syncTables('resultHeaderTable','benefitAdmnForm:searchResultTable');
		    setColumnWidth('resultHeaderTable','30%:20%:25%:15%:10%');
			setColumnWidth('benefitAdmnForm:searchResultTable','30%:20%:25%:15%:10%');

			var divObj = document.getElementById('messageTextForNoQuestionsDiv');

			divObj.style.visibility = 'hidden';	
			divObj.style.height = "2px";				
	} else{
		var divHeader = document.getElementById('benefitAdmnForm:resultDiv');
		
		if(divHeader !=null ){
			divHeader.style.visibility = 'hidden';	
			divHeader.style.height = "2px";	
			}
		}
	*/ 
	var tableObject = document.getElementById('benefitAdmnForm:searchResultTable');
	if(tableObject!= null && tableObject.rows.length > 0) {	
		for(var i=0; i<tableObject.rows.length; i++){
			var rowobj = tableObject.rows[i];
			var cellobj = tableObject.rows[i].cells[2];
			var text = cellobj.children[1].value;
			if(text ==1){
				rowobj.className = 'dataTableEvenRow';
			}
		}
	}
	if(getObj('benefitAdmnForm:panelTierTable') == null || getObj('benefitAdmnForm:panelTierTable').rows.length == 0) {
			getObj('LabelTierHeaderDiv').style.visibility = 'hidden';	
			getObj('displayTierHeaderDiv').style.visibility = 'hidden';
	}
	
	function viewQuestionnaire(){
		var retValue = window.showModalDialog('../contract/viewQuestionnaire.jsp'+getUrl()+'?'+ 'temp=' + Math.random(),'','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');	
	}
	
	function viewAction(){
		getFromDataTableToHidden('benefitAdmnForm:searchResultTable','questnId','benefitAdmnForm:qstnId');
		getFromDataTableToHidden('benefitAdmnForm:searchResultTable','adminLvlOpnId','benefitAdmnForm:admmLvlOptId');

		var param = new Object();
		
		var questionId=document.getElementById('benefitAdmnForm:qstnId').value;
		var primaryentityId=document.getElementById('benefitAdmnForm:prmyId').value;
		var primaryEntytyType="ATTACHCONTRACT";
		var bcId=document.getElementById('benefitAdmnForm:bcId').value;
		var adminLvlOptionId=document.getElementById('benefitAdmnForm:admmLvlOptId').value;
		var secondaryEntityType="ATTACHQUESTION";
		var retValue = window.showModalDialog('../popups/contractQuestionNotesViewPopup.jsp'+getUrl()+'?&questionId='+questionId+'&primaryentityId='+primaryentityId+'&primaryEntytyType='+primaryEntytyType+'&bcId='+bcId+'&adminLvlOptionId='+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType,param,"dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
	}

function loadTierNotes(popupName,tierSysId,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i)
{
secondaryEntityType="ATTACHQUESTION";
var retValue = window.showModalDialog(popupName + "?"
												 	+ "&temp=" + Math.random()
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType+"&tierSysId="+tierSysId
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType , 
													self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");


}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBenefitAdminProductStructure" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
