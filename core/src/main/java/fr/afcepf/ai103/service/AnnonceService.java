package fr.afcepf.ai103.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.DaoMotifAnnulation;
import fr.afcepf.ai103.dao.IDaoAnnonce;
import fr.afcepf.ai103.dao.IDaoMotifAnnulation;
import fr.afcepf.ai103.dao.IDaoReponse;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.MotifAnnulation;
import fr.afcepf.ai103.data.Reponse;
import fr.afcepf.ai103.data.Utilisateur;


@Stateless
@Local


public class AnnonceService implements IAnnonceService {
	
	@EJB
	private IDaoAnnonce daoAnnonce;
	
	@EJB
	private IDaoReponse daoReponse;
	
	@EJB
	private IDaoMotifAnnulation DaoMotifAnnulation;
	
	@Override
	public List<Annonce> getAnnonceByUserId(int id_user)
	{
		return daoAnnonce.getAnnonceByUserId(id_user);
	}
	
	@Override
	public int getNombreReponseByIdPubli(Integer id_publi)
	
	{
		return daoReponse.getNombreReponseByIdPubli(id_publi);
		
	}
	
	@Override
	public Annonce getAnnonceByIdPubli(Integer id_publi)
	{
		return daoAnnonce.getAnnonceById(id_publi);
	}
	
	@Override
	public MotifAnnulation getMotifAnnulationByIdMotifAnnul(Integer id_motif_annul) 
	{
		return DaoMotifAnnulation.GetMotifAnnulationByIdMotifAnnul(id_motif_annul);
	}
	 
	@Override
	public Annonce update(Annonce annonce)
	{
	
		return daoAnnonce.update(annonce);
	}
	
	@Override

	public Reponse update(Reponse reponse)
	{
		return daoReponse.update(reponse);
		
	}

	public List<Annonce> getAnnonces(Utilisateur user)
	{
		return daoAnnonce.getAnnonces(user);

	}
	
	
	@Override

	public List<Reponse> getListeReponseByIdPubli(Integer id_publi)
	{
		return daoReponse.getListeReponseByIdPubli(id_publi);
	}
	 

	public List<Reponse> reponseAnnonce(Utilisateur user)
	{
		return daoReponse.reponseByUser(user);
	}
	
	@Override
	public void ajouterReponseAnnonce(Reponse reponse)
	{
		
		try {
			 daoReponse.ajouterReponseAnnonce(reponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Annonce> recupDemandeEnCours(Utilisateur user)
	{
		List<Reponse> listReponse = daoReponse.reponseByUser(user);
		List<Annonce> annoncesFavorites = new ArrayList<>();
		
		for(Reponse reponse : listReponse)
		{
			if(reponse.getAnnonce().getDateRetrait() == null && reponse.getDateAnnulation() == null && reponse.getDateSelection() == null)
			{
				annoncesFavorites.add(reponse.getAnnonce());
			}
		}
		return annoncesFavorites;

	}
	
	@Override
	public List<Annonce> recupDemandeAnnonceAccepte(Utilisateur user)
	{
		List<Reponse> listReponse = daoReponse.reponseByUser(user);
		List<Annonce> demandeAnnonceAccepte = new ArrayList<>();
		
		for(Reponse reponse : listReponse)
		{
			if(reponse.getAnnonce().getDateRetrait() == null && reponse.getDateAnnulation() == null && reponse.getDateSelection() != null)
			{
				demandeAnnonceAccepte.add(reponse.getAnnonce());
			}
		}
		return demandeAnnonceAccepte;

	}
	

	
	@Override
	public List<Annonce> annonceDiponible(Utilisateur user)
	{
		List<Annonce> annonces = daoAnnonce.getAnnonces(user);
		List<Reponse> listReponse = daoReponse.reponseAutreUser();
		List<Annonce> annonceDispo = new ArrayList<>();
		
		
		
		for(Annonce an : annonces)
		{
			Reponse rep = daoReponse.getReponseByUser(user);
				if(rep.getUtilisateur() != user && rep.getAnnonce().getDateRetrait() == null && rep.getDateAnnulation() == null)
				{
					annonceDispo.add(an);
				}
			
		}
		return annonceDispo;

	}
	
	
	


}
