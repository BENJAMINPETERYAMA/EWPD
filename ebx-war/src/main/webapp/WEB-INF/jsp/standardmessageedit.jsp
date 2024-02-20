	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type = "text/javascript">
 $(document).ready(function(){	
	$('#standardMsgEditNew').blur(function() {
		$('#standardMsgEditNew').val($('#standardMsgEditNew').val().toUpperCase());		
	});
 });
</script>

	<div id="createEditContainer1" style="margin-top:10px; height: 50px">	<!--createEditContainer Starts-->			
				<!--First Table-->
		<input type="hidden" name="standardMsgEditFromPage" id="standardMsgEditFromPage" value="${stdMsgEdit}"/>

		<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3" >	
			<tr class="createEditTable1-Listdata">	
				<td>
				</td>				
		         <td style="vertical-align:middle" class="headTextExclusions" width="100px">Message</td>
		         <td>
					<textarea type="text"  class="standardMsg" id="standardMsgEditNew" name="standardMsgEditNew" >${standardMsgEdit}</textarea>			
				</td>
	        </tr>
		</table>
	</div><!--createEditContainer Ends-->		
