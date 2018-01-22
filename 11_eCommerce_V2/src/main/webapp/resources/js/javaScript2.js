


function note_couleur(id){
	var etoiles=document.getElementById('divEtoile').getElementsByClassName('etoile');
	console.log("Notation etoile !!");
	
	etoiles[0].style.color="grey";
	etoiles[1].style.color="grey";
	etoiles[2].style.color="grey";
	etoiles[3].style.color="grey";
	etoiles[4].style.color="grey";

	
	if(id==1){
		etoiles[0].style.color="red";
		console.log("etoile 0 !!");
	}else if(id==2){		
		etoiles[0].style.color="red";
		etoiles[1].style.color="red";
	}
	else if(id==3){
		etoiles[0].style.color="orange";
		etoiles[1].style.color="orange";
		etoiles[2].style.color="orange";		
	}
	else if(id==4){
		etoiles[0].style.color="green";
		etoiles[1].style.color="green";
		etoiles[2].style.color="green";
		etoiles[3].style.color="green";		
	}
	else if(id==5){
		etoiles[0].style.color="green";
		etoiles[1].style.color="green";
		etoiles[2].style.color="green";
		etoiles[3].style.color="green";
		etoiles[4].style.color="green";
	}
}

function finalValue(){
	var etoiles=document.getElementById('divEtoile').getElementsByClassName('etoile');
	etoiles[0].style.color="grey";
	etoiles[1].style.color="grey";
	etoiles[2].style.color="grey";
	etoiles[3].style.color="grey";
	etoiles[4].style.color="grey";
	
}