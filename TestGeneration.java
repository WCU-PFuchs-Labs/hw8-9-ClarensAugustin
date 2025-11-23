package src;

import java.util.*;
import java.io.*;

public class TestGeneration {
    public static void main(String[] args) {
        if (args.length ==0 ) {
            System.out.println("Usage: java TestGeneration <file>");
            return;
        }

        String fileName = args[0];

        try {
            System.out.println("Creating generation of trees");
            Generation generation = new Generation(500, 5, fileName);

            System.out.println("Evaluating fitness");
            generation.evalAll();

            System.out.println("\nEND PRODUCT");
            generation.printBestTree();
            generation.printBestFitness();

            System.out.println("\nTop Ten Fitness Values: ");
            ArrayList<GPTree> topTen = generation.getTopTen();

            for (int i = 0; i < topTen.size(); i++) {
                System.out.printf("%.2f", topTen.get(i).getFitness());
                if (i < topTen.size() - 1) {
                    System.out.printf(", ");
                }
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }

        sc.close();
    }
}
