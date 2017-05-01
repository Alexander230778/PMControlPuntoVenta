package pe.edu.upc.pmcontrolpuntoventa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import pe.edu.upc.pmcontrolpuntoventa.activities.Home;
import pe.edu.upc.pmcontrolpuntoventa.activities.MapApi;
import pe.edu.upc.pmcontrolpuntoventa.activities.MapEncargado;
import pe.edu.upc.pmcontrolpuntoventa.models.User;
import pe.edu.upc.pmcontrolpuntoventa.network.NewsApi;
import pe.edu.upc.pmcontrolpuntoventa.utilities.Utility;

public class LoginActivity extends AppCompatActivity {

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Email Edit View Object
    EditText nameET;
    // Password Edit View Object
    EditText pwdET;

    private static User user;

    private static String TAG="Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Find Error Msg Text View control by ID
        errorMsg = (TextView)findViewById(R.id.loginError);
        // Find Email Edit View control by ID
        nameET = (EditText)findViewById(R.id.loginName);
        // Find Password Edit View control by ID
        pwdET = (EditText)findViewById(R.id.loginPassword);
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * Method gets triggered when Login button is clicked
     *
     * @param view
     */
    public void loginUser(View view){
        // Get Email Edit View Value
        String name = nameET.getText().toString();
        // Get Password Edit View Value
        String password = pwdET.getText().toString();

        // When Name Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(name) && Utility.isNotNull(password)){
            // Put Http parameter username with value of Email Edit View control
            // Invoke RESTful Web Service with Http parameters
            invokeWS(name, password);
        } else{
            Toast.makeText(getApplicationContext(), "No puede haber campos vacios", Toast.LENGTH_LONG).show();
        }
    }

    public void invokeWS(final String name, final String password){

        prgDialog.show();

        JSONObject obj = new JSONObject();
        try {
            obj.put("name", name);
            obj.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, NewsApi.URL_LOGIN, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("ACTIVE")) {
                                prgDialog.hide();
                                user = User.build(response);
                                navigatetoHome();
                            }else {
                                prgDialog.hide();
                                Toast.makeText(getApplicationContext(), "Error de usuario y/o contraseña", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ErrorListener Login", Toast.LENGTH_LONG).show();
                        prgDialog.hide();
                    }
                });
        queue.add(postRequest);

    }

    /**
     * Method which navigates from Login Activity to Home Activity
     */
    public void navigatetoHome(){
        Intent homeIntent = new Intent(getApplicationContext(), MapEncargado.class);
        homeIntent.putExtra("api_token", user.getApi_token());
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
