package io.public_library.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> getAllPersons(){
		List<Person> persons = new ArrayList<>();
		personRepository.findAll().forEach(persons::add);
		return persons;
	}
	
	public void borrowedBy(Person person) {
		personRepository.save(person);
	}
	
	public String registerUser(Person person) {
		Boolean userExists = personRepository.exists(person.getPersonName());
		if(userExists) {
			return "failure";
		}
		else {
			personRepository.save(person);
			return "success";
		}
	}
}
