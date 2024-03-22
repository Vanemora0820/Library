package com.bookstore.service.imp;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookServiceImp(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void lendBook(Long bookId, Long userId) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (book.getAvailable()== 1) {
                book.setAvailable(0);
                book.setUser(userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No se encontró el usuario con el ID proporcionado."))); // Asocia el libro al usuario
                bookRepository.save(book);
            } else {
                throw new IllegalStateException("El libro no está disponible para prestar.");
            }
        } else {
            throw new NoSuchElementException("No se encontró el libro con el ID proporcionado.");
        }
    }
    @Override
    public void returnBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.getAvailable() == 0) {
                book.setAvailable(1);
                book.setUser(null);
                bookRepository.save(book);
            } else {
                throw new IllegalStateException("El libro ya está disponible.");
            }
        } else {
            throw new NoSuchElementException("No se encontró el libro con el ID proporcionado.");
        }
    }
    @Override
    public List<Book> getLoanedBooks() {
        return bookRepository.findByAvailable(0);
    }

    @Override
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(1);
    }

}
