package com.baway.chilijie.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.chilijie.R;
import com.baway.chilijie.bean.EffecInfo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by MacBook- on 2017/4/17.
 */
public class MaskRecycleViewAdapter extends RecyclerView.Adapter<MaskRecycleViewAdapter.MyViewHolder> {
    private Context context;
    private List<EffecInfo> data;

    //声明接口
    public OnItemClickListener itemClickListener;
    //定义接口
    public interface OnItemClickListener{
        void onItenClick(View view,int position);
    }
    //对外提供一个set方法
    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    public MaskRecycleViewAdapter(Context context, List<EffecInfo> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view=View.inflate(context, R.layout.linerlayout_gridview_item,null);
        final MyViewHolder holder=new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(itemClickListener!=null){
                    itemClickListener.onItenClick(v,position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getGoods_img()).into(holder.item_im);
        holder.t1.setText(data.get(position).getEfficacy());
        holder.t2.setText(data.get(position).getGoods_name());
        holder.t3.setText(data.get(position).getShop_price()+"");
        holder.t4.setText(data.get(position).getMarket_price()+"");
        //价格上划一横线
        holder.t4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final ImageView item_im;
        private final TextView t1,t2,t3,t4;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_im = (ImageView) itemView.findViewById(R.id.ll_iv);
            t1 = (TextView) itemView.findViewById(R.id.ll_tv0);
            t2= (TextView) itemView.findViewById(R.id.ll_tv1);
            t3= (TextView) itemView.findViewById(R.id.ll_tv2);
            t4= (TextView) itemView.findViewById(R.id.ll_tv3);
        }
    }
}
