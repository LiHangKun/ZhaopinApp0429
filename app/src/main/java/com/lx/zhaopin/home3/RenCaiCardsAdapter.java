package com.lx.zhaopin.home3;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.RenCaiListBean;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxn on 2018/11/10 0010.
 */

public class RenCaiCardsAdapter extends BaseAdapter {
    private Context mContext;
    private final List<RenCaiListBean.DataListBean> cardList;

    public RenCaiCardsAdapter(Context context, List<RenCaiListBean.DataListBean> list) {
        mContext = context;
        cardList = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return cardList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return cardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //View cardView = LayoutInflater.from(context).inflate(R.layout.layout_card, parent, false);
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.layout_card_rencai, parent, false);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), cardList.get(position));


        //MyImageView iv_image = (MyImageView) cardView.findViewById(R.id.iv_image);
        //PicassoUtil.setImag(context, cardList.get(position), iv_image);


        TextView tv1 = cardView.findViewById(R.id.tv1);
        TextView tv2 = cardView.findViewById(R.id.tv2);
        TextView tv3 = cardView.findViewById(R.id.tv3);
        TextView tv4 = cardView.findViewById(R.id.tv4);
        TextView tv5 = cardView.findViewById(R.id.tv5);
        TextView tv6 = cardView.findViewById(R.id.tv6);
        TextView tv7 = cardView.findViewById(R.id.tv7);
        TextView tv8 = cardView.findViewById(R.id.tv8);
        TextView tv9 = cardView.findViewById(R.id.tv9);

        FlowLiner flowLiner1 = cardView.findViewById(R.id.flowLiner1);
        FlowLiner flowLiner2 = cardView.findViewById(R.id.flowLiner2);

        RoundedImageView roundedImageView = cardView.findViewById(R.id.roundedImageView);

        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(cardList.get(position).getAvatar()).into(roundedImageView);


        tv1.setText(cardList.get(position).getName());
        tv2.setText(cardList.get(position).getEducation().getName());
        tv3.setText(cardList.get(position).getAge() + "岁");
        tv4.setText(cardList.get(position).getWorkYears() + "年");


        try {
            tv5.setText(cardList.get(position).getLatestCity().getName());
            tv9.setText(cardList.get(position).getExperienceEducation().getSchool());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cardList.get(position).getExperienceWorkList()!= null){
            if (cardList.get(position).getExperienceWorkList().size() !=0){
                tv6.setText(cardList.get(position).getExperienceWorkList().get(0).getCompanyName());
                tv7.setText(cardList.get(position).getExperienceWorkList().get(0).getPositionName());
                tv8.setText(cardList.get(position).getExperienceWorkList().get(0).getExperience());

                String skills = cardList.get(position).getExperienceWorkList().get(0).getSkills();
                String[] split = skills.split(",");
                List<String> flowData = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    flowData.add(split[i]);
                }

                flowLiner1.removeAllViews();
                for (int i = 0; i < flowData.size(); i++) {
                    final TextView radioButton = new TextView(mContext);
                    FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
                    radioButton.setLayoutParams(layoutParams);
                    final String str = flowData.get(i);
                    radioButton.setText(str);
                    radioButton.setGravity(Gravity.CENTER);
                    radioButton.setTextSize(13);
                    radioButton.setPadding(ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6));
                    radioButton.setTextColor(mContext.getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                    //radioButton.setBackgroundResource(R.drawable.search_selector);
                    radioButton.setBackgroundResource(R.drawable.button_shape03);
                    radioButton.setFocusable(true);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    flowLiner1.addView(radioButton);
                }
            }
        }else {
            tv6.setText("");
            tv7.setText("");
            tv8.setText("");
        }

        if (cardList.get(position).getResumeSkillList() != null){
            if (cardList.get(position).getResumeSkillList().size() != 0){
                List<RenCaiListBean.DataListBean.ResumeSkillListBean> resumeSkillList = cardList.get(position).getResumeSkillList();
                List<String> flowData2 = new ArrayList<>();
                for (int i = 0; i < resumeSkillList.size(); i++) {
                    flowData2.add(resumeSkillList.get(i).getName());
                }
                flowLiner2.removeAllViews();
                for (int i = 0; i < flowData2.size(); i++) {
                    final TextView radioButton = new TextView(mContext);
                    FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, ViewUtil.dp2px(mContext, 10), ViewUtil.dp2px(mContext, 10));
                    radioButton.setLayoutParams(layoutParams);
                    final String str = flowData2.get(i);
                    radioButton.setText(str);
                    radioButton.setGravity(Gravity.CENTER);
                    radioButton.setTextSize(13);
                    radioButton.setPadding(ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6), ViewUtil.dp2px(mContext, 18), ViewUtil.dp2px(mContext, 6));
                    radioButton.setTextColor(mContext.getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                    //radioButton.setBackgroundResource(R.drawable.search_selector);
                    radioButton.setBackgroundResource(R.drawable.button_shape03);
                    radioButton.setFocusable(true);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    flowLiner2.addView(radioButton);
                }
            }
        }












       /*//只显示2个
       if (TextUtils.isEmpty(cardList.get(position).getCompany().getImages())) {
            allImageView.setVisibility(View.GONE);
        } else {
            allImageView.setVisibility(View.VISIBLE);
            String images = cardList.get(position).getCompany().getImages();
            String[] split = images.split("\\|");
            if (split.length == 1) {
                gongSiImage1.setVisibility(View.VISIBLE);
                gongSiImage2.setVisibility(View.INVISIBLE);
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(split[0]).into(gongSiImage1);

            } else {
                gongSiImage1.setVisibility(View.VISIBLE);
                gongSiImage2.setVisibility(View.VISIBLE);
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(split[0]).into(gongSiImage1);
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(split[1]).into(gongSiImage2);
            }
        }*/


        /*
        //列表的展示形式
        List<String> gongSiImageList = new ArrayList<>();
        String images = cardList.get(position).getCompany().getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            gongSiImageList.add(split[i]);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//
        recyclerView.setLayoutManager(linearLayoutManager);
        PingJiaImageAdapter pingJiaImageAdapter = new PingJiaImageAdapter(mContext, gongSiImageList);
        recyclerView.setAdapter(pingJiaImageAdapter);
        pingJiaImageAdapter.setOnItemClickListener(new PingJiaImageAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                //showImage(new ImageView(getActivity()), position);
            }
        });*/

        return cardView;
    }

}
