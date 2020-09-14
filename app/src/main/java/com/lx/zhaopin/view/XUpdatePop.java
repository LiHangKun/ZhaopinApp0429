package com.lx.zhaopin.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.AppUtil;

public class XUpdatePop {
	Context mContext;
	View mView;
	public Dialog alertDialog;
	private OnClickListener mCancleClick;
	private String phone;
	private TextView sureTv;
	private TextView cancleTv;
	private TextView phoneTv;


	public XUpdatePop(Context context, OnClickListener mCancleClick, String phone) {
		mContext = context;
		this.mCancleClick=mCancleClick;
		this.phone=phone;
		initUI();
	}

	private void initUI() {
		mView = LayoutInflater.from(mContext).inflate(R.layout.pop_phone, null);
		sureTv=(TextView)mView.findViewById(R.id.sure_tv);
		cancleTv=(TextView)mView.findViewById(R.id.cancle_tv);
		phoneTv=(TextView)mView.findViewById(R.id.phone_tv);
		phoneTv.setText("确认要拨打"+phone+"？");
		cancleTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});

		sureTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mCancleClick.onClick(view);
			}
		});

	}

	public void show() {
		alertDialog = new Dialog(mContext, R.style.shareDialog);
		alertDialog.setContentView(mView, new LayoutParams(AppUtil.getWidth(mContext) - AppUtil.dip2px(mContext, 110), LayoutParams.WRAP_CONTENT));
		alertDialog.getWindow().setGravity(Gravity.CENTER);
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.setCancelable(false);
		alertDialog.show();
	}

	public void dismiss() {
		if (alertDialog != null)
			alertDialog.dismiss();
	}

}
