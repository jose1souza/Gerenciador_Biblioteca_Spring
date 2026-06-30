package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Book;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Category;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.BookRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.CategoryRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.UserRepository;
import jakarta.validation.Valid;

@Controller
public class BookController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("livros", books);
        return "books.html";
    }

    @GetMapping("/books/form")
    public String bookForm(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("usuarios", userRepository.findAll());
        model.addAttribute("categorias", categoryRepository.findAll());
        return "book_form";
    }

    @PostMapping("/books/save")
    public String bookSave(@ModelAttribute("book") @Valid Book book,
                           BindingResult errors,
                           Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("usuarios", userRepository.findAll());
            model.addAttribute("categorias", categoryRepository.findAll());
            return "book_form";
        }

        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            userRepository.findById(book.getAuthor().getId()).ifPresent(book::setAuthor);
        }

        if (book.getCategory() != null && book.getCategory().getId() != null) {
            categoryRepository.findById(book.getCategory().getId()).ifPresent(book::setCategory);
        }

        bookRepository.save(book);
        bookRepository.flush();

        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String bookUpdate(@PathVariable("id") Integer id, Model model) {
        Book book = bookRepository.findById(id).orElse(new Book());
        model.addAttribute("book", book);
        model.addAttribute("usuarios", userRepository.findAll());
        model.addAttribute("categorias", categoryRepository.findAll());
        return "book_form";
    }

    @GetMapping("/books/delete/{id}")
    public String bookDelete(@PathVariable("id") Integer id) {
        bookRepository.findById(id).ifPresent(bookRepository::delete);
        return "redirect:/books";
    }
}
