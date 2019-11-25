package com.example.chanyoung.se;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class ListViewFriendsItem {
    String image, name, email;

    ListViewFriendsItem(String image, String name, String email) {
        this.image = image;
        this.name = name;
        this.email = email;
    }
}

public class Friends_ItemListViewAdpter extends BaseAdapter {
    private ArrayList<ListViewFriendsItem> listViewFriendsItem = new ArrayList<ListViewFriendsItem>();

    @Override
    public int getCount() {
        return listViewFriendsItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewFriendsItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.friends_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView nameTv = (TextView) convertView.findViewById(R.id.friends_name_Tv);
        TextView emailTv = (TextView) convertView.findViewById(R.id.friends_email_Tv);
        ImageView imageIv = (ImageView)convertView.findViewById(R.id.friends_image_Iv) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewFriendsItem listViewItem = listViewFriendsItem.get(position);

        // 아이템 내 각 위젯에 데이터 반영 ( Test 중 )
        nameTv.setText(listViewItem.name);
        emailTv.setText(listViewItem.email);
        //imageIv.setBackground(listViewItem.image);

        TextView firendFavoriteProductBtn = (TextView)convertView.findViewById(R.id.friends_favorite_Btn);
        firendFavoriteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(listViewItem.name);

                Intent favoiteProductActivity_intent = new Intent(parent.getContext(),FavoriteProductActivity.class);
                favoiteProductActivity_intent.putExtra("user",user);
                parent.getContext().startActivity(favoiteProductActivity_intent);
            }
        });


        return convertView;
    }

    public void addItem(String image, String name, String email) {
        ListViewFriendsItem item = new ListViewFriendsItem(image, name, email);

        listViewFriendsItem.add(item);
    }

}
