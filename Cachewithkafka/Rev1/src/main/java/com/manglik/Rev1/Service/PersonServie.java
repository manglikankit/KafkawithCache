package com.manglik.Rev1.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manglik.Rev1.DTO.Order;
import com.manglik.Rev1.DTO.PersonDTO;
import com.manglik.Rev1.Entity.Person;
import com.manglik.Rev1.Repo.PersonRepo;
import com.manglik.Rev1.config.PersonNotFoundException;
import com.manglik.Rev1.httpclient.OrderServiceClient;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static java.lang.Thread.sleep;

@Service
@Slf4j
public class PersonServie {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    JavaMailSender javaMailSender;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private OrderServiceClient orderServiceClient;
    @Autowired
    private RedisCommands<String, String> redisSync;

    public PersonServie(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person savePerson(Person person) {
        return personRepo.save(person);
    }

    public List<Person> getAllPersonWithOrders() {
        String key = "allPerson";
        String res = redisSync.get(key);
        List<Person> personList = null;
        if (StringUtils.isEmpty(res)) {
            try {
                sleep(2);
//                redisSync.ttl(String.valueOf(10));
                System.out.println("Calling service from DB");
                personList = personRepo.findAll();
                try {
                    redisSync.set(key, mapper.writeValueAsString(personList));
                } catch (JsonProcessingException e) {
                    log.error("error in serialisation", e);
                }
                return personList;
            }
            catch (InterruptedException e){
                System.out.println("Got exception");
            }
        } else {
            log.info("serving from cache");
            try {
               personList = mapper.readValue(res,
                        mapper.getTypeFactory().constructCollectionType(List.class, Person.class));
                return personList;
            } catch (JsonProcessingException e) {
                log.error("Error in deserialization", e);
            }
        }
        return personList;
    }

    public PersonDTO getPerson(int id) {
        String key = "person_" + id;

        String res = redisSync.get(key);
        PersonDTO personDTO1=null;
        if (StringUtils.isEmpty(res)) {
            log.info("going call db");
             personDTO1 = personRepo.findById(id).map((person) -> {
                List<Order> orders = orderServiceClient.getOrder(person.getId());
                PersonDTO personDTO = new PersonDTO();
                personDTO.email = person.email;
                personDTO.name = person.name;
                personDTO.orderList = orders ;
                try {
                    redisSync.set(key, mapper.writeValueAsString(personDTO));
                } catch (JsonProcessingException e) {
                    log.error("error in serialisation", e);
                }
                return personDTO;
            }).orElseThrow(() -> new PersonNotFoundException("person not found", new Throwable("not found")));
        } else {
            log.info("serving from cache");
           return mapper.convertValue(res, PersonDTO.class);
        }
        return personDTO1;
    }

    public void sendMail(int id) {
        String email = personRepo.getPersonEmailById(id);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("manglikharshita25@gmail.com");
        mail.setSubject("Order confirmation");
        mail.setFrom(email);
        mail.setText("Test msg");
        javaMailSender.send(mail);
        System.out.println("Mail sent successfully...");
    }

    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }
}
