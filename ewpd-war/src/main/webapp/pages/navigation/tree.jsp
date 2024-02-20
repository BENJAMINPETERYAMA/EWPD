<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="/WEB-INF/ajaxTree.tld" prefix="jcaa"%>

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
<script language="JavaScript" src="../../js/aa.js"></script>
<!--   WAS 6.0 Migration Changes - Tree node border set to none to display the image without blue border -->
<style type="text/css">
.treeNodeImage_WithNoBorder {
	border: none;
}
</style>

<f:subview id="tree2">
	<input type="Button" id="scrollButton"
				style="display:none; visibility: hidden;"
				onClick ="loadScollTop();" />
	<h:inputHidden id="scrollTop" value="#{SearchTreeBackingBean.scrollTop}" />
	<aa:zoneJSF id="treeZone">
		<jcaa:ajaxTree id="serverTree" value="#{SearchTreeBackingBean.treeDataModel}"
		var="node" varNodeToggler="t" preserveToggle="false"
		clientSideToggle="false" showNav="true" ajaxZone="treeZone">

		<f:facet name="root">
			<h:panelGroup>
				<h:outputText value="#{node.description}" styleClass="treeNodeText" />
			</h:panelGroup>
		</f:facet>

		<f:facet name="sub-root">
			<h:panelGroup>
				<h:commandLink
					styleClass="treeNodeText"
					actionListener="#{SearchTreeBackingBean.processSubRootAction}" action="#{SearchTreeBackingBean.getSubRootAction}" onmousedown="getScroll();">
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
				</h:commandLink>
			</h:panelGroup>
		</f:facet>	

		<f:facet name="object-node">
			<h:panelGroup>
				<h:outputText value="#{node.description}" styleClass="treeNodeText" />
			</h:panelGroup>
		</f:facet>

		<f:facet name="page-node">
			<h:panelGroup>
				<h:commandLink
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
					actionListener="#{SearchTreeBackingBean.processPageAction}" action="#{SearchTreeBackingBean.getPageAction}" onmousedown="getScroll();">
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
				</h:commandLink>
			</h:panelGroup>
		</f:facet>

	</jcaa:ajaxTree>
	</aa:zoneJSF>
	<!--   WAS 6.0 Migration Changes - Tree node border set to none to display the image without blue border -->
	<script language="javascript" type="text/javascript">
		window.onload=function(){		
		var images,i;
		 images=document.getElementsByTagName("img");
		 for(i in images){
			 images[i].className = "treeNodeImage_WithNoBorder";
		 }		 
			document.getElementById('scrollButton').click();
		}
		ajaxTreeCall();
    </script>
</f:subview>

