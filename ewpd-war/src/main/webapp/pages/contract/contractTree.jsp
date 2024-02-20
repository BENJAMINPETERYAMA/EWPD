<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
	<h:inputHidden id="scrollTop" value="#{contractTreeBackingBean.scrollTop}" />

	<aa:zoneJSF id="treeZone">
		<jcaa:ajaxTree id="serverTree"
			value="#{contractTreeBackingBean.treeDataModel}" var="node"
			varNodeToggler="t" preserveToggle="false" clientSideToggle="false"
			showNav="true" ajaxZone="treeZone">

			<f:facet name="root">
				<h:panelGroup>
					<h:commandLink
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
						actionListener="#{contractTreeBackingBean.processActionForContract}"
						action="#{contractBasicInfoBackingBean.getBasicInfo}"
						id="rootStart"
						onmousedown="javascript:navigatePageAction(this.id);getScroll();">
						<h:outputText value="#{node.description}"
							styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />
					</h:commandLink>
				</h:panelGroup>
			</f:facet>

			<f:facet name="Administration-Head">
				<h:panelGroup id="contracttreepanel1">

					<h:outputText value="#{node.description}"
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot1"/>

				</h:panelGroup>
			</f:facet>

			<f:facet name="Administration">
				<h:panelGroup id="contracttreepanel2">
					<h:commandLink
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
						actionListener="#{contractTreeBackingBean.processActionForProductAdmin}"
						action="#{contractProductAdminOptionOverrideBackingBean.displayContractBenefitAdministration}"
						id="admin" onmousedown="javascript:navigatePageAction(this.id);getScroll();">
						<h:outputText value="#{node.description}"
							styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot2"/>
					</h:commandLink>
				</h:panelGroup>
			</f:facet>

			<f:facet name="Product">
				<h:panelGroup id="contracttreepanel3">
					<h:commandLink
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
						actionListener="#{contractTreeBackingBean.processActionForProduct}"
						action="#{contractProductGeneralInfoBackingBean.displayProductGeneralInfo}"
						id="product" onmousedown="javascript:navigatePageAction(this.id);getScroll();">
						<h:outputText value="#{node.description}"
							styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot3"/>
					</h:commandLink>
				</h:panelGroup>
			</f:facet>

			<f:facet name="Benefit-Component">
				<h:panelGroup id="contracttreepanel4">
					<h:commandLink
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
						actionListener="#{contractTreeBackingBean.processActionForBenefitComponent}"
						action="#{contractComponentGeneralInfoBackingBean.retrieveBenefitComponent}"
						id="benefitComp"
						onmousedown="javascript:navigatePageAction(this.id);getScroll();">
						<h:outputText value="#{node.description}"
							styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot4"/>
					</h:commandLink>

				</h:panelGroup>
			</f:facet>

			<f:facet name="Standard-Benefit">
				<h:panelGroup id="contracttreepanel5">
					<h:commandLink
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
						actionListener="#{contractTreeBackingBean.processActionForStandardBenefit}"
						action="#{contractCoverageBackingBean.getCoverage}"
						id="stdbenefitDefine"
						onmousedown="javascript:navigatePageAction(this.id);getScroll();">
						<h:outputText value="#{node.description}"
							styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot5"/>
					</h:commandLink>
				</h:panelGroup>
			</f:facet>


	<!-- 	<f:facet name="administration">
				<h:panelGroup>

					<h:outputText value="#{node.description}"
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" />

				</h:panelGroup>
			</f:facet>
	 -->

			<f:facet name="Benefit-Administration">
				<h:panelGroup id="contracttreepanel6">
					<h:commandLink
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
						actionListener="#{contractTreeBackingBean.processActionForContractAdminOption}"
						action="#{adminOptionBackingBean.loadForContract}"
						id="benefitAdmin"
						onmousedown="javascript:navigatePageAction(this.id);getScroll();">
						<h:outputText value="#{node.description}"
							styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot6"/>
					</h:commandLink>

				</h:panelGroup>
			</f:facet>

			<f:facet name="Benefit-Level">
				<h:panelGroup id="Benefit-Level">

					<h:outputText value="#{node.description}"
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot7"/>

				</h:panelGroup>
			</f:facet>


			<f:facet name="Admin-Option">
				<h:panelGroup id="Admin-Option">
					<h:commandLink
						styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}"
						actionListener="#{contractTreeBackingBean.processActionForAdministration}"
						action="#{contractBenefitAdministrationBackingBean.loadQuestionsAction}"
						id="adminOption"
						onmousedown="javascript:navigatePageAction(this.id);getScroll();">
						<h:outputText value="#{node.description}"
							styleClass="#{t.nodeSelected ? 'treeNodeLinkSelected':'treeNodeLink'}" id="contracttreeot8"/>
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
