package fr.afcepf.ai103.dao;

import fr.afcepf.ai103.data.Adresse;


public interface IDaoAdresse 
{

	Adresse create(Adresse adresse, int id_user);

	Adresse update(Adresse adresse);

	void delete(Adresse adresse);

	Adresse getAdresseById(Integer id_adresse);

}
