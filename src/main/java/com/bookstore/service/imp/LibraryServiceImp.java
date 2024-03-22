package com.bookstore.service.imp;

import com.bookstore.repository.LibraryRepository;
import com.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImp implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImp(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;

    }



//    @Override
//    public Library getLibraryById(long id) {
//        Optional<Library> optionalLibrary = Optional.ofNullable(libraryRepository.findById(id));
//        return optionalLibrary.orElse(null);
//    }
}
