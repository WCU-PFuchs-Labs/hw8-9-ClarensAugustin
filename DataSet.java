package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSet {

    private ArrayList<DataRow> data;
    private int numIndependantVariables;

    public DataSet(String fileName) throws IOException {
        data = new ArrayList<>();
        LoadData(fileName);
    }

    private void LoadData(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // Read header line
        String header = reader.readLine();
        String[] headerParts = header.split(",");

        // Count inputs (total columns - 1 for y)
        numIndependantVariables = headerParts.length - 1;

        // Read data lines
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String [] parts = line.split(",");

            double y = Double.parseDouble(parts[0].trim());

            double [] x = new double[numIndependantVariables];
            for (int i = 1; i < numIndependantVariables; i++){
                x[i] = Double.parseDouble(parts[i+1].trim());
            }

            data.add(new DataRow(x, y));
        }
        reader.close();
    }

    public ArrayList<DataRow> getRows(){
        return data;
    }

    public int getNumIndependantVariables() {

        return numIndependantVariables;
    }

    public int size() {
        return data.size();
    }

}
