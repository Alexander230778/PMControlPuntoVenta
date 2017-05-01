package pe.edu.upc.pmcontrolpuntoventa.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pe.edu.upc.pmcontrolpuntoventa.utilities.Utility;

/**
 * Created by ALEXANDER23 on 24/04/2017.
 */

public class Attendance {
    private Integer id;
    private String type;
    private String date;
    private String latIng;
    private Integer employees_id;
    private String hour;

    public Integer getId() {
        return id;
    }

    public Attendance setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Attendance setType(String type) {
        this.type = type;
        return  this;
    }

    public String getDate() {
        return date;
    }

    public Attendance setDate(String date) {
        this.date = date;
        return this;
    }

    public String getLatIng() {
        return latIng;
    }

    public Attendance setLatIng(String latIng) {
        this.latIng = latIng;
        return this;
    }

    public Integer getEmployees_id() {
        return employees_id;
    }

    public Attendance setEmployees_id(Integer employees_id) {
        this.employees_id = employees_id;
        return this;
    }

    public String getHour() {
        return hour;
    }

    public Attendance setHour(String hour) {
        this.hour = hour;
        return this;
    }

    public static Attendance build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Attendance attendance = new Attendance();

        try {

            attendance.setId(jsonSource.getInt("id"))
                    .setType(jsonSource.getString("type"))
                    .setDate(jsonSource.getString("date"))
                    .setLatIng(jsonSource.getString("latlng"))
                    .setHour(jsonSource.getString("time"))
                    .setEmployees_id(jsonSource.getInt("employees_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    public static List<Attendance> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        int length = jsonSources.length();
        List<Attendance> attendances = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            try {
                attendances.add(Attendance.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return attendances;
    }
}
