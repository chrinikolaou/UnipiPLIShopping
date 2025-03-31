package me.chchnikolaou.unipiplishopping.lib;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import me.chchnikolaou.unipiplishopping.object.Product;

public interface FetchedProduct {

    void onFetched(List<Product> products);
    void onError(DatabaseError error);


}
