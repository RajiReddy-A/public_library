package io.public_library.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/")
	public String homePage() {
		List<Book> books = new ArrayList<>(bookService.getAllBooks());
		System.out.println(books);
		return "homepage";
	}
	
	@RequestMapping("/return_book")
	public String returnBook() {
		return "return_book";
	}

}
