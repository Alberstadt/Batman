package fr.afcepf.ai103.dao;

import java.util.List;

import javax.persistence.EntityManager;

import fr.afcepf.ai103.data.ModeConso;

public interface IDaoModeConso {

	ModeConso create(ModeConso m);

	ModeConso update(ModeConso m);

	void delete(Integer id);

	ModeConso getModeConsoById(Integer id);

	List<ModeConso> getAll();

	EntityManager getEm();

	void setEm(EntityManager em);

}