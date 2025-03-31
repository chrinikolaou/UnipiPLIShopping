package me.chchnikolaou.unipiplishopping.lib.builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.chchnikolaou.unipiplishopping.object.Location;
import me.chchnikolaou.unipiplishopping.object.Product;

public class ProductBuilder {

    private final String code;
    private String title;
    private List<String> description;
    private String releaseDate;
    private double cost;
    private List<Location> locations;
    private int imageId;

    public ProductBuilder() {
        this.code = CodeBuilder.generate(7);
        this.description = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public ProductBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder setDescription(List<String> description) {
        this.description = description;
        return this;
    }

    public ProductBuilder addDescriptionLine(String line) {
        this.description.add(line);
        return this;
    }

    public ProductBuilder removeDescriptionLine(int line) {
        if(description.size()-1> line) return this;
        description.remove(line);
        return this;
    }

    public ProductBuilder setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

//    public ProductBuilder setReleaseDate(String date) {
//        this.releaseDate = date;
//
//        //        try {
////            this.releaseDate = LocalDate.parse(date, new DateTimeFormatterBuilder()
////                    .appendPattern("uuuu-MM-dd").toFormatter().withResolverStyle(ResolverStyle.SMART));
////        } catch(Exception ignored) {}
//        return this;
//    }


    public ProductBuilder setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder setLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }

    public ProductBuilder addLocation(Location... location) {
        locations.addAll(Arrays.asList(location));
        return this;
    }

    public ProductBuilder removeLocation(Location... location) {
        Arrays.stream(location).forEach(locations::remove);
        return this;

    }

    public ProductBuilder setImageId(int imageId) {
        this.imageId = imageId;
        return this;
    }

    public Product build() {
        return new Product(
                code,
                title,
                description,
                releaseDate,
                cost,
                locations,
                imageId
        );
    }

}
