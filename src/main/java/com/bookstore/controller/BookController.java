package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookController {

       private final BookService bookService;
       private final LibraryService libraryService;
        @Autowired
        public BookController(BookService bookService, LibraryService libraryService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
        }
        @GetMapping("/books")
        public List<Book> getAllBooks() {
            return bookService.getAllBooks();
        }

//        @PostMapping("/add")
//        public ResponseEntity<String> addBook(@RequestBody Book book, @RequestParam long libraryId) {
//        Library library = libraryService.getLibraryById(libraryId);
//        if (library == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la biblioteca con el ID proporcionado.");
//        }
//        book.setLibraryId(libraryId);
//        bookService.addBook(book);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Libro agregado exitosamente.");
//    }

         @PostMapping("/lend")
         public ResponseEntity<String> lendBook(@RequestParam long bookId, @RequestParam long userId) {
            try {
            bookService.lendBook(bookId, userId);
            return ResponseEntity.ok("El libro se prestó exitosamente.");
         } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el libro o usuario con el ID proporcionado.");
         } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El libro no está disponible para prestar.");
        }
    }


}


