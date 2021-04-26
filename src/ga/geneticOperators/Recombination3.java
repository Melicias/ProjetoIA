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

    private int[] child1, child2;

    @Override
    public void recombine(I ind1, I ind2) {

        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];

        int i1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes()-1)+1;
        int i2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes()-1)+1;
        while(i1 >= i2){
            i1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes()-1)+1;
            i2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes()-1)+1;
        }

        for (int i = i1; i < i2; i++) {
            child1[i] = child2[i];
            child2[i] = child1[i];
        }
        int numGenes = ind1.getNumGenes();

        int indAux = i2;
        while (indAux != i1){
            if (check_forDuplicates(child1, ind2)) {

            }
        }

    }

    private boolean check_forDuplicates(int[] offspring, int indexOfElement) {
        for (int index = 0; index < offspring.length; index++) {
            if ((offspring[index] == offspring[indexOfElement]) &&
                    (indexOfElement != index)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        //TODO
        return "";
    }    
}