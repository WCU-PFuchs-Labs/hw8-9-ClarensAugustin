

import java.util.Random;

public class NodeFactory {
    private Binop[] currentOps;
    private int numIndepVars;

    public NodeFactory(Binop [] binops, int numVars) {
        this.numIndepVars = numVars;
        this.currentOps = binops;

    }

    public Node getOperator (Random rand) {
        // Randomly select one of the Binops and return a new Node with a clone of it
        int index = rand.nextInt(currentOps.length);
        Binop clonedOp = (Binop) currentOps[index].clone();
        return new Node(clonedOp);
    }

    public Node getTerminal (Random rand) {
        // Random number between 0 and numIndepVars (inclusive)
        int choice = rand.nextInt(numIndepVars + 1);

        if (choice < numIndepVars) {
            // Return a Variable with index = choice
            return new Node(new Variable(choice));
        } else {
            // Return a Const with random value in [0, 1]
            return new Node(new Const(rand.nextDouble()));
        }
    }

    public int getNumOps() {
        return currentOps.length;
    }

    public int getNumIndepVars() {
        return numIndepVars;
    }
}

