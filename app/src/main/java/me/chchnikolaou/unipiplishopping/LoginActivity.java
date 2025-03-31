package me.chchnikolaou.unipiplishopping;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.AuthCredential;

import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;

public class LoginActivity extends SmartActivity {

    EditText email, password;


    public LoginActivity() {
        super(R.layout.activity_login, R.id.login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new ToastBuilder(this).setMessage("Hello").setDuration(2).setStyle(Toast.ToastStyle.INFO).build().show();

        initiate();

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        View view = findViewById(R.id.login);
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
    public void inflateTopMenu() {
        // Do nothing
    }

    @Override
    public void unsupported(View view) {
        super.unsupported(view);
    }

    @Override
    public void show(View view) {
        super.showActivity(view, RegisterMethodActivity.class);
    }

    public void signIn(View view) {

        if(email==null || password==null || email.getText().toString().isBlank() || password.getText().toString().isBlank()) {

            new ToastBuilder(this)
                    .setMessage("Please fill out all fields.")
                    .setStyle(Toast.ToastStyle.WARNING)
                    .setDuration(2)
                    .build()
                    .show();

            return;
        }


        getAuth().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(
                    (e)-> {
                        if(e.isSuccessful()) {
                            setUser(getAuth().getCurrentUser());
                            new ToastBuilder(this)
                                    .setMessage("You have successfully logged in!")
                                    .setStyle(Toast.ToastStyle.SUCCESS)
                                    .setDuration(1)
                                    .build().show();

                            showActivity(view, MainActivity.class);

                            return;
                        }

                        new ToastBuilder(this)
                                .setMessage("Invalid Credentials!")
                                .setStyle(Toast.ToastStyle.WARNING)
                                .setDuration(1)
                                .build().show();

                    }
            );
            return;
    }

}