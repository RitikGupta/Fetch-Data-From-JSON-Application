package androidclass.fetchdatafromjson;

/**
 * Created by hp on 6/5/2020.
 */

public class ListItem {
    private String countryName;
    private double lat;
    private double longitude;
    private String region;

    public ListItem(String countryName, double lat, double longitude, String region) {
        this.countryName = countryName;
        this.lat = lat;
        this.longitude = longitude;
        this.region = region;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getLat() {
        return lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getRegion() {
        return region;
    }
}
