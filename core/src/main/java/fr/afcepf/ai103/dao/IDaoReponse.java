package fr.afcepf.ai103.dao;

import java.util.Date;
import java.util.List;

import fr.afcepf.ai103.data.Reponse;
import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoReponse
{
	List<Date> getDateTransByStockId(Integer id_prod_stock);

	int getNombreReponseByIdPubli(Integer id_publi);

	Reponse ajouterReponseAnnonce(Reponse reponse) throws Exception;

	List<Reponse> reponseByUser(Utilisateur user);

	Reponse getReponseByUser(Utilisateur user);

	//List<Reponse> reponseAutreUser(Utilisateur user);

	List<Reponse> reponseAutreUser();
}