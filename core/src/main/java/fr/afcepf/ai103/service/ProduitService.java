package fr.afcepf.ai103.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoProduit;
import fr.afcepf.ai103.data.Produit;

@Stateless
@Local
public class ProduitService implements IProduitService 
{

	@EJB
	private IDaoProduit daoProduit;
	
	
	/* (non-Javadoc)
	 * @see fr.afcepf.ai103.service.IProduitService#getImageProduitById(int)
	 */
	@Override
	public Produit getImageProduitById(int id_prod_stock)
	{
		return daoProduit.getImageProduitById(id_prod_stock);
	}
	
}
