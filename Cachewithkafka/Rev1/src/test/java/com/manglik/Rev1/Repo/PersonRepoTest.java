package com.manglik.Rev1.Repo;

import com.manglik.Rev1.Entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class PersonRepoTest {

    @Mock
    private PersonRepo personRepo;
//    private PersonServie personServie;

    @Test
    void isPersonExistById() {

        Person person = new Person(20, "a@m.com", "Testname");
        personRepo.save(person);
        Boolean actualResult = personRepo.isPersonExistById(person.getId());
        assertThat(actualResult).isTrue();

    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setting Up");
    }
}