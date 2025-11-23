package src;

import java.util.ArrayList;
import java.util.Random;

public class GPTree implements Collector, Comparable<GPTree>, Cloneable {
    private Node root;
    private ArrayList<Node> crossNodes;
    private double fitness;

    GPTree() {
        root = null;
        fitness = Double.MAX_VALUE;
    }


    public GPTree(NodeFactory n, int maxDepth, Random rand) {
        root = n.getOperator(rand);
        root.addRandomKids(n, maxDepth, rand);
        fitness = Double.MAX_VALUE;
    }

    public double eval(double [] data) {
        return root.eval(data);
    }

    // calculating fitness
    public void evalFitness(DataSet dataSet) {
        double sumSquareError = 0;

        for (DataRow row : dataSet.getRows()) {
            // evaluate tree with row inputs
            double predicted = root.eval(row.getIndependentVariables());

            // calculating error
            double error = predicted - row.getDependentVariable();

            // add squared error
            sumSquareError += Math.pow(error, 2);
        }
        this.fitness = sumSquareError;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(GPTree tree) {
        if (this.fitness < tree.fitness) {
            return -1;
        } else if (this.fitness > tree.fitness) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof GPTree)) return false;
        return this.compareTo((GPTree)o) == 0;
    }

    public void traverse(){
        crossNodes = new ArrayList<Node>();
        root.traverse(this);
    }

    @Override
    public Object clone() {
        GPTree cloned = null;
        try {
            cloned = (GPTree) super.clone();
            // deep clone root
            if (this.root != null) {
                cloned.root = (Node) this.root.clone();
            }
            // Clone crossNodes if needed
            if (this.crossNodes != null) {
                cloned.crossNodes = new ArrayList<>();
            }
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported");
        }
        return cloned;
    }

    @Override
    public void collect(Node node) {
        if (!node.isLeaf()) {
            crossNodes.add(node);
        }
    }

    // return a string with all the binop strings

    public String getCrossNodes() {
        StringBuilder string = new StringBuilder();
        int lastIndex = crossNodes.size() -1;
        for (int i = 0; i < lastIndex; ++i){
            Node node = crossNodes.get(i);
            string.append(node.toString());
            string.append(";");
        }
        string.append(crossNodes.get(lastIndex));
        return string.toString();
    }

    // implements left child to left and right child to right
    public void crossover(GPTree tree, Random rand){
        // find the points for crossover
        this.traverse();
        tree.traverse();

        if (this.crossNodes.isEmpty() || tree.crossNodes.isEmpty()) {
            return;
        }
        int thisPoint = rand.nextInt(this.crossNodes.size());
        int treePoint = rand.nextInt(this.crossNodes.size());
        boolean left = rand.nextBoolean();
        // get connection points
        Node thisTrunk = crossNodes.get(thisPoint);
        Node treeTrunk = tree.crossNodes.get(treePoint);

        if(left) {
            thisTrunk.swapLeft(treeTrunk);
        } else {
            thisTrunk.swapRight(treeTrunk);
        }
    }

    public String toString() {
        return root.toString();
    }
}
