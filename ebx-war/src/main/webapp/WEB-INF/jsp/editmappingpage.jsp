<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jspf/pageHead.jspf"%>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />

</head>



<body>
	<div id="wrapper">					<!-- wrapper Starts-->
		
		<div class="header_right">		<!-- header_right Starts-->
			<ul>
			  <li class="welcome">Welcome User</li>
				<li>|</li>
				<li><a href="#">Logout</a></li>
			</ul>
		</div><!-- header_right Ends-->
		
		<div class="header">			<!-- header Starts-->
	  			&#160;
		</div><!-- hedaer Ends-->
		<div id="container">
          <!-- container Starts-->
          <div class="innerContainer">
            <!-- innerContainer Starts-->
                
				<table width="960" border="0" cellspacing="0" cellpadding="0">
                  <tr class="createEditTable1">
                    <th width="117" scope="col"><div align="left" class="style7">Variable ID</div></th>
                    <th width="140" scope="col"><div align="left" class="style7">Description</div></th>
                    <th width="40" scope="col"><div align="left" class="style7">PVA</div></th>					
					<th width="127" scope="col"><div align="left" class="style7">Data Type</div></th>
                    <th width="217" scope="col"><div align="left" class="style7">Minor Heading</div></th>
                    <th width="217" scope="col"><div align="left" class="style7">Major Heading</div></th>
					<th width="87" scope="col"><div align="left" class="style7">System</div></th>
                    <th width="16" scope="col">&#160;</th>
                  </tr>
                </table>
                <table width="960" border="0" cellpadding="0" cellspacing="0" class="Pd3">
					<c:set var="viewHistoryRowCount" value="0" />
					<c:forEach items="${variableList}" var="variable">
						<tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
							<td width="80px">${variable.variableId}</td>
							<td width="140px">${variable.description}</td>
							<td width="40px">${variable.pva}</td>								
							<td width="90px">${variable.dataType}</td>
							<td width="90px">${variable.minorHeadings}</td>
							<td width="90px">${variable.majorHeadings}</td>	
							<td width="50px">${variable.variableSystem}</td>							 
						</tr>
					<c:set var="rowCount" value="${rowCount + 1}"/>
					</c:forEach>                  
                </table>
			
	  <div id="createEditContainer">	<!--createEditContainer Starts-->
				
				<!--First Table-->
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3">
	                  <tr class="createEditTable1-Listdata">
	                    <td width="143" class="headText">EB01 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="95">&#160;</td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" width="134">EB03 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="84">&#160;</td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148">EB02 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
						<td width="121">&#160;</td>
	                  </tr>
					</table>
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3">
	                  <tr class="createEditTable1-Listdata alternate">
	                    <td width="90px">
							<c:if test = "${! empty mapping.eb01.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" value="${mapping.eb01.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.eb01.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" id="EB01Id" />
							</c:if>
						</td>
	                    <td width="140px"><c:if test="${! empty mapping.eb01.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.eb01.hippaCodeSelectedValues[0].description}" /></c:if>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="90px"><input type="textbox" name="eb03Val" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[0].value}"/></td>
	                    <td width="140px"> <c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}" /></td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px">
							<c:if test = "${! empty mapping.eb02.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" value="${mapping.eb02.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.eb02.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" id="EB02Id" />
							</c:if>
						<td width="70px"><c:if test="${! empty mapping.eb02.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.eb02.hippaCodeSelectedValues[0].description}" /></c:if></td>
	                  </tr>
					
					  <tr class="createEditTable1-Listdata">
	                    <td>&#160;</td>
	                    <td>&#160;</td>
						<td>&#160;</td>
						<td><input type="textbox" name="eb03Val" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[0].value}"/></td>
						<td><c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/></td>	                    
	                    <td>&#160;</td>
	                    <td class="headText" width="134">EB06 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
						<td width="134">&#160;</td>
	                  </tr>
					  <tr class="createEditTable1-Listdata alternate">
	                    
	                    <td></td>
	                    <td>&#160;</td>
	                    <td>&#160;</td>						
						<td><input type="textbox" name="eb03Val"  class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[0].value}" /></td>
	                    <td><c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/></td>
	                    <td>&#160;</td>
	                  <td class="headText" width="134">
						<c:if test = "${! empty mapping.eb06.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb06Val" class="inputbox60" value="${mapping.eb06.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.eb06.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb06Val" class="inputbox60" id="EB06Id" />
						</c:if>					  
						<td><c:if test="${! empty mapping.eb06.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.eb06.hippaCodeSelectedValues[0].description}" /></c:if></td>
	                  </tr>
					  <tr class="createEditTable1-Listdata ">
	                    
	                    <td>&#160;</td>
	                    <td>&#160;</td>
	                    <td>&#160;</td>
						<td><input type="textbox" name="eb03val" class="inputbox60" value="${mapping.eb03.hippaCodeSelectedValues[0].value}"/></td>
	                    <td><c:out value="${mapping.eb03.hippaCodeSelectedValues[0].description}"/></td>
	                    <td>&#160;</td>
	                     <td class="headText" width="134">EB09 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
						<td width="134">&nbsp;</td>
	                  </tr>
 <tr class="createEditTable1-Listdata alternate">
		<td></td>
		<td></td>
		<td></td>
		<td><a href="#"><img src="${imageDir}/add.gif" width="19" height="19" /></a></td>
		<td></td>
		<td></td>
		<td width="134">
		<c:if test = "${! empty mapping.eb09.hippaCodeSelectedValues[0].value}">							
			<input type="textbox" name="eb09Val" class="inputbox60" value="${mapping.eb09.hippaCodeSelectedValues[0].value}" />
		</c:if>
		<c:if test = "${ empty mapping.eb09.hippaCodeSelectedValues[0].value}">							
				<input type="textbox" name="eb09Val" class="inputbox60" id="EB09Id" />
		</c:if>
		<td width="134"><c:if test="${! empty mapping.eb09.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.eb09.hippaCodeSelectedValues[0].description}" /></c:if></td>		
	</tr>
</table>
					<div class="fL mR10 pT5"><a href="#"></a></div>
			
			<!--Second Table-->		
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 bT mT5">
	                  <tr class="createEditTable1-Listdata">
	                    <td width="150px" class="headText">II02 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="150px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
						<td width="100px">&#160;</td>
	                  </tr>
					  <tr class="createEditTable1-Listdata alternate">
	                    <td width="100px">
							<c:if test = "${! empty mapping.ii02.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="ii02Val" class="inputbox60" id="${mapping.ii02.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.ii02.hippaCodeSelectedValues[0].value}">							
									<input type="textbox" name="ii02Val" class="inputbox60" id="II02Id" />
							</c:if>
						</td>
	                    <td width="100px">
						<c:if test="${! empty mapping.ii02.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.ii02.hippaCodeSelectedValues[0].description}" /></c:if></td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
	                    <td width="100px">&#160;</td>
						<td width="100px">&#160;</td>
	                  </tr>
					</table>
					
		    <table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3">
	                  <tr class="createEditTable1-Listdata">
	                    <td width="500" class="headText">Message &#160;
                        <input type="checkbox" value="${mapping.msg_type_required}"/>&#160;Required <a href="#">what is this?</a></td>
	                    <td width="92">&#160;</td>
						<td width="69">&#160;</td>
	                    <td width="189" class="headText">Note Type <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
						<td width="100">&#160;</td>
              </tr>
					  <tr class="alternate">
	                    <td width="500">
						<c:if test = "${! empty mapping.message}">							
								<input type="textbox" name="messageValue" class="inputbox60" value="${mapping.message}" />
						</c:if>
						<c:if test = "${ empty mapping.message}">							
								<input type="textbox" name="messageValue" class="inputbox60" id ="msg"/>
						</c:if>						
						</td>
	                    <td width="92">&#160;</td>
						<td width="69">&#160;</td>
	                    <td width="189">
						<c:if test = "${! empty mapping.noteTypeCode.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="noteTypeCodeVal" class="inputbox60" value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.noteTypeCode.hippaCodeSelectedValues[0].value}">							
									<input type="textbox" name="noteTypeCodeVal" class="inputbox60" id="noteTypeCodeId" />
						</c:if>
						</td>
						<td width="100"><c:if test="${! empty mapping.noteTypeCode.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.noteTypeCode.hippaCodeSelectedValues[0].description}" /></c:if></td>
              </tr>
				  </table>
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT10">
	                  <tr class="createEditTable1-Listdata">
	                    <td width="500px" class="headText">Accumulators &#160;<input type="checkbox" value="${mapping.sensitiveBenefitIndicator}"/>&#160;Not Required&#160;&#160;&#160;<a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="100px"></td>
	                  </tr>
					 <c:set var="accumValues" value="${mapping.accum.hippaCodeSelectedValues}" />
					<c:forEach items="${accumValues}" var="accumValues">
					      <tr class="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}">
					            <td width="100px"><c:if test = "${! empty accumValues.value}">							
								<input type="textbox" name="accumVal1" class="inputbox60" value="${accumValues.value}" />
							</c:if>
							<c:if test = "${ empty accumValues.value}">							
									<input type="textbox" name="accumVal1" class="inputbox60" id="ACCUM1Id" />
							</c:if></td>
					            <td width="100px"><c:if test = "${! empty accumValues.description}">
								<c:out value="${accumValues.description}" />
							</c:if></td>				            
					      </tr>
						<c:set var="rowCount" value="${rowCount + 1}"/>
					</c:forEach>	
					
				</table>
				<div class="fL mR10 pT5"><a href="#"><img src="${imageDir}/add.gif" width="19" height="19"/></a></div>
				<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
					<tr class="createEditTable1-Listdata">
	                    <td width="143" class="headText">HSD01 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="95">&#160;</td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" width="134">HSD05 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="84">&#160;</td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
	                  <tr class="createEditTable1-Listdata alternate">
	                    <td width="90px">
							<c:if test = "${! empty mapping.hsd01.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" value="${mapping.hsd01.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd01.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" id="EB01Id" />
							</c:if>
						</td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd01.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd01.hippaCodeSelectedValues[0].description}" /></c:if>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="90px"><c:if test = "${! empty mapping.hsd05.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" value="${mapping.hsd05.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd05.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" id="EB02Id" />
							</c:if></td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd05.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd05.hippaCodeSelectedValues[0].description}" /></c:if></td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>
					<tr class="createEditTable1-Listdata">
	                    <td width="143" class="headText">HSD02 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="95">&#160;</td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" width="134">HSD06 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="84">&#160;</td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
					<tr class="createEditTable1-Listdata alternate">
	                    <td width="90px">
							<c:if test = "${! empty mapping.hsd02.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" value="${mapping.hsd02.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd02.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" id="EB01Id" />
							</c:if>
						</td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd02.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd02.hippaCodeSelectedValues[0].description}" /></c:if>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="90px"><c:if test = "${! empty mapping.hsd06.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" value="${mapping.hsd06.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd06.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" id="EB02Id" />
							</c:if></td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd06.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd06.hippaCodeSelectedValues[0].description}" /></c:if></td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>	
					 <tr class="createEditTable1-Listdata">
	                    <td width="143" class="headText">HSD03 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="95">&#160;</td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" width="134">HSD07 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="84">&#160;</td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
					<tr class="createEditTable1-Listdata alternate">
	                    <td width="90px">
							<c:if test = "${! empty mapping.hsd03.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" value="${mapping.hsd03.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd03.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" id="EB01Id" />
							</c:if>
						</td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd03.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd03.hippaCodeSelectedValues[0].description}" /></c:if>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="90px"><c:if test = "${! empty mapping.hsd07.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" value="${mapping.hsd07.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd07.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" id="EB02Id" />
							</c:if></td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd07.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd07.hippaCodeSelectedValues[0].description}" /></c:if></td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>	
					 <tr class="createEditTable1-Listdata">
	                    <td width="143" class="headText">HSD04 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="95">&#160;</td>
	                    <td width="104">&#160;</td>
	                    <td class="headText" width="134">HSD08 <a href="#">what is this?</a>&#160;<a href="#"><img src="${imageDir}/icon-popup.gif" width="15" height="14"/></a></td>
	                    <td width="84">&#160;</td>
	                    <td width="121">&#160;</td>
	                    <td class="headText" width="148"></td>
						<td width="121">&#160;</td>
	                  </tr>	
					<tr class="createEditTable1-Listdata alternate">
	                    <td width="90px">
							<c:if test = "${! empty mapping.hsd04.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" value="${mapping.hsd04.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd04.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb01Val" class="inputbox60" id="EB01Id" />
							</c:if>
						</td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd04.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd04.hippaCodeSelectedValues[0].description}" /></c:if>
						</td>
	                    <td width="105px">&#160;</td>
	                    <td width="90px"><c:if test = "${! empty mapping.hsd08.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" value="${mapping.hsd08.hippaCodeSelectedValues[0].value}" />
							</c:if>
							<c:if test = "${ empty mapping.hsd08.hippaCodeSelectedValues[0].value}">							
								<input type="textbox" name="eb02Val" class="inputbox60" id="EB02Id" />
							</c:if></td>
	                    <td width="140px"><c:if test="${! empty mapping.hsd08.hippaCodeSelectedValues[0].description}">
						<c:out value="${mapping.hsd08.hippaCodeSelectedValues[0].description}" /></c:if></td>
	                    <td width="105px">&#160;</td>
	                    <td width="120px"></td>							
						<td width="70px"></td>
	                  </tr>			
					</table>	
		    <!--Fourth Table-->	
					
					<table width="950" border="0" cellpadding="0" cellspacing="0" class="Pd3 mT5 bT">
	                  <tr class="">
	                    <td width="900px" class="headText">Change Comments</td>
					  </tr>
					  <tr class="">
	                    <td><textarea name="textarea" name="mappingCommens" id="textarea" rows="5" cols="110"></textarea></td>
					  </tr> 			
					</table>
					
				</div><!--createEditContainer Ends-->			
          
          </div>
		  <!-- innerContainer Ends-->
        </div>
		<!-- container Ends-->
		<div class="footer">
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		      <tr>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">View history</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">Save</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">Done</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">Cancel</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">Not Applicable</a></td>
		        <td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">Send to Test</a></td>
				<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#">Approve</a></td>
				<td width="9" height="0"><img src="${imageDir}/seperator.gif" width="9" height="20"/></td>
		        <td width="0" height="20"><a href="#" class="dicard">Discard Changes</a></td>
		        <td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
		      </tr>
		    </table>					
		</div>	
	
	</div>
	
	<!-- wrapper Ends-->
	

</body>
</html>
