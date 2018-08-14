package fr.afcepf.ai103.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoAdresse;
import fr.afcepf.ai103.data.Adresse;

@Stateless
@Local
public class AdresseService implements IAdresseService 
{

	@EJB
	private IDaoAdresse daoAdresse;


	@Override
	public Adresse ajouterAdresse(Adresse adresse, int id_user)
	{
		System.out.println("SERVICE ajouter adresse");

			return daoAdresse.create(adresse, id_user);

	}
	
	@Override
	public void getAdresseById(int id_adresse)
	{
		daoAdresse.getAdresseById(id_adresse);
	}

	@Override
	public Adresse majAdresse(Adresse newAdresse) 
	{
		System.out.println("SERVICE maj adresse");

		return daoAdresse.update(newAdresse);
	}

}
