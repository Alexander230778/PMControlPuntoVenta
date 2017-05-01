package pe.edu.upc.pmcontrolpuntoventa.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.pmcontrolpuntoventa.PuntoVentaApp;
import pe.edu.upc.pmcontrolpuntoventa.R;
import pe.edu.upc.pmcontrolpuntoventa.adapters.AttendancesAdapter;
import pe.edu.upc.pmcontrolpuntoventa.models.Attendance;
import pe.edu.upc.pmcontrolpuntoventa.models.Employee;
import pe.edu.upc.pmcontrolpuntoventa.models.User;
import pe.edu.upc.pmcontrolpuntoventa.network.NewsApi;

public class MapEncargado extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView attendancesRecyclerView;
    RecyclerView.LayoutManager attendancesLayoutManager;
    AttendancesAdapter attendancesAdapter;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_encargado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        attendancesLayoutManager = new LinearLayoutManager(this);
        attendancesAdapter = new AttendancesAdapter();
        employee = new Employee();

        attendancesLayoutManager = new LinearLayoutManager(this);
        attendancesAdapter = new AttendancesAdapter();
        attendancesAdapter.setEmployee(employee);
        attendancesRecyclerView = (RecyclerView) findViewById(R.id.attendancesRecyclerView);
        attendancesRecyclerView.setLayoutManager(attendancesLayoutManager);
        attendancesRecyclerView.setAdapter(attendancesAdapter);

        updateSources();

    }


    private void updateSources() {
        System.out.print(NewsApi.URL_ATTENDANCES_FOR_USER("5", getIntent().getExtras().getString("api_token")));
        AndroidNetworking.get(NewsApi.URL_ATTENDANCES_FOR_USER("5", getIntent().getExtras().getString("api_token")))
                .addQueryParameter("language", "en")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        if(response.length() < 0) {
                            Log.d("ERROR Employeee", "error");
                            return;
                        }

                        employee = Employee.build(response);
                        attendancesAdapter.setEmployee(employee);
                        attendancesAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error conextion", NewsApi.URL_ATTENDANCES_FOR_USER("5", getIntent().getExtras().getString("api_token")));
                        Log.d("ERROR Employeee", anError.getLocalizedMessage());
                    }
                });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map_encargado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
