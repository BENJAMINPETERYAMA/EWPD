function getQuestions(){
	var arr= new Array();

	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = 'createRefForm:qaString';
	param.selectedValues = getObj('createRefForm:qaString').value;
	param.hiddenIdToDisable = null;
	// Calling popup window.
	var dateTime = new Date();
	var retValue = window.showModalDialog('../popups/questionsAnswersPopup.jsp?'+dateTime, param, "dialogHeight:470px;dialogWidth:517px;resizable=false;status=no;");	
	
	if(retValue == undefined) {
		return false;
	}
	document.getElementById('createRefForm:qaString').value=retValue[0];
	document.getElementById('createRefForm:quesAnsDelString').value=retValue[1];	
	
	if(retValue[0]!="" || retValue[1]!="")
		loadPage();	
	else
		return false;
}

function pvaClick(url,targetDiv,targetHidden,attrCount,attrPosn){
	var lob = 'ALL~ALL';
	var be = 'ALL~ALL';
	var bg = 'ALL~ALL';
	var parentCatalog = 'provider arrangement';
	url = url + '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			 ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
	}
	
function deleteQuestions(){

var tableObject=document.getElementById('createRefForm:questiondt');
var checkedIndex='';
var isDeleted='';
if(null!=tableObject){
	var checkboxCount=0;
		for (var i=0;i<tableObject.rows.length;i++){
			var checkboxObject = tableObject.rows[i].cells[1].children[0];
				if(checkboxObject.checked==true){
				
				isDeleted='true';
					if(checkedIndex!='')
						checkedIndex=checkedIndex+"~"+tableObject.rows[i].childNodes[0].childNodes[0].innerText;
					else 
						checkedIndex=tableObject.rows[i].childNodes[0].childNodes[0].innerText;
				}
				
		}
			if(isDeleted==''){
				alert('No Questions Selected');
				return;
			}
	}
	document.getElementById('createRefForm:quesDelString').value=checkedIndex;	
	submitLink('createRefForm:deleteLink');
}

function functionToClearDiv(){

document.getElementById('amNoDiv').innerHTML='';
document.getElementById('desDiv').innerHTML='';

}

function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp){
      ewpdModalWindow_ewpd( popupName+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);     
      if(queryName=='searchTerm'){
		var term=document.getElementById('createRefForm:txtTerm').value;
		var terms= new Array(6) 
 	 	terms=term.split("~");
	 	if(terms.length>2){
			document.getElementById('createRefForm:aggregateTermCheckBox').checked = true;
			document.getElementById('createRefForm:termCombine').value="true";
				}
		else{
				document.getElementById('createRefForm:aggregateTermCheckBox').checked = false;
				document.getElementById('createRefForm:termCombine').value="false";
		}	
     }      
}


function loadPage(){
submitLink('createRefForm:pageLink');

}
function showIndNr(val){


	if((null!=val) && !('' == val)){
		var indr = val.split('~');	
	
		document.getElementById('amNoDiv').innerHTML=indr[0];
		document.getElementById('DesDiv').innerHTML=indr[1];	
	}else{
		document.getElementById('amNoDiv').innerHTML='';
		document.getElementById('desDiv').innerHTML='';
		
	}
}

function loadSPSSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/SearchReferencePopup.jsp?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}



function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){

	ewpdModalWindow_ewpd( '../popups/searchSPSMultiSelectPopup.jsp?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchPopupSingle.jsp?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}
 function checkQuestionAnswerChangedAndSubmit(objectId){
  
   document.getElementById('createRefForm:questionAnswerModifiedStatus').value=document.getElementById('questionAnswerModifiedDiv').innerHTML;
   var obj = document.getElementById(objectId);
	if(!isEwpdbDataModifed())
	{
		alert('No modifications to be updated.');
		return false;
	}
   return true;
   }


// Function that sends AJAX request using POST method with message.
	 function ajaxCallAdminMethodQestionAnsPopup(url,searchField,divObj,tableId,errorMsgDiv,attrArray,errorTableId,checkboxId,val) {
            //Assign the input parameters to the gloabl objects.
            divObject = divObj;
            tableIdentifier = tableId;
            errorMsgDivObj = errorMsgDiv;
            arrayObj = attrArray;    
            errorTableIdentifier = errorTableId;
			checkboxIdentifier = checkboxId;
			msgObject=val;
         //AJAX request.
         if (window.XMLHttpRequest) {
             req = new XMLHttpRequest();
         } else if (window.ActiveXObject) {
             req = new ActiveXObject("Microsoft.XMLHTTP");
         }
         attrArray['searchString'] = searchField.value;       
         req.open("POST", url,false);         
         req.onreadystatechange = callbackAJAXWithMessage;
         req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
         var encodedParams = encodeFormData(attrArray);
         req.send(encodedParams);
      }    
      
      //Function that is called after AJAX request is processed.
	function callbackAJAXWithMessage() {
		var errorData = null;
	    if (req.readyState == 4) {
	    //If the response is succcess
	        if (req.status == 200) {
	        
	        		var response = req.responseText;
	        		//Div to show the info message that comes along with search results - specific for reserve contract popup
	        		var messageDivObj = document.getElementById('msgDiv'); 
					// Retrieve the search results table
					var tableData = response.substring((response.indexOf('$#~'))+3,response.indexOf('$$~'));
					// Write the search results table to the empty div.
					if(null != divObject){						
						divObject.innerHTML = tableData;
						}
					// Search results table.					
                    var tableObject = document.getElementById(tableIdentifier);						
						
					// Retrieve the message (information or error) table from the response.
					errorData = response.substring((response.indexOf('*#~'))+3,response.indexOf('**~'));
					//if(null != msgDivObj){
					//	if(errorData.indexOf('<table') != -1){
					//		msgDivObj.style.display = 'block';
					//	}else{
					//		msgDivObj.style.display = 'none';
					//	}
					//}
				    // Specific for reserve contract id popups - start				   
                              if(null != messageDivObj){
                                    if(null != tableObject && tableObject.rows.length != 0){
                                          if(errorData.indexOf('<table') != -1){
                                          		
                                          	                                             	    
                                                messageDivObj.innerHTML = errorData;
                                                var message = htmlEncode(document.getElementById(errorTableIdentifier).rows[0].cells[0].childNodes[0].innerText);                                               
                                                messageDivObj.innerHTML = "<li id='infoItem'><b>"+ message + "</b></li>";  
                                                messageDivObj.className = ' infoDivForPopup';
                                          }
                                          else{
                                                messageDivObj.innerHTML = '';
                                                messageDivObj.className = '';
                                          }
                                    }else{
                                          messageDivObj.innerHTML = '';
                                          messageDivObj.className = '';
                                    }
                              }
                              // Specific for reserve contract id popups - end
					
					if(null == tableObject || tableObject.rows.length == 0){

						if(errorData.indexOf('<table') != -1){
							// Write the error table to the empty div.		
							if(null != divObject)  
								divObject.innerHTML = errorData;
							// Get the error message div to remove the border and bullet.
							var messageDiv = document.getElementById('wpdMessageDiv');
							// Get the class attribute of the div.
							var classAttribute = messageDiv.className;
							// Modify it to the ajax class attribute.
							classAttribute = classAttribute+ 'Ajax';
							messageDiv.className = classAttribute;
							// Get the list element and modify the bullet in the list.
							var listObj = document.getElementsByTagName('LI')[0];
							listObj.id = listObj.id + 'Ajax';
							var checkObj = document.getElementById(checkboxIdentifier);
							if(null != checkObj){								
								checkObj.disabled = true;
							}
						}
						else{
							 // In the case of 404 or 500 or any other runtime exception, hide the data divs and show the error div on the screen.
				        	if(null != divObject)		
				        		divObject.style.display = 'none';
				        	// unhide the error div.
				        	if(null != errorMsgDivObj)		
				        		errorMsgDivObj.style.display = 'block';
						}
					}

					var msgvalue=document.getElementById('infoDivMessage');
					if(null != tableObject && tableObject.rows.length > 50){	 
                    msgvalue.innerHTML = "<li id='infoItem'><b>"+ "Search results exceeded 50 records. Please refine the search criteria and try again." + "</b></li>";  
                    msgvalue.className = ' infoDivForPopup';
					}else if(null != tableObject && tableObject.rows.length >= 0 ){
						msgvalue.innerHTML = "";
						msgvalue.className = "";
					}	
					else if(null == tableObject || tableObject.rows.length ==0){
   					    msgvalue.innerHTML = "";
						msgvalue.className = "";
				     }					
					
					// To show the search exceeds msg, since the records in NotesAttachmentBacking bean will be restricted to 50
					// and it would not enter the above condition
					var formName = tableIdentifier.split(':')[0];
					var isRecordsGrtThanMaxSize=document.getElementById(formName+':recordsGreaterThanMaxSize');						
					if(isRecordsGrtThanMaxSize != "undefined" && isRecordsGrtThanMaxSize!=null && isRecordsGrtThanMaxSize.value == 'true'){											 
	                    msgvalue.innerHTML = "<li id='infoItem'><b>"+ "Search results exceeded 50 records. Please refine the search criteria and try again." + "</b></li>";  
	                    msgvalue.className = ' infoDivForPopup';										
					}
										
	        }
	        else{
	       		 // In the case of 404 or 500 or any other runtime exception, hide the data divs and show the error div on the screen.
	        	if(null != divObject)		
	        		divObject.style.display = 'none';
	        	// unhide the error div.
	        	if(null != errorMsgDivObj)		
	        		errorMsgDivObj.style.display = 'block';
	        }
	    }
	}