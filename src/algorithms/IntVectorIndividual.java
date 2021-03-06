package algorithms;

import ga.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class IntVectorIndividual<P extends Problem, I extends IntVectorIndividual> extends Individual<P, I> {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[] genome;

    public IntVectorIndividual(P problem, int size) {
        super(problem);
        size = size-1;
        genome = new int[size];
        for (int i = 0; i < genome.length; i++) {
            genome[i] = i+1;
        }

        for(int i = 0; i< genome.length;i++){
            int save = genome[i];
            int ant = GeneticAlgorithm.random.nextInt(size);
            genome[i] = genome[ant];
            genome[ant] = save;
        }
    }

    public IntVectorIndividual(IntVectorIndividual<P, I> original) {
        super(original);
        this.genome = new int[original.genome.length];
        System.arraycopy(original.genome, 0, genome, 0, genome.length);
    }

    @Override
    public int getNumGenes() {
        return genome.length;
    }

    public int getIndexof(int value){
        for (int i = 0; i < genome.length; i++) {
            if (genome[i] == value)
                return i;
        }
        return -1;
    }

    public ArrayList<Integer> subList(int cut1, int cut2) {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = cut1; i < cut2; i++) {
            values.add(genome[i]);
        }
        return values;
    }

    public int getGene(int index) {
        return genome[index];
    }

    public void setGene(int index, int newValue) {
        genome[index] = newValue;
    }

    @Override
    public void swapGenes(IntVectorIndividual other, int index) {
        int aux = genome[index];
        genome[index] = other.genome[index];
        other.genome[index] = aux;
    }

    public int[] getGenome() {
        return genome;
    }
}
