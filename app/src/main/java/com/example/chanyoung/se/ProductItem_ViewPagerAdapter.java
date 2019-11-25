package com.example.chanyoung.se;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

class ProductItem implements Serializable {
    String gname, cname, imageurl, price, event, gtype, ghate, glove, date;
    ArrayList<String> favoirteProduct;
    boolean isFavorite;

    ProductItem(User user, String gname, String cname, String imageurl, String price, String event, String gtype, String ghate, String glove, String date ) {
        this.gname = gname;
        this.cname = cname;
        this.imageurl = imageurl;
        this.price = price;
        this.event = event;
        this.gtype = gtype;
        this.ghate = ghate;
        this.glove = glove;
        this.date = date;

        this.favoirteProduct = user.favoriteProduct;

        isFavorite = this.favoirteProduct.contains(this.gname);
    }
    void refresh(){
        isFavorite = favoirteProduct.contains(gname);
    }

}

class ProductItem_ViewPagerAdapter extends PagerAdapter {
    private ArrayList<ProductItem> productItem_ArrayList = new ArrayList<ProductItem>();

    @Override
    public int getCount() {
        return productItem_ArrayList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_list_item_view, container, false);
        ProductItem item = productItem_ArrayList.get(position);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent productInfoActivtiy_intent = new Intent(view.getContext(), ProductInfoActivity.class);
                productInfoActivtiy_intent.putExtra("product", (ProductItem) getItem(position));
                view.getContext().startActivity(productInfoActivtiy_intent);
            }
        });

        ImageView itemImageIv = view.findViewById(R.id.productListItemRecycle_image_Iv);
        TextView itemGNameTv = view.findViewById(R.id.productListItemRecycle_gname_Tv);
        TextView itemCNameTv = view.findViewById(R.id.productListItemRecycle_cname_Tv);
        TextView itemPriceTv = view.findViewById(R.id.productListItemRecycle_price_Tv);
        TextView itemTypeTv = view.findViewById(R.id.productListItemRecycle_type_Tv);

        itemGNameTv.setText(item.gname);
        itemCNameTv.setText(item.cname);
        itemPriceTv.setText(item.price);
        itemTypeTv.setText(item.gtype);

        container.addView(view);
        return view;
    }

    public void addItem(User user, String gname, String cname, String imageurl, String price, String event, String gtype, String ghate, String glove, String date ) {
        ProductItem item = new ProductItem(user, gname, cname, imageurl, price, event, gtype, ghate, glove, date);
        productItem_ArrayList.add(item);
    }

    public Object getItem(int position) {
        return productItem_ArrayList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (View) o);
    }
}