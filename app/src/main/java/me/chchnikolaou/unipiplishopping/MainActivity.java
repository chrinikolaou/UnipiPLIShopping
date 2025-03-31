package me.chchnikolaou.unipiplishopping;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import me.chchnikolaou.unipiplishopping.adapter.ProductAdapter;
import me.chchnikolaou.unipiplishopping.lib.FetchedProduct;
import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;
import me.chchnikolaou.unipiplishopping.lib.helper.DatabaseHelper;
import me.chchnikolaou.unipiplishopping.object.Product;

/*
 * This is the start up activity class.
 * Everything begins from this activity if the user is logged in.
 */

public class MainActivity extends SmartActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MainActivity() {
        super(R.layout.activity_main, R.id.activity_main);
    }

    @Override
    public void initiate() {
        super.initiate();

        setupProductsView();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * Custom Class Attributes
         */
        setTopMenu(true).setBottomMenu(true).initiate();

    }



    @Override
    public void inflateTopMenu() {


        LinearLayout layout = findViewById(R.id.top_menu);

        LayoutInflater inflater = LayoutInflater.from(this);

        int[] icons = new int[] {R.drawable.avatar_boy, R.drawable.settings};


        LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );


        for(int i = 0; i < icons.length; i++) {

            final int finalI = i;
            View item = inflater.inflate(R.layout.layout_top_menu_icon, layout, false);
            item.findViewById(R.id.profile_picture).setBackgroundResource(icons[i]);
            item.setOnClickListener((listener)-> {

                if(icons[finalI] == R.drawable.avatar_boy) {
                    showActivity(null, AccountActivity.class);
                    return;
                }

            });

            layout.addView(item);
            if (i < icons.length - 1) {
                View spacer = new View(this);
                spacer.setLayoutParams(itemParams);
                layout.addView(spacer);
            }
        }

    }



    @Override
    public void show(View view) {

    }

    private void setupProductsView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);


        recyclerView = findViewById(R.id.product_recycle_view);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 30;
                outRect.left = 30;
            }
        });

        progressBar = findViewById(R.id.product_recycleview_progressbar);

        progressBar.setVisibility(View.VISIBLE);

        DatabaseHelper.getProducts(getReference(), new FetchedProduct() {

            @Override
            public void onFetched(List<Product> products) {

                progressBar.setVisibility(View.GONE);
                new ToastBuilder(MainActivity.this).setStyle(Toast.ToastStyle.SUCCESS)
                        .setMessage("All products have been fetched.")
                        .setDuration(1)
                        .build().show();

                ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, products);
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onError(DatabaseError error) {
                new ToastBuilder(MainActivity.this).setStyle(Toast.ToastStyle.WARNING)
                        .setMessage("Could not fetch products. " + error.getMessage())
                        .setDuration(1)
                        .build().show();
            }
        });


    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}