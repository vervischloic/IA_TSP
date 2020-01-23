import java.util.ArrayList;

public class GA {
    /* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);


        /* Crossover population
         * Loop over the new population's size and create individuals from
         * Current population (select parents, crossover them, add child to population
         * Question 1-a: To Complete
         */
        int i;
        for (i = 0; i < pop.populationSize(); i++) {
            newPopulation.saveTour(i, RWS(pop));
        }

        // Mutate the new population a bit to add some new genetic material
        /*
         *Question 1-b: Mutate new generation
         */
        java.util.Random gnrtor = new java.util.Random(System.currentTimeMillis());
        for (i = 0; i < pop.populationSize(); i++) {
            if (gnrtor.nextDouble() <= mutationRate) {
                Tour gonnaMutate = newPopulation.getTour(i);
                mutate(gonnaMutate);
                newPopulation.saveTour(i, gonnaMutate);
            }
        }
        //
        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring : Davi's order 
    public static Tour crossover(Tour parent1, Tour parent2) {
        // Create new child tour
        Tour child = new Tour();

        //---------------------------------------------------------------------------------------------------------
        // Crossover 2 parents using the Davi's Order
        /*
         *
         *
         *
         * Question 2 : To Complete:
         *
         *
         */
        //----------------------------------------------------------------------------------------------------------
        int LSBPoint, MSBPoint;
        boolean equal = false;

        int point1 = (int) (Math.random() * parent1.tourSize());
        int point2 = (int) (Math.random() * parent1.tourSize());

        if (point1 < point2) {
            LSBPoint = point1;
            MSBPoint = point2;
        } else if (point1 > point2) {
            LSBPoint = point2;
            MSBPoint = point1;
        } else {
            MSBPoint = LSBPoint = point1;
            equal = true;
        }

        if (!equal) {
            for (int i = 0; i < MSBPoint - LSBPoint; i++) {
                child.setCity(LSBPoint + i, parent1.getCity(LSBPoint + i));
            }
        } else {
            child.setCity(LSBPoint, parent1.getCity(LSBPoint));
        }

        int j = 0;
        for (int i = 0; i < parent1.tourSize(); i++) {
            if (!isCityAlreadyIn(parent2.getCity(i), child)) {
                if (child.getCity(j) == null) {
                    child.setCity(j, parent2.getCity(i));
                    j++;
                }else{
                    j=MSBPoint;
                    child.setCity(j, parent2.getCity(i));
                    j++;
                }
            }
        }
        System.out.println("--------------------------------------------------------");
        System.out.println(child);
        System.out.println("--------------------------------------------------------");
        System.out.println(parent1);
        System.out.println("--------------------------------------------------------");
        System.out.println(parent2);
        System.out.println("--------------------------------------------------------");
        return child;
    }

    private static boolean isCityAlreadyIn(City parentCity, Tour child) {
        for (int i = 0; i < child.tourSize(); i++) {
            if (child.getCity(i) == parentCity) {
                return true;
            }
        }
        return false;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Tour tour) {
        /*
         * Question 3: To complete
         */
        int i;
        City Buffer = new City();
        for (i = 0; i < tour.tourSize(); i++) {
            if (Math.random() < mutationRate) {
                if (i == 0) {
                    Buffer = tour.getCity(0);
                    tour.setCity(0, tour.getCity(tour.tourSize() - 1));
                    tour.setCity(tour.tourSize() - 1, Buffer);
                } else {
                    Buffer = tour.getCity(i);
                    tour.setCity(i, tour.getCity(i - 1));
                    tour.setCity(i - 1, Buffer);
                }
            }
        }
    }

    // Selects candidate tour for crossover using tournament method
    private static Tour tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }

    //Select candidate tour for crossover using a RWS 
    private static Tour RWS(Population pop) {
        ArrayList<Integer> fitness_share = new ArrayList<Integer>();
        Tour candidate = new Tour();
        int i;
        //Calculate sum of fitness values of all individuals in the population
        double sumFit = 0;
        for (i = 0; i < pop.populationSize(); i++) {
            sumFit = sumFit + pop.getTour(i).getFitness();
        }
        //Calculate and Save fitness share for all individuals in population
        double[] probaSet = new double[pop.populationSize()];
        for (i = 0; i < pop.populationSize(); i++) {
            probaSet[i] = pop.getTour(i).getFitness() / sumFit;
        }
        //Select individual based on its fitness share value and random generated value between 0 and 1
        java.util.Random generator = new java.util.Random(System.currentTimeMillis());
        double wheelSpin = generator.nextDouble();
        double wanderer = 0;
        int j = 0;
        while (wanderer < wheelSpin) {
            wanderer = wanderer + probaSet[j];
            j++;
        }
        candidate = pop.getTour(j - 1);
        //

        /*
         *
         * Question 5: To complete
         *
         *
         */
        return candidate;
    }

}
