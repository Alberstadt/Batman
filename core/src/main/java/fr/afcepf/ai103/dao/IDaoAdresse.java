package fr.afcepf.ai103.dao;

import fr.afcepf.ai103.data.Adresse;


public interface IDaoAdresse 
{
	Adresse update(Adresse adresse);

	void delete(Adresse adresse);

	Adresse getAdresseById(Integer id_adresse);

}
