package com.qslion.ecms.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.ecms.entity.Book;
import com.qslion.ecms.service.IBookService;


import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by zhangruigang on 2016/12/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:WEB-INF/ecms-servlet.xml")
public class EsTest {

    @Autowired // 注入
    private IBookService bookService;

    @Test
    public void testBatchAddIndex() {
        List<Book> list= Lists.newArrayList();
        long version=0;
        for (int i=0;i<10;i++) {
            Book bk=new Book();
            bk.setId(UUID.randomUUID().toString());
            bk.setName("test"+i);
            bk.setPrice(RandomUtils.nextLong());
            bk.setVersion(version);
            Map<Integer, Collection<String>> buckets= Maps.newHashMap();
            buckets.put(i,Lists.<String>newArrayList("序号："+i,"hello"));
            bk.setBuckets(buckets);
            list.add(bk);
            version++;
        }
        bookService.batchAddBookIndex(list);
        System.out.println("hello");
    }

}
