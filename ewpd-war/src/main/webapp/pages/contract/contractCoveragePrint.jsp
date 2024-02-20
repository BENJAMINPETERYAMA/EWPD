<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/BenefitLevel.java" --%><%-- /jsf:pagecode --%>
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
<STYLE type="text/css">
.gridColumn0{
	width: 0%;	
}
.gridColumn8{
	width: 8%;	
}
.gridColumn22{
	width: 22%;
	text-align:left;
}
.gridColumn21{
	width: 21%;
	text-align:left;
}
.gridColumn11{
	width: 11%;	
	text-align:left;
}
.gridColumn22{
	width: 22%;	
	text-align:left;
}
.gridColumn25{
	width: 25%;
	text-align:left;
}
 .gridColumn16{
	width: 16%;
	text-align:left;
}
.gridColumn12{
	width: 12%;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	text-align:left;
}
.gridColumn9{
	width: 9%;
	text-align:left;
}
.gridColumn8{
	width: 8%;
	text-align:left;
}
.gridColumn7{
	width: 7%;
}
 .gridColumn3{
	width: 3%;
	text-align:left;
}
 .gridColumn5{
	width: 5%;
	text-align:left;
}
TABLE.border{
	border-collapse: collapse;
	border-width: 1px;
	border: outset 1px;
}

TABLE.border  TD{
	padding: 4px;
	background-color: #$background;
	color: #$backtext;
	margin: 0;
	border: inset 1pt;
}

</style>
<TITLE> Print Benefit</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
<BODY onload="return hideNotesColumn(false);">

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><h:form styleClass="form" id="benefitLevelFormPrint">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					
					</tr>
					<tr><td>&nbsp; </td></tr>
					<TR>
					<TD>  <FIELDSET style="margin-left:20px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:87%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">
                                    </h:outputText>
                              </FIELDSET>
					</TD>
				</TR>
					<TR>						
						<TD colspan="2" valign="top" class="ContentArea"><h:outputText
							value="No Standard Definitions Available."
							rendered="#{contractCoverageBackingBean.printBenftDefnList == null}"
							styleClass="dataTableColumnHeader" />
						<div id="benefitDefitionsDiv">

						<table width="100%" border="0" bordercolor="green" cellspacing="1"
							cellpadding="1">
							<tr>
								<td valign="top" class="ContentArea">
								<fieldset style="width: 90%">
								<DIV id="noBenefitDefinitions" style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000;
						background-color:#FFFFFF;">No Benefit Definitions available.</DIV>
								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="3" cellspacing="1" border="0" bordercolor="red">
									<TR>
										<TD colspan="1" class="ContentArea" ><div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">Associated Benefit Levels</div></TD>
									</TR>
									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
										<table class="smallfont" id="resultsTable" width="100%"
											cellpadding="1" cellspacing="1" border="0">											
											<tr>
												<td>
												<div id="searchResultdataTableDiv" style="overflow:auto;">													
													<h:dataTable  id="bComponentDataTable"
													rendered="#{contractCoverageBackingBean.printBenftDefnList != null}"
													value="#{contractCoverageBackingBean.printBenftDefnList}"
													var="singleValue" style="display:block; text-align:left;" 
													 width="100%" border="1" styleClass="border">
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="Description"/>
                          							</f:facet>
														<h:outputText id="level"
															value="#{singleValue.levelDesc}"/>
													</h:column>
													<h:column>
						 							<f:facet name="header" >
						                              <h:outputText value="Term"/>
						                          	</f:facet>
														<h:outputText id="term"
															value="#{singleValue.termDesc}"/>
													</h:column>
													<h:column>
 													<f:facet name="header" >
                              							<h:outputText value="Frequency - Qualifier"/>
                          							</f:facet>
														<h:outputText id="qualifier"
															value="#{singleValue.qualifierDesc}"/>
													</h:column>
													<h:column>
						 							<f:facet name="header" >
						                              <h:outputText value="PVA"/>
						                          	</f:facet>
														<h:outputText id="pvaVal"
															value="#{singleValue.providerArrangementId}"/>
													</h:column>
													<h:column>
						 							<f:facet name="header" >
						                              <h:outputText value="Format"/>
						                          	</f:facet>
														<h:outputText id="dataTypeLGND"
															value="#{singleValue.dataTypeDesc}"/>
													</h:column>
													<h:column>
 													<f:facet name="header" >
                              							<h:outputText value="Benefit Value"/>
                          							</f:facet>
														<h:outputText id="benfitVal"
															value="#{singleValue.benefitValue}"/>
													</h:column>
													<h:column>
 													<f:facet name="header" >
                              							<h:outputText value="Reference"/>
                          							</f:facet>
														<h:outputText id="reference"
															value="#{singleValue.referenceDesc}"/>
													</h:column>
												</h:dataTable></div>

												</td>
											</tr>
											<tr>
												<TD></TD>
											</tr>
						<tr><td><div id="panel2HeaderTier" class="tabTitleBar"
								style="position:relative;width:100% ">Associated Tiered Benefit Levels</div></td></tr>
							<tr>
								<td bordercolor="#cccccc">
								<DIV id="resultHeaderDiv1"
										style="position:relative;background-color:#FFFFFF;"><h:panelGrid
										id="resultHeaderTable1"
										binding="#{contractCoverageBackingBean.tierHeaderPanelForPrint}"
										rowClasses="dataTableOddRow">
									</h:panelGrid></DIV>
								<DIV id="panelContent"
									style="position:relative;background-color:#FFFFFF;height:165">
								<h:panelGrid id="benresultHeaderTable1"
									binding="#{contractCoverageBackingBean.panelForTierPrint}">
								</h:panelGrid></DIV>
								</td>
							</tr>


											
											<tr>
												<TD></TD>
											</tr>

										</table>
										</TD>


									</TR>
								</TABLE>
								<!--	Start of Table for actual Data	--></fieldset>
								</td>
							</tr>
						</table>
						</div>
						<%--/DIV> --%> <!--	End of Page data	--></TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />

					<!-- End of hidden fields  -->
				</h:commandLink>
			</h:form></td>

		</tr>
	</table>
	</BODY>
</f:view>
<script language="JavaScript" type="text/javascript">
	var tableObject = document.getElementById('benefitLevelFormPrint:bComponentDataTable');

	if(tableObject.rows.length > 0){
	 	var divObj = document.getElementById('noBenefitDefinitions');
		divObj.style.visibility = "hidden";
	}
	else{
		var divObj = document.getElementById('noBenefitDefinitions');
		divObj.style.visibility = "visible";
		divObj.style.height = "2px";
		document.getElementById('resultsTable').style.visibility = "hidden";

		
	}

	if(tableObject.rows.length > 0){
		if(document.getElementById('benefitLevelFormPrint:bComponentDataTable') != null) {		
			setColumnWidth('benefitLevelFormPrint:bComponentDataTable','18%:16%:14%:7%:9%:9%:25%');	
			//setColumnWidth('headerTable','18%:16%:14%:7%:9%:15%:21%');	

		}}

//the function is to hide the notes column 
//since it is the print page
 function hideNotesColumn(do_show) {
    var stl;
    if (do_show) stl = 'block'
    else         stl = 'none';
 	var tables = document.getElementsByTagName('table');
	for(i =0; i<tables.length; i++) {
		var tableid = tables[i].id
		if(tableid.search(/^benefitLevelFormPrint:notesColumnId/)!=-1) {			
			var tierTable  = document.getElementById(tableid);			
	    	var rows = tierTable.getElementsByTagName('tr');		
	    	for (var row=0; row<rows.length;row++) {	     		
 				var noteCell = rows[row].getElementsByTagName('td')[7];
				noteCell.style.display=stl;
	    	}
		}
    }
  }


hideIfNoTierData();
function hideIfNoTierData(){
if(null!=document.getElementById("benefitLevelFormPrint:benresultHeaderTable1")){
	var noOfRows = document.getElementById('benefitLevelFormPrint:benresultHeaderTable1').rows.length ;
	if(noOfRows ==0){
	document.getElementById('panel2HeaderTier').style.visibility = 'hidden';
	document.getElementById('resultHeaderDiv1').style.visibility = 'hidden';
	document.getElementById('panelContent').style.visibility = 'hidden';
	}
	else{
	document.getElementById('panel2HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv1').style.visibility = 'visible';
	document.getElementById('panelContent').style.visibility = 'visible';
	}
}
}

</script>


<script>window.print();</script>
</HTML>
