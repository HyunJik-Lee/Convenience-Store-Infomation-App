package com.example.chanyoung.se;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class WritePostActivity extends AppCompatActivity {

    ImageButton exitBtn, commitBtn;
    EditText titleEt,contentEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writepost_interface);

        exitBtn = (ImageButton)findViewById(R.id.writepost_exit_Btn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        commitBtn = (ImageButton)findViewById(R.id.writepost_commit_Btn);
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String sendMsg = "&id=5" + "&writer=root" + "&title=" + titleEt.getText().toString()+ "&content=" + contentEt.getText().toString();
                    System.out.println(sendMsg);
                    String write_result = new DBHelper().execute("writePost", sendMsg).get();

                    if (write_result.equals("true")) {
                        Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        titleEt = (EditText)findViewById(R.id.writepost_title_Et);
        contentEt = (EditText)findViewById(R.id.writepost_content_Et);
    }
}
