package com.qslion.controller;

import com.qslion.entity.Book;
import com.qslion.service.BookService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangruigang on 2016/9/23.
 */
@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Book get(String bookName) {
        if (StringUtils.isEmpty(bookName)) {
            bookName = "hello world";
        }
        return bookService.get(bookName);
    }

    @RequestMapping(value = "/author/{token}", method = RequestMethod.GET)
    public Object getAuthor(@PathVariable String token) {
        return restTemplate.getForObject("http://ecms-auth/ecms/auth/au/user/detail/1?access_token=" + token, Object.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, Book book) {
        try {
            InputStream input = request.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            String jsonString = "";
            while ((line = bufferedReader.readLine()) != null) {
                jsonString += line + "\r\n";
            }
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookService.add(book);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request, HttpServletResponse response, Book book) {
        bookService.delete(book);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(HttpServletRequest request, HttpServletResponse response, Book book) {
        bookService.update(book);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public BookService getBookService() {
        return bookService;
    }

    @Resource
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
