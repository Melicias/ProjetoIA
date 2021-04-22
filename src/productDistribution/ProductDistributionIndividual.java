package productDistribution;

import algorithms.IntVectorIndividual;
import sun.lwawt.macosx.CSystemTray;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;


public class ProductDistributionIndividual extends IntVectorIndividual<ProductDistributionProblem, ProductDistributionIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    private int size;


    public ProductDistributionIndividual(ProductDistributionProblem problem, int size) {
        super(problem, size);
        //TODO
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ProductDistributionIndividual(ProductDistributionIndividual original) {
        super(original);
        //nr de caixas e nr de camioes?
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
        if(oneTruck.size() != 0){
            allTrucks.add(oneTruck);
        }
        return allTrucks;
    }

    @Override
    public double computeFitness() {
        //TODO
        ArrayList<ArrayList<Integer>> allTrucks = getOrdersForTruck();
        ArrayList<Double> distancePerTruck = new ArrayList<>();
        double distance = 0;
        double falhas = 0;
        ArrayList<Order> orders = problem.getItems();
        for(int i = 0; i < allTrucks.size();i++){
            if(allTrucks.get(i).size() > 0){
                double distancia = problem.getWarehousePosition().distance(orders.get(allTrucks.get(i).get(0)-1).getPosition());
                int boxes = orders.get(allTrucks.get(i).get(0)-1).boxes;
                int j = 1;
                for( ;j < allTrucks.get(i).size(); j++){
                    distancia += orders.get(j-1).getPosition().distance(orders.get(j).getPosition());
                    boxes += orders.get(j).boxes;
                }
                distancia += orders.get(j-1).getPosition().distance(problem.getWarehousePosition());
                distancePerTruck.add(distancia);
                distance += distancia;
                //acrescentar a distancia para o fitness e retirar pontos caso as caixas sejam superiores que o suportado
                if(boxes > problem.getTrucksMaxBoxes()){
                    distance += 500;
                }
            }else{
                //penalty pq n tem viagens nenhumas
                distance += 500;
            }
        }

        double perfectAvg = distance / problem.getNumTrucks();
        for(int i = 0;i < distancePerTruck.size();i++){
            distance += distancePerTruck.get(i) > perfectAvg ? distancePerTruck.get(i) - perfectAvg : perfectAvg- distancePerTruck.get(i);
        }
        System.out.println(distance);
        fitness = distance + falhas;
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
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