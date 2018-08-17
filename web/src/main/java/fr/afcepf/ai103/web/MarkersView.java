package fr.afcepf.ai103.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
@ManagedBean
public class MarkersView implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MapModel simpleModel;
  
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        //LatLng coord1 = new LatLng(x, y); coordonnées adresse foodfriend1
        //LatLng coord2 = new LatLng(x, y); coordonnées adresse foodfriend2
        //LatLng coord3 = new LatLng(x, y); coordonnées adresse foodfriend3
        //LatLng coord4 = new LatLng(x, y); coordonnées adresse foodfriend4
          
        //Basic marker
        //simpleModel.addOverlay(new Marker(coord1, "ProduitFoodfriend1"));
        //simpleModel.addOverlay(new Marker(coord2, "ProduitFoodfriend2"));
        //simpleModel.addOverlay(new Marker(coord3, "ProduitFoodfriend3"));
        //simpleModel.addOverlay(new Marker(coord4, "ProduitFoodfriend4"));
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void recupListeAnnoncesProximite()
    {
    	
    }
    
    
    
}