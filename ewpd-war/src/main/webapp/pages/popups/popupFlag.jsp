<%@page import="java.util.List"%>
<%@page import="com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO"%>
<%
	
	List<ContractWebServiceVO> contractWSErrorList = request
			.getAttribute("contractWSErrorList") != null ? (List<ContractWebServiceVO>) request
			.getAttribute("contractWSErrorList")
			: null;
	boolean canDisplayErrorPopup = false;
	if (contractWSErrorList != null && contractWSErrorList.size() > 0) {
		canDisplayErrorPopup = true;
	}
%>

<%
	if (canDisplayErrorPopup) {
		session.setAttribute("contractWSErrorList",contractWSErrorList);
%>
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
<script>
	var url = '../popups/ebxValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
	var popUpObj = window.open(url,'eBXErrorValidation','height=400,width=900,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no, status=no');
	popUpObj.focus();
</script>
<%
	}
%>

