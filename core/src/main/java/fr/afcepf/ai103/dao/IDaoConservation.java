package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Conservation;

public interface IDaoConservation {

	List<Conservation> getAllConservation();

	Conservation GetConservationByIDConservation(int id_conserv);

}
