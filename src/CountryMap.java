public class CountryMap {
    private City city1;
    private City city2;
    private int duration;

    public CountryMap(City city1, City city2, int duration) {
        this.city1 = city1;
        this.city2 = city2;
        this.duration = duration;
    }

    public CountryMap() {
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toString() {
        return city1.getCityname() + " - " + city2.getCityname() + " (" + duration + ")";
    }
}
