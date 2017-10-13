package com.automic.retorfitnetdemo.bean;

import java.util.List;

/**
 * 类注释：行政区计量列表respond
 * Created by sujingtai on 2017/8/10 0010 下午 5:17
 */

public class BqAreaCountRespond {
    int code;
    List<BqBaseInfoAreaCount> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BqBaseInfoAreaCount> getResult() {
        return result;
    }

    public void setResult(List<BqBaseInfoAreaCount> result) {
        this.result = result;
    }
}
