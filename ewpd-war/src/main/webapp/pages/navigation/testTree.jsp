<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>WellPoint Product Database</title>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<link href="../../css/wpd.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
</head>
<f:view>
	<jsp:include page="../navigation/top.jsp"></jsp:include>
	<body>
    <h:form>
	<table height="80%">
		<tr>
			<td width="20%" valign="top"><jsp:include page="../navigation/tree.jsp"></jsp:include>
             </td>
			<td>&nbsp;</td>
		</tr>
	</table>
    </h:form>
	</body>
	<%@ include file="../navigation/bottom.jsp"%>
</f:view>
</html>
