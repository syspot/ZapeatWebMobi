//inicia o geolocation
		function initialize() {
			// teste para a presença do geolocation
			if (navigator && navigator.geolocation) {
				// faz a requisição da posição do usuário
				navigator.geolocation.getCurrentPosition(geo_success, geo_error);
			} else {
				// use MaxMind IP to location API fallback
				printAddress(geoip_latitude(), geoip_longitude(), true);
			}
		}
	


	function geo_success(position) {
    	printAddress(position.coords.latitude, position.coords.longitude);
	}

	function geo_error(err) {
    	// instead of displaying an error, fall back to MaxMind IP to location library
    	printAddress(geoip_latitude(), geoip_longitude(), true);
	}

	// usa Google Maps API para reversão da sua localização do geocode
	function printAddress(latitude, longitude, isMaxMind) {
		
		// set up the Geocoder object
		var geocoder = new google.maps.Geocoder();
	 
		// turn coordinates into an object
		var yourLocation = new google.maps.LatLng(latitude, longitude);
		// find out info about our location
		geocoder.geocode({ 'latLng': yourLocation }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					//--
					//captura infomação requisitada do endereço
					for (var i=0; i<results[0].address_components.length; i++) {
						
						for (var b=0; b<results[0].address_components[i].types.length;b++) {
							
							if (results[0].address_components[i].types[b] == "locality") {
								city= results[0].address_components[i];
								break;
							} else if (results[0].address_components[i].types[b] == "route") {
								route= results[0].address_components[i];
								break;
							}
							
						}
					}
					
					document.getElementById('info').innerHTML=route.long_name +', '+ city.long_name;
					document.getElementById('location').value=results[0].geometry.location;
					checkFirstTime(document.getElementById('location').value);
					
				} else {
					error('Nenhum resultado foi encontrado.');
				}
			} else {
				
			}
		});
			if(isMaxMind){
				$('body').append('<p>IP to Location Service Provided by MaxMind</p>');
			}
		}
		
		function error(msg){
			alert(msg);
		}