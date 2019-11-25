package com.example.chanyoung.se;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SignUpActivity extends AppCompatActivity {

    Button okBtn;
    Button cancelBtn;

    EditText idEt, pwEt, pwcheckEt, nameEt, phnumEt, emailEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_interface);

        idEt = (EditText) findViewById(R.id.signup_id_Et);
        pwEt = (EditText) findViewById(R.id.signup_pw_Et);
        pwcheckEt = (EditText) findViewById(R.id.signup_pwcheck_Et);
        nameEt = (EditText) findViewById(R.id.signup_name_Et);
        phnumEt = (EditText) findViewById(R.id.signup_phnum_Et);
        emailEt = (EditText) findViewById(R.id.signup_email_Et);

        okBtn = (Button) findViewById(R.id.signup_ok_Btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String sendMsg = "&id=" + idEt.getText().toString() +
                            "&passwd=" + pwEt.getText().toString() +
                            "&name=" + nameEt.getText().toString() +
                            "&cellphone=" + phnumEt.getText().toString() +
                            "&email=" + emailEt.getText().toString();

                    String signUp_result = new DBHelper().execute("signUp", sendMsg).get();

                    if (signUp_result.equals("true")) {
                        new AlertDialog.Builder(SignUpActivity.this)
                                .setTitle("알림")
                                .setMessage("회원가입 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                })
                                .show();
                    } else if (signUp_result.indexOf("무결성") > 0)
                        Toast.makeText(getApplicationContext(), "회원가입 실패\n아이디가 중복되었습니다", Toast.LENGTH_SHORT).show();
                    else if (signUp_result.indexOf("NULL") > 0)
                        Toast.makeText(getApplicationContext(), "회원가입 실패\n모든 정보를 적어주세요", Toast.LENGTH_SHORT).show();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelBtn = (Button) findViewById(R.id.signUp_cancel_Btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
