function disablePage() {
	var transparentDiv = document.getElementById('transparentDiv');
	transparentDiv.style.display = 'block';
	transparentDiv.style.height = document.body.scrollHeight + 'px';
	transparentDiv.style.width = document.body.scrollWidth + 'px';		

	var picDiv = document.getElementById('loadingImageDiv');
	picDiv.style.display = 'block';
}
function getCheckedItems_accum(id,fromSearchPage)
{	
	var tableObject;	
	var isComingFromSearchPage = document.getElementById(fromSearchPage).value;
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var selectedCodes = '';
		var cnt = 0;
		for (var i=1;i<tableObject.rows.length;i++)
		{	
			if(isComingFromSearchPage !="yes" && cnt > 10){
				break;
			}
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			
			if (checkboxObject && checkboxObject.checked)
			{	
				if(cnt > 0)
				{
					selectedValues += '<br>';
					selectedCodes += '~';
				}
				selectedValues += tableObject.rows[i].cells[1].innerText;	
				selectedCodes += tableObject.rows[i].cells[1].innerText + "~" + tableObject.rows[i].cells[1].children[0].value;
				cnt++;
			}
		}	
	}
	if(selectedValues != ''){
		if(cnt == 11){
			alert("Only upto 10 Accumulators can be selected");
			window.returnValue = '';
			return true;
		}
		else
		window.returnValue = selectedValues+'`'+selectedCodes;
	}else{
		window.returnValue = '';
	}
	window.close();	
	return false;

}
function getStdAccumforEdit() {
	
	var e = window.event;
	if(!e || e==undefined) {
		alert("no event");
		return;
	}
	

	var button_id = e.srcElement.id;
	var var1 = button_id.split(':');
	var rowcount = var1[2];
	
	var hiddenFieldNameLOB = "SearchForm:varDataTable:" + rowcount + ":hiddenLOB";
	var hiddenFieldLOBVal = document.getElementById(hiddenFieldNameLOB).value;

	var hiddenFieldNameBE = "SearchForm:varDataTable:" + rowcount + ":hiddenbe";
	var hiddenFieldBEVal = document.getElementById(hiddenFieldNameBE).value;
	
	var hiddenFieldNameGroup = "SearchForm:varDataTable:" + rowcount + ":hiddengroup";
	var hiddenFieldGroupVal = document.getElementById(hiddenFieldNameGroup).value;

	var hiddenFieldNameMBU = "SearchForm:varDataTable:" + rowcount + ":hiddenmbu";
	var hiddenFieldMBUVal = document.getElementById(hiddenFieldNameMBU).value;

	var hiddenFieldNameQUES = "SearchForm:varDataTable:" + rowcount + ":hiddenques";
	var hiddenFieldQUESVal = document.getElementById(hiddenFieldNameQUES).value;
	
	var hiddenFieldNameTYPE = "SearchForm:varDataTable:" + rowcount + ":hiddentype";
	var hiddenFieldTYPEVal = document.getElementById(hiddenFieldNameTYPE).value;

	var hiddenFieldNameBEN = "SearchForm:varDataTable:" + rowcount + ":hiddenbenefit";
	var hiddenFieldBENVal = document.getElementById(hiddenFieldNameBEN).value;
	
	var hiddenFieldNameBENDESC = "SearchForm:varDataTable:" + rowcount + ":hiddenbenefitdesc";
	var hiddenFieldNameBENDESCVal = document.getElementById(hiddenFieldNameBENDESC).value;
	
	var hiddenFieldNameACCUMDESC = "SearchForm:varDataTable:" + rowcount + ":hiddenquesdesc";
	var hiddenFieldNameACCUMDESCVal = document.getElementById(hiddenFieldNameACCUMDESC).value;
	
	BEField = document.getElementById('SearchForm:selectedBEforEdit');
	BEField.value = hiddenFieldBEVal;
	
	LOBField = document.getElementById('SearchForm:selectedLOBforEdit');
	LOBField.value = hiddenFieldLOBVal;
	
	BGField = document.getElementById('SearchForm:selectedBGforEdit');
	BGField.value = hiddenFieldGroupVal;
	
	MBUField = document.getElementById('SearchForm:selectedMBUforEdit');
	MBUField.value = hiddenFieldMBUVal;
	
	QUESField = document.getElementById('SearchForm:selectedQUESforEdit');
	QUESField.value = hiddenFieldQUESVal;
	
    CTField = document.getElementById('SearchForm:selectedCTforEdit');
	CTField.value = hiddenFieldTYPEVal;
	
	BENField = document.getElementById('SearchForm:selectedBENforEdit');
	BENField.value = hiddenFieldBENVal;
	
	BENDESCField = document.getElementById('SearchForm:selectedBENDESCforEdit');
	BENDESCField.value = hiddenFieldNameBENDESCVal;
	
	ACCUMDESCField = document.getElementById('SearchForm:selectedQUESDESCforEdit');
	ACCUMDESCField.value = hiddenFieldNameACCUMDESCVal;

	return true;
}
function delBeAcMap(){
	  var e = window.event;
			if(!e || e==undefined) {
				alert("no event");
				return;
			}
			
			var button_id = e.srcElement.id;
			var var1 = button_id.split(':');
			var rowcount = var1[2];
			
	   var hiddenFieldLOB = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddenLOB";
       var hiddenFieldLOBVal = document.getElementById(hiddenFieldLOB).value;
			  
	  var hiddenFieldBE = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddenbe";
	  var hiddenFieldBEVal = document.getElementById(hiddenFieldBE).value;
	  
	  var hiddenFieldgroup = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddengroup";
	  var hiddenFieldgroupVal = document.getElementById(hiddenFieldgroup).value;
	  
	  var hiddenFieldmbu = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddenmbu";
	  var hiddenFieldmbuVal = document.getElementById(hiddenFieldmbu).value;
	  
	  var hiddenFieldques = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddenques";
	  var hiddenFieldquesVal = document.getElementById(hiddenFieldques).value;
	  
	  var hiddenFieldtype = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddentype";
	  var hiddenFieldtypeVal = document.getElementById(hiddenFieldtype).value;
	  
	  var hiddenFieldAccum = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddenACC";
	  var hiddenFieldAccumVal = document.getElementById(hiddenFieldAccum).value;
	  
	  var hiddenFieldBen = "standardAccumulatorMapForm:varDataTable:" + rowcount + ":hiddenbenefit";
	  var hiddenFieldBenVal = document.getElementById(hiddenFieldBen).value;
	  
	  document.getElementById('standardAccumulatorMapForm:tempBE').value = hiddenFieldBEVal;
	  document.getElementById('standardAccumulatorMapForm:tempACC').value = hiddenFieldAccumVal;
	  document.getElementById('standardAccumulatorMapForm:tempLOB').value = hiddenFieldLOBVal;
	  document.getElementById('standardAccumulatorMapForm:tempBG').value = hiddenFieldgroupVal;
	  document.getElementById('standardAccumulatorMapForm:tempMBU').value = hiddenFieldmbuVal;
	  document.getElementById('standardAccumulatorMapForm:temQUES').value = hiddenFieldquesVal;
	  document.getElementById('standardAccumulatorMapForm:tempTYPE').value = hiddenFieldtypeVal;
	  document.getElementById('standardAccumulatorMapForm:tempBEN').value = hiddenFieldBenVal;
	  
	  deleteMappedAccums();
	  
	}

/*****Function for matching the check box values in the popup - Start*****/
function matchCheckboxItems_accum(id, hiddenObj) {
	var tableObject;
	var valueToCompare1;
	var valueToCompare2;
	var splittedVal;
	if (document.getElementById(id)) {
		var replacedVal = hiddenObj.value.replace(/~~/g,"~");
		splittedVal = replacedVal.split("~");
		tableObject = document.getElementById(id);
		var checkboxObject;
		for(var i = 1; i<tableObject.rows.length; i++)
		{
			checkboxObject = tableObject.rows[i].cells[0].children[0];
			valueToCompare1 = tableObject.rows[i].cells[1].children[0].value;
			if(valueToCompare1.indexOf('@') > -1){
				valueToCompare1 = valueToCompare1.substring(0,valueToCompare1.indexOf('@'));
			}
			for(var j = 0; j<splittedVal.length; j++)
			 {
				valueToCompare2 = splittedVal[j];	
				if(valueToCompare2.indexOf('@') > -1){
					valueToCompare2 = valueToCompare2.substring(0,valueToCompare2.indexOf('@'));
				}	
				if(valueToCompare1 == valueToCompare2) {
					checkboxObject.checked = true;
					break;
				}
			}
		}	
	}
}

function modalWindowforAccumPopup(url, divId, hiddenId, peer_divId,peer_hiddenId){
	
	if((document.getElementById('standardAccumulatorMapForm:selectQuesHidden').value == '')
			&& (document.getElementById('standardAccumulatorMapForm:bnHidden').value == '')){
		  alert("Please select Question and Benefit");
		  return false;
		
		}
	if(document.getElementById('standardAccumulatorMapForm:selectQuesHidden').value == ''){
		  alert("Please select Question");
		  return false;
		
		}
	if(document.getElementById('standardAccumulatorMapForm:bnHidden').value == ''){
		  alert("Please select Benefit");
		  return false;
		
		}
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	
	if( retValue == undefined ) {
		return false;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var hiddenDivObj = document.getElementById(divId);
	var valueForDiv = '';
	var valueForHidden = '';
	if (retValue != '') {
		valueForHidden = retValue.split('`')[1];
	}	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		alert('peer exists');
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != valueForHidden) {
			peer_DivObj.innerHTML = '';
			peer_hiddenObj.value = '';
			peer_DivObj.style.height="17px";
		}	
	}
	
	hiddenValueObj.value = valueForHidden;
	copyHiddenToDiv(hiddenId,divId);
	if(document.getElementById(divId))
	{	
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	if(retValue != '' && retValue!=undefined){
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	else{
		document.getElementById(divId).style.height="17px";
	}
	return true;
}

function modalWindowforAccumPopupSearch(url, divId, hiddenId, peer_divId,peer_hiddenId){
	
	
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	
	if( retValue == undefined ) {
		return false;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var hiddenDivObj = document.getElementById(divId);
	var valueForDiv = '';
	var valueForHidden = '';
	if (retValue != '') {
		valueForHidden = retValue.split('`')[1];
	}	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		alert('peer exists');
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != valueForHidden) {
			peer_DivObj.innerHTML = '';
			peer_hiddenObj.value = '';
			peer_DivObj.style.height="17px";
		}	
	}
	
	hiddenValueObj.value = valueForHidden;
	copyHiddenToDiv(hiddenId,divId);
	if(document.getElementById(divId))
	{	
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	if(retValue != '' && retValue!=undefined){
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	else{
		document.getElementById(divId).style.height="17px";
	}
	return true;
}
function modalWindowForSingleValueSelect2(url, onScreeenControlId, hiddenId){
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	

	if( retValue == undefined ) {
		return;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var hiddenDivObj = document.getElementById(onScreeenControlId);
	var valueForDiv = '';
	var valueForHidden = '';
	
	if (retValue != '') {
		valueForDiv = retValue.split('`')[0];
		valueForHidden = retValue.split('`')[1];
	}	

	hiddenValueObj.value = valueForHidden;
	hiddenDivObj.value = valueForDiv;
	getMappedAccums(hiddenDivObj);
	return;
}
function modalWindowBE(url, divId, hiddenId, peer_divId,peer_hiddenId){
	
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	
	if( retValue == undefined ) {
		return false;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var hiddenDivObj = document.getElementById(divId);
	var valueForDiv = '';
	var valueForHidden = '';
	if (retValue != '') {
		valueForHidden = retValue.split('`')[1];
	}	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		//alert('peer exists');
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != valueForHidden) {
			peer_DivObj.innerHTML = '';
			peer_hiddenObj.value = '';
			peer_DivObj.style.height="17px";
		}	
	}
	
	hiddenValueObj.value = valueForHidden;
	copyHiddenToDiv(hiddenId,divId);
	if(document.getElementById(divId))
	{	
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	if(retValue != '' && retValue!=undefined){
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	else{
		document.getElementById(divId).style.height="17px";
	}
	getMappedAccumsFromBE(hiddenValueObj.value);
	return true;
}
function enablePage() {
	var transparentDiv = document.getElementById('transparentDiv');
	transparentDiv.style.display = 'none';

	var picDiv = document.getElementById('loadingImageDiv');
	picDiv.style.display = 'none';	
}

function freezeScreen() {
//	var div2 = getObj('transparentDiv');
//	var div3 = getObj('loadingImageDiv');
//	if(div2 == null || div2 == undefined) {
//		alert('freeze.html is not included in the page');
//	}
//	div2.style.display = 'block';
//	div2.style.height = document.body.scrollHeight + 'px';
//	div3.style.display = 'block';
//	var elements = document.all;
//	for(i =0; i<elements.length; i++) {
//		if(elements[i].type == 'select-one') {
//			elements[i].style.visibility = 'hidden';
//		}
//	}
}
// For checking the key pressed is digit 
function isNumeric(evt){
  	var k = document.all ? evt.keyCode : evt.which;
   
    return ((k > 47 && k < 58) || k == 8 || k == 0);
  	}
function dateSegmentPopup(url){
	newWin = window.open(url,'ViewDateSegments',',status=no,toolbar=no,scrollbars=no,width=700,height=540,resizable=no,left=250,top=165');
}
// Date segement setToField = getContractId('SearchForm:contractIdForDS')
// Contract search setToField = getContractId('SearchForm:selectedContractId')

function getContractId(setToField) {
	
		var e = window.event;
	
		if(!e || e==undefined) {
			alert("no event");
			return;
		}
	
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];

		var hiddenFieldName = "ContractBasicSearchForm:searchResultTable:" + rowcount + ":contractIdHidden";
		
	
		var hiddenFieldVal = document.getElementById(hiddenFieldName).value;
		
		mandateTypeField = document.getElementById(setToField);
		mandateTypeField.value = hiddenFieldVal;
		return true;
}


function checkAllid(controller, table, name) 
{
	var tableObject = document.getElementById(table);
	var chkname;
	var chkbox;
	for(var i=0; i<tableObject.rows.length; i++) {
		chkbox = tableObject.rows[i].cells[0].children[0];
		if (!(chkbox.disabled == true)) {
			chkbox.checked = controller.checked;
		}
	}
}

function checkAllByDefault(table, name) 
{
	var tableObject = document.getElementById(table);
	var chkname;
	var chkbox;
	for(var i=0; i<tableObject.rows.length; i++) {
		chkbox = tableObject.rows[i].cells[0].children[0];
		if (!(chkbox.disabled == true)) {
			chkbox.checked = true;
		}
	}
}

function modalWindow11(url, divId, hiddenId){
	
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	
	if( retValue == undefined ) {
		return false;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var hiddenDivObj = document.getElementById(divId);
	var valueForDiv = '';
	var valueForHidden = '';
	if (retValue != '') {
		valueForHidden = retValue.split('`')[1];
	}	
	hiddenValueObj.value = valueForHidden;
	copyHiddenToDiv(hiddenId,divId);
	if(document.getElementById(divId))
	{	
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	if(retValue != '' && retValue!=undefined){
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	else{
		document.getElementById(divId).style.height="17px";
	}
	return true;
}

function modalWindowaccum(url, divId, hiddenId, peer_divId,peer_hiddenId){
	
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	
	if( retValue == undefined ) {
		return false;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var hiddenDivObj = document.getElementById(divId);
	var valueForDiv = '';
	var valueForHidden = '';
	if (retValue != '') {
		valueForHidden = retValue.split('`')[1];
	}	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		//alert('peer exists');
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != valueForHidden) {
			peer_DivObj.innerHTML = '';
			peer_hiddenObj.value = '';
			peer_DivObj.style.height="17px";
		}	
	}
	
	hiddenValueObj.value = valueForHidden;
	copyHiddenToDiv(hiddenId,divId);
	if(document.getElementById(divId))
	{	
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	if(retValue != '' && retValue!=undefined){
		document.getElementById(divId).style.height="30px";
		document.getElementById(divId).style.overflow="auto";
	}
	else{
		document.getElementById(divId).style.height="17px";
	}
	
	return true;
}

function modalWindowForSingleValueSelect(url, onScreeenControlId, hiddenId){
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	

	if( retValue == undefined ) {
		return;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var hiddenDivObj = document.getElementById(onScreeenControlId);
	var valueForDiv = '';
	var valueForHidden = '';
	
	if (retValue != '') {
		valueForDiv = retValue.split('`')[0];
		valueForHidden = retValue.split('`')[1];
	}	

	hiddenValueObj.value = valueForHidden;
	hiddenDivObj.value = valueForDiv;
	return;
}
function matchCheckboxItems(id, hiddenObj, hiddenObj_DisabledValues) {
	var tableObject;
	var valueToCompare1;
	var valueToCompare2;
	var splittedVal;
	if (document.getElementById(id)) {
		var splittedVal = hiddenObj.value.split("~");
		if(hiddenObj_DisabledValues != undefined && hiddenObj_DisabledValues.value!= '') {
			var disabledVal = hiddenObj_DisabledValues.value.split("~");
		}
		tableObject = document.getElementById(id);
		var checkboxObject;
		for(var i = 0; i<tableObject.rows.length; i++)
		{
			checkboxObject = tableObject.rows[i].cells[0].children[0];
			valueToCompare1 = tableObject.rows[i].cells[1].children[0].value;
			for(var j = 1; j<splittedVal.length; j+=2)
			 {
				valueToCompare2 = splittedVal[j];
				if(valueToCompare1 == valueToCompare2) {
					checkboxObject.checked = true;
					break;
				}
			}
			if(hiddenObj_DisabledValues != undefined && hiddenObj_DisabledValues.value!= '') {
				for(var j = 1; j<disabledVal.length; j+=2)
				 {
					valueToCompare2 = disabledVal[j];
					if(valueToCompare1 == valueToCompare2) {
						checkboxObject.disabled = true;
						break;
					}
				}
			}
		}
	}
	
}

function getRadioSelection(id){	
	var tableObject;
	if(document.getElementById(id)){
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var selectedCodes = '';
		var radioButtonObj;
		for(var i = 0; i < tableObject.rows.length; i++) {
			radioButtonObj = tableObject.rows[i].cells[0].children[0];
			if(radioButtonObj && radioButtonObj.checked) {
				selectedValues += tableObject.rows[i].cells[1].innerText;
				selectedCodes += tableObject.rows[i].cells[1].innerText + "~" + tableObject.rows[i].cells[1].children[0].value;
			}	
		}
	}
	if(selectedValues != ''){
		window.returnValue = selectedValues+'`'+selectedCodes;
	}else{
		window.returnValue = '';
	}
	window.close();	
	return false;
}

function getCheckedItems(id)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var selectedCodes = '';
		var cnt = 0;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			
			if (checkboxObject && checkboxObject.checked)
			{	
				
				if(cnt > 0)
				{
					selectedValues += '<br>';
					selectedCodes += '~';
				}
				selectedValues += tableObject.rows[i].cells[1].innerText;	
				selectedCodes += tableObject.rows[i].cells[1].innerText + "~" + tableObject.rows[i].cells[1].children[0].value;
				cnt++;
			}
		}	
	}
	if(selectedValues != ''){
		window.returnValue = selectedValues+'`'+selectedCodes;
	}else{
		window.returnValue = '';
	}
	window.close();	
	return false;

}


function getCheckedItemQuestions(id)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var selectedCodes = '';
		var cnt = 0;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			
			if (checkboxObject && checkboxObject.checked)
			{	
				
				if(cnt > 0)
				{
					selectedValues += '<br>';
					selectedCodes += '~';
				}
				selectedValues += tableObject.rows[i].cells[1].innerText;	
				selectedCodes += tableObject.rows[i].cells[1].innerText;
				cnt++;
			}
		}	
	}
	if(selectedValues != ''){
		window.returnValue = selectedValues+'`'+selectedCodes;
	}else{
		window.returnValue = '';
	}
	window.close();	
	return false;

}

function copyHiddenToDiv(hiddenId,divId) {
	var hiddenObj = document.getElementById(hiddenId);
	var divObj = document.getElementById(divId);
	if(hiddenObj.value == null || hiddenObj.value == ''){
			divObj.innerHTML = '';
	}
	parseForDiv(divObj, hiddenObj);
}


function showResultsTab(){
	document.getElementById('panel2Header').click();
	return true;
}

function trim(str)
{
   return str.replace(/^\s*|\s*$/g,"");
}   
   
function displayWithStyle(targetDiv, divHtml)
{

	if(targetDiv != 'undefined' && targetDiv != null)
	{
		if(divHtml != "")
		{
			var splited = divHtml.split("<br>");
			var count = splited.length;

		if(divHtml != '' && divHtml !='undefined'){
		targetDiv.style.height="30px";
		targetDiv.style.overflow="auto";
		}
	else{
		targetDiv.style.height="17px";
		}
			targetDiv.innerHTML = divHtml;
			targetDiv.style.visibility = 'visible';
			targetDiv.style.display = 'block';
			}else{
		}
	}
}


function parseForDiv(divObject, textObject)
{
	var divHtml = "";
	//var textValue = "ABC~1~CDE~2"; example 
	var textValue = textObject.value;
	var values = "";
	if(textValue != null && textValue.length > 0){
		textValue = replaceString(textValue, '<', '&lt;')
		textValue = replaceString(textValue, '>', '&gt;')
		values = textValue.split("~");
	}
	if(values != null && values.length > 0)
	{
		for(var i=0, n = values.length; i<n; i++)
		{
			divHtml += values[i] + "<br>";
	  		i++;
		}
	}
	displayWithStyle(divObject, divHtml); 
}



function parseForStateDiv(divObject, textObject)
{
	var divHtml = "";
	//var textValue = "ABC~1~CDE~2"; example 
	var textValue = textObject.value;
	var values = "";
	if(textValue != null && textValue.length > 0){
		textValue = replaceString(textValue, '<', '&lt;')
		textValue = replaceString(textValue, '>', '&gt;')
		values = textValue.split("~");
	}
	if(values != null && values.length > 0)
	{
		for(var i=0, n = values.length; i<n; i++)
		{
			divHtml += values[i+1] + "<br>";
	  		i++;
		}
	}
	displayWithStyle(divObject, divHtml); 
}

function setColumnWidth(str_tableid, width_all)
{
		var width_cl = width_all.split(':');
		var x = document.getElementById(str_tableid);
		if (!x) return alert ("Can't find table(s) with specified ID (" + str_tableid + ")");

		var rowsOftable = x.rows;
		if(rowsOftable.length == 0)
			return;
		for(var rw=0; rw<rowsOftable.length; rw++) {
			var columns = x.rows[rw].cells;
			for(var i=0; i<columns.length && i<width_cl.length; i++) {
				columns[i].width = width_cl[i];
			}
		}	
}	

/* Function to Show confirmation message for delete in Search result screen */
function confirmTask(msg){
	if(msg == '')
		return true;
	var splited = msg.split(':');
	if(splited.length == 1 || splited.length > 2) {
		alert('The message \"'+ msg + '\" is not in correct format');
		return false;
	}
	var message = splited[0];
	var severity = splited[1];
	if(severity == '0') {
		if(window.confirm(message))
			return true;
	}
	else {
		if(severity == '1') 
			alert(message);
	} 
	return false;
}

function ismaxlength(obj,length) {
	if (obj.value && obj.value.length>length)
		obj.value=obj.value.substring(0,length)

}


function replaceString(sString, sReplaceThis, sWithThis) { 
    if (sReplaceThis != "" && sReplaceThis != sWithThis) {
      var counter = 0;
      var start = 0;
      var before = "";
      var after = "";
      while (counter<sString.length) {
        start = sString.indexOf(sReplaceThis, counter);
        if (start == -1){
         break;
         } else {
           before = sString.substr(0, start);
           after = sString.substr(start + sReplaceThis.length, sString.length);
           sString = before + sWithThis + after;
           counter = before.length + sWithThis.length;
          }
        }
      }
   return sString;
  }
  

// Function to submit page while hitting 'Enter Key'  
function submitOnEnterKey(submitButton){
	var button = document.getElementById(submitButton);

	// Do not submit if 'Enter Key' pressed in Text area.
	var srcElement = window.event.srcElement;
	if( srcElement != null && srcElement != undefined && 
		srcElement.type != undefined && srcElement.type =='textarea') {
		return true;
	}
	// To Identify the LogOut Link
	if(null != srcElement){
		var elementName = srcElement+"";
		var searchIt = elementName.search('logout.jsp');
		if(searchIt != -1){
			return true;
		}
	}
	if(window.event.keyCode==13) {
		button.click();
		return false;
	}
	return true;
}


function trimString (str) {
  while (str.charAt(0) == ' ')
    str = str.substring(1);
  while (str.charAt(str.length - 1) == ' ')
    str = str.substring(0, str.length - 1);
  return str;
}


     
// For setting default value to newmeric field field
// usage 
// onblur="setDefault(this,'0');"
function setDefault(fieldObject, defaultValue)
{
	NUM = /^\d+(\.\d+)?$/;
	var fieldObjectValue = fieldObject.value;
	if(fieldObjectValue == null || trim(fieldObjectValue) == '')
	{
		fieldObject.value = defaultValue;
	}
	else if(NUM.test(fieldObjectValue))
	{
		fieldObject.value = Math.round(fieldObjectValue);
	}
	else
	{
		fieldObject.value = defaultValue;
	}
}

// For checking the key pressed is digit 
 function isNumberKey(evt)
  {

	var k = document.all ? evt.keyCode : evt.which;

//  return false if shift key is pressed in order to avoid characters !@#$ etc.
	if(evt.shiftKey)
		return false;
		
//  if(  Numbers             ||  Keypad Numbers      || backspace || Delete || Left Arrow || Right Arrow || Tab)
	if( (k >= 48 && k <= 57) || ( k>= 96 && k<= 105) || k==8      || k==46  || k== 37     || k==39       || k==9) {
		return true;
	}
    return false;
  }

// For copying a value from a particular row to hidden variable.
function getFromDataTableToHidden(tableId,tableFieldId,targetId){

		var e = window.event;
		if(!e || e==undefined) {
			return false;
		}
		var button_id = e.srcElement.id;
		//alert(button_id);
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		//alert(rowcount);
		var sourceFieldId = tableId + ':' + rowcount + ':' + tableFieldId;
		document.getElementById(targetId).value = document.getElementById(sourceFieldId).value;
		return true;
}


function displaySelectedRadio(sourceId,targetId)
{
	var sourceObject = document.getElementById(sourceId);
	var targetObject = document.getElementById(targetId);
	var inputs = targetObject.getElementsByTagName('input');
	if(sourceObject != 'undefined' && sourceObject != null && targetObject != 'undefined' && targetObject != null )
	{
	
		if(sourceObject.value == 'Y')
			inputs[0].checked =true;
		else
			inputs[1].checked =true;
	
	}
}

function submitLink(commandLinkId){
	document.getElementById(commandLinkId).click();
}
// function to copy value from the source field to hidden field.
function copyToHidden(sourceField,hiddenField){
	var sourceObj = document.getElementById(sourceField);
	var targetObj = document.getElementById(hiddenField);
	targetObj.value = sourceObj.value;
}


function limitText(limitField, limitNum) {
	var obj = document.getElementById(limitField);
	str =obj.value;
	if (str.length > limitNum) {
			obj.value = str.substring(0, limitNum);
	}
}


function isAlphabet(e){
	var keycode;
	if (window.event) keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	if (!(keycode > 64 && keycode < 123))
		return false;
    return true;
}

	
	function modalWindowForQuestions(url,id,hidden){
		var hiddenSelectedValues = '';
		var retValue = window.showModalDialog(url, window, "dialogHeight:450px;dialogWidth:450px;resizable=yes;status=no;");
		retValueDiv =retValue.split("`");
		
		if(document.getElementById(id) && document.getElementById(id).type =='text')
		{
			document.getElementById(id).value = retValueDiv[0] ;		
		}
		else if(document.getElementById(id)){
			document.getElementById(id).innerHTML = retValueDiv[0];
		}
		if(retValue != '' && retValue!='undefined' && retValue != null){
			document.getElementById(id).style.height="30px";
			document.getElementById(id).style.overflow="auto";
		}
		else{
					
			document.getElementById(id).style.height="17px";
			document.getElementById("benefitLevelForm:"+id+"Hidden").value = "";
			document.getElementById(id).innerHTML = "";
		}
		if(retValue != '' && retValue != 'undefined' && retValue != null){
			var selectedValues = retValue.split('<br>');
			for(i = 0; i < selectedValues.length;i++){
				if(hiddenSelectedValues != '')
					hiddenSelectedValues += ',';
				hiddenSelectedValues += selectedValues[i];
			}
		}
		if(hiddenSelectedValues != ''){
			document.getElementById(hidden).value = hiddenSelectedValues;
		}
	}
	
	
	/*function getBenefitCheckedItems(id){
	
	
	
		if (document.getElementById(id) != null)
		{
			//var heading= document.getElementById(id);
			//heading.innerText='';
			var tableObject = document.getElementById(id);
			alert(id);
			var selectedValues = '';
			var hiddenSelectedValues = '';
			for (var i=0;i<tableObject.rows.length;i++)
			{
				var checkboxObject = tableObject.rows[i].cells[0].children[0];
				if (checkboxObject && checkboxObject.checked)
				{	
					if(selectedValues != '')
						selectedValues += '<br>';
					selectedValues += tableObject.rows[i].cells[1].innerText;	
										
				}
			}
			alert(selectedValues);	
		}
		window.returnValue = selectedValues;
		window.close();	
	}*/
	
	
	
	function checkAll(obj, name, id, msgId){
		var checks = document.getElementsByName(name);
		//If no id given, we'll asume that we want all the boxes checked/unchecked.
		if(typeof id == "undefined")
		{
			for(var x = 0; x < checks.length; x++)
			{
				/*
				We'll give all the cluster checkboxes the same state as the master.
				This avoids a *stupid* if/else statement in here. (Which is either true or false(Checked/unchecked))
				*/
				checks[x].checked = obj.checked;
			}
		}
		//Else lets see whether to check or uncheck the "master" checker
		else
		{
			var checkall = document.getElementById(id);
	
			//If we uncheck a cluster then lets uncheck the master
			if(checkall.checked == true && obj.checked == false)
			{
				checkall.checked = false;
			}
			//If we check a cluster, lets see if we should check the master too
			else if(obj.checked == true)
			{
				var bool = true;
				for(var x = 0; x < checks.length; x++)
				{
					if(checks[x].checked != obj.checked)
					{
						bool = false;
						//Only run while bool is true. 
						//(As soon as it turns false all the clusters are not the same state
						break;
					}
				}
	
				//If bool is true, all clusters are checked, and we should check the master
				if(bool == true)
				{
					checkall.checked = true;
				}
			}
		}
	}
	
function closeAction(){
	if(newWinForView!= null && newWinForView!='undefined'){
		newWinForView.close();
	}
}

/**
	Function opens a popup screen, accept return value from the popup and process it and 
	stores to specific fields.
	
	Parameters
	url			(Mandatory) - url of the popup jsp to be opend.<b> 
	targetId	(Mandatory) - This can be inputText/div. The selected values from the
							  popup will be shown in this field.<b> 
	hiddenId	(Mandatory) - Id of the hidden field where the return value of the popup to be stored.
							  This can be same as targetId.In this case this should be inputText.
	attrCount	(Mandatory)	- The number of attributes in the tilda String that constitues  a single data. 
							  eg: id1~desc1~name1~id2~desc2~name2 - here 3 attributes represents a single data. 
							      so the count is 3.
	attrPos		(Mandatory)	- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
							  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given then id1,id2 will be shwon in div.
							  									  - if 2 is given then desc1,desc2 will be shown in div.	
	hiddenIdToDisable(optional) - For later user.							  									  		
*/

function ewpdModalWindow_ewpd(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
	// Creating new object and setting all the values that needs to be passed into the popup screen. These values
	// Object param can be accessed from the popup by "window.dialogArguments".
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	param.selectedValues = getObj(hiddenId).value;
	param.hiddenIdToDisable = hiddenIdToDisable;

	// Calling popup window.
	var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
	// Cancel action from popup. No need to do anything.
	if( retValue == undefined ) {
		return false;
	}
	// Setting the value from popup to hidden field
	var hiddenValueObj = getObj(hiddenId);

	if(peer_divId != undefined && peer_hiddenId != undefined) {
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != retValue) {
			if(peer_hiddenObj.value != ''){
				peer_hiddenObj.value = '';
			}
			if(peer_DivObj != null){
				peer_DivObj.innerHTML = '';
				peer_DivObj.style.height="17px";
			}
		}	
	}
	hiddenValueObj.value = retValue;
	if(hiddenId == targetId)
		return true;

	// Setting necessary values from hidden field to div/text	
	if(	getObj(targetId).type == 'text'){
		copyHiddenToInputText(hiddenId, targetId, attrPos);
		
		return retValue;
				
	}
	else
		copyHiddenToDiv_ewpd(hiddenId, targetId, attrCount, attrPos);
	
	return true;
}
	

function ewpdModalWindow_import(url,hiddenId,hiddenValue, height, width){	
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	
	
	param.selectedValues = hiddenValue; //getObj(hiddenId).value;	
	var retValue = window.showModalDialog(url,param, "dialogHeight:"+height+"px;dialogWidth:"+width+"px;resizable=false;status=no;");		
}

	function ewpdModalWindow_ewpdforlob(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
		// Creating new object and setting all the values that needs to be passed into the popup screen. These values
		// Object param can be accessed from the popup by "window.dialogArguments".
		var param = new Object();
		param.parentWindow = window;
		param.hiddenId = hiddenId;
		param.selectedValues = getObj(hiddenId).value;
		param.hiddenIdToDisable = hiddenIdToDisable;

		// Calling popup window.
		var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
		// Cancel action from popup. No need to do anything.
		if( retValue == undefined ) {
			return false;
		}
		// Setting the value from popup to hidden field
		var hiddenValueObj = getObj(hiddenId);

		
		if(peer_divId != undefined && peer_hiddenId != undefined) {
			var peer_DivObj = document.getElementById(peer_divId);
			var peer_hiddenObj = document.getElementById(peer_hiddenId);
			if(hiddenValueObj.value != retValue) {
				if(peer_hiddenObj.value != ''){
					peer_hiddenObj.value = '';
				}
				if(peer_DivObj != null){
					peer_DivObj.innerHTML = '';
					peer_DivObj.style.height="17px";
				}
			}	
		}
		hiddenValueObj.value = retValue;
		if(hiddenId == targetId)
			return true;

		// Setting necessary values from hidden field to div/text	
		if(	getObj(targetId).type == 'text'){
			copyHiddenToInputText(hiddenId, targetId, attrPos);
			
			return retValue;
					
		}
		else
			copyHiddenToDiv_ewpd(hiddenId, targetId, attrCount, attrPos);
		
		return true;
	}


// method for Admin method create and edit

function ewpdModalWindow_ewpd_AM(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
	// Creating new object and setting all the values that needs to be passed into the popup screen. These values
	// Object param can be accessed from the popup by "window.dialogArguments".
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	//param.selectedValues = getObj(hiddenId).value;
	param.hiddenIdToDisable = hiddenIdToDisable;

	// Calling popup window.
	var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
	// Cancel action from popup. No need to do anything.
	if( retValue == undefined ) {
		if(document.getElementById('createAdminMethodMaintainForm:reqParmid')!=null)
			document.getElementById('createAdminMethodMaintainForm:reqParmid').value = "";
		
		if(document.getElementById('editAdminForm:reqParmid') !=null)
		
		 	document.getElementById('editAdminForm:reqParmid').value= "";		
		return false;
	}
	// Setting the value from popup to hidden field
	var hiddenValueObj = getObj(hiddenId);

	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != retValue) {
			if(peer_hiddenObj.value != ''){
				peer_hiddenObj.value = '';
			}
			if(peer_DivObj != null){
				peer_DivObj.innerHTML = '';
				peer_DivObj.style.height="17px";
			}
		}	
	}
	hiddenValueObj.value = retValue;
	if(hiddenId == targetId)
		return true;

	// Setting necessary values from hidden field to div/text	
	if(	getObj(targetId).type == 'text'){
		copyHiddenToInputText(hiddenId, targetId, attrPos);
		
		return retValue;
				
	}
	else
		copyHiddenToDiv_ewpd(hiddenId, targetId, attrCount, attrPos);
	
	return true;
}


// method for increased pop up size
function ewpdModalWindow_NotesView(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
	// Creating new object and setting all the values that needs to be passed into the popup screen. These values
	// Object param can be accessed from the popup by "window.dialogArguments".
	
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	param.selectedValues = getObj(hiddenId).value;
	param.hiddenIdToDisable = hiddenIdToDisable;

	// Calling popup window.
	var retValue = window.showModalDialog(url, param, "dialogHeight:300px;dialogWidth:450px;resizable=false;status=no;");	
	// Cancel action from popup. No need to do anything.
	if( retValue == undefined ) {
		return false;
	}
	// Setting the value from popup to hidden field
	var hiddenValueObj = getObj(hiddenId);

	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != retValue) {
			if(peer_hiddenObj.value != ''){
				peer_hiddenObj.value = '';
			}
			if(peer_DivObj != null){
				peer_DivObj.innerHTML = '';
				peer_DivObj.style.height="17px";
			}
		}	
	}
	hiddenValueObj.value = retValue;
	if(hiddenId == targetId)
		return true;

	// Setting necessary values from hidden field to div/text	
	if(	getObj(targetId).type == 'text'){
		copyHiddenToInputText(hiddenId, targetId, attrPos);
		return retValue;		
	}
	else
		copyHiddenToDiv_ewpd(hiddenId, targetId, attrCount, attrPos);
	return true;
}

/*
	to display id-description in div
*/

function ewpdModalWindow_ewpd1(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
	// Creating new object and setting all the values that needs to be passed into the popup screen. These values
	// Object param can be accessed from the popup by "window.dialogArguments".
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	param.selectedValues = getObj(hiddenId).value;
	param.hiddenIdToDisable = hiddenIdToDisable;

	// Calling popup window.
	var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:500px;resizable=false;status=no;");	
	// Cancel action from popup. No need to do anything.
	if( retValue == undefined ) {
		return false;
	}
	// Setting the value from popup to hidden field
	var hiddenValueObj = getObj(hiddenId);

	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != retValue) {
			if(peer_hiddenObj.value != ''){
				peer_hiddenObj.value = '';
			}
			if(peer_DivObj != null){
				peer_DivObj.innerHTML = '';
				peer_DivObj.style.height="17px";
			}
		}	
	}
	hiddenValueObj.value = retValue;
	
	if(hiddenId == targetId)
		return true;

	// Setting necessary values from hidden field to div/text	
	if(	getObj(targetId).type == 'text'){
		copyHiddenToInputText1(hiddenId, targetId, attrPos);
		return retValue;		
	}
	else
		copyHiddenToDiv_ewpd1(hiddenId, targetId, attrCount, attrPos);
	return true;
}

/*
	to display id-description in div
*/

/*
	Added for Capturing the RuleType from the Popup to th JSPs

*/

function ewpdModalWindowWithRuleType(url,targetId,hiddenId,ruleTypeHidden ,attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
	// Creating new object and setting all the values that needs to be passed into the popup screen. These values
	// Object param can be accessed from the popup by "window.dialogArguments".
 	
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	

	param.selectedValues = getObj(hiddenId).value;
	param.hiddenIdToDisable = hiddenIdToDisable;
	// Calling popup window.
	var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:500px;resizable=false;status=no;");	
	
	// Cancel action from popup. No need to do anything.
	if( retValue == undefined ) {
		return false;
	}
	// Setting the value from popup to hidden field
	
	
	var hiddenValueObj = getObj(hiddenId);
	var hiddenValueforRuleTypeObj = document.getElementById(ruleTypeHidden);
	

	values = retValue.split("~");
	
	if(values == null || values == ''){	
		var targetDiv = getObj(targetId);	
		targetDiv.innerText = '';
		hiddenValueObj.value = '';
		hiddenValueforRuleTypeObj.value = '';
		return false;
	}
	
	hiddenValueObj.value = values[0]+"~"+values[1];
	hiddenValueforRuleTypeObj.value = values[2];
	
	if(hiddenId == targetId)
		return true;

	// Setting necessary values from hidden field to div/text	
	
	if(	getObj(targetId).type == 'text')
	{
		copyHiddenToInputText1(hiddenId, targetId, attrPos);
		return retValue;		
	}
	else
	{
		copyHiddenToDiv_ewpd1(hiddenId, targetId, attrCount, attrPos);
		}
	return true;
}


function copyHiddenToDiv_ewpd1(hiddenId, divId,attrCount, attrPos) {
	var hiddenObj = getObj(hiddenId);
	var divObj = getObj(divId);
	var divHtml = '';
	var textValue = hiddenObj.value;
	var values;
	var targetDiv = getObj(divId);
	var elementCount = 0;
	if(hiddenObj.value == null || hiddenObj.value == ''){
			divObj.innerText = '';
			targetDiv.style.height="17px";
	}
	if(textValue != null && textValue != undefined && textValue.length > 0){
		attrPos -= 1;			
		values = textValue.split("~");
		for(var i=0, n = values.length; i<n; i+=attrCount) {
			elementCount++;
			divHtml += values[i+attrPos+1]+"-"+values[i+attrPos]+ "\n";
		}
		targetDiv.innerText = divHtml;
		if(elementCount ==0 || elementCount == 1)
			targetDiv.style.height="17px";
		else
			targetDiv.style.height="30px";
		targetDiv.style.overflowY='auto';
		targetDiv.style.overflowX = 'hidden';
	}
	targetDiv.style.visibility = 'visible';
	targetDiv.style.display = 'block';
	
	 
}

/**
	Function gets values from hidden field, process it and stores it to another text field or div.
	<p>this function is only for print of provider specialty code in contract</p>
	
	Parameters
	hiddenId		- hiddenField id from which values to be copied.
	divId			- Target div id to which values to be copied.
	attrCount		- The number of attributes of the tilda String that constitues  a single data which is stored in hidden field.
					  eg: id1~desc1~name1~id2~desc2~name2 - here 3 attributes represents a single data. 
					  									  - so the count is 3.
	attrPos			- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
					  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given then id1,id2 will be shwon in div.
														  - if 2 is given then desc1,desc2 will be shown in div.	
	separator       - Character that should separate the values

*/

function copyHiddenToDivPrint_specialtyCode(hiddenId, divId, attrCount, attrPos , separator) {
	
	var hiddenObj = getObj(hiddenId);
	var divObj = getObj(divId);
	var divHtml = '';
	var textValue = hiddenObj.value;
	var values;
	var targetDiv = getObj(divId);
	var elementCount = 0;
	if(hiddenObj.value == null || hiddenObj.value == ''){
			divObj.innerText = '';
			targetDiv.style.height="17px";
	}
	
	if(textValue != null && textValue != undefined && textValue.length > 0){
		attrPos -= 1;			
		values = textValue.split("~");
		for(var i=0, n = values.length; i<n; i+=attrCount) {
			//elementCount++;
		    divHtml += values[i+attrPos] + separator;
		}
		var divHtmlLength = divHtml.length;
		divHtml = divHtml.slice(0,divHtmlLength-2); 
		targetDiv.innerText = divHtml;
		targetDiv.style.height="100%";
		targetDiv.style.overflowY='hidden';
		targetDiv.style.overflowX = 'hidden';
	}
	targetDiv.style.visibility = 'visible';
	targetDiv.style.display = 'block';
}

/*

/**
	Function gets values from hidden field, process it and stores it to another text field or div.
	
	Parameters
	hiddenId		- hiddenField id from which values to be copied.
	divId			- Target div id to which values to be copied.
	attrCount		- The number of attributes of the tilda String that constitues  a single data which is stored in hidden field.
					  eg: id1~desc1~name1~id2~desc2~name2 - here 3 attributes represents a single data. 
					  									  - so the count is 3.
	attrPos			- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
					  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given then id1,id2 will be shwon in div.
														  - if 2 is given then desc1,desc2 will be shown in div.	
*/
function copyHiddenToDiv_ewpd(hiddenId, divId, attrCount, attrPos) {

	var hiddenObj = getObj(hiddenId);
	var divObj = getObj(divId);
	var divHtml = '';
	var textValue = hiddenObj.value;
	var values;
	var targetDiv = getObj(divId);
	var elementCount = 0;
	if(hiddenObj.value == null || hiddenObj.value == ''){
			divObj.innerText = '';
			targetDiv.style.height="17px";
	}
	
	if(textValue != null && textValue != undefined && textValue.length > 0){
		attrPos -= 1;			
		values = textValue.split("~");
		for(var i=0, n = values.length; i<n; i+=attrCount) {
			elementCount++;
		    divHtml += values[i+attrPos] + "\n";
		}
		targetDiv.innerText = divHtml;
		if(elementCount ==0 || elementCount == 1)
			targetDiv.style.height="17px";
		else
			targetDiv.style.height="30px";
		targetDiv.style.overflowY='auto';
		targetDiv.style.overflowX = 'hidden';
	}
	targetDiv.style.visibility = 'visible';
	targetDiv.style.display = 'block';
}

/*
	Function copies some data from hidden field to a target inpuText field.
	
	Parameters
	hiddenId		- hiddenField id from which values to be copied.
	divId			- Target div id to which values to be copied.
	attrCount		- The number of attributes of the tilda String that constitues  a single data which is stored in hidden field.
					  eg: id1~desc1~name1~id2~desc2~name2 - here 3 attributes represents a single data. 
					  									  - so the count is 3.
	attrPos			- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
					  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given then id1,id2 will be shwon in div.
														  - if 2 is given then desc1,desc2 will be shown in div.	
	
*/
function copyHiddenToInputText(hiddenId, targetId, attrPos){
	var text = getObj(hiddenId);
	if(text != null)
	{
		var textValue = text.value;
		if(textValue != null && textValue.length > 0)
		{
			var values = textValue.split("~");
			getObj(targetId).value = values[attrPos-1];
		}
		else if(textValue == ""){
			getObj(targetId).value = textValue;
		}
	}
}
/*
	to display id-description in div
*/
function copyHiddenToInputText1(hiddenId, targetId, attrPos){
	var text = getObj(hiddenId);
	if(text != null)
	{
		var textValue = text.value;
		if(textValue != null && textValue.length > 0)
		{
			var values = textValue.split("~");
			getObj(targetId).value = values[1]+'-'+values[0];
			
		}
		else if(textValue == ""){
			getObj(targetId).value = textValue;
		}
	}
}

function formatTildaToComma1(objName)
{
    var formattedString = "";
	var obj = document.getElementById(objName);

	var val = obj.innerHTML;

	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{

		for(i=0, n = values.length; i < n; i+=2)
		{
			formattedString += values[i+1]+"-"+values[i] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
	
	
}
	


function getObj(id) {
	return document.getElementById(id);
}

/**
	Function makes a Tilda String depends on the argument.
	
	Parameters
	id			- Id of the data table.
	attrCount	- The number of attributes of the tilda String that constitues  a single data.
	
	The function assumes the first column of data table contains a checkbox/radiobutton and
	all the attributes that need to be included in the tilda string will be available at 
	second column as input hidden field in correct order. So depending on the parameter "attrCount" 
	that much number of values from the second column will be added to tilda String.
*/
function getCheckedItems_ewpd(id, attrCount)
{
	var tableObject;
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);	
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		var adjAccumSelected = 0;
		
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';
				// replacing '~' to '^*#'

				var firstCell = currentCell.children[0].value;
				if(firstCell == 'ADJUD - ACCUM'){
					adjAccumSelected = 1;
				}

				firstCell = firstCell.replace('~','/w0p8d');
				
				if(attrCount > 1){
					var secondCell = currentCell.children[1].value;

					while(secondCell.indexOf('~') >= 0){
						secondCell = secondCell.replace('~','/w0p8d');
						
					}
				}
				if(attrCount == 3){

					var thirdCell = currentCell.children[2].value;
					thirdCell = thirdCell.replace('~','/w0p8d');
					
				}
				
				switch(attrCount){
					case 1: selectedValues += firstCell; 
							break;
					case 2: selectedValues += firstCell + '~' + secondCell;	
					
							break;
					case 3: selectedValues += firstCell + '~' + secondCell + '~' + thirdCell;
							break;
					
				}	
				cnt++;
			}
		}	
	}
	window.returnValue = selectedValues;
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}



function getCheckedItems_ewpdforQuestionPopup(id, attrCount,answerId)
{
	var tableObject;
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);	
		var selectedValues = '';
		var cnt = 0;
		var currentCell;		
		for (var i=0;i<tableObject.rows.length;i=i+2)
		{
			var checkboxObject = tableObject.rows[i].childNodes[0].childNodes[1];			
			
			currentCell = tableObject.rows[i].cells[1];			
			
			if (checkboxObject && checkboxObject.checked) {			
	
				var firstCell = currentCell.children[0].innerText+'#'+tableObject.rows[i].childNodes[0].childNodes[0].value;
				var TBObj = tableObject.rows[i+1].childNodes[1].childNodes[0];
				var inputs = TBObj.getElementsByTagName('input');
				for(var j=0;j<inputs.length;j++){
					if(inputs[j].styleClass="mandatoryError"){
						if(inputs[j].checked){
						firstCell=firstCell+"@"+inputs[j+1].value+"`"+inputs[j+2].value;
						//alert('desc::'+inputs[j+1].value);
						//alert('Value::'+inputs[j+2].value);
						}
					}
				}
				
				
			if(selectedValues !='')
				selectedValues = selectedValues+"~"+firstCell; 
			else 
				selectedValues = firstCell; 
		}	
		
	}
	}
    document.getElementById('benefitTermSelectPopupForm:qaString').value=selectedValues;
	window.returnValue = selectedValues;
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}



function getCheckedItems_ewpdForQuestions(id, attrCount)
{
	var tableObject;
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);	
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';
				// replacing '~' to '^*#'

				var firstCell = currentCell.children[0].value;

				firstCell = firstCell.replace('~','/w0p8d');

				if(attrCount > 1){
					var secondCell = currentCell.children[1].value;

					while(secondCell.indexOf('~') >= 0){
						secondCell = secondCell.replace('~','/w0p8d');
					}
				}
				if(attrCount > 2){
					
					var thirdCell = currentCell.children[2].value;
					thirdCell = thirdCell.replace('~','/w0p8d');
				}
				if(attrCount == 4){

					var  fourthCell = currentCell.children[3].value;
					 fourthCell = fourthCell.replace('~','/w0p8d');
				}
				
				switch(attrCount){
					case 1: selectedValues += firstCell; 
							break;
					case 2: selectedValues += firstCell + '~' + secondCell;	
							break;
					case 3: selectedValues += firstCell + '~' + secondCell + '~' + thirdCell;
							break;
					case 4: selectedValues += firstCell + '~' + secondCell + '~' + thirdCell+'~'+fourthCell;
							break;							
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}	
				cnt++;
			}
		}	
	}
	window.returnValue = selectedValues;
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}

function getCheckedItems_ewpd_notes(id, attrCount)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';
				switch(attrCount){
					case 1: selectedValues += currentCell.children[0].value; 
							break;
					case 2: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}
	window.returnValue = selectedValues;
	window.close();	
	return window.returnValue;

}



/**
	Function select/deselect all the checkboxes in the table.
	
	Parameters
	controller (Mandatory)- Current Object - (usually 'this' should be passed)
	table(Mandatory) - Table Id
	columnNo(Optional)- The column number where the checkboxes resides
	childPos (Optional)- The position of the checkbox within the coloumn
	
*/
function checkAll_ewpd(controller, table, columnNo, childPos) 
{
	
	var tableObject = document.getElementById(table);
	var chkname;
	var chkbox;
	if(columnNo == null || columnNo == undefined)
		columnNo = 1;
	if(childPos == null || childPos == undefined)
		childPos = 1;
		
	for(var i=0; i<tableObject.rows.length; i++) {
		chkbox = tableObject.rows[i].cells[columnNo-1].children[childPos-1];
		if (!(chkbox.disabled == true)) {
			chkbox.checked = controller.checked;
		}
	}
}

/*
	Function checks the checkboxes depending on the value of the source.
	
	Parameters
	table_id		- DataTable id
	source			- The data depending on which the check boxes will be checked.
					  This should be window.dialogArguments.selectedValues
	attrCount(of source)	- The number of attributes of the tilda String that constitues  a single data.
							  eg: id1~desc1~name1~id2~desc2~name2 	- here 3 attributes represents a single data. 
					  									  		 	- so the count is 3.
	attrPos(of source)		- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
					  		  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given, id1, id2 will be used to match the data with DataTable.
																  - if 2 is given, desc1, desc2 will be used to match the data with DataTable.
	tableChildPos			- detail about the data to which the source is to be checked. the position of the hidden field
							  in the second column of data table.
								eg : if given 1 -> the source will be matched against the first hidden field of second column of data table.
									 if given 2 -> the source will be matched against the second hidden field of second column of data table.
*/	
function matchCheckboxItems_ewpd(table_id, source, attrCount, attrPos, tableChildPos) {
	var tableObject;
	var valueToCompare1;
	var splittedVal;
	attrPos -= 1;
	if (document.getElementById(table_id) && source != null && source !='' && source != undefined) {
		var splittedVal = source.split("~");
		tableObject = document.getElementById(table_id);
		var checkboxObject;
		for(var i = 0; i<tableObject.rows.length; i++)
		{
			checkboxObject = tableObject.rows[i].cells[0].children[0];
			valueToCompare1 = tableObject.rows[i].cells[1].children[tableChildPos-1].value;
			for(var j = 0; j<splittedVal.length; j+=attrCount)
			 {
				valueToCompare2 = splittedVal[j+attrPos];
				if(valueToCompare1 == valueToCompare2) {
					checkboxObject.checked = true;
					break;
				}
			}
		}
	}
	
}


/*
	Function checks the checkboxes depending on the value of the source.
	
	Parameters
	table_id		- DataTable id
	source			- The data depending on which the check boxes will be checked.
					  This should be window.dialogArguments.selectedValues
	attrCount(of source)	- The number of attributes of the tilda String that constitues  a single data.
							  eg: id1~desc1~name1~id2~desc2~name2 	- here 3 attributes represents a single data. 
					  									  		 	- so the count is 3.
	attrPos(of source)		- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
					  		  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given, id1, id2 will be used to match the data with DataTable.
																  - if 2 is given, desc1, desc2 will be used to match the data with DataTable.
	tableChildPos			- detail about the data to which the source is to be checked. the position of the hidden field
							  in the second column of data table.
								eg : if given 1 -> the source will be matched against the first hidden field of second column of data table.
									 if given 2 -> the source will be matched against the second hidden field of second column of data table.
*/	
function matchCheckboxItems_ewpd_mandate(table_id, source, attrCount, attrPos, tableChildPos) {
	var tableObject;
	var valueToCompare1;
	var splittedVal;
	var flagToCompare1;
	var flagToCompare2;
	attrPos -= 1;
	
	if (document.getElementById(table_id) && source != null && source !='' && source != undefined) {
		var splittedVal = source.split("~");
		tableObject = document.getElementById(table_id);
		var checkboxObject;

		for(var i = 0; i<tableObject.rows.length; i++)
		{
			checkboxObject = tableObject.rows[i].cells[0].children[0];
			valueToCompare1 = tableObject.rows[i].cells[1].children[tableChildPos-1].value;

			for(var j = 0; j<splittedVal.length; j+=attrCount)
			 {
			valueToCompare2 = splittedVal[j+attrPos];
				if(valueToCompare1.toUpperCase() == valueToCompare2.toUpperCase() ) {
					checkboxObject.checked = true;
					if(splittedVal[j+attrPos+1] == "D"){
						checkboxObject.disabled = true;
						tableObject.rows[i].cells[1].children[2].value = "D";
					}else{
						tableObject.rows[i].cells[1].children[2].value = "E";
					}
					break;
				}
			}
		}
	}
	
}

/*
	Function to synchronize two tables. Normally used to synchronize 
	header table and its corresponding datatabale. Functions sets the
	width of data table to same as that of dataTable.
*/		
var headerTableId_stored;
var dataTableId_stored;
function syncTables(HeaderTableId, dataTableId){
	var headerTable = getObj(HeaderTableId);
	var dataTable = getObj(dataTableId);
	if(headerTable == null || dataTable == null)
		return;
	headerTableId_stored = HeaderTableId;
	dataTableId_stored = dataTableId;
	headerTable.onresize = syncTablesFn;
	syncTablesFn();
}

function syncTablesFn(){
	var relTblWidth = document.getElementById(dataTableId_stored).offsetWidth;
	if(relTblWidth!=0)
	document.getElementById(headerTableId_stored).width = relTblWidth + 'px';
}

	
// ==================================================================================================================
//								Product Structure
// ==================================================================================================================

function prodStructureBenfitComponentSelectModalWindow(url, hiddenId){
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;

	var retValue = window.showModalDialog(url, param, "dialogHeight:450px;dialogWidth:500px;resizable=false;status=no;");	
	if( retValue == undefined ) {
		return false;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	var valueForHidden = '';
	if (retValue != '') {
		valueForHidden = retValue;
	}	
	hiddenValueObj.value = valueForHidden;
	return true;
}

function setValueToHiddenField(tableId, targetId){
	var message = 'Are you sure you want to delete the selected  benefit component?';
	if (confirm(message) ){
		document.getElementById(targetId).value = document.getElementById(tableId).value;
		document.getElementById('prodStructureForm:deleteLink').click();
	} else
		return false;
} 	

function printPageProductStructure(flagName,value,url){
		document.getElementById(flagName).value = value;
		var urlValue = url+"?"+"flag="+document.getElementById(flagName).value;
		newWinForView =window.open(urlValue,'printPreview','status=yes,toolbar=no,scrollbars=yes,height=450,width=950,left=100,top=100,resizable=no');
}


function setValueToHiddenFieldFromDataTable(tableId, tableFieldId, targetId, linkName){
	getFromDataTableToHidden(tableId, tableFieldId, targetId);

	document.getElementById(linkName).click();
	return false;
}


function modalWindowForPreviousVersions(url,hiddenId,linkName){
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	retValue = window.showModalDialog(url, param, "dialogHeight:560px;dialogWidth:600px;resizable=false;status=no;scrollable=false");
	if( retValue == undefined ) {
		return;
	}
	var hiddenValueObj = document.getElementById(hiddenId);
	hiddenValueObj.value = retValue;
	var link = submitLink(linkName);
}

function copyPreviousVersions(tableId,hiddenId,selectedId){
	window.returnValue= getFromDataTableToHidden(tableId,hiddenId,selectedId);
	window.close();
}

function submitDataTableButton(tableId,tableFieldId,targetId,linkName){
	getFromDataTableToHidden(tableId,tableFieldId,targetId);
	var link = submitLink(linkName);
}



// ===================================================================================================================
//								Product Maintanance
// ===================================================================================================================


/* function getProdStrPopupUrl(lobId,beId,bgId,effDateId,expDateId,pdtType,mndtType,stCode,url){
 	var lob = getObj(lobId).value;
	var be = getObj(beId).value;
	var bg = getObj(bgId).value;
	var effDate = getObj(effDateId).value;
	var expDate = getObj(expDateId).value;
	var productType = getObj(pdtType).value;
	var mandateType = getObj(mndtType).value;
	var stateCode = getObj(stCode).value;
	var popupUrl = url;
	popupUrl += 'lob=' + escape(lob) + '&be=' + escape(be) + '&bg=' + escape(bg) + '&effDate=' + escape(effDate) + '&expDate=' + escape(expDate)+ '&productType=' + escape(productType)+ '&mandateType=' + escape(mandateType)+'&stateCode=' + escape(stateCode)+'&temp=' + Math.random();
	return popupUrl;
 }

	function fillSpace(){
		var objList = document.getElementsByTagName('span');
		var obj;
		for(var i=0; i<objList.length; i++) {
			obj = objList[i];
			if(obj.id.length >= 40){ 
				if(obj.id.substring(obj.id.length-9,obj.id.length) == 'spaceSpan') {
					obj.innerHTML='&nbsp;&nbsp;';
				}
			}
		}
	}
*/

 function getProdStrPopupUrl(lobId,beId,bgId,mbuId,effDateId,expDateId,pdtType,mndtType,stCode,url){
 	var lob = getObj(lobId).value;
	var be = getObj(beId).value;
	var bg = getObj(bgId).value;
	var mbu = getObj(mbuId).value;	
	var effDate = getObj(effDateId).value;
	var expDate = getObj(expDateId).value;
	var productType = getObj(pdtType).value;
	var mandateType = getObj(mndtType).value;
	var stateCode = getObj(stCode).value;
	var popupUrl = url;
	popupUrl += 'lob=' + escape(lob) + '&be=' + escape(be) + '&bg=' + escape(bg)+ '&mbu=' + escape(mbu) + '&effDate=' + escape(effDate) + '&expDate=' + escape(expDate)+ '&productType=' + escape(productType)+ '&mandateType=' + escape(mandateType)+'&stateCode=' + escape(stateCode)+'&temp=' + Math.random();
	return popupUrl;
 }

	function fillSpace(){
		var objList = document.getElementsByTagName('span');
		var obj;
		for(var i=0; i<objList.length; i++) {
			obj = objList[i];
			if(obj.id.length >= 40){ 
				if(obj.id.substring(obj.id.length-9,obj.id.length) == 'spaceSpan') {
					obj.innerHTML='&nbsp;&nbsp;';
				}
			}
		}
	}



// ===================================================================================================================
//								Benefit Component
// ===================================================================================================================
function isNumber(e){
	var keycode;
	if (window.event) keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	if (!(keycode > 47 && keycode < 58))
		return false;
    return true;
}









// ===================================================================================================================
//								Standard Benefit
// ===================================================================================================================


	// Citation Number select
	
      function getTextFieldItems(tableId)

      {
            var selectedValues = '';
            var valueForHidden = '';
            var tableObject = document.getElementById(tableId);
            var txtFldValue = "";
            for (var i=0,found =0, n = tableObject.rows.length; i<n ;i++)
            {
                  txtFldValue = tableObject.rows[i].cells[0].children[0].value;
                  txtFldValue = trim(txtFldValue);
                  if(txtFldValue != null && txtFldValue!= '' && txtFldValue.length > 0)
                  {
                        if(found > 0) {
                              selectedValues += '<br>';
                              valueForHidden += '~';
                        }
                        selectedValues +=  txtFldValue  ;
                        valueForHidden += txtFldValue+'~'+txtFldValue;
  					  	found++; 
                  }
            }
// Added ***************************************************************************
            for (var i=0, n = tableObject.rows.length; i<n ;i++){
                  if(tableObject.rows[i].cells[0].children[0].value != '') {
		                if(trim(tableObject.rows[i].cells[0].children[0].value).length > 10) {
                            alert("The citation number length should not be greater than 10");
                            return;
                        }
                        for (var j=i+1 ; j<n ;j++){
                              if(trim(tableObject.rows[i].cells[0].children[0].value) == trim(tableObject.rows[j].cells[0].children[0].value)) {
                                    alert("Duplicate Citation Number Exists. Please modify.");
                                    return;
                              }
                        }
                  }
            }
// *******************************************************************************************
            if(selectedValues != ''){
                  window.returnValue = selectedValues+'`'+valueForHidden;
            }else{
                  window.returnValue = '';
            }
            window.close();   
      }


	/* Function to set citation numbers in popup */
	function setCitation(hiddenObj,tableId,dis)
	{
	    var val1 = hiddenObj.value;
	    var array =val1.split("~");
	    var val="";
	    for(var i=0; i<array.length;i+=2){ 
	    val=val+array[i]+"~";
	    }
	    var finalVal ;
		var tableObject = document.getElementById(tableId);
	    
	    
	    
	    if(val != null && val != '')
	    {
	     
			finalVal = val.split("~");
			
			// For protecting values in transfered status
			var m = 0;
			if(dis != undefined && dis.value != '') {
				var disabledValue = dis.value.split("~");
				m = disabledValue.length;
			}
			//---
			
			if(finalVal != null)
			{
				
			    for(var i=0,j=0, n = finalVal.length ; i < n ; i+=1,j++)
			    {
					if(j > 3)
						   insertRows(tableId, false);
					tableObject.rows[j].cells[0].children[0].value = finalVal[i];
					
					// For transfered
					for(var k=0; k<m; k+=2) {
						if( trim(finalVal[i]) == trim(disabledValue[k]) ) {
							tableObject.rows[j].cells[0].children[0].disabled = true;
							break;
						}
					}
			    }
			}
	    }
	}

	function insertRows(tableId, isVisibile){
			tableObject = document.getElementById(tableId);
            numrows = tableObject.rows.length;
            var rows = eval(numrows+1)

            aRow =  tableObject.insertRow();

            aCell2 = aRow.insertCell();

            aCell2.align='Center';

            aCell2.innerHTML = "<input id='textfieldId' name='textfield' type='text' class='formInputField' maxlength='50' onkeydown='return isNumberKey(this);'>";

            tigra_tables(tableId,0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
            
            	
			if(tableObject && tableObject.rows){
				if(tableObject.rows.length > 13) {
					var divObj = document.getElementById('citationDiv');
					divObj.style.borderBottom = '1px solid #cccccc';
					divObj.style.height = '300px';
				}
			}
			if (isVisibile == true) {
				aCell2.children[0].focus();			     
			}
      }

// ===================================================================================================================
//								Standard Benefit Configuration
// ===================================================================================================================

function getSelectedComponents(id)
{
	var tableObject = document.getElementById(id) ;
	if (tableObject)
	{
		var selectedValues = '';
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			var value = tableObject.rows[i].cells[1].children[0].value;
			if (checkboxObject && checkboxObject.checked)
			{
				if(selectedValues != '')
					selectedValues = selectedValues +'~'+value+'~'+(i+1);
				else
					selectedValues = value+'~'+(i+1);
			}
		}	
	}
	window.returnValue = selectedValues;
	window.close();	
	return selectedValues;
}

function getCheckedItemsForQuestions(id1,id2)
{
	var tableObject1;	
	var tableObject2;	
	if (document.getElementById(id1))
	{
		tableObject1 = document.getElementById(id1);
		tableObject2 = document.getElementById(id2);
		var selectedValues = '';
		var selectedCodes = '';
		var cnt = 0;
		for (var i=0;i<tableObject1.rows.length;i++)
		{
		
			var checkboxObject = tableObject1.rows[i].cells[0].children[0];
			
			if (checkboxObject && checkboxObject.checked)
			{	
				
				if(cnt > 0)
				{
					selectedValues += '<br>';
					selectedCodes += '~';
				}
				selectedValues += tableObject1.rows[i].cells[1].innerText;	
				var abc = tableObject1.rows[i].cells[2].children[0];
				if(abc.options[abc.selectedIndex].text == '')
					selectedCodes += tableObject1.rows[i].cells[1].innerText + "~" + " ";
				else
					selectedCodes += tableObject1.rows[i].cells[1].innerText + "~" + abc.options[abc.selectedIndex].text;
				cnt++;
			}
		}	
		for (var i=0;i<tableObject1.rows.length;i++)
		{
		
			var checkboxObject = tableObject2.rows[i].cells[0].children[0];
			
			if (checkboxObject && checkboxObject.checked)
			{	
				
				if(cnt > 0)
				{
					selectedValues += '<br>';
					selectedCodes += '~';
				}
				selectedValues += tableObject2.rows[i].cells[1].innerText;	
				var abc = tableObject2.rows[i].cells[2].children[0];
				if(abc.options[abc.selectedIndex].text == '')
					selectedCodes += tableObject2.rows[i].cells[1].innerText + "~" + " ";
				else
					selectedCodes += tableObject2.rows[i].cells[1].innerText + "~" + abc.options[abc.selectedIndex].text;
				cnt++;
			}
		}
	}
	
	
	if(selectedValues != ''){
		window.returnValue = selectedValues+'`'+selectedCodes;
	}else{
		window.returnValue = '';
	}
	window.close();	
	return false;

}

/**
 * Function to clear the radioButton when doubleClicked
 */
function clearRadioButton(radioButton){
		radioButton.checked = false;
}

function setStructureKey() {
	
	getFromDataTableToHidden('searchForm:searchResultTable','hidden_productStructureId','searchForm:selectedStructureIdForView');
	getFromDataTableToHidden('searchForm:searchResultTable','hidden_productStructureName','searchForm:selectedStructureNameForView');
	getFromDataTableToHidden('searchForm:searchResultTable','hidden_version','searchForm:selectedStructureVersionForView');
	var url = "../productStructure/viewProductStructureGeneralInformation.jsp"+getUrl()+"?"+"id="+document.getElementById('SearchForm:selectedStructureIdForView').value+"&"+"name="+document.getElementById('SearchForm:selectedStructureNameForView').value+"&"+"version="+document.getElementById('SearchForm:selectedStructureVersionForView').value;
	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	
}	
	

function printSelection() 
{
	
	var param = new Object();
	param.parentWindow = window;
	var url = "../popups/printSelectionPopup.jsp"+getUrl();
	newWinForView =window.showModalDialog(url,param,"dialogHeight:130px;dialogWidth:275px;resizable=false;status=no;");
	
}

/**
 * Method for Product & Product Structure advance print
 */
function productPrintSelection(){
	var param = new Object();
	param.parentWindow = window;
	var url = "../popups/productPrintSelectionPopup.jsp"+getUrl();
	newWinForView =window.showModalDialog(url,param,"dialogHeight:130px;dialogWidth:275px;resizable=false;status=no;");
}

/**
 * Function to extract 1,2 for string 1~a~2~b
 */
function formatTildaToComma(objName)
{
    
	var formattedString = "";
	var obj = document.getElementById(objName);

	var val = obj.innerHTML;

	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{

		for(i=1, n = values.length; i < n; i+=2)
		{
			formattedString += values[i] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}

/**
 * Function to extract a,b for string 1~a~2~b
 */
function formatTildaToCommaforCombo(objName)
{
    
	var formattedString = "";
	var obj = document.getElementById(objName);

	var val = obj.innerHTML;

	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{

		for(i=1, n = values.length; i < n; i+=2)
		{
			formattedString += values[i] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}


function printPage()
{

	window.opener = window.dialogArguments.parentWindow;
	var doc = window.opener.document;
	var navigationForPrint = window.dialogArguments.navigationForPrint;
	commonPrint(printOptionform.printSelection[1].checked, doc,navigationForPrint);
}

function productPrintPage(){
  
	window.opener = window.dialogArguments.parentWindow;
	var doc = window.opener.document;
	var navigationForPrint = window.dialogArguments.navigationForPrint;
	productCommonPrint(printOptionform.printSelection[1].checked, printOptionform.printSelection[2].checked, doc,navigationForPrint);
}	

function productCommonPrint(detailPrintFlag, advancePrinFlag, doc,navigationForPrint){	
	 
	
	if(doc == null || doc == 'undefined')
		doc = window.document;
		
	var currentPrintPage = doc.getElementById("currentPrintPage");
	if(currentPrintPage == null || currentPrintPage == undefined) {
		alert("currentPrintPage field not available");
		return ;
	}
	var printPageValue = currentPrintPage.value;
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	
	
	
	if(detailPrintFlag &&(printPageValue == 'generalInfo'))
		{
			var url = "../productStructure/printProductStructureGeneralInformation.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
	else if(advancePrinFlag &&(printPageValue == 'generalInfo')){
		var url = "../productStructure/printProdStructureComponentHierarchy.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printPageValue == 'generalInfo')	
		{
			var url = "../productStructure/printProductStructureGenInfoBasic.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
	else if(detailPrintFlag &&(printPageValue == 'productGeneralInformation'))
		{
			var printValueForGenInfo = "productGeneralInformation";
			var printValueForComAss = "productComponentAssociation";
			var printValueForNotes = "productNotes";
			var printValueForAdminOption = "productAdminOption";
			var printValueForDenial = "productDenialExclusion";
			var date = new Date();
			var url = "../product/productGeneralInformationDetailedPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForGenInfo + "&&printValueForComAss=" + printValueForComAss + "&&printValueForNotes=" + printValueForNotes + "&&printValueForAdminOption=" + printValueForAdminOption + "&&printValueForDenial=" + printValueForDenial + "&&date=" +currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
	else if(advancePrinFlag &&(printPageValue == 'productGeneralInformation'))
	{
			var printValueForGenInfo = "productGeneralInformation";
			var printValueForComAss = "productComponentAssociation";
			var printValueForNotes = "productNotes";
			var printValueForAdminOption = "productAdminOption";
			var printValueForDenial = "productDenialExclusion";
			var date = new Date();
			var url = "../product/printProductComponentHierarchy.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForGenInfo + "&&printValueForComAss=" + printValueForComAss + "&&printValueForNotes=" + printValueForNotes + "&&printValueForAdminOption=" + printValueForAdminOption + "&&printValueForDenial=" + printValueForDenial + "&&date=" +currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printPageValue == 'productGeneralInformation')	
		{
			var printValueForGenInfo = "productGeneralInformation";
			var url = "../product/productGeneralInfoPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForGenInfo + "&&date=" +currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
}
function commonPrint(printSelectionFlag, doc,navigationForPrint)
{	
 
	if(doc == null || doc == 'undefined')
		doc = window.document;
		
	var currentPrintPage = doc.getElementById("currentPrintPage");
	
	if(currentPrintPage == null || currentPrintPage == undefined) {
		alert("currentPrintPage field not available");
		return ;
	}

	var printPageValue = currentPrintPage.value;
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
		
	
//	if(printSelectionFlag &&((printPageValue == 'serviceTypeMapping')))
//	{
//		var url = "../blueexchange/printServiceTypeMapping.jsp?date="+currentTime;
//		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
//	}
	if(printSelectionFlag &&((printPageValue == 'standardBenefit')||(printPageValue == 'benefitMandate')||(printPageValue == 'standardBenefitDefinition')||(printPageValue == 'benefitLevel')||(printPageValue == 'benefitAdministration')||(printPageValue == 'adminOptions')||(printPageValue == 'addQuestion')||(printPageValue == 'notes')||(printPageValue == 'benefitAdminMethod')))
	{
		var url = "../standardBenefit/standardBenefitDetailPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag &&((printPageValue == 'adminOptionQuestion')||(printPageValue == 'adminOption')))
	{
		var url = "../adminoptions/adminOptionsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}	    
     else if(printSelectionFlag &&((printPageValue == 'contractMigrationReportGeneration') ))
    {
    	var printAction = "print";
    	var date = new Date();
    	var curTime = date.getTime();
    	var number = Math.random();
        var url = "../migration/contractMigrationReportGenerationPrint.jsp"+getUrl()+"?date="+curTime+'&action='+ printAction +'&number='+number;
       	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    }	
     else if(printSelectionFlag &&((printPageValue == 'subCatalogGenInfo') || (printPageValue == 'associatedItemPrint')))
    {
    	var printAction = "print";
    	var date = new Date();
    	var curTime = date.getTime();
    	var number = Math.random();
        var url = "../subCatalog/subCatalogDetailPrint.jsp"+getUrl()+"?date="+curTime+'&action='+ printAction +'&number='+number;
       	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    }	
       else if(printSelectionFlag &&((printPageValue == 'roleGenInfo') || (printPageValue == 'roleDetailPrint')))
    {
    	var printAction = "print";
    	var date = new Date();
    	var curTime = date.getTime();
    	var number = Math.random();
        var url = "../role/roleDetailPrint.jsp"+getUrl()+"?date="+curTime+'&action='+ printAction +'&number='+number;
       	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    }
    
    else if(printSelectionFlag &&((printPageValue == 'moduleGenInfo') || (printPageValue == 'moduleConfigInfo')))
    {
    	var printAction = "print";
    	var date = new Date();
    	var curTime = date.getTime();
    	var number = Math.random();
        var url = "../module/moduleDetailPrint.jsp"+getUrl()+"?date="+curTime+'&action='+ printAction +'&number='+number;
       	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    }
    else if(printSelectionFlag &&((printPageValue == 'contractBasicInfo')||(printPageValue == 'contractSpecificInfo')||(printPageValue == 'contractPricingInfo')||(printPageValue == 'contractComment')||(printPageValue == 'contractNotes')||(printPageValue == 'contractAdminOption')||(printPageValue == 'contractRules')||(printPageValue == 'contractRulesInformation') || (printPageValue == 'contractMembershipInfo')|| (printPageValue == 'contractAdaptedInfo')))
    {
    	var printContractBasicInfo = "contractBasicInfo";
		var printContractSpecificInfo = "contractSpecificInfo";
		var printContractPricingInfo = "contractPricingInfo";
		var printContractComment = "contractComment";
		var printContractNotes = "contractNotes";
		var printContractAdminOption = "printContractAdminOption";
		var printValueForRulesInfo = "contractRulesInformation";
		var printValueForMembership ="contractMembershipInfo";
		var printValueForAdapted = "contractAdaptedInfo";
		var date = new Date();
		var curTime = date.getTime();
    	var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printContractBasicInfo=" + 
					printContractBasicInfo + "&&printContractSpecificInfo=" + printContractSpecificInfo + "&&printContractPricingInfo=" + printContractPricingInfo + "&&printContractComment=" + printContractComment + "&&printContractNotes=" + printContractNotes + "&&printContractAdminOption=" + printContractAdminOption+"&&printValueForGenInfo="+printValueForRulesInfo+"&&printValueForMembership="+printValueForMembership+"&&printValueForAdapted="+printValueForAdapted+ "&&date=" + curTime+"&&temp="+Math.random();
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    }
	else if(printSelectionFlag &&((printPageValue == 'benefitComponent')||(printPageValue == 'benefitComponentHierarchyPrint')||(printPageValue == 'nonAdjBenefitMandates') ||(printPageValue == 'benefitComponentNotes')))
	{
		var url = "../benefitComponent/benefitComponentDetailedPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag && ((printPageValue == 'componentBenefitGeneralInfo')||(printPageValue == 'componentBenefitDefinitions')||(printPageValue == 'benefitOverriddenNotesPrint')||(printPageValue == 'benefitComponentAdminOption')||(printPageValue == 'benefitComponentAdminMethod')||(printPageValue == 'componentBenefitAdministration')||(printPageValue == 'benefitMandateInformation'))){		
		var url = "../benefitComponent/componentBenefitDetailsPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	// start of Product 
	else if(printSelectionFlag &&((printPageValue == 'productGeneralInformation')||(printPageValue == 'productComponentAssociation')||(printPageValue == 'productNotes')||(printPageValue == 'productAdminOption')||(printPageValue == 'productDenialExclusion')))
	{
		var printValueForGenInfo = "productGeneralInformation";
		var printValueForComAss = "productComponentAssociation";
		var printValueForNotes = "productNotes";
		var printValueForAdminOption = "productAdminOption";
		var printValueForDenial = "productDenialExclusion";
		var date = new Date();
		var curTime = date.getTime();
		var url = "../product/productGeneralInformationDetailedPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForGenInfo + "&&printValueForComAss=" + printValueForComAss + "&&printValueForNotes=" + printValueForNotes + "&&printValueForAdminOption=" + printValueForAdminOption + "&&printValueForDenial=" + printValueForDenial + "&&date=" + curTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	//----------------	
	else if(printSelectionFlag &&((printPageValue == 'productBenefitAdministration')||(printPageValue == 'productBenefitDefinitionPrint')||(printPageValue == 'productBenefitGenInfoPrint')||(printPageValue == 'productBenefitNotes')||(printPageValue == 'productBenefitMandateInfoPrint')))
	{		
			var printValueForGenInfo = "productBenefitGenInfoPrint";
			var printValueForBenDet = "productBenefitDefinitionPrint";
			var printValueForNotes = "productBenefitNotes";
			var printValueForManInfo = "productBenefitMandateInfoPrint";
			var date = new Date();
			var urlSubstitute = "../product/productBenefitDefinitonDetailedPrintSubstitute.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForBenDet=" + printValueForBenDet + "&&printValueForManInfo=" + printValueForManInfo + "&&printValueForNotes=" + printValueForNotes + "&&date=" + currentTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	// end of Product
	
	// start of Product Structure
		else if(printSelectionFlag &&((printPageValue == 'generalInfo')||(printPageValue == 'benefitComp')))
		{
			var url = "../productStructure/printProductStructureGeneralInformation.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printSelectionFlag &&((printPageValue == 'benefitDefinition')||(printPageValue == 'nonAdjudicate')||(printPageValue == 'benefitGeneralInfo')||(printPageValue == 'productStructureBenefitNotes')||(printPageValue == 'benefitAdminProductStructure')||(printPageValue == 'productStructureAdminOption')||(printPageValue == 'productStructureAdminMethod')))
		{
			var url = "../productStructure/printprodStructStandardBenefitGeneralInfo.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
	// end of Product Structure
				/* modification starts for admin method */

else if(printSelectionFlag && printPageValue == 'adminOptionOverrideForProduct')
    {
    		var printValueForGenInfo = "productBenefitGenInfoPrint";
			var printValueForBenDet = "productBenefitDefinitionPrint";
			var printValueForNotes = "productBenefitNotes";
			var printValueForManInfo = "productBenefitMandateInfoPrint";
			var date = new Date();
			var urlSubstitute = "../product/productBenefitDefinitonDetailedPrintSubstitute.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForBenDet=" + printValueForBenDet + "&&printValueForManInfo=" + printValueForManInfo + "&&printValueForNotes=" + printValueForNotes + "&&date=" + currentTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    }	
else if(printSelectionFlag && printPageValue == 'productAdminMethod')  {
{
    	
    	var printValueForGenInfo = "productBenefitGenInfoPrint";
			var printValueForBenDet = "productBenefitDefinitionPrint";
			var printValueForNotes = "productBenefitNotes";
			var printValueForManInfo = "productBenefitMandateInfoPrint";
			var date = new Date();
			var urlSubstitute = "../product/productBenefitDefinitonDetailedPrintSubstitute.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForBenDet=" + printValueForBenDet + "&&printValueForManInfo=" + printValueForManInfo + "&&printValueForNotes=" + printValueForNotes + "&&date=" + currentTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		
    }	
    
}    
else if(printSelectionFlag && (printPageValue == 'contractgroupRuleView'))
	{
		var date = new Date();
		var curTime = date.getTime();
    	var number = Math.random();
    	var ruleId = doc.getElementById("printRuleId").value;
    	var url = "../contract/groupRulePrint.jsp"+getUrl()+"?ruleId="+ruleId+"&date="+curTime+'&number='+number;
    	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    }
    //else if(printSelectionFlag &&((printPageValue == 'productStructureAdminOption') || (printPageValue == 'productStructureAdminMethod')))
    //{
    	//var date = new Date();
    	//var curTime = date.getTime();
    	//var number = Math.random();
       // var url = "../productStructure/prodStructureBenefitAdministrationDetailPrint.jsp?date="+curTime+'&number='+number;
       	//newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    //}
  //  else if(printSelectionFlag &&((printPageValue == 'benefitComponentAdminMethod')||(printPageValue == 'benefitComponentAdminOption')))
	//	{
	//		var url = "../benefitComponent/componentBenefitAdministrationDetailPrint.jsp?curTime="+currentTime;
	//		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	//	}	
		
	else if(printSelectionFlag &&((printPageValue == 'adminOptionOverrideForContract')||(printPageValue == 'adminMethodOverridePrint')))
		{
			var url = "../contract/standardBenefitDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	
	
	else if(printSelectionFlag &&((printPageValue == 'adminMethodOverridePrintsub')||(printPageValue == 'adminMethodOverridePrintsub')))
		{
			var url = "../contract/standardBenefitDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	
		
    /* modification endss for admin method */	
	//start of contract
	
	else if(printSelectionFlag &&(printPageValue == 'productBenefitAdministration'))
	{
	
		var date = new Date();
		var curTime = date.getTime();
		var url = "../product/productBenefitAdministrationPrintSubstitute.jsp"+getUrl()+"?date="+curTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag &&((printPageValue == 'productComponentGeneralInfo')||(printPageValue == 'productComponentNotes')||(printPageValue == 'productBenefitInfo')))
	{	
		var date = new Date();
		var curTime = date.getTime();
		var printValueForGenInfo = "productComponentGeneralInfo";
		var printValueForNotes = "productComponentNotes";
		var url = "../product/productComponentGeneralInfoPrint.jsp"+getUrl()+"?printValueForGenInfo="+ printValueForGenInfo + "&&printValueForNotes="+ printValueForNotes + "&&date="+curTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag &&((printPageValue == 'notesGeneralInformation')||(printPageValue == 'notesDataDomain'))){
	var printValueForGenInfo = "notesGeneralInformation";
	var printValueForDataDom = "notesDataDomain";
	var date = new Date();	
	var curTime = date.getTime();
	var url = "../notes/notesEditPrint.jsp"+getUrl()+"?date="+ curTime+"&&printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
	}
	else if(printSelectionFlag &&((printPageValue == 'notesGeneralInformationEdit')||(printPageValue == 'notesDataDomainEdit'))){
		if(navigationForPrint == 'true'){
			var message = "The selected datas may not be displayed in the print unless they are saved.Are you sure you want to proceed?";	
			if(window.confirm(message)){
				var printValueForGenInfo = "notesGeneralInformation";
				var printValueForDataDom = "notesDataDomain";
				var date = new Date();	
				var curTime = date.getTime();
				var url = "../notes/notesPrintView.jsp"+getUrl()+"?date="+ curTime+"&&printValueForGenInfo=" + 
									printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom;;
				newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
	
			}

			else{
				return false;
			}
		}else{
			var printValueForGenInfo = "notesGeneralInformation";
			var printValueForDataDom = "notesDataDomain";
			var date = new Date();	
			var curTime = date.getTime();
			var url = "../notes/notesPrintView.jsp"+getUrl()+"?date="+ curTime+"&&printValueForGenInfo=" + 
								printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom;;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
	
		}
	}else if(printSelectionFlag &&((printPageValue == 'notesViewAllVersionsPrint'))){
		var printValueForViewAllVersions = "notesViewAllVersionsPrint";
		var date = new Date();	
		var curTime = date.getTime();
		var url = "../notes/notesViewAllVersionsPrint.jsp"+getUrl()+"?date="+ curTime+"&&printValueForViewAllVersions=" + printValueForViewAllVersions;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
		
	}
	else if(printSelectionFlag &&((printPageValue == 'contractViewAllVersions'))){
		
		var date = new Date();	
		var curTime = date.getTime();
		var url = "../contract/contractViewAllVersionsPrint.jsp"+getUrl()+"?date="+ curTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
		
	}
	else if(printSelectionFlag &&((printPageValue == 'contractViewAllDS'))){
		
		var date = new Date();
		var curTime = date.getTime();	
		var val ="5";
		var valPage = "1";
		var url = "../contract/contractViewAllDSPrint.jsp"+getUrl()+"?page="+val+'&pageId='+valPage+"date="+ curTime;
		
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
		
	}
	else if(printSelectionFlag &&(printPageValue == 'notesSearchResultPrint'))
	{
		var url = "../notes/noteSearchResultPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag &&(printPageValue == 'contractProductGeneralInformation'))
	{
		
		var printValueForBenefitComponentList = "printValueForBenefitComponentList";
		var printValueForGeneralInfo = "printValueForGeneralInfo";
		var url = "../contract/contractProductBenefitComponentListPrint.jsp"+getUrl()+"?printValueForBenefitComponentList="+ 
					printValueForBenefitComponentList+"&&printValueForGeneralInfo="+printValueForGeneralInfo+"&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		
	}
	else if(printSelectionFlag &&(printPageValue == 'contractProductAdmin'))
	{
		var url = "../contract/contractProductAdministrationSubstitutePrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag &&((printPageValue == 'contractComponentGeneralInfo')||(printPageValue == 'contractBenefitComponentNotePrint')||(printPageValue == 'contractBenefitCustomizationPrint')))
	{		 
		var url = "../contract/benefitComponentDetailedPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag &&((printPageValue == 'contractBenefitNotesPrint')||(printPageValue == 'benefitMandateInfo') ||(printPageValue == 'contractStandardBenefitGeneralPrint') ||(printPageValue == 'contractCoveragePrint') ))
	{
		var url = "../contract/standardBenefitDetailPrintSubstitute.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&(printPageValue == 'contractBenefitAdminProductStructure'))
	{
		var url = "../contract/standardBenefitDetailPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&(printPageValue == 'contractSearchResultPrint'))
	{
		var url = "../contract/contractBasicSearchPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&(printPageValue == 'adminMethodSPSValidationPrint'))
	{
		alert("Print not available");
	}else if(printSelectionFlag &&printPageValue == 'productsearchresultsprint')
	{
			var url = "../search/ProductSearchResultsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&printPageValue == 'productstructuresearchresultsprint')
	{
			var url = "../search/ProductStructureSearchResultsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&printPageValue == 'benefitcomponentsearchresultsprint')
	{
			var url = "../search/BenefitComponentSearchResultsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&printPageValue == 'benefitsearchresultsprint')
	{
			var url = "../search/BenefitSearchResultsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&printPageValue == 'benefitlevelsearchresultsprint')
	{
			var url = "../search/BenefitLevelSearchResultsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&printPageValue == 'notessearchresultsprint')
	{
			var url = "../search/NotesSearchResultsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}else if(printSelectionFlag &&printPageValue == 'contractsearchresultprint')
	{
			var url = "../search/ContractSearchResultsDetailPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag &&((printPageValue == 'productStructureBenefiComponentNotes')||(printPageValue == 'benefitGenInfo')||(printPageValue == 'prodStructAssocBenefitView')))
	{
			var url = "../productStructure/printprodStructBenefitComponentDetailedPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	}
	else if(printSelectionFlag && (printPageValue == 'standardBenefitSearch'))
	{
		var url = "../standardBenefit/standardBenefitSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

	}  
	else if(printSelectionFlag && (printPageValue == 'questionSearchResultPrint'))
	{
		var url = "../question/questionSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

	}
	else if(printSelectionFlag && (printPageValue == 'adminOptionSearchResultPrint'))
	{
		var url = "../adminoptions/adminOptionSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

	}
	else if(printSelectionFlag && (printPageValue == 'benefitComponentSearchResultPrint'))
	{
		var url = "../benefitComponent/benefitComponentSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

	} 	
	else if(printSelectionFlag && (printPageValue == 'productStructureSearchResultPrint'))
	{
		var url = "../productStructure/productStructureSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

	}
	else if(printSelectionFlag && (printPageValue == 'productSearchResultPrint'))
	{
		var url = "../product/productSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

	} 	
	else if(printSelectionFlag && (printPageValue == 'contractProductBenefitComponentList'))
	{
	var printValueForBenefitComponentList = "printValueForBenefitComponentList";
	var printValueForGeneralInfo = "printValueForGeneralInfo";
		var url = "../contract/contractProductBenefitComponentListPrint.jsp"+getUrl()+"?printValueForBenefitComponentList="+ 
					printValueForBenefitComponentList+"&&printValueForGeneralInfo="+printValueForGeneralInfo+"&&temp="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

	} 	  	  	       
	else if(!printSelectionFlag)
	{
		if(printPageValue == 'serviceTypeMapping')
		{
			var url = "../blueexchange/printServiceTypeMapping.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractProductBenefitComponentList')
		{
			var printValueForBenefitComponentList = "printValueForBenefitComponentList";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractProductBenefitComponentListPrint.jsp"+getUrl()+"?printValueForBenefitComponentList="+ 
					printValueForBenefitComponentList+ "&&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
	     else if(printPageValue == 'contractMigrationReportGeneration') 
	    {
	    	var printAction = "print";
	    	var date = new Date();
	    	var curTime = date.getTime();
	    	var number = Math.random();
	        var url = "../migration/contractMigrationReportGenerationPrint.jsp"+getUrl()+"?date="+curTime+'&action='+ printAction +'&number='+number;
	       	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    	}
			
		else if(printPageValue == 'catalogGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../catalog/catalogPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'subCatalogGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../subCatalog/subCatalogPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'taskGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../task/taskPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
         else if(printPageValue == 'roleGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../role/roleGeneralInformationPrint.jsp"+getUrl()+"?number="+ number +"&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'roleConfigInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../role/roleConfigurationPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'moduleGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../module/moduleGeneralInformationPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'associatedSubTask')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../module/associatedSubTaskPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'moduleConfigInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../module/moduleConfigurationPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
         
         else if(printPageValue == 'subTaskGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../task/subTaskPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'associatedItemPrint')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../subCatalog/associatedItemsPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
         else if(printPageValue == 'roleDetailPrint')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../role/roleConfigurationPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
          else if(printPageValue == 'roleTaskConfigInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../role/roleTaskConfigurationPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
         else if(printPageValue == 'roleSubTaskConfigInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../role/roleSubTaskConfigurationPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        else if(printPageValue == 'itemGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../item/itemPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }else if(printPageValue == 'taskGenInfo')
        {
        	var action = 'print';
        	var date = new Date();
        	var curTime = date.getTime();
        	var number = Math.random();
        	var url = "../task/taskPrint.jsp"+getUrl()+"?number="+ number +"&action=" + action+ "&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
        }
        
		else if(printPageValue == 'standardBenefit')
		{
			var url = "../standardBenefit/standardBenefitPrint.jsp"+getUrl()+"?&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

		}
		else if(printPageValue == 'standardBenefitSearch')
		{
			var url = "../standardBenefit/standardBenefitSearchPrint.jsp"+getUrl()+"?&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

		}
		else if(printPageValue == 'benefitMandate')
		{
			
			var url = "../standardBenefit/benefitMandatePrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'standardBenefitDefinition')
		{
			var url = "../standardBenefit/benefitDefinitionPrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
	
		else if(printPageValue == 'nonAdjBenefitMandates')
		{
			var url = "../benefitComponent/nonAdjBenefitMandatesPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'notes')
		{
			var url = "../standardBenefit/benefitNotesPrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractBasicInfo')
		{
			var printContractBasicInfo = "contractBasicInfo";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printContractBasicInfo=" + 
					printContractBasicInfo+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'adminMethodSPSValidationPrint')
		{
			
			var url = "../adminMethods/adminMethodSPSValidationPrintPopup.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractRuleSequenceView')
		{
			var url = "../contractpopups/ruleViewPrintSubstitutePopup.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractBlazeRuleSequenceView')
		{
			alert("Print not available");
		}
		else if(printPageValue == 'contractSpecificInfo')
		{
			var printContractSpecificInfo = "contractSpecificInfo";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printContractSpecificInfo=" + 
					printContractSpecificInfo+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
			else if(printPageValue == 'contractMembershipInfo')
		{
			var printValueForMembership ="contractMembershipInfo";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printValueForMembership=" + 
					printValueForMembership+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractAdaptedInfo')
		{
			var printValueForAdapted ="contractAdaptedInfo";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printValueForAdapted=" + 
					printValueForAdapted+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractPricingInfo')
		{
			var printContractPricingInfo = "contractPricingInfo";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printContractPricingInfo=" + 
					printContractPricingInfo+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractNotes')
		{
			var printContractNotes = "contractNotes";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printContractNotes=" + 
					printContractNotes+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractComment')
		{
			var printContractComment = "contractComment";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printContractComment=" + 
					printContractComment+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractRulesInformation')
		{
			var printValueForRulesInfo = "contractRulesInformation";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForRulesInfo+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractAdminOption')
		{
			var printContractAdminOption = "printContractAdminOption";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractDetailedPrint.jsp"+getUrl()+"?printContractAdminOption=" + 
					printContractAdminOption+ "&&date=" + curTime+"&&temp="+Math.random();
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractViewAllVersions')
		{
			var date = new Date();	
			var curTime = date.getTime();
			var url = "../contract/contractViewAllVersionsPrint.jsp"+getUrl()+"?date="+ curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");	
		}
		else if(printPageValue == 'contractViewAllDS')
		{
			var date = new Date();	
			var curTime = date.getTime();
			var val ="5";
			var valPage = "1";
			var url = "../contract/contractViewAllDSPrint.jsp"+getUrl()+"?page="+val+'&pageId='+valPage+"date="+ curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");	
		}
		else if(printPageValue == 'componentBenefitAdministration')	
		{
			var url = "../benefitComponent/componentBenefitAdministrationPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'productAdmin')	
		{
			var url = "../product/productAdminPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		
		
		else if(printPageValue == 'benefitMandateInformation')			
		{			
			var url = "../benefitComponent/componentBenefitMandateInfoPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	
		else if(printPageValue == 'componentBenefitGeneralInfo')
		{
			var url = "../benefitComponent/componentBenefitGeneralInformationPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'componentBenefitDefinitions')
		{
			var url = "../benefitComponent/componentBenefitDefinitionsPrintInter.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitComponentNotes')
		{
			var url = "../benefitComponent/benefitComponentNotesPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'productStructureBenefiComponentNotes')
		{
			var url = "../productStructure/productStructureBenefitComponentNotesPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'productStructureBenefitNotes')
		{
			var url = "../productStructure/productStructureBenefittNotesPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'questionSearchResultPrint')
		{
			var url = "../question/questionSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	
		}
		else if(printPageValue == 'adminOptionSearchResultPrint')
		{
			var url = "../adminoptions/adminOptionSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	
		}
		else if(printPageValue == 'benefitComponentSearchResultPrint')
		{
			var url = "../benefitComponent/benefitComponentSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	
		}
		else if(printPageValue == 'productStructureSearchResultPrint')
		{
			var url = "../productStructure/productStructureSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	
		}
		else if(printPageValue == 'productSearchResultPrint')
		{
			var url = "../product/productSearchPrint.jsp"+getUrl()+"?&&temp="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
	
		}  
		else if(printPageValue == 'benefitComponentHierarchyPrint')	
		{
			var url = "../benefitComponent/benefitComponentHierarchiesPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitAdministration')
		{
			var date = new Date();
			var curTime = date.getTime();
			var url = "../standardBenefit/benefitAdministrationPrint.jsp"+getUrl()+"?date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	
		else if(printPageValue == 'benefitLevel')
		{
			var date = new Date();
			var time = date.getTime();
			var url = "../standardBenefit/benefitLevelPrint.jsp"+getUrl()+"?time=" + time;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'nonAdjBenefitMandates')
		{
			var url = "../benefitComponent/nonAdjBenefitMandatesPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'componentBenefitAdministration')	
		{
			var url = "../benefitComponent/componentBenefitAdministrationPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue =='componentBenefitGeneralInfo')
		{
			var url = "../benefitComponent/componentBenefitGeneralInformationPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'componentBenefitDefinitions')
		{
			var url = "../benefitComponent/componentBenefitDefinitionsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitComponent')
		{
		var url = "../benefitComponent/componentBenefitGeneralInfoPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}		
		else if( printPageValue == 'benefitOverriddenNotesPrint')
		{			
	    var url = "../benefitComponent/componentBenefitNotesPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	
		else if(printPageValue == 'adminOptions')
		{
			var url = "../standardBenefit/adminOptionsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	
		else if(printPageValue == 'addQuestion'){
		
			var url = "../standardBenefit/addQuestionnairePrintIntermediate.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		// start of product
		else if(printPageValue == 'productGeneralInformation')	
		{		
			var printValueForGenInfo = printPageValue;
			var printValueForComAss = "";
			var printValueForNotes = "";
			var printValueForAdminOption = "";
			var printValueForDenial = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productGeneralInfoPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForGenInfo + "&&date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'productComponentAssociation')	
		{	
			var printValueForGenInfo = "";
			var printValueForComAss = printPageValue;
			var printValueForNotes = "";
			var printValueForAdminOption = "";
			var printValueForDenial = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productAssociatedComponentPrint.jsp"+getUrl()+"?printValueForComAss=" + printValueForComAss + "&&date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");	
		}
		else if(printPageValue == 'productBenefitAdministration')
		{
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productBenefitAdministrationPrintSubstitute.jsp"+getUrl()+"?date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		
		else if(printPageValue == 'productNotes')	
		{
			var printValueForGenInfo = "";
			var printValueForComAss = "";
			var printValueForNotes = printPageValue;
			var printValueForAdminOption = "";
			var printValueForDenial = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productNotesPrint.jsp"+getUrl()+"?printValueForNotes=" + printValueForNotes + "&&date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");					
		}else if(printPageValue == 'productAdminOption')	
		{
			var printValueForGenInfo = "";
			var printValueForComAss = "";
			var printValueForNotes = "";
			var printValueForAdminOption = printPageValue;
			var printValueForDenial = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productAdminOptionPrint.jsp"+getUrl()+"?printValueForAdminOption=" + printValueForAdminOption + "&&date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");					
		}else if(printPageValue == 'productDenialExclusion')	
		{
			var printValueForGenInfo = "";
			var printValueForComAss = "";
			var printValueForNotes = "";
			var printValueForAdminOption = "";
			var printValueForDenial = printPageValue;
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productRulesPrint.jsp"+getUrl()+"?printValueForDenial=" + printValueForDenial + "&&date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");					
		}
		else if(printPageValue == 'productAdministrationQuestion')	
		{
			var printValueForAdminQuestion = printPageValue;
			var url = "../product/productAddQuestionPrintSubstitute.jsp"+getUrl()+"?printValueForAdminQuestion=" + printValueForAdminQuestion + "&times=" + currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'productBenefitDefinitionPrint')	
		{
			var printValueForGenInfo = "";
			var printValueForBenDet = printPageValue;
			var printValueForNotes = "";
			var printValueForManInfo = "";
			var date = new Date();
			var curTime = date.getTime();
			var urlSubstitute = "../product/productBenefitDefinitonDetailedPrintSubstitute.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForBenDet=" + printValueForBenDet + "&&printValueForManInfo=" + printValueForManInfo + "&&printValueForNotes=" + printValueForNotes + "&&date=" + curTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		
		}else if(printPageValue == 'productBenefitGenInfoPrint')	
		{
			var printValueForGenInfo = printPageValue;
			var printValueForBenDet = "";
			var printValueForNotes = "";
			var printValueForManInfo = "";
			var date = new Date();
			var curTime = date.getTime();
			var urlSubstitute = "../product/productBenefitDefinitonDetailedPrintSubstitute.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForBenDet=" + printValueForBenDet + "&&printValueForManInfo=" + printValueForManInfo + "&&printValueForNotes=" + printValueForNotes + "&&date=" + curTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'productBenefitNotes')	
		{
			var printValueForGenInfo = "";
			var printValueForBenDet = "";
			var printValueForNotes = printPageValue;
			var printValueForManInfo = "";
			var date = new Date();
			var curTime = date.getTime();
			var urlSubstitute = "../product/productBenefitDefinitonDetailedPrintSubstitute.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForBenDet=" + printValueForBenDet + "&&printValueForManInfo=" + printValueForManInfo + "&&printValueForNotes=" + printValueForNotes + "&&date=" + curTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'productBenefitMandateInfoPrint')	
		{
			var printValueForGenInfo = "";
			var printValueForBenDet = "";
			var printValueForNotes = "";
			var printValueForManInfo = printPageValue;
			var date = new Date();
			var curTime = date.getTime();
			var urlSubstitute = "../product/productBenefitDefinitonDetailedPrintSubstitute.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForBenDet=" + printValueForBenDet + "&&printValueForManInfo=" + printValueForManInfo + "&&printValueForNotes=" + printValueForNotes + "&&date=" + curTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		 else if (printPageValue == 'contractgroupRuleView')
		{
			var date = new Date();
			var curTime = date.getTime();
    		var number = Math.random();
    		var ruleId = doc.getElementById("printRuleId").value;
    		var url = "../contract/groupRulePrint.jsp"+getUrl()+"?ruleId="+ruleId+"&date="+curTime+'&number='+number;
    		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
    	}
		//-----
		// end of product	
		// start of product structure
		else if(printPageValue == 'generalInfo')	
		{
			var url = "../productStructure/printProductStructureGenInfoBasic.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'benefitComp')	
		{
			var url = "../productStructure/printProdStructureBenefitComponent.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractProductAdmin')	
		{
			var url = "../contract/contractProductAdministrationSubstitutePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitGenInfo')	
		{
			var url = "../productStructure/printprodStructBenefitComponentGeneralInfo.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'prodStructAssocBenefitView')	
		{	
			var url = "../productStructure/printProdStructAssociatedBnftsView.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		
		else if(printPageValue == 'benefitDefinition')	
		{
			var url = "../productStructure/printProdStructureBenefitDefintion.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitGeneralInfo')	
		{
			var url = "../productStructure/printPrdStructureBenefitGeneralInfo.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'nonAdjudicate')	
		{
			var url = "../productStructure/printProdStructNonAdjudicateBenefitMandates.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		
		// end of product structure	 
		// start of product benefit component	
		else if(printPageValue == 'productComponentGeneralInfo')	
		{
			var printValueForGenInfo = printPageValue;
			var printValueForNotes = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productComponentGeneralInfoPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForGenInfo +  "&&printValueForNotes=" + printValueForNotes + "&&date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'productComponentNotes')	
		{
			var printValueForGenInfo = "";
			var printValueForNotes = printPageValue;
			var date = new Date();
			var curTime = date.getTime();
			var url = "../product/productComponentGeneralInfoPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
					printValueForGenInfo +  "&&printValueForNotes=" + printValueForNotes + "&&date=" + curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		// end of product benefit component
		else if(printPageValue == 'adminOption')
		{
		var url = "../adminoptions/adminOptionsPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"adminOptionsPrint","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

		}
		else if(printPageValue == 'adminOptionQuestion')
		{
		//var adminId = document.getElementsByName('hiddenAdminOptionId').value;
		//var adminName = document.getElementById('adminOptionViewForm:adminName').value;
		var url = "../adminoptions/adminOptionsQuestionsPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"adminOptionsQuestionPrint","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

		}
		else if(printPageValue == 'QuestionPrint')
		{
		var url = "../question/printQuestions.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"printQuestions","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

		}
		else if(printPageValue == 'mandatePrint')
		{
		var url = "../mandate/mandatePrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"printQuestions","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");

		}else if(printPageValue == 'notesGeneralInformation'){	
				
			var printValueForGenInfo = printPageValue;
			var printValueForDataDom = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../notes/notesEditPrint.jsp"+getUrl()+"?date="+ curTime+"&&printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom;
			
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'notesDataDomain'){

			var printValueForDataDom = printPageValue;
			var printValueForGenInfo  = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../notes/notesEditPrint.jsp"+getUrl()+"?printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom + "&&date=" + curTime;

			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
			
		}
		else if(printPageValue == 'productBenefitInfo')	
		{	
			var url = "../product/printProdAssociatedBnfts.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractProductGeneralInformation')
		{
			
			var printValueForGeneralInfo = "printValueForGeneralInfo";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../contract/contractProductBenefitComponentListPrint.jsp"+getUrl()+"?printValueForGeneralInfo="+ 
					printValueForGeneralInfo+ "&&date="+curTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
			
		}
		else if(printPageValue == 'notesGeneralInformationEdit'){	

			var printValueForGenInfo = printPageValue;
			var printValueForDataDom = "";
			var date = new Date();
			var curTime = date.getTime();
			var url = "../notes/notesPrintView.jsp"+getUrl()+"?date="+ curTime+"&&printValueForGenInfo=" + 
						printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom;

			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'notesDataDomainEdit'){
		
			if(navigationForPrint == 'true'){
				var message = "The selected datas may not be displayed in the print unless they are saved.Are you sure you want to proceed?"	
				if(window.confirm(message)){
					var printValueForDataDom = printPageValue;
					var printValueForGenInfo  = "";
					var date = new Date();
					var curTime = date.getTime();
					var url = "../notes/notesPrintView.jsp"+getUrl()+"?printValueForGenInfo=" + 
								printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom + "&&date=" + curTime;
		
					newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
				}else{
					return false;
				}
			}else{
				var printValueForDataDom = printPageValue;
				var printValueForGenInfo  = "";
				var date = new Date();
				var curTime = date.getTime();
				var url = "../notes/notesPrintView.jsp"+getUrl()+"?printValueForGenInfo=" + 
							printValueForGenInfo + "&&printValueForDataDom=" + printValueForDataDom + "&&date=" + curTime;
	
				newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");		
			}
		}else if((printPageValue == 'notesViewAllVersionsPrint')){
		var printValueForViewAllVersions = "notesViewAllVersionsPrint";
		var date = new Date();
		var curTime = date.getTime();	
		var url = "../notes/notesViewAllVersionsPrint.jsp"+getUrl()+"?date="+ curTime+"&&printValueForViewAllVersions=" + printValueForViewAllVersions;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");				
		}else if((printPageValue == 'notesSearchResultPrint'))
		{
			var url = "../notes/noteSearchResultPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractComponentGeneralInfo'){	
			var url = "../contract/contractBenefitComponentGeneralInfoPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'contractBenefitComponentNotePrint'){	
			var url = "../contract/benefitComponentNotesAttachmentPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractCoveragePrint'){	
			var urlSubstitute = "../contract/contractCoveragePrintSubstitute.jsp"+getUrl()+"?date="+currentTime;		
		    newWinForView =window.showModalDialog(urlSubstitute,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractStandardBenefitGeneralPrint'){	
			var url = "../contract/contractStandardBenefitGeneralInfoPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractBenefitNotesPrint'){	
			var url = "../contract/contractBenefitNotePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'contractBenefitAdminProductStructure')
		{
			var url = "../contract/contractBenefitAdministrationSubstitutePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'contractSearchResultPrint')
		{
		var url = "../contract/contractBasicSearchPrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'contractsearchresultprint')
		{
			var url = "../search/ContractSearchResultsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'productsearchresultsprint')
		{
			var url = "../search/ProductSearchResultsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'productstructuresearchresultsprint')
		{
			var url = "../search/ProductStructureSearchResultsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'benefitcomponentsearchresultsprint')
		{
			var url = "../search/BenefitComponentSearchResultsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'benefitsearchresultsprint')
		{
			var url = "../search/BenefitSearchResultsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	else if(printPageValue == 'benefitlevelsearchresultsprint')
		{
			var url = "../search/BenefitLevelSearchResultsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	else if(printPageValue == 'notessearchresultsprint')
		{
			var url = "../search/NotesSearchResultsPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}   else if(printPageValue == 'advancedsearchprint')
		{
			var url = "../search/advancedSearchPrintInter.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	  else if(printPageValue == 'basicsearchprint')
		{   
			var url = "../search/basicSearchPrintInter.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}	  else if(printPageValue == 'searchresultsummary')
		{
			var url = "../search/SearchResultsSummaryPrintInter.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}  else if(printPageValue == 'adminMethodOverridePrint')
		{
			var url = "../contract/adminMethodOverridePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'adminMethodOverridePrintsub')
		{	
			var url = "../contract/adminMethodOverridePrintSubstitute.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}		
		else if(printPageValue == 'adminOptionOverrideForContract')
		{
			var url = "../contract/adminOptionOverridePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'benefitComponentAdminMethod')
		{
			var url = "../benefitComponent/adminMethodOverridePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'productStructureAdminMethod')
		{
			var url = "../productStructure/adminMethodOverridePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'productStructureAdminOption')
		{
			var url = "../productStructure/prodStructureBenefitAdministrationOptionPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'productAdminMethod')
		{
			var url = "../product/adminMethodOverridePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'benefitAdminMethod')
		{
			var url = "../adminMethods/adminMethodPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'adminOptionOverrideForProduct')
		{
			var url = "../product/adminOptionOverridePrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'benefitComponentAdminOption')
		{
			var url = "../benefitComponent/componentBenefitAdministrationOptionPrintInter.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitMandateInfo')
		{
			var url = "../contract/contractMandateInformationPrint.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitAdminProductStructure'){
			var url = "../productStructure/printProdStructureBenefitAdministration.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'contractBenefitCustomizationPrint'){
		    var url = "../contract/printContractBenefitCustomization.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'spsMappingPrint'){
            var url = "../blueexchange/printSPSMapping.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }else if(printPageValue == 'customMessageTextViewPrint'){
            var url = "../blueexchange/customMessageTextPrint.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }	else if(printPageValue == 'productRuleValidation'){
            var url = "../product/productRuleValidationPrint.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }else if(printPageValue == 'productUniqueReferenceValidation'){
            var url = "../product/productUniqueReferenceValidationPrint.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }else if(printPageValue == 'contractUniqueReferenceValidation'){
            var url = "../contract/referenceValidationPrintPopup.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }else if(printPageValue == 'contractTransferLogPrint'){
         	var status =document.getElementById('ContractTransferLogViewForm:logPageStatus').value;
			var url = "../contract/contractViewTransferLogPrint.jsp"+getUrl()+"?date="+currentTime+'&status='+status ;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");			
		 }else if(printPageValue == 'adminOptionUniqueReferenceValidation'){
            var url = "../adminoptions/adminOptionUniqueReferenceValidationPrint.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }else if(printPageValue == 'contractRuleValidation'){
            var url = "../contract/contractRuleValidationPrint.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }else if(printPageValue == 'viewServiceTypeCodeMappingPrint'){
            alert("Print not available");
         }else if(printPageValue == 'adminMethodValidation'){
            alert("Print not available");
         }	
		else if(printPageValue == 'adminMethodPopup'){			
            var url = "../popups/adminMethodPrintPopup.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'adminOptionQuestionnaire'){
		var url = "../product/viewProductQuestionnairePrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'benefitAdminOptionQuestionnaire'){
		var url = "../standardBenefit/viewQuestionnairePrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'contractAdminOptionQuestionnaire'){
		var url = "../contract/viewQuestionnairePrint.jsp"+getUrl()+"?date="+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'printIndicativeMappingPage'){
		var url = "../indicativemapping/indicativemappingprint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,param,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'printEditIndicativeMappingPage'){
		var url = "../indicativemapping/editindicativemappingprint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'printEditReferenceMappingPage'){
		
		var url = "../referencemapping/editReferenceMappingPrint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'printReferenceMappingViewPage'){
		var url = "../referencemapping/referencemappingprint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,param,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'adminMethodMappingEditPrint'){
		
		var url = "../adminmethodmapping/adminMethodMappingEditPrint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,param,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'adminMethodEditPrint'){
		
		var url = "../adminMethodMaintain/adminMethodEditPrint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,param,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'printAdminMethodViewPage'){
		var url = "../adminMethodMaintain/adminMethodPrint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,param,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		else if(printPageValue == 'printAdminMethodMappingViewPage'){
		var url = "../adminmethodmapping/adminMethodMappingPrint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,param,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}
		
		else if(printPageValue == 'uncodedNotesValidation'){
            var url = "../contract/unCodedNotesPrintPopupSubs.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }
         else if(printPageValue == 'ruleTypeContractValidation'){
            var url = "../contract/ruleTypeValidationContractPrintPopup.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }
          else if(printPageValue == 'ruleTypeProductValidation'){
            var url = "../product/ruleTypeValidationProductPrintPopup.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }
         
		
		else if(printPageValue == 'prinReferencePage'){
		var url = "../indicativemapping/editindicativemappingprint.jsp"+getUrl()+"?date="+currentTime;
	    var param = new Object();
	    param.parentWindow = window;
		newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}else if(printPageValue == 'uncodedNotesValidation'){
            var url = "../contract/unCodedNotesPrintPopup.jsp"+getUrl()+"?date="+currentTime;
            newWinForView =window.showModalDialog(url,doc,"dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
         }
		else if(printPageValue == 'productBenefitAdministration')
		{
			var date = new Date();
			var url = "../product/productBenefitAdministrationPrintSubstitute.jsp"+getUrl()+"?date="+currentTime;
			newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:950px;resizable=false;status=no;");
		}		
	}
	else
	{
		alert("Print not available");
	}
}
	/*Method for ReferenceData Lookup PopUp*/
	function getSelectedReasoncode(url, formName, targetDiv, targetHidden, attrCount, attrPosn, parentCatalog){
		
		url = url + '?parentCatalog='+parentCatalog+'&lookUpAction=1';
		//return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		var hiddenId = targetHidden;
		var param = new Object();
		param.parentWindow = window;
		param.hiddenId = hiddenId;
		param.selectedValues = getObj(hiddenId).value;
		
	
		// Calling popup window.
		var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
		// Cancel action from popup. No need to do anything.
		if( retValue == undefined ) {
			return false;
		}
		// Setting the value from popup to hidden field
		var hiddenValueObj = getObj(hiddenId);
	
		
		
		hiddenValueObj.value = retValue;
		
		var textValue = retValue;
		var targetDivElement = getObj(targetDiv);
		if(retValue == null || retValue == '' || retValue == 'undefined' || retValue == undefined){
			targetDivElement.innerText = '';
		}else{
			targetDivElement.innerText = retValue;
		}
		return true;
		
	 }

	/*Method for ReferenceData Lookup PopUp*/
	function getSelectedDomainReferenceData(url, formName, busUnitHidden, lobHidden, beHidden, bgHidden, targetDiv, targetHidden, attrCount, attrPosn, parentCatalog){
		var lob = '', be = '', bg = ''; bu= '';
	 	lob = getObj(formName+':'+lobHidden).value;
	 	be = getObj(formName+':'+beHidden).value;
	 	bg = getObj(formName+':'+bgHidden).value;	 	
	 	if('' != busUnitHidden){
		 	bu = getObj(formName+':'+busUnitHidden).value;
		}
		lob = escapeAmpersandSpecialCharacterForRefDataLookUp(lob);
		be = escapeAmpersandSpecialCharacterForRefDataLookUp(be);
		bg = escapeAmpersandSpecialCharacterForRefDataLookUp(bg);
		if('' != busUnitHidden){
			bu = escapeAmpersandSpecialCharacterForRefDataLookUp(bu);
		} 	
		
		if((null != lob && lob != '') && (null != be && be != '') && (null != bg && bg != '') && (null == bu || bu == '')){
			url = url +getUrl()+ '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}else if((null != lob && lob != '') && (null != be && be != '') && (null != bg && bg != '') && (null != bu && bu != '')){
			url = url +getUrl()+ '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg+'&bu='+bu;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}else{
				url = url +getUrl()+ '?parentCatalog='+parentCatalog+'&lookUpAction=1';
				return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}
	 }
	 /*Method for Benefit Term Qualifier PopUp*/
	function getSelectedBenefitQualifierDomainData(url, formName, lobHidden, beHidden, bgHidden, targetDiv, targetHidden, attrCount, attrPosn, parentCatalog){
		var lob = '', be = '', bg = '';
	 	lob = getObj(formName+':'+lobHidden).value;
	 	be = getObj(formName+':'+beHidden).value;
	 	bg = getObj(formName+':'+bgHidden).value;
	 
		lob = escapeAmpersandSpecialCharacterForRefDataLookUp(lob);
		be = escapeAmpersandSpecialCharacterForRefDataLookUp(be);
		bg = escapeAmpersandSpecialCharacterForRefDataLookUp(bg);
		
		if((null != lob && lob != '') && (null != be && be != '') && (null != bg && bg != '')){
			url = url +getUrl()+ '?lookUpAction=12'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg+'&titleName=Qualifier Popup';
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}else{
				url = url +getUrl()+ '?parentCatalog='+parentCatalog+'&lookUpAction=1';
				return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}
	 }
	 /*Method for Benefit Term Qualifier PopUp*/
/*	function getSelectedContractPricingData(url, formName, lobHidden, beHidden, bgHidden, targetDiv, targetHidden, attrCount, attrPosn, parentCatalog){
		var lob = '', be = '', bg = '';
	 	lob = getObj(formName+':'+lobHidden).value;
	 	be = getObj(formName+':'+beHidden).value;
	 	bg = getObj(formName+':'+bgHidden).value;
	 
		lob = escapeAmpersandSpecialCharacterForRefDataLookUp(lob);
		be = escapeAmpersandSpecialCharacterForRefDataLookUp(be);
		bg = escapeAmpersandSpecialCharacterForRefDataLookUp(bg);
		
		if((null != lob && lob != '') && (null != be && be != '') && (null != bg && bg != '')){
			url = url + '&lookUpAction=13'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}else{
				url = url + '?parentCatalog='+parentCatalog+'&lookUpAction=1';
				return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}
	 }
*/	 
	 /*Method for Benefit Term Qualifier PopUp*/
	function getSelectedContractPricingData(url, formName, lobHidden, beHidden, bgHidden, mbuHidden, targetDiv, targetHidden, attrCount, attrPosn, parentCatalog){
		var lob = '', be = '', bg = '';
	 	lob = getObj(formName+':'+lobHidden).value;
	 	be = getObj(formName+':'+beHidden).value;
	 	bg = getObj(formName+':'+bgHidden).value;
	 	mbu = getObj(formName+':'+mbuHidden).value;
	 		 
		lob = escapeAmpersandSpecialCharacterForRefDataLookUp(lob);
		be = escapeAmpersandSpecialCharacterForRefDataLookUp(be);
		bg = escapeAmpersandSpecialCharacterForRefDataLookUp(bg);
		mbu = escapeAmpersandSpecialCharacterForRefDataLookUp(mbu);
		
		if((null != lob && lob != '') && (null != be && be != '') && (null != bg && bg != '') && (null != mbu && mbu != '')){
			url = url + '&lookUpAction=13'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg+'&mbu='+mbu;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}else{
				url = url +getUrl()+ '?parentCatalog='+parentCatalog+'&lookUpAction=1';
				return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}
	 }
	 
	 /*To be deleted*/
	var refDataUseFlag = 'no';
	var refDataVariables = '';

	/* Method to set the flag value to 'yes' if reference data has been selected in a page*/
	function setRefDataUseFlag(formName, elementId, divName){
		var refDataSelectedValue = document.getElementById(formName+':'+elementId).value;
		
		if(refDataSelectedValue != null && refDataSelectedValue != ''){
			//refDataUseFlag = 'yes';
			if(refDataVariables == ''){
				refDataVariables = elementId + '~' + divName;
			}else{
				refDataVariables = refDataVariables + '~' + elementId + '~' + divName;
			}
		}
	}
		
	function appendToRefDataVariablesSelectedRefDataName(elementId, divName){
		var elementVal = document.getElementById(divName).innerHTML;
		if(elementVal != ''){
			if(refDataVariables == ''){
				refDataVariables = elementId + '~' + divName;
			}else{
				refDataVariables = refDataVariables + '~' + elementId + '~' + divName;
			}
		}
	}
		
	/*Method to alert the user when the business domain is changed after selecting reference
      data for that business domain*/
	function clearRefaDataFieldOnBDChange(formName, oldValue, newValue){
		var oldModValue = document.getElementById(formName+':'+oldValue).value
		var newModValue = document.getElementById(formName+':'+newValue).value
//		alert("oldModValue"+oldModValue+"newModValue"+newModValue)
		//if(refDataUseFlag == 'yes'){
			if (refDataVariables != ''){
				var array = new Array();
				array = refDataVariables.split("~");
				for(var i = 0 ; i < array.length ; i=i+2){
					if(oldModValue != newModValue ){
						document.getElementById(formName+':'+array[i]).value = '';
						document.getElementById(array[i+1]).value = '';
						copyHiddenToDiv_ewpd(formName+':'+array[i],array[i+1],2,2); 
					}
				}
			//}
		}
		
	}
	
	/*Method to escape '&' when being send as request parameter along with url for ReferenceData Lookup*/
	function escapeAmpersandSpecialCharacterForRefDataLookUp(objectValue){
		var value = '';
		if(objectValue.indexOf('&') >= 0){
			var array = objectValue.split('&');
			if(array.length > 0){
				for(var i = 0 ; i < array.length ; i++){
					if(i == (array.length-1)){
						value = value + array[i] ;
					}else{
						value = value + array[i] + '/a';
					}
				}
			}
		}else {value = objectValue;}
		
		return value;
	
	}

				

// **Function to allow only numbers**
function isNum(){
	
	ie = (document.all) ? 1 : 0;
	n = !ie;
	
	//function isNum(){
	document.onkeypress = keyDown;
	if (n) {
	    document.captureEvents(Event.KEYPRESS);
	}
	//}
	
	function keyDown(e) {
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return true;

	    if (keycode < 48 || keycode > 57)
	    {
	        return false;
	    }
	}
}

function isNum1(obj){
	ie = (document.all.obj) ? 1 : 0;
	n = !ie;
	
	//function isNum(){
	document.onkeypress = keyDown;
	if(!document.all) {
	    document.captureEvents(Event.KEYPRESS);
	}
	//}
	
	function keyDown(e) {
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return true;

	    if (keycode < 48 || keycode > 57)
	    {
	        return false;
	    }
	}
}
function dummy(){
	document.onkeypress = keyDown;
	function keyDown(){
	}
}

var IGNORED_FIELD1 = "";
var IGNORED_FIELD2 = "";
var IGNORED_FIELD3 = "";
var IGNORED_FIELD4 = "";
var IGNORED_FIELD5 = "";
var IGNORED_FIELD6 = "";
var IGNORED_FIELD7 = "";
var IGNORED_FIELD8 = "";
/* Start Code for checking modified data in the form  */

/* Called on load of the page */
function getModifiedDataOnLoad()
{
	return getJoinElementsValueByTagNames('input,select,textarea');
}
/* Called when user navigate to another page */
function getModifiedDataOnUnLoad()
{
	if(document.ewpdDataChangedForm.ewpdOnloadData.value == '' )
		return '';
	else
		return getJoinElementsValueByTagNames('input,select,textarea');
		
}

/**
 * Use this method to find out data is modifed in the form.
 * true -  data is modified
 * false  - data is not modified
 *
 * Important before using this method 
 * Please include the following line of code in all pages 
	
	//<form name="ewpdDataChangedForm">
	//<input type="hidden" name="ewpdOnloadData" value="" />
	//<script>
	//document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
	//<script> //closing tag
	//<form>	//closing tag
 *
 */
function isEwpdbDataModifed()
{
// checks the folling bit of code in all the pages
// so dont forget to include this all the pages

//<form name="ewpdDataChangedForm">
//<input type="hidden" name="ewpdOnloadData" value="" />
//<script>
//document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
//<script> //closing tag
//<form>	//closing tag

	if(document.ewpdDataChangedForm.ewpdOnloadData == 'undefined')
	{
		alert("Cannot check for modified data... \nPossible fix, include unsavedDataFinder.html ");
		return true;
	}
	else{
		return (document.ewpdDataChangedForm.ewpdOnloadData.value != getModifiedDataOnUnLoad());
		}
}

/* Returns value of all the object in the form */
function getJoinElementsValueByTagNames(list) 
{
	var tagNames = list.split(',');
	var resultArray = new Array();
	var dataOnScreen = "";
	var tagType = "";
	for (var i=0;i<tagNames.length;i++) 
	{
		//alert(tagNames[i]);
		var tags = document.getElementsByTagName(tagNames[i]);
		for (var j=0;j<tags.length;j++) 
		{
			tagType = tags[j].type;
			if(tagType == 'radio' || tagType == 'checkbox')
			{
				if(tags[j].checked == true)
				{			
					//dataOnScreen += tags[j].value;
					dataOnScreen += tags[j].value + '-' + tags[j].name;
				}
			}
			//	document.write(tags[j].name + " = " + tags[j].value + "</br>");
			if(tags[j].name == IGNORED_FIELD1 || tags[j].name == IGNORED_FIELD2 || tags[j].name == IGNORED_FIELD3 
					|| tags[j].name == IGNORED_FIELD4 || tags[j].name == IGNORED_FIELD5 || tags[j].name == IGNORED_FIELD6
					|| tags[j].name == IGNORED_FIELD7 || tags[j].name == IGNORED_FIELD8 
					|| tags[j].name == '__LINK_TARGET__'|| tags[j].name == 'serverTree:org.apache.myfaces.tree.NAV_COMMAND')
			{
			//alert('true');
			}
			else if(tags[j].name != "ewpdOnloadData") {
				
					dataOnScreen += tags[j].value + '-' + tags[j].name;
				
			}
			
		}
		
	}
	return dataOnScreen;
}
/* End Code for checking modified data in the form  */
/* confirmNavigationWithOutSave */
function navigatePageActionSubmit(objectId)
{
	var obj = document.getElementById(objectId);
	if(isEwpdbDataModifed())
	{
	
		var retValue = confirm("Are you sure you want to navigate away from this page? \nAll unsaved changes will be lost.  Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			obj.onclick();
		}
	}else{
		obj.onclick();
//		return true;		
	}
	return false;
}

function navigatePageAction(objectId)
{

	var obj = document.getElementById(objectId);
	if(isEwpdbDataModifed())
	{
	//alert(document.ewpdDataChangedForm.ewpdOnloadData.value +"!="+getModifiedDataOnUnLoad());
		var retValue = confirm("Are you sure you want to navigate away from this page? \nAll unsaved changes will be lost.  Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			obj.onclick();
		}
	}
	return false;
}
function savePageAction(objectId){
	var obj = document.getElementById(objectId);
	if(!isEwpdbDataModifed())
	{
		alert('No modifications to be updated.');
	}
}

function savePageActionForDataTable(objectId, formName){

	var obj = document.getElementById(objectId);
	if(!isEwpdbDataModifed() && document.getElementById(formName+':hiddenValueChanged').value == 0)
	{
		alert('No modifications to be updated.');
	}
	
}

function savePageActionForNotesPage(objectId, formName){
	var obj = document.getElementById(objectId);
	var version = document.getElementById('notesCreateForm:versionHidden').value;
	if(version < 1){
		if(document.getElementById('notesCreateForm:noteNameHidden').value == document.getElementById('notesCreateForm:txtNoteNm').value 
			&& document.getElementById(formName+':hiddenValueChanged').value == 0 
			&& document.getElementById(formName+':hiddenTypeValueChanged').value == 0 
			&& document.getElementById(formName+':systemDomainTxtHiddenOld').value == document.getElementById(formName+':systemDomainTxtHidden').value){
				alert('No modifications to be updated.');
		}
	}else{
		if(document.getElementById(formName+':hiddenValueChanged').value == 0 && document.getElementById(formName+':systemDomainTxtHiddenOld').value == document.getElementById(formName+':systemDomainTxtHidden').value ){
			alert('No modifications to be updated.');
		}
	}
	
}

function validateChars(id,validationMessage){
	var iChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()-_+={}[]|\\:;\" \'<,>.?/"; 
	var vNewLine = '\n';
	var rNewLine = '\r';
	var newLineCheck;
	var splCharCheck;
  	for (var i = 0; i < document.getElementById(id).value.length; i++) {
  		newLineCheck = document.getElementById(id).value.charAt(i);
  		splCharCheck = iChars.indexOf(document.getElementById(id).value.charAt(i));
		if((newLineCheck == vNewLine || newLineCheck == rNewLine) && splCharCheck == -1)
	 	{
	  	}
		else if(splCharCheck == -1)
  		{
  			alert(validationMessage);
  			return false;
  		}

  	}
	return true;
}

	function enableDisableDelete(taleId, ColumnNo, childNum, deleteButtonId) {
		var chk = false;
		if(document.getElementById(taleId) != null &&
			document.getElementById(taleId).rows.length > 0) {

			var rows = document.getElementById(taleId).rows;
			for(var i=0; i<rows.length; i++) {
				if(rows[i].cells[ColumnNo].children[childNum].checked) {
					chk = true;
					break;
				}
			}
		}
		if(chk) {
			document.getElementById(deleteButtonId).disabled = false;
		} else {
			document.getElementById(deleteButtonId).disabled = true;
		}
	}
	

	
	
	var newWinForReferenceValidation;
	function goToLink(link) {
		if(link == 'NO LINK')
			alert('There is no link attached to this icon');
		else{
			if ( null != newWinForReferenceValidation && !newWinForReferenceValidation.closed ) {
            	newWinForReferenceValidation.close();
			} 
			else if(undefined == newWinForReferenceValidation){
				newWinForReferenceValidation = window.open();
				newWinForReferenceValidation.close();
				}
			
				if(link.split("?")!='undefined' && link.split("?").length>1){
				var splitLink=new Array();
				splitLink = link.split("?");	
                var genLink = splitLink[0]+getUrl()+'?'+splitLink[1];
				newWinForReferenceValidation = window.open(genLink,'ViewDateSegments',',status=no,toolbar=no,scrollbars=no,width=1200,height=800,resizable=no,left=50,top=25');
				}else{
				newWinForReferenceValidation = window.open(link+getUrl(),'ViewDateSegments',',status=no,toolbar=no,scrollbars=no,width=1200,height=800,resizable=no,left=50,top=25');
				}
		}
	}
	
   function getSelectedSpsMappingData(url, formName, lobHidden, beHidden, bgHidden, targetDiv, targetHidden, attrCount, attrPosn, parentCatalog){
		var lob = '', be = '', bg = '';

	 	lob = getObj(formName+':'+lobHidden).value;
	 	be = getObj(formName+':'+beHidden).value;
	 	bg = getObj(formName+':'+bgHidden).value;
	 
		lob = escapeAmpersandSpecialCharacterForRefDataLookUp(lob);
		be = escapeAmpersandSpecialCharacterForRefDataLookUp(be);
		bg = escapeAmpersandSpecialCharacterForRefDataLookUp(bg);
		 	
		
		if((null != lob && lob != '') && (null != be && be != '') && (null != bg && bg != '')){
			url = url + getUrl()+'?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}else{
				url = url +getUrl()+ '?parentCatalog='+parentCatalog+'&lookUpAction=1';
				return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		}
	 }
	 
	 function getMultipleSelectedItemsFromDataTable(taleId,targetColNo,targetChildNo, ColumnNo, childNum, hiddenId) {
		
		var list='';
		if(document.getElementById(taleId) != null &&
			document.getElementById(taleId).rows.length > 0) {

			var rows = document.getElementById(taleId).rows;
			for(var i=0; i<rows.length; i++) {
				if(rows[i].cells[ColumnNo].children[childNum].checked) {
					if(list != '')
					list+='~';
					list+=rows[i].cells[targetColNo].children[targetChildNo].value;
					
				}
			}
		}

		if(document.getElementById(hiddenId) != null ){
			document.getElementById(hiddenId).value = list;
		}
	}
	/* Function for date validation using java script
	 * input parameter is formname:textfield
	 */
	function checkForValidDate(Name){
		var dt=document.getElementById(Name);
		if(null != dt){
			var no = isDate(dt.value)	//function to check the input date 
			if (no == 1 || no == 2){
				dt.focus();
				return no;
			}
		    return no;
		}else{
			return false;
		}
 	}
 	/* Function for checking whether the input is an integer or not
	 */
 	function isInteger(s){
		var i;
	    for (i = 0; i < s.length; i++){   
	        // Check that current character is number.
	        var c = s.charAt(i);
		        if (((c < "0") || (c > "9"))) return false;
	    }
	    // All characters are numbers.
	    return true;
	}
	function stripCharsInBag(s, bag){
		var i;
	    var returnString = "";
	    // Search through string's characters one by one.
	    // If character is not in bag, append to returnString.
	    for (i = 0; i < s.length; i++){   
	        var c = s.charAt(i);
	        if (bag.indexOf(c) == -1) returnString += c;
	    }
	    return returnString;
	}
	/* Function for checking the dates of february
	 */
	function daysInFebruary (year){
		// February has 29 days in any year evenly divisible by four,
	    // EXCEPT for centurial years which are not also divisible by 400.
	    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) {
		for (var i = 1; i <= n; i++) {
			this[i] = 31
			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
			if (i==2) {this[i] = 29}
	   } 
	   return this
	}
	/* Function for checking the date. Inorder to display different error messages in the page, 
		the return type is given as a number. Alert messages can also be given instead.
	 */
	function isDate(dtStr){
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf("/")
		var pos2=dtStr.indexOf("/",pos1+1)
		var strMonth=dtStr.substring(0,pos1)
		var strDay=dtStr.substring(pos1+1,pos2)
		var strYear=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){
			//alert("The date format should be : mm/dd/yyyy")
			return 4;//to display as an error message, this number is taken to the page and the message is shown accordingly.
		}
		if (strMonth.length<1 || month<1 || month>12){
			//alert("Invalid date")
			return 1;//to display as an error message, this number is taken to the page and the message is shown accordingly.
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			//alert("Invalid date")
			return 1;//to display as an error message, this number is taken to the page and the message is shown accordingly.
		}
		if (strYear.length != 4 || year==0 || year<1900 || year>9999){
			//alert("Please enter a valid 4 digit year between "+1900+" and "+9999)
			return 2;//to display as an error message, this number is taken to the page and the message is shown accordingly.
		}
		if (dtStr.indexOf("/",pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, "/"))==false){
			//alert("Invalid date")
			return 1;//to display as an error message, this number is taken to the page and the message is shown accordingly.
		}
		return 3;//to display as an error message, this number is taken to the page and the message is shown accordingly.
	}
	
	//AJAX javascript
	
	// Global declarations.
	var req;
	var divObject = new Object();
	var tableIdentifier = new Object();
	var errorMsgDivObj = new Object();
	var arrayObj = new Array(3);
	var checkboxIdentifier = new Object();
	var msgObject = new Object();
	
// Function used to send AJAX request using GET Method.
	function ajaxCall_GET(url,searchField,divObj,tableId,errorMsgDiv,attrArray) {
		//Assign the input parameters to the gloabl objects.
		divObject = divObj;
		tableIdentifier = tableId;
		errorMsgDivObj = errorMsgDiv;
		arrayObj = attrArray;
		//AJAX request.
	   if (window.XMLHttpRequest) {
	       req = new XMLHttpRequest();
	   } else if (window.ActiveXObject) {
	       req = new ActiveXObject("Microsoft.XMLHTTP");
	   }
	   req.open("GET", (url+"&searchString="+ escape(searchField.value)), false);
	   req.onreadystatechange = callbackAJAX;
	   req.send(null);
	}
	
	// Function that sends AJAX request using POST method.
	 function ajaxCall(url,searchField,divObj,tableId,errorMsgDiv,attrArray,errorTableId,checkboxId) {
            //Assign the input parameters to the gloabl objects.
            divObject = divObj;
            tableIdentifier = tableId;
            errorMsgDivObj = errorMsgDiv;
            arrayObj = attrArray;    
            errorTableIdentifier = errorTableId;
			checkboxIdentifier = checkboxId;
         //AJAX request.
         if (window.XMLHttpRequest) {
             req = new XMLHttpRequest();
         } else if (window.ActiveXObject) {
             req = new ActiveXObject("Microsoft.XMLHTTP");
         }
         attrArray['searchString'] = searchField.value;       
         req.open("POST", url,false);    
         req.onreadystatechange = callbackAJAX;
         req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
         var encodedParams = encodeFormData(attrArray);
         req.send(encodedParams);
      }     



	// Function that sends AJAX request using POST method with message.
	 function ajaxCall1(url,searchField,divObj,tableId,errorMsgDiv,attrArray,errorTableId,checkboxId,val) {
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
      
      	// Function that sends AJAX request using POST method with message.
	 function ajaxCallForSingleSelect(url,searchField,divObj,tableId,errorMsgDiv,attrArray,errorTableId,checkboxId,val) {
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
         req.onreadystatechange = callbackAJAXWithMessageForSingleSelect;
         req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
         var encodedParams = encodeFormData(attrArray);
         req.send(encodedParams);
      }   
      
      
      
      function ajaxCallForQualifier (url,searchField,divObj,tableId,errorMsgDiv,attrArray,errorTableId,checkboxId,val) {
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
         req.onreadystatechange = callbackAJAXWithMessageForQualifier;
         req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
         var encodedParams = encodeFormData(attrArray);
         req.send(encodedParams);
      }     
      
      // Encoding the values
      // Space will be encoded as %20 so it needs to be handled seperately
      function encodeFormData(data) {
            var pairs = [];
            var regexp = /%20/g;
            for(var name in data) {
                  var value = data[name].toString();
                  var pair = encodeURIComponent(name).replace(regexp, "+") + '=' +
                        encodeURIComponent(value).replace(regexp, "+");
                  pairs.push(pair);
            }
            return pairs.join('&');
      }
	
	
	//Function that is called after AJAX request is processed.
	function callbackAJAX() {
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
					//Apply style to the table.					
					if(null != tableObject){
							tigra_tables(tableIdentifier,0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');						
							var msgvalue=document.getElementById('infoDivMessage');
							if(null != msgvalue){
								var formName = tableIdentifier.split(':')[0];
								var isRecordsGrtThanMaxSize=document.getElementById(formName+':recordsGreaterThanMaxSize');		
							if(null !=  isRecordsGrtThanMaxSize  && isRecordsGrtThanMaxSize != "undefined" && isRecordsGrtThanMaxSize.value == 'true'){
	    		                msgvalue.innerHTML = "<li id='infoItem'><b>"+ "Search results exceeded 50 records. Please refine the search criteria and try again." + "</b></li>";  
	            		        msgvalue.className = ' infoDivForPopup';		            		        
							}else {								
								msgvalue.innerHTML = "";  
	            		        msgvalue.className = "";										
							}
							}
						}						
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
					
					// calling method to enable check box with  selected  values
					matchCheckBox();
										
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
	
	
	
	//Function that is called after AJAX request is processed.
	function callbackAJAXWithMessageForQualifier() {

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
				
					

					
					//Apply style to the table.
					if(null != tableObject)
						tigra_tables(tableIdentifier,0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
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
					

					// calling method to enable check box with  selected  values
					matchCheckBox();
					isCheckedAll();
										
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
				
					

					
					//Apply style to the table.
					if(null != tableObject){
						tigra_tables(tableIdentifier,0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
						//WAS 6.0 Migration Changes - Alignment issue in filter popup screens having only 2 columns,Width set to 100% when column length is 2
						for(var i = 0; i<tableObject.rows.length; i++){								
								if(tableObject.rows[0].cells.length==2){
									tableObject.rows[i].cells[1].style.width="100%";
								}							
						}
					}
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

					// calling method to enable check box with  selected  values
					matchCheckBox();
										
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
	
	//Function that is called after AJAX request is processed.
	function callbackAJAXWithMessageForSingleSelect() {
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
				
					

					
					//Apply style to the table.
					if(null != tableObject)
						tigra_tables(tableIdentifier,0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
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

					// calling method to enable check box with  selected  values
					matchSingleSelect();
										
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
	
	//  method for enable check box with  selected  values
	function matchCheckBox(){
	matchCheckboxItems_ewpd(tableIdentifier,window.dialogArguments.selectedValues,arrayObj[0],arrayObj[1],arrayObj[2]);
	}
		
	function getTreeForm(){
		var divObj = document.getElementById("aazone.treeZone");	
		
		if(divObj !=null && divObj.getElementsByTagName("a")[0] !=null){		   
	    	var id = divObj.getElementsByTagName("a")[0].id;
	    	return id.split(':')[0];
	    }
	}	
		
	function ajaxTreeCall() {
	   	var treeFormName = getTreeForm();	
		ajaxAnywhere.formName = treeFormName;
		ajaxAnywhere.substituteFormSubmitFunction();
		ajaxAnywhere.isFormSubmitByAjax = function() {
			//WAS 6.0 Migration Changes  -- Commented LINK TARGET which is not Supported by WAS7.0
		//var component =document.forms[treeFormName]['__LINK_TARGET__'].value;
		//	var component = document.getElementById('__LINK_TARGET__');
			var component
		var divArray = document.getElementsByTagName("div");
			for (var i = 0; i<divArray.length; i++){
				if (divArray[i].className =='treeDiv'){					
					var obj =divArray[i];											
					var rX = obj.scrollLeft;
					var rY = obj.offsetTop; 
					var zIndex = obj.style.zIndex;
					var height =obj.offsetHeight; // obj.scrollHeight; 			
					var width = obj.offsetWidth;					
					createCoveringDiv(rX,rY,zIndex,height,width);											
				}
			}
			//WAS 6.0 Migration Changes  -- Commented LINK TARGET which is not Supported by WAS7.0 
			if(null != document.forms[treeFormName]){
			      for (var i = 0; i<document.forms[treeFormName].elements.length; i++){
			            if(null != document.forms[treeFormName].elements[i].name 
			                  && document.forms[treeFormName].elements[i].name 
			                        == 'serverTree:org.apache.myfaces.tree.NAV_COMMAND'){
			                        return true;
			            }
			      }
			   }  
			if(component && component.substring(component.lastIndexOf(':'))== ':t2g')
				return true;
			return false;

		if(component && component.substring(component.lastIndexOf(':'))== ':t2g')
			return true;
		return false;
	}
	ajaxAnywhere.onAfterResponseProcessing = function() {		
		hideCoveringDiv();
	}
	
	
}

		function createCoveringDiv(rX,rY,zIndex,height,width) {
				// Create a new Div to Hide Tree..
				var coverDiv = document.createElement('div');				
				coverDiv.id = 'coverDivPage';		
				coverDiv.style.background= 'white';
				coverDiv.style.display = "block"; 
				coverDiv.style.left = rX + "px";
				coverDiv.style.top = rY + "px";
				coverDiv.style.zIndex=zIndex+1; 				
				coverDiv.style.height = document.body.clientHeight-150 ;    
				coverDiv.style.width = width+"px";			
				coverDiv.style.position = 'absolute';	
				coverDiv.style.cursor = 'wait';	
				coverDiv.style.opacity = 00;
				coverDiv.style.filter = 'alpha(opacity=' + 00 + ')';			
				document.body.appendChild(coverDiv);
				
			
		}
			
		function hideCoveringDiv() {
				var coverDiv = document.getElementById('coverDivPage');	
			
				if (coverDiv != null && coverDiv!= undefined ) {					
					coverDiv.style.cursor = 'default';
					coverDiv.style.display = "none";
					coverDiv.removeNode(true);					
				}
				hideBorderImage();
		}
		//WAS 6.0 Migration Changes  -- Added a Method for hiding border Image 
		function hideBorderImage(){
			var images,i;
			
			 images=document.getElementsByTagName("img");
			 if (images != null && images!= undefined ) {	
			 for(i in images){
				 images[i].className = "treeNodeImage_WithNoBorder";
			 }
			 }
			 return true;
		}
	function getScroll()
		{			
	    	var treeFormName = getTreeForm();	
			var divArray = document.getElementsByTagName("div");			
			for (var i = 0; i<divArray.length; i++){
				if (divArray[i].className =='treeDiv'){
					var top =divArray[i].scrollTop;
					var hidden = treeFormName+':tree2:scrollTop';
					document.getElementById(hidden).value = top;
				
				}
			}
		return true;
	}	

	var loadScrollTopTimeOutCounter =0;	

	function loadScollTop(){	
	
		var treeFormName =  getTreeForm();	
		var divArray = document.getElementsByTagName("div");
		for (var i = 0; i<divArray.length; i++){
			if (divArray[i].className=='treeDiv'){				
				divArray[i].style.height = document.body.clientHeight-150;			
				// Check if the page is loaded.Only if loades set the scrollVal .Or else time out for 200 and Call the function again.
				if ( ( null == divArray[i].scrollTop ) && loadScrollTopTimeOutCounter <10  ) {
					// Counter waits only till 2sec.
					loadScrollTopTimeOutCounter = loadScrollTopTimeOutCounter +1 ;	
					setTimeout ( "loadScollTop()", 200);
				}		
				var hidden = treeFormName+':tree2:scrollTop';			
				var scrollVal = document.getElementById(hidden).value ;

				if(null != scrollVal && '' != scrollVal.trim)	{
				 	divArray[i].scrollTop = scrollVal;
				}
			}
		}		
	}
	function changeId(objId){
      var targetId = objId.id;      
      if((objId.id).indexOf(":")!=-1){
            var target = targetId.split(':');
            objId.id= target[1];
      }
	}
	
	function callUncodedNotesNotesPopUp(){
		var url = '../contract/uncodedNotesPopUpSubstitute.jsp'+getUrl()+'?temp='+Math.random();
		var newWinForView =window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=false;status=no;");
	}
	//Function for number check
	function isNumericCheck(sText) {
	var isNumber=true;
	var validChars = "0123456789";
	var thisChar;
	for (i = 0; i < sText.length && isNumber == true; i++) {  
		thisChar = sText.charAt(i);
		if (validChars.indexOf(thisChar) == -1) isNumber = false;
	}
	return isNumber;

}

function alignDescriptionLength(descLowerLevelvalue,len){
	var arrStr;
	var desc;
	var num = 0;
	arrStr = descLowerLevelvalue.split(" ");
	for(num=0;num<arrStr.length;num++){
	   	if(arrStr[num].length>len){
	   		arrStr[num] = arrStr[num].substring(0,len)+" "+
	   		arrStr[num].substring(len);        			
	   	}
		if(typeof(desc) == 'undefined'){
			desc = arrStr[num];
		}else{
			desc = desc+" "+arrStr[num];
		}
	}
	return desc;
}
 /*
	  Using this function it is possible to show description along with the code 
	 */
	function getSelectedContractPricingData_ewpd_show_code_desc(url, formName, lobHidden, beHidden, bgHidden, targetDiv, targetHidden, attrCount, attrPosn, parentCatalog,modetype){
		var lob = '', be = '', bg = '';
	 	lob = getObj(formName+':'+lobHidden).value;
	 	be = getObj(formName+':'+beHidden).value;
	 	bg = getObj(formName+':'+bgHidden).value;
	 
		lob = escapeAmpersandSpecialCharacterForRefDataLookUp(lob);
		be = escapeAmpersandSpecialCharacterForRefDataLookUp(be);
		bg = escapeAmpersandSpecialCharacterForRefDataLookUp(bg);
		
		if((null != lob && lob != '') && (null != be && be != '') && (null != bg && bg != '')){
			url = url + '&lookUpAction=13'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd_show_code_desc(url, targetDiv, targetHidden, attrCount, attrPosn,modetype);
		}else{
				url = url + '?parentCatalog='+parentCatalog+'&lookUpAction=1';
				return ewpdModalWindow_ewpd_show_code_desc(url, targetDiv, targetHidden, attrCount, attrPosn,modetype);
		}
	 }

/**
	Function gets values from hidden field, process it and stores it to another text field or div.
	
	Parameters
	hiddenId		- hiddenField id from which values to be copied.
	divId			- Target div id to which values to be copied.
	attrCount		- The number of attributes of the tilda String that constitues  a single data which is stored in hidden field.
					  eg: id1~desc1~name1~id2~desc2~name2 - here 3 attributes represents a single data. 
					  									  - so the count is 3.
	attrPos			- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
					  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given then id1,id2 will be shwon in div.
														  - if 2 is given then desc1,desc2 will be shown in div.
	modetype 		- type may be edit,vie or print.
*/
function copyHiddenToDiv_ewpd_show_code_desc(hiddenId, divId, attrCount, attrPos,modetype) {

	var hiddenObj = getObj(hiddenId);
	var divObj = getObj(divId);
	var textValue = hiddenObj.value;
	var values;
	var targetDiv = getObj(divId);
	var elementCount = 0;
	var newTargetTable=document.createElement("table");
	if(hiddenObj.value == null || hiddenObj.value == ''){
			divObj.innerText = '';
			targetDiv.style.height="17px";
	}
	
	if(textValue != null && textValue != undefined && textValue.length > 0){
		attrPos -= 1;	
		values = textValue.split("~");
		newTargetTable.id="providerspecialtycodetableid";
		newTargetTable.border=0;
		var tmpRow = null;
		var tmpCell = null;
		var tagetlinelength;
		for(var i=0, n = values.length; i<n; i+=attrCount) {
			elementCount++;
			
			// row for show code and description 
			tmpRow=newTargetTable.insertRow();
			//column for code
			tmpCell=tmpRow.insertCell();
			tmpCell.style.color="#1762A5";
			if(modetype =='view'){
			tmpCell.style.color="black";
			}
			tmpCell.setAttribute("align","left");
			tmpCell.style.verticalAlign = 'top';
			tmpCell.innerText =  values[i+attrPos]+'-';
			//column for description
			tmpCell=tmpRow.insertCell();
			tmpCell.style.color="#1762A5";
			tmpCell.setAttribute("align","left");
			tmpCell.style.verticalAlign = 'top';
			// here start code for wrapping description 
			var description='';
			var desclen = values[i].length;
			if(modetype =='view'){
			tmpCell.style.color="black";
			tagetlinelength = 22;
			}else{
			tagetlinelength = 14;
			}
			if(desclen>tagetlinelength){
			var k=0;
				for (var j=tagetlinelength;k<desclen; j=j*2){
				description=description.concat(values[i].substring(k, j),' ');
				k = j;
				}
			}else{
			description = values[i];
			}
			tmpCell.innerText = description;
			// here ends code for wrapping description 
		}
		//removing existing elements from div 
    	 var child = targetDiv.getElementsByTagName("*");
    	 for (var i = 0; i < child.length; i++) {
			var elem = allContainedElements[i];
			targetDiv.removeChild(elem);
		}
		//adding new table in to the div 
  		targetDiv.appendChild(newTargetTable);
		if(elementCount ==0)
			targetDiv.style.height="17px";
		else
			targetDiv.style.height="60px";
		if(modetype =='view'){
			targetDiv.style.width="100%";
		}
		targetDiv.style.overflowY='auto';
		targetDiv.style.overflowX = 'hidden';
	}
	targetDiv.style.visibility = 'visible';
	targetDiv.style.display = 'block';
	if(modetype =='view'){
			if(elementCount == 0){
			targetDiv.style.visibility = 'hidden'; 
			}
	}
}

/**
	Function opens a popup screen, accept return value from the popup and process it and 
	stores to specific fields.
	
	Parameters
	url			(Mandatory) - url of the popup jsp to be opend.<b> 
	targetId	(Mandatory) - This can be inputText/div. The selected values from the
							  popup will be shown in this field.<b> 
	hiddenId	(Mandatory) - Id of the hidden field where the return value of the popup to be stored.
							  This can be same as targetId.In this case this should be inputText.
	attrCount	(Mandatory)	- The number of attributes in the tilda String that constitues  a single data. 
							  eg: id1~desc1~name1~id2~desc2~name2 - here 3 attributes represents a single data. 
							      so the count is 3.
	attrPos		(Mandatory)	- Position of the attribute within the tilda String that needs to be shown in the div/inputText.
							  eg: id1~desc1~name1~id2~desc2~name2 - if 1 is given then id1,id2 will be shwon in div.
							  									  - if 2 is given then desc1,desc2 will be shown in div.	
	hiddenIdToDisable(optional) - For later user.							  									  		
*/

function ewpdModalWindow_ewpd_show_code_desc(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable,targettable,modetype){
	// Creating new object and setting all the values that needs to be passed into the popup screen. These values
	// Object param can be accessed from the popup by "window.dialogArguments".
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	param.selectedValues = getObj(hiddenId).value;
	param.hiddenIdToDisable = hiddenIdToDisable;

	// Calling popup window.
	var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
	// Cancel action from popup. No need to do anything.
	if( retValue == undefined ) {
		return false;
	}
	// Setting the value from popup to hidden field
	var hiddenValueObj = getObj(hiddenId);

	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != retValue) {
			if(peer_hiddenObj.value != ''){
				peer_hiddenObj.value = '';
			}
			if(peer_DivObj != null){
				peer_DivObj.innerHTML = '';
				peer_DivObj.style.height="17px";
			}
		}	
	}
	hiddenValueObj.value = retValue;
	if(hiddenId == targetId)
		return true;

	// Setting necessary values from hidden field to div/text	
	if(	getObj(targetId).type == 'text'){
		copyHiddenToInputText(hiddenId, targetId, attrPos);
		
		return retValue;
				
	}
	else
		copyHiddenToDiv_ewpd_show_code_desc(hiddenId, targetId, attrCount, attrPos,targettable,modetype);
	return true;
}

function ewpdModalWindow_termed_contract(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
	// Creating new object and setting all the values that needs to be passed into the popup screen. These values
	// Object param can be accessed from the popup by "window.dialogArguments".
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = hiddenId;
	param.selectedValues = getObj(hiddenId).value;
	param.hiddenIdToDisable = hiddenIdToDisable;

	// Calling popup window.
	var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
	// Cancel action from popup. No need to do anything.
	if( retValue == undefined ) {
		return false;
	}
	// Setting the value from popup to hidden field
	var hiddenValueObj = getObj(hiddenId);

	
	if(peer_divId != undefined && peer_hiddenId != undefined) {
		var peer_DivObj = document.getElementById(peer_divId);
		var peer_hiddenObj = document.getElementById(peer_hiddenId);
		if(hiddenValueObj.value != retValue) {
			if(peer_hiddenObj.value != ''){
				peer_hiddenObj.value = '';
			}
			if(peer_DivObj != null){
				peer_DivObj.innerHTML = '';
				peer_DivObj.style.height="17px";
			}
		}	
	}
	retValue = retValue.split("~")[1];
	if( retValue == undefined ) {
		retValue = '';
	}
	hiddenValueObj.value = retValue;
	if(hiddenId == targetId)
		return true;

	// Setting necessary values from hidden field to div/text	
	if(	getObj(targetId).type == 'text'){
		copyHiddenToInputText(hiddenId, targetId, 1);
		
		return retValue;
				
	}
	else
		copyHiddenToDiv_ewpd(hiddenId, targetId, 2, 1);
	return true;
}
function getUrl(){
	var urlString = window.document.URL;
	var length;
	if(urlString.indexOf("?")!=-1){
	 	length = urlString.indexOf("?");
	}else{
		length = urlString.length;
	}
	var urlSubstring;
	if(urlString.indexOf(";jsessionid=")!=-1){
		urlSubstring = urlString.substring(urlString.indexOf(";jsessionid="), length);
	}else{
		urlSubstring = "";
	}
	return urlSubstring;
}