package productDistribution;

import algorithms.IntVectorIndividual;

import java.util.ArrayList;


public class ProductDistributionIndividual extends IntVectorIndividual<ProductDistributionProblem, ProductDistributionIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes

    public ProductDistributionIndividual(ProductDistributionProblem problem, int size) {
        super(problem, size);
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ProductDistributionIndividual(ProductDistributionIndividual original) {
        super(original);
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    public ArrayList<ArrayList<Integer>> getOrdersForTruck() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public double computeFitness() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
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