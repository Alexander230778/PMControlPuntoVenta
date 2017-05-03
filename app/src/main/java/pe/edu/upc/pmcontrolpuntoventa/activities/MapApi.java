package pe.edu.upc.pmcontrolpuntoventa.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pe.edu.upc.pmcontrolpuntoventa.PuntoVentaApp;
import pe.edu.upc.pmcontrolpuntoventa.R;
import pe.edu.upc.pmcontrolpuntoventa.models.Attendance;

public class MapApi extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_api);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FloatingActionButton getLocationFab = (FloatingActionButton) findViewById(R.id.getLocationFab);
        getLocationFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),  "joder GPS", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        List<Attendance> attendances = PuntoVentaApp.getInstance().getCurrentAttendance();
        for(int i = 0; i < attendances.size(); i++) {
            String positionString[] = attendances.get(i).getLatIng().split(",");
            Double position[] = new Double[2];
            position[0] = Double.parseDouble(positionString[0]);
            position[1] = Double.parseDouble(positionString[1]);
            LatLng sydney = new LatLng(position[0], position[1]);
            mMap.addMarker(new MarkerOptions().position(sydney).title(attendances.get(i).getType()+ " : "+ attendances.get(i).getHour() ));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
        }

    }
}
