package com.example.digitlib.converter.impl;

import com.example.digitlib.converter.Converter;
import com.example.digitlib.dto.PersonDto;
import com.example.digitlib.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter implements Converter<Person, PersonDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public PersonConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Person convertDtoToObject(PersonDto dto) {
        return modelMapper.map(dto, Person.class);
    }

    @Override
    public PersonDto convertObjectToDto(Person entity) {
        return modelMapper.map(entity, PersonDto.class);
    }

    @Override
    public List<Person> convertDtoToObjectList(List<PersonDto> dtoList) {
        return dtoList.stream()
                .map(this::convertDtoToObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> convertObjectToDtoList(List<Person> objectList) {
        return objectList.stream()
                .map(this::convertObjectToDto)
                .collect(Collectors.toList());
    }
}
