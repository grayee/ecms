package com.test;


import com.google.common.net.MediaType;
import java.io.IOException;

/**
 * Created by zhangruigang on 2016/11/2.
 */
public class TestLaunch {
    public static void main(String[] args) throws IOException {

        System.out.println(MediaType.MICROSOFT_EXCEL.toString());
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
