package pe.edu.upc.pmcontrolpuntoventa.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEXANDER23 on 24/04/2017.
 */

public class Shop {
    private Integer id;
    private String latIng;
    private String address;
    private String district;
    private String status;

    public Integer getId() {
        return id;
    }

    public Shop setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLatIng() {
        return latIng;
    }

    public Shop setLatIng(String latIng) {
        this.latIng = latIng;
        return  this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Shop setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Shop setStatus(String status) {
        this.status = status;
        return this;
    }

    public static Shop build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Shop shop = new Shop();
        try {
            shop.setId(jsonSource.getInt("id"))
                    .setLatIng(jsonSource.getString("latIng"))
                    .setAddress(jsonSource.getString("address"))
                    .setDistrict(jsonSource.getString("district"))
                    .setStatus(jsonSource.getString("status"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shop;
    }

    public static List<Shop> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        int length = jsonSources.length();
        List<Shop> shops = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            try {
                shops.add(Shop.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return shops;
    }
}
