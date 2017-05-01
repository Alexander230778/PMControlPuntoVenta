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
    private Date date;
    private String latIng;
    private Integer employees_id;

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

    public Date getDate() {
        return date;
    }

    public Attendance setDate(Date date) {
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

//    public static Source build(JSONObject jsonSource) {
//        if(jsonSource == null) return null;
//        Source source = new Source();
//        try {
//            Map<String, String> urlsToLogos = new HashMap<>();
//            urlsToLogos.put("small",
//                    jsonSource.getJSONObject("urlsToLogos")
//                            .getString("small"));
//            urlsToLogos.put("medium",
//                    jsonSource.getJSONObject("urlsToLogos")
//                            .getString("medium"));
//            urlsToLogos.put("large",
//                    jsonSource.getJSONObject("urlsToLogos")
//                            .getString("large"));
//            int length = jsonSource.getJSONArray("sortBysAvailable").length();
//            List<String> sortBysAvailable = new ArrayList<>();
//            for(int i = 0; i < length; i++) {
//                sortBysAvailable.add(
//                        jsonSource.getJSONArray("sortBysAvailable")
//                                .getString(i));
//            }
//            source.setId(jsonSource.getString("id"))
//                    .setName(jsonSource.getString("name"))
//                    .setDescription(jsonSource.getString("description"))
//                    .setUrl(jsonSource.getString("url"))
//                    .setCategory(jsonSource.getString("category"))
//                    .setLanguage(jsonSource.getString("language"))
//                    .setCountry(jsonSource.getString("country"))
//                    .setUrlsToLogos(urlsToLogos)
//                    .setSortBysAvailable(sortBysAvailable);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return source;
//    }

    public static Attendance build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Attendance attendance = new Attendance();

        try {
//            Map<String, JSONArray> attendances = new HashMap<>();

//            Iterator<?> keys = jsonSource.keys();

//            while( keys.hasNext() ) {
//                String key = (String)keys.next();
//                attendances.put(key,
//                        jsonSource.getJSONObject("attendance")
//                                .getJSONArray(key));
//            }


            attendance.setId(jsonSource.getInt("id"))
                    .setType(jsonSource.getString("type"))
                    .setDate(Utility.convertDate(jsonSource.getString("date")))
                    .setLatIng(jsonSource.getString("latlng"))
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
