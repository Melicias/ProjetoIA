package productDistribution;

import algorithms.IntVectorIndividual;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;


public class ProductDistributionIndividual extends IntVectorIndividual<ProductDistributionProblem, ProductDistributionIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    private double totalDistance;
    private double longestDistance;
    private ArrayList<Integer> numberOfBoxes;


    public ProductDistributionIndividual(ProductDistributionProblem problem, int size) {
        super(problem, size);
        //TODO
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ProductDistributionIndividual(ProductDistributionIndividual original) {
        super(original);
        this.numberOfBoxes = original.numberOfBoxes;
        this.totalDistance = original.totalDistance;
        this.longestDistance = original.longestDistance;
        this.numberOfBoxes = original.numberOfBoxes;
        //TODO
    }


    public ArrayList<ArrayList<Integer>> getOrdersForTruck() {
        //TODO
        ArrayList<ArrayList<Integer>> allTrucks = new ArrayList<>();
        int trucksBeginAt = problem.getItems().size();
        ArrayList<Integer> oneTruck = new ArrayList<>();
        for(int i = 0; i < genome.length; i++){
            if(trucksBeginAt < genome[i]){
                allTrucks.add(oneTruck);
                oneTruck = new ArrayList<>();
            }else{
                oneTruck.add(genome[i]);
            }
        }
        if(oneTruck.size() != 0)
            allTrucks.add(oneTruck);

        return allTrucks;
    }

    @Override
    public double computeFitness() {
        //TODO
        this.numberOfBoxes = new ArrayList<>();
        this.totalDistance = 0;
        this.longestDistance = 0;
        int penalty = 0;

        ArrayList<Double> distancePerTruck = new ArrayList<>();
        ArrayList<ArrayList<Integer>> allTrucks = getOrdersForTruck();
        ArrayList<Order> orders = problem.getItems();
        for(int i = 0; i < allTrucks.size();i++){
            if(allTrucks.get(i).size() > 0){

                double distance = problem.getWarehousePosition().distance(orders.get(allTrucks.get(i).get(0)-1).getPosition());
                int boxes = orders.get(allTrucks.get(i).get(0)-1).boxes;
                int j = 1;
                for( ;j < allTrucks.get(i).size(); j++){
                    distance += orders.get(allTrucks.get(i).get(j-1)-1).getPosition().distance(orders.get(allTrucks.get(i).get(j)-1).getPosition());
                    boxes += orders.get(allTrucks.get(i).get(j)-1).boxes;
                }
                distance += orders.get(allTrucks.get(i).get(j-1)-1).getPosition().distance(problem.getWarehousePosition());
                if(this.longestDistance < distance)
                    this.longestDistance = distance;
                if(boxes > problem.getTrucksMaxBoxes())
                    penalty += 1000;
                numberOfBoxes.add(boxes);
                totalDistance += distance;
                distancePerTruck.add(distance);
            }else{
                penalty += 1000;
            }
        }

        double perfectAvg = this.totalDistance / problem.getNumTrucks();
        for(int i = 0;i < distancePerTruck.size();i++){
            if(distancePerTruck.get(i)%perfectAvg >perfectAvg)
                penalty += 0;
        }

        System.out.println(penalty);

        fitness = this.longestDistance + penalty +totalDistance;
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: " + fitness + "\n");
        sb.append("Biggest distance: " + longestDistance + "\n");
        sb.append("Total distance: " + totalDistance + "\n");
        sb.append(numberOfBoxes.toString());
        //TODO
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(ProductDistributionIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public ProductDistributionIndividual clone() {
        return new ProductDistributionIndividual(this);

    }
}