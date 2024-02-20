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
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
<TITLE>editRootQuestions.jsp</TITLE>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<TABLE width="100%" cellpadding="0" cellspacing="0">	
			
									<TR>
										<TD width="80%"><w:message value="#{rootQuestionsBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								<!--  WAS 7.0 Changes - Hidden id rootQuestionLoad value loaded using binding instead of value -->
			<h:inputHidden id="hiddenAdminId"
									binding="#{rootQuestionsBackingBean.rootQuestionLoad}" />
										
									
				<TR>
				<TD width="80%">&nbsp;
				</TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="editRootQuestionForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>							
							<TD colspan="2" valign="top" class="ContentArea">
							
							
								
							<!--	Start of Table for actual Data	-->
							<TABLE width="98%">
								<TBODY>
									<TR height="3%"><TD align="center" bgcolor="#cccccc" colspan="2"><B>Edit Root Question</B></TD></TR>
										<TR height="5%"><TD align="center" colspan="2">&nbsp;</TD></TR>
									
									<TR bgcolor="#cccccc" height="6%">

												<TD align="right" valign="top" width="100%">
												<DIV id="resultHeaderDiv" align="left"
													style="position:relative;background-color:#cccccc;z-index:1;"><h:panelGrid
											id="resultHeaderTable"
											binding="#{rootQuestionsBackingBean.headerPanel}">
										</h:panelGrid></DIV>												
												</TD>
									</TR>	
									<TR>

												<TD align="right" valign="top" width="100%">
												<DIV id="resultHeaderDiv" align="left"
													style="position:relative;z-index:1;overflow-y:auto;"><h:panelGrid
											id="resultDisplayTable" rowClasses="dataTableOddRow,dataTableEvenRow"
											binding="#{rootQuestionsBackingBean.displayPanel}">
										</h:panelGrid></DIV>												
												</TD>
									</TR>

									<TR>
										<!-- <TD width="10"></TD> -->
										<TD width="1202">
										<h:commandButton value="Save" styleClass="wpdButton"
											id="createButton" tabindex="2" onmousedown="javascript:savePageAction(this.id);"
											onclick="update(); return false;">

										</h:commandButton>
										</TD>
										

									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	-->
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<%--<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>--%>
					<!-- End of hidden fields  -->
					<h:commandLink id="updateQues" style="hidden" action="#{rootQuestionsBackingBean.updateRootQuestions}">					
					</h:commandLink>
					<h:commandLink id="deleteLink"
						style="hidden" action="#{rootQuestionsBackingBean.deleteRootQuestions}"><f:verbatim />
					</h:commandLink>
				</h:form></TD>
			</TR>			
		</TABLE>
	</BODY>
</f:view>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	changeToReadOnly();
	changeButtonOnLoad();
	setColumnWidth('editRootQuestionForm:resultHeaderTable','8%:22%:19%:11%:11%:11%:11%:7%');
	setColumnWidth('editRootQuestionForm:resultDisplayTable','8%:22%:19%:11%:11%:11%:11%:7%');
	var tableObj = document.getElementById('editRootQuestionForm:resultDisplayTable');
	if(tableObj == null || tableObj.rows.length == 0){
		window.close();
	}
	function update(){		
			submitLink('editRootQuestionForm:updateQues');		
	}
	function changeToReadOnly(){
	var tags = document.getElementsByTagName('input');
		var tagType;
		var tagName;
		var tagMatchForReference;
		for(i = 0; i < tags.length; i++){
			tagName = tags[i].name;			
			tagMatchForReference = tagName.match('editRootQuestionForm:referenceInputText');
			if(tagMatchForReference == 'editRootQuestionForm:referenceInputText'){
				tags[i].readOnly = true;
			}			
		}
	}
	function deleteAction(){
	var message = "Are you sure you want to delete?";
	if(window.confirm(message)){
		submitLink('editRootQuestionForm:deleteLink');
		return true;
	}else{
		return false;
	}
	
}

function changeButtonOnLoad(){	
			var tags = document.getElementsByTagName('input');
			var isDeleteFlag = false;
			for(i = 0; i < tags.length; i++){
				if(tags[i].type == 'checkbox'){
					if(tags[i].checked == true){
						isDeleteFlag = true;
						break;
					}
				}
			}
			if(isDeleteFlag == true){
				document.getElementById('editRootQuestionForm:delete').disabled = false;
			}else{
				document.getElementById('editRootQuestionForm:delete').disabled = true;
			}
		
}

function changeButton(object){
	if(object.checked){
			document.getElementById('editRootQuestionForm:delete').disabled = false;
		}else{
			var tags = document.getElementsByTagName('input');
			var isDeleteFlag = false;
			for(i = 0; i < tags.length; i++){
				if(tags[i].type == 'checkbox'){
					if(tags[i].checked == true){
						isDeleteFlag = true;
						break;
					}
				}
			}
			if(isDeleteFlag == true){
				document.getElementById('editRootQuestionForm:delete').disabled = false;
			}else{
				document.getElementById('editRootQuestionForm:delete').disabled = true;
			}
		}
}

	 window.onbeforeunload = function(event)
      {  
			var tableObject = document.getElementById('editRootQuestionForm:resultDisplayTable');
			var flag = false;
			if(isEwpdbDataModifed()){
			    flag = true;
			} 
			if(flag){
				 if( event.clientX < document.body.clientWidth && event.clientY < 0 || event.altKey )   
			      {   
			          window.event.returnValue = "Unsaved data if any will be lost.";   
			      }
			}
		
      }
</script>
</HTML>
