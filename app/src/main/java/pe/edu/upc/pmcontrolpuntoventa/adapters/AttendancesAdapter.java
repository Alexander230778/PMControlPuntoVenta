package pe.edu.upc.pmcontrolpuntoventa.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import pe.edu.upc.pmcontrolpuntoventa.PuntoVentaApp;
import pe.edu.upc.pmcontrolpuntoventa.R;
import pe.edu.upc.pmcontrolpuntoventa.activities.MapApi;
import pe.edu.upc.pmcontrolpuntoventa.models.Attendance;
import pe.edu.upc.pmcontrolpuntoventa.models.Employee;


/**
 * Created by proyecto on 10/04/2017.
 */

public class AttendancesAdapter extends RecyclerView.Adapter<AttendancesAdapter.ViewHolder> {
    private Employee employees;
    String currentKey;
    @Override
    public AttendancesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_attendances,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AttendancesAdapter.ViewHolder holder, final int position) {
        if(employees.getAttendances().size() == 0) return;
        ArrayList<String> listKeys = new ArrayList<String>();
        for ( String key : employees.getAttendances().keySet() ) {
            listKeys.add(key);
        }
        currentKey = listKeys.get(position);
        holder.dateDayTextView.setText(currentKey);
        List<Attendance> listAttendance = employees.getAttendances().get(currentKey);

        for(int i = 0; i < listAttendance.size(); i++) {
            Attendance attendance = listAttendance.get(i);
            switch (attendance.getType()){
                case "checkin":
                    holder.hourCheckintTextView.setText(attendance.getHour());
                    break;
                case "checkout":
                    holder.hourCheckoutTextView.setText(attendance.getHour());
                    break;
            }
        }

        holder.attendancesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listKeys = new ArrayList<String>();
                for ( String key : employees.getAttendances().keySet() ) {
                    listKeys.add(key);
                }
                currentKey = listKeys.get(position);
                PuntoVentaApp
                        .getInstance()
                        .setCurrentAttendance(employees.getAttendances().get(currentKey));
                v.getContext().startActivity(
                        new Intent(
                            v.getContext(),
                            MapApi.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public Map<String, List<Attendance>> getAttendances() {
        return employees.getAttendances();
    }

    public void setEmployee(Employee employee){
        employees = employee;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView attendancesCardView;
        TextView dateDayTextView;
        TextView hourCheckintTextView;
        TextView hourCheckoutTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            attendancesCardView = (CardView) itemView.findViewById(R.id.attendancesCardView);
            dateDayTextView = (TextView) itemView.findViewById(R.id.dateDayTextView);
            hourCheckintTextView = (TextView) itemView.findViewById(R.id.hourCheckintTextView);
            hourCheckoutTextView = (TextView) itemView.findViewById(R.id.hourCheckoutTextView);


        }
    }
}
