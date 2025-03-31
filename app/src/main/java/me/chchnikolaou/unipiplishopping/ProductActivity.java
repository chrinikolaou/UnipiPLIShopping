package me.chchnikolaou.unipiplishopping;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.Timestamp;

import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;
import me.chchnikolaou.unipiplishopping.lib.builder.CodeBuilder;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;
import me.chchnikolaou.unipiplishopping.object.Product;
import me.chchnikolaou.unipiplishopping.order.Order;

public class ProductActivity extends SmartActivity {

    private Product product;

    private ImageView imageView;
    private TextView textView;
    private EditText editText;
    private Button button;

    public ProductActivity() {
        super(R.layout.activity_product, R.id.activity_product);
        this.product = null;
    }

    public ProductActivity(Product product) {
        super(R.layout.activity_product, R.id.activity_product);
        this.product = product;
    }

    @Override
    public void initiate() {
        super.initiate();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTopMenu(true).setBottomMenu(true).initiate();

        textView = findViewById(R.id.textview_product_activity);
        imageView = findViewById(R.id.imageview_product_activity);
        editText = findViewById(R.id.multiline_product_activity);
        button = findViewById(R.id.btn_product_activity);

        loadProductInfo();
    }

    private void loadProductInfo() {

        if(product==null) {
            // Try checking the intent
            String serialized = getIntent().getStringExtra("product");
            if(serialized==null || serialized.isBlank()) return;
            try
            {
                this.product = new Product(serialized);
            } catch (Exception ignored) {}
        }

        if(textView != null) textView.setText(String.format("%s (%s)", product.getTitle(), product.getCode()));
        if(imageView != null) imageView.setImageResource(product.getImageId());
        if(editText != null) editText.setText(product.getDescriptionString());

    }

    @Override
    public void inflateTopMenu() {

    }

    @Override
    public void show(View view) {

    }



    public void purchase(View view) {
        if(getAuth().getCurrentUser()==null) {
            new ToastBuilder(this).setMessage("You must sign-in to purchase products.")
                    .setStyle(Toast.ToastStyle.WARNING).setDuration(1).build().show();
            return;
        }
        new ToastBuilder(this).setMessage("You have ordered the " + product.getTitle())
                .setStyle(Toast.ToastStyle.SUCCESS).setDuration(1).build().show();

        getDatabase().getReference("orders").child(CodeBuilder.generate(10)).setValue(
                new Order(product.getCode(), getFullName(), product.getCost(), Timestamp.now()));


    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}