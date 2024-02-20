/**
 * @author vinod
 */
var alertDuration;
var sessionTimeoutDuration;
var alertTime;

function startTimer(){
	//Configure the parameters to Timeout to change
	//  i.  session timeout duration (currently set to 30 minutes) and
	//  ii. time before session timeout the user should be alerted (set to 3 minutes)
	timer = new Timeout(30,3);
	timer.start();
}

function Timeout(std,ad){
	sessionTimeoutDuration = std;
	alertDuration = ad;
	alertTime = ((sessionTimeoutDuration * 60 * 1000) - (alertDuration * 60 * 1000));
}

Timeout.prototype.cookieString = "sessionexpiry=";
Timeout.prototype.confirmDisplayed = "confirmDisplayed=";
Timeout.prototype.renewURL = "../../pages/navigation/renew.jsp";
Timeout.prototype.logoutURL = "../../pages/navigation/logout.jsp?logout=true";

Timeout.prototype.start = function(){
	var currentPage = window.location;
	//do not run if the call happens from the logout page
	if((currentPage.toString()).indexOf(this.logoutURL) != -1){
		return;
	}
	//reset flag
	document.cookie = this.confirmDisplayed + "false";
	var ms = new Date().getTime() + alertTime;
	document.cookie = this.cookieString + encodeURIComponent(ms);
	//create a random integer between 10 & 20
	var interval = (Math.floor(Math.random() * 10)) + 10;
	timer = window.setInterval("Timeout.prototype.check()",interval * 1000);
}

Timeout.prototype.stop = function(){
	//Not implemented at this time. 
}
Timeout.prototype.check = function(){
	if(this.isConfirmDisplayed() == "true"){
		return;
	}
	var expTime = this.getExpiryTime();
	var now = new Date().getTime();
	if(now >= expTime){
		document.cookie = this.confirmDisplayed + "true";
		var choice = this.displayConfirm(expTime);
		if(choice){
			this.extendSession();
		}else{
			this.logout();
		}
	}
}
Timeout.prototype.isConfirmDisplayed = function(){
	var cookies = document.cookie;
	var position = cookies.indexOf(this.confirmDisplayed);
	if(position != -1){
		var end = cookies.indexOf(";",position);
		if(end == -1){end = cookies.length};
		var conDisp = decodeURIComponent(cookies.substring(position + this.confirmDisplayed.length,end));
		return conDisp;
	}
}

Timeout.prototype.getExpiryTime = function(){
	var cookies = document.cookie;
	var position = cookies.indexOf(this.cookieString);
	if(position != -1){
		var end = cookies.indexOf(";",position);
		if(end == -1){end = cookies.length};
		var expTime = decodeURIComponent(cookies.substring(position + this.cookieString.length,end));
		return parseInt(expTime);
	}
}

Timeout.prototype.displayConfirm = function(expTime){
	var message = "Your current session will time out in approximately " + alertDuration + " minutes, at ";
	message += this.formatDate(new Date(expTime + (alertDuration * 60 * 1000)));
	message += ".\n\nSelect OK to renew your session and continue using eWPD.  Otherwise select Cancel to logout.";
	return window.confirm(message);
}

Timeout.prototype.formatDate = function(expDate){
	var hours = expDate.getHours();
	var minutes = expDate.getMinutes();
	var seconds = expDate.getSeconds();
	var ampmIndicator = "AM";
	if(hours >= 12){
		ampmIndicator = "PM";
	}
	if(hours == 0){
		hours = 12;
	}else if(hours > 12){
		hours = hours - 12;
	}
	if(minutes < 10){
		minutes = "0" + minutes;
	}
	if(seconds < 10){
		seconds = "0" + seconds;
	}
	return hours + ":" + minutes + ":" + seconds + " " + ampmIndicator;
}
/*
 * Make an asynchronous call to renewSession page. 
 * This is a regular JSP that does nothing, but return true
 * if no Exceptions are thrown on the server side. 
 * If request is successful update sessionexpiry cookie to 
 * current time. 
 */
Timeout.prototype.extendSession = function(){
	//Display error if the session has already expired
	var et = this.getExpiryTime() + (alertDuration * 60 * 1000);
	if(new Date().getTime() >= et){
		alert("Your current session cannot be renewed as it expired at " + this.formatDate(new Date(et)));
		return;
	}
	
	try{
		request = new ActiveXObject("Msxml2.XMLHTTP");
	}catch(msxml){
		//failed try the second IE method
		try{
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}catch(microsoft){
			//failed again. Try the standard way;
			try{
				request = new XMLHttpRequest();
			}catch(generic){
				this.displayRenewError();
			}
		}
	}
	request.open("GET",this.renewURL,true);
	request.onreadystatechange = function(){
		if(request.readystate == 4){
			//The session was renewed if we received a HTTP response
			//status of 200.  We are not interested in the actual response. 
			//reset the instance of Timeout by calling start().
			if(request.status == 200){
				Timeout.prototype.start();
			}else{
				Timeout.prototype.displayRenewError();
			}
		}		
	};
	request.send(null);
}

Timeout.prototype.logout = function(){
	window.location = this.logoutURL;
}

Timeout.prototype.displayRenewError = function(){
	alert("Error renewing current session.\n\nPlease save your work immediately.");
}