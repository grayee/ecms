package com.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.web.entity.Book;
import com.qslion.web.service.IBookService;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangruigang on 2016/12/7.
 */
public class EsTest extends BaseTest{

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
