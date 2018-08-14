package fr.afcepf.ai103.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.data.Utilisateur;

@ManagedBean(name="session")
@SessionScoped
public class SessionBean
{
	private Utilisateur user;
	
	public SessionBean() {}

	public Utilisateur getUser()
	{
		return user;
	}

	public void setUser(Utilisateur user)
	{
		this.user = user;
	}
}
