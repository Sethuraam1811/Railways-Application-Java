document.getElementById("trainSubmit").addEventListener("click",function() {
	var count = document.getElementById("seatCount").value;
	console.log(count);
	var passengerStr="";
	for(var i=1;i<=count;i++) {
		
		var pno = "Passenger"+i;
		var pname = "passengername"+i;
		console.log(pname);
		var page = "passengerage"+i;
		console.log(page);
		var pgender = "passengergender"+i;
		console.log(pgender);
		var fc = "foodchoice"+i;
		console.log(fc);
		var aadharno = "aadharnumber"+i;
		console.log(aadharno);
			
		passengerStr += '<div id="passengerTitle"><p> Passenger'+i+'</p></div>'
		
		+'<div><input size="40" type="text" name="'+pname+'" class="inputBox" placeholder ="Enter Passenger Name"/></div>'
		
		+'<div><input size="40" type="text" name="'+page+'" class="inputBox" placeholder ="Enter Passenger Age"/></div>'
		
		+'<div class="radioTitle">'
		+'<select name="'+pgender+'" class="inputBox">'
		+'<option value="none" selected disabled hidden>Choose Given Gender</option>'
		+'<option value="Male">Male</option>'
		+'<option value="Female">Female</option>'
		+'<option value="Other">Other</option>'
		+'</select>'
		+'</div>'
		
		+'<div class="radioTitle">'
		+'<select name="'+fc+'" class="inputBox">'
		+'<option value="none" selected disabled hidden>Add Food For Rs.100?</option>'
		+'<option value="Yes">Yes</option>'
		+'<option value="No">No</option>'
		+'</select>'
		+'</div>'
		
		+'<div><input size="40" type="text" name="'+aadharno+'" class="inputBox" placeholder ="Enter Aadhar Number"></div>'
		
	}
	
	document.getElementById("passengerDetails").innerHTML = passengerStr;
	document.getElementById("bookButton").style.display = "block";
});