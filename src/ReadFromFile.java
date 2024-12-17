import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

public class ReadFromFile {



    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in); //userdan path almak icin scanner yarattik

        System.out.println("please enter the file directory: ");
        //String filePath = sc.nextLine(); //userdan path istedik

        //TEST İÇİN
        String filePath = "/Users/aysenazgelen/Desktop/map2.txt";

        //READS THE FILE AND ASSIGNS EACH LINE TO AN ELEMENT OF A STRING ARRAY
        String[] linearr = Files.readAllLines(Paths.get(filePath)).toArray(new String[0]);


        //GETS THE 1ST LINE AKA NUM OF CITIES
        char[] line1arr = linearr[0].toCharArray();
        int cityCount = Character.getNumericValue(line1arr[0]);

        //GETS THE 2ND LINE AKA CITY NAMES
        char[] line2arr = linearr[1].toCharArray();

        //CONVERTS FROM CHAR ARRAY TO STRING ARRAY
        String[] cityNames = new String[line2arr.length];
        for(int i=0; i<line2arr.length; i++){
            cityNames[i]=Character.toString(line2arr[i]);
        }

        //REMOVES SPACES
        String[] cityNamesArray = new String[cityCount];
        int k = 0;
        for(int i = 0 ; i<cityNames.length; i++ ){
            if (cityNames[i].equals(" ")){
                continue;
            } else {
                cityNamesArray[k] = cityNames[i];
                k++;
            }
        }

        System.out.println(Arrays.toString(linearr));

        //GETS NUM OF ROUTES AND ASSIGNS IT TO AN INTEGER VARIABLE
        String routec = linearr[2].trim();
        System.out.println(routec);
        int routecount = Integer.parseInt(routec);



        //GETS ROUTES
        String[] routesarr = new String[routecount];
        for (int i = 0; i<routecount; i++){
            //routesarr[i] = linearray.get(i+3);
            routesarr[i] = linearr[i+3];
        }

        String wantedroute = linearr[linearr.length-1];


        //print out info
        System.out.println("num of cities is: " + cityCount);
        System.out.println("city names are: "+ Arrays.toString(cityNamesArray));
        System.out.println("there are " + routecount + " routes");
        System.out.println("routes are: "+ Arrays.toString(routesarr));
        System.out.println("wanted route is between: " + wantedroute);

        City[] cities = new City[cityCount];
        for (int i = 0; i < cityCount; i++) {
            cities[i] = new City();
            cities[i].setCityname(cityNamesArray[i]);
        }


        //SEPARATES ROUTESARR AND ASSINGS CITY NAMES AND DURATION TO ROUTES OBJECT


        CountryMap[] routes = new CountryMap[routesarr.length]; //if you want to access routes by routes[index] remove +1
        for (int i = 0; i < routesarr.length; i++) {
            String[] routeSplit = routesarr[i].split(" ");
            String c1Name = routeSplit[0];
            String c2Name = routeSplit[1];
            int duration = Integer.parseInt(routeSplit[2]);
            City c1Obj = City.findCityByName(cities, c1Name);
            City c2Obj = City.findCityByName(cities, c2Name);
            routes[i] = new CountryMap(c1Obj, c2Obj, duration);
        }

        System.out.println(Arrays.toString(routes));

        System.out.println(routes[1]);
        //ROUTES ARE ACCESSIBLE THROUGH ROUTES ARRAY just write routes[routenum] index numbers are adjusted according to that
        System.out.println(routes[1].getCity1()); //accessing individual elements of routes

        CountryMap[] reverseroutes = new CountryMap[routes.length];
        for (int i = 0; i < routesarr.length; i++) {
            City tempc1 = routes[i].getCity2();
            City tempc2 = routes[i].getCity1();
            int revduration = routes[i].getDuration();
            reverseroutes[i]= new CountryMap(tempc1,tempc2,revduration);
        }

        System.out.println(Arrays.toString(reverseroutes));

        CountryMap[] allroutes = new CountryMap[routes.length*2];
        for (int i = 0; i < routes.length; i++) {
            allroutes[i]=routes[i];
        }
        for (int i = 0; i < reverseroutes.length ; i++) {
            allroutes[routes.length + i] = reverseroutes[i];
        }

        System.out.println(Arrays.toString(allroutes));

        String[] wantedroutearr = wantedroute.split(" ");

        City cityfrom = City.findCityByName(cities, wantedroutearr[0]);
        City cityto = City.findCityByName(cities, wantedroutearr[1]);

        System.out.println("from: " + cityfrom + " to: " + cityto);

        //wayfinder function

        City startcity = cityfrom;
        City endcity = new City("null");

        int[] routetimecalc = new int[allroutes.length];
        int routetime = 0;

        while (!(cityto.getCityname().equals(endcity))){
            for (int i=0; i< allroutes.length; i++){
                if(allroutes[i].getCity1()==startcity){

                    routetimecalc[i]=routetimecalc[i]+allroutes[i].getDuration();
                    startcity = allroutes[i].getCity2();


                }
            }
        }





    }


}

