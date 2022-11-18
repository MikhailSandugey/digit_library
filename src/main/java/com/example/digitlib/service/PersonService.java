package com.example.digitlib.service;

import com.example.digitlib.dto.BookDto;
import com.example.digitlib.dto.PersonDto;
import com.example.digitlib.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Interface for PersonService entity
 */
public interface PersonService extends CrudService<PersonDto> {

    /**
     * Finds all persons at a service layer
     * @return List of persons
     */
    List<PersonDto> findAll();

    /**
     * Gets Person by name at a service layer
     * @param name name of Person
     * @return Person
     */
    Optional<Person> getByName(String name);

    /**
     * Gets books by id of Person at a service layer
     * @param id id of Person
     * @return List of books
     */
    List<BookDto> getBooksByPersonId(int id);

}
