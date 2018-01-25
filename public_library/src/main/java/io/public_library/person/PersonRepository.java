package io.public_library.person;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String>{
	
	//public List<Person> findByPersonNameAndBookName(String personName, String bookName);


}
