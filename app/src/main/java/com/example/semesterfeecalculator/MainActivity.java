package com.example.semesterfeecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView tvPercent, tvFees, tvPayable;
    EditText tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.waiver_seekbar);
        tvPercent = findViewById(R.id.tv_parcent);
        tvFees = findViewById(R.id.tv_fees);
        tvPayable = findViewById(R.id.tv_payable);
        tk = findViewById(R.id.amount);
        tvPayable.setText("৳" + 000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPercent.setText("" + progress + "%");
                computeTotalFees();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });
        tk.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                computeTotalFees();
            }
        });

    }


    private void computeTotalFees() {
        if (tk.getText().toString().isEmpty()) {
            tvPayable.setText("৳" + 00);
            return;
        }
        int amount = Integer.parseInt(tk.getText().toString());

        int waiver = seekBar.getProgress();
        int total = (amount * (100 - waiver)) / 100;
        tvPayable.setText("৳" + total);
    }


}
