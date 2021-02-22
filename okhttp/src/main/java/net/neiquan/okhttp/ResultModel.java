package net.neiquan.okhttp;

import java.util.List;


/**
 * @author wang
 * @version V1.0
 * @Description:this class is user for  返回的model
 * @date 2015-11-17 上午11:47:14
 */
public class ResultModel {

    /**
     * if return jsonArray use model list
     */
    private List<?> modelList = null;
    /**
     * if return jsonObject use model list
     */
    private Object model;

    public List<?> getModelList() {
        return modelList;
    }

    public void setModelList(List<?> modelList) {
        this.modelList = modelList;
    }

    public <T> T getModel() {
        return (T) model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
