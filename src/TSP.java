import java.util.ArrayList;
import java.util.Collections;

public class TSP {
    public static ArrayList<City> destinationCities = new ArrayList<City>();

    public static void main(String[] args) {
        // Create and add our cities
        /*
         * Question 4-a: To complete
         */
        City lille = new City(5063,306,"Lille");
        City paris = new City(4886,235,"Paris");
        City rennes = new City(4811,-168,"Rennes");
        City caen = new City(4918,-36,"Caen");
        City metz = new City(4912,617,"Metz");
        City poitiers = new City(4658,34,"Poitiers");
        City lyon = new City(4576,483,"Lyon");
        City bordeaux = new City(4484,-58,"Bordeaux");
        City toulouse = new City(4360,144,"Toulouse");
        City marseilles = new City(4133,-887,"Marseilles");

        destinationCities.add(lille);
        destinationCities.add(paris);
        destinationCities.add(rennes);
        destinationCities.add(caen);
        destinationCities.add(metz);
        destinationCities.add(poitiers);
        destinationCities.add(lyon);
        destinationCities.add(bordeaux);
        destinationCities.add(toulouse);
        destinationCities.add(marseilles);

        // Initialize population
        /*
         * Question 4-b: To complete
         */

        Population pop = new Population(50,true);

        // Evolve population for 100 generations
        /*
         * Question 4-c: To complete
         */

        // Print final results
        /*
         * Questions 4-d and 4-e: To Complete
         */

    }


}
