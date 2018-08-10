package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Produit;

public interface IDaoProduit {

	List<Produit> GetProduitbyIDSousCategorie(int id_sous_cat);

	Produit GetProduitbyIDProduit(int id_prod);

	List<Produit> listeProduitByIdUtilisateur(int id_user);

}
