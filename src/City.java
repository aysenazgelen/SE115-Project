public class City {

    private String cityname;

    public City(String cityname) {
        this.cityname = cityname;
    }

    public City() {
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String toString() {
        return cityname;
    }

    public static City findCityByName(City[] cities, String cityName) {
        for (City city : cities) {
            if (city.getCityname().equals(cityName)) {
                return city;
            }
        }
        return null;
    }

}
