package com.qslion.ecms.service;

import com.qslion.ecms.entity.Book;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by zhangruigang on 2016/9/24.
 */
@WebService
public interface IBookService {
    public void get(@WebParam(name = "book")Book book);
    public void add(Book book);
    public void update(Book book);
    public void delete(Book book);

    void batchAddBookIndex(List<Book> books);
    void batchDeleteBookIndex(List<Book> books);
}
