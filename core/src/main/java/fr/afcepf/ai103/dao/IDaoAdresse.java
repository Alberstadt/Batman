package fr.afcepf.ai103.dao;

import fr.afcepf.ai103.data.Adresse;

public interface IDaoAdresse
{
	Adresse create(Adresse adresse);

	Adresse update(Adresse adresse);

	void delete(Integer id);

	Adresse getAdresseById(Integer id_adresse);
}
