package io.public_library.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="book")
public class Book {

	@Id
	@Column(name="bookId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@Column(name="bookName")
	private String bookName;
	
	@Column(name="author")
	private String author;
	
	@Column(name="copiesTotal")
	private int    copiesTotal;
	
	@Column(name="copiesAvailable")
	private int    copiesAvailable;
	//@JsonManagedReference
	//private JSONArray people;
	//@Column(name="people")
	//private JSONObject people;
	
	public Book() {
		
	}
	
	public Book(int bookId, String bookName, String author, int copiesTotal, int copiesAvailable) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.copiesTotal = copiesTotal;
		this.copiesAvailable = copiesAvailable;
		//this.people = people;
		
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCopiesTotal() {
		return copiesTotal;
	}

	public void setCopiesTotal(int copiesTotal) {
		this.copiesTotal = copiesTotal;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}

	/*public JSONObject getPeople() {
		return people;
	}

	public void setPeople(JSONObject people) {
		this.people = people;
	}*/
	
	
}
