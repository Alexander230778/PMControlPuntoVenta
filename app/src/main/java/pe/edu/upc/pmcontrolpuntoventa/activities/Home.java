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
        //Displays Home Screen
//        if(api_token == null)
            api_token = PuntoVentaApp.getInstance().getCurrentUser().getApi_token();
//            api_token = getIntent().getExtras().getString("api_token");
        setContentView(R.layout.home);
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
