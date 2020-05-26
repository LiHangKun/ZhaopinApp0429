package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PingJiaImageAdapter extends RecyclerView.Adapter<PingJiaImageAdapter.ViewHolder> {

    private List<String> mData;
    private Context mContext;

    public PingJiaImageAdapter() {
    }

    public PingJiaImageAdapter(Context context, List<String> list) {
        mContext = context;
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pingjia_in_image, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(mData.get(i)).into(viewHolder.roundedImageView);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClickListener(i);
            }
        });

    }

    private static final String TAG = "PingJiaImageAdapter";
    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + mData.size());
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final RoundedImageView roundedImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);

        }
    }


    private PingJiaImageAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(PingJiaImageAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int firstPosition);

    }

}
