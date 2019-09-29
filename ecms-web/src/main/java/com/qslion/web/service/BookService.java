package com.qslion.web.service;

import com.qslion.web.entity.Book;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangruigang on 2016/9/24.
 */
@Service
public class BookService implements IBookService {

    public Book get(String bookName) {
        return new Book(RandomStringUtils.random(5, new char[]{'a', 'b', 'c', 'd', 'e', 'f', '1', '2', '3'}), bookName, 25L, 1L);
    }

    public void add(Book book) {
        System.out.println("add book:");
        System.out.println(book.toString());
    }

    public void update(Book book) {
        System.out.println("update book:");
        System.out.println(book.toString());
    }

    public void delete(Book book) {
        System.out.println("delete book:");
        System.out.println(book.toString());
    }


    public void batchAddBookIndex(List<Book> books) {
     /*   List<IndexQuery> indexQueries = Lists.newArrayList();
        for (Book book : books) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(book.getId())).withObject(book).build();
            indexQueries.add(indexQuery);
        }*/
        //elasticsearchTemplate.bulkIndex(indexQueries);
    }

    public void batchDeleteBookIndex(List<Book> books) {
        //elasticsearchTemplate.delete("dd");
    }
}
