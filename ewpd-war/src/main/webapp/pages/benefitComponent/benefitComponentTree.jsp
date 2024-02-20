<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="/WEB-INF/ajaxTree.tld" prefix="jcaa"%>

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
	<h:inputHidden id="scrollTop" value="#{benefitComponentTreeBackingBean.scrollTop}" />
	<aa:zoneJSF id="treeZone">
		<jcaa:ajaxTree id="serverTree"
		value="#{benefitComponentTreeBackingBean.treeDataModel}" var="node"
		varNodeToggler="t" preserveToggle="false" clientSideToggle="false"
		showNav="true" ajaxZone="treeZone">

		<f:facet name="root">
			<h:panelGroup>
				<h:commandLink
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
					actionListener="#{benefitComponentTreeBackingBean.processActionForBenefitComponent}"
					action="#{benefitComponentTreeBackingBean.refresh}" id="root"
					onmousedown="javascript:navigatePageAction(this.id);getScroll();">
					<h:outputText value="#{node.description}"
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
				</h:commandLink>
			</h:panelGroup>
		</f:facet>

		<f:facet name="Benefit-Hierarchy">
			<h:panelGroup>
				<h:commandLink
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
					actionListener="#{benefitComponentTreeBackingBean.processActionForBenefitHierarchy}"
					action="#{benefitComponentTreeBackingBean.refresh}" id="benHier"
					onmousedown="javascript:navigatePageAction(this.id);getScroll();">
					<h:outputText value="#{node.description}"
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
				</h:commandLink>
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Administration-Head">
			<h:panelGroup>
				<h:outputText value="#{node.description}"
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Administration">
			<h:panelGroup>
				<!--  started modification for admin method -->
				<h:commandLink
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
					actionListener="#{benefitComponentTreeBackingBean.processActionForBenefitAdministration}" 
					action ="#{adminOptionBackingBean.loadForBenefitComponent}" id="benAdmin"
					onmousedown="javascript:navigatePageAction(this.id);getScroll();">
				<h:outputText value="#{node.description}"
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
				</h:commandLink>
				<!-- modification for admin method ends here -->
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Level">
			<h:panelGroup>
				<h:outputText value="#{node.description}"
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
			</h:panelGroup>
		</f:facet>
		<f:facet name="Admin-Option">
			<h:panelGroup>
				<h:commandLink
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
					actionListener="#{benefitComponentTreeBackingBean.processActionForAdminOption}"
					action="#{BenefitComponentAdministrationBackingBean.loadQuestionsAction}" id="AdmOpt"
					onmousedown="javascript:navigatePageAction(this.id);getScroll();">
					<h:outputText value="#{node.description}"
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
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
