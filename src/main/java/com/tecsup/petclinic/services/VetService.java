package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import java.util.List;
import java.util.Optional;

public interface VetService {
    Vet create(Vet vet);
    Vet update(Vet vet);
    Optional<Vet> findById(Long id);
    void delete(Long id);
    List<Vet> findAll();
}
