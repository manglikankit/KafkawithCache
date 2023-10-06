package com.manglik.Rev1.Controller;

import com.manglik.Rev1.DTO.PersonDTO;
import com.manglik.Rev1.Entity.Person;
import com.manglik.Rev1.Service.PersonServie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServie personServie;

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody @Valid Person person){
        return new ResponseEntity<>(personServie.savePerson(person), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Integer id){
        return new ResponseEntity<>(personServie.getPerson(id), HttpStatus.OK);
    }
    @GetMapping()
    public List<Person> getAlPerson(){
       return personServie.getAllPersonWithOrders();
    }

}
