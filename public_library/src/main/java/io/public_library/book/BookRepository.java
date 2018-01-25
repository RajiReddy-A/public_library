package io.public_library.book;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book, String>{

	public List<Book> findByBookName(String bookName);
	
	/*@Transactional
	@Modifying
	@Query("update Book book set book.copiesAvailable=book.copiesAvailable-1 where book.bookName=?1")
	public void decreaseCopiesAvailable(String bookName);*/
}
