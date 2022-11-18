package com.example.digitlib.service.impl;

import com.example.digitlib.converter.impl.BookConverter;
import com.example.digitlib.converter.impl.PersonConverter;
import com.example.digitlib.dto.BookDto;
import com.example.digitlib.dto.PersonDto;
import com.example.digitlib.model.Book;
import com.example.digitlib.model.Person;
import com.example.digitlib.repository.BooksRepository;
import com.example.digitlib.repository.PersonRepository;
import com.example.digitlib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;
    private final PersonRepository personRepository;
    private final BookConverter bookConverter;
    private final PersonConverter personConverter;

    @Autowired
    public BookServiceImpl(BooksRepository booksRepository,
                           PersonRepository personRepository,
                           BookConverter bookConverter,
                           PersonConverter personConverter) {
        this.booksRepository = booksRepository;
        this.personRepository = personRepository;
        this.bookConverter = bookConverter;
        this.personConverter = personConverter;
    }

    public List<BookDto> findAll(boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(Sort.by("year")).stream()
                    .map(bookConverter::convertObjectToDto)
                    .collect(Collectors.toList());
        } else {
            return booksRepository.findAll().stream()
                    .map(bookConverter::convertObjectToDto)
                    .collect(Collectors.toList());
        }
    }

    public List<BookDto> findWithPagination(Integer page, Integer bookPerPage, boolean sortByYear) {
        if (sortByYear) {
            return bookConverter.convertObjectToDtoList(
                    booksRepository.findAll(PageRequest.of(page, bookPerPage, Sort.by("year"))).getContent());
        } else {
            return bookConverter.convertObjectToDtoList(
                    booksRepository.findAll(PageRequest.of(page, bookPerPage)).getContent());
        }
    }

    public BookDto findOne(int id) {
        return bookConverter.convertObjectToDto(booksRepository.findById(id).orElse(null));
    }

    public List<BookDto> searchByName(String name) {
        return bookConverter.convertObjectToDtoList(booksRepository.findByNameStartingWith(name));
    }

    @Transactional
    public void save(BookDto bookDto) {
        booksRepository.save(bookConverter.convertDtoToObject(bookDto));
    }

    @Transactional
    public void update(BookDto bookDto, int id) {
        Book bookToUpdate = booksRepository.findById(id).get();
        Book book = bookConverter.convertDtoToObject(bookDto);
        book.setId(id);
        book.setOwner(bookToUpdate.getOwner());
        book.setTakenAt(bookToUpdate.getTakenAt());
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assignBook(int id, PersonDto personDto) {
        Optional<Book> book = booksRepository.findById(id);
        Person person = personRepository.findById(personDto.getId()).get();
        if (book.isPresent()) {
            book.get().setOwner(person);
            book.get().setTakenAt(new Date());
        }
    }

    @Transactional
    public void releaseBook(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            book.get().setOwner(null);
            book.get().setTakenAt(null);
        }
    }

    public PersonDto getBookOwner(int id) {
        Person person = booksRepository.findById(id).map(Book::getOwner).orElse(null);
        if (person == null) {
            return null;
        }
        return personConverter.convertObjectToDto(booksRepository.findById(id).map(Book::getOwner).get());
    }
}
