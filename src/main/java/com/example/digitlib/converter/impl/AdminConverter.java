package com.example.digitlib.converter.impl;

import com.example.digitlib.converter.Converter;
import com.example.digitlib.dto.AdminDto;
import com.example.digitlib.model.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminConverter implements Converter<Admin, AdminDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public AdminConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Admin convertDtoToObject(AdminDto dto) {
        return modelMapper.map(dto, Admin.class);
    }

    @Override
    public AdminDto convertObjectToDto(Admin entity) {
        return modelMapper.map(entity, AdminDto.class);
    }

    @Override
    public List<Admin> convertDtoToObjectList(List<AdminDto> dtoList) {
        return dtoList.stream()
                .map(this::convertDtoToObject)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminDto> convertObjectToDtoList(List<Admin> objectList) {
        return objectList.stream()
                .map(this::convertObjectToDto)
                .collect(Collectors.toList());
    }
}
