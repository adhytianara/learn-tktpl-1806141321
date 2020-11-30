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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_scan)
    Button btnScan;

    @BindView(R.id.btn_send)
    Button btnSend;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.list_wifi)
    ListView listViewWifi;

    @BindView(R.id.tv_nearby_network)
    TextView tvNearbyNetwork;

    private WifiManager wifiManager;
    private List<String> listWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Nearby Network");
        askPermission();
        init();

        btnScan.setOnClickListener(this);
        btnSend.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.btn_scan:
                progressBar.setVisibility(View.VISIBLE);
                scanNearbyNetwork();
                break;
            case R.id.btn_send:
                progressBar.setVisibility(View.VISIBLE);
                if (listWifi != null) {
                    sendListWifi(listWifi);
                }
                break;
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
        progressBar.setVisibility(View.GONE);
    }


    private void scanFailure() {
        List<ScanResult> results = wifiManager.getScanResults();
        displayWifiList(results);
        progressBar.setVisibility(View.GONE);
    }

    private void displayWifiList(List<ScanResult> results) {
        listWifi = new ArrayList<>();
        for (ScanResult scanResult : results) {
            listWifi.add(scanResult.SSID);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listWifi);

        listViewWifi.setAdapter(adapter);
    }

    private void sendListWifi(List<String> listWifi) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://c6554d4a1d6551be103520a26666116e.m.pipedream.net";
        RequestParams params = new RequestParams();
        for (int i = 0; i < listWifi.size(); i++) {
            params.put(String.valueOf(i + 1), listWifi.get(i));
        }
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplication(), "Success", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplication(), "Fail. Status code: " + statusCode, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}