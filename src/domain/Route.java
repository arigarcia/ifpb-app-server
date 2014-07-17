package domain;

public class Route {
    private Integer identity;
    private Coordinate[] coordinates;
    private Long time;
    
    public Route(Integer id) {
        identity = id;
    }

    public Integer getIdentity() {
        return identity;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
