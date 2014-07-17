package domain;

public class Coordinate {
    private long latitude;
    private long longitude;
    
    public Coordinate(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public long getLatitude() {
        return latitude;
    }
    
    public long getLongitude() {
        return longitude;
    }
    
}
