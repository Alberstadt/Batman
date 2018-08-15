package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Unite;

public interface IDaoUnite {

	List<Unite> getAllUnite();

	Unite GetUniteByIDUnite(int id_unite);

}
