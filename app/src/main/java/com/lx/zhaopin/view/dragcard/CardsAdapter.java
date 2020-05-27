package com.lx.zhaopin.view.dragcard;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.PingJiaImageAdapter;
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxn on 2018/11/10 0010.
 */

public class CardsAdapter extends BaseAdapter {
    private Context mContext;
    private final List<ShouYeQiuZhiZheBean.DataListBean> cardList;

    public CardsAdapter(Context context, List<ShouYeQiuZhiZheBean.DataListBean> list) {
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
        View cardView = LayoutInflater.from(mContext).inflate(R.layout.layout_card_jianli, parent, false);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), cardList.get(position));


        //MyImageView iv_image = (MyImageView) cardView.findViewById(R.id.iv_image);
        //PicassoUtil.setImag(context, cardList.get(position), iv_image);


        LinearLayout llViewALL = cardView.findViewById(R.id.llViewALL);
        ImageView imageState = cardView.findViewById(R.id.imageState);
        TextView tv1 = cardView.findViewById(R.id.tv1);
        TextView tv2 = cardView.findViewById(R.id.tv2);
        TextView tv3 = cardView.findViewById(R.id.tv3);
        TextView tv4 = cardView.findViewById(R.id.tv4);
        TextView tv5 = cardView.findViewById(R.id.tv5);
        TextView tv6 = cardView.findViewById(R.id.tv6);
        TextView tv7 = cardView.findViewById(R.id.tv7);
        TextView tv8 = cardView.findViewById(R.id.tv8);
        TextView tv9 = cardView.findViewById(R.id.tv9);
        TextView tv10 = cardView.findViewById(R.id.tv10);
        TextView tv11 = cardView.findViewById(R.id.tv11);
        TextView tv12 = cardView.findViewById(R.id.tv12);
        RecyclerView recyclerView = cardView.findViewById(R.id.recyclerView);
        LinearLayout allImageView = cardView.findViewById(R.id.allImageView);
        ImageView gongSiImage1 = cardView.findViewById(R.id.gongSiImage1);
        ImageView gongSiImage2 = cardView.findViewById(R.id.gongSiImage2);
        RoundedImageView roundedImageView = cardView.findViewById(R.id.roundedImageView);

        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(cardList.get(position).getCompany().getLogo()).into(roundedImageView);

        tv1.setText(cardList.get(position).getName());

        String positionType = cardList.get(position).getPositionType();
        switch (positionType) {
            case "1":
                imageState.setVisibility(View.INVISIBLE);
                tv1.setTextColor(mContext.getResources().getColor(R.color.txt_c333));
                break;
            case "2":
            case "3":
                imageState.setVisibility(View.VISIBLE);
                break;

        }
        tv2.setText(cardList.get(position).getMinSalary() + "K - " + cardList.get(position).getMaxSalary() + "K");
        tv3.setText(cardList.get(position).getCity().getName() + cardList.get(position).getDistrict().getName());
        tv4.setText(cardList.get(position).getExperienceYear().getName() + "年");
        tv5.setText(cardList.get(position).getEducation().getName());
        tv6.setText(cardList.get(position).getDuty());
        tv7.setText(cardList.get(position).getLocation());
        tv8.setText(cardList.get(position).getCompany().getName());
        tv9.setText(cardList.get(position).getCompany().getFinancingName());
        tv10.setText(cardList.get(position).getCompany().getIndustry2Name());
        tv11.setText(cardList.get(position).getCompany().getStaffNum());
        tv12.setText(cardList.get(position).getCompany().getService());


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
        });

        return cardView;
    }

}
