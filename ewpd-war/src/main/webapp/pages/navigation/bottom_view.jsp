<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
	<td>				
		<table width="100%" border="0" cellpadding="5" cellspacing="0" id="footer" >
    	<tr>
        	<td width="30%">Environment :
					<h:outputText id="FooterEnvironment" value="#{CommonBackingBean.environment}" />
			</td>
			<td>Version :<h:outputText id="FooterVersion" value="#{CommonBackingBean.version}"> </h:outputText></td>
        	<td align="right">Copyright &copy; 2008 WellPoint, Inc.  All Rights Reserved |  <a>Contact Us</a></td>
    	</tr>
    	</table>
	</td>	
	</tr>
</table>