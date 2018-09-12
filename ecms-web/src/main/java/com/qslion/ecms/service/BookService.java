package com.qslion.ecms.service;

import com.google.common.collect.Lists;
import com.qslion.ecms.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by zhangruigang on 2016/9/24.
 */
@WebService(endpointInterface= "com.qslion.ecms.service.IBookService",serviceName="bookService")
@Component
public class BookService implements IBookService{
   /* @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;*/
    @WebMethod
    public void get(@WebParam(name = "book")Book book){
        System.out.println("get book:");
        System.out.println(book.toString());
    }
    public void add(Book book){
        System.out.println("add book:");
        System.out.println(book.toString());
    }
    public void update(Book book){
        System.out.println("update book:");
        System.out.println(book.toString());
    }
    public void delete(Book book){
        System.out.println("delete book:");
        System.out.println(book.toString());
    }


    public void batchAddBookIndex(List<Book> books){
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
