package hc;

import algorithms.*;
import ga.geneticOperators.Recombination;

import java.util.Random;

public class HillClimbing<I extends IntVectorIndividual, P extends Problem<I>> extends Algorithm<I,P> {
    //TODO this class might require the definition of additional methods and/or attributes

    public HillClimbing(int maxIterations, Random rand) {
        super(maxIterations, rand);
    }

    @Override
    public I run(P problem) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
