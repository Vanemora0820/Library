package com.bookstore;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@SpringBootApplication

public class Application implements CommandLineRunner {
	private final BookService bookService;
	private final Scanner scanner;
	private final LibraryService libraryService;

	@Autowired
	public Application(BookService bookService, LibraryService libraryService) {
		this.bookService = bookService;
		this.libraryService = libraryService;
		this.scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean exit = false;

		while (!exit) {
			System.out.println("Bienvenido al sistema de la biblioteca VM:");
			System.out.println("Por favor seleccione una opción:");
			System.out.println("1. Ver catálogo de libros");
			System.out.println("2. Agregar un libro");
			System.out.println("3. Solicitar un libro");
			System.out.println("4. Devolver un libro");
			System.out.println("5. Listar libros prestados");
			System.out.println("6. Listar libros Disponibles");
			System.out.println("7. Salir");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					System.out.println("CATÁLOGO DE LIBROS");
					showCatalog();
					break;

				case 2:
					System.out.println("AGREGAR UN LIBRO");
					addBook();
					break;

				case 3:
					System.out.println("SOLICITAR UN LIBRO");
					lendBook();
					break;

				case 4:
					System.out.println("DEVOLVER UN LIBRO");
					returnBook();
					break;

				case 5:
					System.out.println("LISTAR LIBROS PRESTADOS");
					showLoanedBooks();
					break;

				case 6:
					System.out.println("LISTAR LIBROS DISPONIBLES");
					showAvailableBooks();
					break;

				case 7:
					exit = true;
					break;

				default:
					System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
					break;
			}
		}

		scanner.close();
		System.out.println("¡Gracias por usar nuestro sistema!");
	}

	// Método para agregar un libro
	private void addBook() {
		System.out.println("Ingrese el título del libro:");
		String title = scanner.nextLine();

		System.out.println("Ingrese el autor del libro:");
		String author = scanner.nextLine();

		System.out.println("Ingrese la fecha de publicación del libro (Año):");
		int publicationYear = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Ingrese el ID de la biblioteca:");
		long libraryId = scanner.nextLong();
		scanner.nextLine();

//		Library library = libraryService.getLibraryById(libraryId);
//		if (library == null) {
//			System.out.println("No se encontró la biblioteca con el ID proporcionado.");
//			return;
//		}


		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setPublicationDate((long) publicationYear);
		newBook.setLibraryId(libraryId);
		bookService.addBook(newBook);

		System.out.println("Libro agregado exitosamente.");
	}

	private void showCatalog() {
		System.out.println("Catálogo de libros:");
		List<Book> catalog = bookService.getAllBooks();
		for (Book book : catalog) {
			System.out.println("Id: " + book.getId());
			System.out.println("Título: " + book.getTitle());
			System.out.println("Autor: " + book.getAuthor());
			System.out.println("Fecha de publicación: " + book.getPublicationDate());
			System.out.println("-----------------------------");
		}
	}

	private void lendBook() {
		System.out.println("Ingrese el ID del libro que desea solicitar:");
		Long bookId = scanner.nextLong();
		scanner.nextLine();

		System.out.println("Ingrese el ID del usuario que solicita el libro:");
		Long userId = scanner.nextLong();
		scanner.nextLine();

		try {
			bookService.lendBook(bookId, userId);
			System.out.println("Libro prestado exitosamente.");
		} catch (NoSuchElementException | IllegalStateException e) {
			System.out.println(e.getMessage());
		}

	}

	private void returnBook() {
		System.out.println("Ingrese el ID del libro que desea devolver:");
		Long bookId = scanner.nextLong();
		scanner.nextLine();

		try {
			bookService.returnBook(bookId);
			System.out.println("Libro devuelto exitosamente.");
		} catch (NoSuchElementException | IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}
	private void showLoanedBooks() {
		System.out.println("LISTA DE LIBROS PRESTADOS:");
		List<Book> loanedBooks = bookService.getLoanedBooks();
		if (loanedBooks.isEmpty()) {
			System.out.println("No hay libros prestados en este momento.");
		} else {
			for (Book book : loanedBooks) {
				System.out.println("Título: " + book.getTitle());
				System.out.println("Autor: " + book.getAuthor());
				System.out.println("Fecha de publicación: " + book.getPublicationDate());
				System.out.println("Biblioteca: " + book.getLibrary().getName());
				System.out.println("Prestado a: " + book.getUser().getName());
				System.out.println("-----------------------------");
			}
		}
	}

	private void showAvailableBooks() {
		System.out.println("LISTA DE LIBROS DISPONIBLES:");
		List<Book> availableBooks = bookService.getAvailableBooks();
		if (availableBooks.isEmpty()) {
			System.out.println("No hay libros prestados en este momento.");
		} else {
			for (Book book : availableBooks) {
				System.out.println("Título: " + book.getTitle());
				System.out.println("Autor: " + book.getAuthor());
				System.out.println("Fecha de publicación: " + book.getPublicationDate());
				System.out.println("Biblioteca: " + book.getLibrary().getName());
				System.out.println("-----------------------------");
			}
		}
	}

}
