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
        String filePath = "/Users/aysenazgelen/Desktop/map.txt";

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


        //GETS NUM OF ROUTES AND ASSIGNS IT TO AN INTEGER VARIABLE
        char[] routenumarr = linearr[2].toCharArray();
        int routecount = Character.getNumericValue(routenumarr[0]);


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


        //SEPARATES ROUTESARR AND ASSINGS CITY NAMES AND DURATION TO ROUTES OBJECT

        String[] routesSplit;
        Routes[] routes = new Routes[routesarr.length+1]; //if you want to access routes by routes[index] remove +1

        for (int i = 0; i < routesarr.length; i++){
                routesSplit = routesarr[i].split(" ");
                Routes temproute = new Routes();
                temproute.setCity1(routesSplit[0]);
                temproute.setCity2(routesSplit[1]);
                temproute.setDuration(Integer.parseInt(routesSplit[2]));
                routes[i+1]= temproute; //if you want to access routes by routes[index] remove +1
        }
        System.out.println(Arrays.toString(routes));

        System.out.println(routes[1]);
        //ROUTES ARE ACCESSIBLE THROUGH ROUTES ARRAY just write routes[routenum] index numbers are adjusted according to that
        System.out.println(routes[1].getCity1()); //accessing individual elements of routes


    }
}

