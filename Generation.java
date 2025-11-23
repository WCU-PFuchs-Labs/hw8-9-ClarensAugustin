package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generation {
    private GPTree[] trees;
    private DataSet dataSet;
    private NodeFactory nodeFactory;
    private Random rand;
    private int maxDepth;

    public Generation(int size, int maxDepth, String fileName) throws IOException {
        this.maxDepth = maxDepth;
        this.dataSet = new DataSet(fileName);
        this.rand = new Random();

        // operators
        Binop[] operators = {new Plus(), new Minus(), new Divide()};

        // factory with variables
        this.nodeFactory = new NodeFactory(operators, dataSet.getNumIndependantVariables());

        // array of trees
        this.trees = new GPTree[size];
        for (int i = 0; i < size; i++) {
            trees[i] = new GPTree(nodeFactory, maxDepth, rand);
        }
    }

    public void evalAll() {
        // Fitness for each tree
        for (GPTree tree : trees) {
            tree.evalFitness(dataSet);
        }

        // sorting
        Arrays.sort(trees);
    }

    public ArrayList<GPTree> getTopTen() {
        ArrayList<GPTree> topTen = new ArrayList<>();
        int count = Math.min(10, trees.length);

        for (int i = 0; i < count; i++) {
            topTen.add(trees[i]);
        }
        return topTen;
    }

    public void printBestFitness() {
        System.out.printf("Best Fitness: %.2f\n", trees[0].getFitness());
    }

    public void printBestTree() {
        System.out.println("Best Tree: " + trees[0]);
    }

    public void evolve() {
        GPTree[] newGeneration = new GPTree[trees.length];

        newGeneration[0] = (GPTree) trees[0].clone();

        for (int i = 1; i < trees.length; i+=2) {
            GPTree parent1 = tournamentSelect();
            GPTree parent2 = tournamentSelect();

            GPTree child1 = (GPTree) parent1.clone();
            GPTree child2 = (GPTree) parent2.clone();

            // crossover
            child1.crossover(child2, rand);

            // adding children
            newGeneration[i] = child1;
            if (i + 1 < trees.length) {
                newGeneration[i + 1] = child2;
            }
        }
        // replacing old gen with new
        this.trees = newGeneration;
    }

    // Tournament selection
    private GPTree tournamentSelect() {
        int tournamentSize = 5;
        GPTree best = trees[rand.nextInt(trees.length)];

        for (int i = 1; i < tournamentSize; i++) {
            GPTree competitor = trees[rand.nextInt(trees.length)];
            if (competitor.getFitness() < best.getFitness()) {
                best = competitor;
            }
        }
        return best;
    }
    
}
