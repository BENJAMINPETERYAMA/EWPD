<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>
				<script type="text/javascript">
				function viewAllAuditTrail(spsId, fromMappingView) { 
					var fromMappingView = $("#fromMappingView").val();
					$.ajax({
						url: "${context}/ajaxviewallspsaudittrail/viewAllSPSAuditTrail.ajax",
						dataType: "html",
						type: "POST",
						data: "spsId="+spsId+"&fromMappingView="+fromMappingView,
							success: function(data) {
														
								$("#viewAuditTrailId").html(data);
							}
						});
			    }
				function retreiveAuditTrailInDetail(){
					$.ajax({
						url: "${context}/ajaxviewallspsaudittrail/retreiveSPSAuditTrailInDetail.ajax",
						dataType: "html",
						type: "POST",
						data: "spsId=${mapping.spsId.spsId}",
						success: function(data) {
							$("#viewAuditTrailInDetail").html(data);
							$("#viewAuditTrailInDetail").dialog({
									            minHeight : 150,
												maxHeight : 550,
												width : 899,
												position : 'center',
												resizable : true,
												title : 'Mapping Log',
									            modal : true
							});
						}
						});
				}
				</script>
			<div id="viewAuditTrailId">
				<div class="link fL mR10"  title="View Audit Trail">
					<c:if test="${sizeOfAuditTrail == 20}">
						<a href="#" style="color:blue;" onclick ='viewAllAuditTrail("${mapping.spsId.spsId}")';>View All</a>
					</c:if>
				</div>
				<div>
					<%@include file="mappingviewhistory.jsp"%>
				</div>
				<input type="hidden" name="fromMappingView" value="false" id="fromMappingView"/>
			</div>
<div id="viewAuditTrailInDetail"></div>
