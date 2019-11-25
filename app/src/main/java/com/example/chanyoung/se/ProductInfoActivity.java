package com.example.chanyoung.se;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.chanyoung.se.R.drawable.star_btn;

public class ProductInfoActivity extends AppCompatActivity {

    ImageButton exitBtn;
    LayoutInflater inflater;

    ImageView favoriteBtn;
    TextView gnameTv, updateTv, loveTv, hateTv, priceTv, cnameTv, typeTv;

    EditText commentEt;
    ImageButton commentEditBtn;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.comment_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productinfo_interface);

        final Intent intent = getIntent();

        final User user = (User)intent.getSerializableExtra("user");
        final ProductItem productitem = (ProductItem) intent.getSerializableExtra("product");

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        exitBtn = (ImageButton) findViewById(R.id.productinfo_exit_btn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("product",productitem);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        gnameTv = (TextView) findViewById(R.id.productinfo_title_Tv);
        updateTv = (TextView) findViewById(R.id.productinfo_update_Tv);
        priceTv = (TextView) findViewById(R.id.productinfo_price_Tv);
        loveTv = (TextView) findViewById(R.id.productinfo_love_Tv);
        hateTv = (TextView) findViewById(R.id.productinfo_hate_Tv);
        cnameTv = (TextView) findViewById(R.id.productinfo_cname_Tv);
        typeTv = (TextView) findViewById(R.id.productinfo_type_Tv);

        gnameTv.setText(productitem.gname);
        updateTv.setText(productitem.date);
        loveTv.setText(productitem.glove);
        hateTv.setText(productitem.ghate);
        priceTv.setText(productitem.price);
        cnameTv.setText(productitem.cname);
        typeTv.setText(productitem.gtype);

        productitem.refresh();

        favoriteBtn = (ImageView) findViewById(R.id.productinfo_favoriteProduct_btn);
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productitem.isFavorite) {
                    favoriteBtn.setImageResource(R.drawable.star_btn);
                    productitem.favoirteProduct.remove(productitem.gname);
                    try {
                        String sendMsg = "&userid=" + user.id + "&pid=" + productitem.gname.replaceAll("%","%25");;
                        String login_result = new DBHelper().execute("delete_favoriteProduct", sendMsg).get();

                        if (login_result.equals("true")) {
                        } else {
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    favoriteBtn.setImageResource(R.drawable.starcheck_btn);
                    productitem.favoirteProduct.add(productitem.gname);
                    try {
                        String sendMsg = "&userid="+ user.id + "&pid=" + productitem.gname.replaceAll("%","%25");;
                        String login_result = new DBHelper().execute("inset_favoriteProduct", sendMsg).get();

                        if (login_result.equals("true")) {
                        } else {
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                productitem.refresh();
                System.out.println(String.valueOf(productitem.isFavorite));
            }
        });

        if (productitem.isFavorite)
            favoriteBtn.setImageResource(R.drawable.starcheck_btn);
        else
            favoriteBtn.setImageResource(R.drawable.star_btn);

        commentEt = (EditText) findViewById(R.id.productinfo_comment_Et);
        commentEditBtn = (ImageButton) findViewById(R.id.productinfo_commentEdit_btn);
        commentEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 댓글을 작성하는 부분

                LinearLayout post_layout = (LinearLayout) inflater.inflate(R.layout.comment_interface, null);
            }
        });

        LinearLayout post_layout1 = (LinearLayout) inflater.inflate(R.layout.comment_interface, null);
        LinearLayout post_layout2 = (LinearLayout) inflater.inflate(R.layout.comment_interface, null);
        LinearLayout post_layout3 = (LinearLayout) inflater.inflate(R.layout.comment_interface, null);
        LinearLayout post_layout4 = (LinearLayout) inflater.inflate(R.layout.comment_interface, null);

        final LinearLayout post_layout = (LinearLayout) findViewById(R.id.product_content_layout);

        registerForContextMenu(post_layout1);
        post_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductInfoActivity.this.openContextMenu(view);
            }
        });

        post_layout.addView(post_layout1);
        post_layout.addView(post_layout2);
        post_layout.addView(post_layout3);
        post_layout.addView(post_layout4);

    }
}
