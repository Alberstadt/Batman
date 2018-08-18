package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoUtilisateur
{
	//Utilisateur getUserById(Integer id_user);


	Utilisateur verifierMotDePasse(String Pseudo, String password);

	Utilisateur create(Utilisateur u);


	List<Utilisateur> getAllUsers();

	Utilisateur update(Utilisateur sessionUtilisateur);

	List<Utilisateur> GetUsersByDescOrder();

	Utilisateur getUserById(Integer id_user);

}