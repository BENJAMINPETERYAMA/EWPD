				
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/StandardBenefitView.java" --%><%-- /jsf:pagecode --%>

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

<TITLE>Print Associated SubTask</TITLE>
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
<table width="100%" cellpadding="0" cellspacing="20">
<tr>
	<td>
				<h:form styleClass="form" id="associatedSubTaskPrintForm">
					
<!-- End of Tab table -->
								
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText" width="100%">
									<TBODY>
										<TR>
                                            
											<TD colspan=3>
												Administration >> Module (<h:outputText id="headerModuleName" value="#{moduleBackingBean.moduleName}" ></h:outputText>) >> Print
                                              <div id="moduleGenInfo">	
                                             <FIELDSET style="width:70%">
												<h:outputText value="No Sub Tasks Available."
							rendered="#{moduleBackingBean.tasksSubTaskList == null}"
							styleClass="dataTableColumnHeader"/>
						<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;width=100%">Associated Sub Tasks</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD width="35%"><h:outputText
													value="SubTasks"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style=" overflow:auto; width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										rendered="#{moduleBackingBean.tasksSubTaskList != null}"
										value="#{moduleBackingBean.tasksSubTaskList}"
										border="0"
										width="100%">
										
										<h:column>
											<h:outputText id="taskName" value="#{singleValue.subTaskName}"></h:outputText>
											<h:inputHidden id="taskID" value="#{singleValue.subTaskId}"></h:inputHidden>
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
										</h:column>
									</h:dataTable></DIV>
									</td>
						
								</TR>
								<TR>
								<TD>
									</TD>
								</TR>
							</TABLE>
						</DIV>
					</DIV>
                                                    
													
												</FIELDSET>

                                               </div>		                                           


											</TD>
										</TR>
                            
                                       
                                        
                                  	</TBODY>
									</TABLE>	
   </td>
</tr>
<tr>
   <td>
                                                	             
                                																					  
				</h:form>
	</td>
</tr>
</table>
</BODY></f:view>


<script>

	if(document.getElementById('associatedSubTaskPrintForm:searchResultTable') == null) {
			document.getElementById('panel2').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
	}
</script>
<script>window.print();</script>
</HTML>