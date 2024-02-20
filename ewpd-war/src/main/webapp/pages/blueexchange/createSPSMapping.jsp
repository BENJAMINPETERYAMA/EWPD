<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
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

<TITLE>Create SPSMapping</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('spsMappingForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="spsMappingForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:300px"></DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{spsMappingBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE cellpadding="3">
								<TBODY>
									<TR>
										<TD width="40%"><h:outputText id="SPS_Parameter_text"
											value="SPS Parameter ID* "
											styleClass="#{spsMappingBackingBean.spsParamFlag? 'mandatoryError': 'mandatoryNormal'}" />
										</TD>
										<h:inputHidden id="SPS_Parameter_Hidden"
											value="#{spsMappingBackingBean.spsParameter}"></h:inputHidden>
										<TD width="28%"><h:inputHidden id="SPS_Parameter_hid"></h:inputHidden>
										<div id="SPS_Parameter_id_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton alt="SPS Parameter"
											id="SPS_Parameter_Button" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeSPSIdPopup.jsp'+getUrl()+'?lookUpAction='+'27'+'&parentCatalog='+'reference'+'&title='+'SPS Parameter'+'&temp=' + Math.random(), 'spsMappingForm:SPS_Parameter_hid','spsMappingForm:SPS_Parameter_Hidden', 2, 1);splitIDAndDescriptionForSPSParameter();return false;"
											tabindex="1">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="SPS_Param_desc_text"
											value="Description " /></TD>
										<TD width="28%">
										<div id="SPS_Parameter_desc_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%">&nbsp;</TD>
									</TR>

									<TR>
										<TD valign="top" width="40%"><h:outputText id="EB01_text"
											value="EB01      - Eligibility or Benefit Information" /></TD>
										<h:inputHidden id="EB01_Hidden"
											value="#{spsMappingBackingBean.eb01Value}"></h:inputHidden>
										<TD align="left" nowrap width="28%">
										<div id="EB01_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton
											alt="Eligibility or Benefit Information" id="EB01_Button"
											image="../../images/select.gif"
											onclick="callEb01();return false;" tabindex="2">
										</h:commandButton></TD>
									</TR>

									<TR id="EB02TR">
										<TD width="40%"><h:outputText id="EB02_text"
											value="EB02      - Coverage Level Code" /></TD>
										<h:inputHidden id="EB02_Hidden"
											value="#{spsMappingBackingBean.eb02Value}"></h:inputHidden>
										<TD width="28%">
										<div id="EB02_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton alt="Coverage Level Code"
											id="EB02_Button" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeCodesPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'EB02'+'&title='+'Coverage Level Code', 'EB02_Div','spsMappingForm:EB02_Hidden', 2, 1);return false;"
											tabindex="3">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="EB06_text"
											value="EB06      - Time Period Qualifier" /></TD>
										<h:inputHidden id="EB06_Hidden"
											value="#{spsMappingBackingBean.eb06Value}"></h:inputHidden>
										<TD width="28%">
										<div id="EB06_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton alt="Time Period Qualifier"
											id="EB06_Button" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeCodesPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'EB06'+'&title='+'Time Period Qualifier', 'EB06_Div','spsMappingForm:EB06_Hidden', 2, 1);return false;"
											tabindex="4">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="EB09_text"
											value="EB09      - Quantity Qualifier " /></TD>
										<h:inputHidden id="EB09_Hidden"
											value="#{spsMappingBackingBean.eb09Value}"></h:inputHidden>
										<TD width="28%">
										<div id="EB09_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton alt="Industry Code"
											id="EB09_Button" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeCodesPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'EB09'+'&title='+'Quantity Qualifier', 'EB09_Div','spsMappingForm:EB09_Hidden', 2, 1);return false;"
											tabindex="5">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="HSD1_text"
											value="HSD1      - Quantity Qualifier" /></TD>
										<h:inputHidden id="HSD1_Hidden"
											value="#{spsMappingBackingBean.hsd1Value}"></h:inputHidden>
										<TD width="192">
										<div id="HSD1_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton alt="Quantity Qualifier"
											id="HSD1_Button" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeCodesPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'HSD01'+'&title='+'Quantity Qualifier', 'HSD1_Div','spsMappingForm:HSD1_Hidden', 2, 1);return false;"
											tabindex="6">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="HSD2_text"
											value="HSD2      - Benefit Quantity" /></TD>
										<h:inputHidden id="HSD2_Hidden"
											value="#{spsMappingBackingBean.hsd2Value}"></h:inputHidden>
										<TD width="28%"><h:inputText styleClass="formInputField"
											maxlength="2" tabindex="7" id="HSD2"
											value="#{spsMappingBackingBean.hsd2Value}"
											onkeypress="isNum1(this.id);" /></TD>
										<TD width="32%">&nbsp;</TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="HSD3_text"
											value="HSD3      - Unit or Basis for Measurement Code" /></TD>
										<h:inputHidden id="HSD3_Hidden"
											value="#{spsMappingBackingBean.hsd3Value}"></h:inputHidden>
										<TD width="28%">
										<div id="HSD3_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton
											alt="Unit or Basis for Measurment Code" id="HSD3_Button"
											image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeCodesPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'HSD03'+'&title='+'Unit or Basis for Measurement Code', 'HSD3_Div','spsMappingForm:HSD3_Hidden', 2, 1);return false;"
											tabindex="8">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="HSD4_text"
											value="HSD4      - Sample Selection Modulus" /></TD>
										<h:inputHidden id="HSD4_Hidden"
											value="#{spsMappingBackingBean.hsd4Value}"></h:inputHidden>
										<TD width="28%"><h:inputText styleClass="formInputField"
											maxlength="2" tabindex="9" id="HSD4"
											value="#{spsMappingBackingBean.hsd4Value}"
											onkeypress="isNum1(this.id);" /></TD>
										<TD width="32%">&nbsp;</TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="HSD5_text"
											value="HSD5      - Time Period Qualifier" /></TD>
										<h:inputHidden id="HSD5_Hidden"
											value="#{spsMappingBackingBean.hsd5Value}"></h:inputHidden>
										<TD width="28%">
										<div id="HSD5_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="32%"><h:commandButton alt="Time Period Qualifier"
											id="HSD5_Button" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeCodesPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'HSD05'+'&title='+'Time Period Qualifier', 'HSD5_Div','spsMappingForm:HSD5_Hidden', 2, 1);return false;"
											tabindex="10">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="40%"><h:outputText id="HSD6_text"
											value="HSD6      - Number of Periods" /></TD>
										<h:inputHidden id="HSD6_Hidden"
											value="#{spsMappingBackingBean.hsd6Value}"></h:inputHidden>
										<TD width="28%"><h:inputText styleClass="formInputField"
											maxlength="2" tabindex="11" id="HSD6"
											value="#{spsMappingBackingBean.hsd6Value}"
											onkeypress="isNum1(this.id);" /></TD>
										<TD width="32%">&nbsp;</TD>
									</TR>
									<TR>

										<TD valign="top" width="40%"><h:outputText id="accummulatorSPSID"
											value="Accumulator SPS ID" />
										</TD>
										<h:inputHidden id="accummulatorSPSID_Hidden"
													value="#{spsMappingBackingBean.accummulatorSPSID}"></h:inputHidden>
										<TD width="28%">
										<div id="accummulatorSPSID_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="43%"><h:commandButton
											alt="Eligibility or Benefit Information" id="accummulatorSPSID_Button"
											image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd1('../blueexchange/blueExchangeAccumulatorSPSIdSearch.jsp'+getUrl()+'?lookUpAction='+'11'+'&parentCatalog='+'ACCUMULATOR REFERENCE'+'&title='+'Accummulator SPS ID', 'accummulatorSPSID_Div','spsMappingForm:accummulatorSPSID_Hidden', 2, 1);return false;"
											tabindex="5">
										</h:commandButton></TD>
									</TR>
									<TR>
										<TD valign="bottom" width="123"><h:commandButton
											value="Create" styleClass="wpdButton" id="createButton"
											tabindex="12"
											action="#{spsMappingBackingBean.createSPSMapping}">
										</h:commandButton></TD>
										<TD width="192">&nbsp;</TD>
										<TD width="293">&nbsp;</TD>
									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>


<script language="javascript">

	// Space for page realated scripts
if(replaceTildaWithHyphen('spsMappingForm:EB01_Hidden') != null){
document.getElementById('EB01_Div').innerText=replaceTildaWithHyphen('spsMappingForm:EB01_Hidden');
document.getElementById('EB01_Div').style.overflowX="hidden";
}
if(replaceTildaWithHyphen('spsMappingForm:EB02_Hidden') != null){
document.getElementById('EB02_Div').innerText=replaceTildaWithHyphen('spsMappingForm:EB02_Hidden');
document.getElementById('EB02_Div').style.overflowX="hidden";
}
if(replaceTildaWithHyphen('spsMappingForm:EB06_Hidden') != null){
document.getElementById('EB06_Div').innerText=replaceTildaWithHyphen('spsMappingForm:EB06_Hidden');
document.getElementById('EB06_Div').style.overflowX="hidden";
}
if(replaceTildaWithHyphen('spsMappingForm:EB09_Hidden') != null){
document.getElementById('EB09_Div').innerText=replaceTildaWithHyphen('spsMappingForm:EB09_Hidden');
document.getElementById('EB09_Div').style.overflowX="hidden";
}
if(replaceTildaWithHyphen('spsMappingForm:HSD1_Hidden') != null){
document.getElementById('HSD1_Div').innerText=replaceTildaWithHyphen('spsMappingForm:HSD1_Hidden');
document.getElementById('HSD1_Div').style.overflowX="hidden";
}
if(replaceTildaWithHyphen('spsMappingForm:HSD3_Hidden') != null){
document.getElementById('HSD3_Div').innerText=replaceTildaWithHyphen('spsMappingForm:HSD3_Hidden');
document.getElementById('HSD3_Div').style.overflowX="hidden";
}
if(replaceTildaWithHyphen('spsMappingForm:HSD5_Hidden') != null){
document.getElementById('HSD5_Div').innerText=replaceTildaWithHyphen('spsMappingForm:HSD5_Hidden');
document.getElementById('HSD5_Div').style.overflowX="hidden";
}
if(replaceTildaWithHyphen('spsMappingForm:accummulatorSPSID_Hidden') != null){
document.getElementById('accummulatorSPSID_Div').innerText = replaceTildaWithHyphen('spsMappingForm:accummulatorSPSID_Hidden');
document.getElementById('accummulatorSPSID_Div').style.overflowX="hidden";
}

var eb01 = document.getElementById('EB01_Div').innerText;
var eb01val = eb01.substring(0,1);
if(!(eb01val=='C' || eb01val=='G')){
	EB02TR.style.display='none';  
}else{
	EB02TR.style.display='';
}


if(document.getElementById('spsMappingForm:SPS_Parameter_Hidden').value != null &&document.getElementById('spsMappingForm:SPS_Parameter_Hidden').value != ''){
	var hy_str=document.getElementById('spsMappingForm:SPS_Parameter_Hidden').value;
	var val1=hy_str.split('~');
	document.getElementById('SPS_Parameter_id_Div').innerText=val1[1];
    document.getElementById('SPS_Parameter_desc_Div').innerText=val1[0];
}

function splitIDAndDescriptionForSPSParameter(){
	var hy_str=document.getElementById('spsMappingForm:SPS_Parameter_Hidden').value;
	var val1=hy_str.split('~');
    if(val1.length==2){
	   document.getElementById('SPS_Parameter_id_Div').innerText=val1[1];
	   document.getElementById('SPS_Parameter_desc_Div').innerText=val1[0];
    }else{
		document.getElementById('SPS_Parameter_id_Div').innerText="";
   		document.getElementById('SPS_Parameter_desc_Div').innerText="";
	}
}

function callEb01(){
	ewpdModalWindow_ewpd1('../blueexchange/blueExchangeCodesPopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'EB01'+'&title='+'Eligibility or Benefit Information', 'EB01_Div','spsMappingForm:EB01_Hidden', 2, 1);
	if(replaceTildaWithHyphen('spsMappingForm:EB01_Hidden') != null){
		var eb01 = document.getElementById('EB01_Div').innerText;
		var eb01val = eb01.substring(0,1);
		if(!(eb01val=='C' || eb01val=='G')){
			EB02TR.style.display='none';  
			document.getElementById('EB02_Div').innerText = '';
			document.getElementById('spsMappingForm:EB02_Hidden').value = '';
		}else{
			EB02TR.style.display='';
		}
	}else{
		EB02TR.style.display='none';  
		document.getElementById('EB02_Div').innerText = '';
		document.getElementById('spsMappingForm:EB02_Hidden').value = '';
	}
}

function replaceTildaWithHyphen(hiddenId){
    if(document.getElementById(hiddenId).value != null && document.getElementById(hiddenId).value != ''){
	var hy_str=document.getElementById(hiddenId).value;
	var val1=hy_str.split('~');
	return val1[1]+'-'+val1[0]
    }
    return null;
}
</script>
</HTML>

