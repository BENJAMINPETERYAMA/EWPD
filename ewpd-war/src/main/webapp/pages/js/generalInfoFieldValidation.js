// JavaScript Document

function displayMessage(id,sUrl,eUrl)
{
	var formId= document.getElementById(id);
	var successURL=sUrl;
	var errorURL=eUrl;
	if (formId.option2.checked) {
		location.replace(eUrl);
	} 
	else{
		location.replace(sUrl);
	}

}
function generalInfoFieldVal()
{

    var alertmessage="Not all required inputs are available. Please provide data for the marked fields and re-submit the form";
	var state_code_value=document.getElementById('form1:listStateCode');
	var stateCodeStar=document.getElementById('stateCodeStar');
	var productFamily_value=document.getElementById('form1:listProductFamily');
	var productFamilyStar=document.getElementById('productFamilyStar');
	var groupSize_value=document.getElementById('form1:listGroupSize');
	var groupSizeStar=document.getElementById('groupSizeStar');
	var citation_value=document.getElementById('divCitationNumber');
	var citationCodeStar=document.getElementById('citationCodeStar');
	
	if(state_code_value.value == '' || productFamily_value.value == '' || groupSize_value.value == '' || citation_value.value==''){
		if(state_code_value.value == '')
		{
			stateCodeStar.className = "mandatoryError";
		}
		else{
			stateCodeStar.className = "mandatoryNormal";
		}

		if(productFamily_value.value == '')
		{
			productFamilyStar.className = "mandatoryError";
		}
		else{
			productFamilyStar.className = "mandatoryNormal";
		}

		if(groupSize_value.value == '')
		{
			groupSizeStar.className = "mandatoryError";
		}
		else{
			groupSizeStar.className = "mandatoryNormal";
		}
	
		if(citation_value.value == '')
		{
			citationCodeStar.className = "mandatoryError";
		}
		else
		{
			citationCodeStar.className = "mandatoryNormal";
		}


		alert(alertmessage);
			return false;
	}
	else{
		stateCodeStar.className="mandatoryNormal";	
		productFamilyStar.className="mandatoryNormal";	
		groupSizeStar.className = "mandatoryNormal";
		citationCodeStar.className = "mandatoryNormal";
		//Benefit_display2.submit();
			//return tr;
	}
	//return false;
}

function mandateInfoFieldVal(){
			
    var alertmessage="Not all required inputs are available. Please provide data for the marked fields and re-submit the form";
	var majorHeading_value=document.getElementById('TypeEntyData1');
	var majorHeadingStar=document.getElementById('majorHeadingStar');
	var minorHeading_value=document.getElementById('TypeEntyData2');
	var minorHeadingStar=document.getElementById('minorHeadingStar');
	var description_value=document.benefit_display.description_txt;
	var mandateInformation_value=document.benefit_display.mandate_info_txtar;
	var effectiveDate_txt_value=document.benefit_display.effectiveDate_txt;
	var effectiveness_value = document.benefit_display.effectiveness_list;
	var listText = effectiveness_value.options[effectiveness_value.selectedIndex].text;
	//alert(listText);

	if(majorHeading_value.innerText == '' || minorHeading_value.innerText == '' || description_value.value=='' || mandateInformation_value.value=='' || effectiveDate_txt_value.value == '' || listText == '' ){
		
		if(majorHeading_value.innerText == '')
		{
			majorHeadingStar.className = "mandatoryError";
		}
		else{
			majorHeadingStar.className = "mandatoryNormal";
		}

		if(minorHeading_value.innerText == '')
		{
			minorHeadingStar.className = "mandatoryError";
		}
		else{
			minorHeadingStar.className = "mandatoryNormal";
		}
		
		if(description_value.value==""){
			descriptionStar.className = "mandatoryError";
		}
		else{
			descriptionStar.className = "mandatoryNormal";
		}
		
		if(mandateInformation_value.value==""){
			mandateInformationStar.className = "mandatoryError";
		}
		else{
			mandateInformationStar.className = "mandatoryNormal";
		}
		
		if(effectiveDate_txt_value.value==""){
			effectiveDateStar.className = "mandatoryError";
		}
		else{
			effectiveDateStar.className = "mandatoryNormal";
		}

		if(listText == ''){
			effectivenessStar.className = "mandatoryError";
		}
		else{
			effectivenessStar.className = "mandatoryNormal";
		}
		alert(alertmessage);
	}
	else{
		majorHeadingStar.className="mandatoryNormal";	
		minorHeadingStar.className="mandatoryNormal";	
		descriptionStar.className="mandatoryNormal";
		mandateInformationStar.className = "mandatoryNormal";	
		effectiveDateStar.className = "mandatoryNormal";
		effectivenessStar.className = "mandatoryNormal";
		effectivenessStar.className = "mandatoryNormal";
		agree = "true";	
		//var agree=confirm("Are you sure you wish to continue?");
			if (agree)
			{
				if(document.benefit_display.endDate_txt.value==''){
				document.benefit_display.endDate_txt.value="12/31/9999";
				}
				var tag = document.getElementById("infoDiv");
				tag.style.display='block';
				majorHeading_value.innerText ='';
				minorHeading_value.innerText = '';
				benefit_display.reset();
			}
			else
		//		benefit_display.reset();
				return false;
		
//		benefit_display.confirm("Are you sure you want to save the information?");

}
}