package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;

public interface IUtilisateurService {

	Utilisateur verifierMotDePasse(String Pseudo, String password);

	Utilisateur inscription(Utilisateur u);


	Utilisateur getUserById(Integer id_user);

	Utilisateur update(Utilisateur sessionUtilisateur);

	List<Adresse> recupererAdresses(Integer id_user);

	Utilisateur GetLastUser();

}