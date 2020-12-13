package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnClickMe;
    private TextView tvHello;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Android Native App");

        tvHello = findViewById(R.id.tv_hello);
        btnClickMe = findViewById(R.id.btn_clickme);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                tvHello.setText(String.format("Hello %d times", counter));
            }
        });
    }
}