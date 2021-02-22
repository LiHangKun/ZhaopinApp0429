package com.lx.zhaopin.bean;

import android.os.Parcelable;

import com.lx.zhaopin.http.CommonBean;

import java.io.Serializable;
import java.util.List;

public class SelectQiWangBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean implements Serializable {
        /**
         * id :
         * name : 职位名称
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
