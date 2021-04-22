package productDistribution;

import algorithms.IntVectorIndividual;
import sun.lwawt.macosx.CSystemTray;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;


public class ProductDistributionIndividual extends IntVectorIndividual<ProductDistributionProblem, ProductDistributionIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    private int size;
    private double totalDistance;
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
        //TODO
        //throw new UnsupportedOperationException("Not implemented yet.");
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

        ArrayList<ArrayList<Integer>> allTrucks = getOrdersForTruck();
        double biggestDistance = 0;
        int penalty = 0;
        ArrayList<Order> orders = problem.getItems();
        for(int i = 0; i < allTrucks.size();i++){
            if(allTrucks.get(i).size() > 0){
                double distance = problem.getWarehousePosition().distance(orders.get(allTrucks.get(i).get(0)-1).getPosition());
                int boxes = orders.get(allTrucks.get(i).get(0)-1).boxes;
                int j = 1;
                for( ;j < allTrucks.get(i).size(); j++){
                    distance += orders.get(j-1).getPosition().distance(orders.get(j).getPosition());
                    boxes += orders.get(j).boxes;
                }
                distance += orders.get(j-1).getPosition().distance(problem.getWarehousePosition());
                numberOfBoxes.add(boxes);
                if(biggestDistance < distance)
                    biggestDistance = distance;
                totalDistance += distance;
                //se houver mais caixas que camioes, adicionar outra penalizacao
                if(boxes > problem.getTrucksMaxBoxes()){
                    penalty += 500;
                }
            }else{
                //penalty pq n tem viagens nenhumas da uma penalizacao
                penalty += 500;
            }
        }
        fitness = biggestDistance + penalty;
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: " + fitness + "\n");
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