package com.example.digitlib.service;

/**
 * Interface for CRUD service operations at Person/Book entity
 * @param <T> generic type for service
 */

public interface CrudService<T> {
    /**
     * Gets Person/Book by id at a service layer
     * @param id id of Person/Book
     * @return Person/Book
     */
    T findOne(int id);

    /**
     * Adds new Person/Book at a service layer
     * @param t object to add
     */
    void save(T t);

    /**
     * Updates Person/Book by Person/Book and id at a service layer
     * @param t Person/Book
     * @param id id of Person/Book
     */
    void update(T t, int id);

    /**
     * Deletes Person/Book by id at a service layer
     * @param id id of Person/Book
     */
    void delete(int id);
}
