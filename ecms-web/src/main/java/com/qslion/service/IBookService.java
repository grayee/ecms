package com.qslion.service;

import com.qslion.entity.Book;

import javax.jws.WebParam;
import javax.jws.WebService;
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
