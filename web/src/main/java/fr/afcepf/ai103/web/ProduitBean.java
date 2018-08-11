package fr.afcepf.ai103.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.service.IProduitService;

@ManagedBean
@SessionScoped
public class ProduitBean 
{

	
	@EJB
	private IProduitService produitService;
	
	private String imageProduit;
	
	
	public String recupImageProd(int id_prod_stock)
	{
		imageProduit = produitService.getImageProduitById(id_prod_stock).getImage();
		return imageProduit;
	}
	
}
