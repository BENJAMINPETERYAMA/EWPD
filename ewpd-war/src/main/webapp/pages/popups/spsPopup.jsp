<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<title>SPS Popup</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
</head>
<f:view>
<body>
<h:form styleClass="form" id="ebxSPSIdForm">
		<h:inputHidden id="hidd_init" value="#{contractBasicInfoBackingBean.initEbxSPSId}" />
		<table width="100%" cellpadding="2" border="0" class="outputText">
			<TBODY>
				<tr>
					<td bgcolor="#7670B3"><b>SPS Id:&nbsp;<h:outputText
						id="spsIdDisplay" value="#{contractBasicInfoBackingBean.spsId}"></h:outputText></b>
					</td>
				</tr>
				<TR>
					<td>
					<t:div rendered="#{contractBasicInfoBackingBean.ebxWebSerSPSIdDisplayLst == null}"> 
                           <TABLE class="smallfont" id="resultsTable" width="100%">
								<TR>
									<TD>
										<div style="position: relative; color: red; width: 99%" align="center">											
											<b>No SPS Id details to display since <h:outputText id="spsIdDisplay2" value="#{contractBasicInfoBackingBean.spsId}"/> is not a valid SPS id</b>
										</div>
									</TD>
								</TR>
						  </TABLE>      
					</t:div>
					<div id="dataDiv" style="height: 60px; width: 100%; position: relative; z-index: 1; font-size: 10px; overflow: auto;">
					<h:dataTable headerClass="fixedDataTableHeader"
						id="searchResultTable1" var="singleValue" cellpadding="3"
						cellspacing="2" bgcolor="#cccccc"
						rendered="#{contractBasicInfoBackingBean.ebxWebSerSPSIdDisplayLst != null}"
						value="#{contractBasicInfoBackingBean.ebxWebSerSPSIdDisplayLst}"
						rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
						width="100%">
						<h:column>
							<f:facet name="header">
								<f:verbatim>EB01</f:verbatim>
							</f:facet>
							<h:outputText id="eb01" value="#{singleValue.ebB01}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>EB02</f:verbatim>
							</f:facet>
							<h:outputText id="eb02" value="#{singleValue.ebB02}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>EB06</f:verbatim>
							</f:facet>
							<h:outputText id="eb06" value="#{singleValue.ebB06}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>EB09</f:verbatim>
							</f:facet>
							<h:outputText id="eb09" value="#{singleValue.ebB09}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD01</f:verbatim>
							</f:facet>
							<h:outputText id="hsd01" value="#{singleValue.hsD01}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD02</f:verbatim>
							</f:facet>
							<h:outputText id="hsd02" value="#{singleValue.hsD02}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD03</f:verbatim>
							</f:facet>
							<h:outputText id="hsd03" value="#{singleValue.hsD03}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD04</f:verbatim>
							</f:facet>
							<h:outputText id="hsd04" value="#{singleValue.hsD04}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD05</f:verbatim>
							</f:facet>
							<h:outputText id="hsd05" value="#{singleValue.hsD05}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD06</f:verbatim>
							</f:facet>
							<h:outputText id="hsd06" value="#{singleValue.hsD06}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD07</f:verbatim>
							</f:facet>
							<h:outputText id="hsd07" value="#{singleValue.hsD07}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>HSD08</f:verbatim>
							</f:facet>
							<h:outputText id="hsd08" value="#{singleValue.hsD08}"></h:outputText>
						</h:column>
					</h:dataTable></div>
					</td>
				</TR>

			</TBODY>
		</TABLE>
	</h:form>
</body>
</f:view>
</html>