package pe.edu.upc.pmcontrolpuntoventa.network;

import java.util.List;

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

    private List<User> user;

    public User getUser() {
        return user.get(0);
    }

    public void setUser(User user) {
        this.user.add(0, user);
    }


}
