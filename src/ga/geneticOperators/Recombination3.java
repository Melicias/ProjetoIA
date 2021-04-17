package ga.geneticOperators;

import ga.GeneticAlgorithm;
import algorithms.IntVectorIndividual;
import algorithms.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public String toString(){
        //TODO
        return "";
    }    
}