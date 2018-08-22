package fr.afcepf.ai103.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ImageViewBean {
	
	private List<String> images;
	
	
	@PostConstruct
    public void init() {
        images = new ArrayList<String>();
        
            images.add("anti_gaspi.png");
            images.add("ensemble.jpg");
            images.add("fruit.jpg");
            images.add("gaspi.jpg");
            images.add("gaspillage.png");
            images.add("gaspillage2.jpg");
            images.add("legumes.png");
            images.add("main2.jpg");
            images.add("partage.png");
            images.add("partage3.jpg");
            images.add("partage4.jpg");
            images.add("proteger.jpg");
            
    }
	

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
