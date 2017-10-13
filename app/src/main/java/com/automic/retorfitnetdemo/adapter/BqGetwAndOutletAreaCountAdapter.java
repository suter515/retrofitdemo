package com.automic.retorfitnetdemo.adapter;

import android.content.Context;
import android.widget.TextView;


import com.automic.retorfitnetdemo.R;
import com.automic.retorfitnetdemo.bean.BqBaseInfoAreaCount;

import java.util.List;

/**
 * 取水（取水许可、取水计划）和入河排污 行政区域列表adapter
 * Created by qulus on 2017/3/30 0030.
 */

public class BqGetwAndOutletAreaCountAdapter extends BaseTtAdapter<BqBaseInfoAreaCount> {
    private Context mContext ;
    public BqGetwAndOutletAreaCountAdapter(Context context, List<BqBaseInfoAreaCount> mList) {
        super(context, mList);
        this.mContext = context ;
        this.setLayoutId(R.layout.item_area_getwater_count);
        int[] ids = {R.id.tvw_area_name, R.id.tvw_getwater_count} ;
        String fields[] = {"tvwRegNm", "tvwSum"};
        this.setClass(ViewHolderAreaCount.class);
        this.setViewIds(ids);
        this.setFields(fields);
    }

    @Override
    public void addDataToView(BqBaseInfoAreaCount areaCount, Object o) {
        if (o instanceof ViewHolderAreaCount) {
            ((ViewHolderAreaCount) o).tvwRegNm.setText(areaCount.getRegNm()==null
                    ?"" :areaCount.getRegNm());

            if (areaCount.getRegNm() != null) {
                ((ViewHolderAreaCount) o).tvwSum.setText("(" +String.valueOf(areaCount.getSum()) + "万m³)");
            }
        }
    }
    static class ViewHolderAreaCount {
        TextView tvwRegNm ;
        TextView tvwSum ;
    }
}
