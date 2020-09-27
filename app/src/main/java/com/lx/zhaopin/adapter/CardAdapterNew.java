package com.lx.zhaopin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.GangWeiDetailActivity;
import com.lx.zhaopin.activity.GangWeiDetailActivity1;
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 求职者看到的界面
 */

public class CardAdapterNew extends RecyclerView.Adapter<CardAdapterNew.CardHolder> {
    private List<ShouYeQiuZhiZheBean.DataListBean> allList;
    private RequestOptions mRequestOptions;
    private final Context mContext;
    private String salary_format="%s—%sk";
    private String work_info_format ="%s%s | %s | %s";
    private String company_info_format ="%s | %s | %s";
    private String company_pos_format ="%s%s%s";

    public CardAdapterNew(Context context, List<ShouYeQiuZhiZheBean.DataListBean> cardBeanList) {
        mContext = context;
        allList = cardBeanList;
        /*mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror).diskCacheStrategy(DiskCacheStrategy.NONE);*/
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_main, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        /*final CardBean bean = mCardBeanList.get(position);
        Glide.with(holder.itemView).load(bean.getUrl()).apply(mRequestOptions).into(holder.img);
        holder.text.setText(bean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "click " + bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"点击了 img",Toast.LENGTH_SHORT).show();
            }
        });*/


        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(allList.get(position).getCompany().getLogo()).into(holder.roundedImageView);


        String positionType = allList.get(position).getPositionType();
//        switch (positionType) {
//            case "1":
//            case "2":
//                holder.imageState.setVisibility(View.INVISIBLE);
//                holder.tv1Cui.setBackground(null);
//                holder.tv1.setVisibility(View.GONE);
//                holder.tv1Cui.setVisibility(View.VISIBLE);
//                holder.tv1Cui.setText(allList.get(position).getName());
//                holder.tv1Cui.setTextColor(mContext.getResources().getColor(R.color.txt_lv7));
//                break;
//            case "3":
//                holder.imageState.setVisibility(View.VISIBLE);
//                holder.tv1Cui.setVisibility(View.GONE);
//                holder.tv1.setVisibility(View.VISIBLE);
//                holder.tv1.setText(allList.get(position).getName());
//                holder.tv1.setBackground(mContext.getDrawable(R.drawable.biaoqian));
//                holder.tv1.setTextColor(mContext.getResources().getColor(R.color.white));
//                break;
//
//        }

        holder.workName.setText(allList.get(position).getName());
        holder.workSalary.setText(String.format(salary_format, allList.get(position).getMinSalary(), allList.get(position).getMaxSalary()));
        holder.workSalaryRight.setText(String.format(salary_format, allList.get(position).getMinSalary(), allList.get(position).getMaxSalary()));
        holder.workInfoShort.setText(String.format(work_info_format, allList.get(position).getCity().getName(),
                allList.get(position).getDistrict().getName(),
                allList.get(position).getExperienceYear().getName(),
                allList.get(position).getEducation().getName()));
        holder.workInfo.setText(allList.get(position).getDuty());
        holder.workCompanyInfoShort.setText(String.format(company_info_format, allList.get(position).getCompany().getFinancingName(),
                allList.get(position).getCompany().getIndustry2Name(),
                allList.get(position).getCompany().getStaffNum()));
        holder.workCompanyPos.setText(String.format(company_pos_format, allList.get(position).getCity().getName() ,
                allList.get(position).getDistrict().getName(),
                allList.get(position).getLocation()));
        holder.workCompanyName.setText(allList.get(position).getCompany().getName());
        holder.workCompanyInfo.setText(allList.get(position).getCompany().getService());

        //列表的展示形式
        List<String> gongSiImageList = new ArrayList<>();
        String images = allList.get(position).getCompany().getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            gongSiImageList.add(split[i]);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        PingJiaImageAdapter pingJiaImageAdapter = new PingJiaImageAdapter(mContext, gongSiImageList);
        holder.recyclerView.setAdapter(pingJiaImageAdapter);
        pingJiaImageAdapter.setOnItemClickListener(new PingJiaImageAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                //showImage(new ImageView(getActivity()), position);
            }
        });



        holder.workInfoNextLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GangWeiDetailActivity1.class);
                intent.putExtra("pid", allList.get(position).getId());
                mContext.startActivity(intent);
            }
        });

    }

    private static final String TAG = "CardAdapter";

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: 集合" + allList.size());
        return allList == null ? 0 : allList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.work_name)
        TextView workName;
        @BindView(R.id.work_salary)
        TextView workSalary;
        @BindView(R.id.work_salary_second)
        TextView workSalarySecond;
        @BindView(R.id.work_info_short)
        TextView workInfoShort;
        @BindView(R.id.work_info)
        TextView workInfo;
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.work_company_name)
        TextView workCompanyName;
        @BindView(R.id.work_company_info_short)
        TextView workCompanyInfoShort;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.work_company_info)
        TextView workCompanyInfo;
        @BindView(R.id.work_company_pos)
        TextView workCompanyPos;
        @BindView(R.id.work_info_next_ll)
        View workInfoNextLl;
        @BindView(R.id.work_salary_right)
        TextView workSalaryRight;

        public CardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
