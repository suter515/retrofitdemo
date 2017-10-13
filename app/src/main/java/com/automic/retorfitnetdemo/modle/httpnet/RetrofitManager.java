package com.automic.retorfitnetdemo.modle.httpnet;

import android.content.Context;


import com.automic.retorfitnetdemo.utils.AppUtils;
import com.automic.retorfitnetdemo.utils.NetWorkUtils;
import com.automic.retorfitnetdemo.utils.ToastUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类注释：retrofit 管理类
 * Created by sujingtai on 2017/8/10 0010 下午 5:11
 */

public class RetrofitManager {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(String url, Context context) {
        //判断网络状态 无网络时
        if (!NetWorkUtils.networkIsAvailable(context)) {
            ToastUtils.show(context, "请接连网络");
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                    .baseUrl(AppUtils.BASEIP)
                    .build();
        }
        return retrofit;

    }

    public static Retrofit getRetrofit(String url) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                    .baseUrl(url)
                    .build();
        }
        return retrofit;

    }

    public static BqService creatBqService() {

        return getRetrofit(AppUtils.BASEIP).create(BqService.class);
    }
}
