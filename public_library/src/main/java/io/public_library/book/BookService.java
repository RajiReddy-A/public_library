package io.public_library.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.public_library.person.Person;
import io.public_library.person.PersonRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
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
	
	@Transactional
	public String borrowBook(Book book, Person person) {
		Book dbBook = bookRepository.findOne(book.getBookName());
		Person dbPerson = personRepository.findOne(person.getPersonName());
		
		if(dbPerson.getMobile().equals(person.getMobile())) {
			if(dbBook.getCopiesAvailable() >= 1) {
				List<Book> booksList = dbPerson.getListOfBooks();
				booksList.add(dbBook);
				dbPerson.setListOfBooks(booksList);
				List<Person> personsList = dbBook.getListOfPersons();
				personsList.add(dbPerson);
				dbBook.setListOfPersons(personsList);
				dbBook.setCopiesAvailable(dbBook.getCopiesAvailable()-1);
				bookRepository.save(dbBook);
				return "success";
			}
			else {
				return "book not available";
			}
		}
		else {
			return "incorrect mobile";
		}
	}
	/*public void decreaseCopiesAvailable(Book book) {
		//System.out.println("the find one operation is below");
		//List<Book> books = bookRepository.findByBookName(bookName);
		bookRepository.decreaseCopiesAvailable(book.getBookName());
		
	}*/
}
