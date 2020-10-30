import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class CraftingWoodenTables {

    public static void main (String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        long C = in.nextLong(); // Crafting a wooden table required C pieces of wood.
        long N = in.nextLong(); // The number of slots in the pocket.
        long P = in.nextLong(); // A pile of wood with at most P pieces of wood.
        long W = in.nextLong(); // The number of pieces of wood at the beginning.

        long result = 0;
        long lastCell = W % P == 0 ? P : W % P; // The number of pieces of wood in the last used slot.
        long usedSlots = W / P + (W % P > 0 ? 1 : 0);

        long availableSlots =  N - usedSlots;

        // While there is enough wood to build and a new table
        // and there might be an available slot for a new table.
        while (W >= C && availableSlots >= 0) {

            // If the number of pieces of wood in the last cell is lower or equal to the cost of a new table.
            if (lastCell <= C) {

                long remaining = C;

                remaining -= lastCell;

                long slotsToUse = remaining / P; // The number of full slots to use in order to build a new table

                availableSlots += slotsToUse;

                remaining -= slotsToUse * P;

                lastCell = P - remaining;

                W -= C;
                result++;
            }
            // If the number of pieces of wood in the last cell is higher then the cost of a new table.
            else { // lastCell > C
                if (availableSlots == 0) {
                    break;
                } else {
                    lastCell -= C;
                    availableSlots--;
                    result++;
                    W -= C;
                }
            }
        }

        if (availableSlots < 0) {
            result--;
        }

        System.out.println(result);
    }

    private static void setStandardInput() {

        String input = "4 3 3 8";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
