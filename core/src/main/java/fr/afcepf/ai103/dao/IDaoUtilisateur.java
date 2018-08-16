package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoUtilisateur
{
	//Utilisateur getUserById(Integer id_user);

	Utilisateur getUtilisateurByIDUser(int id_user);

	Utilisateur verifierMotDePasse(String Pseudo);

	Utilisateur create(Utilisateur u);

	List<Utilisateur> getAllUsers();
}