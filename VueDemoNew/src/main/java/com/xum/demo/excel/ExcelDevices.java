package com.xum.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelDevices {

    @ExcelProperty(value = "唯一建", index = 0)
    private String id;

    @ExcelProperty(value = "电视名字", index = 1)
    private String tvname;

    @ExcelProperty(value = "电视型号", index = 2)
    private String tvmodelnumber;

    @ExcelProperty(value = "电视序列号", index = 3)
    private String tvserialnumber;

    @ExcelProperty(value = "电视房间号", index = 4)
    private String tvroomid;

    @ExcelProperty(value = "电视mac地址", index = 5)
    private String tvmacaddress;

    @ExcelProperty(value = "电视ip地址", index = 6)
    private String tvipaddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTvname() {
        return tvname;
    }

    public void setTvname(String tvname) {
        this.tvname = tvname;
    }

    public String getTvmodelnumber() {
        return tvmodelnumber;
    }

    public void setTvmodelnumber(String tvmodelnumber) {
        this.tvmodelnumber = tvmodelnumber;
    }

    public String getTvserialnumber() {
        return tvserialnumber;
    }

    public void setTvserialnumber(String tvserialnumber) {
        this.tvserialnumber = tvserialnumber;
    }

    public String getTvroomid() {
        return tvroomid;
    }

    public void setTvroomid(String tvroomid) {
        this.tvroomid = tvroomid;
    }

    public String getTvmacaddress() {
        return tvmacaddress;
    }

    public void setTvmacaddress(String tvmacaddress) {
        this.tvmacaddress = tvmacaddress;
    }

    public String getTvipaddress() {
        return tvipaddress;
    }

    public void setTvipaddress(String tvipaddress) {
        this.tvipaddress = tvipaddress;
    }

}
