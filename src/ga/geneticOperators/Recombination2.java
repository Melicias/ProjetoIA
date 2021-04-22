package ga.geneticOperators;

import ga.GeneticAlgorithm;
import algorithms.IntVectorIndividual;
import algorithms.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public Recombination2(double probability) {
        super(probability);
    }

    private int[] child1, child2;

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];

        int value1 = ind1.getGene(0);
        child1[0] = value1;
        int value2 = ind2.getGene(0);
        child2[0] = value2;
        do{
            child1[ind1.getIndexof(value2)] = value2;
            child2[ind2.getIndexof(value1)] = value1;
            value2 = ind2.getGene(ind1.getIndexof(value2));
            value1 = value2;
        }while(child1[ind1.getIndexof(value2)] != value2);

        for(int i = 0;i<child1.length;i++){
            if(child1[i] == 0)
                child1[i] = ind2.getGene(i);
            if(child2[i] == 0)
                child2[i] = ind1.getGene(i);
        }
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }
    }

    @Override
    public String toString(){
        //TODO
        return "CX";
    }    
}