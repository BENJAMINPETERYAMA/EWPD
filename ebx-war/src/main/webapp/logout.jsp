<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/pageTop.jspf"%>
<div>
<h1></h1>
</div>
<div>
<h3>You have successfully logged out of BX.  Please close this browser window.</h3>
</div>
</div>
<!-- container Ends-->
<%
	request.getSession().invalidate();
 %>
<div class="footer"><!-- footer Starts--></div>
<!-- footer Ends-->
</div>