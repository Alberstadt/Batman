function init_map()
		{
			var myOptions = {zoom:10,center:new google.maps.LatLng(48.8647522,2.378097300000036),mapTypeId: google.maps.MapTypeId.ROADMAP};
			map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);
			marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(48.8647522,2.378097300000036)});
			infowindow = new google.maps.InfoWindow({content:'<strong>Titre</strong><br>57 avenue de la république Paris, France</br>'});
			google.maps.event.addListener(marker, 'click', function(){infowindow.open(map,marker);});
			infowindow.open(map,marker);
		}
			google.maps.event.addDomListener(window, 'load', init_map);