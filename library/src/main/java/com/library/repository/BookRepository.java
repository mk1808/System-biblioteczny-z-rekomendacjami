package com.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.model.Book;
import com.library.model.BookCopy;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

	@Query("SELECT distinct b FROM Book b "
			+"JOIN b.bookAuthors ba "
			+"JOIN b.bookGenres bg "
			+"WHERE "
			+"(cast(:ISBN as text) is null or b.ISBN like %:ISBN%) "
			+"and (cast(:title as text) is null  or b.title like %:title%)"
			+"and (cast(:authorId as text) is null  or ba.author.id = :authorId)"
			+"and (cast(:publisherId as text) is null  or b.publisher.id = :publisherId)"
			+"and (cast(:genreId as text) is null  or bg.genre.id = :genreId)"
			)
	List<Book> findByFilter(
			@Param("ISBN")String ISBN, 
			@Param("title")String title, 
			@Param("authorId")UUID authorId, 
			@Param("publisherId")UUID publisherId, 
			@Param("genreId")UUID genreId);
	
	List<Book> findAllByOrderByCreatedDesc();

}
