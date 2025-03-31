package me.chchnikolaou.unipiplishopping;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.chchnikolaou.unipiplishopping.lib.SmartActivity;

public class RegisterMethodActivity extends SmartActivity {

    public RegisterMethodActivity() {
        super(R.layout.activity_register_method, R.id.register_method);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiate();
    }

    @Override
    public void inflateTopMenu() {
        // Do nothing
    }

    @Override
    public void show(View view) {
        super.showActivity(view, RegisterActivity.class);
    }

    @Override
    public void unsupported(View view) {
        super.unsupported(view);
    }
}