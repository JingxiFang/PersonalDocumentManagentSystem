package com.sdnu.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("jsonMsg")
public class JsonMsg {

    private String msg;
    /**
     * 文件总数量
     */
    private int allCount;
    /**
     * 这个月浏览数量
     */
    private int nowMonthView;
    /**
     * 稍后阅读数量
     */
    private int laterview;
    /**
     * 这个月的上传数量
     */
    private int nowMonthUpload;
    /**
     * 近一年中每个月的上传数量
     */
    private List<ChartData> monthUpladeOfYear;

    /**
     * 近一年中每个月的阅读数量
     */
    private List<ChartData> monthViewOfYear;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getNowMonthView() {
        return nowMonthView;
    }

    public void setNowMonthView(int nowMonthView) {
        this.nowMonthView = nowMonthView;
    }

    public int getLaterview() {
        return laterview;
    }

    public void setLaterview(int laterview) {
        this.laterview = laterview;
    }

    public int getNowMonthUpload() {
        return nowMonthUpload;
    }

    public void setNowMonthUpload(int nowMonthUpload) {
        this.nowMonthUpload = nowMonthUpload;
    }

    public List<ChartData> getMonthUpladeOfYear() {
        return monthUpladeOfYear;
    }

    public void setMonthUpladeOfYear(List<ChartData> monthUpladeOfYear) {
        this.monthUpladeOfYear = monthUpladeOfYear;
    }

    public List<ChartData> getMonthViewOfYear() {
        return monthViewOfYear;
    }

    public void setMonthViewOfYear(List<ChartData> monthViewOfYear) {
        this.monthViewOfYear = monthViewOfYear;
    }
}
