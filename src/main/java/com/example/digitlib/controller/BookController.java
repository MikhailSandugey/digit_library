package com.example.digitlib.controller;

import com.example.digitlib.model.Book;
import com.example.digitlib.model.Person;
import com.example.digitlib.service.impl.BookServiceImpl;
import com.example.digitlib.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookServiceImpl;
    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public BookController(BookServiceImpl bookServiceImpl, PersonServiceImpl personServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping()
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
                        Model model) {
        if (page == null || booksPerPage == null) {
            model.addAttribute("books", bookServiceImpl.findAll(sortByYear));
        } else {
            model.addAttribute("books", bookServiceImpl.findWithPagination(page, booksPerPage, sortByYear));
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookServiceImpl.findOne(id));
        model.addAttribute("owner", bookServiceImpl.getBookOwner(id));
        model.addAttribute("people", personServiceImpl.findAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookServiceImpl.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookServiceImpl.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookServiceImpl.update(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookServiceImpl.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookServiceImpl.assignBook(id, person);
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookServiceImpl.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "books/search";
    }

    @PostMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        model.addAttribute("books", bookServiceImpl.searchByName(name));
        return "books/search";
    }

}
