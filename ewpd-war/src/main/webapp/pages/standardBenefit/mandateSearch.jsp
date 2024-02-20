<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
	<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
	<%@ taglib uri="/wpd.tld" prefix="w"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM Software Development Platform">
	<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"   title="Style">
	<LINK rel="stylesheet" type="text/css" href="../../css/global.css"      title="Style">
	<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"  title="Style">
	<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"    title="Style">
	<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
	<TITLE>Page Title</TITLE>
	<script language="JavaScript" src="../../js/CalendarPopup.js" ></script>
	<script language="JavaScript" src="../../js/date.js" ></script>
	<script language="JavaScript" src="../../js/PopupWindow.js" ></script>
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
<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
	    <tr>
	        <td>
            	<jsp:include page="../navigation/header.jsp"></jsp:include>
            </td>
        </tr>
        <tr>
            <td>
        	    <jsp:include page="../navigation/MenuComponent.jsp"></jsp:include>
            </td>
        </tr>
        <TR>
            <td>
                <TABLE width = "100%" id="breadCumbTable" bgcolor="#7670B3">
        			<tr>
                    	<td height="16" valign="middle" bgcolor="#7670B3" class="breadcrumb">
                        	<SPAN class="breadcrumb">Product &gt;&gt;
					Mandate &gt;&gt; <A href="#">Locate</A></SPAN>
                    	</td>
            		</tr>
            	</TABLE>
         	</td>
        </TR>
        <tr>
        	<td>
            	<h:form styleClass="form" id="MandateForm">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<TR>
                        	<TD width="273" valign="top" class="leftPanel">
                            	<DIV class="treeDiv">   
<!-- Space for Tree  Data     -->                                 
                            	</DIV>
                        	</TD>
                            <TD colspan="2" valign="top" class="ContentArea">
                            	<TABLE>
                                	<TBODY>
                                    	<tr>
                                        	<TD>
<!-- Insert WPD Message Tag --> 
                                        	</TD>
                                    	</tr>       
                                                      </TBODY>
                              	</TABLE>
<!-- Table containing Tabs -->
                             	<div>
                                	<table width="400" border="0" cellpadding="0" cellspacing="0"
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
                                            	 style="position:relative; cursor:hand;"><b>Search Criteria</b></div>
                                                <div id="searchPanelContent" class="tabContentBox"
                                                     style="position:relative;"><br />
<!--  Start of Table for actual Data      -->         
                                                     <TABLE width="35%" border="0" cellspacing="0" cellpadding="3" class="outputText">
                                                     	<TBODY>
                                                            <TR>
                                                                  <TD width="110"><h:outputText value="Business Domain"/> </TD>
                                                                  <TD width="229"><h:inputText styleClass="formInputField" id="txtBusinessDomain" value="#{MandateSearchBackingBean.businessDomain}"/> 
																  				  <h:inputHidden id="BusinessDomainHidden" value="#{MandateSearchBackingBean.businessDomain}"></h:inputHidden>
																  </TD>
																  <TD align="left" width="176">
                                                                  	<h:commandButton alt="Select" id="BusinessDomainButton" image="../../images/select.gif" >
                                                                   	</h:commandButton>
                                                                  </TD>
                                                            </TR>
                                                            <TR>
                                                                  <TD width="110"><h:outputText value="Jurisdiction"/> </TD>
                                                                  <TD width="229"><h:inputText styleClass="formInputField" id="txtJurisdiction" value="#{MandateSearchBackingBean.jurisdiction}"/>
																				  <h:inputHidden id="JurisdictionHidden" value="#{MandateSearchBackingBean.jurisdiction}"></h:inputHidden>
																  </TD>
																  <TD align="left" width="176">
                                                                  	<h:commandButton alt="Select" id="JurisdictionButton" image="../../images/select.gif" >
                                                                   	</h:commandButton>
                                                                  </td>
                                                            </TR>
                                                            <TR>
                                                                  <TD width="110"><h:outputText value="Version"/> </TD>
                                                                  <TD width="229"><h:inputText styleClass="formInputField" id="txtVersionNumber" value="#{MandateSearchBackingBean.version}"/> </TD>
                                                            </TR>
                                                            <TR>
                                                                  <TD width="110"><h:outputText value="Updated Date"/> </TD>
                                                                  <TD width="229"><h:inputText styleClass="formInputField" id="txtUpdatedDate" value="#{MandateSearchBackingBean.updatedDate}" />  
																				  <span class="dateFormat">(mm/dd/yyyy)</span>
                                                                 </td>
                                                                  <td align="left" valign="top" width="176">     
                                                                       <A href="#"
																			onclick="cal1.select('MandateForm:txtUpdatedDate','effectiveDate_cal','MM/dd/yyyy'); return false;"
																			title="cal1.select(document.forms[0].MandateForm:txtUpdatedDate,'effectiveDate_cal','MM/dd/yyyy'); return false;"
																			name="effectiveDate_cal" id="effectiveDate_cal">
                                                                               <IMG src="../../images/cal.gif" alt="Cal" border="0">
                                                                              </A>                          
                                                                   </td>
                                                            </TR>
                                                            <TR>
                                                                  <TD width="110"><h:outputText value="Updated By"/> </TD>
                                                                  <TD width="229"><h:inputText styleClass="formInputField" id="txtUpdatedBy" value="#{MandateSearchBackingBean.updatedBy}"/> </TD>
                                                            </TR>
                                                            <TR>
                                                                  <TD width="110">
                                                                        <h:commandButton value="Locate"  styleClass="wpdButton" action="#{MandateSearchBackingBean.search}"> </h:commandButton>
                                                                  </TD>
                                                                  <TD width="229">&nbsp;</TD>
                                                            </TR>
                                                      </TBODY>
                                                      </TABLE>
                                          		</div>
                                          	</div>
                                          	<div id="panel2">
                                          		<div id="panel2Header" class="tabTitleBar"
                                           		     style="position:relative; cursor:hand;">Search Results</div>
                                          			<div id="panel2Content" class="tabContentBox"
                                          			      style="position:relative;font-size:10px;"><br />
                                        			  <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                                			<tr>
                                                  				<td>
                                                      				<div id="resultHeaderDiv">
                                                      					<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
                                                            				id="resultHeaderTable" border="0" width="100%">
                                                            				<TBODY>
                                                                  				<TR class="dataTableColumnHeader">
                                                                    			    <td align="left" width = "16%"><h:outputText value="Jurisdiction"></h:outputText></td>
                                                                    			    <td align="left" width = "16%"><h:outputText value="Description"></h:outputText>
                                                                   				     </td>
                                                                        			<td align="left" width = "16%"><h:outputText value="Version"></h:outputText></td>
                                                                        			<td align="left" width = "16%"><h:outputText value="Updated Date"></h:outputText></td>
                                                                        			<td align="left" width = "16%"><h:outputText value="Updated By"></h:outputText></td>
                                                                        			<td align="left" width = "20%"><h:outputText value=""></h:outputText></td>
                                                                  				</TR>
                                                            				</TBODY>
                                                      					</TABLE>
                                                      				</div>
                                                      			</td>
                                                			</tr>
                                                			<tr>
                                                    			<TD>
                                                      				<div id="searchResultdataTableDiv"
                                                            			style="height:100px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
                                                      				<!-- Search Result Data Table --> 
                                                      					<h:dataTable
                                                            				rowClasses="dataTableEvenRow,dataTableOddRow"
                                                            				styleClass="outputText" headerClass="dataTableHeader"
                                                            				id="searchResultTable" var="singleValue" cellpadding="3"
                                                            				width="100%" cellspacing="1" bgcolor="#cccccc"
                                                            				rendered="#{MandateSearchBackingBean.searchResult != null}"
                                                            				value="#{MandateSearchBackingBean.searchResult}">
                                                            				<h:column>
                                                                  				<h:outputText id="Jurisdiction" value="#{singleValue.jurisdiction}"></h:outputText>
                                                            				</h:column>
                                                            				<h:column>
                                                                  				<h:outputText id="Description" value="#{singleValue.description}"></h:outputText>
                                                            				</h:column>
                                                            				<h:column>
                                                                  				<h:outputText id="Version"
                                                                        			value="#{singleValue.version}"></h:outputText>
                                                            				</h:column>
                                                            				<h:column>
                                                                  				<h:outputText id="UpdatedDate"
                                                                        			value="#{singleValue.updatedDate}"></h:outputText>
                                                            				</h:column>
																			<h:column>
																				<h:outputText id="UpdatedBy"
                                                                        			value="#{singleValue.updatedBy}"></h:outputText>
                                                            				</h:column>
                                                            				<h:column>
                                                                  				<h:commandButton styleClass="wpdbutton" id="basicView" image="../../images/view.gif"
                                                                  				value="View"></h:commandButton>
                                                                  				<h:commandButton styleClass="wpdbutton" id="basicEdit" image="../../images/edit.gif"
                                                                  					value="Edit"></h:commandButton>
                                                                  			<h:commandButton styleClass="wpdbutton" id="basicDelete" image="../../images/delete.gif"
                                                                  				value="Delete"></h:commandButton>
                                                            				</h:column>
                                                      					</h:dataTable>
																	</div>
                                                      			</TD>
                                                			</tr>
                                    					</table>
                                          			</div>
                                          		</div>
                                        </div>
                                </div>      
<!--  End of Page data  -->
                            </TD>
                         </TR>
                     </table>
<!-- Space for hidden fields -->
                     <h:inputHidden id="hidden1" value="value1"></h:inputHidden>
                     <h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->
                </h:form>
            </td>
        </tr>
        <tr>
        	<td>
            	<%@ include file ="../navigation/pageFooter.jsp" %>
            </td>
        </tr>
	</table>
</BODY>
</f:view>
<script>
            var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
            if(document.getElementById('StandarsBenefitsSearchForm:searchResultTable') != null) {
            /*      tigra_tables('StandarsBenefitsSearchForm:searchResultTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)'); */
                  setColumnWidth('StandarsBenefitsSearchForm:searchResultTable','20%:10%:20%:20%:10%:10%:10%');
                  setColumnWidth('resultHeaderTable','20%:10%:20%:20%:10%:10%:10%');      
                  showResultsTab();
            }else {
                  var headerDiv = document.getElementById('resultHeaderDiv');
                  headerDiv.style.visibility = 'hidden';
                  var headerDiv2 = document.getElementById('searchResultdataTableDiv');
                  headerDiv2.visibility ='hidden';
            }     
            if(document.getElementById('StandarsBenefitsSearchForm:searchResultTable') != null){
                  document.getElementById('StandarsBenefitsSearchForm:searchResultTable').onresize = syncTables;
                  syncTables();
            }
            function syncTables(){
                  var relTblWidth = document.getElementById('StandarsBenefitsSearchForm').offsetWidth;
                  document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
            }     
</script>
</HTML>

