package me.chchnikolaou.unipiplishopping;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;

public class RegisterActivity extends SmartActivity {

    EditText email,username,password;

    public RegisterActivity() {
        super(R.layout.activity_register, R.id.register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiate();

        email = findViewById(R.id.register_email);
        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);

        View view = findViewById(R.id.register);
        view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });

    }


    @Override
    public void show(View view) {
        super.showActivity(view, LoginActivity.class);
    }

    @Override
    public void inflateTopMenu() {
        // Do nothing
    }

    public void signUp(View view) {

        if(email==null || username==null || password==null
        || email.getText().toString().isBlank() || username.getText().toString().isBlank() || password.getText().toString().isBlank()) {
            new ToastBuilder(this)
                    .setMessage("Please fill out all fields.")
                    .setStyle(Toast.ToastStyle.WARNING)
                    .setDuration(2)
                    .build()
                    .show();
            return;
        }

        getAuth().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(
                (e)-> {
                    if(e.isSuccessful()) {
                        setUser(getAuth().getCurrentUser());
                        new ToastBuilder(this)
                                .setMessage("You have successfully signed up! Please login.")
                                .setStyle(Toast.ToastStyle.SUCCESS)
                                .setDuration(2)
                                .build()
                                .show();

                        showActivity(view, LoginActivity.class);
                        return;
                    }
                    new ToastBuilder(this)
                            .setMessage("Failed to sign up. Please try again.")
                            .setStyle(Toast.ToastStyle.WARNING)
                            .setDuration(2)
                            .build()
                            .show();
                }
        );


    }

}