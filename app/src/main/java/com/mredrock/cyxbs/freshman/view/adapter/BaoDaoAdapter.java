package com.mredrock.cyxbs.freshman.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.view.activity.BaoDaoActivity;
import com.mredrock.cyxbs.freshman.view.activity.Main2Activity;

import java.util.List;

/**
 * Created by 67698 on 2018/8/17.
 */

public class BaoDaoAdapter extends RecyclerView.Adapter<BaoDaoAdapter.BaoDaoAdapterViewHolder>{
    Context context;
    List<Strategy>list;
    Activity activity;

    String[] buzou={"(步骤一)","(步骤二)","(步骤三)"};
    public BaoDaoAdapter(List<Strategy>list, Context context, Activity activity)
    {
        this.list=list;
        this.activity=activity;
        this.context=context;
    }

    @Override
    public BaoDaoAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaoDaoAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_baodao,parent,false));
    }

    @Override
    public void onBindViewHolder(BaoDaoAdapterViewHolder holder, final int position) {
            ImageView imageView=holder.imageView1;
        Glide.with(context).load("http://47.106.33.112:8080/welcome2018"+list.get(position).getPicture().get(0)).into(imageView);
        ImageView imageView1=holder.imageView2;
        Glide.with(context).load("http://47.106.33.112:8080/welcome2018"+list.get(position).getPicture().get(1)).into(imageView1);
        TextView textView=holder.title;
        textView.setText(list.get(position).getName());
        final TextView textView1=holder.content;
        textView1.setMaxLines(4);
        textView1.setText(list.get(position).getContent());
        TextView textView2 = holder.buzhou;
        textView2.setText(buzou[position]);
        ImageView imageView2=holder.heshang;
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, Main2Activity.class);
                intent.putExtra("content",list.get(position).getContent());
                intent.putExtra("title",list.get(position).getName());
                intent.putExtra("image1","http://47.106.33.112:8080/welcome2018"+list.get(position).getPicture().get(0));
                intent.putExtra("image2","http://47.106.33.112:8080/welcome2018"+list.get(position).getPicture().get(1));
                intent.putExtra("buzou",buzou[position]);
                activity.startActivity(intent);
                activity.overridePendingTransition(0,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BaoDaoAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        ImageView imageView2;
        TextView title;
        TextView content;
        TextView buzhou;
        ImageView heshang;
        public BaoDaoAdapterViewHolder(View itemView) {
            super(itemView);
            imageView1=(ImageView)itemView.findViewById(R.id.Baodao_image1);
            imageView2=(ImageView)itemView.findViewById(R.id.Baodao_image2);
            title=(TextView)itemView.findViewById(R.id.BaoDao_activity_title);
            content=(TextView)itemView.findViewById(R.id.BaoDao_activity_content);
            buzhou=(TextView)itemView.findViewById(R.id.BaoDao_activity_buzou);
            heshang=(ImageView)itemView.findViewById(R.id.BaoDao_hes);
        }
    }
}
