 package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Contact;
import fr.afcepf.ai103.data.Utilisateur;

public interface IContactService {

	 List<Contact> recupererListeDeMesFoodF(int idUser);

	Contact creerNouveauContact(Contact contact);

	Contact mettreAjourContact(Contact contact);

	Contact recupererContactByIdFriend(int id_friend);

	Contact recupererContactByIdUser(int idUser);

	Contact recupererContactById(int idContact);

	List<Contact> getListDemandeFfEnvoyeesUser(Utilisateur user);

	List<Contact> getListDemandeRecueFfUtilisateur2(Utilisateur user);


}
