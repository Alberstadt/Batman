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
	public Utilisateur verifierMotDePasse(String pseudo, String password)
	{
		return daoUtilisateur.verifierMotDePasse(pseudo, password);
	}
	
	@Override
	public Utilisateur inscription(Utilisateur u)
	{
		return daoUtilisateur.create(u);
	}
	
	@Override
	public Utilisateur getUtilisateur1ByIdUser(int id_friend)
	{
		return daoUtilisateur.getUserById(id_friend);
	}
	
	@Override
	public Utilisateur getUtilisateur2ByIdUser(int idUser)
	{
		return daoUtilisateur.getUserById(idUser);
	}
	
	@Override
	public List<Utilisateur> getAllUsers()
	{
		return daoUtilisateur.getAllUsers();
	}	
		
	@Override
	public Utilisateur getUserById(Integer idUser)
	{
		return daoUtilisateur.getUserById(idUser);
	}

	@Override
	public Utilisateur update(Utilisateur user) 
	{
		return daoUtilisateur.update(user);
	}
	
	@Override
	public List<Adresse> recupererAdresses(Integer idUser)
	{
		Utilisateur user = daoUtilisateur.getUserById(idUser);
		List<Adresse> adresses = user.getAdresses();
		//Pour contourner le lazy initialize
		adresses.size();
		
		return adresses;

	}
	
	@Override
	public Utilisateur GetLastUser()
	{
		List<Utilisateur> users = daoUtilisateur.GetUsersByDescOrder();
		return users.get(0);
	}
	
}
