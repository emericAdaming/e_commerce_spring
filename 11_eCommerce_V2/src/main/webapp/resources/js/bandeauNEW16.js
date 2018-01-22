
$(document).ready(function(){
	  console.log("Affiche news produits");
	  var img=document.getElementsByClassName('imageTaille');
	  console.log(img[0].title);
	   
	  var lol=document.getElementsByClassName('lol');
	  console.log(lol[0].offsetTop);
	  console.log(img[0].offsetTop);
	  
	  //lol[0].style.backgroundColor="red";
	  lol[0].style.position="absolute";
	  lol[0].style.zIndex=1;
	  //lol[0].offsetTop=img[0].offsetTop+'px';
	  //lol[0].style.left=img[0].style.left+'px';
	  var x=(img[0].offsetLeft-4)+'px';	
	  var y=(img[0].offsetTop-5)+'px';
	  lol[0].style.left=x;
	  lol[0].style.top=y;
	  console.log(lol[0].title);
	  console.log(lol[0].offsetTop);
	  console.log(img[0].offsetTop);
	  console.log("Fin Affiche news produits");
	});