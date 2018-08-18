 package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Contact;

public interface IContactService {

	 List<Contact> recupererListeDeMesFoodF(int id_user);

	Contact creerNouveauContact(Contact contact);

	Contact mettreAjourContact(Contact contact);

	Contact recupererContactByIdFriend(int id_friend);

	Contact recupererContactByIdUser(int id_user);

	Contact recupererContactById(int id_contact);

	//void supprimerContact(int id_contact);

}
