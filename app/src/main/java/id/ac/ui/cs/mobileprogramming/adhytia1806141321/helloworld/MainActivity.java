package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_submit;
    private LinearLayout ly_aboutme;
    private EditText et_aboutme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        ly_aboutme = findViewById(R.id.ly_aboutme);
        et_aboutme = findViewById(R.id.et_aboutme);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                SimplePassword passwordObject = new SimplePassword();
                String passString = passwordObject.getPassword();
                String userInput = et_aboutme.getText().toString().toLowerCase();
                if (userInput.equals(passString)) {
                    ly_aboutme.setVisibility(View.VISIBLE);
                    btn_submit.setVisibility(View.GONE);
                    et_aboutme.setVisibility(View.GONE);
                } else {
                    et_aboutme.getText().clear();
                    et_aboutme.setError("ketik aboutme");
                }
                break;
        }
    }
}