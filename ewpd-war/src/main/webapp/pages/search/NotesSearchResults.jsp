
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
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include page="../navigation/tree.jsp"></jsp:include></DIV>

						</TD>

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
									<div id="resultHeaderDiv" style="width:100%;">
											<div style="text-align:center;"><h:panelGrid id="panelTable"
								 	binding="#{searchResultsBackingBean.panel}"  >    
								</h:panelGrid></div>

								<!--  table starts -->
								<h:dataTable
										rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableColumnHeader"
										var="item" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										value="#{searchResultsBackingBean.notes}">
										<h:column>
				  							  <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="ID"></h:outputText>
													<h:commandButton id="asc0" alt="Sort Ascending" title="Sort Ascending"
											   			image="#{searchResultsBackingBean.columnOneImageAsc}"  
														onclick="setFieldsForSort('NOTE_ID_ORDER','ASC','1',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc0"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnOneImageDesc}"  
													    onclick="setFieldsForSort('NOTE_ID_ORDER','DESC','1',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
												</h:panelGroup>
											</f:facet>
											<h:outputText id="noteId" value="#{item.noteId}"></h:outputText>
											<h:inputHidden id="hiddenNoteId" value="#{item.noteId}"/>
										</h:column>
										<h:column>
				  							  <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Name"></h:outputText>
													<h:commandButton id="asc1" alt="Sort Ascending" title="Sort Ascending"
											   			image="#{searchResultsBackingBean.columnTwoImageAsc}"  
														onclick="setFieldsForSort('NOTE_NAME','ASC','2',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc1"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnTwoImageDesc}"  
													    onclick="setFieldsForSort('NOTE_NAME','DESC','2',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
												</h:panelGroup>
											</f:facet>
											<h:outputText id="noteName" value="#{item.noteName}"></h:outputText>
											<h:inputHidden id="hiddenNoteName" value="#{item.noteName}"/>
										</h:column>
										 <h:column>
  											<f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Type"></h:outputText>
													<h:commandButton id="asc2" alt="Sort Ascending" title="Sort Ascending"
											   			image="#{searchResultsBackingBean.columnThreeImageAsc}"  
														onclick="setFieldsForSort('NOTE_TYPE_ORDER','ASC','3',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc2"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnThreeImageDesc}"  
													    onclick="setFieldsForSort('NOTE_TYPE_ORDER','DESC','3',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="typ"
												value="#{item.noteType}"></h:outputText>
										</h:column>
										<h:column>
  											<f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Text"></h:outputText>
													<h:commandButton id="asc3" alt="Sort Ascending" title="Sort Ascending"
											   			image="#{searchResultsBackingBean.columnFourImageAsc}"  
														onclick="setFieldsForSort('NOTE_TEXT_ORDER','ASC','4',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc3"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnFourImageDesc}"  
													    onclick="setFieldsForSort('NOTE_TEXT_ORDER','DESC','4',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="notetext"
												value="#{item.noteText}"></h:outputText>
										</h:column>
										<h:column>
  											<f:facet name="header">
												<h:panelGroup>
													<h:outputText value="version"></h:outputText>
													<h:commandButton id="asc4" alt="Sort Ascending" title="Sort Ascending"
											   			image="#{searchResultsBackingBean.columnFiveImageAsc}"  
														onclick="setFieldsForSort('NOTE_VERSION_ORDER','ASC','5',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc4"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnFiveImageDesc}"  
													    onclick="setFieldsForSort('NOTE_VERSION_ORDER','DESC','5',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
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
													<h:commandButton id="asc5" alt="Sort Ascending" title="Sort Ascending" 
											   			image="#{searchResultsBackingBean.columnSixImageAsc}"  
														onclick="setFieldsForSort('NOTE_STATUS_ORDER','ASC','6',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc5"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnSixImageDesc}"  
													    onclick="setFieldsForSort('NOTE_STATUS_ORDER','DESC','6',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="sta"
												value="#{item.status}"></h:outputText>
										</h:column>
										
										<h:column>
											 <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Actions"></h:outputText>
											   </h:panelGroup>
											</f:facet>	
										<h:commandButton id="image"
										   alt="List entities to which this is associated to" title="List entities to which this is associated to" rendered="#{searchResultsBackingBean.authorizedForAttachments == true}"
										   image="../../images/Association.gif"  onclick="setKeyForView();" action="#{searchResultsBackingBean.viewAttachments}">
										</h:commandButton>  
											<f:verbatim> &nbsp; </f:verbatim>
										<h:commandButton alt="View Notes" title="View Notes" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction(this);return false;" rendered="#{searchResultsBackingBean.authorizedForView == true}" ></h:commandButton>
											<f:verbatim> &nbsp; </f:verbatim>
										<h:commandButton id="image2"
										   alt="More Actions" title="More Actions"
										   image="../../images/more_actions.gif"  action="#{searchResultsBackingBean.viewMoreNotesObject}"
											onclick="setKeyForView();moreActions(this);">
										</h:commandButton>    
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
			<td><%@ include file="../navigation/bottom.jsp"%></td>
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

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="notessearchresultsprint" /></form>
</HTML>
