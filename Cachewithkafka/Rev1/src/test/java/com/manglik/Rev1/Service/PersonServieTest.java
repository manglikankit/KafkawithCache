package com.manglik.Rev1.Service;

import com.manglik.Rev1.Repo.PersonRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServieTest {

    @Mock
    private PersonRepo personRepo;
    private PersonServie personServie;

    @BeforeEach
    void tearDown() {
        System.out.println("Tearing down");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setting Up");
        personServie = new PersonServie(personRepo);
    }
    @Test
    void getAllPerson() {
        personServie.getAllPerson();
        verify(personRepo).findAll();
    }
}