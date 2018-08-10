package fr.afcepf.ai103.service;

import fr.afcepf.ai103.data.Utilisateur;

public interface IUtilisateurService {

	Utilisateur verifierMotDePasse(String Pseudo);

	Utilisateur inscription(Utilisateur u);

}