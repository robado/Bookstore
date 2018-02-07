package com.example.Bookstore.controller;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping("/index")
    public String home() {
        return "index";
    }

    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/booklist")
    public String studentList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    //RESTful
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
        return repository.findOne(bookId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addStudent(Model model){
        model.addAttribute("books", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
        repository.delete(bookId);
        return "redirect:../booklist";
    }

}
