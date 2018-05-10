package com.anhhao.bookstore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Huy on 4/15/2018.
 */

public class Book implements Serializable{
    private String bookId;
    private String name;
    private String author;
    private String description;
    public Book(){}
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("bookId", bookId);
        result.put("name", name);
        result.put("author", author);
        result.put("description", description);
        return result;
    }
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book(String bookId, String name, String author, String description) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.description = description;
    }
}
