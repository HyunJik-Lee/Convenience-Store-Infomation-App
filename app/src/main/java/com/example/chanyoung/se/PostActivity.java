package com.example.chanyoung.se;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
    LayoutInflater inflater;
    ImageButton menuBtn;
    ImageButton exitBtn;

    ImageButton commentEditBtn;

    TextView titleTv, contentsTv, dateTv, loveTv, hateTv;

    EditText commentEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_interface);

        menuBtn = (ImageButton)findViewById(R.id.post_menu_btn);
        registerForContextMenu(menuBtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostActivity.this.openContextMenu(view);
            }
        });

        exitBtn = (ImageButton)findViewById(R.id.post_exit_btn) ;
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        titleTv = (TextView)findViewById(R.id.post_title_Tv);
        contentsTv = (TextView)findViewById(R.id.post_contents_Tv);
        dateTv = (TextView)findViewById(R.id.post_date_Tv);
        loveTv = (TextView)findViewById(R.id.post_love_Tv);
        hateTv = (TextView)findViewById(R.id.post_hate_Tv);


        commentEt = (EditText)findViewById(R.id.post_comment_Et) ;
        commentEditBtn = (ImageButton)findViewById(R.id.post_commentEdit_btn);

        Intent intent = getIntent();
        BorderItem post = (BorderItem)intent.getSerializableExtra("post");

        titleTv.setText(post.title);
        contentsTv.setText(post.content);
        dateTv.setText(post.date);
        loveTv.setText(post.like);
        hateTv.setText(post.hate);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout post_layout1 = (LinearLayout)inflater.inflate(R.layout.comment_interface,null);
        LinearLayout post_layout2 = (LinearLayout)inflater.inflate(R.layout.comment_interface,null);

        LinearLayout post_layout = (LinearLayout)findViewById(R.id.post_content_layout);

        LinearLayout loveBtn = (LinearLayout)findViewById(R.id.post_love_Btn);
        LinearLayout hateBtn = (LinearLayout)findViewById(R.id.post_hate_Btn);

        TextView loveTv = (TextView)findViewById(R.id.post_love_Tv);
        TextView hateTv = (TextView)findViewById(R.id.post_hate_Tv);

        loveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        hateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        post_layout.addView(post_layout1);
        post_layout.addView(post_layout2);

        commentEt = (EditText) findViewById(R.id.post_comment_Et);
        commentEditBtn = (ImageButton)findViewById(R.id.post_commentEdit_btn);
        commentEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 댓글을 작성하는 부분

                LinearLayout post_layout = (LinearLayout)inflater.inflate(R.layout.comment_interface,null);
            }
        });
    }
}
