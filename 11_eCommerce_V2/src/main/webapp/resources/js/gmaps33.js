var map;

$(document).ready(function(){
  var map = new GMaps({
    el: '#directions_map',
    lat: 47.5073346,
    lng: -1.5276831,
    zoom:7, 
   
  });

  
  GMaps.geolocate({
    success: function(position){
     var depl=document.getElementById("deplacement").value;
     console.log(depl);
      map.setCenter(47.220016, position.coords.longitude);
       map.drawRoute({
        origin: [position.coords.latitude, position.coords.longitude],
        destination: [44.870000, -0.5976831],
        travelMode: 'driving',
        strokeColor: '#000',
        strokeOpacity: 0.6,
        strokeWeight: 6,
        
      });
    },
    error: function(error){
      alert('Geolocation failed: '+error.message);
    },
    not_supported: function(){
      alert("Your browser does not support geolocation");
    }
  });
  map.addMarker({
      lat: 44.870000,
      lng: -0.590000,
      title: 'Notre entreprise',
      infoWindow: {
        content: 'Piscines de qualité '
      }
  });
  map.addMarker({
      lat: position.coords.latitude,
      lng:  position.coords.longitude,
      title: 'Votre Position',
      infoWindow: {
        content: 'aa'
      }
  })
});

function calculate(){
	 var depl=document.getElementById("deplacement").value;
	
	 console.log(depl);
	 var tm;
	 if(depl=="Voiture")
		 tm= 'driving';
	 else if(depl=="Velo")
		 tm= 'walking';
	 else
		 tm= 'bicycling';
	 map=new GMaps({
		    el: '#directions_map',
		    lat: 51.5073346,
		    lng: -0.1276831,
		    zoom:7
		  });
	  panel : document.getElementById("panel");
	  
	  direction = new google.maps.DirectionsRenderer({
		    map : map,
		    panel : panel // Dom element pour afficher les instructions d'itinéraire
		  });
	 GMaps.geolocate({
		    success: function(position){
		     var depl=document.getElementById("deplacement").value;
		     console.log(depl);
		     var origin    = document.getElementById('origin').value; // Le point départ
		     var destination =document.getElementById('destination').value; // Le point d'arrivé
		      map.setCenter(position.coords.latitude, position.coords.longitude);
		       map.drawRoute({
		        origin: origin,
		        destination: destination,
		        travelMode: tm,
		        strokeColor: '#000',
		        strokeOpacity: 0.6,
		        strokeWeight: 6
		       },
		        function(response, status){ // Envoie de la requête pour calculer le parcours
		    	   console.log("Fonction calcul parcours");
		            if(status == google.maps.DirectionsStatus.OK){
		            	console.log("Affiche les coord");
		                direction.setDirections(response); // Trace l'itinéraire sur la carte et les différentes étapes du parcours
		            }
		      });
		    },
		    error: function(error){
		      alert('Geolocation failed: '+error.message);
		    },
		    not_supported: function(){
		      alert("Your browser does not support geolocation");
		    }
		  });
	 map.addMarker({
	        lat: 44.870000,
	        lng: -0.590000,
	        title: 'Notre entreprise',
	        infoWindow: {
	          content: 'Piscines de qualité '
	        }
	 })
}