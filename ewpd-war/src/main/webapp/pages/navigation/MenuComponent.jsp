<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:codeBehind language="java" location="/JavaSource/pagecode/pages/navigation/WebMenu1.java" --%><%-- /jsf:codeBehind --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <TD><h:form id="menuForm">	

<h:inputHidden id="jscook_action"></h:inputHidden>

<x:jscookMenu layout="hbr" theme="ThemeOffice" javascriptLocation="/MenuResources" styleLocation="/MenuResources" imageLocation="/MenuResources">
    <x:navigationMenuItems value="#{menuBean.menu}"  />
</x:jscookMenu>
</h:form></TD></tr></table>


