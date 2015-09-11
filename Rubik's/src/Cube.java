/**
 * Rubik's Cube
 *
 * Version History:
 *
 * Pre-Alpha
 * 0.1 - Added faces of cube
 * 0.1.1 - Bug fixes
 * 0.1.2 - Bug fixes
 * 0.1.3 - X Axis added
 *
 * @author ZSY
 * @version 0.1.3
 */

public class Cube {

    private Face[] faces = new Face[6];

    public static void main(String args[]) {
        Cube cube = new Cube();
        cube.prepare();
        cube.turn(0, false);
        cube.turn(1, false);
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.println(cube.toString());
    }

    public void prepare() {
        for (int i = 0; i < 6; i++) {
            faces[i] = new Face(i);
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < 6; i++) {
            output += faces[i].toString() + "\n";
        }
        return output;
    }

    //LRUDFB 0 L'
    public void turn(int direction, boolean inverted) {
        if (direction > 5) {
            System.err.println("Error: direction too large");
            return;
        }
        int axis = direction / 2;
        int[] line = new int[4];
        for (int i = 0; i < 4; i++) {

            line[i] = (3 * i + axis + 2) / 2;
        }

        Face[] original = new Face[6];
        for (int i = 0; i < 6; i++) {
            original[i] = new Face(faces[i]);
        }
        int turning = (axis == 0 ? 6 : axis * 2) - 1;
        for (int i = 0; i < 4; i++) {
            int[][] from = original[line[i + (inverted ? (i == 0 ? 3 : -1) : (i == 3 ? -3 : 1))] - 1].copyValues();
            int[][] to = original[line[i] - 1].copyValues();
            if (i == axis) { // for (axis,i): (0,0)(1,1)(2,2)
                for (int j = 0; j < 3; j++) {
                    to[j][direction * 2] = from[j][direction * 2];
                }
            }
            else if (i == axis + 1) { // for (axis,i): (0,1)(1,2)(2,3)
                for (int j = 0; j < 3; j++) {
                    to[j][direction * 2] = from[direction * 2][Math.abs(j - 2)];
                }
            }
            else if (i == axis + 2 || i == axis - 2) { // for (axis,i): (0,2)(1,3)(2,0)
                for (int j = 0; j < 3; j++) {
                    to[direction * 2][Math.abs(j - 2)] = from[direction * 2][Math.abs(j - 2)];
                }
            }
            else if (i == axis + 3) { // for (axis,i): (0,3)
                for (int j = 0; j < 3; j++) {
                    to[direction * 2][Math.abs(j - 2)] = from[j][direction * 2];
                }
            }
            else {
                System.err.println("Unexpected from-to!");

            }
            faces[line[i] - 1].changeValues(to);
        }
        /**
        int[][] from = original[turning].copyValues();
        int[][] to = original[turning].copyValues();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i + j) % 2 == 0) {

                }
            }
        }
        */
    }
}
