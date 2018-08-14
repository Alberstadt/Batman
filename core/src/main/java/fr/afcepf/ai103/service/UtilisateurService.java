package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoUtilisateur;
import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class UtilisateurService implements IUtilisateurService 
{

	@EJB
	private IDaoUtilisateur daoUtilisateur;
	
	@Override
	public Utilisateur verifierMotDePasse(String Pseudo)
	{
		return daoUtilisateur.verifierMotDePasse(Pseudo);
	}
	
	@Override
	public Utilisateur inscription(Utilisateur u)
	{
		//System.out.println("methode inscription() UtilisateurService");

		return daoUtilisateur.create(u);
	}
	
	@Override
	public List<Adresse> recupererAdresses(Integer id_user)
	{
		Utilisateur user = daoUtilisateur.getUserById(id_user);
		List<Adresse> adresses = user.getAdresses();
		//Pour contourner le lazy initialize
		adresses.size();
		
		return adresses;
	}
}
