package com.jiyun.dell.myzuoye811;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL zhanghuirong on 2017/8/11.
 */

class MyAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertview == null) {
            holder = new ViewHolder();
            convertview = View.inflate(context, R.layout.item, null);
            holder.text = convertview.findViewById(R.id.text);
            convertview.setTag(holder);
        } else {
            holder = (ViewHolder) convertview.getTag();
        }

        holder.text.setText(list.get(position));


        return convertview;
    }

    class ViewHolder {
        TextView text;
    }
}
