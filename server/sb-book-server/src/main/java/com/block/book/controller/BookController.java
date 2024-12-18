package com.block.book.controller;

import com.block.book.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class BookController {

    ArrayList<Book> bookList = new ArrayList<>();

    // 책 생성 API 구현
    @PostMapping("/api/books")
    public Map<String, Object> createBook(@RequestBody Book book){
        bookList.add(book);
        return Map.of("message", "Book created successfully",
                "book", book) ;
    }

    // 책 조회 API 구현
    @GetMapping("/api/books/{id}")
    public Map<String, Object> getBook(@PathVariable String id){
        for( Book book  : bookList){
            if(book.getId().equals(id) ){
                return Map.of("book", book);
            }
        }
        return Map.of("book","해당 책이 없습니다.");
    }

    @PutMapping("/api/books/{id}")
    public Map<String, Object> updateBook(@PathVariable String id, @RequestBody Book book){
        // 기존 책을 업데이트하는 거니까,
        for( Book savedBook  : bookList){
            if(savedBook.getId().equals(id)){
                savedBook.setTitle(book.getTitle());
                savedBook.setAuthor(book.getAuthor());
                return Map.of("message", "Book updated successfully",
                        "book", savedBook) ;
            }
        }
        return Map.of("message", "해당 책은 없습니다.") ;
    }

}
