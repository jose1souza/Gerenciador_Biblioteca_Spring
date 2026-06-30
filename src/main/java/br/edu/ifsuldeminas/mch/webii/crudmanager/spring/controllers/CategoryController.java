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

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Category;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categorias", categories);
        return "categories.html";
    }

    @GetMapping("/categories/form")
    public String categoryForm(@ModelAttribute("category") Category category) {
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String categorySave(@ModelAttribute("category") 
                               @Valid Category category,
                               BindingResult errors) {
        if (errors.hasErrors()) {
            return "category_form";
        }
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}")
    public String categoryUpdate(@PathVariable("id") Integer id, Model model) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            model.addAttribute("category", categoryOpt.get());
        }
        return "category_form";
    }

    @GetMapping("/categories/delete/{id}")
    public String categoryDelete(@PathVariable("id") Integer id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            categoryRepository.delete(categoryOpt.get());
        }
        return "redirect:/categories";
    }
}
