package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;

public interface IDaoAnnonce {

	Annonce create(Annonce annonce);

	Annonce update(Annonce annonce);

	void delete(Integer id);

	Annonce getAnnonceById(Integer id);

	List<Annonce> getAnnonceByUserId(int id_user);

}
