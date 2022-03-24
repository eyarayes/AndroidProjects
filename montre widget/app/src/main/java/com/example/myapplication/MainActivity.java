package fr.koor.clocksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Clock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = (Clock) findViewById( R.id.clock );
    }

    @Override
    protected void onResume() {
        super.onResume();
        clock.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        clock.onPause();
    }
}