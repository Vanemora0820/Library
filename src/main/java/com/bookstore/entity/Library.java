    package com.bookstore.entity;


    import jakarta.persistence.*;
    import lombok.Data;

    import java.util.ArrayList;
    import java.util.List;

    @Data
    @Entity
    @Table(name = "library")
    public class Library {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @OneToMany (mappedBy = "library", cascade = CascadeType.ALL)
        private List<Book> availableBooks = new ArrayList<>();

        @OneToMany (mappedBy = "library", cascade = CascadeType.ALL)
        private List<Book> loanedBooks = new ArrayList<>();


    }
