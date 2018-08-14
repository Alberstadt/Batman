package fr.afcepf.ai103.service;

import fr.afcepf.ai103.data.Adresse;

public interface IAdresseService {

	void getAdresseById(int id_adresse);

	Adresse majAdresse(Adresse newAdresse);

}