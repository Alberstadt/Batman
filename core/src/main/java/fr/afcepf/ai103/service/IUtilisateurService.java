package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Utilisateur;

public interface IUtilisateurService {

	Utilisateur verifierMotDePasse(String Pseudo);

	Utilisateur inscription(Utilisateur u);

	Utilisateur getUtilisateur1ByIdUser(int id_friend);
	
	Utilisateur getUtilisateur2ByIdUser(int id_user);

	List<Utilisateur> getAllUsers();

}