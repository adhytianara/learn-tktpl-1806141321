package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_scan)
    Button btnScan;

    @BindView(R.id.list_wifi)
    ListView listViewWifi;

    @BindView(R.id.tv_nearby_network)
    TextView tvNearbyNetwork;

    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Nearby Network");

        askPermission();
        init();

        btnScan.setOnClickListener(this);
    }


    private void askPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 87);
        }
    }

    private void init() {
        wifiManager = (WifiManager)
                this.getSystemService(Context.WIFI_SERVICE);

        BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                boolean success = intent.getBooleanExtra(
                        WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    scanSuccess();
                } else {
                    scanFailure();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        this.registerReceiver(wifiScanReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_scan) {
            scanNearbyNetwork();
        }
    }

    private void scanNearbyNetwork() {
        boolean success = wifiManager.startScan();
        if (!success) {
            scanFailure();
        }
    }

    private void scanSuccess() {
        List<ScanResult> results = wifiManager.getScanResults();
        displayWifiList(results);
    }


    private void scanFailure() {
        List<ScanResult> results = wifiManager.getScanResults();
        displayWifiList(results);
    }

    private void displayWifiList(List<ScanResult> results) {
        List<String> listWifi = new ArrayList<>();
        for (ScanResult scanResult : results) {
            listWifi.add(scanResult.SSID);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listWifi);

        listViewWifi.setAdapter(adapter);
    }
}