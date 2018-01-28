package io.public_library.book;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.public_library.person.Person;
import io.public_library.person.PersonService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String homePage(Model model) {
		List<Book> booksList = bookService.getAllBooks();
		List<Person> personsList = personService.getAllPersons();
		model.addAttribute("book", new Book());
		model.addAttribute("person", new Person());
		model.addAttribute("booksList", booksList);
		model.addAttribute("personsList", personsList);
		//List<Book> books = new ArrayList<>(bookService.getAllBooks());
		return "homepage";
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String borrowBook(@ModelAttribute Book book, @ModelAttribute Person person, RedirectAttributes redirectAttrs) {
		
		String borrowStatus = bookService.borrowBook(book, person);
		
		if(borrowStatus.equals("success")) {
			redirectAttrs.addFlashAttribute("message", "successfully borrowed");
			return "redirect:/messages";
		}
		else if(borrowStatus.equals("same book already borrowed")) {
			redirectAttrs.addFlashAttribute("message", "same book already borrowed");
			return "redirect:/";
		}
		else if(borrowStatus.equals("book not available")) {
			redirectAttrs.addFlashAttribute("message", "book not available");
			return "redirect:/";
		}
		else {
			redirectAttrs.addFlashAttribute("message", "incorrect mobile");
			return "redirect:/";
		}
		
			
	}
	
	@RequestMapping(value="/return_book", method=RequestMethod.GET)
	public String returnBookPage(Model model) {
		List<Book> booksList = bookService.getAllBooks();
		List<Person> personsList = personService.getAllPersons();
		model.addAttribute("book", new Book());
		model.addAttribute("person", new Person());
		model.addAttribute("booksList", booksList);
		model.addAttribute("personsList", personsList);
		return "return_book";
	}
	
	@RequestMapping(value="/return_book", method=RequestMethod.POST)
	public String returnBook(@ModelAttribute Book book, @ModelAttribute Person person, RedirectAttributes redirectAttrs) {
		
		String returnStatus = bookService.returnBook(book, person);
		
		if(returnStatus.equals("success")) {
			redirectAttrs.addFlashAttribute("message", "successfully returned");
			return "redirect:/messages";
		}
		else if(returnStatus.equals("book not borrowed")) {
			redirectAttrs.addFlashAttribute("message", "book not borrowed");
			return "redirect:/return_book";
		}
		else {
			redirectAttrs.addFlashAttribute("message", "incorrect mobile");
			return "redirect:/return_book";
		}
		
	}
	
	@RequestMapping(value="/user_registration", method=RequestMethod.GET)
	public String userRegistrationPage(Model model) {
		/*String message = (String)model.asMap().get("message");
		model.addAttribute("message", message);*/
		model.addAttribute("person", new Person());
		return "user_registration";
	}
	
	@RequestMapping(value="/user_registration", method=RequestMethod.POST)
	public String userRegistration(@ModelAttribute Person person, RedirectAttributes redirectAttrs) {
		String userRegistered  = personService.registerUser(person);
		if(userRegistered.equals("success")) {
			redirectAttrs.addFlashAttribute("message", "successfully registered");
			return "redirect:/messages";
		}
		else {
			redirectAttrs.addFlashAttribute("message", "username taken");
			return "redirect:/user_registration";
		}
	}
	
	@RequestMapping(value="/messages", method=RequestMethod.GET)
	public void messages(Model model) {
		String message = (String)model.asMap().get("message");
		model.addAttribute("message", message);
	}

}
