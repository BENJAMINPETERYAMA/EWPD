// Numeric validation function
	function isNumeric(field){
		var numericExpression = /^[0-9]+$/;
		if (field.match(numericExpression)){
			return true;
		} else {
			return false;
		}	
}

// Alphabets validation function
	function isAlphabet(field){
	var alphaExp = /^[a-zA-Z]+$/; 	
	if (field.match(alphaExp)){
		return true;
	} else {
		return false;
	}
}
  
// Special characters function
	function isSpecial(field){	
	if(field.match("^[a-zA-Z0-9'.\s]{1,50}$")){
		return true;
	} else {
		return false;
	}
}

// what is this dialog function
function openPopUp(msg,data,event){
	  var position1 = event.clientX;
	  var position2 = event.clientY;
            $("#dialog").html(data); 
            $("#dialog").show();
            $("#dialog").dialog({
     				 title: msg, 
     				 bgiframe: true, 
     				 height: 'auto', 
     				 position: [position1,position2], 
     				 show:'clip',
                 	 hide:'clip',
                 	 modal: false,
                     resizable:false,
                     draggable: true,
                     autoOpen: true                                      
    });
}

//Adding for Date Validation
var dtCh= "/";
var minYear=1900;
var maxYear=9999;

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

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31;
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30;}
		if (i==2) {this[i] = 29;}
   } 
   return this;
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12);
	var pos1=dtStr.indexOf(dtCh);
	var pos2=dtStr.indexOf(dtCh,pos1+1);
	var strMonth=dtStr.substring(0,pos1);
	var strDay=dtStr.substring(pos1+1,pos2);
	var strYear=dtStr.substring(pos2+1);
	strYr=strYear;
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
	}
	month=parseInt(strMonth);
	day=parseInt(strDay);
	year=parseInt(strYr);
	if (pos1==-1 || pos2==-1){
		return false;
	}
	if (strMonth.length<1 || month<1 || month>12){
		return false;
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		return false;
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		return false;
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		return false;
	}
return true;
}

function isGreaterThanTodaysDate(dtStr){
		
	var date = new Date();
	  var currentYear = date.getFullYear();
	  var currentMonth = date.getMonth();
	  var currentDay = date.getDate();
	 
	  var compareDate = new Date(currentYear, currentMonth, currentDay, 0, 0, 0);
	  var fromDate=parseIso8601Date(dtStr);
	  if (compareDate <= fromDate) {
		return false;
	  }  
	return true;
}

function isEmpty(text) {
	  return trim(text).length == 0;
	}

function trim(str) {
	  if (str.length > 0) {
	    while (str.charAt(0) == ' ') {
	      str = str.substring(1);
	    }
	    while (str.charAt(str.length - 1) == ' ') {
	      str = str.substring(0,str.length - 1);
	    }
	  }
	  return str;
	}
	
function parseIso8601Date(text) {
	  if (isEmpty(text))
	    return null;

	  var yyyy = parseInt(text.substr(6,10), 10);
	  var mm = parseInt(text.substr(0,2), 10);
	  var dd = parseInt(text.substr(3,5), 10);
	  return new Date(yyyy,mm-1,dd);
	}

function endDateGreaterThanStartDate(startDate, endDate){
	var endDateFromPage = parseIso8601Date(endDate);
	var startDateFromPage = parseIso8601Date(startDate);
	if (null != endDateFromPage && endDateFromPage < startDateFromPage) {
		return false;
	  }  
	return true;
}