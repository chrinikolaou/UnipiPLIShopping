package me.chchnikolaou.unipiplishopping;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;

public class AccountActivity extends SmartActivity {

    private TextView nameView;
    private TextView ageView;
    private TextView genderView;

    public AccountActivity() {
        super(R.layout.activity_account, R.id.activity_account);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.nameView = findViewById(R.id.account_text_name);
        this.ageView = findViewById(R.id.account_text_age);
        this.genderView = findViewById(R.id.account_text_gender);

        setTopMenu(true).setBottomMenu(true).initiate();
        loadPersonalInfo();
    }



    @Override
    public void inflateTopMenu() {

    }

    @Override
    public void show(View view) {

    }

    private void loadPersonalInfo() {
        if(nameView != null) nameView.setText(String.valueOf(getFullName()));
        if(ageView != null) ageView.setText(String.valueOf(getAge()));
        if(genderView != null) genderView.setText(String.valueOf(getGender()));
    }

    public void save(View view) {
        if(nameView!=null) setFullName(nameView.getText().toString());
        if(ageView!=null) {
            try {
                int age = Integer.parseInt(ageView.getText().toString());
                setAge(age);
            } catch(NumberFormatException ex) {
                new ToastBuilder(this).setMessage("Please specify a real age number.").setStyle(Toast.ToastStyle.WARNING).setDuration(1).build().show();
                return;
            }
        }

        if(genderView!=null) setGender(genderView.getText().toString());

        new ToastBuilder(this).setMessage("All changes were saved successfully.")
                .setStyle(Toast.ToastStyle.SUCCESS).setDuration(1).build().show();
    }

    public void logout(View view) {
        getAuth().signOut();
        showActivity(view, LoginActivity.class);

    }
}