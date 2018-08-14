package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoUtilisateur
{
	Utilisateur getUserById(Integer id_user);


	Utilisateur verifierMotDePasse(String Pseudo, String password);

	Utilisateur create(Utilisateur u);

	Utilisateur update(Utilisateur sessionUtilisateur);

	Utilisateur ajouterAdresse(Adresse adresse, Utilisateur utilisateur);

}