package com.bookstore.service;

import com.bookstore.entity.Book;

import java.util.List;


public interface BookService {

    List<Book> getAllBooks();
    void addBook(Book book);
    void lendBook(Long bookId, Long userId);
    void returnBook(Long id);
    List<Book> getLoanedBooks();

    List<Book> getAvailableBooks();


}
