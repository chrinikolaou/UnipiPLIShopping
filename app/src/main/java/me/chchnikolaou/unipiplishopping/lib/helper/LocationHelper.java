package me.chchnikolaou.unipiplishopping.lib.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.chchnikolaou.unipiplishopping.object.Location;

public class LocationHelper {

    private static final Random random = new Random();


    private static long generateLatitude() {
        return (long) (-90 + (90 - (-90)) * random.nextDouble());
    }

    private static long generateLongitude() {
        return (long) (-180 + (180 - (-180)) * random.nextDouble());
    }


    public static Location generate() {
        return new Location(generateLongitude(), generateLatitude());
    }

    public static List<Location> generate(int number) {
        final List<Location> list = new ArrayList<>();
        for(int i = 0; i < number; i++) list.add(generate());

        return list;
    }

    public static Location convert(android.location.Location location) {
        return new Location(location.getLongitude(), location.getLatitude());
    }



}
