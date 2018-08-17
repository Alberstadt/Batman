package fr.afcepf.ai103.service;

import fr.afcepf.ai103.data.Adresse;

public interface IAdresseService {

	Adresse ajouterAdresse(Adresse adresse, int id_user);

	void getAdresseById(int id_adresse);

	Adresse majAdresse(Adresse newAdresse);

}