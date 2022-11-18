package com.example.digitlib.util;

import com.example.digitlib.dto.PersonDto;
import com.example.digitlib.model.Person;
import com.example.digitlib.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public PersonValidator(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDto personDto = (PersonDto) target;
        if (personServiceImpl.getByName(personDto.getName()).isPresent()) {
            errors.rejectValue("name", "", "Human with this name is already exist!");
        }
    }
}
