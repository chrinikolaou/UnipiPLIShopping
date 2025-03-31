package me.chchnikolaou.unipiplishopping.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.chchnikolaou.unipiplishopping.ProductActivity;
import me.chchnikolaou.unipiplishopping.R;
import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.object.Product;

public class ProductAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final SmartActivity smartActivity;
    private final List<Product> products;

    public ProductAdapter(SmartActivity smartActivity, List<Product> products) {
        this.smartActivity = smartActivity;
        this.products = products;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        if(holder.getImageView() != null) holder.getImageView().setBackgroundResource(product.getImageId());
        if(holder.getTextView() != null) holder.getTextView().setText(product.getTitle());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new ToastBuilder(smartActivity).setMessage(product.getTitle()).setDuration(1).build().show();

                ProductActivity productActivity = new ProductActivity();
                productActivity.setProduct(product);

                Map<String, Object> map = new HashMap<>();
                map.put("product", product.toString());
                smartActivity.showActivity(null, ProductActivity.class, map);

            }
        };


        holder.getTextView().setOnClickListener(listener);
        holder.getImageView().setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public SmartActivity getSmartActivity() {
        return smartActivity;
    }

    public List<Product> getProducts() {
        return products;
    }
}

