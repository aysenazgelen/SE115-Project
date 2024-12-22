import java.io.FileWriter;
import java.io.IOException;

public class WayFinder {
    private static int numofvalidroutes = 0;
    private static final int[] routeDurations = new int[100];
    private static final City[] visitedCities = new City[100];
    private static int visitedIndex = 0;

    // finds methods recursively
    private static void findAllRoutes(CountryMap[] routes, City currentCity, City endCity, int currentDuration, FileWriter writer) throws IOException {
        for (int i=0; i<routes.length; i++) {
            if (routes[i].getCity1() == currentCity) {

                boolean alreadyVisited = false; //ziyaret etmis miydik
                for (int j = 0; j < visitedIndex; j++) {
                    if (visitedCities[j] == routes[i].getCity2()) {
                        alreadyVisited = true;
                        break;
                    }
                } //eger ziyaret etmşssek ziyaret edildi yapioz

                if (!alreadyVisited) {
                    visitedCities[visitedIndex] = routes[i].getCity2();
                    visitedIndex++; //backtrack
                    findAllRoutes(routes, routes[i].getCity2(), endCity, currentDuration + routes[i].getDuration(), writer);
                    visitedIndex--;
                }
            }
        }

        //BITTIGINDE PRINTLE VE DURATIONU TUT
        if (currentCity == endCity) {

            //PRINT THE ROUTES
            System.out.print("Possible Route: ");
            for (int i = 0; i < visitedIndex-1; i++) {
                System.out.print(visitedCities[i].getCityname() + " -> ");
            }
            System.out.println(visitedCities[visitedIndex-1]);
            System.out.println("Duration: " + currentDuration);

            //PRINT THE ROUTES
            StringBuilder routeBuilder = new StringBuilder();
            routeBuilder.append("Possible Route: ");

            for (int i = 0; i < visitedIndex-1; i++) {
                routeBuilder.append(visitedCities[i].getCityname()).append(" -> ");
            }
            routeBuilder.append(visitedCities[visitedIndex - 1].getCityname());

            writer.write(routeBuilder.toString() );
            writer.write("  Duration: " + currentDuration + "\n\n");


            routeDurations[numofvalidroutes] = currentDuration;
            numofvalidroutes++;

        }
    }

    // Method to find the shortest route
    public static int findShortestRoute(CountryMap[] routes, City startCity, City endCity, String outputFilePath) {

        visitedCities[0] = startCity;
        visitedIndex = 1;

        try (FileWriter writer = new FileWriter(outputFilePath)) {
            findAllRoutes(routes, startCity, endCity, 0, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        if (numofvalidroutes == 0) {
            return -1; // NO ROUTES we will use this in the main function to determine the output
        }

        // FIND MIN DUR
        int minDuration = Integer.MAX_VALUE;
        for (int i = 0; i < numofvalidroutes; i++) {
            if (routeDurations[i] < minDuration) {
                minDuration = routeDurations[i];
            }
        }

        return minDuration;
    }

}
