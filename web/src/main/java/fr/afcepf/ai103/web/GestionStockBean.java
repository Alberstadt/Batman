package fr.afcepf.ai103.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.data.Stock;
import fr.afcepf.ai103.service.IStockService;
import fr.afcepf.ai103.service.IUtilisateurService;

@ManagedBean
@SessionScoped
public class GestionStockBean implements Serializable 
{

	@EJB
	private IStockService stockService;
	
	@EJB
	private IUtilisateurService utilisateurService;
	
	@ManagedProperty(value="#{sessionMB}")
	private LoginBean sessionMB;
	
	private static final long serialVersionUID = 1L;
	
	private List<Stock> stocks;
	
	@PostConstruct
	public void init() 
	{
		stocks = stockService.listerProdDispo(sessionMB.getSessionUtilisateur().getId_user());		
	}
	
	private Stock stk;
	
	
	public GestionStockBean()
	{
		
	}

	
	public List<Stock> getStocks() {
		return stocks;
	}
	
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public Stock getStk() {
		return stk;
	}
	
	public void setStk(Stock stk) {
		this.stk = stk;
	}


	public IStockService getStockService() {
		return stockService;
	}


	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}


	public IUtilisateurService getUtilisateurService() {
		return utilisateurService;
	}


	public void setUtilisateurService(IUtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}


	public LoginBean getSessionMB() {
		return sessionMB;
	}


	public void setSessionMB(LoginBean sessionMB) {
		this.sessionMB = sessionMB;
	}
	  
  
  
}
