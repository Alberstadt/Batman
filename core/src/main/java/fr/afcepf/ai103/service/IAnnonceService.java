package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Reponse;
import fr.afcepf.ai103.data.MotifAnnulation;
import fr.afcepf.ai103.data.Utilisateur;

public interface IAnnonceService {

	List<Annonce> getAnnonceByUserId(int id_user);

	int getNombreReponseByIdPubli(Integer id_publi);

	Annonce update(Annonce annonce);

	List<Reponse> getListeReponseByIdPubli(Integer id_publi);

	Reponse update(Reponse reponse);

	void ajouterReponseAnnonce(Reponse reponse);

	Annonce getAnnonceByIdPubli(Integer id_publi);

	MotifAnnulation getMotifAnnulationByIdMotifAnnul(Integer id_motif_annul);

	List<Reponse> reponseAnnonce(Utilisateur user);

	List<Annonce> getAnnonces(Utilisateur user);

	List<Annonce> recupDemandeEnCours(Utilisateur user);

	List<Annonce> recupDemandeAnnonceAccepte(Utilisateur user);

	List<Annonce> annonceDiponible(Utilisateur user);
	


}
