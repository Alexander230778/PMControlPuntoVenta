package pe.edu.upc.pmcontrolpuntoventa;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

import pe.edu.upc.pmcontrolpuntoventa.models.Attendance;
import pe.edu.upc.pmcontrolpuntoventa.models.User;
import pe.edu.upc.pmcontrolpuntoventa.network.NewsApi;

/**
 * Created by skynet on 30/04/17.
 */

public class PuntoVentaApp extends Application {

    private static PuntoVentaApp instance;
    NewsApi newsApi = new NewsApi();

    public PuntoVentaApp(){
        super();
        instance = this;
    }

    public static PuntoVentaApp getInstance(){ return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }

    public void setCurrentUser(User user) {
        newsApi.setUser(user);
    }

    public User getCurrentUser() {
        return newsApi.getUser();
    }


    public List<Attendance> getCurrentAttendance(){
        return newsApi.getAttendance();
    }

    public void setCurrentAttendance(List<Attendance> attendance){
        newsApi.setAttendance(attendance);
    }

}
