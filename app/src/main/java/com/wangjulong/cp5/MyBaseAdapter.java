package com.wangjulong.cp5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * 数据适配器：数据源是开奖号码 List<Kjh>
 * Created by Administrator on 2016/9/9.
 */

class MyBaseAdapter extends BaseAdapter {
    private List<Kjh> list;
    private LayoutInflater mInflater;

    MyBaseAdapter(Context context, List<Kjh> list) {
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        // 文艺式
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);

            viewHolder.textViewSerial = (TextView) convertView.findViewById(R.id.textViewSerial);
            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.textView3);
            viewHolder.textView4 = (TextView) convertView.findViewById(R.id.textView4);
            viewHolder.textView5 = (TextView) convertView.findViewById(R.id.textView5);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Kjh kjh = list.get(position);
        viewHolder.textViewSerial.setText(String.format(Locale.CHINA,"%03d", kjh.serial));
        viewHolder.textViewTitle.setText(String.format(Locale.CHINA,"%08d",kjh.title));
        viewHolder.textView1.setText(String.format(Locale.CHINA,"%02d",kjh.n1));
        viewHolder.textView2.setText(String.format(Locale.CHINA,"%02d",kjh.n2));
        viewHolder.textView3.setText(String.format(Locale.CHINA,"%02d",kjh.n3));
        viewHolder.textView4.setText(String.format(Locale.CHINA,"%02d",kjh.n4));
        viewHolder.textView5.setText(String.format(Locale.CHINA,"%02d",kjh.n5));

        return convertView;


        // 文艺式


//        // 普通式
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.item,null);
//        }
//
//        TextView textViewSerial = (TextView) convertView.findViewById(R.id.textViewSerial);
//        TextView textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
//        TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
//        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
//        TextView textView3 = (TextView) convertView.findViewById(R.id.textView3);
//        TextView textView4 = (TextView) convertView.findViewById(R.id.textView4);
//        TextView textView5 = (TextView) convertView.findViewById(R.id.textView5);
//        Kjh kjh = list.get(position);
//        textViewSerial.setText(String.format(Locale.CHINA,"%03d", kjh.serial));
//        textViewTitle.setText(String.format(Locale.CHINA,"%08d",kjh.title));
//        textView1.setText(String.format(Locale.CHINA,"%02d",kjh.n1));
//        textView2.setText(String.format(Locale.CHINA,"%02d",kjh.n2));
//        textView3.setText(String.format(Locale.CHINA,"%02d",kjh.n3));
//        textView4.setText(String.format(Locale.CHINA,"%02d",kjh.n4));
//        textView5.setText(String.format(Locale.CHINA,"%02d",kjh.n5));
//
//        return convertView;
//        // 普通式

//        // 逗比式
//        View view = mInflater.inflate(R.layout.item, null);
//        TextView textViewSerial = (TextView) view.findViewById(R.id.textViewSerial);
//        TextView textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
//        TextView textView1 = (TextView) view.findViewById(R.id.textView1);
//        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
//        TextView textView3 = (TextView) view.findViewById(R.id.textView3);
//        TextView textView4 = (TextView) view.findViewById(R.id.textView4);
//        TextView textView5 = (TextView) view.findViewById(R.id.textView5);
//
//        Kjh kjh = list.get(position);
//        textViewSerial.setText(String.format(Locale.CHINA,"%03d", kjh.serial));
//        textViewTitle.setText(String.format(Locale.CHINA,"%08d",kjh.title));
//        textView1.setText(String.format(Locale.CHINA,"%02d",kjh.n1));
//        textView2.setText(String.format(Locale.CHINA,"%02d",kjh.n2));
//        textView3.setText(String.format(Locale.CHINA,"%02d",kjh.n3));
//        textView4.setText(String.format(Locale.CHINA,"%02d",kjh.n4));
//        textView5.setText(String.format(Locale.CHINA,"%02d",kjh.n5));
//
//        return view;
//        // 逗比式
    }
    class ViewHolder {
        public TextView textViewSerial;
        public TextView textViewTitle;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;

    }
}
