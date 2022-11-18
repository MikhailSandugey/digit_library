package com.example.digitlib.service.impl;

import com.example.digitlib.converter.impl.BookConverter;
import com.example.digitlib.converter.impl.PersonConverter;
import com.example.digitlib.dto.BookDto;
import com.example.digitlib.dto.PersonDto;
import com.example.digitlib.model.Person;
import com.example.digitlib.repository.PersonRepository;
import com.example.digitlib.service.PersonService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final BookConverter bookConverter;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             PersonConverter personConverter,
                             BookConverter bookConverter) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.bookConverter = bookConverter;
    }

    public List<PersonDto> findAll() {
        return personConverter.convertObjectToDtoList(personRepository.findAll());
    }

    public PersonDto findOne(int id) {
        return personConverter.convertObjectToDto(personRepository.findById(id).orElse(null));
    }

    @Transactional
    public void save(PersonDto personDto) {
        personRepository.save(personConverter.convertDtoToObject(personDto));
    }

    @Transactional
    public void update(PersonDto personDto, int id) {
        Person personToUpdate = personRepository.findById(id).get();
        Person person = personConverter.convertDtoToObject(personDto);
        person.setId(id);
        person.setBooks(personToUpdate.getBooks());
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getByName(String name) {
        return personRepository.findByName(name);
    }

    public List<BookDto> getBooksByPersonId(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            person.get().getBooks().forEach(book -> {
                long diffResult = new Date().getTime() - book.getTakenAt().getTime();
                System.out.println(diffResult);
                if (diffResult > 864000000) {
                    book.setExpired(true);
                }
            });
            return bookConverter.convertObjectToDtoList(person.get().getBooks());
        } else {
            return Collections.emptyList();
        }
    }
}
