<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform" CONTENT="NO-CACHE">
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
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('advancedSearchForm:advSearch');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:12px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="Search >> Advanced Search >> Print">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
		<tr>
			<td><h:form styleClass="form" id="advancedSearchForm" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR><TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message /></TD>
									<td><div id='Div' style="display:none; " class='errorDiv' /><li id=errorItem>Atleast one Search Criteria should be selected. </li></div></td>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->

						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="2">
								
								<TR>
									<TD nowrap="nowrap" valign="top">&nbsp;<h:outputText id="searchFor" 
										value="Search For *"/>
									</TD>
									<TD valign="top">
										<h:panelGrid id="panelValue"
								 	binding="#{advancedSearchBackingBean.selectObjectPanel}"  >    
										</h:panelGrid>
									
								</TR>
								<TR>
									<TD> &nbsp;</TD>
									<TD>
										<div id="contractDiv" style="visibility:visible;">
											<FIELDSET> 
												<LEGEND><FONT color="black">Criteria</FONT></LEGEND>
												<h:panelGrid binding="#{advancedSearchBackingBean.printCriteriaPanel}"  >    
												</h:panelGrid> 
											</FIELDSET>
										</div>
									</TD>
								</TR>			
								<TR>
									<TD nowrap="nowrap">&nbsp;<h:outputText id="limitedTo" value=" Limited To" />
									</TD>
									<TD >
										<TABLE>
											<TR>
												<TD style="width:125px">
													<h:outputText id="lobLabel" value="Line of Business " />
												</TD>
												<TD>
												<h:outputText id="lob" value="#{advancedSearchBackingBean.lobPrintString}"> </h:outputText>
												</TD>
											</TR>
										</TABLE>
									</TD>
									
								</TR>
								<TR>
									<TD width="87">&nbsp;</TD>
									<TD width="631">
										<TABLE>
											<TR>
												<TD style="width:125px"><h:outputText id="businessEntityLabel" value="Business Entity "/>
												<TD>
												<h:outputText id="be" value="#{advancedSearchBackingBean.businessEntityPrintString}"> </h:outputText>
												</TD>
											</TR>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD width="87">&nbsp;</TD>
									<TD width="631">
										<TABLE>
											<TR>
												<TD style="width:125px"><h:outputText id="businessGroupLabel" value="Business Group "/> </TD>
												<TD>
												<h:outputText id="bg" value="#{advancedSearchBackingBean.businessGroupPrintString}"> </h:outputText>
												</TD>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD width="87">&nbsp;</TD>
									<TD width="631">
										<TABLE>
											<TR>
												<TD style="width:125px"><h:outputText id="marketBusinessUnitLabel" value="Market Business Unit "/> </TD>
												<TD>
												<h:outputText id="mbu" value="#{advancedSearchBackingBean.marketBusinessUnitPrintString}"> </h:outputText>
												</TD>
										</TABLE>
									</TD>
								</TR>
								<TR>
								
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
			
		</tr>
	</table>
	
	</BODY>
</f:view>

<script language="JavaScript"><!--
	
	
	function show(e){
		var crossobj;
		document.getElementById('contractDiv').style.display = 'none';
		document.getElementById('productDiv').style.display = 'none';
		document.getElementById('productStructureDiv').style.display = 'none';
		document.getElementById('benefitComponentDiv').style.display = 'none';
		document.getElementById('benefitDiv').style.display = 'none';
		document.getElementById('benefitLevelDiv').style.display = 'none';
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
window.print();
</script>
<script type="text/javascript">



</script>

</HTML>