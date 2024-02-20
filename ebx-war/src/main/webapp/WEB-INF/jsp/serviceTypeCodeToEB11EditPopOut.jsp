	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ebxmapping.js"></script>
 
<script type="text/javascript" >

</script>
</head>
<body >
<table  width="800" border="0" cellpadding="0" cellspacing="0" class="Pd3" ><tr></tr></table>
		 <table width="900" border="0" cellpadding="0" cellspacing="0" class="Pd3" > 
				<tr class="createEditTable1-Listdata">					
	                   <td  class="headTextExclusions" width="50" >LOB/State:  </td>	
	                   <td width="150" align="left" >
							<input type="hidden" value="" id="lobNamehiddenPopOut">
							<input type="hidden" value="" id="lobidhiddenPopOut">
							<input type="hidden" value="" id="actionhiddenPopOut">
							<label id="lobLabelPopOut" ></label>
							<input type="text" id="lobTextPopOut" name = lobTextPopOut" value=""  style="display:none;width: 95%;" maxlength="25" >			
					   </td>
					   <td width="10">&nbsp;</td>	   
	                   <td  class="headTextExclusions" width="40" >MBU:  </td>	
	                   <td  width="400">
	                     	<input type="hidden" value="" id="mbuIdHiddenPopOut" >
							<label id="mbuLabelPopOut"></label>	
							
							<textarea  id="mbuTextPopOut" style="display:none;width: 95%;" maxlength="25" ></textarea>		
	                   </td>
	                    <td width="10">&nbsp;</td>	
	                   <td width="60" class="headTextExclusions" align="left">
	                   		<input type="checkbox"  name="isSSBPopOut" id="isSSBPopOut"  value=""/>&nbsp;SSB
	                
	                   </td>
	                   	<td width="20">&nbsp;</td>	                
	                   <td id = "editTDPopOut" width="80" align="right">
	                   <a href="#" style="color:blue;" onclick="editLobMbuPopOut();" >Edit</a>
	                   </td>
	                  </tr>
	                  </table>
	<table width="925px" border="0" cellpadding="0" cellspacing="0" class="Pd3" >	
				<tr  style="position: fixed; top:0; background-color: #D5DFE5">					
	                    	<td width="95px"  class="headTextExclusions">EB03</td>
	                    	<td width="120px"></td>
							<td width="110px"></td>
							<td width="25px"></td>
							<td width="95px" class="headTextExclusions" >EB11</td>
							<td width="95px"></td>	
							<td width="110px"></td>
							<td width="110px" class="headTextExclusions">Place of Service</td>
	                   		<td width="120px"></td> 
							<td width="50px"></td>         
	       				</tr>
					</table>

<div id="serviceTypeCreateEditContainer"
	style="height: 400px; width: 907px"><!--createEditContainer Starts-->
<table width="920" border="0" cellpadding="0" cellspacing="0"
	class="Pd3" id="serviceTypeMappingsTablePopOut">
	<tbody id="serviceTypeMappingsTbodyPopOut">

		<tr class="createEditTable1-Listdata alternate">
			<td style="width: 95px"><input type="text"
				name="serviceTypeCodeValPopOut" class="inputbox60"
				id="serviceTypeCodePopOutId0" /></td>
			<td style="width: 140px"><label
				id="serviceTypeCodePopOutIdLabel0" for="serviceTypeCodePopOutId0"
				style="font-size: 11px"> <c:out value="" /> </label> <input
				type="hidden" id="serviceTypeCodeDescPopOut0"
				name="serviceTypeCodeDescPopOut" /></td>
			<td width="120px"></td>
			<td style="width: 95px"><input type="text" name="eb11ValPopOut"
				class="inputbox60" id="eb11PopOutId0" /></td>
			<td style="width: 140px"><label id="eb11IdPopOutLabel0"
				for="eb11PopOutId0" style="font-size: 11px"> <c:out value="" />
			</label> <input type="hidden" id="eb11DescPopOut0" name="eb11DescPopOut" />

			</td>
			<td width="120px"></td>
			<td style="width: 95px"><input type="text"
				name="placeOfServiceValPopOut" class="inputbox60"
				id="placeOfServicePopOutId0" /></td>
			<td style="width: 140px"><label
				id="placeOfServicePopOutIdLabel0" for="placeOfServicePopOutId0"
				style="font-size: 11px"> <c:out value="" /> </label> <input
				type="hidden" id="placeOfServiceDescPopOut0"
				name="placeOfServiceDescPopOut" /></td>
			<td width="120px"></td>
		</tr>
		<tr class="createEditTable1-Listdata ">
			<td style="width: 95px"><input type="text"
				name="serviceTypeCodeValPopOut" class="inputbox60"
				id="serviceTypeCodePopOutId1" /></td>
			<td style="width: 140px"><label
				id="serviceTypeCodePopOutIdLabel1" for="serviceTypeCodePopOutId1"
				style="font-size: 11px"> <c:out value="" /> </label> <input
				type="hidden" id="serviceTypeCodeDescPopOut1"
				name="serviceTypeCodeDescPopOut" /></td>
			<td width="120px"></td>
			<td style="width: 95px"><input type="text" name="eb11ValPopOut"
				class="inputbox60" id="eb11PopOutId1" /></td>
			<td style="width: 140px"><label id="eb11IdPopOutLabel1"
				for="eb11PopOutId1" style="font-size: 11px"> <c:out value="" />
			</label> <input type="hidden" id="eb11DescPopOut1" name="eb11DescPopOut"
				value="" /></td>
			<td width="120px"></td>
			<td style="width: 95px"><input type="text"
				name="placeOfServiceValPopOut" class="inputbox60"
				id="placeOfServicePopOutId1" /></td>
			<td style="width: 140px"><label
				id="placeOfServicePopOutIdLabel1" for="placeOfServicePopOutId1"
				style="font-size: 11px"> <c:out value="" /> </label> <input
				type="hidden" id="placeOfServiceDescPopOut1"
				name="placeOfServiceDescPopOut" /></td>
			<td width="120px"></td>
		</tr>


		<tr class="createEditTable1-Listdata alternate">
			<td style="width: 95px"><input type="text"
				name="serviceTypeCodeValPopOut" class="inputbox60"
				id="serviceTypeCodePopOutId2" /></td>
			<td style="width: 140px"><label
				id="serviceTypeCodePopOutIdLabel2" for="serviceTypeCodePopOutId2"
				style="font-size: 11px"> <c:out value="" /> </label> <input
				type="hidden" id="serviceTypeCodeDescPopOut2"
				name="serviceTypeCodeDescPopOut" /></td>
			<td width="120px"></td>
			<td style="width: 95px"><input type="text" name="eb11ValPopOut"
				class="inputbox60" id="eb11PopOutId2" /></td>
			<td style="width: 140px"><label id="eb11IdPopOutLabel2"
				for="eb11PopOutId2" style="font-size: 11px"> <c:out value="" />
			</label> <input type="hidden" id="eb11DescPopOut2" name="eb11DescPopOut" />

			</td>
			<td width="120px"></td>
			<td style="width: 95px"><input type="text"
				name="placeOfServiceValPopOut" class="inputbox60"
				id="placeOfServicePopOutId2" /></td>
			<td style="width: 140px"><label
				id="placeOfServicePopOutIdLabel2" for="placeOfServicePopOutId2"
				style="font-size: 11px"> <c:out value="" /> </label> <input
				type="hidden" id="placeOfServiceDescPopOut2"
				name="placeOfServiceDescPopOut" /></td>
			<td width="120px"></td>
		</tr>
	</tbody>
</table>
<div>
<table border="0">
	<tr>
		<td width="90px"><a href="#"
			onclick="addRowForServiceTypeMappingsPopOut('serviceTypeMappingsTablePopOut',true,'serviceTypeCodePopOutId','serviceTypeCodeValPopOut','','','eb11PopOutId','eb11ValPopOut','','','placeOfServicePopOutId','placeOfServiceValPopOut','','');"><img
			id="serviceTypeMappingsAddButton" src="${imageDir}/add.gif"
			width="19" height="19" /></a></td>
	</tr>
	<tr></tr>
</table>
</div>

</div>
<!--createEditContainer Ends-->	
				
				
				<table>
					<tr><td>&nbsp;</td></tr>
					<tr><td>&nbsp;</td></tr>
					
				</table>
<div id="saveFromPopOutDiv">
	<table border="0" align="center" cellpadding="0" cellspacing="0" class="footerTable">
		<tr>
			<td width="18" height="20"><img src="${imageDir}/footer_left.gif" width="18" height="20" /></td>
			<td width="0" height="20"><a href="#" onclick='saveFromPopOut()'>Save</a></td>
			<td width="18" height="20"><img src="${imageDir}/footer_right.gif" width="18" height="20" /></td>
		</tr>
	</table>
</div>	
</body>
</html>