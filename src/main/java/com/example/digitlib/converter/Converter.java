package com.example.digitlib.converter;

import java.util.List;

public interface Converter<O, D> {

    /**
     * Converts Data transfer object to an entity type
     * @param dto
     * @return object of a needed type
     */
    O convertDtoToObject(D dto);

    /**
     * Converts an entity type to Data transfer object
     * @param entity
     * @return object of a needed type
     */
    D convertObjectToDto(O entity);

    /**
     * Converts list of Data transfer object to a list of entity type
     * @param dtoList
     * @return object list of a needed type
     */
    List<O> convertDtoToObjectList(List<D> dtoList);

    /**
     * Converts list of entity type to list of Data transfer objects
     * @param objectList
     * @return object list of a needed type
     */
    List<D> convertObjectToDtoList(List<O> objectList);
}
