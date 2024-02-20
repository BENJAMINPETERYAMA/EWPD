<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<body>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
function referDataSubmit()	{
	if(document.forms['referenceForm'].reference[0].checked){
		document.forms['referenceForm'].action="${context}/referencedata/showCategoryCodeMapping.ajax";
		document.forms['referenceForm'].submit();
	}else if(document.forms['referenceForm'].reference[1].checked) {
		document.forms['referenceForm'].action="${context}/referencedata/showExclusionManagePage.html";
		document.forms['referenceForm'].submit();
	} /* Reference Data Values -  START */
	else if(document.forms['referenceForm'].reference[2].checked) {
		document.forms['referenceForm'].action="${context}/referencedata/showReferenceDataManagePage.html";
		document.forms['referenceForm'].submit();
	}  /* Reference Data Values -  END */
	/* Manage Reference Data Values April 2012 Release SSCR14181 START */
	else if(document.forms['referenceForm'].reference[3].checked) {
		document.forms['referenceForm'].action="${context}/referencedata/showManageDataTypeToEB01Page.html";
		document.forms['referenceForm'].submit();
	}
	else if(document.forms['referenceForm'].reference[4].checked) {
		document.forms['referenceForm'].action="${context}/referencedata/showManageHeaderRuleToEB03Page.html";
		document.forms['referenceForm'].submit();
	}/* April 2012 Release SSCR14181 END */
	else if(document.forms['referenceForm'].reference[5].checked) {//BXNI June 2012 Release
		document.forms['referenceForm'].action="${context}/referencedata/showManageStandardMessagePage.html";
		document.forms['referenceForm'].submit();
	}
	else if(document.forms['referenceForm'].reference[6].checked) {//BXNI November 2012 Release
		document.forms['referenceForm'].action="${context}/referencedata/showManageServiceTypeCodeToEB11Page.html";
		document.forms['referenceForm'].submit();
	}
	else if(document.forms['referenceForm'].reference[7].checked) {//BXNI November 2012 Release
		document.forms['referenceForm'].action="${context}/referencedata/showHPNReferenceDataManagePage.html";
		document.forms['referenceForm'].submit();
	}
	else if(document.forms['referenceForm'].reference[8].checked) {//BXNI November 2012 Release
		document.forms['referenceForm'].action="${context}/managespidereb03exclusion/showManageEB03ExclusionPage.html";
		document.forms['referenceForm'].submit();
	}
	else if(document.forms['referenceForm'].reference[9].checked) {//BXNI November 2012 Release
		document.forms['referenceForm'].action="${context}/managespidereb03defaultmapping/showManageEB03DefaultMappingPage.html?"+ "currentPage=" + '0'
									+ "&section=" + 'fromLanding';
		document.forms['referenceForm'].submit();
	}
}
</script>


<form name="referenceForm" method="POST">
<div>
<table border="0" cellspacing="0" width="100%">
	<tr height="20">
		<td width="7%"><input type="radio" name="reference"
			checked="checked"></td>
		<td width="93%">Manage Category Code-Service Type Mapping</td>
	</tr>


	<tr height="20">
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage Exclusions</td>
	</tr>
	<!-- Reference Data Values -  START -->
	<tr height="20">
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage Reference Data Values</td>
	</tr>
	<!-- Reference Data Values -  END -->
	<!--  Manage Reference Data Values April 2012 Release SSCR14181 START-->
	<tr height="20">
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage EB01- Data Type Association</td>
	</tr>
	<tr height="20">
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage EB03 - Header Rule Association</td>
	</tr> <!-- April 2012 Release SSCR14181 END-->
	<tr height="20">
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage Standard Message</td>
	</tr> 	
	<tr height="20">
	<!--Added as part of BXNI November Release-->
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage Service Type Code - EB11 mapping</td>
	</tr>
	<tr height="20">
	<!--Added as part of EBX HPN November Release-->
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage HPN Reference Data Values</td>
	</tr>
	<tr height="20">
	<!--Added as part of EBX HPN November Release-->
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage Spider UM EB03 Exclusions</td>
	</tr>
	<tr height="20">
	<!--Added as part of EBX HPN November Release-->
		<td width="7%"><input type="radio" name="reference"></td>
		<td width="93%">Manage Spider UM EB03 Default Mapping</td>
	</tr>
<tr height="20"></tr>

	<tr height="20">
		<td colspan="2"><IMG src="${imageDir}/Go.gif" width="60px"
			onclick="referDataSubmit();"></td>
	</tr>

</table>



</div>

</form>
</body>
</html>
