package pe.edu.upc.pmcontrolpuntoventa.models;

import com.orm.SugarRecord;

/**
 * Created by ALEXANDER23 on 03/05/2017.
 */

public class CurrentUser extends SugarRecord {
    private String name;
    private String password;
    private String api_token;
    private String status;
    private Integer employees_id;

    public CurrentUser(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmployees_id() {
        return employees_id;
    }

    public void setEmployees_id(Integer employees_id) {
        this.employees_id = employees_id;
    }
}
