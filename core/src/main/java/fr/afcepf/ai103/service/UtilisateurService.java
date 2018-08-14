package fr.afcepf.ai103.service;


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
	public Utilisateur verifierMotDePasse(String Pseudo, String password)
	{
		return daoUtilisateur.verifierMotDePasse(Pseudo, password);
	}
	
	@Override
	public Utilisateur inscription(Utilisateur u)
	{
		return daoUtilisateur.create(u);
	}
	
	@Override
	public Utilisateur getUserById(Integer id_user)
	{
		return daoUtilisateur.getUserById(id_user);
	}

	@Override
	public Utilisateur update(Utilisateur sessionUtilisateur) 
	{
		return daoUtilisateur.update(sessionUtilisateur);
	}
	
}
