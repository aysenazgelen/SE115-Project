import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class ReadFromFile {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in); //userdan path almak icin scanner yarattik

        System.out.println("please enter the file directory: ");
        //String filePath = sc.nextLine(); //userdan path istedik

        //TEST İÇİN
        String filePath = "/Users/aysenazgelen/Desktop/map.txt";
        // test pathi: /Users/aysenazgelen/Desktop/map.txt

        List<String> linelist = Files.readAllLines(Paths.get(filePath));
        ArrayList<String> linearray = new ArrayList<>(linelist); //bunu arraye çevirebilir misin bak

        //GETS THE 1ST LINE AKA NUM OF CITIES
        char[] line1arr = linearray.get(0).toCharArray();
        int cityCount = Character.getNumericValue(line1arr[0]);
        System.out.println("num of cities is: " + cityCount);

        //GETS THE 2ND LINE AKA CITY NAMES
        char[] line2arr = linearray.get(1).toCharArray();

        //CONVERTS FROM CHAR ARRAY TO STRING ARRAY
        String[] cityNames = new String[line2arr.length];
        for(int i=0; i<line2arr.length; i++){
            cityNames[i]=Character.toString(line2arr[i]);
        }

        //CONVERTS FROM STRING ARRAY TO ARRAYLIST
        ArrayList<String> cityNamesList = new ArrayList<>();
        for (String city : cityNames) {
            cityNamesList.add(city);
        }

        //REMOVES SPACES
        for(int i = 0; i<cityNamesList.size();i++){
            if (cityNamesList.get(i).equals(" ")){
                cityNamesList.remove(i);
            }
        }

        System.out.println("the cities are: " + cityNamesList);

        //GETS NUM OF ROUTES AND ASSIGNS IT TO AN INTEGER VARIABLE
        char[] routenumarr = linearray.get(2).toCharArray();
        int routecount = Character.getNumericValue(routenumarr[0]);

        System.out.println("there are " + routecount + " routes");

        //GETS ROUTES
        String[] routesarr = new String[routecount];
        for (int i = 0; i<routecount; i++){
            routesarr[i] = linearray.get(i+3);
            System.out.println((i+1)+"st route: "+routesarr[i]);
        }

        String wantedroute = linearray.get(linearray.size()-1);
        System.out.println("wanted route is between: " + wantedroute);




    }
}

