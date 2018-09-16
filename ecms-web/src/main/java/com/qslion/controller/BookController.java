package com.qslion.controller;

import com.qslion.entity.Book;
import com.qslion.service.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangruigang on 2016/9/23.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response, Book book) {
        bookService.get(book);
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
