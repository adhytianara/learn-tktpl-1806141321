package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private Button btnSubmit;
    private EditText etYourName;

    static {
        System.loadLibrary("HelloNameJni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        btnSubmit = findViewById(R.id.btn_submit);
        etYourName = findViewById(R.id.et_name);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yourName = etYourName.getText().toString();
                tvResult.setText(getNativeString(yourName));
                tvResult.setVisibility(View.VISIBLE);
            }
        });
    }

    private native String getNativeString(String string);
}