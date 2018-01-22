package io.public_library.book;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.ArrayList;
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

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String homePage(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("person", new Person());
		//List<Book> books = new ArrayList<>(bookService.getAllBooks());
		return "homepage";
	}
	
	@RequestMapping("/return_book")
	public String returnBook() {
		return "return_book";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String borrowBook(@ModelAttribute Book book, @ModelAttribute Person person) {
		System.out.println(book.getBookName());
		return "borrowed_details";
	}

}
