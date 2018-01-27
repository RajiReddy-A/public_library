package io.public_library.person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.public_library.book.Book;

@Entity
@Table(name="person")
public class Person {

	/*@Id
	@Column(name="personId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personId;*/
	
	@Id
	@Column(name="personName")
	private String personName;
	
	@Column(name="mobile")
	private String mobile;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="bookandperson",
		joinColumns=@JoinColumn(name="personName"),
		inverseJoinColumns=@JoinColumn(name="bookName"))
	private List<Book> listOfBooks = new ArrayList<Book>();
	
	public Person() {
		
	}
	
	public Person(String personName, String mobile) {
		super();
		this.personName = personName;
		this.mobile = mobile;
		
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	
	
}
