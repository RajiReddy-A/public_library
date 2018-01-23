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
		model.addAttribute("book", new Book());
		model.addAttribute("person", new Person());
		model.addAttribute("booksList", booksList);
		//List<Book> books = new ArrayList<>(bookService.getAllBooks());
		return "homepage";
	}
	
	@RequestMapping("/return_book")
	public String returnBook() {
		return "return_book";
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String borrowBook(@ModelAttribute Book book, @ModelAttribute Person person) {
		String bookToBorrow = book.getBookName();
		String personToBorrow = person.getPersonName();
		String mobile = person.getMobile();
		List<Book> listOfBooks = Arrays.asList(book);
		List<Person> listOfPersons = Arrays.asList(person);
		person.setListOfBooks(listOfBooks);
		book.setListOfPersons(listOfPersons);
		bookService.borrowBook(bookToBorrow);
		System.out.println("before personService.borrowedBy");
		personService.borrowedBy(person);
		return "borrowed_details";
	}

}
