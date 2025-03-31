package me.chchnikolaou.unipiplishopping.object;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Product {

    private String code;
    private String title;
    private List<String> description;
    private String releaseDate;
    private double cost;
    private List<Location> locations;
    private int imageId;

    public Product() {}

    public Product(String code, String title, List<String> description, String releaseDate, double cost, List<Location> locations, int imageId) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.cost = cost;
        this.locations = locations;
        this.imageId = imageId;
    }

    public Product(String code, String title, List<String> description, String releaseDate, List<Location> locations, int imageId) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.cost = 0.0;
        this.locations = locations;
        this.imageId = imageId;

    }

    public Product(String serialize) {
        String[] matrix = serialize.split("!@#");
        if(matrix==null || matrix.length < 6) return;
        this.code = matrix[0];
        this.title = matrix[1];
        this.description = new ArrayList<>();


        String[] desc = matrix[2].split("\\.");
        if(desc!=null && desc.length != 0) {
            description.addAll(Arrays.asList(desc));
        }

        this.releaseDate = matrix[3];
        try {
            this.cost = Double.parseDouble(matrix[4]);
            this.imageId = Integer.parseInt(matrix[5]);
        } catch(Exception ignored) {}
    }


    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDescriptionString() {
        StringBuilder str = new StringBuilder();
        for(String line : description) str.append(line).append(" ");
        return str.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s!@#%s!@#%s!@#%s!@#%s!@#%d", code, title, getDescriptionString(),
                releaseDate, String.valueOf(cost), imageId);
    }

}
