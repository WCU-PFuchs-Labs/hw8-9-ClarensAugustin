

public class Variable extends Unop {
    private int index;

    public Variable(int index){
        this.index = index;
    }

    public double eval (double [] values) {
        return values[index];
    }

    public String toString() {

        return "X" + index;
    }
}


