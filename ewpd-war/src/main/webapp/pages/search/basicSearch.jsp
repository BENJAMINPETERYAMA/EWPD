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

<TITLE>Search Engine</TITLE>
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
<style>
	#postit{
		position:absolute;
		width:405px;
		padding:5px;
		background-color:lightyellow;
		border:1px solid black;
		visibility:hidden;
		z-index:100;
	}
</style>

</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('benefitComponentCreateForm:basSearch');" >
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include page="../navigation/tree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message /></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<br />
							<tr>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabActive"><h:outputText
											id="basicSearchTabTable" value=" Basic Search" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabNormal"><h:commandLink action="#{menuBean.actionAdvancedSearch}">
											<h:outputText id="advancweSearchTabTable"
												value=" Advanced Search" />
										</h:commandLink></td>
											
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>


								<td width="50%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="2">
								<tr><br/></tr>
								<TR >
									<TD >
										&nbsp;<h:outputText id="nameLabel"
										value="Identifier *" styleClass="#{basicSearchBackingBean.searchCriteriaEntered?'mandatoryNormal':'mandatoryError'}"/>
									</TD>
									<TD colspan="2" style="width:150px" width="360"><h:inputText id="nameTxt"
										styleClass="formInputField" style="width:312px" tabindex="1"
										value="#{basicSearchBackingBean.searchCriteria}"
										maxlength="200" /><br>
	<div id="postit" style="position:absolute;display:none;visibility:hidden;z-index:1000;width:312px;height:400;overflow:auto; ">
		<span style="font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 11px;">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr width="100%"><td style="text-align:right"><span style="font-size:9px"><a href="javascript:closeit();">Close</a></span></td></tr>
			</table>
				default:  Partial search
                        <table border="0" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc">
                                <tr bgcolor="#ffffff"><td><b>Criteria</b></td><td><b>Matching data</b></td></tr>
                                <tr bgcolor="#ffffff"><td>abc</td><td>xxabcxx, xxabc, abcxx</td></tr>
                                <tr bgcolor="#ffffff"><td>abc def</td><td>xxabc defxx, xxabc def, abc defxx</td></tr>
                        </table>
				'&nbsp;':  Exact match
                        <table border="0" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc">
                                <tr bgcolor="#ffffff"><td><b>Criteria</b></td><td><b>Matching data</b></td></tr>
                                <tr bgcolor="#ffffff"><td>'abc'</td><td>abc</td></tr>
                                <tr bgcolor="#ffffff"><td>&nbsp;</td><td>Will not match - xxabcxx, xxabc, abcxx</td></tr>
                        </table>
				+:  Logical AND
                        <table border="0" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc">
                                <tr bgcolor="#ffffff"><td><b>Criteria</b></td><td><b>Matching data</b></td></tr>
                                <tr bgcolor="#ffffff"><td>abc + def</td><td>xxabcdefxx, xxabc defxx xxabcxx xxdefxx</td></tr>
                                <tr bgcolor="#ffffff"><td>&nbsp;</td><td>Will not match - xxabcxx, xxdefxx</td></tr>
                        </table>
				|:  Logical OR
                        <table border="0" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc">
                                <tr bgcolor="#ffffff"><td><b>Criteria</b></td><td><b>Matching data</b></td></tr>
                                <tr bgcolor="#ffffff"><td>abc | def</td><td>xxabcxx, xxabcdefxx, xxabcxx xxdefxx, xxdefxx</td></tr>
                                <tr bgcolor="#ffffff"><td>'abc' | 'def'</td><td>abc, def</td></tr>
                        </table>
				\:  Escape character to include the operators as part of the search criteria
                        <table border="0" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc">
                                <tr bgcolor="#ffffff"><td><b>Criteria</b></td><td><b>Matching data</b></td></tr>
                                <tr bgcolor="#ffffff"><td>abc\+</td><td>xxabc+xx, xx+abcxx, abc+, +abc</td></tr>
                                <tr bgcolor="#ffffff"><td>'abc\+'</td><td>abc+</td></tr>
                                <tr bgcolor="#ffffff"><td>\+</td><td>xx+xx, xx+, +xx</td></tr>
                                <tr bgcolor="#ffffff"><td>'\+'</td><td>+</td></tr>
                        </table>
				():  Set precedence.  Only 3 levels of nesting is supported.
		</span>
	</div>
									</TD>
									<td align="left" width="204"><img src="../../images/help_16x16.gif" width="16" height="16" style="vertical-align:middle" onclick="showit(event)"></td>
								</TR>
								<TR>
									<TD width="15%"><BR>&nbsp;<h:outputText id="searchFor"
										value="Search For *" styleClass="#{basicSearchBackingBean.anyCheckboxSelected?'mandatoryNormal':'mandatoryError'}"/>
									</TD>
								</TR>
								<TR><TD width="10%"></TD>
						
											<TD colspan="4">
												<FIELDSET style="margin-left:6px;margin-right:-2px;padding-bottom:1px;padding-top:6px;width:450">
													<LEGEND> <FONT color="black">Limited To</FONT></LEGEND>
													<TABLE border="0" cellspacing="5" cellpadding="3">
														<TR>
															<TD width="547"><h:outputText id="lobLabel"
																value="Line of Business " />
															</TD>
															<TD width="210">
															<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
															<h:inputHidden id="lobTxtHidden"
																value="#{basicSearchBackingBean.lineOfBusiness}"></h:inputHidden>
															</TD>
															<TD width="100"><h:commandButton id="lobButton" alt="Select"
																image="../../images/select.gif" style="cursor: hand"
																onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','benefitComponentCreateForm:lobTxtHidden',2,2); return false;"
																tabindex="2" /></TD>	
														</TR>
														<TR>
																<TD width="547"><h:outputText id="businessEntityLabel"
																	value="Business Entity "
																	 />
																</TD>
																<TD width="210">
																<DIV id="businessEntityDiv" class="selectDataDisplayDiv"></DIV>
																<h:inputHidden id="businessEntityTxtHidden"
																	value="#{basicSearchBackingBean.businessEntity}"></h:inputHidden>
																</TD>
																<TD width="100"><h:commandButton id="businessEntityButton"
																	alt="Select" image="../../images/select.gif"
																	style="cursor: hand"
																	onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','benefitComponentCreateForm:businessEntityTxtHidden',2,2); return false;"
																	tabindex="3" /></TD>	
														</TR>
														<TR>
																<TD width="547"><h:outputText id="businessGroupLabel"
																	value="Business Group "
																	/></TD>
																<TD width="210">
																<DIV id="businessGroupDiv" class="selectDataDisplayDiv"></DIV>
																<h:inputHidden id="businessGroupTxtHidden"
																	value="#{basicSearchBackingBean.businessGroup}"></h:inputHidden>
																</TD>
																<TD width="100"><h:commandButton id="businessGroupButton"
																	alt="Select" image="../../images/select.gif"
																	style="cursor: hand"
																	onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','benefitComponentCreateForm:businessGroupTxtHidden',2,2); return false;"
																	tabindex="4" /></TD>
														</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
														<TR>
																<TD width="547"><h:outputText id="marketBusinessUnitLabel"
																	value="Market Business Unit"
																	/></TD>
																<TD width="210">
																<DIV id="marketBusinessUnit" class="selectDataDisplayDiv"></DIV>
																<h:inputHidden id="marketBusinessUnitHidden"
																	value="#{basicSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
																</TD>
																<TD width="100"><h:commandButton id="MarketBusinessUnitButton"
																	alt="Select" image="../../images/select.gif"
																	style="cursor: hand"
																	onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnit','benefitComponentCreateForm:marketBusinessUnitHidden',2,2); return false;"
																	tabindex="5" /></TD>
														</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
														
													</TABLE>
										</FIELDSET>
									</TD>
								</TR>
									<TR>
									<TD>&nbsp;<h:outputText id="align"
										value=" " />
									</TD>
									<TD><h:panelGrid id="panelTable"
								 	binding="#{basicSearchBackingBean.panel}"  >    
										</h:panelGrid> </TD>
									<td colspan="2">&nbsp;</td>
								</TR>

								<TR>
									<TD> <BR>&nbsp;<h:commandButton id="basSearch" value=" Search " tabindex="12"
										styleClass="wpdButton" action ="#{basicSearchBackingBean.search}">
									</h:commandButton></TD>
									<td colspan="3">&nbsp;</td>
								</TR>
						</TABLE>
						</fieldset>
						<!--	End of Page data	--> <BR>



						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
			
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
	
	copyHiddenToDiv_ewpd('benefitComponentCreateForm:lobTxtHidden','lobDiv',2,2);
	copyHiddenToDiv_ewpd('benefitComponentCreateForm:businessEntityTxtHidden','businessEntityDiv',2,2);
	copyHiddenToDiv_ewpd('benefitComponentCreateForm:businessGroupTxtHidden','businessGroupDiv',2,2);	
	copyHiddenToDiv_ewpd('benefitComponentCreateForm:marketBusinessUnitHidden','marketBusinessUnit',2,2);	
	
	document.getElementById('benefitComponentCreateForm:nameTxt').focus();

	function showit(e){
		crossobj=document.getElementById("postit");
		var temp = document.getElementById("benefitComponentCreateForm:nameTxt");
		crossobj.style.display="block";
		crossobj.style.visibility="visible"
	}

	function closeit(){
		crossobj=document.getElementById("postit");
		crossobj.style.visibility="hidden"
		crossobj.style.display="none";
	}
</script>

<!-- <script type="text/javascript">

function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function() {
            if (oldonload) {
                oldonload();
            }
            func();
        }
    }
}

addLoadEvent(function() {
		var crossobj;

		if(null!=document.getElementsByName('benefitComponentCreateForm:panelTable')[0]){
		}else if(null!=document.getElementsByName('benefitComponentCreateForm:panelTable')[1]){
		}else if(null!=document.getElementsByName('benefitComponentCreateForm:panelTable')[2]){
		}else if(null!=document.getElementsByName('benefitComponentCreateForm:panelTable')[3]){
		}else if(null!=document.getElementsByName('benefitComponentCreateForm:panelTable')[4]){
		}else if(null!=document.getElementsByName('benefitComponentCreateForm:panelTable')[5]){
		}else if(null!=document.getElementsByName('benefitComponentCreateForm:panelTable')[6]){
		}else{
			document.getElementById('benefitComponentCreateForm:basSearch').disabled=true;	
        }

})

</script> -->
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="basicsearchprint" /></form>
</HTML>
