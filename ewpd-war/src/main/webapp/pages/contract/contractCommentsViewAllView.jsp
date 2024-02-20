<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

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

<TITLE>WellPoint Product Database: Contract Comment</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>


			<TR>
				<h:inputHidden id="hidden1" value="#{contractCommentBackingBean.loadComments}"></h:inputHidden>
				<TD><h:form styleClass="form" id="ContractCommentForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel" width="273"><!-- Space for Tree  Data	-->
							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="contractTree.jsp"></jsp:include></DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea" width="911">
							<TABLE>
								<TBODY>
									<TR>
										<TD></TD>
									</TR>

								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>

									<TD width="16%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractBasicInfoBackingBean.getBasicInfo}">
												<h:outputText value="General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<!-- >TD width="18%">
		          							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<TR>
				                					<TD width="2" align="left"><IMG src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
													<TD width="100%" class="tabNormal"><h:commandLink action= "#{contractSpecificInfoBackingBean.loadContractSpecificInfo}" > <h:outputText value="Specific Information" /> </h:commandLink> </TD> 
				                					<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
		              							</TR>
		          							</TABLE>
		          						</TD-->
									<TD width="18%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}">
												<h:outputText value="Pricing Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="10%"  id="tabForStandard1">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<td class="tabNormal"><h:commandLink
												action="#{ContractNotesBackingBean.load}"
												id="contractNotesID">
												<h:outputText id="labelNotes" value="Notes"></h:outputText>
											</h:commandLink></td>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="14%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:commandLink
												action="#{contractCommentBackingBean.loadComment}"
												id="linkToComment">
												<h:outputText value="Comments" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<td width="14%"  id="tabForStandard">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}"
												id="linkToAdminOption">
												<h:outputText id="LabelAdminOption" value="Admin Option"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="14%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractRuleBackingBean.displayRules}"
												id="linkToRules">
												<h:outputText id="rules" value="Rules"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
							<h:inputHidden id="maxresult"
								value="#{contractCommentBackingBean.valueToMaxResult}"></h:inputHidden>
							<!--	Start of Table for actual Data	-->
							<div id="resultHeaderDiv"
								style="position:relative;font-size:10px;">
							<TABLE id="resultHeaderTable1" width="100%" cellpadding="3"
								cellspacing="1" bgcolor="#cccccc">
								<TR>
									<TD><h:outputText value="Comments History"></h:outputText></TD>
								</TR>
							</TABLE>
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
								id="resultHeaderTable" border="0" width="100%">
								<TBODY>

									<TR class="dataTableColumnHeader">
										<td align="left"><h:outputText
											styleClass="formOutputColumnField" value="Date"></h:outputText>
										</td>
										<td align="left"><h:outputText
											styleClass="formOutputColumnField" value="User"></h:outputText>
										</td>
										<td align="left"><h:outputText
											styleClass="formOutputColumnField" value="Comments"></h:outputText>
										</td>
										<td>&nbsp;</td>
									</TR>
								</TBODY>
							</TABLE>
							</div>
							<DIV id="searchResultdataTableDiv"
								style="height:100px;width:100%;overflow:auto;"><h:dataTable
								headerClass="dataTableHeader" id="resultsTable"
								styleClass="outputText"
								value="#{contractCommentBackingBean.commentHistroyList}"
								rendered="#{contractCommentBackingBean.commentHistroyList != null}"
								var="eachRow" width="673px" cellpadding="3" cellspacing="1"
								bgcolor="#cccccc" rowClasses="dataTableEvenRow,dataTableOddRow">

								<h:column>
									<h:inputHidden id="commentId" value="#{eachRow.commentSysId}"></h:inputHidden>
									<h:inputHidden id="dateSegmentId"
										value="#{eachRow.dateSegmentSysId}"></h:inputHidden>
									<h:outputText id="date" styleClass="formOutputColumnField"
										value="#{eachRow.createdTimeStamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText><h:outputText id ="space" value =" "> </h:outputText><h:outputText id ="timezone" styleClass="formOutputColumnField" value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="time"
											value="#{requestScope.timezone}">	</h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="hiddenUser"
										styleClass="formOutputColumnField"
										value="#{eachRow.createdUser}"></h:outputText>

								</h:column>
								<h:column>
									<h:outputText id="hiddencomments"
										styleClass="formOutputColumnField"
										value="#{eachRow.commentText}"></h:outputText>

								</h:column>
								<h:column>
									<h:commandButton alt="View" id="viewButton"
										image="../../images/view.gif"
										onclick="viewAction();window.showModalDialog('viewComment.jsp?commentId='+commentId+'&dateSegmentId='+dateSegmentId+'&temp=' + Math.random(),'dummyDiv','dialogHeight:450px;dialogWidth:515px;resizable=false;status=no');return false;">
									</h:commandButton>
									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								</h:column>
							</h:dataTable></div>


							</FIELDSET>

							<br>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
								<td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText value="Transfer Log" styleClass="variableLink" /></a></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<td>:<h:outputText value="#{contractCommentBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>:<h:outputText
											value="#{contractCommentBackingBean.status}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>:<h:outputText
											value="#{contractCommentBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
								</tr>
							</table>
								
							</FIELDSET>



							<!--	End of Page data	-->
							<DIV id="dummyDiv" style="visibility:hidden"></DIV>

							<!-- Space for hidden fields --> <h:inputHidden
								id="commentIdHidden"
								value="#{contractCommentBackingBean.commentId}"></h:inputHidden>
							<h:inputHidden id="dateSegmentIdHidden"
								value="#{contractCommentBackingBean.dateSegmentId}"></h:inputHidden>
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

							<h:inputHidden id="dummyHidden"></h:inputHidden> <!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">	

var commentId;
var dateSegmentId;

	hideResultDiv();
	function hideResultDiv() {
		var tableObj = document.getElementById('ContractCommentForm:resultsTable');

		if(document.getElementById('ContractCommentForm:resultsTable') != null) {

if(document.getElementById('ContractCommentForm:resultsTable').offsetHeight <= 100) {	
			document.getElementById('resultHeaderTable1').width = 100 + "%";
			document.getElementById('resultHeaderTable').width = 100 + "%";
			document.getElementById('ContractCommentForm:resultsTable').width = 100 + "%";
			setColumnWidth('ContractCommentForm:resultsTable','25%:20%:50%:5%');
			setColumnWidth('resultHeaderTable','25%:20%:50%:5%');
		}else{
			var relTblWidth = document.getElementById('ContractCommentForm:resultsTable').offsetWidth;
			//document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
			//document.getElementById('resultHeaderTable1').width = relTblWidth + 'px';
			document.getElementById('resultHeaderTable1').width =document.getElementById('ContractCommentForm:resultsTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = document.getElementById('ContractCommentForm:resultsTable').offsetWidth;
			setColumnWidth('ContractCommentForm:resultsTable','25%:20%:50%:5%');
			setColumnWidth('resultHeaderTable','25%:20%:50%:5%');
		}


			

		}else{
		var divObj = document.getElementById('resultHeaderDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
		}
	}
	function viewAction(){
 		getFromDataTableToHidden('ContractCommentForm:resultsTable','commentId','ContractCommentForm:commentIdHidden');
		getFromDataTableToHidden('ContractCommentForm:resultsTable','dateSegmentId','ContractCommentForm:dateSegmentIdHidden');
		commentId = document.getElementById('ContractCommentForm:commentIdHidden').value;
		dateSegmentId = document.getElementById('ContractCommentForm:dateSegmentIdHidden').value;
	  }

i = document.getElementById("ContractCommentForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}
function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}

 </script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractComment" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
