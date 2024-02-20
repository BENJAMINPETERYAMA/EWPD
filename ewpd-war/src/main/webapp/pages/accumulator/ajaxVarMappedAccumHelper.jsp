<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
</HEAD>
<f:view>
	<BODY>
	<h:form id="standardAccumulatorMapForm">
	<table cellpadding="0" cellspacing="0" width="100%" border="0"><div 
<tr>
				<td>
					<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
											id="resultHeaderTable" border="0" width="100%">
											<TBODY>
												<TR class="dataTableColumnHeader">
													<td align="left"><h:outputText value="Business Entity"></h:outputText>
													</td>
													<td align="left"><h:outputText value="Accumulator Mapped"></h:outputText>
													</td>
												    <td>&nbsp;</td>
												</TR>
											</TBODY>
										</TABLE>  
					</td>
					</tr>  
			<tr>
									<td>
		<div id="searchResultdataTableDiv">
		
		
				<tr>
									<td>  
									<h:outputText value="**$"></h:outputText>
		<h:dataTable cellspacing="1" rowClasses="dataTableEvenRow,dataTableOddRow" 
			width="100%" id="varDataTable"
			value="#{standardAccumBackingBean.searchResultList}" var="singleValue"
			cellpadding="0" style="border:1px solid #cccccc;">
			<h:column>
		          <h:outputText id="bussEntity" value="#{singleValue.businessentity}"></h:outputText>
		          <h:inputHidden id="hiddenBE" value="#{singleValue.businessentity}"></h:inputHidden>
			</h:column>
		   	<h:column>
				 <h:outputText id="standAccum" value="#{singleValue.standardAccumulatorStr}"></h:outputText>
				 <h:inputHidden id="hiddenACC" value="#{singleValue.standardAccumulatorStr}"></h:inputHidden>
				 <h:inputHidden id="hiddenVar" value="#{singleValue.contvar}"></h:inputHidden>
		    </h:column>
		    <h:column>
		    <h:commandButton alt="Delete" id="deleteButton"	image="../../images/delete.gif" onclick="delBeAcMap();return false;"></h:commandButton>
			</h:column>
		</h:dataTable>
		<h:outputText value="@@$"></h:outputText>
		</td>
		</tr>
		</table>
		
		</div>
</td>
</tr>
</table>
		

	</h:form>
		<script language="javascript">
	var headerDiv;

		setColumnWidth('resultHeaderTable','40%:40%:20%');
		setColumnWidth('standardAccumulatorMapForm:varDataTable','40%:40%:20%');	
			
		if(document.getElementById('standardAccumulatorMapForm:varDataTable')!= null){
		document.getElementById('standardAccumulatorMapForm:varDataTable').onresize = syncTables;
		syncTables();
		}
		function syncTables(){
			var relTblWidth = document.getElementById('standardAccumulatorMapForm:varDataTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}
	
	</script>
	</BODY>
</f:view>
</HTML>