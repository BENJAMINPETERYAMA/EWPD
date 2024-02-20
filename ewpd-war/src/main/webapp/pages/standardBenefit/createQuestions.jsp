<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Create Question</TITLE>
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
</HEAD>
<f:view>
<BODY>

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<jsp:include page="../navigation/top.jsp"></jsp:include>
			</td>
		</tr>		
		<tr>
			<td>
				<h:form styleClass="form" id="createQuestionForm">
				
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->						

						 		</DIV>

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<w:message value="#{CreateQuestionsBackingBean.validationMessages}"></w:message>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<td width="186" class="tabActive"> <h:outputText value=" Question"/> </td> 
				                					<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
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
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
							<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
								<TBODY>
							   		<TR>
											<TD width="138"><h:outputText id="question" value="Question*" styleClass="#{CreateQuestionsBackingBean.requiredQuestion ? 'mandatoryError' : 'mandatoryNormal'}" /> </TD>
											<TD width="536"><h:inputText style="width: 225px" styleClass="formInputField" id="questonText"value="#{CreateQuestionsBackingBean.question}"/> </TD>
									</TR>
										
									<TR>
										<TD width="138"><h:outputText id="dataType" value="DataType*" styleClass="#{CreateQuestionsBackingBean.requiredDataType ? 'mandatoryError' : 'mandatoryNormal'}"/> </TD>
										<TD width="536"><h:selectOneMenu styleClass="formInputField" id="dataTypeSelect" value="#{CreateQuestionsBackingBean.dataType}">
                    										<f:selectItem id="defaultValue" itemValue="" itemLabel=""/>
															<f:selectItem id="dollar" itemValue="$" itemLabel="$"/>
                    										<f:selectItem id="percentage" itemValue="%" itemLabel="%"/>
															<f:selectItem id="booleanValue" itemValue="Boolean" itemLabel="Boolean"/>
                  											</h:selectOneMenu></TD>
									</TR>
									<TR><TD height="13" width="138">&nbsp;</TD></TR>
									<TR>
									  <TD colspan="3" height="41">
										<TABLE id="selectTable" border="0" cellspacing="0" cellpadding="3"
											class="outputText">
											<TR>
												<TD width="141"><h:outputText id="answers" value="Possible Answers*" styleClass="#{CreateQuestionsBackingBean.requiredAnswers ? 'mandatoryError' : 'mandatoryNormal'}" /></TD>
												<TD width="185"><h:inputText styleClass="formInputField" id="answerText"/></TD>
												<TD width="52"><h:commandButton value="Add"
													styleClass="wpdButton" onclick="insertOption();return false;" ></h:commandButton></TD>
												<TD align="left" width="302"><h:selectOneMenu
													styleClass="formInputField" id="AnswerSelect" 
													style="width: 90px ;height:50px" >
													<f:selectItem itemValue="" itemLabel="" />
													</h:selectOneMenu></TD>
									  		
											</TR>
											
										</TABLE>
									 </TD>
									</TR>	
									
									<TR><TD width="138">&nbsp;</TD> </TR>
									<TR>
										<TD width="138">
											&nbsp;<h:commandButton value="Create"  styleClass="wpdButton" onclick="getOptions();return true;" action ="#{CreateQuestionsBackingBean.create}" > </h:commandButton>
										</TD>
										<TD width="536">&nbsp;</TD>
									</TR>
									<TR><TD width="138">&nbsp;</TD></TR>
								</TBODY>
							</TABLE>
<!--	End of Page data	-->
								</fieldset>		
							</TD>
						</TR>
					</table>

<!-- Space for hidden fields -->
					<h:inputHidden id="hiddenAnswer" value="#{CreateQuestionsBackingBean.selectedAnswers}"></h:inputHidden>
					<h:inputHidden id="hiddenDefaultAnswer" value="#{CreateQuestionsBackingBean.defaultAnswer}"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file ="../navigation/bottom.jsp" %>
			</td>
		</tr>
	</table>
</BODY>
</f:view>

<script language="JavaScript">
document.getElementById('createQuestionForm:AnswerSelect').size = '8';
var hiddenVal=document.getElementById('createQuestionForm:hiddenAnswer');
var array=hiddenVal.value.split("~");
var Select=document.getElementById("createQuestionForm:AnswerSelect");
Select.remove(0);
for(i=0;i<array.length;i++){
 	var answer=document.createElement('option');
	if(array[i]!=""){		
		answer.text=array[i];
		Select.add(answer);
	}
}

function insertOption()
  {
	
 	var option=document.createElement('option');
	
  	option.text=document.getElementById("createQuestionForm:answerText").value;
 
	
  	var Select=document.getElementById("createQuestionForm:AnswerSelect");
	
  	try
    	{
		
    	Select.add(option,null); // standards compliant
    	}
  	catch(ex)
    	{
		
    	 Select.add(option); // IE only
    	}
		
   }
function getOptions()
  {
  	var Select=document.getElementById("createQuestionForm:AnswerSelect");
	document.getElementById("createQuestionForm:hiddenAnswer").value = '';
  	for (i=0;i<Select.length;i++)
    {
			document.getElementById("createQuestionForm:hiddenAnswer").value =document.getElementById("createQuestionForm:hiddenAnswer").value + Select.options[i].text + '~' ;
    } 
	 var index=Select.selectedIndex;
	 if(Select.length>0){
  	 document.getElementById("createQuestionForm:hiddenDefaultAnswer").value=Select.options[index].text;
	 }
	while (Select.length>0)
     {
     Select.remove(Select.length-1);
    }
	// x.selectedIndex = 0;
	// x.options[x.selectedIndex].text="";

  }



</script>
</HTML>
