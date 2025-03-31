package me.chchnikolaou.unipiplishopping.lib.builder;

import me.chchnikolaou.unipiplishopping.R;
import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;

public class ToastBuilder {

    private final SmartActivity smartActivity;
    private Toast.ToastStyle toastStyle;
    private String message;
    private int duration;



    public ToastBuilder(SmartActivity smartActivity) {
        this.smartActivity = smartActivity;

    }

    public ToastBuilder setStyle(Toast.ToastStyle toastStyle) {
        this.toastStyle = toastStyle;
        return this;
    }

    public ToastBuilder setMessage(String message) {
        this.message = message;
        return this;
    }


    public ToastBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }


    public Toast build() {

        Toast toast = new Toast(smartActivity, message);
        toast.setDuration(duration);
        if(toastStyle!=null) toast.setToastStyle(toastStyle);
        return toast.build();

    }


}
