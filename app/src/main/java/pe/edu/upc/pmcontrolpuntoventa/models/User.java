package pe.edu.upc.pmcontrolpuntoventa.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEXANDER23 on 23/04/2017.
 */

public class User {
    private String name;
    private String password;
    private String api_token;
    private String status;
    private Integer employees_id;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getApi_token() {
        return api_token;
    }

    public User setApi_token(String api_token) {
        this.api_token = api_token;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public User setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getEmployees_id() {
        return employees_id;
    }

    public User setEmployees_id(Integer employees_id) {
        this.employees_id = employees_id;
        return this;
    }

    public static User build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        User user = new User();
        try {
            user.setName(jsonSource.getString("name"))
                    .setApi_token(jsonSource.getString("api_token"))
                    .setStatus(jsonSource.getString("status"))
                    .setEmployees_id(jsonSource.getInt("employees_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        int length = jsonSources.length();
        List<User> users = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            try {
                users.add(User.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
