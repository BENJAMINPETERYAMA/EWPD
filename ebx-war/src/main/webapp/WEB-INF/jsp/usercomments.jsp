<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<title>Blue Exchange Automation</title>
</head>
	<div>	
	<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
          <tr class="">
            <td width="500px" class="headText">Change Comments</td>
		  </tr>
		  <tr class="">
            <td><textarea name="textarea" name="mappingCommens" id="textarea" rows="5" cols="60"></textarea></td>
		  </tr> 			
	</table>
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
		        <td width="20" height="20"><img src="images/footer_left.gif" width="20" height="20" /></td>
		        <td width="0" height="20"><a href="#">Save</a></td>
		        <td width="9" height="0"><img src="images/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">Cancel</a></td>
				<td width="18" height="20"><img src="images/footer_right.gif" width="18" height="20" /></td>
		      </tr>
	</table>	
</div>
