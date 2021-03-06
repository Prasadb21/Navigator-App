package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isServicesOk())
        {
            init();

        }
    }

    private void init()
    {
        Button button = (Button) findViewById(R.id.btnmap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOk() {
        Log.d(TAG , "isServicesOk : Checking google service version") ;
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(available == ConnectionResult.SUCCESS)
        {
            Log.d(TAG ,"isServiceOk : Google play Service is Working") ;
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG , "isServicesOk : an error occured but we can fix it") ;
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this , available, ERROR_DIALOG_REQUEST );
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "You can't make map requests ", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

}