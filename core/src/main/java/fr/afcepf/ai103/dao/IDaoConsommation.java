package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Consommation;

public interface IDaoConsommation {

	// methode

	Double getQuantiteConsoById(Integer id_prod_stock);

	Consommation create(Consommation c);

	List<Consommation> listeProduitConsommerByIdUtilisateur(int id_user);

}