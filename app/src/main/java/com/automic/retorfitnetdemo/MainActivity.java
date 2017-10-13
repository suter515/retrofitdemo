package com.automic.retorfitnetdemo;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.automic.retorfitnetdemo.adapter.BqGetwAndOutletAreaCountAdapter;
import com.automic.retorfitnetdemo.bean.BqAreaCountRespond;
import com.automic.retorfitnetdemo.bean.BqBaseInfoAreaCount;
import com.automic.retorfitnetdemo.modle.httpnet.RetrofitManager;
import com.automic.retorfitnetdemo.modle.httpnet.RxApiManager;
import com.automic.retorfitnetdemo.utils.LogUtils;
import com.automic.retorfitnetdemo.utils.ToastUtils;
import com.automic.retorfitnetdemo.views.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private LinearLayout llSelectArea;
    private TextView tvwWaterSum;
    private ListView lvArea;
    private LinearLayout llGetwaterUser;
    private TextView tvwOneArea;
    private List<BqBaseInfoAreaCount> listAreaCount = new ArrayList<>();
    private Dialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        loadingDialog = LoadingDialog.createLoadingDialog(this,"正在加载中");
        setupView();
        getAreaCountAsync("2017");
    }

    private void setupView() {
        llSelectArea = (LinearLayout) findViewById(R.id.ll_area_select_gwplan);
        llSelectArea.setVisibility(View.VISIBLE);
        tvwWaterSum = (TextView) findViewById(R.id.tvw_sum_getwater_plan);
        lvArea = (ListView) findViewById(R.id.lv_area_getwater_plan_count);

        llGetwaterUser = (LinearLayout) findViewById(R.id.ll_getwater_plan_user);
        llGetwaterUser.setVisibility(View.GONE);
        tvwOneArea = (TextView) findViewById(R.id.tvw_onearea_gwplan_count);

    }

    /**
     * 异步获取行政区列表
     *
     * @param yr
     */
    private void getAreaCountAsync(String yr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date(System.currentTimeMillis());
        String year = simpleDateFormat.format(date);
        if (Integer.parseInt(year) < Integer.parseInt(yr)) {
            ToastUtils.show(mContext, "请选择正确的年份！");
            return;
        }
        loadDialog();
        Subscription subscription = RetrofitManager.creatBqService().getAreaCountGwp(yr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BqAreaCountRespond>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.e("sjt", "完成--completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(mContext, "服务器开小差了");

                        listAreaCount.clear();
                        BqGetwAndOutletAreaCountAdapter simpleAdapter = new BqGetwAndOutletAreaCountAdapter(mContext, listAreaCount);
                        lvArea.setAdapter(simpleAdapter);
                        simpleAdapter.notifyDataSetChanged();
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onNext(BqAreaCountRespond bqAreaCountRespond) {
                        dismissLoadingDialog();
                        int code = bqAreaCountRespond.getCode();
                        if (code == 1) {
                            listAreaCount = bqAreaCountRespond.getResult();
                            if (listAreaCount.size() != 0) {
                                tvwWaterSum.setText("全市(" + listAreaCount.get(0).getSum() + "万m³)");
                                listAreaCount.remove(0);
                            } else {
                                tvwWaterSum.setText("全市( 万m³)");
                                listAreaCount.clear();
                                ToastUtils.show(mContext, "无数据");
                            }
                        } else {
                            tvwWaterSum.setText("全市( 万m³)");
                            listAreaCount.clear();
                            LogUtils.e("sjt", "无数据");
                            ToastUtils.show(mContext, "无数据");
                        }
                        BqGetwAndOutletAreaCountAdapter simpleAdapter = new BqGetwAndOutletAreaCountAdapter(mContext, listAreaCount);
                        lvArea.setAdapter(simpleAdapter);
                        simpleAdapter.notifyDataSetChanged();
                    }
                });


        RxApiManager.get().add("gwplanAreaCout", subscription);
    }
    /**
     * 加载dialog
     */
    public void loadDialog(){
        loadingDialog.show();
    }

    /**
     * 取消dialog
     */
    public void dismissLoadingDialog(){
        loadingDialog.dismiss();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxApiManager.get().cancel("getwaterplan");
//        RxApiManager.get().cancel("gwplanAreaCout");
        RxApiManager.get().cancelAll();
    }

}
