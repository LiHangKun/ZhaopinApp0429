package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.RenCaiDetailBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

//人才详情 renCaiDetail
public class RenCaiDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.recyclerView3)
    RecyclerView recyclerView3;
    @BindView(R.id.tvButton1)
    TextView tvButton1;
    @BindView(R.id.tvButton2)
    TextView tvButton2;
    @BindView(R.id.qiuZhiView)
    LinearLayout qiuZhiView;
    private String rid;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.rencaidetail_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        rid = getIntent().getStringExtra("rid");
        getRenDetail(rid);

    }

    //人才详情
    private void getRenDetail(String rid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", rid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.renCaiDetail, params, new SpotsCallBack<RenCaiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, RenCaiDetailBean resultBean) {
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(resultBean.getAvatar()).into(roundedImageView);

                tv1.setText(resultBean.getName());
                String sex = resultBean.getSex();
                //性别，1.男，2.女
                switch (sex) {
                    case "1":
                        tv2.setText("男");
                        break;
                    case "2":
                        tv2.setText("女");
                        break;
                }
                tv3.setText(resultBean.getMinSalary() + "K - " + resultBean.getMaxSalary() + "K");
                tv4.setText(resultBean.getEducation().getName());
                tv5.setText(resultBean.getBirthday() + "岁");
                tv6.setText(resultBean.getWorkYears() + "年");
                tv7.setText(resultBean.getLatestCityName());
                String jobNature = resultBean.getJobNature();
                //工作性质 1.全职，2.兼职
                switch (jobNature) {
                    case "1":
                        tv8.setText("全职");
                        break;
                    case "2":
                        tv8.setText("兼职");
                        break;
                }
                tv9.setText(resultBean.getLatestCityName());


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.back, R.id.image1, R.id.image2, R.id.image3, R.id.tvButton1, R.id.tvButton2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.image1:
                //收藏
                ToastFactory.getToast(mContext, "收藏").show();
                break;
            case R.id.image2:
                //分享
                ToastFactory.getToast(mContext, "分享").show();
                break;
            case R.id.image3:
                //举报
                ToastFactory.getToast(mContext, "举报").show();
                break;
            case R.id.tvButton1:
                //立即沟通
                break;
            case R.id.tvButton2:
                //职位邀约

                break;
        }
    }
}
