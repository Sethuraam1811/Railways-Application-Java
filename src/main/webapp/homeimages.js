var index = 0;
function changeTrainImage() {
  var i;
  var x = document.getElementsByClassName("trainImg");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  index++;
  if (index > x.length) {
    index = 1
  }    
  x[index-1].style.display = "block";  
  setTimeout(changeTrainImage, 5000);
}
changeTrainImage();

