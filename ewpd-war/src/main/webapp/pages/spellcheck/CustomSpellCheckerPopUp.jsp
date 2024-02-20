<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<%@ page import="org.owasp.esapi.ESAPI" %>
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML//EN">
<html>
<title>Spell Check</title>
<body>
<Center>
<%
String title = ESAPI.encoder().encodeForHTML(request.getParameter("title"));
String licenseKey = com.wellpoint.wpd.web.util.WebConstants.licensekey;
com.wellpoint.wpd.web.dictionary.WPDRapidSpellChecker rapidSpellChecker = new com.wellpoint.wpd.web.dictionary.WPDRapidSpellChecker( licenseKey );
rapidSpellChecker.setIncludeUserDictionaryInSuggestions(true);
rapidSpellChecker.setWordIterator(new com.wellpoint.wpd.web.dictionary.CustomBoundary());
rapidSpellChecker.setAllowAnyCase(true);
rapidSpellChecker.setAllowMixedCase(true);
rapidSpellChecker.setSeparateHyphenWords( false );
rapidSpellChecker.setUserDictionary(new com.wellpoint.wpd.web.dictionary.WPDUserDictionary());
%>
<TABLE width="100%" height="2%">
	<tr>
		<td width="91%" style="color:#800000;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;font-weight:bold;"><%= title %></td>
		<td width="3%">&nbsp;</td>
		<td width="3%">&nbsp;</td>
		<td width="3%">&nbsp;</td>
	</tr>
</TABLE>
<RapidSpellWeb:rapidSpellWeb licenseKey="<%=licenseKey%>" checkerEngine="<%=rapidSpellChecker%>"
	changeToBoxStyle="font-size:10pt; width:200px; font-family:'arial, helvetica, sans-serif';visibility:hidden;"
	changeToLabelStyle="font-size:9pt; font-family:'arial, helvetica, sans-serif'; visibility:hidden;"
	suggestionsBoxStyle="font-size:11px; width:200px;height:100px; font-family:'Verdana, Arial, Helvetica, sans-serif';"
	suggestionsLabelStyle="font-size:11px; font-family:'Verdana, Arial, Helvetica, sans-serif';font-weight:bold;"
	previewPaneStyle="font-family: 'Verdana, Arial, Helvetica, sans-serif'; font-size: 8pt; font-weight:bold;color: #1762A5"
	previewPaneWidth="200" previewPaneHeight="190"
	ignoreButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#2C5F93;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;"
	ignoreAllButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#2C5F93;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;"
	addButtonStyle="font-family:'Tahoma,Arial,Helvetica';font-size:10pt; border:1px solid #b5bed6; background-color:#dddddd; width: 90px;visibility:hidden;"
	addButtonOnMouseOver="this.style.backgroundColor='#b5bed6';this.style.borderColor='#08246b';"
	addButtonOnMouseOut="this.style.backgroundColor='#dddddd';this.style.borderColor='#b5bed6';"
	changeButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#2C5F93;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;"
	changeAllButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#2C5F93;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;"
	finishButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#2C5F93;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;"
	layout="<table border=0><tr><td>			<PreviewPane/>		</td>		<td>
			<table border=0 height=190>				<tr><td>					
<ChangeToLabel/>				</td></tr><tr><td>		
			<ChangeToBox/>				</td></tr><tr><td>	
				<SuggestionsLabel/>				</td></tr><tr>
<td valign=bottom>					<SuggestionsBox/>		
		</td></tr>			</table>		</td>		<td>
			<table border=0 height=160>				<tr><td>
					<IgnoreButton/>				</td></tr><tr><td>		
			<IgnoreAllButton/>				</td></tr><tr><td>		
			<ChangeButton/>				</td></tr><tr><td>	
				<ChangeAllButton/>				</td></tr><tr><td>	
				<FinishButton/>				</td></tr>			</table>	
	</td>		</tr>		</table>		" />

</center>
</body>
</html>
