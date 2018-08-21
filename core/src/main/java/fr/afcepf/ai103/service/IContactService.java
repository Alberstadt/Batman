 package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Contact;

public interface IContactService {

	 List<Contact> recupererListeDeMesFoodF(int idUser);

	Contact creerNouveauContact(Contact contact);

	Contact mettreAjourContact(Contact contact);

	Contact recupererContactByIdFriend(int id_friend);

	Contact recupererContactByIdUser(int idUser);

	Contact recupererContactById(int idContact);

	//void supprimerContact(int id_contact);

}
