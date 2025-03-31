package me.chchnikolaou.unipiplishopping.lib;

import android.view.View;

import java.util.Map;

public interface ActivityLib {

    void loadData();
    void unsupported(View view);
    void showActivity(View view, Class<?> clazz);
    void showActivity(View view, Class<?> clazz, Map<String,Object> data);
}
