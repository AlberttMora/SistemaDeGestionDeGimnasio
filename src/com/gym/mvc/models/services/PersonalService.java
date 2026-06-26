package com.gym.mvc.models.services;

import java.util.List;

import com.gym.mvc.models.Personal;
import com.gym.mvc.models.repositories.PersonalRepository;

public class PersonalService {

    private PersonalRepository repository;

    public PersonalService() {
        repository = new PersonalRepository();
    }

    public List<Personal> getAll() {
        return repository.readAll();
    }

}