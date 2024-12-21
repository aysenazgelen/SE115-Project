public class WayFinder {
    private static int numofvalidroutes = 0;
    private static final int[] routeDurations = new int[100];
    private static final City[] visitedCities = new City[100];
    private static int visitedIndex = 0; // Track the current index in visitedCities

    // Helper method to find all possible routes recursively
    private static void findAllRoutes(CountryMap[] routes, City currentCity, City endCity, int currentDuration) {
        for (int i=0; i<routes.length; i++) {
            if (routes[i].getCity1() == currentCity) {
                
                boolean isalreadyVisited = false;
                for (int j = 0; j < visitedIndex; j++) {
                    if (visitedCities[j] == routes[i].getCity2()) {
                        isalreadyVisited = true;
                        break;
                    }
                }

                if (!isalreadyVisited) {
                    // Mark the current city as visited
                    visitedCities[visitedIndex] = routes[i].getCity2();
                    visitedIndex++;  // Increment visitedIndex before the recursive call
                    // Recur for the next city
                    findAllRoutes(routes, routes[i].getCity2(), endCity, currentDuration + routes[i].getDuration());
                    // Backtrack: Unmark the city after the recursion
                    visitedIndex--;  // Decrement visitedIndex after backtracking
                }
            }
        }

        //BITTIGINDE PRINTLE VE DURATIONU TUT
        if (currentCity == endCity) {
            //PRINT THE ROUTES
            System.out.print("Route: ");
            int j = 0;
            for (int i = 0; i < visitedIndex-1; i++) {
                System.out.print(visitedCities[i].getCityname() + " -> ");
                j++;
            }
            System.out.println(visitedCities[j]);
            System.out.println("Duration: " + currentDuration);
            // Store the route's duration
            routeDurations[numofvalidroutes] = currentDuration;
            numofvalidroutes++;

        }
    }

    // Method to find the shortest route
    public static int findShortestRoute(CountryMap[] routes, City startCity, City endCity) {
        visitedCities[0] = startCity; // Start with the startCity
        visitedIndex = 1; // Start at index 1, as the startCity is already added

        findAllRoutes(routes, startCity, endCity, 0);

        if (numofvalidroutes == 0) {
            return -1; // No valid routes found
        }

        int minDuration = Integer.MAX_VALUE;
        for (int i = 0; i < numofvalidroutes; i++) {
            minDuration = Math.min(minDuration, routeDurations[i]);
        }

        return minDuration;
    }

}
