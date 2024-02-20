<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<title>Header Rule Popup</title>
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
<h:form styleClass="form" id="ebxHeaderRuleForm">
		<h:inputHidden id="hidd_init" value="#{contractBasicInfoBackingBean.initEbxRuleId}" />
		<table width="100%" cellpadding="2" border="0" class="outputText">
			<TBODY>
				<tr>
					<td bgcolor="#7670B3"><b>Header Rule Id:&nbsp;<h:outputText
						id="ruleIdDisplay" value="#{contractBasicInfoBackingBean.ruleId}"></h:outputText></b>
					</td>
				</tr>
				<TR>
					<td>
					<t:div rendered="#{contractBasicInfoBackingBean.ebxWebSerRuleIdDisplayLst == null}"> 
                           <TABLE class="smallfont" id="resultsTable" width="100%">
								<TR>
									<TD>
										<div style="position: relative; color: red; width: 99%" align="center">											
											<b>No Header Rule Id details to display since <h:outputText id="ruleIdDisplay2" value="#{contractBasicInfoBackingBean.ruleId}"/> is not a valid Rule id</b>
										</div>
									</TD>
								</TR>
						  </TABLE>      
					</t:div>
					<div id="dataDiv" style="height: 80px; width: 100%; position: relative; z-index: 1; font-size: 10px; overflow: auto;">
					<h:dataTable headerClass="fixedDataTableHeader"
						id="searchResultTable1" var="singleValue" cellpadding="3"
						cellspacing="2" bgcolor="#cccccc"
						rendered="#{contractBasicInfoBackingBean.ebxWebSerRuleIdDisplayLst != null}"
						value="#{contractBasicInfoBackingBean.ebxWebSerRuleIdDisplayLst}"
						rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
						width="100%">
						<h:column>
							<f:facet name="header">
								<f:verbatim>EB03</f:verbatim>
							</f:facet>
							<h:outputText id="eb03" value="#{singleValue.ebB03}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>III02</f:verbatim>
							</f:facet>
							<h:outputText id="iii02" value="#{singleValue.iii02}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<f:verbatim>UM Rules</f:verbatim>
							</f:facet>
							<h:outputText id="umRules" value="#{singleValue.umRule}"></h:outputText>
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