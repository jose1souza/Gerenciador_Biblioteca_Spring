package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Book;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Category;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.AdrressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.BookRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.CategoryRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.UserRepository;

@Component
public class InitializeDataBase implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdrressRepository adrressRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public void run(String... args) throws Exception {
		Address aEmerson = new Address();
		aEmerson.setPlace("Rua emerson");
		aEmerson.setNumber("10");
		aEmerson.setZipCode("130000");
		adrressRepository.save(aEmerson);
		
		Address aLuiza = new Address();
		aLuiza.setPlace("Rua luiza");
		aLuiza.setNumber("11");
		aLuiza.setZipCode("131000");
		adrressRepository.save(aLuiza);
		
		Address aAna = new Address();
		aAna.setPlace("Rua ana");
		aAna.setNumber("12");
		aAna.setZipCode("132000");
		adrressRepository.save(aAna);
		
		// Consolida a tupla no banco
		adrressRepository.flush();
				
		User emerson = new User();
		emerson.setName("Emerson Carvalho");
		emerson.setGender("M");
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);
		
		User luiza = new User();
		luiza.setName("Luiza Carvalho");
		luiza.setGender("F");
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLuiza);
		
		User ana = new User();
		ana.setName("Ana Carvalho");
		ana.setGender("F");
		ana.setEmail("ana@mail.com");
		ana.setAddress(aAna);
		
		userRepository.save(emerson);
		userRepository.save(luiza);
		userRepository.save(ana);

		Category ficcao = new Category();
		ficcao.setName("Ficção");
		ficcao.setDescription("Livros de ficção.");
		ficcao.setCode("FIC");
		ficcao.setSector("Literatura");
		categoryRepository.save(ficcao);

		Category ensino = new Category();
		ensino.setName("Ensino");
		ensino.setDescription("Livros de ensino.");
		ensino.setCode("NFIC");
		ensino.setSector("Ensino");
		categoryRepository.save(ensino);

		Book senhorDosAneis = new Book();
		senhorDosAneis.setTitle("O Senhor dos Anéis");
		senhorDosAneis.setAuthor(emerson);
		senhorDosAneis.setPublishYear(1954);
		senhorDosAneis.setCategory(ficcao);
		bookRepository.save(senhorDosAneis);

		Book sapiens = new Book();
		sapiens.setTitle("Sapiens: Uma Breve História da Humanidade");
		sapiens.setAuthor(ana);
		sapiens.setPublishYear(2011);
		sapiens.setCategory(ensino);
		bookRepository.save(sapiens);
	}
}
