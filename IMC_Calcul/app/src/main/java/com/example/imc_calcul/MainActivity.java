package com.example.imc_calcul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText mTaille;
    private EditText mPoids;
    private Button mCalc;
    private TextView mResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTaille = findViewById(R.id.et_taille);
        mPoids = findViewById(R.id.et_poids);
        mCalc = findViewById(R.id.button);
        mResultat = findViewById(R.id.res_tv);
        getSupportActionBar().setTitle("Test");
        mCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(mTaille.getText().toString()) || TextUtils.isEmpty(mPoids.getText().toString())){
                    Toast.makeText(MainActivity.this, "veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }else {
                    double t =Double.valueOf(mTaille.getText().toString());
                    double p =Double.valueOf(mPoids.getText().toString());
                    double r = t / (p*p);
                    String res="";
                    if (r > 40) {
                        res = "obésité morbide ou massive";
                    } else if (r <= 40 && r > 35) {

                        res = "obésité sévère";

                    } else if (r <= 35 && r > 30) {

                        res = "obésité modérée";

                    } else if (r <= 30 && r > 25) {

                        res = "surpoids";
                    } else if (r <= 25 && r > 18.5) {

                        res = "corpulence normale";
                    } else if (r <= 18.5 && r > 16.5) {

                        res = "maigreur";
                    } else if (r <= 16.5) {

                        res = "famine";
                    }
                    mResultat.setText(res);

                }
            }
        });
    }
}