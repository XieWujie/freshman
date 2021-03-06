package com.mredrock.cyxbs.freshman.view.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Group;
import com.mredrock.cyxbs.freshman.model.convert.Group_code;
import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;

import java.util.List;

public class OnlineRcAdapter extends RecyclerView.Adapter<OnlineRcAdapter.ViewHolder> {


    private List<Group_x_y> mList;
    private Context mContext;
    private OnItemClickListener listener;

    public OnlineRcAdapter(List<Group_x_y> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_rc_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mList.size()!=1) {
            final Group_x_y group = mList.get(position);
            holder.autoText.setText(group.getN());
            holder.autoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(group.getName());
                }
            });
        } else {
            final Group_code code= mList.get(0).getArray1().get(position);
            holder.autoText.setText(code.getName()+code.getCode());
            holder.autoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addDialog(code,view);
                }
            });
        }
    }

    private void addDialog(final Group_code code,final View view){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        TextView textView = new TextView(mContext);
        textView.setText("复制");
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(60),dip2px(25));
        dialog.setMessage("是否复制号码？");
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClipboardManager clipboardManager = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText("label",code.getCode()));
                Snackbar.make(view,"复制成功",Snackbar.LENGTH_LONG).show();
            }
        });
        dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public  int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
    public void onItemClick(OnItemClickListener listener){
        if (this.listener==null){
            this.listener = listener;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size()==1?mList.get(0).getArray1().size():mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView autoText;
        public ViewHolder(View itemView) {
            super(itemView);
            autoText = (TextView)itemView.findViewById(R.id.online_rc_text);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(String name);
    }
}
