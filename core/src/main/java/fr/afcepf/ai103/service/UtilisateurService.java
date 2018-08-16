package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoUtilisateur;
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
	public Utilisateur getUtilisateur1ByIdUser(int id_friend)
	{
		return daoUtilisateur.getUtilisateurByIDUser(id_friend);
	}
	
	@Override
	public Utilisateur getUtilisateur2ByIdUser(int id_user)
	{
		return daoUtilisateur.getUtilisateurByIDUser(id_user);
	}
	
	@Override
	public List<Utilisateur> getAllUsers()
	{
		return daoUtilisateur.getAllUsers();
	}
}
