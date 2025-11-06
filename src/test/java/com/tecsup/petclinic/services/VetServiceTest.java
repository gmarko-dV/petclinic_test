package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    void testCreateVet() {
        Vet vet = new Vet();
        vet.setFirstName("Juan");
        vet.setLastName("Perez");

        Vet savedVet = vetService.create(vet);

        assertNotNull(savedVet.getId());
    }

    @Test
    void testUpdateVet() {
        Vet vet = new Vet();
        vet.setFirstName("Maria");
        vet.setLastName("Lopez");
        vet = vetService.create(vet);

        vet.setLastName("López García");
        Vet updated = vetService.update(vet);

        assertEquals("López García", updated.getLastName());
    }

    @Test
    void testFindVet() {
        Vet vet = new Vet();
        vet.setFirstName("Carlos");
        vet.setLastName("Torres");
        Vet saved = vetService.create(vet);

        Optional<Vet> found = vetService.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Carlos", found.get().getFirstName());
    }

    @Test
    void testDeleteVet() {
        Vet vet = new Vet();
        vet.setFirstName("Luis");
        vet.setLastName("Reyes");
        Vet saved = vetService.create(vet);

        vetService.delete(saved.getId());
        Optional<Vet> deleted = vetService.findById(saved.getId());

        assertFalse(deleted.isPresent());
    }
}
