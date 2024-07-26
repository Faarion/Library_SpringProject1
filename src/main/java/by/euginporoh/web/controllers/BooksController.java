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
import org.springframework.web.bind.annotation.RequestParam;

import by.euginporoh.web.models.Book;
import by.euginporoh.web.models.Person;
import by.euginporoh.web.services.BooksService;
import by.euginporoh.web.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BooksController {
	
	private final BooksService booksService;
	private final PeopleService peopleService;

	@Autowired
	public BooksController(BooksService booksService, PeopleService peopleService) {
		super();
		this.booksService = booksService;
		this.peopleService = peopleService;
	}

	@GetMapping()
	public String index(Model model, 
			@RequestParam(required = false) Optional<Integer> page, 
			@RequestParam(required = false) Optional<Integer> books_per_page,
			@RequestParam(defaultValue = "false") Boolean sort_by_year) {
		model.addAttribute("books", booksService.findAll(page, books_per_page, sort_by_year));
		return "books/index";
	}

	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", booksService.findOne(id));

		Optional<Person> bookowner = Optional.ofNullable(booksService.findOne(id).getOwner());
		
		if (bookowner.isPresent()) {
			model.addAttribute("owner", bookowner.get());
		} else {
			model.addAttribute("people", peopleService.findAll());
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
		booksService.save(book);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("book", booksService.findOne(id));
		return "books/edit";
	}
	
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return "books/edit";
		}
		booksService.update(id, book);
		return "redirect:/books";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		booksService.delete(id);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/asign")
	public String asignPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
		booksService.asignPerson(id, person);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/free")
	public String freePerson(@PathVariable("id") int id) {
		booksService.release(id);
		return "redirect:/books/" + id;
	}
	
}
