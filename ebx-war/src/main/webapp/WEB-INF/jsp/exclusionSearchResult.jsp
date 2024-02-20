<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<table  id="exclusionResultTab"  class="exclusionSearchTable shadedTextExclude" border="0" cellpadding="0" cellspacing="0" width="100%">
		 					
		
						<TBODY>
					
						<c:if test="${! empty exclusionList}" >
							<c:set var="rowCount" value="0" />
							<c:forEach items="${exclusionList}" var="exclusionVar">
							
									<tr class="alternate" height="15">
										<c:choose>
										<c:when test="${!empty exclusionVar.primaryExclusion && !empty exclusionVar.secondaryExclusion}">
											
											<c:choose>
											
											<c:when test="${exclusionVar.primaryExclusion == 'CONTRACT'}">
												<td width="15%"  class="headTextExclusions"  >Contract</td>
											</c:when>
											
											<c:when test="${exclusionVar.primaryExclusion == 'PRODUCT'}">
												<td width="15%"  class="headTextExclusions" >Product Code</td>
											</c:when>
											
											<c:when test="${exclusionVar.primaryExclusion == 'PRODUCTLINE'}">
												<td width="15%" class="headTextExclusions" >Product Line</td>
											</c:when>
											
											<c:otherwise>
												<td width="15%" class="headTextExclusions" >&#160;</td>
											</c:otherwise>
											
											</c:choose>
											
											<c:choose>
											
											<c:when test="${exclusionVar.secondaryExclusion == 'VARIABLE'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td width="73%" class="headTextExclusions" >Variables</td>
												</c:when>
												<c:otherwise>
													<td width="73%" class="headTextExclusions" >Variable</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.secondaryExclusion == 'SPS'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td width="73%" class="headTextExclusions" >SPS IDs</td>
												</c:when>
												<c:otherwise>
													<td width="73%"  class="headTextExclusions" >SPS ID</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.secondaryExclusion == 'HEADERRULE'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td width="73%"  class="headTextExclusions" >Header Rules</td>
												</c:when>
												<c:otherwise>
													<td width="73%" class="headTextExclusions" >Header Rule</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.secondaryExclusion == 'ACCUM'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td width="73%"  class="headTextExclusions" >Accums</td>
												</c:when>
												<c:otherwise>
													<td width="73%" class="headTextExclusions" >Accum</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:otherwise>
												<td width="73%"  class="headTextExclusions" >&#160;</td>
											</c:otherwise>
											
											</c:choose>
											
											<td>&#160;</td>
											
										</c:when>
										
										<c:otherwise>
											<c:choose>
											
											<c:when test="${exclusionVar.primaryExclusion == 'CONTRACT'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td colspan="2" width="88%"  class="headTextExclusions" >Contracts</td>
												</c:when>
												<c:otherwise>
													<td colspan="2" width="88%"  class="headTextExclusions" >Contract</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.primaryExclusion == 'PRODUCT'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td colspan="2" width="88%"  class="headTextExclusions" >Product Codes</td>
												</c:when>
												<c:otherwise>
													<td colspan="2" width="88%" class="headTextExclusions" >Product Code</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.primaryExclusion == 'PRODUCTLINE'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td colspan="2" width="88%"  class="headTextExclusions">Product Lines</td>
												</c:when>
												<c:otherwise>
													<td colspan="2" width="88%"  class="headTextExclusions" style="padding-top: 1px;">Product Line</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.primaryExclusion == 'SPS'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td colspan="2" width="88%" class="headTextExclusions">SPS</td>
												</c:when>
												<c:otherwise>
													<td colspan="2" width="88%" class="headTextExclusions">SPS</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.primaryExclusion == 'VARIABLE'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td colspan="2" width="88%" class="headTextExclusions">Variables</td>
												</c:when>
												<c:otherwise>
													<td colspan="2" width="88%" class="headTextExclusions">Variable</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											<c:when test="${exclusionVar.primaryExclusion == 'HEADERRULE'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td colspan="2" width="88%" class="headTextExclusions">Header Rule</td>
												</c:when>
												<c:otherwise>
													<td colspan="2" width="88%" class="headTextExclusions">Header Rule</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											

											
											<c:when test="${exclusionVar.primaryExclusion == 'ACCUM'}">
												<c:choose>
												<c:when test="${exclusionVar.exclusionCount > 1}">
													<td colspan="2" width="88%" class="headTextExclusions">Accums</td>
												</c:when>
												<c:otherwise>
													<td colspan="2" width="88%" class="headTextExclusions">Accum</td>
												</c:otherwise>
												</c:choose>	
											</c:when>
											
											
											
											<c:otherwise>
												<td colspan="2" width="88%" class="headTextExclusions">&#160;</td>
											</c:otherwise>
											
											</c:choose>
										
										<td>&#160;</td>
										
										</c:otherwise>
										
										</c:choose>
									</tr>
									
									
									<tr class="white-bg" id="${exclusionVar.exclusionId}">
									<c:choose>
									<c:when test="${!empty exclusionVar.primaryExclusion && !empty exclusionVar.secondaryExclusion}">
										<td   width="15%"  style="word-wrap:break-word;WORD-BREAK:BREAK-ALL;vertical-align: top; padding-top: 2px; border-bottom: 1px solid #d9e5eb;">
											<div class="minimumHeight">
												<c:if test="${! empty exclusionVar.primaryValues}" >
													<c:out value="${exclusionVar.primaryValues}"  />
												</c:if>
											</div>
										</td>
										<td  width="73%"  style="word-wrap:break-word;WORD-BREAK:BREAK-ALL; padding-top: 2px; border-bottom: 1px solid #d9e5eb;">
											<div class="minimumHeight">
											<c:if test="${! empty exclusionVar.secondaryValues}" >
												<c:out value="${exclusionVar.secondaryValues}"  />
											</c:if>
											</div>
										</td>
									</c:when>
									
									<c:otherwise>
									
									<td width="88%"  colspan="2" style="word-wrap:break-word;WORD-BREAK:BREAK-ALL; padding-top: 2px; border-bottom: 1px solid #d9e5eb;">
											<div class="minimumHeight">
												<c:if test="${! empty exclusionVar.primaryValues }" >
													<c:out value="${exclusionVar.primaryValues}" />
												</c:if>
											</div>
									</td>
									</c:otherwise>
									
									</c:choose>
									
									<td width="12%" class="exclusionIcons" align="center" style="border-bottom: 1px solid #d9e5eb; padding-right: 2px;">
								<c:if test="${userUIPermissions.authroizedToManageExclusion}">	
								<a href="#" >
								</a>&#160;
								<a href="#" onclick='editExclusion("${exclusionVar.exclusionId}", "${exclusionVar.primaryExclusion}", "${exclusionVar.primaryValues}", "${exclusionVar.secondaryExclusion}", "${exclusionVar.secondaryValues}");'>
									<img src="${imageDir}/edit_icon.gif"  alt="Edit" />
								</a>	
									&#160;
									<a href="#" onclick='openExclusionDeleteConfirmation("${exclusionVar.exclusionId}");'>
										<img src="${imageDir}/markAsNotApp.gif"  alt="Delete" />
									</a>
								</c:if>
								</td>
									
									</tr>
									
									<c:set var="rowCount" value="${rowCount + 1}"/>
									
							</c:forEach>
						</c:if>
						
								<c:if test="${empty exclusionList}" >
									<tr> <td colspan = "3" align="center" > No Exclusions Found </td></tr>
							</c:if>
						
						
												
						
						
					</TBODY>
		
		</table>
	
	