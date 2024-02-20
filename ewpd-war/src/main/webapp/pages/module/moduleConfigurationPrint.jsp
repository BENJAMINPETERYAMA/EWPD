				
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/StandardBenefitView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Print Module Configuration</TITLE>
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
				<h:form styleClass="form" id="moduleConfigurationPrintForm">
					<h:inputHidden id="moduleId" value="#{moduleBackingBean.viewModule}" />
					<h:outputText value="No Tasks Available."
							rendered="#{moduleBackingBean.searchResultList == null}"
							styleClass="dataTableColumnHeader"/>
					<div id ="printFormDiv">
					
<!-- End of Tab table -->
								
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText" width="70%">
									<TBODY>
										<TR>
                                            
											<TD colspan=1>
												Administration >> Module (<h:outputText id="headerModuleName" value="#{moduleBackingBean.moduleName}" ></h:outputText>) >> Print
                                             <!--  <div id="moduleGenInfo">	-->
                                             <FIELDSET style="width:70%">
												
						<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; width=70% cursor:hand;">Tasks</DIV>
						<DIV id="panel2">
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;">
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="position:relative; cursor:hand;width=100%"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										rendered="#{moduleBackingBean.searchResultList != null}"
										value="#{moduleBackingBean.searchResultList}"
										border="0"
										width="100%">
										
										<h:column>
											<h:outputText id="taskName" value="#{singleValue.taskName}"></h:outputText>
											<h:inputHidden id="taskID" value="#{singleValue.taskId}"></h:inputHidden>
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
										</h:column>
									</h:dataTable>
									</DIV>
									</td>
				<%--<h:inputHidden id="selectedTaskId" value="#{moduleBackingBean.selectedTaskId}"></h:inputHidden>
					</h:inputHidden>--%>
						
								</TR>
								<TR>
								<TD>
									</TD>
								</TR>
							</TABLE>
						</DIV>
					</DIV>
                                                    
													
												</FIELDSET>

                                             <!--   </div> -->


											</TD>
										</TR>
                            
                                       
                                        
                                  	</TBODY>
									</TABLE>	
					</div>
	</h:form>
</BODY></f:view>


<script>

var printForGenInfo = document.getElementById("moduleConfigurationPrintForm:moduleId").value;
initialize();
	function initialize(){
		if(document.getElementById('moduleConfigurationPrintForm:searchResultTable') != null) {
			//setColumnWidth('resultHeaderTable','35%:35%:30%');		
			setColumnWidth('moduleConfigurationPrintForm:searchResultTable','35%:35%:30%');		
		}else{
			document.getElementById('panel2').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
			document.getElementById('panel2Header').style.visibility = 'hidden';
			document.getElementById("printFormDiv").style.visibility = 'hidden';
		}
	}
if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('moduleConfigInfo');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
</script>
<script>window.print();</script>
</HTML>