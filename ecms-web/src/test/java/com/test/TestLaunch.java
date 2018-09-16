package com.test;



import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zhangruigang on 2016/11/2.
 */
public class TestLaunch {
    public static void main(String[] args) throws IOException {
        //testIcal();

        Map<String,String> map=JSON.parseObject("{\n" +
                "  \"originId\": 146,\n" +
                "  \"originUid\": \"skyguardtest@sohu.com\",\n" +
                "  \"from\": \"<skyguardtest@sohu.com>\",\n" +
                "  \"to\": [\n" +
                "    \"skyguardtest<skyguardtest@sohu.com>\"\n" +
                "  ],\n" +
                "  \"subject\": \"回复:回复:回复:webmail测试邮件sohu\",\n" +
                "  \"content\": \"<p><br/></p><p><br/></p><p>----- 原始邮件 -----</p><p><br/></p><p style=\\\"padding-bottom: 5px;\\\"><strong>发件人：</strong>skyguardtest&lt;skyguardtest@sohu.com&gt;</p><p style=\\\"padding-bottom: 5px;\\\"><strong>发送时间：</strong>2016-11-17 14:37:08</p><p style=\\\"padding-bottom: 5px;\\\"><strong>收件人：</strong>skyguardtest&lt;skyguardtest@sohu.com&gt;</p><p style=\\\"padding-bottom: 5px;\\\"><strong>主 题：</strong>回复:回复:webmail测试邮件sohu</p><p><br/></p><p><br/></p><p>----- 原始邮件 -----</p><p><br/></p><p style=\\\"padding-bottom: 5px;\\\"><strong>发件人：</strong>skyguardtest&lt;skyguardtest@sohu.com&gt;</p><p style=\\\"padding-bottom: 5px;\\\"><strong>发送时间：</strong>2016-11-17 14:35:39</p><p style=\\\"padding-bottom: 5px;\\\"><strong>收件人：</strong>skyguardtest&lt;skyguardtest@sohu.com&gt;</p><p style=\\\"padding-bottom: 5px;\\\"><strong>主 题：</strong>回复:webmail测试邮件sohu</p><p><br/></p><p><br/></p><p>----- 原始邮件 -----</p><p><br/></p><p style=\\\"padding-bottom: 5px;\\\"><strong>发件人：</strong>skyguardtest&lt;skyguardtest@sohu.com&gt;</p><p style=\\\"padding-bottom: 5px;\\\"><strong>发送时间：</strong>2016-11-10 15:26:37</p><p style=\\\"padding-bottom: 5px;\\\"><strong>收件人：</strong>danieldiy&lt;danieldiy@126.com&gt;</p><p style=\\\"padding-bottom: 5px;\\\"><strong>主 题：</strong>webmail测试邮件sohu</p><p>Hi，</p><p><br/></p><p>请注意这是WEBmail测试邮件，关键字：天空卫士，请查看证据文件显示是否正常，谢谢！<br/></p><p><br/><br/><br/></p><hr/><p><br/></p><div><a href=\\\"http://score.mail.sohu.com/?ref=mail_tailad\\\" target=\\\"_blank\\\"><img src=\\\"http://ad.mail.sohu.com/mail/images/score_ad_foot1_750x79.png?t=1479364668718\\\"/> </a></div><p><br/><br/><br/></p><p><br/><br/><br/></p><p><br/><br/><br/></p>\",\n" +
                "  \"attachments\": [],\n" +
                "  \"send\": 1,\n" +
                "  \"draftType\": 2\n" +
                "}",Map.class);
        System.out.println(map.get("content"));
    }

    public static void testElastic(){
        //new TransportClient;
    }

    private static void testIcal() throws IOException {
     /*   File file=new File("d://test.ics");
        List<String> list= FileUtils.readLines(file,"gbk");
        StringBuffer buffer=new StringBuffer();
        for (String s : list) {
            buffer.append(s).append("\r\n");
        }
        StringReader builder=new StringReader(buffer.toString());
        CalendarBuilder calendarBuilder=new CalendarBuilder();
        Calendar calendar=calendarBuilder.build(builder);
        List<VEvent> eventList=calendar.getComponents(Component.VEVENT);
        for (VEvent vEvent : eventList) {
            System.out.println(vEvent.getProperty("X-ALT-DESC").getValue());
        }*/
    }

}
