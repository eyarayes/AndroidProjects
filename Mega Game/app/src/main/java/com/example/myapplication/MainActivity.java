package fr.koor.megagame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber;
    private Button btnCompare;
    private TextView lblResult;
    private ProgressBar pgbScore;
    private TextView lblHistory;

    private int searchedValue;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber = (EditText) findViewById( R.id.txtNumber );
        btnCompare = (Button) findViewById( R.id.btnCompare );
        lblResult = (TextView) findViewById( R.id.lblResult );
        pgbScore = (ProgressBar) findViewById( R.id.pgbScore );
        lblHistory = (TextView) findViewById( R.id.lblHistory );

        btnCompare.setOnClickListener( btnCompareListener );

        init();

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double price = 1_000_000.01;
        Log.i( "DEBUG", formatter.format( price ) );

        DateFormat dataFormatter = DateFormat.getDateTimeInstance();
        Log.i( "DEBUG", dataFormatter.format( new Date() ) );
    }

    private void init() {
        score = 0;
        searchedValue = 1 + (int) (Math.random() * 100);
        Log.i( "DEBUG", "Searched value : " + searchedValue );

        txtNumber.setText( "" );
        pgbScore.setProgress( score );
        lblResult.setText( "" );
        lblHistory.setText( "" );

        txtNumber.requestFocus();
    }

    private void congratulations() {
        lblResult.setText( R.string.strCongratulations );

        AlertDialog retryAlert = new AlertDialog.Builder( this ).create();
        retryAlert.setTitle( R.string.app_name );
        retryAlert.setMessage( getString(R.string.strMessage, score ) );

        retryAlert.setButton( AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });

        retryAlert.setButton( AlertDialog.BUTTON_NEGATIVE, getString(R.string.strNo), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        retryAlert.show();
    }

    private View.OnClickListener btnCompareListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i( "DEBUG", "Button clicked" );

            String strNumber = txtNumber.getText().toString();
            if ( strNumber.equals( "" ) ) return;

            int enteredValue = Integer.parseInt( strNumber );
            lblHistory.append( strNumber + "\r\n" );
            pgbScore.incrementProgressBy( 1 );
            score++;

            if ( enteredValue == searchedValue ) {
                congratulations();
            } else if ( enteredValue < searchedValue ) {
                lblResult.setText( R.string.strGreater );
            } else {
                lblResult.setText( R.string.strLower );
            }

            txtNumber.setText( "" );
            txtNumber.requestFocus();

        }
    };

}