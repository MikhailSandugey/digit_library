package com.example.digitlib.service;

import com.example.digitlib.dto.BookDto;
import com.example.digitlib.dto.PersonDto;

import java.util.List;

/**
 * Interface for BookService entity
 */
public interface BookService extends CrudService<BookDto> {

    /**
     * Finds all books with or without sorting at a service layer
     * @param sortByYear true or false, with sort or without
     * @return List of books
     */
    List<BookDto> findAll(boolean sortByYear);

    /**
     * Find books with pagination at a service layer
     * @param page the required page
     * @param bookPerPage amount of books per 1 page
     * @param sortByYear true or false, with sort or without
     * @return List of books
     */
    List<BookDto> findWithPagination(Integer page, Integer bookPerPage, boolean sortByYear);

    /**
     * Find books by part of the name or full name at a service layer
     * @param name name or part of the name
     * @return List of books
     */
    List<BookDto> searchByName(String name);

    /**
     * Assigns Book to Person by id and Person at a service layer
     * @param id id of Book
     * @param personDto exact Person
     */
    void assignBook(int id, PersonDto personDto);

    /**
     * Releases Book from Person by id at a service layer
     * @param id id of Book
     */
    void releaseBook(int id);

    /**
     * Gets owner of Book at a service layer
     * @param id id of Book
     * @return Person
     */
    PersonDto getBookOwner(int id);
}
