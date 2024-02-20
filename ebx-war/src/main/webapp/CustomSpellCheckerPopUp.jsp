<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<%@ page import="org.owasp.esapi.ESAPI" %>
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML//EN">
<html>
<title>Spell Check</title>
<body>
<Center>
<%
String title = ESAPI.encoder().encodeForHTML(request.getParameter("title"));
String licenseKey = "247274227B7A787D7A2778784E4E4A4C364D4D38363A3C3E3A4040403E424446406C7B2425692A25757C352A547A703C7";
com.wellpoint.ets.ebx.dictionary.util.WPDRapidSpellChecker rapidSpellChecker = new com.wellpoint.ets.ebx.dictionary.util.WPDRapidSpellChecker( licenseKey );
rapidSpellChecker.setIncludeUserDictionaryInSuggestions(true);
rapidSpellChecker.setWordIterator(new com.wellpoint.ets.ebx.dictionary.util.CustomBoundary());
rapidSpellChecker.setAllowAnyCase(true);
rapidSpellChecker.setAllowMixedCase(true);
rapidSpellChecker.setSeparateHyphenWords( false );
rapidSpellChecker.setUserDictionary(new com.wellpoint.ets.ebx.dictionary.util.WPDUserDictionary());
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
						   background-color:#61a3cd;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;font-weight:bold;"
	ignoreAllButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#61a3cd;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;font-weight:bold;"
	addButtonStyle="font-family:'Tahoma,Arial,Helvetica';font-size:10pt; border:1px solid #b5bed6; background-color:#dddddd; width: 90px;visibility:hidden;font-weight:bold;"
	addButtonOnMouseOver="this.style.backgroundColor='#b5bed6';this.style.borderColor='#08246b';font-weight:bold;"
	addButtonOnMouseOut="this.style.backgroundColor='#dddddd';this.style.borderColor='#b5bed6';font-weight:bold;"
	changeButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#61a3cd;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;font-weight:bold;"
	changeAllButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#61a3cd;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;font-weight:bold;"
	finishButtonStyle="color:#FFFFFF;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;
						   background-color:#61a3cd;border:1px solid;border-top-color:rgb(27,112,188);
						   border-right-color:rgb(16,67,114);border-bottom-color:rgb(16,67,114);
						   border-left-color:rgb(27,112,188);width:100px;font-weight:bold;"
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
