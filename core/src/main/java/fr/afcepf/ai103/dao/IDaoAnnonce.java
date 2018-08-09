package fr.afcepf.ai103.dao;

import fr.afcepf.ai103.data.Annonce;

public interface IDaoAnnonce {

	Annonce create(Annonce annonce);

	Annonce update(Annonce annonce);

	void delete(Integer id);

	Annonce getAnnonceById(Integer id);

}
