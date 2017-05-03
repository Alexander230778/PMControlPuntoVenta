package pe.edu.upc.pmcontrolpuntoventa.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pe.edu.upc.pmcontrolpuntoventa.PuntoVentaApp;
import pe.edu.upc.pmcontrolpuntoventa.R;

/**
 * Created by ALEXANDER23 on 23/04/2017.
 */

public class Home extends AppCompatActivity {

    String api_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        if (savedInstanceState == null) {
            api_token = getIntent().getExtras().getString("api_token");
        } else {
            api_token = savedInstanceState.getString("api_token");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        api_token = getIntent().getExtras().getString("api_token");
        savedInstanceState.putString("api_token", api_token);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        api_token = savedInstanceState.getString("api_token");
    }

    public void navigatetoReport(View view){
        Intent reportIntent = new Intent(getApplicationContext(), Report.class);
        reportIntent.putExtra("api_token", api_token);
        reportIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(reportIntent);
    }

    public void navigatetoProducts(){
//        Intent homeIntent = new Intent(getApplicationContext(), Home.class);
//        homeIntent.putExtra("api_token", user.getApi_token());
//        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(homeIntent);
    }

}
