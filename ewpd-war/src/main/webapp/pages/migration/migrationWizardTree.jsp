<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:subview id="tree2">

	<t:tree2 id="serverTree" value="#{migrationtTreeBackingBean.treeDataModel}"
		var="node" varNodeToggler="t" preserveToggle="false" clientSideToggle="false" showNav="true">

		<f:facet name="root">
			<h:panelGroup>
				<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
			</h:panelGroup>
		</f:facet>
		
		<f:facet name="Benefit-Definition-Head">
			<h:panelGroup>
			<h:commandLink styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" 
			actionListener="#{migrationtTreeBackingBean.processActionForBenefitDefinitionHead}" action ="#{migrationtTreeBackingBean.getPages}" id="benefitDefine"  onmousedown="javascript:navigatePageAction(this.id);">
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:commandLink>
					
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Definition-Head-Two">
			<h:panelGroup>
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Definition-Head-Three">
			<h:panelGroup>
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Definition-Head-Four">
			<h:panelGroup>
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:panelGroup>
		</f:facet>
		<f:facet name="Product-Head">
			<h:panelGroup>
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Components-Head">
			<h:panelGroup>
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:panelGroup>
		</f:facet>
		<f:facet name="Standard-Benefits-Head">
			<h:panelGroup>
			 <t:graphicImage value="#{node.allLinesMapped ? '/images/accept.png' : '/images/delete.png'}"
					border="0" width="10" height="10"/>
			
			<h:commandLink styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"  
			actionListener="#{migrationtTreeBackingBean.processActionForStandardBenefit}" action ="#{migrationtTreeBackingBean.getPages}" id="stdbenefitDefine"  onmousedown="javascript:navigatePageAction(this.id);">
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:commandLink>
			</h:panelGroup>
		</f:facet>
		<f:facet name="Benefit-Components-Notes-Head">
			<h:panelGroup>
			<h:commandLink styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"  
			actionListener="#{migrationtTreeBackingBean.processActionForBenefitComponentNotes}" action ="#{migrationtTreeBackingBean.getPages}" id="benefitComponentNotesDefine"  onmousedown="javascript:navigatePageAction(this.id);">
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"/>
			</h:commandLink>
			</h:panelGroup>
		</f:facet>
	</t:tree2>
</f:subview>
