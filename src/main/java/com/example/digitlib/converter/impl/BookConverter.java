package com.example.digitlib.converter.impl;

import com.example.digitlib.converter.Converter;
import com.example.digitlib.dto.BookDto;
import com.example.digitlib.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter implements Converter<Book, BookDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public BookConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Book convertDtoToObject(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }

    @Override
    public BookDto convertObjectToDto(Book entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    @Override
    public List<Book> convertDtoToObjectList(List<BookDto> dtoList) {
        return dtoList.stream()
                .map(this::convertDtoToObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> convertObjectToDtoList(List<Book> objectList) {
        return objectList.stream()
                .map(this::convertObjectToDto)
                .collect(Collectors.toList());
    }


}
