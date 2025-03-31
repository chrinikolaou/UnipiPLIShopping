package me.chchnikolaou.unipiplishopping.lib.helper;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.chchnikolaou.unipiplishopping.lib.FetchedProduct;
import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;
import me.chchnikolaou.unipiplishopping.object.Product;
import me.chchnikolaou.unipiplishopping.user.User;

public class DatabaseHelper {

    public static void add(DatabaseReference reference, Product... product) {
        // Use a callback to handle async result
        getProducts(reference, new FetchedProduct() {
            @Override
            public void onFetched(List<Product> products) {

                Arrays.stream(product).forEach(p -> {
                    if (exist(products, p)) return;
                    reference.child(p.getCode()).setValue(p);
                });



            }

            @Override
            public void onError(DatabaseError error) {
                Log.d("DatabaseHelper", "Failed to: " + error.getMessage());
            }
        });
    }

    public static void getProducts(DatabaseReference reference, FetchedProduct callback) {
        Log.d("DatabaseHelper", "Fetching products from Firebase");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Product> list = new ArrayList<>();
                for (DataSnapshot ss : snapshot.getChildren()) {
                    Product product = ss.getValue(Product.class);
                    if (product != null) {
                        list.add(product);
                    }
                }
                Log.d("DatabaseHelper", "Fetched " + list.size() + " products");
                callback.onFetched(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DatabaseHelper", "Failed to fetch products: " + error.getMessage());
                callback.onError(error);
            }
        });
    }

    private static boolean exist(List<Product> list, Product product) {
        Product registered = list.stream().filter(p->p.getTitle().equalsIgnoreCase(product.getTitle())).findAny().orElse(null);
        return registered != null;
    }


}
