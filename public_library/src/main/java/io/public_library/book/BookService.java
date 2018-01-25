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
	
	public Book getBook(String bookName) {
		return bookRepository.findOne(bookName);
	}
	
	public void updateBook(Book book) {
		bookRepository.save(book);
	}
	
	/*public void decreaseCopiesAvailable(Book book) {
		//System.out.println("the find one operation is below");
		//List<Book> books = bookRepository.findByBookName(bookName);
		bookRepository.decreaseCopiesAvailable(book.getBookName());
		
	}*/
}
