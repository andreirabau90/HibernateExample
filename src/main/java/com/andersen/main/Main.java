package com.andersen.main;

import com.andersen.repository.impl.PersonRepository;
import com.andersen.repository.impl.RolesRepository;


public class Main {

    public static void main(String[] args) {
        RolesRepository rolesRepository = new RolesRepository();
        PersonRepository personRepository = new PersonRepository();
    }
}
