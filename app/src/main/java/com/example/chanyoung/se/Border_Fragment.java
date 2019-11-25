package com.example.chanyoung.se;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class Border_Fragment extends Fragment {

    ListView border_listview = null;
    LinearLayout writeBtn;

    ImageButton menuBtn;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_border_fragment, container, false);

        menuBtn = (ImageButton)view.findViewById(R.id.communityFragment_menu_Btn);

        init_BorderList();

        writeBtn = (LinearLayout)view.findViewById(R.id.communityFragment_write_btn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent writePostActivtiy_intent = new Intent(getActivity(), WritePostActivity.class);
                startActivity(writePostActivtiy_intent);
            }
        });


        return view;
    }

    public void init_BorderList() {
        final BorderItem_ListViewAdpter adapter = new BorderItem_ListViewAdpter();

        border_listview = (ListView) view.findViewById(R.id.atCommunity_border_listView);
        border_listview.setAdapter(adapter);

        try {
            JSONArray getBoardList = new JSONArray(new DBHelper().execute("getBoardList", "").get());

            for (int i = 0; i < getBoardList.length(); i++) {
                String title = getBoardList.getJSONObject(i).getString("title");
                String content = getBoardList.getJSONObject(i).getString("content");
                String wdate = getBoardList.getJSONObject(i).getString("wdate");
                String bhate = getBoardList.getJSONObject(i).getString("bhate");
                String blike = getBoardList.getJSONObject(i).getString("blike");

                adapter.addItem(title, content, wdate, bhate, blike);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        border_listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                final Intent postActivtiy_intent = new Intent(getActivity(), PostActivity.class);
                postActivtiy_intent.putExtra("post", (BorderItem)adapter.getItem(position));
                startActivity(postActivtiy_intent);
            }
        });
    }
}
