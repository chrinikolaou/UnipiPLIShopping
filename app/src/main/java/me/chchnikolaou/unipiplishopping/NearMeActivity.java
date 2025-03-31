package me.chchnikolaou.unipiplishopping;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import me.chchnikolaou.unipiplishopping.lib.FetchedProduct;
import me.chchnikolaou.unipiplishopping.lib.SmartActivity;
import me.chchnikolaou.unipiplishopping.lib.Toast;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;
import me.chchnikolaou.unipiplishopping.lib.helper.DatabaseHelper;
import me.chchnikolaou.unipiplishopping.lib.helper.LocationHelper;
import me.chchnikolaou.unipiplishopping.object.Product;

public class NearMeActivity extends SmartActivity implements LocationListener {

    private LocationManager locationManager;
    private TextView textView;
    private TextView dataTextView;
    private TextView radiusTextView;

    private SeekBar seekBar;
    private List<Product> cachedProducts;

    public NearMeActivity() {
        super(R.layout.activity_nearme, R.id.activity_nearme);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTopMenu(true).setBottomMenu(true).initiate();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, this);

        textView = findViewById(R.id.nearme_position);
        textView.setText(String.format(getString(R.string.nearme_location), 0,0));

        dataTextView = findViewById(R.id.nearme_data);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        DatabaseHelper.getProducts(getReference(), new FetchedProduct() {
            @Override
            public void onFetched(List<Product> products) {

                    progressBar.setVisibility(View.GONE);
                    NearMeActivity.this.cachedProducts = products;
                    dataTextView.setText(String.format(getString(R.string.nearme_products), cachedProducts.size()));
            }

            @Override
            public void onError(DatabaseError error) {

                dataTextView.setText(String.format(getString(R.string.nearme_products), -1));
            }
        });

        radiusTextView = findViewById(R.id.nearme_radius);

        seekBar = findViewById(R.id.seekBar);
        radiusTextView.setText(String.format(getString(R.string.nearme_radius_text), 0));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                radiusTextView.setText(String.format(getString(R.string.nearme_radius_text), seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }




    @Override
    public void inflateTopMenu() {

    }

    @Override
    public void show(View view) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 123) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                new ToastBuilder(this).setMessage("You have enabled location permissions!").setDuration(2).setStyle(Toast.ToastStyle.SUCCESS).build().show();
                return;
            }
            showActivity(null, MainActivity.class);
            new ToastBuilder(this).setMessage("Failed to enable location permissions!").setDuration(2).setStyle(Toast.ToastStyle.WARNING).build().show();
        }

    }

    @Override
    public void onLocationChanged(@NonNull android.location.Location location) {
        if(textView==null) return;
        textView.setText(String.format(getString(R.string.nearme_location), location.getLongitude(), location.getLatitude()));


        double radius = seekBar.getProgress();
        cachedProducts.forEach(p-> {
            for(me.chchnikolaou.unipiplishopping.object.Location loc : p.getLocations()) {
                double distance = loc.distance(LocationHelper.convert(location));
                if(distance > radius) {
                    new ToastBuilder(NearMeActivity.this).setMessage("diff: " + distance).setStyle(Toast.ToastStyle.INFO).setDuration(1).build().show();
                    continue;
                }
                new ToastBuilder(NearMeActivity.this).setMessage(p.getTitle() + " is in " + distance + " meters within you.").setDuration(2).setStyle(Toast.ToastStyle.INFO).build().show();
            }
        });

    }
}