package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Adresse;
import fr.afcepf.ai103.data.ModeConso;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class DaoUtilisateur implements IDaoUtilisateur 
{
	@PersistenceContext(unitName="core")
	private EntityManager entityManager;
	
	public DaoUtilisateur(){}

	@Override
	public Utilisateur verifierMotDePasse (String Pseudo, String password)
	{
		Utilisateur u = null;
		try 
		{
			u = entityManager.createQuery("SELECT p FROM Utilisateur p WHERE p.login = :Pseudo AND p.password = :password", Utilisateur.class)
					.setParameter("Pseudo", Pseudo)
					.setParameter("password", password)
					.getSingleResult();
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public Utilisateur ajouterAdresse(Adresse adresse, Utilisateur utilisateur)
	{
        utilisateur.getAdresses().add(adresse);
        adresse.getUtilisateurs().add(utilisateur);
        entityManager.persist(utilisateur);
		return utilisateur;
	}
	
	@Override
	public Utilisateur getUserById(Integer id_user)
	{
		System.out.println("id_user envoyée à getuserbyid " + id_user);
		return entityManager.find(Utilisateur.class, id_user);
	}
	
	@Override
	public Utilisateur create(Utilisateur u)
	{
		entityManager.persist(u);
		return u;
	}
	
	@Override
	public Utilisateur update(Utilisateur sessionUtilisateur)
	{
		return entityManager.merge(sessionUtilisateur);
	}

}
