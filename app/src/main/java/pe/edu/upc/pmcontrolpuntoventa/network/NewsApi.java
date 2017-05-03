package pe.edu.upc.pmcontrolpuntoventa.network;

import java.util.List;

import pe.edu.upc.pmcontrolpuntoventa.models.Attendance;
import pe.edu.upc.pmcontrolpuntoventa.models.User;

/**
 * Created by ALEXANDER23 on 24/04/2017.
 */

public class NewsApi {
    public static String URL_LOGIN = "http://gest.saccaco.me/auth/login";
    public static String URL_LOGOUT = "http://gest.saccaco.me/auth/logout";
    public static String URL_ATTENDANCES_FOR_USER(String employee, String api_token){
        return "http://gest.saccaco.me/api/employees/"+employee+"/attendances?api_token="+api_token;
    }

    private User currentUser;
    private List<Attendance> currentAttendance;

    public User getUser() {
        return currentUser;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public void setAttendance(List<Attendance> attendance){
        this.currentAttendance = attendance;
    }

    public List<Attendance> getAttendance() {return this.currentAttendance;}

}
