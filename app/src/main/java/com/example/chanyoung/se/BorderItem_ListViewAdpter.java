package com.example.chanyoung.se;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

class BorderItem implements Serializable {
    String title, content, date,like,hate;

    BorderItem(String title, String content, String date, String hate, String like) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.hate = hate;
        this.like = like;
    }
}

public class BorderItem_ListViewAdpter extends BaseAdapter {
    private ArrayList<BorderItem> listViewBorderItemList = new ArrayList<BorderItem>();

    @Override
    public int getCount() {
        return listViewBorderItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewBorderItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.board_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titleTv = (TextView) convertView.findViewById(R.id.border_title_Tv);
        TextView contentTv = (TextView) convertView.findViewById(R.id.border_contents_Tv);
        TextView dateTv = (TextView) convertView.findViewById(R.id.border_date_Tv);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        BorderItem listViewItem = listViewBorderItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영 ( Test 중 )
        titleTv.setText(listViewItem.title);
        contentTv.setText(listViewItem.content);
        dateTv.setText(listViewItem.date);

        return convertView;
    }

    public void addItem(String title, String content, String date, String hate, String like) {
        BorderItem item = new BorderItem(title, content, date, hate, like);

        listViewBorderItemList.add(item);
    }

}
