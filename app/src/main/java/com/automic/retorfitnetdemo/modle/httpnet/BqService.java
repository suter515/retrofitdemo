package com.automic.retorfitnetdemo.modle.httpnet;


import com.automic.retorfitnetdemo.bean.BqAreaCountRespond;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类注释：业务查询服务
 * Created by sujingtai on 2017/8/10 0010 下午 4:58
 */

public interface BqService {


    /**
     * 行政区计数--取水计划
     *
     * @param yr
     * @return
     */
    @POST("intpl/summer")
    Observable<BqAreaCountRespond> getAreaCountGwp(
            @Query("yr") String yr
    );


    /**
     * 行政区--取水许可
     *
     * @return
     */
    @POST("wpc/summer")
    Observable<BqAreaCountRespond> getAreaCountGw(

    );

    /**
     * 行政区批准排污量汇总--入河排污口
     *
     * @return
     */
    @POST("pdo/summer")
    Observable<BqAreaCountRespond> getAreaCountOutlet(
    );

}
