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
<TITLE>Search Engine</TITLE>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('advancedSearchForm:advSearch');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="advancedSearchForm" >
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
									<td><div id='Div' style="display:none; " class='errorDiv' /><li id=errorItem>Atleast one Search Criteria should be selected. </li></div></td>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">							
							<tr>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										
										<td width="100%" class="tabNormal"><h:commandLink action="#{menuBean.actionBasicSearch}" immediate="true">
											<h:outputText id="basicSearchTabTable" value=" Basic Search" /></h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabActive">
											<h:outputText id="advancweSearchTabTable"
												value=" Advanced Search" />
										</td>
											<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
										
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
								
								<TR>
									<TD nowrap="nowrap" valign="top"><BR/><h:outputText id="searchFor" 
										value="Search For *"/>
									</TD>
									<TD></TD>
								</TR>
								<TR>
									<TD></TD>
						
											<TD colspan="4">
												<FIELDSET style="margin-left:6px;margin-right:-2px;padding-bottom:1px;padding-top:6px;width:450">
													<LEGEND> <FONT color="black">Limited To</FONT></LEGEND>
													<TABLE border="0" cellspacing="5" cellpadding="3">
														<TR>
															<TD width="500"><h:outputText id="lobLabel"
																value="Line of Business " />
															</TD>
															<TD width="180"><DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
												  			<h:inputHidden id="lobTxtHidden"
																value="#{advancedSearchBackingBean.lineOfBusiness}"></h:inputHidden>
															</TD>
															<TD width="80"><h:commandButton id="lobButton" alt="Select"
																image="../../images/select.gif" style="cursor: hand"
						                         		    	onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lobDiv','advancedSearchForm:lobTxtHidden',2,2); return false;"
															     /></TD>	
														</TR>
														<TR>
																<TD width="500"><h:outputText id="businessEntityLabel"
																	value="Business Entity "
																	 />
																</TD>
																<TD width="180">
																	<DIV id="businessEntityDiv" class="selectDataDisplayDiv"></DIV>
																	<h:inputHidden id="businessEntityTxtHidden"
																	value="#{advancedSearchBackingBean.businessEntity}"></h:inputHidden>
																</TD>
																<TD width="80"><h:commandButton id="businessEntityButton"
																	alt="Select" image="../../images/select.gif"
																	style="cursor: hand"
																	onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','advancedSearchForm:businessEntityTxtHidden',2,2); return false;"
																	/></TD>	
														</TR>
														<TR>
																<TD width="500"><h:outputText id="businessGroupLabel"
																	value="Business Group "
																	/></TD>
																<TD width="180">
																	<DIV id="businessGroupDiv" class="selectDataDisplayDiv"></DIV>
																	<h:inputHidden id="businessGroupTxtHidden"
																	value="#{advancedSearchBackingBean.businessGroup}"></h:inputHidden>
																</TD>
																<TD width="80"><h:commandButton id="businessGroupButton"
																	alt="Select" image="../../images/select.gif"
																	style="cursor: hand"
																	onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','advancedSearchForm:businessGroupTxtHidden',2,2); return false;"
																	/></TD>
														</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
														<TR>
																<TD width="500"><h:outputText id="marketBusinessUnitLabel"
																	value="Market Business Unit"
																	/></TD>
																<TD width="180">
																	<DIV id="marketBusinessUnit" class="selectDataDisplayDiv"></DIV>
																	<h:inputHidden id="marketBusinessUnitHidden"
																	value="#{advancedSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
																</TD>
																<TD width="80"><h:commandButton id="MarketBusinessUnitButton"
																	alt="Select" image="../../images/select.gif"
																	style="cursor: hand"
																	onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnit','advancedSearchForm:marketBusinessUnitHidden',2,2); return false;"
																	/></TD>
														</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
													</TABLE>
										</FIELDSET>
									</TD>
								</TR>
								<TR>
									<TD></TD>	
									<TD valign="top">
										<h:panelGrid id="panelValue" binding="#{advancedSearchBackingBean.panel}"  >    
										</h:panelGrid>
									</TD>
								</TR>
								<TR>
									<TD> &nbsp;</TD>
									<TD>
										<div id="contractDiv" style="display:none;">
											<FIELDSET> 
												<LEGEND><FONT color="Red">Criteria</FONT></LEGEND>
												<h:panelGrid binding="#{advancedSearchBackingBean.contractPanel}"  >    
												</h:panelGrid>
											</FIELDSET>
										</div>
										<div id="productDiv" style="display:none;">
											<FIELDSET> 
												<LEGEND><FONT color="black">Criteria</FONT></LEGEND>
												<h:panelGrid binding="#{advancedSearchBackingBean.productPanel}"  >    
												</h:panelGrid>
											</FIELDSET>
										</div>
										<div id="productStructureDiv" style="display:none;">
											<FIELDSET> 
												<LEGEND><FONT color="black">Criteria</FONT></LEGEND>
												<h:panelGrid binding="#{advancedSearchBackingBean.productstructurePanel}"  >    
												</h:panelGrid> 
											</FIELDSET>
										</div>
										<div id="benefitComponentDiv" style="display:none;">
											<FIELDSET> 
												<LEGEND><FONT color="black">Criteria</FONT></LEGEND>
												<h:panelGrid binding="#{advancedSearchBackingBean.benefitcomponentPanel}"  >    
												</h:panelGrid>	 
											</FIELDSET>
										</div>
										<div id="benefitDiv" style="display:none;">
											<FIELDSET> 
												<LEGEND><FONT color="black">Criteria</FONT></LEGEND>
												<h:panelGrid binding="#{advancedSearchBackingBean.benefitPanel}"  >    
												</h:panelGrid> 
											</FIELDSET>
										</div>
										<div id="benefitlevelDiv" style="display:none;">
											<FIELDSET> 
												<LEGEND><FONT color="black">Criteria</FONT></LEGEND>
												<h:panelGrid id="panelTable"
								 					binding="#{advancedSearchBackingBean.benefitlevelPanel}"  >    
												</h:panelGrid> 
											</FIELDSET>
										</div>
										<div id="notesDiv" style="display:none;">
											<FIELDSET> 
												<LEGEND><FONT color="black">Criteria</FONT></LEGEND>
												<h:panelGrid 
								 					binding="#{advancedSearchBackingBean.notesPanel}"  >    
												</h:panelGrid> 
											</FIELDSET>
										</div>
				
									</TD>
								</TR>			
								
								<TR>
									<TD width="87"><br/><br/>&nbsp;<h:commandButton value=" Search " id="advSearch" 
										styleClass="wpdButton" action ="#{advancedSearchBackingBean.search}">
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

	copyHiddenToDiv_ewpd('advancedSearchForm:lobTxtHidden','lobDiv',2,2);
	copyHiddenToDiv_ewpd('advancedSearchForm:businessEntityTxtHidden','businessEntityDiv',2,2);
	copyHiddenToDiv_ewpd('advancedSearchForm:businessGroupTxtHidden','businessGroupDiv',2,2);	
	copyHiddenToDiv_ewpd('advancedSearchForm:marketBusinessUnitHidden','marketBusinessUnit',2,2);	
function markAsChecked(rowNum){

var text = document.getElementById("advancedSearchForm:textBoxCriteria"+rowNum).value;
	if(text.length > 0){
		document.getElementById("advancedSearchForm:markChecked"+rowNum).checked = true;
	}else{	
		document.getElementById("advancedSearchForm:markChecked"+rowNum).checked = false;
    }	
}

	function show(e){

		
		var crossobj;
		document.getElementById('contractDiv').style.display = 'none';
		document.getElementById('productDiv').style.display = 'none';
		document.getElementById('productStructureDiv').style.display = 'none';
		document.getElementById('benefitComponentDiv').style.display = 'none';
		document.getElementById('benefitDiv').style.display = 'none';
		document.getElementById('benefitlevelDiv').style.display = 'none';
		document.getElementById('notesDiv').style.display = 'none';

		if(e.value =='CONTRACT'){
			crossobj=document.getElementById('contractDiv');	
		}else if(e.value=='PRODUCT'){
			crossobj=document.getElementById('productDiv');		
		}else if(e.value=='PRODUCT_STRUCTURES'){
			crossobj=document.getElementById('productStructureDiv');	
		}else if(e.value=='BENEFIT_COMPONENTS'){
			crossobj=document.getElementById('benefitComponentDiv');		
		}else if(e.value=='BENEFIT'){
			crossobj=document.getElementById('benefitDiv');		
		}else if(e.value=='BENEFIT_LEVEL'){
			crossobj=document.getElementById('benefitLevelDiv');		
		}else if(e.value=='NOTES'){
			crossobj=document.getElementById('notesDiv');
		}
		if(null != crossobj){
			crossobj.style.display="block";
			crossobj.style.visibility="visible";
		}	
	}


</script>
<script type="text/javascript">

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

        if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked==true){
         show(document.getElementsByName('advancedSearchForm:rdObjectType')[1]);
        }else if (null!=document.getElementsByName('advancedSearchForm:rdObjectType')[2]&&document.getElementsByName('advancedSearchForm:rdObjectType')[2].checked==true){
		  show(document.getElementsByName('advancedSearchForm:rdObjectType')[2]);
		}else if (null!=document.getElementsByName('advancedSearchForm:rdObjectType')[3]&&document.getElementsByName('advancedSearchForm:rdObjectType')[3].checked==true){
		  show(document.getElementsByName('advancedSearchForm:rdObjectType')[3]);
		}else if (null!=document.getElementsByName('advancedSearchForm:rdObjectType')[4]&&document.getElementsByName('advancedSearchForm:rdObjectType')[4].checked==true){
		  show(document.getElementsByName('advancedSearchForm:rdObjectType')[4]);
		}else if (null!=document.getElementsByName('advancedSearchForm:rdObjectType')[4]&&document.getElementsByName('advancedSearchForm:rdObjectType')[4].checked==true){
		  show(document.getElementsByName('advancedSearchForm:rdObjectType')[4]);
		}else if (null!=document.getElementsByName('advancedSearchForm:rdObjectType')[5]&&document.getElementsByName('advancedSearchForm:rdObjectType')[5].checked==true){
		  show(document.getElementsByName('advancedSearchForm:rdObjectType')[5]);
		}else if (null!=document.getElementsByName('advancedSearchForm:rdObjectType')[6]&&document.getElementsByName('advancedSearchForm:rdObjectType')[6].checked==true){		
		  show(document.getElementsByName('advancedSearchForm:rdObjectType')[6]);
		}else if (null!=document.getElementsByName('advancedSearchForm:rdObjectType')[7]&&document.getElementsByName('advancedSearchForm:rdObjectType')[7].checked==true){
		  show(document.getElementsByName('advancedSearchForm:rdObjectType')[7]);
		}else{
			    
				if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].value=='CONTRACT'){
			        crossobj=document.getElementById('contractDiv');
					show('CONTRACT');	
					document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked=true;	 
			    }else if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].value=='PRODUCT'){
					crossobj=document.getElementById('productDiv');
					show('PRODUCT');	
					document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked=true;
				}else if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].value=='PRODUCT_STRUCTURES'){
					crossobj=document.getElementById('productStructureDiv');	
					show('PRODUCT_STRUCTURES');
					document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked=true;
				}else if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].value=='BENEFIT_COMPONENTS'){
					crossobj=document.getElementById('benefitComponentDiv');		
					show('BENEFIT_COMPONENTS');
					document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked=true;
				}else if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].value=='BENEFIT'){
					crossobj=document.getElementById('benefitDiv');
					show('BENEFIT');		
					document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked=true;
				}else if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].value=='BENEFIT_LEVEL'){
					crossobj=document.getElementById('benefitLevelDiv');
					show('BENEFIT_LEVEL');		
					document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked=true;
				}else if(null!=document.getElementsByName('advancedSearchForm:rdObjectType')[1]&&document.getElementsByName('advancedSearchForm:rdObjectType')[1].value=='NOTES'){
					crossobj=document.getElementById('notesDiv');
					show('NOTES');
					document.getElementsByName('advancedSearchForm:rdObjectType')[1].checked=true;
				}else{
			       document.getElementById('advancedSearchForm:advSearch').disabled=true;	
			    } 
				if(null != crossobj){
					crossobj.style.display="block";
					crossobj.style.visibility="visible";
				}
     }

})  

 
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="advancedsearchprint" /></form>
</HTML>