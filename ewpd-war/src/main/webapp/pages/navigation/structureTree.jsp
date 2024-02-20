<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<f:subview id="tree2">

	<t:tree2 id="serverTree" value="#{StructureTreeBackingBean.treeDataModel}"
		var="node" varNodeToggler="t" preserveToggle="false"
		clientSideToggle="false" showNav="true">

		<f:facet name="root">
			<h:panelGroup>
				
				<h:outputText value="#{node.description}" styleClass="treeNodeText" />
			</h:panelGroup>
		</f:facet>

		<f:facet name="major-heading">
			<h:panelGroup>
				<h:commandLink
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
					actionListener="#{StructureTreeBackingBean.processActionForMajorHeading}" action ="#{MajorHeadingBackingBean.refresh}">

				<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
				</h:commandLink>
			</h:panelGroup>
		</f:facet>

		

		<f:facet name="minor-heading">
			<h:panelGroup>
				<h:commandLink
					styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
					actionListener="#{StructureTreeBackingBean.processActionForMinorHeading}" action ="#{MinorHeadingBackingBean.refresh}">
					<h:outputText value="#{node.description}" styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
				</h:commandLink>
			</h:panelGroup>
		</f:facet>
	</t:tree2>



</f:subview>