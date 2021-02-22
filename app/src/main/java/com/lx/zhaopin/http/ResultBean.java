package com.lx.zhaopin.http;

import java.io.Serializable;

public class ResultBean implements Serializable {

    public String result;
    public String resultNote;


    public String getResult() {
        return result;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
