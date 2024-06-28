package by.euginporoh.web.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.euginporoh.web.dao.BookDAO;
import by.euginporoh.web.dao.PersonDAO;
import by.euginporoh.web.models.Book;
import by.euginporoh.web.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {
	
	private BookDAO bookDAO;
	private PersonDAO personDAO;

	@Autowired
	public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
		super();
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books", bookDAO.index());
		return "books/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookDAO.show(id));

		Optional<Person> bookowner = bookDAO.getBookOwner(id);
		
		if (bookowner.isPresent()) {
			model.addAttribute("owner", bookowner.get());
		} else {
			model.addAttribute("people", personDAO.index());
		}
		return "books/show";
	}
	
	@GetMapping("/new")
	public String newBook(@ModelAttribute("book") Book book) {
		return "books/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "books/new";
		}
		bookDAO.save(book);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("book", bookDAO.show(id));
		return "books/edit";
	}
	
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return "books/edit";
		}
		bookDAO.update(id, book);
		return "redirect:/books";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		bookDAO.delete(id);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/asign")
	public String asignPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
		bookDAO.asignPerson(id, person.getId());
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/free")
	public String freePerson(@PathVariable("id") int id) {
		bookDAO.release(id);
		return "redirect:/books/" + id;
	}
	
}
