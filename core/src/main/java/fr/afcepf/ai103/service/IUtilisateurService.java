package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;

public interface IUtilisateurService {

	Utilisateur verifierMotDePasse(String Pseudo);

	Utilisateur inscription(Utilisateur u);

	List<Adresse> recupererAdresses(Integer id_user);

}