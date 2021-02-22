package com.lx.zhaopin.other;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class NormalTextWatcher implements TextWatcher {
    private EditText editText;
    private CharSequence temp;//监听前的文本
    private int num;
    private int editStart;//光标开始位置
    private int editEnd;//光标结束位置

    public NormalTextWatcher(EditText text, int num) {
        editText = text;
        this.num = num;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
        temp = s;
    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        editStart = editText.getSelectionStart();
        editEnd = editText.getSelectionEnd();
        if (temp.length() > num) {
            s.delete(editStart - 1, editEnd);
            int tempSelection = editStart;
            editText.setText(s);
            editText.setSelection(tempSelection);
        }
        if (editText.getText().length() == num) {
            showBtnEnable(true);
        } else {
            showBtnEnable(false);
        }
    }

    protected abstract void showBtnEnable(boolean enable);
}