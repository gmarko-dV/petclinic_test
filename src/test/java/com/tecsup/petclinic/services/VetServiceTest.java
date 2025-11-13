package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.dtos.VetDTO;
import org.junit.jupiter.api.Test;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.exceptions.VetNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    /**
     * Prueba de búsqueda de veterinario por ID
     */
    @Test
    public void testFindVetById() {

        String FIRST_NAME_EXPECTED = "James";
        String LAST_NAME_EXPECTED = "Carter";

        Integer ID = 1;

        VetDTO vet = null;

        try {
            vet = this.vetService.findById(ID);
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }
        assertEquals(FIRST_NAME_EXPECTED, vet.getFirstName());
        assertEquals(LAST_NAME_EXPECTED, vet.getLastName());
    }

    /**
     * Prueba de búsqueda de veterinarios por nombre
     */
    @Test
    public void testFindVetByFirstName() {

        String FIND_FIRST_NAME = "James";
        int SIZE_EXPECTED = 1;

        List<VetDTO> vets = this.vetService.findByFirstName(FIND_FIRST_NAME);

        assertEquals(SIZE_EXPECTED, vets.size());
    }

    /**
     * Prueba de búsqueda de veterinarios por apellido
     */
    @Test
    public void testFindVetByLastName() {

        String FIND_LAST_NAME = "Carter";
        int SIZE_EXPECTED = 1;

        List<VetDTO> vets = this.vetService.findByLastName(FIND_LAST_NAME);

        assertEquals(SIZE_EXPECTED, vets.size());
    }

    /**
     * Prueba de creación de veterinario
     */
    @Test
    public void testCreateVet() {

        String VET_FIRST_NAME = "John";
        String VET_LAST_NAME = "Doe";
        String VET_EMAIL = "john.doe@petclinic.com";
        String VET_PHONE = "6085559999";
        Boolean VET_ACTIVE = true;

        VetDTO vetDTO = VetDTO.builder()
                .firstName(VET_FIRST_NAME)
                .lastName(VET_LAST_NAME)
                .email(VET_EMAIL)
                .phone(VET_PHONE)
                .active(VET_ACTIVE)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);

        log.info("VET CREATED :" + newVetDTO.toString());

        assertNotNull(newVetDTO.getId());
        assertEquals(VET_FIRST_NAME, newVetDTO.getFirstName());
        assertEquals(VET_LAST_NAME, newVetDTO.getLastName());
        assertEquals(VET_EMAIL, newVetDTO.getEmail());
        assertEquals(VET_PHONE, newVetDTO.getPhone());
        assertEquals(VET_ACTIVE, newVetDTO.getActive());
    }

    /**
     * Prueba de actualización de veterinario
     */
    @Test
    public void testUpdateVet() {

        String VET_FIRST_NAME = "Jane";
        String VET_LAST_NAME = "Smith";
        String VET_EMAIL = "jane.smith@petclinic.com";
        String VET_PHONE = "6085558888";
        Boolean VET_ACTIVE = true;

        String UP_VET_FIRST_NAME = "Jane";
        String UP_VET_LAST_NAME = "Smith-Jones";
        String UP_VET_EMAIL = "jane.smithjones@petclinic.com";
        String UP_VET_PHONE = "6085557777";
        Boolean UP_VET_ACTIVE = false;

        VetDTO vetDTO = VetDTO.builder()
                .firstName(VET_FIRST_NAME)
                .lastName(VET_LAST_NAME)
                .email(VET_EMAIL)
                .phone(VET_PHONE)
                .active(VET_ACTIVE)
                .build();

        // ------------ Create ---------------

        log.info(">" + vetDTO);
        VetDTO vetDTOCreated = this.vetService.create(vetDTO);
        log.info(">>" + vetDTOCreated);

        // ------------ Update ---------------

        // Prepare data for update
        vetDTOCreated.setLastName(UP_VET_LAST_NAME);
        vetDTOCreated.setEmail(UP_VET_EMAIL);
        vetDTOCreated.setPhone(UP_VET_PHONE);
        vetDTOCreated.setActive(UP_VET_ACTIVE);

        // Execute update
        VetDTO upgradeVetDTO = this.vetService.update(vetDTOCreated);
        log.info(">>>>" + upgradeVetDTO);

        //            EXPECTED        ACTUAL
        assertEquals(UP_VET_FIRST_NAME, upgradeVetDTO.getFirstName());
        assertEquals(UP_VET_LAST_NAME, upgradeVetDTO.getLastName());
        assertEquals(UP_VET_EMAIL, upgradeVetDTO.getEmail());
        assertEquals(UP_VET_PHONE, upgradeVetDTO.getPhone());
        assertEquals(UP_VET_ACTIVE, upgradeVetDTO.getActive());
    }

    /**
     * Prueba de eliminación de veterinario
     */
    @Test
    public void testDeleteVet() {

        String VET_FIRST_NAME = "Robert";
        String VET_LAST_NAME = "Brown";
        String VET_EMAIL = "robert.brown@petclinic.com";
        String VET_PHONE = "6085556666";
        Boolean VET_ACTIVE = true;

        // ------------ Create ---------------

        VetDTO vetDTO = VetDTO.builder()
                .firstName(VET_FIRST_NAME)
                .lastName(VET_LAST_NAME)
                .email(VET_EMAIL)
                .phone(VET_PHONE)
                .active(VET_ACTIVE)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);
        log.info("" + vetDTO);

        // ------------ Delete ---------------

        try {
            this.vetService.delete(newVetDTO.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        // ------------ Validation ---------------

        try {
            this.vetService.findById(newVetDTO.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }
}
