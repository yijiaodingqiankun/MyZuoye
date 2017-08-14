package com.jiyun.dell.myzuoye811;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL zhanghuirong on 2017/8/11.
 */

class MyReAdapter extends RecyclerView.Adapter<MyReAdapter.ViewHolder> {
    private Context context;
    private List<JianJie.CardBean> list;

    public MyReAdapter(Context context, List<JianJie.CardBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_buju, null);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.guoji.setText(list.get(position).getName()+list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView minzu;
        private final TextView guoji;
        private final TextView xingzuo;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            minzu = itemView.findViewById(R.id.minzu);
            guoji = itemView.findViewById(R.id.guoji);
            xingzuo = itemView.findViewById(R.id.xingzuo);
        }
    }
}
