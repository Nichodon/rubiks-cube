/**
 * @author ZSY
 * @version 0.2.1
 */

public class Face {

    private int[][] colors = new int[3][3];

    public Face(int color) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                colors[i][j] = color;
            }
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < 3; i++) {
            output += "[";
            for (int j = 0; j < 3; j++) {
                output += " " + colors[i][j] + " ";
            }
            output += "]\n";
        }
        return output;
    }

    public void changeValues(int[][] values) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                colors[i][j] = values[i][j];
            }
        }
    }

    public int[][] copyValues() {
        int[][] values = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                values[i][j] = colors[i][j];
            }
        }
        return values;
    }

    public Face(Face original) {
        colors = original.copyValues();
    }
}
