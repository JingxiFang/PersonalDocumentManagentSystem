package com.sdnu.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate implements Converter<String,Date> {
    @Override
    public Date convert(String s) {

        if(s.equals("")){
            throw  new RuntimeException("没有传入数据");
        }

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

        try {
            return df.parse(s);
        } catch (ParseException e) {
            throw  new RuntimeException("格式错误");
        }
    }
}
