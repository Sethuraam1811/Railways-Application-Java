var i=1;
document.getElementById("bookBtn").addEventListener("click",function() {
	if(i%2!=0) {
		document.getElementById("trainsPosition").style.display = "block";
		i++;	
	}
	else {
		document.getElementById("trainsPosition").style.display = "none";
		i++;
	}
});

var j=1;
document.getElementById("cancelBtn").addEventListener("click",function() {
	if(j%2!=0) {
		document.getElementById("cancelPosition").style.display = "block";
		j++;	
	}
	else {
		document.getElementById("cancelPosition").style.display = "none";
		j++;
	}
});

var k=1;
document.getElementById("statusBtn").addEventListener("click",function() {
	if(k%2!=0) {
		document.getElementById("statusPosition").style.display = "block";
		k++;	
	}
	else {
		document.getElementById("statusPosition").style.display = "none";
		k++;
	}
});

document.getElementById("cancelSubmit").addEventListener("click",function() {
	//if(confirm("Are you sure you want to cancel ticket?"));
});

var demo = document.getElementById("demo");

document.getElementById("statusSubmit").addEventListener("click",function() {

	var method = document.getElementById("statusForm").method;
	console.log(method);
	var statusPnr = document.getElementById("statusPnr").value;
	console.log(statusPnr);
	var url = "http://localhost:8080/RailwaysApplication/status";
	console.log(url);
	var req = new XMLHttpRequest();
  	req.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
      		var statusData = JSON.parse(req.responseText);	
			console.log(statusData);
			renderHTML(statusData, statusPnr);
			
		}
	};
	req.open(method, url, true);
  	req.send();
	//if(demo.firstChild) demo.removeChild(para);
});



function renderHTML(data,pnr) {
	//var htmlString = "";
	var statusStr = "";
	for(i=0; i < data.length;i++) {
		if(data[i].pnrNumber == pnr) {
			//document.getElementById("pname"+j).innerHTML = data[i].passengerName;
			//document.getElementById("page"+j).innerHTML = data[i].passengerAge;
			//document.getElementById("pgender"+j).innerHTML = data[i].passengerGender;
			//j++;
			 statusStr += '<tr id="tableTrain"><td> Train Number </td><td>'+data[i].trainNo+'</td></tr>' + 
						'<tr><td> Train Name </td><td>'+data[i].trainName+'</td></tr>' +
						'<tr><td> PNR Number </td><td>'+data[i].pnrNumber+'</td></tr>' +
						'<tr><td> Booking Class </td><td>'+data[i].classChoice+'</td></tr>' +
						'<tr><td> Number of Seats </td><td>'+data[i].seatCount+'</td></tr>' +
						'<tr><td> Ticket Status </td><td>'+data[i].ticketStatus+'</td></tr>';
		}
	}
	document.getElementById("statusTable").innerHTML = statusStr;
	j=1;
	//demo.insertAdjacentHTML('beforeend',htmlString);	
}

document.getElementById("trainsListDiv").style.marginTop = "20px";

/*---------------------------------*/
var count=0;

document.getElementById("trainStart").addEventListener("input",function() {
	if(count==0) {
	var url = "http://localhost:8080/RailwaysApplication/bookingmenu";
	console.log(url);
	var req = new XMLHttpRequest();
	
  	req.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
      		var data = JSON.parse(req.responseText);
			console.log(data);
			//renderDestination(bookingData);
			var start = new Array();
			for(var i=0;i<data.length;i++) {
				if(start.includes(String(data[i].startDestination))) {
					continue;
				}
				start.push(String(data[i].startDestination));
			}
			//start.push("Goa","Bangalore","Hyderabad","Kochi");
			console.log(start);
			var startStr="";
			//var setIter = start.values();
			//console.log(setIter);
			for(var j=0;j<start.length;j++) {
				console.log(start[j]);
				console.log(startStr);
				startStr += '<option>'+start[j]+'</option>'; 
				
			}
			var my_list=document.getElementById("browsers");
			my_list.innerHTML = startStr;
		}
	};	
	req.open("GET", url, true);
  	req.send();
	count++;
	}
});
/*-----------------------------------------*/
var count1=0;
document.getElementById("trainEnd").addEventListener("input",function() {
	//if(count1==0) {
	var url = "http://localhost:8080/RailwaysApplication/bookingmenu";
	console.log(url);
	var req = new XMLHttpRequest();
  	req.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
      		var data = JSON.parse(req.responseText);
			console.log(data);
			var startInput = document.getElementById("trainStart").value;
			console.log(startInput);
			//renderDestination(bookingData);
			var end = new Array();
			for(var i=0;i<data.length;i++) {
				if(end.includes(String(data[i].endDestination))) {
					continue;
				}
				else if(String(data[i].startDestination) === startInput){
					end.push(String(data[i].endDestination));
					console.log(data[i].endDestination);
				}
			}
			//end.push("Goa","Hyderabad","Kochi");
			console.log(end);
			var endStr="";
			//var setIter = start.values();
			//console.log(setIter);
			for(var j=0;j<end.length;j++) {
				console.log(end[j]);
				console.log(endStr);
				endStr += '<option>'+end[j]+'</option>'; 
				
			}
			var my_list=document.getElementById("browser");
			my_list.innerHTML = endStr;
		}
	};	
	req.open("GET", url, true);
  	req.send();
	count1++;
	//}
});



/*-----------------------------------------*/

document.getElementById("bookBtn").addEventListener("click",function() {
	document.getElementById("bookingAlert").style.display = "none"
});