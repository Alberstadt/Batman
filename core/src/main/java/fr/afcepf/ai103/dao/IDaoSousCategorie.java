package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.SousCategorie;

public interface IDaoSousCategorie {

	List<SousCategorie> getAllSousCategories();

	List<SousCategorie> getSousCategoriebyIDCategorie(int id_cat);
	
}
