package pe.edu.upc.pmcontrolpuntoventa.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEXANDER23 on 24/04/2017.
 */

public class Product {
    private Integer id;
    private String name;
    private String description;
    private String model;
    private String status;

    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Product setModel(String model) {
        this.model = model;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Product setStatus(String status) {
        this.status = status;
        return this;
    }

    public static Product build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Product product = new Product();
        try {
            product.setId(jsonSource.getInt("id"))
                    .setName(jsonSource.getString("name"))
                    .setDescription(jsonSource.getString("description"))
                    .setModel(jsonSource.getString("model"))
                    .setStatus(jsonSource.getString("status"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static List<Product> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        int length = jsonSources.length();
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            try {
                products.add(Product.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
}
