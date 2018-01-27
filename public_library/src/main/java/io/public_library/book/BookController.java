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
	public String borrowBook(@ModelAttribute Book book, @ModelAttribute Person person) {
		String bookToBorrow = book.getBookName();
		String personToBorrow = person.getPersonName();
		String mobile = person.getMobile();
		Book dbBook = bookService.getBook(bookToBorrow);
		
		if(dbBook.getCopiesAvailable() >= 1) {
			List<Book> listOfBooks = Arrays.asList(dbBook);
			List<Person> listOfPersons = Arrays.asList(person);
			person.setListOfBooks(listOfBooks);
			book.setListOfPersons(listOfPersons);
			dbBook.setCopiesAvailable(dbBook.getCopiesAvailable() - 1);
			personService.borrowedBy(person);
			//bookService.decreaseCopiesAvailable(dbBook);
			bookService.updateBook(dbBook);
			System.out.println(person.getListOfBooks());
			List<Book> bookCheck = person.getListOfBooks();
			return "borrowed_details";
		}
		else {
			return "book_not_available";
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
		System.out.println();
		return "return_book";
	}
	
	@RequestMapping(value="/return_book", method=RequestMethod.POST)
	public String returnBook(@ModelAttribute Book book, @ModelAttribute Person person) {
		return "chek";
	}
	
	@RequestMapping(value="/user_registration", method=RequestMethod.GET)
	public String userRegistrationPage(Model model) {
		String message = (String)model.asMap().get("message");
		model.addAttribute("person", new Person());
		model.addAttribute("message", message);
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
