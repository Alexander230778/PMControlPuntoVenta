package pe.edu.upc.pmcontrolpuntoventa.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

import org.json.JSONObject;

import pe.edu.upc.pmcontrolpuntoventa.R;
import pe.edu.upc.pmcontrolpuntoventa.adapters.AttendancesAdapter;
import pe.edu.upc.pmcontrolpuntoventa.models.Employee;
import pe.edu.upc.pmcontrolpuntoventa.network.NewsApi;

public class Report extends AppCompatActivity {


    RecyclerView attendancesRecyclerView;
    RecyclerView.LayoutManager attendancesLayoutManager;
    AttendancesAdapter attendancesAdapter;
    Employee employee;
    RecyclerViewHeader headerAttendancesRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        attendancesLayoutManager = new LinearLayoutManager(this);
        attendancesAdapter = new AttendancesAdapter();
        employee = new Employee();

        attendancesLayoutManager = new LinearLayoutManager(this);
        attendancesAdapter = new AttendancesAdapter();
        attendancesAdapter.setEmployee(employee);
        attendancesRecyclerView = (RecyclerView) findViewById(R.id.attendancesRecyclerView);
        attendancesRecyclerView.setLayoutManager(attendancesLayoutManager);
        attendancesRecyclerView.setAdapter(attendancesAdapter);
        headerAttendancesRecyclerView = (RecyclerViewHeader) findViewById(R.id.headerAttendancesRecyclerView);
        headerAttendancesRecyclerView.attachTo(attendancesRecyclerView);
        updateSources();

    }


    private void updateSources() {

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

}
