package io.public_library.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}
	
	public String borrowBook(String bookName) {
		System.out.println("the find one operation is below");
		List<Book> books = bookRepository.findByBookName(bookName);
		
		if(books.get(0).getCopiesAvailable() >= 1) {
			System.out.println("the book is available");
			bookRepository.decreaseCopiesAvailable(books.get(0).getBookName());
			return "success";
		}
		else {
			System.out.println("the book is not available");
			return "failure";
		}
		
	}
}
