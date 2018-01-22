package io.public_library.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	/*@RequestMapping("/")
	public String homePage() {
		List<Person> persons = new ArrayList<>(personService.getAllPersons());
		System.out.println(persons);
		return "homepage";
	}
	
	@RequestMapping("/return_book")
	public String returnBook() {
		return "return_book";
	}*/

}
