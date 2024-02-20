<html>
	<head>
		<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
		<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
		<%@ taglib uri="/wpd.tld" prefix="w"%>
		<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
		<TITLE>Benefit Selection Test Results</TITLE>
		<style type = "text/css">
			.headerColumnWith{
				width:16%;text-align: left;vertical-align: top;
			}

			.fontWrap{
				font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;white-space:nowrap;
			}
			.fontStr{
				font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;
			}
			.fontErr{
				font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold; color: #F00;
			}
			.fontHeader{
				font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;white-space:nowrap;color:#1762A5;
			}
			.w7  { width:7%; text-align: left;vertical-align: top;}
			.w8  { width:8%; text-align: left;vertical-align: top;}
			.w14 { width:14%; text-align: left;vertical-align: top;}
			.w16 { width:16%; text-align: left;vertical-align: top;}
			.tcM{ padding:0px 0px 27px 0px;border:1px double #F00 }
		</style>
	</head>
	<f:view>
	<h:inputHidden value="#{testSuiteBackingBean.executeTestSute}"></h:inputHidden>
	<body width="100%" style="margin-left:7px;">
		<div style="width:100%;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><jsp:include page="../navigation/top_print_page.jsp"></jsp:include></td>
				</tr>
			</table>
		</div>
		<div><w:message></w:message></div>
		<div>
			<fieldset>
			<table style="margin:19px 59px;">
				<tr>
					<td style="font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;">Test suite</td>
					<td style="padding-right:37px;">:&nbsp;&nbsp;<h:outputText value="#{testSuiteBackingBean.testSuiteName}" /></td>
					<td style="font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;">Contract ID</td>
					<td style="padding-right:37px;">:&nbsp;&nbsp;<h:outputText value="#{testSuiteBackingBean.contractId}" /></td>
					<td style="font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;">Run Date</td>
					<td>:&nbsp;&nbsp;<h:outputText value="#{testSuiteBackingBean.date}" /> </td>
				</tr>
				<tr>
					<td style="font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;">Start date</td>
					<td>:&nbsp;&nbsp;<h:outputText value="#{testSuiteBackingBean.startDate}" /> </td>
					<td style="font-family: Verdana, Arial, Helvetica, sans-serif;font-weight: bold;">End date</td>
					<td>:&nbsp;&nbsp;<h:outputText value="#{testSuiteBackingBean.endDate}"/></td>
				</tr>
			</table>
			<div style="margin-top:33px">
					<h:dataTable id="table1" var="testCaseBO"
								  rendered="#{testSuiteBackingBean.testSuiteResults != null}"
								  value="#{testSuiteBackingBean.testSuiteResults}"
								  style="width:100%">
		<h:column>
				<h:panelGrid id="panel1" columns="2">
				<h:outputText value="Test Case   : " />
				<h:outputText styleClass="fontHeader"
					value="#{testCaseBO.testCaseName}" />
			</h:panelGrid>
			<h:panelGrid id="panel11" columns="6"
				columnClasses="headerColumnWith"
				style="padding:0px 13px; border:1px solid #CCCCCC;">
				<h:outputText styleClass="fontWrap" value="Claim Type" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.claimType}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="ITS/PROV Spec" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.itsProvSpec}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="Edit" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.edit}"></h:outputText>

				<h:outputText styleClass="fontWrap" value="Provider ID" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.providerId}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="Hospital Facility Code" />
				<h:outputText
					value=": #{testCaseBO.claimHeaderBO.hospitalFacilityCode}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="Days From Injury" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.daysFromInjury}"></h:outputText>

				<h:outputText styleClass="fontWrap" value="MED Assign IND" />
				<h:outputText
					value=": #{testCaseBO.claimHeaderBO.medAssignIndicator}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="Gender" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.gender}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="Member Relationship Code" />
				<h:outputText
					value=": #{testCaseBO.claimHeaderBO.memberRelationshipCode}"></h:outputText>

				<h:outputText styleClass="fontWrap" value="Age" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.age}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="Group State" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.groupState}"></h:outputText>
				<h:outputText styleClass="fontWrap" value="ITS Claim" />
				<h:outputText value=": #{testCaseBO.claimHeaderBO.itsClaim}"></h:outputText>
			</h:panelGrid>
			<h:dataTable id="table12" var="claimLineBO"
			    rendered="#{testCaseBO.claimLineDetailList != null}"
				value="#{testCaseBO.claimLineDetailList}"
				style="margin:3px 0px 27px 0px; border:1px solid #CCCCCC;"
				rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
				binding="#{testSuiteBackingBean.dataTable}"
				width="100%">
				<h:column>
					<h:panelGrid id="panel2" columns="9"
						columnClasses="w7,w8,w8,w8,w16,w16,w16,w14,w7"
						style="margin:0px 13px;width:100%;text-valign:top;">
						<h:outputText value="Line #" />
						<h:outputText value="Diagnosis Code" />
						<h:outputText value="TT Code" />
						<h:outputText value="Place of Service" />
						<h:outputText value="Expected Benefit component" />
						<h:outputText value="Expected Benefit" />
						<h:outputText value="Expected Rider Benefit" />
						<h:outputText value="Expected Basic Benefit" />
						<h:outputText value="status" />
						<h:outputText styleClass="fontStr" value="#{testSuiteBackingBean.dataTable.rowIndex+1}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.diagnosisCode}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.ttCode}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.placeOfService}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.expectedBenefitComponentDesc}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.expectedMajBenefitDesc}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.expectedRiderBenefitDesc}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.expectedBasicBenefitDesc}" />
						<h:outputText styleClass="#{(claimLineBO.status)? 'fontStr' : 'fontErr' }" value="#{(claimLineBO.status)? 'Success' : 'Fail' }" />

						<h:outputText value="HCPC Code" />
						<h:outputText value="Revenue Code" />
						<h:outputText value="Type of Bill" />
						<h:outputText value="Modifier Code" />
						<h:outputText value="Actual  Benefit component" />
						<h:outputText value="Actual Benefit" />
						<h:outputText value="Actual Rider benefit" />
						<h:outputText value="Actual Basic Benefit" />
						<h:outputText value=" " />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.hcpcCode}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.revenueCode}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.typeOfBillCode}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.modifierCode}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.actualBenefitComponent}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.actualBenefit}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.actualRiderBenefit}" />
						<h:outputText styleClass="fontStr" value="#{claimLineBO.actualBasicBenefit}" />
						<h:outputText rendered="#{claimLineBO.accidentRider}" value="Accident rider applies" />

					</h:panelGrid>
							</h:column>
						</h:dataTable>
					</h:column>
				</h:dataTable>
			</div>
		</div>
		<table width="100%" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/bottom_view.jsp"></jsp:include></td>
			</tr>
		</table>
	</body>
</f:view>
</html>
