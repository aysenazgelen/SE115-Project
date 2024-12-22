import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

public class ReadFromFile {

    public static void main(String[] args) throws IOException {

        //getting filepath from the the user THROUGH COMMAND LINE
        String filePath = null;
        try {
            if (args.length < 1) {
                System.err.println(">> Whoopsie :( It looks like there is an issue with your filepath. Please provide a valid file path and try again.");
                System.exit(1);  // Exit with a non-zero code to indicate failure
            }
            filePath = args[0];
        } catch (Exception e) {
            System.err.println("Error occurred while processing the file path: " + e.getMessage());
            throw new IllegalArgumentException("Invalid file path provided.");
        }

        ///HOW TO CALL:
        //cd IdeaProjects
        //cd SE115-Project
        //cd src
        //javac ReadFromFile.java
        //java ReadFromFile /Users/aysenazgelen/Desktop/map.txt


        //FOR TESTING PURPOSES
        //String filePath = "/Users/aysenazgelen/Desktop/map.txt";

        //READS THE FILE AND ASSIGNS EACH LINE TO AN ELEMENT OF A STRING ARRAY
        String[] linearr = Files.readAllLines(Paths.get(filePath)).toArray(new String[0]);

        //GETS THE 1ST LINE AKA NUM OF CITIES // trim vs yap
        char[] line1arr = linearr[0].toCharArray();
        int cityCount = Integer.parseInt(linearr[0].trim());

        //GETS THE 2ND LINE AKA CITY NAMES
        char[] line2arr = linearr[1].toCharArray();

        //CONVERTS FROM CHAR ARRAY TO STRING ARRAY
        String[] cityNames = new String[line2arr.length];
        for(int i=0; i<line2arr.length; i++){
            cityNames[i]=Character.toString(line2arr[i]);
        }

        //REMOVES SPACES
        String[] cityNamesArray = new String[cityCount];
        int j = 0; //acts as a counter
        for (int i = 0; i < cityNames.length; i++) {
            if (cityNames[i].equals(" ")) {
                continue;
            } else {
                if (j >= cityCount) {
                    System.err.println(">> Whoops! You have entered more city names than the city count.");
                    System.exit(1);
                }
                cityNamesArray[j] = cityNames[i];
                j++;
            }
        }

        if (j != cityCount) {
            System.err.println(">> Whoops! There aren't enough cities compared to city count");
            System.exit(1);
        }


        //GETS NUM OF ROUTES AND ASSIGNS IT TO AN INTEGER VARIABLE
        String routec = linearr[2].trim();
        int routecount = Integer.parseInt(routec);
        if (linearr.length < 3 + routecount + 1) {
            System.err.println(">> Whoops! Route count does not match the number of given routes.");
            System.exit(1);
        }



        //GETS ROUTES
        String[] routesarr = new String[routecount];
        for (int i = 0; i<routecount; i++){
            routesarr[i] = linearr[i+3];
        }

        String wantedroute = linearr[linearr.length-1];


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

        //ROUTE BETWEEN WHAT CITIES IS STORED IN WANTEDROUTEARR
        String[] wantedroutearr = wantedroute.split(" ");

        //AND THEN THOSE CITIES ARE ASSIGNED TO VARIABLES STARTCITY AND ENDCITY
        City startCity = City.findCityByName(cities, wantedroutearr[0]);
        City endCity = City.findCityByName(cities, wantedroutearr[1]);

        if (startCity == null) {
            System.err.println(">> Start city \"" + wantedroutearr[0] + "\" does not exist.");
            System.exit(1);
        }
        if (endCity == null) {
            System.err.println(">> End city \"" + wantedroutearr[1] + "\" does not exist.");
            System.exit(1);
        }

        //print out info
        System.out.println(">> Yippie! Your file was read succesfully. Here is the info you have given in the file.");
        System.out.println(">> There are " + cityCount + " cities.");
        System.out.println(">> The name of these cities are: "+ Arrays.toString(cityNamesArray));
        System.out.println(">> There are " + routecount + " routes.");
        System.out.println(">> These routes are: "+ Arrays.toString(routesarr));
        System.out.println(">> You are looking for the shortest route from: " + startCity + " to: " + endCity);


        //calling the wayfinder function
        String outputFilePath = "output.txt";
        int minDur = WayFinder.findShortestRoute(routes, startCity, endCity, outputFilePath);
        FileWriter writer = new FileWriter(outputFilePath, true);

        if (minDur == -1) {
            System.out.println("There are no routes between " + startCity + " and " + endCity);
            writer.write("There are no routes between " + startCity + " and " + endCity);
        } else {
            System.out.print("The shortest duration from " + startCity + " to " + endCity + " is: " + minDur);
            writer.write("The shortest duration from " + startCity + " to " + endCity + " is: " + minDur);
        }

        writer.close();





    }

}

