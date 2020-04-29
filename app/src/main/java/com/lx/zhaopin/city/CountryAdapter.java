package com.lx.zhaopin.city;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.lx.zhaopin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2017/11/6.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private String[] letters = new String[]{
      "A","B","C","D","E","F","G","H","I","J","K","L","M","N"
            ,"O","P","Q","R","S","T","U","V","W","X","Y","Z"
    };

    private List<CityBean> data = new ArrayList<>();

    public interface onMyItemClickListener{
        void onItemClick(View view, int position);
    }

    private onMyItemClickListener listener;

    public  void setOnMyItemClickListener(onMyItemClickListener listener){
        this.listener  = listener;
    }

    private HashMap<String, Integer> indexPos = new HashMap<>();

    public CountryAdapter(List<CityBean> data){
        this.data = data;

        if (data!=null && data.size()>0){

            indexPos.put(PinyinUtil.getFirstLetter((String) data.get(0).getCitysName()),0);
            for (int i = 1;i <data.size(); i++){
                String thisLetter = PinyinUtil.getFirstLetter((String) data.get(i).getCitysName());
                String lastLetter = PinyinUtil.getFirstLetter((String) data.get(i-1).getCitysName());
                if (!TextUtils.equals(thisLetter, lastLetter)){
                    indexPos.put(thisLetter, i);
                }
            }

            indexPos.put("#",0);
            indexPos.put("A",0);
            for (int i = 1; i<letters.length; i++){
                if (indexPos.get(letters[i])==null){
                    indexPos.put(letters[i], indexPos.get(letters[i-1]));
                }
            }
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item_text,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String str = (String) data.get(position).getCitysName();
        /*if (type==1){//展示 2  级数据
            str = data.get(position).areaName;
        }else{//展示 最后 一级数据
            str
        }*/

        String lastItemLetter = "";
        String thisLetter = PinyinUtil.getFirstLetter(str);

        if (position==0){
            holder.letter.setVisibility(View.VISIBLE);
            holder.letter.setText(thisLetter);
            holder.text.setVisibility(View.VISIBLE);
            holder.text.setText(str);
        }else{
            lastItemLetter = PinyinUtil.getFirstLetter((String) data.get(position-1).getCitysName());
            if (lastItemLetter.equals(thisLetter)){
                holder.letter.setVisibility(View.GONE);
                holder.text.setVisibility(View.VISIBLE);
                holder.text.setText(str);
            }else{
                holder.letter.setVisibility(View.VISIBLE);
                holder.letter.setText(thisLetter);
                holder.text.setVisibility(View.VISIBLE);
                holder.text.setText(str);
            }
        }

        holder.text.setText(str);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(v,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView letter,text;

        public ViewHolder(View itemView) {
            super(itemView);
            letter = (TextView)itemView.findViewById(R.id.letter);
            text = (TextView)itemView.findViewById(R.id.text);
        }

    }

    public int getLetterPosition(String str){

        if (null==indexPos.get(str)){
            return 0;
        }else{
            return indexPos.get(str);
        }

    }

}
