
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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Search Engine</TITLE>
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


</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:12px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="Search >> Search Results >> Notes >> Print">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
					<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>
						<div id="panel2Header" ><b>
							<h:outputText id="associationText" value="#{searchResultsBackingBean.associationBreadCrumb}">
                        </h:outputText></b>
						   <h:inputHidden id ="associationChain" value="#{searchResultsBackingBean.associationBreadCrumb}"/>
						</div>
							<BR>
						<!-- Table containing Tabs -->
								
								<!--  table starts -->
								<h:dataTable
										styleClass="outputText" headerClass="dataTableColumnHeader"
										var="item" cellpadding="3"
										width="100%" cellspacing="1" 
										value="#{searchResultsBackingBean.notes}">
										<h:column>
				  							  <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="ID"></h:outputText>
												</h:panelGroup>
											</f:facet>
											<h:outputText id="noteId" value="#{item.noteId}"></h:outputText>
											<h:inputHidden id="hiddenNoteId" value="#{item.noteId}"/>
										</h:column>
										<h:column>
				  							  <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Name"></h:outputText>
												</h:panelGroup>
											</f:facet>
											<h:outputText id="noteName" value="#{item.noteName}"></h:outputText>
											<h:inputHidden id="hiddenNoteName" value="#{item.noteName}"/>
										</h:column>
										 <h:column>
  											<f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Type"></h:outputText>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="typ"
												value="#{item.noteType}"></h:outputText>
										</h:column>
										<h:column>
  											<f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Text"></h:outputText>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="notetext"
												value="#{item.noteText}"></h:outputText>
										</h:column>
										<h:column>
  											<f:facet name="header">
												<h:panelGroup>
													<h:outputText value="version"></h:outputText>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="noteVersion"
												value="#{item.version}"></h:outputText>
											<h:inputHidden id="hiddenNoteVersion" value="#{item.version}"/>
										</h:column>
										
										<h:column>
  											<f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Status"></h:outputText>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="sta"
												value="#{item.status}"></h:outputText>
										</h:column>
							<!--  table ends -->
							</h:dataTable>
						</TD>
					</TR>
				</table>
				<!-- Space for hidden fields -->
				<h:inputHidden id="numberOfPages" value="#{searchResultsBackingBean.numberOfPages}"/>
				<h:inputHidden id="clickedIndex" value="#{searchResultsBackingBean.clickedIndex}"/>
				<h:inputHidden id="noteid" value="#{searchResultsBackingBean.noteid}"/>
				<h:inputHidden id="notename" value="#{searchResultsBackingBean.notename}"/>
				<h:inputHidden id="query" value="#{searchResultsBackingBean.query}"/>
				<h:inputHidden id="viewAssociation" value="#{searchResultsBackingBean.viewAssociation}"/>
				<h:inputHidden id="fieldToSort" value="#{searchResultsBackingBean.fieldToSort}"/>
				<h:inputHidden id="sortOrder" value="#{searchResultsBackingBean.notesSortOrder}"/>
				<h:inputHidden id="sortColumn" value="#{searchResultsBackingBean.notesSortColumn}"/>
				<h:inputHidden id="association" value="#{searchResultsBackingBean.association}"/>
				<!-- End of hidden fields  -->
			</h:form>
			<h:form styleClass="form" id="noteLocateForm">
				<h:inputHidden id="txtid"
											value="#{notesSearchBackingBean.noteIdForLocate}"></h:inputHidden>
				<h:inputHidden id="txtName"
											value="#{notesSearchBackingBean.noteName}"></h:inputHidden>

					<h:dataTable   id="txtSr"
										rowClasses="dataTableEvenRow,dataTableOddRow"
										style="display:none; visibility: hidden;" headerClass="dataTableColumnHeader"
										var="item" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										value="#{notesSearchBackingBean.notesSearchResultList}">
                          </h:dataTable>
				<h:commandLink id="locatePerformButton"  style="display:none; visibility: hidden;"
									 action="#{notesSearchBackingBean.locateNotes}">
                              <f:verbatim /></h:commandLink>	
			</h:form></td>

		</tr>
		<tr>
		
		</tr>
	</table>
	</BODY>
</f:view>


<script language="JavaScript">
	function moreActions(object){
		var objectName = object.name;
		var urlParts = objectName.split(':');
		var namVarId = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":" + "hiddenNoteId";
		var objId = document.getElementsByName(namVarId)[0];
		var namVarName = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":" + "hiddenNoteName";
		var objName = document.getElementsByName(namVarName)[0];
		document.getElementById('benefitComponentCreateForm:noteid').value = objId.value;
		document.getElementById('benefitComponentCreateForm:notename').value = objName.value;
	}

	function viewAction(but){	
			var id = but.id;
			var arr = id.split(':');
			var noteId = document.getElementById(arr[0] + ':' + arr[1] + ':' +  arr[2] + ':hiddenNoteId').value;
			var noteName = document.getElementById(arr[0] + ':' + arr[1] + ':' +  arr[2] + ':hiddenNoteName').value;
			var noteVersion = document.getElementById(arr[0] + ':' + arr[1] + ':' +  arr[2] + ':hiddenNoteVersion').value;
		 	var url = '../notes/notesView.jsp'+getUrl()+'?notesId='+noteId+"&noteName="+noteName+"&noteVersion="+noteVersion;
			newWinForView=window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");		
		}
	function setKeyForView() {
		var e = window.event;
		if(!e || e==undefined) {
			return false;
		}
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		document.getElementById('benefitComponentCreateForm:clickedIndex').value = rowcount;
		return true;
     }

		function setFieldsForSort(field, order,column,object){
		var ascImageUrl = "pushup1.jpg";
		var descImageUrl = "pushdown1.jpg";
		var objectUrl = object.src;
		var urlParts = objectUrl.split('/');
		var length = urlParts.length;
		objectUrl = urlParts[length - 1];
		if(null == object){
		return false;
		}
		if(ascImageUrl == objectUrl || descImageUrl == objectUrl){
		object.disabled = true;
		return false;
		}
		document.getElementById('benefitComponentCreateForm:fieldToSort').value = field;
		document.getElementById('benefitComponentCreateForm:sortOrder').value = order;
		document.getElementById('benefitComponentCreateForm:sortColumn').value = column;
		return true;
	}
window.print();
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="notessearchresultsprint" /></form>
</HTML>
