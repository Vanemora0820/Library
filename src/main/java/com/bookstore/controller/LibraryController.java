package com.bookstore.controller;

import com.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryController {

        private final LibraryService libraryService;

        @Autowired
        public LibraryController(LibraryService libraryService) {
            this.libraryService = libraryService;
        }


    }
