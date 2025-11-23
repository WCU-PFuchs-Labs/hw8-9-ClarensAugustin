package src;

import java.util.*;
import java.io.*;

public class TestGPTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter data file name: ");
        String fileName = sc.nextLine();

        try {
            System.out.println("Creating initial Generation of 500 trees");
            Generation generation = new Generation(500, 5, fileName);

            // Evolve for 50 generations
            for (int i = 1; i <= 50; i++) {
                System.out.println("Generation " + i + ":");

                generation.evalAll();
                generation.printBestTree();
                generation.printBestFitness();

                // Evolve to next generation
                if (i < 50) {
                    generation.evolve();
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}