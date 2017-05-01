package pe.edu.upc.pmcontrolpuntoventa.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by ALEXANDER23 on 23/04/2017.
 */

public class Employee {
    private Integer id;
    private String name;
    private String last_name;
    private String doc_type;
    private String doc_number;
    private String type;
    private Integer shops_id;
    private Map<String, List<Attendance>> attendances;

    public Integer getId() {
        return id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public Employee setLast_name(String last_name) {
        this.last_name = last_name;
        return  this;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public Employee setDoc_type(String doc_type) {
        this.doc_type = doc_type;
        return this;
    }

    public String getDoc_number() {
        return doc_number;
    }

    public Employee setDoc_number(String doc_number) {
        this.doc_number = doc_number;
        return this;
    }

    public String getType() {
        return type;
    }

    public Employee setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getShops_id() {
        return shops_id;
    }

    public Employee setShops_id(Integer shops_id) {
        this.shops_id = shops_id;
        return this;
    }

    public Map<String, List<Attendance>> getAttendances() {
        return attendances;
    }

    public Employee setAttendances(JSONObject attendances) {
            Iterator<?> keys = attendances.keys();

            while( keys.hasNext() ) {
                String key = (String)keys.next();
                try{
                    JSONArray attendancesArray = attendances.getJSONArray(key);
                    if(attendancesArray == null) return null;
                    int length = attendancesArray.length();
                    List<Attendance> listAttendance = new ArrayList<>();
                    for(int i = 0; i < length; i++) {
                        try {
                            listAttendance.add(Attendance.build(attendancesArray.getJSONObject(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    this.attendances.put(key, listAttendance);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        return this;
    }

    public static Employee build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Employee employee = new Employee();
        try {
            employee.setId(jsonSource.getInt("id"))
                    .setName(jsonSource.getString("name"))
                    .setLast_name(jsonSource.getString("last_name"))
                    .setDoc_type(jsonSource.getString("doc_type"))
                    .setDoc_number(jsonSource.getString("doc_number"))
                    .setType(jsonSource.getString("type"))
                    .setShops_id(jsonSource.getInt("shops_id"));

            if (jsonSource.getJSONObject("attendance") != null) {
                employee.setAttendances(jsonSource.getJSONObject("attendance"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        int length = jsonSources.length();
        List<Employee> employees = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            try {
                employees.add(Employee.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }
}
