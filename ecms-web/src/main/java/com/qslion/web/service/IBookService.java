package com.qslion.web.service;

import com.qslion.web.entity.Book;

import java.util.List;

/**
 * Created by zhangruigang on 2016/9/24.
 */
public interface IBookService {
    Book get(String bookName);

    void add(Book book);

    void update(Book book);

    void delete(Book book);

    void batchAddBookIndex(List<Book> books);

    void batchDeleteBookIndex(List<Book> books);
}
