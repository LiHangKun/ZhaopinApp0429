package com.lx.zhaopin.other;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.SelectQiWangBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectedHangYeAdapter extends RecyclerView.Adapter<SelectedHangYeAdapter.CustomViewHolder> {

    private List<SelectQiWangBean.DataListBean> list;
    private Context mContext;
    private DelListener delListener;

    public SelectedHangYeAdapter(Context context, List<SelectQiWangBean.DataListBean> strs) {
        this.list = strs;
        mContext = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_selected_hangye, viewGroup, false);
        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, final int i) {
        viewHolder.selHangyeTv.setText(list.get(i).getName());
        viewHolder.delHangyeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delListener != null){
                    delListener.onRemoved(list.get(i));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sel_hangye_tv)
        TextView selHangyeTv;
        @BindView(R.id.del_hangye_img)
        ImageView delHangyeImg;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setDelListener(DelListener delListener) {
        this.delListener = delListener;
    }

    public interface DelListener{
        void onRemoved(SelectQiWangBean.DataListBean bean);
    }

}
