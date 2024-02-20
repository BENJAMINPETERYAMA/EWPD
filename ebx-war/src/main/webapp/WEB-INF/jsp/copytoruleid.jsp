<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(document).ready(function(){
		$('#spsIdForCopyTo1').blur(function() {
		   if( $(this).val()== null || $.trim($(this).val()).length < 1 ) {
		   	$("#createIdLabel").text('');
		   }
			$('#spsIdForCopyTo1').val($('#spsIdForCopyTo1').val().toUpperCase());	
		});
	
	$("#spsIdForCopyTo1").autocomplete({ 
		select: function(event, ui) { $("#createIdLabel").text(ui.item.id).removeClass('invalid_hippacode_value'); },
		source: function(request, response) {
					$.ajax({
						url: "${context}/ajaxautocomplete.ajax",
						dataType: "json",
						type:"POST",  
						data: "key="+escape(request.term)+ "&name=RULEID",
						success: function(data) {
						cache: false,
						response(data.rows);
						list = data.rows;
						}
					});
		},
		change: function(event, ui) {
			var text = $(this).val();
			if(!ui.item){
				displayLabelForSelectedItem(text,list,"createIdLabel",inValidRuleID);
			}
		}

	})
  });
</script>

<table border="0" cellpadding="0" cellspacing="0"
	class="mt10 mL10 Pd3 shadedText">
	<tr>

		<td height="26" width="150px">RULE ID <SPAN class="star">*</SPAN></td>
		<td height="26" width="150px">
			<INPUT type="text" name="spsIdForCopyTo1" id="spsIdForCopyTo1" class="input" />
			<INPUT type="hidden" name="oldSPSID" id="oldSPSID" value="${mapping.rule.headerRuleId}" />
		</td>
		<td height="26" width="661">
			<LABEL id="createIdLabel" for="spsIdForCopyTo1" style="font-size:11px"></LABEL>
		</td>
	</tr>
</table>
<br></br>
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="0" height="20">
		</td>
		<td width="0" height="20"><a href="#" onclick='copyTo();'><img
			src="${imageDir}/ok_but.gif" alt="OK" title="OK"/></a></td>

	</tr>
</table>


