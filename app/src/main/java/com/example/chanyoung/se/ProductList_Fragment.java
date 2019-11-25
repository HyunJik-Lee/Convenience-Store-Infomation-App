package com.example.chanyoung.se;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class ProductList_Fragment extends Fragment {
    Spinner classificationSp, sortSp;
    private TabLayout tabLayout;
    GridView productList_gridview;

    String setCname;
    String setType;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Constants.RequestCode.REQUEST_CODE_NAME_INPUT: {
                if (resultCode == ProductInfoActivity.RESULT_OK) {
                    String text = data.getStringExtra(KeySets.KEY_NAME_INPUT);
                    if (!TextUtils.isEmpty(text)) {
                        textResult.setText(text);
                    }
                }
                break;
            }
        }*/
    }

    String setSort;
    Product_ItemGridViewAdpter adapter;

    User user;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() != null && getActivity() instanceof MainActivity) {
            user = ((User) ((MainActivity) getActivity()).getData());
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_productlist_fragment, container, false);

        setCname = "전체";

        classificationSp = (Spinner) view.findViewById(R.id.productListFragment_classification_Spinner);
        classificationSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Product_ItemGridViewAdpter adapter = new Product_ItemGridViewAdpter();
                setType = String.valueOf(adapterView.getItemAtPosition(i));
                getProductlist();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sortSp = (Spinner) view.findViewById(R.id.productListFragment_sort_Spinner);
        sortSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Product_ItemGridViewAdpter adapter = new Product_ItemGridViewAdpter();
                setSort = String.valueOf(adapterView.getItemAtPosition(i));
                getProductlist();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        productList_gridview = (GridView) view.findViewById(R.id.productlistGridView);

        tabLayout = (TabLayout) view.findViewById(R.id.Convenience_tab_layout);

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Product_ItemGridViewAdpter adapter = new Product_ItemGridViewAdpter();
                setCname = String.valueOf(tab.getText());
                setCname = setCname.replaceAll(" ", "");
                getProductlist();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public void getProductlist() {
        adapter = new Product_ItemGridViewAdpter();
        try {
            JSONArray getGoodsList = null;

            getGoodsList = new JSONArray(new DBHelper().execute("getGoodsList", "&conv=" + setCname + "&type=" + setType + "&sort=" + setSort).get());

            for (int i = 0; i < getGoodsList.length(); i++) {
                String gname = getGoodsList.getJSONObject(i).getString("gname");
                String cname = getGoodsList.getJSONObject(i).getString("cname");
                String imageurl = getGoodsList.getJSONObject(i).getString("imageurl");
                String price = getGoodsList.getJSONObject(i).getString("price");
                String event = getGoodsList.getJSONObject(i).getString("event");
                String gtype = getGoodsList.getJSONObject(i).getString("gtype");
                String ghate = getGoodsList.getJSONObject(i).getString("ghate");
                String glike = getGoodsList.getJSONObject(i).getString("glike");
                String date = getGoodsList.getJSONObject(i).getString("date");

                adapter.addItem(user, gname, cname, imageurl, price, event, gtype, ghate, glike, date);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        productList_gridview.setAdapter(adapter);
    }
}
