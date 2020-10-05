package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_aboutme, btn_stopwatch;
    private LinearLayout ly_aboutme, ly_stopwatch;
    private Button buttonStart, buttonStop;
    private TextView textViewTimer = null;

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_aboutme = findViewById(R.id.btn_aboutme);
        btn_aboutme.setOnClickListener(this);
        ly_aboutme = findViewById(R.id.ly_aboutme);
        btn_stopwatch = findViewById(R.id.btn_stopwatch);
        btn_stopwatch.setOnClickListener(this);
        ly_stopwatch = findViewById(R.id.ly_stopwatch);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        buttonStop = findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(StopwatchService.STOPWATCH_BR));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(br);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, StopwatchService.class));
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            String hours = intent.getStringExtra("hours");
            String minutes = intent.getStringExtra("minutes");
            String seconds = intent.getStringExtra("seconds");
            String milliseconds = intent.getStringExtra("milliseconds");
            textViewTimer.setText(hours + ":" + minutes + ":" + seconds + "." + milliseconds);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_aboutme:
                ly_aboutme.setVisibility(View.VISIBLE);
                ly_stopwatch.setVisibility(View.GONE);
                btn_aboutme.setEnabled(false);
                btn_aboutme.setAlpha(.5f);
                btn_stopwatch.setEnabled(true);
                btn_stopwatch.setAlpha(1f);
                break;
            case R.id.btn_stopwatch:
                ly_aboutme.setVisibility(View.GONE);
                ly_stopwatch.setVisibility(View.VISIBLE);
                btn_stopwatch.setEnabled(false);
                btn_stopwatch.setAlpha(.5f);
                btn_aboutme.setEnabled(true);
                btn_aboutme.setAlpha(1f);
                break;
            case R.id.buttonStart:
                startService(new Intent(MainActivity.this, StopwatchService.class));
                break;
            case R.id.buttonStop:
                stopService(new Intent(MainActivity.this, StopwatchService.class));
                textViewTimer.setText("00:00:00.0");
                break;
        }
    }
}