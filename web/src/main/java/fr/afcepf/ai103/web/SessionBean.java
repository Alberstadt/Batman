package fr.afcepf.ai103.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai103.data.Utilisateur;

@ManagedBean(name="sessionBean", eager = true)
@SessionScoped
public class SessionBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
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
