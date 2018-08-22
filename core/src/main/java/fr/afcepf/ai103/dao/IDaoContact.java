package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoContact {

	Contact create(Contact contact);

	Contact update(Contact contact);

	void delete(int id_contact);

	Contact getContactById(int id_contact);

	Contact getContactByIdUser(int idUser);

	Contact getContactByIdFriend(int id_friend);

	List<Contact> listeDesContactsDeUser(int idUser);

	List<Contact> getListDemandeFfEnvoyeesUser(Utilisateur user);

	List<Contact> getListDemandeRecueFfUtilisateur2(Utilisateur user);
	

}
