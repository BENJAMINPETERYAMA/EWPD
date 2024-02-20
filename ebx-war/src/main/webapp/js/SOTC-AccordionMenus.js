var ContentHeight ;
var TimeToSlide = 50.0;
var flag=false;
var openAccordion = '';

function runAccordion(index)
{
  var nID = "Accordion" + index + "Content";
  if(openAccordion == nID)
    nID = '';
  setTimeout("animate(" + new Date().getTime() + "," + TimeToSlide + ",'" + openAccordion + "','" + nID + "')", 33);
  
  openAccordion = nID;
  
}

function animate(lastTick, timeLeft, closingId, openingId)
{  
  var curTick = new Date().getTime();
  var elapsedTicks = curTick - lastTick;
 
  var opening = (openingId == '') ? null : document.getElementById(openingId);
  var closing = (closingId == '') ? null : document.getElementById(closingId);

  if(timeLeft <= elapsedTicks)
  {
    if(opening != null)
    {
      opening.style.display = 'block';
      opening.style.height = ContentHeight + 'px';
      
      if(flag == false)
      {
	      document.getElementById('inProgressDivHeader1').style.display= 'none';
	      document.getElementById('inProgressDivHeader2').style.display= 'block';
	      
	      document.getElementById('inProgressDiv').style.display= 'block';
	  	  document.getElementById('inProgressDivSub').style.display= 'none';
	  	  if(openingId == "Accordion1Content")
		  {
			  document.getElementById('inProgressDivHeader1').style.display= 'none';
			  document.getElementById('inProgressDivHeader2').style.display= 'block';
			  
			  document.getElementById('inProgressDiv').style.display= 'block';
	  		  document.getElementById('inProgressDivSub').style.display= 'none';
		  }
		  else
		  {
			  document.getElementById('inProgressDivHeader1').style.display= 'block';
			  document.getElementById('inProgressDivHeader2').style.display= 'none';
			  
			  document.getElementById('inProgressDiv').style.display= 'none';
	  		  document.getElementById('inProgressDivSub').style.display= 'block';
		  }
	  }
      else
	  {
	  	
	      if(openingId == "Accordion1Content")
		  {
			  document.getElementById('inProgressDivHeader1').style.display= 'none';
			  document.getElementById('inProgressDivHeader2').style.display= 'block';
			  
			  document.getElementById('inProgressDiv').style.display= 'block';
	  		  document.getElementById('inProgressDivSub').style.display= 'none';
		  }
		  else
		  {
			  document.getElementById('inProgressDivHeader1').style.display= 'block';
			  document.getElementById('inProgressDivHeader2').style.display= 'none';
			  
			  document.getElementById('inProgressDiv').style.display= 'none';
	  		  document.getElementById('inProgressDivSub').style.display= 'block';
		  }
	  }
    }
   if(closing != null)
    {
      closing.style.display = 'none';
      closing.style.height = '0px';
      if(flag== true)
      {
        	if(openingId == "Accordion1Content")
		    {
			     document.getElementById('inProgressDivHeader1').style.display= 'none';
			     document.getElementById('inProgressDivHeader2').style.display= 'block';
			     
			     document.getElementById('inProgressDiv').style.display= 'block';
	 			 document.getElementById('inProgressDivSub').style.display= 'none';
			 }
			else
			{
			    document.getElementById('inProgressDivHeader1').style.display= 'block';
			    document.getElementById('inProgressDivHeader2').style.display= 'none';
			    
			    document.getElementById('inProgressDiv').style.display= 'none';
	 			document.getElementById('inProgressDivSub').style.display= 'block';
			}
      }
      else
      {
            document.getElementById('inProgressDivHeader1').style.display= 'block';
            document.getElementById('inProgressDivHeader2').style.display= 'block';
           if(openingId == "Accordion1Content") {
           		 
           		 document.getElementById('inProgressDivHeader1').style.display= 'none';
           		 
           		document.getElementById('inProgressDiv').style.display= 'block';
           		document.getElementById('inProgressDivSub').style.display= 'none';
           }else if(openingId == "Accordion2Content"){
           		
           		document.getElementById('inProgressDivHeader2').style.display= 'none';
           		
           		
	 	   		document.getElementById('inProgressDivSub').style.display= 'block';
	 	   		document.getElementById('inProgressDiv').style.display= 'none';
	 	   }
       }
      
    }
    flag=false;
    return;
  }

  timeLeft -= elapsedTicks;
  var newClosedHeight = Math.round((timeLeft/TimeToSlide) * ContentHeight);
 
  if(opening != null)
  {
    flag=true;
    if(opening.style.display != 'block') {
    opening.style.display = 'block';
    }
    opening.style.height = (ContentHeight - newClosedHeight) + 'px';
  }
  
  if(closing != null)
  {
    closing.style.height = newClosedHeight + 'px';
  }
 
  setTimeout("animate(" + curTick + "," + timeLeft +",'" + closingId + "','" + openingId + "')", 33);
}