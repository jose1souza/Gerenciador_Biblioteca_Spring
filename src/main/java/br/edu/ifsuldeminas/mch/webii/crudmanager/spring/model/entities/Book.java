package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "livros")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O título não pode ser vazio.")
	private String title;

	@ManyToOne
	@JoinColumn(name = "author_id")
	@NotNull(message = "O author do livro é obrigatório.")
	private User author;

	@NotNull(message = "Ano de publicação é obrigatório.")
	@Digits(integer = 4, fraction = 0, message = "Ano deve ter 4 dígitos.")
	private Integer publishYear;

	@ManyToOne
    @JoinColumn(name = "category_id")
	@NotNull(message = "A categoria do livro é obrigatória.")
	private Category category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Integer getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Integer publishYear) {
		this.publishYear = publishYear;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
