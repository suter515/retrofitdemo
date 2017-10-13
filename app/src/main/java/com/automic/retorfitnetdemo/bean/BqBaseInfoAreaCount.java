package com.automic.retorfitnetdemo.bean;

/**
 * 类注释：取水户查询--行政区取水量
 * Created by sujingtai on 2017/7/24 0024 下午 4:11
 */

public class BqBaseInfoAreaCount {
    private double sum;//取水总量
    private String regCd;//行政区编号
    private String regNm;//行政区名字

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getRegCd() {
        return regCd;
    }

    public void setRegCd(String regCd) {
        this.regCd = regCd;
    }

    public String getRegNm() {
        return regNm;
    }

    public void setRegNm(String regNm) {
        this.regNm = regNm;
    }
}
