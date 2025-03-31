package me.chchnikolaou.unipiplishopping.object;

public class Location {

    private double lon;
    private double lan;

    public Location() {}

    public Location(double lon, double lan) {
        this.lon = lon;
        this.lan = lan;
    }


    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    public double distance(Location location) {
        final int EARTH_RADIUS_KM = 6371; // Radius of the Earth in kilometers

        // Convert longitude and latitude from degrees to radians
        double lat1 = Math.toRadians(this.lan);
        double lon1 = Math.toRadians(this.lon);
        double lat2 = Math.toRadians(location.getLan());
        double lon2 = Math.toRadians(location.getLon());

        // Compute differences
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Compute distance
        return EARTH_RADIUS_KM * c;
    }

}
