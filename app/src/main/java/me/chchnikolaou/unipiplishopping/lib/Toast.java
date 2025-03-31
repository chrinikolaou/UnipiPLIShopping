package me.chchnikolaou.unipiplishopping.lib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.chchnikolaou.unipiplishopping.R;

public class Toast extends android.widget.Toast {

    private final SmartActivity smartActivity;
    private final String message;
    private ToastStyle toastStyle;


    public Toast(SmartActivity smartActivity, String message) {
        super(smartActivity.getBaseContext());
        this.smartActivity = smartActivity;
        this.message = message;
        setText(message);

    }

    public void setToastStyle(ToastStyle toastStyle) {
        this.toastStyle = toastStyle;
    }


    @Override
    public void show() {
        if(message==null) return;
        super.show();
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public void setView(View view) {
        super.setView(view);

    }

    @Nullable
    @Override
    public View getView() {
        LayoutInflater inflater = getSmartActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_toast, (ViewGroup) smartActivity.findViewById(R.id.layout_toast_frame));

        if(toastStyle==null) toastStyle = ToastStyle.INFO;

        switch(toastStyle) {
            case INFO:
                view.setBackgroundResource(R.drawable.toast_info);
            break;

            case WARNING:
                view.setBackgroundResource(R.drawable.toast_warning);
                break;

            case SUCCESS:
                view.setBackgroundResource(R.drawable.toast_success);
                break;
        }


        TextView textView = view.findViewById(R.id.layout_toast_textview);
        if(textView==null) return null;
        if(getMessage()!=null) textView.setText(getMessage());
        else textView.setText("Invalid message.");
        return view;

    }

    @Override
    public void setDuration(int duration) {
        super.setDuration(duration);
    }

    @Override
    public int getDuration() {
        return super.getDuration();
    }

    @Override
    public void setMargin(float horizontalMargin, float verticalMargin) {
        super.setMargin(horizontalMargin, verticalMargin);
    }

    @Override
    public float getHorizontalMargin() {
        return super.getHorizontalMargin();
    }

    @Override
    public float getVerticalMargin() {
        return super.getVerticalMargin();
    }

    @Override
    public void setGravity(int gravity, int xOffset, int yOffset) {
        super.setGravity(gravity, xOffset, yOffset);
    }

    @Override
    public int getGravity() {
        return super.getGravity();
    }

    @Override
    public int getXOffset() {
        return super.getXOffset();
    }

    @Override
    public int getYOffset() {
        return super.getYOffset();
    }

    @Override
    public void addCallback(@NonNull Callback callback) {
        super.addCallback(callback);
    }

    @Override
    public void removeCallback(@NonNull Callback callback) {
        super.removeCallback(callback);
    }

    @Override
    public void setText(int resId) {
        super.setText(resId);
    }

    @Override
    public void setText(CharSequence s) {
        super.setText(s);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public SmartActivity getSmartActivity() {
        return smartActivity;
    }

    public String getMessage() {
        return message;
    }

    public Toast build() {
        setView(getView());
        return this;
    }

    public enum ToastStyle {
        INFO, WARNING, SUCCESS;
    }
}

