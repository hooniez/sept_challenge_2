package com.example.person.controller;

import com.example.person.dao.PersonRepository;
import com.example.person.model.Person;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/person")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        return ResponseEntity.accepted().body(personRepository.save(person));
    }

    @GetMapping(path="/person{id}")
    public ResponseEntity<?> findPersonById(@PathVariable("id") Long id) {
        return ResponseEntity.accepted().body(personRepository.findById(id).get());
    }

    @GetMapping(path="/persons")
    public List<Person> findAllPeople() {
        return personRepository.findAll();
    }

    @PutMapping("/person")
    public ResponseEntity<?> updatePerson(@RequestBody Person person) {
        return ResponseEntity.accepted().body(personRepository.save(person));
    }

    @DeleteMapping("/person{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personRepository.deleteById(id);
    }

}
