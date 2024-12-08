public class Routes {
    private String city1;
    private String city2;
    private int duration;

    public Routes(String city1, String city2, int duration) {
        this.city1 = city1;
        this.city2 = city2;
        this.duration = duration;
    }

    public Routes() {
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toString() {
        return (city1 + " " +city2 + " "+ duration);
    }
}
