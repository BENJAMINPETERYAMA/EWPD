<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/test/ComponentBenefitGeneralInformation.java" --%><%-- /jsf:pagecode --%>

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Benefit Component General Information</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form"
					id="benefitComponentGeneralInformationForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><!-- Space for Tree  Data	-->
							<!--<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>				

						 	

							--></TD>

							<TD colspan="2" valign="top" class="ContentArea"><!-- Table containing Tabs -->
							<TABLE width="50%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:commandLink>
												<h:outputText value=" General Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{productStructureGeneralInfoBackingBean.retrieveContractBenefitComponent}">
												<h:outputText value="Notes" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
									<TR>
										<TD colspan="5">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="3">
											<tr>
												<TD width="130"><h:outputText value="Line of Business" /></TD>
												<h:inputHidden id="hiddenLob"
													value="#{productStructureGeneralInfoBackingBean.lob}"></h:inputHidden>
												<TD width="194">
												<DIV id="divForLob" class="selectDivReadOnly"></DIV>
												<SCRIPT>parseForDiv(document.getElementById('divForLob'), document.getElementById('benefitComponentGeneralInformationForm:hiddenLob')); </SCRIPT>
												</TD>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Business Entity" /></TD>
												<h:inputHidden id="hiddenEntity"
													value="#{productStructureGeneralInfoBackingBean.entity}"></h:inputHidden>
												<TD width="194">
												<DIV id="divForEntity" class="selectDivReadOnly"></DIV>
												<SCRIPT>parseForDiv(document.getElementById('divForEntity'), document.getElementById('benefitComponentGeneralInformationForm:hiddenEntity')); </SCRIPT>
												</TD>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Business Group" /></TD>
												<h:inputHidden id="hiddenGroup"
													value="#{productStructureGeneralInfoBackingBean.group}"></h:inputHidden>
												<TD width="194">
												<DIV id="divForGroup" class="selectDivReadOnly"></DIV>
												<SCRIPT> parseForDiv(document.getElementById('divForGroup'), document.getElementById('benefitComponentGeneralInformationForm:hiddenGroup')); </SCRIPT>
												</TD>
											</TR>
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Effective Date" /></TD>
										<TD width="239"><h:outputText
											value="#{productStructureGeneralInfoBackingBean.effectiveDate}"
											id="effDate" styleClass="formInputFieldReadOnly" /> <h:inputHidden
											id="effDateHidden"
											value="#{productStructureGeneralInfoBackingBean.effectiveDate}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:effDateHidden','benefitComponentGeneralInformationForm:effDate',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Expiry Date" /></TD>
										<TD width="239"><h:outputText id="expiryDate"
											value="#{productStructureGeneralInfoBackingBean.expiryDate}"
											styleClass="formInputFieldReadOnly" /> <h:inputHidden
											id="expiryDateHidden"
											value="#{productStructureGeneralInfoBackingBean.expiryDate}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:expiryDateHidden','benefitComponentGeneralInformationForm:expiryDate',1); </SCRIPT>

										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Name" /></TD>
										<TD width="239"><h:outputText id="name"
											value="#{productStructureGeneralInfoBackingBean.name}"
											styleClass="formInputFieldReadOnly" /> <h:inputHidden
											id="nameHidden"
											value="#{productStructureGeneralInfoBackingBean.name}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:nameHidden','benefitComponentGeneralInformationForm:name',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135" valign="top"><h:outputText value="Description" /></TD>
										<TD width="239"><h:inputTextarea
											styleClass="formTxtAreaReadOnly"
											value="#{productStructureGeneralInfoBackingBean.description}"
											id="desc" readonly="true" /> <h:inputHidden id="descHidden"
											value="#{productStructureGeneralInfoBackingBean.description}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:descHidden','benefitComponentGeneralInformationForm:desc',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Created By" /></TD>
										<TD width="239"><h:outputText id="createdUser"
											value="#{productStructureGeneralInfoBackingBean.createdUser}"
											styleClass="formInputFieldReadOnly" /> <h:inputHidden
											id="createdUserHidden"
											value="#{productStructureGeneralInfoBackingBean.createdUser}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:createdUserHidden','benefitComponentGeneralInformationForm:createdUser',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Created Date" /></TD>
										<TD width="239"><h:outputText id="creationDate"
											value="#{productStructureGeneralInfoBackingBean.creationDate}"
											styleClass="formInputFieldReadOnly">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{productStructureGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:creationDateHidden','benefitComponentGeneralInformationForm:creationDate',1); </SCRIPT>
										<h:inputHidden id="time" value="#{requestScope.timezone}">
										</h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Last Updated By" /></TD>
										<TD width="239"><h:outputText id="lastUpdatedUser"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}"
											styleClass="formInputFieldReadOnly" /> <h:inputHidden
											id="updatedUserHidden"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:updatedUserHidden','benefitComponentGeneralInformationForm:lastUpdatedUser',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="Last Updated Date" /></TD>
										<TD width="239"><h:outputText id="lastUpdatedDate"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}"
											styleClass="formInputFieldReadOnly">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updatedDateHidden"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitComponentGeneralInformationForm:updatedDateHidden','benefitComponentGeneralInformationForm:lastUpdatedDate',1); </SCRIPT>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							</fieldset>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->



					<h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink>
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
	//For Business Domain Values
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:hiddenLob','divForLob',2,1); 
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:hiddenEntity','divForEntity',2,1); 
	copyHiddenToDiv_ewpd('benefitComponentGeneralInformationForm:hiddenGroup','divForGroup',2,1); 
</script>
</HTML>




