package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoAnnonce;
import fr.afcepf.ai103.dao.IDaoReponse;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Reponse;

@Stateless
@Local


public class AnnonceService implements IAnnonceService {
	
	@EJB
	private IDaoAnnonce daoAnnonce;
	
	@EJB
	
	private IDaoReponse daoReponse;
	
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
	public Annonce update(Annonce annonce)
	{
	
		return daoAnnonce.update(annonce);
	}
	
	@Override
	public Reponse update(Reponse reponse)
	{
		return daoReponse.update(reponse);
	}
	
	
	@Override
	public List<Reponse> getListeReponseByIdPubli(Integer id_publi)
	{
		return daoReponse.getListeReponseByIdPubli(id_publi);
	}
	 
}
