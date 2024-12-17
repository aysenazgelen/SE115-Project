public class WayFinder {
    private CountryMap[] allroutes;
    private CountryMap route;
    private CountryMap startcity;
    private CountryMap endcity;

    public WayFinder() {
    }

    public CountryMap[] getAllroutes() {
        return allroutes;
    }

    public void setAllroutes(CountryMap[] allroutes) {
        this.allroutes = allroutes;
    }

    public CountryMap getRoute() {
        return route;
    }

    public void setRoute(CountryMap route) {
        this.route = route;
    }

    public CountryMap getStartcity() {
        return startcity;
    }

    public void setStartcity(CountryMap startcity) {
        this.startcity = startcity;
    }

    public CountryMap getEndcity() {
        return endcity;
    }

    public void setEndcity(CountryMap endcity) {
        this.endcity = endcity;
    }


}
