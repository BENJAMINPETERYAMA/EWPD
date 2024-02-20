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
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Reference Mapping Print</TITLE>
</HEAD>
<f:view>
	<h:form styleClass="form" id="editIndicativePrintForm">
		<BODY>
		<TABLE cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="100%">
				<FIELDSET
					style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%;">
				<div id="breadcrumb"
					style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;border:1px solid #FFFFFF;">
				</div>
				</FIELDSET>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<TR>
				<TD width="100%">

				<FIELDSET
					style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%">
				<TABLE border="0" align="center" width="100%" cellspacing="0"
					cellpadding="3">
					<TBODY>
						<TR>
							<TD width="20%" align="left"><h:outputText value="Reference Id"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="refDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>
						<TR>
							<TD width="20%" align="left"><h:outputText value="Secondary Code"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="snDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>
						<TR>
							<TD width="20%" align="left"><h:outputText value="Description"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="DesDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>
						<TR>
							<TD width="20%" align="left"><h:outputText value="Type"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="typeDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>
						<TR>
							<TD width="20%" align="left"><h:outputText value="Term"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
								<div id="TermDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
							
						</TR>


						<TR>
							<TD width="20%" align="left"><h:outputText value="Qualifier"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="QualifierDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>
						<TR>
							<TD width="20%" align="left"><h:outputText value="Provider Arrangement"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="ProviderArrangementDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>
							<TR>
							<TD width="20%" align="left"><h:outputText value="Data Type"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="DataTypeDiv"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>

						<TR>
							<TD width="20%" align="left"><h:outputText value="Created By"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="createdBy"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>						


						<TR>
							<TD width="20%" align="left"><h:outputText
								value="Created Date"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="createdDate"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>

						<TR>
							<TD width="20%" align="left"><h:outputText
								value="Last Updated By"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="lastUpdatedBy"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>

						<TR>
							<TD width="20%" align="left"><h:outputText
								value="Last Updated date"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;" /></TD>
							<TD>
							<div id="lastUpdatedDate"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#000000;"></div>
							</TD>
						</TR>

					</TBODY>
				</TABLE>

				</FIELDSET>
				</TD>
			</TR>

		</TABLE>

		</BODY>
	</h:form>
</f:view>
<script>

window.opener = window.dialogArguments.parentWindow;
var doc = window.opener.document;

document.getElementById('refDiv').innerHTML = doc.getElementById('refDiv').innerHTML;
document.getElementById('snDiv').innerHTML = doc.getElementById('snDiv').innerHTML;

document.getElementById('DesDiv').innerHTML = doc.getElementById('DesDiv').innerHTML;
document.getElementById('TermDiv').innerHTML = doc.getElementById('TermDiv').innerHTML;
document.getElementById('QualifierDiv').innerHTML = doc.getElementById('QualifierDiv').innerHTML;
document.getElementById('ProviderArrangementDiv').innerHTML = doc.getElementById('ProviderArrangementDiv').innerHTML;
 var obj=doc.getElementById('editForm:CorpPlanCd_list1');

document.getElementById('typeDiv').innerHTML = obj.options[obj.selectedIndex].text;

document.getElementById('DataTypeDiv').innerHTML = doc.getElementById('DataTypeDiv').innerHTML;



document.getElementById('createdBy').innerHTML = doc.getElementById('mapcu').innerHTML;


document.getElementById('createdDate').innerHTML = doc.getElementById('mapcdo').innerHTML;

document.getElementById('lastUpdatedBy').innerHTML = doc.getElementById('mapclu').innerHTML;

document.getElementById('lastUpdatedDate').innerHTML = doc.getElementById('maplte').innerHTML;

//var obj=doc.getElementById('editForm:indhid').value;
//var arr=obj.split('~');
//document.getElementById('description').innerText=doc.getElementById('editForm:msg').value;

document.getElementById('breadcrumb').innerHTML = 'Administration >> Reference Mapping ('+doc.getElementById('refDiv').innerHTML+') >> Print';

window.print();
</script>
</HTML>
