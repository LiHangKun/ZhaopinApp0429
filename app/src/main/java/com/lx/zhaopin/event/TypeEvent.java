package com.lx.zhaopin.event;

import com.lx.zhaopin.bean.SelectQiWangBean;

import java.util.List;

public class TypeEvent {
    public List<SelectQiWangBean.DataListBean> mSelectedData;

    public TypeEvent(List<SelectQiWangBean.DataListBean> mSelectedData) {
        this.mSelectedData = mSelectedData;
    }

    public List<SelectQiWangBean.DataListBean> getmSelectedData() {
        return mSelectedData;
    }

    public void setmSelectedData(List<SelectQiWangBean.DataListBean> mSelectedData) {
        this.mSelectedData = mSelectedData;
    }
}
