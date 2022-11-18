package com.example.digitlib.controller;

import com.example.digitlib.dto.PersonDto;
import com.example.digitlib.model.Person;
import com.example.digitlib.service.impl.PersonServiceImpl;
import com.example.digitlib.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonServiceImpl personServiceImpl;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonServiceImpl personServiceImpl, PersonValidator personValidator) {
        this.personServiceImpl = personServiceImpl;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personServiceImpl.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personServiceImpl.findOne(id));
        model.addAttribute("books", personServiceImpl.getBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid PersonDto personDto,
                         BindingResult bindingResult) {
        personValidator.validate(personDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personServiceImpl.save(personDto);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personServiceImpl.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid PersonDto personDto, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(personDto, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        personServiceImpl.update(personDto, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personServiceImpl.delete(id);
        return "redirect:/people";
    }
}
