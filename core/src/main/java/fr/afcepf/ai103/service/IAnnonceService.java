package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Reponse;

public interface IAnnonceService {

	List<Annonce> getAnnonceByUserId(int id_user);

	int getNombreReponseByIdPubli(Integer id_publi);

	Annonce update(Annonce annonce);

	List<Reponse> getListeReponseByIdPubli(Integer id_publi);

	Reponse update(Reponse reponse);


}
