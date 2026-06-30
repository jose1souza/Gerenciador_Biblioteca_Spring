package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity(name="usuarios")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message="O nome do usuário não pode ser vazio.")
	private String name;
	
	@NotBlank(message="O gênero do usuário não pode ser vazio.")
	@Length(max = 1, message = "Genero deve ter apenas 1 caracter.")
	private String gender;
	
	@NotBlank(message="O email do usuário não pode ser vazio.")
	@Email(message = "Endereço de email inválido.")
	private String email;
	
	@OneToOne(optional = false)
	@Valid
	private Address address;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> livros;
	
	public User() {}
	
	User(Integer id){
		this.id = id;
		setName("");
		setGender("");
		setEmail("");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}
