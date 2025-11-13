package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;

/**
 * 
 * @author jgomezm
 *
 */
public interface VetService {

	/**
	 * 
	 * @param vetDTO
	 * @return
	 */
	public VetDTO create(VetDTO vetDTO);

	/**
	 * 
	 * @param vet
	 * @return
	 */
	VetDTO update(VetDTO vet);

	/**
	 * 
	 * @param id
	 * @throws VetNotFoundException
	 */
	void delete(Integer id) throws VetNotFoundException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	VetDTO findById(Integer id) throws VetNotFoundException;

	/**
	 * 
	 * @param firstName
	 * @return
	 */
	List<VetDTO> findByFirstName(String firstName);

	/**
	 * 
	 * @param lastName
	 * @return
	 */
	List<VetDTO> findByLastName(String lastName);

	/**
	 *
	 * @return
	 */
	List<Vet> findAll();
}

