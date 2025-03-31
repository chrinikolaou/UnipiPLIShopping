package me.chchnikolaou.unipiplishopping.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.chchnikolaou.unipiplishopping.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.product_text_view);
        imageView = itemView.findViewById(R.id.product_image_view);

    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
