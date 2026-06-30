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

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.AdrressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdrressRepository addressRepository;
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		
		List<User> users = userRepository.findAll();
		model.addAttribute("usuarios", users);
		
		return "users.html";
	}
	
	@GetMapping("/users/form")
	public String userForm(@ModelAttribute("user") 
	                       User user) {
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String userSave(@ModelAttribute("user")
						   @Valid
	                       User user,
	                       BindingResult errors) {
		if (errors.hasErrors())
			return "user_form";
		
		addressRepository.save(user.getAddress());
		addressRepository.flush();
		userRepository.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/{id}")
	public String userUpdate(@PathVariable("id") 
	                         Integer id,
	                         Model model) {
		
		Optional<User> userOpt = userRepository.findById(id);
		
		User user = null;
		if (userOpt.isPresent())
			user = userOpt.get();
		
		model.addAttribute("user", user);
		
		return "user_form";
	}
	
	@GetMapping("/users/delete/{id}")
	public String userDelete(@PathVariable("id") 
	                         Integer id) {
		
		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isPresent()) {
			Address address = userOpt.get().getAddress();
			
			userRepository.delete(userOpt.get());
			addressRepository.delete(address);
        }

		
		return "redirect:/users";
	}
}
