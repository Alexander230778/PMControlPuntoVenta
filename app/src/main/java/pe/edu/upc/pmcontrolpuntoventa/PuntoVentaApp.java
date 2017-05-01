package pe.edu.upc.pmcontrolpuntoventa;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

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

}
