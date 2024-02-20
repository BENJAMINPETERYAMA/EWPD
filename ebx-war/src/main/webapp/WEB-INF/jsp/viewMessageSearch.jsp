<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="imageDir" value="${pageContext.request.contextPath}/images" />
<c:set var="rowCount" value="0" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {     
	    
	    	 document.getElementById('eb03IconUnmapped').style.display= 'none';
		     document.getElementById('varIconUnmapped').style.display= 'none';
		     document.getElementById('hdrruleIconUnmapped').style.display= 'none';
		     document.getElementById('spsIconUnmapped').style.display= 'none';
		     document.getElementById('msgIconUnmapped').style.display= 'none';
              
              var strMessageResults=$("#messageResultsTable tbody tr td").html();
              if($.trim(strMessageResults) != 'No Results matching your search criteria'){
                 	$('#messageResultsTable').dataTable(
				{ 			   
							   
						"bPaginate": false ,				    
						"bFilter": false, 
					 	"bSearchable":false, 
						"bInfo":false,
						"bSort": true,
						"bStateSave": false,
						"bJQueryUI": false,
						"bProcessing": false,
						"aaSorting": [],
						"bAutoWidth": false,
						"bSortClasses": true,
						"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
									if($(nRow).hasClass('isStandardMessage') != true){
										if (iDisplayIndex % 2  == 0 ){  
														    $(nRow).removeClass( 'alternate' );
															$(nRow).addClass( 'white-bg' );							
										}else{
														    $(nRow).removeClass( 'white-bg' );
														    $(nRow).addClass( 'alternate' );								 
										}
									}					
									return nRow;
								},
						"aoColumns" : [
				            { sWidth: '25px' },
				            { sWidth: '90px' },
				             { sWidth: '90px' },
				              { sWidth: '115px' },
				            { sWidth: '70px' },
				            { sWidth: '250px' }
				           ]   
						
					} );
                 }
			
		    
	 
	 }); 
	function setSelectedText(msg){
		$('#hdMessageDiscription').val(msg);
	}
	
	function displaySortMarkSearchResult(id)
   {
	   
	   switch(id)
	   {
	     case 'searchEb03' :
	     {
	    
		     document.getElementById('eb03IconUnmapped').style.display= 'block';
		     document.getElementById('varIconUnmapped').style.display= 'none';
		     document.getElementById('hdrruleIconUnmapped').style.display= 'none';
		     document.getElementById('spsIconUnmapped').style.display= 'none';
		     document.getElementById('msgIconUnmapped').style.display= 'none';
		     break;
	     }
	     case 'searchVar' :
	     {
	     
	         document.getElementById('eb03IconUnmapped').style.display= 'none';
		     document.getElementById('varIconUnmapped').style.display= 'block';
		     document.getElementById('hdrruleIconUnmapped').style.display= 'none';
		     document.getElementById('spsIconUnmapped').style.display= 'none';
		     document.getElementById('msgIconUnmapped').style.display= 'none';
		     break;
	     } 
	     case 'searchHdrrule':
	     {
	     
		     document.getElementById('eb03IconUnmapped').style.display= 'none';
		     document.getElementById('varIconUnmapped').style.display= 'none';
		     document.getElementById('hdrruleIconUnmapped').style.display= 'block';
		     document.getElementById('spsIconUnmapped').style.display= 'none';
		     document.getElementById('msgIconUnmapped').style.display= 'none';
		     break;
	     }    
	      case 'searchSps':
	     {
	     
		     document.getElementById('eb03IconUnmapped').style.display= 'none';
		     document.getElementById('varIconUnmapped').style.display= 'none';
		     document.getElementById('hdrruleIconUnmapped').style.display= 'none';
		     document.getElementById('spsIconUnmapped').style.display= 'block';
		     document.getElementById('msgIconUnmapped').style.display= 'none';
		     break;k;
	     }    
	      case 'searchMsg':
	     {
	     
		     document.getElementById('eb03IconUnmapped').style.display= 'none';
		     document.getElementById('varIconUnmapped').style.display= 'none';
		     document.getElementById('hdrruleIconUnmapped').style.display= 'none';
		     document.getElementById('spsIconUnmapped').style.display= 'none';
		     document.getElementById('msgIconUnmapped').style.display= 'block';
		     break;;
	     }   	        
	}	   
   }
</script>
<input type="hidden" id="hdMessageDiscription" value=""/>
	<div id="messageSearchResultDiv" class="MsgSearchResultDiv">
		<table id="messageResultsTable" width="640px" border="1" cellpadding="0" cellspacing="0">
		<THEAD>
			<tr height="20">
				<th width="25px" class="tableHeader">&#160;</th>
				<th id="searchEb03" onclick="displaySortMarkSearchResult(this.id)" width="90px" class="tableHeader">EB03&#160;
					<span id="eb03IconUnmapped" class="ui-widget-header" style="padding-left:30px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
					</span>
				</th>
				<th id="searchVar" onclick="displaySortMarkSearchResult(this.id)" width="90px" class="tableHeader">Variable ID&#160;
					<span id="varIconUnmapped" class="ui-widget-header" style="padding-left:60px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
					</span>
				</th>
				<th id="searchHdrrule" onclick="displaySortMarkSearchResult(this.id)" width="115px" class="tableHeader">Header Rule ID&#160;
					<span id="hdrruleIconUnmapped" class="ui-widget-header" style="padding-left:85px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
					</span>
				</th>
				<th id="searchSps" onclick="displaySortMarkSearchResult(this.id)" width="70px" class="tableHeader">SPS ID&#160;
					<span id="spsIconUnmapped" class="ui-widget-header" style="padding-left:35px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
					</span>
				</th>
				<th id="searchMsg" width="250px" onclick="displaySortMarkSearchResult(this.id)" class="tableHeader">Message Text
					<span id="msgIconUnmapped" class="ui-widget-header" style="padding-left:70px;display:inline-block;background:none; border:0px; position:right; top:-4px; left:-7px;height:10px;">
						<span class="ui-icon ui-icon-carat-1-s" style="position:right;height:10px;"/>
					</span>
				</th>
			</tr>
		</THEAD>
		<TBODY >
		<c:if test="${! empty searchResults}">
		<c:forEach items="${searchResults}" var="searchResult">
		<c:if test="${searchResult.isStandardMessage=='Y'}">
				<c:set var="rowClass" value="isStandardMessage" />
			</c:if>
			<c:if test="${searchResult.isStandardMessage=='N'}">
				<c:set var="rowClass"  value="${rowCount mod 2 == 0 ? 'white-bg' : 'alternate'}"/>		
			</c:if>	
			<tr class="${rowClass}">
				<td width="25px" ><input name="messageDescription" id="messageDescription" type="radio" value="${searchResult.messageText}" onclick="setSelectedText(this.value)"/></td>
				<td width="90px" style="word-wrap: break-word">${searchResult.EB03}</td>
				<td width="90px" style="word-wrap: break-word">${searchResult.variableId}</td>
				<td width="115px" style="word-wrap: break-word">${searchResult.headerRuleId}</td>
				<td width="70px" style="word-wrap: break-word">${searchResult.spsId}</td>
				<td width="215px" style="word-wrap: break-word">${searchResult.messageText}</td>
			</tr>
		<c:set var="rowCount" value="${rowCount + 1}"/>
		</c:forEach>
		</c:if>	
		<c:if test="${empty searchResults}">
			<tr height="100">
				<td colspan="6" align="center" valign="middle" height="100">No Results matching your search
				criteria</td>
			</tr>
		</c:if>
		</TBODY>
		</table>
	</div>
	<c:if test="${! empty searchResults}">
		<div style="padding-left:200px;padding-top:10px;">
		<form>
		<table>
			<tr>
				<td width="70"><c:if test="${page.totalNoOfPages != 1}">
					<a href="#"
						onclick='searchMessageText("${page.currentPage}","First");'><img
						src="${imageDir}/First Page.gif" /></a>
					<a href="#"
						onclick='searchMessageText("${page.currentPage}","Previous");'><img
						src="${imageDir}/Previous.gif" /></a>
				</c:if></td>
	
				<td valign="middle"
					style="padding-top:-70px;padding-right:10px;padding-left:10px;vertical-align:middle;">
				Page ${page.currentPage} of ${page.totalNoOfPages}</td>
	
				<td width="70"><c:if test="${page.totalNoOfPages != 1}">
					<a href="#"
						onclick='searchMessageText("${page.currentPage}","Next");'><img
						src="${imageDir}/Next.gif" /></a>
					<a href="#"
						onclick='searchMessageText("${page.totalNoOfPages}","Last");'><img
						src="${imageDir}/Last Page.gif" /></a>
					<input type="hidden" name="currentPage" id="currentPage"
						value="${page.currentPage}" />
				</c:if></td>
	
			</tr>
		</table>
		</form>
		</div>
	</c:if>
	<table width="100%" height="20" ><tr><td></td></tr></table>
	<c:if test="${! empty searchResults}">
	<table  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>  
		<td>
			<a href="#" onclick="selectMessageText()"><img src="${imageDir}/select_but.png" 
			alt="Select" title="Select"/></a>
		</td>	
      </tr>
	</table>
	</c:if>
	<table width="100%" height="20" ><tr><td></td></tr></table>