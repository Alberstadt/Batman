package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;

public interface IAnnonceService {

	List<Annonce> getAnnonceByUserId(int id_user);

	int getNombreReponseByIdPubli(Integer id_publi);

	Annonce update(Annonce annonce);

}
